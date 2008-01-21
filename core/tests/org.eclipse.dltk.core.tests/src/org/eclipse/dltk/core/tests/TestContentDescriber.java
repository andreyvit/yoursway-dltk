package org.eclipse.dltk.core.tests;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.ITextContentDescriber;

public class TestContentDescriber implements ITextContentDescriber {

	public TestContentDescriber() {
		// TODO Auto-generated constructor stub
	}

	public int describe(Reader contents, IContentDescription description)
			throws IOException {
		return INVALID;
	}

	public int describe(InputStream contents, IContentDescription description)
			throws IOException {
		return INVALID;
	}

	public QualifiedName[] getSupportedOptions() {
		return null;
	}

}
