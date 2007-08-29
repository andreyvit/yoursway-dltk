package org.eclipse.dltk.installer;

import java.io.File;
import java.io.IOException;

import org.eclipse.epp.installer.archive.Archives;
import org.eclipse.epp.installer.archive.IArchive;
import org.eclipse.epp.installer.archive.IArchiveEntry;
import org.eclipse.epp.installer.core.CheckAdminPrivilegesStep;
import org.eclipse.epp.installer.core.IConfigurationProvider;
import org.eclipse.epp.installer.core.InstallOptions;
import org.eclipse.epp.installer.core.model.Context;
import org.eclipse.epp.installer.core.model.Installer;
import org.eclipse.epp.installer.core.operations.ExtractDirectoryOperation;
import org.eclipse.epp.installer.core.steps.ChoiceStep;
import org.eclipse.epp.installer.core.steps.ChooseLocationStep;
import org.eclipse.epp.installer.core.steps.PromptUserStep;
import org.eclipse.epp.installer.core.steps.RunOperationsStep;
import org.eclipse.epp.installer.core.steps.ScrollablePromptUserStep;

public class EuropaInstallConfiguration implements IConfigurationProvider {

	public void configure(Installer installer) {
		final InstallOptions options = installer.getOptions();

		installer.setTitle(options
				.getString(InstallOptions.OPTION_PRODUCT_NAME)
				+ " Installer");
		
		// Welcome step
		ScrollablePromptUserStep welcomeStep = new CheckAdminPrivilegesStep(
				installer);
		welcomeStep.setTitle("Welcome to the Eclipse Europa installer");
		welcomeStep.setText("This is an example installer, which will install one of EPP packages on your platform");
		installer.add(welcomeStep);

		ChoiceStep licenseStep = new ChoiceStep(installer);
		licenseStep.setTitle("License Agreement");
		licenseStep
				.setDescription("Please read the following important information before continue.");
		licenseStep.setChoiceText(options
				.getString(InstallOptions.OPTION_LICENSE_AGREEMENT));
		licenseStep.setAcceptText("I accept the agreement");
		licenseStep.setDeclineText("I do not accept the agreement");
		licenseStep.setContinueOnDecline(false);
		licenseStep.setDefaultChoice(false);
		installer.add(licenseStep);

		ChooseLocationStep installDirStep = new ChooseLocationStep(installer);
		installDirStep.setDescription("Where should the "
				+ options.getString(InstallOptions.OPTION_PRODUCT_NAME)
				+ " be installed?");
		installDirStep.setTitle("Select Destination Location");
		installer.add(installDirStep);

		final PromptUserStep verifyStep = new PromptUserStep(installer) {
			public void aboutToStep() {
				setText("Click Next to continue with the installation, or click Back if you want to review or change any settings.\n\n"
						+ "Product:\n   "
						+ options.getString(InstallOptions.OPTION_PRODUCT_NAME)
						+ "\n\nLocation:\n   "
						+ options.getString(InstallOptions.OPTION_INSTALL_DIR));
			}
		};
		verifyStep.setTitle("Ready to Install");
		verifyStep.setDescription("Setup is now ready to begin installing "
				+ options.getString(InstallOptions.OPTION_PRODUCT_NAME)
				+ " on your computer.");
		installer.add(verifyStep);

		final RunOperationsStep installStep = new RunOperationsStep(installer) {
			public void aboutToStep() {
				try {
					createInstallOperations(this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		installStep.setDescription("Installing...");
		installer.add(installStep);

		final PromptUserStep resultStep = new PromptUserStep(installer) {
			public void aboutToStep() {
				setText("Thank you very much for choosing "
						+ options.getString(InstallOptions.OPTION_PRODUCT_NAME)
						+ ".\n\nClick Finish to exit setup.");
			}
		};
		resultStep.setTitle("Installation complete");
		installer.add(resultStep);
	}

	/**
	 * Create the install operations based upon the user's choices.
	 * 
	 * @param step
	 *            the step about to be performed
	 */
	protected void createInstallOperations(RunOperationsStep step)
			throws IOException {
		// Assemble install operations

		String archivePath = System.getProperty(Context.INSTALLER_JAR_PROPERTY);
		if (archivePath == null)
			throw new IOException("archive name unspecified, please set "
					+ Context.INSTALLER_JAR_PROPERTY + " property.");
		IArchive archive = Archives.createArchive(new File(archivePath));
		if(archive == null)
			throw new IOException("archive not found at: " + archivePath);
		IArchiveEntry entry = archive.getEntry("eclipse");

		File dir = new File(step.getOptions().getString(
				InstallOptions.OPTION_INSTALL_DIR));

		step.add(new ExtractDirectoryOperation(archive, entry, dir));
		// step.add(new RegisterProductOperation(step.getOptions(),
		// "c:\\temp\\install.exe" ));
		// step.add(new CreateUninstallerOperation(dir, UNINSTALL_JAR,
		// EXCLUSION_PATTERN ));
	}

	private String id;
	
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
