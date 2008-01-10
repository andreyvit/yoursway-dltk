package org.eclipse.dltk.tcl.core;

public class TclKeywords implements ITclKeywords {
	private static String[] fgKeywords = { "after", "append", "array",
			"binary", "break", "catch", "cd", "clock", "close", "concat",
			"console", "continue", "error", "eof", "eval", "exec", "exit",
			"expr", "fblocked", "fconfigure", "fcopy", "file", "fileevent",
			"flush", "for", "foreach", "format", "gets", "glob", "history",
			"if", "else", "elseif", "incr", "info", "interp", "join",
			"lappend", "lindex", "linsert", "list", "llength", "load",
			"lrange", "lreplace", "lsearch", "lsort", "namespace", "open",
			"package", "pid", "proc", "puts", "pwd", "read", "regexp",
			"regsub", "rename", "scan", "seek", "set", "socket", "source",
			"split", "string", "subst", "switch", "tell", "time", "trace",
			"unknown", "unset", "vwait", "while", "variable", "upvar",
			"global", "return", "require" };

	private static String[] fgExecKeywords = { "after", "append", "array",
			"binary", "break", "catch", "cd", "clock", "close", "concat",
			"console", "continue", "error", "eof", "eval", "exec", "exit",
			"expr", "fblocked", "fconfigure", "fcopy", "file", "fileevent",
			"flush", "for", "foreach", "format", "gets", "glob", "global",
			"history", "if", "else", "elseif", "incr", "info", "interp",
			"join", "lappend", "lindex", "linsert", "list", "llength", "load",
			"lrange", "lreplace", "lsearch", "lsort", "open", "pid", "puts",
			"pwd", "read", "regexp", "regsub", "rename", "scan", "seek", "set",
			"socket", "source", "split", "string", "subst", "switch", "tell",
			"time", "trace", "unknown", "unset", "uplevel", "vwait", "while",
			"return" };

	private static String[] fgFunctionKeywords = { "after", "append", "array",
			"binary", "break", "catch", "cd", "clock", "close", "concat",
			"console", "continue", "error", "eof", "eval", "exec", "exit",
			"expr", "fblocked", "fconfigure", "fcopy", "file", "fileevent",
			"flush", "for", "foreach", "format", "gets", "glob", "global",
			"history", "if", "else", "elseif", "incr", "info", "interp",
			"join", "lappend", "lindex", "linsert", "list", "llength", "load",
			"lrange", "lreplace", "lsearch", "lsort", "open", "pid", "puts",
			"pwd", "read", "regexp", "regsub", "rename", "return", "scan",
			"seek", "set", "socket", "source", "split", "string", "subst",
			"switch", "tell", "time", "trace", "unknown", "unset", "uplevel",
			"upvar", "variable", "vwait", "while" };

	private static String[] fgModuleKeywords = { "after", "append", "array",
			"binary", "break", "catch", "cd", "clock", "close", "concat",
			"console", "continue", "error", "eof", "eval", "exec", "exit",
			"expr", "fblocked", "fconfigure", "fcopy", "file", "fileevent",
			"flush", "for", "foreach", "format", "gets", "glob", "history",
			"if", "else", "elseif", "incr", "info", "interp", "join",
			"lappend", "lindex", "linsert", "list", "llength", "load",
			"lrange", "lreplace", "lsearch", "lsort", "namespace",
			"namespace eval",
			"namespace children",
			"namespace code",
			"namespace current",
			"namespace delete",
			"namespace export",
			"namespace import",
			"namespace forget",
			"namespace inscope",
			"namespace parent",
			"namespace qualifiers",
			"namespace tail",
			"namespace which",
			"open", "package", "package provide",
			"package require", "pid", "proc", "puts", "pwd", "read", "regexp",
			"regsub", "rename", "scan", "seek", "set", "socket", "source",
			"split", "string", "subst", "switch", "tell", "time", "trace",
			"unknown", "unset", "vwait", "while" };

	private static String[] fgNamespaceKeywords = { "after", "append", "array",
			"binary", "break", "catch", "cd", "clock", "close", "concat",
			"console", "continue", "error", "eof", "eval", "exec", "exit",
			"expr", "fblocked", "fconfigure", "fcopy", "file", "fileevent",
			"flush", "for", "foreach", "format", "gets", "glob", "global",
			"history", "if", "else", "elseif", "incr", "info", "interp",
			"join", "lappend", "lindex", "linsert", "list", "llength", "load",
			"lrange", "lreplace", "lsearch", "lsort", "namespace", "open",
			"pid", "proc", "puts", "pwd", "read", "regexp", "regsub", "rename",
			"scan", "seek", "set", "socket", "source", "split", "string",
			"subst", "switch", "tell", "time", "trace", "unknown", "unset",
			"uplevel", "upvar", "variable", "vwait", "while" };

	public String[] getKeywords() {
		return fgKeywords;
	}

	public String[] getKeywords(int type) {
		if (type == MODULE) {
			return fgModuleKeywords;
		} else if (type == FUNCTION) {
			return fgFunctionKeywords;
		} else if (type == NAMESPACE) {
			return fgNamespaceKeywords;
		} else if (type == EXEC_EXPRESSION) {
			return fgExecKeywords;
		}
		return getKeywords();
	}
	public static String[] append(String prefix, String[] a, String[] b) {
		int len = 0;
		if( a != null ) {
			len = a.length;
		}
		String[] ns = new String[len + b.length];
		if( a != null ) {
			System.arraycopy(a, 0, ns, 0, len);
		}
		for (int i = 0; i < b.length; i++) {
			ns[len + i] = prefix + b[i];
		}
		return ns;
	}
	public static String[] append(String[] a, String[] b) {
		if( b == null ) {
			return a;
		}
		String[] ns = new String[a.length + b.length];
		System.arraycopy(a, 0, ns, 0, a.length);
		System.arraycopy(b, 0, ns, a.length, b.length);
		return ns;
	}
}
