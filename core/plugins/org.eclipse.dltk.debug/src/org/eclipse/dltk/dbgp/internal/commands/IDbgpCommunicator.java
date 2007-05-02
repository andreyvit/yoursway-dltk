/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal.commands;

import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.DbgpRequest;
import org.w3c.dom.Element;

public interface IDbgpCommunicator {
	Element communicate(DbgpRequest request) throws DbgpException;
}
