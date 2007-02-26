package org.eclipse.dltk.ruby.core.model;

import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.internal.core.ModelElement;
import org.eclipse.dltk.internal.core.SourceMethod;



public class FakeMethod extends SourceMethod {

	private String receiver;
	
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public FakeMethod(ModelElement parent, String name) {
		super(parent, name);
	}

	public IDLTKProject getScriptProject() {		
		return parent.getScriptProject();
	}
	
	

}
