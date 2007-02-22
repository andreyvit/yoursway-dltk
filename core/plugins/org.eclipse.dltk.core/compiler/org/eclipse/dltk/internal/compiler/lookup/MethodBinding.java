/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.compiler.lookup;

/*
 * This class is one big TODO&FIXME
 */
public class MethodBinding extends Binding implements TypeConstants {
	
	public char[] selector;
	public int modifiers;
	public TypeBinding returnType;
	public TypeBinding[] parameters;
	public ReferenceBinding declarer;
		
	public long tagBits;
	
protected MethodBinding() {
	// for creating problem or synthetic method
}

public MethodBinding(int modifiers, char[] selector, 
		TypeBinding returnType, TypeBinding[] parameters, ReferenceBinding declaringClass) {
	this.modifiers = modifiers;
	this.selector = selector;
	this.returnType = returnType;
	this.parameters = (parameters == null || parameters.length == 0) ? Binding.NO_PARAMETERS : parameters;
	this.declarer = declaringClass;
}


public int kind() {
	return Binding.METHOD;
}

/* Answer true if the receiver is a constructor
*/
public final boolean isConstructor() {	
	return selector == TypeConstants.INIT;
}


public char[] readableName() {
	StringBuffer buffer = new StringBuffer(parameters.length + 1 * 20);
	if (isConstructor())
		buffer.append(declarer.sourceName());
	else
		buffer.append(selector);
	buffer.append('(');
	if (parameters != Binding.NO_PARAMETERS) {
		for (int i = 0, length = parameters.length; i < length; i++) {
			if (i > 0)
				buffer.append(", "); //$NON-NLS-1$
			buffer.append(parameters[i].readableName());
		}
	}
	buffer.append(')');
	return buffer.toString().toCharArray();
}

/* Answer true if the argument types & the receiver's parameters are equal
*/
public final boolean areParametersEqual(MethodBinding method) {
	TypeBinding[] args = method.parameters;
	if (parameters == args)
		return true;

	int length = parameters.length;
	if (length != args.length)
		return false;
	
	for (int i = 0; i < length; i++)
		if (parameters[i] != args[i])
			return false;
	return true;
}

}
