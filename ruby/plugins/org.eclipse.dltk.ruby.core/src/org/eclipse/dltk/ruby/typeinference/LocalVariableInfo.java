/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ruby.ast.RubyAssignment;

public final class LocalVariableInfo {
	
	public final ASTNode declaringScope;
	
	public final RubyAssignment[] assignments;

	public LocalVariableInfo(final ASTNode declaringScope, final RubyAssignment[] assignments) {
		this.declaringScope = declaringScope;
		this.assignments = assignments;
	}
	
}
