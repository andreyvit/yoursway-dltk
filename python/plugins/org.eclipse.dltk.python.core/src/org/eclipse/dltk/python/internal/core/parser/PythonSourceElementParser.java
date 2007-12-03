/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.core.parser;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceElementParser;
import org.eclipse.dltk.core.SourceParserUtil;
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

	public void parseSourceModule(char[] contents,
			ISourceModuleInfo astCache, char[] filename) {

		ModuleDeclaration moduleDeclaration = SourceParserUtil
				.getModuleDeclaration(filename, contents,
						PythonNature.NATURE_ID, this.fReporter, astCache);

		PythonSourceElementRequestor requestor = new PythonSourceElementRequestor(
				this.fRequestor);

		try {
			moduleDeclaration.traverse(requestor);
		} catch (Exception e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
	}

	public void setRequestor(ISourceElementRequestor requestor) {
		this.fRequestor = requestor;
	}

	public void setReporter(IProblemReporter reporter) {
		this.fReporter = reporter;
	}
}
