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
package org.eclipse.dltk.debug.core.model;

import java.net.URI;

import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.dltk.debug.internal.core.model.IScriptDebugTargetStreamManager;

public interface IScriptDebugTarget extends IDebugTarget {
	boolean isInitialized();

	void runToLine(URI uri, int lineNumber);

	// Listener
	void addListener(IScriptDebugTargetListener listener);

	void removeListener(IScriptDebugTargetListener listener);

	// Request timeout
	void setRequestTimeout(int timeout);

	int getRequestTimeout();

	// StreamManager
	void setStreamManager(IScriptDebugTargetStreamManager manager);

	IScriptDebugTargetStreamManager getStreamManager();
}
