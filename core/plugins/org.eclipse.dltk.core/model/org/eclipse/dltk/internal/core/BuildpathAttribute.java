/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import org.eclipse.dltk.core.IBuildpathAttribute;
import org.eclipse.dltk.internal.core.util.Util;

public class BuildpathAttribute implements IBuildpathAttribute {
	
	private String name;
	private String value;
	
	public BuildpathAttribute(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public boolean equals(Object obj) {
		if (!(obj instanceof BuildpathAttribute)) return false;
		BuildpathAttribute other = (BuildpathAttribute) obj;
		return this.name.equals(other.name) && this.value.equals(other.value);
	}

    public String getName() {
		return this.name;
    }

    public String getValue() {
		return this.value;
    }
    
    public int hashCode() {
     	return Util.combineHashCodes(this.name.hashCode(), this.value.hashCode());
    }
    
    public String toString() {
    	return this.name + "=" + this.value; //$NON-NLS-1$
    }

}
