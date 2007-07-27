package org.eclipse.dltk.ruby.core.tests.launching;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

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
import org.eclipse.dltk.internal.launching.InterpreterDefinitionsContainer;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.core.tests.Activator;
import org.eclipse.dltk.ruby.launching.RubyLaunchConfigurationDelegate;

public class RubyLaunchingTests extends AbstractModelTests {

	public static class MyInterpretersUpdater {

		private InterpreterDefinitionsContainer fOriginalInterpreters;

		private void saveCurrentAsOriginal() {
			fOriginalInterpreters = new InterpreterDefinitionsContainer();

			final String[] natureIds = ScriptRuntime.getInterpreterNatures();
			for (int i = 0; i < natureIds.length; i++) {
				final String natureId = natureIds[i];

				IInterpreterInstall def = ScriptRuntime
						.getDefaultInterpreterInstall(natureId);

				if (def != null) {
					fOriginalInterpreters
							.setDefaultInterpreterInstallCompositeID(natureId,
									ScriptRuntime
											.getCompositeIdFromInterpreter(def));
				}
			}

			final IInterpreterInstallType[] types = ScriptRuntime
					.getInterpreterInstallTypes();
			for (int i = 0; i < types.length; i++) {
				IInterpreterInstall[] installs = types[i]
						.getInterpreterInstalls();
				if (installs != null) {
					for (int j = 0; j < installs.length; j++) {
						fOriginalInterpreters.addInterpreter(installs[j]);
					}
				}
			}
		}

		private void saveInterpreterDefinitions(
				final InterpreterDefinitionsContainer container) {
			try {
				final String xml = container.getAsXML();
				ScriptRuntime.getPreferences().setValue(
						ScriptRuntime.PREF_INTERPRETER_XML, xml);
				ScriptRuntime.savePreferences();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public MyInterpretersUpdater() {
			saveCurrentAsOriginal();
		}

		public boolean updateInterpreterSettings(String langNatureId,
				IInterpreterInstall[] interpreters,
				IInterpreterInstall defaultInterpreter) {
			// Create a Interpreter definition container
			InterpreterDefinitionsContainer container = new InterpreterDefinitionsContainer();

			// Default interpreter id for natureId
			if (defaultInterpreter != null) {
				final String defaultId = ScriptRuntime
						.getCompositeIdFromInterpreter(defaultInterpreter);
				container.setDefaultInterpreterInstallCompositeID(langNatureId,
						defaultId);
			} else {
				container.setDefaultInterpreterInstallCompositeID(langNatureId,
						null);
			}

			// Interpreters for natureId
			for (int i = 0; i < interpreters.length; i++) {
				container.addInterpreter(interpreters[i]);
			}

			// Default interpreters for other languages
			final String[] natureIds = fOriginalInterpreters
					.getInterpreterNatures();
			for (int i = 0; i < natureIds.length; i++) {
				final String natureId = natureIds[i];
				if (!langNatureId.equals(natureId)) {
					final String defaultId = fOriginalInterpreters
							.getDefaultInterpreterInstallCompositeID(natureId);
					container.setDefaultInterpreterInstallCompositeID(natureId,
							defaultId);
				}
			}

			// Save interpreters from other languages to the container
			final Iterator it = fOriginalInterpreters.getInterpreterList()
					.iterator();
			while (it.hasNext()) {
				final IInterpreterInstall install = (IInterpreterInstall) it
						.next();
				if (!langNatureId.equals(install.getInterpreterInstallType()
						.getNatureId())) {
					container.addInterpreter(install);
				}
			}

			saveInterpreterDefinitions(container);

			saveCurrentAsOriginal();

			return true;
		}
	}

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
				if (attributeName
						.equals(ScriptLaunchConfigurationConstants.ATTR_DEFAULT_BUILDPATH)) {
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
				} else if (attributeName
						.equals(ScriptLaunchConfigurationConstants.ATTR_WORKING_DIRECTORY)) {
					return null;
				} else if (attributeName
						.equals(ScriptLaunchConfigurationConstants.ATTR_SCRIPT_ARGUMENTS)) {
					return "";
				} else if (attributeName
						.equals(ScriptLaunchConfigurationConstants.ATTR_INTERPRETER_ARGUMENTS)) {
					return "";
				} else if (attributeName
						.equals(ScriptLaunchConfigurationConstants.ATTR_SCRIPT_NATURE)) {
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

	public void __testLaunch() throws Exception {
		IProject project = scriptProject.getProject();
		IResource member = project.findMember("src/test.rb");

		IInterpreterInstallType[] installTypes = ScriptRuntime
				.getInterpreterInstallTypes(RubyNature.NATURE_ID);
		IInterpreterInstallType installType = installTypes[0];

		IInterpreterInstall install = installType
				.createInterpreterInstall("test_install");

		install.setName("my ruby");
		install.setInstallLocation(new File("C:/ruby/bin/ruby.exe"));
		install.setLibraryLocations(null);

		MyInterpretersUpdater updater = new MyInterpretersUpdater();
		updater.updateInterpreterSettings(RubyNature.NATURE_ID,
				new IInterpreterInstall[] { install }, install);

		// Interpreter install (for testing)
		final IInterpreterInstall myInstall = ScriptRuntime
				.getDefaultInterpreterInstall(RubyNature.NATURE_ID);

		ILaunch launch = new Launch(null, ILaunchManager.RUN_MODE, null);

		RubyLaunchConfigurationDelegate delegate = new RubyLaunchConfigurationDelegate();
		delegate.launch(createConfiguration(), ILaunchManager.RUN_MODE, launch,
				null);

		// assertTrue(member != null);
	}

	protected InterpreterConfig createInterperterConfig() {
		IProject project = scriptProject.getProject();
		IResource member = project.findMember("src/test.rb");
		IPath scriptPath = member.getLocation();

		return new InterpreterConfig(scriptPath);
	}

	public void testInterpreterConfig() {
		IProject project = scriptProject.getProject();
		IResource member = project.findMember("src/test.rb");
		IPath scriptPath = member.getLocation();

		InterpreterConfig config = new InterpreterConfig(scriptPath);

		// Creation
		assertNotNull(config.getScriptFilePath());
		assertNotNull(config.getWorkingDirectoryPath());

		assertEquals(scriptPath, config.getScriptFilePath());
		assertEquals(scriptPath.removeLastSegments(1), config
				.getWorkingDirectoryPath());

		// Null as script file
		try {
			config.setScriptFile((IPath) null);
			fail("Should raise an IllegalArgumentException");
		} catch (IllegalArgumentException e) {

		}

		try {
			config.setScriptFile((File) null);
			fail("Should raise an IllegalArgumentException");
		} catch (IllegalArgumentException e) {

		}

		// Null as working directory
		try {
			config.setWorkingDirectory((IPath) null);
			fail("Should raise an IllegalArgumentException");
		} catch (IllegalArgumentException e) {

		}

		try {
			config.setWorkingDirectory((File) null);
			fail("Should raise an IllegalArgumentException");
		} catch (IllegalArgumentException e) {

		}
	}

	public void testInterpreterConfigEnvArgs() {
		InterpreterConfig config = createInterperterConfig();

		// Environment
		assertEquals(0, config.getEnvVars().size());

		// Not existent variable
		assertFalse(config.hasEnvVar("DIMAN"));
		assertNull(config.getEnvVar("KDS"));

		// Add variable
		config.addEnvVar("KDS", "DIMAN");
		assertTrue(config.hasEnvVar("KDS"));
		assertNotNull(config.getEnvVar("KDS"));
		assertEquals("DIMAN", config.getEnvVar("KDS"));

		// Remove
		config.removeEnvVar("KDS");
		assertFalse(config.hasEnvVar("DIMAN"));
		assertNull(config.getEnvVar("KDS"));

		assertEquals(0, config.getEnvVars().size());

		// Adding null name
		try {
			config.addEnvVar("KDS", null);
			fail("Should raise an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}

		// Adding null value
		try {
			config.addEnvVar(null, "DIMAN");
			fail("Should raise an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
	}

	public void testInterperterConfigScriptArgs() {
		InterpreterConfig config = createInterperterConfig();

		// Script arguments
		assertEquals(0, config.getScriptArgs().size());
		assertFalse(config.hasScriptArg("-gXXX"));

		// Adding first argument
		config.addScriptArg("-gXXX");
		assertTrue(config.hasScriptArg("-gXXX"));

		// Adding second argument
		config.addScriptArg("-pXXX");
		assertTrue(config.hasScriptArg("-pXXX"));

		final List args = config.getScriptArgs();
		assertEquals(2, args.size());
		assertEquals("-gXXX", args.get(0));
		assertEquals("-pXXX", args.get(1));

		// Adding null argument
		try {
			config.addScriptArg(null);
			fail("Should raise an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
	}

	public void testInterperterConfigInterpreterArgs() {
		InterpreterConfig config = createInterperterConfig();

		// Script arguments
		assertEquals(0, config.getInterpreterArgs().size());
		assertFalse(config.hasInterpreterArg("arg1"));

		// Adding first argument
		config.addInterpreterArg("arg1");
		assertTrue(config.hasInterpreterArg("arg1"));

		// Adding second argument
		config.addInterpreterArg("arg2");
		assertTrue(config.hasInterpreterArg("arg2"));

		final List args = config.getInterpreterArgs();
		assertEquals(2, args.size());
		assertEquals("arg1", args.get(0));
		assertEquals("arg2", args.get(1));

		// Adding null argument
		try {
			config.addInterpreterArg(null);
			fail("Should raise an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
	}
}
