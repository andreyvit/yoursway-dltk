package org.eclipse.dltk.tcl.internal.core.parser.processors.tcl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.internal.parsers.raw.SimpleTclParser;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclParseException;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclScript;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclWord;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.core.ast.BinaryExpression;
import org.eclipse.dltk.tcl.core.ast.TclSwitchStatement;

public class TclSwitchCommandProcessor extends AbstractTclCommandProcessor {

	private static class MismatchException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public MismatchException() {
		}

		public MismatchException(String str) {
			super(str);
		}
	}

	private static final int OPTIONS_START_OR_STRING_POS = 1; // position in
																// statements
																// list

	/**
	 * @return 'string' argument position
	 */
	private int matchOptions(TclStatement statement) {
		int pos = OPTIONS_START_OR_STRING_POS;
		while (pos < statement.getCount()
				&& statement.getAt(pos) instanceof SimpleReference) {
			SimpleReference argument = (SimpleReference) statement.getAt(pos);
			String argumentRepresentation = argument.getName();
			if (TclSwitchStatement.options.contains(argumentRepresentation))
				++pos;
			else
				break;
			if (TclSwitchStatement.isOptionsEndMarker(argumentRepresentation))
				break;
		}
		return pos;
	}

	/**
	 * @return an expression which is intended to be matched by the one of the
	 *         alternatives
	 * @throws MismatchException
	 */
	private ASTNode matchString(TclStatement statement, int pos,
			ITclParser parser) {
		if (pos >= statement.getCount()) {
			this.report(parser, "Syntax error: not enough arguments.",
					statement, ProblemSeverities.Error);
			throw new MismatchException();
		}
		return statement.getAt(pos);
	}

	/**
	 * @throws MismatchException
	 */
	private List /* <BinaryExpression> */matchAlternatives(
			TclStatement statement, int pos, ITclParser parser, ASTNode parent) {
		List /* <BinaryExpression> */list;
		List statements;
		if (statement.getAt(pos) instanceof TclBlockExpression) {
			statements = new ArrayList();
			TclBlockExpression block = null;
			try {
				block = (TclBlockExpression) statement.getAt(pos);
				String blockContent = block.getBlock();
				blockContent = blockContent.substring(1,
						blockContent.length() - 1);
				TclScript altBlock = SimpleTclParser.parse(blockContent);
				List commands = altBlock.getCommands();
				for (Iterator i = commands.iterator(); i.hasNext();) {
					TclCommand command = (TclCommand) i.next();
					for (Iterator j = command.getWords().iterator(); j
							.hasNext();) {
						TclCommand newCommand = new TclCommand();
						newCommand.addWord((TclWord) j.next());
						statements.add(parser.processLocal(newCommand, block
								.sourceStart() + 1 - parser.getStartPos(), null));
					}
				}
			} catch (TclParseException e) {
				this.report(parser, "Parsing error: " + e.getMessage(), block,
						ProblemSeverities.Error);
				throw new MismatchException();
			}
		} else {
			statements = statement.getExpressions();
			statements = statements.subList(pos, statements.size());
		}
		if (statements.size() % 2 != 0 || statements.size() < 2
				&& statements.size() > 0) {
			int start = ((ASTNode) statements.get(0)).sourceStart();
			int end = ((ASTNode) statements.get(statements.size() - 1))
					.sourceEnd();
			this.report(parser, "Incorrect alternatives block.", start, end,
					ProblemSeverities.Error);
			throw new MismatchException();
		}
		list = new ArrayList(statements.size() / 2);
		for (Iterator i = statements.iterator(); i.hasNext();) {
			ASTNode caseExpression = (ASTNode) i.next();
			ASTNode doExpression = (ASTNode) i.next();
			if (doExpression instanceof TclBlockExpression) {
				TclBlockExpression block = (TclBlockExpression) doExpression;
				String blockContent = block.getBlock();
				blockContent = blockContent.substring(1,
						blockContent.length() - 1);
				Block bl = new Block(block.sourceStart(), block.sourceEnd());
				parser.parse(blockContent, block.sourceStart() + 1
						- parser.getStartPos(), bl);
				doExpression = block;
			}
			list.add(new BinaryExpression(caseExpression,
					Expression.E_CONDITIONAL, doExpression));
		}
		return list;
	}

	public ASTNode process(TclCommand command, ITclParser parser, int offset,
			ASTNode parent) {
		ASTNode node = parser.processLocal(command, offset, parent);
		if (!(node instanceof TclStatement)) {
			return null;
		}
		TclStatement statement = (TclStatement) node;
		TclSwitchStatement switchStatement = new TclSwitchStatement(statement
				.sourceStart(), statement.sourceEnd());
		this.addToParent(parent, switchStatement);
		try {
			int startIndex = matchOptions(statement);
			ASTNode string = matchString(statement, startIndex, parser);
			List /* <BinaryExpression> */alternatives = matchAlternatives(
					statement, startIndex + 1, parser, switchStatement);
			switchStatement.setAlternatives(alternatives);
			switchStatement.setString(string);
			return switchStatement;
		} catch (MismatchException e) {
			// TODO: error reporting should be here
			return null;
		}
	}
}
