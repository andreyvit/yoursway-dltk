/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.javascript.parser;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;

public class Test {

	public static void main(String[] args) {		
		JavaScriptSourceParser sp=new JavaScriptSourceParser();
		ModuleDeclaration parse = sp.parse(null, "function main(){}".toCharArray(), null);
		System.out.println("Dpone");
	}
}
