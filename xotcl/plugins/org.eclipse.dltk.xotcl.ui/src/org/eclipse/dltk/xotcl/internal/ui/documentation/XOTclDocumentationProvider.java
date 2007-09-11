/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.xotcl.internal.ui.documentation;

import java.io.Reader;
import java.io.StringReader;

import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.tcl.internal.ui.documentation.ScriptDocumentationProvider;
import org.eclipse.dltk.ui.documentation.IScriptDocumentationProvider;
import org.eclipse.dltk.xotcl.core.documentation.XOTclDocumentationResolver;

public class XOTclDocumentationProvider extends ScriptDocumentationProvider implements IScriptDocumentationProvider {
		
	public Reader getInfo(IMember member, boolean lookIntoParents, boolean lookIntoExternal)  {
		String header = XOTclDocumentationResolver.getDocumentationFor(member);
		// Lets look selected module for documentation for this element.		
		return new StringReader (convertToHTML (header));
	}
	public Reader getInfo(String content) {
		return null;
	}
}

