package org.eclipse.dltk.dbgp.internal;

import java.net.URI;

import org.eclipse.dltk.dbgp.IDbgpStackLevel;

public class DbgpStackLevel implements IDbgpStackLevel {

	private int level;

	private int lineNumber;

	private int lineBegin;

	private int lineEnd;

	private URI fileUri;

	private int type;

	public DbgpStackLevel(URI fileUri, int level, int lineNumber,
			int lineBegin, int lineEnd) {
		this.fileUri = fileUri;
		this.level = level;
		this.lineNumber = lineNumber;
		this.lineBegin = lineBegin;
		this.lineEnd = lineEnd;
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

	public int getType() {
		return type;
	}

	public URI getFileURI() {
		return fileUri;
	}

	public String toString() {
		return "DbgpStackLevel(level: " + level + ", line: " + lineNumber
				+ ", begin: " + lineBegin + ", end: " + lineEnd + ")";
	}

	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((fileUri == null) ? 0 : fileUri.hashCode());
		result = prime * result + level;
		result = prime * result + lineBegin;
		result = prime * result + lineEnd;
		result = prime * result + lineNumber;
		result = prime * result + type;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final DbgpStackLevel other = (DbgpStackLevel) obj;
		if (fileUri == null) {
			if (other.fileUri != null)
				return false;
		} else if (!fileUri.equals(other.fileUri))
			return false;
		if (level != other.level)
			return false;
		if (lineBegin != other.lineBegin)
			return false;
		if (lineEnd != other.lineEnd)
			return false;
		if (lineNumber != other.lineNumber)
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}
