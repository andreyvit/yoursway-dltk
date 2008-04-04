package org.eclipse.dltk.examples.python.internal;

import org.eclipse.dltk.examples.python.internal.ui.editor.ExamplePythonTextTools;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class ExamplePythonUI extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.dltk.examples.python.part3";

	// The shared instance
	private static ExamplePythonUI plugin;
	
	private ExamplePythonTextTools fPythonTextTools;
	
	/**
	 * The constructor
	 */
	public ExamplePythonUI() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static ExamplePythonUI getDefault() {
		return plugin;
	}

	public synchronized ExamplePythonTextTools getTextTools() {
		if (fPythonTextTools == null)
			fPythonTextTools= new ExamplePythonTextTools(true);
	        return fPythonTextTools;
	}	
}
