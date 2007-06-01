package org.eclipse.dltk.validators.internal.core.externalchecker;


public class ExternalCheckerProblem {

	private String type;
	private String message;
	private int line;
	
	public ExternalCheckerProblem(String type, String message, int line){
		this.type = type;
		this.message = message;
		
		if(line>=0){
			this.line = line;
		}else{
			this.line=0;
		}
	}
		
	public int getLineNumber() {
		return line;
	}
	
	public String getDescription() {
		return message;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
