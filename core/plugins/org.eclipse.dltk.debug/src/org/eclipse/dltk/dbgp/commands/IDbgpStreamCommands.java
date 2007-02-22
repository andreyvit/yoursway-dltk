package org.eclipse.dltk.dbgp.commands;

import org.eclipse.dltk.dbgp.exceptions.DbgpException;

public interface IDbgpStreamCommands {
	final int DISABLE = 0;

	final int COPY = 1;

	final int REDIRECT = 2;

	boolean configureStdout(int value) throws DbgpException;

	boolean configureStderr(int value) throws DbgpException;
}
