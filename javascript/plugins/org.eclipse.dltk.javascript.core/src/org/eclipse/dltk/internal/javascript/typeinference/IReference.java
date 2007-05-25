/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.javascript.typeinference;

import java.util.Collection;
import java.util.Set;

import org.eclipse.dltk.internal.core.ModelElement;

public interface IReference {

	/**
	 * this method returns array of elements that can be identified by child
	 * with specified key if key is not null; or array of possible child
	 * elements of this element (methods and properties if key is null)
	 * 
	 * @param resolveLocals
	 *            TODO
	 * @param key
	 * 
	 * @return
	 */
	public Set getChilds(boolean resolveLocals);

	public IReference getChild(String key, boolean resolveLocals);

	public void setChild(String key, IReference ref);

	/**
	 * returns name of this reference
	 * 
	 * @return
	 */
	public String getName();

	public boolean isChildishReference();

	public void recordDelete(String fieldId);

	public IReference getPrototype(boolean resolveLocals);

	public void setPrototype(IReference ref);

	public void addModelElements(Collection toAdd);

	public void setLocationInformation(ModelElement mo, int position, int length);

	public boolean isFunctionRef();

}
