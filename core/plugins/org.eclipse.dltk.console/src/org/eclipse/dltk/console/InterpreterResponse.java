/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.console;

public class InterpreterResponse {
	private int state;
	private String content;
	
	public InterpreterResponse(int state, String content){
		this.state = state;
		this.content = content;
	}
	
	public int getState(){
		return state;
	}
	
	public String getContent() {
		return content;
	}
}
