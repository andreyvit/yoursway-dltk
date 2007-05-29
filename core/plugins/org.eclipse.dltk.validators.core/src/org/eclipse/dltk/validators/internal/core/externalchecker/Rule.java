package org.eclipse.dltk.validators.internal.core.externalchecker;

public class Rule {
	private String rule;
	
	public Rule(String rule){
		this.rule = rule;
	}
	
	public String getDescription(){
		return rule;
	}
	
	public void setDescription(String s){
		this.rule = s;
	}

}
