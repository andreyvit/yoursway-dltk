package org.eclipse.dltk.debug.ui;

import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.dltk.debug.core.model.IScriptBreakpoint;
import org.eclipse.dltk.debug.core.model.IScriptLineBreakpoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;

public class ScriptBreakpointPropertyPage extends PropertyPage {

	private Button enabledButton;

	private Button hitCountButton;

	private Text hitCountText;

	private Combo hitConditionCombo;

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

		// Hit composite
		Composite hitComposite = createComposite(parent, 1);
		hitCountButton = createCheckButton(hitComposite, "Enable hit checking");

		// Hit count
		Composite hitCountComposite = createComposite(hitComposite, 2);
		createLabel(hitCountComposite, "Hit count:");
		hitCountText = new Text(hitCountComposite, SWT.BORDER);
		hitCountText.setLayoutData(new GridData());

		int hitCount = breakpoint.getHitValue();

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
		} catch (CoreException e) {

		}

		setValid(true);
		return mainComposite;
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
		
		if (false){
			int hitCount = Integer.parseInt(hitCountText.getText());
			breakpoint.setHitValue(hitCount);
			
			int hitCondition = hitConditionCombo.getSelectionIndex();			
			breakpoint.setHitCondition(hitCondition);
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
					
					DebugPlugin.getDefault().getBreakpointManager()
							.fireBreakpointChanged(breakpoint);
				}
			}, null, 0, null);

		} catch (CoreException e) {
			//TODO: log exception
		}

		return super.performOk();
	}

	public boolean performCancel() {
		return super.performCancel();
	}
}
