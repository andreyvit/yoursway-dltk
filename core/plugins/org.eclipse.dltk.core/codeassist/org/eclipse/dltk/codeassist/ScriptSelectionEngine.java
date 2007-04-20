package org.eclipse.dltk.codeassist;

import java.util.Map;

import org.eclipse.dltk.internal.codeassist.impl.AssistOptions;
import org.eclipse.dltk.internal.codeassist.impl.Engine;
import org.eclipse.dltk.internal.compiler.lookup.LookupEnvironment;
import org.eclipse.dltk.internal.core.SearchableEnvironment;

public abstract class ScriptSelectionEngine extends Engine implements
		ISelectionEngine {

	public ScriptSelectionEngine() {
		super(null);
	}
	public void setEnvironment(SearchableEnvironment environment) {
		this.nameEnvironment = environment;
		this.lookupEnvironment = new LookupEnvironment(this, nameEnvironment);
	}

	public void setOptions(Map options) {
		this.options = new AssistOptions(options);
	}
}
