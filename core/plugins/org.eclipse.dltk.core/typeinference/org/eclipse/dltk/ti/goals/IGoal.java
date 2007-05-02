/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ti.goals;

import org.eclipse.dltk.ti.IContext;

public interface IGoal {
	
	public final IGoal[] NO_GOALS = new IGoal[0];
	
	/**
	 * Returns context, in which this goal should be considered. Context
	 * contains, for example, the instance of the class a method is called of,
	 * precalculated scope or something like that.
	 * 
	 * @return The context of this goal, or <code>null</code> is there is none.
	 */
	public IContext getContext ();
	
}
