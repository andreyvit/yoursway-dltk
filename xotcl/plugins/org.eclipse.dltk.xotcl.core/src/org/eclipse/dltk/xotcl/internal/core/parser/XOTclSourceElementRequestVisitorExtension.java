package org.eclipse.dltk.xotcl.internal.core.parser;

import java.util.List;

import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.declarations.Declaration;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.tcl.core.extensions.ISourceElementRequestVisitorExtension;
import org.eclipse.dltk.tcl.internal.parser.TclSourceElementRequestVisitor;
import org.eclipse.dltk.tcl.internal.parser.TclSourceElementRequestVisitor.ExitFromType;
import org.eclipse.dltk.xotcl.core.IXOTclModifiers;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclFieldDeclaration;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodCallStatement;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclProcCallStatement;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclVariableDeclaration;

public class XOTclSourceElementRequestVisitorExtension implements
		ISourceElementRequestVisitorExtension {

	public int getModifiers(Declaration s) {
		if ((s.getModifiers() & IXOTclModifiers.AccXOTcl) != 0) {
			// This is ordinary class.
			return s.getModifiers();
		}
		return 0;
	}

	public boolean visit(Statement statement,
			TclSourceElementRequestVisitor original) {
		if (statement instanceof XOTclMethodCallStatement) {
			XOTclMethodCallStatement call = (XOTclMethodCallStatement) statement;
			SimpleReference callName = call.getCallName();
			int len = 0;
			if (call.getArgs() != null) {
				ASTListNode arguments = call.getArgs();
				List childs = arguments.getChilds();
				if (childs != null) {
					len = childs.size();
				}
			}

			original.getRequestor().acceptMethodReference(
					callName.getName().toCharArray(), len, call.sourceStart(),
					call.sourceEnd());
			return true;

			// Also lets add type references from here.
		} else if (statement instanceof XOTclProcCallStatement) {
			XOTclProcCallStatement call = (XOTclProcCallStatement) statement;
			SimpleReference callName = call.getCallName();
			int len = 0;

			original.getRequestor().acceptMethodReference(
					callName.getName().toCharArray(), len, call.sourceStart(),
					call.sourceEnd());

			return true;
		}
		return false;
	}

	public ExitFromType getExitExtended(MethodDeclaration method,
			TclSourceElementRequestVisitor original) {
		String tName = method.getDeclaringTypeName();
		if (tName == null) {
			tName = "";
		}
		return original.resolveType(method, tName + "::dummy", false);
	}

	public boolean extendedExitRequired(MethodDeclaration method,
			TclSourceElementRequestVisitor original) {
		return (method.getModifiers() & IXOTclModifiers.AccXOTcl) != 0;
	}

	public ExitFromType processField(FieldDeclaration decl,
			TclSourceElementRequestVisitor original) {
		if ((decl.getModifiers() & IXOTclModifiers.AccXOTcl) != 0
				&& decl instanceof XOTclVariableDeclaration) {
			XOTclFieldDeclaration field = (XOTclFieldDeclaration) decl;
			String tName = field.getDeclaringTypeName();
			if (tName == null) {
				tName = "";
			}
			return original.resolveType(field, tName + "::dummy", false);
		}
		return null;
	}
}
