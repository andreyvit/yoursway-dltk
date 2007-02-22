package org.eclipse.dltk.ruby.tests.typeinference;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ruby.typeinference.internal.RubyTypeModel;
import org.eclipse.dltk.typeinference.ASTCaching;
import org.eclipse.dltk.typeinference.IUnit;

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

	public void executeTest(String folder, String name, RubyTypeModel calculator) throws Exception {
		ISourceModule cu = getSourceModule(SRC_PROJECT, folder, name);
		IUnit unit = calculator.getUnit(cu);
		unit.getASTNode(ASTCaching.REPARSE);
		calculator.recalculate(unit);
	}

}
