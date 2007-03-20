package org.eclipse.dltk.tcl.internal.tclchecker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;


public class TclCheckerBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = TclCheckerPlugin.PLUGIN_ID
			+ ".tclcheckerbuilder";
	
	private static final String JOB_NAME = "Checking with TclChecker";

	class ResourceVisitor implements IResourceDeltaVisitor, IResourceVisitor {
		private List resources;

		public ResourceVisitor(List resources) {
			this.resources = resources;
		}

		public boolean visit(IResourceDelta delta) throws CoreException {
			IResource resource = delta.getResource();
			switch (delta.getKind()) {
			case IResourceDelta.ADDED:
				resources.add(resource);
				break;
			case IResourceDelta.CHANGED:
				resources.add(resource);
				break;
			}
			return true;
		}

		public boolean visit(IResource resource) {
			resources.add(resource);
			return true;
		}
	}

	public TclCheckerBuilder() {
	}

	protected IProject[] build(int kind, Map args, IProgressMonitor monitor)
			throws CoreException {
		if (kind == FULL_BUILD) {
			fullBuild(monitor);
		} else {
			IResourceDelta delta = getDelta(getProject());
			if (delta == null) {
				fullBuild(monitor);
			} else {
				incrementalBuild(delta, monitor);
			}
		}
		return null;
	}

	protected void fullBuild(final IProgressMonitor monitor)
			throws CoreException {
		try {
			List resource = new ArrayList();
			getProject().accept(new ResourceVisitor(resource));
			runTclChecker(resource);
		} catch (CoreException e) {
		}
	}

	protected void incrementalBuild(IResourceDelta delta,
			IProgressMonitor monitor) throws CoreException {
		List reources = new ArrayList();
		delta.accept(new ResourceVisitor(reources));
		runTclChecker(reources);
	}

	protected void runTclChecker(List resources) throws CoreException {
		List sourceModules = new ArrayList();

		Iterator it = resources.iterator();
		while (it.hasNext()) {
			IModelElement element = DLTKCore.create((IResource) it.next());
			if (element instanceof ISourceModule) {
				sourceModules.add(element);
			}
		}
		
		TclChecker checker = new TclChecker(TclCheckerPlugin.getDefault()
				.getPreferenceStore());
		
		if (!checker.canCheck()){
			TclCheckerNature.removeNature(getProject());
			TclCheckerMarker.clearMarkers(getProject());
			return;
		}
	
		checker.check(sourceModules, JOB_NAME, false);
	}
}
