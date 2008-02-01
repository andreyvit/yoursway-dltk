package org.eclipse.dltk.tcl.core;

import java.io.IOException;
import java.io.Reader;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.dltk.core.ScriptContentDescriber;

public class TclContentDescriber extends ScriptContentDescriber {
	protected static Pattern[] header_patterns = {
			Pattern.compile("#!\\s*.*tclsh", Pattern.MULTILINE),
			Pattern.compile("#!\\s*/usr/bin/tclsh", Pattern.MULTILINE),
			Pattern.compile("#!\\s*/usr/bin/expect", Pattern.MULTILINE),
			Pattern.compile("#!\\s*/usr/bin/wish", Pattern.MULTILINE),
			Pattern
					.compile(
							"# ;;; Local Variables?: \\*\\*\\*\\s*\r*\n# ;;; mode: t|Tcl \\*\\*\\*\\s*\r*\n# ;;; End: \\*\\*\\*",
							Pattern.MULTILINE),
			Pattern
					.compile(
							"#!\\s*/bin/(ba|tc)?sh\\s*\r*\n#.*\\\\s*\r*\nexec .*tclsh .*",
							Pattern.MULTILINE),
			Pattern
					.compile(
							"#!\\s*/bin/(ba|tc)?sh\\s*\r*\n#.*\\\\s*\r*\nexec .*expect .*",
							Pattern.MULTILINE),
			Pattern
					.compile(
							"#!\\s*/bin/(ba|tc)?sh\\s*\r*\n#.*\\\\s*\r*\nexec .*wish.* .*",
							Pattern.MULTILINE),
			Pattern.compile(
					"#!\\s*/bin/(ba|tc)?sh\\s*\r*\n\\s*exec .*wish.* .*",
					Pattern.MULTILINE),
			Pattern.compile(
					"#!\\s*/bin/(ba|tc)?sh\\s*\r*\n\\s*exec .*tclsh.* .*",
					Pattern.MULTILINE),
			Pattern.compile(
					"#!\\s*/bin/(ba|tc)?sh\\s*\r*\n\\s*exec .*expect.* .*",
					Pattern.MULTILINE) };

	public int describe(Reader contents, IContentDescription description)
			throws IOException {
		if (checkPatterns(contents, header_patterns, null)) {
			description.setProperty(DLTK_VALID, TRUE);
			return VALID;
		}
		return INDETERMINATE;
	}
}
