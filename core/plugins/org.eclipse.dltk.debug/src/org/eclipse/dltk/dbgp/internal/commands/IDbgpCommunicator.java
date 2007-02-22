package org.eclipse.dltk.dbgp.internal.commands;

import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.DbgpRequest;
import org.w3c.dom.Element;

public interface IDbgpCommunicator {
	Element communicate(DbgpRequest request) throws DbgpException;
}
