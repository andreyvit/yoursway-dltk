package org.eclipse.dltk.tcl.internal.core.packages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.parser.ISourceParserConstants;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.compiler.problem.DefaultProblem;
import org.eclipse.dltk.compiler.problem.IProblemFactory;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.core.builder.IScriptBuilder;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.core.TclParseUtil.CodeModel;
import org.eclipse.dltk.tcl.core.ast.TclPackageDeclaration;

public class TclCheckBuilder implements IScriptBuilder {

	public static final String TCL_PROBLEM_REQUIRE = "tcl.problem.require";
	IScriptProject project;

	public IStatus buildModelElements(IScriptProject project, List elements,
			IProgressMonitor monitor, int status) {
		this.project = project;
		int est = estimateElementsToBuild(elements);
		if (est == 0) {
			if (monitor != null) {
				monitor.done();
			}
			return null;
		}
		IDLTKLanguageToolkit toolkit;
		toolkit = DLTKLanguageManager.getLanguageToolkit(project);
		if (!toolkit.getNatureId().equals(TclNature.NATURE_ID)) {
			return null;
		}
		if (monitor != null) {
			monitor.beginTask("Perfoming code checks", est);
		}

		Map resourceToPackagesList = new HashMap();
		Set packagesInBuild = new HashSet();

		IInterpreterInstall install = null;
		try {
			install = ScriptRuntime.getInterpreterInstall(project);
		} catch (CoreException e1) {
			if (DLTKCore.DEBUG) {
				e1.printStackTrace();
			}
		}
		if (install == null) {
			return null;
		}

		PackagesManager manager = PackagesManager.getInstance();
		Set packageNames = manager.getPackageNames(install);
		Set buildpath = getBuildpath(project);

		Set packageNamesInProject = new HashSet();
		if (status != IScriptBuilder.FULL_BUILD) {
			Set names = manager.getInternalPackageNames(install, project);
			if (names != null) {
				packageNamesInProject.addAll(names);
			}
		}
		processSources(elements, monitor, resourceToPackagesList,
				packagesInBuild, packageNamesInProject);

		manager.setInternalPackageNames(install, packageNamesInProject);

		// This method will populate all required paths.
		manager.getPathsForPackages(install, packagesInBuild);

		Set keySet = resourceToPackagesList.keySet();
		IProblemFactory factory;
		factory = DLTKLanguageManager.getProblemFactory(toolkit.getNatureId());
		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			if (monitor != null && monitor.isCanceled()) {
				return null;
			}
			ISourceModule module = (ISourceModule) iterator.next();
			try {
				cleanMarkers(module.getResource());
			} catch (CoreException e1) {
				if (DLTKCore.DEBUG) {
					e1.printStackTrace();
				}
			}
			List pkgs = (List) resourceToPackagesList.get(module);
			CodeModel model = null;
			try {
				model = new CodeModel(module.getSource());
			} catch (ModelException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
				continue;
			}

			IProblemReporter reporter = factory.createReporter(module
					.getResource());
			for (Iterator iterator2 = pkgs.iterator(); iterator2.hasNext();) {
				TclPackageDeclaration pkg = (TclPackageDeclaration) iterator2
						.next();
				checkPackage(pkg, packageNames, reporter, model, manager,
						install, buildpath, project);
			}
		}

		if (monitor != null) {
			monitor.done();
		}
		return null;
	}

	private static Set getBuildpath(IScriptProject project) {
		IBuildpathEntry[] resolvedBuildpath;
		try {
			resolvedBuildpath = project.getResolvedBuildpath(true);
		} catch (ModelException e1) {
			e1.printStackTrace();
			return null;
		}
		Set buildpath = new HashSet();
		for (int i = 0; i < resolvedBuildpath.length; i++) {
			if (resolvedBuildpath[i].getEntryKind() == IBuildpathEntry.BPE_LIBRARY
					&& resolvedBuildpath[i].isExternal()) {
				buildpath.add(resolvedBuildpath[i].getPath());
			}
		}
		return buildpath;
	}

	private void processSources(List elements, IProgressMonitor monitor,
			Map resourceToPackagesList, Set packagesInBuild,
			Set packageNamesInProject) {
		for (int i = 0; i < elements.size(); i++) {
			IModelElement element = (IModelElement) elements.get(i);
			if (element.getElementType() == IModelElement.SOURCE_MODULE) {
				IProjectFragment projectFragment = (IProjectFragment) element
						.getAncestor(IModelElement.PROJECT_FRAGMENT);
				if (!projectFragment.isExternal()) {
					try {
						if (monitor != null) {
							String taskTitle = "Performing code checks for "
									+ project.getElementName() + " ("
									+ (elements.size() - i) + "):"
									+ element.getElementName();
							monitor.subTask(taskTitle);
						}
						IDLTKLanguageToolkit toolkit = DLTKLanguageManager
								.getLanguageToolkit(element);
						if (toolkit == null) {
							if (monitor != null) {
								monitor.worked(1);
							}
							continue;
						}

						ISourceModule module = (ISourceModule) element;
						// IResource resource = module.getResource();
						// cleanMarkers(resource);

						ModuleDeclaration declaration = SourceParserUtil
								.getModuleDeclaration(module, null,
										ISourceParserConstants.RUNTIME_MODEL);

						final ArrayList list = new ArrayList();
						resourceToPackagesList.put(module, list);
						fillPackagesDeclarations(declaration, list,
								packagesInBuild, packageNamesInProject);

						if (monitor != null) {
							monitor.worked(1);
						}
					} catch (CoreException e) {
						if (DLTKCore.DEBUG) {
							e.printStackTrace();
						}
					} catch (Exception e) {
						if (DLTKCore.DEBUG) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	public static void cleanMarkers(IResource resource) throws CoreException {
		IMarker[] findMarkers = resource.findMarkers(
				DefaultProblem.MARKER_TYPE_PROBLEM, true,
				IResource.DEPTH_INFINITE);
		for (int j = 0; j < findMarkers.length; j++) {
			if (findMarkers[j].getAttribute(TCL_PROBLEM_REQUIRE, null) != null) {
				findMarkers[j].delete();
			}
		}
	}

	public static void fillPackagesDeclarations(ModuleDeclaration declaration,
			final ArrayList list, final Set packagesInBuild,
			final Set packageNamesInProject) throws Exception {
		declaration.traverse(new ASTVisitor() {
			public boolean visit(Statement s) throws Exception {
				if (s instanceof TclPackageDeclaration) {
					TclPackageDeclaration pkg = (TclPackageDeclaration) s;
					if (pkg.getStyle() == TclPackageDeclaration.STYLE_REQUIRE) {
						TclPackageDeclaration copy = new TclPackageDeclaration(
								pkg);
						String name = copy.getName();
						if (name.indexOf("$") == -1) {
							if (list != null) {
								list.add(copy);
							}
							packagesInBuild.add(name);
						}
					} else if (pkg.getStyle() == TclPackageDeclaration.STYLE_IFNEEDED
							|| pkg.getStyle() == TclPackageDeclaration.STYLE_PROVIDE) {
						packageNamesInProject.add(pkg.getName());
					}
					return false;
				}
				return super.visit(s);
			}
		});
	}

	private static void reportPackageProblem(TclPackageDeclaration pkg,
			IProblemReporter reporter, CodeModel model, String name,
			String pkgName) {
		try {
			IMarker marker = reporter.reportProblem(new DefaultProblem("",
					name, 777, null, ProblemSeverities.Error,
					pkg.sourceStart(), pkg.sourceEnd(), model.getLineNumber(pkg
							.sourceStart(), pkg.sourceEnd())));
			marker.setAttribute(TCL_PROBLEM_REQUIRE, pkgName);
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
	}

	public static void checkPackage(TclPackageDeclaration pkg,
			Set packageNames, IProblemReporter reporter, CodeModel model,
			PackagesManager manager, IInterpreterInstall install,
			Set buildpath, IScriptProject scriptProject) {
		if (pkg.getStyle() == TclPackageDeclaration.STYLE_REQUIRE) {
			String packageName = pkg.getName();

			Set internalNames = manager.getInternalPackageNames(install,
					scriptProject);
			if (internalNames.contains(packageName)) {
				return;
			}
			if (packageIsFiltered(packageName)) {
				return;
			}

			// Report unknown projects
			if (!packageNames.contains(packageName)
					&& !internalNames.contains(packageName)) {
				try {
					IMarker marker = reporter.reportProblem(new DefaultProblem(
							"", "Unknown package:" + packageName, 777, null,
							ProblemSeverities.Error, pkg.sourceStart(), pkg
									.sourceEnd(), model.getLineNumber(pkg
									.sourceStart(), pkg.sourceEnd())));
					marker.setAttribute(TCL_PROBLEM_REQUIRE, packageName);
				} catch (CoreException e) {
					if (DLTKCore.DEBUG) {
						e.printStackTrace();
					}
				}
				return;
			}

			// Receive main package and it paths.
			boolean error = checkPackage(pkg, reporter, model, manager,
					install, buildpath, packageName, scriptProject);

			Map dependencies = manager.getDependencies(packageName, install);
			for (Iterator iterator = dependencies.keySet().iterator(); iterator
					.hasNext();) {
				String pkgName = (String) iterator.next();
				boolean fail = checkPackage(pkg, reporter, model, manager,
						install, buildpath, pkgName, scriptProject);
				if (fail) {
					error = true;
				}
			}
			if (error) {
				reportPackageProblem(pkg, reporter, model, "Package "
						+ packageName + " has unresolved dependencies.",
						packageName);
			}
		}
	}

	private static boolean packageIsFiltered(String packageName) {
		if (packageName == null || packageName.length() == 0) {
			return true;
		}
		if (packageName.indexOf("$") != -1 || packageName.indexOf("[") != -1
				|| packageName.indexOf("]") != -1) {
			return true;
		}
		return false;
	}

	public static boolean checkPackage(TclPackageDeclaration pkg,
			IProblemReporter reporter, CodeModel model,
			PackagesManager manager, IInterpreterInstall install,
			Set buildpath, String packageName, IScriptProject project) {

		Set names = manager.getInternalPackageNames(install, project);
		if (names != null) { // Internal package check
			if (names.contains(packageName)) {
				return false;
			}
		}

		IPath[] paths = manager.getPathsForPackage(install, packageName);
		// Check what package path are in project buildpath.
		return checkPackagePaths(pkg, reporter, model, buildpath, paths,
				packageName);
	}

	private static boolean checkPackagePaths(TclPackageDeclaration pkg,
			IProblemReporter reporter, CodeModel model, Set buildpath,
			IPath[] paths, String packageName) {
		boolean error = false;
		List notPaths = new ArrayList();
		if (paths != null) {
			for (int i = 0; i < paths.length; i++) {
				if (!buildpath.contains(paths[i])) {
					boolean prefix = false;
					for (Iterator iterator = buildpath.iterator(); iterator
							.hasNext();) {
						IPath pp = (IPath) iterator.next();
						if (pp.isPrefixOf(paths[i])) {
							prefix = true;
							break;
						}
					}
					if (!prefix) {
						error = true;
						notPaths.add(paths[i]);
					}
				}
			}
		}
		return error;
	}

	public IStatus buildResources(IScriptProject project, List resources,
			IProgressMonitor monitor, int status) {
		return null;
	}

	public int estimateElementsToBuild(List elements) {
		int estimation = 0;
		for (int i = 0; i < elements.size(); i++) {
			IModelElement element = (IModelElement) elements.get(i);
			if (element.getElementType() == IModelElement.SOURCE_MODULE) {
				IProjectFragment projectFragment = (IProjectFragment) element
						.getAncestor(IModelElement.PROJECT_FRAGMENT);
				if (!projectFragment.isExternal())
					estimation++;
			}
		}
		return estimation;
	}

	public static void checkPackage(TclPackageDeclaration pkg,
			IProblemReporter reporter, IScriptProject scriptProject,
			CodeModel model) {
		IInterpreterInstall install = null;
		try {
			install = ScriptRuntime.getInterpreterInstall(scriptProject);
		} catch (CoreException e1) {
			if (DLTKCore.DEBUG) {
				e1.printStackTrace();
			}
		}
		if (install == null) {
			return;
		}
		Set buildpath = getBuildpath(scriptProject);
		PackagesManager manager = PackagesManager.getInstance();
		Set packageNames = manager.getPackageNames(install);
		checkPackage(pkg, packageNames, reporter, model, manager, install,
				buildpath, scriptProject);
	}

	public Set getDependencies(IScriptProject project, Set resources,
			Set allResources, Set oldExternalFolders, Set externalFolders) {
		if (oldExternalFolders.size() != externalFolders.size()) {
			// We need to rebuild all elements in this builder.
			return allResources;
		}
		Set min = new HashSet();
		min.addAll(oldExternalFolders);
		min.removeAll(externalFolders);
		if (min.size() != 0) {
			return allResources;
		}
		return null;
	}
}
