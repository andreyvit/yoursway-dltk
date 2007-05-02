/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.editor.selectionaction;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;


public class SelectionHistory {

	private List fHistory;
	private ScriptEditor fEditor;
	private ISelectionChangedListener fSelectionListener;
	private int fSelectionChangeListenerCounter;
	private StructureSelectHistoryAction fHistoryAction;

	public SelectionHistory(ScriptEditor editor) {
		Assert.isNotNull(editor);
		fEditor= editor;
		fHistory= new ArrayList(3);
		fSelectionListener= new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				if (fSelectionChangeListenerCounter == 0)
					flush();
			}
		};
		fEditor.getSelectionProvider().addSelectionChangedListener(fSelectionListener);
	}

	public void setHistoryAction(StructureSelectHistoryAction action) {
		Assert.isNotNull(action);
		fHistoryAction= action;
	}

	public boolean isEmpty() {
		return fHistory.isEmpty();
	}

	public void remember(ISourceRange range) {
		fHistory.add(range);
		fHistoryAction.update();
	}

	public ISourceRange getLast() {
		if (isEmpty())
			return null;
		int size= fHistory.size();
		ISourceRange result= (ISourceRange)fHistory.remove(size - 1);
		fHistoryAction.update();
		return result;
	}

	public void flush() {
		if (fHistory.isEmpty())
			return;
		fHistory.clear();
		fHistoryAction.update();
	}

	public void ignoreSelectionChanges() {
		fSelectionChangeListenerCounter++;
	}

	public void listenToSelectionChanges() {
		fSelectionChangeListenerCounter--;
	}

	public void dispose() {
		fEditor.getSelectionProvider().removeSelectionChangedListener(fSelectionListener);
	}
}
