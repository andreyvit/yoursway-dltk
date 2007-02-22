/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.core.tests.model;

import java.util.ArrayList;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.BufferChangedEvent;
import org.eclipse.dltk.core.IBuffer;
import org.eclipse.dltk.core.IBufferChangedListener;
import org.eclipse.dltk.core.IOpenable;

/*
 * A simple implementation of IBuffer.
 */
public class TestBuffer implements IBuffer {
	private IOpenable owner;
	private ArrayList changeListeners;
	private char[] contents = null;
	private boolean hasUnsavedChanges = false;

	public TestBuffer(IOpenable owner) {
		this.owner = owner;
	}

	public void addBufferChangedListener(IBufferChangedListener listener) {
		if (this.changeListeners == null) {
			this.changeListeners = new ArrayList(5);
		}
		if (!this.changeListeners.contains(listener)) {
			this.changeListeners.add(listener);
		}
	}

	public void append(char[] text) {
		this.hasUnsavedChanges = true;
	}

	public void append(String text) {
		this.hasUnsavedChanges = true;
	}

	public void close() {
		this.contents = null; // mark as closed
		if (this.changeListeners != null) {
			BufferChangedEvent event = null;
			event = new BufferChangedEvent(this, 0, 0, null);
			for (int i = 0, size = this.changeListeners.size(); i < size; ++i) {
				IBufferChangedListener listener = (IBufferChangedListener) this.changeListeners
						.get(i);
				listener.bufferChanged(event);
			}
			this.changeListeners = null;
		}
	}

	public char getChar(int position) {
		return 0;
	}

	public char[] getCharacters() {
		return contents;
	}

	public String getContents() {
		return new String(contents);
	}

	public int getLength() {
		return contents.length;
	}

	public IOpenable getOwner() {
		return this.owner;
	}

	public String getText(int offset, int length) {
		return null;
	}

	public IResource getUnderlyingResource() {
		return null;
	}

	public boolean hasUnsavedChanges() {
		return this.hasUnsavedChanges;
	}

	public boolean isClosed() {
		return this.contents == null;
	}

	public boolean isReadOnly() {
		return false;
	}

	public void removeBufferChangedListener(IBufferChangedListener listener) {
		if (this.changeListeners != null) {
			this.changeListeners.remove(listener);
			if (this.changeListeners.size() == 0) {
				this.changeListeners = null;
			}
		}
	}

	public void replace(int position, int length, char[] text) {
		this.hasUnsavedChanges = true;
	}

	public void replace(int position, int length, String text) {
		this.hasUnsavedChanges = true;
	}

	public void save(IProgressMonitor progress, boolean force) {
		this.hasUnsavedChanges = false;
	}

	public void setContents(char[] characters) {
		this.contents = characters;
		this.hasUnsavedChanges = true;
	}

	public void setContents(String characters) {
		this.contents = characters.toCharArray();
		this.hasUnsavedChanges = true;
	}
}
