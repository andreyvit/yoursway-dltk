/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.text;

import org.eclipse.dltk.javascript.ui.text.IJavaScriptPartitions;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.ui.texteditor.ITextEditor;


public class JavascriptTextTools extends ScriptTextTools {
	
	private IPartitionTokenScanner fPartitionScanner;
	
	private final static String[] LEGAL_CONTENT_TYPES = new String[] {
		IJavaScriptPartitions.JS_STRING,
		IJavaScriptPartitions.JS_COMMENT
	};

	public JavascriptTextTools(boolean autoDisposeOnDisplayDispose) {
		super(IJavaScriptPartitions.JS_PARTITIONING, LEGAL_CONTENT_TYPES, autoDisposeOnDisplayDispose);
		fPartitionScanner = new JavascriptPartitionScanner();
	}

	
	public ScriptSourceViewerConfiguration createSourceViewerConfiguraton(
			IPreferenceStore preferenceStore, ITextEditor editor, String partitioning) {
		return new JavascriptSourceViewerConfiguration(getColorManager(), preferenceStore, editor, partitioning);
	}

	
	public IPartitionTokenScanner getPartitionScanner() {
		return fPartitionScanner;
	}

}
