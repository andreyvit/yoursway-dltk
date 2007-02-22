/*
 * (c) 2002, 2005 xored software and others all rights reserved. http://www.xored.com
 */

package org.eclipse.dltk.ast;

import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.compiler.IProblem;

public abstract class ASTVisitor {
	public void acceptProblem(IProblem problem) {
	}

	public boolean visitGeneral(ASTNode node) throws Exception {
		return true;
	}
	
	public boolean endvisitGeneral(ASTNode node) throws Exception {
		return true;
	}

	public boolean visit(Statement s) throws Exception {
		return visitGeneral(s);
	}

	public boolean visit(Expression s) throws Exception {
		return visitGeneral(s);
	}

	public boolean visit(TypeDeclaration s) throws Exception {
		return visitGeneral(s);
	}

	public boolean visit(MethodDeclaration s) throws Exception {
		return visitGeneral(s);
	}

	public boolean visit(ModuleDeclaration s) throws Exception {
		return visitGeneral(s);
	}

	public boolean endvisit(Statement s) throws Exception {
		return endvisitGeneral(s);
	}

	public boolean endvisit(Expression s) throws Exception {
		return endvisitGeneral(s);
	}

	public boolean endvisit(TypeDeclaration s) throws Exception {
		return endvisitGeneral(s);
	}

	public boolean endvisit(MethodDeclaration s) throws Exception {
		return endvisitGeneral(s);
	}

	public boolean endvisit(ModuleDeclaration s) throws Exception {
		return endvisitGeneral(s);
	}
}
