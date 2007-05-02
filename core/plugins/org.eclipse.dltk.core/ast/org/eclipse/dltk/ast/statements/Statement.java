package org.eclipse.dltk.ast.statements;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.DLTKToken;

/**
 * Base class for all statements.
 * 
 */
public abstract class Statement extends ASTNode implements StatementConstants {
	protected Statement(int start, int end) {
		super(start, end);
	}

	protected Statement() {
		super();
	}

	protected Statement(DLTKToken token) {
		super(token);
	}

	public abstract int getKind();

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj instanceof Statement) {
			Statement s = (Statement) obj;
			if (s.sourceEnd() < 0 || s.sourceStart() < 0) {
				return false;
			}				
			return sourceStart() == s.sourceStart()
					&& sourceEnd() == s.sourceEnd();
		}

		return false;
	}
}
