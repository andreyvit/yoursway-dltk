package org.eclipse.dltk.ast.utils;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;

public class ASTUtil {
	public static List getStatements(ASTNode node) {
		if (node instanceof ModuleDeclaration) {
			return ((ModuleDeclaration) node).getStatements();
		} else if (node instanceof TypeDeclaration) {
			return ((TypeDeclaration) node).getStatements();
		} else if (node instanceof MethodDeclaration) {
			return ((MethodDeclaration) node).getStatements();
		}
		return null;
	}
}
