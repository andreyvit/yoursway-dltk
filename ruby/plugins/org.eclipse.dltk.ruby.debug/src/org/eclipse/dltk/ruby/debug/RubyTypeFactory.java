package org.eclipse.dltk.ruby.debug;

import org.eclipse.dltk.debug.core.model.AtomicScriptType;
import org.eclipse.dltk.debug.core.model.CollectionScriptType;
import org.eclipse.dltk.debug.core.model.ComplexScriptType;
import org.eclipse.dltk.debug.core.model.IScriptType;
import org.eclipse.dltk.debug.core.model.IScriptTypeFactory;
import org.eclipse.dltk.debug.core.model.StringScriptType;

public class RubyTypeFactory implements IScriptTypeFactory {
	private static final String[] atomicTypes = { "NilClass", "Fixnum",
			"TrueClass", "FalseClass", "Integer", "Bignum" };
	private static final String[] collectionTypes = { "Array", "Hash" };

	public RubyTypeFactory() {

	}

	public IScriptType buildType(String type) {
		if ("String".equals(type)) {
			return new StringScriptType(type);
		}
		
		for (int i = 0; i < atomicTypes.length; ++i) {
			if (atomicTypes[i].equals(type)) {
				return new AtomicScriptType(type);
			}
		}

		for (int i = 0; i < collectionTypes.length; ++i) {
			if (collectionTypes[i].equals(type)) {
				return new CollectionScriptType(type);
			}
		}
		return new ComplexScriptType(type);
	}
}
