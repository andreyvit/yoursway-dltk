package org.eclipse.dltk.tcl.internal.core.codeassist;

public class TclVisibilityUtils {
	public static boolean isPrivate(String name) {
		if( name.indexOf("::") != -1) {
			String []split = name.split("::");
			name = split[split.length-1];
		}
		if (name.length() > 0) {
			char c = name.charAt(0);
			if (Character.isUpperCase(c) || c == '_') {
				return true;
			}
		}
		return false;
	}
}
