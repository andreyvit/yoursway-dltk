/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.ui.text.completion;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ModelException;

/**
 * Proposal info that computes the javadoc lazily when it is queried.
 * 
 */
public abstract class MemberProposalInfo extends ProposalInfo {
	// configuration
	protected final IDLTKProject fScriptProject;
	protected final CompletionProposal fProposal;

	// cache filled lazily
	private boolean fModelElementResolved = false;

	/**
	 * Creates a new proposal info.
	 * 
	 * @param project
	 *            the script project to reference when resolving types
	 * @param proposal
	 *            the proposal to generate information for
	 */
	public MemberProposalInfo(IDLTKProject project, CompletionProposal proposal) {
		Assert.isNotNull(project);
		Assert.isNotNull(proposal);
		fScriptProject = project;
		fProposal = proposal;
	}

	/**
	 * Returns the script element that this computer corresponds to, possibly
	 * <code>null</code>.
	 * 
	 * @return the script element that this computer corresponds to, possibly
	 *         <code>null</code>
	 * @throws ModelException
	 */
	public IModelElement getModelElement() throws ModelException {
		if (!fModelElementResolved) {
			fModelElementResolved = true;
			fElement = resolveMember();
		}
		return fElement;
	}

	/**
	 * Resolves the member described by the receiver and returns it if found.
	 * Returns <code>null</code> if no corresponding member can be found.
	 * 
	 * @return the resolved member or <code>null</code> if none is found
	 * @throws ModelException
	 *             if accessing the script model fails
	 */
	protected abstract IMember resolveMember() throws ModelException;

}
