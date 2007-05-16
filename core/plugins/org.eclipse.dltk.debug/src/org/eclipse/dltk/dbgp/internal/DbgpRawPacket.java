/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.utils.DbgpXmlParser;
import org.w3c.dom.Document;

public class DbgpRawPacket {
	

	protected static int readPacketSize(InputStream input) throws IOException {
		StringBuffer sb = new StringBuffer();

		int b = -1;
		while (true) {
			b = input.read();
			
			if (b == -1) {
				throw new IOException();
			}

			if (b == 0) {
				break;
			}
			
			sb.append((char) b);
		}

		return Integer.parseInt(sb.toString());
	}

	protected static String readPacketXml(InputStream input, int size)
			throws IOException {
		byte[] bytes = new byte[size];

		int offset = 0;
		int n = -1;
		while ((n = input.read(bytes, offset, size - offset)) != -1
				&& (offset < size)) {
			offset += n;
		}

		if (offset != size) {
			throw new IOException("Can't read packet body");
		}

		if (input.read() != 0) {
			throw new IOException("No termination '0' byte");
		}

		return new String(bytes, "ASCII");
	}

	public static DbgpRawPacket readPacket(InputStream input)
			throws IOException {
		int size = readPacketSize(input);
		String xml = readPacketXml(input, size);
		return new DbgpRawPacket(size, xml);
	}

	private int size;

	private String xml;

	protected DbgpRawPacket(int size, String xml) {
		this.size = size;
		this.xml = xml;
	}

	public int getSize() {
		return size;
	}

	public String getXml() {
		return xml;
	}

	public Document getParsedXml() throws DbgpException {
		return DbgpXmlParser.parseXml(xml);
	}

	public String toString() {
		return "DbgpPacket (" + size + " bytes) " + xml;
	}
}
