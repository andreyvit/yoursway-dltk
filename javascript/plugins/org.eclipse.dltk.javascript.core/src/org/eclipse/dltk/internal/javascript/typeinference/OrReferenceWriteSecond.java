/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.javascript.typeinference;

public class OrReferenceWriteSecond extends OrReference {

	public void setChild(String key, IReference ref) {
		second.setChild(key, ref);
	}

	public IReference getChild(String key, boolean resolveLocals) {
		IReference child = one.getChild(key, resolveLocals);
		if (child != null)
			return child;
		IReference child2 = second.getChild(key, resolveLocals);
		if (child2 != null)
			return child2;		
		return null;
	}

	public OrReferenceWriteSecond(IReference one, IReference second) {
		super(one, second);
	}

	public void recordDelete(String fieldId) {
		second.recordDelete(fieldId);
	}

	public void setPrototype(IReference ref) {
		second.setPrototype(ref);
	}
}
