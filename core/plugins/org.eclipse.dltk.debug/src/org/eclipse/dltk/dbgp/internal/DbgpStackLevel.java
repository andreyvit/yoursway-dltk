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

import org.eclipse.dltk.dbgp.IDbgpStackLevel;

public class DbgpStackLevel implements IDbgpStackLevel {

	private final int level;

	private final int lineNumber;

	private final int lineBegin;

	private final int lineEnd;

	private final URI fileUri;

	private final String where;

	public DbgpStackLevel(URI fileUri, String where, int level, int lineNumber,
			int lineBegin, int lineEnd) {
		this.fileUri = fileUri;
		this.level = level;
		this.lineNumber = lineNumber;
		this.lineBegin = lineBegin;
		this.lineEnd = lineEnd;
		this.where = where;
	}

	public String getWhere() {
		return where;
	}

	public int getLevel() {
		return level;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public int getLineBegin() {
		return lineBegin;
	}

	public int getLineEnd() {
		return lineEnd;
	}

	public URI getFileURI() {
		return fileUri;
	}

	public String toString() {
		return "DbgpStackLevel(level: " + level + ", line: " + lineNumber //$NON-NLS-1$ //$NON-NLS-2$
				+ ", begin: " + lineBegin + ", end: " + lineEnd + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileUri == null) ? 0 : fileUri.hashCode());
		result = prime * result + level;
		result = prime * result + lineBegin;
		result = prime * result + lineEnd;
		result = prime * result + lineNumber;
		result = prime * result + ((where == null) ? 0 : where.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DbgpStackLevel)) {
			return false;
		}
		final DbgpStackLevel other = (DbgpStackLevel) obj;
		if (fileUri == null) {
			if (other.fileUri != null) {
				return false;
			}
		} else if (!fileUri.equals(other.fileUri)) {
			return false;
		}
		if (level != other.level) {
			return false;
		}
		if (lineBegin != other.lineBegin) {
			return false;
		}
		if (lineEnd != other.lineEnd) {
			return false;
		}
		if (lineNumber != other.lineNumber) {
			return false;
		}
		if (where == null) {
			if (other.where != null) {
				return false;
			}
		} else if (!where.equals(other.where)) {
			return false;
		}
		return true;
	}
}
