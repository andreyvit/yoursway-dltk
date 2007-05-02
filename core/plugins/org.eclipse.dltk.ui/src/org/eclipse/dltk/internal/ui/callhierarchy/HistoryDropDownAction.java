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

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;



class HistoryDropDownAction extends Action implements IMenuCreator {
	
	private static class ClearHistoryAction extends Action {

		private CallHierarchyViewPart fView;
		
		public ClearHistoryAction(CallHierarchyViewPart view) {
			super(CallHierarchyMessages.HistoryDropDownAction_clearhistory_label);
			fView= view;
		}
			
		public void run() {
			fView.setHistoryEntries(new IMethod[0]);
			fView.setMethod(null);
		}
	}
	
    public static final int RESULTS_IN_DROP_DOWN = 10;
    private CallHierarchyViewPart fView;
    private Menu fMenu;

    public HistoryDropDownAction(CallHierarchyViewPart view) {
        fView = view;
        fMenu = null;
        setToolTipText(CallHierarchyMessages.HistoryDropDownAction_tooltip); 
        DLTKPluginImages.setLocalImageDescriptors(this, "history_list.gif"); //$NON-NLS-1$

//        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IJavaHelpContextIds.CALL_HIERARCHY_HISTORY_DROP_DOWN_ACTION);
        if (DLTKCore.DEBUG) {
			System.err.println("Add help support here...");
		}	

        setMenuCreator(this);
    }

    public Menu getMenu(Menu parent) {
        return null;
    }

    public Menu getMenu(Control parent) {
        if (fMenu != null) {
            fMenu.dispose();
        }
        fMenu= new Menu(parent);
        IMethod[] elements= fView.getHistoryEntries();
        addEntries(fMenu, elements);
		new MenuItem(fMenu, SWT.SEPARATOR);
		addActionToMenu(fMenu, new HistoryListAction(fView));
		addActionToMenu(fMenu, new ClearHistoryAction(fView));
        return fMenu;
    }

    public void dispose() {
        fView = null;

        if (fMenu != null) {
            fMenu.dispose();
            fMenu = null;
        }
    }

    protected void addActionToMenu(Menu parent, Action action) {
        ActionContributionItem item = new ActionContributionItem(action);
        item.fill(parent, -1);
    }

    private boolean addEntries(Menu menu, IMethod[] elements) {
        boolean checked = false;

        int min = Math.min(elements.length, RESULTS_IN_DROP_DOWN);

        for (int i = 0; i < min; i++) {
            HistoryAction action = new HistoryAction(fView, elements[i]);
            action.setChecked(elements[i].equals(fView.getMethod()));
            checked = checked || action.isChecked();
            addActionToMenu(menu, action);
        }

        return checked;
    }

    public void run() {
        (new HistoryListAction(fView)).run();
    }
}
