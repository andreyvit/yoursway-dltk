package org.eclipse.dltk.ruby.tests.search.mixin;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.MixinModel;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.indexing.IndexManager;
import org.eclipse.dltk.core.tests.model.AbstractDLTKSearchTests;
import org.eclipse.dltk.internal.compiler.env.AccessRuleSet;
import org.eclipse.dltk.internal.core.ModelManager;
import org.eclipse.dltk.internal.core.Openable;
import org.eclipse.dltk.internal.core.search.IndexQueryRequestor;
import org.eclipse.dltk.internal.core.search.PatternSearchJob;
import org.eclipse.dltk.internal.core.search.matching.MixinPattern;
import org.eclipse.dltk.internal.core.util.HandleFactory;
import org.eclipse.dltk.ruby.core.RubyLanguageToolkit;
import org.eclipse.dltk.ruby.tests.Activator;

/**
 * !!!!! BE Aware Tests Depend on Execution of each over. Because they modify project.
 * @author Haiodo
 *
 */
public class MixinTests extends AbstractDLTKSearchTests implements IDLTKSearchConstants {
	private static final String TCLSEARCH = "mixins";

	public MixinTests(String name) {
		super(Activator.PLUGIN_ID, name);
	}

	public static Suite suite() {
		return new Suite(MixinTests.class);
	}

	public void setUpSuite() throws Exception {
		super.setUpSuite();
		up();
		waitUntilIndexesReady();
		ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.FULL_BUILD, new NullProgressMonitor());
		waitForAutoBuild();
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
	public void testMixins01() throws Exception { 
		
		System.gc();
		long start = Runtime.getRuntime().freeMemory();
		long timeStart = System.currentTimeMillis();
		MixinModel model = new MixinModel(RubyLanguageToolkit.getDefault());
		String[] patterns = model.findKeys("*");
		System.out.println(patterns.length);
//		assertEquals(27, patterns.length);
		
		IMixinElement mixinElement = model.get("Bar");
		assertNotNull(mixinElement);
		Object[] objs = mixinElement.getAllObjects();
		System.gc();
		long end = Runtime.getRuntime().freeMemory();
		long timeEnd = System.currentTimeMillis();
		assertNotNull(objs);
		assertEquals(9, objs.length);
		System.out.println("Memory:" + (end - start) + " time:" + (timeEnd - timeStart));
		
		ISourceModule sourceModule = getSourceModule(TCLSEARCH, "src", "/simple/localvar.rb");
		sourceModule.delete(true, null);
		
		Object objs2[] = mixinElement.getAllObjects();
		assertEquals(8, objs2.length);
		
	}
	/**
	 * Simple type declaration test.
	 */
	public void testMixins02() throws Exception { 
		
		System.gc();
		long start = Runtime.getRuntime().freeMemory();
		long timeStart = System.currentTimeMillis();
		MixinModel model = new MixinModel(RubyLanguageToolkit.getDefault());
		IMixinElement mixinElement = model.get("Bar");
		assertNotNull(mixinElement);
		Object[] objs = mixinElement.getAllObjects();
		int len = objs.length;
		assertNotNull(objs);
		System.gc();
		long end = Runtime.getRuntime().freeMemory();
		long timeEnd = System.currentTimeMillis();
		System.out.println("Memory:" + (end - start) + " time:" + (timeEnd - timeStart));
		
		IScriptFolder scriptFolder = getScriptFolder(TCLSEARCH, "src", new Path( "simple") );
		String contents = "class Bar\n def myFyb\n end\n end\n";
		ISourceModule createSourceModule = scriptFolder.createSourceModule("NewModule.rb", contents, true, null);
		ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.FULL_BUILD, new NullProgressMonitor());
		waitForAutoBuild();
		
		Object objs2[] = mixinElement.getAllObjects();
		assertEquals(len + 1, objs2.length);
		
	}
	
	public void testMixins03() throws Exception { 
			
		final IDLTKSearchScope scope = SearchEngine.createWorkspaceScope(RubyLanguageToolkit.getDefault()); 
		// Index requestor
		final List documentPathFilter = new ArrayList();
		final HandleFactory factory = new HandleFactory();
		final List modules = new ArrayList();
		IndexQueryRequestor searchRequestor = new IndexQueryRequestor(){
			public boolean acceptIndexMatch(String documentPath,
					SearchPattern indexRecord, SearchParticipant participant,
					AccessRuleSet access) {
				IPath fullPath = new Path(documentPath);
				if( documentPathFilter.contains(fullPath)) {
					return true;
				}
				documentPathFilter.add(fullPath);
				Openable createOpenable = factory.createOpenable(documentPath, scope);
				if( createOpenable instanceof ISourceModule ) {
					modules.add(createOpenable);
				}
				
				return true;
			}
		};
		IndexManager indexManager = ModelManager.getModelManager().getIndexManager();
		
		MixinPattern pattern = new MixinPattern("*strange*".toCharArray(), SearchPattern.R_EXACT_MATCH | SearchPattern.R_CASE_SENSITIVE |  SearchPattern.R_PATTERN_MATCH);
		// add type names from indexes
		indexManager.performConcurrentJob(
			new PatternSearchJob(
				pattern, 
				SearchEngine.getDefaultSearchParticipant(), // Script search only
				scope, 
				searchRequestor),
				IDLTKSearchConstants.WAIT_UNTIL_READY_TO_SEARCH,
			null);	
		System.out.println(modules);
		
	}
	
	public void testMixins04() throws Exception { 
		MixinModel model = new MixinModel(RubyLanguageToolkit.getDefault());
		IMixinElement mixinElement = model.get("Bar%{func");
		assertNotNull(mixinElement);
		mixinElement = model.get("Bar");
		assertNotNull(mixinElement);
		mixinElement = model.get("Foo");
		assertNotNull(mixinElement);
		ISourceModule sourceModule = getSourceModule(TCLSEARCH, "src", "/simple/new.rb");
		sourceModule.delete(true, null);
		mixinElement = model.get("Bar");
		assertNotNull(mixinElement);
		
	}
	public void testMixins05() throws Exception { 
		ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.FULL_BUILD, new NullProgressMonitor());
		waitUntilIndexesReady();
		MixinModel model = new MixinModel(RubyLanguageToolkit.getDefault());
		String[] patterns = model.findKeys("*");
		assertEquals(71, patterns.length);
		
		patterns = model.findKeys("*strange*");
		assertEquals(2, patterns.length);
		
		patterns = model.findKeys("*func");
		assertEquals(2, patterns.length);
	}
}
