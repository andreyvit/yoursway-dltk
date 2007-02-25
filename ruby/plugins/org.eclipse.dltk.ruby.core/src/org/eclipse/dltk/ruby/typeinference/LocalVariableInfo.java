/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Assignment;

public final class LocalVariableInfo {
	
	public final ASTNode declaringScope;
	
	public final Assignment[] assignments;

	public LocalVariableInfo(final ASTNode declaringScope, final Assignment[] assignments) {
		this.declaringScope = declaringScope;
		this.assignments = assignments;
	}
	
}
