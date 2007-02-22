package org.eclipse.dltk.ui.text.blocks;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public interface MultiMap {
	
	public void put(Object key, Object value);
	
	public void putAll(Object key, Collection values);
	
	public Set get(Object key);
	
	public Iterator iterator();
	
	public Set keySet();
	
}
