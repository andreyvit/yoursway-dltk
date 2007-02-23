package org.eclipse.dltk.evaluation.types;

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
	
}
