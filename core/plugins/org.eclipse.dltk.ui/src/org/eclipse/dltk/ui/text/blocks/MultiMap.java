/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.text.blocks;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public interface MultiMap {
	
	public void put(Object key, Object value);
	
	public void putAll(Object key, Collection values);
	
	public Set get(Object key);
	
	public Iterator iterator();
	
	public Set keySet();
	
}
