package org.eclipse.dltk.javascript.internal.ui;

import org.eclipse.dltk.javascript.internal.ui.text.JavascriptTextTools;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The activator class controls the plug-in life cycle
 */
public class JavaScriptUI extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.dltk.javascript.ui";

	// The shared instance
	private static JavaScriptUI plugin;
	
	private JavascriptTextTools fJavascriptTextTools;
	
	/**
	 * The constructor
	 */
	public JavaScriptUI() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
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
	public static JavaScriptUI getDefault() {
		return plugin;
	}
	
	public synchronized JavascriptTextTools getTextTools() {
		if (fJavascriptTextTools == null)
			fJavascriptTextTools= new JavascriptTextTools(true);
		return fJavascriptTextTools;
	}	

}