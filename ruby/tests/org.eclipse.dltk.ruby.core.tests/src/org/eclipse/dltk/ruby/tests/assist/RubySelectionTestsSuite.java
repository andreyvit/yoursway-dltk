package org.eclipse.dltk.ruby.tests.assist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ruby.tests.Activator;

public class RubySelectionTestsSuite extends TestSuite {

		private interface IAssertion {

			void check();

		}

		public RubySelectionTestsSuite(final String path) {
			super(path);
			
			URL entry = Activator.getDefault().getBundle().getEntry(path);
			
			int pos = path.lastIndexOf('/');
			final String name = (pos >= 0 ? path.substring(pos + 1) : path);
			
			String content;
			try {
				content = loadContent(path);
			} catch (IOException e) {				
				e.printStackTrace();
				return;
			}
			
			ArrayList sources = new ArrayList();
			final HashMap destinations = new HashMap();
			
			String[] lines = content.split("\n");
			for (int i = 0; i < lines.length; i++) {
				String line = lines[i].trim();
				if (line.startsWith("##")) {
					StringTokenizer tok = new StringTokenizer(line.substring(2));
					int line1 = Integer.parseInt(tok.nextToken());
					int pos1 = Integer.parseInt(tok.nextToken());
					int line2 = Integer.parseInt(tok.nextToken());
					int pos2 = Integer.parseInt(tok.nextToken());
					int apos1 = getPos (content, line1, pos1);
					int apos2 = getPos (content, line2, pos2);
					System.out.println("df");
					Integer integer = new Integer(apos1);
					sources.add(integer);
					Object st = destinations.get(integer);
					if (st != null) {
						List list = (List)st;
						list.add(new Integer(apos2));
					} else {
						List list = new ArrayList ();
						list.add(new Integer(apos2));
						destinations.put(integer, list);
					}					
				}
			}
			
			Assert.isTrue(sources.size() == destinations.size());
			
			for (Iterator iterator = sources.iterator(); iterator.hasNext();) {
				final Integer index = (Integer) iterator.next();				
				addTest(new TestCase("test" + index) {

					private RubySelectionTests tests;

					public void setUp() throws Exception {
						tests = new RubySelectionTests("ruby selection tests");
						try {
							tests.setUpSuite();						
						} catch (Exception e){							
						}
					}

					
					protected void runTest() throws Throwable {
						tests.executeTest(name, index.intValue(), (ArrayList)destinations.get(index));						
					}

					
				});
			}
			
			
		
		}

		private String loadContent(String path) throws IOException {
			StringBuffer buffer = new StringBuffer();
			InputStream input = null;
			try {
				input = Activator.getDefault().openResource(path);
				InputStreamReader reader = new InputStreamReader(input);
				BufferedReader br = new BufferedReader(reader);
				String s = br.readLine();
				while (s != null) {
					buffer.append(s + "\r\n");
					s = br.readLine();
				}
			} finally {
				if (input != null) {
					input.close();
				}
			}
			String content = buffer.toString();
			return content;
		}
		
		private static int getPos (String content, int line, int col) {
			char[] charArray = content.toCharArray();
			int l = 1, i;
			for (i = 0; i < charArray.length && l != line; i++) {
				char ch = charArray[i];
				if (ch == '\n') {
					l++;
				}				
			}									
			return i + col;
		}
			

	}
