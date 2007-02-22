package org.eclipse.dltk.dbgp.exceptions;

public class DbgpException extends Exception {

	private static final long serialVersionUID = 1L;

	public DbgpException() {
		super();
	}

	public DbgpException(String message, Throwable cause) {
		super(message, cause);
	}

	public DbgpException(String message) {
		super(message);
	}

	public DbgpException(Throwable cause) {
		super(cause);
	}
}
