package org.eclipse.dltk.rhino.dbgp;

import java.io.IOException;

public class DbgpIOException extends DbgpException {
	private static final long serialVersionUID = 1L;

	public DbgpIOException(IOException e) {
		super(e);
	}
}
