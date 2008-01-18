package org.eclipse.dltk.tcl.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.ITextContentDescriber;

public class TclContentDescriber implements ITextContentDescriber {

	public int describe(Reader contents, IContentDescription description)
			throws IOException {
		IStatus status = TclLanguageToolkit.checkTclFile(contents);
		if (status.getSeverity() == IStatus.OK) {
			return VALID;
		}
		return INVALID;
	}

	public int describe(InputStream contents, IContentDescription description)
			throws IOException {
		IStatus status = TclLanguageToolkit.checkTclFile(new InputStreamReader(
				contents));
		if (status.getSeverity() == IStatus.OK) {
			return VALID;
		}
		return INVALID;
	}

	public QualifiedName[] getSupportedOptions() {
		return null;
	}
}
