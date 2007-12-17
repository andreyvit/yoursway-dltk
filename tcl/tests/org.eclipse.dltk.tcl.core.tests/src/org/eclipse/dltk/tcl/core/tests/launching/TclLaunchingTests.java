package org.eclipse.dltk.tcl.core.tests.launching;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.core.tests.launching.ScriptLaunchingTests;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.launching.TclLaunchConfigurationDelegate;

public class TclLaunchingTests extends ScriptLaunchingTests {

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
		fail("Active State Tcl debugging engine not installed");
	}

	protected String getScriptFileName() {
		// TODO Auto-generated method stub
		return null;
	}
}
