/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.tests.model;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import junit.framework.Assert;
import junit.framework.Test;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IOpenable;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceReference;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.tests.model.ModifyingResourceTests;
import org.eclipse.dltk.internal.core.util.Util;
import org.eclipse.dltk.python.core.PythonNature;
import org.eclipse.dltk.python.tests.PythonTestsPlugin;


public class EncodingTests extends ModifyingResourceTests {
	private static final String[] PYTHON_NATURE = new String[] {
		PythonNature.NATURE_ID
	};
		
	IProject encodingProject;
	IScriptProject encodingScriptProject;
	IFile utf8File;
	ISourceReference utf8Source;

	private boolean setUpSuite;
	static String InterpreterEncoding = System.getProperty("file.encoding");
	static String wkspEncoding = InterpreterEncoding;

	public EncodingTests(String name) {
		super(  PythonTestsPlugin.PLUGIN_NAME, name );
	}
	
	public static Test suite() {
		return new Suite(EncodingTests.class);
	}
	// Use this static initializer to specify subset for tests
	// All specified tests which do not belong to the class are skipped...
	static {
//		TESTS_NAMES = new String[] { "testBug110576" };
//		TESTS_NUMBERS = new int[] { 2, 12 };
//		TESTS_RANGE = new int[] { 16, -1 };
	}

	public void setUpSuite() throws Exception {
		setUpSuite = true;
		super.setUpSuite();
	}

	public void tearDownSuite() throws Exception {
		setUpSuite = false;
		super.tearDownSuite();
	}
	protected void setUp() throws Exception {
//		if(!setUpSuite) {
//			setUpSuite();
//		}
		super.setUp();
		wkspEncoding = getWorkspaceRoot().getDefaultCharset();
		System.out.println("Encoding tests using Workspace charset: "+wkspEncoding+" and Interpreter charset: "+InterpreterEncoding);
		encodingScriptProject = setUpScriptProject("Encoding");
		this.encodingProject = (IProject) encodingScriptProject.getResource();
		utf8File = (IFile) this.encodingProject.findMember("src/testUTF8/Test.py");
		
	}

	/*
	 *  (non-Javadoc)
	 * Reset UTF-8 file and project charset to default.
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		this.encodingProject.setDefaultCharset(null, null);
		if (utf8File.exists()) utf8File.setCharset(null, null);
		if (utf8Source != null) ((IOpenable) utf8Source).close();
		encodingScriptProject.close();
		getWorkspaceRoot().setDefaultCharset(null, null);
		deleteProject("Encoding");
		super.tearDown();
	}

	void compareContents(ISourceModule cu, String encoding) throws ModelException {
		compareContents(cu, encoding, false);
	}

	void compareContents(ISourceModule cu, String encoding, boolean bom) throws ModelException {
		// Compare source strings
		String source = cu.getSource();
		String systemSourceRenamed = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(source);
		IFile file = (IFile) cu.getUnderlyingResource();
		String renamedContents = new String (Util.getResourceContentsAsCharArray(file));
		renamedContents = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(renamedContents);
		assertEquals("Encoded UTF-8 source should have been decoded the same way!", renamedContents, systemSourceRenamed);
		// Compare bytes array
		byte[] renamedSourceBytes = null;
		try {
			renamedSourceBytes = source.getBytes(encoding);
		}
		catch (UnsupportedEncodingException uue) {
		}
		assertNotNull("Unsupported encoding: "+encoding, renamedSourceBytes);
		byte[] renamedEncodedBytes = Util.getResourceContentsAsByteArray(file);
		int start = bom ? IContentDescription.BOM_UTF_8.length : 0;
		assertEquals("Wrong size of encoded string", renamedEncodedBytes.length-start, renamedSourceBytes.length);
		for (int i = 0, max = renamedSourceBytes.length; i < max; i++) {
			assertTrue("Wrong size of encoded character at " + i, renamedSourceBytes[i] == renamedEncodedBytes[i+start]);
		}
	}

	public boolean convertToIndependantLineDelimiter(File file) {
		return false; // don't convert to independant line delimiter as this make tests fail on linux
	}
	/**
	 * Check that the compilation unit is saved with the proper encoding.
	 */
	public void testCreateSourceModuleAndImportContainer() throws ModelException, CoreException {
		String savedEncoding = null;
		try {
			Preferences preferences = ResourcesPlugin.getPlugin().getPluginPreferences();
			
			savedEncoding = preferences.getString(ResourcesPlugin.PREF_ENCODING);
			String encoding = "UTF-8";
			preferences.setValue(ResourcesPlugin.PREF_ENCODING, encoding);
			
			ResourcesPlugin.getPlugin().savePluginPreferences();

			IScriptProject newProject = createScriptProject("P", PYTHON_NATURE, new String[] { "" });
			IScriptFolder pkg = getScriptFolder("P", "", new Path(""));
			String source = 
				"class A:\r\n" +
				"	def main(*args) :\r\n" +
				"		print(\"\u00e9\");\r\n" +
				"";
			ISourceModule cu= pkg.createSourceModule("A.py", source, false, new NullProgressMonitor());
			assertCreation(cu);
			cu.rename("B.py", true, new NullProgressMonitor());
			cu = pkg.getSourceModule("B.py");
			cu.rename("A.py", true, new NullProgressMonitor());
			cu = pkg.getSourceModule("A.py");
			byte[] tab = null;
			try {
				tab = cu.getSource().getBytes(encoding);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
			byte[] encodedContents = Util.getResourceContentsAsByteArray(newProject.getProject().getWorkspace().getRoot().getFile(cu.getPath()));
			assertEquals("wrong size of encoded string", tab.length, encodedContents.length);
			for (int i = 0, max = tab.length; i < max; i++) {
				assertTrue("wrong size of encoded character at" + i, tab[i] == encodedContents[i]);
			}
		} finally {
			deleteProject("P");
			Preferences preferences = ResourcesPlugin.getPlugin().getPluginPreferences();
			preferences.setValue(ResourcesPlugin.PREF_ENCODING, savedEncoding);
			ResourcesPlugin.getPlugin().savePluginPreferences();
		}
	}	

	/*
	##################
	#	Test with compilation units
	##################
	/*
	 * Get compilation unit source on a file written in UTF-8 charset using specific UTF-8 encoding for file.
	 * Verify first that source is the same than file contents read using UTF-8 encoding...
	 * Also verify that bytes array converted back to UTF-8 is the same than the file bytes array.
	 */
	public void test001() throws ModelException, CoreException, UnsupportedEncodingException {

		// Set file encoding
		String encoding = "UTF-8";
		utf8File.setCharset(encoding, null);
		
		// Get source and compare with file contents
		Assert.assertTrue("", utf8File.exists());
		utf8Source = getSourceModule(utf8File.getFullPath().toString());
		String source = utf8Source.getSource();
		String systemSource = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(source);
		String encodedContents = new String (Util.getResourceContentsAsCharArray(utf8File));
		encodedContents = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(encodedContents);
		assertEquals("Encoded UTF-8 source should have been decoded the same way!", encodedContents, systemSource);

		// Now compare bytes array
		byte[] sourceBytes = source.getBytes(encoding);
		byte[] encodedBytes = Util.getResourceContentsAsByteArray(utf8File);
		assertEquals("Wrong size of encoded string", encodedBytes.length, sourceBytes.length);
		for (int i = 0, max = sourceBytes.length; i < max; i++) {
			assertTrue("Wrong size of encoded character at " + i, sourceBytes[i] == encodedBytes[i]);
		}
	}	

	/*
	 * Get compilation unit source on a file written in UTF-8 charset using UTF-8 encoding for project.
	 * Verify first that source is the same than file contents read using UTF-8 encoding...
	 * Also verify that bytes array converted back to UTF-8 is the same than the file bytes array.
	 */
	public void test002() throws ModelException, CoreException, UnsupportedEncodingException {

		// Set project encoding
		String encoding = "UTF-8";
		this.encodingProject.setDefaultCharset(encoding, null);
		
		// Get source and compare with file contents
		utf8Source = getSourceModule(utf8File.getFullPath().toString());
		String source = utf8Source.getSource();
		String systemSource = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(source);
		String encodedContents = new String (Util.getResourceContentsAsCharArray(utf8File));
		encodedContents = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(encodedContents);
		assertEquals("Encoded UTF-8 source should have been decoded the same way!", encodedContents, systemSource);

		// Now compare bytes array
		byte[] sourceBytes = source.getBytes(encoding);
		byte[] encodedBytes = Util.getResourceContentsAsByteArray(utf8File);
		assertEquals("Wrong size of encoded string", encodedBytes.length, sourceBytes.length);
		for (int i = 0, max = sourceBytes.length; i < max; i++) {
			assertTrue("Wrong size of encoded character at " + i, sourceBytes[i] == encodedBytes[i]);
		}
	}	

	/*
	 * Get compilation unit source on a file written in UTF-8 charset using workspace default encoding.
	 * Verify that source is the same than file contents read using workspace default encoding...
	 * Also verify that bytes array converted back to wokrspace default encoding is the same than the file bytes array.
	 * Do not compare array contents in case of Interpreter default encoding equals to "ASCII" as meaningful bit 7 is lost
	 * during first conversion...
	 */
	public void test003() throws ModelException, CoreException, UnsupportedEncodingException {

		// Get source and compare with file contents
		utf8Source = getSourceModule(utf8File.getFullPath().toString());
		String source = utf8Source.getSource();
		String systemSource = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(source);
		String encodedContents = new String (Util.getResourceContentsAsCharArray(utf8File));
		encodedContents = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(encodedContents);
		assertEquals("Encoded UTF-8 source should have been decoded the same way!", encodedContents, systemSource);
			
		// Now compare bytes array
		byte[] sourceBytes = source.getBytes(wkspEncoding);
		byte[] encodedBytes = Util.getResourceContentsAsByteArray(utf8File);
		assertEquals("Wrong size of encoded string", encodedBytes.length, sourceBytes.length);
		// Do not compare arrays contents as system encoding may have lost meaningful bit 7 during convertion...)
//		if (!"ASCII".equals(InterpreterEncoding)) {
//			for (int i = 0, max = sourceBytes.length; i < max; i++) {
//				assertTrue("Wrong size of encoded character at " + i, sourceBytes[i] == encodedBytes[i]);
//			}
//		}
	}

	/*
	 * Get compilation unit source on a file written in UTF-8 charset using an encoding
	 * for file different than Interpreter default one.
	 * Verify that source is different than file contents read using Interpreter default encoding...
	 */
	public void test004() throws ModelException, CoreException {

		// Set file encoding
		String encoding = "UTF-8".equals(InterpreterEncoding) ? "Cp1252" : "UTF-8";
		utf8File.setCharset(encoding, null);
		
		// Get source and compare with file contents
		utf8Source = getSourceModule(utf8File.getFullPath().toString());
		String source = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(utf8Source.getSource());
		String encodedContents = new String (Util.getResourceContentsAsCharArray(utf8File, InterpreterEncoding));
		encodedContents = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(encodedContents);
		assertFalse("Sources should not be the same as they were decoded with different encoding!", encodedContents.equals(source));
	}

	/*
	 * Get compilation unit source on a file written in UTF-8 charset using an encoding
	 * for project different than Interpreter default one.
	 * Verify that source is different than file contents read using Interpreter default encoding...
	 */
	public void test005() throws ModelException, CoreException {

		// Set project encoding
		String encoding = "UTF-8".equals(InterpreterEncoding) ? "Cp1252" : "UTF-8";
		this.encodingProject.setDefaultCharset(encoding, null);
		
		// Get source and compare with file contents
		utf8Source = getSourceModule(utf8File.getFullPath().toString());
		String source = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(utf8Source.getSource());
		String encodedContents = new String (Util.getResourceContentsAsCharArray(utf8File, InterpreterEncoding));
		encodedContents = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(encodedContents);
		assertFalse("Sources should not be the same as they were decoded with different encoding!", encodedContents.equals(source));
	}	

	/*
	 * Get compilation unit source on a file written in UTF-8 charset using workspace default encoding.
	 * Verify that source is different than file contents read using Interpreter default encoding or another one
	 * if Interpreter and Workspace default encodings are identical...
	 */
	public void test006() throws ModelException, CoreException {

		// Set encoding different than workspace default one
		String encoding = wkspEncoding.equals(InterpreterEncoding) ? ("UTF-8".equals(wkspEncoding) ? "Cp1252" : "UTF-8") : InterpreterEncoding;

		// Get source and compare with file contents
		utf8Source = getSourceModule(utf8File.getFullPath().toString());
		String source = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(utf8Source.getSource());
		String encodedContents = new String (Util.getResourceContentsAsCharArray(utf8File, encoding));
		encodedContents = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(encodedContents);
		assertFalse("Sources should not be the same as they were decoded with different encoding!", encodedContents.equals(source));
	}

	/*
	##############
	#	Tests with class file
	##############
	/* Same config than test001  */
	public void test011() throws ModelException, CoreException, UnsupportedEncodingException {

		// Set file encoding
		String encoding = "UTF-8";
		utf8File.setCharset(encoding, null);
		
		// Get source and compare with file contents
		utf8Source = getClassFile("Encoding" , "bins", "testUTF8", "Test.class"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		String source = utf8Source.getSource();
		String systemSource = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(source);
		String encodedContents = new String (Util.getResourceContentsAsCharArray(utf8File));
		encodedContents = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(encodedContents);
		assertEquals("Encoded UTF-8 source should have been decoded the same way!", encodedContents, systemSource);

		// Now compare bytes array
		byte[] sourceBytes = source.getBytes(encoding);
		byte[] encodedBytes = Util.getResourceContentsAsByteArray(utf8File);
		assertEquals("Wrong size of encoded string", encodedBytes.length, sourceBytes.length);
		for (int i = 0, max = sourceBytes.length; i < max; i++) {
			assertTrue("Wrong size of encoded character at " + i, sourceBytes[i] == encodedBytes[i]);
		}
	}	

	private ISourceReference getClassFile(String string, String string2, String string3, String string4) throws ModelException{
		return getSourceModule(string, string2, string3+"/"+string4);
	}

	/* Same config than test002  */
	public void test012() throws ModelException, CoreException, UnsupportedEncodingException {

		// Set project encoding
		String encoding = "UTF-8";
		this.encodingProject.setDefaultCharset(encoding, null);
		
		// Get source and compare with file contents
		utf8Source = getClassFile("Encoding" , "bins", "testUTF8", "Test.class"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		String source = utf8Source.getSource();
		String systemSource = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(source);
		String encodedContents = new String (Util.getResourceContentsAsCharArray(utf8File));
		encodedContents = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(encodedContents);
		assertEquals("Encoded UTF-8 source should have been decoded the same way!", encodedContents, systemSource);

		// Now compare bytes array
		byte[] sourceBytes = source.getBytes(encoding);
		byte[] encodedBytes = Util.getResourceContentsAsByteArray(utf8File);
		assertEquals("Wrong size of encoded string", encodedBytes.length, sourceBytes.length);
		for (int i = 0, max = sourceBytes.length; i < max; i++) {
			assertTrue("Wrong size of encoded character at " + i, sourceBytes[i] == encodedBytes[i]);
		}
	}	

	/* Same config than test003  */
	public void test013() throws ModelException, CoreException, UnsupportedEncodingException {

		// Get source and compare with file contents
		utf8Source = getClassFile("Encoding" , "bins", "testUTF8", "Test.class"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		String source = utf8Source.getSource();
		String systemSource = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(source);
		String encodedContents = new String (Util.getResourceContentsAsCharArray(utf8File));
		encodedContents = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(encodedContents);
		assertEquals("Encoded UTF-8 source should have been decoded the same way!", encodedContents, systemSource);
			
		// Now compare bytes array
		byte[] sourceBytes = source.getBytes(wkspEncoding);
		byte[] encodedBytes = Util.getResourceContentsAsByteArray(utf8File);
		assertEquals("Wrong size of encoded string", encodedBytes.length, sourceBytes.length);
		// Do not compare arrays contents as system encoding may have lost meaningful bit 7 during convertion...)
//		if (!"ASCII".equals(InterpreterEncoding)) {
//			for (int i = 0, max = sourceBytes.length; i < max; i++) {
//				assertTrue("Wrong size of encoded character at " + i, sourceBytes[i] == encodedBytes[i]);
//			}
//		}
	}

	/* Same config than test004  */
	public void test014() throws ModelException, CoreException {

		// Set file encoding
		String encoding = "UTF-8".equals(InterpreterEncoding) ? "Cp1252" : "UTF-8";
		utf8File.setCharset(encoding, null);
		
		// Get source and compare with file contents
		utf8Source = getClassFile("Encoding" , "bins", "testUTF8", "Test.class"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		String source = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(utf8Source.getSource());
		String encodedContents = new String (Util.getResourceContentsAsCharArray(utf8File, InterpreterEncoding));
		encodedContents = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(encodedContents);
		assertFalse("Sources should not be the same as they were decoded with different encoding!", encodedContents.equals(source));
	}

	/* Same config than test005  */
	public void test015() throws ModelException, CoreException {

		// Set project encoding
		String encoding = "UTF-8".equals(InterpreterEncoding) ? "Cp1252" : "UTF-8";
		this.encodingProject.setDefaultCharset(encoding, null);
		
		// Get source and compare with file contents
		utf8Source = getClassFile("Encoding" , "bins", "testUTF8", "Test.class"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		String source = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(utf8Source.getSource());
		String encodedContents = new String (Util.getResourceContentsAsCharArray(utf8File, InterpreterEncoding));
		encodedContents = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(encodedContents);
		assertFalse("Sources should not be the same as they were decoded with different encoding!", encodedContents.equals(source));
	}	

	/* Same config than test006  */
	public void test016() throws ModelException, CoreException {

		// Set encoding different than workspace default one
		String encoding = wkspEncoding.equals(InterpreterEncoding) ? ("UTF-8".equals(wkspEncoding) ? "Cp1252" : "UTF-8") : InterpreterEncoding;

		// Get source and compare with file contents
		utf8Source = getSourceModule("Encoding" , "bins", "testUTF8/Test.class"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
		String source = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(utf8Source.getSource());
		String encodedContents = new String (Util.getResourceContentsAsCharArray(utf8File, encoding));
		encodedContents = org.eclipse.dltk.core.tests.util.Util.convertToIndependantLineDelimiter(encodedContents);
		assertFalse("Sources should not be the same as they were decoded with different encoding!", encodedContents.equals(source));
	}

	/*
	###############################
	#	Tests with archive file and source attached in zip file
	###############################
	/**
	 * Get class file from archive file with an associated source written in UTF-8 charset using no specific encoding for file.
	 * Verification is done by comparing source with file contents read directly with Interpreter encoding...
	 */
	public void test021() throws ModelException, CoreException {

		// Get class file and compare source
		IProjectFragment root = getProjectFragment("Encoding", "testUTF8.jar");
		utf8Source = root.getScriptFolder("testUTF8").getSourceModule("Test.class");
		assertNotNull(utf8Source);
		String source = utf8Source.getSource();
		assertNotNull(source);
		String encodedContents = new String (Util.getResourceContentsAsCharArray(utf8File, InterpreterEncoding));
		assertSourceEquals("Encoded UTF-8 source should have been decoded the same way!", source, encodedContents);

		// Cannot compare bytes array without encoding as we're dependent of linux/windows os for new lines delimiter
	}

	/*
	 * Get class file from jar file with an associated source written in UTF-8 charset using specific UTF-8 encoding for project.
	 * Verification is done by comparing source with file contents read directly with UTF-8 encoding...
	 */
	public void test022() throws ModelException, CoreException {

		// Set project encoding
		String encoding = "UTF-8".equals(InterpreterEncoding) ? "Cp1252" : "UTF-8";
		this.encodingProject.setDefaultCharset(encoding, null);

		// Get class file and compare source (should not be the same as modify charset on zip file has no effect...)
		IProjectFragment root = getProjectFragment("Encoding", "testUTF8.jar");
		utf8Source = root.getScriptFolder("testUTF8").getSourceModule("Test.class");
		assertNotNull(utf8Source);
		String source = utf8Source.getSource();
		assertNotNull(source);
		String encodedContents = new String (Util.getResourceContentsAsCharArray(utf8File, encoding));
		assertFalse("Sources should not be the same as they were decoded with different encoding!", encodedContents.equals(source));
	}

	/*
	 * Get class file from archive file with an associated source written in UTF-8 charset using specific UTF-8 encoding for file.
	 * Verification is done by comparing source with file contents read directly with UTF-8 encoding...
	 */
	public void test023() throws ModelException, CoreException {

		// Set file encoding
		String encoding = "UTF-8".equals(InterpreterEncoding) ? "Cp1252" : "UTF-8";
		IFile zipFile = (IFile) this.encodingProject.findMember("testUTF8.zip"); //$NON-NLS-1$
		assertNotNull("Cannot find class file!", zipFile);
		zipFile.setCharset(encoding, null);

		// Get class file and compare source (should not be the same as modify charset on zip file has no effect...)
		IProjectFragment root = getProjectFragment("Encoding", "testUTF8.jar");
		utf8Source = root.getScriptFolder("testUTF8").getSourceModule("Test.class");
		assertNotNull(utf8Source);
		String source = utf8Source.getSource();
		assertNotNull(source);
		String encodedContents = new String (Util.getResourceContentsAsCharArray(utf8File, encoding));
		assertFalse("Sources should not be the same as they were decoded with different encoding!", encodedContents.equals(source));
		
		// Reset zip file encoding
		zipFile.setCharset(null, null);
	}

	/**
	 * Test for bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=55930.
	 * Verify Buffer.save(IProgressMonitor, boolean) method.
	 */
	public void test030() throws ModelException, CoreException {
		ISourceModule workingCopy = null;
		try {
			String encoding = "UTF-8";
			this.createScriptProject("P", PYTHON_NATURE, new String[] {""});
			String initialContent = "/**\n"+
				" */\n"+
				"public class Test {}";
			IFile file = this.createFile("P/Test.py", initialContent);
			file.setCharset(encoding, null);
			ISourceModule cu = this.getSourceModule("P/Test.py"); 
			
			// Modif direct the buffer
			String firstModif = "/**\n"+
				" * Caract?res exotiques:\n"+
				" * ?|#|?|?|?|?|?|?|?|?|??\n"+
				" */\n"+
				"public class Test {}";
			cu.getBuffer().setContents(firstModif);
			cu.getBuffer().save(null, true);
			String source = cu.getBuffer().getContents();
			
			// Compare strings and bytes arrays
			String encodedContents = new String (Util.getResourceContentsAsCharArray(file, encoding));
			assertEquals("Encoded UTF-8 source should have been decoded the same way!", encodedContents, source);
			byte[] sourceBytes = source.getBytes(encoding);
			byte[] encodedBytes = Util.getResourceContentsAsByteArray(file);
			assertEquals("Wrong size of encoded string", encodedBytes.length, sourceBytes.length);
			for (int i = 0, max = sourceBytes.length; i < max; i++) {
				assertTrue("Wrong size of encoded character at " + i, sourceBytes[i] == encodedBytes[i]);
			}
		} catch (UnsupportedEncodingException e) {
		} finally {
			this.stopDeltas();
			if (workingCopy != null) {
				workingCopy.discardWorkingCopy();
			}
			this.deleteProject("P");
		}

	}

	/**
	 * Test for bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=55930.
	 * Verify CommitWorkingCopyOperation.executeOperation() method.
	 */
	public void test031() throws ModelException, CoreException {
		ISourceModule workingCopy = null;
		try {
			String encoding = "UTF-8";
			this.createScriptProject("P", PYTHON_NATURE, new String[] {""});
			String initialContent = "/**\n"+
				" */\n"+
				"public class Test {}";
			IFile file = this.createFile("P/Test.py", initialContent);
			file.setCharset(encoding, null);
			ISourceModule cu = this.getSourceModule("P/Test.py"); 
			
			// Modif using working copy
			workingCopy = cu.getWorkingCopy(null);
			String secondModif = "/**\n"+
				" * Caract?res exotiques:\n"+
				" * ?|#|?|?|?|?|?|?|?|?|??\n"+
				" * Autres caract?res exotiques:\n"+
				" * ?|?|?|?|?|?\n"+
				" */\n"+
				"public class Test {}";
			workingCopy.getBuffer().setContents(secondModif);
			workingCopy.commitWorkingCopy(true, null);
			String source = workingCopy.getBuffer().getContents();
			
			// Compare strings and bytes arrays
			String encodedContents = new String (Util.getResourceContentsAsCharArray(file));
			assertEquals("Encoded UTF-8 source should have been decoded the same way!", encodedContents, source);
			byte[] sourceBytes = source.getBytes(encoding);
			byte[] encodedBytes = Util.getResourceContentsAsByteArray(file);
			assertEquals("Wrong size of encoded string", encodedBytes.length, sourceBytes.length);
			for (int i = 0, max = sourceBytes.length; i < max; i++) {
				assertTrue("Wrong size of encoded character at " + i, sourceBytes[i] == encodedBytes[i]);
			}
		} catch (UnsupportedEncodingException e) {
		} finally {
			this.stopDeltas();
			if (workingCopy != null) {
				workingCopy.discardWorkingCopy();
			}
			this.deleteProject("P");
		}
	}

	/*
	 * Get compilation unit source on a file written in UTF-8 BOM charset using default charset.
	 * Verify first that source is the same than UTF-8 file contents read using UTF-8 encoding...
	 */
	public void test032() throws ModelException, CoreException {

		// Set file encoding
		String encoding = "UTF-8";
		utf8File.setCharset(encoding, null);
		
		// Get source and compare with file contents
		utf8Source = getSourceModule(utf8File.getFullPath().toString());
		String source = utf8Source.getSource();

		// Get source and compare with file contents
		IFile bomFile = (IFile) this.encodingProject.findMember("src/testUTF8BOM/Test.py");
		ISourceReference bomSourceRef = getSourceModule(bomFile.getFullPath().toString());
		String bomSource = bomSourceRef.getSource();
		assertEquals("BOM UTF-8 source should be idtentical than UTF-8!", source, bomSource);
	}	
	
//TODO: Source engine
//	/*
//	 * Ensures that a file is reindexed when the encoding changes.
//	 * (regression test for bug 68585 index is out of date after encoding change)
//	 */
//	public void test033() throws CoreException {
//		try {
//			createFolder("/Encoding/src/test68585");
//			final String encoding = "UTF-8".equals(wkspEncoding) ? "Cp1252" : "UTF-8";
//			getWorkspace().run(new IWorkspaceRunnable() {
//				public void run(IProgressMonitor monitor) throws CoreException {
//					// use a different encoding to make the file unreadable
//					IFile file = null;
//					try {
//						file = createFile(
//							"/Encoding/src/test68585/X.py", 
//							"package  test68585;\n" +
//							"public class X {\n" +
//							"}\n" +
//							"class Y\u00F4 {}",
//							encoding);
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//						return;
//					}
//					file.setCharset(wkspEncoding, null);
//				}
//			}, 
//			null);
//			
//			IDLTKSearchScope scope = SearchEngine.createWorkspaceScope();
//			DLTKSearchResultCollector resultCollector = new DLTKSearchResultCollector();
//			search(
//				"Y\u00F4", 
//				IDLTKSearchConstants.TYPE,
//				IDLTKSearchConstants.DECLARATIONS,
//				scope, 
//				resultCollector);
//			assertSearchResults("Should not get any result", "", resultCollector);
//			
//			// change encoding so that file is readable
//			getFile("/Encoding/src/test68585/X.py").setCharset(encoding, null);
//			search(
//				"Y\u00F4", 
//				IDLTKSearchConstants.TYPE,
//				IDLTKSearchConstants.DECLARATIONS,
//				scope, 
//				resultCollector);
//			assertSearchResults(
//				"Should have been reindexed", 
//				"src/test68585/X.py test68585.Y\u00F4 [Y\u00F4]",
//				resultCollector);
//		} finally {
//			deleteFolder("/Encoding/src/test68585");
//		}
//	}

	/**
	 * Bug 66898: refactor-rename: encoding is not preserved
	 * @see "http://bugs.eclipse.org/bugs/show_bug.cgi?id=66898"
	 */
	public void testBug66898() throws ModelException, CoreException {

		// Set file encoding
		String encoding = "UTF-8".equals(InterpreterEncoding) ? "Cp1252" : "UTF-8";
		IFile file = (IFile) this.encodingProject.findMember("src/testBug66898/Test.py");
		file.setCharset(encoding, null);
		String fileName = file.getName();
		ISourceModule cu = getSourceModule(file.getFullPath().toString());
		createFolder("/Encoding/src/tmp");
		IScriptFolder packFrag = getScriptFolder("Encoding", "src", new Path("tmp"));
		
		try {
			// Move file
			cu.move(packFrag, null, null, false, null);
			ISourceModule destSource = packFrag.getSourceModule(fileName);
			IFile destFile = (IFile) destSource.getUnderlyingResource();
			assertEquals("Moved file should keep encoding", encoding, destFile.getCharset());
	
			// Get source and compare with file contents
			compareContents(destSource, encoding);
			
			// Rename file
			destSource.rename("TestUTF8.py", false, null);
			ISourceModule renamedSource = packFrag.getSourceModule("TestUTF8.py");
			IFile renamedFile = (IFile) renamedSource.getUnderlyingResource();
			assertEquals("Moved file should keep encoding", encoding, renamedFile.getCharset());
			
			// Compare contents again
			compareContents(renamedSource, encoding);
		}
		finally {
			// Delete temporary folder
			//renamedFile.move(utf8File.getFullPath(), false, null);
			//assertEquals("Moved file should keep encoding", encoding, utf8File.getCharset());
			deleteFolder("/Encoding/src/tmp");
		}
	}	
	public void testBug66898b() throws ModelException, CoreException {

		// Set file encoding
		final String encoding = "UTF-8".equals(InterpreterEncoding) ? "Cp1252" : "UTF-8";
		final IFile file = (IFile) this.encodingProject.findMember("src/testBug66898b/Test.py");
		file.setCharset(encoding, null);
		final String fileName = file.getName();
		final IScriptFolder srcFolder = getScriptFolder("Encoding", "src", "testBug66898b");
		createFolder("/Encoding/src/tmp");
		final IScriptFolder tmpFolder = getScriptFolder("Encoding", "src", "tmp");
	
		try {
			// Copy file
			IWorkspaceRunnable copy = new IWorkspaceRunnable() {
				public void run(IProgressMonitor monitor) throws CoreException {
					ISourceModule cu = getSourceModule(file.getFullPath().toString());
					cu.copy(tmpFolder, null, null, true, null);
					cu.close(); // purge buffer contents from cache
					ISourceModule dest = tmpFolder.getSourceModule(fileName);
					IFile destFile = (IFile) dest.getUnderlyingResource();
					assertEquals("Copied file should keep encoding", encoding, destFile.getCharset());
			
					// Get source and compare with file contents
					compareContents(dest, encoding);
				}
			};
			DLTKCore.run(copy, null);
	
			// Rename file
			IWorkspaceRunnable rename = new IWorkspaceRunnable() {
				public void run(IProgressMonitor monitor) throws CoreException {
					ISourceModule cu = tmpFolder.getSourceModule(fileName);
					cu.rename("Renamed.py", true, null);
					cu.close(); // purge buffer contents from cache
					ISourceModule ren = tmpFolder.getSourceModule("Renamed.py");
					IFile renFile = (IFile) ren.getUnderlyingResource();
					assertEquals("Renamed file should keep encoding", encoding, renFile.getCharset());
			
					// Get source and compare with file contents
					compareContents(ren, encoding);
				}
			};
			DLTKCore.run(rename, null);
	
			// Move file
			IWorkspaceRunnable move = new IWorkspaceRunnable() {
				public void run(IProgressMonitor monitor) throws CoreException {
					ISourceModule cu = tmpFolder.getSourceModule("Renamed.py");
					cu.move(srcFolder, null, null, true, null);
					cu.close(); // purge buffer contents from cache
					ISourceModule moved = srcFolder.getSourceModule("Renamed.py");
					IFile movedFile = (IFile) moved.getUnderlyingResource();
					assertEquals("Renamed file should keep encoding", encoding, movedFile.getCharset());
			
					// Get source and compare with file contents
					compareContents(moved, encoding);
				}
			};
			DLTKCore.run(move, null);
		}
		finally {
			// Delete temporary file and folder
			ISourceModule cu = srcFolder.getSourceModule("Renamed.py");
			if (cu.exists()) cu.delete(true, null);
			deleteFolder("/Encoding/src/tmp");
		}
	}	

	private IScriptFolder getScriptFolder(String string, String string2, String string3) throws ModelException{
		return getScriptFolder(string, string2, new Path(string3));
	}

	/**
	 * Bug 70598: [Encoding] ArrayIndexOutOfBoundsException while testing BOM on *.txt files
	 * @see "https://bugs.eclipse.org/bugs/show_bug.cgi?id=70598"
	 */
	public void testBug70598() throws ModelException, CoreException, IOException {

		// Create empty file
		IFile emptyFile = createFile("/Encoding/src/testUTF8BOM/Empty.py", new byte[0]);

		// Test read empty content using io file
		File file = new File(this.encodingProject.getLocation().toString(), emptyFile.getProjectRelativePath().toString());
		char[] fileContents = org.eclipse.dltk.compiler.util.Util.getFileCharContent(file, "UTF-8");
		assertEquals("We should not get any character!", "", new String(fileContents));

		// Test read empty content using io file
		char[] ifileContents =Util.getResourceContentsAsCharArray(emptyFile, "UTF-8");
		assertEquals("We should not get any character!", "", new String(ifileContents));
		
		// Delete empty file
		deleteFile(file.getPath());
	}	

	/**
	 * Bug 110576: [encoding] Rename CU looses encoding for file which charset is determined by contents
	 * @see "http://bugs.eclipse.org/bugs/show_bug.cgi?id=110576"
	 */
	public void testBug110576() throws ModelException, CoreException {

		String os = System.getProperty("osgi.os");
		if (!"win32".equals(os)) {
			System.out.println("Bug 110576 is not tested under "+os+" os...");
			return;
		}

		// Verify file UTF-8 BOM encoding
		IFile file = (IFile) this.encodingProject.findMember("src/testBug110576/Test.py");
		verifyUtf8BOM(file);

		String fileName = file.getName();
		ISourceModule testCU = getSourceModule(file.getFullPath().toString());
		createFolder("/Encoding/src/tmp");
		IScriptFolder tmpPackage = getScriptFolder("Encoding", "src", new Path("tmp"));
		
		try {
			// Copy file
			testCU.copy(tmpPackage, null, null, false, null);
			ISourceModule copiedCU = tmpPackage.getSourceModule(fileName);
			IFile copiedFile = (IFile) copiedCU.getUnderlyingResource();
			verifyUtf8BOM(copiedFile);
	
			// Get source and compare with file contents
			compareContents(copiedCU, "UTF-8", true/*BOM*/);

			// Rename file
			copiedCU.rename("TestUTF8.py", false, null);
			ISourceModule renamedCU = tmpPackage.getSourceModule("TestUTF8.py");
			IFile renamedFile = (IFile) renamedCU.getUnderlyingResource();
			verifyUtf8BOM(renamedFile);
			fileName = renamedFile.getName();
			
			// Compare contents again
			compareContents(renamedCU, "UTF-8", true/*BOM*/);

			// Move file
			createFolder("/Encoding/src/tmp/sub");
			IScriptFolder subPackage = getScriptFolder("Encoding", "src", new Path("tmp.sub"));
			renamedCU.move(subPackage, null, null, false, null);
			ISourceModule movedCU = subPackage.getSourceModule(fileName);
			IFile movedFile = (IFile) movedCU.getUnderlyingResource();
			verifyUtf8BOM(movedFile);
	
			// Get source and compare with file contents
			compareContents(movedCU, "UTF-8", true/*BOM*/);
		}
		finally {
			// Delete temporary folder
			//renamedFile.move(utf8File.getFullPath(), false, null);
			//assertEquals("Moved file should keep encoding", encoding, utf8File.getCharset());
			deleteFolder("/Encoding/src/tmp");
		}
	}

	private void verifyUtf8BOM(IFile file) throws CoreException {
		assertNull("File should not have any explicit charset", file.getCharset(false));
		IContentDescription contentDescription = file.getContentDescription();
		assertNotNull("File should have a content description", contentDescription);
		assertEquals("Content description charset should be UTF-8", "UTF-8", contentDescription.getCharset());
		assertNotNull("File should be UTF-8 BOM!", contentDescription.getProperty(IContentDescription.BYTE_ORDER_MARK));
	}	
}
