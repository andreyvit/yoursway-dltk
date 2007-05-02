/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal;

import org.eclipse.dltk.dbgp.IDbgpNotification;
import org.w3c.dom.Element;

public class DbgpNotification implements IDbgpNotification {
	private Element body;

	private String name;

	public DbgpNotification(String name, Element body) {
		this.body = body;
		this.name = name;
	}

	public Element getBody() {
		return body;
	}

	public String getName() {
		return name;
	}

}
