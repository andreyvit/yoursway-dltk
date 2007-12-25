/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *

 *******************************************************************************/
package org.eclipse.dltk.evaluation.types;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class FunctionType implements IFunctionType {
	private MethodDeclaration fMethod;
	private ModuleDeclaration fModule;

	private boolean fWrongCall = false; // set to true then call with diffirent
										// arguments.

	public FunctionType(ModuleDeclaration module, MethodDeclaration method) {
		this.fMethod = method;
		this.fModule = module;
	}

	public FunctionType(ModuleDeclaration module, MethodDeclaration method,
			boolean wrongCall) {
		this.fMethod = method;
		this.fModule = module;
		this.fWrongCall = wrongCall;
	}

	public boolean equals(Object obj) {

		if (obj instanceof FunctionType) {
			FunctionType m = (FunctionType) obj;
			if (this.fMethod == m.fMethod && this.fModule == m.fModule) {
				return true;
			}
		}
		return false;
	}
	public int hashCode() {
		return this.fMethod.hashCode()*10 + this.fModule.hashCode();
	}

	public String getTypeName() {
		String add = "";
		if (this.fWrongCall) {
			add = " wrong arguments call";
		}
		if (fMethod != null) {
			return "function:" + fMethod.getName() + add;
		}
		return "function: !!unknown!!";
	}

	public ASTNode getFunction() {
		return this.fMethod;
	}

	public boolean subtypeOf(IEvaluatedType type) {
		// TODO Auto-generated method stub
		return false;
	}

}
