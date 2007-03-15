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

import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

public class FilteredFileSelectionDialog extends ElementTreeSelectionDialog {
	public FilteredFileSelectionDialog(Shell parent, IContentType[] contentTypes) {
		super(parent, new WorkbenchLabelProvider(), new WorkbenchContentProvider());
		setShellStyle(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE);

		setTitle(JavaScriptUIMessages.FilteredFileSelectionDialog_0);
		setMessage(JavaScriptUIMessages.FilteredFileSelectionDialog_1);
		setAllowMultiple(false);
		addFilter(new ContentTypeViewerFilter(contentTypes));
	}
}
