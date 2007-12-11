/*******************************************************************************
 * Copyright (c) 2006, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.ui.interpreters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.dltk.launching.EnvironmentVariable;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;

public class EnvironmentVariableContentProvider implements ITreeContentProvider {

	private Viewer fViewer;

	private EnvironmentVariable[] fVariables = new EnvironmentVariable[0];

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		fViewer = viewer;
	}

	public Object[] getElements(Object inputElement) {
		return fVariables;
	}

	public void setVariables(EnvironmentVariable[] vars) {
		if (vars != null) {
			fVariables = new EnvironmentVariable[vars.length];
			for (int i = 0; i < vars.length; i++) {
				fVariables[i] = new EnvironmentVariable(vars[i]);
			}
		}
		else {
			fVariables = new EnvironmentVariable[0];
		}
		fViewer.refresh();
	}

	public EnvironmentVariable[] getVariables() {
		EnvironmentVariable[] variables = new EnvironmentVariable[fVariables.length];
		for (int i = 0; i < variables.length; i++) {
			variables[i] = new EnvironmentVariable(fVariables[i]);
		}
		return variables;
	}

	/**
	 * Returns the list of libraries in the given selection. SubElements are
	 * replaced by their parent libraries.
	 */
	private Set getSelectedLibraries(IStructuredSelection selection) {
		Set libraries = new HashSet();
		for (Iterator iter = selection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof EnvironmentVariable) {
				libraries.add(element);
			}
		}
		return libraries;
	}

	/**
	 * Move the libraries of the given selection up.
	 */
	public void up(IStructuredSelection selection) {
		Set libraries = getSelectedLibraries(selection);
		for (int i = 0; i < fVariables.length - 1; i++) {
			if (libraries.contains(fVariables[i + 1])) {
				EnvironmentVariable temp = fVariables[i];
				fVariables[i] = fVariables[i + 1];
				fVariables[i + 1] = temp;
			}
		}
		fViewer.refresh();
		fViewer.setSelection(selection);
	}

	/**
	 * Move the libraries of the given selection down.
	 */
	public void down(IStructuredSelection selection) {
		Set libraries = getSelectedLibraries(selection);
		for (int i = fVariables.length - 1; i > 0; i--) {
			if (libraries.contains(fVariables[i - 1])) {
				EnvironmentVariable temp = fVariables[i];
				fVariables[i] = fVariables[i - 1];
				fVariables[i - 1] = temp;
			}
		}
		fViewer.refresh();
		fViewer.setSelection(selection);
	}

	/**
	 * Remove the libraries contained in the given selection.
	 */
	public void remove(IStructuredSelection selection) {
		List newLibraries = new ArrayList();
		for (int i = 0; i < fVariables.length; i++) {
			newLibraries.add(fVariables[i]);
		}
		Iterator iterator = selection.iterator();
		while (iterator.hasNext()) {
			Object element = iterator.next();
			if (element instanceof EnvironmentVariable) {
				newLibraries.remove(element);
			}
		}
		fVariables = (EnvironmentVariable[]) newLibraries
				.toArray(new EnvironmentVariable[newLibraries.size()]);
		fViewer.refresh();
	}

	/**
	 * Add the given libraries before the selection, or after the existing
	 * libraries if the selection is empty.
	 */
	public void add(EnvironmentVariable[] libs, IStructuredSelection selection) {
		List newLibraries = new ArrayList(fVariables.length + libs.length);
		for (int i = 0; i < fVariables.length; i++) {
			newLibraries.add(fVariables[i]);
		}
		List toAdd = new ArrayList(libs.length);
		for (int i = 0; i < libs.length; i++) {
			toAdd.add(new EnvironmentVariable(libs[i]));
		}
		if (selection.isEmpty()) {
			newLibraries.addAll(toAdd);
		} else {
			Object element = selection.getFirstElement();
			EnvironmentVariable firstLib = (EnvironmentVariable) element;
			int index = newLibraries.indexOf(firstLib);
			newLibraries.addAll(index, toAdd);
		}
		fVariables = (EnvironmentVariable[]) newLibraries
				.toArray(new EnvironmentVariable[newLibraries.size()]);
		fViewer.refresh();
		fViewer.setSelection(new StructuredSelection(libs), true);
	}

	/**
	 * Returns the standin libraries being edited.
	 * 
	 * @return standins
	 */
	public EnvironmentVariable[] getStandins() {
		return fVariables;
	}

	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		return false;
	}

}
