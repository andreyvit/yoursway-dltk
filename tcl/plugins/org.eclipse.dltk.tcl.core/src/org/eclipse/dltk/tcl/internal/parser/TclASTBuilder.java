/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.parser;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.tcl.ast.TclModuleDeclaration;

/**
 * 
 * @author haiodo
 * 
 */
public class TclASTBuilder {
	public static final int TYPE_MODULE = 0;

	public static final int TYPE_NAMESPACE = 1;

	public  static final int TYPE_PROC = 2;

	public static final int TYPE_UNKNOWN = 3;

	private static void replaceChild(ASTNode node, ASTNode statement, ASTNode nsType) {
		if (node instanceof ModuleDeclaration) {
			List statements = ((ModuleDeclaration) node).getStatements();
			int index = statements.indexOf(statement);
			statements.remove(index);
			statements.add(index, nsType);
		} else if (node instanceof TypeDeclaration) {
			List statements = ((TypeDeclaration) node).getStatements();
			int index = statements.indexOf(statement);
			statements.remove(index);
			statements.add(index, nsType);
		}
		else if (node instanceof Block) {
			List statements = ((Block) node).getStatements();
			int index = statements.indexOf(statement);
			statements.remove(index);
			statements.add(index, nsType);
		}
	}

	// Rebuild AST with corect type declarations and methods.
	public static void rebuildMethods(TclModuleDeclaration unit) {
		TypeDeclaration[] types = unit.getTypes();
		if (types != null) {
			for (int i = 0; i < types.length; i++) {
				TypeDeclaration type = types[i];
				rebuildMethodProcessBodies(type, unit);
			}
		}
		
		MethodDeclaration[] methods = unit.getFunctions();
		if (methods != null) {
//			PatternLocator locator = getPatternLocator();
			for (int i = 0; i < methods.length; i++) {
				MethodDeclaration method = methods[i];
				if (method instanceof MethodDeclaration) {
					MethodDeclaration methodDeclaration = method;
					String name = methodDeclaration.getName();
					if( name.indexOf("::") != -1 ) {
						if( name.startsWith("::")) {
							name = name.substring(2);
						}
						if( name.endsWith("::")) {
							name = name.substring(0, name.length() - 2 );
						}
						name = name.replaceAll("(::)+", "::");
						String[] split = name.split("::");
						methodDeclaration.setName(split[split.length - 1]);
						TypeDeclaration type = searchCreateTypeDeclaration(unit, split, method, 0);
						if( type != null ) {
							unit.getStatements().remove(methodDeclaration);
							unit.getFunctionList().remove(methodDeclaration);
							type.getStatements().add(methodDeclaration);
							type.getMethodList().add(methodDeclaration);
						}
					}
				}
			}
		}
	}

	private static TypeDeclaration searchCreateTypeDeclaration(
			TclModuleDeclaration unit, String[] split, MethodDeclaration method, int offset) {
		if( split.length - 1 <= offset ) {
			return null;
		}
		String typeName = split[0 + offset];
		TypeDeclaration[] types = unit.getTypes();
		for (int i = 0; i < types.length; i++) {
			if( types[i].getName().equals(typeName)) {
				return searchCreateTypeDeclaration(types[i], split, method, offset + 1);
			}
		}
		// not found, lets create one new.
		TypeDeclaration decl = new TypeDeclaration(typeName, method.getNameStart(), method.getNameEnd(), method.sourceStart(), method.sourceEnd());
		unit.addStatement(decl);
		unit.getTypeList().add(decl);
		return searchCreateTypeDeclaration(decl, split, method, offset + 1);
	}

	private static TypeDeclaration searchCreateTypeDeclaration(
			TypeDeclaration typeDeclaration, String[] split,
			MethodDeclaration method, int offset ) {
		if(offset == split.length - 1) {
			return typeDeclaration;
		}
		String typeName = split[0 + offset];
		TypeDeclaration[] types = typeDeclaration.getTypes();
		for (int i = 0; i < types.length; i++) {
			if( types[i].getName().equals(typeName)) {
				return searchCreateTypeDeclaration(types[i], split, method, offset + 1);
			}
		}
		// not found, lets create one new.
		TypeDeclaration decl = new TypeDeclaration(typeName, method.getNameStart(), method.getNameEnd(), method.sourceStart(), method.sourceEnd());
		typeDeclaration.getStatements().add(decl);
		typeDeclaration.getTypeList().add(decl);
		return searchCreateTypeDeclaration(decl, split, method, offset + 1);
	}

	// Add functions to modules if then belong to top level elements.
	protected static void rebuildMethodProcessBodies(TypeDeclaration type, TclModuleDeclaration module) {

		MethodDeclaration[] methods = type.getMethods();
		if (methods != null) {
			for (int i = 0; i < methods.length; i++) {
				MethodDeclaration method = methods[i];
				if (method instanceof MethodDeclaration) {
					MethodDeclaration methodDeclaration = method;
					//TODO: Add support of internal to top level modifications.
					String name = methodDeclaration.getName();
					if( name.indexOf("::") != -1 ) {
						boolean start = false;
						if( name.startsWith("::")) {
							name = name.substring(2);
							start = true;
						}
						if( name.endsWith("::")) {
							name = name.substring(0, name.length() - 2 );
						}
						name = name.replaceAll("(::)+", "::");
						String[] split = name.split("::");
						if( start && split.length > 2 ) {
							module.getFunctionList().add(methodDeclaration);
							type.getMethodList().remove(methodDeclaration);
							type.getStatements().remove(methodDeclaration);
						}
						else {
							method.setName(split[split.length - 1]);
							TypeDeclaration decl = searchCreateTypeDeclaration(type, split, method, 0);
							if( decl != null ) {
								type.getMethodList().remove(methodDeclaration);
								type.getStatements().remove(methodDeclaration);
								decl.getStatements().add(method);
								decl.getMethodList().add(method);
							}
						}
					}
				}
			}
		}

		TypeDeclaration[] memberTypes = type.getTypes();
		if (memberTypes != null) {
			for (int i = 0; i < memberTypes.length; i++) {
				TypeDeclaration memberType = memberTypes[i];
				rebuildMethodProcessBodies(memberType, module);
			}
		}
	}

}
