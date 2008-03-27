/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.statements.Block;

public class RubyModuleDeclaration extends TypeDeclaration {

	private ASTNode name;

	public RubyModuleDeclaration(ASTNode name, Block body, int start, int end) {
		super("", name.sourceStart(), name.sourceEnd(), start, end); //$NON-NLS-1$
		ASTListNode el = new ASTListNode();
		this.setSuperClasses(el);
		this.name = name;
		this.fBody = body;
		setStart(start);
		setEnd(end);
	}

	public ASTNode getClassName() {
		return name;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (this.getClassName() != null) {
				this.getClassName().traverse(visitor);
			}
			if (this.getBody() != null) {
				getBody().traverse(visitor);
			}
			visitor.endvisit(this);
		}
	}

	public List/* <String> */getSuperClassNames() {
		List/* < String > */names = new ArrayList/* < String > */();
		names.addAll(super.getSuperClassNames());
		String name;
		for (Iterator iter = getSuperClasses().getChilds().iterator(); iter
				.hasNext();) {
			name = RubyASTUtil.resolveClassName((ASTNode) iter.next());
			if (name != null && name.length() > 0)
				names.add(name);
		}
		return names;
	}

}
