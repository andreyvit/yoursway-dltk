/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.ecipse.dltk.javascript.internal.representation;

import java.util.HashMap;

import org.eclipse.dltk.internal.javascript.typeinference.IReference;

public class ScriptEnvironment implements IEnvironment {

	private HashMap map = new HashMap();

	static IEnvironment env;

	private static void init() {
	}
	public static synchronized IEnvironment getInstance() {
		if (env == null)
			init();
		return env;
	}

	public IReference query(String name) {
		return null;
	}
}
