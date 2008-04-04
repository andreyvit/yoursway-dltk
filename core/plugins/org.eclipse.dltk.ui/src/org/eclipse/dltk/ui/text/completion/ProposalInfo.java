/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.text.completion;

import java.io.IOException;
import java.io.Reader;

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

//	private String extractScriptdoc(String content) throws ModelException,
//			IOException {
//		if (content != null && fElement != null ) {
//			IDLTKLanguageToolkit languageToolkit = null;
//			try {
//				languageToolkit = DLTKLanguageManager.getLanguageToolkit(fElement);
//			} catch (CoreException e) {
//				if( DLTKCore.DEBUG ) {
//					e.printStackTrace();
//				}
//				return null;
//			}
//			StringBuffer buffer = new StringBuffer();
//			Reader reader = ScriptDocumentationAccess.getHTMLContentReader(languageToolkit.getNatureID(),
//					content);
//			HTMLPrinter.addParagraph(buffer, reader);
//			if (buffer.length() > 0) {
//				HTMLPrinter.addPageEpilog(buffer);
//				return buffer.toString();
//			}
//			return null;
//		}
//
//		return null;
//	}

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
		nature = DLTKLanguageManager.getLanguageToolkit(member)
				.getNatureId();
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
