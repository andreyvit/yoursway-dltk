package org.eclipse.dltk.validators.internal.core.externalchecker;

public class CustomWildcard {

	private String letter;
	private String spattern;
	private String description;
	
	public CustomWildcard(String letter, String pattern, String description){
		this.letter = letter;
		this.spattern = pattern;
		this.description = description;
	}
	public void setLetter(String letter) {
		this.letter = letter;
	}
	public String getLetter() {
		return letter;
	}
	public void setSpattern(String spattern) {
		this.spattern = spattern;
	}
	public String getSpattern() {
		return spattern;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}

}
