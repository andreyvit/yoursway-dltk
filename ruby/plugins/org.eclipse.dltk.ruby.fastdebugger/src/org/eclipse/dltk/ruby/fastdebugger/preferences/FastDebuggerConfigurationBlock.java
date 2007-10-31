package org.eclipse.dltk.ruby.fastdebugger.preferences;

import java.util.ArrayList;

import org.eclipse.dltk.ui.preferences.ImprovedAbstractConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore.OverlayKey;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

public class FastDebuggerConfigurationBlock extends
		ImprovedAbstractConfigurationBlock {
	
	private Button enableLogging;

	protected void createLogSection(Composite parent) {
		final Group group = SWTFactory.createGroup(parent, "Logging", 3, 1,
				GridData.FILL_HORIZONTAL);

		enableLogging = SWTFactory.createCheckButton(group, "Enable", null,
				false, 1);
		SWTFactory.createHorizontalSpacer(group, 2);

		SWTFactory.createLabel(group, "Log name:", 1);
		Text logFileName = SWTFactory.createText(group, SWT.BORDER, 1,
				"fastdebug_{0}.log");
		
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		logFileName.setLayoutData(gd);
		
		//SWTFactory.createHorizontalSpacer(group, 1);

		SWTFactory.createLabel(group, "Log folder:", 1);
		Text logFolderName = SWTFactory.createText(group, SWT.BORDER, 1, "");
		logFolderName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Button browseButton = SWTFactory.createPushButton(group, "Browse...",
				null);

		// Bindings
		bindControl(enableLogging,
				FastDebuggerPreferenceConstants.ENABLE_LOGGING);

		// Dependencies
		createDependency(enableLogging, logFileName);
		createDependency(enableLogging, logFolderName);
		createDependency(enableLogging, browseButton);
	}

	private OverlayKey[] createKeys() {
		ArrayList keys = new ArrayList();

		keys.add(new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.STRING,
				FastDebuggerPreferenceConstants.ENABLE_LOGGING));

		return (OverlayKey[]) keys
				.toArray(new OverlayPreferenceStore.OverlayKey[keys.size()]);
	}

	public FastDebuggerConfigurationBlock(OverlayPreferenceStore store,
			PreferencePage preferencePage) {
		super(store, preferencePage);

		store.addKeys(createKeys());
	}

	public Control createControl(Composite parent) {
		Composite composite = SWTFactory.createComposite(parent, parent
				.getFont(), 1, 1, GridData.FILL);
		createLogSection(composite);
		return composite;
	}
}
