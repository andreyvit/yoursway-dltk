package org.eclipse.dltk.tcl.internal.core.codeassist.selection;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.references.SimpleReference;

public class SelectionOnKeywordOrFunction extends SimpleReference {
	public SelectionOnKeywordOrFunction(String completionToken,
			ASTNode completionNode, ASTNode node) {
		super(completionNode.sourceStart(), completionNode.sourceEnd(),
				completionToken);
	}
}
