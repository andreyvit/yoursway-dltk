package org.eclipse.dltk.itcl.internal.core.parser.processors;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.itcl.internal.core.IIncrTclModifiers;
import org.eclipse.dltk.itcl.internal.core.parser.ast.IncrTclEnsemble;
import org.eclipse.dltk.itcl.internal.core.parser.ast.IncrTclEnsemblePart;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;

public class IncrTclEnsembleCommandProcessor extends
		AbstractTclCommandProcessor {

	public IncrTclEnsembleCommandProcessor() {
	}

	public ASTNode process(TclCommand command, ITclParser parser, int offset,
			ASTNode parent) {
		TclStatement statement = parser.processLocal(command, offset, parent);
		if (statement == null
				|| (statement != null && statement.getCount() == 0)) {
			return null;
		}
		return processEnsemble(parent, statement, parser);
	}

	private ASTNode processEnsemble(ASTNode parent, TclStatement statement,
			ITclParser parser) {
		Expression classNameExpr = statement.getAt(1);
		Expression blockExpr = statement.getAt(2);
		if (classNameExpr instanceof SimpleReference) {
			IncrTclEnsemble ensamble = new IncrTclEnsemble(statement
					.sourceStart(), statement.sourceEnd());
			ensamble.setName(((SimpleReference) classNameExpr).getName());
			ensamble.setNameStart(classNameExpr.sourceStart());
			ensamble.setNameEnd(classNameExpr.sourceEnd());

			ensamble.setModifiers(IIncrTclModifiers.AccIncrTcl);
			this.addToParent(parent, ensamble);

			if (blockExpr instanceof TclBlockExpression) {
				TclBlockExpression block = (TclBlockExpression) blockExpr;
				List commands = block.parseBlockSimple(false);
				for (int i = 0; i < commands.size(); i++) {
					ASTNode nde = (ASTNode) commands.get(i);
					if (nde instanceof TclStatement) {
						TclStatement st = (TclStatement) nde;
						Expression commandName = st.getAt(0);
						if (commandName instanceof SimpleReference) {
							String commandNameValue = ((SimpleReference) commandName)
									.getName();
							if (commandNameValue.equals("ensemble")) {
								processEnsemble(ensamble, st, parser);
							} else if (commandNameValue.equals("part")) {
								processPart(ensamble, st, parser);
							}
						}
					}
				}
			} else {
				List expressions = statement.getExpressions();
				List subList = expressions.subList(2, expressions.size());
				TclStatement subSt = new TclStatement(subList);
				processPart(ensamble, subSt, parser);
			}
			return ensamble;
		}
		return null;
	}

	private void processPart(IncrTclEnsemble ensamble, TclStatement statement,
			ITclParser parser) {
		if (statement.getCount() < 2) {
			this.report(parser, "Wrong number of arguments", statement
					.sourceStart(), statement.sourceEnd(),
					ProblemSeverities.Error);
			addToParent(ensamble, statement);
			return;
		}
		Expression procName = statement.getAt(1);

		String sName = IncrTclUtils.extractMethodName(procName);
		if (sName == null || sName.length() == 0) {
			this.report(parser, "Wrong number of arguments", statement
					.sourceStart(), statement.sourceEnd(),
					ProblemSeverities.Error);
			return;
		}
		Expression procArguments = null;// statement.getAt(2);
		Expression procCode = null;// statement.getAt(3);
		if (statement.getCount() >= 3) {
			procArguments = statement.getAt(2);
		}
		if (statement.getCount() == 4) {
			procCode = statement.getAt(3);
		}

		List arguments = IncrTclUtils.extractMethodArguments(procArguments);

		IncrTclEnsemblePart part = new IncrTclEnsemblePart(statement
				.sourceStart(), statement.sourceEnd());
		part.setName(sName);
		part.setNameStart(procName.sourceStart());
		part.setNameEnd(procName.sourceEnd());
		part.acceptArguments(arguments);
		part.setModifier(IIncrTclModifiers.AccIncrTcl);
		Block block = new Block(procCode.sourceStart(), procCode.sourceEnd());
		part.acceptBody(block);
		IncrTclUtils.parseAddToBlock(parser, procCode, block);
		this.addToParent(ensamble, part);
	}

	public void addToParent(ASTNode parent, ASTNode node) {
		if (parent instanceof IncrTclEnsemble
				&& node instanceof IncrTclEnsemble) {
			IncrTclEnsemble ensemble = (IncrTclEnsemble) parent;
			ensemble.addEnsamble((IncrTclEnsemble) node);
		}
		if (parent instanceof IncrTclEnsemble
				&& node instanceof IncrTclEnsemblePart) {
			IncrTclEnsemble ensemble = (IncrTclEnsemble) parent;
			ensemble.addPart((IncrTclEnsemblePart) node);
		}
		super.addToParent(parent, node);
	}

}
