package org.eclipse.dltk.core;

import java.util.Map;

public interface ICalleeProcessor {
	/**
	 * Return list of AST reference statements.
	 * @return
	 */
	Map doOperation();
}
