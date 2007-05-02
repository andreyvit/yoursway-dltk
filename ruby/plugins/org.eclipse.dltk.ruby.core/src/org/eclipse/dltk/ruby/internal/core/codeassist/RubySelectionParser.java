/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.core.codeassist;

import org.eclipse.dltk.ast.ASTNode;

//import antlr.ANTLRException;


public class RubySelectionParser extends RubyAssistParser {
	
	public void handleNotInElement(ASTNode node, int position) {		
	}

	public void parseBlockStatements(ASTNode node, ASTNode inNode, int position) {
//		/**
//		 * This is possible function completion.
//		 */
//		if (node instanceof TclStatement) {
//			TclStatement statement = (TclStatement) node;
//			List expressions = statement.getExpressions();
//			int len = expressions.size();
//			boolean first = false;
//			ASTNode completionNode = null;
//			String completionToken = null;
//			for (int i = 0; i < len; ++i) {
//				ASTNode n = (ASTNode) expressions.get(i);
//				if (n.sourceStart() <= position && n.sourceEnd() >= position) {
//					if (i == 0) {
//						first = true;
//					}
//					completionNode = n;
//				}
//			}
//			if (completionNode instanceof SimpleReference) {
//				completionToken = ((SimpleReference) completionNode).getName();
//				if( completionToken.indexOf("[") != -1 ) {
//					processInnerExecuteExpression(inNode, position,
//							completionNode, completionToken);
//				}
//			} 
//			// Map Variable
//			if( completionToken != null && completionToken.indexOf('(') != -1 ) { 
//				if (position < completionNode.sourceStart() + completionToken.indexOf('(')) {
//					completionToken = completionToken.substring(0, completionToken.indexOf('(') );
//				} else {
//					completionToken = completionToken.substring(completionToken.indexOf('(') + 1,  
//							completionToken.length() - 1);				
//				}
//			}
//			if (completionNode instanceof TclBlockExpression) {
//				TclBlockExpression block = (TclBlockExpression) completionNode;
//				//try {
//					List s = block.parseBlock();
//					if (s != null) {
//						int slen = s.size();
//						for (int u = 0; u < slen; ++u) {
//							ASTNode n = (ASTNode) s.get(u);							
//							if (n != null && n.sourceStart() <= position && n.sourceEnd() >= position) {
//								parseBlockStatements(n, inNode, position);
//							}
//						}
//					}
//					handleNotInElement(inNode, position);
//				/*} catch (ANTLRException e) {
//					if (DLTKCore.DEBUG_PARSER)
//						e.printStackTrace();
//				}*/
//			}
//			if (completionNode instanceof StringLiteral) {
//				int pos = position - completionNode.sourceStart();
//				
//				String content = ((StringLiteral)completionNode).getValue();
//				if( content.indexOf("[") != -1 ) {
//					processInnerExecuteExpression(inNode, position,
//							completionNode, content);
//				}
//				
//				SimpleReference tok = TclParseUtils.findVariableFromString((StringLiteral) completionNode, pos);
//				if (tok != null) {
//					this.assistNodeParent = inNode;
//					ASTNode nde = new SelectionOnVariable(tok.getName(), tok, node, inNode);
//					if( nde != null ) {
//						throw new SelectionNodeFound(nde);
//					}
//				}
//			}
//			if (completionNode instanceof TclExecuteExpression) {
//				TclExecuteExpression expr = (TclExecuteExpression) completionNode;
//				List exprs = expr.parseExpression();
//				for (int i = 0; i < exprs.size(); ++i) {
//					ASTNode n = (ASTNode) exprs.get(i);
//					if (n.sourceStart() <= position && n.sourceEnd() >= position) {
//						parseBlockStatements(n, expr, position);
//					}
//				}
//				handleNotInElement(expr, position);
//			}
//			String var = TclParseUtils.returnVariableCheck(statement, position);
//			if (( completionToken != null && completionToken.startsWith("$") ) || var != null ) {
//				this.assistNodeParent = inNode;
//				if( var != null ) {					
////					ASTNode nde = new SelectionOnVariable("$" + var, statement, node, inNode);
////					throw new SelectionNodeFound(nde);
//					ASTNode nde = new SelectionOnAST(statement);
//					throw new SelectionNodeFound(nde);
//				}
//				else {
//					ASTNode nde = new SelectionOnVariable(completionToken, completionNode, node, inNode);
//					throw new SelectionNodeFound(nde);
//				}
//			} else {				
//				if( completionToken != null && completionNode != null ) {
//					ASTNode nde = new SelectionOnKeywordOrFunction(completionToken, completionNode, node);
//					this.assistNodeParent = inNode;
//					throw new SelectionNodeFound(nde);	
//				}
//			}
//		} else if (node instanceof MethodDeclaration) {
//			MethodDeclaration method = (MethodDeclaration) node;
//			List statements = method.getStatements();
//			boolean inStatement = false;
//			if (method.getNameStart() <= position && method.getNameEnd() >= position) {
//				ASTNode nde = new SelectionOnAST(method);
//				this.assistNodeParent = inNode;
//				throw new SelectionNodeFound(nde);
//			}
//			if (statements != null) {
//				int length = statements.size();
//				for (int i = 0; i < length; i++) {
//					ASTNode nde = (ASTNode) statements.get(i);
//					if (nde.sourceStart() <= position && nde.sourceEnd() >= position) {
//						inStatement = true;
//						parseBlockStatements(nde, method, position);
//					}
//				}
//			}			
//			if (!inStatement) {
//				this.handleNotInElement(method, position);
//			}
//		}
	}

	
}
