/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.internal.debug.ui.actions;

import org.eclipse.debug.ui.DebugPopup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;

public class PopupScriptDisplayAction  extends ScriptDisplayAction {
	private static class DisplayPopup extends DebugPopup {
		private String message;
		
        public DisplayPopup(String message, Shell shell, Point anchor) {
        	// TODO: add real commandId
            super(shell, anchor, null);
            
            this.message = message;
        }

        protected String getActionText() {
			return Messages.PopupScriptDisplayAction_moveToDisplayView;
		}

		/*protected void persist() {
            IDataDisplay directDisplay = getDirectDataDisplay();
            Display display = DLTKDebugUIPlugin.getStandardDisplay();

            if (!display.isDisposed()) {
                IDataDisplay dataDisplay = getDataDisplay();
                if (dataDisplay != null) {
                    if (directDisplay == null) {
                        dataDisplay.displayExpression(snippet);
                    }
                    dataDisplay.displayExpressionValue(resultString);
                }
            }
        }*/

        protected Control createDialogArea(Composite parent) {
            GridData gd = new GridData(GridData.FILL_BOTH);
            StyledText text = new StyledText(parent, SWT.MULTI | SWT.READ_ONLY | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL);
            text.setLayoutData(gd);

            text.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_INFO_FOREGROUND));
            text.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));

            text.setText(message);
            return text;
        }
    }

    public PopupScriptDisplayAction() {
        super();
    }

    private void showPopup(StyledText textWidget, String message) {
        DebugPopup displayPopup = new DisplayPopup(message, getShell(), getPopupAnchor(textWidget));
        displayPopup.open();
    }

    protected void displayStringResult(String currentSnippet, final String currentResultString) {
        IWorkbenchPart part = getPart();

        final StyledText textWidget = getStyledText(part);
        if (textWidget != null) {
            Display.getDefault().asyncExec(new Runnable() {
                public void run() {
                    showPopup(textWidget, currentResultString);
                }
            });
            
            evaluationCleanup();
        }
    }
}
