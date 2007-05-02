/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.typeinference.goals;

import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.ti.IContext;
import org.eclipse.dltk.ti.goals.AbstractTypeGoal;

/**
 * Evaluates type of a given constant element, that are not a Class,
 * for ex.:
 * <code>A = 4</code>
 */
public class NonTypeConstantTypeGoal extends AbstractTypeGoal {

	private final IMixinElement element;

	public NonTypeConstantTypeGoal(IContext context, IMixinElement element) {
		super(context);
		this.element = element;
	}

	public IMixinElement getElement() {
		return element;
	}

}
