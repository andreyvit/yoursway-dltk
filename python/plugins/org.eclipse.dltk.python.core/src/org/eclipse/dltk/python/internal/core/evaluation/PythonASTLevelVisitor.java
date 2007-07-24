/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.core.evaluation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.python.parser.ast.PythonImportFromStatement;
import org.eclipse.dltk.python.parser.ast.expressions.PythonImportExpression;
import org.eclipse.dltk.python.parser.ast.statements.IfStatement;
import org.eclipse.dltk.python.parser.ast.statements.SwitchStatement;
import org.eclipse.dltk.python.parser.ast.statements.WhileStatement;


/**
 * Return all nodes on selected AST level. Return only Assignment and Method elements.
 * 
 * @author haiodo
 * 
 */

public class PythonASTLevelVisitor extends ASTVisitor
{
	private List/*< ASTNode >*/ fAppropriateNodes = new ArrayList/*< ASTNode >*/( );

	int count = 0;

	boolean first = true;

	public PythonASTLevelVisitor( ) {

	}

	public List/*< ASTNode >*/ getNodes( ) {

		return this.fAppropriateNodes;
	}

	
	public boolean visit( Expression expression ) throws Exception {

		if( !first ) {
			this.fAppropriateNodes.add( expression );
		}
		else {
			first = false;
		}

		this.count++;
		if( this.count > 1 ) {
			this.count--;
			return false;
		}
		return true;
	}

	
	public boolean visit( Statement statement ) throws Exception {

		// Not adding arguments here.
		if( statement instanceof Argument ) {
			return false;
		}
		// Not adding blocks here.
		if( statement instanceof Block ) {
			return true;
		}
		if( !first ) {
			this.fAppropriateNodes.add( statement );
		}
		else {
			first = false;
		}
		// not goint into imports.
		if( statement instanceof PythonImportExpression ) {
			return false;
		}
		if( statement instanceof PythonImportFromStatement ) {
			return false;
		}
		if( statement instanceof IfStatement ) {// go into in any case.
			return true;
		}

		if( statement instanceof WhileStatement ) {
			return true;
		}

		if( statement instanceof SwitchStatement ) {
			return true;
		}

		this.count++;
		if( this.count > 1 ) {
			this.count--;
			return false;
		}
		return true;
	}

	
	public boolean visit( MethodDeclaration method ) throws Exception {

		this.count++;

		if( !first ) {
			this.fAppropriateNodes.add( method );
		}
		else {
			first = false;
		}
		if( this.count > 1 ) {
			return false;
		}
		return true;
	}

	
	public boolean visit( ModuleDeclaration declaration ) throws Exception {

		this.count++;
		if( this.count > 1 ) {
			return false;
		}
		return true;
	}

	
	public boolean visit( TypeDeclaration typeDeclaration ) throws Exception {

		this.count++;
		if( !first ) {
			this.fAppropriateNodes.add( typeDeclaration );
		}
		else {
			first = false;
		}

		if( this.count > 1 ) {
			return false;
		}

		return true;
	}
}
