/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *

 *******************************************************************************/
package org.eclipse.dltk.evaluation.types;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class ClassInstanceType implements IClassType {
	private TypeDeclaration fClass;
	private ModuleDeclaration fModule;

	public ClassInstanceType(ModuleDeclaration module, TypeDeclaration method) {
		this.fClass = method;
		this.fModule = module;
	}

	public boolean equals(Object obj) {

		if (obj instanceof ClassInstanceType) {
			ClassInstanceType m = (ClassInstanceType) obj;
			if (this.fClass == m.fClass && this.fModule == m.fModule) {
				return true;
			}
		}
		return false;
	}

	public TypeDeclaration getTypeDeclaration() {
		return this.fClass;
	}

	public String getTypeName() {
		if (fClass != null) {
			return "class:" + fClass.getName() + " instance";
		}
		return "class instance: !!unknown!!";
	}

	public boolean subtypeOf(IEvaluatedType type) {
		// TODO Auto-generated method stub
		return false;
	}

}
