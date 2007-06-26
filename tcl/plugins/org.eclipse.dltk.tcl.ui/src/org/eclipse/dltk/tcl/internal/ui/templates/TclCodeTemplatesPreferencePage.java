/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.templates;

import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.dltk.tcl.internal.ui.text.SimpleTclSourceViewerConfiguration;
import org.eclipse.dltk.tcl.internal.ui.text.TclTextTools;
import org.eclipse.dltk.tcl.ui.text.TclPartitions;
import org.eclipse.dltk.ui.templates.ScriptTemplatePreferencePage;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class TclCodeTemplatesPreferencePage extends ScriptTemplatePreferencePage
		implements IWorkbenchPreferencePage {
	public TclCodeTemplatesPreferencePage() {
		setPreferenceStore(TclUI.getDefault().getPreferenceStore());
		
		setTemplateStore(TclTemplateAccess.getInstance().getTemplateStore());
		setContextTypeRegistry(TclTemplateAccess.getInstance().getContextTypeRegistry());
	}

	protected ScriptSourceViewerConfiguration createSourceViewerConfiguration(IDocument document) {
		IPreferenceStore store = TclUI.getDefault().getPreferenceStore();

		TclTextTools textTools = TclUI.getDefault().getTextTools();
		textTools.setupDocumentPartitioner(document,
				TclPartitions.TCL_PARTITIONING);

		return new SimpleTclSourceViewerConfiguration(
				textTools.getColorManager(), store, null,
				TclPartitions.TCL_PARTITIONING, false);
		
	}
}
