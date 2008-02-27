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
import org.eclipse.dltk.internal.debug.core.model.ScriptValue;
import org.eclipse.ui.IEditorInput;

public class RubyDebugModelPresentation extends ScriptDebugModelPresentation {
	private static final String RUBY_EDITOR_ID = "org.eclipse.dltk.ruby.ui.editor.RubyEditor";

	public String getEditorId(IEditorInput input, Object element) {
		return RUBY_EDITOR_ID;
	}

	public String getDetailPaneText(IScriptValue value) {
		if (value instanceof ScriptValue) {
			return ((ScriptValue) value).getRawValue();
		}
		return super.getDetailPaneText(value);
	}
}
