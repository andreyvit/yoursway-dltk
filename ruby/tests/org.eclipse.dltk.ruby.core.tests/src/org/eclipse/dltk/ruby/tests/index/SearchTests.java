package org.eclipse.dltk.ruby.tests.index;

import java.io.IOException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.compiler.util.SimpleSet;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.search.BasicSearchEngine;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.index.EntryResult;
import org.eclipse.dltk.core.search.index.Index;
import org.eclipse.dltk.core.search.indexing.IIndexConstants;
import org.eclipse.dltk.core.search.indexing.IndexManager;
import org.eclipse.dltk.core.search.indexing.ReadWriteMonitor;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.core.tests.model.AbstractDLTKSearchTests;
import org.eclipse.dltk.internal.core.ModelManager;
import org.eclipse.dltk.ruby.tests.Activator;


public class SearchTests extends AbstractDLTKSearchTests implements IDLTKSearchConstants {
	private static final String TCLSEARCH = "index";

	public SearchTests(String name) {
		super(Activator.PLUGIN_ID, name);
	}

	public static Suite suite() {
		return new Suite(SearchTests.class);
	}

	public void setUpSuite() throws Exception {
		super.setUpSuite();
		up();
	}

	public void tearDownSuite() throws Exception {
		deleteProject(TCLSEARCH);
		super.tearDownSuite();
	}
	
	private void up() throws Exception {
		if (SCRIPT_PROJECT == null) {
			SCRIPT_PROJECT = setUpScriptProject(TCLSEARCH);
		}
	}

	/**
	 * Simple type declaration test.
	 */
	public void testNamespaceDeclaration01() throws Exception { 
		up();
		ISourceModule sm = getSourceModule(TCLSEARCH, "src", "foo.rb");
		IDLTKProject project = (IDLTKProject) sm.getAncestor(IModelElement.SCRIPT_PROJECT);
		IDLTKSearchScope scope = BasicSearchEngine.createSearchScope(new IModelElement[] {project}, true);
		IPath[] projectsAndArchives = scope.enclosingProjectsAndZips();
		IndexManager manager = ModelManager.getModelManager().getIndexManager();
		SimpleSet locations = new SimpleSet();
		for (int i = 0; i < projectsAndArchives.length; i++)
			locations.add(manager.computeIndexLocation(projectsAndArchives[i]));
		IPath[] indexLocations = new IPath[locations.elementSize];
		Object[] values = locations.values;
		int count = 0;
		for (int i = values.length; --i >= 0;)
			if (values[i] != null)
				indexLocations[count++] = new Path((String) values[i]);
		for (int i = 0; i < projectsAndArchives.length; i++) {
			IPath location = projectsAndArchives[i];
			Index index = manager.getIndex(location, true, true);
			ReadWriteMonitor monitor = index.monitor;
			if (monitor == null)
				System.out.println("FUCK");
			try {
				monitor.enterRead();
				index.startQuery();
				try {
					EntryResult[] results = index.query(new char[][]{ IIndexConstants.TYPE_DECL}, "Foo*".toCharArray(), SearchPattern.R_PATTERN_MATCH);
					for (int j = 0; j < results.length; j++) {
						EntryResult result = results[j];
						System.out.println("Result " + j + ": " + new String(result.getWord()));
					}
				} finally {
					index.stopQuery();
				}
			} finally {
				monitor.exitRead(); // finished reading
			}
		}
	}

}
