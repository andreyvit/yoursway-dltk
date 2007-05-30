package org.eclipse.dltk.validators.internal.core.externalchecker;

public class CustomWildcard {

	private char letter;
	private String spattern;
	
	public CustomWildcard(char l, String s){
		this.letter = l;
		this.spattern = s;
	}
	public void setLetter(char letter) {
		this.letter = letter;
	}
	public char getLetter() {
		return letter;
	}
	public void setSpattern(String spattern) {
		this.spattern = spattern;
	}
	public String getSpattern() {
		return spattern;
	}

}
