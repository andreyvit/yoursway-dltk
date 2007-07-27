package org.eclipse.dltk.ruby.core.tests.launching;

import junit.framework.Test;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.dltk.core.tests.launching.ScriptLaunchingTests;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.launching.RubyLaunchConfigurationDelegate;

public class RubyLaunchingTests extends ScriptLaunchingTests {

	public RubyLaunchingTests(String name) {
		super("org.eclipse.dltk.ruby.core.tests", name);
	}

	public RubyLaunchingTests(String testProjectName, String name) {
		super(testProjectName, name);
	}

	public static Test suite() {
		return new Suite(RubyLaunchingTests.class);
	}

	protected String getProjectName() {
		return "launching";
	}

	protected String getNatureId() {
		return RubyNature.NATURE_ID;
	}

	protected void createLaunch(ILaunch launch, String mode, String arguments)
			throws CoreException {
		final AbstractScriptLaunchConfigurationDelegate delegate = new RubyLaunchConfigurationDelegate();
		delegate
				.launch(createTestLaunchConfiguration(getNatureId(),
						getProjectName(), "src/test.rb", arguments), mode,
						launch, null);
	}
}
