/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal;

import java.util.List;

import org.eclipse.dltk.dbgp.IDbgpProperty;

public class DbgpProperty implements IDbgpProperty {

	private String name;

	private String fullName;

	private String type;

	private String value;

	private int size;

	private int childrenCount;

	private List availableChildren;

	private boolean constant;

	private boolean hasChildren;

	private String key;

	public DbgpProperty(String name, String fullName, String type,
			String value, int size, int childrenCount, boolean hasChildren,
			List availableChildren, boolean constant, String key) {
		this.name = name;
		this.fullName = fullName;
		this.type = type;
		this.value = value;
		this.size = size;
		this.childrenCount = childrenCount;
		this.availableChildren = availableChildren;
		this.hasChildren = hasChildren;
		this.constant = constant;
		this.key = key;
	}

	public String getFullName() {
		return fullName;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getSize() {
		return size;
	}

	public boolean hasChildren() {
		return hasChildren;
	}

	public int getChildrenCount() {
		return childrenCount;
	}

	public IDbgpProperty[] getAvailableChildren() {
		return (IDbgpProperty[]) availableChildren
				.toArray(new IDbgpProperty[availableChildren.size()]);
	}

	public boolean isConstant() {
		return constant;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("DbgpProperty (Name: " + name
				+ "; Full name: " + fullName + "; Type: " + type + "; Value: "
				+ value + ")");
		// Iterator iter = availableChildren.iterator();
		//		
		// while(iter.hasNext()){
		// sb.append('\n');
		// sb.append('\t');
		// sb.append(iter.next().toString());
		// }

		return sb.toString();
	}

	public String getKey() {
		return key;
	}
}
