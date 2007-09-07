package org.eclipse.dltk.xotcl.core.tests.parser;

import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.tcl.internal.parsers.raw.SimpleTclParser;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclParseException;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclScript;
import org.eclipse.dltk.xotcl.core.ast.IfStatement;
import org.eclipse.dltk.xotcl.core.ast.TclCatchStatement;
import org.eclipse.dltk.xotcl.core.ast.TclGlobalVariableDeclaration;
import org.eclipse.dltk.xotcl.core.ast.TclPackageDeclaration;
import org.eclipse.dltk.xotcl.core.ast.TclSwitchStatement;
import org.eclipse.dltk.xotcl.core.ast.TclUpvarVariableDeclaration;
import org.eclipse.dltk.xotcl.core.ast.TclVariableDeclaration;
import org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl.TclCatchProcessor;
import org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl.TclGlobalVariableProcessor;
import org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl.TclIfProcessor;
import org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl.TclNamespaceProcessor;
import org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl.TclPackageProcessor;
import org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl.TclProcProcessor;
import org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl.TclSwitchCommandProcessor;
import org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl.TclUpvarProcessor;
import org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl.TclVariableProcessor;

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

	private void testCondition(IfStatement ifStatement)
	{
		assertNotNull(ifStatement.getCondition());
		assertEquals(true, ifStatement.getCondition() instanceof ASTListNode);
		ASTListNode condition = (ASTListNode)ifStatement.getCondition();
		assertEquals(3, condition.getChilds().size());
		assertTrue(condition.getChilds().get(0) instanceof SimpleReference);
		assertTrue(condition.getChilds().get(1) instanceof SimpleReference);
		assertTrue(condition.getChilds().get(2) instanceof SimpleReference);
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

		testCondition(ifStatement);
		
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

		testCondition(ifStatement);

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

		testCondition(ifStatement);

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
		assertTrue(statement instanceof TclGlobalVariableDeclaration ||  statement instanceof Block);
		if (statement instanceof ASTListNode)
		{
			ASTListNode list = (ASTListNode)statement;
			assertEquals(varNum, list.getChilds().size());
			for (Iterator i = list.getChilds().iterator(); i.hasNext();)
				assertTrue(i.next() instanceof TclGlobalVariableDeclaration);
		}
	}
	
	public void testTclPackageProcessor001() throws TclParseException
	{
		String script = "package ifneeded pack 1.0.1 {script}";
		testPackageImports(script, TclPackageDeclaration.STYLE_IFNEEDED, true);
	}
	
	public void testTclPackageProcessor002() throws TclParseException
	{
		String script = "package ifneeded pack 1.0.1";
		testPackageImports(script, TclPackageDeclaration.STYLE_IFNEEDED, false);
	}
	
	public void testTclPackageProcessor003() throws TclParseException
	{
		String script = "package require pack 1.0.1";
		testPackageImports(script, TclPackageDeclaration.STYLE_REQUIRE, false);
	}

	public void testTclPackageProcessor004() throws TclParseException
	{
		String script = "package require -exact pack 1.0.1";
		testPackageImports(script, TclPackageDeclaration.STYLE_REQUIRE, false);
	}

	public void testTclPackageProcessor005() throws TclParseException
	{
		String script = "package provide pack 1.0.1";
		testPackageImports(script, TclPackageDeclaration.STYLE_PROVIDE, false);
	}
	
	private void testPackageImports(String script, int style, boolean hasScript ) throws TclParseException
	{
		TclPackageProcessor processor = new TclPackageProcessor();
		ASTNode statement = processor.process(toCommand(script), new TestTclParser(script), 0, null);
		assertNotNull(statement);
		assertTrue(statement instanceof TclPackageDeclaration);
		TclPackageDeclaration decl = (TclPackageDeclaration)statement;
		assertEquals(style, decl.getStyle());
		assertEquals("pack", decl.getName());
		assertEquals("1.0.1", ((SimpleReference)decl.getVersion()).getName());
		if (hasScript)
			assertNotNull(decl.getScript());
		else
			assertNull(decl.getScript());
	}
	
	// For tests on this command with qualified names see org.eclipse.dltk.xotcl.core.tests.parser.XOTclParserUtilTests 
	public void testTclProcProcessor001() throws TclParseException
	{
		String script = "proc func args { pid }";
		testTclProcProcessor(script, 1);
	}
	public void testTclProcProcessor002() throws TclParseException
	{
		String script = "proc func {arg0, {arg1 \"value\"} } { pid }";
		MethodDeclaration method = testTclProcProcessor(script, 2);
		assertNotNull((Argument)method.getArguments().get(1));
	}
	
	public void testTclProcProcessor003() throws TclParseException
	{
		String script = "proc func {} { pid }";
		testTclProcProcessor(script, 0);
	}

	private MethodDeclaration testTclProcProcessor(String script, int argNum) throws TclParseException
	{
		TclProcProcessor processor = new TclProcProcessor();
		ASTNode node = processor.process(toCommand(script), new TestTclParser(script), 0, null);
		assertNotNull(node);
		assertTrue(node instanceof MethodDeclaration);
		MethodDeclaration method = (MethodDeclaration)node;
		assertEquals("func", method.getName());
		assertEquals(argNum, method.getArguments().size());
		return method;
	}
	
	// For tests on this command with qualified names see org.eclipse.dltk.xotcl.core.tests.parser.XOTclParserUtilTests 
	public void testTclVariableProcessor001() throws TclParseException
	{
		String script = "set var \"value\"";
		testTclVariableProcessor(script,true);
	}
	
	public void testTclVariableProcessor002() throws TclParseException
	{
		String script = "set var";
		testTclVariableProcessor(script,false);
	}

	private void testTclVariableProcessor(String script, boolean initialValue) throws TclParseException
	{
		TclVariableProcessor processor = new TclVariableProcessor();
		ASTNode node = processor.process(toCommand(script), new TestTclParser(script), 0, null);
		assertNotNull(node);
		TclVariableDeclaration var = (TclVariableDeclaration)node;
		assertEquals("var", var.getName());
		if (initialValue) assertNotNull(var.getInitializer());
	}
	
	public void testTclUpVarProcessor001() throws TclParseException
	{
		String script = "upvar otherVar0 myVar0";
		testTclUpVarProcessor(script,1);
	}
	
	public void testTclUpVarProcessor002() throws TclParseException
	{
		String script = "upvar otherVar0 myVar0 otherVar1 myVar1";
		testTclUpVarProcessor(script,2);
	}
	
	public void testTclUpVarProcessor003() throws TclParseException
	{
		String script = "upvar 1 otherVar0 myVar0";
		testTclUpVarProcessor(script,1);
	}
	public void testTclUpVarProcessor004() throws TclParseException
	{
		String script = "upvar #1 otherVar0 myVar0";
		testTclUpVarProcessor(script,1);
	}
	
	private void testTclUpVarProcessor(String script, int varNum) throws TclParseException
	{
		TclUpvarProcessor processor = new TclUpvarProcessor();
		ASTNode node = processor.process(toCommand(script), new TestTclParser(script), 0, null);
		assertNotNull(node);
		assertTrue(node instanceof ASTListNode || node instanceof TclUpvarVariableDeclaration);
		if (node instanceof ASTListNode)
			assertEquals(varNum, ((ASTListNode)node).getChilds().size());
	}
	
	public void testTclNamespaceProcessor001() throws TclParseException
	{
		String script = "namespace eval :: pid";
		testTclNamespaceProcessor(script);
	}

	public void testTclNamespaceProcessor002() throws TclParseException
	{
		String script = "namespace eval :: {pid;} {puts boo!}";
		testTclNamespaceProcessor(script);
	}

	public void testTclNamespaceProcessor003() throws TclParseException
	{
		String script = "namespace eval :: {pid}";
		testTclNamespaceProcessor(script);
	}

	public void testTclNamespaceProcessor004() throws TclParseException
	{
		String script = "namespace eval :: puts boo!";
		testTclNamespaceProcessor(script);
	}

	private void testTclNamespaceProcessor(String script) throws TclParseException
	{
		TclNamespaceProcessor processor = new TclNamespaceProcessor();
		ASTNode node = processor.process(toCommand(script), new TestTclParser(script), 0, null);
		assertNotNull(node);
		assertTrue(node instanceof TypeDeclaration);
		assertTrue((Modifiers.AccNameSpace & ((TypeDeclaration)node).getModifiers()) != 0);
		assertNotNull(((TypeDeclaration)node).getBody());
	}
	
	public void testTclSwitchProcessor001() throws TclParseException
	{
		String script = "switch -glob aaab { a*b - b {expr 1} a* {expr 2} default {expr 3} }";
		testTclSwitchProcessor(script, 4);
	}
	
	public void testTclSwitchProcessor002() throws TclParseException
	{
		String script = "switch -glob aaab a*b - b {expr 1} a* {expr 2} default {expr 3}";
		testTclSwitchProcessor(script, 4);
	}
	
	public void testTclSwitchProcessor003() throws TclParseException
	{
		String script = "switch -exact -regexp -glob \"\" default {puts boo}";
		testTclSwitchProcessor(script, 1);
	}
	
	public void testTclSwitchProcessor004() throws TclParseException
	{
		String script = "switch -regexp -exact -glob -- -bu -bu {puts boo}";
		testTclSwitchProcessor(script, 1);
	}

	private void testTclSwitchProcessor(String script, int alternativesNumber) throws TclParseException
	{
		TclSwitchCommandProcessor processor =  new TclSwitchCommandProcessor();
		ASTNode node = processor.process(toCommand(script), new TestTclParser(script), 0, null);
		assertNotNull(node);
		TclSwitchStatement switchExpr = (TclSwitchStatement)node;
		assertNotNull(switchExpr.getString());
		assertNotNull(switchExpr.getAlternatives());
		assertEquals(alternativesNumber, switchExpr.getAlternatives().getChilds().size());
		
	}
}
