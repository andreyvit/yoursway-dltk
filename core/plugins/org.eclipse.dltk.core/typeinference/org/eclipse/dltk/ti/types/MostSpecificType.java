/**
 * 
 */
package org.eclipse.dltk.ti.types;


/**
 * Represents most specific type in language. This class is just a symbol,
 * so user algorithms should convert it to contrete type himself if needed.
 */
public final class MostSpecificType implements IEvaluatedType {

	private static MostSpecificType instance;
	
	private MostSpecificType() {}
	
	public String getTypeName() {
		return null;
	}
	
	public static MostSpecificType getInstance() {
		if (instance == null)
			instance = new MostSpecificType();
		return instance;
	}

	public boolean subtypeOf(IEvaluatedType type) {
		return false;
	}
	
}