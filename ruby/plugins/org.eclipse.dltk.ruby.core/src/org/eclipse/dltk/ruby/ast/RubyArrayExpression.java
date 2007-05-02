package org.eclipse.dltk.ruby.ast;

import java.util.Iterator;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.ASTListNode;

public class RubyArrayExpression extends ASTListNode {

	public static final int ARRAY_BRACKETS = 0;

	public static final int ARRAY_WSMALL = 1;

	public static final int ARRAY_WBIG = 2;

	private int arrayKind = 0;
	private ASTNode asterixElement;

	public ASTNode getAsterixElement() {
		return asterixElement;
	}

	public void setAsterixElement(ASTNode asterixElement) {
		this.asterixElement = asterixElement;
	}

	public int getArrayKind() {
		return arrayKind;
	}

	public void setArrayKind(int arrayKind) {
		this.arrayKind = arrayKind;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (this.getChilds() != null) {
				for (Iterator iter = this.getChilds().iterator(); iter
						.hasNext();) {
					ASTNode s = (ASTNode) iter.next();
					s.traverse(visitor);
				}
			}
			if (asterixElement != null)
				asterixElement.traverse(visitor);
			visitor.endvisit(this);
		}
	}

}
