/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.formatting;

import java.util.Map;

public abstract class AbstractBlockMarker {
	protected int pos;
	private AbstractBlockMarker next;
	private String keyword;

	protected AbstractBlockMarker(String aKeyword, int aPos) {
		this.keyword = aKeyword;
		this.pos = aPos;
	}

	protected abstract void indentBeforePrint(IndentationState state);
	
	protected abstract void indentAfterPrint(IndentationState state);

	public int getPos() {
		return pos;
	}

	public AbstractBlockMarker getNext() {
		return next;
	}

	public String getKeyword() {
		return keyword;
	}

	protected void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setNext(AbstractBlockMarker next) {
		this.next = next;
	}

	public void appendIndentedLine(StringBuffer sb, IndentationState state, String originalLine, String strippedLine, Map options) {
		sb.append(state.getIndentationString(options));
		sb.append(strippedLine);
	}

	public void print() {
		System.out.println("Pos: " + pos + ", type: " + this.getClass().getName() + ", keyword: " + this.getKeyword()) ;
		if (next != null) {
			next.print();	
		}	
	}
}
