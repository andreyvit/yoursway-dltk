package org.eclipse.dltk.xotcl.core.tests.parser;

import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.tcl.internal.parsers.raw.SimpleTclParser;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclParseException;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclScript;
import org.eclipse.dltk.xotcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.xotcl.core.ast.IfStatement;
import org.eclipse.dltk.xotcl.core.ast.TclCatchStatement;
import org.eclipse.dltk.xotcl.core.ast.TclGlobalVariableDeclaration;
import org.eclipse.dltk.xotcl.core.ast.TclVariableDeclaration;
import org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl.TclCatchProcessor;
import org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl.TclGlobalVariableProcessor;
import org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl.TclIfProcessor;

import junit.framework.TestCase;

public class TclCommandProcessorTests extends TestCase
{
	static TclCommand toCommand(String content) throws TclParseException
	{
		TclScript parse = SimpleTclParser.parse(content);
		List commands = parse.getCommands();
		assertEquals(1, commands.size());
		assertNotNull(commands.get(0));
		return (TclCommand) commands.get(0);
	}
	
	////// If processor test.
	public void testIfProcessor001() throws Throwable
	{
		String content = "if {a < 2} {\n" + 
						 "	set b 20\n" + 
						 "} else {\n" + 
						 "	set b 10\n" + 
						 "	set d 30\n" + 
						 "}\n";
		this.processTestIf001(content);
	}

	public void testIfProcessor001a() throws Throwable
	{
		String content = "if {a < 2} then {\n" + 
						 "	set b 20\n" + 
						 "} else {\n" + 
						 "	set b 10\n" + 
						 "	set d 30\n" + 
						 "}\n";
		this.processTestIf001(content);
	}

	private void processTestIf001(String content) throws TclParseException
	{
		TclCommand ifCommand = TclCommandProcessorTests.toCommand(content);
		TclIfProcessor ifProcessor = new TclIfProcessor();
		TestTclParser testParser = new TestTclParser(content);

		ASTNode statement = ifProcessor.process(ifCommand, testParser, 0, null);
		assertNotNull(statement);
		assertEquals(true, statement instanceof IfStatement);
		IfStatement ifStatement = (IfStatement) statement;

		assertNotNull(ifStatement.getCondition());
		assertEquals(true, ifStatement.getCondition() instanceof StringLiteral);
		assertEquals("a < 2", ((StringLiteral) ifStatement.getCondition()).getValue());

		this.assertBlockWithSize(ifStatement.getThen(), 1);
		this.assertBlockWithSize(ifStatement.getElse(), 2);
	}

	private void assertBlockWithSize(Statement then, int size)
	{
		assertNotNull(then);
		List childs = then.getChilds();
		assertNotNull(childs);
		assertEquals(size, childs.size());
	}

	public void testIfProcessor002() throws Throwable
	{
		String content = "if {a < 2} {\n" + 
						 "	set b 20\n" + 
						 "} elseif { a == 4 } {\n" + 
						 "	set b 10\n" + "	set d 30\n" + 
						 "} else {\n" + "	set d 90\n" + 
						 "	set f 10\n" + 
						 "	set a 0\n" + 
						 "}";
		this.processTestIf002(content);
	}

	public void testIfProcessor002a() throws Throwable
	{
		String content = "if {a < 2} then {\n" + 
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

	public void testIfProcessor003() throws Throwable
	{
		String content = "if {a < 2} {\n" + 
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

	private void processTestIf002(String content) throws TclParseException
	{
		TclCommand ifCommand = TclCommandProcessorTests.toCommand(content);
		TclIfProcessor ifProcessor = new TclIfProcessor();
		TestTclParser testParser = new TestTclParser(content);

		ASTNode statement = ifProcessor.process(ifCommand, testParser, 0, null);
		assertNotNull(statement);
		assertEquals(true, statement instanceof IfStatement);
		IfStatement ifStatement = (IfStatement) statement;

		assertNotNull(ifStatement.getCondition());
		assertEquals(true, ifStatement.getCondition() instanceof StringLiteral);
		assertEquals("a < 2", ((StringLiteral) ifStatement.getCondition()).getValue());

		this.assertBlockWithSize(ifStatement.getThen(), 1);
		Statement else1 = ifStatement.getElse();
		assertNotNull(else1);
		assertEquals(true, else1 instanceof IfStatement);
		IfStatement elseStatement = (IfStatement) else1;
		this.assertBlockWithSize(elseStatement.getThen(), 2);
		this.assertBlockWithSize(elseStatement.getElse(), 3);
	}

	public void testIfProcessor004() throws Throwable
	{
		String content = "if {a < 2} {\n" +
						 "	set b 20\n" + 
						 "}\n";
		TclCommand ifCommand = TclCommandProcessorTests.toCommand(content);
		TclIfProcessor ifProcessor = new TclIfProcessor();
		TestTclParser testParser = new TestTclParser(content);

		ASTNode statement = ifProcessor.process(ifCommand, testParser, 0, null);
		assertNotNull(statement);
		assertEquals(true, statement instanceof IfStatement);
		IfStatement ifStatement = (IfStatement) statement;

		assertNotNull(ifStatement.getCondition());
		assertEquals(true, ifStatement.getCondition() instanceof StringLiteral);
		assertEquals("a < 2", ((StringLiteral) ifStatement.getCondition()).getValue());

		this.assertBlockWithSize(ifStatement.getThen(), 1);
	}

	public void testTclCatchProcessor001() throws TclParseException
	{
		String script = "catch {} var";
		testTclCatchProcessor(script, true);
	}

	public void testTclCatchProcessor002() throws TclParseException
	{
		String script = "catch {}";
		testTclCatchProcessor(script, false);
	}

	private void testTclCatchProcessor(String script, boolean withVariable) throws TclParseException
	{
		TclCatchProcessor processor = new TclCatchProcessor();
		
		ASTNode node = processor.process(toCommand(script), new TestTclParser(script), 0, null);
		assertNotNull(node);
		assertTrue(node instanceof TclCatchStatement);
		TclCatchStatement catchSatement = (TclCatchStatement)node;
		if (withVariable)
		{
			assertEquals(2,catchSatement.getChilds().size());
			assertTrue(catchSatement.getChilds().get(1) instanceof TclVariableDeclaration);
		}
		else 
			assertEquals(1,catchSatement.getChilds().size());
		assertTrue(catchSatement.getChilds().get(0) instanceof Block);
	}
	public void testTclGlobalVariableProcessor001() throws TclParseException
	{
		String script = "global var0 var1";
		testTclGlobalVariableProcessor(script,2);
	}
	public void testTclGlobalVariableProcessor002() throws TclParseException
	{
		String script = "global var";
		testTclGlobalVariableProcessor(script,1);
	}
	private void testTclGlobalVariableProcessor(String script, int varNum) throws TclParseException
	{
		TclGlobalVariableProcessor processor = new TclGlobalVariableProcessor();
		
		ASTNode statement = processor.process(toCommand(script), new TestTclParser(script), 0, null);
		assertNotNull(statement);
		assertTrue(statement instanceof TclGlobalVariableDeclaration ||  statement instanceof ASTListNode);
		if (statement instanceof ASTListNode)
		{
			ASTListNode list = (ASTListNode)statement;
			assertEquals(varNum, list.getChilds().size());
			for (Iterator i = list.getChilds().iterator(); i.hasNext();)
				assertTrue(i.next() instanceof TclGlobalVariableDeclaration);
		}
	}
}
