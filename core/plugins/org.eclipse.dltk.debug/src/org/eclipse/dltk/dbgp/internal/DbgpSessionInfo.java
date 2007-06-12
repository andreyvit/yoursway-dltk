/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal;

import java.net.URI;

import org.eclipse.dltk.dbgp.IDbgpSessionInfo;

public class DbgpSessionInfo implements IDbgpSessionInfo {
	private final String appId;

	private final String ideKey;

	private final String session;

	private final String threadId;

	private final String parentId;

	private final String language;

	private final URI fileUri;

	public DbgpSessionInfo(String appId, String ideKey, String session,
			String threadId, String parentId, String language, URI fileUri) {
		super();
		this.appId = appId;
		this.ideKey = ideKey;
		this.session = session;
		this.threadId = threadId;
		this.parentId = parentId;
		this.language = language;
		this.fileUri = fileUri;
	}

	public String getApplicationId() {
		return appId;
	}

	public URI getFileUri() {
		return fileUri;
	}

	public String getIdeKey() {
		return ideKey;
	}

	public String getLanguage() {
		return language;
	}

	public String getParentAppId() {
		return parentId;
	}

	public String getSession() {
		return session;
	}

	public String getThreadId() {
		return threadId;
	}

}
