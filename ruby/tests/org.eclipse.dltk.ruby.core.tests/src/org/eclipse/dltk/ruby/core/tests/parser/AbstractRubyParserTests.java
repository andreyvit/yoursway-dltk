/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.core.tests.parser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.tests.model.AbstractModelTests;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.core.tests.Activator;

public abstract class AbstractRubyParserTests extends AbstractModelTests {
	public AbstractRubyParserTests(String testProjectName, String name) {
		super(testProjectName, name);
	}

	public ModuleDeclaration processScript(String name) throws Exception {
		ModuleDeclaration module = null;
		InputStream input = null;
		try {
			input = Activator.openResource(name);

			InputStreamReader reader = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(reader);
			StringBuffer buffer = new StringBuffer();
			while (br.ready()) {
				String l = br.readLine();
				if (l != null) {
					buffer.append(l);
					buffer.append('\n');
				}
			}
			module = DLTKLanguageManager
			.getSourceParser(RubyNature.NATURE_ID).parse(name.toCharArray(), buffer
					.toString().toCharArray(), null);
			assertNotNull(module);
			assertFalse(module.isEmpty());
		} finally {
			if (input != null) {
				input.close();
			}
		}
		return module;
	}
}
