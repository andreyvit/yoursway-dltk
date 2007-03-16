package org.eclipse.dltk.ruby.tests.typehierarchy;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ITypeHierarchy;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.tests.model.AbstractDLTKSearchTests;
import org.eclipse.dltk.ruby.tests.Activator;


public class TypeHierarchyTests extends AbstractDLTKSearchTests implements IDLTKSearchConstants {
	private static final String PROJECT = "typehierarchy";

	public TypeHierarchyTests(String name) {
		super(Activator.PLUGIN_ID, name);
	}

	public static Suite suite() {
		return new Suite(TypeHierarchyTests.class);
	}

	public void setUpSuite() throws Exception {
		super.setUpSuite();
		up();
	}

	public void tearDownSuite() throws Exception {
		deleteProject(PROJECT);
		super.tearDownSuite();
	}
	
	private void up() throws Exception {
		if (SCRIPT_PROJECT == null) {
			SCRIPT_PROJECT = setUpScriptProject(PROJECT);
		}
	}
	public void testBuildHierarcy001() throws Exception {
		up();
		IType type = getSourceModule(PROJECT, "src", "src1.rb").getType("B");
//		search(type, DECLARATIONS, getSearchScope(PROJECT));
//		assertSearchResults("src/p3/X.tcl p3/Z$T2", resultCollector);	
		ITypeHierarchy hierarchy = type.newTypeHierarchy(new NullProgressMonitor());
		assertNotNull(hierarchy);
		IType[] types = hierarchy.getSuperclass(type);
		assertNotNull(types);
		
	}
}
