/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.ui.editor;

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.dltk.python.internal.ui.PythonUI;
import org.eclipse.dltk.python.internal.ui.text.PythonTextTools;
import org.eclipse.dltk.python.ui.text.IPythonPartitions;
import org.eclipse.jface.text.IDocument;


/**
 * The document setup participant for Python.
 */
public class PythonDocumentSetupParticipant implements
		IDocumentSetupParticipant {

	public PythonDocumentSetupParticipant() {
	}

	/*
	 * @see org.eclipse.core.filebuffers.IDocumentSetupParticipant#setup(org.eclipse.jface.text.IDocument)
	 */
	public void setup(IDocument document) {
		PythonTextTools tools = PythonUI.getDefault().getTextTools();
		tools.setupDocumentPartitioner(document,
				IPythonPartitions.PYTHON_PARTITIONING);
	}
}
