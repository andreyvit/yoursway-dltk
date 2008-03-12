/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.preferences;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.search.indexing.IndexManager;
import org.eclipse.dltk.internal.core.ModelManager;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;

public class ScriptCorePreferencePage extends
		AbstractConfigurationBlockPreferencePage {

	protected IPreferenceConfigurationBlock createConfigurationBlock(
			OverlayPreferenceStore store) {
		return new ImprovedAbstractConfigurationBlock(store, this) {
			public Control createControl(Composite parent) {
				Composite composite = SWTFactory.createComposite(parent, parent
						.getFont(), 2, 1, GridData.FILL);
				if (DLTKCore.SHOW_REINDEX) {
					Label l = new Label(composite, SWT.PUSH);
					l.setText(Messages.ScriptCorePreferencePage_manualReindex);
					Button reCreateIndex = new Button(composite, SWT.PUSH);
					reCreateIndex.setText(Messages.ScriptCorePreferencePage_reindex);
					reCreateIndex.addSelectionListener(new SelectionListener() {

						public void widgetDefaultSelected(SelectionEvent e) {
						}

						public void widgetSelected(SelectionEvent e) {
							IndexManager indexManager = ModelManager
									.getModelManager().getIndexManager();
							indexManager.rebuild();

							try {
								PlatformUI.getWorkbench().getProgressService()
										.run(false, true,
												new IRunnableWithProgress() {
													public void run(
															IProgressMonitor monitor)
															throws InvocationTargetException,
															InterruptedException {
														try {
															ResourcesPlugin
																	.getWorkspace()
																	.build(
																			IncrementalProjectBuilder.FULL_BUILD,
																			monitor);
														} catch (CoreException e) {
															// TODO
															// Auto-generated
															// catch
															// block
															e.printStackTrace();
														}
													}

												});
							} catch (InvocationTargetException e3) {
								// TODO Auto-generated catch block
								e3.printStackTrace();
							} catch (InterruptedException e3) {
								// TODO Auto-generated catch block
								e3.printStackTrace();
							}
						}
					});
				}
				return composite;
			}
		};
	}

	protected String getHelpId() {
		return null;
	}

	protected void setDescription() {
		setDescription(""); //$NON-NLS-1$
	}

	protected void setPreferenceStore() {
		setPreferenceStore(DLTKUIPlugin.getDefault().getPreferenceStore());
	}
}
