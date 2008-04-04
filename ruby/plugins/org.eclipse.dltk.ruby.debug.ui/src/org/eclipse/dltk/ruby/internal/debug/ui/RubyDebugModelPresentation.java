/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.debug.ui;

import org.eclipse.dltk.debug.core.model.IScriptValue;
import org.eclipse.dltk.debug.ui.ScriptDebugModelPresentation;
import org.eclipse.dltk.internal.ui.editor.EditorUtility;
import org.eclipse.dltk.ruby.internal.ui.editor.RubyEditor;
import org.eclipse.ui.IEditorInput;

public class RubyDebugModelPresentation extends ScriptDebugModelPresentation {

	public String getEditorId(IEditorInput input, Object element) {
		String editorId = EditorUtility.getEditorID(input, element);
		if (editorId == null)
			editorId = RubyEditor.EDITOR_ID;

		return editorId;
	}

	public String getDetailPaneText(IScriptValue value) {
		return value.getRawValue();
	}
}
