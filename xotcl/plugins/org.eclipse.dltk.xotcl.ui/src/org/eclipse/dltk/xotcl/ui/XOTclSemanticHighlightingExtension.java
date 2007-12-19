package org.eclipse.dltk.xotcl.ui;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.internal.ui.editor.SemanticHighlightingManager.HighlightedPosition;
import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.Highlighting;
import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.SemanticHighlighting;
import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.SemanticHighlightingPresenter;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.internal.ui.text.TclTextTools;
import org.eclipse.dltk.tcl.ui.TclPreferenceConstants;
import org.eclipse.dltk.tcl.ui.semantilhighlighting.ISemanticHighlightingExtension;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclDocumentationNode;

public class XOTclSemanticHighlightingExtension implements
		ISemanticHighlightingExtension {
	private SemanticHighlighting[] highlightings = new SemanticHighlighting[] { new TclTextTools.SH(
			TclPreferenceConstants.EDITOR_SINGLE_LINE_COMMENT_COLOR, null) };

	public XOTclSemanticHighlightingExtension() {
	}

	public HighlightedPosition[] calculatePositions(ASTNode node,
			SemanticHighlightingPresenter presenter,
			Highlighting[] highlightings) {
		Highlighting highlightingFrom = getHighlightingFrom(
				this.highlightings[0], highlightings);
		if (node instanceof TclStatement) {
			TclStatement st = (TclStatement) node;
			if (st.getAt(0) instanceof SimpleReference
					&& ((SimpleReference) st.getAt(0)).getName().equals("@")) {
				Highlighting hl = highlightingFrom;
				return new HighlightedPosition[] { presenter
						.createHighlightedPosition(st.sourceStart(), st
								.sourceEnd()
								- st.sourceStart(), hl) };
			}
		} else if (node instanceof XOTclDocumentationNode) {
			return new HighlightedPosition[] { presenter
					.createHighlightedPosition(node.sourceStart(), node
							.sourceEnd()
							- node.sourceStart(), highlightingFrom) };
		}
		return new HighlightedPosition[0];
	}

	private Highlighting getHighlightingFrom(
			SemanticHighlighting semanticHighlighting,
			Highlighting[] highlightings2) {
		for (int i = 0; i < highlightings2.length; i++) {
			if (highlightings2[i].getSemaHighlighting().equals(
					semanticHighlighting)) {
				return highlightings2[i];
			}
		}
		return null;
	}

	public SemanticHighlighting[] getHighlightings() {
		return highlightings;
	}
}
