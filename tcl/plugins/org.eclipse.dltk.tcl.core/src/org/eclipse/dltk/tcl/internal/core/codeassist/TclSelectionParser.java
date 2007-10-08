/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.core.codeassist;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.Declaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.internal.codeassist.select.SelectionNodeFound;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.ast.expressions.TclExecuteExpression;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.internal.core.codeassist.selection.SelectionOnAST;
import org.eclipse.dltk.tcl.internal.core.codeassist.selection.SelectionOnKeywordOrFunction;
import org.eclipse.dltk.tcl.internal.core.codeassist.selection.SelectionOnNode;
import org.eclipse.dltk.tcl.internal.core.codeassist.selection.SelectionOnVariable;
import org.eclipse.dltk.tcl.internal.parser.TclParseUtils;

public class TclSelectionParser extends TclAssistParser {
	public void handleNotInElement(ASTNode node, int position) {
		// System.out.println(node);
		if (node instanceof TypeDeclaration) {
			TypeDeclaration memberType = (TypeDeclaration) node;
			if (memberType.getNameStart() <= position
					&& memberType.getNameEnd() >= position) {
				ASTNode nde = new SelectionOnAST(memberType);
				throw new SelectionNodeFound(nde);
			}
		}
	}

	public void parseBlockStatements(ASTNode node, ASTNode inNode, int position) {
		if (node instanceof TclStatement) {
			TclStatement statement = (TclStatement) node;
			List expressions = statement.getExpressions();
			int len = expressions.size();
			boolean first = false;
			ASTNode completionNode = null;
			String completionToken = null;
			for (int i = 0; i < len; ++i) {
				ASTNode n = (ASTNode) expressions.get(i);
				if (n.sourceStart() <= position && n.sourceEnd() >= position) {
					if (i == 0) {
						first = true;
					}
					completionNode = n;
				}
			}
			if (completionNode instanceof SimpleReference) {
				completionToken = ((SimpleReference) completionNode).getName();
				if (completionToken.indexOf("[") != -1) {
					processInnerExecuteExpression(inNode, position,
							completionNode, completionToken);
				}
			}
			// Map Variable
			if (completionToken != null && completionToken.indexOf('(') != -1) {
				if (position < completionNode.sourceStart()
						+ completionToken.indexOf('(')) {
					completionToken = completionToken.substring(0,
							completionToken.indexOf('('));
				} else {
					completionToken = completionToken.substring(completionToken
							.indexOf('(') + 1, completionToken.length() - 1);
				}
			}
			if (completionNode instanceof TclBlockExpression) {
				TclBlockExpression block = (TclBlockExpression) completionNode;
				List s = block.parseBlock();
				if (s != null) {
					int slen = s.size();
					for (int u = 0; u < slen; ++u) {
						ASTNode n = (ASTNode) s.get(u);
						if (n != null && n.sourceStart() <= position
								&& n.sourceEnd() >= position) {
							parseBlockStatements(n, inNode, position);
						}
					}
				}
				handleNotInElement(inNode, position);
			}
			if (completionNode instanceof StringLiteral) {
				processStringLiteral(node, inNode, position, completionNode);
			}
			if (completionNode instanceof TclExecuteExpression) {
				TclExecuteExpression expr = (TclExecuteExpression) completionNode;
				List exprs = expr.parseExpression();
				for (int i = 0; i < exprs.size(); ++i) {
					ASTNode n = (ASTNode) exprs.get(i);
					if (n.sourceStart() <= position
							&& n.sourceEnd() >= position) {
						parseBlockStatements(n, expr, position);
					}
				}
				handleNotInElement(expr, position);
			}
			String var = TclParseUtils.returnVariableCheck(statement, position);
			if ((completionToken != null && completionToken.startsWith("$"))
					|| var != null) {
				this.assistNodeParent = inNode;
				if (var != null) {
					// ASTNode nde = new SelectionOnVariable("$" + var,
					// statement, node, inNode);
					// throw new SelectionNodeFound(nde);
					ASTNode nde = new SelectionOnAST(statement);
					throw new SelectionNodeFound(nde);
				} else {
					// inNode = this.findInNode(module, completionNode);
					ASTNode nde = new SelectionOnVariable(completionToken,
							completionNode, node, inNode);
					throw new SelectionNodeFound(nde);
				}
			} else {
				if (completionToken != null && completionNode != null && first) {
					ASTNode nde = new SelectionOnKeywordOrFunction(
							completionToken, completionNode, node);
					this.assistNodeParent = inNode;
					throw new SelectionNodeFound(nde);
				}
			}
			if (completionNode != null) {
				this.visitElements(completionNode, position);
			}
		} else if (node instanceof MethodDeclaration) {
			MethodDeclaration method = (MethodDeclaration) node;
			List statements = method.getStatements();
			boolean inStatement = false;
			if (method.getNameStart() <= position
					&& method.getNameEnd() >= position) {
				ASTNode nde = new SelectionOnAST(method);
				this.assistNodeParent = inNode;
				throw new SelectionNodeFound(nde);
			}
			if (statements != null) {
				int length = statements.size();
				for (int i = 0; i < length; i++) {
					ASTNode nde = (ASTNode) statements.get(i);
					if (nde.sourceStart() <= position
							&& nde.sourceEnd() > position) {
						inStatement = true;
						parseBlockStatements(nde, method, position);
					}
				}
			}
			if (!inStatement) {
				this.handleNotInElement(method, position);
			}
		}
		// Handle other cases here
		// New Parser cases
		if (node.sourceStart() <= position && node.sourceEnd() >= position) {
			visitElements(node, position);
			SelectionOnNode nde = new SelectionOnNode(node);
			nde.setPosition(position);
			throw new SelectionNodeFound(nde);
		}
	}

	private void visitElements(ASTNode node, int position) {
		if (!(node instanceof TclStatement)) {
			SelectionVisitor visitor = createSelectionVisitor(position);
			try {
				node.traverse(visitor);
			} catch (SelectionNodeFound e) {
				throw e;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private SelectionVisitor createSelectionVisitor(int position) {
		return new SelectionVisitor(position, this
				.getModule());
	}

	private void processStringLiteral(ASTNode node, ASTNode inNode,
			int position, ASTNode completionNode) {
		int pos = position - completionNode.sourceStart();

		String content = ((StringLiteral) completionNode).getValue();
		if (content.indexOf("[") != -1) {
			processInnerExecuteExpression(inNode, position, completionNode,
					content);
		}

		SimpleReference tok = TclParseUtils.findVariableFromString(
				(StringLiteral) completionNode, pos);
		if (tok != null) {
			this.assistNodeParent = inNode;
			ASTNode nde = new SelectionOnVariable(tok.getName(), tok, node,
					inNode);
			if (nde != null) {
				throw new SelectionNodeFound(nde);
			}
		}
	}

	private class SelectionVisitor extends ASTVisitor {
		private int position;
		ModuleDeclaration module;

		SelectionVisitor(int position, ModuleDeclaration module) {
			this.position = position;
			this.module = module;
		}

		public boolean visit(Statement s) throws Exception {
			if (s.sourceStart() <= position && s.sourceEnd() >= position) {
				if (s instanceof TclStatement) {
					ASTNode inNode = findInNode(this.module, s);
					TclSelectionParser.this.parseBlockStatements(s, inNode,
							position);
				} else if (s instanceof Declaration) {
					Declaration decl = (Declaration) s;
					if (decl.getNameStart() <= position
							&& position <= decl.getNameEnd()) {
						SelectionOnAST nde = new SelectionOnAST(s);
						throw new SelectionNodeFound(nde);
					}
				} 
			}
			return super.visit(s);
		}

		public boolean visit(Expression s) throws Exception {
			if (s.sourceStart() <= position && s.sourceEnd() >= position) {
				if (s instanceof StringLiteral) {
					ASTNode inNode = findInNode(this.module, s);
					TclSelectionParser.this.processStringLiteral(s, inNode,
							position, s);
				} else if (s instanceof SimpleReference) {
					SimpleReference ref = (SimpleReference) s;
					if (ref.getName().startsWith("$")) {
						ASTNode inNode = findInNode(this.module, s);
						assistNodeParent = inNode;
						ASTNode nde = new SelectionOnVariable(ref.getName(),
								ref, ref, inNode);
						throw new SelectionNodeFound(nde);
					} else {
						SimpleReference var = TclParseUtils
								.extractVariableFromString(ref.sourceStart(),
										ref.sourceEnd(), position
												- ref.sourceStart(), ref
												.getName());
						if (var != null) {
							ASTNode inNode = findInNode(this.module, s);
							assistNodeParent = inNode;
							ASTNode nde = new SelectionOnVariable(
									var.getName(), ref, ref, inNode);
							throw new SelectionNodeFound(nde);
						}
					}
				}
			}
			return super.visit(s);
		}
	};

	private ASTNode findInNode(ModuleDeclaration module, ASTNode node) {
		return TclParseUtil.getScopeParent(module, node);
	}

	private void processInnerExecuteExpression(ASTNode inNode, int position,
			ASTNode completionNode, String completionToken) {
		String toks = null;
		int pos = position - completionNode.sourceStart();

		int end = 0;
		for (end = pos; end < completionToken.length(); ++end) {
			char c = completionToken.charAt(end);
			if (c == '[') {
				break;
			}
			if (c == ']') {
				end = end + 1;
				break;
			}
		}
		int begin = 0;
		for (begin = pos - 1; begin > 0; --begin) {
			char c = completionToken.charAt(begin);
			if (c == ']') {
				break;
			}
			if (c == '[') {
				break;
			}
		}
		if (begin > 0 && begin < end && end <= completionToken.length()) {
			toks = completionToken.substring(begin, end);
		} else {
			return;
		}
		if (toks != null) {
			TclExecuteExpression ex = new TclExecuteExpression(completionNode
					.sourceStart()
					+ begin, completionNode.sourceStart() + end, toks);
			List exprs = ex.parseExpression();
			for (int i = 0; i < exprs.size(); ++i) {
				this.parseBlockStatements((ASTNode) exprs.get(i), inNode,
						position);
			}
			return;
		}
	}
}
