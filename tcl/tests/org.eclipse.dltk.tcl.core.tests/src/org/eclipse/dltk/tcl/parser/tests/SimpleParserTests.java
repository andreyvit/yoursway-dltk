/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.parser.tests;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import junit.framework.TestCase;

import org.eclipse.dltk.launching.LaunchingMessages;
import org.eclipse.dltk.tcl.core.tests.model.Activator;
import org.eclipse.dltk.tcl.internal.parsers.raw.BracesSubstitution;
import org.eclipse.dltk.tcl.internal.parsers.raw.CommandSubstitution;
import org.eclipse.dltk.tcl.internal.parsers.raw.MagicBackslashSubstitution;
import org.eclipse.dltk.tcl.internal.parsers.raw.NormalBackslashSubstitution;
import org.eclipse.dltk.tcl.internal.parsers.raw.QuotesSubstitution;
import org.eclipse.dltk.tcl.internal.parsers.raw.SimpleTclParser;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclScript;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclWord;
import org.eclipse.dltk.tcl.internal.parsers.raw.VariableSubstitution;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SimpleParserTests extends TestCase {

	private String getContents(URL url) throws IOException {
		InputStream input = null;
		String result = "";
		try {
			input = new BufferedInputStream(url.openStream());

			// Simple copy
			int ch = -1;
			while ((ch = input.read()) != -1) {
				result += (char) ch;
			}
		} finally {
			if (input != null) {
				input.close();
			}
		}
		return result;
	}

	private void print(TclWord s) {
		System.out.println(" - - word from " + s.getStart() + " to "
				+ s.getEnd());
		List cmd = s.getContents();
		for (Iterator iter = cmd.iterator(); iter.hasNext();) {
			Object o = iter.next();
			System.out.println(" - - - " + o.toString());
			if (o instanceof CommandSubstitution) {
				System.out.println("# internal script #");
				print(((CommandSubstitution) o).getScript());
				System.out.println("# end of internal script #");
			}
		}
	}

	private void print(TclCommand s) {
		System.out.println(" - command from " + s.getStart() + " to "
				+ s.getEnd());
		List cmd = s.getWords();
		for (Iterator iter = cmd.iterator(); iter.hasNext();) {
			TclWord word = (TclWord) iter.next();
			print(word);
		}
	}

	private void print(TclScript s) {
		System.out.println("script from " + s.getStart() + " to " + s.getEnd());
		List cmd = s.getCommands();
		for (Iterator iter = cmd.iterator(); iter.hasNext();) {
			TclCommand c = (TclCommand) iter.next();
			if (c == null)
				System.out
						.println("comment(AND ERROR TOO, no null ptr should be in result) here!");
			else
				print(c);
		}
	}

	/**
	 * Returns a Document that can be used to build a DOM tree
	 * 
	 * @return the Document
	 * @throws ParserConfigurationException
	 *             if an exception occurs creating the document builder
	 */
	public static Document getDocument() throws ParserConfigurationException {
		DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dfactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		return doc;
	}

	/**
	 * Serializes a XML document into a string - encoded in UTF8 format, with
	 * platform line separators.
	 * 
	 * @param doc
	 *            document to serialize
	 * @return the document as a string
	 */
	public static String serializeDocument(Document doc) throws IOException,
			TransformerException {
		ByteArrayOutputStream s = new ByteArrayOutputStream();

		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.setOutputProperty(OutputKeys.METHOD, "xml"); //$NON-NLS-1$
		transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$

		DOMSource source = new DOMSource(doc);
		StreamResult outputTarget = new StreamResult(s);
		transformer.transform(source, outputTarget);

		return s.toString("UTF8"); //$NON-NLS-1$			
	}

	private String getCodeRange(String code, int start, int end) {
		return code.substring(start, end + 1);
	}

	private Document parseXML(String source) {
		Document doc = null;
		try {
			// Wrapper the stream for efficient parsing
			InputStream stream = new ByteArrayInputStream(source.getBytes());

			// Do the parsing and obtain the top-level node
			try {
				DocumentBuilder parser = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder();
				parser.setErrorHandler(new DefaultHandler());
				doc = parser.parse(new InputSource(stream));
			} catch (SAXException e) {
				throw new IOException(LaunchingMessages.ScriptRuntime_badFormat);
			} catch (ParserConfigurationException e) {
				stream.close();
				throw new IOException(LaunchingMessages.ScriptRuntime_badFormat);
			} finally {
				stream.close();
			}
		} catch (IOException e) {
		}
		return doc;
	}
	
	private Document createXMLFromSource(String source, TclScript s) {
		Document doc;
		try {
			doc = getDocument();
			getXML(source, s, doc);
			return doc;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} 
		return null;
	}

	private Node getWordAsNode(String source, TclWord word, Document doc) {
		Element w = doc.createElement("word");
		w.setAttribute("start", Integer.toString(word.getStart()));
		w.setAttribute("end", Integer.toString(word.getEnd()));
		String wordRawText = getCodeRange(source, word.getStart(), word
				.getEnd());
		w.setAttribute("text", wordRawText);

		List parts = word.getContents();
		for (Iterator wordIter = parts.iterator(); wordIter.hasNext();) {
			Object o = wordIter.next();
			Element p = null;
			if (o instanceof String) {
				p = doc.createElement("string");
				p.setAttribute("text", o.toString());
			} else if (o instanceof CommandSubstitution) {
				CommandSubstitution cs = (CommandSubstitution) o;
				p = doc.createElement("command");
				String code = getCodeRange(source, cs.getStart(), cs.getEnd());
				if (code == null) {
					throw new NullPointerException("failed to get source from "
							+ cs.getStart() + " to " + cs.getEnd());
				}
				p.setAttribute("text", code);
			} else if (o instanceof QuotesSubstitution) {
				p = doc.createElement("quotes");
				// String rawText = ((QuotesSubstitution)o).getRawText();
				String rawText = getCodeRange(source, ((QuotesSubstitution) o)
						.getStart(), ((QuotesSubstitution) o).getEnd());
				if (rawText == null)
					throw new NullPointerException(o.toString()
							+ " has null raw text");
				p.setAttribute("text", rawText);
			} else if (o instanceof BracesSubstitution) {
				p = doc.createElement("braces");
				// String rawText = ((BracesSubstitution)o).getRawText();
				String rawText = getCodeRange(source, ((BracesSubstitution) o)
						.getStart(), ((BracesSubstitution) o).getEnd());
				if (rawText == null)
					throw new NullPointerException(o.toString()
							+ " has null raw text");
				p.setAttribute("text", rawText);
			} else if (o instanceof VariableSubstitution) {
				p = doc.createElement("variable");
				VariableSubstitution vs = (VariableSubstitution) o;
				p.setAttribute("start", Integer.toString(vs.getStart()));
				p.setAttribute("end", Integer.toString(vs.getEnd()));
				p.setAttribute("kind", Integer.toString(vs.getKind()));
				if (vs.getName() == null)
					throw new NullPointerException(o.toString()
							+ " has null name");
				p.setAttribute("name", vs.getName());
				if (vs.getIndex() != null)
					p.appendChild(getWordAsNode(source, vs.getIndex(), doc));
			} else if (o instanceof NormalBackslashSubstitution) {
				p = doc.createElement("normalbs");
				// String rawText =
				// ((NormalBackslashSubstitution)o).getRawText();
				String rawText = getCodeRange(source,
						((NormalBackslashSubstitution) o).getStart(),
						((NormalBackslashSubstitution) o).getEnd());
				if (rawText == null)
					throw new NullPointerException(o.toString()
							+ " has null raw text");
				p.setAttribute("text", rawText);
			} else if (o instanceof MagicBackslashSubstitution) {
				p = doc.createElement("newlinebs");
			}
			w.appendChild(p);
		}
		return w;
	}

	private Node getXML(String source, TclScript s, Document doc) {
		Element script = doc.createElement("script");
		doc.appendChild(script);
		List cmd = s.getCommands();
		for (Iterator iter = cmd.iterator(); iter.hasNext();) {
			TclCommand c = (TclCommand) iter.next();
			Element command = doc.createElement("command");
			command.setAttribute("start", Integer.toString(c.getStart()));
			command.setAttribute("end", Integer.toString(c.getEnd()));
			script.appendChild(command);
			List words = c.getWords();
			for (Iterator cmdIter = words.iterator(); cmdIter.hasNext();) {
				TclWord word = (TclWord) cmdIter.next();
				Node w = getWordAsNode(source, word, doc);
				command.appendChild(w);
			}
		}
		return script;
	}

	private void megaprint(String source, TclScript result) {
		String xml;
		try {
			xml = serializeDocument(createXMLFromSource(source, result));
			System.out.println(xml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void runTestOn(String name) throws Exception {
		System.out.println("Running " + name);
		URL in = Activator.getDefault().getBundle().getEntry(name);
		URL ans = Activator.getDefault().getBundle().getEntry(name + ".xml");
		if (in == null || ans == null)
			throw new Exception();
		try {
			String content = getContents(in);
			TclScript s = SimpleTclParser.parse(content);

			Document docFromSource = createXMLFromSource(content, s);
			String resXML = serializeDocument(docFromSource);
			String rightXML = getContents(ans);
			Document docFromFile = parseXML(rightXML);
			rightXML = serializeDocument(docFromFile);
			
			// compare results
			assertEquals(rightXML.trim(), resXML.trim());
//			assertTrue (docFromFile.equals(docFromSource));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void _testAll() throws Exception {
		Enumeration e = Activator.getDefault().getBundle().getEntryPaths(
				"rawtests/");
		while (e.hasMoreElements()) {
			Object o = e.nextElement();
			System.out.println("found test " + o);
			// runTestOn ((new Path(o.toString())).lastSegment());
			runTestOn(o.toString());
		}
	}

	public void test0() throws Exception {
		runTestOn("rawtests/0.tcl");
	}

	public void test1() throws Exception {
		runTestOn("rawtests/1.tcl");
	}

	public void test2() throws Exception {
		runTestOn("rawtests/2.tcl");
	}

	public void test3() throws Exception {
		runTestOn("rawtests/3.tcl");
	}

	public void test4() throws Exception {
		runTestOn("rawtests/4.tcl");
	}

	public void test5() throws Exception {
		runTestOn("rawtests/5.tcl");
	}

	public void test6() throws Exception {
		runTestOn("rawtests/6.tcl");
	}

	public void test7() throws Exception {
		runTestOn("rawtests/simple0.tcl");
	}

	public void test8() throws Exception {
		runTestOn("rawtests/simple1.tcl");
	}

	public void test9() throws Exception {
		runTestOn("rawtests/simple3.tcl");
	}

	public void test10() throws Exception {
		runTestOn("rawtests/all.tcl");
	}

	public void test11() throws Exception {
		runTestOn("rawtests/a.tcl");
	}

	public void test12() throws Exception {
		String content = "\r\n	    puts $a ; puts \"wow!\"\r";
		SimpleTclParser.parse(content);
	}

	public void test13() throws Exception {
		runTestOn("rawtests/7.tcl");
	}

	public void test14() throws Exception {
		runTestOn("rawtests/8.tcl");
	}

	public void test15() throws Exception {
		runTestOn("rawtests/9.tcl");
	}

	public void test16() throws Exception {
		runTestOn("rawtests/b.tcl");
	}

	public void test17() throws Exception {
		runTestOn("rawtests/c.tcl");
	}

	public void test18() throws Exception {
		runTestOn("rawtests/d.tcl");
	}

	public void test19() throws Exception {
		runTestOn("rawtests/e.tcl");
	}

	public void test20() throws Exception {
		runTestOn("rawtests/f.tcl");
	}

	public void test21() throws Exception {
		runTestOn("rawtests/g.tcl");
	}

	public void _testUtil() throws Exception { // used to visually in-place
												// debug
		// String content = "{ \r\n variable asdf\r\n variable {[<>#%\"]} \r\n
		// \r\n \r\n proc foo {} {\r\n \n }\n \n}";
		String content = "lreplace $list $index $index $newValue";
		// URL in =
		// TestsPlugin.getDefault().getBundle().getEntry("rawtests/all.tcl");
		// String content = getContents(in);
		TclScript s = SimpleTclParser.parse(content);
		print(s);
		megaprint(content, s);
	}

}
