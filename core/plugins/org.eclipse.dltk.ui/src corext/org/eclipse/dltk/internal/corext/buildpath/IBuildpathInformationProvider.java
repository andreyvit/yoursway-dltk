/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.internal.corext.buildpath;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.IAddArchivesQuery;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.IAddLibrariesQuery;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.ICreateFolderQuery;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.IInclusionExclusionQuery;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.ILinkToQuery;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.IRemoveLinkedFolderQuery;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;



/**
 * Interface representing a information provider for
 * operations. The interface allows the operation to get 
 * information about the current state and to callback on 
 * the provider if the result of an operation needs to be handled.
 * 

 */
public interface IBuildpathInformationProvider {
    public static final int ADD_SEL_SF_TO_BP= 0;
    public static final int REMOVE_FROM_BP= 1;
    public static final int EXCLUDE= 2;
    public static final int UNEXCLUDE= 3;
    public static final int EDIT_FILTERS= 4;
    public static final int CREATE_LINK= 5;
    public static final int RESET_ALL= 6; 
    public static final int RESET= 7;
    public static final int INCLUDE= 8;
    public static final int UNINCLUDE= 9;
    public static final int CREATE_FOLDER= 10;
    public static final int ADD_ZIP_TO_BP= 11;
    public static final int ADD_LIB_TO_BP= 12;
    public static final int ADD_SEL_LIB_TO_BP= 13;
    
    /**
     * Method to invoce the <code>IBuildpathInformationProvider</code> to 
     * process the result of the corresponding operation. Normally, operations 
     * call this method at the end of their computation an pass the result 
     * back to the provider.
     * 
     * @param resultElements the result list of an operation, can be empty
     * @param exception an exception object in case that an exception occurred, 
     * <code>null</code> otherwise. Note: clients should check the exception
     * object before processing the result because otherwise, the result might be
     * incorrect
     * @param operationType constant to specify which kind of operation was executed;
     * corresponds to one of the following constants of <code>IBuildpathInformationProvider</code>:
     * <li>CREATE_FOLDER</li>
     * <li>ADD_TO_BP</li>
     * <li>REMOVE_FROM_BP</li>
     * <li>INCLUDE</li>
     * <li>UNINCLUDE</li>
     * <li>EXCLUDE</li>
     * <li>UNEXCLUDE</li>
     * <li>EDIT</li>
     * <li>RESET</li>
     * <li>CREATE_OUTPUT</li>
     */
    void handleResult(List resultElements, CoreException exception, int operationType);
    
    /**
     * Method to retrieve the current list of selected elements of the provider, this is 
     * the objects on which the operation should be executed on.
     * 
     * For example: if a tree item is selected and an operation should be 
     * executed on behalf of this item, then <code>getSelection()</code> 
     * should return this item. 
     * 
     * @return the current list of selected elements from the provider, must not be 
     * <code>null</code>
     */
    IStructuredSelection getSelection();
    
    /**
     * Method to retrieve the script project from the provider.
     * 
     * @return the current script project, must not be <code>null</code>
     */
    IScriptProject getScriptProject();           
    /**
     * Method to retrieve an <code>IInclusionExclusionQuery</code> from 
     * the provider.
     * 
     * @return an <code>IInclusionExclusionQuery</code>, must not be 
     * <code>null</code>
     * @throws ModelException
     * 
     * @see BuildpathModifierQueries#getDefaultInclusionExclusionQuery(Shell)
     */
    IInclusionExclusionQuery getInclusionExclusionQuery() throws ModelException;
       
    
    /**
     * Method to retrieve an <code>ILinkToQuery</code> from 
     * the provider.
     * 
     * @return an <code>ILinkToQuery</code>, must not be 
     * <code>null</code>
     * @throws ModelException
     * 
     * @see BuildpathModifierQueries#getDefaultCreateFolderQuery(Shell, IScriptProject)
     */
    ILinkToQuery getLinkFolderQuery() throws ModelException;
    
    /**
     * Method to retrieve an <code>IRemoveLinkedFolderQuery</code> from 
     * the provider.
     * 
     * @return an <code>IRemoveLinkedFolderQuery</code>, must not be 
     * <code>null</code>
     * @throws ModelException
     * 
     * @see BuildpathModifierQueries#getDefaultRemoveLinkedFolderQuery(Shell)
     */
    IRemoveLinkedFolderQuery getRemoveLinkedFolderQuery() throws ModelException;
    
    /**
     * Method to retrieve an <code>IAddArchivesQuery</code> from 
     * the provider.
     * 
     * @return an <code>IAddArchivesQuery</code>, must not be 
     * <code>null</code>
     * @throws ModelException
     * 
     * @see BuildpathModifierQueries#getDefaultArchivesQuery(Shell)
     */
    IAddArchivesQuery getExternalArchivesQuery() throws ModelException;
    
    /**
     * Method to retrieve an <code>IAddLibrariesQuery</code> from 
     * the provider.
     * 
     * @return an <code>IAddLibrariesQuery</code>, must not be 
     * <code>null</code>
     * @throws ModelException
     * 
     * @see BuildpathModifierQueries#getDefaultLibrariesQuery(Shell)
     */
    IAddLibrariesQuery getLibrariesQuery() throws ModelException;

    /**
     * Method to retrieve an <code>ICreateFolderQuery</code> from 
     * the provider.
     * 
     * @return an <code>IFolderCreationQuery</code>, must not be 
     * <code>null</code>
     * @throws ModelException
     * 
     * @see BuildpathModifierQueries#getDefaultCreateFolderQuery(Shell, IScriptProject)
     */
	ICreateFolderQuery getCreateFolderQuery() throws ModelException;
    
    /**
     * Delete all newly created folders and files.
     * Resources that existed before will not be 
     * deleted. It is assumed that the implementor of 
     * this interface knows which resources have been 
     * created and therefore is also able to remove 
     * all of them.
     */
    void deleteCreatedResources();
}
