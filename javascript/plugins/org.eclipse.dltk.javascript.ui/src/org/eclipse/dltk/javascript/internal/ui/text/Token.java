package org.eclipse.dltk.javascript.internal.ui.text;

public class Token {

	String context;
	int start;
	int textLength;
	int length;
	String tagName;
	
	public Token(String context, int start, int textLength, int length,
			String currentTagName) {
		this.context=context;
		this.start=start;
		this.tagName=currentTagName;
		this.textLength=textLength;
	}

}
