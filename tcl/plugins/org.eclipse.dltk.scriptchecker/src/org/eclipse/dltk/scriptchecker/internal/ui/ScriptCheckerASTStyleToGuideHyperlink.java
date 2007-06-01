/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.scriptchecker.internal.ui;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.scriptchecker.internal.ui.ATSGuideManager.GuideNode;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.console.IHyperlink;
import org.eclipse.ui.console.TextConsole;

public class ScriptCheckerASTStyleToGuideHyperlink implements IHyperlink {
	private TextConsole fConsole;
	private final static Pattern pattern = Pattern
			.compile("((\\w:)?[^:]+):(\\d+):(.*):\\s*\\((.*)\\)(.*)");

	public void linkEntered() {
	}

	public void linkExited() {
	}

	protected TextConsole getConsole() {
		return fConsole;
	}

	public ScriptCheckerASTStyleToGuideHyperlink(TextConsole console) {
		this.fConsole = console;
	}
	
	protected String getLinkText() throws CoreException {
		try {
			IDocument document = getConsole().getDocument();
			IRegion region = getConsole().getRegion(this);
			int regionOffset = region.getOffset();

			int lineNumber = document.getLineOfOffset(regionOffset);
			IRegion lineInformation = document.getLineInformation(lineNumber);
			int lineOffset = lineInformation.getOffset();
			String line = document.get(lineOffset, lineInformation.getLength());

			return line;
		} catch (BadLocationException e) {
			return "";
		}
	}

	public void linkActivated() {
		String guide = getGuide(getConsole());
		if (guide != null) {
			GuideNode[] guides = ATSGuideManager.getInstance().getGuides();
			for (int i = 0; i < guides.length; i++) {
				if( !guides[i].isRegexp() && guide.equals( guides[i].getPattern() ) ) {
					String uri = guides[i].getUri();
					executeBrowser(uri);
				}
				else if( guides[i].isRegexp() ) {
					String pattern = guides[i].getPattern();
					Pattern p = Pattern.compile(pattern);
					Matcher matcher = p.matcher(guide);
					if( matcher.find() ) {
						String uri = guides[i].getUri();
						int pos = uri.indexOf("%s");
						if( pos != -1 ) {
							String p1 = uri.substring(0, pos);
							String p2 = uri.substring(pos + 2, uri.length());
							uri = p1 + guide + p2;
						}
						executeBrowser(uri);
					}
				}
			}
		}
	}

	private void executeBrowser(String uri) {
		try {
			IWorkbenchBrowserSupport browserSupport = PlatformUI.getWorkbench().getBrowserSupport();
			IWebBrowser browser = browserSupport.createBrowser(null);	
			browser.openURL(new URL( uri ));
		} catch (PartInitException e) {
			if( DLTKCore.DEBUG ) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			if( DLTKCore.DEBUG ) {
				e.printStackTrace();
			}
		}
	}

	private String getGuide(TextConsole console) {
		Matcher m = null;
		try {
			m = pattern.matcher(getLinkText());
		} catch (CoreException e) {
			if( DLTKCore.DEBUG ) {
				e.printStackTrace();
			}
		}
		if (m != null && m.find()) {
			String lineText = m.group(5);
			if( lineText != null ) {
				return lineText;
			}
		}
		return "";
	}

	/**
	 * Returns the console this link is contained in.
	 * 
	 * @return console
	 */

	public int computeOffset(int offset, int length, TextConsole console) {
		Matcher m = matchPattern(offset, length, console);
		if (m != null && m.find()) {
			int len = m.start(5);
			return offset + len;
		}
		return offset;
	}

	public int computeLength(int offset, int length, TextConsole console) {
		Matcher m = matchPattern(offset, length, console);
		if (m != null && m.find()) {
			int len = m.end(5) - m.start(5);
			return len;
		}
		return length;
	}

	private Matcher matchPattern(int offset, int length, TextConsole console) {
		String linkText = null;

		IDocument document = console.getDocument();
		try {
			int lineNumber = document.getLineOfOffset(offset);
			IRegion lineInformation = document.getLineInformation(lineNumber);
			int lineOffset = lineInformation.getOffset();
			linkText = document.get(lineOffset, lineInformation.getLength());
		} catch (BadLocationException e) {
			return null;
		}
		Matcher m = pattern.matcher(linkText);
		return m;
	}
}
