/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.core.model.internal;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.ast.ASTCaching;
import org.eclipse.dltk.ast.declarations.ISourceParser;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.core.RubyPlugin;
import org.eclipse.dltk.ruby.core.model.IElement;
import org.eclipse.dltk.ruby.core.model.IElementCriteria;
import org.eclipse.dltk.ruby.core.model.IElementKind;
import org.eclipse.dltk.ruby.core.model.IModel;

public class Model implements IModel {

	private final IScriptProject project;

	private final Map sourceModulesToASTs = new WeakHashMap();

	public Model(IScriptProject project) {
		this.project = project;
	}

	public ModuleDeclaration getASTNode(ISourceModule sourceModule,
			ASTCaching caching) {
		ModuleDeclaration result = null;
		SoftReference astNode = (SoftReference) sourceModulesToASTs
				.get(sourceModule);
		if (astNode != null && caching != ASTCaching.REPARSE)
			result = (ModuleDeclaration) astNode.get();
		if (result == null && caching != ASTCaching.CACHED_ONLY) {
			try {
				ISourceParser parser = DLTKLanguageManager
						.getSourceParser(RubyNature.NATURE_ID);
				result = parser.parse(sourceModule.getPath().toString()
						.toCharArray(), sourceModule.getSourceAsCharArray(),
						null);
			} catch (ModelException e) {
				RubyPlugin.log(e);
				result = null;
			} catch (CoreException e) {
				e.printStackTrace();
			}
			if (result != null) {
				astNode = new SoftReference(result);
				sourceModulesToASTs.put(sourceModule, astNode);
			}
		}
		return result;
	}

	public ISourceModule[] search(String name) {
		Collection sourceModules = new ArrayList();
		try {
			IModelElement[] children = project.getChildren();
			addModules(sourceModules, children);
		} catch (ModelException e) {
			e.printStackTrace();
		}
		return (ISourceModule[]) sourceModules
				.toArray(new ISourceModule[sourceModules.size()]);
	}

	private void addModules(Collection sourceModules, IModelElement[] children)
			throws ModelException {
		for (int i = 0; i < children.length; i++) {
			IModelElement element = children[i];
			if (element instanceof ISourceModule)
				sourceModules.add(element);
			else if (element instanceof IParent)
				addModules(sourceModules, ((IParent) element).getChildren());
		}
	}

	public IElement[] findChildren(IElementCriteria criteria, String name,
			IProgressMonitor pm) {
		if (criteria == IElementCriteria.CLASS_OR_MIXIN) {

		}
		return null;
	}

	public IElement getAncestor(IElementCriteria criteria) {
		return null;
	}

	public IElementKind getElementKind() {
		return IElementKind.MODEL;
	}

}
