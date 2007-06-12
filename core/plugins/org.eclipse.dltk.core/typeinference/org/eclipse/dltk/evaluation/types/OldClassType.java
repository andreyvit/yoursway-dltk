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

public class OldClassType implements IClassType {
	private TypeDeclaration fClass;
	private ModuleDeclaration fModule;

	public OldClassType(ModuleDeclaration module, TypeDeclaration method) {
		this.fClass = method;
		this.fModule = module;
	}

	public boolean equals(Object obj) {

		if (obj instanceof OldClassType) {
			OldClassType m = (OldClassType) obj;
			if (this.fClass == m.fClass && this.fModule == m.fModule) {
				return true;
			}
		}
		return false;
	}

	public String getTypeName() {
		if (fClass != null) {
			return "class:" + fClass.getName();
		}
		return "class: !!unknown!!";
	}

	public TypeDeclaration getTypeDeclaration() {

		return this.fClass;
	}

	public boolean subtypeOf(IEvaluatedType type) {
		// TODO Auto-generated method stub
		return false;
	}

}
