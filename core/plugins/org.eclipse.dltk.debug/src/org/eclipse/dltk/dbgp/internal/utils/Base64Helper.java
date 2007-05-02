/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal.utils;

import java.io.IOException;

import org.eclipse.dltk.dbgp.exceptions.DbgpIOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Helper {
	private static final BASE64Encoder encoder = new BASE64Encoder();

	private static final BASE64Decoder decoder = new BASE64Decoder();

	public static String encodeString(String s) {
		return encoder.encode(s.getBytes());
	}

	public static String decodeString(String base64) throws DbgpIOException {
		try {
			return new String(decoder.decodeBuffer(base64));
		} catch (IOException e) {
			throw new DbgpIOException(e);
		}
	}

	public static String encodeBytes(byte[] bytes) {
		return new String(encoder.encode(bytes));
	}

	public static byte[] decodeBytes(String base64) throws DbgpIOException {
		try {
			return decoder.decodeBuffer(base64);
		} catch (IOException e) {
			throw new DbgpIOException(e);
		}
	}
}
