/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.core.codeassist;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.codeassist.IAssistParser;
import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.ruby.internal.parser.RubySourceElementParser;


public abstract class RubyAssistParser implements IAssistParser {

	protected ModuleDeclaration module;
	
	protected ASTNode assistNodeParent = null;

	public ASTNode getAssistNodeParent() {
		return assistNodeParent;
	}

	public void setSource(ModuleDeclaration unit) {
		this.module = unit;
	}

	public ModuleDeclaration parse(ISourceModule sourceUnit) {
		ModuleDeclaration module=null;
		module = RubySourceElementParser.parseModule((org.eclipse.dltk.core.ISourceModule)sourceUnit.getModelElement());
		
		return module;
	}
}
