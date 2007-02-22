package org.eclipse.dltk.dbgp.commands;

import java.util.Map;

import org.eclipse.dltk.dbgp.exceptions.DbgpException;

public interface IDbgpDataTypeCommands {

	final int BOOL_TYPE = 0;
	final int INT_TYPE = 1;
	final int FLOAT_TYPE = 2;
	final int STRING_TYPE = 3;
	final int NULL_TYPE = 4;
	final int ARRAY_TYPE = 5;
	final int HASH_TYPE = 6;
	final int OBJECT_TYPE = 8;
	final int RESOURCE_TYPE = 9;

	Map getTypeMap() throws DbgpException;
}
