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
