/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.core.parser;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.declarations.ISourceParser;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.ISourceElementParser;
import org.eclipse.dltk.core.ISourceModuleInfoCache.ISourceModuleInfo;
import org.eclipse.dltk.python.core.PythonNature;
import org.eclipse.dltk.python.internal.core.parser.visitors.PythonSourceElementRequestor;

public class PythonSourceElementParser implements ISourceElementParser {

	private ISourceElementRequestor fRequestor = null;
	private IProblemReporter fReporter = null;

	/**
	 * Python lexer handler helper.
	 * 
	 * @param enveronment
	 */

	public PythonSourceElementParser( /*
										 * ISourceElementRequestor requestor,
										 * IProblemReporter problemReporter
										 */) {
		// this.fRequestor = requestor;
		// this.fReporter = problemReporter;
	}

	public ModuleDeclaration parseSourceModule(char[] contents,
			ISourceModuleInfo astCashe, char[] filename) {

		String content = new String(contents);

		ISourceParser sourceParser;// = new PythonSourceParser(this.fReporter);
		try {
			sourceParser = DLTKLanguageManager
					.getSourceParser(PythonNature.NATURE_ID);
		} catch (CoreException e) {
			throw new RuntimeException("Failed to create python source parser",
					e);
		}
		ModuleDeclaration moduleDeclaration = null;
		try {
			moduleDeclaration = sourceParser.parse(null, content.toCharArray(),
					fReporter);

			PythonSourceElementRequestor requestor = new PythonSourceElementRequestor(
					this.fRequestor);

			moduleDeclaration.traverse(requestor);
		} catch (Throwable e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		return moduleDeclaration;
	}

	public void setRequestor(ISourceElementRequestor requestor) {
		this.fRequestor = requestor;
	}

	public void setReporter(IProblemReporter reporter) {
		this.fReporter = reporter;
	}
}
