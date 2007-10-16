/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.parser;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceElementParser;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceModuleInfoCache;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.core.ISourceModuleInfoCache.ISourceModuleInfo;
import org.eclipse.dltk.internal.core.ModelManager;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.internal.parser.visitors.RubySourceElementRequestor;

public class RubySourceElementParser implements ISourceElementParser {

	public static final Object AST = "ast";
	private ISourceElementRequestor fRequestor = null;
	private IProblemReporter problemReporter;

	public RubySourceElementParser(/*
									 * ISourceElementRequestor requestor,
									 * IProblemReporter problemReporter
									 */) {
		// this.fRequestor = requestor;
		// this.problemReporter = problemReporter;
	}

	public ModuleDeclaration parseSourceModule(char[] contents,
			ISourceModuleInfo astCashe, char[] filename) {

		ModuleDeclaration moduleDeclaration = parseModule(astCashe, contents,
				this.problemReporter, filename);

		RubySourceElementRequestor requestor = new RubySourceElementRequestor(
				this.fRequestor);

		try {
			moduleDeclaration.traverse(requestor);

		} catch (Exception e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		return moduleDeclaration;
	}

	public static ModuleDeclaration parseModule(ISourceModuleInfo astCache,
			char[] content, IProblemReporter problemReporter, char[] filename) {
		return SourceParserUtil.getModuleDeclaration(filename, content,
				RubyNature.NATURE_ID, problemReporter, astCache);
	}

	public void setRequestor(ISourceElementRequestor requestor) {
		this.fRequestor = requestor;
	}

	public static ModuleDeclaration parseModule(ISourceModule module) {
		ISourceModuleInfoCache sourceModuleInfoCache = ModelManager
				.getModelManager().getSourceModuleInfoCache();
		try {
			return parseModule(sourceModuleInfoCache.get(module), module
					.getSourceAsCharArray(), null, null);
		} catch (ModelException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
			return null;
		}
	}

	public void setReporter(IProblemReporter reporter) {
		this.problemReporter = reporter;
	}
}
