package org.eclipse.dltk.debug.core;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugException;
import org.eclipse.dltk.debug.internal.core.model.DbgpService;
import org.osgi.framework.BundleContext;

/**
 * The plugin class for the DLTK Debug Model plug-in.
 */

public class DLTKDebugPlugin extends Plugin {

	public static final String PLUGIN_ID = "org.eclipse.dltk.debug";

	public static final int INTERNAL_ERROR = 120;

	private static DLTKDebugPlugin fgPlugin;

	public static DLTKDebugPlugin getDefault() {
		return fgPlugin;
	}

	public DLTKDebugPlugin() {
		super();
		fgPlugin = this;
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		DbgpService.shutdown();
	}

	public IDbgpService getDbgpService() {
		return DbgpService.getInstance();
	}

	public IDbgpService getDbgpService(int port) {
		return DbgpService.getInstance(port);
	}

	// Logging
	public static void log(Throwable t) {
		Throwable top = t;
		if (t instanceof DebugException) {
			Throwable throwable = ((DebugException) t).getStatus()
					.getException();
			if (throwable != null) {
				top = throwable;
			}
		}

		log(new Status(IStatus.ERROR, PLUGIN_ID, INTERNAL_ERROR,
				"Internal error logged from DLTKDebugPlugin: ", top));
	}

	public static void log(IStatus status) {
		getDefault().getLog().log(status);
	}
}
