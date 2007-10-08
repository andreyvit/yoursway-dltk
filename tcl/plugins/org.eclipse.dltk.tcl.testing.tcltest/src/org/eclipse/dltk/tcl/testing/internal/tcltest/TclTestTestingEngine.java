package org.eclipse.dltk.tcl.testing.internal.tcltest;

import java.io.IOException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.tcl.testing.ITclTestingEngine;
import org.eclipse.dltk.testing.ITestingProcessor;
import org.eclipse.dltk.utils.DeployHelper;

public class TclTestTestingEngine implements ITclTestingEngine {
	public TclTestTestingEngine() {
	}

	public String getId() {
		return Activator.PLUGIN_ID + ".testingEngine";
	}

	public String getName() {
		return "Tcl Test";
	}

	public ITestingProcessor getProcessor(ILaunch launch) {
		return new TcltestOutputProcessor(launch);
	}

	public boolean isValidModule(ISourceModule module) {
		// TODO Auto-generated method stub
		return true;
	}

	public void correctLaunchConfiguration(InterpreterConfig config) {
		// We need to extract tcl source module and correct config.
		try {
			IPath runner = DeployHelper.deploy(Activator.getDefault(),
					"scripts/tcltestEngine.tcl");
			IPath scriptFilePath = config.getScriptFilePath();
			config.setScriptFile(runner);
			config.addScriptArg(scriptFilePath.toOSString(), 0);
		} catch (IOException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
	}
}
