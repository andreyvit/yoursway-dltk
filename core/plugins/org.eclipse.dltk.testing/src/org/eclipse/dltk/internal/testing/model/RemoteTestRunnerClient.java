/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Julien Ruaux: jruaux@octo.com 
 * 	   Vincent Massol: vmassol@octo.com 
 *******************************************************************************/
package org.eclipse.dltk.internal.testing.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.dltk.testing.DLTKTestingPlugin;
import org.eclipse.dltk.testing.ITestingClient;
import org.eclipse.dltk.testing.MessageIds;

/**
 * The client side of the RemoteTestRunner. Handles the
 * marshaling of the different messages.
 */
public class RemoteTestRunnerClient implements ITestingClient {
	public RemoteTestRunnerClient() {
		operationsThread= new Thread(runner);
		operationsThread.start();
	}
	public abstract class ListenerSafeRunnable implements ISafeRunnable {
		public void handleException(Throwable exception) {
			DLTKTestingPlugin.log(exception);
		}
	}
	/**
	 * A simple state machine to process requests from the RemoteTestRunner
	 */
	abstract class ProcessingState {
		abstract ProcessingState readMessage(String message);
	}

	class DefaultProcessingState extends ProcessingState {
		ProcessingState readMessage(String message) {
			if (message.startsWith(MessageIds.TRACE_START)) {
				fFailedTrace.setLength(0);
				return fTraceState;
			}
			if (message.startsWith(MessageIds.EXPECTED_START)) {
				fExpectedResult.setLength(0);
				return fExpectedState;
			}
			if (message.startsWith(MessageIds.ACTUAL_START)) {
				fActualResult.setLength(0);
				return fActualState;
			}
			if (message.startsWith(MessageIds.RTRACE_START)) {
				fFailedRerunTrace.setLength(0);
				return fRerunState;
			}
			String arg= message.substring(MessageIds.MSG_HEADER_LENGTH);
			if (message.startsWith(MessageIds.TEST_RUN_START)) {
				// version < 2 format: count
				// version >= 2 format: count+" "+version
				int count= 0;
				int v= arg.indexOf(' ');
				if (v == -1) {
					fVersion= "v1"; //$NON-NLS-1$
					count= Integer.parseInt(arg);
				} else {
					fVersion= arg.substring(v + 1);
					String sc= arg.substring(0, v);
					count= Integer.parseInt(sc);
				}
				notifyTestRunStarted(count);
				return this;
			}
			if (message.startsWith(MessageIds.TEST_START)) {
				notifyTestStarted(arg);
				return this;
			}
			if (message.startsWith(MessageIds.TEST_END)) {
				notifyTestEnded(arg);
				return this;
			}
			if (message.startsWith(MessageIds.TEST_ERROR)) {
				extractFailure(arg, ITestRunListener2.STATUS_ERROR);
				return this;
			}
			if (message.startsWith(MessageIds.TEST_FAILED)) {
				extractFailure(arg, ITestRunListener2.STATUS_FAILURE);
				return this;
			}
			if (message.startsWith(MessageIds.TEST_RUN_END)) {
				long elapsedTime= Long.parseLong(arg);
				testRunEnded(elapsedTime);
				return this;
			}
			if (message.startsWith(MessageIds.TEST_STOPPED)) {
				long elapsedTime= Long.parseLong(arg);
				notifyTestRunStopped(elapsedTime);
//	            shutDown();
				return this;
			}
			if (message.startsWith(MessageIds.TEST_TREE)) {
				notifyTestTreeEntry(arg);
				return this;
			}
			if (message.startsWith(MessageIds.TEST_RERAN)) {
				if (hasTestId())
					scanReranMessage(arg);
				else
					scanOldReranMessage(arg);
				return this;
			}
			return this;
		}
	}

	/**
	 * Base class for states in which messages are appended to an internal
	 * string buffer until an end message is read.
	 */
	class AppendingProcessingState extends ProcessingState {
		private final StringBuffer fBuffer;
		private String fEndString;

		AppendingProcessingState(StringBuffer buffer, String endString) {
			this.fBuffer= buffer;
			this.fEndString= endString;
		}

		ProcessingState readMessage(String message) {
			if (message.startsWith(fEndString)) {
				entireStringRead();
				return fDefaultState;
			}
			fBuffer.append(message);
			fBuffer.append('\n');
			return this;
		}

		/**
		 * subclasses can override to do special things when end message is read
		 */
		void entireStringRead() {
		}
	}

	class TraceProcessingState extends AppendingProcessingState {
		TraceProcessingState() {
			super(fFailedTrace, MessageIds.TRACE_END);
		}

		void entireStringRead() {
			notifyTestFailed();
			fExpectedResult.setLength(0);
			fActualResult.setLength(0);
		}

		ProcessingState readMessage(String message) {
			if (message.startsWith(MessageIds.TRACE_END)) {
				notifyTestFailed();
				fFailedTrace.setLength(0);
				fActualResult.setLength(0);
				fExpectedResult.setLength(0);
				return fDefaultState;
			}
			fFailedTrace.append(message).append('\n');
			return this;
		}
	}

	/**
	 * The failed trace that is currently reported from the RemoteTestRunner
	 */
	private final StringBuffer fFailedTrace= new StringBuffer();
	/**
	 * The expected test result
	 */
	private final StringBuffer fExpectedResult= new StringBuffer();
	/**
	 * The actual test result
	 */
	private final StringBuffer fActualResult= new StringBuffer();
	/**
	 * The failed trace of a reran test
	 */
	private final StringBuffer fFailedRerunTrace= new StringBuffer();


	ProcessingState fDefaultState= new DefaultProcessingState();
	ProcessingState fTraceState= new TraceProcessingState();
	ProcessingState fExpectedState= new AppendingProcessingState(fExpectedResult, MessageIds.EXPECTED_END);
	ProcessingState fActualState= new AppendingProcessingState(fActualResult, MessageIds.ACTUAL_END);
	ProcessingState fRerunState= new AppendingProcessingState(fFailedRerunTrace, MessageIds.RTRACE_END);
	ProcessingState fCurrentState= fDefaultState;

	/**
	 * An array of listeners that are informed about test events.
	 */
	private ITestRunListener2[] fListeners;

	/**
	 * The server socket
	 */
//	private ServerSocket fServerSocket;
//	private Socket fSocket;
//	private int fPort= -1;
	private PrintWriter fWriter;
	private BufferedReader fBufferedReader;
	/**
	 * The protocol version
	 */
	private String fVersion;
	/**
	 * The failed test that is currently reported from the RemoteTestRunner
	 */
	private String fFailedTest;
	/**
	 * The Id of the failed test
	 */
	private String fFailedTestId;
	
	private int fFailedCode;
	/**
	 * The kind of failure of the test that is currently reported as failed
	 */
	private int fFailureKind;

	private boolean fDebug= false;

	/**
	 * Reads the message stream from the RemoteTestRunner
	 */

	/**
	 * Start listening to a test run. Start a server connection that
	 * the RemoteTestRunner can connect to.
	 * 
	 * @param listeners 
	 * @param port 
	 */
	public synchronized void startListening(ITestRunListener2[] listeners) {
		fListeners= listeners;
//		fPort= port;
//		ServerConnection connection= new ServerConnection(port);
//		connection.start();
	}

	/**
	 * Requests to stop the remote test run.
	 */
	public synchronized void stopTest() {
//		if (isRunning()) {
//			fWriter.println(MessageIds.TEST_STOP);
//			fWriter.flush();
//		}
	}


	private String readMessage(BufferedReader in) throws IOException {
		return in.readLine();
	}

	private void receiveMessage(String message) {
		fCurrentState= fCurrentState.readMessage(message);
	}

	private void scanOldReranMessage(String arg) {
		// OLD V1 format
		// format: className" "testName" "status
		// status: FAILURE, ERROR, OK
		int c= arg.indexOf(" "); //$NON-NLS-1$
		int t= arg.indexOf(" ", c + 1); //$NON-NLS-1$
		String className= arg.substring(0, c);
		String testName= arg.substring(c + 1, t);
		String status= arg.substring(t + 1);
		String testId= className + testName;
		notifyTestReran(testId, className, testName, status);
	}

	private void scanReranMessage(String arg) {
		// format: testId" "className" "testName" "status
		// status: FAILURE, ERROR, OK
		int i= arg.indexOf(' ');
		int c= arg.indexOf(' ', i + 1);
		int t= arg.indexOf(' ', c + 1);
		String testId= arg.substring(0, i);
		String className= arg.substring(i + 1, c);
		String testName= arg.substring(c + 1, t);
		String status= arg.substring(t + 1);
		notifyTestReran(testId, className, testName, status);
	}

	private void notifyTestReran(String testId, String className, String testName, String status) {
		int statusCode= ITestRunListener2.STATUS_OK;
		if (status.equals("FAILURE")) //$NON-NLS-1$
			statusCode= ITestRunListener2.STATUS_FAILURE;
		else if (status.equals("ERROR")) //$NON-NLS-1$
			statusCode= ITestRunListener2.STATUS_ERROR;

		String trace= ""; //$NON-NLS-1$
		if (statusCode != ITestRunListener2.STATUS_OK)
			trace= fFailedRerunTrace.toString();
		// assumption a rerun trace was sent before
		notifyTestReran(testId, className, testName, statusCode, trace);
	}

	private void extractFailure(String arg, int status) {
		String s[]= extractTestId(arg);
		fFailedTestId= s[0];
		fFailedTest= s[1];
		fFailureKind= status;
	}
	private void extractFailure(String arg, int code, int status) {
		String s[]= extractTestId(arg);
		fFailedTestId= s[0];
		fFailedTest= s[1];
		fFailedCode = code;
		fFailureKind= status;
	}

	/**
	 * @param arg test name
	 * @return an array with two elements. The first one is the testId, the second one the testName.
	 */
	String[] extractTestId(String arg) {
		String[] result= new String[2];
		if (!hasTestId()) {
			result[0]= arg; // use the test name as the test Id
			result[1]= arg;
			return result;
		}
		int i= arg.indexOf(',');
		result[0]= arg.substring(0, i);
		result[1]= arg.substring(i + 1, arg.length());
		return result;
	}

	private boolean hasTestId() {
		if (fVersion == null) // TODO fix me
			return true;
		return fVersion.equals("v2"); //$NON-NLS-1$
	}

	private void notifyTestReran(final String testId, final String className, final String testName, final int statusCode, final String trace) {
		for (int i= 0; i < fListeners.length; i++) {
			final ITestRunListener2 listener= fListeners[i];
			SafeRunner.run(new ListenerSafeRunnable() {
				public void run() {
					listener.testReran(testId, className, testName, statusCode, trace, fExpectedResult.toString(), fActualResult.toString());
				}
			});
		}
	}

	private void notifyTestTreeEntry(final String treeEntry) {
		for (int i= 0; i < fListeners.length; i++) {
			ITestRunListener2 listener= fListeners[i];
			if (!hasTestId())
				listener.testTreeEntry(fakeTestId(treeEntry));
			else
				listener.testTreeEntry(treeEntry);
		}
	}

	private String fakeTestId(String treeEntry) {
		// extract the test name and add it as the testId
		int index0= treeEntry.indexOf(',');
		String testName= treeEntry.substring(0, index0).trim();
		return testName + "," + treeEntry; //$NON-NLS-1$
	}

	private void notifyTestRunStopped(final long elapsedTime) {
		if (DLTKTestingPlugin.isStopped())
			return;
		for (int i= 0; i < fListeners.length; i++) {
			final ITestRunListener2 listener= fListeners[i];
			SafeRunner.run(new ListenerSafeRunnable() {
				public void run() {
					listener.testRunStopped(elapsedTime);
				}
			});
		}
	}
	private static class RRunner {
		public static void run(final ListenerSafeRunnable run) {
//			PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
//				public void run() {
//					SafeRunner.run(run);
//				}
//			});
			SafeRunner.run(run);
		}
	}

	private void testRunEnded(final long elapsedTime) {
		if (DLTKTestingPlugin.isStopped())
			return;
		for (int i= 0; i < fListeners.length; i++) {
			final ITestRunListener2 listener= fListeners[i];
			SafeRunner.run(new ListenerSafeRunnable() {
				public void run() {
					listener.testRunEnded(elapsedTime);
				}
			});
		}
		isTerminated = true;
	}

	private void notifyTestEnded(final String test) {
		if (DLTKTestingPlugin.isStopped())
			return;
		for (int i= 0; i < fListeners.length; i++) {
			final ITestRunListener2 listener= fListeners[i];
			SafeRunner.run(new ListenerSafeRunnable() {
				public void run() {
					String s[]= extractTestId(test);
					listener.testEnded(s[0], s[1]);
				}
			});
		}
	}

	private void notifyTestStarted(final String test) {
		if (DLTKTestingPlugin.isStopped())
			return;
		for (int i= 0; i < fListeners.length; i++) {
			final ITestRunListener2 listener= fListeners[i];
			SafeRunner.run(new ListenerSafeRunnable() {
				public void run() {
					String s[]= extractTestId(test);
					listener.testStarted(s[0], s[1]);
				}
			});
		}
	}

	private void notifyTestRunStarted(final int count) {
		if (DLTKTestingPlugin.isStopped())
			return;
		for (int i= 0; i < fListeners.length; i++) {
			final ITestRunListener2 listener= fListeners[i];

			RRunner.run(new ListenerSafeRunnable() {
				public void run() {
					listener.testRunStarted(count);
				}
			});
		}
	}

	private void notifyTestFailed() {
		if (DLTKTestingPlugin.isStopped())
			return;
		for (int i= 0; i < fListeners.length; i++) {
			final ITestRunListener2 listener= fListeners[i];
			RRunner.run(new ListenerSafeRunnable() {
				public void run() {
					listener.testFailed(fFailureKind, fFailedTestId, fFailedTest, fFailedTrace.toString(), fExpectedResult.toString(), fActualResult.toString(), fFailedCode);
				}
			});
		}
	}

	private void notifyTestRunTerminated() {
		// fix for 77771 RemoteTestRunnerClient doing work after junit shutdown [JUnit]
		if (DLTKTestingPlugin.isStopped())
			return;
		for (int i= 0; i < fListeners.length; i++) {
			final ITestRunListener2 listener= fListeners[i];
			RRunner.run(new ListenerSafeRunnable() {
				public void run() {
					listener.testRunTerminated();
				}
			});
		}
	}

	public void rerunTest(String testId, String className, String testName) {
//		if (isRunning()) {
//			fActualResult.setLength(0);
//			fExpectedResult.setLength(0);
//			fWriter.println(MessageIds.TEST_RERUN+testId+" "+className+" "+testName); //$NON-NLS-1$ //$NON-NLS-2$
//			fWriter.flush();
//		}
	}

	public void stopWaiting() {
		// TODO Auto-generated method stub

	}

	public boolean isRunning() {
		return !isTerminated;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dltk.internal.testing.model.ITestingClient#testRunStart(int)
	 */
	public void testRunStart(final int count) {
		if(isTerminated) {
			return;
		}
		synchronized (operations) {
			operations.add(new Runnable() {
				public void run() {
					fCurrentState= fDefaultState;
					notifyTestRunStarted(count);
				}
			});
			operations.notify();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dltk.internal.testing.model.ITestingClient#testTree(int, java.lang.String, boolean, int)
	 */
	public void testTree(final int testId, final String testName, final boolean issuite, final int testCound) {
		if(isTerminated) {
			return;
		}
		synchronized (operations) {
			operations.add(new Runnable() {
				public void run() {
					fCurrentState= fDefaultState;
					notifyTestTreeEntry(Integer.toString(testId) + "," + testName + "," + Boolean.toString(issuite) + "," + Integer.toString(testCound));
				}
			});
			operations.notify();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dltk.internal.testing.model.ITestingClient#testTerminated()
	 */
	public void testTerminated(final int elapse) {
		if(isTerminated) {
			return;
		}
		synchronized (operations) {
			operations.add(new Runnable() {
				public void run() {
					fCurrentState= fDefaultState;
					testRunEnded(elapse);
				}
			});
			operations.notify();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dltk.internal.testing.model.ITestingClient#testStarted(int, java.lang.String)
	 */
	public void testStarted(final int id, final String name) {
		if(isTerminated) {
			return;
		}
		synchronized (operations) {
			operations.add(new Runnable() {
				public void run() {
					fCurrentState= fDefaultState;
					notifyTestStarted(Integer.toString(id) + "," + name);
				}
			});
			operations.notify();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dltk.internal.testing.model.ITestingClient#testEnded(int, java.lang.String)
	 */
	public void testEnded(final int id, final String name) {
		if(isTerminated) {
			return;
		}
		synchronized (operations) {
			operations.add(new Runnable() {
				public void run() {
					fCurrentState= fDefaultState;
					notifyTestEnded(Integer.toString(id) + "," + name);
				}
			});
			operations.notify();
		}
	}

	public void testFailed(final int id, final String name) {
		if(isTerminated) {
			return;
		}
		synchronized (operations) {
			operations.add(new Runnable() {
				public void run() {
					fCurrentState= fDefaultState;
//					notifyTestFailed();(Integer.toString(id) + "," + name);
					extractFailure(Integer.toString(id) + "," + name, ITestRunListener2.STATUS_FAILURE, -1);
				}
			});
			operations.notify();
		}
	}
	public void testFailed(final int code,final int id, final String name) {
		if(isTerminated) {
			return;
		}
		synchronized (operations) {
			operations.add(new Runnable() {
				public void run() {
					fCurrentState= fDefaultState;
//					notifyTestFailed();(Integer.toString(id) + "," + name);
					extractFailure(Integer.toString(id) + "," + name, code, ITestRunListener2.STATUS_FAILURE);
				}
			});
			operations.notify();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dltk.internal.testing.model.ITestingClient#traceMessage(java.lang.String)
	 */
	public void traceMessage(final String message) {
		if(isTerminated) {
			return;
		}
		synchronized (operations) {
			operations.add(new Runnable() {
				public void run() {
//					fCurrentState= fDefaultState;
//					notifyTestFailed();(Integer.toString(id) + "," + name);
//					receiveMessage(MessageIds.TRACE_START);
					receiveMessage(message);
//					receiveMessage(MessageIds.TRACE_END);
				}
			});
			operations.notify();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dltk.internal.testing.model.ITestingClient#testError(int, java.lang.String)
	 */
	public void testError(final int id, final String name) {
		if(isTerminated) {
			return;
		}
		synchronized (operations) {
			operations.add(new Runnable() {
				public void run() {
					fCurrentState= fDefaultState;
//					notifyTestFailed();(Integer.toString(id) + "," + name);
					extractFailure(Integer.toString(id) + "," + name, ITestRunListener2.STATUS_ERROR);
				}
			});
			operations.notify();
		}
	}
	
	public void testActual(final String actual) {
		if(isTerminated) {
			return;
		}
		synchronized (operations) {
			operations.add(new Runnable() {
				public void run() {
//					fCurrentState= fDefaultState;
//					notifyTestFailed();(Integer.toString(id) + "," + name);
					receiveMessage(MessageIds.ACTUAL_START);
					receiveMessage(actual);
					receiveMessage(MessageIds.ACTUAL_END);
				}
			});
			operations.notify();
		}
	}
	public void testExpected(final String expected) {
		if(isTerminated) {
			return;
		}
		synchronized (operations) {
			operations.add(new Runnable() {
				public void run() {
//					fCurrentState= fDefaultState;
//					notifyTestFailed();(Integer.toString(id) + "," + name);
					receiveMessage(MessageIds.EXPECTED_START);
					receiveMessage(expected);
					receiveMessage(MessageIds.EXPECTED_END);
				}
			});
			operations.notify();
		}
	}
	public void traceStart() {
		if(isTerminated) {
			return;
		}
		synchronized (operations) {
			operations.add(new Runnable() {
				public void run() {
					receiveMessage(MessageIds.TRACE_START);
				}
			});
			operations.notify();
		}
	}
	public void traceEnd() {
		if(isTerminated) {
			return;
		}
		synchronized (operations) {
			if(operations == null) {
				return;
			}
			operations.add(new Runnable() {
				public void run() {
					receiveMessage(MessageIds.TRACE_END);
				}
			});
			operations.notify();
		}
	}


	private List operations= new ArrayList();
	private Thread operationsThread;
	private boolean isTerminated = false;
	private Runnable runner= new Runnable() {
		public void run() {
			while (!isTerminated || operations.size() > 0) {
				Runnable operation= null;
				synchronized (operations) {
					if (operations.size() > 0) {
						operation= (Runnable) operations.get(0);
						operations.remove(0);
					} else {
						try {
							operations.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				if (operation != null) {
					try {
//						System.out.println("Exec operation");
//						System.out.flush();
						operation.run();
					} catch (Throwable t) {
						t.printStackTrace();
					}
				}
			}
		}
	};

}
