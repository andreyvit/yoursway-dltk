package org.eclipse.dltk.javascript.internal.ui.editor;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.internal.ui.actions.FoldingActionGroup;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.internal.ui.editor.ScriptOutlinePage;
import org.eclipse.dltk.javascript.core.JavaScriptLanguageToolkit;
import org.eclipse.dltk.javascript.internal.ui.JavaScriptUI;
import org.eclipse.dltk.javascript.internal.ui.text.JavaScriptPairMatcher;
import org.eclipse.dltk.javascript.internal.ui.text.folding.JavascriptFoldingStructureProvider;
import org.eclipse.dltk.javascript.ui.text.IJavaScriptPartitions;
import org.eclipse.dltk.ui.text.TextTools;
import org.eclipse.dltk.ui.text.folding.IFoldingStructureProvider;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;

public class JavaScriptEditor extends ScriptEditor {

	public static final String EDITOR_ID = "org.eclipse.dltk.javascript.internal.ui.editor.JavascriptEditor";

	public static final String EDITOR_CONTEXT = "#JavascriptEditorContext";
	
	private JavaScriptPairMatcher bracketMatcher = new JavaScriptPairMatcher("{}[]()".toCharArray());

	protected void initializeEditor() {
		super.initializeEditor();
		setEditorContextMenuId(EDITOR_CONTEXT);
	}

	protected IPreferenceStore getScriptPreferenceStore() {
		return JavaScriptUI.getDefault().getPreferenceStore();
	}

	protected TextTools getTextTools() {
		return JavaScriptUI.getDefault().getTextTools();
	}

	protected ScriptOutlinePage doCreateOutlinePage() {
		return new JavaScriptOutlinePage(this, JavaScriptUI.getDefault()
				.getPreferenceStore());
	}
	
	protected void configureSourceViewerDecorationSupport(
			SourceViewerDecorationSupport support) {
		support.setCharacterPairMatcher(bracketMatcher);
		support.setMatchingCharacterPainterPreferenceKeys(MATCHING_BRACKETS,
				MATCHING_BRACKETS_COLOR);

		super.configureSourceViewerDecorationSupport(support);
	}

	protected void connectPartitioningToElement(IEditorInput input,
			IDocument document) {
		if (document instanceof IDocumentExtension3) {
			IDocumentExtension3 extension = (IDocumentExtension3) document;
			if (extension
					.getDocumentPartitioner(IJavaScriptPartitions.JS_PARTITIONING) == null) {
				JavaScriptDocumentSetupParticipant participant = new JavaScriptDocumentSetupParticipant();
				participant.setup(document);
			}
		}
	}

	IFoldingStructureProvider fFoldingProvider = null;

	protected IFoldingStructureProvider getFoldingStructureProvider() {
		if (fFoldingProvider == null) {
			fFoldingProvider = new JavascriptFoldingStructureProvider();
		}
		return fFoldingProvider;
	}

	protected FoldingActionGroup createFoldingActionGroup() {
		return new FoldingActionGroup(this, getViewer(), JavaScriptUI
				.getDefault().getPreferenceStore());
	}

	public String getEditorId() {
		return EDITOR_ID;
	}

	public IDLTKLanguageToolkit getLanguageToolkit() {
		return JavaScriptLanguageToolkit.getDefault();
	}

	public String getCallHierarchyID() {
		return "org.eclipse.dltk.callhierarchy.view";
	}

	/*
	 * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditor#initializeKeyBindingScopes()
	 */
	protected void initializeKeyBindingScopes() {
		setKeyBindingScopes(new String[] { "org.eclipse.dltk.ui.javascriptEditorScope" }); //$NON-NLS-1$
	}
}
