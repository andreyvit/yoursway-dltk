/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal.packets;

import org.w3c.dom.Element;

public class DbgpStreamPacket extends DbgpPacket {
	private static final String STDERR = "stderr";

	private static final String STDOUT = "stdout";

	private final String type;

	private final String textContent;

	public DbgpStreamPacket(String type, String textContent, Element content) {
		super(content);
		
		if (!STDERR.equalsIgnoreCase(type) && !STDOUT.equalsIgnoreCase(type)) {
			throw new IllegalArgumentException("Invalid type value");
		}

		if (textContent == null) {
			throw new IllegalArgumentException("Content cannot be null");
		}

		this.type = type;
		this.textContent = textContent;
	}

	public boolean isStdout() {
		return STDOUT.equalsIgnoreCase(type);
	}

	public boolean isStderr() {
		return STDERR.equalsIgnoreCase(type);
	}

	public String getTextContent() {
		return textContent;
	}

	public String toString() {
		return "DbgpStreamPacket (Type: " + type + "; Content: " + textContent
				+ ";)";
	}
}
