/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.tclchecker;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.validators.core.AbstractValidator;
import org.eclipse.dltk.validators.core.IValidatorType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TclCheckerImpl extends AbstractValidator {
	boolean initialized = false;

	protected TclCheckerImpl(String id, IValidatorType type) {
		super(id, null, type);
	}

	protected TclCheckerImpl(String id, String name, IValidatorType type) {
		super(id, name, type);
	}

	protected TclCheckerImpl(String id, Element element, IValidatorType type)
			throws IOException {
		super(id, null, type);
		loadFrom(element);
	}

	protected void loadFrom(Element element) {
		if (initialized) {
			return;
		}
		initialized = true;
		super.loadFrom(element);
	}

	public void storeTo(Document doc, Element element) {
		super.storeTo(doc, element);
	}

	public IStatus validate(IResource resource[], OutputStream console) {
		return Status.CANCEL_STATUS;
	}

	public IStatus validate(ISourceModule[] module, OutputStream console) {
		try {
			List els = new ArrayList();
			// els.add(module);
			for (int i = 0; i < module.length; i++) {
				IResource res = module[i].getResource();
				if (res != null) {
					els.add(module[i]);
					try {
						TclCheckerMarker.clearMarkers(res);
					} catch (CoreException e) {
						if (DLTKCore.DEBUG) {
							e.printStackTrace();
						}
					}
				}
			}
			if (els.size() == 0) {
				return Status.OK_STATUS;
			}
			if (getProgressMonitor() != null) {
				getProgressMonitor().beginTask("Checking with tclchecker",
						els.size());
			}
			TclChecker checker = new TclChecker(TclCheckerPlugin.getDefault()
					.getPreferenceStore());

			IProgressMonitor progressMonitor = getProgressMonitor();
			checker.check(els, progressMonitor, console);
			return Status.OK_STATUS;
		} finally {
			if (getProgressMonitor() != null) {
				getProgressMonitor().done();
			}
		}
	}

	public boolean isValidatorValid() {
		TclChecker checker = new TclChecker(TclCheckerPlugin.getDefault()
				.getPreferenceStore());

		return checker.canCheck();
	}

	public void clean(ISourceModule[] module) {
		for (int i = 0; i < module.length; i++) {
			clean(module[i].getResource());
		}
	}

	public void clean(IResource[] resources) {
		for (int i = 0; i < resources.length; i++) {
			if (resources[i] != null) {
				clean(resources[i]);
			}
		}
	}

	public void clean(IResource resource) {
		try {
			TclCheckerMarker.clearMarkers(resource);
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
	}
}
