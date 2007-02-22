package org.eclipse.dltk.debug.ui;

import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.dltk.debug.internal.core.model.IScriptDebugTargetStreamManager;
import org.eclipse.dltk.debug.internal.core.model.IScriptThreadStreamProxy;
import org.eclipse.ui.console.IOConsole;


public class ConsoleScriptDebugTargetStreamManager implements
		IScriptDebugTargetStreamManager {
	private IOConsole console;

	public ConsoleScriptDebugTargetStreamManager(IOConsole console) {
		this.console = console;
	}

	public IScriptThreadStreamProxy makeThreadStreamProxy() {
		final InputStream input = console.getInputStream();
		final OutputStream output = console.newOutputStream();

		return new IScriptThreadStreamProxy() {

			public OutputStream getStderr() {
				return output;
			}

			public OutputStream getStdout() {
				return output;
			}

			public InputStream getStdin() {
				return input;
			}
		};
	}
}
