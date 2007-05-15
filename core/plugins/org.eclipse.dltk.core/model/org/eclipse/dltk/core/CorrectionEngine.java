package org.eclipse.dltk.core;

import org.eclipse.core.resources.IMarker;
import org.eclipse.dltk.internal.core.util.Util;

public class CorrectionEngine {
	public static String[] getProblemArguments(IMarker problemMarker) {
		String argumentsString = problemMarker.getAttribute(IScriptModelMarker.ARGUMENTS, null);
		return Util.getProblemArgumentsFromMarker(argumentsString);
	}
}
