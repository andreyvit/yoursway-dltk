/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.ui.tests.folding;

import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.dltk.tcl.internal.ui.text.TclPartitionScanner;
import org.eclipse.dltk.tcl.internal.ui.text.folding.TclFoldingStructureProvider;
import org.eclipse.dltk.tcl.ui.TclPreferenceConstants;
import org.eclipse.dltk.tcl.ui.tests.TclUITestsPlugin;
import org.eclipse.dltk.tcl.ui.text.TclPartitions;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.source.projection.ProjectionAnnotationModel;


public class TclFoldingTest extends TestCase {
	
	private class MyTclASTFoldingStructureProvider extends TclFoldingStructureProvider {
		protected FoldingStructureComputationContext createInitialContext() {
			initializePreferences(fStore);
			return createContext(true);
		}
		
		protected FoldingStructureComputationContext createContext(boolean allowCollapse) {
			ProjectionAnnotationModel model= new ProjectionAnnotationModel ();
			
			IDocument doc = getDocument();
			if (doc == null)
				return null;
			
			return new FoldingStructureComputationContext(doc, model, allowCollapse);
		}
		
		Document fDocument;
		
		public void setDocument (Document doc) {
			fDocument = doc;
		}
		
		protected IDocument getDocument () {
			return fDocument;
		}
		
		public Map testComputeFoldingStructure(String contents, FoldingStructureComputationContext ctx) {
			super.computeFoldingStructure(contents, ctx);
			return ctx.getMap();
		}
		
	};

	IPreferenceStore fStore;	
	MyTclASTFoldingStructureProvider provider;
	
	/**
	 * Installs a partitioner with <code>document</code>.
	 * 
	 * @param document
	 *            the document
	 */
	private void installDocumentStuff(Document document) {
		String[] types = new String[] { TclPartitions.TCL_STRING,
				TclPartitions.TCL_COMMENT, IDocument.DEFAULT_CONTENT_TYPE };
		FastPartitioner partitioner = new FastPartitioner(
				new TclPartitionScanner(), types);
		partitioner.connect(document);
		document.setDocumentPartitioner(TclPartitions.TCL_PARTITIONING,
				partitioner);
	}
	
	/**
	 * Removes partitioner with <code>document</code>.
	 *
	 * @param document the document
	 */
	private void removeDocumentStuff(Document document) {
		document.setDocumentPartitioner(TclPartitions.TCL_PARTITIONING, null);
	}

	protected void setUp() throws Exception {
		super.setUp();
		fStore = TclUITestsPlugin.getDefault().getPreferenceStore();
		TclPreferenceConstants.initializeDefaultValues(fStore);
		provider = new MyTclASTFoldingStructureProvider();
	}
	
	public void test0() throws Exception {
		fStore.setValue(TclPreferenceConstants.EDITOR_FOLDING_LINES_LIMIT, 2);
		String content = "#ab\n#dc\n";
		Document document = new Document(content);
		installDocumentStuff(document);
		provider.setDocument(document);		
		Map result = provider.testComputeFoldingStructure(content, provider.createInitialContext());
		assertEquals(1, result.size());
		removeDocumentStuff(document);
	}
	
	public void test1() throws Exception {
		String content = "#ab\n\n#dc\n";
		Document document = new Document(content);
		installDocumentStuff(document);
		provider.setDocument(document);
		fStore.setValue(TclPreferenceConstants.EDITOR_FOLDING_COMMENTS_WITH_NEWLINES, false);
		Map result = provider.testComputeFoldingStructure(content, provider.createInitialContext());
		assertEquals(0, result.size());
		removeDocumentStuff(document);
	}
	
	public void test2() throws Exception {
		String content = "#ab\n\n#dc\n";
		Document document = new Document(content);
		installDocumentStuff(document);
		provider.setDocument(document);
		fStore.setValue(TclPreferenceConstants.EDITOR_FOLDING_COMMENTS_WITH_NEWLINES, true);
		Map result = provider.testComputeFoldingStructure(content, provider.createInitialContext());
		assertEquals(1, result.size());
		removeDocumentStuff(document);
	}
	
	public void test3() throws Exception {
		fStore.setValue(TclPreferenceConstants.EDITOR_FOLDING_LINES_LIMIT, 2);
		String content = " namespace eval NM {\n"+
		"    # headercomment\n"+
		"    # here\n"+
		"    # ...\n"+
		"}\n"+
		"proc foo {} {\n"+
		"    if $a {\n"+
		"       doo\n"+
		"       doo2\n"+
		"       anothercmdblock xxx {\n"+
		"            #...\n"+
		"       }\n"+
		"    }\n"+
		"}\n";
		Document document = new Document(content);
		installDocumentStuff(document);
		provider.setDocument(document);
		fStore.setValue(TclPreferenceConstants.EDITOR_FOLDING_COMMENTS_WITH_NEWLINES, true);
		fStore.setValue(TclPreferenceConstants.EDITOR_FOLDING_BLOCKS, TclPreferenceConstants.EDITOR_FOLDING_BLOCKS_EXCLUDE);
		fStore.setValue(TclPreferenceConstants.EDITOR_FOLDING_EXCLUDE_LIST, "");
		Map result = provider.testComputeFoldingStructure(content, provider.createInitialContext());
		assertEquals(4, result.size());
		removeDocumentStuff(document);
	}
	
	public void test4() throws Exception {
		String content = "namespace eval NM {\n"+
		"    # headercomment\n"+
		"    # here\n"+
		"    # ...\n"+
		"}\n"+
		"proc foo {} {\n"+
		"    if $a {\n"+
		"       doo\n"+
		"       doo2\n"+
		"       anothercmdblock xxx {\n"+
		"            #...\n"+
		"       }\n"+
		"    }\n"+
		"}\n";
		Document document = new Document(content);
		installDocumentStuff(document);
		provider.setDocument(document);
		fStore.setValue(TclPreferenceConstants.EDITOR_FOLDING_COMMENTS_WITH_NEWLINES, true);
		fStore.setValue(TclPreferenceConstants.EDITOR_FOLDING_BLOCKS, TclPreferenceConstants.EDITOR_FOLDING_BLOCKS_INCLUDE);
		fStore.setValue(TclPreferenceConstants.EDITOR_FOLDING_INCLUDE_LIST, "anothercmdblock");
		Map result = provider.testComputeFoldingStructure(content, provider.createInitialContext());
		assertEquals(1, result.size());
		removeDocumentStuff(document);
	}
	
	
}