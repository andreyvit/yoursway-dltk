package org.eclipse.dltk.javascript.internal.launching;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.internal.launching.AbstractInterpreterInstallType;
import org.eclipse.dltk.javascript.core.JavaScriptNature;
import org.eclipse.dltk.javascript.launching.JavaScriptLaunchingPlugin;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.LibraryLocation;
import org.osgi.framework.Bundle;

public class GenericJavaScriptInstallType extends
		AbstractInterpreterInstallType {

	public String getNatureId() {
		return JavaScriptNature.NATURE_ID;
	}

	public String getName() {
		return "Generic Rhino install";
	}

	public LibraryLocation[] getDefaultLibraryLocations(File installLocation) {
		if (true) {
			Bundle bundle = Platform
					.getBundle("org.eclipse.dltk.javascript.rhino");

			URL resolve;
			try {
				resolve = FileLocator.toFileURL(bundle
						.getResource("RhinoRunner.class"));
				try {
					
					File fl = new File(new URI(resolve.toString())).getParentFile();
					return new LibraryLocation[] { new LibraryLocation(
							new Path(fl.getAbsolutePath())) };
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		// TODO Auto-generated method stub
		return super.getDefaultLibraryLocations(installLocation);
	}

	private static String[] possibleExes = { "js" };

	protected String getPluginId() {
		return JavaScriptLaunchingPlugin.PLUGIN_ID;
	}

	protected String[] getPossibleInterpreterNames() {
		return possibleExes;
	}

	protected IInterpreterInstall doCreateInterpreterInstall(String id) {
		return new GenericJavaScriptInstall(this, id);
	}

	protected void filterEnvironment(Map environment) {
		// make sure that $auto_path is clean
		environment.remove("TCLLIBPATH");
		// block wish from showing window under linux
		environment.remove("DISPLAY");
	}

	public IStatus validateInstallLocation(File installLocation) {
		return Status.OK_STATUS;
	}

	protected File createPathFile() throws IOException {
		Bundle bundle = JavaScriptLaunchingPlugin.getDefault().getBundle();
		return storeToMetadata(bundle, "path.tcl", "scripts/path.tcl");
	}

	protected String[] parsePaths(String result) {
		ArrayList paths = new ArrayList();
		String subs = null;
		int index = 0;
		while (index < result.length()) {
			// skip whitespaces
			while (index < result.length()
					&& Character.isWhitespace(result.charAt(index)))
				index++;
			if (index == result.length())
				break;

			if (result.charAt(index) == '{') {
				int start = index;
				while (index < result.length() && result.charAt(index) != '}')
					index++;
				if (index == result.length())
					break;
				subs = result.substring(start + 1, index);
			} else {
				int start = index;
				while (index < result.length() && result.charAt(index) != ' ')
					index++;
				subs = result.substring(start, index);
			}

			paths.add(subs);
			index++;
		}

		return (String[]) paths.toArray(new String[paths.size()]);
	}

	protected ILog getLog() {
		return JavaScriptLaunchingPlugin.getDefault().getLog();
	}
}
