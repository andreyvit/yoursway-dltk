package org.eclipse.dltk.javascript.core.dom.support.sample;

import org.mozilla.javascript.ScriptableObject;

public class HelloHost extends ScriptableObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void jsFunction_Hello() {
		System.out.println("Hello world");
	}

	public String getClassName() {
		return "HelloHost";
	}
}
