package org.eclipse.dltk.ruby.fastdebugger;

import java.io.IOException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.ruby.abstractdebugger.AbstractRubyDebuggerPlugin;
import org.eclipse.dltk.utils.DeployHelper;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class FastDebuggerPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.dltk.ruby.fastdebugger"; //$NON-NLS-1$

	// The shared instance
	private static FastDebuggerPlugin plugin;

	/**
	 * The constructor
	 */
	public FastDebuggerPlugin() {
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
	public static FastDebuggerPlugin getDefault() {
		return plugin;
	}

	private static final String DEBUGGER_DIR = "/debugger"; //$NON-NLS-1$

	public IPath deployDebuggerSource() throws IOException {
		AbstractRubyDebuggerPlugin.getDefault().deployDebuggerSource(this);
		return DeployHelper.deploy(this, DEBUGGER_DIR);
	}
}
