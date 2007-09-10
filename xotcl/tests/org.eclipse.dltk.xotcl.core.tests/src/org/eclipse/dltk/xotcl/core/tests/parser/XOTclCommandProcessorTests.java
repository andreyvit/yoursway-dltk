package org.eclipse.dltk.xotcl.core.tests.parser;

import java.util.List;

import junit.framework.TestCase;

import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclParseException;
import org.eclipse.dltk.xotcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.xotcl.core.IXOTclModifiers;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclInstanceVariable;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodCallStatement;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodDeclaration;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclObjectDeclaration;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclVariableDeclaration;
import org.eclipse.dltk.xotcl.internal.core.parser.processors.xotcl.XOTclClassAllProcProcessor;
import org.eclipse.dltk.xotcl.internal.core.parser.processors.xotcl.XOTclClassMethodCallProcessor;
import org.eclipse.dltk.xotcl.internal.core.parser.processors.xotcl.XOTclClassNewInstanceProcessor;
import org.eclipse.dltk.xotcl.internal.core.parser.processors.xotcl.XOTclClassProcessor;
import org.eclipse.dltk.xotcl.internal.core.parser.processors.xotcl.XOTclObjectCreateProcessor;
import org.eclipse.dltk.xotcl.internal.core.parser.processors.xotcl.XOTclObjectSetProcessor;

public class XOTclCommandProcessorTests extends TestCase {
	public void testClassCreateCommandProcessor005() throws Throwable
	{
		String content1 = "Class ClubMember -superclass MySuperClass -parameter {title goal {name \"\"}}";
		String content2 = "Class create ClubMember -superclass MySuperClass -parameter {title goal {name \"\"}}";

		testClassCreateCommandProcessor(process(content1, new XOTclClassProcessor()));
		testClassCreateCommandProcessor(process(content2, new XOTclClassProcessor()));
	}
	
	// makes the assertions 
	private void testClassCreateCommandProcessor(ASTNode statement)
	{
		assertNotNull(statement);
		assertTrue(statement instanceof TypeDeclaration);
		TypeDeclaration type = (TypeDeclaration) statement;
		assertEquals("ClubMember", type.getName());
		List superClassNames = type.getSuperClassNames();
		assertNotNull(superClassNames);
		assertNotNull(superClassNames.get(0));
		assertTrue(superClassNames.get(0) instanceof String);
		assertEquals("MySuperClass", ((String) superClassNames.get(0)));
		FieldDeclaration[] fields = type.getVariables();
		assertTrue(3 == fields.length);
		assertEquals("title", fields[0].getName());
		assertEquals("goal", fields[1].getName());
		assertEquals("name", fields[2].getName());
	}

	private ASTNode process(String content, AbstractTclCommandProcessor processor) throws TclParseException
	{
		TclCommand ifCommand = TclCommandProcessorTests.toCommand(content);
		TestTclParser testParser = new TestTclParser(content);
		ASTNode statement = processor.process(ifCommand, testParser, 0, null);
		return statement;
	}
	
	public void testObjectCreateProcessor() throws TclParseException
	{
		String content1 = "Object obj";
		String content2 = "Object create obj";
		AbstractTclCommandProcessor processor = new XOTclObjectCreateProcessor();
		
		ASTNode statement = processor.process(TclCommandProcessorTests.toCommand(content1), new TestTclParser(content1), 0, null);
		testObjectCreateProcessor(statement);
		statement = processor.process(TclCommandProcessorTests.toCommand(content2), new TestTclParser(content2), 0, null);
		testObjectCreateProcessor(statement);
	}
	
	private void testObjectCreateProcessor(ASTNode statement)	//making assertions
	{
		assertNotNull(statement);
		assertTrue(statement instanceof XOTclObjectDeclaration);
		XOTclObjectDeclaration decl = (XOTclObjectDeclaration)statement;
		assertEquals("obj",decl.getName());		
		assertTrue(0 != (IXOTclModifiers.AccXOTcl & decl.getModifiers()));
		assertTrue(0 != (IXOTclModifiers.AccXOTclObject & decl.getModifiers()));
	}
	
	public void testObjectSetProcessor() throws TclParseException
	{
		String content = "obj set field value";
		AbstractTclCommandProcessor processor = new XOTclObjectSetProcessor();
		
		processor.setDetectedParameter(new TypeDeclaration("Object",0,0,0,0));
		ASTNode statement = processor.process(TclCommandProcessorTests.toCommand(content), new TestTclParser(content), 0, null);
		assertNotNull(statement);
		assertTrue(statement instanceof FieldDeclaration);
		XOTclVariableDeclaration decl = (XOTclVariableDeclaration)statement;
		assertEquals("Object", decl.getDeclaringTypeName());
		assertEquals("field", decl.getName());
		assertTrue(0 != (IXOTclModifiers.AccXOTcl & decl.getModifiers()));
		// this shouldn't be true here 'cause the test is 'synthetic':
		// assertTrue((IXOTclModifiers.AccXOTclObject & decl.getDeclaringType().getModifiers())!=0);
	}
	
	public void testXOTclProcCallProcessor() throws TclParseException
	{
		String content = "obj procedure arg0";
		AbstractTclCommandProcessor processor = new XOTclClassMethodCallProcessor();
		
		processor.setDetectedParameter(new XOTclInstanceVariable("obj",0,0,0,0));
		ASTNode statement = processor.process(TclCommandProcessorTests.toCommand(content), new TestTclParser(content), 0, null);
		assertNotNull(statement);
		assertTrue(statement instanceof XOTclMethodCallStatement);
		XOTclMethodCallStatement methodCall = (XOTclMethodCallStatement)statement;
		assertEquals("procedure",methodCall.getCallName().getName());
		ASTListNode arguments = methodCall.getArgs();
		assertEquals(1, arguments.getChilds().size());
	}
	
	public void testXOTclClassNewInstanceProcessor() throws TclParseException
	{
		String content = "ClassName inst";
		AbstractTclCommandProcessor processor = new XOTclClassNewInstanceProcessor();
		
		processor.setDetectedParameter(new TypeDeclaration("ClassName",0,0,0,0));
		ASTNode statement = processor.process(TclCommandProcessorTests.toCommand(content), new TestTclParser(content), 0, null);
		assertNotNull(statement);
		assertTrue(statement instanceof XOTclInstanceVariable);
		XOTclInstanceVariable var = (XOTclInstanceVariable)statement;
		assertEquals("inst", var.getName());
		assertTrue(0 != (IXOTclModifiers.AccXOTcl & var.getModifiers()));
	}
	
	public void testXOtclClassMethodCallProcessor() throws TclParseException
	{
		String content = "Foo bar arg0";
		AbstractTclCommandProcessor processor = new XOTclClassMethodCallProcessor();
		
		processor.setDetectedParameter(new XOTclInstanceVariable("Foo",0,0,0,0));
		ASTNode statement = processor.process(TclCommandProcessorTests.toCommand(content), new TestTclParser(content), 0, null);
		assertNotNull(statement);
		assertTrue(statement instanceof XOTclMethodCallStatement);
		XOTclMethodCallStatement methodCall = (XOTclMethodCallStatement)statement;
		assertEquals("bar", methodCall.getCallName().getName());
		assertEquals(1, methodCall.getArgs().getChilds().size());
	}
	
	public void testXOTclClassAllProcProcessor001() throws TclParseException
	{
		String content = "Foo proc bar {arg0} {}";
		testXOTclClassAllProcProcessor(content,new TypeDeclaration("Foo",0,0,0,0));
	}
	public void testXOTclClassAllProcProcessor002() throws TclParseException
	{
		String content = "Foo instproc bar {arg0} {}";
		testXOTclClassAllProcProcessor(content,new TypeDeclaration("Foo",0,0,0,0));
	}
	
	public void testXOTclClassAllProcProcessor003() throws TclParseException
	{
		String content = "obj proc bar {arg0} {}";
		testXOTclClassAllProcProcessor(content, new XOTclInstanceVariable("obj",0,0,0,0));		
	}
	
	public void testXOTclClassAllProcProcessor004() throws TclParseException
	{
		String content = "Object proc bar {arg0} {}";
		AbstractTclCommandProcessor processor = new XOTclClassAllProcProcessor();
		
		processor.setDetectedParameter(new TypeDeclaration("Object",0,0,0,0));
		ASTNode statement = processor.process(TclCommandProcessorTests.toCommand(content), new TestTclParser(content), 0, null);
		assertNotNull(statement);
		assertTrue(statement instanceof XOTclMethodDeclaration);
		XOTclMethodDeclaration decl = (XOTclMethodDeclaration)statement;
		assertEquals("Object", decl.getDeclaringTypeName());
		assertEquals("bar", decl.getName());
		assertEquals(1, decl.getArguments().size());
		assertTrue(0 != (IXOTclModifiers.AccXOTcl & decl.getModifiers()));
	}
	
	//making assertion on the content
	private void testXOTclClassAllProcProcessor(String content, Object parameter) throws TclParseException
	{
		AbstractTclCommandProcessor processor = new XOTclClassAllProcProcessor();
		
		processor.setDetectedParameter(parameter);
		ASTNode statement = processor.process(TclCommandProcessorTests.toCommand(content), new TestTclParser(content), 0, null);
		assertNotNull(statement);
		assertTrue(statement instanceof XOTclMethodDeclaration);
		XOTclMethodDeclaration decl = (XOTclMethodDeclaration)statement;
		if (parameter instanceof TypeDeclaration) 
			assertEquals("Foo", decl.getDeclaringTypeName());
		else if (parameter instanceof FieldDeclaration)
			assertEquals("obj", decl.getDeclaringTypeName());
		assertEquals("bar", decl.getName());
		assertEquals(1, decl.getArguments().size());
		assertTrue(0 != (IXOTclModifiers.AccXOTcl & decl.getModifiers()));
	}
}
