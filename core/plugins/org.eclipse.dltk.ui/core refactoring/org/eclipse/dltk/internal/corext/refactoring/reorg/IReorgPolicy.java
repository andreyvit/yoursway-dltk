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
package org.eclipse.dltk.internal.corext.refactoring.reorg;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.tagging.IQualifiedNameUpdating;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;
import org.eclipse.ltk.core.refactoring.participants.ReorgExecutionLog;
import org.eclipse.ltk.core.refactoring.participants.SharableParticipants;


public interface IReorgPolicy extends IReorgEnablementPolicy, IQualifiedNameUpdating {
	public RefactoringStatus checkFinalConditions(IProgressMonitor pm, CheckConditionsContext context, IReorgQueries reorgQueries) throws CoreException;
	public RefactoringStatus setDestination(IResource resource) throws ModelException;
	public RefactoringStatus setDestination(IModelElement modelElement) throws ModelException;
	
	public boolean canChildrenBeDestinations(IResource resource);
	public boolean canChildrenBeDestinations(IModelElement modelElement);
	public boolean canElementBeDestination(IResource resource);
	public boolean canElementBeDestination(IModelElement modelElement);
	
	public IResource[] getResources();
	public IModelElement[] getScriptElements();
	
	public IResource getResourceDestination();
	public IModelElement getScriptElementDestination();
	
	public boolean hasAllInputSet();

	public boolean canUpdateReferences();
	public void setUpdateReferences(boolean update);
	public boolean getUpdateReferences();
	public boolean canUpdateQualifiedNames();
	
	public RefactoringParticipant[] loadParticipants(RefactoringStatus status, RefactoringProcessor processor, String[] natures, SharableParticipants shared) throws CoreException;
	
	public static interface ICopyPolicy extends IReorgPolicy{
		public Change createChange(IProgressMonitor pm, INewNameQueries copyQueries) throws ModelException;
		public ReorgExecutionLog getReorgExecutionLog();
	}
	public static interface IMovePolicy extends IReorgPolicy{
		public Change createChange(IProgressMonitor pm) throws ModelException;
		public Change postCreateChange(Change[] participantChanges, IProgressMonitor pm) throws CoreException;
		public ICreateTargetQuery getCreateTargetQuery(ICreateTargetQueries createQueries);
		public boolean isTextualMove();
	}
}
