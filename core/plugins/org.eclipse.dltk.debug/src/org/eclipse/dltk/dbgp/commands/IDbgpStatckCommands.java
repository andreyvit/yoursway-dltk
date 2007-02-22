package org.eclipse.dltk.dbgp.commands;

import java.util.List;

import org.eclipse.dltk.dbgp.IDbgpStackLevel;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;

public interface IDbgpStatckCommands {
	int getStackDepth() throws DbgpException;

	List getStackLevels() throws DbgpException;

	IDbgpStackLevel getStackLevel(int stackDepth) throws DbgpException;
}
