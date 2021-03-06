/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.viewsupport.ScriptUILabelProvider;
import org.eclipse.jface.preference.IPreferenceStore;

public interface IDLTKUILanguageToolkit {
	ScriptElementLabels getScriptElementLabels();

	ScriptUILabelProvider createScriptUILabelProvider();
	
	IDLTKLanguageToolkit getCoreToolkit();

	IPreferenceStore getPreferenceStore();

	String getPartitioningId();

	String getEditorId(Object inputElement);

	String getInterpreterContainerId();
	
	ScriptTextTools getTextTools();
	
	ScriptSourceViewerConfiguration createSourceViewerConfiguration();
	
	// Per module script explorer show children way.
	boolean getProvideMembers(ISourceModule element);
	
	String getInterpreterPreferencePage();
	
	String getDebugPreferencePage();
	
	String[] getEditorPreferencePages();
}
