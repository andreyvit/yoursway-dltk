package org.eclipse.dltk.ruby.ui.tests.text;

import junit.framework.TestCase;

import org.eclipse.dltk.ruby.core.text.RubyContext;
import org.eclipse.dltk.ruby.core.text.RubyContext.HeuristicLookupResult;
import org.eclipse.dltk.ruby.internal.ui.text.syntax.RubyContextUtils;
import org.eclipse.jface.text.Document;

public class ContextTest extends TestCase {
	
    public HeuristicLookupResult doTest(String data, RubyContext expected, int inside) throws Exception {
	    Document doc = new Document(data);
	    HeuristicLookupResult result = RubyContext.determineContext(RubyContextUtils.createDocumentAdapter(doc), doc.getLength(), RubyContext.MODE_FULL);
	    assertEquals(expected.toString(), result.context.toString());
    	assertEquals(inside, result.inside);
	    return result;
    }
    
    
    public void testEmpty() throws Exception {
    	doTest("", RubyContext.COMMAND_START, RubyContext.INSIDE_NOTHING);
    }
    
    public void testDirectOperand() throws Exception {
    	doTest("2 + 3 + ", RubyContext.EXPRESSION_START, RubyContext.INSIDE_NOTHING);
    }
    
    public void testDirectOperator() throws Exception {
    	doTest("2 + 3", RubyContext.AFTER_EXPRESSION, RubyContext.INSIDE_NUMERIC_CONSTANT);
    }
    
    public void testPrevLineOperand() throws Exception {
    	doTest("puts 2 + 3 +\n", RubyContext.EXPRESSION_START, RubyContext.INSIDE_NOTHING);
    }
    
    public void testContinuedOperator() throws Exception {
    	doTest("puts 2 + 3 \\\n", RubyContext.AFTER_EXPRESSION, RubyContext.INSIDE_NUMERIC_CONSTANT);
    }
    
    public void testContinuedOperand() throws Exception {
    	doTest("puts 2 + \\\n", RubyContext.EXPRESSION_START, RubyContext.INSIDE_NOTHING);
    }
    
    public void testNewLineStatement() throws Exception {
    	doTest("puts 2 + 3\n", RubyContext.COMMAND_START, RubyContext.INSIDE_NOTHING);
    }
    
    public void testSemicolonStatement() throws Exception {
    	doTest("puts 2 + 3; ", RubyContext.COMMAND_START, RubyContext.INSIDE_NOTHING);
    }
    
    public void testFunctionCallOperator() throws Exception {
    	doTest("foo ", RubyContext.ARGUMENT_OR_AFTER_EXPRESSION, RubyContext.INSIDE_IDENTIFIER);
    }
    
    public void testSpecialFunctionCallOperator() throws Exception {
    	doTest("foo.gsub ", RubyContext.ARGUMENT, RubyContext.INSIDE_IDENTIFIER);
    }
    
    public void testReturnOperator() throws Exception {
    	doTest("return ", RubyContext.KEYWORD_ARGUMENT, RubyContext.INSIDE_IDENTIFIER);
    }
    
    public void testIfOperand() throws Exception {
    	doTest("if ", RubyContext.EXPRESSION_START, RubyContext.INSIDE_IDENTIFIER);
    }
    
    public void testBeginStatement() throws Exception {
    	doTest("begin ", RubyContext.COMMAND_START, RubyContext.INSIDE_IDENTIFIER);
    }
    
    public void testMethodCallDot() throws Exception {
    	doTest("foo.", RubyContext.AFTER_DOT, RubyContext.INSIDE_NOTHING);
    }
    
    public void testNamespaceResolution() throws Exception {
    	doTest("Foo::", RubyContext.AFTER_DOT, RubyContext.INSIDE_NOTHING); // XXX should be special context?
    }
    
    public void testSymbol() throws Exception {
    	doTest(":", RubyContext.AFTER_DOT, RubyContext.INSIDE_NOTHING); // XXX should be special context?
    }
    
    public void testMethodDefinition() throws Exception {
    	doTest("def ", RubyContext.NAME, RubyContext.INSIDE_IDENTIFIER);
    }
    
    public void testClassDefinition() throws Exception {
    	doTest("class ", RubyContext.NAME, RubyContext.INSIDE_IDENTIFIER);
    }
    
    public void testModuleDefinition() throws Exception {
    	doTest("module ", RubyContext.EXPRESSION_START, RubyContext.INSIDE_IDENTIFIER);
    }
    
    public void testInsideGlobalVariable() throws Exception {
    	doTest("$foo", RubyContext.AFTER_EXPRESSION, RubyContext.INSIDE_GLOBAL_VARIABLE);
    }
    
    public void testInsideClassVariable() throws Exception {
    	doTest("@@foo", RubyContext.AFTER_EXPRESSION, RubyContext.INSIDE_CLASS_VARIABLE);
    }
    
    public void testInsideInstanceVariable() throws Exception {
    	doTest("@foo", RubyContext.AFTER_EXPRESSION, RubyContext.INSIDE_INSTANCE_VARIABLE);
    }
    
}
