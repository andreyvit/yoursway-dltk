package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.references.Reference;

/**
 * @author ssanders
 */
public class RubyASTUtil {

	private RubyASTUtil() {
	}

	public static String resolveClassName(ASTNode node) {
		String className = ""; //$NON-NLS-1$

		if (node instanceof Reference) {
			className = ((Reference) node).getStringRepresentation();
		} else if (node instanceof RubyColonExpression) {
			RubyColonExpression rcExp = (RubyColonExpression) node;

			if (rcExp.getLeft() != null) {
				className = resolveClassName(rcExp.getLeft());

				className += "::"; //$NON-NLS-1$
			}

			className += rcExp.getName();
		}

		return className;
	}

}
