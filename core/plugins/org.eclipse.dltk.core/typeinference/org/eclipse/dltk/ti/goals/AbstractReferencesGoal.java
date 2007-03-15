package org.eclipse.dltk.ti.goals;

import org.eclipse.dltk.ti.IContext;

public class AbstractReferencesGoal extends AbstractGoal {

	private final String name;
	private final Object parentModelKey;
	
	
	public AbstractReferencesGoal(IContext context, String name, Object parentKey) {
		super(context);
		this.name = name;
		parentModelKey = parentKey;
	}

	public String getName() {
		return name;
	}

	public Object getParentModelKey() {
		return parentModelKey;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((parentModelKey == null) ? 0 : parentModelKey.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final AbstractReferencesGoal other = (AbstractReferencesGoal) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parentModelKey == null) {
			if (other.parentModelKey != null)
				return false;
		} else if (!parentModelKey.equals(other.parentModelKey))
			return false;
		return true;
	}
	
}
