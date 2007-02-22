package org.eclipse.dltk.ruby.ui.tests.text;

import junit.framework.TestCase;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ruby.ui.tests.internal.TestUtils;
import org.eclipse.dltk.ruby.ui.text.IRubyPartitions;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextUtilities;

public class PartitioningTest extends TestCase {

	public void doTest(String data, String partition)
			throws Exception {
		int startPos = data.indexOf("§");
		Assert.isLegal(startPos >= 0);
		data = data.substring(0, startPos) + data.substring(startPos + 1);
		int endPos = data.indexOf("§");
		Assert.isLegal(endPos >= 0);
		data = data.substring(0, endPos) + data.substring(endPos + 1);
		String test = data.substring(startPos, endPos);
		Document doc = new Document(data);
		TestUtils.installStuff(doc);
		StringBuffer expected = new StringBuffer(), actual = new StringBuffer();
		for (int offset = startPos; offset < endPos; offset++) {
			ITypedRegion p2 = TextUtilities.getPartition(doc, IRubyPartitions.RUBY_PARTITIONING, offset, 
				(offset > startPos));
			expected.append(offset);
			expected.append(" ");
			expected.append(partition);
			expected.append("\n");
			actual.append(offset);
			actual.append(" ");
			actual.append(p2.getType());
			actual.append("\n");
			offset = p2.getOffset() + p2.getLength();
		}
		assertEquals("Wrong partition at \"" + test + "\" in doc:\n" + data, expected.toString(),
				actual.toString());
	}
	
	public void testCode() throws Exception {
		doTest("§class Foo; end§", IDocument.DEFAULT_CONTENT_TYPE);
	}
	
	public void testString() throws Exception {
		doTest("puts §\"Hello, world\"§, a", IRubyPartitions.RUBY_STRING);
	}
	
	public void testPercentStringAfterPuts() throws Exception {
		doTest("puts §%s/foo bar boz/§ / 2", IRubyPartitions.RUBY_STRING);
	}
	
	public void testPercentStringAfterMethodCall() throws Exception {
		doTest("def foo(*args); puts(*args); end\nfoo §%s/foo bar boz/§ / 2", IRubyPartitions.RUBY_STRING);
	}
	
	public void testPercentOperatorAfterVariable() throws Exception {
		// XXX: this does not start a string in Ruby, but will be treated as a string in IDE
		doTest("foo = 20\nfoo §%s/foo bar boz/§ 2", IRubyPartitions.RUBY_STRING);
	}
	
	public void testPercentDoesStartString() throws Exception {
		doTest("if a == §%s/2/§ then puts 1 else puts 2 end", IRubyPartitions.RUBY_STRING);
	}
	
	public void testPercentDoesNotStartString() throws Exception {
		// XXX: this does not start a string in Ruby, but will be treated as a string in IDE
		doTest("puts bar §%s/2/§\n3", IRubyPartitions.RUBY_STRING);
	}
	
	private void doHereDocTest(String data) throws Exception {
		String s1 = data.replaceAll("[±ø∑]", "");
		String s2 = data.replaceAll("±", "§");
		String s3 = data.replaceAll("ø", "§");
		String s4 = data.replaceAll("∑", "§");
		doTest(s1, IRubyPartitions.RUBY_STRING);
		if (!data.equals(s2))
			doTest(s2, IRubyPartitions.RUBY_STRING);
		if (!data.equals(s3))
			doTest(s3, IRubyPartitions.RUBY_STRING);
		if (!data.equals(s4))
			doTest(s3, IRubyPartitions.RUBY_STRING);
	}
	
	public void testAllSortsOfHeredocs() throws Exception {
		for (int x = 0; x < 2 * 3; x++) {
			String minus = ((x & 1) != 0 ? "-" : "");
			String quote = ((x & 6) == 0 ? "" : ((x & 6) == 1 ? "'" : "\""));
			doHereDocTest("puts §<<" + minus + quote + "HEREDOC" + quote + "§, 'blah'\n" +
					"±one two three\n" +
					"fourdman five six\n" +
					"seven eight\n" +
					"HEREDOC±\n" +
					"puts ");
		}
	}
	
	public void testAllSortsOfSequentialHeredocs() throws Exception {
		for (int x = 0; x < 2 * 3; x++) {
			String minus = ((x & 1) != 0 ? "-" : "");
			String quote = ((x & 6) == 0 ? "" : ((x & 6) == 1 ? "'" : "\""));
			doHereDocTest("puts §<<" + minus + quote + "HEREDOC" + quote + "§, 'blah', ø<<-BOO, 123\n" +
					"±one two three\n" +
					"fourdman five six\n" +
					"seven eight\n" +
					"HEREDOC±\n" +
					"∑sdfsdfdsfsdfsd\n" +
					"BOO∑\n" +
					"puts 222");
		}
	}
	
	public void testEmbeddedCode() throws Exception {
		doTest("puts \"Press any #{§if 2 > 4 then key else reset_button end§} to continue\"", IDocument.DEFAULT_CONTENT_TYPE);
	}

}
