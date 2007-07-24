/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.core.evaluation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.python.parser.ast.PythonImportFromStatement;
import org.eclipse.dltk.python.parser.ast.PythonImportStatement;
import org.eclipse.dltk.python.parser.ast.expressions.Assignment;
import org.eclipse.dltk.python.parser.ast.expressions.ExtendedVariableReference;
import org.eclipse.dltk.python.parser.ast.expressions.PythonImportAsExpression;
import org.eclipse.dltk.python.parser.ast.expressions.PythonImportExpression;


public class PythonASTFindVisitor extends ASTVisitor {
	private String fName = null;

	private List/* < ASTNode > */fAppropriateNodes = new ArrayList/* < ASTNode > */();

	private Map/* < ASTNode, ASTNode> */fParentsMap = new HashMap/*
																 * < ASTNode,
																 * ASTNode>
																 */();

	private boolean fDontVisitMethods = false;

	private int innerCount = 0;

	List/* < ASTNode > */fParents = new ArrayList/* < ASTNode > */();

	public PythonASTFindVisitor(String name) {
		fName = name;
	}

	public PythonASTFindVisitor(String name, boolean dontVisitClassOrMethod) {
		fName = name;
		fDontVisitMethods = dontVisitClassOrMethod;
	}

	void putChild(ASTNode node) {
		ASTNode parent = null;
		if (this.fParents.size() > 0) {
			parent = (ASTNode)this.fParents.get(this.fParents.size() - 1);
		}
		this.fParentsMap.put(node, parent);
	}

	void putPrevChild(ASTNode node) {
		ASTNode parent = null;
		if (this.fParents.size() > 1) {
			parent = (ASTNode)this.fParents.get(this.fParents.size() - 2);
		}
		this.fParentsMap.put(node, parent);
	}

	public List/* < ASTNode > */getNodes() {
		return this.fAppropriateNodes;
	}

	public Map/* < ASTNode, ASTNode > */getParents() {
		return this.fParentsMap;
	}

	public boolean visit(Expression expression) throws Exception {

		this.putChild(expression);
		if (expression instanceof Assignment) {
			this.fParents.add(expression);

			Assignment assignment = (Assignment) expression;
			Statement left = assignment.getLeft();
			if (left instanceof SimpleReference) {
				if (((SimpleReference) left).getName().equals(this.fName)) {
					this.fAppropriateNodes.add(expression);
				}
			}
			// FIXME: Add correct handling of this here.
			if (left instanceof ExtendedVariableReference) {
				ExtendedVariableReference ref = (ExtendedVariableReference) left;
				if (ref.isDot(0)) {
					Expression first = ref.getExpression(0);
					Expression second = ref.getExpression(1);
					if (first instanceof VariableReference
							&& ((VariableReference) first).getName().equals(
									"self")) {
						if (second instanceof VariableReference
								&& ((VariableReference) second).getName()
										.equals(this.fName)) {
							this.fAppropriateNodes.add(expression);

							List/*<Expression>*/ expressions = ref.getExpressions();
							Iterator i = expressions.iterator();
							while( i.hasNext() ) {
								Expression exp = (Expression)i.next();
								this.fParentsMap.put(exp, ref);
							}
						}
					}
				}
			}
		} else if (expression instanceof ExtendedVariableReference) {
			// add child parents to map.
			ExtendedVariableReference ref = (ExtendedVariableReference) expression;
			List/*<Expression>*/ expressions = ref.getExpressions();
			Iterator i = expressions.iterator();
			while( i.hasNext() ) {
				Expression exp = (Expression)i.next();
				this.fParentsMap.put(exp, ref);
			}
			return false;
		} else {
			this.fParents.add(expression);
		}
		// TODO: Add lambda handling here.
		return true;
	}

	public boolean visit(MethodDeclaration method) throws Exception {
		this.innerCount++;
		if (this.innerCount > 1) {
			if (fDontVisitMethods) {
				this.innerCount--;
				return false;
			}
		}
		this.putChild(method);
		this.fParents.add(method);
		if (method.getName().equals(this.fName)) {
			this.fAppropriateNodes.add(method);
		}
		return true;
	}

	public boolean visit(Statement statement) throws Exception {
		if (statement instanceof Argument) {
			this.putPrevChild(statement);
		} else {
			this.putChild(statement);
		}

		if (statement instanceof PythonImportFromStatement) {
			PythonImportFromStatement importStatement = (PythonImportFromStatement) statement;
			Map/*<String, String>*/ importedAsNames = importStatement
					.getImportedAsNames();
			if (importedAsNames.containsValue(this.fName)) {
				this.fAppropriateNodes.add(statement);
				return false;
			}
		}
		if (statement instanceof PythonImportStatement) {
			PythonImportStatement importStatement = (PythonImportStatement) statement;

			List/*<Expression>*/ imports = importStatement.getImports();
			Iterator i = imports.iterator();
			while( i.hasNext() ) {
				Expression imp = (Expression)i.next();
				String name = "";
				if (imp instanceof PythonImportExpression) {
					name = ((PythonImportExpression) imp).getName();
				} else if (imp instanceof PythonImportAsExpression) {
					name = ((PythonImportAsExpression) imp).getAsName();
				}
				if (name.equals(this.fName)) {
					this.fAppropriateNodes.add(statement);
					break;
				}
			}
			return false;
		}

		// if( !(statement instanceof Block ) ) {
		this.fParents.add(statement);
		// }
		return true;
	}

	public boolean visit(ModuleDeclaration declaration) throws Exception {
		this.innerCount++;
		if (this.innerCount > 1) {
			if (fDontVisitMethods) {
				this.innerCount--;
				return false;
			}
		}
		this.fParents.add(declaration);
		return true;
	}

	public boolean visit(TypeDeclaration typeDeclaration) throws Exception {
		this.innerCount++;
		if (this.innerCount > 1) {
			if (fDontVisitMethods) {
				this.innerCount--;
				return false;
			}
		}
		this.putChild(typeDeclaration);
		this.fParents.add(typeDeclaration);

		if (typeDeclaration.getName().equals(this.fName)) {
			this.fAppropriateNodes.add(typeDeclaration);
		}
		return true;
	}

	public boolean endvisit(Expression s) throws Exception {
		// if( s instanceof Assignment ) {
		this.fParents.remove(s);
		// }

		return true;
	}

	public boolean endvisit(MethodDeclaration method) throws Exception {
		this.innerCount--;
		this.fParents.remove(method);
		return true;
	}

	public boolean endvisit(ModuleDeclaration declaration) throws Exception {
		this.innerCount--;
		this.fParents.remove(declaration);
		return true;
	}

	public boolean endvisit(Statement statement) throws Exception {
		// if( !( statement instanceof Block ) ) {
		this.fParents.remove(statement);
		// }
		return true;
	}

	public boolean endvisit(TypeDeclaration typeDeclaration) throws Exception {
		this.innerCount--;
		this.fParents.remove(typeDeclaration);
		return true;
	}
}
