/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core.search;

import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.search.IDLTKSearchScope;

public abstract class AbstractSearchScope implements IDLTKSearchScope {

	/*
	 * (non-Javadoc) Process the given delta and refresh its internal state if
	 * needed. Returns whether the internal state was refreshed.
	 */
	public abstract void processDelta(IModelElementDelta delta);

}
