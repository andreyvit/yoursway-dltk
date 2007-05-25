/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.javascript.typeinference;

import org.eclipse.dltk.internal.javascript.reference.resolvers.ReferenceResolverContext;

public class CallResultReference extends AbstractCallResultReference {

	protected IReference root;

	public CallResultReference(HostCollection collection, String key,
			String id, ReferenceResolverContext cs) {
		super(key, id, cs);
		int pm = id.lastIndexOf('.');
		if (pm != -1) {
			String root = id.substring(0, pm);
			this.root = collection.getReference(root);

		}
	}

	public IReference getRoot() {
		return root;
	}

	public String getResultId() {
		return "!!!returnValue";
	}

	public boolean isFunctionRef() {
		return false;
	}
}
