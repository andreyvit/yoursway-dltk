package org.eclipse.dltk.ruby.internal.debug.ui.preferences;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.dltk.launching.debug.DebuggingEngineManager;
import org.eclipse.dltk.launching.debug.IDebuggingEngine;
import org.eclipse.dltk.ruby.core.RubyNature;
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
	
	protected void updateDescription(String text) {
		description.setText(text);
	}

	protected void createXXX(Composite parent, Object data) {
		Group group = new Group(parent, SWT.NONE);
		group.setFont(parent.getFont());
		group.setText("Debugging Engine");
		group.setLayoutData(data);

		GridLayout layout = new GridLayout(2, false);
		group.setLayout(layout);

		// Name
		Label label = new Label(group, SWT.NONE);
		label.setText("Name:");

		allEngines = new ComboViewer(group);
		allEngines.setLabelProvider(new LabelProvider() {
			public String getText(Object element) {
				return ((IDebuggingEngine) element).getName();
			}
		});

		allEngines.add(DebuggingEngineManager.getInstance()
				.getDebuggingEngines(RubyNature.NATURE_ID));

		allEngines.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				updateDescription(getSelectedDebuggineEngine()
						.getDescription());
			}
		});

		// Description
		Label label2 = new Label(group, SWT.NONE);
		label2.setText("Description:");

		this.description = new Label(group, SWT.NONE);
		this.description.setText("dfdf");
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

		createXXX(top, makeGridData());
		
		initializeValues();

		return top;
	}

	protected void initializeValues() {
		Preferences prefs = RubyLaunchingPlugin.getDefault()
				.getPluginPreferences();
		IDebuggingEngine engine = DebuggingEngineManager.getInstance()
				.getDebuggingEngine(RubyNature.NATURE_ID,
						prefs.getString("engine"));

		allEngines.setSelection(new StructuredSelection(engine));
	}

	public void init(IWorkbench workbench) {

	}

	public boolean performOk() {
		Preferences prefs = RubyLaunchingPlugin.getDefault()
				.getPluginPreferences();
		prefs.setValue("engine", getSelectedDebuggineEngine().getId());
		RubyLaunchingPlugin.getDefault().savePluginPreferences();
		return true;
	}
}