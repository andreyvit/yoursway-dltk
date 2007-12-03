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
import org.eclipse.dltk.core.AbstractSourceElementParser;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.core.ISourceModuleInfoCache.ISourceModuleInfo;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.internal.parser.visitors.RubySourceElementRequestor;

public class RubySourceElementParser extends AbstractSourceElementParser {

	public RubySourceElementParser() {
	}

	public void parseSourceModule(char[] contents,
			ISourceModuleInfo astCache, char[] filename) {

		ModuleDeclaration moduleDeclaration = SourceParserUtil
				.getModuleDeclaration(filename, contents, RubyNature.NATURE_ID,
						this.getProblemReporter(), astCache);

		RubySourceElementRequestor requestor = new RubySourceElementRequestor(
				this.getRequestor());

		try {
			moduleDeclaration.traverse(requestor);

		} catch (Exception e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
	}
}
