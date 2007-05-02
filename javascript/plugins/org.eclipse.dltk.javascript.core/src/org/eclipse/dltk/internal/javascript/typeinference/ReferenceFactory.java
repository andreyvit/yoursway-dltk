/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.javascript.typeinference;




public class ReferenceFactory {

	public static IReference createNumberReference( String name,double number){
		return new UncknownReference(name,false);		
	}

	public static IReference createStringReference(String name,String string) {
		return new UncknownReference(name,false);
	}

	public static IReference createBooleanReference(String name,boolean b) {
		return new UncknownReference(name,false);
	}
}
