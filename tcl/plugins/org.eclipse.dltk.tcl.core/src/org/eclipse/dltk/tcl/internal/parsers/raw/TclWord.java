package org.eclipse.dltk.tcl.internal.parsers.raw;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Tcl word is a list of text pieces and substitutions.
 * 
 * @author fourdman
 * 
 */
public class TclWord extends TclElement {

	List contents;

	TclWord() {
		contents = new ArrayList();
	}

	public void add(String text) {
		Object o = null;
		if (contents.size() > 0)
			o = contents.get(contents.size() - 1);
		if (o != null && o instanceof String) {
			contents.set(contents.size() - 1, (String) o + text);
		} else
			contents.add(text);
	}

	public void add(char c) {
		add("" + c);
	}

	public void add(ISubstitution s) {
		contents.add(s);
	}

	public List getContents() {
		return contents;
	}

	public boolean empty() {
		for (Iterator iter = contents.iterator(); iter.hasNext();) {
			Object o = iter.next();
			if (o instanceof ISubstitution)
				return false;
			if (o instanceof String) {
				if (((String) o).trim().length() > 0)
					return false;
			}
		}
		return true;
	}

	public int length() {
		int result = 0;
		for (Iterator iter = contents.iterator(); iter.hasNext();) {
			Object o = iter.next();
			if (o instanceof TclElement) {
				TclElement el = (TclElement) o;
				result += el.getEnd() - el.getStart() + 1;
			} else if (o instanceof String) {
				result += ((String) o).length();
			}
		}
		return result;
	}

}
