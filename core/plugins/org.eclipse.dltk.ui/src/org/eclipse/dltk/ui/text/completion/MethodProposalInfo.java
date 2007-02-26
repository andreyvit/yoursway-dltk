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
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.Signature;
import org.eclipse.dltk.internal.corext.template.completion.SignatureUtil;

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
	 * Resolves the member described by the receiver and returns it if found.
	 * Returns <code>null</code> if no corresponding member can be found.
	 * 
	 * @return the resolved member or <code>null</code> if none is found
	 * @throws ModelException
	 *             if accessing thescriptmodel fails
	 */
	protected IMember resolveMember() throws ModelException {
		char[] declarationSignature = fProposal.getDeclarationSignature();
		if (declarationSignature != null) {
			String typeName = SignatureUtil.stripSignatureToFQN(String
					.valueOf(declarationSignature));
			IType type = fScriptProject.findType(typeName);
			if (type != null) {
				String name = String.valueOf(fProposal.getName());
				String[] parameters = Signature.getParameterTypes(String
						.valueOf(SignatureUtil.fix83600(fProposal
								.getSignature())));
				for (int i = 0; i < parameters.length; i++) {
					parameters[i] = SignatureUtil.getLowerBound(parameters[i]);
				}
				boolean isConstructor = fProposal.isConstructor();
				return findMethod(name, parameters, isConstructor, type);
			}
		}
		return null;
	}

	/* adapted from DltkModelUtil */
	/**
	 * Finds a method in a type. This searches for a method with the same name
	 * and signature. Parameter types are only compared by the simple name, no
	 * resolving for the fully qualified type name is done. Constructors are
	 * only compared by parameters, not the name.
	 * 
	 * @param name
	 *            The name of the method to find
	 * @param paramTypes
	 *            The type signatures of the parameters e.g.
	 *            <code>{"QString;","I"}</code>
	 * @param isConstructor
	 *            If the method is a constructor
	 * @return The first found method or <code>null</code>, if nothing found
	 */
	private IMethod findMethod(String name, String[] paramTypes,
			boolean isConstructor, IType type) throws ModelException {
		return findMethod(name, paramTypes, isConstructor, type.getMethods());
	}

	/**
	 * Finds a method by name. This searches for a method with a name and
	 * signature. Parameter types are only compared by the simple name, no
	 * resolving for the fully qualified type name is done. Constructors are
	 * only compared by parameters, not the name.
	 * 
	 * @param name
	 *            The name of the method to find
	 * @param paramTypes
	 *            The type signatures of the parameters e.g.
	 *            <code>{"QString;","I"}</code>
	 * @param isConstructor
	 *            If the method is a constructor
	 * @param methods
	 *            The methods to search in
	 * @param typeVariables
	 *            a map from type variables to concretely used types
	 * @return The found method or <code>null</code>, if nothing found
	 */
	private IMethod findMethod(String name, String[] paramTypes,
			boolean isConstructor, IMethod[] methods) throws ModelException {
		for (int i = methods.length - 1; i >= 0; i--) {
			if (isSameMethodSignature(name, paramTypes, isConstructor,
					methods[i])) {
				return methods[i];
			}
		}
		return fFallbackMatch;
	}

	/**
	 * Tests if a method equals to the given signature. Parameter types are only
	 * compared by the simple name, no resolving for the fully qualified type
	 * name is done. Constructors are only compared by parameters, not the name.
	 * 
	 * @param name
	 *            Name of the method
	 * @param paramTypes
	 *            The type signatures of the parameters e.g.
	 *            <code>{"QString;","I"}</code>
	 * @param isConstructor
	 *            Specifies if the method is a constructor
	 * @param method
	 *            the method to be compared with this info's method
	 * @param typeVariables
	 *            a map from type variables to types
	 * @return Returns <code>true</code> if the method has the given name and
	 *         parameter types and constructor state.
	 */
	private boolean isSameMethodSignature(String name, String[] paramTypes,
			boolean isConstructor, IMethod method) throws ModelException {
		if (isConstructor || name.equals(method.getElementName())) {
			if (isConstructor == method.isConstructor()) {
				String[] otherParams = method.getParameters();
				if (paramTypes.length == otherParams.length) {
					fFallbackMatch = method;
					String signature = method.getSignature();
					String[] otherParamsFromSignature = Signature
							.getParameterTypes(signature); // types
					// are
					// resolved
					// /
					// upper-bounded
					// no need to check method type variables since these are
					// not yet bound when proposing a method
					for (int i = 0; i < paramTypes.length; i++) {
						// String ourParamName=
						// computeSimpleTypeName(paramTypes[i], typeVariables);
						// String otherParamName1=
						// computeSimpleTypeName(otherParams[i], typeVariables);
						// String otherParamName2=
						// computeSimpleTypeName(otherParamsFromSignature[i],
						// typeVariables);
						if (DLTKCore.DEBUG) {
							System.out.println("TODO: Check this comparison:"
									+ paramTypes[i] + "==" + otherParams[i]);
						}
						if (!paramTypes[i].equals(otherParams[i])) {
							return false;
						}
					}
					return true;
				}
			}
		}
		return false;
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
}
