package org.eclipse.dltk.ruby.tests.typeinference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.StringTokenizer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ruby.internal.parser.JRubySourceParser;
import org.eclipse.dltk.ruby.tests.Activator;
import org.eclipse.dltk.ruby.tests.assist.RubySelectionTests;
import org.eclipse.dltk.ruby.typeinference.RubyTypeUtils;
import org.eclipse.dltk.ruby.typeinference.internal.RubyTypeModel;
import org.eclipse.dltk.typeinference.AnyTypeDescriptor;
import org.eclipse.dltk.typeinference.IKnownTypeDescriptor;
import org.eclipse.dltk.typeinference.IMethodDescriptor;
import org.eclipse.dltk.typeinference.ITypeDescriptor;
import org.eclipse.dltk.typeinference.RecursiveCallTypeDescriptor;

public class TypeInferenceSuite extends TestSuite {

	private interface IAssertion {

		void check();

	}

	public TypeInferenceSuite(String testsDirectory) {
		super(testsDirectory);
		Enumeration entryPaths = Activator.getDefault().getBundle().getEntryPaths(testsDirectory);
		while (entryPaths.hasMoreElements()) {
			final String path = (String) entryPaths.nextElement();
			URL entry = Activator.getDefault().getBundle().getEntry(path);
			try {
				entry.openStream().close();
			} catch (Exception e) {
				continue;
			}
			int pos = path.lastIndexOf('/');
			final String name = (pos >= 0 ? path.substring(pos + 1) : path);
			String x = path.substring(0, pos);
			pos = x.lastIndexOf('/');
			final String folder = (pos >= 0 ? x.substring(pos + 1) : x);
			addTest(new TestCase(name) {

				private Collection assertions = new ArrayList();

				private RubyTypeModel calculator;

				public void setUp() {
					calculator = new RubyTypeModel();
				}

				class MethodReturnTypeAssertion implements IAssertion {

					private final String className;

					private final String methodName;

					private final String correctClassRef;

					public MethodReturnTypeAssertion(String className, String methodName,
							String correctClassRef) {
						this.className = className;
						this.methodName = methodName;
						this.correctClassRef = correctClassRef;
					}

					public void check() {
						ITypeDescriptor correctType;
						if ("recursion".equals(correctClassRef))
							correctType = RecursiveCallTypeDescriptor.INSTANCE;
						else if ("any".equals(correctClassRef))
							correctType = AnyTypeDescriptor.INSTANCE;
						else
							correctType = lookupType(correctClassRef);
						IKnownTypeDescriptor methType = lookupType(className);
						assertNotNull("class " + className + " not found", methType);
						IMethodDescriptor method = methType.getMethodByName(methodName);
						assertNotNull("method " + methodName + " not found", method);
						ITypeDescriptor checkedType = method.getReturnType();
						assertEquals("Incorrect type of " + className + "." + methodName, correctType, checkedType);
					}

				}

				protected void runTest() throws Throwable {
					String content = loadContent(path);
					String[] lines = content.split("\n");
					for (int i = 0; i < lines.length; i++) {
						String line = lines[i].trim();
						if (line.startsWith("##")) {
							StringTokenizer tok = new StringTokenizer(line.substring(2));
							String test = tok.nextToken();
							if ("meth".equals(test)) {
								String methodRef = tok.nextToken();
								String arrow = tok.nextToken();
								Assert.isLegal(arrow.equals("=>"));
								String correctClassRef = tok.nextToken();
								int dotPos = methodRef.lastIndexOf('.');
								if (dotPos >= 0) {
									String classRef = methodRef.substring(0, dotPos);
									String methodName = methodRef.substring(dotPos + 1);
									assertions.add(new MethodReturnTypeAssertion(classRef,
											methodName, correctClassRef));
								} else {
									Assert.isLegal(false);
								}
							} else {
								Assert.isLegal(false);
							}
						}
					}
					
					Assert.isLegal(assertions.size() > 0);
					
					if("simple.rb".equals(name)) 
						System.out.println();

					TypeInferenceTest tests = new TypeInferenceTest("ruby selection tests");
					tests.setUpSuite();
					try {
						tests.executeTest(folder, name, calculator);						
						for (Iterator iter = assertions.iterator(); iter.hasNext();) {
							IAssertion assertion = (IAssertion) iter.next();
							assertion.check();
						}
					} finally {
						tests.tearDownSuite();
					}
				}

				private IKnownTypeDescriptor lookupType(String typeName) {
					return RubyTypeUtils.lookupType(calculator.getScope(), typeName);
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
				buffer.append(s + "\n");
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

}
