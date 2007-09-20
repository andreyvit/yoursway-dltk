/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.testing.ui;

import org.eclipse.swt.SWTError;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;

import org.eclipse.ui.PlatformUI;

import org.eclipse.dltk.internal.testing.model.TestElement;
import org.eclipse.dltk.ui.DLTKUIPlugin;



/**
 * Copies the names of the methods that failed and their traces to the clipboard.
 */
public class CopyFailureListAction extends Action {
	
	private final Clipboard fClipboard;
	private final TestRunnerViewPart fRunner;
		
	public CopyFailureListAction(TestRunnerViewPart runner, Clipboard clipboard) {
		super(DLTKTestingMessages.CopyFailureList_action_label);
		fRunner= runner;  
		fClipboard= clipboard;
		PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IDLTKTestingHelpContextIds.COPYFAILURELIST_ACTION);
	}

	/*
	 * @see IAction#run()
	 */
	public void run() {
		TextTransfer plainTextTransfer = TextTransfer.getInstance();
					
		try {
			fClipboard.setContents(
					new String[] { getAllFailureTraces() }, 
					new Transfer[] { plainTextTransfer });
		} catch (SWTError e){
			if (e.code != DND.ERROR_CANNOT_SET_CLIPBOARD) 
				throw e;
			if (MessageDialog.openQuestion(DLTKUIPlugin.getActiveWorkbenchShell(), DLTKTestingMessages.CopyFailureList_problem, DLTKTestingMessages.CopyFailureList_clipboard_busy))  
				run();
		}
	}
	
	public String getAllFailureTraces() {
		StringBuffer buf= new StringBuffer();
		TestElement[] failures= fRunner.getAllFailures();
		
		String lineDelim= System.getProperty("line.separator", "\n");  //$NON-NLS-1$//$NON-NLS-2$
		for (int i= 0; i < failures.length; i++) {
			TestElement failure= failures[i];
			buf.append(failure.getTestName()).append(lineDelim);
			String failureTrace= failure.getTrace();
			if (failureTrace != null) {
				int start= 0;
				while (start < failureTrace.length()) {
					int idx= failureTrace.indexOf('\n', start);
					if (idx != -1) {
						String line= failureTrace.substring(start, idx);
						buf.append(line).append(lineDelim);
						start= idx + 1;
					} else {
						start= Integer.MAX_VALUE;
					}
				}
			}
		}
		return buf.toString();
	}


}
