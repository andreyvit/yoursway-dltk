package org.eclipse.dltk.internal.javascript.typeinference;

public class OrReferenceWriteSecond extends OrReference {

	public void setChild(String key, IReference ref) {
		second.setChild(key, ref);
	}

	public IReference getChild(String key, boolean resolveLocals) {
		IReference child = one.getChild(key, resolveLocals);
		if (child != null)
			return child;
		IReference child2 = second.getChild(key, resolveLocals);
		if (child2 != null)
			return child2;		
		return null;
	}

	public OrReferenceWriteSecond(IReference one, IReference second) {
		super(one, second);
	}

	public void recordDelete(String fieldId) {
		second.recordDelete(fieldId);
	}

	public void setPrototype(IReference ref) {
		second.setPrototype(ref);
	}
}
