package org.eclipse.dltk.ruby.tests.typeinference;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ruby.typeinference.RubyTypeInferencingUtils;
import org.eclipse.dltk.ti.ITypeInferencer;

public class TypeInferenceTest extends AbstractTypeInferencingTests {

	private static final String SRC_PROJECT = "typeinference";

	public TypeInferenceTest(String name) {
		super("org.eclipse.dltk.ruby.core.tests", name);
	}

	public void setUpSuite() throws Exception {
		PROJECT = setUpScriptProject(SRC_PROJECT);

		super.setUpSuite();
	}
	
	public void tearDownSuite() throws Exception {
		deleteProject(SRC_PROJECT);
		super.tearDownSuite();
	}

	public void executeTest(String folder, String name, ITypeInferencer inferencer, Collection assertions) throws Exception {
		ISourceModule cu = getSourceModule(SRC_PROJECT, folder, name);
		ModuleDeclaration rootNode = RubyTypeInferencingUtils.parseSource(cu);
		for (Iterator iter = assertions.iterator(); iter.hasNext();) {
			IAssertion assertion = (IAssertion) iter.next();
			assertion.check(rootNode, cu, inferencer);
		}
	}

}
