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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.dltk.compiler.CharOperation;
import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.compiler.env.ISourceType;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISearchableEnvironment;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.WorkingCopyOwner;
import org.eclipse.dltk.core.search.BasicSearchEngine;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.internal.codeassist.ISearchRequestor;
import org.eclipse.dltk.internal.compiler.env.AccessRestriction;
import org.eclipse.dltk.internal.compiler.env.NameEnvironmentAnswer;
import org.eclipse.dltk.internal.core.search.IRestrictedAccessTypeRequestor;

/**
 * This class provides a <code>SearchableBuilderEnvironment</code> for code
 * assist which uses the Script model as a search tool.
 */
public class SearchableEnvironment implements IDLTKSearchConstants,
		ISearchableEnvironment {
	public NameLookup nameLookup;
	
	protected ISourceModule unitToSkip;
	
	protected org.eclipse.dltk.core.ISourceModule[] workingCopies;
	
	protected DLTKProject project;
	
	protected IDLTKSearchScope searchScope;
	
	protected boolean checkAccessRestrictions;

	/**
	 * Creates a SearchableEnvironment on the given project
	 */
	public SearchableEnvironment(DLTKProject project,
			org.eclipse.dltk.core.ISourceModule[] workingCopies)
			throws ModelException {
		this.project = project;
		this.checkAccessRestrictions = !DLTKCore.IGNORE.equals(project
				.getOption(DLTKCore.COMPILER_PB_FORBIDDEN_REFERENCE, true))
				|| !DLTKCore.IGNORE.equals(project.getOption(
						DLTKCore.COMPILER_PB_DISCOURAGED_REFERENCE, true));
		this.workingCopies = workingCopies;
		this.nameLookup = project.newNameLookup(workingCopies);
		// Create search scope with visible entry on the project's buildpath
		if (this.checkAccessRestrictions) {
			this.searchScope = BasicSearchEngine
					.createSearchScope(new IModelElement[] { project });
		} else {
			this.searchScope = BasicSearchEngine
					.createSearchScope(this.nameLookup.ProjectFragments);
		}
	}

	/**
	 * Creates a SearchableEnvironment on the given project
	 */
	public SearchableEnvironment(DLTKProject project, WorkingCopyOwner owner)
			throws ModelException {
		this(project, owner == null ? null : ModelManager.getModelManager()
				.getWorkingCopies(owner, true)); // add primary WCs
	}

	/**
	 * Returns the given type in the the given package if it exists, otherwise
	 * <code>null</code>.
	 */
	protected NameEnvironmentAnswer find(String typeName, String packageName) {
		if (packageName == null)
			packageName = IScriptFolder.DEFAULT_FOLDER_NAME;
		NameLookup.Answer answer = this.nameLookup.findType(typeName,
				packageName, false, NameLookup.ACCEPT_ALL,
				this.checkAccessRestrictions);
		if (answer != null) {
			// construct name env answer
			if (DLTKCore.DEBUG) {
				System.err.println("TODO: Add binary types support code.");
			}
			// if (answer.type instanceof BinaryType) { // BinaryType
			// try {
			// return new NameEnvironmentAnswer((IBinaryType) ((BinaryType)
			// answer.type).getElementInfo(), answer.restriction);
			// } catch (ModelException npe) {
			// return null;
			// }
			// } else
			{ // SourceType
				try {
					// retrieve the requested type
					SourceTypeElementInfo sourceType = (SourceTypeElementInfo) ((SourceType) answer.type)
							.getElementInfo();
					ISourceType topLevelType = sourceType;
					while (topLevelType.getEnclosingType() != null) {
						topLevelType = topLevelType.getEnclosingType();
					}
					// find all siblings (other types declared in same unit,
					// since may be used for name resolution)
					IType[] types = sourceType.getHandle().getSourceModule()
							.getTypes();
					ISourceType[] sourceTypes = new ISourceType[types.length];
					// in the resulting collection, ensure the requested type is
					// the first one
					sourceTypes[0] = sourceType;
					int length = types.length;
					for (int i = 0, index = 1; i < length; i++) {
						ISourceType otherType = (ISourceType) ((ModelElement) types[i])
								.getElementInfo();
						if (!otherType.equals(topLevelType) && index < length) // check
							// that
							// the
							// index
							// is
							// in
							// bounds
							// (see
							// https://bugs.eclipse.org/bugs/show_bug.cgi?id=62861)
							sourceTypes[index++] = otherType;
					}
					return new NameEnvironmentAnswer(sourceTypes,
							answer.restriction);
				} catch (ModelException npe) {
					return null;
				}
			}
		}
		return null;
	}
	
	public void findPackages(char[] prefix, ISearchRequestor requestor) {
		this.nameLookup.seekScriptFolders(new String(prefix), true,
				new SearchableEnvironmentRequestor(requestor));
	}

	public NameEnvironmentAnswer findType(char[][] compoundTypeName) {
		if (compoundTypeName == null)
			return null;
		int length = compoundTypeName.length;
		if (length <= 1) {
			if (length == 0)
				return null;
			return find(new String(compoundTypeName[0]), null);
		}
		int lengthM1 = length - 1;
		char[][] packageName = new char[lengthM1][];
		System.arraycopy(compoundTypeName, 0, packageName, 0, lengthM1);
		return find(new String(compoundTypeName[lengthM1]), CharOperation
				.toString(packageName));
	}
	
	public NameEnvironmentAnswer findType(char[] name, char[][] packageName) {
		if (name == null)
			return null;
		return find(new String(name), packageName == null
				|| packageName.length == 0 ? null : CharOperation
				.toString(packageName));
	}
	
	public void findTypes(char[] prefix, final boolean findMembers,
			boolean camelCaseMatch, final ISearchRequestor storage) {
		/*
		 * if (true){ findTypes(new String(prefix), storage,
		 * NameLookup.ACCEPT_CLASSES | NameLookup.ACCEPT_INTERFACES); return; }
		 */
		try {
			final String excludePath;
			if (this.unitToSkip != null) {
				if (!(this.unitToSkip instanceof IModelElement)) {
					// revert to model investigation
					findTypes(new String(prefix), storage,
							NameLookup.ACCEPT_ALL);
					return;
				}
				excludePath = ((IModelElement) this.unitToSkip).getPath()
						.toString();
			} else {
				excludePath = null;
			}
			int lastDotIndex = CharOperation.lastIndexOf('.', prefix);
			char[] qualification, simpleName;
			if (lastDotIndex < 0) {
				qualification = null;
				if (camelCaseMatch) {
					simpleName = prefix;
				} else {
					simpleName = CharOperation.toLowerCase(prefix);
				}
			} else {
				qualification = CharOperation.subarray(prefix, 0, lastDotIndex);
				if (camelCaseMatch) {
					simpleName = CharOperation.subarray(prefix,
							lastDotIndex + 1, prefix.length);
				} else {
					simpleName = CharOperation.toLowerCase(CharOperation
							.subarray(prefix, lastDotIndex + 1, prefix.length));
				}
			}
			IProgressMonitor progressMonitor = new IProgressMonitor() {
				boolean isCanceled = false;

				public void beginTask(String name, int totalWork) {
					// implements interface method
				}

				public void done() {
					// implements interface method
				}

				public void internalWorked(double work) {
					// implements interface method
				}

				public boolean isCanceled() {
					return isCanceled;
				}

				public void setCanceled(boolean value) {
					isCanceled = value;
				}

				public void setTaskName(String name) {
					// implements interface method
				}

				public void subTask(String name) {
					// implements interface method
				}

				public void worked(int work) {
					// implements interface method
				}
			};
			IRestrictedAccessTypeRequestor typeRequestor = new IRestrictedAccessTypeRequestor() {
				public void acceptType(int modifiers, char[] packageName,
						char[] simpleTypeName, char[][] enclosingTypeNames,
						String path, AccessRestriction access) {
					if (excludePath != null && excludePath.equals(path))
						return;
					if (!findMembers && enclosingTypeNames != null
							&& enclosingTypeNames.length > 0)
						return; // accept only top level types
					storage.acceptType(packageName, simpleTypeName,
							enclosingTypeNames, modifiers, access);
				}
			};
			try {
				int matchRule = SearchPattern.R_PREFIX_MATCH;
				if (camelCaseMatch)
					matchRule |= SearchPattern.R_CAMELCASE_MATCH;
				new BasicSearchEngine(this.workingCopies).searchAllTypeNames(
						qualification,
						SearchPattern.R_EXACT_MATCH,
						simpleName,
						matchRule, // not case sensitive
						IDLTKSearchConstants.TYPE, this.searchScope,
						typeRequestor, CANCEL_IF_NOT_READY_TO_SEARCH,
						progressMonitor);
			} catch (OperationCanceledException e) {
				findTypes(new String(prefix), storage, NameLookup.ACCEPT_ALL);
			}
		} catch (ModelException e) {
			findTypes(new String(prefix), storage, NameLookup.ACCEPT_ALL);
		}
	}

	/**
	 * Returns all types whose name starts with the given (qualified)
	 * <code>prefix</code>.
	 * 
	 * If the <code>prefix</code> is unqualified, all types whose simple name
	 * matches the <code>prefix</code> are returned.
	 */
	private void findTypes(String prefix, ISearchRequestor storage, int type) {
		// TODO (david) should add camel case support
		SearchableEnvironmentRequestor requestor = new SearchableEnvironmentRequestor(
				storage, this.unitToSkip, this.project, this.nameLookup);
		int index = prefix.lastIndexOf('.');
		if (index == -1) {
			this.nameLookup.seekTypes(prefix, null, true, type, requestor);
		} else {
			String packageName = prefix.substring(0, index);
			ModelElementRequestor elementRequestor = new ModelElementRequestor();
			this.nameLookup.seekScriptFolders(packageName, false,
					elementRequestor);
			IScriptFolder[] fragments = elementRequestor.getScriptFolders();
			if (fragments != null) {
				String className = prefix.substring(index + 1);
				for (int i = 0, length = fragments.length; i < length; i++)
					if (fragments[i] != null)
						this.nameLookup.seekTypes(className, fragments[i],
								true, type, requestor);
			}
		}
	}

	/**
	 * Returns a printable string for the array.
	 */
	protected String toStringChar(char[] name) {
		return "[" //$NON-NLS-1$
				+ new String(name) + "]"; //$NON-NLS-1$
	}

	/**
	 * Returns a printable string for the array.
	 */
	protected String toStringCharChar(char[][] names) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < names.length; i++) {
			result.append(toStringChar(names[i]));
		}
		return result.toString();
	}
	
	public void cleanup() {
		// nothing to do
	}

	public NameLookup getNameLookup() {
		return this.nameLookup;
	}
}
