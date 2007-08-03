package org.eclipse.dltk.debug.tests;

import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.tests.model.AbstractModelTests;

public abstract class AbstractDebugTests extends AbstractModelTests {
	public AbstractDebugTests(String testProjectName, String name) {
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

	// protected File getPluginDirectoryPath() {
	// try {
	// URL platformURL = Activator.getDefault().getBundle().getEntry("/");
	// return new File(FileLocator.toFileURL(platformURL).getFile());
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

	protected abstract String getProjectName();
}
