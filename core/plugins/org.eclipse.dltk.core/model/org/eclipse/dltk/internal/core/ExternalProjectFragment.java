package org.eclipse.dltk.internal.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelStatusConstants;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.util.Util;


/**
 * Project fragment to external source folder.
 * 
 * @author haiodo
 * 
 */
public class ExternalProjectFragment extends ProjectFragment {
	public final static ArrayList EMPTY_LIST = new ArrayList();
	/**
	 * The path to the zip file (a workspace relative path if the archive is
	 * internal, or an OS path if the archive is external)
	 */
	protected final IPath fPath;
	protected final boolean fReadOnly;
	protected final boolean fOnlyScriptResources;

	protected ExternalProjectFragment(IPath path, DLTKProject project, boolean isReadOnly, boolean onlyScriptResources) {
		super(null, project);
		this.fPath = path;
		this.fReadOnly = isReadOnly;
		this.fOnlyScriptResources = onlyScriptResources;
	}

	/**
	 * Compute the package fragment children of this package fragment root.
	 */
	protected boolean computeChildren(OpenableElementInfo info, Map newElements) throws ModelException {
		ArrayList vChildren = new ArrayList(5);
		ArrayList vForeign = new ArrayList(5);
		char[][] inclusionPatterns = fullInclusionPatternChars();
		char[][] exclusionPatterns = fullExclusionPatternChars();
		computeFolderChildren(this.fPath, 
				!Util.isExcluded(this.fPath, inclusionPatterns, exclusionPatterns, true), vChildren, vForeign, newElements, inclusionPatterns, exclusionPatterns);
		IModelElement[] children = new IModelElement[vChildren.size()];
		vChildren.toArray(children);
		info.setChildren(children);
		return true;
	}

	/**
	 * Starting at this folder, create folders and add them to the collection of
	 * children.
	 * @param newElements 
	 * 
	 * @exception ModelException
	 *                The resource associated with this project fragment does
	 *                not exist
	 */
	protected void computeFolderChildren(IPath path, boolean isIncluded, ArrayList vChildren, ArrayList vForeign, Map newElements, char[][] inclusionPatterns, char[][] exclusionPatterns) throws ModelException {
		IPath lpath = path.removeFirstSegments(this.fPath.segmentCount());
		ExternalScriptFolder fldr = (ExternalScriptFolder)getScriptFolder(lpath);
		boolean valid = Util.isValidSourcePackageName(this, path );
		if( ( lpath.segmentCount() == 0 || valid ) && isIncluded ) {			
			vChildren.add(fldr);
		}
		else {			
			if( this.fOnlyScriptResources ) {
				return;
			}
			if( !valid ) {
				return;
			}
		}		
		List scriptElements = new ArrayList();
		List nonScriptElements = new ArrayList();
		try {
			File file = new File(path.toOSString());
			String[] members = file.list();
			if( members != null ) {
				for (int i = 0, max = members.length; i < max; i++) {
					String member = members[i];
					IPath memberPath = path.append(member);
					File memberFile = new File(memberPath.toOSString());
					if (memberFile.isDirectory()) {
						boolean isMemberIncluded = !Util.isExcluded(memberPath, inclusionPatterns, exclusionPatterns, true);
						computeFolderChildren(memberPath, isMemberIncluded, vChildren, vForeign, newElements, inclusionPatterns, exclusionPatterns );						
					} else {
						if (Util.isValidSourceModule(this, memberPath)) {
							scriptElements.add(memberPath);
						} else {
							if( !this.fOnlyScriptResources || valid ) {
								nonScriptElements.add(memberPath);
							}
						}
					}
				}
			}
			ExternalScriptFolderInfo fragInfo = new ExternalScriptFolderInfo();
			fldr.computeChildren(fragInfo, scriptElements);
			fldr.computeForeignResources(fragInfo, nonScriptElements);
			newElements.put(fldr, fragInfo);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e, IModelStatusConstants.ELEMENT_DOES_NOT_EXIST);
			/*
			 * could be thrown by ElementTree when path is not found
			 */
		} catch (CoreException e) {
			throw new ModelException(e);
		}
	}

	public IScriptFolder getScriptFolder(IPath path) {
			try {
				List childs = getChildrenOfType(SCRIPT_FOLDER);
				for( int i = 0; i < childs.size(); ++i ) {
					IScriptFolder folder = (IScriptFolder)childs.get(i);
					if( folder.getElementName().equals(path.toPortableString())) {
						return folder;
					}
				}
			} catch (ModelException e) {
				if( DLTKCore.DEBUG ) {
					e.printStackTrace();
				}
			}
		return new ExternalScriptFolder(this, path);
	}
	public IScriptFolder getScriptFolder(String path) {
		return getScriptFolder(new Path(path));
	}

	public boolean isReadOnly() {
		return fReadOnly;
	}

	protected Object createElementInfo() {
		return new ExternalProjectFragmentInfo();
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
		if (this.resource == null) {
			this.resource = Model.getTarget(ResourcesPlugin.getWorkspace().getRoot(), this.fPath, false);
		}
		if (this.resource instanceof IResource) {
			return super.getResource();
		} else {
			// external
			return null;
		}
	}

	/**
	 * Returns whether the corresponding resource or associated file exists
	 */
	protected boolean resourceExists() {
		if (this.isExternal()) {
			File file = new File(this.fPath.toOSString());
			return file.exists() && file.isDirectory();
		} else {
			return super.resourceExists();
		}
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
		if (o instanceof ExternalProjectFragment) {
			ExternalProjectFragment other= (ExternalProjectFragment) o;
			return this.fPath.equals(other.fPath);
		}
		return false;
	}
	public String getElementName() {		
		return fPath.toOSString().replace(File.separatorChar, JEM_SKIP_DELIMETER);		
	}
}
