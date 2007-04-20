package org.eclipse.dltk.ruby.ast;

import java.util.Iterator;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.CompoundStatement;
import org.eclipse.dltk.ast.statements.Statement;

public class RubyArrayExpression extends CompoundStatement {
	
	public static final int ARRAY_BRACKETS = 0; 
	
	public static final int ARRAY_WSMALL = 1;
	
	public static final int ARRAY_WBIG = 2;

	private int arrayKind = 0;
	private Statement asterixElement;

	public Statement getAsterixElement() {
		return asterixElement;
	}

	public void setAsterixElement(Statement asterixElement) {
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
			if (this.getStatements() != null) {
				for (Iterator iter = this.getStatements().iterator(); iter.hasNext();) {
					Statement s = (Statement) iter.next();
					s.traverse(visitor);				
				}				
			}
			if (asterixElement != null)
				asterixElement.traverse(visitor);
			visitor.endvisit(this);
		}
	}

	
	
}


