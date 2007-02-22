package org.eclipse.dltk.tcl.internal.parsers.raw;

import java.util.ArrayList;
import java.util.List;

public class TclCommand extends TclElement {

	private List words;

	public TclCommand() {
		words = new ArrayList();
	}

	public void addWord(TclWord w) {
		if (w.empty()) {
			return;
		}
		w.setEnd(w.getStart() + w.length() - 1);
		words.add(w);
	}

	public List getWords() {
		return words;
	}

}
