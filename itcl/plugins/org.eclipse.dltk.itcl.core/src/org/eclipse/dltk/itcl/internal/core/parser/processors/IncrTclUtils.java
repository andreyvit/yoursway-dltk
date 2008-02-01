package org.eclipse.dltk.itcl.internal.core.parser.processors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.ast.expressions.TclExecuteExpression;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.internal.parser.TclParseUtils;

public class IncrTclUtils {
	public static String extractMethodName(Expression procName) {
		String sName = null;
		if (procName instanceof SimpleReference) {
			sName = ((SimpleReference) procName).getName();
		} else if (procName instanceof TclBlockExpression) {
			sName = ((TclBlockExpression) procName).getBlock();
		} else if (procName instanceof TclExecuteExpression) {
			sName = ((TclExecuteExpression) procName).getExpression();
		}
		return sName;
	}

	public static List extractMethodArguments(Expression procArguments) {
		List arguments = null;
		if (procArguments instanceof TclBlockExpression) {
			List/* < Statement > */st = null;

			st = ((TclBlockExpression) procArguments).parseBlockSimple(false);

			arguments = TclParseUtils.parseArguments(st);
		}
		if (procArguments instanceof SimpleReference) {
			arguments = new ArrayList();
			Argument a = new Argument((SimpleReference) procArguments,
					procArguments.sourceStart(), null, 0);
			arguments.add(a);
		}
		if (arguments == null) {
			return new ArrayList();
		}
		return arguments;
	}

	public static void parseBlockAdd(ITclParser parser, Expression procCode,
			MethodDeclaration method) {
		if (procCode != null) {
			Block block = new Block(procCode.sourceStart(), procCode
					.sourceEnd());
			method.acceptBody(block);
			parseAddToBlock(parser, procCode, block);
		}
	}

	public static void parseAddToBlock(ITclParser parser, Expression procCode,
			Block block) {
		String content = parser.substring(procCode.sourceStart(), procCode
				.sourceEnd());
		if (content.startsWith("{") && content.endsWith("}")) {
			content = parser.substring(procCode.sourceStart() + 1, procCode
					.sourceEnd() - 1);
		}
		parser.parse(content,
				procCode.sourceStart() + 1 - parser.getStartPos(), block);
	}
}
