package org.eclipse.dltk.ruby.tests.parser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.tests.model.AbstractModelTests;
import org.eclipse.dltk.ruby.internal.parser.RubySourceParser;
import org.eclipse.dltk.ruby.tests.Activator;
import org.eclipse.dltk.utils.CorePrinter;


public class RubyParserTests extends AbstractModelTests {
	public RubyParserTests(String name) {
		super(Activator.PLUGIN_ID, name);
	}

	public static Suite suite() {
		return new Suite(RubyParserTests.class);
	}

	public void processScript(String name) throws Exception {
		InputStream input = null;
		try {
			input = Activator.getDefault().openResource(name);

			InputStreamReader reader = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(reader);
			StringBuffer buffer = new StringBuffer();
			String s = br.readLine();
			while (s != null) {
				buffer.append(s + "\n");
				s = br.readLine();
			}
			RubySourceParser parser = new RubySourceParser();
			ModuleDeclaration module = parser.parse(buffer.toString());
			CorePrinter printer = new CorePrinter(System.out, true);
			module.printNode(printer);
			printer.close();
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}
	
	void testTwoPlusTwo() {
		assertEquals(4, 2 + 2);
	}

//	public String[] processLexing(String name) throws Exception {
//		System.out.println("#PROCESS_LEXING:" + name);
//		InputStream input = null;
//		try {
//			input = Activator.getDefault().openResource(name);
//
//			InputStreamReader reader = new InputStreamReader(input);
//			RubyLexer lexer = new RubyLexer(reader);
//			lexer.setSourceHandler(new RubySourceParser() {
//
//			});
//			List toks = new ArrayList();
//			while (true) {
//				DLTKToken t = lexer.nextToken();
//				if (t.getType() == DLTKToken.EOF_TYPE) {
//					break;
//				}
//				toks.add(t.getText());
//				System.out.print("\"" + t.getText() + "\",");
//			}
//			System.out.println();
//			return (String[]) toks.toArray(new String[toks.size()]);
//		} finally {
//			if (input != null) {
//				input.close();
//			}
//		}
//	}

//	public void testLexer001() throws Exception {
//		String[] toks = processLexing("/workspace/parse/test_call.rb");
//		String[] orig = { "class", "TestCall", "<", "Test::Unit::TestCase", "def", "aaa", "(", "a", ",", "b", "=", "100", ",", "*", "rest", ")",
//				"res", "=", "[", "a", ",", "b", "]", "res", "+", "=", "rest", "if", "rest", "return", "res", "end", "def", "test_call",
//				"assert_raises", "(", "ArgumentError", ")", "{", "aaa", "(", ")", "}", "assert_raises", "(", "ArgumentError", ")", "{", "aaa", "}",
//				"assert_equal", "(", "[", "1", ",", "100", "]", ",", "aaa", "(", "1", ")", ")", "assert_equal", "(", "[", "1", ",", "2", "]", ",",
//				"aaa", "(", "1", ",", "2", ")", ")", "assert_equal", "(", "[", "1", ",", "2", ",", "3", ",", "4", "]", ",", "aaa", "(", "1", ",",
//				"2", ",", "3", ",", "4", ")", ")", "assert_equal", "(", "[", "1", ",", "2", ",", "3", ",", "4", "]", ",", "aaa", "(", "1", ",", "*",
//				"[", "2", ",", "3", ",", "4", "]", ")", ")", "end", "end" };
//		assertEquals(toks.length, orig.length);
//		for (int i = 0; i < toks.length; ++i) {
//			assertEquals(toks[i], orig[i]);
//		}
//	}
//
//	public void testLexer002() throws Exception {
//		String[] toks = processLexing("/workspace/parse/test_iterator.rb");
//		String[] orig = { "require", "'test/unit'", "class", "Array", "def", "iter_test1", "collect", "{", "|", "e", "|", "[", "e", ",", "yield",
//				"(", "e", ")", "]", "}", ".sort", "{", "|", "a", ",", "b", "|", "a", "[", "1", "]", "<=", ">", "b", "[", "1", "]", "}", "end", "def",
//				"iter_test2", "a", "=", "collect", "{", "|", "e", "|", "[", "e", ",", "yield", "(", "e", ")", "]", "}", "a.sort", "{", "|", "a", ",",
//				"b", "|", "a", "[", "1", "]", "<=", ">", "b", "[", "1", "]", "}", "end", "end", "class", "TestIterator", "<", "Test::Unit::TestCase",
//				"def", "ttt", "assert", "(", "iterator" };
//		assertEquals(toks.length, orig.length);
//		for (int i = 0; i < toks.length; ++i) {
//			assertEquals(toks[i], orig[i]);
//		}
//	}
}
