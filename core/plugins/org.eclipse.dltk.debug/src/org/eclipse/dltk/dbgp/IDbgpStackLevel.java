package org.eclipse.dltk.dbgp;

import java.net.URI;

public interface IDbgpStackLevel {
	int getLevel();

	int getType();

	int getLineNumber();

	int getLineBegin();

	int getLineEnd();

	URI getFileURI();
}
