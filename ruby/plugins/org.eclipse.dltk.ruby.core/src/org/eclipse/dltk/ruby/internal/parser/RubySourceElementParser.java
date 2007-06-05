/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.parser;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.declarations.ISourceParser;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.ISourceElementParser;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceModuleInfoCache;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.ISourceModuleInfoCache.ISourceModuleInfo;
import org.eclipse.dltk.internal.core.ModelManager;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.internal.parser.visitors.RubySourceElementRequestor;

public class RubySourceElementParser implements ISourceElementParser {

	public static final Object AST = "ast";
	private ISourceElementRequestor fRequestor = null;
	private IProblemReporter problemReporter;

	public RubySourceElementParser(/*ISourceElementRequestor requestor,
			IProblemReporter problemReporter*/) {
//		this.fRequestor = requestor;
//		this.problemReporter = problemReporter;
	}

	public ModuleDeclaration parseSourceModule(char[] contents, ISourceModuleInfo astCashe) {

		ModuleDeclaration moduleDeclaration = parseModule(astCashe, contents, this.problemReporter);

		RubySourceElementRequestor requestor = new RubySourceElementRequestor(
				this.fRequestor);

		try {
			moduleDeclaration.traverse(requestor);
			
		} catch (Exception e) {
			if( DLTKCore.DEBUG ) {
				e.printStackTrace();
			}
		}
		return moduleDeclaration;
	}

	public static ModuleDeclaration parseModule(ISourceModuleInfo astCache,
			char[] content, IProblemReporter problemReporter ) {
		ModuleDeclaration moduleDeclaration = null;
		if( astCache != null ) {
			moduleDeclaration = (ModuleDeclaration)astCache.get(AST);
		}
		if( moduleDeclaration == null ) {
			ISourceParser sourceParser = null;
			try {
				sourceParser = DLTKLanguageManager.getSourceParser(RubyNature.NATURE_ID);
			} catch (CoreException e) {
				e.printStackTrace();
			}
			if( sourceParser != null ) {
				moduleDeclaration = sourceParser.parse(content, problemReporter);
				if( moduleDeclaration != null && astCache != null ) {
					astCache.put(AST, moduleDeclaration );
				}
			}
		}
		return moduleDeclaration;
	}

	public void setRequestor(ISourceElementRequestor requestor) {
		this.fRequestor = requestor;
	}

	public static ModuleDeclaration parseModule(ISourceModule module) {
		ISourceModuleInfoCache sourceModuleInfoCache = ModelManager.getModelManager().getSourceModuleInfoCache();
		try {
			return parseModule(sourceModuleInfoCache.get(module), module.getSourceAsCharArray(), null);
		} catch (ModelException e) {
			if( DLTKCore.DEBUG ) {
				e.printStackTrace();
			}
			return null;
		}
	}

	public void setReporter(IProblemReporter reporter) {
		this.problemReporter = reporter;
	}
}
