/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.compiler;


public interface ISourceElementRequestor {
	
	public abstract static class ElementInfo {
		public int declarationStart;
		public int modifiers;
		public String name;
		public int nameSourceStart;
		public int nameSourceEnd;		
	}

	public static class TypeInfo extends ElementInfo {
		public String[] superclasses;
	}
	
	public static class MethodInfo extends ElementInfo {
		public String[] parameterNames;
		public String[] parameterInitializers;
		public String[] exceptionTypes;
	}
	
	public static class FieldInfo extends ElementInfo {		
	}
	
	void enterModule();
	
	void enterModuleRoot();
	
	void exitModuleRoot();
	
	void enterField(FieldInfo info);
	
	/**
	 * Add selected field only if it not pressent yet.
	 * If field are added into method, it also checks method arguments.
	 * @param info
	 * @return TODO
	 */
	boolean enterFieldCheckDuplicates(FieldInfo info);
	
	void enterMethod(MethodInfo info);
	
	void acceptPackage(int declarationStart, int declarationEnd, char[] name);
	
	/**
	 * equivalent to enterMethod except for removing previous declared methods with same name. 
	 * @param info
	 */
	void enterMethodRemoveSame(MethodInfo info);
		
	
	/**
	 * Enter method for selected parent in currect module.
	 * @param info
	 * @param parentName parent name delimited with $ symbol.
	 * @return boolean - return false if parent with selected name, coul'd not be found.
	 */
	boolean enterMethodWithParentType(MethodInfo info, String parentName, String delimiter);
	
	/**
	 * Enter field for selected parent in currect module.
	 * @param info
	 * @param parentName parent name delimited with $ symbol.
	 * @return boolean - return false if parent with selected name, coul'd not be found.
	 */
	boolean enterFieldWithParentType(FieldInfo info, String parentName, String delimiter);
	
	void enterType(TypeInfo info);
	
	/**
	 * If type with same name already exist, then enter it instead.
	 * @param info
	 * @return boolean false if no such type found.
	 */
	boolean enterTypeAppend(String fullName, String delimiter);
	
	void exitModule(int declarationEnd);
	
	void exitField(int declarationEnd);
	
	void exitMethod(int declarationEnd);
	
	void exitType(int declarationEnd);
		
	void acceptMethodReference(char[] methodName, int argCount, int sourcePosition, int sourceEndPosition);
	
	void acceptTypeReference(char[][] typeName, int sourceStart, int sourceEnd);

	void acceptTypeReference(char[] typeName, int sourcePosition);
	
	void acceptFieldReference(char[] fieldName, int sourcePosition);
}
