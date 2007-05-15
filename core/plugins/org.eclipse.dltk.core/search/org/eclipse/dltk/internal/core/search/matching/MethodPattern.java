/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core.search.matching;

import java.io.IOException;

import org.eclipse.dltk.compiler.CharOperation;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.index.EntryResult;
import org.eclipse.dltk.core.search.index.Index;
import org.eclipse.dltk.core.search.indexing.IIndexConstants;


public class MethodPattern extends DLTKSearchPattern implements IIndexConstants {
	protected boolean findDeclarations;
	protected boolean findReferences;
	public char[] selector;
	public char[] declaringQualification;
	public char[] declaringSimpleName;
	public char[][] parameterQualifications;
	public char[][] parameterSimpleNames;
	public int parameterCount;
	// extra reference info
	protected IType declaringType;
	// Signatures and arguments for generic search	
	boolean methodParameters = false;
	char[][] methodArguments;
	protected static char[][] REF_CATEGORIES = {
		METHOD_REF
	};
	protected static char[][] REF_AND_DECL_CATEGORIES = {
			METHOD_REF, METHOD_DECL
	};
	protected static char[][] DECL_CATEGORIES = {
		METHOD_DECL
	};

	/**
	 * Method entries are encoded as selector '/' Arity: e.g. 'foo/0'
	 */
	public static char[] createIndexKey(char[] selector, int argCount) {
		char[] countChars = argCount < 10 ? COUNTS[argCount] : (SEPARATOR + String.valueOf(argCount)).toCharArray(); //$NON-NLS-1$
		return CharOperation.concat(selector, countChars);
	}

	MethodPattern(int matchRule) {
		super(METHOD_PATTERN, matchRule);
	}

	public MethodPattern(boolean findDeclarations, boolean findReferences, char[] selector, char[] declaringQualification,
			char[] declaringSimpleName, IType declaringType, int matchRule) {
		this(matchRule);
		this.findDeclarations = findDeclarations;
		this.findReferences = findReferences;
		this.selector = (isCaseSensitive() || isCamelCase()) ? selector : CharOperation.toLowerCase(selector);
		this.declaringQualification = isCaseSensitive() ? declaringQualification : CharOperation.toLowerCase(declaringQualification);
		this.declaringSimpleName = isCaseSensitive() ? declaringSimpleName : CharOperation.toLowerCase(declaringSimpleName);		
		if (parameterSimpleNames != null) {
			this.parameterCount = parameterSimpleNames.length;
			this.parameterQualifications = new char[this.parameterCount][];
			this.parameterSimpleNames = new char[this.parameterCount][];
			for (int i = 0; i < this.parameterCount; i++) {
				this.parameterQualifications[i] = isCaseSensitive() ? parameterQualifications[i]
						: CharOperation.toLowerCase(parameterQualifications[i]);
				this.parameterSimpleNames[i] = isCaseSensitive() ? parameterSimpleNames[i]
						: CharOperation.toLowerCase(parameterSimpleNames[i]);
			}
		} else {
			this.parameterCount = -1;
		}
		this.declaringType = declaringType;
	}

	/*
	 * Instanciate a method pattern with signatures for generics search
	 */
	public MethodPattern(boolean findDeclarations, boolean findReferences, char[] selector, char[] declaringQualification,
			char[] declaringSimpleName, IMethod method, int matchRule) {
		this(findDeclarations, findReferences, selector, declaringQualification, declaringSimpleName, method==null?null:method.getDeclaringType(), matchRule);
		// Get unique key for parameterized constructors
//		String genericDeclaringTypeSignature = null;
		// String genericSignature = null;
		// String key;
		methodParameters = true;
		// Store type signature and arguments for declaring type
//		if (genericDeclaringTypeSignature != null) {
//			this.typeSignatures = Util.splitTypeLevelsSignature(genericDeclaringTypeSignature);
//			// setTypeArguments(Util.getAllTypeArguments(this.typeSignatures));
//		} else {
//			storeTypeSignaturesAndArguments(declaringType);
//		}
		
		// Store type signatures and arguments for method
		methodArguments = extractMethodArguments(method);
	}

	/*
	 * Instanciate a method pattern with signatures for generics search
	 */
	public MethodPattern(boolean findDeclarations, boolean findReferences, char[] selector, char[] declaringQualification,
			char[] declaringSimpleName, String declaringSignature, char[][] arguments, int matchRule) {
		this(findDeclarations, findReferences, selector, declaringQualification, declaringSimpleName, (IMethod)null, matchRule);
		// Store type signature and arguments for declaring type
		
		// Store type signatures and arguments for method
		methodArguments = arguments;
	}

	public void decodeIndexKey(char[] key) {
		int last = key.length - 1;
		this.parameterCount = 0;
		this.selector = null;
		int power = 1;
		for (int i = last; i >= 0; i--) {
			if (key[i] == SEPARATOR) {
				System.arraycopy(key, 0, this.selector = new char[i], 0, i);
				break;
			}
			if (i == last) {
				this.parameterCount = key[i] - '0';
			} else {
				power *= 10;
				this.parameterCount += power * (key[i] - '0');
			}
		}
	}

	public SearchPattern getBlankPattern() {
		return new MethodPattern(R_EXACT_MATCH | R_CASE_SENSITIVE);
	}

	public char[][] getIndexCategories() {
		if (this.findReferences)
			return this.findDeclarations ? REF_AND_DECL_CATEGORIES : REF_CATEGORIES;
		if (this.findDeclarations)
			return DECL_CATEGORIES;
		return CharOperation.NO_CHAR_CHAR;
	}

	boolean hasMethodArguments() {
		return methodArguments != null && methodArguments.length > 0;
	}

	boolean hasMethodParameters() {
		return methodParameters;
	}

	public boolean isPolymorphicSearch() {
		return this.findReferences;
	}

	public boolean matchesDecodedKey(SearchPattern decodedPattern) {
		MethodPattern pattern = (MethodPattern) decodedPattern;
		return (this.parameterCount == pattern.parameterCount || this.parameterCount == -1) && matchesName(this.selector, pattern.selector);
	}


	public EntryResult[] queryIn(Index index) throws IOException {
		char[] key = this.selector; // can be null
		int matchRule = getMatchRule();
		switch (getMatchMode()) {
			case R_EXACT_MATCH:
				if (this.isCamelCase)
					break;
				if (this.selector != null && this.parameterCount >= 0)
					key = createIndexKey(this.selector, this.parameterCount);
				else { // do a prefix query with the selector
					matchRule &= ~R_EXACT_MATCH;
					matchRule |= R_PREFIX_MATCH;
				}
				break;
			case R_PREFIX_MATCH:
				// do a prefix query with the selector
				break;
			case R_PATTERN_MATCH:
				if (this.parameterCount >= 0)
					key = createIndexKey(this.selector == null ? ONE_STAR : this.selector, this.parameterCount);
				else if (this.selector != null && this.selector[this.selector.length - 1] != '*')
					key = CharOperation.concat(this.selector, ONE_STAR, SEPARATOR);
				// else do a pattern query with just the selector
				break;
			case R_REGEXP_MATCH:
				// TODO (frederic) implement regular expression match
				break;
		}
		return index.query(getIndexCategories(), key, matchRule); // match
																	// rule is
																	// irrelevant
																	// when the
																	// key is
																	// null
	}

	protected StringBuffer print(StringBuffer output) {
		if (this.findDeclarations) {
			output.append(this.findReferences ? "MethodCombinedPattern: " //$NON-NLS-1$
					: "MethodDeclarationPattern: "); //$NON-NLS-1$
		} else {
			output.append("MethodReferencePattern: "); //$NON-NLS-1$
		}
		if (declaringQualification != null)
			output.append(declaringQualification).append('.');
		if (declaringSimpleName != null)
			output.append(declaringSimpleName).append('.');
		else if (declaringQualification != null)
			output.append("*."); //$NON-NLS-1$
		if (selector != null)
			output.append(selector);
		else
			output.append("*"); //$NON-NLS-1$
		output.append('(');
		if (parameterSimpleNames == null) {
			output.append("..."); //$NON-NLS-1$
		} else {
			for (int i = 0, max = parameterSimpleNames.length; i < max; i++) {
				if (i > 0)
					output.append(", "); //$NON-NLS-1$
				if (parameterQualifications[i] != null)
					output.append(parameterQualifications[i]).append('.');
				if (parameterSimpleNames[i] == null)
					output.append('*');
				else
					output.append(parameterSimpleNames[i]);
			}
		}
		output.append(')');		
		return super.print(output);
	}
}
