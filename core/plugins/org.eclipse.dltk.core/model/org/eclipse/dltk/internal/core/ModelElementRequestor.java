/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import java.util.ArrayList;

import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IType;


/**
 * @see IModelElementRequestor
 */

public class ModelElementRequestor implements IModelElementRequestor {
	/**
	 * True if this requestor no longer wants to receive results from its
	 * <code>IRequestorNameLookup</code>.
	 */
	protected boolean fCanceled = false;

	/**
	 * A collection of the resulting fields, or <code>null</code> if no field
	 * results have been received.
	 */
	protected ArrayList fFields = null;

	/**
	 * A collection of the resulting initializers, or <code>null</code> if no
	 * initializer results have been received.
	 */
	protected ArrayList fInitializers = null;

	/**
	 * A collection of the resulting member types, or <code>null</code> if no
	 * member type results have been received.
	 */
	protected ArrayList fMemberTypes = null;

	/**
	 * A collection of the resulting methods, or <code>null</code> if no
	 * method results have been received.
	 */
	protected ArrayList fMethods = null;

	/**
	 * A collection of the resulting package fragments, or <code>null</code>
	 * if no package fragment results have been received.
	 */
	protected ArrayList fScriptFolders = null;

	/**
	 * A collection of the resulting types, or <code>null</code> if no type
	 * results have been received.
	 */
	protected ArrayList fTypes = null;

	/**
	 * Empty arrays used for efficiency
	 */
	protected static IField[] fgEmptyFieldArray = new IField[0];

	protected static IType[] fgEmptyTypeArray = new IType[0];

	protected static IScriptFolder[] fgEmptyScriptFolderArray = new IScriptFolder[0];

	protected static IMethod[] fgEmptyMethodArray = new IMethod[0];

	/**
	 * @see IModelElementRequestor
	 */
	public void acceptField(IField field) {
		if (fFields == null) {
			fFields = new ArrayList();
		}
		fFields.add(field);
	}

	/**
	 * @see IModelElementRequestor
	 */
	public void acceptMemberType(IType type) {
		if (fMemberTypes == null) {
			fMemberTypes = new ArrayList();
		}
		fMemberTypes.add(type);
	}

	/**
	 * @see IModelElementRequestor
	 */
	public void acceptMethod(IMethod method) {
		if (fMethods == null) {
			fMethods = new ArrayList();
		}
		fMethods.add(method);
	}

	/**
	 * @see IModelElementRequestor
	 */
	public void acceptScriptFolder(IScriptFolder ScriptFolder) {
		if (fScriptFolders == null) {
			fScriptFolders = new ArrayList();
		}
		fScriptFolders.add(ScriptFolder);
	}

	/**
	 * @see IModelElementRequestor
	 */
	public void acceptType(IType type) {
		if (fTypes == null) {
			fTypes = new ArrayList();
		}
		fTypes.add(type);
	}

	/**
	 * @see IModelElementRequestor
	 */
	public IField[] getFields() {
		if (fFields == null) {
			return fgEmptyFieldArray;
		}
		int size = fFields.size();
		IField[] results = new IField[size];
		fFields.toArray(results);
		return results;
	}


	/**
	 * @see IModelElementRequestor
	 */
	public IType[] getMemberTypes() {
		if (fMemberTypes == null) {
			return fgEmptyTypeArray;
		}
		int size = fMemberTypes.size();
		IType[] results = new IType[size];
		fMemberTypes.toArray(results);
		return results;
	}

	/**
	 * @see IModelElementRequestor
	 */
	public IMethod[] getMethods() {
		if (fMethods == null) {
			return fgEmptyMethodArray;
		}
		int size = fMethods.size();
		IMethod[] results = new IMethod[size];
		fMethods.toArray(results);
		return results;
	}

	/**
	 * @see IModelElementRequestor
	 */
	public IScriptFolder[] getScriptFolders() {
		if (fScriptFolders == null) {
			return fgEmptyScriptFolderArray;
		}
		int size = fScriptFolders.size();
		IScriptFolder[] results = new IScriptFolder[size];
		fScriptFolders.toArray(results);
		return results;
	}

	/**
	 * @see IModelElementRequestor
	 */
	public IType[] getTypes() {
		if (fTypes == null) {
			return fgEmptyTypeArray;
		}
		int size = fTypes.size();
		IType[] results = new IType[size];
		fTypes.toArray(results);
		return results;
	}

	/**
	 * @see IModelElementRequestor
	 */
	public boolean isCanceled() {
		return fCanceled;
	}

	/**
	 * Reset the state of this requestor.
	 */
	public void reset() {
		fCanceled = false;
		fFields = null;
		fInitializers = null;
		fMemberTypes = null;
		fMethods = null;
		fScriptFolders = null;
		fTypes = null;
	}

	/**
	 * Sets the #isCanceled state of this requestor to true or false.
	 */
	public void setCanceled(boolean b) {
		fCanceled = b;
	}
}
