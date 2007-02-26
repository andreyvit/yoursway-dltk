/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.ui.text.completion;

import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.template.completion.SignatureUtil;

/**
 * Proposal info that computes the javadoc lazily when it is queried.
 * 
 */
public final class FieldProposalInfo extends MemberProposalInfo {

	/**
	 * Creates a new proposal info.
	 * 
	 * @param project
	 *            thescriptproject to reference when resolving types
	 * @param proposal
	 *            the proposal to generate information for
	 */
	public FieldProposalInfo(IDLTKProject project, CompletionProposal proposal) {
		super(project, proposal);
	}

	/**
	 * Resolves the member described by the receiver and returns it if found.
	 * Returns <code>null</code> if no corresponding member can be found.
	 * 
	 * @return the resolved member or <code>null</code> if none is found
	 * @throws ModelException
	 *             if accessing thescriptmodel fails
	 */
	protected IMember resolveMember() throws ModelException {
		char[] declarationSignature = fProposal.getDeclarationSignature();
		// for synthetic fields on arrays, declaration signatures may be null
		// TODO remove when https://bugs.eclipse.org/bugs/show_bug.cgi?id=84690
		// gets fixed
		if (declarationSignature == null)
			return null;
		
		String typeName = SignatureUtil.stripSignatureToFQN(String
				.valueOf(declarationSignature));
		IType type = fScriptProject.findType(typeName);
		if (type != null) {
			String name = String.valueOf(fProposal.getName());
			IField field = type.getField(name);
			if (field.exists())
				return field;
		}

		return null;
	}
}
