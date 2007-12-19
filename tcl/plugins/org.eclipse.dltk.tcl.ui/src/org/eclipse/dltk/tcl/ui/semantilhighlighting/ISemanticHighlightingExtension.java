package org.eclipse.dltk.tcl.ui.semantilhighlighting;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.internal.ui.editor.SemanticHighlightingManager.HighlightedPosition;
import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.Highlighting;
import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.SemanticHighlighting;
import org.eclipse.dltk.internal.ui.editor.semantic.highlighting.SemanticHighlightingPresenter;

public interface ISemanticHighlightingExtension {

	SemanticHighlighting[] getHighlightings();

	HighlightedPosition[] calculatePositions(ASTNode node,
			SemanticHighlightingPresenter presenter,
			Highlighting[] highlightings);

}
