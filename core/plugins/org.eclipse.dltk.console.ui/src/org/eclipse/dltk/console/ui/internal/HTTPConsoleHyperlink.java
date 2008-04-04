/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.console.ui.internal;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.internal.launching.DLTKLaunchingPlugin;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.console.IHyperlink;
import org.eclipse.ui.console.TextConsole;

/**
 * A hyper link implementation for http:// protocol hyper links.
 */
public class HTTPConsoleHyperlink implements IHyperlink {
	private TextConsole fConsole;

	public HTTPConsoleHyperlink(TextConsole console) {
		fConsole = console;
	}

	public void linkEntered() {
	}

	public void linkExited() {
	}

	public void linkActivated() {
		String uri = getLinkURI();
		IWorkbenchBrowserSupport browserSupport = PlatformUI.getWorkbench()
				.getBrowserSupport();
		IWebBrowser browser;
		try {
			browser = browserSupport.createBrowser(null);
			browser.openURL(new URL(uri));
		} catch (PartInitException e) {
			IStatus status = new Status(IStatus.ERROR,
					DLTKLaunchingPlugin.PLUGIN_ID,
					MessageFormat.format(Messages.HTTPConsoleHyperlink_failedToInitializeBrowserFor, new Object[] { uri }), e);
			DLTKLaunchingPlugin.getDefault().getLog().log(status);
		} catch (MalformedURLException e) {
			IStatus status = new Status(IStatus.ERROR,
					DLTKLaunchingPlugin.PLUGIN_ID,
					MessageFormat.format(Messages.HTTPConsoleHyperlink_failedToOpenInvalidUri, new Object[] { uri }), e);
			DLTKLaunchingPlugin.getDefault().getLog().log(status);
		}
	}

	protected TextConsole getConsole() {
		return fConsole;
	}

	protected String getLinkURI() {
		try {
			IDocument document = getConsole().getDocument();
			IRegion region = getConsole().getRegion(this);
			return document.get(region.getOffset(), region.getLength());
		} catch (BadLocationException e) {
		}
		return null;
	}

}
