/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core.search.indexing;

import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.compiler.util.SimpleLookupTable;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IBuiltinModuleProvider;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.ISourceElementParser;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.index.Index;
import org.eclipse.dltk.internal.core.BuiltinProjectFragment;
import org.eclipse.dltk.internal.core.search.DLTKSearchDocument;
import org.eclipse.dltk.internal.core.search.processing.JobManager;

class AddBuiltinFolderToIndex extends IndexRequest {
	IProject project;
	IDLTKProject scriptProject;

	public AddBuiltinFolderToIndex(IPath folderPath, IProject project,
			IndexManager manager) {
		super(folderPath, manager);
		this.project = project;
		scriptProject = DLTKCore.create(this.project);
	}

	public int hashCode() {
		// if (this.containerPath != null)
		// return this.containerPath.hashCode();
		try {
			IDLTKLanguageToolkit languageToolkit = DLTKLanguageManager
					.getLanguageToolkit(scriptProject);
			return languageToolkit.getNatureID().hashCode();
		} catch (CoreException e) {
			return -1;
		}
	}

	public boolean equals(Object o) {
		if (o instanceof AddBuiltinFolderToIndex) {
			try {
				IDLTKLanguageToolkit languageToolkit = DLTKLanguageManager
						.getLanguageToolkit(scriptProject);
				IDLTKLanguageToolkit languageToolki2 = DLTKLanguageManager
						.getLanguageToolkit(((AddBuiltinFolderToIndex) o).scriptProject);
				if (languageToolkit.getNatureID().equals(
						languageToolki2.getNatureID())) {
					return true;
				}
			} catch (CoreException e) {
				return false;
			}
			// if (this.containerPath != null)
			// return this.containerPath.equals(((AddBuiltinFolderToIndex)
			// o).containerPath);
		}
		return false;
	}

//	private static String EXISTS = "OK"; //$NON-NLS-1$
//	private static String DELETED = "DELETED"; //$NON-NLS-1$

	public boolean execute(IProgressMonitor progressMonitor) {
		if (this.isCancelled || progressMonitor != null
				&& progressMonitor.isCanceled())
			return true;
		if (!project.isAccessible())
			return true; // nothing to do

		/* ensure no concurrent write access to index */
//		IPath fullPath = project.getProject().getFullPath();
		String cfp = containerPath.toString();
		if( cfp.startsWith(IBuildpathEntry.BUILTIN_EXTERNAL_ENTRY_STR)) {
			cfp = cfp.substring(IBuildpathEntry.BUILTIN_EXTERNAL_ENTRY_STR.length());
		}
		Index index = this.manager.getSpecialIndex("builtin", cfp, containerPath.toOSString() );
		if (index == null) {
			if (JobManager.VERBOSE)
				org.eclipse.dltk.internal.core.util.Util
						.verbose("-> index could not be created for " + this.containerPath); //$NON-NLS-1$
			return true;
		}
		ReadWriteMonitor monitor = index.monitor;
		if (monitor == null) {
			if (JobManager.VERBOSE)
				org.eclipse.dltk.internal.core.util.Util
						.verbose("-> index for " + this.containerPath + " just got deleted"); //$NON-NLS-1$//$NON-NLS-2$
			return true; // index got deleted since acquired
		}
		try {
			monitor.enterRead(); // ask permission to read
			final IPath container = this.containerPath;
			final IndexManager indexManager = this.manager;
			final ISourceElementParser parser = indexManager
					.getSourceElementParser(scriptProject, null/*
															 * requestor will be
															 * set by indexer
															 */);
			final SourceIndexerRequestor requestor = indexManager
					.getSourceRequestor(scriptProject);
			if (JobManager.VERBOSE)
				org.eclipse.dltk.internal.core.util.Util
						.verbose("-> indexing " + containerPath.toOSString()); //$NON-NLS-1$
			long initialTime = System.currentTimeMillis();
			
			SearchParticipant participant = SearchEngine
				 	.getDefaultSearchParticipant();
			
			visit(null, scriptProject, parser, requestor, indexManager, container,
					true, participant, index);
			this.manager.saveIndex(index);
			if (JobManager.VERBOSE)
				org.eclipse.dltk.internal.core.util.Util
						.verbose("-> done indexing of " //$NON-NLS-1$
								+ containerPath.toOSString()
								+ " (" //$NON-NLS-1$
								+ (System.currentTimeMillis() - initialTime)
								+ "ms)"); //$NON-NLS-1$			
		} catch (IOException ex) {
			if (JobManager.VERBOSE) {
				org.eclipse.dltk.internal.core.util.Util
						.verbose("-> failed to index " + this.containerPath + " because of the following exception:"); //$NON-NLS-1$ //$NON-NLS-2$
				ex.printStackTrace();
			}
//			manager.removeIndex(this.containerPath);
			return false;
		} finally {
			monitor.exitRead(); // free read lock
		}
		return true;
	}

	private void visit(SimpleLookupTable table, IDLTKProject project, ISourceElementParser parser, SourceIndexerRequestor requestor, IndexManager indexManager,
			IPath container, boolean operation, SearchParticipant participant, Index index) {
		
		IDLTKLanguageToolkit toolkit = null;
		try {
			toolkit = DLTKLanguageManager.getLanguageToolkit(project);
		} catch (CoreException e) {
			if( DLTKCore.DEBUG ) {
				e.printStackTrace();
			}
		}
		IBuiltinModuleProvider provider = BuiltinProjectFragment.getBuiltinProvider(project);
		if( provider == null ) {
			return;
		}
		String[] files = provider.getBuiltinModules();
		if (files != null) {
			for (int i = 0; i < files.length; ++i) {
				if (this.isCancelled) {
					if (JobManager.VERBOSE)
						org.eclipse.dltk.internal.core.util.Util.verbose("-> indexing of " + containerPath.toOSString() + " has been cancelled"); //$NON-NLS-1$ //$NON-NLS-2$
					return;
				}
				
				indexDocument(parser, requestor, participant, index, files[i], toolkit, provider.getBuiltinModuleContent(files[i]) );
			}
		}
	}

	private void indexDocument(ISourceElementParser parser,
			SourceIndexerRequestor requestor, SearchParticipant participant,
			Index index, String path, IDLTKLanguageToolkit toolkit, String contents ) {
		IPath dpath = (new Path(path));/*.removeFirstSegments(this.containerPath
				.segmentCount());*/
		dpath = dpath.setDevice(null);
		
		DLTKSearchDocument entryDocument = new DLTKSearchDocument(dpath
				.toOSString(), new Path( "" ), contents.toCharArray(), participant, true);
		entryDocument.parser = parser;
		entryDocument.requestor = requestor;
		entryDocument.toolkit = toolkit;
		this.manager.indexDocument(entryDocument, participant, index,
				this.containerPath);
	}

	public String toString() {
		return "adding " + this.containerPath + " to index " + this.containerPath; //$NON-NLS-1$ //$NON-NLS-2$
	}
}
