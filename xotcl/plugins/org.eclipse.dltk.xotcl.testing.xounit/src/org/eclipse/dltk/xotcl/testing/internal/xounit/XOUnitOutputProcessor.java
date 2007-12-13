/**
 * 
 */
package org.eclipse.dltk.xotcl.testing.internal.xounit;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.testing.DLTKTestingPlugin;
import org.eclipse.dltk.testing.ITestingClient;
import org.eclipse.dltk.testing.ITestingProcessor;
import org.eclipse.dltk.testing.model.ITestRunSession;

class XOUnitOutputProcessor implements ITestingProcessor {
	private ILaunch launch;
	long start = 0;
	private ISourceModule module;

	public XOUnitOutputProcessor(ILaunch launch) {
		this.launch = launch;
		ILaunchConfiguration launchConfiguration = launch
				.getLaunchConfiguration();

		try {
			module = AbstractScriptLaunchConfigurationDelegate
					.getSourceModule(launchConfiguration);
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
	}

	Map testNameToId = new HashMap();

	int index = 0;
	private ITestRunSession session;
	private ITestingClient client;
	private boolean skip = false;
	private String message = "";
	private int state = STATE_NORMAL;
	private static final int STATE_NORMAL = 0;
	private static final int STATE_RESULT_WAS = 1;
	private static final int STATE_RESULT_ACTUAL = 2;
	private String resultActual = "";
	private String resultExpected = "";
	int total = 0;

	public void done() {
		checkEndTest();
		// System.out.println("DONE");
		if (session == null || client == null) {
			// System.out.println("Session is NULL");
			return;
		}
		session.setTotalCount(total);
		client.testTerminated((int) (System.currentTimeMillis() - start));
	}

	int currentTest = -1;
	boolean inTrace = false;
	String actual = "";
	String expected = "";
	String testPrefix;

	public void processLine(String line) {
		// System.out.println("#" + line);
		if (session == null || client == null) {
			return;
		}
		// System.out.println("@");
		if (line.length() == 0) {
			return;
		}

		if (!skip) {
			if (line.startsWith("Pass:")) {
				checkEndTest();
				line = line.trim();
				String[] split = line.split(" ");
				if (split.length >= 3) {
					testPrefix = split[1] + "::" + split[2];
					if (testNameToId.containsKey(testPrefix)) {
						int id = ((Integer) testNameToId.get(testPrefix))
								.intValue();
						client.testEnded(id, testPrefix);
					}
				}
				resetState();

			} else if (line.startsWith("Error:")) {
				checkEndTest();
				line = line.trim();
				String[] split = line.split(" ");
				if (split.length >= 3) {
					testPrefix = split[1] + "::" + split[2];
					if (testNameToId.containsKey(testPrefix)) {
						currentTest = ((Integer) testNameToId.get(testPrefix))
								.intValue();
						client.testError(currentTest, testPrefix);
						client.testEnded(currentTest, testPrefix);
						client.testActual("");
						client.testExpected("");
						client.traceStart();	
						inTrace = true;
					}
				}
				// resetState();
			} else if (line.startsWith("Failure:")) {
				checkEndTest();
				line = line.trim();
				String[] split = line.split(" ");
				if (split.length >= 3) {
					testPrefix = split[1] + "::" + split[2];
					if (testNameToId.containsKey(testPrefix)) {
						currentTest = ((Integer) testNameToId.get(testPrefix))
								.intValue();
						client.testFailed(currentTest, testPrefix);
//						client.traceStart();
//						client.traceEnd();
					}
				}
			} else if (line.startsWith("Actual:")) {
				actual = line.substring("Actual:".length());
			} else if (line.startsWith("Expected:")) {
				expected = line.substring("Expected:".length());
			}
			else if( line.startsWith("=================")) {
				checkEndTest();
			}
			else {
				if( inTrace ) {
					client.traceMessage(line);
				}
			}
		}
	}

	private void checkEndTest() {
		if( currentTest == -1 ) {
			return;
		}
		if (inTrace) {
			//Error case
			client.traceEnd();
		}
		else {
			//Failed case
			client.testActual(actual);
			client.testExpected(expected);
			client.traceStart();
			client.traceEnd();
			client.testEnded(currentTest, testPrefix);
		}
		inTrace = false;
		currentTest = -1;
	}

	private void resetState() {
		state = STATE_NORMAL;
		resultActual = "";
		resultExpected = "";
	}

	public void start() {
		// System.out.println("!!!!!!START!!!!!!");
		this.start = System.currentTimeMillis();
		index = 0;
		session = DLTKTestingPlugin.getTestRunSession(launch);
		if (session == null)
			return;

		client = session.getTestRunnerClient();
		if (client != null) {
			client.testRunStart(0);
		}

		// We need to traverse module and add all tests to map
		ModuleDeclaration moduleDeclaration = SourceParserUtil
				.getModuleDeclaration(module, null);
		processModule(moduleDeclaration);
	}

	private void processModule(ModuleDeclaration moduleDeclaration) {
		if (moduleDeclaration == null) {
			return;
		}
		TypeDeclaration[] tests = XOUnitTestingEngine
				.findTests(moduleDeclaration);
		total = 0;
		total = processSubtests(tests, moduleDeclaration);
		session.setTotalCount(total);
	}

	private int processSubtests(TypeDeclaration[] tests,
			ModuleDeclaration moduleDeclaration) {
		int summ = 0;
		for (int i = 0; i < tests.length; i++) {
			int id = ++index;
			TypeDeclaration[] subtests = getSubtest(tests[i]);
			String testName = tests[i].getName();
			String testPrefix = TclParseUtil.getElementFQN(tests[i], "::",
					moduleDeclaration);
			if (!testPrefix.startsWith("::")) {
				testPrefix = "::" + testPrefix;
			}
			this.testNameToId.put(testPrefix, new Integer(id));
			MethodDeclaration[] testCases = XOUnitTestingEngine
					.getTestCases(tests[i]);

			if (subtests.length + testCases.length > 0) {
				client.testTree(id, testName, true, subtests.length
						+ testCases.length);
				client.testStarted(id, testName);
				// process subtests
				summ += processSubtests(subtests, moduleDeclaration);
				// process testcases
				for (int j = 0; j < testCases.length; j++) {
					int testCaseId = ++index;
					String testCaseName = testCases[j].getName();
					String testCasePrefix = TclParseUtil.getElementFQN(
							testCases[j], "::", moduleDeclaration);
					if (!testCasePrefix.startsWith("::")) {
						testCasePrefix = "::" + testCasePrefix;
					}

					this.testNameToId.put(testCasePrefix, new Integer(
							testCaseId));
					client.testTree(testCaseId, testCaseName + "(" + testPrefix
							+ ")", false, 0);
					client.testStarted(testCaseId, testCaseName + "("
							+ testPrefix + ")");
				}
				summ += testCases.length;
			}
		}
		return summ;
	}

	private TypeDeclaration[] getSubtest(TypeDeclaration typeDeclaration) {
		return XOUnitTestingEngine.getTests(typeDeclaration);
	}
}