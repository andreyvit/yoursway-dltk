/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.ui.breakpoints;

import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.dltk.debug.core.ScriptDebugManager;
import org.eclipse.dltk.debug.core.model.IScriptBreakpoint;
import org.eclipse.dltk.debug.core.model.IScriptLineBreakpoint;
import org.eclipse.dltk.debug.core.model.IScriptMethodEntryBreakpoint;
import org.eclipse.dltk.debug.core.model.IScriptWatchPoint;
import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.dltk.ui.DLTKUILanguageManager;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;

public class ScriptBreakpointPropertyPage extends PropertyPage {

	// Enabled
	private Button enabledBreakpointButton;

	// Hit count checking
	private Button hitCountCheckingButton;
	private Combo hitConditionCombo;
	private Text hitValueText;

	// Expression
	private SourceViewer expressionViewer;
	private Button enableExpressionButton;

	// Other
	private Button method_entry;
	private Button method_exit;

	// Simple access methods
	protected boolean getBreakointEnableState() {
		return enabledBreakpointButton.getSelection();
	}

	protected boolean getEnabledHitChecking() {
		return hitCountCheckingButton.getSelection();
	}

	// Hit value & condition
	protected void setHitCondition(int condition) {
		hitConditionCombo.select(condition);
	}

	protected int getHitCondition() {
		return getEnabledHitChecking() ? hitConditionCombo.getSelectionIndex()
				: -1;
	}

	protected void setHitValue(int value) {
		hitValueText.setText(Integer.toString(value));
	}

	protected int getHitValue() {
		return getEnabledHitChecking() ? Integer.parseInt(hitValueText
				.getText()) : -1;
	}

	// Expresion
	protected void setExpression(String expression) {
		expressionViewer.getDocument().set(expression);
	}

	protected String getExpression() {
		return expressionViewer.getDocument().get();
	}

	protected void setExpressionState(boolean state) {
		enableExpressionButton.setSelection(state);
	}

	protected boolean getExpressionState() {
		return enableExpressionButton.getSelection();
	}

	protected Composite createComposite(Composite parent, int numColumns) {
		Composite composit = new Composite(parent, SWT.NONE);
		composit.setFont(parent.getFont());

		GridLayout layout = new GridLayout(numColumns, false);
		composit.setLayout(layout);
		composit.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		return composit;
	}

	protected Button createRadioButton(Composite parent, String text) {
		Button button = new Button(parent, SWT.RADIO | SWT.LEFT);
		button.setText(text);
		button.setFont(parent.getFont());
		button.setLayoutData(new GridData());
		return button;
	}

	protected Button createCheckButton(Composite parent, String text) {
		Button button = new Button(parent, SWT.CHECK | SWT.LEFT);
		button.setText(text);
		button.setFont(parent.getFont());
		button.setLayoutData(new GridData());
		return button;
	}

	protected Label createLabel(Composite parent, String text) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(text);
		label.setFont(parent.getFont());
		label.setLayoutData(new GridData());
		return label;
	}

	// Static breakpoint information
	protected void createLabels(Composite parent) throws CoreException {
		IScriptBreakpoint breakpoint = getBreakpoint();

		Composite labelComposite = createComposite(parent, 2);

		// Type
		createLabel(labelComposite, "Type:");
		createLabel(labelComposite, breakpoint.getClass().getName());

		// Script language
		createLabel(labelComposite, "Script Language:");
		createLabel(labelComposite, breakpoint.getModelIdentifier());

		// Resource name
		String resourceName = breakpoint.getResourceName();
		if (resourceName != null) {
			createLabel(labelComposite, "File:");
			createLabel(labelComposite, resourceName);
		}

		// Hit count
		createLabel(labelComposite, "Hit count:");
		createLabel(labelComposite, "Not available"); // retreive hit count
		// from debugging engine

		createTypeSpecificLabels(labelComposite);
	}

	protected void createTypeSpecificLabels(Composite parent)
			throws CoreException {
		IScriptBreakpoint breakpoint = getBreakpoint();

		// IScriptLineBreakpoint
		if (breakpoint instanceof IScriptLineBreakpoint) {
			IScriptLineBreakpoint lineBreakpoint = (IScriptLineBreakpoint) breakpoint;

			// Line number
			int lineNumber = lineBreakpoint.getLineNumber();
			createLabel(parent, "Line Number:");
			createLabel(parent, Integer.toString(lineNumber));
		}

		// IScriptWatchPoint
		if (breakpoint instanceof IScriptWatchPoint) {
			IScriptWatchPoint watchPoint = (IScriptWatchPoint) breakpoint;
			createLabel(parent, "Watch field:");
			createLabel(parent, watchPoint.getFieldName());
		}

		// Other types... IScriptExceptionBreakpoint for example

	}

	// Changable breakpoint information
	protected void createEnabledButton(Composite parent) throws CoreException {
		enabledBreakpointButton = createCheckButton(parent, "&Enabled");
		enabledBreakpointButton.setSelection(getBreakpoint().isEnabled());
	}

	protected void createHitCountEditor(Composite parent) {
		Composite hitCountComposite = createComposite(parent, 4);

		// Hit count checking
		hitCountCheckingButton = createCheckButton(hitCountComposite,
				"Break when hit count");

		hitCountCheckingButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				updateControlsState();
			}
		});

		hitConditionCombo = new Combo(hitCountComposite, SWT.DROP_DOWN
				| SWT.READ_ONLY);

		// Hit condition
		hitConditionCombo.add("greater (>=)",
				IScriptBreakpoint.HIT_CONDITION_GREATER);

		hitConditionCombo.add("equal (==)",
				IScriptBreakpoint.HIT_CONDITION_EQUAL);

		hitConditionCombo.add("multiple (%)",
				IScriptBreakpoint.HIT_CONDITION_MULTIPLE);

		hitConditionCombo.setData(new GridData());

		// Hit value
		hitValueText = new Text(hitCountComposite, SWT.BORDER);
		hitValueText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		createLabel(hitCountComposite, "hits");
	}

	protected void createExpressionEditor(Composite parent) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayout(new GridLayout(1, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		enableExpressionButton = new Button(group, SWT.CHECK);
		enableExpressionButton.setText("Enable Condition");
		enableExpressionButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				updateControlsState();
			}
		});

		expressionViewer = new ScriptSourceViewer(group, null, null, false,
				SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL, null);

		ScriptDebugManager manager = ScriptDebugManager.getInstance();
		String natureId = manager.getNatureByModel(getBreakpoint()
				.getModelIdentifier());

		IDLTKUILanguageToolkit toolkit = DLTKUILanguageManager
				.getLanguageToolkit(natureId);

		IDocument document = new Document();

		toolkit.getTextTools().setupDocumentPartitioner(document,
				toolkit.getPartitioningId());

		ScriptSourceViewerConfiguration config = toolkit
				.createSourceViwerConfiguration();

		expressionViewer.configure(config);
		expressionViewer.setEditable(true);
		expressionViewer.setDocument(document);

		expressionViewer.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));

	}

	private void createTypeSpecificExtensions(Composite mainComposite) {
		IScriptBreakpoint breakpoint = getBreakpoint();

		if (breakpoint instanceof IScriptMethodEntryBreakpoint) {
			IScriptMethodEntryBreakpoint ee = (IScriptMethodEntryBreakpoint) breakpoint;
			Composite createComposite = createComposite(mainComposite, 4);
			method_entry = createCheckButton(createComposite,
					"Suspend on Method entry");
			method_exit = createCheckButton(createComposite,
					"Suspend on Method exit");
			method_entry.setSelection(ee.breakOnEntry());
			method_exit.setSelection(ee.breakOnExit());
		}

		if (breakpoint instanceof IScriptWatchPoint) {
			IScriptWatchPoint ee = (IScriptWatchPoint) breakpoint;
			Composite createComposite = createComposite(mainComposite, 4);
			method_entry = createCheckButton(createComposite,
					"Suspend on Access");
			method_exit = createCheckButton(createComposite,
					"Suspend on Modification");
			try {
				method_entry.setSelection(ee.isAccess());
				method_exit.setSelection(ee.isModification());
			} catch (CoreException e) {
				DebugPlugin.log(e);
			}
		}
	}

	protected IScriptBreakpoint getBreakpoint() {
		return (IScriptBreakpoint) getElement();
	}

	protected Control createContents(Composite parent) {
		noDefaultAndApplyButton();
		Composite composite = createComposite(parent, 1);

		try {
			createLabels(composite);
			createEnabledButton(composite);
			createHitCountEditor(composite);
			createExpressionEditor(composite);
			createTypeSpecificExtensions(composite);
		} catch (CoreException e) {

		}

		setValid(true);

		updateControlsState();

		return composite;
	}

	protected void updateControlsState() {
		// Hit count editor

		boolean hitChecking = hitCountCheckingButton.getSelection();
		hitConditionCombo.setEnabled(hitChecking);
		hitValueText.setEnabled(hitChecking);

		boolean expressionEnabled = enableExpressionButton.getSelection();
		Control control = expressionViewer.getControl();

		control.setEnabled(expressionEnabled);
	}

	protected void loadValues() throws CoreException {
		IScriptBreakpoint breakpoint = getBreakpoint();

		// breakpoint.isEnabled();

		final int hitValue = breakpoint.getHitValue();
		if (hitValue != -1) {
			setHitValue(hitValue);
			setHitCondition(breakpoint.getHitCondition());
		} else {

		}

		setExpressionState(breakpoint.getExpressionState());
		setExpression(breakpoint.getExpression());
	}

	protected void saveValues() throws CoreException {
		IScriptBreakpoint breakpoint = getBreakpoint();

		breakpoint.setEnabled(getBreakointEnableState());

		breakpoint.setExpression(getExpression());
		breakpoint.setExpressionState(getExpressionState());

		breakpoint.setHitValue(getHitValue());
		breakpoint.setHitCondition(getHitCondition());
	}

	public boolean performOk() {
		try {
			ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
				public void run(IProgressMonitor monitor) throws CoreException {
					saveValues();
					DebugPlugin.getDefault().getBreakpointManager()
							.fireBreakpointChanged(getBreakpoint());
				}
			}, null, 0, null);

		} catch (CoreException e) {
			// TODO: log exception
		}

		return super.performOk();
	}
}
