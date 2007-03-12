package org.eclipse.dltk.dbgp.internal;

import org.eclipse.core.runtime.ListenerList;

public abstract class DbgpTermination implements IDbgpTermination {
	private ListenerList listeners = new ListenerList();

	protected void fireObjectTerminated(final Exception e) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				Object[] list = listeners.getListeners();
				for (int i = 0; i < list.length; ++i) {
					((IDbgpTerminationListener) list[i]).objectTerminated(
							DbgpTermination.this, e);
				}
			}
		});

		thread.start();
	}

	public void addTerminationListener(IDbgpTerminationListener listener) {
		listeners.add(listener);

	}

	public void removeTerminationListener(IDbgpTerminationListener listener) {
		listeners.remove(listener);
	}
}
