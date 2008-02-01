package org.eclipse.dltk.tcl.core.tests.launching;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.tcl.activestatedebugger.TclActiveStateDebuggerConstants;
import org.eclipse.dltk.tcl.activestatedebugger.TclActiveStateDebuggerPlugin;
import org.eclipse.dltk.tcl.activestatedebugger.TclActiveStateDebuggerRunner;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.internal.debug.TclDebugConstants;
import org.eclipse.dltk.tcl.internal.debug.TclDebugPlugin;
import org.eclipse.dltk.tcl.launching.TclLaunchConfigurationDelegate;

public class TclLaunchingTests extends ScriptLaunchingTests {
	private static final String DBGP_TCLDEBUG = "/home/dltk/apps/tcl_debug/dbgp_tcldebug";

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

	public void testDebugTclsh() throws Exception {
		initializeActiveStateDebugEngine();
		DebugEventStats stats = super.internalTestDebug("tclsh");
		int suspendCount = stats.getSuspendCount();
		assertEquals(2, suspendCount);

		assertEquals(3, stats.getResumeCount());

		// Checking extended events count
		assertEquals(1, stats.getBeforeVmStarted());
		assertEquals(1, stats.getBeforeCodeLoaded());
		assertEquals(3, stats.getBeforeResumeCount());
		assertEquals(2, stats.getBeforeSuspendCount());
	}

	public void testDebugWish() throws Exception {
		initializeActiveStateDebugEngine();
		DebugEventStats stats = super.internalTestDebug("wish");
		int suspendCount = stats.getSuspendCount();
		assertEquals(2, suspendCount);

		assertEquals(3, stats.getResumeCount());

		// Checking extended events count
		assertEquals(1, stats.getBeforeVmStarted());
		assertEquals(1, stats.getBeforeCodeLoaded());
		assertEquals(3, stats.getBeforeResumeCount());
		assertEquals(2, stats.getBeforeSuspendCount());
	}

	public void testDebugExpect() throws Exception {
		initializeActiveStateDebugEngine();
		DebugEventStats stats = super.internalTestDebug("expect");
		int suspendCount = stats.getSuspendCount();
		assertEquals(2, suspendCount);

		assertEquals(3, stats.getResumeCount());

		// Checking extended events count
		assertEquals(1, stats.getBeforeVmStarted());
		assertEquals(1, stats.getBeforeCodeLoaded());
		assertEquals(3, stats.getBeforeResumeCount());
		assertEquals(2, stats.getBeforeSuspendCount());
	}

	private boolean initialized = false;

	private void initializeActiveStateDebugEngine() {
		if (initialized) {
			return;
		}
		TclDebugPlugin.getDefault().getPluginPreferences().setValue(
				TclDebugConstants.DEBUGGING_ENGINE_ID_KEY,
				TclActiveStateDebuggerRunner.ENGINE_ID);

		// PathFilesContainer container = new PathFilesContainer();
		Plugin plugin = TclActiveStateDebuggerPlugin.getDefault();

		String path = DBGP_TCLDEBUG;
		File file = new File(path);
		// Lets search if we could not found in default location.
		boolean inDefault = true;
		if (!file.exists()) {
			PathFilesContainer container = new PathFilesContainer();
			Searcher searcher = new Searcher();
			container.accept(searcher);
			path = searcher.getPath();
			inDefault = false;
		}
		if (!inDefault && path == null) {
			assertNotNull("Couldn't find ActiveState debugger", path);
		}
		plugin
				.getPluginPreferences()
				.setValue(
						TclActiveStateDebuggerConstants.DEBUGGING_ENGINE_PATH_KEY,
						path);
		initialized = true;
	}

	protected IInterpreterInstall[] getPredefinedInterpreterInstalls() {
		IInterpreterInstallType[] installTypes = ScriptRuntime
				.getInterpreterInstallTypes(TclNature.NATURE_ID);
		int id = 0;
		List installs = new ArrayList();
		for (int i = 0; i < installTypes.length; i++) {
			String installId = getNatureId() + "_";
			createAddInstall(installs, "/usr/bin/tclsh", installId
					+ Integer.toString(++id), installTypes[i]);
			createAddInstall(installs, "/usr/bin/expect", installId
					+ Integer.toString(++id), installTypes[i]);
			createAddInstall(installs, "/usr/bin/wish", installId
					+ Integer.toString(++id), installTypes[i]);
		}
		if (installs.size() > 0) {
			return (IInterpreterInstall[]) installs
					.toArray(new IInterpreterInstall[installs.size()]);
		}
		return searchInstalls(TclNature.NATURE_ID);
	}

	protected boolean hasPredefinedInterpreters() {
		return true;
	}

	public void testTclsh() throws Exception {
		String NAME = "tclsh";
		this.internalTestRequiredInterpreterAvailable(NAME);
		this.internalTestRun(NAME);
	}

	public void testWish() throws Exception {
		String NAME = "wish";
		this.internalTestRequiredInterpreterAvailable(NAME);
		this.internalTestRun(NAME);
	}

	public void testExpect() throws Exception {
		String NAME = "expect";
		this.internalTestRequiredInterpreterAvailable(NAME);
		this.internalTestRun(NAME, SKIP_STDOUT_TEST);
	}

	protected void configureEnvironment(Map env) {
		// This is required by wish to function correctly
		// env.put("DISPLAY", "");
	}
}
