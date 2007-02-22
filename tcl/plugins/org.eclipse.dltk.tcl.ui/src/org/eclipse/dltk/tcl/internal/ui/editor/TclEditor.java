package org.eclipse.dltk.tcl.internal.ui.editor;

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.internal.ui.actions.FoldingActionGroup;
import org.eclipse.dltk.internal.ui.editor.DLTKEditorMessages;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.internal.ui.editor.ScriptOutlinePage;
import org.eclipse.dltk.internal.ui.editor.ToggleCommentAction;
import org.eclipse.dltk.tcl.core.TclLanguageToolkit;
import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.dltk.tcl.internal.ui.text.TclPairMatcher;
import org.eclipse.dltk.tcl.internal.ui.text.folding.TclFoldingStructureProvider;
import org.eclipse.dltk.tcl.ui.TclPreferenceConstants;
import org.eclipse.dltk.tcl.ui.text.TclPartitions;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.actions.IDLTKEditorActionDefinitionIds;
import org.eclipse.dltk.ui.text.TextTools;
import org.eclipse.dltk.ui.text.folding.IFoldingStructureProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.ITextViewerExtension5;
import org.eclipse.jface.text.source.ICharacterPairMatcher;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.eclipse.ui.texteditor.TextOperationAction;

public class TclEditor extends ScriptEditor {
	public static final String EDITOR_ID = "org.eclipse.dltk.tcl.ui.editor.TclEditor";

	public static final String EDITOR_CONTEXT = "#TclEditorContext";

	public static final String RULER_CONTEXT = "#TclRulerContext";

	private IFoldingStructureProvider foldingProvider;

	private TclPairMatcher bracketMatcher = new TclPairMatcher(BRACKETS);

	private void configureToggleCommentAction() {
		IAction action = getAction("ToggleComment"); //$NON-NLS-1$
		if (action instanceof ToggleCommentAction) {
			ISourceViewer sourceViewer = getSourceViewer();
			SourceViewerConfiguration configuration = getSourceViewerConfiguration();
			((ToggleCommentAction) action).configure(sourceViewer,
					configuration);
		}
	}

	protected void initializeEditor() {
		super.initializeEditor();
		
		setEditorContextMenuId(EDITOR_CONTEXT);
		setRulerContextMenuId(RULER_CONTEXT);
	}

	protected void createActions() {
		super.createActions();

		// Comment
		Action action = new TextOperationAction(DLTKEditorMessages
				.getBundleForConstructedKeys(),
				"Comment.", this, ITextOperationTarget.PREFIX); //$NON-NLS-1$
		action.setActionDefinitionId(IDLTKEditorActionDefinitionIds.COMMENT);
		setAction("Comment", action); //$NON-NLS-1$
		markAsStateDependentAction("Comment", true); //$NON-NLS-1$

		// Uncomment
		action = new TextOperationAction(DLTKEditorMessages
				.getBundleForConstructedKeys(),
				"Uncomment.", this, ITextOperationTarget.STRIP_PREFIX); //$NON-NLS-1$
		action.setActionDefinitionId(IDLTKEditorActionDefinitionIds.UNCOMMENT);
		setAction("Uncomment", action); //$NON-NLS-1$
		markAsStateDependentAction("Uncomment", true); //$NON-NLS-1$

		// Toggle comment
		action = new ToggleCommentAction(DLTKEditorMessages
				.getBundleForConstructedKeys(), "ToggleComment.", this); //$NON-NLS-1$
		action
				.setActionDefinitionId(IDLTKEditorActionDefinitionIds.TOGGLE_COMMENT);
		setAction("ToggleComment", action); //$NON-NLS-1$
		markAsStateDependentAction("ToggleComment", true); //$NON-NLS-1$
		configureToggleCommentAction();
	}

	protected void handlePreferenceStoreChanged(PropertyChangeEvent event) {
		String property = event.getProperty();
		try {
			ISourceViewer sourceViewer = getSourceViewer();
			if (sourceViewer == null) {
				return;
			}
			if (TclPreferenceConstants.EDITOR_FOLDING_BLOCKS.equals(property)
					|| TclPreferenceConstants.EDITOR_FOLDING_COMMENTS_WITH_NEWLINES
							.equals(property)
					|| TclPreferenceConstants.EDITOR_FOLDING_EXCLUDE_LIST
							.equals(property)
					|| TclPreferenceConstants.EDITOR_FOLDING_INCLUDE_LIST
							.equals(property)
					|| PreferenceConstants.EDITOR_FOLDING_LINES_LIMIT
							.equals(property)) {

				if (sourceViewer instanceof ProjectionViewer) {
					fProjectionModelUpdater.initialize();
				}
				return;
			}
		} finally {
			super.handlePreferenceStoreChanged(event);
		}
	}

	protected IPreferenceStore getScriptPreferenceStore() {
		return TclUI.getDefault().getPreferenceStore(); 
	}
	
	protected TextTools getTextTools() {
		return TclUI.getDefault().getTclTextTools();
	}

	protected ScriptOutlinePage doCreateOutlinePage() {
		return new TclOutlinePage(this, TclUI.getDefault()
				.getPreferenceStore());		
	}

	protected void connectPartitioningToElement(IEditorInput input,
			IDocument document) {
		if (document instanceof IDocumentExtension3) {
			IDocumentExtension3 doc = (IDocumentExtension3) document;
			if (doc.getDocumentPartitioner(TclPartitions.TCL_PARTITIONING) == null) {
				IDocumentSetupParticipant participant = new TclDocumentSetupParticipant();
				participant.setup(document);
			}
		}
	}

	protected IFoldingStructureProvider getFoldingStructureProvider() {
		if (foldingProvider == null) {
			foldingProvider = new TclFoldingStructureProvider();
		}
		
		return foldingProvider;
	}

	protected FoldingActionGroup createFoldingActionGroup() {
		return new FoldingActionGroup(this, getViewer(), TclUI.getDefault()
				.getPreferenceStore());
	}

	public String getEditorId() {
		return EDITOR_ID;
	}

	protected void initializeKeyBindingScopes() {
		setKeyBindingScopes(new String[] { "org.eclipse.dltk.ui.tclEditorScope" }); //$NON-NLS-1$
	}

	public IDLTKLanguageToolkit getLanguageToolkit() {
		return TclLanguageToolkit.getDefault();
	}

	public String getCallHierarchyID() {
		return "org.eclipse.dltk.callhierarchy.view";
	}

	/**
	 * Jumps to the matching bracket.
	 */
	public void gotoMatchingBracket() {
		ISourceViewer sourceViewer = getSourceViewer();
		IDocument document = sourceViewer.getDocument();
		if (document == null)
			return;

		IRegion selection = getSignedSelection(sourceViewer);

		int selectionLength = Math.abs(selection.getLength());
		if (selectionLength > 1) {
			setStatusLineErrorMessage("No bracket selected");
			sourceViewer.getTextWidget().getDisplay().beep();
			return;
		}

		// #26314
		int sourceCaretOffset = selection.getOffset() + selection.getLength();
		if (isSurroundedByBrackets(document, sourceCaretOffset))
			sourceCaretOffset -= selection.getLength();

		IRegion region = bracketMatcher.match(document, sourceCaretOffset);
		if (region == null) {
			setStatusLineErrorMessage("No matching bracket found");
			sourceViewer.getTextWidget().getDisplay().beep();
			return;
		}

		int offset = region.getOffset();
		int length = region.getLength();

		if (length < 1)
			return;

		int anchor = bracketMatcher.getAnchor();
		// http://dev.eclipse.org/bugs/show_bug.cgi?id=34195
		int targetOffset = (ICharacterPairMatcher.RIGHT == anchor) ? offset + 1
				: offset + length;

		boolean visible = false;
		if (sourceViewer instanceof ITextViewerExtension5) {
			ITextViewerExtension5 extension = (ITextViewerExtension5) sourceViewer;
			visible = (extension.modelOffset2WidgetOffset(targetOffset) > -1);
		} else {
			IRegion visibleRegion = sourceViewer.getVisibleRegion();
			// http://dev.eclipse.org/bugs/show_bug.cgi?id=34195
			visible = (targetOffset >= visibleRegion.getOffset() && targetOffset <= visibleRegion
					.getOffset()
					+ visibleRegion.getLength());
		}

		if (!visible) {
			setStatusLineErrorMessage("Matching bracket is outside selected element");
			sourceViewer.getTextWidget().getDisplay().beep();
			return;
		}

		if (selection.getLength() < 0)
			targetOffset -= selection.getLength();

		sourceViewer.setSelectedRange(targetOffset, selection.getLength());
		sourceViewer.revealRange(targetOffset, selection.getLength());
	}

	protected void configureSourceViewerDecorationSupport(
			SourceViewerDecorationSupport support) {
		support.setCharacterPairMatcher(bracketMatcher);
		support.setMatchingCharacterPainterPreferenceKeys(MATCHING_BRACKETS,
				MATCHING_BRACKETS_COLOR);

		super.configureSourceViewerDecorationSupport(support);
	}
}