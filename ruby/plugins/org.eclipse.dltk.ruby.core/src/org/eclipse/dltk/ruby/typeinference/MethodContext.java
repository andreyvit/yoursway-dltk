/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ti.IContext;
import org.eclipse.dltk.ti.IInstanceContext;
import org.eclipse.dltk.ti.ISourceModuleContext;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class MethodContext implements IContext, IArgumentsContext, IInstanceContext, ISourceModuleContext {

	private final ISourceModule sourceModule;

	private final ModuleDeclaration rootNode;

	private final String[] argNames;

	private final IEvaluatedType[] argTypes;

	private IEvaluatedType instanceType;

	public MethodContext(IContext parent, ISourceModule sourceModule, ModuleDeclaration rootNode,
			String[] argNames, IEvaluatedType[] argTypes) {
		this.sourceModule = sourceModule;
		this.rootNode = rootNode;
		this.argNames = argNames;
		this.argTypes = argTypes;
		if (parent instanceof IInstanceContext) {
			instanceType = ((IInstanceContext) parent).getInstanceType();
		}
	}

	public IEvaluatedType getArgumentType(String name) {
		for (int i = 0; i < argNames.length; i++) {
			String argName = argNames[i];
			if (name.equals(argName))
				if (i < argTypes.length)
					return argTypes[i];
				else
					return null;
		}
		return null;
	}

	public IEvaluatedType getInstanceType() {
		return instanceType;
	}

	public ModuleDeclaration getRootNode() {
		return rootNode;
	}

	public ISourceModule getSourceModule() {
		return sourceModule;
	}
	
	public String getLangNature() {
		if (sourceModule != null) {
			try {
				IDLTKLanguageToolkit languageToolkit = DLTKLanguageManager.getLanguageToolkit(sourceModule);
				if (languageToolkit != null)
					return languageToolkit.getNatureID();
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
