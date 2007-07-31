package org.eclipse.dltk.xotcl.internal.core;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.codeassist.complete.CompletionNodeFound;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.internal.core.codeassist.completion.TclCompletionParser;

public class XOTclCompletionParser extends TclCompletionParser {
	private class XOTclCompletionVisitor extends ASTVisitor {
		public ASTNode inNode;
		public int position;
		public boolean visitGeneral(ASTNode node) throws Exception {
			super.visitGeneral(node);
			if( node instanceof TclStatement && node.sourceStart() <= position
					&& position <= node.sourceEnd()) {
				XOTclCompletionParser.this.superParerBlockStatements(node, this.inNode, this.position);
				return false;
			}
			return true;
		}
	};
	private XOTclCompletionVisitor visitor = new XOTclCompletionVisitor();
	public void superParerBlockStatements(ASTNode node, ASTNode inNode, int position) {
		super.parseBlockStatements(node, inNode, position);
	}
	public void parseBlockStatements(ASTNode node, ASTNode inNode, int position) {
		super.parseBlockStatements(node, inNode, position);
		try {
			visitor.inNode = inNode;
			visitor.position = position;
			node.traverse(visitor);
			this.handleNotInElement(node, position);
		} catch (Exception e) {
			if( e instanceof CompletionNodeFound ) {
				throw (CompletionNodeFound)e;
			}
			e.printStackTrace();
		}
	}
}
