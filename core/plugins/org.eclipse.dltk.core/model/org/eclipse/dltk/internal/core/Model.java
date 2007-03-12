package org.eclipse.dltk.internal.core;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptModel;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.WorkingCopyOwner;
import org.eclipse.dltk.internal.core.util.MementoTokenizer;
import org.eclipse.dltk.utils.CorePrinter;


public class Model extends Openable implements IScriptModel {
	/**
	 * A set of java.io.Files used as a cache of external files that are known
	 * to be existing. Note this cache is kept for the whole session.
	 */
	public static HashSet existingExternalFiles = new HashSet();

	/**
	 * A set of external files ({@link #existingExternalFiles}) which have
	 * been confirmed as file (ie. which returns true to
	 * {@link java.io.File#isFile()}. Note this cache is kept for the whole
	 * session.
	 */
	public static HashSet existingExternalConfirmedFiles = new HashSet();

	protected Model() {
		super(null);
	}

	protected boolean buildStructure(OpenableElementInfo info,
			IProgressMonitor pm, Map newElements, IResource underlyingResource) /*
																				 * throws
																				 * ModelException
																				 */{
		// determine my children
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot()
				.getProjects();
		for (int i = 0, max = projects.length; i < max; i++) {
			IProject project = projects[i];
			if (DLTKLanguageManager.hasScriptNature(project)) {
				info.addChild(getScriptProject(project));
			}
		}
		newElements.put(this, info);
		return true;
	}

	/**
	 * Helper method - returns the targeted item (IResource if internal or
	 * java.io.File if external), or null if unbound Internal items must be
	 * referred to using container relative paths.
	 */
	public static Object getTarget(IContainer container, IPath path,
			boolean checkResourceExistence) {
		if (path == null)
			return null;
		// lookup - inside the container
		if (path.getDevice() == null) { // container relative paths should not
			// contain a device
			// (see http://dev.eclipse.org/bugs/show_bug.cgi?id=18684)
			// (case of a workspace rooted at d:\ )
			IResource resource = container.findMember(path);
			if (resource != null) {
				if (!checkResourceExistence || resource.exists())
					return resource;
				return null;
			}
		}
		// if path is relative, it cannot be an external path
		// (see http://dev.eclipse.org/bugs/show_bug.cgi?id=22517)
		if (!path.isAbsolute())
			return null;
		// lookup - outside the container
		File externalFile = new File(path.toOSString());
		if (!checkResourceExistence) {
			return externalFile;
		} else if (existingExternalFiles.contains(externalFile)) {
			return externalFile;
		} else {
			if (ModelManager.ZIP_ACCESS_VERBOSE) {
				System.out
						.println("(" + Thread.currentThread() + ") [Model.getTarget(...)] Checking existence of " + path.toString()); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if (externalFile.exists()) {
				// cache external file
				existingExternalFiles.add(externalFile);
				return externalFile;
			}
		}
		return null;
	}

	/**
	 * Returns the active script project associated with the specified resource,
	 * or <code>null</code> if no script project yet exists for the resource.
	 * 
	 * @exception IllegalArgumentException
	 *                if the given resource is not one of an IProject, IFolder,
	 *                or IFile.
	 */
	public IDLTKProject getScriptProject(IResource resource) {
		switch (resource.getType()) {
		case IResource.FOLDER:
			return new DLTKProject(((IFolder) resource).getProject(), this);
		case IResource.FILE:
			return new DLTKProject(((IFile) resource).getProject(), this);
		case IResource.PROJECT:
			return new DLTKProject((IProject) resource, this);
		default:
			throw new IllegalArgumentException(
					"invalid resource for the project");
		}
	}

	/**
	 * @see IScriptModel
	 */
	public IDLTKProject getScriptProject(String projectName) {
		return new DLTKProject(ResourcesPlugin.getWorkspace().getRoot()
				.getProject(projectName), this);
	}

	/**
	 * @see IScriptModel
	 */
	public IDLTKProject[] getScriptProjects() throws ModelException {
		ArrayList list = getChildrenOfType(SCRIPT_PROJECT);
		IDLTKProject[] array = new IDLTKProject[list.size()];
		list.toArray(array);
		return array;
	}

	public void copy(IModelElement[] elements, IModelElement[] containers,
			IModelElement[] siblings, String[] renamings, boolean force,
			IProgressMonitor monitor) throws ModelException {
		if (elements != null && elements.length > 0 && elements[0] != null
				&& elements[0].getElementType() < IModelElement.TYPE) {
			runOperation(new CopyResourceElementsOperation(elements,
					containers, force), elements, siblings, renamings, monitor);
		} else {
			runOperation(
					new CopyElementsOperation(elements, containers, force),
					elements, siblings, renamings, monitor);
		}
	}

	protected Object createElementInfo() {
		return new ModelInfo();
	}

	public int getElementType() {
		return SCRIPT_MODEL;
	}

	public IResource getResource() {
		return ResourcesPlugin.getWorkspace().getRoot();
	}

	public IPath getPath() {
		return Path.ROOT;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Model))
			return false;
		return super.equals(o);
	}

	/**
	 * Flushes the cache of external files known to be existing.
	 */
	public static void flushExternalFileCache() {
		existingExternalFiles = new HashSet();
		existingExternalConfirmedFiles = new HashSet();
	}

	/**
	 * Configures and runs the <code>MultiOperation</code>.
	 */
	protected void runOperation(MultiOperation op, IModelElement[] elements,
			IModelElement[] siblings, String[] renamings,
			IProgressMonitor monitor) throws ModelException {
		op.setRenamings(renamings);
		if (siblings != null) {
			for (int i = 0; i < elements.length; i++) {
				op.setInsertBefore(elements[i], siblings[i]);
			}
		}
		op.runOperation(monitor);
	}

	/**
	 * @private Debugging purposes
	 */
	protected void toStringInfo(int tab, StringBuffer buffer, Object info,
			boolean showResolvedInfo) {
		buffer.append(this.tabString(tab));
		buffer.append("Model"); //$NON-NLS-1$
		if (info == null) {
			buffer.append(" (not open)"); //$NON-NLS-1$
		}
	}

	public void printNode(CorePrinter output) {
		output.formatPrint("DLTK Model:" + getElementName());
		output.indent();
		try {
			IModelElement modelElements[] = this.getChildren();
			for (int i = 0; i < modelElements.length; ++i) {
				IModelElement element = modelElements[i];
				if (element instanceof ModelElement) {
					((ModelElement) element).printNode(output);
				} else {
					output.print("Unknown element:" + element);
				}
			}
		} catch (ModelException ex) {
			output.formatPrint(ex.getLocalizedMessage());
		}
		output.dedent();
	}
	
	public void delete(IModelElement[] elements, boolean force,
			IProgressMonitor monitor) throws ModelException {
		if (elements != null && elements.length > 0 && elements[0] != null
				&& elements[0].getElementType() < IModelElement.TYPE) {
			new DeleteResourceElementsOperation(elements, force)
					.runOperation(monitor);
		} else {
			// new DeleteElementsOperation(elements,
			// force).runOperation(monitor);
		}
		if (DLTKCore.DEBUG) {
			System.err.println("Add Delete operations");
		}
	}

	/**
	 * Helper method - returns the file item (ie. which returns true to
	 * {@link java.io.File#isFile()}, or null if unbound
	 */
	public static synchronized File getFile(Object target) {
		if (existingExternalConfirmedFiles.contains(target))
			return (File) target;
		if (target instanceof File) {
			File f = (File) target;
			if (f.isFile()) {
				existingExternalConfirmedFiles.add(f);
				return f;
			}
		}
		return null;
	}

	/**
	 * Helper method - returns whether an object is afile (ie. which returns
	 * true to {@link java.io.File#isFile()}.
	 */
	public static boolean isFile(Object target) {
		return getFile(target) != null;
	}

	/*
	 * @see IScriptModel
	 */
	public boolean contains(IResource resource) {
		switch (resource.getType()) {
		case IResource.ROOT:
		case IResource.PROJECT:
			return true;
		}
		// file or folder
		IDLTKProject[] projects;
		try {
			projects = this.getScriptProjects();
		} catch (ModelException e) {
			return false;
		}
		for (int i = 0, length = projects.length; i < length; i++) {
			DLTKProject project = (DLTKProject) projects[i];
			if (!project.contains(resource)) {
				return false;
			}
		}
		return true;
	}

	public IModelElement getHandleFromMemento(String token,
			MementoTokenizer memento, WorkingCopyOwner owner) {
		switch (token.charAt(0)) {
		case JEM_SCRIPTPROJECT:
			if (!memento.hasMoreTokens())
				return this;
			String projectName = memento.nextToken();
			ModelElement project = (ModelElement) getScriptProject(projectName);
			return project.getHandleFromMemento(memento, owner);
		}
		return null;
	}

	protected char getHandleMementoDelimiter() {
		Assert.isTrue(false, "Should not be called"); //$NON-NLS-1$
		return 0;
	}

	public Object[] getForeignResources() throws ModelException {
		return ((ModelInfo) getElementInfo()).getForeignResources();
	}

	public IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	public void move(IModelElement[] elements, IModelElement[] containers,
			IModelElement[] siblings, String[] renamings, boolean force,
			IProgressMonitor monitor) throws ModelException {
		if (elements != null && elements.length > 0 && elements[0] != null
				&& elements[0].getElementType() < IModelElement.TYPE) {
			runOperation(new MoveResourceElementsOperation(elements,
					containers, force), elements, siblings, renamings, monitor);
		} else {
			if (DLTKCore.DEBUG) {
				System.err.println("TODO:Add move elements operation");
			}
			// runOperation(new MoveElementsOperation(elements, containers,
			// force), elements, siblings, renamings, monitor);
		}
	}

	public IResource getUnderlyingResource() {
		return null;
	}

	protected void getHandleMemento(StringBuffer buff) {
		buff.append(getElementName());
	}

	public void rename(IModelElement[] elements, IModelElement[] destinations,
			String[] renamings, boolean force, IProgressMonitor monitor)
			throws ModelException {
		MultiOperation op;
		if (elements != null && elements.length > 0 && elements[0] != null
				&& elements[0].getElementType() < IModelElement.TYPE) {
			op = new RenameResourceElementsOperation(elements, destinations,
					renamings, force);
		} else {
			op = new RenameElementsOperation(elements, destinations, renamings,
					force);
		}

		op.runOperation(monitor);
	}
}
