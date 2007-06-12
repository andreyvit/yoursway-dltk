/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.debug.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.text.source.IVerticalRulerInfo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IActionDelegate2;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.AbstractRulerActionDelegate;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * Toggles a breakpoint when ruler is double-clicked. This action delegate can
 * be contributed to an editor with the <code>editorActions</code> extension
 * point. This action is as a factory that creates another action that performs
 * the actual breakpoint toggling. The created action acts on the editor's
 * <code>IToggleBreakpointsTagret</code> to toggle breakpoints.
 * <p>
 * Following is example plug-in XML used to contribute this action to an editor.
 * Note that the label attribute of this action is not displayed in the editor.
 * Instead, the label of the created action is displayed.
 * 
 * <pre>
 * &lt;extension point=&quot;org.eclipse.ui.editorActions&quot;&gt;
 *    &lt;editorContribution
 *          targetID=&quot;example.editor&quot;
 *          id=&quot;example.rulerActions&quot;&gt;
 *       &lt;action
 *             label=&quot;Not Used&quot;
 *             class=&quot;org.eclipse.debug.ui.actions.RulerToggleBreakpointActionDelegate&quot;
 *             style=&quot;push&quot;
 *             actionID=&quot;RulerDoubleClick&quot;
 *             id=&quot;example.doubleClickBreakpointAction&quot;/&gt;
 *    &lt;/editorContribution&gt;
 * &lt;/extension&gt;
 * </pre>
 * 
 * </p>
 * <p>
 * This action can also be contributed to a vertical ruler context menu via the
 * <code>popupMenus</code> extension point, by referencing the ruler's context
 * menu identifier in the <code>targetID</code> attribute.
 * 
 * <pre>
 * &lt;extension point=&quot;org.eclipse.ui.popupMenus&quot;&gt;
 *   &lt;viewerContribution
 *     targetID=&quot;example.rulerContextMenuId&quot;
 *     id=&quot;example.RulerPopupActions&quot;&gt;
 *       &lt;action
 *         label=&quot;Toggle Breakpoint&quot;
 *         class=&quot;org.eclipse.debug.ui.actions.RulerToggleBreakpointActionDelegate&quot;
 *         menubarPath=&quot;additions&quot;
 *         id=&quot;example.rulerContextMenu.toggleBreakpointAction&quot;&gt;
 *       &lt;/action&gt;
 *   &lt;/viewerContribution&gt;
 * </pre>
 * 
 * </p>
 * <p>
 * Clients may refer to this class as an action delegate in plug-in XML. This
 * class is not intended to be subclassed.
 * </p>
 * 
 * @since 3.1
 */
public class RulerToggleWatchPointActionDelegate extends
		AbstractRulerActionDelegate implements IActionDelegate2 {

	public void menuAboutToShow(IMenuManager manager) {
		super.menuAboutToShow(manager);

	}

	private IEditorPart fEditor = null;
	private ToggleWatchPointAction fDelegate = null;

	protected IAction createAction(ITextEditor editor,
			IVerticalRulerInfo rulerInfo) {
		fDelegate = new ToggleWatchPointAction(editor, null, rulerInfo);
		return fDelegate;
	}

	public void setActiveEditor(IAction callerAction, IEditorPart targetEditor) {
		if (fEditor != null) {
			if (fDelegate != null) {
				fDelegate.dispose();
				fDelegate = null;
			}
		}
		fEditor = targetEditor;
		super.setActiveEditor(callerAction, targetEditor);
	}

	public void init(IAction action) {
	}

	public void dispose() {
		if (fDelegate != null) {
			fDelegate.dispose();
		}
		fDelegate = null;
		fEditor = null;
	}

	public void runWithEvent(IAction action, Event event) {
		run(action);
	}
}
