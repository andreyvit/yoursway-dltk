/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.templates;

import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.dltk.tcl.internal.ui.text.SimpleTclSourceViewerConfiguration;
import org.eclipse.dltk.tcl.internal.ui.text.TclTextTools;
import org.eclipse.dltk.tcl.ui.text.TclPartitions;
import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;
import org.eclipse.dltk.ui.templates.ScriptTemplatePreferencePage;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.jface.text.IDocument;

/**
 * Tcl code templates preference page
 */
public class TclCodeTemplatesPreferencePage extends
		ScriptTemplatePreferencePage {

	/*
	 * @see org.eclipse.dltk.ui.templates.ScriptTemplatePreferencePage#createSourceViewerConfiguration()
	 */
	protected ScriptSourceViewerConfiguration createSourceViewerConfiguration() {
		return new SimpleTclSourceViewerConfiguration(getTextTools()
				.getColorManager(), getPreferenceStore(), null,
				TclPartitions.TCL_PARTITIONING, false);
	}

	/*
	 * @see org.eclipse.dltk.ui.templates.ScriptTemplatePreferencePage#setDocumentParticioner(org.eclipse.jface.text.IDocument)
	 */
	protected void setDocumentParticioner(IDocument document) {
		getTextTools().setupDocumentPartitioner(document,
				TclPartitions.TCL_PARTITIONING);
	}

	/*
	 * @see org.eclipse.dltk.ui.templates.ScriptTemplatePreferencePage#setPreferenceStore()
	 */
	protected void setPreferenceStore() {
		setPreferenceStore(TclUI.getDefault().getPreferenceStore());
	}

	/*
	 * @see org.eclipse.dltk.ui.templates.ScriptTemplatePreferencePage#getTemplateAccess()
	 */
	protected ScriptTemplateAccess getTemplateAccess() {
		return TclTemplateAccess.getInstance();
	}

	private TclTextTools getTextTools() {
		return TclUI.getDefault().getTextTools();
	}
}
