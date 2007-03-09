package org.eclipse.dltk.dbgp;

public class DbgpServerException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DbgpServerException() {
		super();
	}

	public DbgpServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public DbgpServerException(String message) {
		super(message);
	}

	public DbgpServerException(Throwable cause) {
		super(cause);
	}
}
