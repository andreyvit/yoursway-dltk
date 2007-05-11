/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.search.matching.PatternLocator;
import org.eclipse.dltk.tcl.ast.TclModuleDeclaration;
import org.eclipse.dltk.tcl.ast.TclNamespaceDeclaration;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;

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

	/**
	 * Extends AST and returns top types and functions to selected lists.
	 * 
	 * @param declaration
	 * @param types
	 * @param functions
	 */
	public static void buildAST(TclModuleDeclaration declaration, List types, List functions, List variables) {
		build(declaration, declaration.getStatements(), TYPE_MODULE);
		List statements = declaration.getStatements();
		if (statements != null) {
			Iterator i = statements.iterator();
			while (i.hasNext()) {
				ASTNode node = (ASTNode) i.next();
				if (node instanceof MethodDeclaration) {
					functions.add(node);
				} else if (node instanceof TypeDeclaration) {
					types.add(node);
				}
				else if( node instanceof TclStatement ) {
					
					TclStatement tNode = (TclStatement)node;
					
					FieldDeclaration[] fields = TclParseUtils.returnVariableDeclarations(tNode);
					for( int k = 0; k < fields.length; ++k ) {
						boolean exist = false;
						for( int t = 0; t < variables.size(); ++t ) {
							FieldDeclaration f = (FieldDeclaration)variables.get(t);
							if( f.getName().equals(fields[k].getName())) {
								exist = true;
								break;
							}
						}
						if( !exist ) {
							variables.add( fields[k] );
						}
					}
				}
			}
		}
	}

	public static void build(ASTNode node, List ostatements, int type) {
		if (ostatements == null) {
			return;
		}
		List newStatements = new ArrayList(ostatements.size());
		newStatements.addAll(ostatements);
		Iterator i = newStatements.iterator();
		while (i.hasNext()) {
			Statement sst = (Statement) i.next();
			if (sst instanceof TclStatement) {
				final TclStatement statement = (TclStatement) sst;
				Expression commandId = statement.getAt(0);
				if (commandId != null && commandId instanceof SimpleReference) {
					String name = ((SimpleReference) commandId).getName();
					// System.out.println( "statement:" + name );
					if (name.equals("proc")) {
						processProc(node, statement);
					} else if (name.equals("namespace")) {
						Expression nameSpaceArg = statement.getAt(1);
						if (nameSpaceArg == null || !(nameSpaceArg instanceof SimpleReference)) {
							// TODO: Add error reporting here.
							System.err.println("tcl: namespace argument is null or not simple reference");
							continue;
						}
						Expression nameSpaceName = statement.getAt(2);
						if (nameSpaceName == null || !(nameSpaceName instanceof SimpleReference)) {
							// TODO: Add error reporting here.
							continue;
						}
						String sNameSpaceArg = ((SimpleReference) nameSpaceArg).getName();
						String sNameSpaceName = ((SimpleReference) nameSpaceName).getName();
						Expression code = statement.getAt(3);
						if (code == null || !(code instanceof TclBlockExpression)) {
							// TODO: Add error reporting here.
							continue;
						}
						if (sNameSpaceArg.equals("eval")) {
							TclNamespaceDeclaration nsType = new TclNamespaceDeclaration(sNameSpaceName, nameSpaceName.sourceStart(), nameSpaceName.sourceEnd(),
									statement.sourceStart(), statement.sourceEnd());
							if( node instanceof TypeDeclaration ) {
								TypeDeclaration t = ((TypeDeclaration)node);
								nsType.setEnclosingTypeName(t.getEnclosingTypeName() + "$" + t.getName() );
							}
							replaceChild(node, statement, nsType);
							List/* < Statement > */inner = null;
							
							inner = ((TclBlockExpression) code).parseBlock(code.sourceStart() + 1);
							
							Block block = new Block(code.sourceStart(), code.sourceEnd(), inner);
							nsType.setBody(block);
							build(nsType, inner, TYPE_NAMESPACE);
						}
					}
				}
			}
		}
	}

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

	private static void processProc(ASTNode parent, TclStatement statement) {
		// should be 3 parameters.
		if (statement.getCount() < 4) {
			// TODO: Add error reporting here.
			// System.err.println( "tcl proc not correct..." );
			return;
		}
		Expression procName = statement.getAt(1);
		if (procName == null/*
							 * || !( procName instanceof SimpleReference )
							 */) {
			throw new RuntimeException("empty proc name");
		}
		if (!(procName instanceof SimpleReference)) // FIXME
			return;
		Expression procArguments = statement.getAt(2);
		Expression procCode = statement.getAt(3);
		// System.out.println( "Define proc: " + procName + " " + procCode );
		List arguments = null;
		if (procArguments instanceof TclBlockExpression) {
			List/* < Statement > */st = null;
			//try {
				st = ((TclBlockExpression) procArguments).parseBlock();
			/*} catch (ANTLRException ex) {
				if( DLTKCore.DEBUG_PARSER )
					ex.printStackTrace();
			}*/
			arguments = TclParseUtils.parseArguments(st);
		}
		if (procArguments instanceof SimpleReference) {
			arguments = new ArrayList();
			Argument a = new Argument((SimpleReference)procArguments, procArguments.sourceStart(), null, 0 );
			arguments.add(a);
		}
		SimpleReference refProcName = (SimpleReference) procName;
		MethodDeclaration method = new MethodDeclaration(refProcName.getName(), refProcName.sourceStart(), refProcName.sourceEnd(), statement
				.sourceStart(), statement.sourceEnd());
		if( parent instanceof TypeDeclaration ) {
			TypeDeclaration t = ((TypeDeclaration)parent);
			method.setDeclaringTypeName(t.getEnclosingTypeName() + "$" + t.getName() );
		}
		method.acceptArguments(arguments);
		replaceChild(parent, statement, method);

		if (procCode instanceof TclBlockExpression) {
			List code = null;
			//try {
				code = ((TclBlockExpression) procCode).parseBlock(procCode.sourceStart() + 1);
				Iterator i = code.iterator();
				while (i.hasNext()) {
					Statement in = (Statement) i.next();
					in.setStart(in.sourceStart());
					in.setEnd(in.sourceEnd());
				}
				
				Block body = new Block(procCode.sourceStart(), procCode.sourceEnd(), code);
				method.acceptBody(body, false);
				// Correct end value. Because some argument may be after, this
				// is not correct for proc, but we need this for correct
				// completion.
				build(method, code, TYPE_PROC);
			/*} catch (ANTLRException e) {
				if( DLTKCore.DEBUG_PARSER ) {
					System.out.println("ANTLRException: " + e.getMessage());
					System.out.println("TclASTBuilder.processProc()");
					e.printStackTrace();
				}
			}*/
		}
	}

	// Enriches AST with callInvocations and other stuff.
	public static void buildFullAST(TclModuleDeclaration decl) {
		// TODO Auto-generated method stub
		
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
						name = name.replace("(::)+", "::");
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
