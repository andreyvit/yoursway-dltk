package org.eclipse.dltk.ruby.debug;

import org.eclipse.dltk.debug.core.model.AtomicScriptType;
import org.eclipse.dltk.debug.core.model.CollectionScriptType;
import org.eclipse.dltk.debug.core.model.ComplexScriptType;
import org.eclipse.dltk.debug.core.model.IScriptType;
import org.eclipse.dltk.debug.core.model.IScriptTypeFactory;
import org.eclipse.dltk.debug.core.model.StringScriptType;

public class RubyTypeFactory implements IScriptTypeFactory {
	private static final String[] atomicTypes = { "Bignum", "FalseClass", //$NON-NLS-1$  //$NON-NLS-2$
			"Fixnum", "Float", "Integer", "NilClass", "Numeric", "Range", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
			"Regexp", "String", "Symbol", "TrueClass" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	private static final String[] collectionTypes = { "Array", "Hash", "Set" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	public RubyTypeFactory() {

	}

	public IScriptType buildType(String type) {
		if ("String".equals(type)) { //$NON-NLS-1$
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
