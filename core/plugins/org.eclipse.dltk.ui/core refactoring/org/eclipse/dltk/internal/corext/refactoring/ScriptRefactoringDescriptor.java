/*******************************************************************************
 * Copyright (c) 2006, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.WorkingCopyOwner;
import org.eclipse.dltk.internal.corext.refactoring.tagging.IScriptableRefactoring;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.ltk.core.refactoring.Refactoring;
import org.eclipse.ltk.core.refactoring.RefactoringContribution;
import org.eclipse.ltk.core.refactoring.RefactoringCore;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments;


/**
 * Descriptor object of a script refactoring.
 * 
	 *
 */
public final class ScriptRefactoringDescriptor extends RefactoringDescriptor {

	/**
	 * Predefined argument called <code>element&lt;Number&gt;</code>.
	 * <p>
	 * This argument should be used to describe the elements being refactored.
	 * The value of this argument does not necessarily have to uniquely identify
	 * the elements. However, it must be possible to uniquely identify the
	 * elements using the value of this argument in conjunction with the values
	 * of the other user-defined attributes.
	 * </p>
	 * <p>
	 * The element arguments are simply distinguished by appending a number to
	 * the argument name, e.g. element1. The indices of this argument are non
	 * zero-based.
	 * </p>
	 */
	public static final String ATTRIBUTE_ELEMENT= "element"; //$NON-NLS-1$

	/**
	 * Predefined argument called <code>input</code>.
	 * <p>
	 * This argument should be used to describe the element being refactored.
	 * The value of this argument does not necessarily have to uniquely identify
	 * the input element. However, it must be possible to uniquely identify the
	 * input element using the value of this argument in conjunction with the
	 * values of the other user-defined attributes.
	 * </p>
	 */
	public static final String ATTRIBUTE_INPUT= "input"; //$NON-NLS-1$

	/**
	 * Predefined argument called <code>name</code>.
	 * <p>
	 * This argument should be used to name the element being refactored. The
	 * value of this argument may be shown in the user interface.
	 * </p>
	 */
	public static final String ATTRIBUTE_NAME= "name"; //$NON-NLS-1$

	/**
	 * Predefined argument called <code>selection</code>.
	 * <p>
	 * This argument should be used to describe user input selections within a
	 * text file. The value of this argument has the format "offset length".
	 * </p>
	 */
	public static final String ATTRIBUTE_SELECTION= "selection"; //$NON-NLS-1$

	/** The version attribute */
	private static final String ATTRIBUTE_VERSION= "version"; //$NON-NLS-1$

	/**
	 * Constant describing the deprecation resolving flag.
	 * <p>
	 * Clients should set this flag to indicate that the refactoring can used to
	 * resolve deprecation problems of members. Refactorings which can run on
	 * binary targets, but require a source attachment to work correctly, should
	 * set the <code>ARCHIVE_SOURCE_ATTACHMENT</code> flag as well.
	 * </p>
	 */
	public static final int DEPRECATION_RESOLVING= 1 << 17;

	/**
	 * Constant describing the archive importable flag.
	 * <p>
	 * Clients should set this flag to indicate that the refactoring can be
	 * imported from a archive file. If this flag is set,
	 * <code>ARCHIVE_REFACTORABLE</code> should be set as well.
	 * </p>
	 */
	public static final int ARCHIVE_IMPORTABLE= 1 << 16;

	/**
	 * Constant describing the jar refactorable flag.
	 * <p>
	 * Clients should set this flag to indicate that the refactoring can be
	 * performed on a JAR file. Refactorings which can run on binary targets,
	 * but require a source attachment to work correctly, should set the
	 * <code>JAR_SOURCE_ATTACHMENT</code> flag as well.
	 * </p>
	 */
	public static final int ARCHIVE_REFACTORABLE= 1 << 19;
	
	/** The version value 1.0 */
	private static final String VALUE_VERSION_1_0= "1.0"; //$NON-NLS-1$

	/**
	 * Converts the specified element to an input handle.
	 * 
	 * @param project
	 *            the project, or <code>null</code> for the workspace
	 * @param element
	 *            the element
	 * @return a corresponding input handle
	 */
	public static String elementToHandle(final String project, final IModelElement element) {
		final String handle= element.getHandleIdentifier();
		if (project != null && !(element instanceof IScriptProject)) {
			final String id= element.getScriptProject().getHandleIdentifier();
			return handle.substring(id.length());
		}
		return handle;
	}

	/**
	 * Converts an input handle back to the corresponding script element.
	 * 
	 * @param project
	 *            the project, or <code>null</code> for the workspace
	 * @param handle
	 *            the input handle
	 * @return the corresponding script element, or <code>null</code> if no such
	 *         element exists
	 */
	public static IModelElement handleToElement(final String project, final String handle) {
		return handleToElement(project, handle, true);
	}

	/**
	 * Converts an input handle back to the corresponding script element.
	 * 
	 * @param project
	 *            the project, or <code>null</code> for the workspace
	 * @param handle
	 *            the input handle
	 * @param check
	 *            <code>true</code> to check for existence of the element,
	 *            <code>false</code> otherwise
	 * @return the corresponding script element, or <code>null</code> if no such
	 *         element exists
	 */
	public static IModelElement handleToElement(final String project, final String handle, final boolean check) {
		return handleToElement(null, project, handle, check);
	}

	/**
	 * Converts an input handle back to the corresponding script element.
	 * 
	 * @param owner
	 *            the working copy owner
	 * @param project
	 *            the project, or <code>null</code> for the workspace
	 * @param handle
	 *            the input handle
	 * @param check
	 *            <code>true</code> to check for existence of the element,
	 *            <code>false</code> otherwise
	 * @return the corresponding script element, or <code>null</code> if no such
	 *         element exists
	 */
	public static IModelElement handleToElement(final WorkingCopyOwner owner, final String project, final String handle, final boolean check) {
		IModelElement element= null;
		if (owner != null)
			element= DLTKCore.create(handle, owner);
		else
			element= DLTKCore.create(handle);
		if (element == null && project != null) {
			final IScriptProject scriptProject= DLTKCore.create(ResourcesPlugin.getWorkspace().getRoot()).getScriptProject(project);
			final String identifier= scriptProject.getHandleIdentifier();
			if (owner != null)
				element= DLTKCore.create(identifier + handle, owner);
			else
				element= DLTKCore.create(identifier + handle);
		}
		if (DLTKCore.DEBUG) {
			System.err.println("TODo: ScriptRefactoringDescriptor add find Methods member in types..."); //$NON-NLS-1$
		}
//		if (check && element instanceof IMethod) {
//			final IMethod method= (IMethod) element;
//			final IMethod[] methods= method.getDeclaringType().findMethods(method);
//			if (methods != null && methods.length > 0)
//				element= methods[0];
//		}
		if (element != null && (!check || element.exists()))
			return element;
		return null;
	}

	/**
	 * Converts an input handle with the given prefix back to the corresponding
	 * resource.
	 * 
	 * @param project
	 *            the project, or <code>null</code> for the workspace
	 * @param handle
	 *            the input handle
	 * 
	 * @return the corresponding resource, or <code>null</code> if no such
	 *         resource exists
	 */
	public static IResource handleToResource(final String project, final String handle) {
		final IWorkspaceRoot root= ResourcesPlugin.getWorkspace().getRoot();
		if ("".equals(handle)) //$NON-NLS-1$
			return null;
		final IPath path= Path.fromPortableString(handle);
		if (path == null)
			return null;
		if (project != null && !"".equals(project)) //$NON-NLS-1$
			return root.getProject(project).findMember(path);
		return root.findMember(path);
	}

	/**
	 * Converts the specified resource to an input handle.
	 * 
	 * @param project
	 *            the project, or <code>null</code> for the workspace
	 * @param resource
	 *            the resource
	 * 
	 * @return the input handle
	 */
	public static String resourceToHandle(final String project, final IResource resource) {
		if (project != null && !"".equals(project)) //$NON-NLS-1$
			return resource.getProjectRelativePath().toPortableString();
		return resource.getFullPath().toPortableString();
	}

	/** The map of arguments (element type: &lt;String, String&gt;) */
	private final Map fArguments;

	/** The refactoring contribution, or <code>null</code> */
	private ScriptRefactoringContribution fContribution;

	/**
	 * Creates a new script refactoring descriptor.
	 * 
	 * @param contribution
	 *            the refactoring contribution, or <code>null</code>
	 * @param id
	 *            the unique id of the refactoring
	 * @param project
	 *            the project name, or <code>null</code>
	 * @param description
	 *            the description
	 * @param comment
	 *            the comment, or <code>null</code>
	 * @param arguments
	 *            the argument map
	 * @param flags
	 *            the flags
	 */
	public ScriptRefactoringDescriptor(final ScriptRefactoringContribution contribution, final String id, final String project, final String description, final String comment, final Map arguments, final int flags) {
		super(id, project, description, comment, flags);
		Assert.isNotNull(arguments);
		fContribution= contribution;
		fArguments= arguments;
	}

	/**
	 * Creates a new script refactoring descriptor.
	 * 
	 * @param id
	 *            the unique id of the refactoring
	 * @param project
	 *            the project name, or <code>null</code>
	 * @param description
	 *            the description
	 * @param comment
	 *            the comment, or <code>null</code>
	 * @param arguments
	 *            the argument map
	 * @param flags
	 *            the flags
	 */
	public ScriptRefactoringDescriptor(final String id, final String project, final String description, final String comment, final Map arguments, final int flags) {
		this(null, id, project, description, comment, arguments, flags);
	}

	/**
	 * Creates refactoring arguments for this refactoring descriptor.
	 * 
	 * @return the refactoring arguments
	 */
	public RefactoringArguments createArguments() {
		final ScriptRefactoringArguments arguments= new ScriptRefactoringArguments(getProject());
		for (final Iterator iterator= fArguments.entrySet().iterator(); iterator.hasNext();) {
			final Map.Entry entry= (Entry) iterator.next();
			final String name= (String) entry.getKey();
			final String value= (String) entry.getValue();
			if (name != null && !"".equals(name) && value != null) //$NON-NLS-1$
				arguments.setAttribute(name, value);
		}
		return arguments;
	}

	/**
	 * {@inheritDoc}
	 */
	public Refactoring createRefactoring(final RefactoringStatus status) throws CoreException {
		Refactoring refactoring= null;
		if (fContribution != null)
			refactoring= fContribution.createRefactoring(this);
		else {
			final RefactoringContribution contribution= RefactoringCore.getRefactoringContribution(getID());
			if (contribution instanceof ScriptRefactoringContribution) {
				fContribution= (ScriptRefactoringContribution) contribution;
				refactoring= fContribution.createRefactoring(this);
			}
		}
		if (refactoring != null) {
			if (refactoring instanceof IScriptableRefactoring) {
				final RefactoringStatus result= ((IScriptableRefactoring) refactoring).initialize(createArguments());
				if (result.hasFatalError())
					throw new CoreException(new Status(IStatus.ERROR, DLTKUIPlugin.PLUGIN_ID, 0, result.getMessageMatchingSeverity(RefactoringStatus.FATAL), null));
				status.merge(result);
			} else
				throw new CoreException(new Status(IStatus.ERROR, DLTKUIPlugin.PLUGIN_ID, 0, Messages.format(RefactoringCoreMessages.ScriptRefactoringDescriptor_initialization_error, getDescription()), null));
		}
		return refactoring;
	}

	/**
	 * Converts the specified element to an input handle.
	 * 
	 * @param element
	 *            the element
	 * @return a corresponding input handle
	 */
	public String elementToHandle(final IModelElement element) {
		Assert.isNotNull(element);
		return elementToHandle(getProject(), element);
	}

	/**
	 * Returns the argument map
	 * 
	 * @return the argument map.
	 */
	public Map getArguments() {
		final Map map= new HashMap(fArguments);
		map.put(ATTRIBUTE_VERSION, VALUE_VERSION_1_0);
		return map;
	}

	/**
	 * Returns the refactoring contribution.
	 * 
	 * @return the refactoring contribution, or <code>null</code>
	 */
	public ScriptRefactoringContribution getContribution() {
		return fContribution;
	}
}
