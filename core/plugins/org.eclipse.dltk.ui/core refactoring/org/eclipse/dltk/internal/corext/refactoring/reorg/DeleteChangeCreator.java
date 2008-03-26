/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring.reorg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceManipulation;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.internal.corext.refactoring.Checks;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringCoreMessages;
import org.eclipse.dltk.internal.corext.refactoring.changes.DeleteFileChange;
import org.eclipse.dltk.internal.corext.refactoring.changes.DeleteFolderChange;
import org.eclipse.dltk.internal.corext.refactoring.changes.DeleteFromBuildpathChange;
import org.eclipse.dltk.internal.corext.refactoring.changes.DeleteProjectFragmentChange;
import org.eclipse.dltk.internal.corext.refactoring.changes.DeleteSourceManipulationChange;
import org.eclipse.dltk.internal.corext.refactoring.changes.DynamicValidationStateChange;
import org.eclipse.dltk.internal.corext.refactoring.util.TextChangeManager;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;
import org.eclipse.ltk.core.refactoring.NullChange;
import org.eclipse.ltk.core.refactoring.TextFileChange;
import org.eclipse.text.edits.DeleteEdit;
import org.eclipse.text.edits.MultiTextEdit;



class DeleteChangeCreator {
	private DeleteChangeCreator() {
		//private
	}
	
	static Change createDeleteChange(TextChangeManager manager, IResource[] resources, IModelElement[] modelElements, String changeName) throws CoreException {
		final DynamicValidationStateChange result= new DynamicValidationStateChange(changeName) {
			public Change perform(IProgressMonitor pm) throws CoreException {
				super.perform(pm);
				return null;
			}
		};
		for (int i= 0; i < modelElements.length; i++) {
			IModelElement element= modelElements[i];
			if (! ReorgUtils.isInsideSourceModule(element))
				result.add(createDeleteChange(element));
		}

		for (int i= 0; i < resources.length; i++) {
			result.add(createDeleteChange(resources[i]));
		}
		
		Map grouped= ReorgUtils.groupBySourceModule(getElementsSmallerThanCu(modelElements));
		if (grouped.size() != 0 ){
			Assert.isNotNull(manager);
			for (Iterator iter= grouped.keySet().iterator(); iter.hasNext();) {
				ISourceModule cu= (ISourceModule) iter.next();
				Change change = createDeleteChange(cu, (List)grouped.get(cu), manager);
				if( change != null ) {
					result.add(change);
				}
			}
		}

		return result;
	}
	
	private static Change createDeleteChange(IResource resource) {
		Assert.isTrue(! (resource instanceof IWorkspaceRoot));//cannot be done
		Assert.isTrue(! (resource instanceof IProject)); //project deletion is handled by the workbench
		if (resource instanceof IFile)
			return new DeleteFileChange((IFile)resource, true);
		if (resource instanceof IFolder)
			return new DeleteFolderChange((IFolder)resource, true);
		Assert.isTrue(false);//there're no more kinds
		return null;
	}

	/*
	 * List<IModelElement> modelElements
	 */
	private static Change createDeleteChange(ISourceModule cu, List modelElements, TextChangeManager manager) throws CoreException {
//		SourceModule cuNode= RefactoringASTParser.parseWithASTProvider(cu, false, null);
//		SourceModuleRewrite rewriter= new SourceModuleRewrite(cu, cuNode);
	  Assert.isNotNull(cu);
	  Assert.isNotNull(modelElements);
	  Assert.isNotNull(manager);
      TextFileChange textFileChange = null;
	  if (cu != null && cu.getResource() instanceof IFile) {
	    textFileChange = new TextFileChange(cu.getElementName(), (IFile)cu.getResource());
	    MultiTextEdit fileChangeRootEdit = new MultiTextEdit();
	    textFileChange.setEdit(fileChangeRootEdit);

	    manager.manage(cu, textFileChange);

	    IModelElement[] elements= (IModelElement[]) modelElements.toArray(new IModelElement[modelElements.size()]);

		for (int cnt = 0, max = elements.length; cnt < max; cnt++) {
		  ISourceRange sourceRange = null;
		  if (elements[cnt] instanceof IMember) {
		    IMember type = (IMember)elements[cnt];
		    sourceRange = type.getSourceRange();
		  }
		  if (sourceRange != null) {
		    DeleteEdit edit = new DeleteEdit(sourceRange.getOffset(), sourceRange.getLength());
		    
		    fileChangeRootEdit.addChild(edit);
		    if (cu.isWorkingCopy()) {
		      textFileChange.setSaveMode(TextFileChange.LEAVE_DIRTY);
		    }

		  }
        }
	  }
		
		//		ASTNodeDeleteUtil.markAsDeleted(elements, rewriter, null);
//		return addTextEditFromRewrite(manager, cu, rewriter.getASTRewrite());
	  return textFileChange;
	}

//	private static TextChange addTextEditFromRewrite(TextChangeManager manager, ISourceModule cu, ASTRewrite rewrite) throws CoreException {
//		try {
//			ITextFileBuffer buffer= RefactoringFileBuffers.acquire(cu);
//			TextEdit resultingEdits= rewrite.rewriteAST(buffer.getDocument(), cu.getScriptProject().getOptions(true));
//			TextChange textChange= manager.get(cu);
//			if (textChange instanceof TextFileChange) {
//				TextFileChange tfc= (TextFileChange) textChange;
//				if (cu.isWorkingCopy())
//					tfc.setSaveMode(TextFileChange.LEAVE_DIRTY);
//			}
//			String message= RefactoringCoreMessages.DeleteChangeCreator_1; 
//			TextChangeCompatibility.addTextEdit(textChange, message, resultingEdits);
//			return textChange;
//		} finally {
//			RefactoringFileBuffers.release(cu);
//		}
//	}

	//List<IModelElement>
	private static List getElementsSmallerThanCu(IModelElement[] modelElements){
		List result= new ArrayList();
		for (int i= 0; i < modelElements.length; i++) {
			IModelElement element= modelElements[i];
			if (ReorgUtils.isInsideSourceModule(element))
				result.add(element);
		}
		return result;
	}

	private static Change createDeleteChange(IModelElement modelElement) {
		Assert.isTrue(! ReorgUtils.isInsideSourceModule(modelElement));
		
		switch(modelElement.getElementType()){
			case IModelElement.PROJECT_FRAGMENT:
				return createProjectFragmentDeleteChange((IProjectFragment)modelElement);

			case IModelElement.SCRIPT_FOLDER:				
				return createSourceManipulationDeleteChange((IScriptFolder)modelElement);

			case IModelElement.SOURCE_MODULE:				
				return createSourceManipulationDeleteChange((ISourceModule)modelElement);			

			case IModelElement.SCRIPT_MODEL: //cannot be done
				Assert.isTrue(false);
				return null;

			case IModelElement.SCRIPT_PROJECT: //handled differently
				Assert.isTrue(false);
				return null;

			case IModelElement.TYPE:
			case IModelElement.FIELD:
			case IModelElement.METHOD:
//			case IModelElement.INITIALIZER:
//			case IModelElement.PACKAGE_DECLARATION:
//			case IModelElement.IMPORT_CONTAINER:
//			case IModelElement.IMPORT_DECLARATION:
//				Assert.isTrue(false);//not done here
			default:
				Assert.isTrue(false);//there's no more kinds
				return new NullChange();
		}
	}

	private static Change createSourceManipulationDeleteChange(ISourceManipulation element) {
		//XXX workaround for bug 31384, in case of linked ISourceManipulation delete the resource
		if (element instanceof ISourceModule || element instanceof IScriptFolder){
			IResource resource;
			if (element instanceof ISourceModule)
				resource= ReorgUtils.getResource((ISourceModule)element);
			else 
				resource= ((IScriptFolder)element).getResource();
			if (resource != null && resource.isLinked())
				return createDeleteChange(resource);
		}
		return new DeleteSourceManipulationChange(element, true);
	}
	
	private static Change createProjectFragmentDeleteChange(IProjectFragment root) {
		IResource resource= root.getResource();
		if (resource != null && resource.isLinked()){
			//XXX using this code is a workaround for jcore bug 31998
			//jcore cannot handle linked stuff
			//normally, we should always create DeleteProjectFragmentChange
			CompositeChange composite= new DynamicValidationStateChange(RefactoringCoreMessages.DeleteRefactoring_delete_package_fragment_root); 
	
			composite.add(new DeleteFromBuildpathChange(root));
			Assert.isTrue(! Checks.isBuildpathDelete(root));//checked in preconditions
			composite.add(createDeleteChange(resource));
	
			return composite;
		} else {
			Assert.isTrue(! root.isExternal());
			// TODO remove the query argument
			return new DeleteProjectFragmentChange(root, true, null); 
		}
	}
}
