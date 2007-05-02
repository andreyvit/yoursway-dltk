/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core;

import java.security.Signature;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Represents an entire source module (source file). Module elements need to be opened before they can be navigated or manipulated. The children
 * appear in the order in which they are declared in the source. If a file cannot be parsed, its structure remains unknown. Use
 * {@link IModelElement#isStructureKnown} to determine whether this is the case.
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 */
public interface ISourceModule extends IModule, ISourceReference,ISourceManipulation,ICodeAssist
{
	/**
	 * Commits the contents of this working copy to its underlying resource.
	 *
	 * <p>It is possible that the contents of the original resource have changed
	 * since this working copy was created, in which case there is an update conflict.
	 * The value of the <code>force</code> parameter effects the resolution of
	 * such a conflict:<ul>
	 * <li> <code>true</code> - in this case the contents of this working copy are applied to
	 * 	the underlying resource even though this working copy was created before
	 *		a subsequent change in the resource</li>
	 * <li> <code>false</code> - in this case a {@link ModelException} is thrown</li>
	 * </ul>
	 * <p>
	 * A working copy can be created on a not-yet existing compilation
	 * unit. In particular, such a working copy can then be committed in order to create
	 * the corresponding compilation unit.
	 * </p>
	 * @param force a flag to handle the cases when the contents of the original resource have changed
	 * since this working copy was created
	 * @param monitor the given progress monitor
	 * @throws ModelException if this working copy could not commit. Reasons include:
	 * <ul>
	 * <li> A {@link org.eclipse.core.runtime.CoreException} occurred while updating an underlying resource
	 * <li> This element is not a working copy (INVALID_ELEMENT_TYPES)
	 * <li> A update conflict (described above) (UPDATE_CONFLICT)
	 * </ul>
	 *
	 */
	void commitWorkingCopy(boolean force, IProgressMonitor monitor) throws ModelException;
	/**
	 * Changes this compilation unit handle into a working copy. A new {@link IBuffer} is
	 * created using this compilation unit handle's owner. Uses the primary owner is none was
	 * specified when this compilation unit handle was created.
	 * <p>
	 * When switching to working copy mode, problems are reported to given 
	 * {@link IProblemRequestor}. Note that once in working copy mode, the given
	 * {@link IProblemRequestor} is ignored. Only the original {@link IProblemRequestor}
	 * is used to report subsequent problems.
	 * </p>
	 * <p>
	 * Once in working copy mode, changes to this compilation unit or its children are done in memory.
	 * Only the new buffer is affected. Using {@link #commitWorkingCopy(boolean, IProgressMonitor)}
	 * will bring the underlying resource in sync with this compilation unit.
	 * </p>
	 * <p>
	 * If this compilation unit was already in working copy mode, an internal counter is incremented and no
	 * other action is taken on this compilation unit. To bring this compilation unit back into the original mode 
	 * (where it reflects the underlying resource), {@link #discardWorkingCopy} must be call as many 
	 * times as {@link #becomeWorkingCopy(IProblemRequestor, IProgressMonitor)}.
	 * </p>
	 * 
	 * @param problemRequestor a requestor which will get notified of problems detected during
	 * 	reconciling as they are discovered. The requestor can be set to <code>null</code> indicating
	 * 	that the client is not interested in problems.
	 * @param monitor a progress monitor used to report progress while opening this compilation unit
	 * 	or <code>null</code> if no progress should be reported 
	 * @throws ModelException if this compilation unit could not become a working copy.
	 * @see #discardWorkingCopy()
	 *
	 */
	void becomeWorkingCopy(IProblemRequestor problemRequestor, IProgressMonitor monitor) throws ModelException;
	
	/**
	 * Changes this compilation unit in working copy mode back to its original mode.
	 * <p>
	 * This has no effect if this compilation unit was not in working copy mode.
	 * </p>
	 * <p>
	 * If {@link #becomeWorkingCopy} was called several times on this compilation unit, {@link #discardWorkingCopy} must be called as many times
	 * before it switches back to the original mode.
	 * </p>
	 * 
	 * @throws ModelException
	 *                 if this working copy could not return in its original mode.
	 * @see #becomeWorkingCopy(IProblemRequestor, IProgressMonitor)
	 */
	void discardWorkingCopy( ) throws ModelException;

	/**
	 * Returns the source code associated with this element. This extracts the substring from the source buffer containing this source element.
	 * This corresponds to the source range that would be returned by <code>getSourceRange</code>.
	 * 
	 * @return the source code, or <code>null</code> if this element has no associated source code
	 * @exception ModelException
	 *                    if an exception occurs while accessing its corresponding resource
	 */
	String getSource( ) throws ModelException;
	
	/**
	 * Returns the source code associated with this element. This extracts the substring from the source buffer containing this source element.
	 * This corresponds to the source range that would be returned by <code>getSourceRange</code>.
	 * 
	 * @return the source code, or <code>null</code> if this element has no associated source code
	 * @exception ModelException
	 *                    if an exception occurs while accessing its corresponding resource
	 */
	char[] getSourceAsCharArray() throws ModelException;

	/**
	 * Returns a new working copy of this compilation unit if it is a primary compilation unit, or this compilation unit if it is already a
	 * non-primary working copy.
	 * <p>
	 * Note: if intending to share a working copy amongst several clients, then
	 * {@link #getWorkingCopy(WorkingCopyOwner, IProblemRequestor, IProgressMonitor)} should be used instead.
	 * </p>
	 * <p>
	 * When the working copy instance is created, an ADDED IModelElementDelta is reported on this working copy.
	 * </p>
	 * <p>
	 * Once done with the working copy, users of this method must discard it using {@link #discardWorkingCopy()}.
	 * </p>
	 * <p>
	 * A working copy can be created on a not-yet existing compilation unit. In particular, such a working copy can then be committed
	 * in order to create the corresponding compilation unit.
	 * </p>
	 * 
	 * @param monitor
	 *                a progress monitor used to report progress while opening this compilation unit or <code>null</code> if no progress should
	 *                be reported
	 * @throws ModelException
	 *                 if the contents of this element can not be determined.
	 * @return a new working copy of this element if this element is not a working copy, or this element if this element is already a working copy
	 */
	ISourceModule getWorkingCopy( IProgressMonitor monitor ) throws ModelException;
	
	ISourceModule getWorkingCopy(WorkingCopyOwner owner, IProblemRequestor problemRequestor, IProgressMonitor monitor) throws ModelException;

	/**
	 * Reconciles the contents of this working copy, sends out a model delta
	 * notification indicating the nature of the change of the working copy since
	 * the last time it was either reconciled or made consistent 
	 * ({@link IOpenable#makeConsistent(IProgressMonitor)}), and returns a
	 * compilation unit AST if requested.
	 * <p>
	 * It performs the reconciliation by locally caching the contents of 
	 * the working copy, updating the contents, then creating a delta 
	 * over the cached contents and the new contents, and finally firing
	 * this delta.
	 * <p>
	 * The boolean argument allows to force problem detection even if the
	 * working copy is already consistent.
	 * </p>
	 * <p>
	 * This functionality allows to specify a working copy owner which is used
	 * during problem detection. All references contained in the working copy are
	 * resolved against other units; for which corresponding owned working copies
	 * are going to take precedence over their original compilation units. If
	 * <code>null</code> is passed in, then the primary working copy owner is used.
	 * </p>
	 * <p>
	 * Compilation problems found in the new contents are notified through the
	 * {@link IProblemRequestor} interface which was passed at
	 * creation, and no longer as transient markers.
	 * </p>
	 * <p>
	 * Note:  added/removed/changed inner types generate change deltas.
	 * </p>
	 * <p>
	 * If requested, a DOM AST representing the compilation unit is returned.
	 * Its bindings are computed only if the problem requestor is active, or if the
	 * problem detection is forced. This method returns <code>null</code> if the
	 * creation of the DOM AST was not requested, or if the requested level of AST
	 * API is not supported, or if the working copy was already consistent.
	 * </p>
	 *
	 * @param astLevel either {@link #NO_AST} if no AST is wanted,
	 * or the of the AST if one is wanted
	 * @param forceProblemDetection boolean indicating whether problem should be 
	 *   recomputed even if the source hasn't changed
	 * @param owner the owner of working copies that take precedence over the 
	 *   original compilation units, or <code>null</code> if the primary working
	 *   copy owner should be used
	 * @param monitor a progress monitor
	 * @return the compilation unit AST or <code>null</code> if not requested, 
	 *    or if the requested level of AST API is not supported,
	 *    or if the working copy was consistent
	 * @throws ModelException if the contents of the original element
	 *		cannot be accessed. Reasons include:
	 * <ul>
	 * <li> The original script element does not exist (ELEMENT_DOES_NOT_EXIST)</li>
	 * </ul>
	 */
	void reconcile(  boolean forceProblemDetection, WorkingCopyOwner owner, IProgressMonitor monitor ) throws ModelException;
	/**
	 * Returns the primary compilation unit (whose owner is the primary owner)
	 * this working copy was created from, or this compilation unit if this a primary
	 * compilation unit.
	 * <p>
	 * Note that the returned primary compilation unit can be in working copy mode.
	 * </p>
	 * 
	 * @return the primary compilation unit this working copy was created from,
	 * or this compilation unit if it is primary
	 */
	public ISourceModule getPrimary();
	
	/**
	 * Returns the smallest element within this compilation unit that 
	 * includes the given source position (that is, a method, field, etc.), or
	 * <code>null</code> if there is no element other than the compilation
	 * unit itself at the given position, or if the given position is not
	 * within the source range of this compilation unit.
	 *
	 * @param position a source position inside the compilation unit
	 * @return the innermost script element enclosing a given source position or <code>null</code>
	 *	if none (excluding the compilation unit).
	 * @throws ModelException if the compilation unit does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 */
	IModelElement getElementAt(int position) throws ModelException;
	
	/**
	 * Returns the working copy owner of this working copy.
	 * Returns null if it is not a working copy or if it has no owner.
	 * 
	 * @return WorkingCopyOwner the owner of this working copy or <code>null</code>
	 *
	 */
	WorkingCopyOwner getOwner();
	/**
	 * Returns whether this element is a working copy.
	 * 
	 * @return true if this element is a working copy, false otherwise
	 *
	 */
	boolean isWorkingCopy();
	
	/**
	 * Returns the top-level type declared in this compilation unit with the given simple type name.
	 * The type name has to be a valid compilation unit name.
	 * This is a handle-only method. The type may or may not exist.
	 *
	 * @param name the simple name of the requested type in the compilation unit
	 * @return a handle onto the corresponding type. The type may or may not exist.
	 */
	IType getType(String name);
	/**
	 * Returns the top-level types declared in this compilation unit
	 * in the order in which they appear in the source.
	 *
	 * @return the top-level types declared in this compilation unit
	 * @throws ModelException if this element does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 */
	IType[] getTypes() throws ModelException;
	
	IType[] getAllTypes() throws ModelException;
	
	/**
	 * Read only source module. From external resources.
	 */
	boolean isReadOnly();
	
	boolean isPrimary();
	
	/**
	 * Returns the first package declaration in this compilation unit with the given package name
	 * (there normally is at most one package declaration).
	 * This is a handle-only method. The package declaration may or may not exist.
	 *
	 * @param name the name of the package declaration as defined by JLS2 7.4. (For example, <code>"java.lang"</code>)
	 * @return the first package declaration in this compilation unit with the given package name
	 */
	IPackageDeclaration getPackageDeclaration(String name);
	/**
	 * Returns the package declarations in this compilation unit
	 * in the order in which they appear in the source.
	 * There normally is at most one package declaration.
	 *
	 * @return an array of package declaration (normally of size one)
	 *
	 * @throws ModelException if this element does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 */
	IPackageDeclaration[] getPackageDeclarations() throws ModelException;
	
	/**
	 * Returns the method with the specified name and parameter types
	 * in this type (for example, <code>"foo", {"I", "QString;"}</code>).
	 * To get the handle for a constructor, the name specified must be the
	 * simple name of the enclosing type.
	 * This is a handle-only method.  The method may or may not be present.
	 * <p>
	 * The type signatures may be either unresolved (for source types)
	 * or resolved (for binary types), and either basic (for basic types)
	 * or rich (for parameterized types).
	 * </p>
	 * 
	 * @param name the given name
	 * @return the method with the specified name and parameter types in this type
	 */
	IMethod getMethod(String name);
	
	IField getField(String string);
	
	IField[] getFields() throws ModelException;
	
	boolean isBuiltin();
}