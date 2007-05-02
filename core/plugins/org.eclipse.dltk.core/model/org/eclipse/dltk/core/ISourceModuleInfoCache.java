/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core;


public interface ISourceModuleInfoCache {
	interface ISourceModuleInfo {
		Object get(Object key);
		void put(Object key, Object value);
		void remove(Object key);
		boolean isEmpty();
	}
	public ISourceModuleInfo get(ISourceModule module);
	public void remove(ISourceModule sourceModule);
}
