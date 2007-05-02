/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.javascript.parser.visitors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.ast.PositionInformation;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.SourceElementRequestVisitor;

public class JavaScriptSourceElementRequestor extends
		SourceElementRequestVisitor {

	private static class TypeField {
		private String fName;

		private String fInitValue;

		private PositionInformation fPos;

		private Expression fExpression;

		private ASTNode fToNode;

		TypeField(String name, String initValue, PositionInformation pos,
				Expression expression, ASTNode toNode) {

			this.fName = name;
			this.fInitValue = initValue;
			this.fPos = pos;
			this.fExpression = expression;
			this.fToNode = toNode;
		}

		String getName() {

			return this.fName;
		}

		String getInitValue() {

			return this.fInitValue;
		}

		PositionInformation getPos() {

			return this.fPos;
		}

		Expression getExpression() {

			return this.fExpression;
		}

		ASTNode getToNode() {

			return this.fToNode;
		}

		public boolean equals(Object obj) {

			if (obj instanceof TypeField) {
				TypeField second = (TypeField) obj;
				return second.fName.equals(this.fName)
						&& second.fToNode.equals(this.fToNode);
			}
			return super.equals(obj);
		}

		public String toString() {

			return this.fName;
		}

	}

	// Used to prehold fields if adding in methods.
	private List/* <TypeField> */fNotAddedFields = new ArrayList/* <TypeField> */();

	/**
	 * Used to depermine duplicate names.
	 */
	private Map fTypeVariables = new HashMap();

	public JavaScriptSourceElementRequestor(ISourceElementRequestor requestor) {

		super(requestor);
	}

	/**
	 * Used to create Call value in python syntax.
	 */
	protected String makeLanguageDependentValue(Expression value) {

		String outValue = "";
		/*if (value instanceof ExtendedVariableReference) {
			// Lets use AST Printer to print extended variable in python like
			// syntax.
			StringWriter stringWriter = new StringWriter();
			CorePrinter printer = new CorePrinter(stringWriter);
			value.printNode(printer);
			printer.flush();
			return stringWriter.getBuffer().toString();
		}*/
		return outValue;
	}

	/**
	 * Parsers Expresssion and extract correct variable reference.
	 * 
	 * @param left
	 */
	private void addVariableReference(Expression left, Expression right,
			boolean inClass, boolean inMethod) {

		if (left == null) {
			throw new RuntimeException("addVariable expression can't be null");
		}
		if (left instanceof VariableReference) {
			VariableReference var = (VariableReference) left;

			if (!inMethod) { // for module static of class static variables.

				if (canAddVariables((ASTNode) this.fNodes.peek(), var.getName())) {
					ISourceElementRequestor.FieldInfo info = new ISourceElementRequestor.FieldInfo();
					info.modifiers = Modifiers.AccStatic;
					info.name = var.getName();
					info.nameSourceEnd = var.sourceEnd() - 1;
					info.nameSourceStart = var.sourceStart();
					info.declarationStart = var.sourceStart();
					this.fRequestor.enterField(info);
					if (right != null) {
						this.fRequestor.exitField(right.sourceEnd() - 1);
					} else {
						this.fRequestor.exitField(var.sourceEnd() - 1);
					}
				}
			}

		}/* else if (left instanceof ExtendedVariableReference) {
			// This is for in class and in method.
			if (inClass && inMethod) {
				ExtendedVariableReference extendedVariable = ((ExtendedVariableReference) left);

				List varParts = extendedVariable.getExpressions();
				if (extendedVariable.isDot(0)) {
					Expression first = (Expression) varParts.get(0);
					// support only local variable addition.
					// TODO: Add more complex variable addition.
					Expression second = (Expression) varParts.get(1);

					if (first instanceof VariableReference
							&& second instanceof VariableReference) {
						String varName = ((VariableReference) first).getName();
						MethodDeclaration currentMethod = this
								.getCurrentMethod();
						List <Argument> arguments = currentMethod
								.getArguments();
						if (arguments != null && arguments.size() > 0) {
							Argument firstArgument = (Argument) arguments
									.get(0);
							String argumentName = firstArgument.getName();
							if (argumentName.equals(varName)) {
								VariableReference var = (VariableReference) second;
								int initialValueStart = 0;
								int initialValueEnd = 0;
								if (right != null) {
									initialValueStart = right.sourceStart();
									initialValueEnd = right.sourceEnd();
								}
								PositionInformation pos = new PositionInformation(
										var.sourceStart(), var.sourceEnd(),
										initialValueStart, initialValueEnd);
								String initialString = this.makeValue(right);
								ASTNode method = (ASTNode) this.fNodes.pop();
								ASTNode toClass = (ASTNode) this.fNodes.peek();
								this.fNodes.push(method);

								TypeField field = new TypeField(var.getName(),
										initialString, pos, left, toClass);
								this.fNotAddedFields.add(field);
							}
						}
					}
				}
			} else if (left instanceof ExpressionList) { // Multiple
				// TODO: Add list of variables reporting.
				
				 * // assignment. ExpressionList list = (ExpressionList) left;
				 * List<Expression> exprs = list.getExpressions(); for
				 * (Expression expr : exprs) { }
				 
			} else {// TODO: dynamic variable handling not yet supported.

			}
		}*/
	}

	public boolean visit(Expression expression) throws Exception {

		return true;
	}

	public boolean endvisit(Expression expression) throws Exception {

		return true;
	}

	protected void onEndVisitMethod(MethodDeclaration method) {

		Iterator i = this.fNotAddedFields.iterator();
		while (i.hasNext()) {
			TypeField field = (TypeField) i.next();
			if (canAddVariables(field.getToNode(), field.getName())) {

				PositionInformation pos = field.getPos();

				ISourceElementRequestor.FieldInfo info = new ISourceElementRequestor.FieldInfo();
				info.modifiers = Modifiers.AccStatic;
				info.name = field.getName();
				info.nameSourceEnd = pos.nameEnd - 1;
				info.nameSourceStart = pos.nameStart;
				info.declarationStart = pos.sourceStart;
				this.fRequestor.enterField(info);
				this.fRequestor.exitField(pos.sourceEnd);

			}
		}
		this.fNotAddedFields.clear();
	}

	public boolean visit(Statement statement) throws Exception {

		return true;
	}

	private boolean canAddVariables(ASTNode type, String name) {

		if (this.fTypeVariables.containsKey(type)) {
			List variables = (List) this.fTypeVariables.get(type);
			if (variables.contains(name)) {
				return false;
			}
			variables.add(name);
			return true;
		} else {
			List variables = new ArrayList();
			variables.add(name);
			this.fTypeVariables.put(type, variables);
			return true;
		}
	}

	public boolean endvisit(Statement s) throws Exception {
		return true;
	}

	public boolean visit(MethodDeclaration method) throws Exception {
		this.fNodes.push(method);
		List/* < Argument > */args = method.getArguments();
		String[] parameter = new String[args.size()];
		for (int a = 0; a < args.size(); a++) {
			Argument arg = (Argument) args.get(a);
			parameter[a] = arg.getName();
		}
		ISourceElementRequestor.MethodInfo mi = new ISourceElementRequestor.MethodInfo();
		mi.parameterNames = parameter;
		mi.name = method.getName();
		mi.modifiers = method.getModifiers();
		mi.nameSourceStart = method.getNameStart();
		mi.nameSourceEnd = method.getNameEnd() - 1;
		mi.declarationStart = method.sourceStart();
		this.fRequestor.enterMethodRemoveSame(mi);
		this.fInMethod = true;
		this.fCurrentMethod = method;
		return true;
	}
}
