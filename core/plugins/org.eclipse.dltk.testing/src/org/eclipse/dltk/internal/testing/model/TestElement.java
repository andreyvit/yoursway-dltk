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

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.testing.ITestingClient;
import org.eclipse.dltk.testing.model.ITestElement;
import org.eclipse.dltk.testing.model.ITestElementContainer;
import org.eclipse.dltk.testing.model.ITestRunSession;

public abstract class TestElement implements ITestElement {
	public final static class Status {
		public static final Status RUNNING_ERROR = new Status(
				"RUNNING_ERROR", 5); //$NON-NLS-1$
		public static final Status RUNNING_FAILURE = new Status(
				"RUNNING_FAILURE", 6); //$NON-NLS-1$
		public static final Status RUNNING = new Status("RUNNING", 3); //$NON-NLS-1$

		public static final Status ERROR = new Status(
				"ERROR", /* 1 */ITestRunListener2.STATUS_ERROR); //$NON-NLS-1$
		public static final Status FAILURE = new Status(
				"FAILURE", /* 2 */ITestRunListener2.STATUS_FAILURE); //$NON-NLS-1$

		public static final Status FAILURE_BLOCKED = new Status(
				"BLOCKED", /* 2 */ITestRunListener2.STATUS_FAILURE, ITestingClient.BLOCKED); //$NON-NLS-1$
		public static final Status FAILURE_SKIPPED = new Status(
				"SKIPPED", /* 2 */ITestRunListener2.STATUS_FAILURE, ITestingClient.SKIPPED); //$NON-NLS-1$
		public static final Status FAILURE_UNKNOWN = new Status(
				"UNKNOWN", /* 2 */ITestRunListener2.STATUS_FAILURE, ITestingClient.UNKNOWN); //$NON-NLS-1$
		public static final Status FAILURE_ABORTED = new Status(
				"ABORTED", /* 2 */ITestRunListener2.STATUS_FAILURE, ITestingClient.ABORTED); //$NON-NLS-1$

		public static final Status OK = new Status(
				"OK", /* 0 */ITestRunListener2.STATUS_OK); //$NON-NLS-1$
		public static final Status NOT_RUN = new Status("NOT_RUN", 4); //$NON-NLS-1$

		private static final Status[] OLD_CODE = { OK, ERROR, FAILURE };

		private final String fName;
		private final int fOldCode;
		private int failedCode;

		private Status(String name, int oldCode) {
			fName = name;
			fOldCode = oldCode;
			failedCode = ITestingClient.PASSED;
		}

		private Status(String name, int oldCode, int failedCode) {
			fName = name;
			fOldCode = oldCode;
			this.failedCode = failedCode;
		}

		public int getOldCode() {
			return fOldCode;
		}

		public String toString() {
			return fName;
		}

		/* error state predicates */

		public boolean isOK() {
			return this == OK || this == RUNNING || this == NOT_RUN;
		}

		public boolean isFailure() {
			return this.fOldCode == FAILURE.fOldCode || this == RUNNING_FAILURE;
		}

		public int getFailedCode() {
			return this.failedCode;
		}

		public boolean isError() {
			return this == ERROR || this == RUNNING_ERROR;
		}

		public boolean isErrorOrFailure() {
			return isError() || isFailure();
		}

		/* progress state predicates */

		public boolean isNotRun() {
			return this == NOT_RUN;
		}

		public boolean isRunning() {
			return this == RUNNING || this == RUNNING_FAILURE
					|| this == RUNNING_ERROR;
		}

		public boolean isDone() {
			return this == OK || this == FAILURE || this == ERROR;
		}

		public static Status combineStatus(Status one, Status two) {
			Status progress = combineProgress(one, two);
			Status error = combineError(one, two);
			return combineProgressAndErrorStatus(progress, error);
		}

		private static Status combineProgress(Status one, Status two) {
			if (one.isNotRun() && two.isNotRun())
				return NOT_RUN;
			else if (one.isDone() && two.isDone())
				return OK;
			else if (!one.isRunning() && !two.isRunning())
				return OK; // one done, one not-run -> a parent failed and its
			// children are not run
			else
				return RUNNING;
		}

		private static Status combineError(Status one, Status two) {
			if (one.isError() || two.isError())
				return ERROR;
			else if (one.isFailure() || two.isFailure())
				return FAILURE;
			else
				return OK;
		}

		private static Status combineProgressAndErrorStatus(Status progress,
				Status error) {
			if (progress.isDone()) {
				if (error.isError())
					return ERROR;
				if (error.isFailure())
					return FAILURE;
				return OK;
			}

			if (progress.isNotRun()) {
				// Assert.isTrue(!error.isErrorOrFailure());
				return NOT_RUN;
			}

			// Assert.isTrue(progress.isRunning());
			if (error.isError())
				return RUNNING_ERROR;
			if (error.isFailure())
				return RUNNING_FAILURE;
			// Assert.isTrue(error.isOK());
			return RUNNING;
		}

		/**
		 * @param oldStatus
		 *            one of {@link ITestRunListener2}'s STATUS_* constants
		 * @return the Status
		 */
		public static Status convert(int oldStatus, int failedCode) {
			if (oldStatus < 3) {
				// This is Failed/ special case
				if (oldStatus == 2) {
					switch (failedCode) {
					case ITestingClient.ABORTED:
						return FAILURE_ABORTED;
					case ITestingClient.BLOCKED:
						return FAILURE_BLOCKED;
					case ITestingClient.SKIPPED:
						return FAILURE_SKIPPED;
					case ITestingClient.UNKNOWN:
						return FAILURE_UNKNOWN;
					default:
						return FAILURE;
					}
				}
				return OLD_CODE[oldStatus];
			}
			return null;
		}

		public Result convertToResult() {
			if (isNotRun())
				return Result.UNDEFINED;
			if (isError())
				return Result.ERROR;
			if (isFailure())
				return Result.FAILURE;
			if (isRunning()) {
				return Result.UNDEFINED;
			}
			return Result.OK;
		}

		public ProgressState convertToProgressState() {
			if (isRunning()) {
				return ProgressState.RUNNING;
			}
			if (isDone()) {
				return ProgressState.COMPLETED;
			}
			return ProgressState.NOT_STARTED;
		}

	}

	private final TestSuiteElement fParent;
	private final String fId;
	private String fTestName;

	private Status fStatus;
	private String fTrace;
	private String fExpected;
	private String fActual;

	/**
	 * @param parent
	 *            the parent, can be <code>null</code>
	 * @param id
	 *            the test id
	 * @param testName
	 *            the test name
	 */
	public TestElement(TestSuiteElement parent, String id, String testName) {
		Assert.isNotNull(id);
		Assert.isNotNull(testName);
		fParent = parent;
		fId = id;
		fTestName = testName;
		fStatus = Status.NOT_RUN;
		if (parent != null)
			parent.addChild(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.junit.ITestElement#getProgressState()
	 */
	public ProgressState getProgressState() {
		return getStatus().convertToProgressState();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.junit.ITestElement#getTestResult()
	 */
	public Result getTestResult(boolean includeChildren) {
		return getStatus().convertToResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.junit.ITestElement#getTestRunSession()
	 */
	public ITestRunSession getTestRunSession() {
		return getRoot().getTestRunSession();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.junit.ITestElement#getParentContainer()
	 */
	public ITestElementContainer getParentContainer() {
		if (fParent instanceof TestRoot) {
			return getTestRunSession();
		}
		return fParent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.junit.model.ITestElement#getFailureTrace()
	 */
	public FailureTrace getFailureTrace() {
		Result testResult = getTestResult(false);
		if (testResult == Result.ERROR || testResult == Result.FAILURE) {
			return new FailureTrace(fTrace, fExpected, fActual);
		}
		return null;
	}

	/**
	 * @return the parent suite, or <code>null</code> for the root
	 */
	public TestSuiteElement getParent() {
		return fParent;
	}

	public String getId() {
		return fId;
	}

	public String getTestName() {
		return fTestName;
	}

	public void setName(String name) {
		fTestName = name;
	}

	public void setStatus(Status status) {
		// TODO: notify about change?
		// TODO: multiple errors/failures per test
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=125296

		fStatus = status;
		TestSuiteElement parent = getParent();
		if (parent != null)
			parent.childChangedStatus(this, status);
	}

	public void setStatus(Status status, String trace, String expected,
			String actual) {
		// TODO: notify about change?
		// TODO: multiple errors/failures per test
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=125296
		fTrace = trace;
		fExpected = expected;
		fActual = actual;
		setStatus(status);
	}

	public Status getStatus() {
		return fStatus;
	}

	public String getTrace() {
		return fTrace;
	}

	public String getExpected() {
		return fExpected;
	}

	public String getActual() {
		return fActual;
	}

	public boolean isComparisonFailure() {
		return fExpected != null && fActual != null;
	}

	/**
	 * @see org.eclipse.jdt.internal.junit.runner.ITestIdentifier#getName()
	 * @see org.eclipse.jdt.internal.junit.runner.MessageIds#TEST_IDENTIFIER_MESSAGE_FORMAT
	 */
	public String getClassName() {
		return extractClassName(getTestName());
	}

	private String extractClassName(String testNameString) {
		int index = testNameString.indexOf('(');
		if (index < 0)
			return testNameString;
		testNameString = testNameString.substring(index + 1);
		testNameString = testNameString.replace('$', '.'); // see bug 178503
		return testNameString.substring(0, testNameString.indexOf(')'));
	}

	public TestRoot getRoot() {
		return getParent().getRoot();
	}

	public String toString() {
		return getProgressState() + " - " + getTestResult(true); //$NON-NLS-1$
	}
}
