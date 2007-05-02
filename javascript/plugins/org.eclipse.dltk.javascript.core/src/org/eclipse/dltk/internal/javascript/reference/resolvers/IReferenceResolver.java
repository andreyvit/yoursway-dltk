/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.javascript.reference.resolvers;

import java.util.Set;

import org.eclipse.dltk.core.ISourceModule;

public interface IReferenceResolver {

	boolean canResolve(ISourceModule module);

	public Set getChilds(IResolvableReference ref);

	public Set resolveGlobals(String id);

	void processCall(String call, String objId);
	
	void init(ReferenceResolverContext owner);
	
	
}
