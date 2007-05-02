/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.compiler;

import java.util.List;
import java.util.Stack;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.Literal;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.statements.Statement;

public class SourceElementRequestVisitor extends ASTVisitor {

	protected ISourceElementRequestor fRequestor = null;

	protected boolean fInClass = false; // if we are in class
	protected boolean fInMethod = false; // if we are in method

	protected MethodDeclaration fCurrentMethod = null;

	protected Stack fNodes = new Stack(); // Used to hold visited nodes in
											// deeph

	protected SourceElementRequestVisitor(ISourceElementRequestor requesor) {
		this.fRequestor = requesor;
	}

	protected MethodDeclaration getCurrentMethod() {
		return fCurrentMethod;
	}

	protected String makeLanguageDependentValue(ASTNode expr) {
		return "";
	}

	/**
	 * Called then end of methd are visited. Method are added to model before
	 * this call is done.
	 */
	protected void onEndVisitMethod(MethodDeclaration method) {

	}

	protected String[] processSuperClasses(TypeDeclaration type) {
		List names = type.getSuperClassNames();

		if (names.isEmpty()) {
			return null;
		}

		return (String[]) names.toArray(new String[names.size()]);
	}

	/**
	 * Called then end of class are visted. Class are added to model before this
	 * call is done.
	 */
	protected void onEndVisitClass(TypeDeclaration type) {
	}

	/**
	 * Creates correct string value from expression. For example for
	 * StringLiteral returns "value". And so on.
	 * 
	 * Return "" if it is imposible to make value from expression.
	 * 
	 * @param expr
	 * @return
	 */
	protected String makeValue(ASTNode stmt) {
//		if (!(stmt instanceof Expression))
//			return null;

		

		String value = "";
		if (stmt instanceof StringLiteral) {
			value = "\"" + ((StringLiteral) stmt).getValue() + "\"";
		} else if (stmt instanceof Literal) {
			value = ((Literal) stmt).getValue();
		} else /*if (stmt instanceof ExtendedVariableReference) */{
			// If it is Dot.
			// Lets make recursive value parsing in this case.
			value += makeLanguageDependentValue(stmt);
		}

		return value;
	}

	public boolean endvisit(MethodDeclaration method) throws Exception {
		fRequestor.exitMethod(method.sourceEnd());
		fInMethod = false;
		fCurrentMethod = null;

		onEndVisitMethod(method);

		fNodes.pop();
		return true;
	}

	public boolean endvisit(TypeDeclaration typeDeclaration) throws Exception {
		fRequestor.exitType(typeDeclaration.sourceEnd());
		fInClass = false;
		onEndVisitClass(typeDeclaration);
		fNodes.pop();
		return true;
	}

	public boolean visit(MethodDeclaration method) throws Exception {
		fNodes.push(method);
		List args = method.getArguments();

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

		fRequestor.enterMethod(mi);

		fInMethod = true;
		fCurrentMethod = method;
		return true;
	}

	public boolean visit(TypeDeclaration s) throws Exception {
		fNodes.push(s);

		ISourceElementRequestor.TypeInfo info = new ISourceElementRequestor.TypeInfo();
		info.modifiers = s.getModifiers();
		info.name = s.getName();
		info.nameSourceStart = s.getNameStart();
		info.nameSourceEnd = s.getNameEnd() - 1;
		info.declarationStart = s.sourceStart();
		info.superclasses = processSuperClasses(s);

		fRequestor.enterType(info);
		fInClass = true;

		return true;
	}

	public boolean endvisit(ModuleDeclaration s) throws Exception {
		fRequestor.exitModule(s.sourceEnd());
		fNodes.pop();
		return true;
	}

	public boolean visit(ModuleDeclaration declaration) throws Exception {
		fNodes.push(declaration);
		fRequestor.enterModule();
		return true;
	}

	public boolean endvisit(Expression s) throws Exception {
		fNodes.pop();
		return true;
	}

	public boolean endvisit(Statement s) throws Exception {
		fNodes.pop();
		return true;
	}

	public boolean visit(Expression expression) throws Exception {
		fNodes.push(expression);
		return true;
	}

	public boolean visit(Statement statement) throws Exception {
		fNodes.push(statement);
		return true;
	}
}
