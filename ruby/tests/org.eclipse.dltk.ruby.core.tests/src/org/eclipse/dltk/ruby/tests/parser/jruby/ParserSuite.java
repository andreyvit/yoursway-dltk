package org.eclipse.dltk.ruby.tests.parser.jruby;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ruby.internal.parser.JRubySourceParser;
import org.eclipse.dltk.ruby.tests.Activator;

public class ParserSuite extends TestSuite {

	private interface IAssertion {
		void check();
	}

	public ParserSuite(String testsDirectory) {
		super(testsDirectory);
		Enumeration entryPaths = Activator.getDefault().getBundle().getEntryPaths(testsDirectory);
		while (entryPaths.hasMoreElements()) {
			final String path = (String) entryPaths.nextElement();
			if (path.endsWith(".exp"))
				continue;
			URL entry = Activator.getDefault().getBundle().getEntry(path);
			try {
				entry.openStream().close();
			} catch (Exception e) {
				continue;
			}
			int pos = path.lastIndexOf('/');
			final String name = (pos >= 0 ? path.substring(pos + 1) : path);
			pos = path.lastIndexOf('.');
			final String cleanPath = (pos >= 0 ? path.substring(0, pos) : path);
			addTest(new TestCase(name) {

				
				public void setUp() {
					
				}

				protected void runTest() throws Throwable {
					Map map = new HashMap ();
					String input = loadInput(cleanPath + ".rb", map);
					String output = loadOutput(cleanPath + ".exp", map);
					
					JRubySourceParser parser = new JRubySourceParser(null);
					ModuleDeclaration module = parser.parse(input);
					assertNotNull(module);
					
					AST2StringVisitor vis = new AST2StringVisitor();
					module.traverse(vis);
					String fact = vis.getResult();
					
					if (!"success".equals(output.trim()))
						assertEquals(output, fact);
				}

			});
		}
	}
	
	public static String loadContent(String path) throws IOException {
		InputStream stream = Activator.getDefault().openResource(path);
		int length = stream.available();
		byte[] data = new byte[length];
		stream.read(data);
		stream.close();
		return new String(data, "utf-8");
	}

	private String loadInput(String path, Map map) throws IOException {
		String content = loadContent(path);
		StringBuffer result = new StringBuffer ();
		char[] charArray = content.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			if (c > 'z') {
				map.put(new Character(c), new Integer(result.length()));
			} else {
				result.append(c);
			}
		}
				
		return result.toString();
	}
	
	private String loadOutput(String path, Map map) throws IOException {
		String content = loadContent(path);
		
		StringBuffer result = new StringBuffer ();
		for (int i = 0; i < content.length(); i++) {
			char c = content.charAt(i);
			if (c > '~') {
				Integer pos = (Integer) map.get(new Character(c));
				Assert.isNotNull(pos);
				result.append (pos.intValue());
			} else {
				result.append(c);
			}
		}
				
		return result.toString();
	}

}
