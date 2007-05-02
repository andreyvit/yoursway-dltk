/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ast.references.VariableKind;
import org.eclipse.dltk.ti.IContext;
import org.eclipse.dltk.ti.goals.AbstractTypeGoal;

/**
 * Type of a class/instance/global/constant variables
 * 
 */
public class VariableTypeGoal extends AbstractTypeGoal {

	private final String name;
	private final String parentKey;
	private final VariableKind kind;

	public VariableTypeGoal(IContext context, String name, String parent,
			VariableKind kind) {
		super(context);
		this.name = name;
		parentKey = parent;
		this.kind = kind;
	}

	public VariableKind getKind() {
		return kind;
	}

	public String getName() {
		return name;
	}

	public String getParentKey() {
		return parentKey;
	}

}
