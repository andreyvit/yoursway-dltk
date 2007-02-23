/**
 * 
 */
package org.eclipse.dltk.ruby.tests.typeinference;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ddp.ITypeInferencer;

public interface IAssertion {

	void check(ModuleDeclaration rootNode, ISourceModule cu, ITypeInferencer inferencer) throws Exception;

}
