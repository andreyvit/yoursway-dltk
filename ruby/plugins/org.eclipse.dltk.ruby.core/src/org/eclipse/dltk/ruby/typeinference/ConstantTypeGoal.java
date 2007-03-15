package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ti.IContext;
import org.eclipse.dltk.ti.goals.AbstractGoal;

public class ConstantTypeGoal extends AbstractGoal {

	private final int offset;
	private final String name;

	public ConstantTypeGoal(IContext context, int offset, String name) {
		super(context);
		this.offset = offset;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getOffset() {
		return offset;
	}

}
