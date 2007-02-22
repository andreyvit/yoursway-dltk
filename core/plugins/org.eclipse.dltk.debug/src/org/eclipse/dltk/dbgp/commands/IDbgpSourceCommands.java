package org.eclipse.dltk.dbgp.commands;

import java.net.URI;

import org.eclipse.dltk.dbgp.exceptions.DbgpException;

public interface IDbgpSourceCommands {
	String getSource(URI uri) throws DbgpException;

	String getSource(URI uri, int beginLine) throws DbgpException;

	String getSource(URI uri, int beginLine, int endLine) throws DbgpException;
}
