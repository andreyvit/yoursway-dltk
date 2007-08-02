package org.eclipse.dltk.xotcl.core.tests.parser;

import java.util.List;

import junit.framework.TestCase;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.tcl.internal.parsers.raw.SimpleTclParser;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclParseException;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclScript;
import org.eclipse.dltk.xotcl.core.ast.IfStatement;
import org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl.TclIfProcessor;
import org.eclipse.dltk.xotcl.internal.core.parser.processors.xotcl.XOTclClassProcessor;

public class XOTclComandProcessorTests extends TestCase {
	private TclCommand toCommand( String content ) throws TclParseException {
		TclScript parse = SimpleTclParser.parse(content);
		List commands = parse.getCommands();
		assertEquals(1, commands.size());
		assertNotNull(commands.get(0));
		return (TclCommand) commands.get(0);
	}
		
	////// If processor test.
	public void testIfProcessor001() throws Throwable {
		String content =
			"if {a < 2} {\n" +
			"	set b 20\n" +
			"} else {\n" +
			"	set b 10\n" +
			"	set d 30\n" +
			"}\n";
		this.processTestIf001(content);
	}
	public void testIfProcessor001a() throws Throwable {
		String content =
			"if {a < 2} then {\n" +
			"	set b 20\n" +
			"} else {\n" +
			"	set b 10\n" +
			"	set d 30\n" +
			"}\n";
		this.processTestIf001(content);
	}
	private void processTestIf001(String content) throws TclParseException {
		TclCommand ifCommand = this.toCommand(content);
		TclIfProcessor ifProcessor = new TclIfProcessor();
		TestTclParser testParser = new TestTclParser(content);

		ASTNode statement = ifProcessor.process(ifCommand, testParser, 0, null);
		assertNotNull(statement);
		assertEquals(true, statement instanceof IfStatement);
		IfStatement ifStatement = (IfStatement) statement;

		assertNotNull(ifStatement.getCondition());
		assertEquals( true, ifStatement.getCondition() instanceof StringLiteral );
		assertEquals( "a < 2", ((StringLiteral)ifStatement.getCondition()).getValue() );

		this.assertBlockWithSize(ifStatement.getThen(), 1);
		this.assertBlockWithSize(ifStatement.getElse(), 2);
	}

	private void assertBlockWithSize(Statement then, int size) {
		assertNotNull(then);
		List childs = then.getChilds();
		assertNotNull(childs);
		assertEquals(size, childs.size());
	}
	public void testIfProcessor002() throws Throwable {
		String content =
			"if {a < 2} {\n" +
			"	set b 20\n" +
			"} elseif { a == 4 } {\n" +
			"	set b 10\n" +
			"	set d 30\n" +
			"} else {\n" +
			"	set d 90\n" +
			"	set f 10\n" +
			"	set a 0\n" +
			"}";
		this.processTestIf002(content);
	}
	public void testIfProcessor002a() throws Throwable {
		String content =
			"if {a < 2} then {\n" +
			"	set b 20\n" +
			"} elseif { a == 4 } {\n" +
			"	set b 10\n" +
			"	set d 30\n" +
			"} else {\n" +
			"	set d 90\n" +
			"	set f 10\n" +
			"	set a 0\n" +
			"}\n";
		this.processTestIf002(content);
	}
	public void testIfProcessor003() throws Throwable {
		String content =
			"if {a < 2} {\n" +
			"	set b 20\n" +
			"} elseif { a == 4 } then {\n" +
			"	set b 10\n" +
			"	set d 30\n" +
			"} else {\n" +
			"	set d 90\n" +
			"	set f 10\n" +
			"	set a 0\n" +
			"}\n";
		this.processTestIf002(content);
	}
	private void processTestIf002(String content) throws TclParseException {
		TclCommand ifCommand = this.toCommand(content);
		TclIfProcessor ifProcessor = new TclIfProcessor();
		TestTclParser testParser = new TestTclParser(content);

		ASTNode statement = ifProcessor.process(ifCommand, testParser, 0, null);
		assertNotNull(statement);
		assertEquals(true, statement instanceof IfStatement);
		IfStatement ifStatement = (IfStatement) statement;

		assertNotNull(ifStatement.getCondition());
		assertEquals( true, ifStatement.getCondition() instanceof StringLiteral );
		assertEquals( "a < 2", ((StringLiteral)ifStatement.getCondition()).getValue() );

		this.assertBlockWithSize(ifStatement.getThen(), 1);
		Statement else1 = ifStatement.getElse();
		assertNotNull(else1);
		assertEquals(true, else1 instanceof IfStatement );
		IfStatement elseStatement = (IfStatement) else1;
		this.assertBlockWithSize(elseStatement.getThen(), 2);
		this.assertBlockWithSize(elseStatement.getElse(), 3);
	}
	public void testIfProcessor004() throws Throwable {
		String content =
			"if {a < 2} {\n" +
			"	set b 20\n" +
			"}\n";
		TclCommand ifCommand = this.toCommand(content);
		TclIfProcessor ifProcessor = new TclIfProcessor();
		TestTclParser testParser = new TestTclParser(content);

		ASTNode statement = ifProcessor.process(ifCommand, testParser, 0, null);
		assertNotNull(statement);
		assertEquals(true, statement instanceof IfStatement);
		IfStatement ifStatement = (IfStatement) statement;

		assertNotNull(ifStatement.getCondition());
		assertEquals( true, ifStatement.getCondition() instanceof StringLiteral );
		assertEquals( "a < 2", ((StringLiteral)ifStatement.getCondition()).getValue() );

		this.assertBlockWithSize(ifStatement.getThen(), 1);
	}

	////
	public void testClassCreateCommandProcessor005() throws Throwable {
		String content =  "Class ClubMember -superclass MySuperClass -parameter {title goal {name \"\"}}";
		ASTNode statement = process(content, new XOTclClassProcessor());
		assertNotNull(statement);
		assertTrue(statement instanceof TypeDeclaration );
		TypeDeclaration type = (TypeDeclaration) statement;
		assertEquals("ClubMember",type.getName());
		List superClassNames = type.getSuperClassNames();
		assertNotNull(superClassNames);
		assertNotNull( superClassNames.get(0) );
		assertTrue( superClassNames.get(0) instanceof String );
		assertEquals( "MySuperClass", ((String)superClassNames.get(0)) );
		
	}
	private ASTNode process(String content, XOTclClassProcessor processor)
			throws TclParseException {
		TclCommand ifCommand = this.toCommand(content);
		TestTclParser testParser = new TestTclParser(content);
		ASTNode statement = processor.process(ifCommand, testParser, 0, null);
		return statement;
	}
}
