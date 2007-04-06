package org.eclipse.dltk.tcl.internal.tclchecker;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.validators.core.AbstractValidator;
import org.eclipse.dltk.validators.core.IValidatorType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TclCheckerImpl extends AbstractValidator {
	private static final String JOB_NAME = "Checking with TclChecker";
	protected TclCheckerImpl(String id, IValidatorType type) {
		super(id, null, type);
	}

	protected TclCheckerImpl(String id, String name, IValidatorType type) {
		super(id, name, type);
	}

	protected TclCheckerImpl(String id, Element element, IValidatorType type)
			throws IOException {
		super(id, null, type);
	}

	public void storeTo(Document doc, Element element) {
	}

	public IStatus validate(IResource resource, OutputStream console) {
		return Status.OK_STATUS;
	}
	public IStatus validate(ISourceModule module, OutputStream console) {
		IResource resource = module.getResource();
		TclChecker checker = new TclChecker(TclCheckerPlugin.getDefault()
				.getPreferenceStore());
		
		if (!checker.canCheck()){
			try {
				TclCheckerMarker.clearMarkers(resource);
			} catch (CoreException e) {
				e.printStackTrace();
			}
			return Status.CANCEL_STATUS;
		}
	
		List els = new ArrayList();
		els.add( module );
		checker.check(els, JOB_NAME, false);
		return Status.OK_STATUS;
	}

	public boolean isValidatorValid() {
		TclChecker checker = new TclChecker(TclCheckerPlugin.getDefault()
				.getPreferenceStore());
		
		return checker.canCheck();
	}
}
