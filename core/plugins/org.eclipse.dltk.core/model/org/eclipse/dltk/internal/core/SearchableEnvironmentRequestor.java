/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import org.eclipse.dltk.compiler.CharOperation;
import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.codeassist.ISearchRequestor;
import org.eclipse.dltk.internal.compiler.env.AccessRestriction;
import org.eclipse.dltk.internal.compiler.env.AccessRuleSet;

/**
 * Implements <code>IModelElementRequestor</code>
 */
class SearchableEnvironmentRequestor extends ModelElementRequestor {
	/**
	 * The <code>ISearchRequestor</code> this ModelElementRequestor wraps and
	 * forwards results to.
	 */
	protected ISearchRequestor requestor;

	/**
	 * The <code>ICompilationUNit</code> this ModelElementRequestor will not
	 * accept types within.
	 */
	protected ISourceModule unitToSkip;

	protected IDLTKProject project;

	protected NameLookup nameLookup;

	protected boolean checkAccessRestrictions;

	/**
	 * Constructs a SearchableEnvironmentRequestor that wraps the given
	 * SearchRequestor.
	 */
	public SearchableEnvironmentRequestor(ISearchRequestor requestor) {
		this.requestor = requestor;
		this.unitToSkip = null;
		this.project = null;
		this.nameLookup = null;
		this.checkAccessRestrictions = false;

	}

	/**
	 * Constructs a SearchableEnvironmentRequestor that wraps the given
	 * SearchRequestor. The requestor will not accept types in the
	 * <code>unitToSkip</code>.
	 */
	public SearchableEnvironmentRequestor(ISearchRequestor requestor,
			ISourceModule unitToSkip, IDLTKProject project,
			NameLookup nameLookup) {
		this.requestor = requestor;
		this.unitToSkip = unitToSkip;
		this.project = project;
		this.nameLookup = nameLookup;
		this.checkAccessRestrictions = !DLTKCore.IGNORE.equals(project
				.getOption(DLTKCore.COMPILER_PB_FORBIDDEN_REFERENCE, true))
				|| !DLTKCore.IGNORE.equals(project.getOption(
						DLTKCore.COMPILER_PB_DISCOURAGED_REFERENCE, true));
	}

	/**
	 * @see IModelElementRequestor
	 */
	public void acceptScriptFolder(IScriptFolder ScriptFolder) {
		this.requestor.acceptPackage(ScriptFolder.getElementName()
				.toCharArray());
	}

	/**
	 * @see IModelElementRequestor
	 */
	public void acceptType(IType type) {
		try {
			if (this.unitToSkip != null
					&& this.unitToSkip.equals(type.getSourceModule())) {
				return;
			}
			char[] packageName = type.getScriptFolder().getElementName()
					.toCharArray();

			// determine associated access restriction
			AccessRestriction accessRestriction = null;

			if (this.checkAccessRestrictions
					&& (!type.getScriptProject().equals(this.project))) {
				ProjectFragment root = (ProjectFragment) type
						.getAncestor(IModelElement.PROJECT_FRAGMENT);
				BuildpathEntry entry = (BuildpathEntry) this.nameLookup.rootToResolvedEntries
						.get(root);
				if (entry != null) { // reverse map always contains resolved
					// CP entry
					AccessRuleSet accessRuleSet = entry.getAccessRuleSet();
					if (accessRuleSet != null) {
						// TODO (philippe) improve char[] <-> String conversions
						// to avoid performing them on the fly
						char[][] packageChars = CharOperation.splitOn('.',
								packageName);
						char[] fileWithoutExtension = type.getElementName()
								.toCharArray();
						accessRestriction = accessRuleSet
								.getViolatedRestriction(CharOperation
										.concatWith(packageChars,
												fileWithoutExtension, '/'));
					}
				}
			}
			this.requestor.acceptType(packageName, type.getElementName()
					.toCharArray(), null, type.getFlags(), accessRestriction);
		} catch (ModelException jme) {
			// ignore
		}
	}
}
