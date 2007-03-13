package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * begin
 * 		<body>
 * rescue <rescue>
 * else <else>
 * end
 *
 */
public class RescueStatement extends Statement {

	private Statement bodyNode;
	private Statement elseNode;
	private RescueBodyStatement rescueNode;

	public RescueStatement(int start, int end, Statement bodyNode,
			Statement elseNode, RescueBodyStatement rescueNode) {
		super(start, end);
		this.bodyNode = bodyNode;
		this.elseNode = elseNode;
		this.rescueNode = rescueNode;
	}

	public Statement getBodyNode() {
		return bodyNode;
	}

	public Statement getElseNode() {
		return elseNode;
	}

	public RescueBodyStatement getRescueNode() {
		return rescueNode;
	}

	public int getKind() {
		return 0;
	}

	public void printNode(CorePrinter output) {
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (bodyNode != null)
				bodyNode.traverse(visitor);
			if (elseNode != null)
				elseNode.traverse(visitor);
			if (rescueNode != null)
				rescueNode.traverse(visitor);
			visitor.endvisit(this);
		}
	}

}
