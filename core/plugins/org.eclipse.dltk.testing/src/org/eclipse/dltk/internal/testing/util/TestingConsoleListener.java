package org.eclipse.dltk.internal.testing.util;

import org.eclipse.jface.text.IRegion;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleListener;
import org.eclipse.ui.console.TextConsole;

import org.eclipse.debug.core.ILaunch;

import org.eclipse.debug.internal.ui.views.console.ProcessConsole;
import org.eclipse.dltk.debug.ui.ScriptDebugConsole;
import org.eclipse.dltk.testing.ITestingProcessor;

public class TestingConsoleListener implements IConsoleListener {
	private ILaunch launch;
	private ITestingProcessor processor;

	public TestingConsoleListener(ILaunch launch, ITestingProcessor processor) {
		this.launch= launch;
		this.processor= processor;
	}

	public void consolesAdded(IConsole[] consoles) {
		for (int i= 0; i < consoles.length; i++) {
			if (consoles[i] instanceof ProcessConsole) {
				ProcessConsole pc= (ProcessConsole) consoles[i];
				if (pc.getProcess().getLaunch().equals(launch)) {
					process(pc);
				}
			}
			else if( consoles[i] instanceof ScriptDebugConsole ) {
				ScriptDebugConsole cl = (ScriptDebugConsole) consoles[i];
				if (cl.getLaunch().equals(launch)) {
					process(cl);
				}
			}
		}
	}

	private void process(TextConsole pc) {
		processor.start();
		pc.addPatternMatchListener(new ConsoleLineNotifier() {
			private boolean first = true;
			public void connect(TextConsole console) {
				super.connect(console);
			}

			public void lineAppended(IRegion region, String content) {
				if( first ) {
					processor.start();
					first = false;
				}
				processor.processLine(content);
			}

			
			public synchronized void disconnect() {
				super.disconnect();
				ConsolePlugin.getDefault().getConsoleManager().removeConsoleListener(TestingConsoleListener.this);
				processor.done();
			}

			public synchronized void consoleClosed() {
				super.consoleClosed();
			}
		});
	}

	public void consolesRemoved(IConsole[] consoles) {
	}
}
