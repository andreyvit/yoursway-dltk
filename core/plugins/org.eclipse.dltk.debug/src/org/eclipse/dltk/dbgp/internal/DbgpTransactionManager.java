package org.eclipse.dltk.dbgp.internal;

public class DbgpTransactionManager {
	private static DbgpTransactionManager instance = new DbgpTransactionManager();

	public static DbgpTransactionManager getInstance() {
		return instance;
	}

	private Object lock = new Object();

	private int id;

	private DbgpTransactionManager() {
		this.id = 0;
	}

	public int generateId() {
		synchronized (lock) {
			return id++;
		}
	}
}
