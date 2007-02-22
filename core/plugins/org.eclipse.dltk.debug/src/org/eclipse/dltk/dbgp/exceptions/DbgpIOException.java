package org.eclipse.dltk.dbgp.exceptions;

import java.io.IOException;

public class DbgpIOException extends DbgpException {
	private static final long serialVersionUID = 1L;

	public DbgpIOException(IOException e) {
		super(e);
	}
}
