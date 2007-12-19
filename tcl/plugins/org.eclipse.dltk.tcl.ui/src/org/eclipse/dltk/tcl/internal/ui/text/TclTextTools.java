/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.dltk.core.SimpleClassDLTKExtensionManager;
import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.PositionUpdater;
import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.SemanticHighlighting;
import org.eclipse.dltk.tcl.internal.ui.TclSemanticPositionUpdater;
import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.dltk.tcl.ui.TclPreferenceConstants;
import org.eclipse.dltk.tcl.ui.semantilhighlighting.ISemanticHighlightingExtension;
import org.eclipse.dltk.tcl.ui.text.TclPartitions;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.ui.texteditor.ITextEditor;

public class TclTextTools extends ScriptTextTools {

	private IPartitionTokenScanner fPartitionScanner;

	private SimpleClassDLTKExtensionManager extensions = new SimpleClassDLTKExtensionManager(
			TclUI.PLUGIN_ID + ".tclSemanticHighlighting");

	private final static String[] LEGAL_CONTENT_TYPES = new String[] {
			TclPartitions.TCL_STRING, TclPartitions.TCL_COMMENT };

	public TclTextTools(boolean autoDisposeOnDisplayDispose) {
		super(TclPartitions.TCL_PARTITIONING, LEGAL_CONTENT_TYPES,
				autoDisposeOnDisplayDispose);
		fPartitionScanner = new TclPartitionScanner();
	}

	public ScriptSourceViewerConfiguration createSourceViewerConfiguraton(
			IPreferenceStore preferenceStore, ITextEditor editor,
			String partitioning) {
		return new TclSourceViewerConfiguration(getColorManager(),
				preferenceStore, editor, partitioning);
	}

	public IPartitionTokenScanner getPartitionScanner() {
		return fPartitionScanner;
	}
	
	private ISemanticHighlightingExtension[] getExtensions() {
		Object[] objects = extensions.getObjects();
		ISemanticHighlightingExtension[] exts = new ISemanticHighlightingExtension[objects.length];
		
		for (int i = 0; i < objects.length; i++) {
			exts[i] = ((ISemanticHighlightingExtension) objects[i]);
		}
		return exts;
	}

	public SemanticHighlighting[] getSemanticHighlightings() {
		List highlightings = new ArrayList();
		ISemanticHighlightingExtension[] exts = getExtensions();
		for (int i = 0; i < exts.length; i++) {
			SemanticHighlighting[] hl = exts[i].getHighlightings();
			if (hl != null) {
				highlightings.addAll(Arrays.asList(hl));
			}
		}
		return new SemanticHighlighting[] { new SH(
				TclPreferenceConstants.EDITOR_SINGLE_LINE_COMMENT_COLOR, null) };
	}

	public PositionUpdater getSemanticPositionUpdater() {
		return new TclSemanticPositionUpdater(getExtensions());
	}

	public final static class SH extends SemanticHighlighting {

		private String preferenceKey;

		public String getBackgroundPreferenceKey() {
			return bgColor;
		}

		private String bgColor;

		public SH(String editorXmlTagNameColor, String bgColor) {
			this.preferenceKey = editorXmlTagNameColor;
			this.bgColor = bgColor;
		}

		public String getPreferenceKey() {
			return preferenceKey;
		}

		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((preferenceKey == null) ? 0 : preferenceKey.hashCode());
			return result;
		}

		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final SH other = (SH) obj;
			if (preferenceKey == null) {
				if (other.preferenceKey != null)
					return false;
			} else if (!preferenceKey.equals(other.preferenceKey))
				return false;
			return true;
		}
	}
}
