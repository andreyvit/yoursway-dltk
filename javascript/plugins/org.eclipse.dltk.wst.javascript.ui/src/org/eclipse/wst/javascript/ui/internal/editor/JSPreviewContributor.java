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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class JSPreviewContributor {
	private ViewForm fViewForm = null;
	private Browser fBrowser = null;
	private JSEditor fEditor = null;
	private Composite fPreviewToolBar = null;
	private Combo fCombo = null;
	private IProject fProject = null;

	public JSPreviewContributor(ViewForm form, Browser browser, JSEditor editor) {
		super();

		fViewForm = form;
		fBrowser = browser;
		fEditor = editor;
	}

	public void createPreviewToolBar() {

		if (fViewForm == null)
			return;

		fViewForm.setTopCenterSeparate(true);

		fPreviewToolBar = new Composite(fViewForm, SWT.NONE);
		RowLayout layout = new RowLayout();
		layout.marginTop = layout.marginBottom = 0;
		fPreviewToolBar.setLayout(layout);
		fViewForm.setTopRight(fPreviewToolBar);

		ToolBar toolBar = new ToolBar(fPreviewToolBar, SWT.FLAT);

		ToolItem browseButton = new ToolItem(toolBar, SWT.PUSH);
		browseButton.setText(JavaScriptUIMessages.BrowseButtonText); //$NON-NLS-1$
		browseButton.setToolTipText(JavaScriptUIMessages.BrowseButtonToolTipText); //$NON-NLS-1$
		browseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Shell shell = ((ToolItem) e.widget).getParent().getShell();
				IContentType[] contentTypes = new IContentType[2];
				IContentTypeManager manager = Platform.getContentTypeManager();
				// ISSUE: we shouldn't have to depend on HTML and JSP in this
				// direction?
				contentTypes[0] = manager.getContentType("org.eclipse.wst.html.core.htmlsource"); //$NON-NLS-1$
				contentTypes[1] = manager.getContentType("org.eclipse.jst.jsp.core.jspsource"); //$NON-NLS-1$
				FilteredFileSelectionDialog dialog = new FilteredFileSelectionDialog(shell, contentTypes);
				IResource resource = fProject;
				if (resource == null)
					resource = ResourcesPlugin.getWorkspace().getRoot();
				dialog.setInput(resource);
				dialog.open();
				Object[] result = dialog.getResult();
				if (result != null && result.length > 0) {
					IPath resourcePath = ((IResource) result[0]).getFullPath();
					String previewFileName = ""; //$NON-NLS-1$
					if (resourcePath != null) {
						previewFileName = resourcePath.toString();
					}
					fCombo.add(previewFileName);
					fCombo.setText(previewFileName);
					refresh();
				}
			}
		});

		Composite topLeft = new Composite(fViewForm, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		gridLayout.numColumns = 2;
		topLeft.setLayout(gridLayout);

		CLabel title = new CLabel(topLeft, SWT.NONE);
		title.setText(JavaScriptUIMessages.PreviewPageDropDownLabel); //$NON-NLS-1$
		title.setLayoutData(new GridData());

		fCombo = new Combo(topLeft, SWT.DROP_DOWN);
		String defaultFileName = JavaScriptUIMessages.DefaultFileName; //$NON-NLS-1$
		fCombo.add(defaultFileName);
		fCombo.setText(defaultFileName);
		fCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		fCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				refresh();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				refresh();
			}
		});

		fViewForm.setTopLeft(topLeft);
	}

	public void setProject(IProject project) {
		fProject = project;
	}

	public void refresh() {
		IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(fCombo.getText());
		if (resource != null && resource.getType() == IResource.FILE) {
			String url = "file://" + resource.getLocation(); //$NON-NLS-1$
			fBrowser.setUrl(url);
		}
		else {
			String editorText = fEditor.getDocumentProvider().getDocument(fEditor.getEditorInput()).get();
			String previewText = "<HTML><BODY><SCRIPT language=\"JavaScript\">" + "<!--" + "\n" + editorText + "\n" + "//-->" + "</SCRIPT></BODY></HTML>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
			fBrowser.setText(previewText);
		}
	}
}
