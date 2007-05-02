/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.text.completion;

import org.eclipse.dltk.javascript.internal.ui.JavaScriptUI;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;

public class JavaScriptContentAssistPreference extends ContentAssistPreference {
	static JavaScriptContentAssistPreference sDefault;
	protected ScriptTextTools getTextTools() {
		return JavaScriptUI.getDefault().getTextTools();
	}

	public static ContentAssistPreference getDefault() {
		if( sDefault == null ) {
			sDefault = new JavaScriptContentAssistPreference();
		}
		return sDefault;
	}
}
