package org.eclipse.dltk.validators.internal.core.externalchecker;


public class ExternalCheckerProblem {

	private String type;
	private String message;
	private int line;
	private String filename;
	
	public ExternalCheckerProblem(String type, String message, int line, String filename){
		this.type = type;
		this.message = message;
		this.setFilename(filename);
		
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

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}
}
