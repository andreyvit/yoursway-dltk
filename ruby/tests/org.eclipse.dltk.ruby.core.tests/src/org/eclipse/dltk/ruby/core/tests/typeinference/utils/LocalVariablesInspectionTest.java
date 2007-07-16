/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.core.tests.typeinference.utils;

import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ruby.core.tests.typeinference.AbstractTypeInferencingTests;
import org.eclipse.dltk.ruby.internal.parsers.jruby.ASTUtils;
import org.eclipse.dltk.ruby.typeinference.LocalVariableInfo;
import org.eclipse.dltk.ruby.typeinference.RubyTypeInferencingUtils;

public class LocalVariablesInspectionTest extends AbstractTypeInferencingTests {

	private static final String PATH_PREFIX = "/workspace/typeinference2/localvars/";
	private static final String SRC_PROJECT = "typeinference2";

	public LocalVariablesInspectionTest(String name) {
		super("org.eclipse.dltk.ruby.core.tests", name);
	}

	public void setUpSuite() throws Exception {
		PROJECT = setUpScriptProject(SRC_PROJECT);
		super.setUpSuite();
		waitUntilIndexesReady();
		ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.FULL_BUILD, new NullProgressMonitor());
		
	}
	
	public void tearDownSuite() throws Exception {
		deleteProject(SRC_PROJECT);
		super.tearDownSuite();
	}

	public void testGlobalExistant() throws Exception {
		String content = loadContent(PATH_PREFIX + "simple1.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("puts") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "x");
		assertEquals(0, info.getKind());
		assertNotNull(info.getLastAssignment());
		assertEquals(0, info.getConditionalAssignments().length);
	}
	
	public void testGlobalExistant2() throws Exception {
		String content = loadContent(PATH_PREFIX + "simple1.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("puts y") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "y");
		assertEquals(0, info.getKind());
		assertNotNull(info.getLastAssignment());
		assertEquals(0, info.getConditionalAssignments().length);
	}
	
	public void testGlobalNotExistant() throws Exception {
		String content = loadContent(PATH_PREFIX + "simple1.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("puts") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "y");
		assertEquals(0, info.getKind());
		assertNull(info.getLastAssignment());
		assertEquals(0, info.getConditionalAssignments().length);
	}
	
	public void testGlobalConditional1() throws Exception {
		String content = loadContent(PATH_PREFIX + "conditional1.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("puts") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "x");
		assertEquals(0, info.getKind());
		assertNull(info.getLastAssignment());
		assertEquals(2, info.getConditionalAssignments().length);
	}
	
	public void testGlobalConditional2() throws Exception {
		String content = loadContent(PATH_PREFIX + "conditional2.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("puts") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "x");
		assertEquals(0, info.getKind());
		assertNotNull(info.getLastAssignment());
		assertEquals(2, info.getConditionalAssignments().length);
	}
	
	public void testGlobalConditional3() throws Exception {
		String content = loadContent(PATH_PREFIX + "conditional3.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("puts") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "x");
		assertEquals(0, info.getKind());
		assertNotNull(info.getLastAssignment());
		assertEquals(0, info.getConditionalAssignments().length);
	}
	
	public void testGlobalConditional4() throws Exception {
		String content = loadContent(PATH_PREFIX + "conditional4.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("bye") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "x");
		assertEquals(0, info.getKind());
		assertNotNull(info.getLastAssignment());
		assertEquals(1, info.getConditionalAssignments().length);
	}
	
	public void testInsideMethod1() throws Exception {
		String content = loadContent(PATH_PREFIX + "method1.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("puts") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "x");
		assertEquals(LocalVariableInfo.KIND_METHOD_ARG, info.getKind());
		assertNull(info.getLastAssignment());
		assertEquals(0, info.getConditionalAssignments().length);
		assertTrue (info.getDeclaringScope() instanceof MethodDeclaration);
	}
	
	public void testInsideMethod1_2() throws Exception {
		String content = loadContent(PATH_PREFIX + "method1.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("y = 53") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "x");
		assertEquals(LocalVariableInfo.KIND_METHOD_ARG, info.getKind());
		assertNotNull(info.getLastAssignment());
		assertEquals(0, info.getConditionalAssignments().length);
		assertTrue (info.getDeclaringScope() instanceof MethodDeclaration);
	}

	public void testInsideMethod1_3() throws Exception {
		String content = loadContent(PATH_PREFIX + "method1.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("puts \"") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "x");
		assertEquals(0, info.getKind());
		assertNotNull(info.getLastAssignment());
		assertEquals(0, info.getConditionalAssignments().length);
		assertTrue (info.getDeclaringScope() instanceof ModuleDeclaration);
	}
	
	public void testInsideMethod1_4() throws Exception {
		String content = loadContent(PATH_PREFIX + "method1.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("bye") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "y");
		assertEquals(0, info.getKind());
		assertNotNull(info.getLastAssignment());
		assertEquals(0, info.getConditionalAssignments().length);
		assertTrue (info.getDeclaringScope() instanceof MethodDeclaration);
	}
	
	public void testInsideType1() throws Exception {
		String content = loadContent(PATH_PREFIX + "class1.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("puts x") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "x");
		assertTrue (info.getDeclaringScope() instanceof TypeDeclaration);
		assertEquals(0, info.getKind());
		assertNotNull(info.getLastAssignment());
		assertEquals(1, info.getConditionalAssignments().length);
	}
	
	public void testInsideType1_2() throws Exception {
		String content = loadContent(PATH_PREFIX + "class1.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("yyyy") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "x");
		assertEquals(0, info.getKind());
		assertNull(info.getLastAssignment());
		assertEquals(0, info.getConditionalAssignments().length);
	}
	
	public void testInsideType1_3() throws Exception {
		String content = loadContent(PATH_PREFIX + "class1.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("bye") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "x");
		assertTrue (info.getDeclaringScope() instanceof TypeDeclaration);
		assertEquals(0, info.getKind());
		assertNotNull(info.getLastAssignment());
		assertEquals(0, info.getConditionalAssignments().length);
	}
	
	public void testInsideType1_4() throws Exception {
		String content = loadContent(PATH_PREFIX + "class1.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("putx") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "x");
		assertTrue (info.getDeclaringScope() instanceof TypeDeclaration);
		assertEquals ("Cool", ((TypeDeclaration)(info.getDeclaringScope())).getName());
		assertEquals(0, info.getKind());
		assertNotNull(info.getLastAssignment());
		assertEquals(0, info.getConditionalAssignments().length);
	}
	
	public void testInsideMethod2() throws Exception {
		String content = loadContent(PATH_PREFIX + "method2.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("haha") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "x");
		assertTrue (info.getDeclaringScope() instanceof MethodDeclaration);
		assertEquals(LocalVariableInfo.KIND_METHOD_ARG, info.getKind());
		assertNotNull(info.getLastAssignment());
		assertEquals(1, info.getConditionalAssignments().length);
	}
	
	public void testInsideMethod2_1() throws Exception {
		String content = loadContent(PATH_PREFIX + "method2.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("puts x") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "x");
		assertTrue (info.getDeclaringScope() instanceof MethodDeclaration);
		assertEquals(LocalVariableInfo.KIND_METHOD_ARG, info.getKind());
		assertNull(info.getLastAssignment());
		assertEquals(0, info.getConditionalAssignments().length);
	}
	
	public void testInsideMethod2_2() throws Exception {
		String content = loadContent(PATH_PREFIX + "method2.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("gigi") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "x");
		assertTrue (info.getDeclaringScope() instanceof MethodDeclaration);
		assertEquals(LocalVariableInfo.KIND_METHOD_ARG, info.getKind());
		assertNull(info.getLastAssignment());
		assertEquals(1, info.getConditionalAssignments().length);
	}
	
	public void testLoops1() throws Exception {
		String content = loadContent(PATH_PREFIX + "loop1.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("puts var") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "var");
		assertTrue (info.getDeclaringScope() instanceof TypeDeclaration);
		assertEquals(LocalVariableInfo.KIND_DEFAULT, info.getKind());
		assertNotNull(info.getLastAssignment());
		assertEquals(0, info.getConditionalAssignments().length);
	}
	
	public void testLoops1_2() throws Exception {
		String content = loadContent(PATH_PREFIX + "loop1.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("for") - 2;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "var");
		assertTrue (info.getDeclaringScope() instanceof MethodDeclaration);
		assertEquals(LocalVariableInfo.KIND_METHOD_ARG, info.getKind());
		assertNotNull(info.getLastAssignment());
		assertEquals(0, info.getConditionalAssignments().length);
	}
	
	public void testLoops1_3() throws Exception {
		String content = loadContent(PATH_PREFIX + "loop1.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("putX var") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "var");
		assertTrue (info.getDeclaringScope() instanceof MethodDeclaration);
		assertEquals(LocalVariableInfo.KIND_LOOP_VAR, info.getKind());
		assertNotNull(info.getLastAssignment());
		assertEquals(0, info.getConditionalAssignments().length);
	}
	
	public void testLoops1_4() throws Exception {
		String content = loadContent(PATH_PREFIX + "loop1.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("putxxx") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "var");
		assertTrue (info.getDeclaringScope() instanceof MethodDeclaration);
		assertEquals(LocalVariableInfo.KIND_LOOP_VAR, info.getKind());
		assertNotNull(info.getLastAssignment());
		assertEquals(0, info.getConditionalAssignments().length);
	}
	
	
	public void testBlocks1() throws Exception {
		String content = loadContent(PATH_PREFIX + "blocks1.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("label1") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "var");
		assertTrue (info.getDeclaringScope() instanceof MethodDeclaration);
		assertEquals(LocalVariableInfo.KIND_BLOCK_ARG, info.getKind());
		assertNull(info.getLastAssignment());
		assertEquals(0, info.getConditionalAssignments().length);
	}
	
	public void testBlocks1_2() throws Exception {
		String content = loadContent(PATH_PREFIX + "blocks1.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("label2") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "var");
		assertTrue (info.getDeclaringScope() instanceof MethodDeclaration);
		assertEquals(LocalVariableInfo.KIND_DEFAULT, info.getKind());
		assertNotNull(info.getLastAssignment());
		assertEquals(0, info.getConditionalAssignments().length);
	}
	
	public void testBlocks1_3() throws Exception {
		String content = loadContent(PATH_PREFIX + "blocks1.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("label3") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "var");
		assertTrue (info.getDeclaringScope() instanceof MethodDeclaration);
		assertEquals(LocalVariableInfo.KIND_BLOCK_ARG, info.getKind());
		assertNull(info.getLastAssignment());
		assertEquals(1, info.getConditionalAssignments().length);
	}
	
	public void testBlocks1_4() throws Exception {
		String content = loadContent(PATH_PREFIX + "blocks1.rb");
		ModuleDeclaration ast = ASTUtils.getAST(content.toCharArray());
		int offset = content.indexOf("label4") + 1;
		
		LocalVariableInfo info = RubyTypeInferencingUtils.inspectLocalVariable(ast, offset, "var");
		assertTrue (info.getDeclaringScope() instanceof TypeDeclaration);
		assertEquals(LocalVariableInfo.KIND_DEFAULT, info.getKind());
		assertNull(info.getLastAssignment());
		assertEquals(1, info.getConditionalAssignments().length);
	}
	
}
