/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.references.TypeReference;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.core.search.matching.MatchLocatorParser;
import org.eclipse.dltk.core.search.matching.PatternLocator;
import org.eclipse.dltk.core.search.matching.PossibleMatch;
import org.eclipse.dltk.tcl.TclKeywords;
import org.eclipse.dltk.tcl.ast.TclModuleDeclaration;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.ast.expressions.TclExecuteExpression;
import org.eclipse.dltk.tcl.internal.parser.TclParseUtils;
import org.eclipse.dltk.tcl.internal.parser.TclSourceParser;

public class TclMatchLocatorParser extends MatchLocatorParser {
	private TclSourceParser parser;

	private static String[] kw = TclKeywords.getKeywords();

	private static Map kwMap = new HashMap();
	static {
		for (int q = 0; q < kw.length; ++q) {
			kwMap.put(kw[q], Boolean.TRUE);
		}
	}

	public TclMatchLocatorParser(MatchLocator locator) {
		super(locator);
		try {
			parser = (TclSourceParser) DLTKLanguageManager.getSourceParser(TclNature.NATURE_ID);
		} catch (CoreException e) {
			if( DLTKCore.DEBUG ) {
				e.printStackTrace();
			}
		}
	}

	public ModuleDeclaration parse(PossibleMatch possibleMatch) {
		TclModuleDeclaration module = (TclModuleDeclaration) parser.parse(possibleMatch.getFileName(), possibleMatch
						.getSourceContents().toCharArray(), null);
		module.rebuild();
		module.rebuildMethods();
		return module;
	}

	public void parseBodies(ModuleDeclaration unit) {
		TypeDeclaration[] types = unit.getTypes();
		if (types != null) {
			for (int i = 0; i < types.length; i++) {
				TypeDeclaration type = types[i];
				getPatternLocator().match(processType(type), getNodeSet());
				parseBodies(type);
			}
		}
		MethodDeclaration[] methods = unit.getFunctions();
		if (methods != null) {
			PatternLocator locator = getPatternLocator();
			for (int i = 0; i < methods.length; i++) {
				MethodDeclaration method = methods[i];
				if (method instanceof MethodDeclaration) {
					MethodDeclaration methodDeclaration = method;
									
					locator.match(processMethod(methodDeclaration),
							getNodeSet());
					parseBodies(methodDeclaration);
				}
			}
		}

		ASTNode[] nodes = unit.getNonTypeOrMethodNode();
		int length = nodes.length;
		for (int i = 0; i < length; i++) {
			processStatement(nodes[i]);
		}
	}

	private MethodDeclaration processMethod(MethodDeclaration m) {
		String name = m.getName();
		if (name.startsWith("::")) {
			name = name.substring(2);
		}
		if (name.indexOf("::") != -1) {
			int pos = name.lastIndexOf("::");
			String declTypeName = name.substring(0, pos);
			m.setDeclaringTypeName(declTypeName);
			name = name.substring(pos + 2);
		}
		m.setName(name);
		return m;
	}

	private TypeDeclaration processType(TypeDeclaration t) {
		String name = t.getName();
		if (name.startsWith("::")) {
			name = name.substring(2);
		}
		if (name.endsWith("::")) {
			name = name.substring(0, name.length() - 2);
		}
		t.setName(name);
		return t;
	}

	protected void parseBodies(TypeDeclaration type) {

		PatternLocator locator = getPatternLocator();

		MethodDeclaration[] methods = type.getMethods();
		if (methods != null) {
			for (int i = 0; i < methods.length; i++) {
				MethodDeclaration method = methods[i];
				if (method instanceof MethodDeclaration) {
					MethodDeclaration methodDeclaration = method;
					locator.match(processMethod(methodDeclaration),
							getNodeSet());
					parseBodies(methodDeclaration);
				}
			}
		}

		TypeDeclaration[] memberTypes = type.getTypes();
		if (memberTypes != null) {
			for (int i = 0; i < memberTypes.length; i++) {
				TypeDeclaration memberType = memberTypes[i];
				locator.match(processType(memberType), getNodeSet());
				this.parseBodies(memberType);
			}
		}
		ASTNode[] nodes = type.getNonTypeOrMethodNode();
		int length = nodes.length;
		for (int i = 0; i < length; i++) {
			processStatement(nodes[i]);
		}
	}

	protected void parseBodies(MethodDeclaration method) {
		List nodes = method.getStatements();
		int length = nodes.size();
		for (int i = 0; i < length; i++) {
			ASTNode node = (ASTNode) nodes.get(i);
			processStatement(node);
		}
	}

	private void processStatement(ASTNode node) {
		if (node == null) {
			return;
		}
		if (node instanceof TclStatement) {
			PatternLocator locator = getPatternLocator();
			TclStatement statement = (TclStatement) node;
			// process variables.
			FieldDeclaration[] fields = TclParseUtils
					.returnVariableDeclarations(statement);
			for (int k = 0; k < fields.length; ++k) {
				locator.match(fields[k], getNodeSet());
			}
			processReferences(statement);
		}
	}

	private void processReferences(TclStatement statement) {
		Expression commandId = statement.getAt(0);
		PatternLocator locator;
		locator = getPatternLocator();
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
								locator.match((CallExpression) new CallExpression(
										commandId.sourceStart(), commandId
										.sourceEnd(), null, ns[i], null),
										getNodeSet());
							}
							else {
								locator.match((TypeReference) new TypeReference(
										commandId.sourceStart(), commandId
										.sourceEnd(), ns[i]),
										getNodeSet());
							}
						}
					}
				}
			}
			if (name.equals("catch")) {
				if (statement.getCount() >= 2) {
					Expression e = statement.getAt(1);
					if (e instanceof TclBlockExpression)
						processBlock(e);
				}
			}
			if (name.equals("if")) {
				processIf(statement);
			}
			if (name.equals("for")) {
				processFor(statement);
			}
			if (name.equals("while")) {
				processWhile(statement);
			}
		}
		for (int j = 1; j < statement.getCount(); ++j) {
			Expression st = statement.getAt(j);
			if (st instanceof TclExecuteExpression) {
				TclExecuteExpression expr = (TclExecuteExpression) st;
				List exprs = expr.parseExpression();
				for (int i = 0; i < exprs.size(); ++i) {
					if (exprs.get(i) instanceof TclStatement) {
						processReferences((TclStatement) exprs.get(i));
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
						locator.match(ref, getNodeSet());
						pos = pos + ref.getName().length();
					}
					pos = value.indexOf("$", pos + 1 );
				}	
			}
//			else if (st instanceof TclBlockExpression) {
//				int pos = 0;
//				StringLiteral literal = (StringLiteral)st;
//				String value = literal.getValue();
//				pos = value.indexOf("$");
//				while( pos != -1 ) {
//					SimpleReference ref = TclParseUtils.findVariableFromString(literal, pos);
//					if( ref != null ) {
//						ref.setName(ref.getName().substring(1));
//						ref.setEnd(ref.sourceEnd() - 1);
//						locator.match(ref, getNodeSet());
//						pos = pos + ref.getName().length();
//					}
//					pos = value.indexOf("$", pos + 1 );
//				}	
//			}  
			else if (st instanceof SimpleReference) {
				SimpleReference ref = (SimpleReference) st;
				String name = ref.getName();
				if (name.startsWith("$")) { // This is variable usage.
					ref.setName(name.substring(1));
					ref.setEnd(ref.sourceEnd() - 1);
					locator.match(ref, getNodeSet());
				}
			}
		}
	}

	private void processBlock(Expression e) {
		TclBlockExpression block = (TclBlockExpression) e;
		List code = block.parseBlock(block.sourceStart() + 1);
		for (int i = 0; i < code.size(); ++i) {
			if (code.get(i) instanceof TclStatement) {
				processReferences((TclStatement) code.get(i));
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
				processBlock(bl);
			}
		}

		int bi = 4; // Skip expression
		if (bi < len) {
			Expression bl = (Expression) exprs.get(bi);
			if (bl instanceof TclBlockExpression) {
				processBlock(bl);
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
				processBlock(bl);
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
							processBlock(bl);
						}
						break;
					}
					i = bi - 1;
				} else if (value.equals("else")) {
					if (i + 1 < len) {
						Expression bl = (Expression) exprs.get(i + 1);
						if (bl instanceof TclBlockExpression) {
							processBlock(bl);
						}
					}
				}
			}
		}
	}
}
