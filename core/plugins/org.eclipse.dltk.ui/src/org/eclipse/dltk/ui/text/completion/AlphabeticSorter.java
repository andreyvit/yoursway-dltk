/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.ui.text.completion;

import org.eclipse.jface.text.contentassist.ICompletionProposal;

/**
 * A alphabetic proposal based sorter.
 */
public final class AlphabeticSorter extends AbstractProposalSorter {

	private final CompletionProposalComparator fComparator= new CompletionProposalComparator();
	
	public AlphabeticSorter() {
		fComparator.setOrderAlphabetically(true);
	}
	
	public int compare(ICompletionProposal p1, ICompletionProposal p2) {
		return fComparator.compare(p1, p2);
	}

}
