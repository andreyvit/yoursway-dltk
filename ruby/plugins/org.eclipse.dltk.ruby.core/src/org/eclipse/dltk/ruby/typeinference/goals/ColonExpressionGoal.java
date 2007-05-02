/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.typeinference.goals;

import org.eclipse.dltk.ruby.ast.RubyColonExpression;
import org.eclipse.dltk.ti.BasicContext;
import org.eclipse.dltk.ti.goals.AbstractTypeGoal;

public class ColonExpressionGoal extends AbstractTypeGoal {

	private final RubyColonExpression expr;

	public ColonExpressionGoal(BasicContext context, RubyColonExpression expr) {
		super(context);
		this.expr = expr;
	}

	public RubyColonExpression getColonExpression() {
		return expr;
	}
	
}
