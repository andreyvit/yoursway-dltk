package org.eclipse.dltk.internal.compiler.lookup;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.env.INameEnvironment;
import org.eclipse.dltk.internal.compiler.env.AccessRestriction;
import org.eclipse.dltk.internal.compiler.impl.ITypeRequestor;

public class LookupEnvironment {
	public LookupEnvironment(ITypeRequestor typeRequestor,
			INameEnvironment nameEnvironment) {
	}
	
	public void buildTypeScope(ModuleDeclaration unit,
			AccessRestriction accessRestriction) {
		//TODO: fix this. Don't simply remove this line!!!
		/* SourceModuleScope scope = */new SourceModuleScope(unit, this);		
	}
}
