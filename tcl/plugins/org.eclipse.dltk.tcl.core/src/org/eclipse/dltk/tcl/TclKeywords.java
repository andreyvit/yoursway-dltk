package org.eclipse.dltk.tcl;

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
			"namespace eval", "open", "package", "package provide",
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
		return null;
	}

}
