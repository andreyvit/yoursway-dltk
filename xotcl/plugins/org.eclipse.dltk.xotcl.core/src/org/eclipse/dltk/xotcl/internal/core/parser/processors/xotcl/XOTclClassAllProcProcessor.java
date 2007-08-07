package org.eclipse.dltk.xotcl.internal.core.parser.processors.xotcl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.ast.expressions.TclExecuteExpression;
import org.eclipse.dltk.tcl.internal.parser.TclParseUtils;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.xotcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.xotcl.core.ITclParser;
import org.eclipse.dltk.xotcl.core.IXOTclModifiers;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclInstanceVariable;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodDeclaration;
import org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl.Messages;

/**
 * Process "#Class#instproc", "#Class#proc" and "#Object#proc", and instance
 * proc commands.
 * 
 * @author haiodo
 * 
 */

public class XOTclClassAllProcProcessor extends AbstractTclCommandProcessor {

	public XOTclClassAllProcProcessor() {
	}

	public ASTNode process(TclCommand command, ITclParser parser, int offset,
			ASTNode parent) {
		ASTNode node = parser.processLocal(command, offset);
		if (!(node instanceof TclStatement)) {
			return null;
		}
		TclStatement statement = (TclStatement) node;
		ASTNode decl = null;

		if (getDetectedParameter() != null
				&& (getDetectedParameter() instanceof ASTNode)) {
			decl = (ASTNode) getDetectedParameter();
		}
		if (decl == null) {
			return null;
		}
		if (statement.getCount() < 5) {
			this.report(parser,
					Messages.TclProcProcessor_Wrong_Number_of_Arguments,
					command.getStart(), command.getEnd(),
					ProblemSeverities.Error);
			return null;
		}
		int index = 2;
		Expression procName = statement.getAt(index);

		String sName = null;
		sName = extractName(procName);

		if (sName == null || sName.length() == 0) {
			this.report(parser, Messages.TclProcProcessor_Empty_Proc_Name,
					command.getStart() + offset, command.getEnd() + offset,
					ProblemSeverities.Error);
			return null;
		}
		Expression procArguments = statement.getAt(index + 1);
		Expression procCode = statement.getAt(index + 2);

		List arguments = null;
		if (procArguments instanceof TclBlockExpression) {
			List/* < Statement > */st = null;

			st = ((TclBlockExpression) procArguments).parseBlockSimple();

			arguments = TclParseUtils.parseArguments(st);
		}
		if (procArguments instanceof SimpleReference) {
			arguments = new ArrayList();
			Argument a = new Argument((SimpleReference) procArguments,
					procArguments.sourceStart(), null, 0);
			arguments.add(a);
		}

		XOTclMethodDeclaration method = new XOTclMethodDeclaration(command
				.getStart()
				+ offset, command.getEnd() + offset);
		method.setDeclaringXOTclType(decl);
		if (decl instanceof TypeDeclaration) {
			method.setDeclaringTypeName(((TypeDeclaration) decl).getName());
		}
		else if( decl instanceof XOTclInstanceVariable ) {
			method.setDeclaringTypeName(((XOTclInstanceVariable)decl).getName());
		}

		method.setModifiers(IXOTclModifiers.AccXOTcl);
		method.setName(sName);
		method.setNameStart(procName.sourceStart());
		method.setNameEnd(procName.sourceEnd());
		method.setTypeNameRef((SimpleReference) statement.getAt(0));
		method.acceptArguments(arguments);
		Block block = new Block(procCode.sourceStart(), procCode.sourceEnd());

		String content = parser.substring(procCode.sourceStart() + 1, procCode
				.sourceEnd() - 1);
		method.acceptBody(block);
		this.addToParent(parent, method);

		if (decl instanceof TypeDeclaration) {
			((TypeDeclaration)decl).getMethodList().add(method);
		}
		parser.parse(content, procCode.sourceStart() + 1, block);
		return method;
	}

	private String extractName(Expression procName) {
		if (procName instanceof SimpleReference) {
			return ((SimpleReference) procName).getName();
		} else if (procName instanceof TclBlockExpression) {
			return ((TclBlockExpression) procName).getBlock();
		} else if (procName instanceof TclExecuteExpression) {
			return ((TclExecuteExpression) procName).getExpression();
		}
		return null;
	}
}
