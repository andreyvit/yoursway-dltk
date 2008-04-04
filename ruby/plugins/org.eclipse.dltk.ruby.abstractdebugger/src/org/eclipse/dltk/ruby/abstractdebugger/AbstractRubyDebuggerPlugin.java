package org.eclipse.dltk.ruby.abstractdebugger;

import java.io.IOException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.dltk.utils.DeployHelper;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class AbstractRubyDebuggerPlugin extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.dltk.ruby.abstractdebugger"; //$NON-NLS-1$

	// The shared instance
	private static AbstractRubyDebuggerPlugin plugin;
	
	/**
	 * The constructor
	 */
	public AbstractRubyDebuggerPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
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
	public static AbstractRubyDebuggerPlugin getDefault() {
		return plugin;
	}
	
	private static final String DEBUGGER_DIR = "/debugger"; //$NON-NLS-1$

	public IPath deployDebuggerSource(Plugin childDebuggerPlugin) throws IOException {
		return DeployHelper.deploy(getBundle(), DEBUGGER_DIR, childDebuggerPlugin.getStateLocation());
	}
}
