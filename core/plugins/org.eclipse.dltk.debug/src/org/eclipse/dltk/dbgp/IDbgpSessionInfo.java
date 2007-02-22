package org.eclipse.dltk.dbgp;

import java.net.URI;

public interface IDbgpSessionInfo {
	String getApplicationId();

	String getIdeKey();

	String getSession();

	String getThreadId();

	String getParentAppId();

	String getLanguage();

	URI getFileUri();
}
