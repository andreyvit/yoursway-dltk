package org.eclipse.dltk.console;

import java.util.ArrayList;
import java.util.List;

public class ScriptConsoleHistory {
	private List lines;

	private int currLine;

	public ScriptConsoleHistory () {
		this.lines = new ArrayList();
		this.lines.add("");
		this.currLine = 0;
	}

	public void update(String line) {
		lines.set(currLine, line);
	}

	public void commit() {
		if (get().length() == 0)
			return;

		lines.set(lines.size() - 1, get());

		lines.add("");
		currLine = lines.size() - 1;
	}

	public boolean prev() {
		if (currLine > 0) {
			--currLine;
			return true;
		}

		return false;
	}

	public boolean next() {
		if (currLine < lines.size() - 1) {
			++currLine;
			return true;
		}

		return false;
	}

	public String get() {
		if (lines.isEmpty()) {
			return null;
		}

		return (String) lines.get(currLine);
	}
}
