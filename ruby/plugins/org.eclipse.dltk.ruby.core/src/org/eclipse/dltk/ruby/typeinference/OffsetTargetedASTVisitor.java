package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;

public class OffsetTargetedASTVisitor extends ASTVisitor {

	private final int requestedOffset;

	public OffsetTargetedASTVisitor(int requestedOffset) {
		this.requestedOffset = requestedOffset;
	}

	protected boolean interesting(ASTNode s) {
		if (s.sourceStart() >= 0 && s.sourceEnd() > s.sourceStart()
				&& (requestedOffset < s.sourceStart() || requestedOffset >= s.sourceEnd()))
			return false;
		return true;
	}

	public final boolean visit(MethodDeclaration s) {
		if (!interesting(s))
			return false;
		return visitInteresting(s);
	}

	protected boolean visitInteresting(MethodDeclaration s) {
		return visitGeneralInteresting(s);
	}

	public final boolean visit(ModuleDeclaration s) {
		if (!interesting(s))
			return false;
		return visitInteresting(s);
	}

	protected boolean visitInteresting(ModuleDeclaration s) {
		return visitGeneralInteresting(s);
	}

	public final boolean visit(TypeDeclaration s) throws Exception {
		if (!interesting(s))
			return false;
		return visitInteresting(s);
	}

	protected boolean visitInteresting(TypeDeclaration s) throws Exception {
		return visitGeneralInteresting(s);
	}

	public final boolean visit(Expression s) throws Exception {
		if (!interesting(s))
			return false;
		return visitInteresting(s);
	}

	protected boolean visitInteresting(Expression s) {
		return visitGeneralInteresting(s);
	}

	public final boolean visit(Statement s) throws Exception {
		if (!interesting(s))
			return false;
		return visitInteresting(s);
	}

	protected boolean visitInteresting(Statement s) {
		return visitGeneralInteresting(s);
	}

	public final boolean visitGeneral(ASTNode s) throws Exception {
		if (!interesting(s))
			return false;
		return visitGeneralInteresting(s);
	}

	protected boolean visitGeneralInteresting(ASTNode s) {
		return true;
	}

}
