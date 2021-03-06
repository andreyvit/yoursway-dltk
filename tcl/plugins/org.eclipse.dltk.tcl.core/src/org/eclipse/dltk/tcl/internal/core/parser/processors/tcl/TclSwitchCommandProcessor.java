package org.eclipse.dltk.tcl.internal.core.parser.processors.tcl;

import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.core.ast.TclAdvancedExecuteExpression;
import org.eclipse.dltk.tcl.core.ast.TclSwitchStatement;

public class TclSwitchCommandProcessor extends AbstractTclCommandProcessor {

	public ASTNode process(TclStatement statement, ITclParser parser, 
			ASTNode parent) {

		TclSwitchStatement switchStatement = new TclSwitchStatement(statement
				.sourceStart(), statement.sourceEnd());
		this.addToParent(parent, switchStatement);
		int patternsStart = -1;
		for (int i = 1; i < statement.getCount(); i++) {
			Expression at = statement.getAt(i);
			if (at instanceof SimpleReference) {
				String value = ((SimpleReference) at).getName();

				if (!("-exact".equals(value) || "-regexp".equals(value)
						|| "-glob".equals(value) || "--".equals(value))) {
					// We found pattern
					patternsStart = i + 1;
					switchStatement.setString(at);
					break;
				}
			} else if (at instanceof StringLiteral) {
				patternsStart = i + 1;
				switchStatement.setString(at);
				break;
			}
			else if (at instanceof TclAdvancedExecuteExpression) {
				patternsStart = i + 1;
				switchStatement.setString(at);
				break;
			}
		}
		if (patternsStart != -1 && patternsStart < statement.getCount()) {
			Expression at = statement.getAt(patternsStart);
			if (at instanceof TclBlockExpression) {
				List list = ((TclBlockExpression) at).parseBlockSimple(false);
				Block bll = new Block(at.sourceStart(), at.sourceEnd());
				switchStatement.acceptBlock(bll);
				if (list != null) {
					for (Iterator iterator = list.iterator(); iterator
							.hasNext();) {
						ASTNode st = (ASTNode) iterator.next();
						if (st instanceof TclBlockExpression) {
							parserBlockAddTo(parser, switchStatement,
									(TclBlockExpression) st);
						}
						if (st instanceof TclStatement) {
							TclStatement stt = (TclStatement) st;
							for (int i = 0; i < stt.getCount(); i++) {
								ASTNode sttt = (ASTNode) stt.getAt(i);
								if (sttt instanceof TclBlockExpression) {
									parserBlockAddTo(parser, switchStatement,
											(TclBlockExpression) sttt);
								}
							}
						}
					}
				}
			} else {
				// We simple iterate and and parse all block expressions.
				int index = 0;
				for (int i = patternsStart; i < statement.getCount(); i++) {
					Expression st = statement.getAt(patternsStart);
					if (st instanceof TclBlockExpression) {
						parserBlockAddTo(parser, switchStatement,
								(TclBlockExpression) st);
					}
					index += 1;
				}
			}
		}
		return switchStatement;
	}

	private void parserBlockAddTo(ITclParser parser,
			TclSwitchStatement switchStatement, TclBlockExpression st) {
		Block block = new Block(st.sourceStart(), st.sourceEnd());
		String content = parser.substring(st.sourceStart(), st.sourceEnd());
		if (content.startsWith("{") && content.endsWith("}")) {
			content = parser
					.substring(st.sourceStart() + 1, st.sourceEnd() - 1);
		}
		switchStatement.addChild(block);
		parser.parse(content, st.sourceStart() + 1 - parser.getStartPos(),
				block);
	}
}
