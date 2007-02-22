package org.eclipse.dltk.ruby.internal.parsers.jruby;

import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;

public class ASTUtils {

	private ASTUtils() {
		throw new AssertionError("Cannot instantiate utility class");
	}

	public static void setVisibility(MethodDeclaration methodDeclaration, int newVisibility) {
		int modifiers = methodDeclaration.getModifiers();
		modifiers = modifiers & ~(Modifiers.AccPublic | Modifiers.AccProtected | Modifiers.AccPrivate | Modifiers.AccDefault);
		methodDeclaration.setModifiers(modifiers | newVisibility);
	}
	
}
