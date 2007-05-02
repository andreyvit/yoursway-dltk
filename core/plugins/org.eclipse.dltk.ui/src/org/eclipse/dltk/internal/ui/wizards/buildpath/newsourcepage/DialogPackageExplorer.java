/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.buildpath.BuildpathModifier;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.internal.ui.StandardModelElementContentProvider;
import org.eclipse.dltk.internal.ui.filters.LibraryFilter;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.internal.ui.wizards.buildpath.BPListElementAttribute;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.DialogPackageExplorerActionGroup.DialogExplorerActionContext;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.ModelElementSorter;
import org.eclipse.dltk.ui.ScriptElementImageProvider;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.dltk.ui.viewsupport.AppearanceAwareLabelProvider;
import org.eclipse.dltk.ui.viewsupport.DecoratingModelLabelProvider;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;


/**
 * A package explorer widget that can be used in dialogs. It uses its own 
 * content provider, label provider, element sorter and filter to display
 * elements that are not shown usually in the package explorer of the
 * workspace.
 */
public abstract class DialogPackageExplorer implements IMenuListener, ISelectionChangedListener {
    /**
     * A extended content provider for the package explorer which can additionally display
     * an output folder item.
     */
    private final class PackageContentProvider extends StandardModelElementContentProvider {
        public PackageContentProvider() {
            super();
        }
        
        /**
         * Get the elements of the current project
         * 
         * @param element the element to get the children from, will
         * not be used, instead the project childrens are returned directly
         * @return returns the children of the project
         */
        public Object[] getElements(Object element) {
            if (fCurrJProject == null)
                return new Object[0];
            return new Object[] {fCurrJProject};
        }
    }    
    /**
     * A extended label provider for the package explorer which can additionally display
     * an output folder item.
     */
    private final class PackageLabelProvider extends AppearanceAwareLabelProvider {
        
        public PackageLabelProvider(long textFlags, int imageFlags, IPreferenceStore store) {
            super(textFlags, imageFlags, store);
        }
        
        public String getText(Object element) {
            String text= super.getText(element);
            try {
                if (element instanceof IProjectFragment) {
                    IProjectFragment root= (IProjectFragment)element;
                    if (root.exists() && BuildpathModifier.filtersSet(root)) {
                        IBuildpathEntry entry= root.getRawBuildpathEntry();
                        int excluded= entry.getExclusionPatterns().length;
                        if (excluded == 1)
                            return Messages.format(NewWizardMessages.DialogPackageExplorer_LabelProvider_SingleExcluded, text); 
                        else if (excluded > 1)
                            return Messages.format(NewWizardMessages.DialogPackageExplorer_LabelProvider_MultiExcluded, new Object[] {text, new Integer(excluded)}); 
                    }
                }
                if (element instanceof IDLTKProject) {
                    IDLTKProject project= (IDLTKProject)element;
                    if (project.exists() && project.isOnBuildpath(project)) {
                        IProjectFragment root= project.findProjectFragment(project.getPath());
                        if (BuildpathModifier.filtersSet(root)) {
                            IBuildpathEntry entry= root.getRawBuildpathEntry();
                            int excluded= entry.getExclusionPatterns().length;
                            if (excluded == 1)
                                return Messages.format(NewWizardMessages.DialogPackageExplorer_LabelProvider_SingleExcluded, text); 
                            else if (excluded > 1)
                                return Messages.format(NewWizardMessages.DialogPackageExplorer_LabelProvider_MultiExcluded, new Object[] {text, new Integer(excluded)}); 
                        }
                    }
                }
                if (element instanceof IFile || element instanceof IFolder) {
                    IResource resource= (IResource)element;
                        if (resource.exists() && BuildpathModifier.isExcluded(resource, fCurrJProject))
                            return Messages.format(NewWizardMessages.DialogPackageExplorer_LabelProvider_Excluded, text); 
                }
            } catch (ModelException e) {
                DLTKUIPlugin.log(e);
            }
            return text;
        }
        

        public Color getForeground(Object element) {
            try {
                if (element instanceof IProjectFragment) {
                    IProjectFragment root= (IProjectFragment)element;
                    if (root.exists() && BuildpathModifier.filtersSet(root))
                        return getBlueColor();
                }
                if (element instanceof IDLTKProject) {
                    IDLTKProject project= (IDLTKProject)element;
                    if (project.exists() && project.isOnBuildpath(project)) {
                        IProjectFragment root= project.findProjectFragment(project.getPath());
                        if (root != null && BuildpathModifier.filtersSet(root))
                            return getBlueColor();
                    }
                }
                if (element instanceof IFile || element instanceof IFolder) {
                    IResource resource= (IResource)element;
                    if (resource.exists() && BuildpathModifier.isExcluded(resource, fCurrJProject))
                        return getBlueColor();
                } 
            } catch (ModelException e) {
                DLTKUIPlugin.log(e);
            }
            return null;
        }
        
        private Color getBlueColor() {
            return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
        }
        
        public Image getImage(Object element) {      
            return super.getImage(element);
        }
        
        public void dispose() {
            super.dispose();
        }

		protected ImageDescriptor getSourceModuleIcon(IModelElement element, int renderFlags) {
			if (DLTKCore.DEBUG) {
				System.err.println("Dialog package explorer label provider returns ghost for source modules...");
			}
			return DLTKPluginImages.DESC_OBJS_GHOST;
		}
    }
    
    /**
     * A extended element sorter for the package explorer which displays the output
     * folder (if any) as first child of a source folder. The other script elements
     * are sorted in the normal way.
     */
    private final class ExtendedModelElementSorter extends ModelElementSorter {
        public ExtendedModelElementSorter() {
            super();
        }
        
        public int compare(Viewer viewer, Object e1, Object e2) {
            if (e1 instanceof BPListElementAttribute)
                return -1;
            if (e2 instanceof BPListElementAttribute)
                return 1;
            return super.compare(viewer, e1, e2);
        }
    }
    
    /**
     * A extended filter for the package explorer which filters libraries and
     * files if their name is either ".Buildpath" or ".project".
     */
    private final class PackageFilter extends LibraryFilter {        
        public boolean select(Viewer viewer, Object parentElement, Object element) {
            try {
                if (element instanceof IFile) {
                    IFile file= (IFile) element;
                    if (file.getName().equals(".buildpath") || file.getName().equals(".project")) //$NON-NLS-1$//$NON-NLS-2$
                        return false;
                }
                if (element instanceof IProjectFragment) {
                    IBuildpathEntry cpe= ((IProjectFragment)element).getRawBuildpathEntry();
                    if (cpe == null || cpe.getEntryKind() == IBuildpathEntry.BPE_CONTAINER)
                        return false;
                }
            } catch (ModelException e) {
                DLTKUIPlugin.log(e);
            }
//            if (element instanceof IProjectFragment) {
//                IProjectFragment root= (IProjectFragment)element;
//                if (root.getElementName().endsWith(".zip") ) //$NON-NLS-1$ //$NON-NLS-2$
//                    return false;
//            }
//            return super.select(viewer, parentElement, element);// &&*/ fOutputFolderFilter.select(viewer, parentElement, element);            
            return true;
        }
    }
    
    /** The tree showing the project like in the package explorer */
    private TreeViewer fPackageViewer;
    /** The tree's context menu */
    private Menu fContextMenu;
    /** The action group which is used to fill the context menu. The action group
     * is also called if the selection on the tree changes */
    private DialogPackageExplorerActionGroup fActionGroup;

    /** Stores the current selection in the tree 
     * @see #getSelection()
     */
    private IStructuredSelection fCurrentSelection;
    
    /** The current script project
     * @see #setInput(IDLTKProject)
     */
    private IDLTKProject fCurrJProject;
    
    public DialogPackageExplorer() {
        fActionGroup= null;
        fCurrJProject= null;
        fCurrentSelection= new StructuredSelection();
    }
    
    public Control createControl(Composite parent) {
        fPackageViewer= new TreeViewer(parent, SWT.MULTI);
        if (DLTKCore.DEBUG) {
			System.err.println("Add comparer");
		}
        //fPackageViewer.setComparer(WorkingSetModel.COMPARER);
        fPackageViewer.addFilter(new PackageFilter());
        fPackageViewer.setSorter(new ExtendedModelElementSorter());
        fPackageViewer.addDoubleClickListener(new IDoubleClickListener() {
            public void doubleClick(DoubleClickEvent event) {
                Object element= ((IStructuredSelection)event.getSelection()).getFirstElement();
                if (fPackageViewer.isExpandable(element)) {
                    fPackageViewer.setExpandedState(element, !fPackageViewer.getExpandedState(element));
                } 
            }
        });
        fPackageViewer.addSelectionChangedListener(this);
        
        MenuManager menuMgr= new MenuManager("#PopupMenu"); //$NON-NLS-1$
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(this);
        fContextMenu= menuMgr.createContextMenu(fPackageViewer.getTree());
        fPackageViewer.getTree().setMenu(fContextMenu);
        parent.addDisposeListener(new DisposeListener() {
            public void widgetDisposed(DisposeEvent e) {
                fContextMenu.dispose();
            }
        });
        
        return fPackageViewer.getControl();
    }
    
    /**
     * Sets the action group for the package explorer.
     * The action group is necessary to populate the 
     * context menu with available actions. If no 
     * context menu is needed, then this method does not 
     * have to be called.
     * 
     * Should only be called once.
     *  
     * @param actionGroup the action group to be used for 
     * the context menu.
     */
    public void setActionGroup(final DialogPackageExplorerActionGroup actionGroup) {
        fActionGroup= actionGroup;
        fPackageViewer.getControl().addDisposeListener(new DisposeListener() {
            public void widgetDisposed(DisposeEvent e) {
                if (actionGroup != null)
                    actionGroup.dispose();
            }
        });
    }
    
    /**
     * Populate the context menu with the necessary actions.
     * 
     * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
     */
    public void menuAboutToShow(IMenuManager manager) {
        if (fActionGroup == null) // no context menu
            return;
        DLTKUIPlugin.createStandardGroups(manager);
        fActionGroup.fillContextMenu(manager);
    }
    
    /**
     * Set the content and label provider of the
     * <code>fPackageViewer</code>
     */
    public void setContentProvider() {
		PackageContentProvider contentProvider= new PackageContentProvider();
		PackageLabelProvider labelProvider= new PackageLabelProvider(AppearanceAwareLabelProvider.DEFAULT_TEXTFLAGS | ScriptElementLabels.P_COMPRESSED,
				AppearanceAwareLabelProvider.DEFAULT_IMAGEFLAGS | ScriptElementImageProvider.SMALL_ICONS, getPreferenceStore());
		fPackageViewer.setContentProvider(contentProvider);
		fPackageViewer.setLabelProvider(new DecoratingModelLabelProvider(labelProvider, false));
    }
    
    protected abstract IPreferenceStore getPreferenceStore();

	/**
     * Set the input for the package explorer.
     * 
     * @param project the project to be displayed
     */
    public void setInput(IDLTKProject project) {
        fCurrJProject= project;
        fPackageViewer.setInput(new Object());
        IStructuredSelection selection= new StructuredSelection(project);
        fPackageViewer.setSelection(selection);
        fPackageViewer.expandToLevel(2);
        fCurrentSelection= selection;
        try {
            if (fActionGroup != null)
                fActionGroup.refresh(new DialogExplorerActionContext(fCurrentSelection, fCurrJProject));
        } catch (ModelException e) {
            DLTKUIPlugin.log(e);
        }
    }
    
    /**
     * Refresh the project tree
     */
    public void refresh() {
        fPackageViewer.refresh(true);
    }
    
    /**
     * Set the selection and focus to the list of elements
     * @param elements the object to be selected and displayed
     */
    public void setSelection(List elements) {
        if (elements == null || elements.size() == 0)
            return;
        fPackageViewer.refresh();
        IStructuredSelection selection= new StructuredSelection(elements);
        fPackageViewer.setSelection(selection, true);
        fPackageViewer.getTree().setFocus();
        
        if (elements.size() == 1 && elements.get(0) instanceof IDLTKProject)
            fPackageViewer.expandToLevel(elements.get(0), 1);
    }
    
    /**
     * The current list of selected elements. The 
     * list may be empty if no element is selected.
     * 
     * @return the current selection
     */
    public IStructuredSelection getSelection() {
        return fCurrentSelection;
    }
    
    /**
     * Get the viewer's control
     * 
     * @return the viewers control
     */
    public Control getViewerControl() {
        return fPackageViewer.getControl();
    }


    /**
     * Inform the <code>fActionGroup</code> about the selection change and store the 
     * latest selection.
     * 
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     * @see DialogPackageExplorerActionGroup#setContext(DialogExplorerActionContext)
     */
    public void selectionChanged(SelectionChangedEvent event) {
        fCurrentSelection= (IStructuredSelection)event.getSelection();
        try {
            if (fActionGroup != null)
                fActionGroup.setContext(new DialogExplorerActionContext(fCurrentSelection, fCurrJProject));
        } catch (ModelException e) {
            DLTKUIPlugin.log(e);
        }
    }
}
