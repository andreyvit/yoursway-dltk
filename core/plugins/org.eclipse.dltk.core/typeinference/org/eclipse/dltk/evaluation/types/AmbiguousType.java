package org.eclipse.dltk.evaluation.types;

import org.eclipse.dltk.ti.types.IEvaluatedType;

public class AmbiguousType implements IEvaluatedType
{	
	
	private final IEvaluatedType[] possibleTypes;

	public AmbiguousType(IEvaluatedType[] possibleTypes) {
		this.possibleTypes = possibleTypes;
	}
	
	public String getTypeName( ) {
		StringBuffer result = new StringBuffer();
		result.append("Ambigous <");
		for (int i = 0; i < possibleTypes.length; i++) {
			IEvaluatedType type = possibleTypes[i];
			if (i > 0)
				result.append(", ");
			result.append(type.getTypeName());
		}
		result.append(">");
		return result.toString();
	}

	public IEvaluatedType[] getPossibleTypes() {
		return possibleTypes;
	}

	public boolean subtypeOf(IEvaluatedType type) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
