package org.eclipse.dltk.dbgp.commands;

import org.eclipse.dltk.dbgp.IDbgpProperty;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;

public interface IDbgpExtendedCommands {
	final int DISABLE = 0;

	final int REDIRECT = 1;

	boolean configureStdin(int value) throws DbgpException;

	boolean sendStdin(String data) throws DbgpException;

	boolean makeBreak() throws DbgpException;

	IDbgpProperty evaluate(String string) throws DbgpException;

	IDbgpProperty expression(String expression) throws DbgpException;

	IDbgpProperty execute(String code) throws DbgpException;
}
