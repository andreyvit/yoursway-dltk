/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.dltk.internal.testing.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchListener;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.internal.testing.Messages;
import org.eclipse.dltk.internal.testing.launcher.DLTKTestingLaunchConfigurationConstants;
import org.eclipse.dltk.internal.testing.model.TestElement.Status;
import org.eclipse.dltk.internal.testing.ui.DLTKTestingPreferencesConstants;
import org.eclipse.dltk.internal.testing.ui.TestRunnerViewPart;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;
import org.eclipse.dltk.testing.DLTKTestingPlugin;
import org.eclipse.dltk.testing.ITestKind;
import org.eclipse.dltk.testing.ITestSession;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Central registry for JUnit test runs.
 */
public final class DLTKTestingModel {

	private final class DLTKTestingLaunchListener implements ILaunchListener {

		/**
		 * Used to track new launches. We need to do this so that we only attach
		 * a TestRunner once to a launch. Once a test runner is connected, it is
		 * removed from the set.
		 */
		private HashSet fTrackedLaunches = new HashSet(20);

		/*
		 * @see ILaunchListener#launchAdded(ILaunch)
		 */
		public void launchAdded(ILaunch launch) {
			fTrackedLaunches.add(launch);
		}

		/*
		 * @see ILaunchListener#launchRemoved(ILaunch)
		 */
		public void launchRemoved(final ILaunch launch) {
			fTrackedLaunches.remove(launch);
			// TODO: story for removing old test runs?
			// getDisplay().asyncExec(new Runnable() {
			// public void run() {
			// TestRunnerViewPart testRunnerViewPart=
			// findTestRunnerViewPartInActivePage();
			// if (testRunnerViewPart != null && testRunnerViewPart.isCreated()
			// && launch.equals(testRunnerViewPart.getLastLaunch()))
			// testRunnerViewPart.reset();
			// }
			// });
		}

		/*
		 * @see ILaunchListener#launchChanged(ILaunch)
		 */
		public void launchChanged(final ILaunch launch) {
			if (!fTrackedLaunches.contains(launch))
				return;

			ILaunchConfiguration config = launch.getLaunchConfiguration();
			if (config == null)
				return;

			final IScriptProject javaProject = DLTKTestingLaunchConfigurationConstants
					.getScriptProject(config);
			if (javaProject == null)
				return;

			// test whether the launch defines the JUnit attributes
			String atr = launch
					.getAttribute(org.eclipse.dltk.testing.ITestKind.LAUNCH_ATTR_TEST_KIND);
			if ((atr != null)) {
				// org.eclipse.dltk.testing.ITestKind testKind =
				// TestKindHandler.getInstance().getTestKind(atr);
				// if (testKind != null) {
				fTrackedLaunches.remove(launch);
				getDisplay().asyncExec(new Runnable() {
					public void run() {
						connectTestRunner(launch, javaProject);
					}
				});
				// }
			}
		}

		private void connectTestRunner(ILaunch launch,
				IScriptProject javaProject) {
			showTestRunnerViewPartInActivePage(findTestRunnerViewPartInActivePage());

			// TODO: Do notifications have to be sent in UI thread?
			// Check concurrent access to fTestRunSessions (no problem inside
			// asyncExec())
			int maxCount = DLTKTestingPlugin.getDefault().getPreferenceStore()
					.getInt(DLTKTestingPreferencesConstants.MAX_TEST_RUNS);
			int toDelete = fTestRunSessions.size() - maxCount;
			while (toDelete > 0) {
				toDelete--;
				TestRunSession session = (TestRunSession) fTestRunSessions
						.removeLast();
				notifyTestRunSessionRemoved(session);
			}

			TestRunSession testRunSession = new TestRunSession(launch,
					javaProject);
			addTestRunSession(testRunSession);
		}

		private TestRunnerViewPart showTestRunnerViewPartInActivePage(
				TestRunnerViewPart testRunner) {
			IWorkbenchPart activePart = null;
			IWorkbenchPage page = null;
			try {
				// TODO: have to force the creation of view part contents
				// otherwise the UI will not be updated
				if (testRunner != null && testRunner.isCreated())
					return testRunner;
				page = DLTKTestingPlugin.getActivePage();
				if (page == null)
					return null;
				activePart = page.getActivePart();
				// show the result view if it isn't shown yet
				return (TestRunnerViewPart) page
						.showView(TestRunnerViewPart.NAME);
			} catch (PartInitException pie) {
				DLTKTestingPlugin.log(pie);
				return null;
			} finally {
				// restore focus stolen by the creation of the result view
				if (page != null && activePart != null)
					page.activate(activePart);
			}
		}

		private TestRunnerViewPart findTestRunnerViewPartInActivePage() {
			IWorkbenchPage page = DLTKTestingPlugin.getActivePage();
			if (page == null)
				return null;
			return (TestRunnerViewPart) page.findView(TestRunnerViewPart.NAME);
		}

		private Display getDisplay() {
			// Shell shell= getActiveWorkbenchShell();
			// if (shell != null) {
			// return shell.getDisplay();
			// }
			Display display = Display.getCurrent();
			if (display == null) {
				display = Display.getDefault();
			}
			return display;
		}
	}

	public static IScriptProject getScriptProject(
			ILaunchConfiguration configuration) {
		try {
			String projectName = configuration.getAttribute(
					ScriptLaunchConfigurationConstants.ATTR_PROJECT_NAME,
					(String) null);
			if (projectName != null && projectName.length() > 0) {
				return DLTKCore.create(ResourcesPlugin.getWorkspace().getRoot()
						.getProject(projectName));
			}
		} catch (CoreException e) {
		}
		return null;
	}

	/** @deprecated */
	private static final class LegacyTestRunSessionListener implements
			ITestRunSessionListener {
		private TestRunSession fActiveTestRunSession;
		private ITestSessionListener fTestSessionListener;

		public void sessionAdded(TestRunSession testRunSession) {
			// Only serve one legacy ITestRunListener at a time, since they
			// cannot distinguish between different concurrent test sessions:
			if (fActiveTestRunSession != null)
				return;

			fActiveTestRunSession = testRunSession;

			fTestSessionListener = new ITestSessionListener() {
				public void testAdded(TestElement testElement) {
				}

				public void sessionStarted() {
					org.eclipse.dltk.testing.ITestRunListener[] testRunListeners = DLTKTestingPlugin
							.getDefault().getTestRunListeners();
					for (int i = 0; i < testRunListeners.length; i++) {
						testRunListeners[i]
								.testRunStarted(fActiveTestRunSession
										.getTotalCount());
					}
				}

				public void sessionTerminated() {
					org.eclipse.dltk.testing.ITestRunListener[] testRunListeners = DLTKTestingPlugin
							.getDefault().getTestRunListeners();
					for (int i = 0; i < testRunListeners.length; i++) {
						testRunListeners[i].testRunTerminated();
					}
					sessionRemoved(fActiveTestRunSession);
				}

				public void sessionStopped(long elapsedTime) {
					org.eclipse.dltk.testing.ITestRunListener[] testRunListeners = DLTKTestingPlugin
							.getDefault().getTestRunListeners();
					for (int i = 0; i < testRunListeners.length; i++) {
						testRunListeners[i].testRunStopped(elapsedTime);
					}
					sessionRemoved(fActiveTestRunSession);
				}

				public void sessionEnded(long elapsedTime) {
					org.eclipse.dltk.testing.ITestRunListener[] testRunListeners = DLTKTestingPlugin
							.getDefault().getTestRunListeners();
					for (int i = 0; i < testRunListeners.length; i++) {
						testRunListeners[i].testRunEnded(elapsedTime);
					}
					sessionRemoved(fActiveTestRunSession);
				}

				public void runningBegins() {
					// ignore
				}

				public void testStarted(TestCaseElement testCaseElement) {
					org.eclipse.dltk.testing.ITestRunListener[] testRunListeners = DLTKTestingPlugin
							.getDefault().getTestRunListeners();
					for (int i = 0; i < testRunListeners.length; i++) {
						testRunListeners[i].testStarted(
								testCaseElement.getId(), testCaseElement
										.getTestName());
					}
				}

				public void testFailed(TestElement testElement, Status status,
						String trace, String expected, String actual, int code) {
					org.eclipse.dltk.testing.ITestRunListener[] testRunListeners = DLTKTestingPlugin
							.getDefault().getTestRunListeners();
					for (int i = 0; i < testRunListeners.length; i++) {
						testRunListeners[i].testFailed(status.getOldCode(),
								testElement.getId(), testElement.getTestName(),
								trace);
					}
				}

				public void testEnded(TestCaseElement testCaseElement) {
					org.eclipse.dltk.testing.ITestRunListener[] testRunListeners = DLTKTestingPlugin
							.getDefault().getTestRunListeners();
					for (int i = 0; i < testRunListeners.length; i++) {
						testRunListeners[i].testEnded(testCaseElement.getId(),
								testCaseElement.getTestName());
					}
				}

				public void testReran(TestCaseElement testCaseElement,
						Status status, String trace, String expectedResult,
						String actualResult) {
					org.eclipse.dltk.testing.ITestRunListener[] testRunListeners = DLTKTestingPlugin
							.getDefault().getTestRunListeners();
					for (int i = 0; i < testRunListeners.length; i++) {
						testRunListeners[i].testReran(testCaseElement.getId(),
								testCaseElement.getClassName(), testCaseElement
										.getTestMethodName(), status
										.getOldCode(), trace);
					}
				}

				public boolean acceptsSwapToDisk() {
					return true;
				}
			};
			fActiveTestRunSession.addTestSessionListener(fTestSessionListener);
		}

		public void sessionRemoved(TestRunSession testRunSession) {
			if (fActiveTestRunSession == testRunSession) {
				fActiveTestRunSession
						.removeTestSessionListener(fTestSessionListener);
				fTestSessionListener = null;
				fActiveTestRunSession = null;
			}
		}
	}

	private final ListenerList fTestRunSessionListeners = new ListenerList();
	/**
	 * Active test run sessions, youngest first.
	 */
	private final LinkedList/* <TestRunSession> */fTestRunSessions = new LinkedList();
	private final ILaunchListener fLaunchListener = new DLTKTestingLaunchListener();

	/**
	 * Starts the model (called by the {@link DLTKTestingPlugin} on startup).
	 */
	public void start() {
		ILaunchManager launchManager = DebugPlugin.getDefault()
				.getLaunchManager();
		launchManager.addLaunchListener(fLaunchListener);

		/*
		 * TODO: restore on restart: - only import headers! - only import last n
		 * sessions; remove all other files in historyDirectory
		 */
		// File historyDirectory= JUnitPlugin.getHistoryDirectory();
		// File[] swapFiles= historyDirectory.listFiles();
		// if (swapFiles != null) {
		// Arrays.sort(swapFiles, new Comparator() {
		// public int compare(Object o1, Object o2) {
		// String name1= ((File) o1).getName();
		// String name2= ((File) o2).getName();
		// return name1.compareTo(name2);
		// }
		// });
		// for (int i= 0; i < swapFiles.length; i++) {
		// final File file= swapFiles[i];
		// SafeRunner.run(new ISafeRunnable() {
		// public void run() throws Exception {
		// importTestRunSession(file );
		// }
		// public void handleException(Throwable exception) {
		// JUnitPlugin.log(exception);
		// }
		// });
		// }
		// }
		addTestRunSessionListener(new LegacyTestRunSessionListener());
	}

	/**
	 * Stops the model (called by the {@link DLTKTestingPlugin} on shutdown).
	 */
	public void stop() {
		ILaunchManager launchManager = DebugPlugin.getDefault()
				.getLaunchManager();
		launchManager.removeLaunchListener(fLaunchListener);

		File historyDirectory = DLTKTestingPlugin.getHistoryDirectory();
		File[] swapFiles = historyDirectory.listFiles();
		if (swapFiles != null) {
			for (int i = 0; i < swapFiles.length; i++) {
				swapFiles[i].delete();
			}
		}

		// for (Iterator iter= fTestRunSessions.iterator(); iter.hasNext();) {
		// final TestRunSession session= (TestRunSession) iter.next();
		// SafeRunner.run(new ISafeRunnable() {
		// public void run() throws Exception {
		// session.swapOut();
		// }
		// public void handleException(Throwable exception) {
		// JUnitPlugin.log(exception);
		// }
		// });
		// }
	}

	public void addTestRunSessionListener(ITestRunSessionListener listener) {
		fTestRunSessionListeners.add(listener);
	}

	public void removeTestRunSessionListener(ITestRunSessionListener listener) {
		fTestRunSessionListeners.remove(listener);
	}

	/**
	 * @return a list of active {@link TestRunSession}s. The list is a copy of
	 *         the internal data structure and modifications do not affect the
	 *         global list of active sessions. The list is sorted by age,
	 *         youngest first.
	 */
	public List getTestRunSessions() {
		return new ArrayList(fTestRunSessions);
	}

	public TestRunSession getTestRunSession(ILaunch launch) {
		for (Iterator it = fTestRunSessions.iterator(); it.hasNext();) {
			TestRunSession session = (TestRunSession) it.next();
			if (session.getLaunch().getAttribute(
					ITestKind.LAUNCH_ATTR_TEST_KIND).equals(
					launch.getAttribute(ITestKind.LAUNCH_ATTR_TEST_KIND)))
				return session;
		}
		return null;
	}

	/**
	 * Adds the given {@link TestRunSession} and notifies all registered
	 * {@link ITestRunSessionListener}s.
	 * <p>
	 * <b>To be called in the UI thread only!</b>
	 * </p>
	 * 
	 * @param testRunSession
	 *            the session to add
	 */
	public void addTestRunSession(TestRunSession testRunSession) {
		Assert.isNotNull(testRunSession);
		Assert.isLegal(!fTestRunSessions.contains(testRunSession));
		fTestRunSessions.addFirst(testRunSession);
		notifyTestRunSessionAdded(testRunSession);
	}

	/**
	 * Imports a test run session from the given file.
	 * 
	 * @param file
	 *            a file containing a test run session transcript
	 * @return the imported test run session
	 * @throws CoreException
	 *             if the import failed
	 */
	public static ITestSession importTestRunSession(File file)
			throws CoreException {
		try {
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			// parserFactory.setValidating(true); // TODO: add DTD and debug
			// flag
			SAXParser parser = parserFactory.newSAXParser();
			TestRunHandler handler = new TestRunHandler();
			parser.parse(file, handler);
			TestRunSession session = handler.getTestRunSession();
			DLTKTestingPlugin.getModel().addTestRunSession(session);
			return session;
		} catch (ParserConfigurationException e) {
			throwImportError(file, e);
		} catch (SAXException e) {
			throwImportError(file, e);
		} catch (IOException e) {
			throwImportError(file, e);
		}
		return null; // does not happen
	}

	public static void importIntoTestRunSession(File swapFile,
			TestRunSession testRunSession) throws CoreException {
		try {
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			// parserFactory.setValidating(true); // TODO: add DTD and debug
			// flag
			SAXParser parser = parserFactory.newSAXParser();
			TestRunHandler handler = new TestRunHandler(testRunSession);
			parser.parse(swapFile, handler);
		} catch (ParserConfigurationException e) {
			throwImportError(swapFile, e);
		} catch (SAXException e) {
			throwImportError(swapFile, e);
		} catch (IOException e) {
			throwImportError(swapFile, e);
		}
	}

	/**
	 * Exports the given test run session.
	 * 
	 * @param testRunSession
	 *            the test run session
	 * @param file
	 *            the destination
	 * @throws CoreException
	 */
	public static void exportTestRunSession(TestRunSession testRunSession,
			File file) throws CoreException {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			exportTestRunSession(testRunSession, out);

		} catch (IOException e) {
			throwExportError(file, e);
		} catch (TransformerConfigurationException e) {
			throwExportError(file, e);
		} catch (TransformerException e) {
			throwExportError(file, e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e2) {
					DLTKTestingPlugin.log(e2);
				}
			}
		}
	}

	public static void exportTestRunSession(TestRunSession testRunSession,
			OutputStream out) throws TransformerFactoryConfigurationError,
			TransformerException {

		Transformer transformer = TransformerFactory.newInstance()
				.newTransformer();
		InputSource inputSource = new InputSource();
		SAXSource source = new SAXSource(new TestRunSessionSerializer(
				testRunSession), inputSource);
		StreamResult result = new StreamResult(out);
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); //$NON-NLS-1$
		transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$
		/*
		 * Bug in Xalan: Only indents if proprietary property
		 * org.apache.xalan.templates.OutputProperties.S_KEY_INDENT_AMOUNT is
		 * set.
		 * 
		 * Bug in Xalan as shipped with J2SE 5.0: Does not read the
		 * indent-amount property at all >:-(.
		 */
		try {
			transformer.setOutputProperty(
					"{http://xml.apache.org/xalan}indent-amount", "2"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (IllegalArgumentException e) {
			// no indentation today...
		}
		transformer.transform(source, result);
	}

	private static void throwExportError(File file, Exception e)
			throws CoreException {
		throw new CoreException(new org.eclipse.core.runtime.Status(
				IStatus.ERROR, DLTKTestingPlugin.getPluginId(), Messages
						.format(ModelMessages.JUnitModel_could_not_write, file
								.getAbsolutePath()), e));
	}

	private static void throwImportError(File file, Exception e)
			throws CoreException {
		throw new CoreException(new org.eclipse.core.runtime.Status(
				IStatus.ERROR, DLTKTestingPlugin.getPluginId(), Messages
						.format(ModelMessages.JUnitModel_could_not_read, file
								.getAbsolutePath()), e));
	}

	/**
	 * Removes the given {@link TestRunSession} and notifies all registered
	 * {@link ITestRunSessionListener}s.
	 * <p>
	 * <b>To be called in the UI thread only!</b>
	 * </p>
	 * 
	 * @param testRunSession
	 *            the session to remove
	 */
	public void removeTestRunSession(TestRunSession testRunSession) {
		boolean existed = fTestRunSessions.remove(testRunSession);
		if (existed) {
			notifyTestRunSessionRemoved(testRunSession);
		}
		testRunSession.removeSwapFile();
	}

	private void notifyTestRunSessionRemoved(TestRunSession testRunSession) {
		testRunSession.stopTestRun();
		ILaunch launch = testRunSession.getLaunch();
		if (launch != null) {
			ILaunchManager launchManager = DebugPlugin.getDefault()
					.getLaunchManager();
			launchManager.removeLaunch(launch);
		}

		Object[] listeners = fTestRunSessionListeners.getListeners();
		for (int i = 0; i < listeners.length; ++i) {
			((ITestRunSessionListener) listeners[i])
					.sessionRemoved(testRunSession);
		}
	}

	private void notifyTestRunSessionAdded(TestRunSession testRunSession) {
		Object[] listeners = fTestRunSessionListeners.getListeners();
		for (int i = 0; i < listeners.length; ++i) {
			((ITestRunSessionListener) listeners[i])
					.sessionAdded(testRunSession);
		}
	}

}
