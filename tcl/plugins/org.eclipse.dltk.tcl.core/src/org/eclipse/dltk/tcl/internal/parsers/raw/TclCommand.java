/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
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
