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

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.core.IModelStatusConstants;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.util.Messages;
import org.eclipse.dltk.internal.core.util.Util;


/**
 * This operation copies/moves/renames a collection of resources from their
 * current container to a new container, optionally renaming the elements.
 * <p>
 * Notes:
 * <ul>
 * <li>If there is already an resource with the same name in the new container,
 * the operation either overwrites or aborts, depending on the collision policy
 * setting. The default setting is abort.
 * 
 * <li>When a compilation unit is copied to a new package, the package
 * declaration in the compilation unit is automatically updated.
 * 
 * <li>The collection of elements being copied must all share the same type of
 * container.
 * 
 * <li>This operation can be used to copy and rename elements within the same
 * container.
 * 
 * <li>This operation only copies compilation units and package fragments. It
 * does not copy package fragment roots - a platform operation must be used for
 * that.
 * </ul>
 * 
 */
public class CopyResourceElementsOperation extends MultiOperation {
	/**
	 * The list of new resources created during this operation.
	 */
	protected ArrayList createdElements;
	/**
	 * Table specifying deltas for elements being copied/moved/renamed. Keyed by
	 * elements' project(s), and values are the corresponding deltas.
	 */
	protected Map deltasPerProject = new HashMap(1);

	// /**
	// * The <code>ASTParser</code> used to manipulate the source code of
	// * <code>ISourceModule</code>.
	// */
	// protected ASTParser parser;
	/**
	 * When executed, this operation will copy the given resources to the given
	 * container.
	 */
	public CopyResourceElementsOperation(IModelElement[] resourcesToCopy, IModelElement destContainer, boolean force) {
		this(resourcesToCopy, new IModelElement[] {
			destContainer
		}, force);
	}

	/**
	 * When executed, this operation will copy the given resources to the given
	 * containers. The resources and destination containers must be in the
	 * correct order. If there is > 1 destination, the number of destinations
	 * must be the same as the number of resources being copied/moved.
	 */
	public CopyResourceElementsOperation(IModelElement[] resourcesToCopy, IModelElement[] destContainers, boolean force) {
		super(resourcesToCopy, destContainers, force);
		// initializeASTParser();
	}

	// private void initializeASTParser() {
	// this.parser = ASTParser.newParser(AST.JLS3);
	// }
	/**
	 * Returns the children of <code>source</code> which are affected by this
	 * operation. If <code>source</code> is a <code>K_SOURCE</code>, these
	 * are the <code>.java</code> files, if it is a <code>K_BINARY</code>,
	 * they are the <code>.class</code> files.
	 */
	private IResource[] collectResourcesOfInterest(IScriptFolder source) throws ModelException {
		IModelElement[] children = source.getChildren();
		int childOfInterest = IModelElement.SOURCE_MODULE;
		// if (source.getKind() == IProjectFragment.K_BINARY) {
		// childOfInterest = IModelElement.CLASS_FILE;
		// }
		ArrayList correctKindChildren = new ArrayList(children.length);
		for (int i = 0; i < children.length; i++) {
			IModelElement child = children[i];
			if (child.getElementType() == childOfInterest) {
				correctKindChildren.add(child.getResource());
			}
		}
		// Gather non-java resources
		Object[] nonScriptResources = source.getForeignResources();
		int actualNonScriptResourceCount = 0;
		for (int i = 0, max = nonScriptResources.length; i < max; i++) {
			if (nonScriptResources[i] instanceof IResource)
				actualNonScriptResourceCount++;
		}
		IResource[] actualNonScriptResources = new IResource[actualNonScriptResourceCount];
		for (int i = 0, max = nonScriptResources.length, index = 0; i < max; i++) {
			if (nonScriptResources[i] instanceof IResource)
				actualNonScriptResources[index++] = (IResource) nonScriptResources[i];
		}
		if (actualNonScriptResourceCount != 0) {
			int correctKindChildrenSize = correctKindChildren.size();
			IResource[] result = new IResource[correctKindChildrenSize + actualNonScriptResourceCount];
			correctKindChildren.toArray(result);
			System.arraycopy(actualNonScriptResources, 0, result, correctKindChildrenSize, actualNonScriptResourceCount);
			return result;
		} else {
			IResource[] result = new IResource[correctKindChildren.size()];
			correctKindChildren.toArray(result);
			return result;
		}
	}

	/**
	 * Creates any destination package fragment(s) which do not exists yet.
	 * Return true if a read-only package fragment has been found among package
	 * fragments, false otherwise
	 */
	private boolean createNeededScriptFolders(IContainer sourceFolder, ProjectFragment root, IPath newFragName, boolean moveFolder)
			throws ModelException {
		boolean containsReadOnlyScriptFolder = false;
		IContainer parentFolder = (IContainer) root.getResource();
		ModelElementDelta projectDelta = null;
		IPath sideEffectPackageName = new Path("");
		char[][] inclusionPatterns = root.fullInclusionPatternChars();
		char[][] exclusionPatterns = root.fullExclusionPatternChars();
		for (int i = 0; i < newFragName.segmentCount(); i++) {
			String subFolderName = newFragName.segment(i);
			sideEffectPackageName = sideEffectPackageName.append(subFolderName);
			IResource subFolder = parentFolder.findMember(subFolderName);
			if (subFolder == null) {
				// create deepest folder only if not a move (folder will be
				// moved in processScriptFolderResource)
				if (!(moveFolder && i == newFragName.segmentCount() - 1)) {
					createFolder(parentFolder, subFolderName, force);
				}
				parentFolder = parentFolder.getFolder(new Path(subFolderName));
				sourceFolder = sourceFolder.getFolder(new Path(subFolderName));
				if (Util.isReadOnly(sourceFolder)) {
					containsReadOnlyScriptFolder = true;
				}
				IScriptFolder sideEffectPackage = root.getScriptFolder(sideEffectPackageName);
				if (i < newFragName.segmentCount() - 1 // all but the last one
														// are side effect
														// packages
						&& !Util.isExcluded(parentFolder, inclusionPatterns, exclusionPatterns)) {
					if (projectDelta == null) {
						projectDelta = getDeltaFor(root.getScriptProject());
					}
					projectDelta.added(sideEffectPackage);
				}
				createdElements.add(sideEffectPackage);
			} else {
				parentFolder = (IContainer) subFolder;
			}
		}
		return containsReadOnlyScriptFolder;
	}

	/**
	 * Returns the <code>ScriptElementDelta</code> for <code>scriptProject</code>,
	 * creating it and putting it in <code>fDeltasPerProject</code> if it does
	 * not exist yet.
	 */
	private ModelElementDelta getDeltaFor(IDLTKProject scriptProject) {
		ModelElementDelta delta = (ModelElementDelta) deltasPerProject.get(scriptProject);
		if (delta == null) {
			delta = new ModelElementDelta(scriptProject);
			deltasPerProject.put(scriptProject, delta);
		}
		return delta;
	}

	/**
	 * @see MultiOperation
	 */
	protected String getMainTaskName() {
		return Messages.operation_copyResourceProgress;
	}

	/**
	 * Sets the deltas to register the changes resulting from this operation for
	 * this source element and its destination. If the operation is a cross
	 * project operation
	 * <ul>
	 * <li>On a copy, the delta should be rooted in the dest project
	 * <li>On a move, two deltas are generated
	 * <ul>
	 * <li>one rooted in the source project
	 * <li>one rooted in the destination project
	 * </ul>
	 * </ul>
	 * If the operation is rooted in a single project, the delta is rooted in
	 * that project
	 * 
	 */
	protected void prepareDeltas(IModelElement sourceElement, IModelElement destinationElement, boolean isMove) {
		if (Util.isExcluded(sourceElement) || Util.isExcluded(destinationElement))
			return;
		IDLTKProject destProject = destinationElement.getScriptProject();
		if (isMove) {
			IDLTKProject sourceProject = sourceElement.getScriptProject();
			getDeltaFor(sourceProject).movedFrom(sourceElement, destinationElement);
			getDeltaFor(destProject).movedTo(destinationElement, sourceElement);
		} else {
			getDeltaFor(destProject).added(destinationElement);
		}
	}

	/**
	 * Copies/moves a compilation unit with the name <code>newCUName</code> to
	 * the destination package.<br>
	 * The package statement in the compilation unit is updated if necessary.
	 * The main type of the compilation unit is renamed if necessary.
	 * 
	 * @exception ScriptModelException
	 *                if the operation is unable to complete
	 */
	private void processSourceModuleResource(ISourceModule source, ScriptFolder dest) throws ModelException {
		String newCUName = getNewNameFor(source);
		String destName = (newCUName != null) ? newCUName : source.getElementName();
		// ASTRewrite rewrite = updateContent(source, dest, newCUName); // null
		// if unchanged
		// TODO (frederic) remove when bug 67606 will be fixed (bug 67823)
		// store encoding (fix bug 66898)
		IFile sourceResource = (IFile) source.getResource();
//		String sourceEncoding = null;
//		try {
//			if( sourceResource != null ) {
//				sourceEncoding = sourceResource.getCharset(false);
//			}
//		} catch (CoreException ce) {
//			// no problem, use default encoding
//		}
		// end todo
		// copy resource
		IContainer destFolder = (IContainer) dest.getResource(); // can be an
																	// IFolder
																	// or an
																	// IProject
		IFile destFile = destFolder.getFile(new Path(destName));
		SourceModule destCU = new SourceModule(dest, destName, DefaultWorkingCopyOwner.PRIMARY);
		if (sourceResource == null || !destFile.equals(sourceResource)) {
			try {
				if (!destCU.isWorkingCopy()) {
					if (destFile.exists()) {
						if (this.force) {
							// we can remove it
							deleteResource(destFile, IResource.KEEP_HISTORY);
							destCU.close(); // ensure the in-memory buffer for
											// the dest CU is closed
						} else {
							// abort
							throw new ModelException(new ModelStatus(IModelStatusConstants.NAME_COLLISION, Messages.bind(
									Messages.status_nameCollision, destFile.getFullPath().toString())));
						}
					}
					int flags = this.force ? IResource.FORCE : IResource.NONE;
					if (this.isMove()) {
						flags |= IResource.KEEP_HISTORY;
						if( sourceResource != null ) {
							sourceResource.move(destFile.getFullPath(), flags, getSubProgressMonitor(1));
						}
						else {
							if (DLTKCore.DEBUG) {
								System.err.println("TODO: Add correct status message here...");
							} 
							throw new ModelException(new ModelStatus(IModelStatusConstants.NAME_COLLISION, Messages.bind(
									Messages.status_invalidResource, destFile.getFullPath().toString())));
						}
					} else {
						// if (rewrite != null) flags |= IResource.KEEP_HISTORY;
						if( sourceResource == null ) {
							ByteArrayInputStream bais = new ByteArrayInputStream(new byte[0]);
							destFile.create(bais, IResource.FORCE, getSubProgressMonitor(1));							
							destCU.getBuffer().setContents(source.getBuffer().getContents());
							destCU.save(getSubProgressMonitor(1), true);
						}
						else {							
							sourceResource.copy(destFile.getFullPath(), flags, getSubProgressMonitor(1));
						}
					}
					this.setAttribute(HAS_MODIFIED_RESOURCE_ATTR, TRUE);
				} else {
					destCU.getBuffer().setContents(source.getBuffer().getContents());
				}
			} catch (ModelException e) {
				throw e;
			} catch (CoreException e) {
				throw new ModelException(e);
			}
			// update new resource content
			// if (rewrite != null){
			// boolean wasReadOnly = destFile.isReadOnly();
			// try {
			// saveContent(dest, destName, rewrite, sourceEncoding, destFile);
			// } catch (CoreException e) {
			// if (e instanceof ModelException) throw (ModelException) e;
			// throw new ModelException(e);
			// } finally {
			// Util.setReadOnly(destFile, wasReadOnly);
			// }
			// }
			// register the correct change deltas
			prepareDeltas(source, destCU, isMove());
			if (newCUName != null) {
				// the main type has been renamed
				if (DLTKCore.DEBUG) {
					System.err.println("TODO: Add remove extensions here...");
				}
				String oldName = /* Util.getNameWithoutScriptLikeExtension( */source.getElementName();// );
				String newName = /* Util.getNameWithoutScriptLikeExtension( */newCUName;// );
				prepareDeltas(source.getType(oldName), destCU.getType(newName), isMove());
			}
		} else {
			if (!this.force) {
				throw new ModelException(new ModelStatus(IModelStatusConstants.NAME_COLLISION, Messages.bind(Messages.status_nameCollision,
						destFile.getFullPath().toString())));
			}
			// update new resource content
			// in case we do a saveas on the same resource we have to simply
			// update the contents
			// see http://dev.eclipse.org/bugs/show_bug.cgi?id=9351
			// try {
			// if (rewrite != null){
			// saveContent(dest, destName, rewrite, sourceEncoding, destFile);
			// }
			// } catch (CoreException e) {
			// if (e instanceof ModelException) throw (ModelException) e;
			// throw new ModelException(e);
			// }
		}
	}

	/**
	 * Process all of the changed deltas generated by this operation.
	 */
	protected void processDeltas() {
		for (Iterator deltas = this.deltasPerProject.values().iterator(); deltas.hasNext();) {
			addDelta((IModelElementDelta) deltas.next());
		}
	}

	/**
	 * @see MultiOperation This method delegates to
	 *      <code>processSourceModuleResource</code> or
	 *      <code>processScriptFolderResource</code>, depending on the
	 *      type of <code>element</code>.
	 */
	protected void processElement(IModelElement element) throws ModelException {
		IModelElement dest = getDestinationParent(element);
		switch (element.getElementType()) {
			case IModelElement.SOURCE_MODULE:
				processSourceModuleResource((ISourceModule) element, (ScriptFolder) dest);
				createdElements.add(((IScriptFolder) dest).getSourceModule(element.getElementName()));
				break;
			case IModelElement.SCRIPT_FOLDER:
				processScriptFolderResource((ScriptFolder) element, (ProjectFragment) dest, getNewNameFor(element));
				break;
			default:
				throw new ModelException(new ModelStatus(IModelStatusConstants.INVALID_ELEMENT_TYPES, element));
		}
	}

	/**
	 * @see MultiOperation Overridden to allow special processing of
	 *      <code>ScriptElementDelta</code>s and <code>fResultElements</code>.
	 */
	protected void processElements() throws ModelException {
		createdElements = new ArrayList(elementsToProcess.length);
		try {
			super.processElements();
		} catch (ModelException jme) {
			throw jme;
		} finally {
			resultElements = new IModelElement[createdElements.size()];
			createdElements.toArray(resultElements);
			processDeltas();
		}
	}

	/**
	 * Copies/moves a package fragment with the name <code>newName</code> to
	 * the destination package.<br>
	 * 
	 * @exception ScriptModelException
	 *                if the operation is unable to complete
	 */
	private void processScriptFolderResource(ScriptFolder source, ProjectFragment root, String newName) throws ModelException {
		try {
			// String[] newFragName = (newName == null) ? source.path.segments()
			// : Util.getTrimmedSimpleNames(newName);
			IPath newFragName = (newName == null) ? source.path : new Path(newName);
			IScriptFolder newFrag = root.getScriptFolder(newFragName);
			IResource[] resources = collectResourcesOfInterest(source);
			// if isMove() can we move the folder itself ? (see
			// http://bugs.eclipse.org/bugs/show_bug.cgi?id=22458)
			boolean shouldMoveFolder = isMove() && newFrag.getResource() != null &&!newFrag.getResource().exists(); // if
																					// new
																					// pkg
																					// fragment
																					// exists,
																					// it
																					// is
																					// an
																					// override
			IFolder srcFolder = (IFolder) source.getResource();
			IPath destPath = newFrag.getPath();
			if (shouldMoveFolder) {
				// check if destination is not included in source
				if (srcFolder.getFullPath().isPrefixOf(destPath)) {
					shouldMoveFolder = false;
				} else {
					// check if there are no sub-packages
					IResource[] members = srcFolder.members();
					for (int i = 0; i < members.length; i++) {
						if (members[i] instanceof IFolder) {
							shouldMoveFolder = false;
							break;
						}
					}
				}
			}
			boolean containsReadOnlySubScriptFolders = createNeededScriptFolders((IContainer) source.getParent().getResource(), root,
					newFragName, shouldMoveFolder);
			boolean sourceIsReadOnly = Util.isReadOnly(srcFolder);
			// Process resources
			if (shouldMoveFolder) {
				// move underlying resource
				// TODO Revisit once bug 43044 is fixed
				if (sourceIsReadOnly ) {
					Util.setReadOnly(srcFolder, false);
				}
				srcFolder.move(destPath, force, true /* keep history */, getSubProgressMonitor(1));
				if (sourceIsReadOnly) {
					Util.setReadOnly(srcFolder, true);
				}
				this.setAttribute(HAS_MODIFIED_RESOURCE_ATTR, TRUE);
			} else {
				// process the leaf resources
				if (resources.length > 0) {
					if (isRename()) {
						if (!destPath.equals(source.getPath())) {
							moveResources(resources, destPath);
						}
					} else if (isMove()) {
						// we need to delete this resource if this operation
						// wants to override existing resources
						for (int i = 0, max = resources.length; i < max; i++) {
							IResource destinationResource = ResourcesPlugin.getWorkspace().getRoot().findMember(
									destPath.append(resources[i].getName()));
							if (destinationResource != null) {
								if (force) {
									deleteResource(destinationResource, IResource.KEEP_HISTORY);
								} else {
									throw new ModelException(new ModelStatus(IModelStatusConstants.NAME_COLLISION, Messages.bind(
											Messages.status_nameCollision, destinationResource.getFullPath().toString())));
								}
							}
						}
						moveResources(resources, destPath);
					} else {
						// we need to delete this resource if this operation
						// wants to override existing resources
						for (int i = 0, max = resources.length; i < max; i++) {
							IResource destinationResource = ResourcesPlugin.getWorkspace().getRoot().findMember(
									destPath.append(resources[i].getName()));
							if (destinationResource != null) {
								if (force) {
									// we need to delete this resource if this
									// operation wants to override existing
									// resources
									deleteResource(destinationResource, IResource.KEEP_HISTORY);
								} else {
									throw new ModelException(new ModelStatus(IModelStatusConstants.NAME_COLLISION, Messages.bind(
											Messages.status_nameCollision, destinationResource.getFullPath().toString())));
								}
							}
						}
						copyResources(resources, destPath);
					}
				}
			}
			// Update package statement in compilation unit if needed
			if (!Util.equalArraysOrNull(new Object[] {
				newFragName
			}, new Object[] {
				source.path
			})) { // if package has been renamed, update the compilation units
				char[][] inclusionPatterns = root.fullInclusionPatternChars();
				char[][] exclusionPatterns = root.fullExclusionPatternChars();
				for (int i = 0; i < resources.length; i++) {
					String resourceName = resources[i].getName();
					IDLTKLanguageToolkit toolkit = DLTKLanguageManager.getLanguageToolkit(newFrag);
					if (toolkit != null && toolkit.validateSourceModule(resources[i]).getSeverity() != IStatus.ERROR) {
						// we only consider potential compilation units
						ISourceModule cu = newFrag.getSourceModule(resourceName);
						if (Util.isExcluded(cu.getPath(), inclusionPatterns, exclusionPatterns, false/*
																										 * not
																										 * a
																										 * folder
																										 */))
							continue;
						if (DLTKCore.DEBUG) {
							System.err.println("TODO:Add source module modification code here...");
						}
						// this.parser.setSource(cu);
						// SourceModule astCU = (SourceModule)
						// this.parser.createAST(this.progressMonitor);
						// AST ast = astCU.getAST();
						// ASTRewrite rewrite = ASTRewrite.create(ast);
						// updatePackageStatement(astCU, newFragName, rewrite);
						// IDocument document = getDocument(cu);
						// TextEdit edits = rewrite.rewriteAST(document, null);
						// try {
						// edits.apply(document);
						// } catch (BadLocationException e) {
						// throw new ModelException(e,
						// IModelStatusConstants.INVALID_CONTENTS);
						// }
						cu.save(null, false);
					}
				}
			}
			// Discard empty old package (if still empty after the rename)
			boolean isEmpty = true;
			if (isMove() && srcFolder != null) {
				// delete remaining files in this package (.class file in the
				// case where Proj=src=bin)
				// in case of a copy
				updateReadOnlyScriptFoldersForMove((IContainer) source.getParent().getResource(), root, newFragName, sourceIsReadOnly);
				if (srcFolder.exists()) {
					IResource[] remaining = srcFolder.members();
					for (int i = 0, length = remaining.length; i < length; i++) {
						IResource file = remaining[i];
						if (file instanceof IFile) {
							if (Util.isReadOnly(file)) {
								Util.setReadOnly(file, false);
							}
							this.deleteResource(file, IResource.FORCE | IResource.KEEP_HISTORY);
						} else {
							isEmpty = false;
						}
					}
				}
				if (isEmpty) {
					IResource rootResource;
					// check if source is included in destination
					if (destPath.isPrefixOf(srcFolder.getFullPath())) {
						rootResource = newFrag.getResource();
					} else {
						rootResource = source.getParent().getResource();
					}
					// delete recursively empty folders
					deleteEmptyScriptFolder(source, false, rootResource);
				}
			} else if (containsReadOnlySubScriptFolders) {
				// in case of a copy
				updateReadOnlyScriptFoldersForCopy((IContainer) source.getParent().getResource(), root, newFragName);
			}
			// workaround for bug
			// https://bugs.eclipse.org/bugs/show_bug.cgi?id=24505
			if (isEmpty && isMove() && !(Util.isExcluded(source) || Util.isExcluded(newFrag))) {
				IDLTKProject sourceProject = source.getScriptProject();
				getDeltaFor(sourceProject).movedFrom(source, newFrag);
				IDLTKProject destProject = newFrag.getScriptProject();
				getDeltaFor(destProject).movedTo(newFrag, source);
			}
		} catch (ModelException e) {
			throw e;
		} catch (CoreException ce) {
			throw new ModelException(ce);
		}
	}

	private void updateReadOnlyScriptFoldersForCopy(IContainer sourceFolder, IProjectFragment root, IPath newFragName) {
		IContainer parentFolder = (IContainer) root.getResource();
		for (int i = 0, length = newFragName.segmentCount(); i < length; i++) {
			String subFolderName = newFragName.segment(i);
			parentFolder = parentFolder.getFolder(new Path(subFolderName));
			sourceFolder = sourceFolder.getFolder(new Path(subFolderName));
			if (sourceFolder.exists() && Util.isReadOnly(sourceFolder)) {
				Util.setReadOnly(parentFolder, true);
			}
		}
	}

	private void updateReadOnlyScriptFoldersForMove(IContainer sourceFolder, IProjectFragment root, IPath newFragName,
			boolean sourceFolderIsReadOnly) {
		IContainer parentFolder = (IContainer) root.getResource();
		for (int i = 0, length = newFragName.segmentCount(); i < length; i++) {
			String subFolderName = newFragName.segment(i);
			parentFolder = parentFolder.getFolder(new Path(subFolderName));
			sourceFolder = sourceFolder.getFolder(new Path(subFolderName));
			if ((sourceFolder.exists() && Util.isReadOnly(sourceFolder)) || (i == length - 1 && sourceFolderIsReadOnly)) {
				Util.setReadOnly(parentFolder, true);
				// the source folder will be deleted anyway (move operation)
				Util.setReadOnly(sourceFolder, false);
			}
		}
	}

	/**
	 * Possible failures:
	 * <ul>
	 * <li>NO_ELEMENTS_TO_PROCESS - no elements supplied to the operation
	 * <li>INDEX_OUT_OF_BOUNDS - the number of renamings supplied to the
	 * operation does not match the number of elements that were supplied.
	 * </ul>
	 */
	protected IModelStatus verify() {
		IModelStatus status = super.verify();
		if (!status.isOK()) {
			return status;
		}
		if (this.renamingsList != null && this.renamingsList.length != elementsToProcess.length) {
			return new ModelStatus(IModelStatusConstants.INDEX_OUT_OF_BOUNDS);
		}
		return ModelStatus.VERIFIED_OK;
	}

	/**
	 * @see MultiOperation
	 */
	protected void verify(IModelElement element) throws ModelException {
		if (element == null || !element.exists())
			error(IModelStatusConstants.ELEMENT_DOES_NOT_EXIST, element);
		if (element.isReadOnly() && (isRename() || isMove()))
			error(IModelStatusConstants.READ_ONLY, element);
		IResource resource = element.getResource();
		if (resource instanceof IFolder) {
			if (resource.isLinked()) {
				error(IModelStatusConstants.INVALID_RESOURCE, element);
			}
		}
		int elementType = element.getElementType();
		if (elementType == IModelElement.SOURCE_MODULE && element instanceof SourceModule) {
			SourceModule compilationUnit = (SourceModule) element;
			if (isMove() && compilationUnit.isWorkingCopy() && !compilationUnit.isPrimary())
				error(IModelStatusConstants.INVALID_ELEMENT_TYPES, element);			
		} 
		else if(elementType== IModelElement.SOURCE_MODULE && element instanceof ExternalSourceModule ) {
			if (isMove())
				error(IModelStatusConstants.INVALID_ELEMENT_TYPES, element);
		}
		else if (elementType != IModelElement.SCRIPT_FOLDER) {
			error(IModelStatusConstants.INVALID_ELEMENT_TYPES, element);
		}
		ModelElement dest = (ModelElement) getDestinationParent(element);
		verifyDestination(element, dest);
		if (this.renamings != null) {
			verifyRenaming(element);
		}
	}
}
