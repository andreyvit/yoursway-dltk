/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.core.search;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.ast.references.ConstantReference;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.references.TypeReference;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.core.search.matching.MatchLocatorParser;
import org.eclipse.dltk.core.search.matching.PatternLocator;
import org.eclipse.dltk.core.search.matching.PossibleMatch;
import org.eclipse.dltk.ruby.ast.RubyAssignment;
import org.eclipse.dltk.ruby.ast.RubyConstantDeclaration;
import org.eclipse.dltk.ruby.internal.parser.RubySourceElementParser;

public class RubyMatchLocatorParser extends MatchLocatorParser {
	// private JRubySourceParser parser;

	public RubyMatchLocatorParser(MatchLocator locator) {
		super(locator);
		// parser = new JRubySourceParser(null);
	}

	public ModuleDeclaration parse(PossibleMatch possibleMatch) {
		// ModuleDeclaration module =
		// parser.parse(possibleMatch.getSourceContents());
		ModuleDeclaration module = RubySourceElementParser
				.parseModule((org.eclipse.dltk.core.ISourceModule) possibleMatch
						.getModelElement());
		// module.rebuild();
		return module;
	}

	private ASTVisitor visitor = new ASTVisitor() {

		public boolean visitGeneral(ASTNode node) throws Exception {
			processStatement(node);
			return true;
			// return super.visitGeneral(node);
		}

		public boolean visit(MethodDeclaration s) throws Exception {
			getPatternLocator().match(processMethod(s), getNodeSet());
			return true;
		}

		// public boolean visit(ModuleDeclaration s) throws Exception {
		// return true;
		// }

		public boolean visit(TypeDeclaration s) throws Exception {
			getPatternLocator().match(processType(s), getNodeSet());
			return true;
		}
	};

	public void parseBodies(ModuleDeclaration unit) {
		// TypeDeclaration[] types = unit.getTypes();
		// if (types != null) {
		// for (int i = 0; i < types.length; i++) {
		// TypeDeclaration type = types[i];
		// getPatternLocator().match(processType(type), getNodeSet());
		// parseBodies(type);
		// }
		// }
		// MethodDeclaration[] methods = unit.getFunctions();
		// if (methods != null) {
		// PatternLocator locator = getPatternLocator();
		// for (int i = 0; i < methods.length; i++) {
		// MethodDeclaration method = methods[i];
		// if (method instanceof MethodDeclaration) {
		// MethodDeclaration methodDeclaration = method;
		// locator.match(processMethod(methodDeclaration), getNodeSet());
		// parseBodies(methodDeclaration);
		// }
		// }
		// }
		//
		// // ASTNode[] nodes = unit.getNonTypeOrMethodNode();
		// // int length = nodes.length;
		// // for (int i = 0; i < length; i++) {
		// // processStatement(nodes[i]);
		// // }
		try {
			unit.traverse(visitor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private MethodDeclaration processMethod(MethodDeclaration m) {
		return m;
	}

	private TypeDeclaration processType(TypeDeclaration t) {
		return t;
	}

	// protected void parseBodies(TypeDeclaration type) {
	//
	// PatternLocator locator = getPatternLocator();
	//
	// MethodDeclaration[] methods = type.getMethods();
	// if (methods != null) {
	// for (int i = 0; i < methods.length; i++) {
	// MethodDeclaration method = methods[i];
	// if (method instanceof MethodDeclaration) {
	// MethodDeclaration methodDeclaration = method;
	// locator.match(processMethod(methodDeclaration), getNodeSet());
	// parseBodies(methodDeclaration);
	// }
	// }
	// }
	//
	// TypeDeclaration[] memberTypes = type.getTypes();
	// if (memberTypes != null) {
	// for (int i = 0; i < memberTypes.length; i++) {
	// TypeDeclaration memberType = memberTypes[i];
	// locator.match(processType(memberType), getNodeSet());
	// this.parseBodies(memberType);
	// }
	// }
	// // ASTNode[] nodes = type.getNonTypeOrMethodNode();
	// // int length = nodes.length;
	// // for (int i = 0; i < length; i++) {
	// // processStatement(nodes[i]);
	// // }
	// try {
	// type.traverse(visitor);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// protected void parseBodies(MethodDeclaration method) {
	// // List nodes = method.getStatements();
	// // int length = nodes.size();
	// // for (int i = 0; i < length; i++) {
	// // ASTNode node = (ASTNode) nodes.get(i);
	// // processStatement(node);
	// // }
	// try {
	// method.traverse(visitor);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	private void processStatement(ASTNode node) {
		if (node == null) {
			return;
		}
		PatternLocator locator = getPatternLocator();
		if (node instanceof CallExpression) {
			CallExpression call = (CallExpression) node;
			int start = call.sourceStart();
			int end = call.sourceEnd();
			if (start < 0) {
				start = 0;
			}
			if (end < 0) {
				end = 1;
			}
			locator
					.match(
							/*
							 * (CallExpression) new CallExpression(start, end,
							 * call.getReceiver(), call.getName(),
							 * call.getArgs())
							 */call,
							getNodeSet());
			if( call.getName().equals("new")) {
				ASTNode receiver = call.getReceiver();
				if( receiver instanceof ConstantReference ) {
					TypeReference ref = new TypeReference(receiver.sourceStart(), receiver.sourceEnd(), ((ConstantReference)receiver).getName());
					locator.match(ref, getNodeSet());
				}
			}
		} else if (node instanceof RubyAssignment) {
			// Assignment handling (this is static variable assignment.)

			RubyAssignment assignment = (RubyAssignment) node;
			ASTNode left = assignment.getLeft();
			if (left instanceof VariableReference) {
				VariableReference var = (VariableReference) left;
				FieldDeclaration field = new FieldDeclaration(var.getName(),
						var.sourceStart(), var.sourceEnd()-1, var.sourceStart(),
						var.sourceEnd()-1);
				locator.match(field, getNodeSet());
			}
		} else if (node instanceof VariableReference) {
			VariableReference variableReference = (VariableReference) node;
			int pos = variableReference.sourceStart();
			if (pos < 0) {
				pos = 0;
			}
			locator.match((SimpleReference) new SimpleReference(pos, pos
					+ variableReference.getName().length(), variableReference
					.getName()), getNodeSet());
		} else if (node instanceof ConstantReference) {
			ConstantReference variableReference = (ConstantReference) node;
			int pos = variableReference.sourceStart();
			if (pos < 0) {
				pos = 0;
			}
			locator.match((SimpleReference) new SimpleReference(pos, pos
					+ variableReference.getName().length(), variableReference
					.getName()), getNodeSet());
		} else if (node instanceof RubyConstantDeclaration) {

			RubyConstantDeclaration var = (RubyConstantDeclaration) node;
			SimpleReference name = var.getName();
			FieldDeclaration field = new FieldDeclaration(name.getName(), name
					.sourceStart(), name.sourceEnd(), name.sourceStart(), name
					.sourceEnd());
			locator.match(field, getNodeSet());

		}
	}
}
