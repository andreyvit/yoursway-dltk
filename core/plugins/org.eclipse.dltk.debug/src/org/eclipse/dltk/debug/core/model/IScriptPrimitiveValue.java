/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.core.model;

/**
 * A primitive value on a Script debug target.
 * <p>
 * Clients are not intended to implement this interface.
 * </p>
 * 
 * 
 */
public interface IScriptPrimitiveValue extends IScriptValue {

	/**
	 * Returns this value as a boolean.
	 * 
	 * @return this value as a boolean
	 */
	public boolean getBooleanValue();

	/**
	 * Returns this value as a byte
	 * 
	 * @return this value as a byte
	 */
	public byte getByteValue();

	/**
	 * Returns this value as a char
	 * 
	 * @return this value as a char
	 */
	public char getCharValue();

	/**
	 * Returns this value as a double
	 * 
	 * @return this value as a double
	 */
	public double getDoubleValue();

	/**
	 * Returns this value as a float
	 * 
	 * @return this value as a float
	 */
	public float getFloatValue();

	/**
	 * Returns this value as an int
	 * 
	 * @return this value as an int
	 */
	public int getIntValue();

	/**
	 * Returns this value as a long
	 * 
	 * @return this value as a long
	 */
	public long getLongValue();

	/**
	 * Returns this value as a short
	 * 
	 * @return this value as a short
	 */
	public short getShortValue();
}
