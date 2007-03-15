package org.eclipse.dltk.javascript.scriptdoc;

import org.eclipse.dltk.core.ISourceRange;

public class SourceRange implements ISourceRange {

	int offset;
	int length;
	public SourceRange(int i, int j) {
		this.offset=i;
		this.length=j;
	}

	public int getLength() {
		return length;
	}

	public int getOffset() {
		return offset;
	}

}
