/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.text.blocks;

import org.eclipse.dltk.ui.text.util.TextUtils;

public class Keyword {

	private final String text;

	private final Recognition recognition;

	private final KeywordRole role;

	public Keyword(String text, KeywordRole role, Recognition recognition) {
		this.text = text;
		this.role = role;
		this.recognition = recognition;
	}

	public KeywordRole getRole() {
		return role;
	}

	public String getText() {
		return text;
	}

	public String getPattern() {
		return recognition.decorateRegularExploression(TextUtils.Pattern_quote(text));
	}

	public Recognition getRecognition() {
		return recognition;
	}

	public String toString() {
		return text;
	}

}
