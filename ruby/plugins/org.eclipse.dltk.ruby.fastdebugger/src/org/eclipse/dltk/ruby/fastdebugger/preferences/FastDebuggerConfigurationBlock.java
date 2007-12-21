package org.eclipse.dltk.ruby.fastdebugger.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.ruby.fastdebugger.FastDebuggerPlugin;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.FieldValidators;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.dltk.ui.util.SWTFactory;
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
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class FastDebuggerConfigurationBlock extends AbstractOptionsBlock {

	private static PreferenceKey ENABLE_LOGGING = new PreferenceKey(
			FastDebuggerPlugin.PLUGIN_ID,
			FastDebuggerPreferenceConstants.ENABLE_LOGGING);

	private static PreferenceKey LOG_FILE_PATH = new PreferenceKey(
			FastDebuggerPlugin.PLUGIN_ID,
			FastDebuggerPreferenceConstants.LOG_FILE_PATH);

	private static PreferenceKey LOG_FILE_NAME = new PreferenceKey(
			FastDebuggerPlugin.PLUGIN_ID,
			FastDebuggerPreferenceConstants.LOG_FILE_NAME);

	private Button enableLogging;
	private Text logFileName;
	private Text logFilePath;

	// ~ Constructors

	public FastDebuggerConfigurationBlock(IStatusChangeListener context,
			IProject project, IWorkbenchPreferenceContainer container) {
		super(context, project, getKeys(), container);
	}

	// ~ Methods

	protected void createLogSection(final Composite parent) {
		final Group group = SWTFactory.createGroup(parent,
				FastDebuggerPreferenceMessages.LoggingGroupLabel, 3, 1,
				GridData.FILL_HORIZONTAL);

		enableLogging = SWTFactory.createCheckButton(group,
				FastDebuggerPreferenceMessages.EnableLoggingLabel, null, false,
				1);
		SWTFactory.createHorizontalSpacer(group, 2);

		SWTFactory.createLabel(group,
				FastDebuggerPreferenceMessages.LogNameLabel, 1);
		logFileName = SWTFactory.createText(group, SWT.BORDER, 1, "");
		SWTFactory.createHorizontalSpacer(group, 1);

		SWTFactory.createHorizontalSpacer(group, 1);
		SWTFactory.createLabel(group,
				FastDebuggerPreferenceMessages.LogNameFormatLabel, 5, 1);
		SWTFactory.createHorizontalSpacer(group, 1);

		SWTFactory.createLabel(group,
				FastDebuggerPreferenceMessages.LogFolderLabel, 1);
		logFilePath = SWTFactory.createText(group, SWT.BORDER, 1, "");
		logFilePath.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button browseButton = SWTFactory.createPushButton(group,
				FastDebuggerPreferenceMessages.BrowseButton, null);
		browseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(parent.getShell(),
						SWT.OPEN);
				String dir = dialog.open();
				if (dir != null) {
					logFilePath.setText(dir);
				}
			}
		});

		Control[] slaves = { logFileName, logFilePath, browseButton };

		bindControl(enableLogging, ENABLE_LOGGING, slaves);
		bindControl(logFileName, LOG_FILE_NAME,
				FieldValidators.FILE_NAME_VALIDATOR);
		bindControl(logFilePath, LOG_FILE_PATH, FieldValidators.DIR_VALIDATOR);
	}

	protected Control createOptionsBlock(Composite parent) {
		Composite composite = SWTFactory.createComposite(parent, parent
				.getFont(), 1, 1, GridData.FILL);
		createLogSection(composite);
		return composite;
	}

	private static PreferenceKey[] getKeys() {
		return new PreferenceKey[] { ENABLE_LOGGING, LOG_FILE_PATH,
				LOG_FILE_NAME };
	}
}
