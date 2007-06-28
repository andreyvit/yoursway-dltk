/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.debug.ui.preferences;

import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.tcl.internal.debug.TclDebugPreferences;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class TclDebugPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	private Text debuggingEnginePath;

	protected void createDebuggingEnginePath(Composite parent, Object data) {
		Group group = new Group(parent, SWT.NONE);
		group.setFont(parent.getFont());
		group.setText("Path to debugging engine");
		group.setLayoutData(data);

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		group.setLayout(layout);

		// Path
		debuggingEnginePath = new Text(group, SWT.BORDER);

		debuggingEnginePath.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				validteDebuggingEnginePath();
			}
		});

		GridData pathData = new GridData();
		pathData.grabExcessHorizontalSpace = true;
		pathData.horizontalAlignment = GridData.FILL;
		debuggingEnginePath.setLayoutData(data);

		// Browse
		Button button = new Button(group, SWT.PUSH);
		button.setText("Browse...");

		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
				String file = dialog.open();
				if (file != null) {
					debuggingEnginePath.setText(file);
				}
			}
		});
	}

	protected GridData makeGridData() {
		GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = GridData.FILL;
		return data;
	}

	protected Control createContents(Composite parent) {
		setDescription(TclDebugPreferencesMessages.PreferencesDescription);
		
		Composite top = new Composite(parent, SWT.NONE);

		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		top.setLayout(layout);

		createDebuggingEnginePath(top, makeGridData());

		initializeValues();
		validateValues();

		return top;
	}

	protected void initializeValues() {
		debuggingEnginePath.setText(TclDebugPreferences
				.getDebuggingEnginePath());
	}

	protected void validteDebuggingEnginePath() {
		String txtPath = debuggingEnginePath.getText().trim();

		IPath path = Path.fromOSString(txtPath);
		File file = path.toFile();
		
		if ("".equals(txtPath)) {
			setMessage("Empty path", WARNING);
			setValid(true);
		} else if (!file.exists()) {
			setMessage("File not exists", ERROR);
			setValid(false);
		} else if (!file.isFile()) {
			setMessage("Not a file", ERROR);
			setValid(false);
		} else {
			setMessage(null);
			setValid(true);
		}
	}

	protected void validateValues() {
		validteDebuggingEnginePath();
	}

	public void init(IWorkbench workbench) {

	}

	public boolean performOk() {
		TclDebugPreferences.setDebuggingEnginePath(debuggingEnginePath
				.getText());

		TclDebugPreferences.save();
		return true;
	}
}
