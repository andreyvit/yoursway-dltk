package org.eclipse.dltk.dbgp.internal;

import org.eclipse.dltk.core.DLTKCore;

public abstract class DbgpWorkingThread extends DbgpTermination {
	private Thread thread;

	public void start() {
		if (thread == null || !thread.isAlive()) {
			thread = new Thread(new Runnable() {
				public void run() {
					try {
						workingCycle();
					} catch (Exception e) {
						fireObjectTerminated(e);
						return;
					}

					fireObjectTerminated(null);
				}
			});

			thread.start();

			if (DLTKCore.DEBUG) {
				System.out.println("Starting thread for: " + getClass());
			}
		} else {
			throw new IllegalStateException("Thread already started");
		}
	}

	// IDbgpTerminate
	public void requestTermination() {
		if (DLTKCore.DEBUG) {
			System.out.println("DbgpWorkingThread.requestTerminate(): "
					+ getClass());
		}
		thread.interrupt();
	}

	public void waitTerminated() throws InterruptedException {
		if (DLTKCore.DEBUG) {
			System.out.println("DbgpWorkingThread.waitTerminated(): "
					+ getClass());
		}
		thread.join();
	}

	// Working cycle
	protected abstract void workingCycle() throws Exception;
}
