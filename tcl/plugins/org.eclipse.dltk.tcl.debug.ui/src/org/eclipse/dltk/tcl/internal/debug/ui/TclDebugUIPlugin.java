package org.eclipse.dltk.tcl.internal.debug.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class TclDebugUIPlugin extends AbstractUIPlugin {

	public TclDebugUIPlugin() {
		plugin = this;
	}

	// The shared instance.
	private static TclDebugUIPlugin plugin;

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static TclDebugUIPlugin getDefault() {
		return plugin;
	}
}
