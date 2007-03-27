package org.eclipse.dltk.internal.core.mixin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.builder.IScriptBuilder;
import org.eclipse.dltk.core.mixin.IMixinParser;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.TypeNameRequestor;
import org.eclipse.dltk.core.search.index.Index;
import org.eclipse.dltk.core.search.indexing.IndexManager;
import org.eclipse.dltk.core.search.indexing.InternalSearchDocument;
import org.eclipse.dltk.internal.core.ExternalProjectFragment;
import org.eclipse.dltk.internal.core.ExternalSourceModule;
import org.eclipse.dltk.internal.core.ModelManager;
import org.eclipse.dltk.internal.core.search.DLTKSearchDocument;

public class MixinBuilder implements IScriptBuilder {
	public IStatus buildResources(IDLTKProject project, List resources, IProgressMonitor monitor) {
		return null;
	}

	public List getDependencies(IDLTKProject project, List resources) {
		return null;
	}

	public IStatus buildModelElements(IDLTKProject project, List elements,
			IProgressMonitor monitor) {
		IndexManager manager = ModelManager.getModelManager().getIndexManager();
		
		IDLTKLanguageToolkit toolkit = null;
		IMixinParser parser = null;
		try {
			toolkit = DLTKLanguageManager.getLanguageToolkit(project);
			parser = MixinManager.getMixinParser(toolkit.getNatureID());
		} catch (CoreException e1) {
			e1.printStackTrace();
		}
		
		if( parser == null || toolkit == null ) {
			return null;
		}
		try {
			waitUntilIndexReady(toolkit);
			IPath fullPath = project.getProject().getFullPath();
			
			Map indexes = new HashMap();
			
			Index mixinIndex = manager.getSpecialIndex("mixin",  /*project.getProject()*/ fullPath.toString(), fullPath.toOSString() );
			for (int i = 0; i < elements.size(); ++i) {
				Index currentIndex = mixinIndex;
				monitor.worked(1);
				ISourceModule element = (ISourceModule) elements.get(i);
				
				IProjectFragment projectFragment = (IProjectFragment)element.getAncestor(IModelElement.PROJECT_FRAGMENT);
				IPath containerPath = project.getPath();
				if( projectFragment instanceof ExternalProjectFragment ) {
					IPath path = projectFragment.getPath();
					if( indexes.containsKey(path)) {
						currentIndex = (Index)indexes.get(path);
						containerPath = path;
					}
					else {
						Index index = manager.getSpecialIndex("mixin", path.toString(), path.toOSString() );
						if( index != null ) {
							currentIndex = index;
							indexes.put(path, index);
							containerPath = path;
						}
					}
				}
				
				char[] source = element.getSourceAsCharArray();
				SearchParticipant participant = SearchEngine
						.getDefaultSearchParticipant();
				
				DLTKSearchDocument document;
					document = new DLTKSearchDocument(element
						.getPath().toOSString(), containerPath, source, participant, element instanceof ExternalSourceModule );
//				System.out.println("mixin indexing:" + document.getPath());
				((InternalSearchDocument) document).toolkit = toolkit;
				String containerRelativePath = null;
				
				if (element instanceof ExternalSourceModule) {
					containerRelativePath= (element.getPath().removeFirstSegments(containerPath.segmentCount()).setDevice(null)
									.toString());
				} else {
					containerRelativePath = (element.getPath()
									.removeFirstSegments(1).toOSString());
				}
				((InternalSearchDocument) document)
				.setContainerRelativePath(containerRelativePath);
				currentIndex.remove(containerRelativePath);
				((InternalSearchDocument) document).setIndex(currentIndex);
				new MixinIndexer(document, source).indexDocument();
			}
			if (mixinIndex != null)
				manager.saveIndex(mixinIndex);
			Iterator iterator = indexes.values().iterator();
			while(iterator.hasNext()) {
				Index index = (Index)iterator.next();
				manager.saveIndex(index);
			}
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private void waitUntilIndexReady(IDLTKLanguageToolkit toolkit) {
		// dummy query for waiting until the indexes are ready
		SearchEngine engine = new SearchEngine();
		IDLTKSearchScope scope = SearchEngine.createWorkspaceScope(toolkit);
		try {
			engine.searchAllTypeNames(null, "!@$#!@".toCharArray(),
					SearchPattern.R_PATTERN_MATCH
							| SearchPattern.R_CASE_SENSITIVE,
					IDLTKSearchConstants.TYPE, scope, new TypeNameRequestor() {
						public void acceptType(int modifiers,
								char[] packageName, char[] simpleTypeName,
								char[][] enclosingTypeNames, String path) {
						}
					}, IDLTKSearchConstants.WAIT_UNTIL_READY_TO_SEARCH, null);
		} catch (CoreException e) {
		}

	}
	private static MixinBuilder builder = new MixinBuilder();
	public static MixinBuilder getDefault() {
		return builder;
	}
}
