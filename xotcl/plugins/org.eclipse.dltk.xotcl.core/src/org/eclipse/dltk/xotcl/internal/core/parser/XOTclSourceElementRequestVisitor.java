package org.eclipse.dltk.xotcl.internal.core.parser;

import java.util.List;

import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.ast.declarations.Declaration;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.tcl.ast.TclConstants;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.core.ast.TclGlobalVariableDeclaration;
import org.eclipse.dltk.tcl.core.ast.TclUpvarVariableDeclaration;
import org.eclipse.dltk.tcl.internal.parser.TclSourceElementRequestVisitor;
import org.eclipse.dltk.xotcl.core.IXOTclModifiers;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclFieldDeclaration;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodCallStatement;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclProcCallStatement;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclVariableDeclaration;

public class XOTclSourceElementRequestVisitor extends
		TclSourceElementRequestVisitor {

	public XOTclSourceElementRequestVisitor(ISourceElementRequestor requesor,
			IProblemReporter reporter) {
		super(requesor, reporter);
	}

	protected int getModifiers(Declaration s) {
		int flags = 0;

		if ((s.getModifiers() & Modifiers.AccAbstract) != 0) {
			flags |= Modifiers.AccAbstract;
		}
		if ((s.getModifiers() & Modifiers.AccNameSpace) != 0
				&& s instanceof TypeDeclaration) {
			return Modifiers.AccNameSpace | flags;
		}
		if ((s.getModifiers() & IXOTclModifiers.AccXOTcl) != 0) {
			// This is ordinary class.
			return IXOTclModifiers.AccXOTcl | flags;
		}
		return flags;
	}

	public boolean visit(Statement statement) throws Exception {
		if( !super.visit(statement) ) {
			return false;
		}
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

			this.fRequestor.acceptMethodReference(callName.getName()
					.toCharArray(), len, call.sourceStart(), call.sourceEnd());

			// Also lets add type references from here.
		} else if (statement instanceof XOTclProcCallStatement) {
			XOTclProcCallStatement call = (XOTclProcCallStatement) statement;
			SimpleReference callName = call.getCallName();
			int len = 0;

			this.fRequestor.acceptMethodReference(callName.getName()
					.toCharArray(), len, call.sourceStart(), call.sourceEnd());

			// Also lets add type references from here.
		}
		return true;
	}

	protected ExitFromType getExitExtended(MethodDeclaration method) {
		String tName = method.getDeclaringTypeName();
		if (tName == null) {
			tName = "";
		}
		return this.resolveType(method, tName + "::dummy", false);
	}

	protected boolean extendedExitRequired(MethodDeclaration method) {
		return (method.getModifiers() & IXOTclModifiers.AccXOTcl) != 0;
	}

	protected boolean processField(Statement statement) {
		FieldDeclaration decl = (FieldDeclaration) statement;
		ISourceElementRequestor.FieldInfo fi = new ISourceElementRequestor.FieldInfo();
		fi.nameSourceStart = decl.getNameStart();
		fi.nameSourceEnd = decl.getNameEnd() - 1;
		fi.declarationStart = decl.sourceStart();
		fi.modifiers = 0;
		if (statement instanceof TclGlobalVariableDeclaration) {
			fi.modifiers = org.eclipse.dltk.tcl.ast.TclConstants.TCL_FIELD_TYPE_GLOBAL
					| this.getModifiers(decl);
		} else if (statement instanceof TclUpvarVariableDeclaration) {
			fi.modifiers = org.eclipse.dltk.tcl.ast.TclConstants.TCL_FIELD_TYPE_UPVAR
					| this.getModifiers(decl);
		}

		boolean needExit = false;

		String arrayName = null;
		String arrayIndex = null;
		String name = decl.getName();
		if (TclParseUtil.isArrayVariable(name)) {
			arrayName = TclParseUtil.extractArrayName(name);
			arrayIndex = TclParseUtil.extractArrayIndex(name);
		}
		if (arrayName != null) {
			name = arrayName;
		}
		fi.name = name;
		String fullName = TclParseUtil.escapeName(name);
		ExitFromType exit = null;// this.resolveType(decl, fullName, false);
		if ((decl.getModifiers() & IXOTclModifiers.AccXOTcl) != 0
				&& decl instanceof XOTclVariableDeclaration) {
			XOTclFieldDeclaration field = (XOTclFieldDeclaration) decl;
			String tName = field.getDeclaringTypeName();
			if (tName == null) {
				tName = "";
			}
			exit = this.resolveType(field, tName + "::dummy", false);
		} else {
			exit = this.resolveType(decl, fullName, false);
		}
		needExit = this.fRequestor.enterFieldCheckDuplicates(fi);
		int end = decl.sourceEnd();
		if (needExit) {
			if (arrayName != null) {
				ISourceElementRequestor.FieldInfo fiIndex = new ISourceElementRequestor.FieldInfo();
				fiIndex.name = arrayName + "(" + arrayIndex + ")";
				fiIndex.nameSourceStart = decl.getNameStart();
				fiIndex.nameSourceEnd = decl.getNameEnd() - 1;
				fiIndex.declarationStart = decl.sourceStart();
				fiIndex.modifiers = TclConstants.TCL_FIELD_TYPE_INDEX
						| this.getModifiers(decl);
				if (this.fRequestor.enterFieldCheckDuplicates(fiIndex)) {
					this.fRequestor.exitField(end);
				}
			}
			this.fRequestor.exitField(end);
		}
		exit.go();
		return false;
	}
}
