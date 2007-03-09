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
package org.eclipse.dltk.internal.corext.template.completion;

import org.eclipse.dltk.core.Signature;

/**
 * Utilities for Signature operations.
 * 
 * @see Signature
	 *
 */
public final class SignatureUtil {
	
	/**
	 * Returns the fully qualified type name of the given signature, with any
	 * type parameters and arrays erased.
	 * 
	 * @param signature the signature
	 * @return the fully qualified type name of the signature
	 */
	public static String stripSignatureToFQN(String signature) throws IllegalArgumentException {
		signature= Signature.getTypeErasure(signature);
		signature= Signature.getElementType(signature);
		return Signature.toString(signature);
	}
}
