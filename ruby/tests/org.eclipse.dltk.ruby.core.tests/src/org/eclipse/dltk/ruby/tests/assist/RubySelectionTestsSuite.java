/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.tests.assist;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.dltk.ruby.tests.Activator;

public class RubySelectionTestsSuite extends TestSuite {

	public RubySelectionTestsSuite(final String path) {
		super(path);

		int pos = path.lastIndexOf('/');
		final String name = (pos >= 0 ? path.substring(pos + 1) : path);

		String content;
		try {
			content = loadContent(path);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		ArrayList positions = new ArrayList();

		int lineOffset = 0;
		String[] lines = content.split("\n");
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i].trim();
			int cmnt = -1;
			if ((cmnt = line.indexOf("##")) != -1) {				
				String substring = line.substring(cmnt + 2);
				if (substring.startsWith("#"))
					continue;
				StringTokenizer tok = new StringTokenizer(substring);
				
				int offset = Integer.parseInt(tok.nextToken()) + lineOffset;
				int length = 0;
				if (tok.hasMoreTokens()) {
					length = Integer.parseInt(tok.nextToken());
				}
				positions.add(new int[] { offset, length, i + 1 });
			}
			lineOffset += lines[i].length() + 1;
		}

		for (Iterator iterator = positions.iterator(); iterator.hasNext();) {
			final int[] position = (int[]) iterator.next();
			addTest(new TestCase("on \"" +content.substring(position[0] - 5, position[0] + 5) +  "\" at [" + position[0] + "," + position[1] + "] line " + position[2]) {

				private RubySelectionTests tests;

				public void setUp() throws Exception {
					tests = new RubySelectionTests("ruby selection tests");
					try {
						tests.setUpSuite();
					} catch (Exception e) {
					}
				}

				protected void runTest() throws Throwable {
					tests.executeTest(name, position[0], position[1]);
				}

			});
		}

	}

	private String loadContent(String path) throws IOException {
		StringBuffer buffer = new StringBuffer();
		InputStream input = null;
		try {
			input = Activator.openResource(path);
			InputStreamReader reader = new InputStreamReader(input);
			char[] cbuf = new char[10000];
			while (input.available() > 0) {
				int read = reader.read(cbuf);
				buffer.append(cbuf, 0, read);
			}
		} finally {
			if (input != null) {
				input.close();
			}
		}
		String content = buffer.toString();
		return content;
	}

}
