package org.eclipse.dltk.javascript.internal.ui.editor;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.ISourceReference;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.actions.FoldingActionGroup;
import org.eclipse.dltk.internal.ui.editor.DLTKEditorMessages;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.internal.ui.editor.ScriptOutlinePage;
import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.dltk.internal.ui.editor.ToggleCommentAction;
import org.eclipse.dltk.javascript.core.JavaScriptLanguageToolkit;
import org.eclipse.dltk.javascript.internal.ui.JavaScriptUI;
import org.eclipse.dltk.javascript.internal.ui.text.JavaScriptPairMatcher;
import org.eclipse.dltk.javascript.internal.ui.text.folding.JavascriptFoldingStructureProvider;
import org.eclipse.dltk.javascript.ui.JavascriptPreferenceConstants;
import org.eclipse.dltk.javascript.ui.actions.AddBlockCommentAction;
import org.eclipse.dltk.javascript.ui.actions.GenerateActionGroup;
import org.eclipse.dltk.javascript.ui.actions.IndentAction;
import org.eclipse.dltk.javascript.ui.actions.RemoveBlockCommentAction;
import org.eclipse.dltk.javascript.ui.text.IJavaScriptPartitions;
import org.eclipse.dltk.ui.actions.IDLTKEditorActionDefinitionIds;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.folding.IFoldingStructureProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.ITextViewerExtension;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.IUpdate;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.eclipse.ui.texteditor.TextOperationAction;

public class JavaScriptEditor extends ScriptEditor {

	public static final String EDITOR_ID = "org.eclipse.dltk.javascript.internal.ui.editor.JavascriptEditor";

	public static final String EDITOR_CONTEXT = "#JavascriptEditorContext";
	
	public static final String RULER_CONTEXT = "#JavascriptRulerContext";

	private org.eclipse.dltk.internal.ui.editor.BracketInserter fBracketInserter = new JavaScriptBracketInserter(
			this);
	
protected class FormatElementAction extends Action implements IUpdate {
		
		/*
		 * @since 3.2
		 */
		FormatElementAction() {
			setEnabled(isEditorInputModifiable());
		}

		/*
		 * @see org.eclipse.jface.action.IAction#run()
		 */
		public void run() {

			final ScriptSourceViewer viewer= (ScriptSourceViewer) getSourceViewer();
			if (viewer.isEditable()) {

				final Point selection= viewer.rememberSelection();
				try {
					viewer.setRedraw(false);

					final String type= TextUtilities.getContentType(viewer.getDocument(), IJavaScriptPartitions.JS_PARTITIONING, selection.x, true);
					if (type.equals(IDocument.DEFAULT_CONTENT_TYPE) && selection.y == 0) {

						try {
							final IModelElement element= getElementAt(selection.x, true);
							if (element != null && element.exists()) {

								final int kind= element.getElementType();
								if (kind == IModelElement.TYPE || kind == IModelElement.METHOD ) {

									final ISourceReference reference= (ISourceReference)element;
									final ISourceRange range= reference.getSourceRange();

									if (range != null) {
										viewer.setSelectedRange(range.getOffset(), range.getLength());
										viewer.doOperation(ISourceViewer.FORMAT);
									}
								}
							}
						} catch (ModelException exception) {
							// Should not happen
						}
					} else {
						viewer.setSelectedRange(selection.x, 1);
						viewer.doOperation(ISourceViewer.FORMAT);
					}
				} catch (BadLocationException exception) {
					// Can not happen
				} finally {

					viewer.setRedraw(true);
					viewer.restoreSelection();
				}
			}
		}

		/*
		 * @see org.eclipse.ui.texteditor.IUpdate#update()
		 * @since 3.2
		 */
		public void update() {
			setEnabled(isEditorInputModifiable());
		}
	}

	private JavaScriptPairMatcher bracketMatcher = new JavaScriptPairMatcher(
			"{}[]()".toCharArray());

	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		IPreferenceStore preferenceStore = getPreferenceStore();
		boolean closeBrackets = preferenceStore.getBoolean(CLOSE_BRACKETS);
		boolean closeStrings = preferenceStore.getBoolean(CLOSE_STRINGS);
		boolean closeAngularBrackets = false;

		fBracketInserter.setCloseBracketsEnabled(closeBrackets);
		fBracketInserter.setCloseStringsEnabled(closeStrings);
		fBracketInserter.setCloseAngularBracketsEnabled(closeAngularBrackets);

		ISourceViewer sourceViewer = getSourceViewer();
		if (sourceViewer instanceof ITextViewerExtension)
			((ITextViewerExtension) sourceViewer)
					.prependVerifyKeyListener(fBracketInserter);

//		if (isMarkingOccurrences())
//			installOccurrencesFinder(false);
	}

	protected void initializeEditor() {
		super.initializeEditor();
		setEditorContextMenuId(EDITOR_CONTEXT);
		setRulerContextMenuId(RULER_CONTEXT);
	}

	protected IPreferenceStore getScriptPreferenceStore() {
		return JavaScriptUI.getDefault().getPreferenceStore();
	}

	protected ScriptTextTools getTextTools() {
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

	private GenerateActionGroup fGenerateActionGroup;

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
	
	public void dispose() {
		ISourceViewer sourceViewer= getSourceViewer();
		if (sourceViewer instanceof ITextViewerExtension)
			((ITextViewerExtension) sourceViewer).removeVerifyKeyListener(fBracketInserter);
		super.dispose();
	}
	
	/** Preference key for automatically closing strings */
	private final static String CLOSE_STRINGS= JavascriptPreferenceConstants.EDITOR_CLOSE_STRINGS;
	/** Preference key for automatically closing brackets and parenthesis */
	private final static String CLOSE_BRACKETS= JavascriptPreferenceConstants.EDITOR_CLOSE_BRACKETS;
	
	protected void handlePreferenceStoreChanged(PropertyChangeEvent event){
		try {

				String p= event.getProperty();
				if (CLOSE_BRACKETS.equals(p)) {
					fBracketInserter.setCloseBracketsEnabled(getPreferenceStore().getBoolean(p));
					return;
				}

				if (CLOSE_STRINGS.equals(p)) {
					fBracketInserter.setCloseStringsEnabled(getPreferenceStore().getBoolean(p));
					return;
				}
				
//				if (SPACES_FOR_TABS.equals(p)) {
//					if (isTabsToSpacesConversionEnabled())
//						installTabsToSpacesConverter();
//					else
//						uninstallTabsToSpacesConverter();
//					return;
//				}
//
//				if (PreferenceConstants.EDITOR_SMART_TAB.equals(p)) {
//					if (getPreferenceStore().getBoolean(PreferenceConstants.EDITOR_SMART_TAB)) {
//						setActionActivationCode("IndentOnTab", '\t', -1, SWT.NONE); //$NON-NLS-1$
//					} else {
//						removeActionActivationCode("IndentOnTab"); //$NON-NLS-1$
//					}
//				}
//
//				IContentAssistant c= asv.getContentAssistant();
//				if (c instanceof ContentAssistant)
//					ContentAssistPreference.changeConfiguration((ContentAssistant) c, getPreferenceStore(), event);
//
//				if (CODE_FORMATTER_TAB_SIZE.equals(p) && isTabsToSpacesConversionEnabled()) {
//					uninstallTabsToSpacesConverter();
//					installTabsToSpacesConverter();
//				}
		} finally {
			super.handlePreferenceStoreChanged(event);
		}
	}

	/*
	 * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditor#initializeKeyBindingScopes()
	 */
	protected void initializeKeyBindingScopes() {
		setKeyBindingScopes(new String[] { "org.eclipse.dltk.ui.javascriptEditorScope" }); //$NON-NLS-1$
	}
	
	protected void createActions() {
		super.createActions();

		Action action = new TextOperationAction(DLTKEditorMessages
				.getBundleForConstructedKeys(),
				"Comment.", this, ITextOperationTarget.PREFIX); //$NON-NLS-1$
		action.setActionDefinitionId(IDLTKEditorActionDefinitionIds.COMMENT);
		setAction("Comment", action); //$NON-NLS-1$
		markAsStateDependentAction("Comment", true); //$NON-NLS-1$

		action = new TextOperationAction(DLTKEditorMessages
				.getBundleForConstructedKeys(),
				"Uncomment.", this, ITextOperationTarget.STRIP_PREFIX); //$NON-NLS-1$
		action.setActionDefinitionId(IDLTKEditorActionDefinitionIds.UNCOMMENT);
		setAction("Uncomment", action); //$NON-NLS-1$
		markAsStateDependentAction("Uncomment", true); //$NON-NLS-1$

		action = new ToggleCommentAction(DLTKEditorMessages
				.getBundleForConstructedKeys(), "ToggleComment.", this); //$NON-NLS-1$
		action
				.setActionDefinitionId(IDLTKEditorActionDefinitionIds.TOGGLE_COMMENT);
		setAction("ToggleComment", action); //$NON-NLS-1$
		markAsStateDependentAction("ToggleComment", true); //$NON-NLS-1$
		ISourceViewer sourceViewer = getSourceViewer();
		SourceViewerConfiguration configuration = getSourceViewerConfiguration();
		((ToggleCommentAction) action).configure(sourceViewer, configuration);
		action= new TextOperationAction(DLTKEditorMessages.getBundleForConstructedKeys(), "Format.", this, ISourceViewer.FORMAT); //$NON-NLS-1$
		action.setActionDefinitionId(IDLTKEditorActionDefinitionIds.FORMAT);
		setAction("Format", action); //$NON-NLS-1$
		markAsStateDependentAction("Format", true); //$NON-NLS-1$
		markAsSelectionDependentAction("Format", true); //$NON-NLS-1$
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(action, IJavaHelpContextIds.FORMAT_ACTION);

		action= new FormatElementAction();
		action.setActionDefinitionId(IDLTKEditorActionDefinitionIds.QUICK_FORMAT);
		setAction("QuickFormat", action); //$NON-NLS-1$
		markAsStateDependentAction("QuickFormat", true); //$NON-NLS-1$
		
		action= new AddBlockCommentAction(DLTKEditorMessages.getBundleForConstructedKeys(), "AddBlockComment.", this);  //$NON-NLS-1$
		action.setActionDefinitionId(IDLTKEditorActionDefinitionIds.ADD_BLOCK_COMMENT);
		setAction("AddBlockComment", action); //$NON-NLS-1$
		markAsStateDependentAction("AddBlockComment", true); //$NON-NLS-1$
		markAsSelectionDependentAction("AddBlockComment", true); //$NON-NLS-1$
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(action, IDLTKJavaHelpContextIds.ADD_BLOCK_COMMENT_ACTION);

		action= new RemoveBlockCommentAction(DLTKEditorMessages.getBundleForConstructedKeys(), "RemoveBlockComment.", this);  //$NON-NLS-1$
		action.setActionDefinitionId(IDLTKEditorActionDefinitionIds.REMOVE_BLOCK_COMMENT);
		setAction("RemoveBlockComment", action); //$NON-NLS-1$
		markAsStateDependentAction("RemoveBlockComment", true); //$NON-NLS-1$
		markAsSelectionDependentAction("RemoveBlockComment", true); //$NON-NLS-1$
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(action, IJavaHelpContextIds.REMOVE_BLOCK_COMMENT_ACTION);

		action= new IndentAction(DLTKEditorMessages.getBundleForConstructedKeys(), "Indent.", this, false); //$NON-NLS-1$
		action.setActionDefinitionId(IDLTKEditorActionDefinitionIds.INDENT);
		setAction("Indent", action); //$NON-NLS-1$
		markAsStateDependentAction("Indent", true); //$NON-NLS-1$
		markAsSelectionDependentAction("Indent", true); //$NON-NLS-1$
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(action, IJavaHelpContextIds.INDENT_ACTION);
		

		
		
		

		fGenerateActionGroup= new GenerateActionGroup(this, ITextEditorActionConstants.GROUP_EDIT);
		//ActionGroup rg= new RefactorActionGroup(this, ITextEditorActionConstants.GROUP_EDIT, false);
		//ActionGroup surroundWith= new SurroundWithActionGroup(this, ITextEditorActionConstants.GROUP_EDIT);
		
		fContextMenuGroup.addGroup(fGenerateActionGroup);
		//fActionGroups.addGroup(rg);
		//fActionGroups.addGroup(fGenerateActionGroup);
		
		
	}
}
