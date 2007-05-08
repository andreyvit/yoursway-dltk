/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.text.completion;

import java.util.Comparator;

import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.templates.TemplateProposal;

/**
 * Comparator for script completion proposals. Completion proposals can be sorted by relevance or
 * alphabetically.
 * <p>
 * Note: this comparator imposes orderings that are inconsistent with equals.
 * </p>
 * 
 */
public final class CompletionProposalComparator implements Comparator {

	private boolean fOrderAlphabetically;

	/**
	 * Creates a comparator that sorts by relevance.
	 */
	public CompletionProposalComparator() {
		fOrderAlphabetically= false;
	}

	/**
	 * Sets the sort order. Default is <code>false</code>, i.e. order by
	 * relevance.
	 *
	 * @param orderAlphabetically <code>true</code> to order alphabetically,
	 *        <code>false</code> to order by relevance
	 */
	public void setOrderAlphabetically(boolean orderAlphabetically) {
		fOrderAlphabetically= orderAlphabetically;
	}
	
	public int compare(Object o1, Object o2) {
		ICompletionProposal p1= (ICompletionProposal) o1;
		ICompletionProposal p2= (ICompletionProposal) o2;

		if (!fOrderAlphabetically) {
			int r1= getRelevance(p1);
			int r2= getRelevance(p2);
			int relevanceDif= r2 - r1;
			if (relevanceDif != 0) {
				return relevanceDif;
			}
		}
		/*
		 * TODO the correct (but possibly much slower) sorting would use a
		 * collator.
		 */
		// fix for bug 67468
		return getSortKey(p1).compareToIgnoreCase(getSortKey(p2));
	}

	private String getSortKey(ICompletionProposal p) {
		if (p instanceof AbstractScriptCompletionProposal)
			return ((AbstractScriptCompletionProposal) p).getSortString();
		return p.getDisplayString();
	}

	private int getRelevance(ICompletionProposal obj) {
		if (obj instanceof IScriptCompletionProposal) {
			IScriptCompletionProposal jcp= (IScriptCompletionProposal) obj;
			return jcp.getRelevance();
		} else if (obj instanceof TemplateProposal) {
			TemplateProposal tp= (TemplateProposal) obj;
			return tp.getRelevance();
		}
		// catch all
		return 0;
	}
}
