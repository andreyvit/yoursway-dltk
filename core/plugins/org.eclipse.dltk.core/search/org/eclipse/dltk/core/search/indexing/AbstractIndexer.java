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
package org.eclipse.dltk.core.search.indexing;

import org.eclipse.dltk.compiler.CharOperation;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.Signature;
import org.eclipse.dltk.core.search.SearchDocument;
import org.eclipse.dltk.internal.core.search.matching.FieldPattern;
import org.eclipse.dltk.internal.core.search.matching.MethodPattern;
import org.eclipse.dltk.internal.core.search.matching.SuperTypeReferencePattern;
import org.eclipse.dltk.internal.core.search.matching.TypeDeclarationPattern;

public abstract class AbstractIndexer implements IIndexConstants {

	protected SearchDocument document;

	public AbstractIndexer(SearchDocument document) {
		this.document = document;
	}

	public void addTypeDeclaration(int modifiers, char[] packageName,
			String name, char[][] enclosingTypeNames, String[] superclasss) {
		char[] indexKey = TypeDeclarationPattern.createIndexKey(modifiers, name
				.toCharArray(), packageName, enclosingTypeNames);
		addIndexEntry(TYPE_DECL, indexKey);
		//
		if (superclasss != null) {
			// for (int i = 0; i < superclasss.length; ++i) {
			// char[] superclass = erasure(superclasss[i].toCharArray());
			// addTypeReference(superclass);
			// }
			for (int i = 0, max = superclasss.length; i < max; i++) {
				char[] superClass = erasure(superclasss[i].toCharArray());
				addTypeReference(superClass);
				addIndexEntry(SUPER_REF, SuperTypeReferencePattern
						.createIndexKey(modifiers, packageName, name
								.toCharArray(), enclosingTypeNames, null,
								TYPE_SUFFIX, superClass, TYPE_SUFFIX));

			}
		}
	}

	private char[] erasure(char[] typeName) {
		int genericStart = CharOperation.indexOf(Signature.C_GENERIC_START,
				typeName);
		if (genericStart > -1)
			typeName = CharOperation.subarray(typeName, 0, genericStart);
		return typeName;
	}

	public void addConstructorDeclaration(String typeName,
			String[] parameterTypes, String[] exceptionTypes) {
		// int argCount = parameterTypes == null ? 0 : parameterTypes.length;
		// addIndexEntry(CONSTRUCTOR_DECL,
		// ConstructorPattern.createIndexKey(CharOperation.lastSegment(typeName,'.'),
		// argCount));
		//	
		// if (parameterTypes != null) {
		// for (int i = 0; i < argCount; i++)
		// addTypeReference(parameterTypes[i]);
		// }
		// if (exceptionTypes != null)
		// for (int i = 0, max = exceptionTypes.length; i < max; i++)
		// addTypeReference(exceptionTypes[i]);
	}

	public void addConstructorReference(char[] typeName, int argCount) {
		// char[] simpleTypeName = CharOperation.lastSegment(typeName,'.');
		// addTypeReference(simpleTypeName);
		// addIndexEntry(CONSTRUCTOR_REF,
		// ConstructorPattern.createIndexKey(simpleTypeName, argCount));
		// char[] innermostTypeName =
		// CharOperation.lastSegment(simpleTypeName,'$');
		// if (innermostTypeName != simpleTypeName)
		// addIndexEntry(CONSTRUCTOR_REF,
		// ConstructorPattern.createIndexKey(innermostTypeName, argCount));
	}

	public void addFieldDeclaration(char[] typeName, char[] fieldName) {
		addIndexEntry(FIELD_DECL, FieldPattern.createIndexKey(fieldName));
		addTypeReference(typeName);
	}

	public void addFieldDeclaration(char[] fieldName) {
		addIndexEntry(FIELD_DECL, FieldPattern.createIndexKey(fieldName));
	}

	public void addFieldReference(char[] fieldName) {
		addNameReference(fieldName);
	}

	protected void addIndexEntry(char[] category, char[] key) {
		this.document.addIndexEntry(category, key);
	}

	public void addMethodDeclaration(String methodName,
			String[] parameterNames, String[] exceptionTypes) {
		int argCount = parameterNames == null ? 0 : parameterNames.length;
		addIndexEntry(METHOD_DECL, MethodPattern.createIndexKey(methodName
				.toCharArray(), argCount));

		// if (parameterNames != null) {
		// for (int i = 0; i < argCount; i++)
		// addNameReference((parameterNames[i]).toCharArray());
		// }
		// if (exceptionTypes != null)
		// for (int i = 0, max = exceptionTypes.length; i < max; i++)
		// addTypeReference(exceptionTypes[i]);
		// if (returnType != null)
		// addTypeReference(returnType);
	}

	public void addMethodReference(char[] methodName, int argCount) {
		addIndexEntry(METHOD_REF, MethodPattern.createIndexKey(methodName,
				argCount));
	}

	public void addNameReference(char[] name) {
		addIndexEntry(REF, name);
	}

	public void addTypeReference(char[] typeName) {
		addNameReference(CharOperation.lastSegment(typeName,
				IScriptFolder.PACKAGE_DELIMITER));
	}

	public abstract void indexDocument();
	
	public void addMixin(char[] name) {
		addIndexEntry(MIXIN, name);
	}
}
