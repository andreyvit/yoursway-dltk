package org.eclipse.dltk.tcl.internal.core.codeassist.selection;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.references.SimpleReference;

public class SelectionOnVariable extends SimpleReference {
	public SelectionOnVariable(String name, ASTNode token, ASTNode node,
			ASTNode inNode) {
		super(token.sourceStart(), token.sourceEnd(), name);
	}
}
