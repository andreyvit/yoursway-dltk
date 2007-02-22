package org.eclipse.dltk.ruby.typeinference.internal;

public interface INodeProcessor {
	
	void init(IContext globalContext);

	Class[] getRecognizedNodes();

}
