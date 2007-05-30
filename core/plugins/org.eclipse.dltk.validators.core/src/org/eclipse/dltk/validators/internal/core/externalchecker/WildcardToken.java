package org.eclipse.dltk.validators.internal.core.externalchecker;

public class WildcardToken {
	
	private String type;
	private Object value;
	
	public WildcardToken(String t, Object v){
		this.setType(t);
		this.setValue(v);
	}

	private void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

}
