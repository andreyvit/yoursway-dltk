/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.formatting;

import java.util.Map;

import org.eclipse.dltk.javascript.internal.ui.JavaScriptUI;
import org.eclipse.dltk.javascript.internal.ui.text.JsPreferenceInterpreter;
import org.eclipse.dltk.ui.text.util.TabStyle;
import org.eclipse.text.edits.ReplaceEdit;
import org.eclipse.text.edits.TextEdit;

import com.xored.org.mozilla.javascript.CompilerEnvirons;
import com.xored.org.mozilla.javascript.Decompiler;
import com.xored.org.mozilla.javascript.ErrorReporter;
import com.xored.org.mozilla.javascript.EvaluatorException;
import com.xored.org.mozilla.javascript.Parser;
import com.xored.org.mozilla.javascript.UintMap;

public class OldCodeFormatter extends CodeFormatter {

	Map options;

	public OldCodeFormatter(Map options) {
		this.options = options;
	}

	public TextEdit format(int kind, String source, int offset, int length,
			StringBuffer computeIndentation, String lineSeparator) {
		String newText = formatString(
				source.substring(offset, offset + length), computeIndentation);
		return new ReplaceEdit(offset, length, newText);
	}

	public String formatString(String substring, StringBuffer computeIndentation) {
		Parser ps = new Parser(new CompilerEnvirons(), new ErrorReporter() {

			public void error(String message, String sourceName, int line,
					String lineSource, int offset) {
				// TODO Auto-generated method stub

			}

			public EvaluatorException runtimeError(String message,
					String sourceName, int line, String lineSource,
					int lineOffset) {
				// TODO Auto-generated method stub
				return null;
			}

			public void warning(String message, String sourceName, int line,
					String lineSource, int lineOffset) {
				// TODO Auto-generated method stub

			}

		});
		ps.parse(substring, "", 0);
		String encodedSource = ps.getEncodedSource();
		Decompiler de = new Decompiler();
		de.setIndent(computeIndentation);
		UintMap uintMap = new UintMap();
		JsPreferenceInterpreter pi = new JsPreferenceInterpreter(JavaScriptUI
				.getDefault().getPreferenceStore());
		uintMap.put(Decompiler.INDENT_GAP_PROP, pi.getIndentSize());
		TabStyle tabStyle = pi.getTabStyle();
		if (tabStyle==TabStyle.TAB)
		uintMap.put(Decompiler.INDENT_USE_TAB, 1);
		else
		uintMap.put(Decompiler.INDENT_USE_TAB, 0);	
		if (computeIndentation == null || computeIndentation.length() == 0)
			return de.decompile(encodedSource, 0, uintMap).trim();
		else
			return de.decompile(encodedSource, 0, uintMap);
	}

}
