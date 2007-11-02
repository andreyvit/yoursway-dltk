package org.eclipse.dltk.ruby.fastdebugger.preferences;

import java.util.ArrayList;

import org.eclipse.dltk.ruby.fastdebugger.FastDebuggerPlugin;
import org.eclipse.dltk.ui.preferences.ImprovedAbstractConfigurationBlock;
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
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

public class FastDebuggerConfigurationBlock extends
		ImprovedAbstractConfigurationBlock {
	
	private Button enableLogging;
	private Text logFilePath;
	private Text logFileName;
	
	protected void createLogSection(final Composite parent) {
		final Group group = SWTFactory.createGroup(parent, "Logging", 3, 1,
				GridData.FILL_HORIZONTAL);

		enableLogging = SWTFactory.createCheckButton(group, "Enable", null,	false, 1);
		SWTFactory.createHorizontalSpacer(group, 2);

		SWTFactory.createLabel(group, "Log name:", 1);
		logFileName = SWTFactory.createText(group, SWT.BORDER, 1, "");
		
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		logFileName.setLayoutData(gd);
		
		SWTFactory.createLabel(group, "Log folder:", 1);
		logFilePath = SWTFactory.createText(group, SWT.BORDER, 1, "");
		logFilePath.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Button browseButton = SWTFactory.createPushButton(group, "Browse...",
				null);
		browseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(parent.getShell(), SWT.OPEN);
				String dir = dialog.open();
				if (dir != null) {
					logFilePath.setText(dir);
				}					
			}			
		});
		
		// Bindings
		bindControl(enableLogging, FastDebuggerPreferenceConstants.ENABLE_LOGGING);
		bindControl(logFilePath, FastDebuggerPreferenceConstants.LOG_FILE_PATH);
		bindControl(logFileName, FastDebuggerPreferenceConstants.LOG_FILE_NAME);
		
		// Dependencies
		createDependency(enableLogging, logFileName);
		createDependency(enableLogging, logFilePath);
		createDependency(enableLogging, browseButton);
	}

	private OverlayKey[] createKeys() {
		ArrayList keys = new ArrayList();

		keys.add(new OverlayKey(
				OverlayPreferenceStore.STRING,
				FastDebuggerPreferenceConstants.ENABLE_LOGGING));
		
		keys.add(new OverlayKey(
				OverlayPreferenceStore.STRING,
				FastDebuggerPreferenceConstants.LOG_FILE_PATH));
		
		keys.add(new OverlayKey(
				OverlayPreferenceStore.STRING,
				FastDebuggerPreferenceConstants.LOG_FILE_NAME));
		
		return (OverlayKey[]) keys
				.toArray(new OverlayPreferenceStore.OverlayKey[keys.size()]);
	}

	public void performDefaults() {
		enableLogging.setEnabled(true);
		logFileName.setText("fastdebug_{0}.log");
		logFilePath.setText(FastDebuggerPlugin.getDefault().getStateLocation().toOSString());
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
