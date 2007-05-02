/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.dltk.internal.javascript.typeinference;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.dltk.internal.core.ModelElement;

public final class ContextReference implements IReference {
	
	private final HostCollection function;
	private final String key;

	ContextReference(HostCollection function, String key) {
		this.function = function;
		this.key = key;
	}

	public IReference getChild(String key, boolean resolveLocals) {
		return function.getReference(key);
	}

	public Set getChilds(boolean resolveLocals) {
		if (!resolveLocals)return new HashSet();
		Map references = function.getReferences();
		Collection values = references.values();
		HashSet hashSet = new HashSet(values);
		hashSet.remove(this);
		return hashSet;
	}

	public String getName() {
		return key;
	}

	public IReference getPrototype(boolean resolveLocals) {
		return getChild("prototype", resolveLocals);
	}

	public boolean isChildishReference() {
		return false;
	}

	public void recordDelete(String fieldId) {
		
	}

	public void setChild(String key, IReference ref) {
		
	}

	public void setPrototype(IReference ref) {
		
	}

	public void addModelElements(Collection toAdd) {
		
	}
	
	public void setLocationInformation(ModelElement mo, int position, int length) {
		
	}
}