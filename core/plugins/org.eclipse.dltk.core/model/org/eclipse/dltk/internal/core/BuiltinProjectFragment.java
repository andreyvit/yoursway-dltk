package org.eclipse.dltk.internal.core;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathContainer;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IBuiltinModuleProvider;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelStatusConstants;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.WorkingCopyOwner;
import org.eclipse.dltk.internal.core.util.MementoTokenizer;
import org.eclipse.dltk.internal.core.util.Util;

/**
 * Project fragment for buildpath script folders and modules.
 * 
 * @author haiodo
 * 
 */
public class BuiltinProjectFragment extends ProjectFragment {
	public final static ArrayList EMPTY_LIST = new ArrayList();

	protected final IPath fPath;
	
	IBuiltinModuleProvider builtinProvider;
	/**
	 * Maps folder names and source module names.
	 */

	protected BuiltinProjectFragment(IPath path, DLTKProject project) {
		super(null, project);
		this.fPath = path;
		builtinProvider = getBuiltinProvider(project);
	}

	public static IBuiltinModuleProvider getBuiltinProvider(IDLTKProject project) {
		try {
			IBuildpathEntry[] entries = project.getRawBuildpath();
			IPath containerPath = null;
			for (int i = 0; i < entries.length; i++) {
				if (entries[i].getEntryKind() == IBuildpathEntry.BPE_CONTAINER) {
					containerPath = entries[i].getPath();
				}
			}
			if (containerPath == null) {
				return null;
			}
			IBuildpathContainer buildpathContainer = ModelManager.getModelManager().getBuildpathContainer(containerPath,
					project);
			if( buildpathContainer == null ) {
				return null;
			}
			IBuiltinModuleProvider builtinProvider = buildpathContainer.getBuiltinProvider();
			return builtinProvider;
		} catch (CoreException ex) {
			if (DLTKCore.DEBUG) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public static boolean isSupported(IDLTKProject project) {
		IBuiltinModuleProvider prov = getBuiltinProvider(project);
		return prov != null && prov.getBuiltinModules() != null;
	}

	/**
	 * Compute the package fragment children of this package fragment root.
	 */
	protected boolean computeChildren(OpenableElementInfo info, Map newElements)
			throws ModelException {
		ArrayList vChildren = new ArrayList(5);
		ArrayList vForeign = new ArrayList(5);
		char[][] inclusionPatterns = fullInclusionPatternChars();
		char[][] exclusionPatterns = fullExclusionPatternChars();
		computeFolderChildren(this.fPath, !Util.isExcluded(this.fPath,
				inclusionPatterns, exclusionPatterns, true), vChildren,
				vForeign, newElements, inclusionPatterns, exclusionPatterns);
		IModelElement[] children = new IModelElement[vChildren.size()];
		vChildren.toArray(children);
		info.setChildren(children);
		return true;
	}

	/**
	 * Starting at this folder, create folders and add them to the collection of
	 * children.
	 * 
	 * @param newElements
	 * 
	 * @exception ModelException
	 *                The resource associated with this project fragment does
	 *                not exist
	 */
	protected void computeFolderChildren(IPath path, boolean isIncluded,
			ArrayList vChildren, ArrayList vForeign, Map newElements,
			char[][] inclusionPatterns, char[][] exclusionPatterns)
			throws ModelException {
		BuiltinScriptFolder fldr = (BuiltinScriptFolder)getScriptFolder(new Path(""));
		vChildren.add(fldr);
		if( this.builtinProvider == null ) {
			return;
		}
		try {
			BuiltinScriptFolderInfo fragInfo = new BuiltinScriptFolderInfo();
			fldr.computeChildren(fragInfo, this.builtinProvider.getBuiltinModules() );
			fldr.computeForeignResources(fragInfo);
			newElements.put(fldr, fragInfo);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e, IModelStatusConstants.ELEMENT_DOES_NOT_EXIST);
			/*
			 * could be thrown by ElementTree when path is not found
			 */
		}
	}

	public IScriptFolder getScriptFolder(IPath path) {
		try {
			List childs = getChildrenOfType(SCRIPT_FOLDER);
			for (int i = 0; i < childs.size(); ++i) {
				IScriptFolder folder = (IScriptFolder) childs.get(i);
				if (folder.getElementName().equals(path.toPortableString())) {
					return folder;
				}
			}
		} catch (ModelException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		return new BuiltinScriptFolder(this, path);
	}

	public IScriptFolder getScriptFolder(String path) {
		return getScriptFolder(new Path(path));
	}

	public boolean isReadOnly() {
		return true;
	}

	protected Object createElementInfo() {
		return new BuiltinProjectFragmentInfo();
	}

	public boolean isArchive() {
		return false;
	}

	public boolean isExternal() {
		return true;
	}

	public IResource getUnderlyingResource() throws ModelException {
		return null;
	}

	public int hashCode() {
		return this.fPath.hashCode();
	}

	public IPath getPath() {
		if (isExternal()) {
			return this.fPath;
		} else {
			return super.getPath();
		}
	}

	public IResource getResource() {
		return null;
	}

	/**
	 * Returns whether the corresponding resource or associated file exists
	 */
	protected boolean resourceExists() {
		return true;
	}

	protected void toStringAncestors(StringBuffer buffer) {
		if (isExternal())
			return;
		super.toStringAncestors(buffer);
	}

	public int getKind() {
		return IProjectFragment.K_SOURCE;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o instanceof BuiltinProjectFragment) {
			BuiltinProjectFragment other = (BuiltinProjectFragment) o;
			return this.fPath.equals(other.fPath);
		}
		return false;
	}

	public String getElementName() {
		return fPath.toOSString().replace(File.separatorChar,
				JEM_SKIP_DELIMETER);
	}

	public IModelElement getHandleFromMemento(String token,
			MementoTokenizer memento, WorkingCopyOwner owner) {
		switch (token.charAt(0)) {
		case JEM_SCRIPTFOLDER:
			String pkgName;
			if (memento.hasMoreTokens()) {
				pkgName = memento.nextToken();
				char firstChar = pkgName.charAt(0);
				if (firstChar == JEM_SOURCEMODULE || firstChar == JEM_COUNT) {
					token = pkgName;
					pkgName = IProjectFragment.DEFAULT_SCRIPT_FOLDER_NAME;
				} else {
					token = null;
				}
			} else {
				pkgName = IScriptFolder.DEFAULT_FOLDER_NAME;
				token = null;
			}
			ModelElement pkg = (ModelElement) getScriptFolder(pkgName);
			if (token == null) {
				return pkg.getHandleFromMemento(memento, owner);
			} else {
				return pkg.getHandleFromMemento(token, memento, owner);
			}
		}
		return null;
	}

	protected char getHandleMementoDelimiter() {
		return JEM_PROJECTFRAGMENT;
	}
	/*
	 * @see IProjectFragment
	 */
	public IBuildpathEntry getRawBuildpathEntry() throws ModelException {
		IBuildpathEntry rawEntry = null;
		DLTKProject project = (DLTKProject) this.getScriptProject();
		project.getResolvedBuildpath(true/*ignoreUnresolvedEntry*/, false/*don't generateMarkerOnError*/, false/*don't returnResolutionInProgress*/); // force the reverse rawEntry cache to be populated
										// be populated
		Map resolvedPathToRawEntries = project.getPerProjectInfo().resolvedPathToRawEntries;
		if (resolvedPathToRawEntries != null) {
			rawEntry = (IBuildpathEntry) resolvedPathToRawEntries.get(new Path( this.getPath().segment(0) ));
			//try to guest map from internal element.
			if( rawEntry != null && rawEntry.getEntryKind() == IBuildpathEntry.BPE_CONTAINER ) {
				IBuildpathContainer container = DLTKCore.getBuildpathContainer(rawEntry.getPath(), project);
				IBuildpathEntry entrys[] = container.getBuildpathEntries();
				for( int i = 0; i < entrys.length; ++i ) {
					if( entrys[i].getPath().equals(new Path( this.getPath().segment(0)))) {
						return entrys[i];
					}
				}
			}
		}
		return rawEntry;
	}
	/*
	 * Validate whether this project fragment is on the buildpath of its
	 * project.
	 */
	protected IStatus validateOnBuildpath() {
		return Status.OK_STATUS;		
	}
	public boolean exists() {
		return true;
	}
}
