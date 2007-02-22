package org.eclipse.dltk.dbgp.internal;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.dltk.core.DLTKCore;

public abstract class DbgpTermination implements IDbgpTermination {
	private ListenerList listeners = new ListenerList();

	protected void fireObjectTerminated(final Exception e) {
		(new Thread(new Runnable() {
			public void run() {
				if (DLTKCore.DEBUG) {
					System.out.println("Termnated: "
							+ DbgpTermination.this.getClass());
				}
				Object[] list = listeners.getListeners();
				for (int i = 0; i < list.length; ++i) {
					((IDbgpTerminationListener) list[i]).objectTerminated(
							DbgpTermination.this, e);
				}
			}
		})).start();
	}

	public void addTerminationListener(IDbgpTerminationListener listener) {
		listeners.add(listener);

	}

	public void removeTerminationListener(IDbgpTerminationListener listener) {
		listeners.remove(listener);
	}
}
