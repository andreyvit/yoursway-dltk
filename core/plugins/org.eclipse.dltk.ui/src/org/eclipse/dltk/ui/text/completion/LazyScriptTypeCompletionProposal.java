/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.text.completion;

import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.util.QualifiedTypeNameHistory;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;

/**
 * If passed compilation unit is not null, the replacement string will be seen
 * as a qualified type name.
 */
public abstract class LazyScriptTypeCompletionProposal extends
		LazyScriptCompletionProposal {

	protected final ISourceModule fSourceModule;

//	private String fQualifiedName;
	private String fSimpleName;

	public LazyScriptTypeCompletionProposal(CompletionProposal proposal,
			ScriptContentAssistInvocationContext context) {
		super(proposal, context);
		fSourceModule = context.getSourceModule();
//		fQualifiedName = null;
	}

	public final String getQualifiedTypeName() {
//		if (fQualifiedName == null) 
//			fQualifiedName = String.valueOf(Signature.toCharArray(Signature
//					.getTypeErasure(fProposal.getSignature())));
//		return fQualifiedName;
		return null;
	}

	protected final String getSimpleTypeName() {			
		return fSimpleName;
	}

	protected String computeReplacementString() {
		String replacement = super.computeReplacementString();

		/*
		 * Always use the simple name for non-formal scriptdoc references to
		 * types.
		 */
		// TODO fix
		if (fProposal.getKind() == CompletionProposal.TYPE_REF
				&& fInvocationContext.getCoreContext().isInDoc())
			return getSimpleTypeName();

		String qualifiedTypeName = getQualifiedTypeName();
		if (qualifiedTypeName.indexOf('.') == -1)
			// default package - no imports needed
			return qualifiedTypeName;

		/*
		 * If the user types in the qualification, don't force import rewriting
		 * on him - insert the qualified name.
		 */
		IDocument document = fInvocationContext.getDocument();
		if (document != null) {
			String prefix = getPrefix(document, getReplacementOffset()
					+ getReplacementLength());
			int dotIndex = prefix.lastIndexOf('.');
			// match up to the last dot in order to make higher level matching
			// still work (camel case...)
			if (dotIndex != -1
					&& qualifiedTypeName.toLowerCase().startsWith(
							prefix.substring(0, dotIndex + 1).toLowerCase()))
				return qualifiedTypeName;
		}

		/*
		 * The replacement does not contain a qualification (e.g. an inner type
		 * qualified by its parent) - use the replacement directly.
		 */
		if (replacement.indexOf('.') == -1) {
			if (isInDoc())
				return getSimpleTypeName(); // don't use the braces added for
											// javadoc link proposals
			return replacement;
		}

		return qualifiedTypeName;
	}

	public void apply(IDocument document, char trigger, int offset) {
		boolean insertClosingParenthesis = trigger == '('
				&& autocloseBrackets();
		if (insertClosingParenthesis) {
			updateReplacementWithParentheses();
			trigger = '\0';
		}
		super.apply(document, trigger, offset);

		if (insertClosingParenthesis) {
			setUpLinkedMode(document, ')');
		}

		try {
			rememberSelection();
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateReplacementWithParentheses() {
		StringBuffer replacement = new StringBuffer(getReplacementString());
		// FormatterPrefs prefs= getFormatterPrefs();

		// if (prefs.beforeOpeningParen)
		// replacement.append(SPACE);
		replacement.append(LPAREN);

		// if (prefs.afterOpeningParen)
		// replacement.append(SPACE);

		setCursorPosition(replacement.length());

		// if (prefs.afterOpeningParen)
		// replacement.append(SPACE);

		replacement.append(RPAREN);

		setReplacementString(replacement.toString());
	}

	/**
	 * Remembers the selection in the content assist history.
	 * 
	 * @throws ModelException
	 *             if anything goes wrong
	 * 
	 */
	protected final void rememberSelection() throws ModelException {
		IType lhs = fInvocationContext.getExpectedType();
		IType rhs = (IType) getModelElement();
		if (lhs != null && rhs != null)
			DLTKUIPlugin.getDefault().getContentAssistHistory().remember(lhs,
					rhs);

		QualifiedTypeNameHistory.remember(getQualifiedTypeName());
	}

	/**
	 * Returns <code>true</code> if imports may be added. The return value
	 * depends on the context and preferences only and does not take into
	 * account the contents of the compilation unit or the kind of proposal.
	 * Even if <code>true</code> is returned, there may be cases where no
	 * imports are added for the proposal. For example:
	 * <ul>
	 * <li>when completing within the import section</li>
	 * <li>when completing informal javadoc references (e.g. within
	 * <code>&lt;code&gt;</code> tags)</li>
	 * <li>when completing a type that conflicts with an existing import</li>
	 * <li>when completing an implicitly imported type (same package,
	 * <code>java.lang</code> types)</li>
	 * </ul>
	 * <p>
	 * The decision whether a qualified type or the simple type name should be
	 * inserted must take into account these different scenarios.
	 * </p>
	 * <p>
	 * Subclasses may extend.
	 * </p>
	 * 
	 * @return <code>true</code> if imports may be added, <code>false</code>
	 *         if not
	 */
	protected boolean allowAddingImports() {
		if (isInDoc()) {
			// TODO fix
			// if (!fContext.isInJavadocFormalReference())
			// return false;
			if (fProposal.getKind() == CompletionProposal.TYPE_REF
					&& fInvocationContext.getCoreContext().isInDoc())
				return false;

			if (!isDocProcessingEnabled())
				return false;
		}

		IPreferenceStore preferenceStore = DLTKUIPlugin.getDefault()
				.getPreferenceStore();
		return preferenceStore
				.getBoolean(PreferenceConstants.CODEASSIST_ADDIMPORT);
	}

	private boolean isDocProcessingEnabled() {
//		IDLTKProject project = fSourceModule.getScriptProject();
//		boolean processJavadoc;
		if (DLTKCore.DEBUG) {
			System.out.println("TODO: Add documentation support.");
		}
		// if (project == null)
		// processJavadoc=
		// DLTKCore.ENABLED.equals(DLTKCore.getOption(DLTKCore.COMPILER_DOC_COMMENT_SUPPORT));
		// else
		// processJavadoc=
		// DLTKCore.ENABLED.equals(project.getOption(DLTKCore.COMPILER_DOC_COMMENT_SUPPORT,
		// true));
		// return processJavadoc;
		return false;
	}

	protected boolean isValidPrefix(String prefix) {
		return isPrefix(prefix, getSimpleTypeName())
				|| isPrefix(prefix, getQualifiedTypeName());
	}

	public CharSequence getPrefixCompletionText(IDocument document,
			int completionOffset) {
		String prefix = getPrefix(document, completionOffset);

		String completion;
		// return the qualified name if the prefix is already qualified
		if (prefix.indexOf('.') != -1)
			completion = getQualifiedTypeName();
		else
			completion = getSimpleTypeName();

		if (isCamelCaseMatching())
			return getCamelCaseCompound(prefix, completion);

		return completion;
	}

	protected abstract char[] getTypeTriggers();

	protected abstract char[] getDocTriggers();

	protected char[] computeTriggerCharacters() {
		return isInDoc() ? getDocTriggers() : getTypeTriggers();
	}

	protected ProposalInfo computeProposalInfo() {
		if (fSourceModule != null) {
			IDLTKProject project = fSourceModule.getScriptProject();
			if (project != null)
				return new TypeProposalInfo(project, fProposal);
		}
		return super.computeProposalInfo();
	}

	protected int computeRelevance() {
		/*
		 * There are two histories: the RHS history remembers types used for the
		 * current expected type (left hand side), while the type history
		 * remembers recently used types in general).
		 * 
		 * The presence of an RHS ranking is a much more precise sign for
		 * relevance as it proves the subtype relationship between the proposed
		 * type and the expected type.
		 * 
		 * The "recently used" factor (of either the RHS or general history) is
		 * less important, it should not override other relevance factors such
		 * as if the type is already imported etc.
		 */
		float rhsHistoryRank = fInvocationContext
				.getHistoryRelevance(getQualifiedTypeName());
		float typeHistoryRank = QualifiedTypeNameHistory.getDefault()
				.getNormalizedPosition(getQualifiedTypeName());

		int recencyBoost = Math.round((rhsHistoryRank + typeHistoryRank) * 5);
		int rhsBoost = rhsHistoryRank > 0.0f ? 50 : 0;
		int baseRelevance = super.computeRelevance();

		return baseRelevance + rhsBoost + recencyBoost;
	}
}
