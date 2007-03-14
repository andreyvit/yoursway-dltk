package org.eclipse.dltk.ruby.tests.search.mixin;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.compiler.util.SimpleSet;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.MixinModel;
import org.eclipse.dltk.core.search.BasicSearchEngine;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.index.EntryResult;
import org.eclipse.dltk.core.search.index.Index;
import org.eclipse.dltk.core.search.indexing.IIndexConstants;
import org.eclipse.dltk.core.search.indexing.IndexManager;
import org.eclipse.dltk.core.search.indexing.ReadWriteMonitor;
import org.eclipse.dltk.core.tests.model.AbstractDLTKSearchTests;
import org.eclipse.dltk.internal.core.ModelManager;
import org.eclipse.dltk.ruby.core.RubyLanguageToolkit;
import org.eclipse.dltk.ruby.tests.Activator;


public class MixinTests extends AbstractDLTKSearchTests implements IDLTKSearchConstants {
	private static final String TCLSEARCH = "typeinference";

	public MixinTests(String name) {
		super(Activator.PLUGIN_ID, name);
	}

	public static Suite suite() {
		return new Suite(MixinTests.class);
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
	public void testMixins01() throws Exception { 
		up();
		MixinModel model = new MixinModel(RubyLanguageToolkit.getDefault());
		IMixinElement mixinElement = model.get("Boz");
		assertNotNull(mixinElement);
	}

}
