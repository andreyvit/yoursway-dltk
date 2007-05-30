package org.eclipse.dltk.validators.internal.core.externalchecker;

public class Rule {
	private String rule;
	private String type;
	
	public Rule(String rule, String type){
		this.rule = rule;
		this.setType(type);
	}
	
	public String getDescription(){
		return rule;
	}
	
	public void setDescription(String s){
		this.rule = s;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
