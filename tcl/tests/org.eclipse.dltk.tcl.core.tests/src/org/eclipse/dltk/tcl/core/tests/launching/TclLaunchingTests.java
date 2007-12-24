package org.eclipse.dltk.tcl.core.tests.launching;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.core.tests.launching.IFileVisitor;
import org.eclipse.dltk.core.tests.launching.PathFilesContainer;
import org.eclipse.dltk.core.tests.launching.ScriptLaunchingTests;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.tcl.activestatedebugger.TclActiveStateDebuggerConstants;
import org.eclipse.dltk.tcl.activestatedebugger.TclActiveStateDebuggerPlugin;
import org.eclipse.dltk.tcl.activestatedebugger.TclActiveStateDebuggerRunner;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.internal.debug.TclDebugConstants;
import org.eclipse.dltk.tcl.internal.debug.TclDebugPlugin;
import org.eclipse.dltk.tcl.launching.TclLaunchConfigurationDelegate;

public class TclLaunchingTests extends ScriptLaunchingTests {
	class Searcher implements IFileVisitor {
		private String debuggingEnginePath = null;

		public boolean visit(File file) {
			if (file.isFile() && file.getName().startsWith("dbgp_tcldebug")) {
				debuggingEnginePath = file.getAbsolutePath();
			}

			if (file.isDirectory() && debuggingEnginePath == null) {
				return true;
			} else {
				return false;
			}
		}

		public String getPath() {
			return debuggingEnginePath;
		}
	};

	public TclLaunchingTests(String name) {
		super("org.eclipse.dltk.tcl.core.tests", name);
	}

	public TclLaunchingTests(String testProjectName, String name) {
		super(testProjectName, name);
	}

	public void setUpSuite() throws Exception {
		super.setUpSuite();

		List newInterpreterInstalls = new ArrayList();
		for (int i = 0; i < interpreterInstalls.length; ++i) {
			IInterpreterInstall install = interpreterInstalls[i];
			final String name = install.getInstallLocation().getName();
			if (name.indexOf("tclsh") != -1) {
				newInterpreterInstalls.add(install);
			}
		}

		interpreterInstalls = (IInterpreterInstall[]) newInterpreterInstalls
				.toArray(new IInterpreterInstall[newInterpreterInstalls.size()]);
	}

	public static Test suite() {
		return new Suite(TclLaunchingTests.class);
	}

	protected String getProjectName() {
		return "launching";
	}

	protected String getNatureId() {
		return TclNature.NATURE_ID;
	}

	protected String getDebugModelId() {
		return TclDebugConstants.DEBUG_MODEL_ID;
	}

	protected ILaunchConfiguration createLaunchConfiguration(String arguments) {
		return createTestLaunchConfiguration(getNatureId(), getProjectName(),
				"src/test.tcl", arguments);
	}

	protected void startLaunch(ILaunch launch) throws CoreException {
		final AbstractScriptLaunchConfigurationDelegate delegate = new TclLaunchConfigurationDelegate();
		delegate.launch(launch.getLaunchConfiguration(),
				launch.getLaunchMode(), launch, null);
	}

	public void testDebug() throws Exception {
		TclDebugPlugin.getDefault().getPluginPreferences().setValue(
				TclDebugConstants.DEBUGGING_ENGINE_ID_KEY,
				TclActiveStateDebuggerRunner.ENGINE_ID);

		PathFilesContainer container = new PathFilesContainer();
		Searcher searcher = new Searcher();
		container.accept(searcher);

		Plugin p = TclActiveStateDebuggerPlugin.getDefault();
		String path = searcher.getPath();
		assertNotNull("Couldn't find ActiveState debugger", path);
		p.getPluginPreferences().setValue(
				TclActiveStateDebuggerConstants.DEBUGGING_ENGINE_PATH_KEY,
				path);

		super.testDebug();
	}

	protected String[] getRequiredInterpreterNames() {
		String[] required = { "tclsh", "wish", "expect" };		
		return required;
	}
}
