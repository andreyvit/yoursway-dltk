package org.eclipse.dltk.debug.ui.launchConfigurations;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.dltk.debug.ui.messages.DLTKLaunchConfigurationsMessages;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;
import org.eclipse.dltk.ui.preferences.FieldValidators;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

public abstract class RemoteLaunchConfigurationTab extends
		ScriptLaunchConfigurationTab {

	private static int DEFAULT_PORT = 10000;
	private static String DEFAULT_IDEKEY = "idekey";

	private Text port;
	private Text ideKey;

	/*
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
	 */
	public String getName() {
		return DLTKLaunchConfigurationsMessages.remoteTab_title;
	}

	/*
	 * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#getImage()
	 */
	public Image getImage() {
		return DebugUITools.getImage(IDebugUIConstants.IMG_LCL_DISCONNECT);
	}

	/*
	 * @see org.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#doInitializeForm(org.eclipse.debug.core.ILaunchConfiguration)
	 */
	protected void doInitializeForm(ILaunchConfiguration config) {
		String text = null;
		try {
			text = config.getAttribute(
					ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_PORT,
					Integer.toString(DEFAULT_PORT));
		} catch (CoreException e) {
			// log?
			text = Integer.toString(DEFAULT_PORT);
		}

		port.setText(text);

		try {
			text = config
					.getAttribute(
							ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_SESSION_ID,
							DEFAULT_IDEKEY);
		} catch (CoreException e) {
			// log?
			text = DEFAULT_IDEKEY;
		}

		ideKey.setText(text);
	}

	/*
	 * @see org.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#doPerformApply(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
	 */
	protected void doPerformApply(ILaunchConfigurationWorkingCopy config) {
		config.setAttribute(
				ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_PORT, port
						.getText().trim());
		config.setAttribute(
				ScriptLaunchConfigurationConstants.ATTR_DLTK_DBGP_SESSION_ID,
				ideKey.getText().trim());
	}

	/*
	 * @see org.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#doCanSave()
	 */
	protected boolean doCanSave() {
		return validatePort() && validateIdeKey();
	}

	
	protected boolean validatePort() {
		IStatus result = FieldValidators.PORT_VALIDATOR
				.validate(port.getText());

		if (!result.isOK()) {
			setErrorMessage(result.getMessage());
			return false;
		}

		return true;
	}

	protected boolean validateIdeKey() {
		String projectName = ideKey.getText().trim();
		if (projectName.length() == 0) {
			setErrorMessage(DLTKLaunchConfigurationsMessages.remoteError_ideKeyEmpty);
			return false;
		}

		return true;
	}

	/*
	 * @see org.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#guessProjectName()
	 */
	protected String guessProjectName() {
		String[] guesses = getProjectAndScriptNames();

		return (guesses == null) ? EMPTY_STRING : guesses[0];
	}

	/*
	 * @see org.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#doCreateControl(org.eclipse.swt.widgets.Composite)
	 */
	protected void doCreateControl(Composite composite) {
		Group group = SWTFactory
				.createGroup(
						composite,
						DLTKLaunchConfigurationsMessages.remoteTab_connectionProperties,
						2, 1, GridData.FILL_HORIZONTAL);

		SWTFactory.createLabel(group,
				DLTKLaunchConfigurationsMessages.remoteTab_connectionPort, 1);
		port = SWTFactory.createText(group, SWT.BORDER, 1, EMPTY_STRING);
		port.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateLaunchConfigurationDialog();
			}
		});

		SWTFactory.createLabel(group,
				DLTKLaunchConfigurationsMessages.remoteTab_connectionIdeKey, 1);
		ideKey = SWTFactory.createText(group, SWT.BORDER, 1, EMPTY_STRING);
		ideKey.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateLaunchConfigurationDialog();
			}
		});
	}
}
