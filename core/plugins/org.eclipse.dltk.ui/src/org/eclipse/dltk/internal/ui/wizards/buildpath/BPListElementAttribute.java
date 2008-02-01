/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.wizards.buildpath;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathAttribute;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.internal.ui.wizards.BuildpathAttributeConfiguration.BuildpathAttributeAccess;



/**
  */
public class BPListElementAttribute {

	private BPListElement fParent;
	private String fKey;
	private Object fValue;
	
	private IStatus fStatus;
	private final boolean fBuiltIn;
	
	private BuildpathAttributeAccess fCachedAccess;
	
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
    public IBuildpathAttribute getBuildpathAttribute() {
		Assert.isTrue(!fBuiltIn);
		return DLTKCore.newBuildpathAttribute(fKey, (String) fValue);
	}
    public BuildpathAttributeAccess getBuildpathAttributeAccess() {
    	if (fCachedAccess == null) {
	    	fCachedAccess= new BuildpathAttributeAccess() {
	    		public IBuildpathAttribute getBuildpathAttribute() {
	 				return BPListElementAttribute.this.getBuildpathAttribute();
				}
				public IScriptProject getScriptProject() {
					return getParent().getScriptProject();
				}
				public IBuildpathEntry getParentBuildpathEntry() {
					return getParent().getBuildpathEntry();
				}
	    	};
    	}
    	return fCachedAccess;
    }
}
