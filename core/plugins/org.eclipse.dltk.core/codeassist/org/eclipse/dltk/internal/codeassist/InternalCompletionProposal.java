/*******************************************************************************
 * Copyright (c) 2004, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.codeassist;

import org.eclipse.dltk.codeassist.ICompletionEngine;
import org.eclipse.dltk.compiler.CharOperation;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IAccessRule;
import org.eclipse.dltk.internal.core.NameLookup;

/**
 * Internal completion proposal
 */
public class InternalCompletionProposal {
	
	static final char[] ARG = "arg".toCharArray();  //$NON-NLS-1$
	static final char[] ARG0 = "arg0".toCharArray();  //$NON-NLS-1$
	static final char[] ARG1 = "arg1".toCharArray();  //$NON-NLS-1$
	static final char[] ARG2 = "arg2".toCharArray();  //$NON-NLS-1$
	static final char[] ARG3 = "arg3".toCharArray();  //$NON-NLS-1$
	static final char[][] ARGS1 = new char[][]{ARG0};
	static final char[][] ARGS2 = new char[][]{ARG0, ARG1};
	static final char[][] ARGS3 = new char[][]{ARG0, ARG1, ARG2};
	static final char[][] ARGS4 = new char[][]{ARG0, ARG1, ARG2, ARG3};
	
	public ICompletionEngine completionEngine;
	public NameLookup nameLookup;
	
	protected char[] declarationPackageName;
	protected char[] declarationTypeName;
	protected char[] packageName;
	protected char[] typeName;
	protected char[][] parameterPackageNames;
	protected char[][] parameterTypeNames;
	
	protected char[] originalSignature;
	
	protected int accessibility = IAccessRule.K_ACCESSIBLE;
	
	protected boolean isConstructor = false;
	
	protected char[][] createDefaultParameterNames(int length) {
		char[][] parameterNames;
		switch (length) {
			case 0 :
				parameterNames = new char[length][];
				break;
			case 1 :
				parameterNames = ARGS1;
				break;
			case 2 :
				parameterNames = ARGS2;
				break;
			case 3 :
				parameterNames = ARGS3;
				break;
			case 4 :
				parameterNames = ARGS4;
				break;
			default :
				parameterNames = new char[length][];
				for (int i = 0; i < length; i++) {
					parameterNames[i] = CharOperation.concat(ARG, String.valueOf(i).toCharArray());
				}
				break;
		}
		return parameterNames;
	}
	protected char[][] findMethodParameterNames(char[] declaringTypePackageName, char[] declaringTypeName, char[] selector, char[][] paramTypeNames){
		if (DLTKCore.DEBUG) {
			System.err.println("TODO: Add find method parameter names");
		}
		return null;
	}
	
	protected char[] getDeclarationPackageName() {
		return this.declarationPackageName;
	}
	
	protected char[] getDeclarationTypeName() {
		return this.declarationTypeName;
	}
	
	protected char[] getPackageName() {
		return this.packageName;
	}
	
	protected char[] getTypeName() {
		return this.typeName;
	}
	
	protected char[][] getParameterPackageNames() {
		return this.parameterPackageNames;
	}
	
	
	protected char[][] getParameterTypeNames() {
		return this.parameterTypeNames;
	}
	
	protected void setDeclarationPackageName(char[] declarationPackageName) {
		this.declarationPackageName = declarationPackageName;
	}
	
	protected void setDeclarationTypeName(char[] declarationTypeName) {
		this.declarationTypeName = declarationTypeName;
	}
	
	protected void setPackageName(char[] packageName) {
		this.packageName = packageName;
	}
	
	protected void setTypeName(char[] typeName) {
		this.typeName = typeName;
	}
	
	protected void setParameterPackageNames(char[][] parameterPackageNames) {
		this.parameterPackageNames = parameterPackageNames;
	}
	
	protected void setParameterTypeNames(char[][] parameterTypeNames) {
		this.parameterTypeNames = parameterTypeNames;
	}
	
	protected void setAccessibility(int kind) {
		this.accessibility = kind;
	}
	
	protected void setIsContructor(boolean isConstructor) {
		this.isConstructor = isConstructor;
	}
	public void setOriginalSignature(char[] originalSignature) {
		this.originalSignature = originalSignature;
	}
}
