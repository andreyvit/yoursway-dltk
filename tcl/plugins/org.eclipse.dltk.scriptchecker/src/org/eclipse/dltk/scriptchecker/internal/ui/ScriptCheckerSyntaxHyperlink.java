/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.scriptchecker.internal.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.scriptchecker.internal.core.ScriptCheckerPlugin;
import org.eclipse.ui.console.TextConsole;

/**
 * A hyperlink from a stack trace line of the form "(file "*.*")"
 */
public class ScriptCheckerSyntaxHyperlink extends ScriptCheckerGenericHyperlink {

	public ScriptCheckerSyntaxHyperlink(TextConsole console) {
		super(console);
	}

	protected String getFileName(String linkText) throws CoreException {
		Pattern p = Pattern.compile("((\\w:)?[^:]+):(\\d+)\\s+\\((\\w+)\\)\\s+(.*)");
		Matcher m = p.matcher(linkText);
		if (m.find()) {
			String name = m.group(1);
			return name;
		}
		IStatus status = new Status(
				IStatus.ERROR,
				ScriptCheckerPlugin.PLUGIN_ID,
				0,
				"Error"/*ConsoleMessages.TclFileHyperlink_Unable_to_parse_type_name_from_hyperlink__5*/,
				null);
		throw new CoreException(status);
	}

	protected int getLineNumber(String linkText) throws CoreException {
		Pattern p = Pattern.compile("((\\w:)?[^:]+):(\\d+)\\s+\\((\\w+)\\)\\s+(.*)");
		Matcher m = p.matcher(linkText);
		if (m.find()) {
			String lineText = m.group(3);
			try {
				return Integer.parseInt(lineText);
			} catch (NumberFormatException e) {
			}
		}
		throw new CoreException(Status.CANCEL_STATUS);
	}
}
