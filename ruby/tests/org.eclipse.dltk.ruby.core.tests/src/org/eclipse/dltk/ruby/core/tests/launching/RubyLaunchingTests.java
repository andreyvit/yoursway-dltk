package org.eclipse.dltk.ruby.core.tests.launching;

import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Test;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchDelegate;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.Launch;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.tests.model.AbstractModelTests;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.core.tests.Activator;
import org.eclipse.dltk.ruby.internal.launching.RubyInterpreterRunner;
import org.eclipse.dltk.ruby.launching.RubyLaunchConfigurationDelegate;

public class RubyLaunchingTests extends AbstractModelTests {
	private static final String PROJECT_NAME = "launching";

	private IScriptProject scriptProject;

	public RubyLaunchingTests(String testProjectName, String name) {
		super(testProjectName, name);
		// TODO Auto-generated constructor stub
	}

	// Test
	public RubyLaunchingTests(String name) {
		super(Activator.PLUGIN_ID, name);
	}

	public static Test suite() {
		return new Suite(RubyLaunchingTests.class);
	}

	// Configuration
	public void setUpSuite() throws Exception {
		super.setUpSuite();
		scriptProject = setUpScriptProject(PROJECT_NAME);
		
		// Set up Ruby nature
		final IProject project = scriptProject.getProject();
		IProjectDescription description = project.getDescription();
		description.setNatureIds(new String[] { RubyNature.NATURE_ID });
		project.setDescription(description, null);

		// waitUntilIndexesReady();
		// ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.FULL_BUILD,
		// new NullProgressMonitor());
		// waitForAutoBuild();
	}

	public void tearDownSuite() throws Exception {
		deleteProject(PROJECT_NAME);
		super.tearDownSuite();
	}

	public ILaunchConfiguration createConfiguration() {
		return new ILaunchConfiguration() {

			public boolean contentsEqual(ILaunchConfiguration configuration) {
				// TODO Auto-generated method stub
				return false;
			}

			public ILaunchConfigurationWorkingCopy copy(String name)
					throws CoreException {
				// TODO Auto-generated method stub
				return null;
			}

			public void delete() throws CoreException {
				// TODO Auto-generated method stub

			}

			public boolean exists() {
				// TODO Auto-generated method stub
				return false;
			}

			public boolean getAttribute(String attributeName,
					boolean defaultValue) throws CoreException {
				if (attributeName.equals(ScriptLaunchConfigurationConstants.ATTR_DEFAULT_BUILDPATH)) {
					return true;
				}
				
				return false;
			}

			public int getAttribute(String attributeName, int defaultValue)
					throws CoreException {
				// TODO Auto-generated method stub
				return 0;
			}

			public List getAttribute(String attributeName, List defaultValue)
					throws CoreException {
				// TODO Auto-generated method stub
				return null;
			}

			public Set getAttribute(String attributeName, Set defaultValue)
					throws CoreException {
				// TODO Auto-generated method stub
				return null;
			}

			public Map getAttribute(String attributeName, Map defaultValue)
					throws CoreException {
				// TODO Auto-generated method stub
				return null;
			}

			public String getAttribute(String attributeName, String defaultValue)
					throws CoreException {

				if (attributeName
						.equals(ScriptLaunchConfigurationConstants.ATTR_MAIN_SCRIPT_NAME)) {
					return "src/test.rb";
				} else if (attributeName
						.equals(ScriptLaunchConfigurationConstants.ATTR_PROJECT_NAME)) {
					return "launching";
				} else if (attributeName.equals(ScriptLaunchConfigurationConstants.ATTR_WORKING_DIRECTORY)) {
					return null;
				} else if (attributeName.equals(ScriptLaunchConfigurationConstants.ATTR_SCRIPT_ARGUMENTS)){
					return "";
				} else if (attributeName.equals(ScriptLaunchConfigurationConstants.ATTR_INTERPRETER_ARGUMENTS)) {
					return "";
				} else if (attributeName.equals(ScriptLaunchConfigurationConstants.ATTR_SCRIPT_NATURE)) {
					return RubyNature.NATURE_ID;
				}
			
			

				// TODO Auto-generated method stub
				return null;
			}

			public Map getAttributes() throws CoreException {
				// TODO Auto-generated method stub
				return null;
			}

			public String getCategory() throws CoreException {
				// TODO Auto-generated method stub
				return null;
			}

			public IFile getFile() {
				// TODO Auto-generated method stub
				return null;
			}

			public IPath getLocation() {
				// TODO Auto-generated method stub
				return null;
			}

			public IResource[] getMappedResources() throws CoreException {
				// TODO Auto-generated method stub
				return null;
			}

			public String getMemento() throws CoreException {
				// TODO Auto-generated method stub
				return null;
			}

			public Set getModes() throws CoreException {
				// TODO Auto-generated method stub
				return null;
			}

			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}

			public ILaunchDelegate getPreferredDelegate(Set modes)
					throws CoreException {
				// TODO Auto-generated method stub
				return null;
			}

			public ILaunchConfigurationType getType() throws CoreException {
				// TODO Auto-generated method stub
				return null;
			}

			public ILaunchConfigurationWorkingCopy getWorkingCopy()
					throws CoreException {
				// TODO Auto-generated method stub
				return null;
			}

			public boolean isLocal() {
				// TODO Auto-generated method stub
				return false;
			}

			public boolean isMigrationCandidate() throws CoreException {
				// TODO Auto-generated method stub
				return false;
			}

			public boolean isReadOnly() {
				// TODO Auto-generated method stub
				return false;
			}

			public boolean isWorkingCopy() {
				// TODO Auto-generated method stub
				return false;
			}

			public ILaunch launch(String mode, IProgressMonitor monitor)
					throws CoreException {
				// TODO Auto-generated method stub
				return null;
			}

			public ILaunch launch(String mode, IProgressMonitor monitor,
					boolean build) throws CoreException {
				// TODO Auto-generated method stub
				return null;
			}

			public ILaunch launch(String mode, IProgressMonitor monitor,
					boolean build, boolean register) throws CoreException {
				// TODO Auto-generated method stub
				return null;
			}

			public void migrate() throws CoreException {
				// TODO Auto-generated method stub

			}

			public boolean supportsMode(String mode) throws CoreException {
				// TODO Auto-generated method stub
				return false;
			}

			public Object getAdapter(Class adapter) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	public void testLaunch() throws Exception {
		IProject project = scriptProject.getProject();
		IResource member = project.findMember("src/test.rb");

		// Interpreter install
		final IInterpreterInstall install = ScriptRuntime
				.getDefaultInterpreterInstall(RubyNature.NATURE_ID);

		// Interperter runner
		// RubyInterpreterRunner runner = new RubyInterpreterRunner(install);
		// runner.run(config, launch, null);

		ILaunch launch = new Launch(null, ILaunchManager.RUN_MODE, null);

//		RubyLaunchConfigurationDelegate delegate = new RubyLaunchConfigurationDelegate();
//		delegate.launch(createConfiguration(), ILaunchManager.RUN_MODE, launch,
//				null);

		assertTrue(member != null);
	}
}
