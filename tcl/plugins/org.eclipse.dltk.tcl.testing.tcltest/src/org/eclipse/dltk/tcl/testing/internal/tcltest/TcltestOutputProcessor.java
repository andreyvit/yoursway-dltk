/**
 * 
 */
package org.eclipse.dltk.tcl.testing.internal.tcltest;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.dltk.testing.DLTKTestingPlugin;
import org.eclipse.dltk.testing.ITestingClient;
import org.eclipse.dltk.testing.ITestingProcessor;
import org.eclipse.dltk.testing.model.ITestRunSession;

class TcltestOutputProcessor implements ITestingProcessor {
	private ILaunch launch;
	long start = 0;

	public TcltestOutputProcessor(ILaunch launch) {
		this.launch = launch;
	}
	int index = 0;
	private ITestRunSession session;
	private ITestingClient client;
	private boolean skip = false;
	private String message;

	public void done() {
		session.setTotalCount(index);
		client.testTerminated((int)(System.currentTimeMillis() - start));
	}

	public void processLine(String line) {
		System.out.println("#" + line);
		if (line.length() == 0) {
			return;
		}
		if (line.startsWith("====")) {
			if (line.endsWith("FAILED")) {
				if (!skip) {
					message = line.substring(line.indexOf(" ",
							line.indexOf(" ") + 1), line.lastIndexOf(" "));

				}
				if (skip) {
					int lastIndexOf = line.indexOf(" ", line.indexOf(" ") + 1);
					String name = line.substring(5, lastIndexOf);
					
					int id = ++index;

					client.testTree(id, name, false, 0);
					client.testStarted(id, name);
					session.setTotalCount(id);
					client.testFailed(id, name);
					client.traceMessage(message);
				}
				skip = !skip;
			}
		} else if (line.equals("---- Result was:")) {

		} else if (line
				.equals("---- Result should have been (exact matching):")) {

		}
		if (!skip) {
			if (line.startsWith("++++")) {

				int lastIndexOf = line.lastIndexOf(" ");
				String name = line.substring(5, lastIndexOf);
				String state = line.substring(lastIndexOf + 1);
				if ("PASSED".equals(state)) {
					int id = ++index;
					client.testTree(id, name, false, 0);
					client.testStarted(id, name);
					session.setTotalCount(id);
					client.testEnded(id, name);
				}
			}
		}
	}

	public void start() {
		this.start = System.currentTimeMillis();
		index = 0;
		session = DLTKTestingPlugin.getTestRunSession(launch);
		if (session == null)
			return;

		client = session.getTestRunnerClient();
		client.testRunStart(0);
	}
}