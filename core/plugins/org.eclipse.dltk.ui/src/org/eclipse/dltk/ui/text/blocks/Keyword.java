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
