/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.editor;

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.dltk.javascript.internal.ui.JavaScriptUI;
import org.eclipse.dltk.javascript.internal.ui.text.JavascriptTextTools;
import org.eclipse.dltk.javascript.ui.text.IJavaScriptPartitions;
import org.eclipse.jface.text.IDocument;


/**
 * The document setup participant for Javascript.
 */
public class JavaScriptDocumentSetupParticipant implements
		IDocumentSetupParticipant {

	public JavaScriptDocumentSetupParticipant() {
	}

	/*
	 * @see org.eclipse.core.filebuffers.IDocumentSetupParticipant#setup(org.eclipse.jface.text.IDocument)
	 */
	public void setup(IDocument document) {
		JavascriptTextTools tools = JavaScriptUI.getDefault().getTextTools();
		tools.setupDocumentPartitioner(document,
				IJavaScriptPartitions.JS_PARTITIONING);
	}
}
