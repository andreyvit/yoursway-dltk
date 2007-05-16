/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal.packets;

import java.io.IOException;
import java.io.OutputStream;

public class DbgpPacketSender {
	private OutputStream output;

	private IDbgpRawLogger logger;

	public DbgpPacketSender(OutputStream output) {
		if (output == null) {
			throw new IllegalArgumentException();
		}

		this.output = output;
	}

	public void sendCommand(String command) throws IOException {
		if (command == null) {
			throw new IllegalArgumentException();
		}

		if (logger != null) {
			logger.log(command);
		}

		output.write(command.getBytes("ASCII"));
		output.write(0);
		output.flush();
	}

	public void setLogger(IDbgpRawLogger logger) {
		this.logger = logger;
	}
}
