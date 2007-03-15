package org.eclipse.dltk.javascript.core;

public class JavaScriptKeywords {

	private static final String[] fgKeywords = {
		"oneof",
		"break","else","new","var",		
		"case","finally","return","void"
		,"catch","for","switch","while"
		,"continue","function","this","with",
		"default","if","throw"
		,"delete" ,"in","try",
		"do","instanceof","typeof"
	};

	public static String[] getJavaScriptKeywords() {
		return fgKeywords;
	}
}
