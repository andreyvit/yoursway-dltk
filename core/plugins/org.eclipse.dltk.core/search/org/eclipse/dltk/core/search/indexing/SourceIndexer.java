/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.core.search.indexing;

import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceElementParser;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchDocument;
import org.eclipse.dltk.internal.core.ModelManager;


/**
 * A SourceIndexer indexes script files using a script parser. The following items
 * are indexed: Declarations of: - Classes<br> - Interfaces; <br> - Methods;<br> -
 * Fields;<br>
 * References to: - Methods (with number of arguments); <br> - Fields;<br> -
 * Types;<br> - Constructors.
 */
public class SourceIndexer extends AbstractIndexer {
	
	static long maxWorkTime = 0;
	
	public SourceIndexer(SearchDocument document) {
		super(document);
	}

	public void indexDocument() {
						
		long started  = (new Date ()).getTime();
		
		// Create a new Parser
		SourceIndexerRequestor requestor = ((InternalSearchDocument) this.document).requestor;
		String documentPath = this.document.getPath();
		IPath path = new Path(documentPath);
		ISourceElementParser parser = ((InternalSearchDocument) this.document).parser;
		if (!this.document.isExternal()) {
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(path.segment(0));
			IDLTKProject dltkProject = DLTKCore.create(project);
			
			if( requestor == null ) {
				requestor = ModelManager.getModelManager().indexManager.getSourceRequestor(dltkProject);
			}
			requestor.setIndexer(this);
			
			if (parser == null) {
				parser = ModelManager.getModelManager().indexManager.getSourceElementParser(dltkProject, requestor);
			} else {
				parser.setRequirestor(requestor);
			}
			String pkgName = "";
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			ISourceModule sourceModule = null;
			if (file.exists()) {
				ISourceModule module = (ISourceModule) DLTKCore.create(file);
				if (module != null) {
					sourceModule = module;
					IScriptFolder folder = (IScriptFolder) module.getParent();
					pkgName = folder.getElementName();
				}
			}
			requestor.setPackageName(pkgName);
			// Launch the parser
			char[] source = null;
			char[] name = null;
			try {
				source = document.getCharContents();
				name = documentPath.toCharArray();
			} catch (Exception e) {
				// ignore
			}
			if (source == null || name == null)
				return; // could not retrieve document info (e.g. resource was
						// discarded)
			parser.parseSourceModule(source, null);
			
			// build mixin index entries
			buildMixin(source);
			
		} else { // This is for external documents				
			if (parser == null || requestor == null ) {
				//parser = ModelManager.getModelManager().indexManager.getSourceElementParser(dltkProject, requestor);
				if (DLTKCore.DEBUG) {
					System.err.println("TODO: Add getSourceElementParser here.");
				}
				return;
			} else {
				parser.setRequirestor(requestor);
			}
			requestor.setIndexer(this);
			String ppath = path.toString();
			if (DLTKCore.DEBUG) {
				System.err.println("TODO: Correct me please...");
			}
			String pkgName = (new Path( ppath.substring(ppath.indexOf(IDLTKSearchScope.FILE_ENTRY_SEPARATOR)+1) ).removeLastSegments(1)).toString();
			requestor.setPackageName(pkgName);
			// Launch the parser
			char[] source = null;
			char[] name = null;
			try {
				source = document.getCharContents();
				name = documentPath.toCharArray();
			} catch (Exception e) {
				// ignore
			}
			if (source == null || name == null)
				return; // could not retrieve document info (e.g. resource was
						// discarded)
			parser.parseSourceModule(source, null);
			
			long ended  = (new Date ()).getTime();
			
			if (ended - started > maxWorkTime) {
				maxWorkTime = ended - started;
				if( DLTKCore.VERBOSE ) {
					System.err.println("Max indexDocument() work time " + maxWorkTime + " on " + document.getPath());
				}
			}
			// build mixin index entries
			buildMixin(source);	
		}
	}

	// Building if mixin simple entries.
	private void buildMixin(char[] source) {
		
	}
}
