package org.eclipse.dltk.ruby.tests.parser.jruby;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.tests.model.AbstractModelTests;
import org.eclipse.dltk.ruby.internal.parser.JRubySourceParser;
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
			while( br.ready() ) {
				String l = br.readLine();
				if( l != null ) {
					buffer.append(l);
					buffer.append('\n');
				}
			}
			JRubySourceParser parser = new JRubySourceParser(null);
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

	public void testJRubyParser001() throws Exception {
		processScript("/workspace/parse/test_call.rb");
	}
//	public void testJRubyParser002() throws Exception {
//		processScript("/workspace/parse/test_iterator.rb");
//	}
}
