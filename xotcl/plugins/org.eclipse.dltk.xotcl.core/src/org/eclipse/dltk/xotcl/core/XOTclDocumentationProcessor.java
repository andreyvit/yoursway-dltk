package org.eclipse.dltk.xotcl.core;

import java.util.Arrays;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.internal.parsers.raw.SimpleTclParser;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclParseException;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclScript;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclDocumentationNode;

public class XOTclDocumentationProcessor extends AbstractTclCommandProcessor
		implements ITclCommandProcessor {

	public XOTclDocumentationProcessor() {
		// TODO Auto-generated constructor stub
	}

	public void preprocessStatement(TclStatement st) {
		ASTNode[] nodes = (ASTNode[]) st.getExpressions().toArray(
				new ASTNode[st.getCount()]);
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] instanceof TclBlockExpression) {

				TclBlockExpression tclExecuteExpression = ((TclBlockExpression) nodes[i]);
				String expression = tclExecuteExpression.getBlock();
				expression = expression.substring(1, expression.length() - 1);
				Block newExpr = new Block();
				nodes[i] = newExpr;
				st.setExpressions(Arrays.asList(nodes));
				try {
					TclScript parse = SimpleTclParser.parse(expression);
					List commands = parse.getCommands();
					for (int j = 0; j < commands.size(); j++) {
						if (commands.get(j) instanceof TclCommand) {
							TclStatement st2 = TclParseUtil.convertToAST(
									(TclCommand) commands.get(j), ""
											.toCharArray(), 0, expression, 0);
							newExpr.addStatement(st2);
						}
					}
				} catch (TclParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		st.setExpressions(Arrays.asList(nodes));
	}

	public ASTNode process(TclCommand command, ITclParser parser, int offset,
			ASTNode parent) {
//		String docText = parser.getContent().substring(
//				offset + command.getStart(), command.getEnd() + 1 + offset);
//		System.out.println(docText);
		TclStatement st = TclParseUtil.convertToAST(command, parser
				.getFileName(), offset, parser.getContent(), parser
				.getStartPos());
		// preprocessStatement(st);
		final XOTclDocumentationNode doc = new XOTclDocumentationNode();
		String objName = TclParseUtil.getNameFromNode((ASTNode) (st.getAt(1)));
		if (objName != null) {
			doc.setStart(st.sourceStart());
			doc.setEnd(st.sourceEnd());
			if ("Class".equals(objName)) {
				String name = TclParseUtil.getNameFromNode((ASTNode) (st
						.getAt(2)));
				if (name != null) {
					processAddDescriptions(name, st, doc);
				}
			}
			else if ("Object".equals(objName)) {
				String name = TclParseUtil.getNameFromNode((ASTNode) (st
						.getAt(2)));
				if (name != null) {
					processAddDescriptions(name, st, doc);
				}
			}
			else {
				String docCommand = TclParseUtil.getNameFromNode((ASTNode) (st
						.getAt(2)));
				if (docCommand != null) {
					if ("instproc".equals(docCommand)) {
						String name = TclParseUtil
								.getNameFromNode((ASTNode) (st.getAt(3)));
						if (name != null) {
							processAddDescriptions(objName + "::" + name, st,
									doc);
						}
					}
				}
			}
		}
		this.addToParent(parent, doc);
		return doc;
	}

	private void processAddDescriptions(final String name, TclStatement st,
			final XOTclDocumentationNode doc) {
		preprocessStatement(st);
		try {
			st.traverse(new ASTVisitor() {
				public boolean visit(Statement s) throws Exception {
					if (s instanceof TclStatement) {
						TclStatement s2 = (TclStatement) s;
						String n = TclParseUtil.getNameFromNode((s2).getAt(0));
						if (n != null && n.equals("description")
								&& s2.getCount() > 1
								&& s2.getAt(1) instanceof TclBlockExpression) {
							TclBlockExpression bl = (TclBlockExpression) s2
									.getAt(1);
							String blvalue = bl.getBlock();
							doc.appendDescription(name, blvalue.substring(1,
									blvalue.length() - 1));
						}
					}
					return super.visit(s);
				}
			});
		} catch (Exception e) {
		}
	}

}
