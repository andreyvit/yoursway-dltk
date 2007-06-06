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
import java.util.HashSet;
import java.util.Set;

import org.eclipse.dltk.internal.core.ModelElement;

public class OrReference implements IReference {

	protected final IReference one;
	protected final IReference second;

	public OrReference(IReference one, IReference second) {
		super();
		this.one = one;
		this.second = second;
	}

	public Set getChilds(boolean resolveLocals) {
		if (entered)
			return new HashSet();
		entered = true;
		Set set = one.getChilds(resolveLocals);
		set.addAll(second.getChilds(resolveLocals));
		entered = false;
		return set;
	}

	public String getName() {
		return one.getName();
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (entered)
			return result;
		entered = true;
		result = prime * result + ((one == null) ? 0 : one.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		entered = false;
		return result;
	}

	public boolean equals(Object obj) {
		if (entered)
			return false;

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final OrReference other = (OrReference) obj;
		if (one == null) {
			if (other.one != null)
				return false;
		} else if (!one.equals(other.one))
			return false;
		if (second == null) {
			if (other.second != null)
				return false;
		} else if (!second.equals(other.second))
			return false;
		return true;
	}

	boolean entered = false;

	public IReference getChild(String key, boolean resolveLocals) {
		if (entered)
			return null;
		entered = true;
		IReference child = one.getChild(key, resolveLocals);
		IReference child2 = second.getChild(key, resolveLocals);
		entered = false;
		if (child == null)
			return child2;
		if (child2 == null)
			return child;
		return new OrReference(child, child2);
	}

	public void setChild(String key, IReference ref) {
		one.setChild(key, ref);
		second.setChild(key, ref);
	}

	public boolean isChildishReference() {
		return one.isChildishReference() || second.isChildishReference();
	}

	public void recordDelete(String fieldId) {

	}

	public IReference getPrototype(boolean resolveLocals) {
		IReference prototype = one.getPrototype(false);
		IReference prototype2 = second.getPrototype(false);
		if (prototype == null)
			return prototype2;
		if (prototype2 == null)
			return prototype;
		return new OrReference(prototype, prototype2);
	}

	public void setPrototype(IReference ref) {
		one.setPrototype(ref);
		second.setPrototype(ref);
	}

	public void addModelElements(Collection toAdd) {
		one.addModelElements(toAdd);
		second.addModelElements(toAdd);
	}

	public void setLocationInformation(ModelElement mo, int position, int length) {
		one.setLocationInformation(mo, position, length);
		second.setLocationInformation(mo, position, length);
	}

	public boolean isFunctionRef() {
		return one.isFunctionRef() || second.isFunctionRef();
	}

	public boolean isLocal() {
		return one.isLocal() || second.isLocal();
	}

	public void setLocal(boolean local) {
		one.setLocal(local);
		second.setLocal(local);
	}

}
