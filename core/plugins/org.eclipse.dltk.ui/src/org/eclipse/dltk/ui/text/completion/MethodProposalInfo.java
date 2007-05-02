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
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.ModelException;

/**
 * Proposal info that computes the javadoc lazily when it is queried.
 */
public final class MethodProposalInfo extends MemberProposalInfo {
	/**
	 * Fallback in case we can't match a generic method. The fall back is only
	 * based on method name and number of parameters.
	 */
	private IMethod fFallbackMatch;

	/**
	 * Creates a new proposal info.
	 * 
	 * @param project
	 *            thescriptproject to reference when resolving types
	 * @param proposal
	 *            the proposal to generate information for
	 */
	public MethodProposalInfo(IDLTKProject project, CompletionProposal proposal) {
		super(project, proposal);
	}

	protected IMember resolveMember() throws ModelException {
		throw new Error("Unimplemented method");		
	}
}
