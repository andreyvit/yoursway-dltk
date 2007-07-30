package org.eclipse.dltk.debug.tests;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.tests.model.AbstractModelTests;

public abstract class ScriptDebugTest extends AbstractModelTests {
	public ScriptDebugTest(String testProjectName, String name) {
		super(testProjectName, name);
	}

	protected IScriptProject scriptProject;

	// Configuration
	public void setUpSuite() throws Exception {
		super.setUpSuite();
		scriptProject = setUpScriptProject(getProjectName());

		// final IProject project = scriptProject.getProject();
		// IProjectDescription description = project.getDescription();
		// description.setNatureIds(new String[] { getNatureId() });
		// project.setDescription(description, null);
	}

	public void tearDownSuite() throws Exception {
		deleteProject(getProjectName());
		super.tearDownSuite();
	}

	protected String getPluginDirectoryPath() {
		try {
			URL platformURL = Activator.getDefault().getBundle().getEntry("/");
			return new File(FileLocator.toFileURL(platformURL).getFile())
					.getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected abstract String getProjectName();
}
