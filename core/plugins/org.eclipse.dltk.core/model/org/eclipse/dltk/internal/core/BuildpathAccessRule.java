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
package org.eclipse.dltk.internal.core;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.core.IAccessRule;
import org.eclipse.dltk.internal.compiler.env.AccessRule;


public class BuildpathAccessRule extends AccessRule implements IAccessRule {
	
	public BuildpathAccessRule(IPath pattern, int kind) {
		this(pattern.toString().toCharArray(), toProblemId(kind));
	}
	
	public BuildpathAccessRule(char[] pattern, int problemId) {
		super(pattern, problemId);
	}
	
	private static int toProblemId(int kind) {
		boolean ignoreIfBetter = (kind & IAccessRule.IGNORE_IF_BETTER) != 0;
		switch (kind & ~IAccessRule.IGNORE_IF_BETTER) {
			case K_NON_ACCESSIBLE:
				return ignoreIfBetter ? IProblem.ForbiddenReference | AccessRule.IgnoreIfBetter : IProblem.ForbiddenReference;
			case K_DISCOURAGED:
				return ignoreIfBetter ? IProblem.DiscouragedReference | AccessRule.IgnoreIfBetter : IProblem.DiscouragedReference;
			default:
				return ignoreIfBetter ? AccessRule.IgnoreIfBetter : 0;
		}
	}

	public IPath getPattern() {
		return new Path(new String(this.pattern));
	}

	public int getKind() {
		switch (getProblemId()) {
			case IProblem.ForbiddenReference:
				return K_NON_ACCESSIBLE;
			case IProblem.DiscouragedReference:
				return K_DISCOURAGED;
			default:
				return K_ACCESSIBLE;
		}
	}

}
