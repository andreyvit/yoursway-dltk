package org.eclipse.dltk.dbgp.commands;

import java.util.List;
import java.util.Map;

import org.eclipse.dltk.dbgp.exceptions.DbgpException;

public interface IDbgpContextCommands {
	Map getContextNames(int stackDepth) throws DbgpException;

	List getContextProperties(int stackDepth) throws DbgpException;

	List getContextProperties(int stackDepth, int contextId)
			throws DbgpException;
}
