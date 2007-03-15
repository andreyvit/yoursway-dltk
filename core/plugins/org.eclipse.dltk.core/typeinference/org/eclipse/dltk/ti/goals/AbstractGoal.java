package org.eclipse.dltk.ti.goals;

import org.eclipse.dltk.ti.IContext;

public abstract class AbstractGoal implements IGoal {

	protected final IContext context;

	public AbstractGoal(IContext context) {
		this.context = context;
	}

	public IContext getContext() {
		return context;
	}

}
