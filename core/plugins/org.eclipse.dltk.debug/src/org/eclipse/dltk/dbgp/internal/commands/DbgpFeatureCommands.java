/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal.commands;

import org.eclipse.dltk.dbgp.IDbgpFeature;
import org.eclipse.dltk.dbgp.commands.IDbgpFeatureCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.dbgp.internal.DbgpRequest;
import org.eclipse.dltk.dbgp.internal.utils.DbgpXmlEntityParser;
import org.eclipse.dltk.dbgp.internal.utils.DbgpXmlParser;

public class DbgpFeatureCommands extends DbgpBaseCommands implements
		IDbgpFeatureCommands {

	private static final String FEATURE_SET_COMMAND = "feature_set"; //$NON-NLS-1$

	private static final String FEATURE_GET_COMMAND = "feature_get"; //$NON-NLS-1$

	public DbgpFeatureCommands(IDbgpCommunicator communicator) {
		super(communicator);
	}

	public IDbgpFeature getFeature(String featureName) throws DbgpException {
		DbgpRequest request = createRequest(FEATURE_GET_COMMAND);
		request.addOption("-n", featureName); //$NON-NLS-1$
		return DbgpXmlEntityParser.parseFeature(communicate(request));
	}

	public boolean setFeature(String featureName, String featureValue)
			throws DbgpException {
		DbgpRequest request = createRequest(FEATURE_SET_COMMAND);
		request.addOption("-n", featureName); //$NON-NLS-1$
		request.addOption("-v", featureValue); //$NON-NLS-1$
		return DbgpXmlParser.parseSuccess(communicate(request));
	}
}
