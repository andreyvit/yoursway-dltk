/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.javascript.ui.internal.common;



import java.util.List;
import java.util.Vector;

public class JSContentElementImpl implements ContentElement {

	private String fName = ""; //$NON-NLS-1$
	private int fNameOffset = 0;
	private int fOffset = 0;
	private int fLength = 0;
	private int fType = 0;
	private ContentElement fParent = null;
	private int fJavaDocOffset = -500;
	private String fJavaDocString = null;
	private Vector fChildren = null;
	private static boolean fbSupportChildren = true;
	private static boolean fbSupportVariables = true;

	public static void setSupportChildren(boolean sc) {
		fbSupportChildren = sc;
	}

	public static void setSupportVariables(boolean sv) {
		fbSupportVariables = sv;
	}

	/**
	 * @see ContentElement#hasChildren(Object)
	 */
	public boolean hasChildren(Object element) {
		if (!fbSupportChildren)
			return false;
		if (fType == JSContentElementConstants.JS_FUNCTION) {
			List children = fChildren;
			if ((children != null) && (children.size() > 0)) {
				if (fbSupportVariables)
					return true;
				for(int i = 0; i < children.size(); i++) {
					JSContentElementImpl el = (JSContentElementImpl) children.get(i);
					if (el.getType() == JSContentElementConstants.JS_FUNCTION) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * @see ContentElement#hasChildren(Object)
	 */
	public boolean hasChildren2() {
		if (fType == JSContentElementConstants.JS_FUNCTION) {
			List children = getChildren2();
			if ((children != null) && (children.size() > 0))
				return true;
		}
		return false;
	}

	/**
	 * @see ContentElement#getChildren()
	 */
	public Vector getChildren() {
		if (!fbSupportChildren || fChildren == null)
			return null;
		Vector retval = new Vector();
		for(int i = 0; i < fChildren.size(); i++){
			JSContentElementImpl el = (JSContentElementImpl) fChildren.get(i);
			if (el.getType() == JSContentElementConstants.JS_FUNCTION || fbSupportVariables)
				retval.add(el);
		}
		return retval;
	}

	/**
	 * @see ContentElement#getChildren2()
	 */
	public Vector getChildren2() {
		return fChildren;
	}

	/**
	 * @see ContentElement#getName()
	 */
	public String getName() {
		return fName;
	}

	/**
	 * 
	 */
	public int getJavaDocOffset() {
		return fJavaDocOffset;
	}

	/**
	 * 
	 */
	public String getJavaDocString() {
		return fJavaDocString;
	}

	/**
	 * @see ContentElement#getNameOffset()
	 */
	public int getNameOffset() {
		return fNameOffset;
	}

	/**
	 * @see ContentElement#getOffset()
	 */
	public int getOffset() {
		return fOffset;
	}

	/**
	 * @see ContentElement#getLength()
	 */
	public int getLength() {
		return fLength;
	}

	/**
	 * @see ContentElement#getType()
	 */
	public int getType() {
		return fType;
	}

	/**
	 * @see ContentElement#setName(String name)
	 */
	public void setName(String name) {
		fName = name;
	}

	/**
	 * 
	 */
	public void setJavaDocOffset(int off) {
		fJavaDocOffset = off;
	}

	/**
	 * 
	 */
	public void setJavaDocString(String str) {
		fJavaDocString = str;
	}

	/**
	 * @see ContentElement#setNameOffset(int)
	 */
	public void setNameOffset(int nameOffset) {
		fNameOffset = nameOffset;
	}

	/**
	 * @see ContentElement#setOffset(int)
	 */
	public void setOffset(int offset) {
		fOffset = offset;
	}

	/**
	 * @see ContentElement#setLength(int)
	 */
	public void setLength(int length) {
		fLength = length;
	}

	/**
	 * @see ContentElement#setType(int)
	 */
	public void setType(int type) {
		fType = type;
	}

	public void addChild(ContentElement child) {
		if (fChildren == null)
			fChildren = new Vector();

		fChildren.add(child);
	}

	public String toString() {
		return getName();
	}

	public ContentElement getParent() {
		return fParent;
	}

	public void setParent(ContentElement parent) {
		fParent = parent;
	}

	/**
	 * @return Returns the SupportChildren.
	 */
	public static boolean isSupportChildren() {
		return fbSupportChildren;
	}

	/**
	 * @return Returns the fbSupportVariables.
	 */
	public static boolean isSupportVariables() {
		return fbSupportVariables;
	}
}
