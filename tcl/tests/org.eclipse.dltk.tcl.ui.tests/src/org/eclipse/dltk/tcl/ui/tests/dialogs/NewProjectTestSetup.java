/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.ui.tests.dialogs;

import java.util.ArrayList;
import java.util.List;

import junit.extensions.TestSetup;
import junit.framework.Test;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.util.CoreUtility;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.tests.ScriptProjectHelper;
import org.eclipse.dltk.ui.tests.TestOptions;



public class NewProjectTestSetup extends TestSetup {

    public static final String WORKSPACE_PROJECT= "WorkspaceProject";
    public static final String WORKSPACE_PROJECT_SRC= "WorkspaceProjectWithSourceAndInFolder";
    
    public static IScriptProject getProject(String projectName) throws CoreException {
        return ScriptProjectHelper.createDLTKProject(projectName);
    }
    
//    public static IBuildpathEntry[] getDefaultBuildpath() {
//        //return PreferenceConstants.getDefaultInterpreterEnvironmentLibrary();
//    	return null;
//    }
    
    private IScriptProject fWorkspaceProject;
    private IScriptProject fWorkspaceProjectWithSrc;
    private IScriptProject fExternalProject;

    private boolean fAutobuilding;
    
    public NewProjectTestSetup(Test test) {
        super(test);
        try {
            fAutobuilding= ScriptProjectHelper.setAutoBuilding(false);
        } catch (CoreException e) {
            DLTKUIPlugin.log(e);
        }
    }
    
    public IScriptProject getProject(IScriptProject currentProject) throws CoreException {
        String name= currentProject.getElementName();
        currentProject.getProject().delete(true, null);
        if (name.equals(WORKSPACE_PROJECT))
            return getWorkspaceProject();
        if (name.equals(WORKSPACE_PROJECT_SRC))
            return getWorkspaceProjectWithSrc();
        return null;
    }
    
    public IScriptProject getWorkspaceProject() {
        try {
            fWorkspaceProject= getProject(WORKSPACE_PROJECT);
            List cpEntries= new ArrayList();
            IPath projectPath= fWorkspaceProject.getProject().getFullPath();
            cpEntries.add(DLTKCore.newSourceEntry(projectPath));
            //cpEntries.addAll(Arrays.asList(getDefaultBuildpath()));
            IBuildpathEntry[] entries= (IBuildpathEntry[]) cpEntries.toArray(new IBuildpathEntry[cpEntries.size()]);
            fWorkspaceProject.setRawBuildpath(entries, new NullProgressMonitor());
        } catch (ModelException e) {
            DLTKUIPlugin.log(e);
        } catch (CoreException e) {
            DLTKUIPlugin.log(e);
        }
        return fWorkspaceProject;
    }
    
    public IScriptProject getWorkspaceProjectWithSrc() {
        try {
            fWorkspaceProjectWithSrc= getProject(WORKSPACE_PROJECT_SRC);
            createWithSrcAndBinFolder(fWorkspaceProjectWithSrc);
        } catch (CoreException e) {
            DLTKUIPlugin.log(e);
        }
        return fWorkspaceProjectWithSrc;
    }
    
    public IScriptProject getExternalProject() throws CoreException {
        return fExternalProject;
    }
    /* (non-Javadoc)
     * @see junit.extensions.TestSetup#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        DLTKCore.setOptions(TestOptions.getDefaultOptions());
        TestOptions.initializeCodeGenerationOptions();
        //DLTKUIPlugin.getDefault().getCodeTemplateStore().load(); 
    }

    protected void tearDown() throws Exception {
        if (fWorkspaceProject != null && fWorkspaceProject.exists())
            ScriptProjectHelper.delete(fWorkspaceProject);
        if (fWorkspaceProjectWithSrc != null && fWorkspaceProjectWithSrc.exists())
            ScriptProjectHelper.delete(fWorkspaceProjectWithSrc);
        if (fExternalProject != null && fExternalProject.exists())
            ScriptProjectHelper.delete(fExternalProject);
        ScriptProjectHelper.setAutoBuilding(fAutobuilding);
    }
    
    private void createWithSrcAndBinFolder(IScriptProject project) {
        IPath srcPath= new Path("src");
        try {
            if (srcPath.segmentCount() > 0) {
                IFolder folder= project.getProject().getFolder(srcPath);
                CoreUtility.createFolder(folder, true, true, null);
            }
            
            final IPath projectPath= project.getProject().getFullPath();
    
            // configure the buildpath entries, including the default InterpreterEnvironment library.
            List cpEntries= new ArrayList();
            cpEntries.add(DLTKCore.newSourceEntry(projectPath.append(srcPath)));
            //cpEntries.addAll(Arrays.asList(PreferenceConstants.getDefaultInterpreterEnvironmentLibrary()));
            IBuildpathEntry[] entries= (IBuildpathEntry[]) cpEntries.toArray(new IBuildpathEntry[cpEntries.size()]);
            
            project.setRawBuildpath(entries, null);
        } catch (CoreException e) {
            DLTKUIPlugin.log(e);
        }
    }
}
