/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptModel;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceReference;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
 
/**
 * A base content provider for Java elements. It provides access to the
 * Java element hierarchy without listening to changes in the Java model.
 * If updating the presentation on Java model change is required than 
 * clients have to subclass, listen to Java model changes and have to update
 * the UI using corresponding methods provided by the JFace viewers or their 
 * own UI presentation.
 * <p>
 * The following Java element hierarchy is surfaced by this content provider:
 * <p>
 * <pre>
Java model (<code>IJavaModel</code>)
   Java project (<code>IJavaProject</code>)
      package fragment root (<code>IPackageFragmentRoot</code>)
         package fragment (<code>IPackageFragment</code>)
            compilation unit (<code>ICompilationUnit</code>)
            binary class file (<code>IClassFile</code>)
 * </pre>
 * </p> 			
 * <p>
 * Note that when the entire Java project is declared to be package fragment root,
 * the corresponding package fragment root element that normally appears between the
 * Java project and the package fragments is automatically filtered out.
 * </p>
 *
 */
public class StandardModelElementContentProvider implements ITreeContentProvider, IWorkingCopyProvider {

	protected static final Object[] NO_CHILDREN= new Object[0];
	protected boolean fProvideMembers;
	protected boolean fProvideWorkingCopy;
	
	/**
	 * Creates a new content provider. The content provider does not
	 * provide members of compilation units or class files.
	 */	
	public StandardModelElementContentProvider() {
		this(false);
	}
	
	/**
	 * Creates a new <code>StandardJavaElementContentProvider</code>.
	 *
	 * @param provideMembers if <code>true</code> members below compilation units 
	 * and class files are provided. 
	 */
	public StandardModelElementContentProvider(boolean provideMembers) {
		fProvideMembers= provideMembers;
		fProvideWorkingCopy= provideMembers;
	}
	
	/**
	 * Returns whether members are provided when asking
	 * for a compilation units or class file for its children.
	 * 
	 * @return <code>true</code> if the content provider provides members; 
	 * otherwise <code>false</code> is returned
	 */
	public boolean getProvideMembers() {
		return fProvideMembers;
	}

	/**
	 * Sets whether the content provider is supposed to return members
	 * when asking a compilation unit or class file for its children.
	 * 
	 * @param b if <code>true</code> then members are provided. 
	 * If <code>false</code> compilation units and class files are the
	 * leaves provided by this content provider.
	 */
	public void setProvideMembers(boolean b) {
		//hello
		fProvideMembers= b;
	}
	
	/**
	 * @deprecated Since 3.0 compilation unit children are always provided as working copies. The Java model
	 * does not support the 'original' mode anymore. 
	 */
	public boolean getProvideWorkingCopy() {
		return fProvideWorkingCopy;
	}

	/**
	 * @deprecated Since 3.0 compilation unit children are always provided from the working copy. The Java model
	 * offers a unified world and does not support the 'original' mode anymore. 
	 */
	public void setProvideWorkingCopy(boolean b) {
		fProvideWorkingCopy= b;
	}

	/* (non-Javadoc)
	 * @see IWorkingCopyProvider#providesWorkingCopies()
	 */
	public boolean providesWorkingCopies() {
		return getProvideWorkingCopy();
	}

	/* (non-Javadoc)
	 * Method declared on IStructuredContentProvider.
	 */
	public Object[] getElements(Object parent) {
		return getChildren(parent);
	}
	
	/* (non-Javadoc)
	 * Method declared on IContentProvider.
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	/* (non-Javadoc)
	 * Method declared on IContentProvider.
	 */
	public void dispose() {
	}

	/* (non-Javadoc)
	 * Method declared on ITreeContentProvider.
	 */
	public Object[] getChildren(Object element) {
		if (!exists(element))
			return NO_CHILDREN;
			
		try {
			if (element instanceof IScriptModel) 
				return getScriptProjects((IScriptModel)element);
			
			if (element instanceof IScriptProject) 
				return getProjectFragments((IScriptProject)element);
			
			if (element instanceof IProjectFragment) 
				return getProjectFragmentContent((IProjectFragment)element);
			
			if (element instanceof IScriptFolder) 
				return getScriptFolderContent((IScriptFolder)element);
				
			if (element instanceof IFolder)
				return getFolderContent((IFolder)element);
//			
//			if (element instanceof IJarEntryResource) {
//				return ((IJarEntryResource) element).getChildren();
//			}
//			
			if (getProvideMembers() && element instanceof ISourceReference && element instanceof IParent) {
				return ((IParent)element).getChildren();
			}
		} catch (CoreException e) {
			return NO_CHILDREN;
		}		
		return NO_CHILDREN;	
	}

	/* (non-Javadoc)
	 * @see ITreeContentProvider
	 */
	public boolean hasChildren(Object element) {
		if (getProvideMembers()) {
			// assume CUs and class files are never empty
			if (element instanceof ISourceModule ) {
				return true;
			}
		} else {
			// don't allow to drill down into a compilation unit or class file
			if (element instanceof ISourceModule ||
				element instanceof IFile)
			return false;
		}
			
		if (element instanceof IScriptProject) {
			IScriptProject jp= (IScriptProject)element;
			if (!jp.getProject().isOpen()) {
				return false;
			}	
		}
		
		if (element instanceof IParent) {
			try {
				// when we have Java children return true, else we fetch all the children
				if (((IParent)element).hasChildren())
					return true;
			} catch(ModelException e) {
				return true;
			}
		}
		Object[] children= getChildren(element);
		return (children != null) && children.length > 0;
	}
	 
	/* (non-Javadoc)
	 * Method declared on ITreeContentProvider.
	 */
	public Object getParent(Object element) {
		if (!exists(element))
			return null;
		return internalGetParent(element);			
	}
	
	/**
	 * Evaluates all children of a given {@link IPackageFragmentRoot}. Clients can override this method.
	 * @param root The root to evaluate the children for.
	 * @return The children of the root
	 * @exception JavaModelException if the package fragment root does not exist or if an
	 *      exception occurs while accessing its corresponding resource
	 *      
	 */
	protected Object[] getProjectFragmentContent(IProjectFragment root) throws ModelException {
		IModelElement[] fragments= root.getChildren();
		
		List newFragments = new ArrayList();
		for (int i = 0; i < fragments.length; ++i) {
			if (fragments[i] instanceof IScriptFolder) {
				IScriptFolder scriptFolder = ((IScriptFolder) fragments[i]);
				if (scriptFolder.isRootFolder()) {
					IModelElement[] children = scriptFolder.getChildren();
					for (int j = 0; j < children.length; ++j) {
						newFragments.add(children[j]);
					}
					continue;
				}
			}
			newFragments.add(fragments[i]);
		}
		fragments = (IModelElement[]) newFragments.toArray(new IModelElement[newFragments.size()]);
		
		if (isProjectProjectFragment(root)) {
			return fragments;
		}
		Object[] nonJavaResources= root.getForeignResources();
		if (nonJavaResources == null)
			return fragments;
		return concatenate(fragments, nonJavaResources);
	}
	
	/**
	 * Evaluates all children of a given {@link IJavaProject}. Clients can override this method.
	 * @param project The Java project to evaluate the children for.
	 * @return The children of the project. Typically these are package fragment roots but can also be other elements.
	 * @exception JavaModelException if the Java project does not exist or if an
	 *      exception occurs while accessing its corresponding resource
	 */
	protected Object[] getProjectFragments(IScriptProject project) throws ModelException {
		if (!project.getProject().isOpen())
			return NO_CHILDREN;
			
		IProjectFragment[] roots= project.getProjectFragments();
		List list= new ArrayList(roots.length);
		// filter out package fragments that correspond to projects and
		// replace them with the package fragments directly
		for (int i= 0; i < roots.length; i++) {
			IProjectFragment root= roots[i];
			if (isProjectProjectFragment(root)) {
				Object[] fragments= getProjectFragmentContent(root);
				for (int j= 0; j < fragments.length; j++) {
					list.add(fragments[j]);
				}
			} else {
				list.add(root);
			} 
		}
		Object[] resources= project.getForeignResources();
		for (int i= 0; i < resources.length; i++) {
			list.add(resources[i]);
		}
		return list.toArray();
	}

	/**
	 * Note: This method is for internal use only. Clients should not call this method.
	 */
	protected Object[] getScriptProjects(IScriptModel jm) throws ModelException {
		return jm.getScriptProjects();
	}
	
	/**
	 * Evaluates all children of a given {@link IPackageFragment}. Clients can override this method.
	 * @param fragment The fragment to evaluate the children for.
	 * @return The children of the given package fragment.
	 * @exception JavaModelException if the package fragment does not exist or if an
	 *      exception occurs while accessing its corresponding resource
	 *      
	 * @since 3.3
	 */
	protected Object[] getScriptFolderContent(IScriptFolder fragment) throws ModelException {
//		if (fragment.getKind() == IProjectFragment.K_SOURCE) {
			return concatenate(fragment.getSourceModules(), fragment.getForeignResources());
//		}
//		return concatenate(fragment.getClassFiles(), fragment.getForeignResources());
	}
	
	/**
	 * Evaluates all children of a given {@link IFolder}. Clients can override this method.
	 * @param folder The folder to evaluate the children for.
	 * @return The children of the given package fragment.
	 * @exception CoreException if the folder does not exist.
	 *      
	 */
	protected Object[] getFolderContent(IFolder folder) throws CoreException {
		IResource[] members= folder.members();
		IScriptProject javaProject= DLTKCore.create(folder.getProject());
		if (javaProject == null || !javaProject.exists())
			return members;
		boolean isFolderOnClasspath = javaProject.isOnBuildpath(folder);
		List nonJavaResources= new ArrayList();
		// Can be on classpath but as a member of non-java resource folder
		for (int i= 0; i < members.length; i++) {
			IResource member= members[i];
			// A resource can also be a java element
			// in the case of exclusion and inclusion filters.
			// We therefore exclude Java elements from the list
			// of non-Java resources.
			if (isFolderOnClasspath) {
				if (javaProject.findProjectFragment(member.getFullPath()) == null) {
					nonJavaResources.add(member);
				} 
			} else if (!javaProject.isOnBuildpath(member)) {
				nonJavaResources.add(member);
			}
		}
		return nonJavaResources.toArray();
	}
	
	/**
	 * Note: This method is for internal use only. Clients should not call this method.
	 */
	protected boolean isBuildPathChange(IModelElementDelta delta) {
		
		// need to test the flags only for package fragment roots
		if (delta.getElement().getElementType() != IModelElement.PROJECT_FRAGMENT)
			return false;
		
		int flags= delta.getFlags();
		return (delta.getKind() == IModelElementDelta.CHANGED && 
			((flags & IModelElementDelta.F_ADDED_TO_BUILDPATH) != 0) ||
			 ((flags & IModelElementDelta.F_REMOVED_FROM_BUILDPATH) != 0) ||
			 ((flags & IModelElementDelta.F_REORDER) != 0));
	}
	
	/**
	 * Note: This method is for internal use only. Clients should not call this method.
	 */
	protected Object skipProjectProjectFragment(IProjectFragment root) {
		if (isProjectProjectFragment(root))
			return root.getParent(); 
		return root;
	}
	
	/**
	 * Note: This method is for internal use only. Clients should not call this method.
	 */
	protected boolean isScriptFolderEmpty(IModelElement element) throws ModelException {
		if (element instanceof IScriptFolder) {
			IScriptFolder fragment= (IScriptFolder)element;
			if (fragment.exists() && !(fragment.hasChildren() || fragment.getForeignResources().length > 0) && fragment.hasSubfolders()) 
				return true;
		}
		return false;
	}

	/**
	 * Note: This method is for internal use only. Clients should not call this method.
	 */
	protected boolean isProjectProjectFragment(IProjectFragment root) {
		IScriptProject javaProject= root.getScriptProject();
		return javaProject != null && javaProject.getPath().equals(root.getPath());
	}
	
	/**
	 * Note: This method is for internal use only. Clients should not call this method.
	 */
	protected boolean exists(Object element) {
		if (element == null) {
			return false;
		}
		if (element instanceof IResource) {
			return ((IResource)element).exists();
		}
		if (element instanceof IModelElement) {
			return ((IModelElement)element).exists();
		}
		return true;
	}
	
	/**
	 * Note: This method is for internal use only. Clients should not call this method.
	 */
	protected Object internalGetParent(Object element) {

		// try to map resources to the containing package fragment
		if (element instanceof IResource) {
			IResource parent= ((IResource)element).getParent();
			IModelElement jParent= DLTKCore.create(parent);
			// http://bugs.eclipse.org/bugs/show_bug.cgi?id=31374
			if (jParent != null && jParent.exists()) 
				return jParent;
			return parent;
		} else if (element instanceof IModelElement) {
			IModelElement parent= ((IModelElement) element).getParent();
			// for package fragments that are contained in a project package fragment
			// we have to skip the package fragment root as the parent.
			if (element instanceof IScriptFolder) {
				return skipProjectProjectFragment((IProjectFragment) parent);
			}
			return parent;
		} /*else if (element instanceof IJarEntryResource) {
			return ((IJarEntryResource) element).getParent();
		}*/
		return null;
	}
		
	/**
	 * Note: This method is for internal use only. Clients should not call this method.
	 */
	protected static Object[] concatenate(Object[] a1, Object[] a2) {
		int a1Len= a1.length;
		int a2Len= a2.length;
		if (a1Len == 0) return a2;
		if (a2Len == 0) return a1;
		Object[] res= new Object[a1Len + a2Len];
		System.arraycopy(a1, 0, res, 0, a1Len);
		System.arraycopy(a2, 0, res, a1Len, a2Len); 
		return res;
	}


}
