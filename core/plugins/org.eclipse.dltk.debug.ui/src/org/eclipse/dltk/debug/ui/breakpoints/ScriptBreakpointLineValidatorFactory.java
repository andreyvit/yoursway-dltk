package org.eclipse.dltk.debug.ui.breakpoints;


public class ScriptBreakpointLineValidatorFactory {
	public static final IScriptBreakpointLineValidator NON_EMPTY_VALIDATOR = new IScriptBreakpointLineValidator() {
		public boolean isValid(String line, int number) {
			return line.trim().length() > 0;
		}
	};

	public static IScriptBreakpointLineValidator createNonEmptyNoCommentValidator(
			final String commentPrefix) {
		return new IScriptBreakpointLineValidator() {
			public boolean isValid(String line, int number) {
				final String trimmedLine = line.trim();
				return trimmedLine.length() > 0
						&& trimmedLine.indexOf(commentPrefix) != 0;
			}
		};
	}
}
