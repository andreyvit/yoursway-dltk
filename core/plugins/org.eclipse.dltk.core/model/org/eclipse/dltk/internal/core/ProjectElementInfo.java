/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.util.HashtableOfArrayToObject;
import org.eclipse.dltk.internal.core.util.Util;



class ProjectElementInfo extends OpenableElementInfo {

	static class ProjectCache {
		ProjectCache(IProjectFragment[] allProjectFragmentCache, HashtableOfArrayToObject allPkgFragmentsCache, HashtableOfArrayToObject isPackageCache, Map rootToResolvedEntries) {
			this.allProjectFragmentCache = allProjectFragmentCache;
			this.allPkgFragmentsCache = allPkgFragmentsCache;
			this.rootToResolvedEntries = rootToResolvedEntries;
			this.isPackageCache = isPackageCache;
		}
		
		/*
		 * A cache of all project fragments of this project.
		 */
		public IProjectFragment[] allProjectFragmentCache;
		
		/*
		 * A cache of all package fragments in this project. (a map from
		 * String[] (the package name) to IProjectFragment[] (the package
		 * fragment roots that contain a package fragment with this name)
		 */
		public HashtableOfArrayToObject allPkgFragmentsCache;
		
		/*
		 * A set of package names (String[]) that are known to be packages.
		 */
		public HashtableOfArrayToObject isPackageCache;
	
		public Map rootToResolvedEntries;		
	}
	
	ProjectCache projectCache;
	
	/**
	 * A array with all the non-script resources contained by this Project
	 * fragment
	 */
	private Object[] foreignResources;	
	
	/*
	 * Reset the package fragment roots and package fragment caches
	 */
	void resetCaches() {
		this.projectCache = null;
	}
	
	void setForeignResources(Object[] resources) {

		this.foreignResources = resources;
	}

	public Object[] getForeignResources(ScriptProject project) {		
			if (this.foreignResources == null) {
				this.foreignResources = computeForeignResources(project);
			}
			return this.foreignResources;
		}

	private Object[] computeForeignResources(ScriptProject project) {
		
		// determine if src == project and/or if bin == project
		IPath projectPath = project.getProject().getFullPath();
		boolean srcIsProject = false;
		char[][] inclusionPatterns = null;
		char[][] exclusionPatterns = null;
		IBuildpathEntry[] buildpath = null;		
		try {
			buildpath = project.getResolvedBuildpath();
			for (int i = 0; i < buildpath.length; i++) {
				IBuildpathEntry entry = buildpath[i];
				if (projectPath.equals(entry.getPath())) {
					srcIsProject = true;
					inclusionPatterns = ((BuildpathEntry)entry).fullInclusionPatternChars();
					exclusionPatterns = ((BuildpathEntry)entry).fullExclusionPatternChars();
					break;
				}
			}			
		} catch (ModelException e) {
			// ignore
		}

		Object[] resources = new IResource[5];
		int resourcesCounter = 0;
		try {
			IResource[] members = ((IContainer) project.getResource()).members();
			for (int i = 0, max = members.length; i < max; i++) {
				IResource res = members[i];
				switch (res.getType()) {
					case IResource.FILE :
						IPath resFullPath = res.getFullPath();
						String resName = res.getName();
						
						// ignore a archive file on the buildpath
						if (org.eclipse.dltk.compiler.util.Util.isArchiveFileName(resName) && this.isBuildpathEntry(resFullPath, buildpath)) {
							break;
						}
						// ignore source file if src == project
						if (srcIsProject 
							&& Util.isValidSourceModule(project, res)
							&& !Util.isExcluded(res, inclusionPatterns, exclusionPatterns)) {
							break;
						}
						//TODO: Add language dependent filters here.
						// else add nonscriptresource
						if (resources.length == resourcesCounter) {
							// resize
							System.arraycopy(
								resources,
								0,
								(resources = new IResource[resourcesCounter * 2]),
								0,
								resourcesCounter);
						}
						resources[resourcesCounter++] = res;
						break;
					case IResource.FOLDER :
						resFullPath = res.getFullPath();
						
						// ignore non-excluded folders on the buildpath or that correspond to an output location
						if ((srcIsProject && !Util.isExcluded(res, inclusionPatterns, exclusionPatterns) && Util.isValidFolderNameForPackage(res.getName()))
								|| this.isBuildpathEntry(resFullPath, buildpath)) {
							break;
						}
						// else add nonscriptresource
						if (resources.length == resourcesCounter) {
							// resize
							System.arraycopy(
								resources,
								0,
								(resources = new IResource[resourcesCounter * 2]),
								0,
								resourcesCounter);
						}
						resources[resourcesCounter++] = res;
				}
			}
			if (resources.length != resourcesCounter) {
				System.arraycopy(
					resources,
					0,
					(resources = new IResource[resourcesCounter]),
					0,
					resourcesCounter);
			}
		} catch (CoreException e) {
			resources = NO_NON_SCRIPT_RESOURCES;
			resourcesCounter = 0;
		}
		return resources;
	}
	/*
	 * Returns whether the given path is a buildpath entry or an output location.
	 */
	private boolean isBuildpathEntry(IPath path, IBuildpathEntry[] resolvedBuildpath) {		
		for (int i = 0, length = resolvedBuildpath.length; i < length; i++) {
			IBuildpathEntry entry = resolvedBuildpath[i];
			if (entry.getPath().equals(path)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Adds the given name and its super names to the given set
	 * (e.g. for {"a", "b", "c"}, adds {"a", "b", "c"}, {"a", "b"}, and {"a"})
	 */
	public static void addNames(String[] name, HashtableOfArrayToObject set) {
		set.put(name, name);
		int length = name.length;
		for (int i = length-1; i > 0; i--) {
			String[] superName = new String[i];
			System.arraycopy(name, 0, superName, 0, i);
			set.put(superName, superName);
		}
	}
	/*
	 * Creates a new name lookup for this project info. 
	 * The given project is assumed to be the handle of this info.
	 * This name lookup first looks in the given working copies.
	 */
	NameLookup newNameLookup(ScriptProject project, ISourceModule[] workingCopies) {
		ProjectCache cache = getProjectCache(project);
		return new NameLookup(cache.allProjectFragmentCache, cache.allPkgFragmentsCache, cache.isPackageCache, workingCopies, cache.rootToResolvedEntries);
	}
	ProjectCache getProjectCache(ScriptProject project) {
		ProjectCache cache = this.projectCache;
		if (cache == null) {
			IProjectFragment[] roots;
			Map reverseMap = new HashMap(3);
			try {
				roots = project.getAllProjectFragments(reverseMap);
			} catch (ModelException e) {
				// project does not exist: cannot happen since this is the info of the project
				roots = new IProjectFragment[0];
				reverseMap.clear();
			}
			HashtableOfArrayToObject fragmentsCache = new HashtableOfArrayToObject();
			HashtableOfArrayToObject isPackageCache = new HashtableOfArrayToObject();
			for (int i = 0, length = roots.length; i < length; i++) {
				IProjectFragment root = roots[i];
				IModelElement[] frags = null;
				try {
					if(DLTKCore.DEBUG) {
						System.err.println("TODO: Require to check for ExternalProjectFragment compatibility.");
					}
					if (root.isArchive() && !root.isOpen()) {
						ArchiveProjectFragmentInfo info = new ArchiveProjectFragmentInfo();
						((ArchiveProjectFragment) root).computeChildren(info, new HashMap());
						frags = info.children;
					} else 
						frags = root.getChildren();
				} catch (ModelException e) {
					// root doesn't exist: ignore
					continue;
				}
				for (int j = 0, length2 = frags.length; j < length2; j++) {
					ScriptFolder fragment= (ScriptFolder) frags[j];
					String[] pkgName = fragment.path.segments();
					Object existing = fragmentsCache.get(pkgName);
					if (existing == null) {
						fragmentsCache.put(pkgName, root);
						// cache whether each package and its including packages (see https://bugs.eclipse.org/bugs/show_bug.cgi?id=119161)
						// are actual packages
						addNames(pkgName, isPackageCache);
					} else {
						if (existing instanceof ProjectFragment) {
							fragmentsCache.put(pkgName, new IProjectFragment[] {(ProjectFragment) existing, root});
						} else {
							IProjectFragment[] entry= (IProjectFragment[]) existing;
							IProjectFragment[] copy= new IProjectFragment[entry.length + 1];
							System.arraycopy(entry, 0, copy, 0, entry.length);
							copy[entry.length]= root;
							fragmentsCache.put(pkgName, copy);
						}
					}
				}
			}
			cache = new ProjectCache(roots, fragmentsCache, isPackageCache, reverseMap);
			this.projectCache = cache;
		}
		return cache;
	}
}
