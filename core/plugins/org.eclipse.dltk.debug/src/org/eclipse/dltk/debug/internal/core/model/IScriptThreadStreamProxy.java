package org.eclipse.dltk.debug.internal.core.model;

import java.io.InputStream;
import java.io.OutputStream;

public interface IScriptThreadStreamProxy {
	InputStream getStdin();

	OutputStream getStdout();

	OutputStream getStderr();
}
