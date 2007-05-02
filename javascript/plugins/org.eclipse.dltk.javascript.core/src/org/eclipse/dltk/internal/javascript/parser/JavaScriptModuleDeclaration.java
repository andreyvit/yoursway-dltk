/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.javascript.parser;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.internal.javascript.typeinference.HostCollection;

public class JavaScriptModuleDeclaration extends ModuleDeclaration{

	HostCollection collection;
	Map  functionMap;
	public JavaScriptModuleDeclaration(int sourceLength) {
		super(sourceLength,true);
	
	}

	public void doRebuild() {
		buildAST(getTypeList(), getFunctionList(), getVariablesList() );		
	}
	
	public void setFunctionMap(Map functionMap) {
		this.functionMap = functionMap;
	}

	/**
	 * Extends AST and returns top types and functions to selected lists.
	 * 
	 * @param declaration
	 * @param types
	 * @param functions
	 */
	public void buildAST(List types, List functions, List vars) {
		List statements = getStatements();
		if (statements != null) {
			Iterator i = statements.iterator();
			while (i.hasNext()) {
				ASTNode node = (ASTNode) i.next();
				if (node instanceof MethodDeclaration) {
					functions.add(node);
				} else if (node instanceof TypeDeclaration) {
					types.add(node);
				}
				else if (node instanceof FieldDeclaration){
					vars.add(node);
				}
			}
		}
	}

	public HostCollection getCollection() {
		return collection;
	}
	public boolean isEmpty(){
		return false;
	}

	public void setCollection(HostCollection collection) {
		this.collection = collection;
	}

	public Map getFunctionMap() {
		return functionMap;
	}

	private Collection references;
	public void setReferences(Collection references) {
		this.references=references;
	}
	
	public Collection getReferences(){
		return this.references;
	}
}
