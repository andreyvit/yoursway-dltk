/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.examples.python.internal.ui.editor;

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.dltk.examples.python.internal.ExamplePythonUI;
import org.eclipse.jface.text.IDocument;

/**
 * The document setup participant for Python.
 */
public class ExamplePythonDocumentSetupParticipant implements
		IDocumentSetupParticipant {

	public ExamplePythonDocumentSetupParticipant() {
	}

	public void setup(IDocument document) {
		ExamplePythonTextTools tools = ExamplePythonUI.getDefault()
				.getTextTools();
		tools.setupDocumentPartitioner(document,
				IExamplePythonPartitions.PYTHON_PARTITIONING);
	}
}
