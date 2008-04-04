/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.debug.ui.interpreters;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterComboBlock;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterContainerHelper;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.internal.core.packages.PackagesManager;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListDialog;

public class TclInterpreterComboBlock extends AbstractInterpreterComboBlock {
	private Set packages = new HashSet();

	public class PackagesLabelProvider extends LabelProvider {

		public Image getImage(Object element) {
			if (element instanceof String) {
				String packageName = (String) element;
				IInterpreterInstall install = getInterpreter();
				if (install == null) {
					install = ScriptRuntime
							.getDefaultInterpreterInstall(TclNature.NATURE_ID);
				}
				if (install != null) {
					final Set names = PackagesManager.getInstance().getPackageNames(
							install);
					if( !names.contains(packageName)) {
						return DLTKPluginImages.DESC_OBJS_ERROR.createImage();
					}
				}
			}

			return DLTKPluginImages.get(DLTKPluginImages.IMG_OBJS_PACKAGE);
		}

		public String getText(Object element) {
			if (element instanceof String) {
				return (String) element;
			}
			return super.getText(element);
		}

	}

	private class PackagesContentProvider implements ITreeContentProvider {

		private final Object[] NONE_OBJECT = new Object[0];

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof Set) {
				return getElements(parentElement);
			}
			return NONE_OBJECT;
		}

		public Object getParent(Object element) {
			return null;
		}

		public boolean hasChildren(Object element) {
			return false;
		}

		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof Set) {
				return packages.toArray();
			}
			return NONE_OBJECT;
		}
	}

	private TreeViewer fElements;
	private IScriptProject scriptProject;

	protected void showInterpreterPreferencePage() {
		showPrefPage(TclInterpreterPreferencePage.PAGE_ID);
	}

	protected String getCurrentLanguageNature() {
		return TclNature.NATURE_ID;
	}

	public void createControl(Composite ancestor) {
		super.createControl(ancestor);
		Composite composite = new Composite(ancestor, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		org.eclipse.swt.layout.GridLayout gridLayout = new org.eclipse.swt.layout.GridLayout(
				2, false);
		composite.setLayout(gridLayout);

		this.fElements = new TreeViewer(composite);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);

		this.fElements.getTree().setLayoutData(data);

		Composite buttons = new Composite(composite, SWT.NONE);
		GridData data2 = new GridData(SWT.FILL, SWT.FILL, false, false);
		buttons.setLayoutData(data2);

		GridLayout gridLayout2 = new GridLayout(1, true);
		buttons.setLayout(gridLayout2);

		Button add = new Button(buttons, SWT.PUSH);
		data2 = new GridData(SWT.FILL, SWT.DEFAULT, false, false);
		add.setLayoutData(data2);
		add.setText("Add");
		add.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				addPackage();
			}
		});
		final Button remove = new Button(buttons, SWT.PUSH);
		remove.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				removePackage();
			}
		});
		remove.setText("Remove");
		remove.setLayoutData(data2);

		// setTitle("Packages");
		// setMessage("Package dependencies list");
		// this.setDescription("Package dependencies list");

		this.fElements.setContentProvider(new PackagesContentProvider());
		this.fElements.setLabelProvider(new PackagesLabelProvider());
		this.fElements.setInput(this.packages);
		this.fElements
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						ISelection selection = event.getSelection();
						if (selection instanceof IStructuredSelection) {
							IStructuredSelection sel = (IStructuredSelection) selection;
							remove.setEnabled(!sel.isEmpty());
						}
					}
				});
		remove.setEnabled(false);
		
		this.addPropertyChangeListener(new IPropertyChangeListener(){

			public void propertyChange(PropertyChangeEvent event) {
				if( event.getProperty().equals(PROPERTY_INTERPRETER)) {
					refreshView();
				}
			}
		});
	}

	protected void removePackage() {
		ISelection selection = this.fElements.getSelection();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection sel = (IStructuredSelection) selection;
			boolean update = false;
			for (Iterator iterator = sel.iterator(); iterator.hasNext();) {
				String pkg = (String) iterator.next();
				boolean res = this.packages.remove(pkg);
				if (res) {
					update = res;
				}
			}
			if (update) {
				refreshView();
			}
		}
	}

	private void refreshView() {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				fElements.refresh();
			}
		});
	}

	protected void addPackage() {
		IInterpreterInstall install = null;
		install = this.getInterpreter();
		if (install == null) {
			install = ScriptRuntime
					.getDefaultInterpreterInstall(TclNature.NATURE_ID);
		}
		if (install != null) {
			final Set names = PackagesManager.getInstance().getPackageNames(
					install);
			ListDialog dialog = new ListDialog(this.fElements.getControl()
					.getShell());
			dialog.setContentProvider(new IStructuredContentProvider() {
				public Object[] getElements(Object inputElement) {
					return names.toArray();
				}

				public void dispose() {
				}

				public void inputChanged(Viewer viewer, Object oldInput,
						Object newInput) {
				}
			});
			dialog.setLabelProvider(new PackagesLabelProvider());
			dialog.setInput(names);
			if (dialog.open() == ListDialog.OK) {
				Object[] result = dialog.getResult();
				for (int i = 0; i < result.length; i++) {
					String pkg = (String) result[i];
					this.packages.add(pkg);
				}
				refreshView();
			}
		} else {
			MessageBox box = new MessageBox(this.fElements.getControl()
					.getShell(), SWT.OK | SWT.ICON_INFORMATION
					| SWT.APPLICATION_MODAL);
			box.setText("Packages");
			box.setText("Project interpreter could not be found...");
			box.open();
		}
	}

	public void initialize(IScriptProject project,
			IBuildpathEntry[] currentEntries) {
		this.scriptProject = project;
		Set set = InterpreterContainerHelper
				.getInterpreterContainerDependencies(project);
		this.packages.addAll(set);
	}

	public IBuildpathEntry getEntry() {
		IBuildpathEntry createPackagesContainer = InterpreterContainerHelper
				.createPackagesContainer(this.packages, getInterpreterPath());
		return createPackagesContainer;
	}
}
