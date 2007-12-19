package org.eclipse.dltk.tcl.core.extensions;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.tcl.internal.core.codeassist.TclSelectionEngine;
import org.eclipse.dltk.tcl.internal.core.codeassist.selection.SelectionOnKeywordOrFunction;

public interface ISelectionExtension {

	void selectionOnKeywordOrFunction(SelectionOnKeywordOrFunction key,
			TclSelectionEngine tclSelectionEngine);

	void selectionOnAST(ASTNode node, TclSelectionEngine tclSelectionEngine);

	void selectionOnNode(ASTNode node, int position,
			TclSelectionEngine tclSelectionEngine);

}
