package org.eclipse.dltk.console;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class ScriptConsolePlugin extends Plugin {

	public static final String PLUGIN_ID = "org.eclipse.dltk.console"; //$NON-NLS-1$
	
	//The shared instance.
	private static ScriptConsolePlugin plugin;
	
	/**
	 * The constructor.
	 */
	public ScriptConsolePlugin() {
		plugin = this;
	}

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
	public static ScriptConsolePlugin getDefault() {
		return plugin;
	}	
}
	


