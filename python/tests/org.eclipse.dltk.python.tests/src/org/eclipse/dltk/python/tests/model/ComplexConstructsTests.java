package org.eclipse.dltk.python.tests.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import junit.framework.Test;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.tests.model.AbstractModelTests;
import org.eclipse.dltk.core.tests.util.ModelTestUtils;
import org.eclipse.dltk.python.tests.PythonTestsPlugin;

public class ComplexConstructsTests extends AbstractModelTests 
{
	private static final String PROJECT_NAME = "pytests_proj";
	private static final String ASSIGNMENT_TESTS_FILE_NAME = "assignment_tests.py";

	private static final String SCRIPT_SOURCE = "a,b=1,2;c=d=1;x=y=lambda z:z;";
	
	public ComplexConstructsTests(String name)
	{
		super(PythonTestsPlugin.PLUGIN_NAME, name); 
	}

	public static Test suite() 
	{
		return new Suite( ComplexConstructsTests.class);
	}

	public void setUpSuite() throws Exception
	{
		super.setUpSuite();
		IScriptProject sp = this.setUpScriptProject(PROJECT_NAME);
		IProject prj = sp.getProject();
		IFile testFile = prj.getFile(ASSIGNMENT_TESTS_FILE_NAME);
		InputStream source = new ByteArrayInputStream(SCRIPT_SOURCE.getBytes());
		testFile.create(source, true, new NullProgressMonitor());
	}
	public void tearDownSuite() throws Exception
	{
		super.tearDownSuite();
		deleteProject(PROJECT_NAME);
	}
	
	public void testAssignment() throws Exception
	{
		IScriptProject project = getScriptProject( PROJECT_NAME );
		ISourceModule module = this.getSourceModule( PROJECT_NAME, project.getPath().toString(), ASSIGNMENT_TESTS_FILE_NAME);
		IModelElement[] children = module.getChildren();
		ModelTestUtils.getAssertField(children, "a");
		ModelTestUtils.getAssertField(children, "b");
		ModelTestUtils.getAssertField(children, "c");
		ModelTestUtils.getAssertField(children, "d");
		ModelTestUtils.getAssertMethod(children, "x", 1);
		ModelTestUtils.getAssertMethod(children, "y", 1);
	}
}
