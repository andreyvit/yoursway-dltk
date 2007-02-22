package org.eclipse.dltk.ui.text.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.ui.CodeFormatterConstants;

public abstract class TabStyle {
	
	private final String name;

	public TabStyle(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}
	
	public static final TabStyle TAB = new TabStyle(CodeFormatterConstants.TAB) {
		
	};
	
	public static final TabStyle SPACES = new TabStyle(CodeFormatterConstants.SPACE) {
		
	};
	
	public static final TabStyle MIXED = new TabStyle(CodeFormatterConstants.MIXED) {
		
	};
	
	private static final Map byName = new HashMap();
	
	static {
		byName.put(TAB.getName(), TAB);
		byName.put(SPACES.getName(), SPACES);
		byName.put(MIXED.getName(), MIXED);
	}
	
	public static TabStyle forName(String name) {
		return (TabStyle) byName.get(name);
	}
	
	public static TabStyle forName(String name, TabStyle deflt) {
		TabStyle result = forName(name);
		if (result == null)
			return deflt;
		else
			return result;
	}
	
}
