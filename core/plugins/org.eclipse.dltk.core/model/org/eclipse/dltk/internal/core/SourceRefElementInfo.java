package org.eclipse.dltk.internal.core;

import org.eclipse.dltk.core.ISourceRange;

class SourceRefElementInfo extends ModelElementInfo {
	protected int fSourceRangeStart, fSourceRangeEnd;

	public int getDeclarationSourceEnd() {
		return fSourceRangeEnd;
	}

	public int getDeclarationSourceStart() {
		return fSourceRangeStart;
	}
	
	protected ISourceRange getSourceRange() {
		return new SourceRange(fSourceRangeStart, fSourceRangeEnd - fSourceRangeStart + 1);
	}
	
	protected void setSourceRangeEnd(int end) {
		fSourceRangeEnd = end;
	}
	
	protected void setSourceRangeStart(int start) {
		fSourceRangeStart = start;
	}

}
