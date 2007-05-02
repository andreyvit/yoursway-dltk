package org.eclipse.dltk.dbgp.commands;

import org.eclipse.dltk.dbgp.IDbgpProperty;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;

public interface IDbgpPropertyCommands {
	/*
	 * -d stack depth (optional, debugger engine should assume zero if not
	 * provided) -c context id (optional, retrieved by context-names, debugger
	 * engine should assume zero if not provided) -n property long name
	 * (required) -m max data size to retrieve (optional) -t data type
	 * (optional) -p data page (optional, for arrays, hashes, objects, etc.) -k
	 * property key as retrieved in a property element, optional, used for
	 * property_get of children and property_value, required if it was provided
	 * by the debugger engine. -a property address as retrieved in a property
	 * element, optional, used for property_set/value, required if it was
	 * provided by the debugger engine.
	 */

	IDbgpProperty getPropertyByKey(String name, String key) throws DbgpException;
	
	IDbgpProperty getProperty(String name) throws DbgpException;

	IDbgpProperty getProperty(String name, int stackDepth) throws DbgpException;

	IDbgpProperty getProperty(String name, int stackDepth, int contextId)
			throws DbgpException;

	IDbgpProperty getProperty(String name, int stackDepth, int contextId,
			String dataType, String dataPage) throws DbgpException;

	boolean setProperty(IDbgpProperty property) throws DbgpException;

	boolean setProperty(String name, int stackDepth, String value)
			throws DbgpException;
}
