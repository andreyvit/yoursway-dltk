package org.eclipse.dltk.python.activestatedebugger;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class PythonActiveStateDebuggerPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.dltk.ruby.activestatedebugger";

	// The shared instance
	private static PythonActiveStateDebuggerPlugin plugin;

	/**
	 * The constructor
	 */
	public PythonActiveStateDebuggerPlugin() {
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static PythonActiveStateDebuggerPlugin getDefault() {
		return plugin;
	}
}
