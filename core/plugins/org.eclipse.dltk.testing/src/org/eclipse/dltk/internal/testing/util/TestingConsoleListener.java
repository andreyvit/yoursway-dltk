package org.eclipse.dltk.internal.testing.util;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.internal.ui.views.console.ProcessConsole;
import org.eclipse.dltk.debug.ui.ScriptDebugConsole;
import org.eclipse.dltk.testing.ITestKind;
import org.eclipse.dltk.testing.ITestingProcessor;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleListener;
import org.eclipse.ui.console.TextConsole;

public class TestingConsoleListener implements IConsoleListener {
	private ILaunch launch;
	private ITestingProcessor processor;
	private boolean initialized = false;
	private boolean finalized = false;

	public TestingConsoleListener(ILaunch launch, ITestingProcessor processor) {
		this.launch = launch;
		this.processor = processor;
		IConsole[] consoles = ConsolePlugin.getDefault().getConsoleManager()
				.getConsoles();
		checkConsoles(consoles);
	}

	public synchronized void consolesAdded(IConsole[] consoles) {
//		System.out.println("consolesAdded:" + consoles.length);
		checkConsoles(consoles);
	}

	private synchronized void checkConsoles(IConsole[] consoles) {
		if (initialized) {
			return;
		}
		for (int i = 0; i < consoles.length; i++) {
			if (consoles[i] instanceof ProcessConsole) {
				ProcessConsole pc = (ProcessConsole) consoles[i];

				if (pc.getProcess().getLaunch().getAttribute(
						ITestKind.LAUNCH_ATTR_TEST_KIND).equals(
						launch.getAttribute(ITestKind.LAUNCH_ATTR_TEST_KIND))) {
					process(pc, launch);
					initialized = true;
				}
			} else if (consoles[i] instanceof ScriptDebugConsole) {
				ScriptDebugConsole cl = (ScriptDebugConsole) consoles[i];
				ILaunch launch2 = cl.getLaunch();
				String attribute = launch2
						.getAttribute(ITestKind.LAUNCH_ATTR_TEST_KIND);
				if (launch2 != null
						&& attribute != null
						&& attribute.equals(launch
								.getAttribute(ITestKind.LAUNCH_ATTR_TEST_KIND))) {
					process(cl, launch);
					initialized = true;
				}
			}
		}
	}

	private synchronized void done() {
		if (!finalized) {
			finalized = true;
			processor.done();
			ConsolePlugin.getDefault().getConsoleManager()
					.removeConsoleListener(this);
		}
	}

	private void process(TextConsole pc, final ILaunch launch) {
		pc.addPatternMatchListener(new ConsoleLineNotifier() {
			private boolean first = true;

			public void connect(TextConsole console) {
				super.connect(console);
//				System.out.println("%");
			}

			public synchronized void lineAppended(IRegion region, String content) {
				if (first) {
					first = false;
					processor.start();
				}
				processor.processLine(content);
			}

			public synchronized void disconnect() {
				super.disconnect();
				done();
			}

			public synchronized void consoleClosed() {
				super.consoleClosed();
				done();
			}
		});
	}

	public void consolesRemoved(IConsole[] consoles) {
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((launch.getAttribute(ITestKind.LAUNCH_ATTR_TEST_KIND) == null) ? 0
						: launch.getAttribute(ITestKind.LAUNCH_ATTR_TEST_KIND)
								.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TestingConsoleListener other = (TestingConsoleListener) obj;
		if (launch.getAttribute(ITestKind.LAUNCH_ATTR_TEST_KIND) == null) {
			if (other.launch.getAttribute(ITestKind.LAUNCH_ATTR_TEST_KIND) != null)
				return false;
		} else if (!launch.getAttribute(ITestKind.LAUNCH_ATTR_TEST_KIND)
				.equals(
						other.launch
								.getAttribute(ITestKind.LAUNCH_ATTR_TEST_KIND)))
			return false;
		return true;
	}

}
