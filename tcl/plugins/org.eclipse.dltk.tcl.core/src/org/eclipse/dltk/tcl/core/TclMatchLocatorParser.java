/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *

 *******************************************************************************/
package org.eclipse.dltk.tcl.core;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.references.TypeReference;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.core.search.matching.PatternLocator;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.ast.expressions.TclExecuteExpression;
import org.eclipse.dltk.tcl.internal.parser.TclParseUtils;

public class TclMatchLocatorParser extends BasicTclMatchLocatorParser {

	public TclMatchLocatorParser(MatchLocator locator) {
		super(locator);
	}

	protected void processStatement(ASTNode node) {
		if (node == null) {
			return;
		}
		if (node instanceof TclStatement) {
			PatternLocator locator = this.getPatternLocator();
			TclStatement statement = (TclStatement) node;
			// process variables.
			FieldDeclaration[] fields = TclParseUtils
					.returnVariableDeclarations(statement);
			for (int k = 0; k < fields.length; ++k) {
				locator.match(fields[k], this.getNodeSet());
			}
			this.processReferences(statement);
		}
	}
	protected void processReferences(TclStatement statement) {
		Expression commandId = statement.getAt(0);
		PatternLocator locator;
		locator = this.getPatternLocator();
		if (commandId != null && commandId instanceof SimpleReference) {
			String name = ((SimpleReference) commandId).getName();
			if (name.startsWith("::")) {
				name = name.substring(2);
			}
			if (!kwMap.containsKey(name)) {
				// int argCount = statement.getCount() - 1;
				// System.out.println("Matching:"+ name);
				if (!kwMap.containsKey(name)) {
					String[] ns = name.split("::");
					for (int i = 0; i < ns.length; ++i) {
						if (ns[i].length() > 0) {
							if(i == ns.length - 1 ) {
								locator.match(new CallExpression(
										commandId.sourceStart(), commandId
										.sourceEnd(), null, ns[i], null),
										this.getNodeSet());
							}
							else {
								locator.match(new TypeReference(
										commandId.sourceStart(), commandId
										.sourceEnd(), ns[i]),
										this.getNodeSet());
							}
						}
					}
				}
			}
			if (name.equals("catch")) {
				if (statement.getCount() >= 2) {
					Expression e = statement.getAt(1);
					if (e instanceof TclBlockExpression) {
						this.processBlock(e);
					}
				}
			}
			if (name.equals("if")) {
				this.processIf(statement);
			}
			if (name.equals("for")) {
				this.processFor(statement);
			}
			if (name.equals("while")) {
				this.processWhile(statement);
			}
		}
		for (int j = 1; j < statement.getCount(); ++j) {
			Expression st = statement.getAt(j);
			if (st instanceof TclExecuteExpression) {
				TclExecuteExpression expr = (TclExecuteExpression) st;
				List exprs = expr.parseExpression();
				for (int i = 0; i < exprs.size(); ++i) {
					if (exprs.get(i) instanceof TclStatement) {
						this.processReferences((TclStatement) exprs.get(i));
					}
				}
			} else if (st instanceof StringLiteral) {
				int pos = 0;
				StringLiteral literal = (StringLiteral)st;
				String value = literal.getValue();
				pos = value.indexOf("$");
				while( pos != -1 ) {
					SimpleReference ref = TclParseUtils.findVariableFromString(literal, pos);
					if( ref != null ) {
						ref.setName(ref.getName().substring(1));
						ref.setEnd(ref.sourceEnd() - 1);
						locator.match(ref, this.getNodeSet());
						pos = pos + ref.getName().length();
					}
					pos = value.indexOf("$", pos + 1 );
				}
			}
			else if (st instanceof SimpleReference) {
				SimpleReference ref = (SimpleReference) st;
				String name = ref.getName();
				if (name.startsWith("$")) { // This is variable usage.
					ref.setName(name.substring(1));
					ref.setEnd(ref.sourceEnd() - 1);
					locator.match(ref, this.getNodeSet());
				}
			}
		}
	}

	private void processBlock(Expression e) {
		TclBlockExpression block = (TclBlockExpression) e;
		List code = block.parseBlock(block.sourceStart() + 1);
		for (int i = 0; i < code.size(); ++i) {
			if (code.get(i) instanceof TclStatement) {
				this.processReferences((TclStatement) code.get(i));
			}
		}
	}

	/**
	 * @deprecated
	 * @param statement
	 */
	private void processFor(TclStatement statement) {
		// TODO: Add variable corrections here.
		List exprs = statement.getExpressions();
		int len = exprs.size();

		if (1 < len) { // Process initializers
			Expression bl = (Expression) exprs.get(1);
			if (bl instanceof TclBlockExpression) {
				this.processBlock(bl);
			}
		}

		int bi = 4; // Skip expression
		if (bi < len) {
			Expression bl = (Expression) exprs.get(bi);
			if (bl instanceof TclBlockExpression) {
				this.processBlock(bl);
			}
		}
	}

	/**
	 * @deprecated
	 * @param statement
	 */
	private void processWhile(TclStatement statement) {
		List exprs = statement.getExpressions();
		int len = exprs.size();
		int bi = 2; // Skip expression
		if (bi < len) {
			Expression bl = (Expression) exprs.get(bi);
			if (bl instanceof TclBlockExpression) {
				this.processBlock(bl);
			}
		}
	}

	/**
	 * @deprecated
	 * @param statement
	 */
	private void processIf(TclStatement statement) {
		List exprs = statement.getExpressions();
		int len = exprs.size();
		for (int i = 0; i < len; ++i) {
			Expression e = (Expression) exprs.get(i);
			if (e instanceof SimpleReference) {
				String value = ((SimpleReference) e).getName();
				if (value.equals("if") || value.equals("elseif")) {
					int bi = i + 2; // Skip expression
					while (bi < len) {
						Expression bl = (Expression) exprs.get(bi);
						// Check for then statement
						if (bl instanceof SimpleReference) {
							String thenSt = ((SimpleReference) bl).getName();
							if (thenSt != null && thenSt.equals("then")) {
								bi++;
								continue;
							}
						} else if (bl instanceof TclBlockExpression) {
							this.processBlock(bl);
						}
						break;
					}
					i = bi - 1;
				} else if (value.equals("else")) {
					if (i + 1 < len) {
						Expression bl = (Expression) exprs.get(i + 1);
						if (bl instanceof TclBlockExpression) {
							this.processBlock(bl);
						}
					}
				}
			}
		}
	}
}
