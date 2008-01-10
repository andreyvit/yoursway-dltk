package org.eclipse.dltk.xotcl.internal.core;

import org.eclipse.dltk.tcl.core.ITclKeywords;
import org.eclipse.dltk.tcl.core.TclKeywords;

public class XOTclKeywords implements ITclKeywords {
	private static String[] XOTclKeywords = { "Class", "instproc", "@", "my",
			"instvar", "Object", "self", "next" };

	public static final String XOTclCommandClassArgs[] = new String[] {
			"_unknown", "allinstances", "alloc", "create", "info",
			"instdestroy", "instfilter", "instfilterguard", "instforward",
			"instinvar", "instmixin", "instparametercmd", "instproc", "new",
			"parameter", "parameterclass", "recreate", "superclass", "unknown" };
	public static final String XOTclCommandObjectArgs[] = new String[] {
			"abstract", "append", "array", "autoname", "check", "class",
			"cleanup", "configure", "contains", "copy", "destroy", "eval",
			"exists", "extractConfigureArg", "filter", "filterguard",
			"filtersearch", "forward", "getExitHandler", "hasclass", "incr",
			"info", "instvar", "invar", "isclass", "ismetaclass", "ismixin",
			"isobject", "istype", "lappend", "mixin", "move", "noinit",
			"parametercmd", "proc", "procsearch", "requireNamespace", "set",
			"setExitHandler", "subst", "trace", "unset", "uplevel", "upvar",
			"volatile", "vwait", "create" };
	private static String[][] all = new String[END_INDEX][];
	static {
		all[ALL] = XOTclKeywords;
		all[ALL] = appendXOTcl(all[ALL]);
		all[ALL] = appendXOTclOther(all[ALL]);
		for (int i = START_INDEX; i < END_INDEX; ++i) {
			all[i] = XOTclKeywords;
			all[i] = appendXOTcl(all[i]);
		}
	}

	private static String[] appendXOTcl(String[] kw) {
		return TclKeywords.append("Object ", TclKeywords.append("Class ", kw,
				XOTclCommandClassArgs), XOTclCommandObjectArgs);
	}

	private static String[] appendXOTclOther(String[] kw) {
		return TclKeywords.append("", TclKeywords.append("", kw,
				XOTclCommandClassArgs), XOTclCommandObjectArgs);
	}

	public String[] getKeywords() {
		return all[ALL];
	}

	public String[] getKeywords(int type) {
		if (type >= 0 && type < all.length) {
			return all[type];
		}
		return null;
	}
}
