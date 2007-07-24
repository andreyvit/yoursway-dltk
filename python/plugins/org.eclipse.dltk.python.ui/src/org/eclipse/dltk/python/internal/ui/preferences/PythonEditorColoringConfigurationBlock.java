/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.ui.preferences;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.dltk.python.internal.ui.editor.PythonDocumentSetupParticipant;
import org.eclipse.dltk.python.internal.ui.text.SimplePythonSourceViewerConfiguration;
import org.eclipse.dltk.python.ui.PythonPreferenceConstants;
import org.eclipse.dltk.python.ui.text.IPythonPartitions;
import org.eclipse.dltk.ui.preferences.AbstractScriptEditorColoringConfigurationBlock;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.preferences.PreferencesMessages;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.texteditor.ITextEditor;


public class PythonEditorColoringConfigurationBlock extends
		AbstractScriptEditorColoringConfigurationBlock implements
		IPreferenceConfigurationBlock {
	
	private static final String PREVIEW_FILE_NAME = "PreviewFile.txt";

	private static final String[][] fSyntaxColorListModel = new String[][] {
			{ PreferencesMessages.DLTKEditorPreferencePage_singleLineComment,
				PythonPreferenceConstants.EDITOR_SINGLE_LINE_COMMENT_COLOR,
					sCommentsCategory },
			{ PreferencesMessages.DLTKEditorPreferencePage_keywords,
				PythonPreferenceConstants.EDITOR_KEYWORD_COLOR, sCoreCategory },

                        { PreferencesMessages.DLTKEditorPreferencePage_returnKeyword,
                                PythonPreferenceConstants.EDITOR_KEYWORD_RETURN_COLOR, sCoreCategory },
				
			{ PreferencesMessages.DLTKEditorPreferencePage_strings,
				PythonPreferenceConstants.EDITOR_STRING_COLOR, sCoreCategory },

			{ PreferencesMessages.DLTKEditorPreferencePage_numbers,
				PythonPreferenceConstants.EDITOR_NUMBER_COLOR, sCoreCategory },
								
			{ PreferencesMessages.DLTKEditorPreferencePage_decorators,
				PythonPreferenceConstants.EDITOR_DECORATOR_COLOR, sCoreCategory },

			{ PreferencesMessages.DLTKEditorPreferencePage_class_colors,
				PythonPreferenceConstants.EDITOR_CLASS_DEFINITION_COLOR, sCoreCategory },
					
			{ PreferencesMessages.DLTKEditorPreferencePage_function_colors,
				PythonPreferenceConstants.EDITOR_FUNCTION_DEFINITION_COLOR, sCoreCategory },
					
/*			{ PreferencesMessages.DLTKEditorPreferencePage_variables,
								PythonPreferenceConstants.EDITOR_VARIABLE_COLOR, sCoreCategory }*/ };
	

	public PythonEditorColoringConfigurationBlock(OverlayPreferenceStore store) {
		super(store);
	}

	protected String[][] getSyntaxColorListModel() {
		return fSyntaxColorListModel;
	}

	protected ProjectionViewer createPreviewViewer(Composite parent,
			IVerticalRuler verticalRuler, IOverviewRuler overviewRuler,
			boolean showAnnotationsOverview, int styles, IPreferenceStore store) {
		return new ScriptSourceViewer(parent, verticalRuler, overviewRuler,
				showAnnotationsOverview, styles, store);
	}

	protected ScriptSourceViewerConfiguration createSimpleSourceViewerConfiguration(
			IColorManager colorManager, IPreferenceStore preferenceStore,
			ITextEditor editor, boolean configureFormatter) {
		return new SimplePythonSourceViewerConfiguration(colorManager,
				preferenceStore, editor, IPythonPartitions.PYTHON_PARTITIONING,
				configureFormatter);
	}

	protected void setDocumentPartitioning(IDocument document) {
		PythonDocumentSetupParticipant participant = new PythonDocumentSetupParticipant();
		participant.setup(document);
	}

	protected String getPreviewContent() {
		String line;
		String separator = System.getProperty("line.separator"); //$NON-NLS-1$
		StringBuffer buffer = new StringBuffer(512);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(getClass()
					.getResourceAsStream(PREVIEW_FILE_NAME)));
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
				buffer.append(separator);
			}
		} catch (IOException io) {
			// DLTKUIPlugin.log(io);
			io.printStackTrace();
			// System.err.println("io");
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
		return buffer.toString();

	}
}
