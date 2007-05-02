/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ti.IContext;
import org.eclipse.dltk.ti.goals.AbstractTypeGoal;

public class ConstantTypeGoal extends AbstractTypeGoal {

	private final int offset;
	private final String name;

	public ConstantTypeGoal(IContext context, int offset, String name) {
		super(context);
		this.offset = offset;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getOffset() {
		return offset;
	}

}
