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
package org.eclipse.dltk.tcl.internal.ui.editor;

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.dltk.tcl.internal.ui.text.TclTextTools;
import org.eclipse.dltk.tcl.ui.text.TclPartitions;
import org.eclipse.jface.text.IDocument;

public class TclDocumentSetupParticipant implements IDocumentSetupParticipant {

	public TclDocumentSetupParticipant() {
	}

	public void setup(IDocument document) {
		TclTextTools tools = TclUI.getDefault().getTextTools();
		tools.setupDocumentPartitioner(document,
				TclPartitions.TCL_PARTITIONING);
	}
}
