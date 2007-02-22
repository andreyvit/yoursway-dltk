package org.eclipse.dltk.core.tests.model;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class ModelTestsPlugin extends Plugin {

	public static final String PLUGIN_NAME = "org.eclipse.dltk.core.tests";
	public static final String TEST_NATURE = "org.eclipse.dltk.core.tests.testnature";
	
	//The shared instance.
	private static ModelTestsPlugin plugin;
	
	/**
	 * The constructor.
	 */
	public ModelTestsPlugin() {
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
	public static ModelTestsPlugin getDefault() {
		return plugin;
	}

}
