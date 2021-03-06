/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.dltk.core.IElementCacheListener;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.util.LRUCache;

/**
 * An LRU cache of <code>ModelElements</code>.
 */
public class ElementCache extends OverflowingLRUCache {
	IModelElement spaceLimitParent = null;
	private ListenerList listeners;

	/**
	 * Constructs a new element cache of the given size.
	 */
	public ElementCache(int size) {
		super(size);
	}

	/**
	 * Constructs a new element cache of the given size.
	 */
	public ElementCache(int size, int overflow) {
		super(size, overflow);
	}

	/**
	 * Returns true if the element is successfully closed and removed from the
	 * cache, otherwise false.
	 * 
	 * <p>
	 * NOTE: this triggers an external removal of this element by closing the
	 * element.
	 */
	protected boolean close(LRUCacheEntry entry) {
		Openable element = (Openable) entry._fKey;
		notifyListenersClose(element);
		try {
			if (!element.canBeRemovedFromCache()) {
				return false;
			} else {
				// We must close an entire external folder of zip.
				if (element instanceof ArchiveFolder
						|| element instanceof ExternalScriptFolder) {
					ScriptFolder archiveFolder = (ScriptFolder) element;
					ProjectFragment root = (ProjectFragment) archiveFolder
							.getParent();
					root.close();
				} else {
					element.close();
				}
				return true;
			}
		} catch (ModelException npe) {
			return false;
		}
	}

	/*
	 * Ensures that there is enough room for adding the given number of
	 * children. If the space limit must be increased, record the parent that
	 * needed this space limit.
	 */
	protected void ensureSpaceLimit(int childrenSize, IModelElement parent) {
		// ensure the children can be put without closing other elements
		int spaceNeeded = 1 + (int) ((1 + fLoadFactor) * (childrenSize + fOverflow));
		if (fSpaceLimit < spaceNeeded) {
			// parent is being opened with more children than the space limit
			shrink(); // remove overflow
			setSpaceLimit(spaceNeeded);
			this.spaceLimitParent = parent;
		}
	}

	/*
	 * Returns a new instance of the receiver.
	 */
	protected LRUCache newInstance(int size, int overflow) {
		return new ElementCache(size, overflow);
	}

	/*
	 * If the given parent was the one that increased the space limit, reset the
	 * space limit to the given default value.
	 */
	protected void resetSpaceLimit(int defaultLimit, IModelElement parent) {
		if (parent.equals(this.spaceLimitParent)) {
			setSpaceLimit(defaultLimit);
			this.spaceLimitParent = null;
		}
	}

	protected ListenerList getListenerList() {
		if (listeners == null) {
			listeners = new ListenerList();
		}
		return listeners;
	}

	public void addListener(IElementCacheListener listener) {
		getListenerList().add(listener);
	}

	public void removeListener(IElementCacheListener listener) {
		getListenerList().remove(listener);
	}

	protected void notifyListenersClose(Object element) {
		Object[] listeners2 = getListenerList().getListeners();
		for (int i = 0; i < listeners2.length; i++) {
			IElementCacheListener listener = (IElementCacheListener) listeners2[i];
			if (listener != null) {
				listener.close(element);
			}
		}
	}
}
