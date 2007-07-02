/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.core.codeassist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ISourceParser;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.codeassist.IAssistParser;
import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.tcl.core.ITclKeywords;
import org.eclipse.dltk.tcl.core.TclNature;

public abstract class TclAssistParser implements IAssistParser {
	protected static final int MODULE = ITclKeywords.MODULE;

	protected static final int NAMESPACE = ITclKeywords.NAMESPACE;

	protected static final int FUNCTION = ITclKeywords.FUNCTION;

	protected static final int EXEC_EXPRESSION = ITclKeywords.EXEC_EXPRESSION;

	protected ISourceParser parser = null;

	protected ModuleDeclaration module;

	protected ASTNode assistNodeParent = null;
	
	public TclAssistParser() {
		try {
			this.parser = DLTKLanguageManager.getSourceParser(TclNature.NATURE_ID);
		} catch (CoreException e) {
			if( DLTKCore.DEBUG ) {
				e.printStackTrace();
			}
		}
	}

	public ASTNode getAssistNodeParent() {
		return assistNodeParent;
	}

	protected void findElementsTo(List statements, ASTNode node, List elements) {
		if (statements == null) {
			return;
		}
		Iterator i = statements.iterator();
		while (i.hasNext()) {
			ASTNode n = (ASTNode) i.next();
			if (n.equals(node)) {
				elements.add(n);
				return;
			}
			if (n.sourceStart() <= node.sourceStart()
					&& node.sourceEnd() <= n.sourceEnd()) {
				elements.add(n);
				findElementsTo(TclASTUtil.getStatements(n), node, elements);
				return;
			}
		}

	}

	protected List findLevelsTo(ASTNode astNodeParent) {
		List elements = new ArrayList();
		if (this.module != null || astNodeParent instanceof ModuleDeclaration) {
			if (this.module == null) {
				this.module = (ModuleDeclaration) astNodeParent;
			}
			elements.add(this.module);
			findElementsTo(TclASTUtil.getStatements(this.module),
					astNodeParent, elements);
		}
		return elements;
	}

	public void setSource(ModuleDeclaration unit) {
		this.module = unit;
	}

	public ModuleDeclaration parse(ISourceModule sourceUnit) {
		ModuleDeclaration module = this.parser.parse(sourceUnit.getFileName(), sourceUnit
						.getSourceContents().toCharArray(), null);
		module.rebuild();

		TclASTUtil.extendStatements(module, sourceUnit.getSourceContents());

		return module;
	}
}
