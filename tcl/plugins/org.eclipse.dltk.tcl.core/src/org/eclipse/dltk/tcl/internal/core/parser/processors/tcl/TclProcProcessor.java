package org.eclipse.dltk.tcl.internal.core.parser.processors.tcl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.ast.expressions.TclExecuteExpression;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.internal.parser.TclParseUtils;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;

public class TclProcProcessor extends AbstractTclCommandProcessor {

	public ASTNode process(TclCommand command, ITclParser parser, int offset,
			ASTNode parent) {
		ASTNode node = parser.processLocal(command, offset, parent);
		if (!(node instanceof TclStatement)) {
			return null;
		}
		TclStatement statement = (TclStatement) node;
		if (statement.getCount() < 4) {
			this.report(parser,
					Messages.TclProcProcessor_Wrong_Number_of_Arguments,
					command.getStart(), command.getEnd(),
					ProblemSeverities.Error);
			return null;
		}
		Expression procName = statement.getAt(1);

		String sName = null;
		if (procName instanceof SimpleReference) {
			sName = ((SimpleReference) procName).getName();
		} else if (procName instanceof TclBlockExpression) {
			sName = ((TclBlockExpression) procName).getBlock();
		} else if (procName instanceof TclExecuteExpression) {
			sName = ((TclExecuteExpression) procName).getExpression();
		}
		if (sName == null || sName.length() == 0) {
			this.report(parser, Messages.TclProcProcessor_Empty_Proc_Name,
					command.getStart() + offset, command.getEnd() + offset,
					ProblemSeverities.Error);
			return null;
		}
		Expression procArguments = statement.getAt(2);
		Expression procCode = statement.getAt(3);

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

		MethodDeclaration method = new MethodDeclaration(command.getStart()
				+ offset, command.getEnd() + offset);
		method.setName(sName);
		method.setNameStart(procName.sourceStart());
		method.setNameEnd(procName.sourceEnd());
		method.acceptArguments(arguments);
		Block block = new Block(procCode.sourceStart(), procCode.sourceEnd());

		String content = parser.substring(procCode.sourceStart(), procCode
				.sourceEnd());
		if (content.startsWith("{") && content.endsWith("}")) {
			content = parser.substring(procCode.sourceStart() + 1, procCode
					.sourceEnd() - 1);
		}
		method.acceptBody(block);
		parser.parse(content,
				procCode.sourceStart() + 1 - parser.getStartPos(), block);
		this.addToParent(parent, method);
		return method;
	}
}
