/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp;


public interface IDbgpProperty {

	/*
	 * Variable name. This is the short part of the name. For instance, in PHP:
	 * $v = 0; // short name 'v' class:$v; // short name 'v'
	 */
	String getName();

	/*
	 * Variable name. This is the long form of the name which can be eval'd by
	 * the language to retrieve the value of the variable. $v = 0; // long name
	 * 'v' class::$v; // short name 'v', long 'class::$v' $this->v; // short
	 * name 'v', long '$this->v'
	 */
	String getFullName();

	/*
	 * Language specific data type name
	 */
	String getType();

	/*
	 * Size of property data in bytes
	 */
	int getSize();

	String getValue();

	void setValue(String value);

	boolean isConstant();

	boolean hasChildren();

	int getChildrenCount();

	IDbgpProperty[] getAvailableChildren();
	
	String getKey();
}
