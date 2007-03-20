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
package org.eclipse.dltk.internal.compiler.lookup;

import org.eclipse.dltk.compiler.env.lookup.Scope;

public class BlockScope extends Scope {
	// Local variable management

	/* position for next variable */
	public int localIndex;

	/* start position in this scope - for ordering scopes vs. variables */
	public int startIndex;

	/* for variable allocation throughout scopes */
	public int offset;

	/* for variable allocation throughout scopes */
	public int maxOffset;

	// finally scopes must be shifted behind respective try&catch scope(s) so as
	// to avoid
	// collisions of secret variables (return address, save value).
	public BlockScope[] shiftScopes;

	public Scope[] subscopes = new Scope[1]; // need access from code assist
	public int subscopeCount = 0; // need access from code assist
	
	private String basicToString(int tab) {
		String newLine = "\n"; //$NON-NLS-1$
		for (int i = tab; --i >= 0;)
			newLine += "\t"; //$NON-NLS-1$
		String s = newLine + "--- Block Scope ---"; //$NON-NLS-1$
		newLine += "\t"; //$NON-NLS-1$
		s += newLine + "startIndex = " + this.startIndex; //$NON-NLS-1$
		return s;
	}

	public BlockScope(BlockScope parent) {
		this(parent, true);
	}

	public BlockScope(BlockScope parent, boolean addToParentScope) {
		this(Scope.BLOCK_SCOPE, parent);

		if (addToParentScope) {
			parent.addSubscope(this);
		}

		this.startIndex = parent.localIndex;
	}

	public BlockScope(BlockScope parent, int variableCount) {
		this(Scope.BLOCK_SCOPE, parent);
		parent.addSubscope(this);
		this.startIndex = parent.localIndex;
	}

	protected BlockScope(int kind, Scope parent) {
		super(kind, parent);
	}

	public void addSubscope(Scope childScope) {
		if (this.subscopeCount == this.subscopes.length)
			System.arraycopy(this.subscopes, 0,
					(this.subscopes = new Scope[this.subscopeCount * 2]), 0,
					this.subscopeCount);
		this.subscopes[this.subscopeCount++] = childScope;
	}

	public int maxShiftedOffset() {
		int max = -1;
		if (this.shiftScopes != null) {
			for (int i = 0, length = this.shiftScopes.length; i < length; i++) {
				int subMaxOffset = this.shiftScopes[i].maxOffset;
				if (subMaxOffset > max)
					max = subMaxOffset;
			}
		}
		return max;
	}

	/*
	 * Answer the index of this scope relatively to its parent. For method
	 * scope, answers -1 (not a classScope relative position)
	 */
	public int scopeIndex() {
		if (this instanceof MethodScope)
			return -1;
		BlockScope parentScope = (BlockScope) this.parent;
		Scope[] parentSubscopes = parentScope.subscopes;
		for (int i = 0, max = parentScope.subscopeCount; i < max; i++) {
			if (parentSubscopes[i] == this)
				return i;
		}
		return -1;
	}

	// start position in this scope - for ordering scopes vs. variables
	public int startIndex() {
		return this.startIndex;
	}

	public String toString() {
		return toString(0);
	}

	public String toString(int tab) {
		String s = basicToString(tab);
		for (int i = 0; i < this.subscopeCount; i++)
			if (this.subscopes[i] instanceof BlockScope)
				s += ((BlockScope) this.subscopes[i]).toString(tab + 1) + "\n"; //$NON-NLS-1$
		return s;
	}
}
