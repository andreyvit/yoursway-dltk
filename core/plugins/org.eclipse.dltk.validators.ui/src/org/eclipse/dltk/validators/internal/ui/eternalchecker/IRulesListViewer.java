package org.eclipse.dltk.validators.internal.ui.eternalchecker;

import org.eclipse.dltk.validators.internal.core.externalchecker.Rule;

public interface IRulesListViewer {

	public void addRule(Rule r);
	public void removeRule(Rule r);
	public void updateRule(Rule r);

}
