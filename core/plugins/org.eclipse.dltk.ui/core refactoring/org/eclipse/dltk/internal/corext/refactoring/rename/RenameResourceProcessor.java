/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring.rename;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.mapping.IResourceChangeDescriptionFactory;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringAvailabilityTester;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringCoreMessages;
import org.eclipse.dltk.internal.corext.refactoring.ScriptRefactoringArguments;
import org.eclipse.dltk.internal.corext.refactoring.ScriptRefactoringDescriptor;
import org.eclipse.dltk.internal.corext.refactoring.ScriptRefactoringDescriptorComment;
import org.eclipse.dltk.internal.corext.refactoring.changes.DynamicValidationStateChange;
import org.eclipse.dltk.internal.corext.refactoring.changes.RenameResourceChange;
import org.eclipse.dltk.internal.corext.refactoring.code.ScriptableRefactoring;
import org.eclipse.dltk.internal.corext.refactoring.participants.ResourceProcessors;
import org.eclipse.dltk.internal.corext.refactoring.tagging.ICommentProvider;
import org.eclipse.dltk.internal.corext.refactoring.tagging.INameUpdating;
import org.eclipse.dltk.internal.corext.refactoring.tagging.IScriptableRefactoring;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.internal.corext.util.Resources;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments;
import org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant;
import org.eclipse.ltk.core.refactoring.participants.RenameArguments;
import org.eclipse.ltk.core.refactoring.participants.RenameProcessor;
import org.eclipse.ltk.core.refactoring.participants.ResourceChangeChecker;
import org.eclipse.ltk.core.refactoring.participants.SharableParticipants;


public class RenameResourceProcessor extends RenameProcessor implements IScriptableRefactoring, ICommentProvider, INameUpdating {

	static final String ID_RENAME_RESOURCE= "org.eclipse.dltk.ui.rename.resource"; //$NON-NLS-1$
	private IResource fResource;
	private String fNewElementName;
	private String fComment;
	private RenameModifications fRenameModifications;
		
	public static final String IDENTIFIER= "org.eclipse.dltk.ui.renameResourceProcessor"; //$NON-NLS-1$
	
	/**
	 * Creates a new rename resource processor.
	 * @param resource the resource, or <code>null</code> if invoked by scripting
	 */
	public RenameResourceProcessor(IResource resource) {
		fResource= resource;
		if (resource != null) {
			setNewElementName(resource.getName());
		}
	}

	//---- INameUpdating ---------------------------------------------------
	
	public void setNewElementName(String newName) {
		Assert.isNotNull(newName);
		fNewElementName= newName;
	}

	public String getNewElementName() {
		return fNewElementName;
	}
	
	//---- IRenameProcessor methods ---------------------------------------
		
	public String getIdentifier() {
		return IDENTIFIER;
	}
	
	public boolean isApplicable() throws ModelException {
		return RefactoringAvailabilityTester.isRenameAvailable(fResource);
	}
	
	public String getProcessorName() {
		return RefactoringCoreMessages.RenameResourceProcessor_name;
	}
	
	public Object[] getElements() {
		return new Object[] {fResource};
	}
	
	public String getCurrentElementName() {
		return fResource.getName();
	}
	
	public String[] getAffectedProjectNatures() throws CoreException {
		return ResourceProcessors.computeAffectedNatures(fResource);
	}

	public Object getNewElement() {
		return ResourcesPlugin.getWorkspace().getRoot().findMember(createNewPath(getNewElementName()));
	}

	public boolean getUpdateReferences() {
		return true;
	}
	
	public RefactoringParticipant[] loadParticipants(RefactoringStatus status, SharableParticipants shared) throws CoreException {
		return fRenameModifications.loadParticipants(status, this, getAffectedProjectNatures(), shared);
	}
	
	//--- Condition checking --------------------------------------------

	public RefactoringStatus checkInitialConditions(IProgressMonitor pm) throws CoreException {
		return RefactoringStatus.create(Resources.checkInSync(fResource));
	}
	
	/* non java-doc
	 * @see IRenameRefactoring#checkNewName()
	 */
	public RefactoringStatus checkNewElementName(String newName) throws ModelException {
		Assert.isNotNull(newName, "new name"); //$NON-NLS-1$
		IContainer c= fResource.getParent();
		if (c == null)
			return RefactoringStatus.createFatalErrorStatus(RefactoringCoreMessages.RenameResourceRefactoring_Internal_Error); 
						
		if (c.findMember(newName) != null)
			return RefactoringStatus.createFatalErrorStatus(RefactoringCoreMessages.RenameResourceRefactoring_alread_exists); 
			
		if (!c.getFullPath().isValidSegment(newName))
			return RefactoringStatus.createFatalErrorStatus(RefactoringCoreMessages.RenameResourceRefactoring_invalidName); 
	
		RefactoringStatus result= RefactoringStatus.create(c.getWorkspace().validateName(newName, fResource.getType()));
		if (! result.hasFatalError())
			result.merge(RefactoringStatus.create(c.getWorkspace().validatePath(createNewPath(newName), fResource.getType())));		
		return result;		
	}
	
	/* non java-doc
	 * @see Refactoring#checkInput(IProgressMonitor)
	 */
	public RefactoringStatus checkFinalConditions(IProgressMonitor pm, CheckConditionsContext context) throws ModelException {
		pm.beginTask("", 1); //$NON-NLS-1$
		try{
			fRenameModifications= new RenameModifications();
			fRenameModifications.rename(fResource, new RenameArguments(getNewElementName(), getUpdateReferences()));
			
			ResourceChangeChecker checker= (ResourceChangeChecker) context.getChecker(ResourceChangeChecker.class);
			IResourceChangeDescriptionFactory deltaFactory= checker.getDeltaFactory();
			fRenameModifications.buildDelta(deltaFactory);
			
			return new RefactoringStatus();
		} finally{
			pm.done();
		}	
	}

	private String createNewPath(String newName){
		return fResource.getFullPath().removeLastSegments(1).append(newName).toString();
	}
		
	//--- changes 

	public Change createChange(IProgressMonitor pm) throws ModelException {
		pm.beginTask("", 1); //$NON-NLS-1$
		try {
			final Map arguments= new HashMap();
			String project= null;
			if (fResource.getType() != IResource.PROJECT)
				project= fResource.getProject().getName();
			final String header= Messages.format(RefactoringCoreMessages.RenameResourceChange_descriptor_description, new String[] { fResource.getFullPath().toString(), getNewElementName()});
			final String description= Messages.format(RefactoringCoreMessages.RenameResourceChange_descriptor_description_short, fResource.getName());
			final String comment= new ScriptRefactoringDescriptorComment(this, header).asString();
			final ScriptRefactoringDescriptor descriptor= new ScriptRefactoringDescriptor(RenameResourceProcessor.ID_RENAME_RESOURCE, project, description, comment, arguments, RefactoringDescriptor.STRUCTURAL_CHANGE | RefactoringDescriptor.MULTI_CHANGE | RefactoringDescriptor.BREAKING_CHANGE);
			arguments.put(ScriptRefactoringDescriptor.ATTRIBUTE_INPUT, ScriptRefactoringDescriptor.resourceToHandle(project, fResource));
			arguments.put(ScriptRefactoringDescriptor.ATTRIBUTE_NAME, getNewElementName());
			return new DynamicValidationStateChange(new RenameResourceChange(descriptor, fResource, getNewElementName(), comment));
		} finally {
			pm.done();
		}
	}

	public RefactoringStatus initialize(final RefactoringArguments arguments) {
		if (arguments instanceof ScriptRefactoringArguments) {
			final ScriptRefactoringArguments extended= (ScriptRefactoringArguments) arguments;
			final String handle= extended.getAttribute(ScriptRefactoringDescriptor.ATTRIBUTE_INPUT);
			if (handle != null) {
				fResource= ScriptRefactoringDescriptor.handleToResource(extended.getProject(), handle);
				if (fResource == null || !fResource.exists())
					return ScriptableRefactoring.createInputFatalStatus(fResource, getRefactoring().getName(), ID_RENAME_RESOURCE);
			} else
				return RefactoringStatus.createFatalErrorStatus(Messages.format(RefactoringCoreMessages.InitializableRefactoring_argument_not_exist, ScriptRefactoringDescriptor.ATTRIBUTE_INPUT));
			final String name= extended.getAttribute(ScriptRefactoringDescriptor.ATTRIBUTE_NAME);
			if (name != null && !"".equals(name)) //$NON-NLS-1$
				setNewElementName(name);
			else
				return RefactoringStatus.createFatalErrorStatus(Messages.format(RefactoringCoreMessages.InitializableRefactoring_argument_not_exist, ScriptRefactoringDescriptor.ATTRIBUTE_NAME));
		} else
			return RefactoringStatus.createFatalErrorStatus(RefactoringCoreMessages.InitializableRefactoring_inacceptable_arguments);
		return new RefactoringStatus();
	}

	public boolean canEnableComment() {
		return true;
	}

	public String getComment() {
		return fComment;
	}

	public void setComment(final String comment) {
		fComment= comment;
	}
}
