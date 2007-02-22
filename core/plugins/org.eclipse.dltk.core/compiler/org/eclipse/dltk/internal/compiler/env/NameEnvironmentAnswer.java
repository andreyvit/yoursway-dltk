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
package org.eclipse.dltk.internal.compiler.env;

import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.compiler.env.ISourceType;

public class NameEnvironmentAnswer {
	private ISourceModule compilationUnit;
	private ISourceType[] sourceTypes;
	private AccessRestriction accessRestriction;

	public NameEnvironmentAnswer(ISourceModule compilationUnit,
			AccessRestriction accessRestriction) {
		this.compilationUnit = compilationUnit;
		this.accessRestriction = accessRestriction;
	}

	public NameEnvironmentAnswer(ISourceType[] sourceTypes,
			AccessRestriction accessRestriction) {
		this.sourceTypes = sourceTypes;
		this.accessRestriction = accessRestriction;
	}

	/**
	 * Returns the associated access restriction, or null if none.
	 */
	public AccessRestriction getAccessRestriction() {
		return this.accessRestriction;
	}

	/**
	 * Answer the compilation unit or null if the receiver represents a binary
	 * or source type.
	 */
	public ISourceModule getSourceModule() {
		return this.compilationUnit;
	}

	/**
	 * Answer the unresolved source forms for the type or null if the receiver
	 * represents a compilation unit or binary type.
	 * 
	 * Multiple source forms can be answered in case the originating compilation
	 * unit did contain several type at once. Then the first type is guaranteed
	 * to be the requested type.
	 */
	public ISourceType[] getSourceTypes() {
		return this.sourceTypes;
	}

	/**
	 * Answer whether the receiver contains the compilation unit which defines
	 * the type.
	 */
	public boolean isSourceModule() {
		return this.compilationUnit != null;
	}

	/**
	 * Answer whether the receiver contains the unresolved source form of the
	 * type.
	 */
	public boolean isSourceType() {
		return this.sourceTypes != null;
	}

	public boolean ignoreIfBetter() {
		return this.accessRestriction != null
				&& this.accessRestriction.ignoreIfBetter();
	}

	/*
	 * Returns whether this answer is better than the other awswer. (accessible
	 * is better than discouraged, which is better than non-accessible)
	 */
	public boolean isBetter(NameEnvironmentAnswer otherAnswer) {
		if (otherAnswer == null)
			return true;
		if (this.accessRestriction == null)
			return true;
		return otherAnswer.accessRestriction != null
				&& this.accessRestriction.getProblemId() < otherAnswer.accessRestriction
						.getProblemId();
	}
}
