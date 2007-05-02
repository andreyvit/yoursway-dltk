/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *          (report 36180: Callers/Callees view)
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.callhierarchy;

import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.ActionGroup;


/**
 * Action group to add the filter action to a view part's toolbar
 * menu.
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 */
public class CallHierarchyFiltersActionGroup extends ActionGroup {

    class ShowFilterDialogAction extends Action {
        ShowFilterDialogAction() {
            setText(CallHierarchyMessages.ShowFilterDialogAction_text); 
            setImageDescriptor(DLTKPluginImages.DESC_ELCL_FILTER);
			setDisabledImageDescriptor(DLTKPluginImages.DESC_DLCL_FILTER);
        }
        
        public void run() {
            openDialog();
        }
    }

    private IViewPart fPart;

    /**
     * Creates a new <code>CustomFiltersActionGroup</code>.
     * 
     * @param part      the view part that owns this action group
     * @param viewer    the viewer to be filtered
     */
    public CallHierarchyFiltersActionGroup(IViewPart part, StructuredViewer viewer) {
        fPart= part;
    }

    /* (non-Javadoc)
     * Method declared on ActionGroup.
     */
    public void fillActionBars(IActionBars actionBars) {
        fillViewMenu(actionBars.getMenuManager());
    }

    private void fillViewMenu(IMenuManager viewMenu) {
        viewMenu.add(new Separator("filters")); //$NON-NLS-1$
        viewMenu.add(new ShowFilterDialogAction());
    }

    /* (non-Javadoc)
     * Method declared on ActionGroup.
     */
    public void dispose() {
        super.dispose();
    }
    
    // ---------- dialog related code ----------

    private void openDialog() {
        FiltersDialog dialog= new FiltersDialog(
            fPart.getViewSite().getShell());
        
        dialog.open();
    }
}
