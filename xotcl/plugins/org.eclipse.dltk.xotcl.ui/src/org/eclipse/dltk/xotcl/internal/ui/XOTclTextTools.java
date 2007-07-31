package org.eclipse.dltk.xotcl.internal.ui;

import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.PositionUpdater;
import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.SemanticHighlighting;
import org.eclipse.dltk.tcl.internal.ui.text.TclTextTools;
import org.eclipse.dltk.tcl.ui.TclPreferenceConstants;

public class XOTclTextTools extends TclTextTools {
	public XOTclTextTools(boolean autoDisposeOnDisplayDispose) {
		super(autoDisposeOnDisplayDispose);
	}

	private final class SH extends SemanticHighlighting {

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

	}

	public SemanticHighlighting[] getSemanticHighlightings() {
		return new SemanticHighlighting[] { new SH(
				TclPreferenceConstants.EDITOR_SINGLE_LINE_COMMENT_COLOR, null) };
	}

	public PositionUpdater getSemanticPositionUpdater() {
		return new XOTclSemanticPositionUpdater();
	}
}
