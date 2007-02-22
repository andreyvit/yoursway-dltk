package org.eclipse.dltk.dbgp.exceptions;

public class DbgpProtocolException extends DbgpException {

	private static final long serialVersionUID = 1L;

	public DbgpProtocolException() {
		super();
	}

	public DbgpProtocolException(String message, Throwable cause) {
		super(message, cause);
	}

	public DbgpProtocolException(String message) {
		super(message);
	}

	public DbgpProtocolException(Throwable cause) {
		super(cause);
	}
}
