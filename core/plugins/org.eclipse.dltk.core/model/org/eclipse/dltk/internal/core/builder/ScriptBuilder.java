package org.eclipse.dltk.internal.core.builder;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementVisitor;
import org.eclipse.dltk.core.IModelMarker;
import org.eclipse.dltk.core.builder.IScriptBuilder;

public class ScriptBuilder extends IncrementalProjectBuilder {
	public static final boolean DEBUG = DLTKCore.DEBUG_SCRIPT_BUILDER;

	class ResourceVisitor implements IResourceDeltaVisitor, IResourceVisitor {
		private List resources;

		public ResourceVisitor(List resources) {
			this.resources = resources;
		}

		public boolean visit(IResourceDelta delta) throws CoreException {
			IResource resource = delta.getResource();
			switch (delta.getKind()) {
			case IResourceDelta.ADDED:
			case IResourceDelta.CHANGED:
				if (!this.resources.contains(resource) && resource.getType() == IResource.FILE ) {
					resources.add(resource);
				}
				break;
			}
			return true;
		}

		public boolean visit(IResource resource) {
			if (!this.resources.contains(resource)&& resource.getType() == IResource.FILE) {
				resources.add(resource);
			}
			return true;
		}
	}

	class ModelModuleVisitor implements IModelElementVisitor {
		private List elements;

		public ModelModuleVisitor(List elements) {
			this.elements = elements;
		}

		public boolean visit(IModelElement element) {
			if (element.getElementType() == IModelElement.SOURCE_MODULE) {
				if (!elements.contains(element)) {
					elements.add(element);
				}
				return false; // do not enter into source module content.
			}
			return true;
		}
	}

	/**
	 * Hook allowing to initialize some static state before a complete build
	 * iteration. This hook is invoked during PRE_AUTO_BUILD notification
	 */
	public static void buildStarting() {
		// build is about to start
	}

	/**
	 * Hook allowing to reset some static state after a complete build
	 * iteration. This hook is invoked during POST_AUTO_BUILD notification
	 */
	public static void buildFinished() {
		if (DLTKCore.DEBUG)
			System.out.println("build finished");
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
			List resources = new ArrayList();

			// Project real resources.
			IProject project = getProject();
			if (!DLTKLanguageManager.hasScriptNature(project)) {
				return;
			}
			IDLTKProject dltkProject = DLTKCore.create(project);
			project.accept(new ResourceVisitor(resources));
			// Call builders for resources.
			buildResources(resources);

			// Project external resources should also be added into list. Only
			// on full build we need to manage this.
			List elements = new ArrayList();
			dltkProject.accept(new ModelModuleVisitor(elements));
			buildElements(elements);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	protected void incrementalBuild(IResourceDelta delta,
			IProgressMonitor monitor) throws CoreException {
		IProject project = getProject();
		if (!DLTKLanguageManager.hasScriptNature(project)) {
			return;
		}
		List resources = new ArrayList();
		delta.accept(new ResourceVisitor(resources));
		// Call builders for resources.
		List actualResourcesToBuild = findDependencies(resources);
		buildResources(actualResourcesToBuild);
	}

	protected void buildResources(List resources) {
		List status = new ArrayList();
		IDLTKProject dltkProject = DLTKCore.create(getProject());
		for (int i = 0; i < resources.size(); ++i) {
			IResource res = (IResource) resources.get(i);
			IProject project = res.getProject();
			String[] natureIds = null;
			try {
				natureIds = project.getDescription().getNatureIds();
			} catch (CoreException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
				continue;
			}
			// Check if resource is model element.
			try {
				IModelElement element = dltkProject.findElement(res
						.getProjectRelativePath());
				if (element != null && element.getElementType() == IModelElement.SOURCE_MODULE ) {
					IDLTKLanguageToolkit toolkit = DLTKLanguageManager
							.getLanguageToolkit(element);
					if (toolkit != null) {
						IScriptBuilder[] builders = ScriptBuilderManager
								.getScriptBuilders(toolkit.getNatureID());
						if (builders != null) {
							for (int k = 0; k < builders.length; k++) {
								IStatus s = builders[k].buildModelElement(element);
								if (s != null && s.getSeverity() != IStatus.OK) {
									status.add(s);
								}
							}
						}
						continue;
					}
				}
			} catch (CoreException e1) {
				e1.printStackTrace();
			}
			//Else build as resource.
			for (int j = 0; j < natureIds.length; j++) {
				try {
					IScriptBuilder[] builders = ScriptBuilderManager
							.getScriptBuilders(natureIds[j]);
					if (builders != null) {
						for (int k = 0; k < builders.length; k++) {
							IStatus s = builders[k].buildResources(res);
							if (s != null && s.getSeverity() != IStatus.OK) {
								status.add(s);
							}
						}
					}
				} catch (CoreException e) {
					if (DLTKCore.DEBUG) {
						e.printStackTrace();
					}
				}
			}
		}
		// TODO: Do something with status.
	}

	protected void buildElements(List elements) {
		List status = new ArrayList();
		for (int i = 0; i < elements.size(); ++i) {
			IModelElement elem = (IModelElement) elements.get(i);
			try {
				IDLTKLanguageToolkit toolkit = DLTKLanguageManager
						.getLanguageToolkit(elem);
				if (toolkit == null) {
					// TODO: Add error reporting here...
					continue;
				}
				IScriptBuilder[] builders = ScriptBuilderManager
						.getScriptBuilders(toolkit.getNatureID());
				if (builders != null) {
					for (int k = 0; k < builders.length; k++) {
						IStatus s = builders[k].buildModelElement(elem);
						if (s != null && s.getSeverity() != IStatus.OK) {
							status.add(s);
						}
					}
				}
			} catch (CoreException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			}
		}
		// TODO: Do something with status.
	}

	private List findDependencies(List resources) {
		try {
			IScriptBuilder[] builders = ScriptBuilderManager
					.getAllScriptBuilders();
			List elementsToCheck = new ArrayList();
			elementsToCheck.addAll(resources);
			List result = new ArrayList();
			result.addAll(resources);
			while (elementsToCheck.size() > 0) {
				List newElementsToCheck = new ArrayList();
				for (int i = 0; i < builders.length; ++i) {
					List newResources = builders[i]
							.getDependencies(elementsToCheck);
					if (newResources != null) {
						newElementsToCheck.addAll(newResources);
					}
				}
				for (int i = 0; i < newElementsToCheck.size(); ++i) {
					Object o = newElementsToCheck.get(i);
					if (!result.contains(o)) {
						result.add(o);
					}
				}
				elementsToCheck.clear();
				elementsToCheck.addAll(newElementsToCheck);
			}
			return result;
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return resources;
	}

	public static void removeProblemsAndTasksFor(IResource resource) {
		try {
			if (resource != null && resource.exists()) {
				resource.deleteMarkers(
						IModelMarker.SCRIPT_MODEL_PROBLEM_MARKER, false,
						IResource.DEPTH_INFINITE);
				resource.deleteMarkers(IModelMarker.TASK_MARKER, false,
						IResource.DEPTH_INFINITE);

				// delete managed markers
			}
		} catch (CoreException e) {
			// assume there were no problems
		}
	}

	public static void writeState(Object state, DataOutputStream out)
			throws IOException {
	}
}
