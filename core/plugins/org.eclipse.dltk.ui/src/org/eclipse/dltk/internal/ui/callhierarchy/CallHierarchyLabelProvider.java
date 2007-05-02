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

import java.util.Collection;

import org.eclipse.dltk.internal.corext.callhierarchy.MethodWrapper;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.ScriptElementImageProvider;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.dltk.ui.viewsupport.AppearanceAwareLabelProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.model.IWorkbenchAdapter;


class CallHierarchyLabelProvider extends AppearanceAwareLabelProvider {
//	private final static long FULLY_QUALIFIED = new Long(ScriptElementLabels.F_FULLY_QUALIFIED | ScriptElementLabels.M_FULLY_QUALIFIED | ScriptElementLabels.I_FULLY_QUALIFIED | ScriptElementLabels.T_FULLY_QUALIFIED ).longValue();
//	
//    private static final long TEXTFLAGS= DEFAULT_TEXTFLAGS | FULLY_QUALIFIED | ScriptElementLabels.P_POST_QUALIFIED | ScriptElementLabels.P_COMPRESSED;// | ScriptElementLabels.M_FULLY_QUALIFIED | ScriptElementLabels.PREPEND_ROOT_PATH;
//    private static final int IMAGEFLAGS= DEFAULT_IMAGEFLAGS | ScriptElementImageProvider.SMALL_ICONS;
	private static final long TEXTFLAGS= DEFAULT_TEXTFLAGS | ScriptElementLabels.ALL_POST_QUALIFIED | ScriptElementLabels.P_COMPRESSED;
    private static final int IMAGEFLAGS= DEFAULT_IMAGEFLAGS | ScriptElementImageProvider.SMALL_ICONS;

    private ILabelDecorator fDecorator;
    
    CallHierarchyLabelProvider() {
        super(TEXTFLAGS, IMAGEFLAGS, DLTKUIPlugin.getDefault().getPreferenceStore());
        fDecorator= new CallHierarchyLabelDecorator();
    }
    /**
     * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
     */
    public Image getImage(Object element) {
        Image result= null;
        if (element instanceof MethodWrapper) {
            MethodWrapper methodWrapper = (MethodWrapper) element;

            if (methodWrapper.getMember() != null) {
                result= fDecorator.decorateImage(super.getImage(methodWrapper.getMember()), methodWrapper);
            }
        } else if (isPendingUpdate(element)) {
            return null;
        } else {
            result= super.getImage(element);
        }
        
        return result;
    }

    /*
     * @see ILabelProvider#getText(Object)
     */
    public String getText(Object element) {
        if (element instanceof MethodWrapper) {
            MethodWrapper methodWrapper = (MethodWrapper) element;

            if (methodWrapper.getMember() != null) {
                return getElementLabel(methodWrapper);
            } else {
                return CallHierarchyMessages.CallHierarchyLabelProvider_root; 
            }
        } else if (element == TreeTermination.SEARCH_CANCELED) {
            return CallHierarchyMessages.CallHierarchyLabelProvider_searchCanceled; 
        } else if (isPendingUpdate(element)) {
            return CallHierarchyMessages.CallHierarchyLabelProvider_updatePending; 
        }

        return CallHierarchyMessages.CallHierarchyLabelProvider_noMethodSelected; 
    }

    private boolean isPendingUpdate(Object element) {
        return element instanceof IWorkbenchAdapter;
    }
    private String getElementLabel(MethodWrapper methodWrapper) {
        String label = super.getText(methodWrapper.getMember());

        Collection callLocations = methodWrapper.getMethodCall().getCallLocations();

        if ((callLocations != null) && (callLocations.size() > 1)) {
            return Messages.format(CallHierarchyMessages.CallHierarchyLabelProvider_matches, new String[]{label, String.valueOf(callLocations.size())}); 
        }

        return label;
    }
}
