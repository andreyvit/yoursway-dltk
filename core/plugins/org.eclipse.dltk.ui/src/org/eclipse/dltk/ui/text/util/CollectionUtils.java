package org.eclipse.dltk.ui.text.util;

import java.util.ArrayList;

public class CollectionUtils {

	public static void removeElementsFromIndexToEnd(ArrayList list, int index) {
		for (int i = list.size() - 1; i >= index; i--)
			list.remove(i);
	}
	
}
