package org.eclipse.dlkt.javascript.dom.support.internal;

import org.eclipse.dltk.internal.javascript.reference.resolvers.IReferenceResolver;
import org.eclipse.dltk.internal.javascript.reference.resolvers.IResolverFactory;

public class DomResolverFactory implements IResolverFactory {

	public IReferenceResolver create() {
		return new DOMResolver();
	}

}
