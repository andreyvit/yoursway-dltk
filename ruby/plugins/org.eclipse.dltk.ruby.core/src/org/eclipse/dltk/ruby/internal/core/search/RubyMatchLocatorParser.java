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
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.core.search.matching.MatchLocatorParser;
import org.eclipse.dltk.core.search.matching.PatternLocator;
import org.eclipse.dltk.core.search.matching.PossibleMatch;
import org.eclipse.dltk.ruby.ast.RubyAssignment;
import org.eclipse.dltk.ruby.ast.RubyConstantDeclaration;

public class RubyMatchLocatorParser extends MatchLocatorParser {
	public RubyMatchLocatorParser(MatchLocator locator) {
		super(locator);
	}

	public ModuleDeclaration parse(PossibleMatch possibleMatch) {
		ModuleDeclaration module = SourceParserUtil.getModuleDeclaration(
				(org.eclipse.dltk.core.ISourceModule) possibleMatch
						.getModelElement(), null);
		return module;
	}

	private ASTVisitor visitor = new ASTVisitor() {

		public boolean visitGeneral(ASTNode node) throws Exception {
			RubyMatchLocatorParser.this.processStatement(node);
			return true;
		}

		public boolean visit(MethodDeclaration s) throws Exception {
			RubyMatchLocatorParser.this.getPatternLocator().match(
					RubyMatchLocatorParser.this.processMethod(s),
					RubyMatchLocatorParser.this.getNodeSet());
			return true;
		}

		public boolean visit(TypeDeclaration s) throws Exception {
			RubyMatchLocatorParser.this.getPatternLocator().match(
					RubyMatchLocatorParser.this.processType(s),
					RubyMatchLocatorParser.this.getNodeSet());
			return true;
		}
	};

	public void parseBodies(ModuleDeclaration unit) {
		try {
			unit.traverse(this.visitor);
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

	private void processStatement(ASTNode node) {
		if (node == null) {
			return;
		}
		PatternLocator locator = this.getPatternLocator();
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
			locator.match(
			/*
			 * (CallExpression) new CallExpression(start, end,
			 * call.getReceiver(), call.getName(), call.getArgs())
			 */call, this.getNodeSet());
			if (call.getName().equals("new")) {
				ASTNode receiver = call.getReceiver();
				if (receiver instanceof ConstantReference) {
					TypeReference ref = new TypeReference(receiver
							.sourceStart(), receiver.sourceEnd(),
							((ConstantReference) receiver).getName());
					locator.match(ref, this.getNodeSet());
				}
			}
		} else if (node instanceof RubyAssignment) {
			// Assignment handling (this is static variable assignment.)

			RubyAssignment assignment = (RubyAssignment) node;
			ASTNode left = assignment.getLeft();
			if (left instanceof VariableReference) {
				VariableReference var = (VariableReference) left;
				FieldDeclaration field = new FieldDeclaration(var.getName(),
						var.sourceStart(), var.sourceEnd() - 1, var
								.sourceStart(), var.sourceEnd() - 1);
				locator.match(field, this.getNodeSet());
			}
		} else if (node instanceof VariableReference) {
			VariableReference variableReference = (VariableReference) node;
			int pos = variableReference.sourceStart();
			if (pos < 0) {
				pos = 0;
			}
			locator.match(new SimpleReference(pos, pos
					+ variableReference.getName().length(), variableReference
					.getName()), this.getNodeSet());
		} else if (node instanceof ConstantReference) {
			ConstantReference variableReference = (ConstantReference) node;
			int pos = variableReference.sourceStart();
			if (pos < 0) {
				pos = 0;
			}
			locator.match(new SimpleReference(pos, pos
					+ variableReference.getName().length(), variableReference
					.getName()), this.getNodeSet());
		} else if (node instanceof RubyConstantDeclaration) {

			RubyConstantDeclaration var = (RubyConstantDeclaration) node;
			SimpleReference name = var.getName();
			FieldDeclaration field = new FieldDeclaration(name.getName(), name
					.sourceStart(), name.sourceEnd(), name.sourceStart(), name
					.sourceEnd());
			locator.match(field, this.getNodeSet());

		}
	}
}
