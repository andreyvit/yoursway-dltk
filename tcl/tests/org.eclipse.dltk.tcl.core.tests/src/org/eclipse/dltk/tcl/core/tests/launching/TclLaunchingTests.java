package org.eclipse.dltk.tcl.core.tests.launching;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
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
			if (name.indexOf("w") == -1) {
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

	protected void createLaunch(ILaunch launch, String mode, String arguments)
			throws CoreException {
		final AbstractScriptLaunchConfigurationDelegate delegate = new TclLaunchConfigurationDelegate();
		delegate.launch(createTestLaunchConfiguration(getNatureId(),
				getProjectName(), "src/test.tcl", arguments), mode, launch,
				null);
	}
}
