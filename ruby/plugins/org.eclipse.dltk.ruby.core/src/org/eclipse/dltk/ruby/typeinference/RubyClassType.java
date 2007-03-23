package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.evaluation.types.IClassType;
import org.eclipse.dltk.ruby.internal.parser.mixin.RubyMixinClass;
import org.eclipse.dltk.ti.types.ClassType;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class RubyClassType extends ClassType implements IClassType {

	private String modelKey;

	public RubyClassType(String modelKey) {
		this.modelKey = modelKey;
	}

	public boolean subtypeOf(IEvaluatedType type) {
		return false;
	}

	public String getModelKey() {
		return modelKey;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((modelKey == null) ? 0 : modelKey.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final RubyClassType other = (RubyClassType) obj;
		if (modelKey == null) {
			if (other.modelKey != null)
				return false;
		} else if (!modelKey.equals(other.modelKey))
			return false;
		return true;
	}
	
	

}
