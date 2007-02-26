/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.ui.text.completion;

import java.io.IOException;
import java.io.Reader;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.documentation.ScriptDocumentationAccess;

public class ProposalInfo {
	private boolean fScriptdocResolved = false;
	private String fScriptdoc = null;
	
	private String hackMessage;
	
	public void setHackMessage(String message){
		hackMessage = message;		 
	}

	protected IModelElement fElement;

	public ProposalInfo(IMember member) {
		fElement = member;
	}

	protected ProposalInfo() {
		fElement = null;
	}

	public IModelElement getModelElement() throws ModelException {
		return fElement;
	}

	/**
	 * Gets the text for this proposal info formatted as HTML, or
	 * <code>null</code> if no text is available.
	 * 
	 * @param monitor
	 *            a progress monitor
	 * @return the additional info text
	 */
	public  String getInfo(IProgressMonitor monitor) {
//		if (hackMessage != null){
//			return hackMessage;			
//		}
		
		if (!fScriptdocResolved) {
			fScriptdocResolved = true;
			fScriptdoc = computeInfo(monitor);
		}
		return fScriptdoc;
	}

	/**
	 * Gets the text for this proposal info formatted as HTML, or
	 * <code>null</code> if no text is available.
	 * 
	 * @param monitor
	 *            a progress monitor
	 * @return the additional info text
	 */
	private String computeInfo(IProgressMonitor monitor) {
		try {
			final IModelElement modelElement = getModelElement();
			if (modelElement instanceof IMember) {
				IMember member = (IMember) modelElement;
				return extractScriptdoc(member, monitor);
			}
		} catch (ModelException e) {
			DLTKUIPlugin.log(e);
		} catch (IOException e) {
			DLTKUIPlugin.log(e);
		}
		return null;
	}

	private String extractScriptdoc(String content) throws ModelException,
			IOException {
		if (content != null) {
			//return ScriptDocumentationAccess.getHTMLContentReader(nature,
					//content);
		}

		return null;
	}

	/**
	 * Extracts the javadoc for the given <code>IMember</code> and returns it
	 * as HTML.
	 * 
	 * @param member
	 *            the member to get the documentation for
	 * @param monitor
	 *            a progress monitor
	 * @return the javadoc for <code>member</code> or <code>null</code> if
	 *         it is not available
	 * @throws ModelException
	 *             if accessing the javadoc fails
	 * @throws IOException
	 *             if reading the javadoc fails
	 */
	private String extractScriptdoc(IMember member, IProgressMonitor monitor)
			throws ModelException, IOException {
		if (member != null) {
			Reader reader = getHTMLContentReader(member, monitor);
			if (reader != null)
				return getString(reader);
		}
		return null;
	}

	private Reader getHTMLContentReader(IMember member, IProgressMonitor monitor)
			throws ModelException {
		String nature = null;
		try {
			nature = DLTKLanguageManager.getLangaugeToolkit(member)
					.getNatureID();
		} catch (CoreException e) {
			return null;
		}
		if (nature == null)
			return null;
		return ScriptDocumentationAccess.getHTMLContentReader(nature, member,
				true, false);
	}

	/**
	 * Gets the reader content as a String
	 */
	private static String getString(Reader reader) {
		StringBuffer buf = new StringBuffer();
		char[] buffer = new char[1024];
		int count;
		try {
			while ((count = reader.read(buffer)) != -1)
				buf.append(buffer, 0, count);
		} catch (IOException e) {
			return null;
		}
		return buf.toString();
	}
}
