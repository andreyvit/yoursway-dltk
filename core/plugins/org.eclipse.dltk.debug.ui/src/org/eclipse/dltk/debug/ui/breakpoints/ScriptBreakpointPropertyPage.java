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
import org.eclipse.dltk.debug.core.model.IScriptBreakpoint;
import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
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

	// Simple access methods
	protected boolean getBreakointEnableState() {
		return enabledBreakpointButton.getSelection();
	}

	protected boolean getEnabledHitChecking() {
		return hitCountCheckingButton.getSelection();
	}

	protected void setEnabledHitChecking(boolean state) {
		hitCountCheckingButton.setSelection(state);
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

	// Expression
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

		// Script language
		createLabel(labelComposite, BreakpointMessages.LanguageLabel);
		createLabel(labelComposite, BreakpointUtils.getLanguageToolkit(
				breakpoint).getLanguageName());

		// Resource name
		String resourceName = breakpoint.getResourceName();
		if (resourceName != null) {
			createLabel(labelComposite, BreakpointMessages.FileLabel);
			createLabel(labelComposite, resourceName);
		}

		// Hit count
		createLabel(labelComposite, BreakpointMessages.HitCountLabel);
		int hitCount = breakpoint.getHitCount();
		createLabel(labelComposite,
				hitCount == -1 ? BreakpointMessages.HitCountNotAvailableMessage
						: Integer.toString(hitCount));
		// from debugging engine

		createTypeSpecificLabels(labelComposite);
	}

	protected void createTypeSpecificLabels(Composite parent)
			throws CoreException {
		// Nothing to do here
	}

	// Breakpoint information
	protected void createButtons(Composite parent) throws CoreException {
		Composite buttonsComposite = createComposite(parent, 1);

		enabledBreakpointButton = createCheckButton(buttonsComposite,
				BreakpointMessages.EnabledLabel);

		createTypeSpecificButtons(buttonsComposite);
	}

	protected void createTypeSpecificButtons(Composite parent) {

	}

	protected void createHitCountEditor(Composite parent) {
		Composite hitCountComposite = createComposite(parent, 4);

		// Hit count checking
		hitCountCheckingButton = createCheckButton(hitCountComposite,
				BreakpointMessages.BreakWhenHitCountLabel);

		hitCountCheckingButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				updateControlsState();
			}
		});

		hitConditionCombo = new Combo(hitCountComposite, SWT.READ_ONLY);

		// Hit condition
		hitConditionCombo.add(BreakpointMessages.HitConditionGreaterOrEqual,
				IScriptBreakpoint.HIT_CONDITION_GREATER_OR_EQUAL);

		hitConditionCombo.add(BreakpointMessages.HitConditionEqual,
				IScriptBreakpoint.HIT_CONDITION_EQUAL);

		hitConditionCombo.add(BreakpointMessages.HitConditionMultiple,
				IScriptBreakpoint.HIT_CONDITION_MULTIPLE);

		hitConditionCombo
				.select(IScriptBreakpoint.HIT_CONDITION_GREATER_OR_EQUAL);

		hitConditionCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				updateControlsState();
			}
		});

		hitConditionCombo.setData(new GridData());

		// Hit value
		hitValueText = new Text(hitCountComposite, SWT.BORDER);
		hitValueText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		hitValueText.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				updateControlsState();
			}
		});

		createLabel(hitCountComposite, BreakpointMessages.HitsLabel);
	}

	protected void createExpressionEditor(Composite parent) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayout(new GridLayout(1, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		enableExpressionButton = new Button(group, SWT.CHECK);
		enableExpressionButton.setText(BreakpointMessages.UseConditionLabel);
		enableExpressionButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				updateControlsState();
			}
		});

		expressionViewer = new ScriptSourceViewer(group, null, null, false,
				SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL, null);

		IDLTKUILanguageToolkit toolkit = BreakpointUtils
				.getUILanguageToolkit(getBreakpoint());

		IDocument document = new Document();

		toolkit.getTextTools().setupDocumentPartitioner(document,
				toolkit.getPartitioningId());

		ScriptSourceViewerConfiguration config = toolkit
				.createSourceViwerConfiguration();

		expressionViewer.configure(config);
		expressionViewer.setDocument(document);

		expressionViewer.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));

	}

	protected IScriptBreakpoint getBreakpoint() {
		return (IScriptBreakpoint) getElement();
	}

	protected Control createContents(Composite parent) {
		noDefaultAndApplyButton();
		Composite composite = createComposite(parent, 1);

		try {
			createLabels(composite);
			createButtons(composite);

			if (hasHitCountEditor()) {
				createHitCountEditor(composite);
			}

			if (hasExpressionEditor()) {
				createExpressionEditor(composite);
			}

			loadValues();
			updateControlsState();
		} catch (CoreException e) {
			// TODO: log exception
		}

		return composite;
	}

	protected boolean hasHitCountEditor() {
		return true;
	}

	protected boolean hasExpressionEditor() {
		return true;
	}

	protected void loadValues() throws CoreException {
		IScriptBreakpoint breakpoint = getBreakpoint();

		// Enabled
		enabledBreakpointButton.setSelection(breakpoint.isEnabled());

		// Hit conditions
		if (hasHitCountEditor()) {

			final int hitValue = breakpoint.getHitValue();
			if (hitValue != -1) {
				setHitValue(hitValue);
				setHitCondition(breakpoint.getHitCondition());
				setEnabledHitChecking(true);
			} else {
				setEnabledHitChecking(false);
			}
		}

		// Expression
		if (hasExpressionEditor()) {
			setExpressionState(breakpoint.getExpressionState());
			setExpression(breakpoint.getExpression());
		}
	}

	protected void saveValues() throws CoreException {
		IScriptBreakpoint breakpoint = getBreakpoint();

		breakpoint.setEnabled(getBreakointEnableState());

		if (hasHitCountEditor()) {
			breakpoint.setHitValue(getHitValue());
			breakpoint.setHitCondition(getHitCondition());
		}

		if (hasExpressionEditor()) {
			breakpoint.setExpression(getExpression());
			breakpoint.setExpressionState(getExpressionState());
		}
	}

	protected void updateControlsState() {
		// Hit count
		if (hasHitCountEditor()) {
			boolean hitChecking = hitCountCheckingButton.getSelection();
			hitConditionCombo.setEnabled(hitChecking);
			hitValueText.setEnabled(hitChecking);
		}

		// Expression
		if (hasExpressionEditor()) {
			boolean expressionEnabled = enableExpressionButton.getSelection();
			Control control = expressionViewer.getControl();
			control.setEnabled(expressionEnabled);
		}

		validateValues();
	}

	protected void validateValues() {
		boolean valid = true;
		String errorMessage = null;

		if (hasHitCountEditor()) {
			if (getEnabledHitChecking()) {
				try {
					getHitValue();
				} catch (NumberFormatException e) {
					valid = false;
					errorMessage = BreakpointMessages.InvalidNumberOfHits;
				}
			}
		}

		setValid(valid);
		setErrorMessage(errorMessage);
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
			DebugPlugin.log(e);
		}

		return super.performOk();
	}
}
