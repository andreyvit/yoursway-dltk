package org.eclipse.dltk.itcl.internal.core.parser.processors;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.itcl.internal.core.IIncrTclModifiers;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;

public class IncrTclBodyCommandProcessor extends AbstractTclCommandProcessor {

	public IncrTclBodyCommandProcessor() {
	}

	public ASTNode process(TclCommand command, ITclParser parser, int offset,
			ASTNode parent) {
		TclStatement statement = parser.processLocal(command, offset, parent);
		if (statement == null
				|| (statement != null && statement.getCount() == 0)) {
			return null;
		}
		if (statement.getCount() < 4) {
			this.report(parser, "Wrong number of arguments", statement
					.sourceStart(), statement.sourceEnd(),
					ProblemSeverities.Error);
			addToParent(parent, statement);
			return statement;
		}
		Expression procName = statement.getAt(1);

		String sName = IncrTclUtils.extractMethodName(procName);
		if (sName == null || sName.length() == 0) {
			this.report(parser, "Wrong number of arguments", statement
					.sourceStart(), statement.sourceEnd(),
					ProblemSeverities.Error);
			addToParent(parent, statement);
			return statement;
		}
		Expression procArguments = statement.getAt(2);
		Expression procCode = statement.getAt(3);

		List arguments = IncrTclUtils.extractMethodArguments(procArguments);

		MethodDeclaration method = new MethodDeclaration(statement
				.sourceStart(), statement.sourceEnd());
		int index = sName.lastIndexOf("::");
		String className = sName.substring(0, index);
		String methodName = sName.substring(index + 2);
		method.setName(methodName);
		method.setNameStart(procName.sourceStart());
		method.setNameEnd(procName.sourceEnd());
		method.acceptArguments(arguments);
		// For correct modifiers we need to add
		IncrTclUtils.parseBlockAdd(parser, procCode, method);

		method.setModifier(IIncrTclModifiers.AccIncrTcl);
		ModuleDeclaration module = this.getModuleDeclaration();
		TypeDeclaration possiblyType = TclParseUtil.findTclTypeDeclarationFrom(
				module, parent, className, false);
		if (possiblyType != null) {
			MethodDeclaration[] methods = possiblyType.getMethods();
			for (int i = 0; i < methods.length; i++) {
				List args = methods[i].getArguments();
				if (methods[i].getName().equals(methodName)
						&& (args != null && args.size() == method
								.getArguments().size())) {
					// possiblyType.
					method.setModifier(methods[i].getModifiers());
					this.addToParent(possiblyType, method);
					break;
				}
			}
		} else {
			this.addToParent(parent, method);
		}

		return method;
	}

}
