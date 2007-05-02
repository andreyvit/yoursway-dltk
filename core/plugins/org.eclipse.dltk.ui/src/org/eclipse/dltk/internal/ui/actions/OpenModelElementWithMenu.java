/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.ui.DLTKUILanguageManager;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.internal.ide.DialogUtil;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.part.FileEditorInput;

import com.ibm.icu.text.Collator;

/**
 * A menu for opening model element in the workbench.
 * <p>
 * An <code>OpenWithMenu</code> is used to populate a menu with
 * "Open With" actions.  One action is added for each editor which is applicable
 * to the selected file. If the user selects one of these items, the corresponding
 * editor is opened on the file.
 * </p>
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 */
public class OpenModelElementWithMenu extends ContributionItem {
    private IWorkbenchPage page;

    private IAdaptable element;

    private IEditorRegistry registry = PlatformUI.getWorkbench()
            .getEditorRegistry();

    private static Hashtable imageCache = new Hashtable(11);

    /**
     * The id of this action.
     */
    public static final String ID = PlatformUI.PLUGIN_ID + ".OpenWithMenu";//$NON-NLS-1$

    /**
     * Match both the input and id, so that different types of editor can be opened on the same input.
     */
    private static final int MATCH_BOTH = IWorkbenchPage.MATCH_INPUT | IWorkbenchPage.MATCH_ID;
    
    /*
     * Compares the labels from two IEditorDescriptor objects 
     */
    private static final Comparator comparer = new Comparator() {
        private Collator collator = Collator.getInstance();

        public int compare(Object arg0, Object arg1) {
            String s1 = ((IEditorDescriptor) arg0).getLabel();
            String s2 = ((IEditorDescriptor) arg1).getLabel();
            return collator.compare(s1, s2);
        }
    };

    /**
     * Constructs a new instance of <code>OpenWithMenu</code>. 
     * <p>
     * If this method is used be sure to set the selected file by invoking
     * <code>setFile</code>.  The file input is required when the user
     * selects an item in the menu.  At that point the menu will attempt to
     * open an editor with the file as its input.
     * </p>
     *
     * @param page the page where the editor is opened if an item within
     *		the menu is selected
     */
    public OpenModelElementWithMenu(IWorkbenchPage page) {
        this(page, null);
    }

    /**
     * Constructs a new instance of <code>OpenWithMenu</code>.  
     *
     * @param page the page where the editor is opened if an item within
     *		the menu is selected
     * @param file the selected file
     */
    public OpenModelElementWithMenu(IWorkbenchPage page, IAdaptable element) {
        super(ID);
        this.page = page;
        this.element = element;
    }

    /**
     * Returns an image to show for the corresponding editor descriptor.
     *
     * @param editorDesc the editor descriptor, or null for the system editor
     * @return the image or null
     */
    private Image getImage(IEditorDescriptor editorDesc) {
        ImageDescriptor imageDesc = getImageDescriptor(editorDesc);
        if (imageDesc == null) {
            return null;
        }
        Image image = (Image) imageCache.get(imageDesc);
        if (image == null) {
            image = imageDesc.createImage();
            imageCache.put(imageDesc, image);
        }
        return image;
    }

    /**
     * Returns the image descriptor for the given editor descriptor,
     * or null if it has no image.
     */
    private ImageDescriptor getImageDescriptor(IEditorDescriptor editorDesc) {
        ImageDescriptor imageDesc = null;
        if (editorDesc == null) {
            imageDesc = registry
                    .getImageDescriptor(getFileResource().getName());
			//TODO: is this case valid, and if so, what are the implications for content-type editor bindings?
        } else {
            imageDesc = editorDesc.getImageDescriptor();
        }
        if (imageDesc == null) {
            if (editorDesc.getId().equals(
                    IEditorRegistry.SYSTEM_EXTERNAL_EDITOR_ID)) {
				imageDesc = registry
                        .getSystemExternalEditorImageDescriptor(getFileResource()
                                .getName());
			}
        }
        return imageDesc;
    }

    /**
     * Creates the menu item for the editor descriptor.
     *
     * @param menu the menu to add the item to
     * @param descriptor the editor descriptor, or null for the system editor
     * @param preferredEditor the descriptor of the preferred editor, or <code>null</code>
     */
    private void createMenuItem(Menu menu, final IEditorDescriptor descriptor,
            final IEditorDescriptor preferredEditor) {
        // XXX: Would be better to use bold here, but SWT does not support it.
        final MenuItem menuItem = new MenuItem(menu, SWT.RADIO);
        boolean isPreferred = preferredEditor != null
                && descriptor.getId().equals(preferredEditor.getId());
        menuItem.setSelection(isPreferred);
        menuItem.setText(descriptor.getLabel());
        Image image = getImage(descriptor);
        if (image != null) {
            menuItem.setImage(image);
        }
        Listener listener = new Listener() {
            public void handleEvent(Event event) {
                switch (event.type) {
                case SWT.Selection:
                    if (menuItem.getSelection()) {
						openEditor(descriptor);
					}
                    break;
                }
            }
        };
        menuItem.addListener(SWT.Selection, listener);
    }
    
    IEditorDescriptor getDefaultEditor () {
    	IEditorDescriptor desc = null;
    	IModelElement elem = getModelElement();
        if (elem != null) {
        	String editorID = null;
			try {
				IDLTKUILanguageToolkit toolkit = DLTKUILanguageManager.getLanguageToolkit(elem);
				editorID = toolkit.getEditorID(elem);				
			} catch (CoreException e) {				
			}
			if (editorID != null) {
				desc = registry.findEditor(editorID);
			}
        }
        if (desc != null)
        	return desc;
    	
    	IFile file = getFileResource();
        if (file != null) {
        	return IDE.getDefaultEditor(file);
        }
        
    	return null;
    }

    /* (non-Javadoc)
     * Fills the menu with perspective items.
     */
    public void fill(Menu menu, int index) {
        IFile file = getFileResource();
        if (file == null) {
            return;
        }
        
        

        IEditorDescriptor defaultEditor = registry
                .findEditor(IDEWorkbenchPlugin.DEFAULT_TEXT_EDITOR_ID); // may be null
        IEditorDescriptor preferredEditor = getDefaultEditor (); // may be null
        
        Object[] editors = registry.getEditors(file.getName(), IDE.getContentType(file));
        Collections.sort(Arrays.asList(editors), comparer);

        boolean defaultFound = false;

        //Check that we don't add it twice. This is possible
        //if the same editor goes to two mappings.
        ArrayList alreadyMapped = new ArrayList();
        
        if (preferredEditor != null) {
        	createMenuItem(menu, preferredEditor, preferredEditor);
        	if (defaultEditor != null
                    && preferredEditor.getId().equals(defaultEditor.getId())) {
				defaultFound = true;
			}			
            alreadyMapped.add(preferredEditor);
        }

        for (int i = 0; i < editors.length; i++) {
            IEditorDescriptor editor = (IEditorDescriptor) editors[i];
            if (!alreadyMapped.contains(editor)) {
                createMenuItem(menu, editor, preferredEditor);
                if (defaultEditor != null
                        && editor.getId().equals(defaultEditor.getId())) {
					defaultFound = true;
				}
                alreadyMapped.add(editor);
            }
        }
        
        
        

        // Only add a separator if there is something to separate
        if (editors.length > 0) {
			new MenuItem(menu, SWT.SEPARATOR);
		}
//      Add default editor. Check it if it is saved as the preference.
        if (!defaultFound && defaultEditor != null) {
            createMenuItem(menu, defaultEditor, preferredEditor);
        }

        // Add system editor (should never be null)
        IEditorDescriptor descriptor = registry
                .findEditor(IEditorRegistry.SYSTEM_EXTERNAL_EDITOR_ID);
        createMenuItem(menu, descriptor, preferredEditor);

//        // Add system in-place editor (can be null)
//        descriptor = registry
//                .findEditor(IEditorRegistry.SYSTEM_INPLACE_EDITOR_ID);
//        if (descriptor != null) {
//            createMenuItem(menu, descriptor, preferredEditor);
//        }
        createDefaultMenuItem(menu, file);
    }
	

    private IModelElement getModelElement() {
        if (this.element instanceof IModelElement) {
            return (IModelElement) element;
        }        
       
        return null;
    }
    
    /**
     * Converts the IAdaptable file to IFile or null.
     */
    private IFile getFileResource() {
    	IResource resource = (IResource) this.element
                .getAdapter(IResource.class);
        if (resource instanceof IFile) {
            return (IFile) resource;
        }
       
        return null;
    }

    /* (non-Javadoc)
     * Returns whether this menu is dynamic.
     */
    public boolean isDynamic() {
        return true;
    }

    /**
     * Opens the given editor on the selected file.
     *
     * @param editor the editor descriptor, or null for the system editor
     */
    private void openEditor(IEditorDescriptor editor) {
        IFile file = getFileResource();
        if (file == null) {
            return;
        }
        try {
            String editorId = editor == null ? IEditorRegistry.SYSTEM_EXTERNAL_EDITOR_ID
                    : editor.getId();
            ((WorkbenchPage) page).openEditor(new FileEditorInput(file), editorId, true, MATCH_BOTH);
            // only remember the default editor if the open succeeds
            IDE.setDefaultEditor(file, editorId);
        } catch (PartInitException e) {
            DialogUtil.openError(page.getWorkbenchWindow().getShell(),
                    IDEWorkbenchMessages.OpenWithMenu_dialogTitle,
                    e.getMessage(), e);
        }
    }

    /**
     * Creates the menu item for clearing the current selection.
     *
     * @param menu the menu to add the item to
     * @param file the file being edited
     */
    private void createDefaultMenuItem(Menu menu, final IFile file) {
    	final IEditorDescriptor desc = getDefaultEditor ();
    	if( desc == null ) {
    		return;
    	}
        final MenuItem menuItem = new MenuItem(menu, SWT.RADIO);
        menuItem.setSelection(desc == null);
        menuItem.setText(IDEWorkbenchMessages.DefaultEditorDescription_name);

        Listener listener = new Listener() {
            public void handleEvent(Event event) {
                switch (event.type) {
                case SWT.Selection:
                    if (menuItem.getSelection()) {
                        IDE.setDefaultEditor(file, null);
                        try {
                        	IEditorDescriptor desc = IDE.getEditorDescriptor(file);
                            page.openEditor(new FileEditorInput(file), desc.getId(), true, MATCH_BOTH);
                        } catch (PartInitException e) {
                            DialogUtil.openError(page.getWorkbenchWindow()
                                    .getShell(), IDEWorkbenchMessages.OpenWithMenu_dialogTitle,
                                    e.getMessage(), e);
                        }
                    }
                    break;
                }
            }
        };

        menuItem.addListener(SWT.Selection, listener);
    }
}
