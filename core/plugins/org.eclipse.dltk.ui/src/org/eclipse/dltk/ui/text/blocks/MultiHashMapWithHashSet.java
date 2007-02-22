package org.eclipse.dltk.ui.text.blocks;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MultiHashMapWithHashSet implements MultiMap {

	private Map map = new HashMap();

	public Set get(Object key) {
		return (Set) map.get(key);
	}

	public void put(Object key, Object value) {
		lookupCollectionByKey(key).add(value);
	}

	private Collection lookupCollectionByKey(Object key) {
		Collection coll = (Collection) map.get(key);
		if (coll == null) {
			coll = new HashSet();
			map.put(key, coll);
		}
		return coll;
	}

	public Iterator iterator() {
		return map.entrySet().iterator();
	}

	public void putAll(Object key, Collection values) {
		lookupCollectionByKey(key).addAll(values);
	}

	public Set keySet() {
		return map.keySet();
	}

}
