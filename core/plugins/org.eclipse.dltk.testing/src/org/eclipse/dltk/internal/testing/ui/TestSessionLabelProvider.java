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

package org.eclipse.dltk.internal.testing.ui;

import org.eclipse.dltk.internal.testing.Messages;
import org.eclipse.dltk.internal.testing.model.TestCaseElement;
import org.eclipse.dltk.internal.testing.model.TestSuiteElement;
import org.eclipse.dltk.internal.testing.model.TestElement.Status;
import org.eclipse.dltk.testing.ITestingClient;
import org.eclipse.dltk.testing.model.ITestCaseElement;
import org.eclipse.dltk.testing.model.ITestElement;
import org.eclipse.dltk.testing.model.ITestRunSession;
import org.eclipse.dltk.testing.model.ITestSuiteElement;
import org.eclipse.dltk.ui.viewsupport.ColoredString;
import org.eclipse.dltk.ui.viewsupport.IRichLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class TestSessionLabelProvider extends LabelProvider implements
		IRichLabelProvider {

	private final TestRunnerViewPart fTestRunnerPart;
	private final int fLayoutMode;

	public TestSessionLabelProvider(TestRunnerViewPart testRunnerPart,
			int layoutMode) {
		fTestRunnerPart = testRunnerPart;
		fLayoutMode = layoutMode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.internal.ui.viewsupport.IRichLabelProvider#getRichTextLabel(java.lang.Object)
	 */
	public ColoredString getRichTextLabel(Object element) {
		String label = getSimpleLabel(element);
		if (label == null) {
			return new ColoredString(element.toString());
		}
		ColoredString text = new ColoredString(label);
		// if (fLayoutMode == TestRunnerViewPart.LAYOUT_HIERARCHICAL) {
		// if (((ITestElement) element).getParentContainer() instanceof
		// ITestRunSession) {
		// String testKindDisplayName= fTestRunnerPart.getTestKindDisplayName();
		// if (testKindDisplayName != null) {
		// String decorated=
		// Messages.format(JUnitMessages.TestSessionLabelProvider_testName_JUnitVersion,
		// new Object[] { label, testKindDisplayName });
		// return ScriptElementLabels.decorateColoredString(text, decorated,
		// ColoredJavaElementLabels.QUALIFIER_STYLE);
		// }
		// }
		// } else {
		// if (element instanceof ITestCaseElement) {
		// String className= ((ITestCaseElement) element).getTestClassName();
		// String decorated=
		// Messages.format(JUnitMessages.TestSessionLabelProvider_testMethodName_className,
		// new Object[] { label, className });
		// return ColoredJavaElementLabels.decorateColoredString(text,
		// decorated, ColoredJavaElementLabels.QUALIFIER_STYLE);
		// }
		// }
		return text;
	}

	private String getSimpleLabel(Object element) {
		if (element instanceof ITestCaseElement) {
			return ((ITestCaseElement) element).getTestMethodName();
		} else if (element instanceof ITestSuiteElement) {
			return ((ITestSuiteElement) element).getSuiteTypeName();
		}
		return null;
	}

	public String getText(Object element) {
		String label = getSimpleLabel(element);
		if (label == null) {
			return element.toString();
		}
		if (fLayoutMode == TestRunnerViewPart.LAYOUT_HIERARCHICAL) {
			if (((ITestElement) element).getParentContainer() instanceof ITestRunSession) {
				String testKindDisplayName = fTestRunnerPart
						.getTestKindDisplayName();
				if (testKindDisplayName != null) {
					return Messages
							.format(
									DLTKTestingMessages.TestSessionLabelProvider_testName_JUnitVersion,
									new Object[] { label, testKindDisplayName });
				}
			}
		} else {
			if (element instanceof ITestCaseElement) {
				String className = ((ITestCaseElement) element)
						.getTestClassName();
				return Messages
						.format(
								DLTKTestingMessages.TestSessionLabelProvider_testMethodName_className,
								new Object[] { label, className });
			}
		}
		return label;
	}

	public Image getImage(Object element) {
		if (element instanceof TestCaseElement) {
			TestCaseElement testCaseElement = ((TestCaseElement) element);
			if (testCaseElement.isIgnored())
				return fTestRunnerPart.fTestIgnoredIcon;

			Status status = testCaseElement.getStatus();
			if (status.isNotRun())
				return fTestRunnerPart.fTestIcon;
			else if (status.isRunning())
				return fTestRunnerPart.fTestRunningIcon;
			else if (status.isError())
				return fTestRunnerPart.fTestErrorIcon;
			else if (status.isFailure()) {
				switch (status.getFailedCode()) {
				case ITestingClient.ABORTED:
					return fTestRunnerPart.fTestAbortedIcon;
				case ITestingClient.BLOCKED:
					return fTestRunnerPart.fTestBlockedIcon;
				case ITestingClient.SKIPPED:
					return fTestRunnerPart.fTestSkippedIcon;
				case ITestingClient.UNKNOWN:
					return fTestRunnerPart.fTestUnknownIcon;
				default:
					return fTestRunnerPart.fTestFailIcon;
				}
			} else if (status.isOK())
				return fTestRunnerPart.fTestOkIcon;
			else
				throw new IllegalStateException(element.toString());

		} else if (element instanceof TestSuiteElement) {
			Status status = ((TestSuiteElement) element).getStatus();
			if (status.isNotRun())
				return fTestRunnerPart.fSuiteIcon;
			else if (status.isRunning())
				return fTestRunnerPart.fSuiteRunningIcon;
			else if (status.isError())
				return fTestRunnerPart.fSuiteErrorIcon;
			else if (status.isFailure())
				switch (status.getFailedCode()) {
				case ITestingClient.ABORTED:
					return fTestRunnerPart.fSuiteAbortedIcon;
				case ITestingClient.BLOCKED:
					return fTestRunnerPart.fSuiteBlockedIcon;
				case ITestingClient.SKIPPED:
					return fTestRunnerPart.fSuiteSkippedIcon;
				case ITestingClient.UNKNOWN:
					return fTestRunnerPart.fSuiteUnknownIcon;
				default:
					return fTestRunnerPart.fSuiteFailIcon;
				}
			else if (status.isOK())
				return fTestRunnerPart.fSuiteOkIcon;
			else
				throw new IllegalStateException(element.toString());

		} else {
			throw new IllegalArgumentException(String.valueOf(element));
		}
	}
}
