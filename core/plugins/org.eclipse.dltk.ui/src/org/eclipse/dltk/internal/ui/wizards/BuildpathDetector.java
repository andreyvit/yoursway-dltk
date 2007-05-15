/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.wizards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.internal.core.BuildpathEntry;

import com.ibm.icu.text.Collator;

/**
 */
public class BuildpathDetector implements IResourceProxyVisitor {
	private HashMap fSourceFolders;
	private List fClassFiles;
	private HashSet fJARFiles;
	private IProject fProject;
	private IBuildpathEntry[] fResultBuildpath;
	private IProgressMonitor fMonitor;
	private static class CPSorter implements Comparator {
		private Collator fCollator = Collator.getInstance();

		public int compare(Object o1, Object o2) {
			IBuildpathEntry e1 = (IBuildpathEntry) o1;
			IBuildpathEntry e2 = (IBuildpathEntry) o2;
			return fCollator.compare(e1.getPath().toString(), e2.getPath().toString());
		}
	}

	public BuildpathDetector(IProject project, IProgressMonitor monitor) throws CoreException {
		fSourceFolders = new HashMap();
		fJARFiles = new HashSet(10);
		fClassFiles = new ArrayList(100);
		fProject = project;
		fResultBuildpath = null;
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
		detectBuildpath(monitor);
	}

	private boolean isNested(IPath path, Iterator iter) {
		while (iter.hasNext()) {
			IPath other = (IPath) iter.next();
			if (other.isPrefixOf(path)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method detectBuildpath.
	 * 
	 * @param monitor
	 *            The progress monitor (not null)
	 * @throws CoreException
	 */
	private void detectBuildpath(IProgressMonitor monitor) throws CoreException {
		try {
			monitor.beginTask(NewWizardMessages.ClassPathDetector_operation_description, 4);
			fMonitor = monitor;
			fProject.accept(this, IResource.NONE);
			monitor.worked(1);
			ArrayList cpEntries = new ArrayList();
			detectSourceFolders(cpEntries);
			if (monitor.isCanceled()) {
				throw new OperationCanceledException();
			}
			monitor.worked(1);
			// IPath outputLocation= detectOutputFolder(cpEntries);
			if (monitor.isCanceled()) {
				throw new OperationCanceledException();
			}
			monitor.worked(1);
			detectLibraries(cpEntries);
			if (monitor.isCanceled()) {
				throw new OperationCanceledException();
			}
			monitor.worked(1);
			if (cpEntries.isEmpty() && fClassFiles.isEmpty()) {
				return;
			}
			// IBuildpathEntry[] InterpreterEnvironmentEntries=
			// PreferenceConstants.getDefaultInterpreterEnvironmentLibrary();
			// for (int i= 0; i < InterpreterEnvironmentEntries.length; i++) {
			// cpEntries.add(InterpreterEnvironmentEntries[i]);
			// }
			IBuildpathEntry[] entries = (IBuildpathEntry[]) cpEntries.toArray(new IBuildpathEntry[cpEntries.size()]);
			if (!BuildpathEntry.validateBuildpath(DLTKCore.create(fProject), entries).isOK()) {
				return;
			}
			fResultBuildpath = entries;
		} finally {
			monitor.done();
		}
	}

//	private IPath findInSourceFolders(IPath path) {
//		Iterator iter = fSourceFolders.keySet().iterator();
//		while (iter.hasNext()) {
//			Object key = iter.next();
//			List cus = (List) fSourceFolders.get(key);
//			if (cus.contains(path)) {
//				return (IPath) key;
//			}
//		}
//		return null;
//	}

	private void detectLibraries(ArrayList cpEntries) {
		ArrayList res = new ArrayList();
		Set sourceFolderSet = fSourceFolders.keySet();
		for (Iterator iter = fJARFiles.iterator(); iter.hasNext();) {
			IPath path = (IPath) iter.next();
			if (isNested(path, sourceFolderSet.iterator())) {
				continue;
			}
			IBuildpathEntry entry = DLTKCore.newLibraryEntry(path);
			res.add(entry);
		}
		Collections.sort(res, new CPSorter());
		cpEntries.addAll(res);
	}

	private void detectSourceFolders(ArrayList resEntries) {
		ArrayList res = new ArrayList();
		Set sourceFolderSet = fSourceFolders.keySet();
		for (Iterator iter = sourceFolderSet.iterator(); iter.hasNext();) {
			IPath path = (IPath) iter.next();
			ArrayList excluded = new ArrayList();
			for (Iterator inner = sourceFolderSet.iterator(); inner.hasNext();) {
				IPath other = (IPath) inner.next();
				if (!path.equals(other) && path.isPrefixOf(other)) {
					IPath pathToExclude = other.removeFirstSegments(path.segmentCount()).addTrailingSeparator();
					excluded.add(pathToExclude);
				}
			}
			IPath[] excludedPaths = (IPath[]) excluded.toArray(new IPath[excluded.size()]);
			IBuildpathEntry entry = DLTKCore.newSourceEntry(path, excludedPaths);
			res.add(entry);
		}
		Collections.sort(res, new CPSorter());
		resEntries.addAll(res);
	}
//	private void addToMap(HashMap map, IPath folderPath, IPath relPath) {
//		List list = (List) map.get(folderPath);
//		if (list == null) {
//			list = new ArrayList(50);
//			map.put(folderPath, list);
//		}
//		list.add(relPath);
//	}
//
//	private IPath getFolderPath(IPath packPath, IPath relpath) {
//		int remainingSegments = packPath.segmentCount() - relpath.segmentCount();
//		if (remainingSegments >= 0) {
//			IPath common = packPath.removeFirstSegments(remainingSegments);
//			if (common.equals(relpath)) {
//				return packPath.uptoSegment(remainingSegments);
//			}
//		}
//		return null;
//	}

	private boolean hasExtension(String name, String ext) {
		return name.endsWith(ext) && (ext.length() != name.length());
	}

	private boolean isValidCUResource(IResource res) {
		IDLTKLanguageToolkit tk = DLTKLanguageManager.findToolkit(res);
		if( tk != null ) {
			return !tk.validateSourceModule(res).matches(IStatus.ERROR);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IResourceProxyVisitor#visit(org.eclipse.core.resources.IResourceProxy)
	 */
	public boolean visit(IResourceProxy proxy) {
		if (fMonitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		if (proxy.getType() == IResource.FILE) {
			String name = proxy.getName();
			IResource res = proxy.requestResource();
			if (isValidCUResource(res)) {
				if (DLTKCore.DEBUG) {
					System.err.println("Add language dependent detecting of buildpaths here...");
				}
				//visitCompilationUnit((IFile) proxy.requestResource());			
			} else if (hasExtension(name, ".zip")) { //$NON-NLS-1$
				fJARFiles.add(proxy.requestFullPath());
			}
			return false;
		}
		return true;
	}

	public IBuildpathEntry[] getBuildpath() {
		return fResultBuildpath;
	}
}
