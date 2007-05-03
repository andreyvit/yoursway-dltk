/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.ui;

import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.dltk.debug.core.model.IScriptBreakpoint;
import org.eclipse.dltk.debug.core.model.IScriptLineBreakpoint;
import org.eclipse.dltk.debug.core.model.IScriptMethodEntryBreakpoint;
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

	private Button enabledButton;

	private Button hitCountButton;

	private Text hitCountText;

	private Combo hitConditionCombo;

	private Text conditionExpressionText;

	private Button enableConditionExpression;

	private Button method_entry;

	private Button method_exit;

	protected Composite createComposite(Composite parent, int numColumns) {
		Composite composit = new Composite(parent, SWT.NONE);
		composit.setFont(parent.getFont());
		GridLayout layout = new GridLayout();
		layout.numColumns = numColumns;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
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

	protected void createLabels(Composite parent) throws CoreException {
		IScriptBreakpoint breakpoint = getBreakpoint();

		Composite labelComposite = createComposite(parent, 2);

		// Resource name
		String resourceName = breakpoint.getResourceName();
		if (resourceName != null) {
			// File
			createLabel(labelComposite, "File:");
			createLabel(labelComposite, resourceName);

			// Hit count
			int hitCount = breakpoint.getHitCount();
			createLabel(labelComposite, "Hit count:");			
			createLabel(labelComposite, hitCount == -1 ? "Not available"
					: Integer.toString(hitCount));

		}

		createTypeSpecificLabels(labelComposite);
	}

	protected void createTypeSpecificLabels(Composite parent)
			throws CoreException {
		IScriptBreakpoint breakpoint = getBreakpoint();

		// IScriptLineBreakpoint
		if (breakpoint instanceof IScriptLineBreakpoint) {
			IScriptLineBreakpoint b = (IScriptLineBreakpoint) breakpoint;

			// Line number
			int lineNumber = b.getLineNumber();
			createLabel(parent, "Line Number: ");
			createLabel(parent, Integer.toString(lineNumber));
		}
	}

	protected void createEnabledButton(Composite parent) throws CoreException {
		enabledButton = createCheckButton(parent, "&Enabled");
		enabledButton.setSelection(getBreakpoint().isEnabled());
	}

	protected void createHitCountEditor(Composite parent) {
		IScriptBreakpoint breakpoint = getBreakpoint();
		int hitCount = breakpoint.getHitValue();
		
		// Hit composite
		Composite hitComposite = createComposite(parent, 1);
		hitCountButton = createCheckButton(hitComposite, "Enable hit checking");
		
		// Hit count
		Composite hitCountComposite = createComposite(hitComposite, 2);
		createLabel(hitCountComposite, "Hit count:");
		hitCountText = new Text(hitCountComposite, SWT.BORDER);
		hitCountText.setLayoutData(new GridData());
		if (hitCount<=0)hitCountButton.setSelection(false);
		else hitCountButton.setSelection(true);
		
		if (hitCount > 0) {
			hitCountText.setText(Integer.toString(hitCount));
		}
		

		// Hit condition
		Composite hitConditionComposite = createComposite(hitComposite, 2);

		createLabel(hitConditionComposite, "Hit condition:");
		hitConditionCombo = new Combo(hitConditionComposite, SWT.DROP_DOWN
				| SWT.READ_ONLY);

		hitConditionCombo.add("greater",
				IScriptBreakpoint.HIT_CONDITION_GREATER);

		hitConditionCombo.add("equal", IScriptBreakpoint.HIT_CONDITION_EQUAL);

		hitConditionCombo.add("multiple",
				IScriptBreakpoint.HIT_CONDITION_MULTIPLE);
		boolean enabled = hitCountButton.getSelection();
		hitConditionCombo.setEnabled(enabled);
		hitCountText.setEnabled(enabled);
		hitCountButton.addSelectionListener(new SelectionAdapter(){

			public void widgetSelected(SelectionEvent e) {
				boolean enabled = hitCountButton.getSelection();
				hitConditionCombo.setEnabled(enabled);
				hitCountText.setEnabled(enabled);
			}
			
		});
		int hitCondition = breakpoint.getHitCondition();

		if (hitCondition > 0) {
			hitConditionCombo.select(hitCondition);
		} else {
			hitConditionCombo.select(IScriptBreakpoint.HIT_CONDITION_GREATER);
		}
	}

	protected Control createContents(Composite parent) {
		noDefaultAndApplyButton();
		Composite mainComposite = createComposite(parent, 1);

		try {
			createLabels(mainComposite);
			createEnabledButton(mainComposite);
			createHitCountEditor(mainComposite);
			createConditionExpression(mainComposite);
			createTypeSpecificExtensions(mainComposite);
		} catch (CoreException e) {

		}

		setValid(true);
		return mainComposite;
	}

	private void createTypeSpecificExtensions(Composite mainComposite) {
		IScriptBreakpoint breakpoint = getBreakpoint();
		if (breakpoint instanceof IScriptMethodEntryBreakpoint){
			IScriptMethodEntryBreakpoint ee=(IScriptMethodEntryBreakpoint) breakpoint;
			Composite createComposite = createComposite(mainComposite, 4);
			method_entry = createCheckButton(createComposite,"Suspend on Method entry");
			method_exit = createCheckButton(createComposite,"Suspend on Method exit");
			method_entry.setSelection(ee.shouldBreakOnEntry());
			method_exit.setSelection(ee.shouldBreakOnExit());
		}
		
	}

	private void createConditionExpression(Composite mainComposite) {
		Group gr=new Group(mainComposite,SWT.NONE);
		gr.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		gr.setLayout(new GridLayout(1,false));
		enableConditionExpression = new Button(gr,SWT.CHECK);
		enableConditionExpression.setText("Enable Condition");
		conditionExpressionText = new Text(gr,SWT.WRAP|SWT.MULTI|SWT.BORDER);
		conditionExpressionText.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		IScriptBreakpoint breakpoint = getBreakpoint();
		boolean conditionalExpressionEnabled = breakpoint.isConditionalExpressionEnabled();
		enableConditionExpression.setSelection(conditionalExpressionEnabled);
		conditionExpressionText.setEnabled(conditionalExpressionEnabled);
		String conditionalExpression = breakpoint.getConditionalExpression();
		enableConditionExpression.addSelectionListener(new SelectionAdapter(){

			public void widgetSelected(SelectionEvent e) {
				conditionExpressionText.setEnabled(enableConditionExpression.getSelection());
			}
			
		});
		if (conditionalExpression!=null)
		{		
		conditionExpressionText.setText(conditionalExpression);
		}
	}

	protected IScriptBreakpoint getBreakpoint() {
		return (IScriptBreakpoint) getElement();
	}

	private void storeEnabled(IScriptBreakpoint breakpoint)
			throws CoreException {
		boolean enabled = enabledButton.getSelection();
		breakpoint.setEnabled(enabled);
	}

	private void storeHits(IScriptBreakpoint breakpoint)
			throws CoreException {
		
		if (hitCountButton.getSelection()){
			try{
			int hitCount = Integer.parseInt(hitCountText.getText());
			breakpoint.setHitValue(hitCount);
			
			int hitCondition = hitConditionCombo.getSelectionIndex();			
			breakpoint.setHitCondition(hitCondition);
			}catch (NumberFormatException e) {
				breakpoint.setHitValue(-1);
				breakpoint.setHitCondition(-1);
			}
		} else {
			breakpoint.setHitValue(-1);
			breakpoint.setHitCondition(-1);
		}
	}

	public boolean performOk() {
		try {
			ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
				public void run(IProgressMonitor monitor) throws CoreException {
					IScriptBreakpoint breakpoint = getBreakpoint();

					storeEnabled(breakpoint);
					storeHits(breakpoint);			
					storeConditions(breakpoint);
					storeEntryValues(breakpoint);
					DebugPlugin.getDefault().getBreakpointManager()
							.fireBreakpointChanged(breakpoint);
				}

				private void storeEntryValues(IScriptBreakpoint breakpoint) {
					if (breakpoint instanceof IScriptMethodEntryBreakpoint)
					{
					IScriptMethodEntryBreakpoint ee=(IScriptMethodEntryBreakpoint) breakpoint;
					if (method_entry!=null){
						boolean sel=method_entry.getSelection();
						try {
							ee.setBreakOnEntry(sel);
						} catch (CoreException e) {
							DLTKDebugUIPlugin.log(e);
						}
					}
					if (method_exit!=null){
						boolean sel=method_exit.getSelection();
						try {
							ee.setBreakOnExit(sel);
						} catch (CoreException e) {
							DLTKDebugUIPlugin.log(e);
						}
					}
					
					}
					
				}
			}, null, 0, null);

		} catch (CoreException e) {
			//TODO: log exception
		}

		return super.performOk();
	}

	protected void storeConditions(IScriptBreakpoint breakpoint) {
		try {
			boolean selection = this.enableConditionExpression.getSelection();
			breakpoint.setConditionalExpressionEnabled(selection);
			breakpoint.setConditionalExpression(this.conditionExpressionText.getText());
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	public boolean performCancel() {
		return super.performCancel();
	}
}
