/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.wizards.buildpath;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathAttribute;



/**
  */
public class BPListElementAttribute {

	private BPListElement fParent;
	private String fKey;
	private Object fValue;
	private final boolean fBuiltIn;
	
	public BPListElementAttribute(BPListElement parent, String key, Object value, boolean builtIn) {
		fKey= key;
		fValue= value;
		fParent= parent;
		fBuiltIn= builtIn;
		if (!builtIn) {
			Assert.isTrue(value instanceof String || value == null);
		}	
	}
	
	public IBuildpathAttribute newBuildpathAttribute() {
		Assert.isTrue(!fBuiltIn);
		if (fValue != null) {
			return DLTKCore.newBuildpathAttribute(fKey, (String) fValue);
		}
		return null;
	}
	
	public BPListElement getParent() {
		return fParent;
	}
	
	/**
	 * @return Returns <code>true</code> if the attribute is a built in attribute.
	 */
	public boolean isBuiltIn() {
		return fBuiltIn;
	}
	
	/**
	 * @return Returns <code>true</code> if the attribute is in a non-modifiable buildpath container
	 */
	public boolean isInNonModifiableContainer() {
		return fParent.isInNonModifiableContainer();
	}

	/**
	 * Returns the key.
	 * @return String
	 */
	public String getKey() {
		return fKey;
	}

	/**
	 * Returns the value.
	 * @return Object
	 */
	public Object getValue() {
		return fValue;
	}
	
	/**
	 * Returns the value.
	 */
	public void setValue(Object value) {
		fValue= value;
	}
	
    public boolean equals(Object obj) {
        if (!(obj instanceof BPListElementAttribute))
            return false;
        BPListElementAttribute attrib= (BPListElementAttribute)obj;
        return attrib.fKey== this.fKey && attrib.getParent().getPath().equals(fParent.getPath());
    }
}
