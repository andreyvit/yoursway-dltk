/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ElementChangedEvent;
import org.eclipse.dltk.core.IElementCacheListener;
import org.eclipse.dltk.core.IElementChangedListener;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceModuleInfoCache;

/**
 * Used to cache some source module information. All information related to
 * source module are removed, then source module are changed.
 * 
 * @author haiodo
 * 
 */
public class SourceModuleInfoCache implements ISourceModuleInfoCache {
	private ElementCache cache = null;
	static long allAccess = 0;
	static long miss = 0;
	static long closes = 0;

	public SourceModuleInfoCache() {
		// set the size of the caches in function of the maximum amount of
		// memory available
		// long maxMemory = Runtime.getRuntime().freeMemory();
		// if max memory is infinite, set the ratio to 4d which corresponds to
		// the 256MB that Eclipse defaults to
		// (see https://bugs.eclipse.org/bugs/show_bug.cgi?id=111299)
		double ratio = 30; // 64000000

		this.cache = new ElementCache(
				(int) (ModelCache.DEFAULT_ROOT_SIZE * ratio));
		this.cache.setLoadFactor(0.90);
		this.cache.addListener(new IElementCacheListener() {
			public void close(Object element) {
				closes++;
			}
		});
		DLTKCore.addElementChangedListener(changedListener);
	}

	public void stop() {
		DLTKCore.removeElementChangedListener(changedListener);
	}

	public ISourceModuleInfo get(ISourceModule module) {
		Object object = this.cache.get(module);
		if (DLTKCore.VERBOSE) {
			System.out.println("Filling ratio:" + this.cache.fillingRatio());
		}
		allAccess++;
		if (object == null) {
			miss++;
			return returnAdd(module);
		}
		SoftReference ref = (SoftReference) object;
		ISourceModuleInfo info = (ISourceModuleInfo) ref.get();
		if (info == null) {
			miss++;
			return returnAdd(module);
		}
		// this.cache.printStats();
		if (DLTKCore.PERFOMANCE) {
			System.out.println("SourceModuleInfoCache: access:" + allAccess
					+ " ok:" + (100.0f * (allAccess - miss) / allAccess)
					+ "% closes:" + closes);
			System.out.println("Filling ratio:" + this.cache.fillingRatio());
		}
		return (ISourceModuleInfo) info;
	}

	private ISourceModuleInfo returnAdd(ISourceModule module) {
		ISourceModuleInfo info = new SourceModuleInfo();
		SoftReference ref = new SoftReference(info);
		this.cache.put(module, ref);
		this.cache.ensureSpaceLimit(1, module);
		return info;
	}

	private IElementChangedListener changedListener = new IElementChangedListener() {
		public void elementChanged(ElementChangedEvent event) {
			IModelElementDelta delta = event.getDelta();
			processDelta(delta);
		}

		private void processDelta(IModelElementDelta delta) {
			IModelElement element = delta.getElement();
			if (delta.getKind() == IModelElementDelta.REMOVED
					|| delta.getKind() == IModelElementDelta.CHANGED
					|| (delta.getFlags() & IModelElementDelta.F_REMOVED_FROM_BUILDPATH) != 0
					|| (delta.getFlags() & IModelElementDelta.CHANGED) != 0) {
				if (element.getElementType() == IModelElement.SOURCE_MODULE) {
					SourceModuleInfoCache.this.remove((ISourceModule) element);
				}
			}
			if ((delta.getFlags() & IModelElementDelta.F_CHILDREN) != 0) {
				IModelElementDelta[] affectedChildren = delta
						.getAffectedChildren();
				for (int i = 0; i < affectedChildren.length; i++) {
					IModelElementDelta child = affectedChildren[i];
					processDelta(child);
				}
			}
		}
	};

	private static class SourceModuleInfo implements ISourceModuleInfo {
		private Map map;

		public Object get(Object key) {
			if (map == null) {
				return null;
			}
			return map.get(key);
		}

		public void put(Object key, Object value) {
			if (map == null) {
				map = new HashMap();
			}
			map.put(key, value);
		}

		public void remove(Object key) {
			if (map != null) {
				map.remove(key);
			}
		}

		public boolean isEmpty() {
			if (this.map == null) {
				return true;
			}
			return this.map.isEmpty();
		}
	}

	public void remove(ISourceModule element) {
		cache.remove(element);
		cache.resetSpaceLimit(ModelCache.DEFAULT_ROOT_SIZE, element);
	}
}
