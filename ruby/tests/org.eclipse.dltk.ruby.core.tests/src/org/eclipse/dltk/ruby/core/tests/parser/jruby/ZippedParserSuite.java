/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.core.tests.parser.jruby;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.tests.model.AbstractModelTests;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.core.tests.Activator;
import org.eclipse.dltk.ruby.internal.parser.JRubySourceParser;

public class ZippedParserSuite extends TestSuite {

	public ZippedParserSuite(String testsZip) {
		super(testsZip);
		ZipFile zipFile;
		try {
			zipFile = new ZipFile(AbstractModelTests.storeToMetadata(Activator
					.getDefault().getBundle(), "parser.zip", testsZip));
			try {
				Enumeration entries = zipFile.entries();
				while (entries.hasMoreElements()) {
					ZipEntry entry = (ZipEntry) entries.nextElement();
					final String fileName = entry.getName();
					final String content = loadContent(zipFile
							.getInputStream(entry));

					addTest(new TestCase(entry.getName()) {

						public void setUp() {

						}

						protected void runTest() throws Throwable {
							JRubySourceParser.setSilentState(false);
							ModuleDeclaration module = DLTKLanguageManager
									.getSourceParser(RubyNature.NATURE_ID)
									.parse(fileName.toCharArray(),
											content.toCharArray(), null);
							assertNotNull(module);
						}

					});
				}
			} finally {
				zipFile.close();
			}
		} catch (ZipException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String loadContent(InputStream stream) throws IOException {
		try {
			int length = stream.available();
			byte[] data = new byte[length];
			stream.read(data);
			return new String(data, "utf-8");
		} finally {
			stream.close();
		}
	}

}
