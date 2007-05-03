/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.core;

import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.core.search.indexing.SourceIndexerRequestor;

public class TclSourceIndexerRequestor extends SourceIndexerRequestor {
	public void acceptMethodReference(char[] methodName, int argCount,
			int sourcePosition, int sourceEndPosition) {
		// System.out.println("TclSourceIndexerRequestor:Add Method Reference: "
		// + new String(methodName));
		String mName = new String(methodName);
		String[] ns = mName.split("::");
		if( ns.length > 0 ) {
			this.indexer.addMethodReference(ns[ns.length - 1].toCharArray(),
					argCount);
		}
		for (int i = 0; i < ns.length - 1; ++i) {
			if (ns[i].length() > 0) {
				// System.out.println("TCLReferenceIndexing: Added namespace
				// reference:" + ns[i]);
				this.indexer.addTypeReference(ns[i].toCharArray());
			}
		}
	}

	public boolean enterTypeAppend(String fullName, String delimiter) {
		if (fullName.startsWith("::")) {
//			if (DLTKCore.DEBUG) {
//				System.out
//						.println("We need to correct index global namespace append from other namespace..");
//			}
			String name = fullName.substring(2);
			this.indexer.addTypeDeclaration(Modifiers.AccNameSpace,
					this.pkgName, name, enclosingTypeNames(), null);
			this.pushTypeName(name.toCharArray());
		} else {
			String name = fullName;
			int pos = fullName.lastIndexOf("::");
			if (pos != -1) {
				name = fullName.substring(pos + 2);
			}
			// TODO: Need to support entering into complex name.
			this.indexer.addTypeDeclaration(Modifiers.AccNameSpace,
					this.pkgName, name, enclosingTypeNames(), null);
			this.pushTypeName(name.toCharArray());
		}
		return true;
	}

	public boolean enterTypeAppend(TypeInfo info, String fullName,
			String delimiter) {
		return false;
	}

	public void enterMethodRemoveSame(MethodInfo info) {
		enterMethod(info);
	}

	public boolean enterMethodWithParentType(MethodInfo info,
			String parentName, String delimiter) {
		enterMethod(info);
		return false;
	}
}
