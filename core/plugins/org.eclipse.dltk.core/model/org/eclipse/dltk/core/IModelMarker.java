package org.eclipse.dltk.core;


public interface IModelMarker {
	
	/**
	 * Script runtime problem marker type (value
	 * <code>"org.eclipse.dltk.core.runtime_problem"</code>). This can be used to
	 * recognize those markers in the workspace that flag problems detected by
	 * the script tooling during compilation.
	 */
	String SCRIPT_RUNTIME_PROBLEM_MARKER = DLTKCore.PLUGIN_ID + ".runtime_problem"; //$NON-NLS-1$

	/**
	 * Build path problem marker type (value
	 * <code>"org.eclipse.dltk.core.buildpath_problem"</code>). This can be
	 * used to recognize those markers in the workspace that flag problems
	 * detected by the Script tooling during buildpath setting.
	 */
	public static final String BUILDPATH_PROBLEM_MARKER = DLTKCore.PLUGIN_ID
			+ ".buildpath_problem"; //$NON-NLS-1$

	/**
	 * Arguments marker attribute (value <code>"arguments"</code>). Arguments
	 * are concatenated into one String, prefixed with an argument count
	 * (followed with colon separator) and separated with '#' characters. For
	 * example: { "foo", "bar" } is encoded as "2:foo#bar", { } is encoded as
	 * "0: "
	 * 
	 *
	 */
	public static final String ARGUMENTS = "arguments";

	/**
	 * Buildpath file format marker attribute (value
	 * <code>"buildpathFileFormat"</code>). Used only on buildpath problem
	 * markers. The value of this attribute is either "true" or "false".
	 * 
	 *
	 */
	public static final String BUILDPATH_FILE_FORMAT = "buildpathFileFormat";

	/**
	 * Cycle detected marker attribute (value <code>"cycleDetected"</code>).
	 * Used only on buildpath problem markers. The value of this attribute is
	 * either "true" or "false".
	 */
	public static final String CYCLE_DETECTED = "cycleDetected";

	/**
	 * Id marker attribute (value <code>"id"</code>).
	 */
	public static final String ID = "id";

}