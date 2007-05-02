/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 * 			(report 36180: Callers/Callees view)
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.callhierarchy;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.jface.action.Action;


/**
 * Toggles the call direction of the call hierarchy (i.e. toggles between showing callers and callees.)
 */
class ToggleCallModeAction extends Action {

    private CallHierarchyViewPart fView;    
    private int fMode;
    
    public ToggleCallModeAction(CallHierarchyViewPart v, int mode) {
        super("", AS_RADIO_BUTTON); //$NON-NLS-1$
        if (mode == CallHierarchyViewPart.CALL_MODE_CALLERS) {
            setText(CallHierarchyMessages.ToggleCallModeAction_callers_label); 
            setDescription(CallHierarchyMessages.ToggleCallModeAction_callers_description); 
            setToolTipText(CallHierarchyMessages.ToggleCallModeAction_callers_tooltip); 
            DLTKPluginImages.setLocalImageDescriptors(this, "ch_callers.gif"); //$NON-NLS-1$
        } else if (mode == CallHierarchyViewPart.CALL_MODE_CALLEES) {
            setText(CallHierarchyMessages.ToggleCallModeAction_callees_label); 
            setDescription(CallHierarchyMessages.ToggleCallModeAction_callees_description); 
            setToolTipText(CallHierarchyMessages.ToggleCallModeAction_callees_tooltip); 
            DLTKPluginImages.setLocalImageDescriptors(this, "ch_callees.gif"); //$NON-NLS-1$
        } else {
            Assert.isTrue(false);
        }
        fView= v;
        fMode= mode;
        if (DLTKCore.DEBUG) {
			System.err.println("Add help support here...");
		}
		
//        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IJavaHelpContextIds.CALL_HIERARCHY_TOGGLE_CALL_MODE_ACTION);
    }
    
    public int getMode() {
        return fMode;
    }   
    
    /*
     * @see Action#actionPerformed
     */     
    public void run() {
        fView.setCallMode(fMode); // will toggle the checked state
    }
    
}
