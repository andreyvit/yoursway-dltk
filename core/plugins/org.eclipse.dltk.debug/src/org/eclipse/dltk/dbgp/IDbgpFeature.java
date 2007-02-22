package org.eclipse.dltk.dbgp;

public interface IDbgpFeature {
	final String ZERO_VALUE = "0";

	final String ONE_VALUE = "1";

	boolean isSupported();

	String getName();

	String getValue();
}
