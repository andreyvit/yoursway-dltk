package org.eclipse.dltk.dbgp.internal;

import org.eclipse.dltk.dbgp.IDbgpFeature;

public class DbgpFeature implements IDbgpFeature {
	private boolean supported;

	private String name;

	private String value;

	public DbgpFeature(boolean supported, String name, String value) {
		this.supported = supported;
		this.name = name;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public boolean isSupported() {
		return supported;
	}

	public String toString() {
		return "DbgpFeature (name: " + name + "; value: " + value
				+ "; supported: " + supported + ")";
	}
}
