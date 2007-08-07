package org.eclipse.dltk.xotcl.internal.core;

import org.eclipse.dltk.tcl.core.ITclKeywords;
import org.eclipse.dltk.tcl.core.TclKeywords;

public class XOTclKeywords implements ITclKeywords {
	private static String[] XOTclKeywords = { "Class", "instproc", "@", "my", "instvar", "Object", "self", "next" };
	
	public static final String XOTclCommandClassArgs[] = new String[] { "_unknown", "allinstances", "alloc", "create", "info", "instdestroy", "instfilter", "instfilterguard",
        "instforward", "instinvar", "instmixin", "instparametercmd", "instproc", "new", "parameter", "parameterclass", "recreate",
        "superclass", "unknown" };
	public static final String XOTclCommandObjectArgs[] = new String[] {
        "abstract", "append", "array", "autoname", "check", "class", "cleanup", "configure", "contains", "copy", "destroy",
        "eval", "exists", "extractConfigureArg", "filter", "filterguard", "filtersearch", "forward", "getExitHandler", "hasclass",
        "incr", "info", "instvar", "invar", "isclass", "ismetaclass", "ismixin", "isobject", "istype", "lappend", "mixin", "move", "noinit",
        "parametercmd", "proc", "procsearch", "requireNamespace", "set", "setExitHandler", "subst", "trace", "unset", "uplevel",
        "upvar", "volatile", "vwait", "create"
	};
	private static String[][] all = new String[END_INDEX][];
	static {
		TclKeywords kw = new TclKeywords();
		all[ALL] = append(kw.getKeywords());
		all[ALL] = appendXOTcl(all[ALL]);
		all[ALL] = appendXOTclOther(all[ALL]);
		for( int i = START_INDEX; i < END_INDEX; ++i ) {
			all[i] = append(kw.getKeywords(i));
			all[i] = appendXOTcl(all[i]);
		}
	}
	private static String[] appendXOTcl(String[] kw) {
		return append("Object ", append( "Class ", kw, XOTclCommandClassArgs ), XOTclCommandObjectArgs );
	}
	private static String[] appendXOTclOther(String[] kw) {
		return append("", append( "", kw, XOTclCommandClassArgs ), XOTclCommandObjectArgs );
	}
	private static String[] append(String[] s) {
		if( s == null ) {
			return XOTclKeywords;
		}
		String[] ns = new String[s.length + XOTclKeywords.length];
		System.arraycopy(s, 0, ns, 0, s.length);
		System.arraycopy(XOTclKeywords, 0, ns, s.length, XOTclKeywords.length);
		return ns;
	}
	private static String[] append(String prefix, String[] a, String[] b) {
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

	public String[] getKeywords() {
		return all[ALL];
	}

	public String[] getKeywords(int type) {
		if( type >= 0 && type < all.length) {
			return all[type];
		}
		return null;
	}
}
