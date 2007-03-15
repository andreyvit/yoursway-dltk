/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.javascript.ui.internal.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.StatusTextEvent;
import org.eclipse.swt.browser.StatusTextListener;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;

public class JSPreviewPage {
	private JSEditor fEditor = null;
	private ViewForm fViewForm = null;
	private Browser fBrowser = null;
	private JSPreviewContributor fPreviewContributor = null;

	public JSPreviewPage(Composite parent, JSEditor editor) {
		fEditor = editor;

		fViewForm = new ViewForm(parent, SWT.NONE);
		fBrowser = new Browser(fViewForm, SWT.NONE);
		fViewForm.setContent(fBrowser);
		final IActionBars actionBars = fEditor.getEditorSite().getActionBars();
		fBrowser.addStatusTextListener(new StatusTextListener() {
			public void changed(StatusTextEvent event) {
				actionBars.getStatusLineManager().setMessage(event.text);
			}
		});

		fPreviewContributor = new JSPreviewContributor(fViewForm, fBrowser, fEditor);
		fPreviewContributor.createPreviewToolBar();

		IEditorInput input = fEditor.getEditorInput();
		IProject project = null;
		if (input instanceof IFileEditorInput) {
			IFile inputFile = ((IFileEditorInput) input).getFile();
			project = inputFile.getProject();
		}
		fPreviewContributor.setProject(project);
	}

	public Control getControl() {
		return fViewForm;
	}

	public void refresh() {
		fPreviewContributor.refresh();
	}
}
