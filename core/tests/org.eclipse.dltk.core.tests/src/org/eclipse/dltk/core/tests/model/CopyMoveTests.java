/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core.tests.model;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceManipulation;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;


abstract public class CopyMoveTests extends ModifyingResourceTests {
	public CopyMoveTests(String TestProjectName, String name) {
		super(TestProjectName, name);
	}

	/**
	 * Attempts to copy the element with optional forcing. The operation should
	 * fail with the failure code.
	 */
	public void copyNegative(IModelElement element, IModelElement destination,
			IModelElement sibling, String rename, boolean force, int failureCode) {
		try {
			((ISourceManipulation) element).copy(destination, sibling, rename,
					force, null);
		} catch (ModelException jme) {
			assertTrue("Code not correct for ModelException: " + jme, jme
					.getStatus().getCode() == failureCode);
			return;
		}
		assertTrue("The copy should have failed for: " + element, false);
		return;
	}

	/**
	 * Attempts to copy the elements with optional forcing. The operation should
	 * fail with the failure code.
	 */
	public void copyNegative(IModelElement[] elements,
			IModelElement[] destinations, IModelElement[] siblings,
			String[] renames, boolean force, int failureCode) {
		try {
			getScriptModel().copy(elements, destinations, siblings, renames,
					force, null);
		} catch (ModelException jme) {
			assertTrue("Code not correct for ModelException: " + jme, jme
					.getStatus().getCode() == failureCode);
			return;
		}
		assertTrue("The move should have failed for for multiple elements: ",
				false);
		return;
	}

	/**
	 * Copies the element to the container with optional rename and forcing. The
	 * operation should succeed, so any exceptions encountered are thrown.
	 */
	public IModelElement copyPositive(IModelElement element,
			IModelElement container, IModelElement sibling, String rename,
			boolean force) throws ModelException {
		// if forcing, ensure that a name collision exists
		if (force) {
			IModelElement collision = generateHandle(element, rename, container);
			assertTrue("Collision does not exist", collision.exists());
		}

		IModelElement copy;
		try {
			startDeltas();

			// copy
			((ISourceManipulation) element).copy(container, sibling, rename,
					force, null);

			// ensure the original element still exists
			assertTrue("The original element must still exist", element
					.exists());

			// generate the new element handle
			copy = generateHandle(element, rename, container);
			assertTrue("Copy should exist", copy.exists());

			// ensure correct position
			if (element.getElementType() > IModelElement.SOURCE_MODULE) {
				ensureCorrectPositioning((IParent) container, sibling, copy);
			} else {
				if (container.getElementType() == IModelElement.PROJECT_FRAGMENT) {
				} else {
					// ensure package name is correct
					//TODO: Add package declaration checks.
				}
			}
//			if (copy.getElementType() == IModelElement.IMPORT_DECLARATION)
//				container = ((ISourceModule) container).getImportContainer();
			//TODO: Add import declarations check.
			IModelElementDelta destDelta = getDeltaFor(container, true);
			assertTrue("Destination container not changed", destDelta != null
					&& destDelta.getKind() == IModelElementDelta.CHANGED);
			IModelElementDelta[] deltas = destDelta.getAddedChildren();
			assertTrue("Added children not correct for element copy", deltas[0]
					.getElement().equals(copy));
		} finally {
			stopDeltas();
		}
		return copy;
	}

	/**
	 * Generates a new handle to the original element in its new container.
	 */
	public IModelElement generateHandle(IModelElement original, String rename,
			IModelElement container) {
		String name = original.getElementName();
		if (rename != null) {
			name = rename;
		}
		switch (container.getElementType()) {
		case IModelElement.PROJECT_FRAGMENT:
			switch (original.getElementType()) {
			case IModelElement.SCRIPT_FOLDER:
				return ((IProjectFragment) container)
						.getScriptFolder(name);
			default:
				assertTrue("illegal child type", false);
				break;
			}
			break;
		case IModelElement.SCRIPT_FOLDER:
			switch (original.getElementType()) {
			case IModelElement.SOURCE_MODULE:
				return ((IScriptFolder) container).getSourceModule(name);
			default:
				assertTrue("illegal child type", false);
				break;
			}
			break;
		case IModelElement.SOURCE_MODULE:
			//TODO: Add import and package declarations check...
			switch (original.getElementType()) {
//			case IModelElement.IMPORT_DECLARATION:
//				return ((ISourceModule) container).getImport(name);
//			case IModelElement.PACKAGE_DECLARATION:
//				return ((ISourceModule) container)
//						.getPackageDeclaration(name);
			case IModelElement.TYPE:
				if (isMainType(original, container)) {
					// the cu has been renamed as well
					container = ((IScriptFolder) container.getParent())
							.getSourceModule(name + ".java");
				}
				return ((ISourceModule) container).getType(name);
			default:
				assertTrue("illegal child type", false);
				break;
			}
			break;
		case IModelElement.TYPE:
			switch (original.getElementType()) {
			case IModelElement.METHOD:
				if (name.equals(original.getParent().getElementName())) {
					// method is a constructor
					return ((IType) container).getMethod(container
							.getElementName());
				}
				return ((IType) container).getMethod(name);
			case IModelElement.FIELD:
				return ((IType) container).getField(name);
			case IModelElement.TYPE:
				return ((IType) container).getType(name);
//			case IModelElement.INITIALIZER:
//				// hack to return the first initializer
//				return ((IType) container).getInitializer(1);
			default:
				assertTrue("illegal child type", false);
				break;
			}
			break;
		default:
			assertTrue("unsupported container type", false);
			break;
		}
		assertTrue("should not get here", false);
		return null;
	}

	/**
	 * Returns true if this element is the main type of its compilation unit.
	 */
	protected boolean isMainType(IModelElement element, IModelElement parent) {
		if (parent instanceof ISourceModule) {
			if (element instanceof IType) {
				ISourceModule cu = (ISourceModule) parent;
				String typeName = cu.getElementName();
				typeName = typeName.substring(0, typeName.length() - 5);
				return element.getElementName().equals(typeName)
						&& element.getParent().equals(cu);
			}
		}
		return false;
	}

	/**
	 * Attempts to move the element with optional forcing. The operation should
	 * fail with the failure code.
	 */
	public void moveNegative(IModelElement element, IModelElement destination,
			IModelElement sibling, String rename, boolean force, int failureCode) {
		try {
			((ISourceManipulation) element).move(destination, sibling, rename,
					force, null);
		} catch (ModelException jme) {
			assertTrue("Code not correct for ModelException: " + jme, jme
					.getStatus().getCode() == failureCode);
			return;
		}
		assertTrue("The move should have failed for: " + element, false);
		return;
	}

	/**
	 * Attempts to move the element with optional forcing. The operation should
	 * fail with the failure code.
	 */
	public void moveNegative(IModelElement[] elements,
			IModelElement[] destinations, IModelElement[] siblings,
			String[] renames, boolean force, int failureCode) {
		try {
			getScriptModel().move(elements, destinations, siblings, renames,
					force, null);
		} catch (ModelException jme) {
			assertTrue("Code not correct for ModelException: " + jme, jme
					.getStatus().getCode() == failureCode);
			return;
		}
		assertTrue("The move should have failed for for multiple elements: ",
				false);
		return;
	}

	/**
	 * Moves the element to the container with optional rename and forcing. The
	 * operation should succeed, so any exceptions encountered are thrown.
	 */
	public void movePositive(IModelElement element, IModelElement container,
			IModelElement sibling, String rename, boolean force)
			throws ModelException {
		IModelElement[] siblings = new IModelElement[] { sibling };
		String[] renamings = new String[] { rename };
		if (sibling == null) {
			siblings = null;
		}
		if (rename == null) {
			renamings = null;
		}
		movePositive(new IModelElement[] { element },
				new IModelElement[] { container }, siblings, renamings, force);
	}

	/**
	 * Moves the elements to the containers with optional renaming and forcing.
	 * The operation should succeed, so any exceptions encountered are thrown.
	 */
	public void movePositive(IModelElement[] elements,
			IModelElement[] destinations, IModelElement[] siblings,
			String[] names, boolean force) throws ModelException {
		movePositive(elements, destinations, siblings, names, force, null);
	}

	/**
	 * Moves the elements to the containers with optional renaming and forcing.
	 * The operation should succeed, so any exceptions encountered are thrown.
	 */
	public void movePositive(IModelElement[] elements,
			IModelElement[] destinations, IModelElement[] siblings,
			String[] names, boolean force, IProgressMonitor monitor)
			throws ModelException {
		// if forcing, ensure that a name collision exists
		int i;
		if (force) {
			for (i = 0; i < elements.length; i++) {
				IModelElement e = elements[i];
				IModelElement collision = null;
				if (names == null) {
					collision = generateHandle(e, null, destinations[i]);
				} else {
					collision = generateHandle(e, names[i], destinations[i]);
				}
				assertTrue("Collision does not exist", collision.exists());
			}
		}

		try {
			startDeltas();

			// move
			getScriptModel().move(elements, destinations, siblings, names, force,
					monitor);
			for (i = 0; i < elements.length; i++) {
				IModelElement element = elements[i];
				IModelElement moved = null;
				if (names == null) {
					moved = generateHandle(element, null, destinations[i]);
				} else {
					moved = generateHandle(element, names[i], destinations[i]);
				}
				// ensure the original element no longer exists, unless moving
				// within the same container
				if (!destinations[i].equals(element.getParent())) {
					if (element.getElementType() == IModelElement.SCRIPT_FOLDER) {
						// moving a package fragment does not necessary mean
						// that it no longer exists
						// it could have members that are not part of the Script 
						// Model
						try {
							IResource[] members = ((IContainer) element
									.getUnderlyingResource()).members();
							if (members.length == 0) {
								assertTrue(
										"The original element must not exist",
										!element.exists());
							}
						} catch (CoreException ce) {
							throw new ModelException(ce);
						}
					} else {
						assertTrue("The original element must not exist",
								!element.exists());
					}
				}
				assertTrue("Moved element should exist", moved.exists());

				// ensure correct position
				if (element.getElementType() > IModelElement.SOURCE_MODULE) {
					if (siblings != null && siblings.length > 0) {
						ensureCorrectPositioning((IParent) moved.getParent(),
								siblings[i], moved);
					}
				} else {
					IModelElement container = destinations[i];
					if (container.getElementType() == IModelElement.PROJECT_FRAGMENT) {
					} else { // ensure package name is correct

						//TODO: Add import declarations checking.
					}
				}
				IModelElementDelta destDelta = null;
				if (isMainType(element, destinations[i]) && names != null
						&& names[i] != null) { // moved/renamed main type to
												// same cu
					destDelta = getDeltaFor(moved.getParent());
					assertTrue(
							"Renamed compilation unit as result of main type not added",
							destDelta != null
									&& destDelta.getKind() == IModelElementDelta.ADDED);
					assertTrue("flag should be F_MOVED_FROM", (destDelta
							.getFlags() & IModelElementDelta.F_MOVED_FROM) > 0);
					assertTrue("moved from handle should be original",
							destDelta.getMovedFromElement().equals(
									element.getParent()));
				} else {
					destDelta = getDeltaFor(destinations[i], true);
					assertTrue(
							"Destination container not changed",
							destDelta != null
									&& destDelta.getKind() == IModelElementDelta.CHANGED);
					IModelElementDelta[] deltas = destDelta.getAddedChildren();
					assertTrue("Added children not correct for element copy",
							deltas[i].getElement().equals(moved));
					assertTrue("should be K_ADDED",
							deltas[i].getKind() == IModelElementDelta.ADDED);
					IModelElementDelta sourceDelta = getDeltaFor(element, false);
					assertTrue("should be K_REMOVED",
							sourceDelta.getKind() == IModelElementDelta.REMOVED);
				}
			}
		} finally {
			stopDeltas();
		}
	}
}
