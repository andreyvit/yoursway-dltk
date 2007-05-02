/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.ui.actions;

import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.ui.actions.RulerBreakpointAction;
import org.eclipse.dltk.debug.core.model.IScriptBreakpoint;
import org.eclipse.jface.text.source.IVerticalRulerInfo;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.dialogs.PropertyDialogAction;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.IUpdate;

public class ScriptBreakpointPropertiesRulerAction extends
		RulerBreakpointAction implements IUpdate {

	private IBreakpoint fBreakpoint;

	public ScriptBreakpointPropertiesRulerAction(ITextEditor editor,
			IVerticalRulerInfo info) {
		super(editor, info);
		setText(ActionMessages.ScriptBreakpointPropertiesRulerAction_BreakpointProperties);
	}

	public void run() {
		if (getBreakpoint() != null) {
			PropertyDialogAction action = new PropertyDialogAction(getEditor()
					.getEditorSite(), new ISelectionProvider() {
				public void addSelectionChangedListener(
						ISelectionChangedListener listener) {
				}

				public ISelection getSelection() {
					return new StructuredSelection(getBreakpoint());
				}

				public void removeSelectionChangedListener(
						ISelectionChangedListener listener) {
				}

				public void setSelection(ISelection selection) {
				}
			});
			action.run();
		}
	}

	public void update() {
		fBreakpoint = null;
		IBreakpoint breakpoint = getBreakpoint();
		if (breakpoint != null && (breakpoint instanceof IScriptBreakpoint)) {
			fBreakpoint = breakpoint;
		}
		setEnabled(fBreakpoint != null);
	}
}
