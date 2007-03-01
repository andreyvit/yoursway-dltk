/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.ui.text.completion;

import java.util.Map;

import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.Signature;

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

	/**
	 * Returns the simple erased name for a given type signature, possibly
	 * replacing type variables.
	 * 
	 * @param signature
	 *            the type signature
	 * @param typeVariables
	 *            the Map&lt;SimpleName, VariableName>
	 * @return the simple erased name for signature
	 */
	private String computeSimpleTypeName(String signature, Map typeVariables) {
		// method equality uses erased types
		String erasure = Signature.getTypeErasure(signature);
		erasure = erasure.replaceAll("/", "."); //$NON-NLS-1$//$NON-NLS-2$
		String simpleName = Signature
				.getSimpleName(Signature.toString(erasure));
		char[] typeVar = (char[]) typeVariables.get(simpleName);
		if (typeVar != null)
			simpleName = String.valueOf(Signature
					.getSignatureSimpleName(typeVar));
		return simpleName;
	}

	protected IMember resolveMember() throws ModelException {
		throw new Error("Unimplemented method");		
	}
}
