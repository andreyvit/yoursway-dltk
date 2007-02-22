package org.eclipse.dltk.core.tests.model;

import junit.framework.Test;

import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;


public class ModelMembersTests extends AbstractModelTests {
	public ModelMembersTests(String name) {
		super(ModelTestsPlugin.PLUGIN_NAME, name);
	}

	public static Test suite() {
		return new Suite(ModelMembersTests.class);
	}

	public void setUpSuite() throws Exception {
		super.setUpSuite();
		setUpScriptProject("ModelMembers");
	}

	public void tearDownSuite() throws Exception {
		deleteProject("ModelMembers");
		super.tearDownSuite();
	}

	public void test001() throws ModelException {
		ISourceModule module = getSourceModule("ModelMembers", "src1", new Path("X.txt"));
		assertNotNull("No source module", module);
		IModelElement[] children = module.getChildren();
		assertNotNull("No children", children);
		assertEquals("Wrong size", 2, children.length);
		IType type = (IType) children[0];
		assertEquals("Class1", type.getElementName());
		assertEquals("Wrong size", 1, type.getChildren().length);
		IMember proc = (IMember) children[1];
		assertEquals("Procedure1", proc.getElementName());
	}
}
