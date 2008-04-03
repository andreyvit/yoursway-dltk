package org.eclipse.dltk.tcl.internal.ui.packages;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.parser.ISourceParserConstants;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementVisitor;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.internal.core.BuiltinProjectFragment;
import org.eclipse.dltk.internal.core.BuiltinSourceModule;
import org.eclipse.dltk.internal.core.ExternalProjectFragment;
import org.eclipse.dltk.internal.core.ExternalSourceModule;
import org.eclipse.dltk.launching.InterpreterContainerHelper;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.core.ast.TclPackageDeclaration;
import org.eclipse.dltk.tcl.internal.ui.text.TclCorrectionProcessor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class FixAllDependenciesActionDelegate implements
		IWorkbenchWindowActionDelegate {
	private ISelection selection;

	public void dispose() {
	}

	public void init(IWorkbenchWindow window) {
	}

	public void run(IAction action) {
		if (this.selection == null) {
			return;
		}
		processSelectionToElements(selection);
	}

	private static class SourceModuleVisitor implements IModelElementVisitor {
		private Set elements;

		public SourceModuleVisitor(Set elements) {
			this.elements = elements;
		}

		/**
		 * Visit only external source modules, witch we aren't builded yet.
		 */
		public boolean visit(IModelElement element) {
			if (element.getElementType() == IModelElement.PROJECT_FRAGMENT) {
				if ((element instanceof ExternalProjectFragment)
						|| (element instanceof BuiltinProjectFragment)) {
					return false;
				}
			}
			if (element.getElementType() == IModelElement.SOURCE_MODULE
					&& !(element instanceof ExternalSourceModule || element instanceof BuiltinSourceModule)
					&& element.getResource() != null) {
				if (!elements.contains(element)) {
					IDLTKLanguageToolkit tk = DLTKLanguageManager
							.getLanguageToolkit((IModelElement) element);
					if (tk != null
							&& tk.getNatureId().equals(TclNature.NATURE_ID)) {
						elements.add(element);
					}
				}
				return false; // do not enter into source module content.
			}
			return true;
		}
	}

	private static class ResourceVisitor implements IResourceVisitor {
		private Set resources;

		public ResourceVisitor(Set resources) {
			this.resources = resources;
		}

		public boolean visit(IResource resource) {
			if (!this.resources.contains(resource)
					&& resource.getType() == IResource.FILE) {
				resources.add(resource);
				return false;
			}
			return true;
		}
	}

//	private static HandleFactory factory = new HandleFactory();

	private static Object convertResourceToModelElement(Object o) {
		if (o instanceof IModelElement) {
			return o;
		}
		if (!(o instanceof IResource)) {
			return null;
		}
		IResource res = (IResource) o;
		IProject project = res.getProject();
		if (!DLTKLanguageManager.hasScriptNature(project)) {
			return null; // Lets pass not script projects.
		}
		IModelElement element = DLTKCore.create(res);
		if (element != null
				&& element.getElementType() == IModelElement.SOURCE_MODULE
				&& element.exists()) {
			return element;
		} else {
			return res;
		}
	}

	public static void processResourcesToElements(Object o, final Set elements,
			final Set resources) {
		if (o instanceof IResource) {
			Set els = new HashSet();
			ResourceVisitor visitor = new ResourceVisitor(els);
			try {
				((IResource) o).accept(visitor);
			} catch (CoreException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			}
			for (Iterator iterator = els.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();

				Object eo = convertResourceToModelElement(object);
				if (eo != null) {
					if (eo instanceof IModelElement && !elements.contains(eo)) {
						IDLTKLanguageToolkit tk = DLTKLanguageManager
								.getLanguageToolkit((IModelElement) eo);
						if (tk != null
								&& tk.getNatureId().equals(
										TclNature.NATURE_ID)) {
							elements.add(eo);
						}
					} else if (eo instanceof IResource
							&& !resources.contains(eo)) {
						resources.add(eo);
					}
				}
			}
		} else if (o instanceof IModelElement) {
			if (o instanceof IParent) {
				SourceModuleVisitor visitor = new SourceModuleVisitor(elements);
				try {
					((IModelElement) o).accept(visitor);
				} catch (ModelException e) {
					if (DLTKCore.DEBUG) {
						e.printStackTrace();
					}
				}
			} else if (!(o instanceof ISourceModule)) {
				ISourceModule module = (ISourceModule) ((IModelElement) o)
						.getAncestor(IModelElement.SOURCE_MODULE);
				if (elements.contains(module)) {
					elements.add(module);
				}
			} else if (o instanceof ISourceModule) {
				if (!elements.contains(o)) {
					elements.add(o);
				}
			}
		}
	}

	protected void processSelectionToElements(ISelection selection) {
		final Set elements = new HashSet();
		final Set resources = new HashSet();
		if (this.selection != null
				&& this.selection instanceof IStructuredSelection) {
			IStructuredSelection sel = (IStructuredSelection) this.selection;
			Iterator iterator = sel.iterator();
			for (; iterator.hasNext();) {
				Object o = iterator.next();
				processResourcesToElements(o, elements, resources);
			}
		}

		Job job = new Job("Fix pacakge dependencies...") {
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Fixing dependencies", elements.size());
				final Map projectToPackages = new HashMap();
				for (Iterator iterator = elements.iterator(); iterator
						.hasNext();) {
					final ISourceModule module = (ISourceModule) iterator
							.next();
//					IResource res = module.getResource();
					ModuleDeclaration declaration = SourceParserUtil
							.getModuleDeclaration(module, null,
									ISourceParserConstants.RUNTIME_MODEL);
					processModule(projectToPackages, module, declaration);
					monitor.worked(1);
				}
				for (Iterator iterator2 = projectToPackages.keySet().iterator(); iterator2
						.hasNext();) {
					IScriptProject project = (IScriptProject) iterator2.next();
					Set values = (Set) projectToPackages.get(project);
					Set names = InterpreterContainerHelper
							.getInterpreterContainerDependencies(project);
					names.addAll(values);
					if (project != null) {
						InterpreterContainerHelper
								.setInterpreterContainerDependencies(project,
										names);
					}
				}
				monitor.done();
				return Status.OK_STATUS;
			}

			private void processModule(final Map projectToPackages,
					final ISourceModule module, ModuleDeclaration declaration) {
				try {
					declaration.traverse(new ASTVisitor() {
						public boolean visit(Statement s) throws Exception {
							if (s instanceof TclPackageDeclaration) {
								TclPackageDeclaration pkg = (TclPackageDeclaration) s;
								if (pkg.getStyle() == TclPackageDeclaration.STYLE_REQUIRE) {
									TclPackageDeclaration copy = new TclPackageDeclaration(
											pkg);
									String pkgName = copy.getName();
									IScriptProject project = module
											.getScriptProject();
									if (TclCorrectionProcessor.isFixable(
											pkgName, project)) {
										if (projectToPackages
												.containsKey(project)) {
											Set pkgs = (Set) projectToPackages
													.get(project);
											pkgs.add(pkgName);
										} else {
											Set pkgs = new HashSet();
											pkgs.add(pkgName);
											projectToPackages
													.put(project, pkgs);
										}
									}
								}
								return false;
							}
							return super.visit(s);
						}
					});
				} catch (Exception e) {
					if (DLTKCore.DEBUG) {
						e.printStackTrace();
					}
				}
			}
		};
		Set resourcesOnly = new HashSet();
		resourcesOnly.addAll(resources);
		for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
			ISourceModule module = (ISourceModule) iterator.next();
			resourcesOnly.add(module.getResource());
		}
		job.setUser(true);
		job.schedule();
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}
}
