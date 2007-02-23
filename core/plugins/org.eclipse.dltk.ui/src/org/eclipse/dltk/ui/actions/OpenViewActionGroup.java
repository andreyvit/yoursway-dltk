/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.ui.actions;

import org.eclipse.dltk.internal.ui.callhierarchy.OpenCallHierarchyAction;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.ui.IContextMenuConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.dialogs.PropertyDialogAction;
import org.eclipse.ui.part.Page;
import org.eclipse.ui.texteditor.IWorkbenchActionDefinitionIds;


/**
 * Action group that adds actions to open a new DLTK view part or an external 
 * viewer to a context menu and the global menu bar.
 * 
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 * 
	 *
 */
public class OpenViewActionGroup extends ActionGroup {

    private boolean fEditorIsOwner;
	private boolean fIsTypeHiararchyViewerOwner;
    private boolean fIsCallHiararchyViewerOwner;
    
	private ISelectionProvider fSelectionProvider;

	//private OpenSuperImplementationAction fOpenSuperImplementation;
	//private OpenExternalJavadocAction fOpenExternalJavadoc;
//	private OpenTypeHierarchyAction fOpenTypeHierarchy;
    private OpenCallHierarchyAction fOpenCallHierarchy;
	private PropertyDialogAction fOpenPropertiesDialog;
	
	boolean disableCallHierarcy = false;

	/**
	 * Creates a new <code>OpenActionGroup</code>. The group requires
	 * that the selection provided by the page's selection provider is 
	 * of type {@link IStructuredSelection}.
	 * 
	 * @param page the page that owns this action group
	 */
	public OpenViewActionGroup(Page page) {
		createSiteActions(page.getSite(), null);
	}
	
	/**
	 * Creates a new <code>OpenActionGroup</code>. The group requires
	 * that the selection provided by the given selection provider is 
	 * of type {@link IStructuredSelection}.
	 * 
	 * @param page the page that owns this action group
	 * @param selectionProvider the selection provider used instead of the
	 *  page selection provider.
	 * 
	 *
	 */
	public OpenViewActionGroup(Page page, ISelectionProvider selectionProvider) {
		createSiteActions(page.getSite(), selectionProvider);
	}
	
	/**
	 * Creates a new <code>OpenActionGroup</code>. The group requires
	 * that the selection provided by the part's selection provider is 
	 * of type {@link IStructuredSelection}.
	 * 
	 * @param part the view part that owns this action group
	 */
	public OpenViewActionGroup(IViewPart part) {
		this(part, null);		
	}
	
	/**
	 * Creates a new <code>OpenActionGroup</code>. The group requires
	 * that the selection provided by the given selection provider is of type
	 * {@link IStructuredSelection}.
	 * 
	 * @param part the view part that owns this action group
	 * @param selectionProvider the selection provider used instead of the
	 *  page selection provider.
	 *  
	 *
	 */
	public OpenViewActionGroup(IViewPart part, ISelectionProvider selectionProvider) {
		createSiteActions(part.getSite(), selectionProvider);
		// we do a name check here to avoid class loading. 
		String partName= part.getClass().getName();
		fIsTypeHiararchyViewerOwner= "org.eclipse.dltk.internal.ui.typehierarchy.TypeHierarchyViewPart".equals(partName); //$NON-NLS-1$
		fIsCallHiararchyViewerOwner= "org.eclipse.dltk.internal.ui.callhierarchy.CallHierarchyViewPart".equals(partName); //$NON-NLS-1$
	}
	
	/**
	 * Creates a new <code>OpenActionGroup</code>. The group requires
	 * that the selection provided by the given selection provider is of type 
	 * {@link IStructuredSelection}.
	 * 
	 * @param site the site that will own the action group.
	 * @param selectionProvider the selection provider used instead of the
	 *  page selection provider.
	 *  
	 *
	 */
	public OpenViewActionGroup(IWorkbenchSite site, ISelectionProvider selectionProvider) {
		createSiteActions(site, selectionProvider);
	}
	
	/**
	 * Note: This constructor is for internal use only. Clients should not call this constructor.
	 * @param part the editor part
	 */
	public OpenViewActionGroup(ScriptEditor part) {
		fEditorIsOwner= true;
		
		if( part.getCallHierarchyID() == null ) {
			disableCallHierarcy = true;
		}

		//fOpenSuperImplementation= new OpenSuperImplementationAction(part);
		//fOpenSuperImplementation.setActionDefinitionId(IScriptEditorActionDefinitionIds.OPEN_SUPER_IMPLEMENTATION);
		//part.setAction("OpenSuperImplementation", fOpenSuperImplementation); //$NON-NLS-1$

		//fOpenExternalJavadoc= new OpenExternalJavadocAction(part);
		//fOpenExternalJavadoc.setActionDefinitionId(IScriptEditorActionDefinitionIds.OPEN_EXTERNAL_JAVADOC);
		//part.setAction("OpenExternalJavadoc", fOpenExternalJavadoc); //$NON-NLS-1$

//		fOpenTypeHierarchy= new OpenTypeHierarchyAction(part);
//		fOpenTypeHierarchy.setActionDefinitionId(IDLTKEditorActionDefinitionIds.OPEN_TYPE_HIERARCHY);
//		part.setAction("OpenTypeHierarchy", fOpenTypeHierarchy); //$NON-NLS-1$
		if( !disableCallHierarcy ) {
			fOpenCallHierarchy= new OpenCallHierarchyAction(part);
			fOpenCallHierarchy.setActionDefinitionId(IDLTKEditorActionDefinitionIds.OPEN_CALL_HIERARCHY);
			part.setAction("OpenCallHierarchy", fOpenCallHierarchy); //$NON-NLS-1$
		}

		initialize(part.getEditorSite().getSelectionProvider());
	}
	
	private void createSiteActions(IWorkbenchSite site, ISelectionProvider specialProvider) {
//		fOpenSuperImplementation= new OpenSuperImplementationAction(site);
//		fOpenSuperImplementation.setActionDefinitionId(IScriptEditorActionDefinitionIds.OPEN_SUPER_IMPLEMENTATION);
//		fOpenSuperImplementation.setSpecialSelectionProvider(specialProvider);
//		
//		fOpenExternalJavadoc= new OpenExternalJavadocAction(site);
//		fOpenExternalJavadoc.setActionDefinitionId(IScriptEditorActionDefinitionIds.OPEN_EXTERNAL_JAVADOC);
//		fOpenExternalJavadoc.setSpecialSelectionProvider(specialProvider);
//
//		fOpenTypeHierarchy= new OpenTypeHierarchyAction(site);
//		fOpenTypeHierarchy.setActionDefinitionId(IDLTKEditorActionDefinitionIds.OPEN_TYPE_HIERARCHY);
//		fOpenTypeHierarchy.setSpecialSelectionProvider(specialProvider);

		if( !disableCallHierarcy ) {
			fOpenCallHierarchy= new OpenCallHierarchyAction(site);
			fOpenCallHierarchy.setActionDefinitionId(IDLTKEditorActionDefinitionIds.OPEN_CALL_HIERARCHY);
			fOpenCallHierarchy.setSpecialSelectionProvider(specialProvider);
		}

        ISelectionProvider provider= specialProvider != null ? specialProvider : site.getSelectionProvider();
        
        if(getShowProperties()) {
	        fOpenPropertiesDialog= new PropertyDialogAction(site, provider);
	        fOpenPropertiesDialog.setActionDefinitionId(IWorkbenchActionDefinitionIds.PROPERTIES);
        }
		
        initialize(provider);
	}

	private void initialize(ISelectionProvider provider) {
		fSelectionProvider= provider;
		ISelection selection= provider.getSelection();
//		fOpenSuperImplementation.update(selection);
//		fOpenExternalJavadoc.update(selection);
//		fOpenTypeHierarchy.update(selection);
		if( !disableCallHierarcy ) {
			fOpenCallHierarchy.update(selection);
		}
		if (!fEditorIsOwner) {
			if(getShowProperties()) {
				if (selection instanceof IStructuredSelection) {
					IStructuredSelection ss= (IStructuredSelection)selection;
					fOpenPropertiesDialog.selectionChanged(ss);
				} else {
					fOpenPropertiesDialog.selectionChanged(selection);
				}
			}
//			provider.addSelectionChangedListener(fOpenSuperImplementation);
//			provider.addSelectionChangedListener(fOpenExternalJavadoc);
//			provider.addSelectionChangedListener(fOpenTypeHierarchy);
			if( !disableCallHierarcy ) {
				provider.addSelectionChangedListener(fOpenCallHierarchy);
			}
			// no need to register the open properties dialog action since it registers itself
		}
	}

	/* (non-Javadoc)
	 * Method declared in ActionGroup
	 */
	public void fillActionBars(IActionBars actionBar) {
		super.fillActionBars(actionBar);
		setGlobalActionHandlers(actionBar);
	}
	
	/* (non-Javadoc)
	 * Method declared in ActionGroup
	 */
	public void fillContextMenu(IMenuManager menu) {
		super.fillContextMenu(menu);
//		if (!fIsTypeHiararchyViewerOwner)
//			appendToGroup(menu, fOpenTypeHierarchy);
        if (!fIsCallHiararchyViewerOwner) {
        	if( !disableCallHierarcy ) {
        		appendToGroup(menu, fOpenCallHierarchy);
        	}
        }
		IStructuredSelection selection= getStructuredSelection();
		if (getShowProperties() && fOpenPropertiesDialog != null && fOpenPropertiesDialog.isEnabled() && selection != null &&fOpenPropertiesDialog.isApplicableForSelection(selection))
			menu.appendToGroup(IContextMenuConstants.GROUP_PROPERTIES, fOpenPropertiesDialog);
	}

	/*
	 * @see ActionGroup#dispose()
	 */
	public void dispose() {
//		fSelectionProvider.removeSelectionChangedListener(fOpenSuperImplementation);
//		fSelectionProvider.removeSelectionChangedListener(fOpenExternalJavadoc);
//		fSelectionProvider.removeSelectionChangedListener(fOpenTypeHierarchy);
		if( !disableCallHierarcy ) {
			fSelectionProvider.removeSelectionChangedListener(fOpenCallHierarchy);
		}
		super.dispose();
	}
	
	private void setGlobalActionHandlers(IActionBars actionBars) {
//		actionBars.setGlobalActionHandler(DLTKActionConstants.OPEN_SUPER_IMPLEMENTATION, fOpenSuperImplementation);
//		actionBars.setGlobalActionHandler(DLTKActionConstants.OPEN_EXTERNAL_JAVA_DOC, fOpenExternalJavadoc);
//		actionBars.setGlobalActionHandler(DLTKActionConstants.OPEN_TYPE_HIERARCHY, fOpenTypeHierarchy);
		if( !disableCallHierarcy ) {
			actionBars.setGlobalActionHandler(DLTKActionConstants.OPEN_CALL_HIERARCHY, fOpenCallHierarchy);
		}
        
        if (!fEditorIsOwner && getShowProperties())
        	actionBars.setGlobalActionHandler(ActionFactory.PROPERTIES.getId(), fOpenPropertiesDialog);		
	}
	
	private void appendToGroup(IMenuManager menu, IAction action) {
		if (action.isEnabled())
			menu.appendToGroup(IContextMenuConstants.GROUP_OPEN, action);
	}
	
	private IStructuredSelection getStructuredSelection() {
		ISelection selection= getContext().getSelection();
		if (selection instanceof IStructuredSelection)
			return (IStructuredSelection)selection;
		return null;
	}
	
	/**
	 * Note: This method is for internal use only.
	 * As specified in the class documentation, this class should not be subclassed by clients.
	 */
	protected boolean getShowProperties() {
		return true;
	}
}
