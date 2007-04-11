package org.eclipse.dltk.tcl.internal.tclchecker;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class TclCheckerPlugin extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "org.eclipse.dltk.tcl.tclchecker";
	
	//The shared instance.
	private static TclCheckerPlugin plugin;
	
	/**
	 * The constructor.
	 */
	public TclCheckerPlugin() {
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
	public static TclCheckerPlugin getDefault() {
		return plugin;
	}	
	
//	private IPreferenceStore preferenceStore;
//	
//	public IPreferenceStore getPreferenceStore() {
//        if (preferenceStore == null) {
//            preferenceStore = new ScopedPreferenceStore(new InstanceScope(),getBundle().getSymbolicName());
//
//        }        
//        return preferenceStore;
//    }
}