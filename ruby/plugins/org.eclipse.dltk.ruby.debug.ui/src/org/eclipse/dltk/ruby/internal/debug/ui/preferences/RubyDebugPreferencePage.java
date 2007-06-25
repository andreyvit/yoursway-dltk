package org.eclipse.dltk.ruby.internal.debug.ui.preferences;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.dltk.launching.debug.DebuggingEngineManager;
import org.eclipse.dltk.launching.debug.IDebuggingEngine;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.internal.launching.debug.RubyDebuggingConstants;
import org.eclipse.dltk.ruby.launching.RubyLaunchingPlugin;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class RubyDebugPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	private ComboViewer allEngines;
	private Label description;

	protected IDebuggingEngine getSelectedDebuggineEngine() {
		IStructuredSelection selection = (IStructuredSelection) allEngines
				.getSelection();
		if (selection != null) {
			return (IDebuggingEngine) selection.getFirstElement();
		}

		return null;
	}

	protected void setSelectedDebuggingEngine(IDebuggingEngine engine) {
		if (engine != null) {
			allEngines.setSelection(new StructuredSelection(engine));
		}
	}

	protected void updateDescription(String text) {
		description.setText(PreferenceMessages.DescriptionLabel + " " + text);
	}

	protected void createEngineSelector(Composite parent, Object data) {
		Group group = new Group(parent, SWT.NONE);
		group.setFont(parent.getFont());
		group.setLayoutData(data);
		group.setText(PreferenceMessages.DebuggingEngine);

		GridLayout layout = new GridLayout(1, false);
		group.setLayout(layout);

		// Name
		Label nameLabel = new Label(group, SWT.NONE);
		nameLabel.setText(PreferenceMessages.NameLabel);

		allEngines = new ComboViewer(group);
		allEngines.getCombo().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));
		allEngines.setLabelProvider(new LabelProvider() {
			public String getText(Object element) {
				return ((IDebuggingEngine) element).getName();
			}
		});

		allEngines.add(DebuggingEngineManager.getInstance()
				.getDebuggingEngines(RubyNature.NATURE_ID));

		allEngines.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				updateDescription(getSelectedDebuggineEngine().getDescription());
			}
		});

		// Description
		this.description = new Label(group, SWT.NONE);
		this.description.setText("");
		this.description.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true));
	}

	protected GridData makeGridData() {
		GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = GridData.FILL;
		return data;
	}

	protected Control createContents(Composite parent) {
		Composite top = new Composite(parent, SWT.NONE);

		GridLayout layout = new GridLayout(1, false);
		top.setLayout(layout);

		createEngineSelector(top, makeGridData());

		initializeValues();

		return top;
	}

	protected void initializeValues() {
		DebuggingEngineManager manager = DebuggingEngineManager.getInstance();

		Preferences prefs = RubyLaunchingPlugin.getDefault()
				.getPluginPreferences();

		String engineId = prefs
				.getString(RubyDebuggingConstants.DEBUGGING_ENGINE_ID);

		if (engineId.equals("")) {
			// Engine not selected (for example, first launch)
			IDebuggingEngine engine = manager
					.getSelectedDebuggineEngine(RubyNature.NATURE_ID);
			if (engine != null) {
				engineId = engine.getId();
			}
		}

		if (!engineId.equals("")) {
			IDebuggingEngine engine = manager.getDebuggingEngine(
					RubyNature.NATURE_ID, engineId);

			setSelectedDebuggingEngine(engine);
		}
	}

	public void init(IWorkbench workbench) {

	}

	public boolean performOk() {
		Preferences prefs = RubyLaunchingPlugin.getDefault()
				.getPluginPreferences();

		IDebuggingEngine engine = getSelectedDebuggineEngine();

		if (engine != null) {
			prefs.setValue(RubyDebuggingConstants.DEBUGGING_ENGINE_ID, engine
					.getId());
		}

		RubyLaunchingPlugin.getDefault().savePluginPreferences();

		return true;
	}
}