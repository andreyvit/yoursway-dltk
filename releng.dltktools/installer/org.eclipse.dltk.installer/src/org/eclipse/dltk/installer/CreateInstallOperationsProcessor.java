package org.eclipse.dltk.installer;

import java.io.File;
import java.io.IOException;

import org.eclipse.epp.installer.archive.Archives;
import org.eclipse.epp.installer.archive.IArchive;
import org.eclipse.epp.installer.archive.IArchiveEntry;
import org.eclipse.epp.installer.core.InstallOptions;
import org.eclipse.epp.installer.core.model.Context;
import org.eclipse.epp.installer.core.model.IInstallStep;
import org.eclipse.epp.installer.core.model.IStepProcessor;
import org.eclipse.epp.installer.core.operations.ExtractDirectoryOperation;
import org.eclipse.epp.installer.core.steps.RunOperationsStep;

public class CreateInstallOperationsProcessor implements IStepProcessor {

	public void process(IInstallStep step) {
		if (step instanceof RunOperationsStep) {
			String archivePath = System.getProperty(Context.INSTALLER_JAR_PROPERTY);
			if (archivePath == null)
				throw new RuntimeException("archive name unspecified, please set "
						+ Context.INSTALLER_JAR_PROPERTY + " property.");
			IArchive archive;
			try {
				archive = Archives.createArchive(new File(archivePath));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			if(archive == null)
				throw new RuntimeException("archive not found at: " + archivePath);
			IArchiveEntry entry = archive.getEntry("eclipse");

			File dir = new File(step.getOptions().getString(
					InstallOptions.OPTION_INSTALL_DIR));

			((RunOperationsStep) step).add(new ExtractDirectoryOperation(archive, entry, dir));
		}
	}

}
