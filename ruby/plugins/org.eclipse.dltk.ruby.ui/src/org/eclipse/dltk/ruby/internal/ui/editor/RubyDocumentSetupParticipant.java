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
package org.eclipse.dltk.ruby.internal.ui.editor;

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.dltk.ruby.internal.ui.RubyUI;
import org.eclipse.dltk.ruby.internal.ui.text.RubyTextTools;
import org.eclipse.dltk.ruby.ui.text.RubyPartitions;
import org.eclipse.jface.text.IDocument;

/**
 * The document setup participant for Python.
 */
public class RubyDocumentSetupParticipant implements
		IDocumentSetupParticipant {

	public RubyDocumentSetupParticipant() {
	}

	public void setup(IDocument document) {
		RubyTextTools tools = RubyUI.getDefault().getTextTools();
		tools.setupDocumentPartitioner(document,
				RubyPartitions.RUBY_PARTITIONING);
	}
}
