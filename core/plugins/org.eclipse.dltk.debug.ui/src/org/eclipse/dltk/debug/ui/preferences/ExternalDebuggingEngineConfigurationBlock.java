package org.eclipse.dltk.debug.ui.preferences;

import java.util.ArrayList;

import org.eclipse.dltk.ui.preferences.ImprovedAbstractConfigurationBlock;
import org.eclipse.dltk.ui.preferences.FieldValidators;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore.OverlayKey;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

public abstract class ExternalDebuggingEngineConfigurationBlock extends
		ImprovedAbstractConfigurationBlock {

	private Text enginePath;

	private OverlayKey[] createKeys() {
		ArrayList keys = new ArrayList();
		keys.add(new OverlayKey(OverlayPreferenceStore.STRING,
				getDebuggingEnginePathKey()));
		return (OverlayKey[]) keys
				.toArray(new OverlayPreferenceStore.OverlayKey[keys.size()]);
	}

	public ExternalDebuggingEngineConfigurationBlock(
			OverlayPreferenceStore store, PreferencePage preferencePage) {
		super(store, preferencePage);
		store.addKeys(createKeys());
	}

	protected void createEnginePath(final Composite parent) {
		// Group
		final Group group = SWTFactory.createGroup(parent,
				ScriptDebugPreferencesMessages.ExternalEngineGroup, 3, 1,
				GridData.FILL_HORIZONTAL);

		// Engine path
		SWTFactory.createLabel(group, ScriptDebugPreferencesMessages.PathLabel,
				1);
		enginePath = SWTFactory.createText(group, SWT.BORDER, 1, "");
		bindControl(enginePath, getDebuggingEnginePathKey(),
				FieldValidators.PATH_VALIDATOR);

		// Browse
		final Button button = SWTFactory.createPushButton(group,
				ScriptDebugPreferencesMessages.BrowseEnginePath, null);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(parent.getShell(), SWT.OPEN);
				String file = dialog.open();
				if (file != null) {
					enginePath.setText(file);
				}
			}
		});
	}

	public Control createControl(Composite parent) {
		final Composite composite = SWTFactory.createComposite(parent, parent
				.getFont(), 1, 1, GridData.FILL);
		createEnginePath(composite);
		return composite;
	}
	
	protected abstract String getDebuggingEnginePathKey();
}
