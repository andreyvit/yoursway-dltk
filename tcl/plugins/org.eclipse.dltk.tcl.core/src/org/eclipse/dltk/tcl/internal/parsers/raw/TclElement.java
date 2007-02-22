package org.eclipse.dltk.tcl.internal.parsers.raw;

public abstract class TclElement {

	private int start, end;

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
}
