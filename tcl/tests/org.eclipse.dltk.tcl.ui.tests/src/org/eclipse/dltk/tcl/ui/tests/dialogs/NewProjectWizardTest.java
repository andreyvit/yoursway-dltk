/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.ui.tests.dialogs;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.BuildpathEntry;
import org.eclipse.dltk.internal.corext.buildpath.AddLibraryOperation;
import org.eclipse.dltk.internal.corext.buildpath.AddSelectedLibraryOperation;
import org.eclipse.dltk.internal.corext.buildpath.AddSelectedSourceFolderOperation;
import org.eclipse.dltk.internal.corext.buildpath.BuildpathModifier;
import org.eclipse.dltk.internal.corext.buildpath.BuildpathModifierOperation;
import org.eclipse.dltk.internal.corext.buildpath.EditFiltersOperation;
import org.eclipse.dltk.internal.corext.buildpath.ExcludeOperation;
import org.eclipse.dltk.internal.corext.buildpath.IBuildpathInformationProvider;
import org.eclipse.dltk.internal.corext.buildpath.IncludeOperation;
import org.eclipse.dltk.internal.corext.buildpath.RemoveFromBuildpathOperation;
import org.eclipse.dltk.internal.corext.buildpath.ResetOperation;
import org.eclipse.dltk.internal.corext.buildpath.UnexcludeOperation;
import org.eclipse.dltk.internal.corext.buildpath.UnincludeOperation;
import org.eclipse.dltk.internal.ui.scriptview.BuildPathContainer;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.internal.ui.wizards.buildpath.BPListElement;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.IAddArchivesQuery;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.IAddLibrariesQuery;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.ICreateFolderQuery;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.ILinkToQuery;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.IRemoveLinkedFolderQuery;
import org.eclipse.dltk.ui.tests.DLTKProjectHelper;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;


public class NewProjectWizardTest extends TestCase {
	
    private static final Class THIS= NewProjectWizardTest.class;
    
    protected IDLTKProject fProject;
    protected static NewProjectTestSetup fTestSetup;
    protected String fNormalFolder= "NormalFolder";
    protected String fSubFolder= "SubFolder";
    
    public NewProjectWizardTest(String name) {
        super(name);
    }
    
    public static Test allTests() {
        return setUpTest(new TestSuite(THIS));
    }
    
    public static Test setUpTest(Test test) {
    	fTestSetup= new NewProjectTestSetup(test);
        return fTestSetup;
    }
    
    public static Test suite() {
        TestSuite suite= new TestSuite(THIS);
        fTestSetup= new NewProjectTestSetup(suite);
        suite.addTestSuite(NewEmptyProjectWizardTest.THIS);
        suite.addTestSuite(NewProjectWizardOperationTest.THIS);
        return fTestSetup;
    }
    
    protected void setUp() throws Exception {
        fProject= fTestSetup.getWorkspaceProjectWithSrc();
        assertFalse(fProject.isOnBuildpath(fProject.getUnderlyingResource()));
    }

    protected void tearDown() throws Exception {
        fProject.getProject().delete(true, true, null);
    }
    
    // Test folder creation (on project, on fragment root, on fragment; as source folder, as normal folder)
    public void testCreateNormalFolderOnProject() throws CoreException, InvocationTargetException, InterruptedException {
        IFolder folder= (IFolder)executeOperation(IBuildpathInformationProvider.CREATE_FOLDER, null, 
        		getNormalFolderCreationQuery(), null);
        
        assertTrue(folder.exists());
        assertTrue(folder.getName().equals(fNormalFolder));
        assertTrue(BuildpathModifier.getBuildpathEntryFor(folder.getFullPath(), fProject, IBuildpathEntry.BPE_SOURCE) == null);
        
        validateBuildpath();
    }
    
    public void testCreateSourceFolderOnProject() throws CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.CREATE_FOLDER, null, 
        		getSourceFolderCreationQuery(), null);
        
        assertTrue(root.getUnderlyingResource().exists());
        assertTrue(root.getElementName().equals(fSubFolder));
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_SOURCE) == null);
        
        validateBuildpath();
    }
    
    public void testCreateNormalFolderOnFragRoot() throws CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment parentRoot= addToBuildpath(new Path(fSubFolder));
        BuildpathModifierQueries.ICreateFolderQuery folderQuery= new BuildpathModifierQueries.ICreateFolderQuery() {

            public boolean doQuery() {
                return true;
            }

            public boolean isSourceFolder() {
                return false;
            }

            public IFolder getCreatedFolder() {
                return getFolderHandle(new Path(fSubFolder).append(fNormalFolder));
            }
            
        };
        IFolder folder= (IFolder)executeOperation(IBuildpathInformationProvider.CREATE_FOLDER, parentRoot,  folderQuery, null);
        
        assertTrue(folder.exists());
        assertTrue(folder.getName().equals(fNormalFolder));
        assertTrue(BuildpathModifier.getBuildpathEntryFor(new Path(fNormalFolder), fProject, IBuildpathEntry.BPE_SOURCE) == null);
        assertTrue(contains(new Path(fNormalFolder), parentRoot.getRawBuildpathEntry().getExclusionPatterns(), null));
        
        validateBuildpath();
    }
    
    public void testCreateSourceFolderOnFragRoot() throws CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment parentRoot= addToBuildpath(new Path(fNormalFolder));
        BuildpathModifierQueries.ICreateFolderQuery folderQuery= new BuildpathModifierQueries.ICreateFolderQuery() {

            public boolean doQuery() {
                return true;
            }

            public boolean isSourceFolder() {
                return true;
            }

            public IFolder getCreatedFolder() {
                return getFolderHandle(new Path(fNormalFolder).append(fSubFolder));
            }
            
        };
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.CREATE_FOLDER, parentRoot,  folderQuery, null);
        
        assertTrue(root.getUnderlyingResource().exists());
        assertTrue(root.getElementName().equals(fSubFolder));
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_SOURCE) == null);
        assertTrue(contains(new Path(fSubFolder), parentRoot.getRawBuildpathEntry().getExclusionPatterns(), null));
        
        validateBuildpath();
    }
    
    public void testCreateNormalFolderOnFrag() throws CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        final IPath nfFolder= new Path(fNormalFolder).append(fSubFolder).append("nfFolder");
        IFolder fragment= getFolderHandle(new Path(fNormalFolder).append(fSubFolder));
        BuildpathModifierQueries.ICreateFolderQuery folderQuery= new BuildpathModifierQueries.ICreateFolderQuery() {

            public boolean doQuery() {
                return true;
            }

            public boolean isSourceFolder() {
                return false;
            }

            public IFolder getCreatedFolder() {
                return getFolderHandle(nfFolder);
            }
            
        };
        IFolder folder= (IFolder)executeOperation(IBuildpathInformationProvider.CREATE_FOLDER, fragment,  folderQuery, null);
        
        assertTrue(folder.exists());
        assertTrue(folder.getParent().equals(fragment));
        assertTrue(folder.getName().equals(nfFolder.lastSegment()));
        assertTrue(BuildpathModifier.getBuildpathEntryFor(folder.getFullPath(), fProject, IBuildpathEntry.BPE_SOURCE) == null);
        assertTrue(contains(folder.getFullPath().removeFirstSegments(2), root.getRawBuildpathEntry().getExclusionPatterns(), null));
        
        validateBuildpath();
    }
    
    public void testCreateSourceFolderOnFrag() throws CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment parentRoot= addToBuildpath(new Path(fNormalFolder));
        IFolder fragment= getFolderHandle(new Path(fNormalFolder).append(fSubFolder));
        final IPath srcFolder= new Path(fNormalFolder).append(fSubFolder).append("srcFolder");
        
        BuildpathModifierQueries.ICreateFolderQuery folderQuery= new BuildpathModifierQueries.ICreateFolderQuery() {

            public boolean doQuery() {
                return true;
            }

            public boolean isSourceFolder() {
                return true;
            }

            public IFolder getCreatedFolder() {
                return getFolderHandle(srcFolder);
            }
            
        };
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.CREATE_FOLDER, fragment,  folderQuery, null);
        
        assertTrue(root.getUnderlyingResource().exists());
        assertTrue(root.getUnderlyingResource().getParent().equals(fragment));
        assertTrue(root.getElementName().equals(srcFolder.lastSegment()));
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_SOURCE) == null);
        assertTrue(contains(root.getPath().removeFirstSegments(2), parentRoot.getRawBuildpathEntry().getExclusionPatterns(), null));
        
        validateBuildpath();
    }
    
    public void testAddNormalFolderToBP() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        int numberOfEntries= fProject.getRawBuildpath().length;
        
        addToBuildpath(new Path(fNormalFolder));
        
        int newNumberOfEntries= fProject.getRawBuildpath().length;
        assertTrue(numberOfEntries + 1 == newNumberOfEntries);
        
        validateBuildpath();
    }
    
    public void testAddNestedNormalFolderToBP() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        IFolder cpFolder= excludePackage();
        
        IProjectFragment root= fProject.findProjectFragment(cpFolder.getFullPath());
        IFolder folder= getFolderHandle(cpFolder.getProjectRelativePath().append(fSubFolder));
        
        IBuildpathEntry entry= root.getRawBuildpathEntry();
        assertTrue(contains(new Path(folder.getName()), entry.getExclusionPatterns(), null));
        
        addToBuildpath(folder.getProjectRelativePath());
        
        entry= root.getRawBuildpathEntry();
        assertTrue(contains(new Path(folder.getName()), entry.getExclusionPatterns(), null));
        
        validateBuildpath();
    }    
    
    public void testAddPackageToBP() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        getFolderHandle(new Path(fNormalFolder).append(fSubFolder));
        IScriptFolder fragment= root.getScriptFolder(fSubFolder);
        
        IBuildpathEntry entry= root.getRawBuildpathEntry();
        
        int nrExclusions= entry.getExclusionPatterns().length;        
        assertFalse(contains(new Path(fragment.getElementName()), entry.getExclusionPatterns(), null));
        
        addToBuildpath(fragment);
         
        entry= root.getRawBuildpathEntry();
        assertTrue(contains(new Path(fragment.getElementName()), entry.getExclusionPatterns(), null));
        assertTrue(entry.getExclusionPatterns().length - 1 == nrExclusions);
        
        validateBuildpath();
    }
    
    public void testAddIncludedPackageToBP() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        IFolder cpFolder= includePackage();
        
        IProjectFragment root= fProject.findProjectFragment(cpFolder.getFullPath());
        IScriptFolder fragment= root.getScriptFolder(fSubFolder);
        
        IBuildpathEntry entry= root.getRawBuildpathEntry();
        
        int nrInclusions= entry.getInclusionPatterns().length;
        int nrExclusions= entry.getExclusionPatterns().length;
        assertTrue(contains(new Path(fragment.getElementName()), entry.getInclusionPatterns(), null));
        assertFalse(contains(new Path(fragment.getElementName()), entry.getExclusionPatterns(), null));
        
        addToBuildpath(fragment);
        
        entry= root.getRawBuildpathEntry();
        assertFalse(contains(new Path(fragment.getElementName()), entry.getInclusionPatterns(), null));
        assertTrue(contains(new Path(fragment.getElementName()), entry.getExclusionPatterns(), null));
        assertTrue(entry.getInclusionPatterns().length + 1 == nrInclusions);
        assertTrue(entry.getExclusionPatterns().length - 1 == nrExclusions);
        
        validateBuildpath();
    }
    
    public void testAddExcludedPackageToBP() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        IFolder cpFolder= excludePackage();
        IProjectFragment root= fProject.findProjectFragment(cpFolder.getFullPath());
        IScriptFolder element= root.getScriptFolder(fSubFolder);
        
        IBuildpathEntry entry= root.getRawBuildpathEntry();
        assertTrue(contains(new Path(element.getElementName()), entry.getExclusionPatterns(), null));
        
        addToBuildpath(element);
        
        root= fProject.findProjectFragment(element.getParent().getPath());
        entry= root.getRawBuildpathEntry();
        
        assertTrue(contains(new Path(element.getElementName()), entry.getExclusionPatterns(), null));
        
        validateBuildpath();
    }
    
    public void testAddProjectToBP() throws CoreException, InvocationTargetException, InterruptedException {
        // Situation: Project wich one source folder and one normal folder as 
        // direct childs --> adding the project to the BP should convert the folder into 
        // a package and the .java file into a compilation unit
        IPath srcPath= new Path("src2");
        IProjectFragment root= addToBuildpath(srcPath);
        IFolder normalFolder= getFolderHandle(new Path(fNormalFolder));
        IProjectFragment projectRoot= getProjectRoot(fProject.getCorrespondingResource());
        IScriptFolder fragment= projectRoot.createScriptFolder("", false, null);
        
        assertTrue(BuildpathModifier.getBuildpathEntryFor(fProject.getPath(), fProject, IBuildpathEntry.BPE_SOURCE) == null);
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_SOURCE) == null);
        assertTrue(fProject.findScriptFolder(normalFolder.getFullPath()) == null);
        assertTrue(fProject.findScriptFolder(fragment.getPath()) == null);
        
        IDLTKProject project= (IDLTKProject)executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, fProject,  null, null);
        assertTrue(project.equals(fProject));
        
        // project is on buildpath
        assertFalse(BuildpathModifier.getBuildpathEntryFor(fProject.getPath(), fProject, IBuildpathEntry.BPE_SOURCE) == null);
        // root is on buildpath and excluded on the project
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_SOURCE) == null);
        assertTrue(BuildpathModifier.isExcluded(root.getUnderlyingResource(), fProject));
        assertFalse(fProject.findScriptFolder(normalFolder.getFullPath()) == null);
        assertFalse(fProject.findScriptFolder(fragment.getPath()) == null);
        
        validateBuildpath();
    }
    
    public void testAddArchiveFileToBP() throws InvocationTargetException, InterruptedException, CoreException, IOException {
        IPath libraryPath= fProject.getPath().append("src2").append("archive.zip");
        testRemoveArchiveFileFromBP();
        IFile zipFile= fProject.getProject().getFile(libraryPath.removeFirstSegments(1));
        assertTrue(zipFile.getFileExtension().equals("zip"));
        
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_LIB_TO_BP, zipFile,  null, null);
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        
        validateBuildpath();
    }
    
    public void testAddZipFileToBP() throws InvocationTargetException, InterruptedException, CoreException {
        IPath libraryPath= fProject.getPath().append("src2").append("archive.zip");
        testRemoveZipFileFromBP();
        IFile zipFile= fProject.getProject().getFile(libraryPath.removeFirstSegments(1));
        assertTrue(zipFile.getFileExtension().equals("zip"));
        
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_LIB_TO_BP, zipFile,  null, null);
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        
        validateBuildpath();
    }
    
    public void testAddInterpreterEnvironmentToBP() throws InvocationTargetException, InterruptedException, CoreException {
        IBuildpathEntry[] entries= fProject.getRawBuildpath();
        IBuildpathEntry entry= null;
        for(int i= 0; i < entries.length; i++) {
            if(entries[i].getEntryKind() == IBuildpathEntry.BPE_CONTAINER) {
                entry= entries[i];
                break;
            }
        }
        assertTrue(entry != null);
        assertFalse(BuildpathModifier.getBuildpathEntryFor(entry.getPath(), fProject, IBuildpathEntry.BPE_CONTAINER) == null);
        testRemoveInterpreterEnvironmentFromBP();
        BuildPathContainer container= (BuildPathContainer)executeOperation(IBuildpathInformationProvider.ADD_LIB_TO_BP, entry, null, null);
        assertTrue(container.getBuildpathEntry().equals(entry));
        assertFalse(BuildpathModifier.getBuildpathEntryFor(entry.getPath(), fProject, IBuildpathEntry.BPE_CONTAINER) == null);
        
        validateBuildpath();
    }
    
    public void testRemoveFromBP() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        // add folder
        int before= fProject.getRawBuildpath().length;
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        
        // and remove it
        IFolder folder= (IFolder)executeOperation(IBuildpathInformationProvider.REMOVE_FROM_BP, root,  null, null);
        
        assertTrue(folder.getFullPath().equals(root.getPath()));
        assertFalse(contains(new Path(fNormalFolder), getPaths(), null));
        int after= fProject.getRawBuildpath().length;
        assertTrue(before == after);
        
        validateBuildpath();
    }
    
    public void removeProjectFromBP() throws CoreException, InvocationTargetException, InterruptedException {
        IPath srcPath= new Path("src2");
        IProjectFragment root= addToBuildpath(srcPath);
        IFolder normalFolder= getFolderHandle(new Path(fNormalFolder));
        IProjectFragment projectRoot= getProjectRoot(fProject.getCorrespondingResource());
        IScriptFolder fragment= projectRoot.createScriptFolder("", false, null);
        
        // add project to class path
        projectRoot= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, fProject,  null, null);
        assertTrue(projectRoot.equals(getProjectRoot(fProject.getCorrespondingResource())));
        
        // project is on buildpath
        assertFalse(BuildpathModifier.getBuildpathEntryFor(fProject.getPath(), fProject, IBuildpathEntry.BPE_SOURCE) == null);
        // root is on buildpath
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_SOURCE) == null);
        assertFalse(fProject.findScriptFolder(normalFolder.getFullPath()) == null);
        assertFalse(fProject.findScriptFolder(fragment.getPath()) == null);
        
        IDLTKProject jProject= (IDLTKProject)executeOperation(IBuildpathInformationProvider.REMOVE_FROM_BP, fProject, null, null);
        
        assertTrue(jProject.equals(fProject));
        assertTrue(BuildpathModifier.getBuildpathEntryFor(fProject.getPath(), fProject, IBuildpathEntry.BPE_SOURCE) == null);
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_SOURCE) == null);
        assertTrue(fProject.findScriptFolder(normalFolder.getFullPath()) == null);
        assertTrue(fProject.findScriptFolder(fragment.getPath()) == null);
        
        projectRoot= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, fProject,  null, null);
        assertTrue(projectRoot.equals(getProjectRoot(fProject.getCorrespondingResource())));
        
        validateBuildpath();
    }
    
    // Test include, exclude, uninclude, unexclude, ...
    public void testIncludePackage() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        includePackage();
        
        validateBuildpath();
    }
    
    public void testExcludePackage() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        excludePackage();
        
        validateBuildpath();
    }
    
    public void testExcludeIncludedPackage() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        IFolder cpFolder= includePackage();
        IProjectFragment root= fProject.findProjectFragment(cpFolder.getFullPath());
        IScriptFolder fragment= root.getScriptFolder(fSubFolder);
        
        IBuildpathEntry entry= root.getRawBuildpathEntry();
        int nrIncluded= entry.getInclusionPatterns().length;
        int nrExcluded= entry.getExclusionPatterns().length;
        
        IFolder folder= (IFolder)executeOperation(IBuildpathInformationProvider.EXCLUDE, fragment,  null, null);
        
        entry= root.getRawBuildpathEntry();
        IPath[] inclusionPatterns= entry.getInclusionPatterns();
        IPath[] exclusionPatterns= entry.getExclusionPatterns();
        assertTrue(inclusionPatterns.length + 1 == nrIncluded);
        assertTrue(exclusionPatterns.length - 1 == nrExcluded);
        assertFalse(contains(new Path(fragment.getElementName()), inclusionPatterns, null));
        assertTrue(contains(new Path(fragment.getElementName()), exclusionPatterns, null));
        assertTrue(folder.getFullPath().equals(fragment.getPath()));
        
        validateBuildpath();
    }
    
    public void testIncludeExcludedFolder() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        IFolder cpFolder= excludePackage();
        
        IProjectFragment root= fProject.findProjectFragment(cpFolder.getFullPath());
        IFolder folder= getFolderHandle(cpFolder.getProjectRelativePath().append(fSubFolder));
        
        IBuildpathEntry entry= root.getRawBuildpathEntry();
        int nrIncluded= entry.getInclusionPatterns().length;
        int nrExcluded= entry.getExclusionPatterns().length;
        
        IScriptFolder fragment= (IScriptFolder)executeOperation(IBuildpathInformationProvider.INCLUDE, folder, null, null);
        
        entry= root.getRawBuildpathEntry();
        IPath[] inclusionPatterns= entry.getInclusionPatterns();
        IPath[] exclusionPatterns= entry.getExclusionPatterns();
        assertTrue(inclusionPatterns.length - 1 == nrIncluded);
        assertTrue(exclusionPatterns.length + 1 == nrExcluded);
        assertTrue(contains(new Path(folder.getName()), inclusionPatterns, null));
        assertFalse(contains(new Path(folder.getName()), exclusionPatterns, null));
        assertTrue(fragment.getPath().equals(folder.getFullPath()));
        
        validateBuildpath();
    }
    
    public void testRemoveInclusion() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        IFolder cpFolder= includePackage();
        
        IProjectFragment root= fProject.findProjectFragment(cpFolder.getFullPath());
        IScriptFolder fragment= root.getScriptFolder(fSubFolder);
        
        IBuildpathEntry entry= root.getRawBuildpathEntry();
        int nrIncluded= entry.getInclusionPatterns().length;
        int nrExcluded= entry.getExclusionPatterns().length;
        
        IScriptFolder frag= (IScriptFolder)executeOperation(IBuildpathInformationProvider.UNINCLUDE, fragment,  null, null);
        
        entry= root.getRawBuildpathEntry();
        IPath[] inclusionPatterns= entry.getInclusionPatterns();
        IPath[] exclusionPatterns= entry.getExclusionPatterns();
        assertTrue(inclusionPatterns.length + 1 == nrIncluded);
        assertTrue(exclusionPatterns.length == nrExcluded);
        assertFalse(contains(new Path(fragment.getElementName()), inclusionPatterns, null));
        assertFalse(contains(new Path(fragment.getElementName()), exclusionPatterns, null));
        assertTrue(frag.getPath().equals(fragment.getPath()));
        
        validateBuildpath();
    }
    
    public void testRemoveExclusion() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        IFolder cpFolder= excludePackage();
        
        IProjectFragment root= fProject.findProjectFragment(cpFolder.getFullPath());
        IFolder folder= getFolderHandle(cpFolder.getProjectRelativePath().append(fSubFolder));
        
        IBuildpathEntry entry= root.getRawBuildpathEntry();
        int nrIncluded= entry.getInclusionPatterns().length;
        int nrExcluded= entry.getExclusionPatterns().length;
        
        IScriptFolder fragment= (IScriptFolder)executeOperation(IBuildpathInformationProvider.UNEXCLUDE, folder,  null, null);
        
        entry= root.getRawBuildpathEntry();
        IPath[] inclusionPatterns= entry.getInclusionPatterns();
        IPath[] exclusionPatterns= entry.getExclusionPatterns();
        assertTrue(inclusionPatterns.length == nrIncluded);
        assertTrue(exclusionPatterns.length + 1 == nrExcluded);
        assertFalse(contains(new Path(folder.getName()), inclusionPatterns, null));
        assertFalse(contains(new Path(folder.getName()), exclusionPatterns, null));
        assertTrue(fragment.getPath().equals(folder.getFullPath()));
        
        validateBuildpath();
    }

    public void testRemoveArchiveFileFromBP() throws InvocationTargetException, InterruptedException, CoreException, IOException {
        IPath srcPath= new Path("src2");
        IProjectFragment parentRoot= addToBuildpath(srcPath);
        IPath libraryPath= parentRoot.getPath().append("archive.zip");
        IProjectFragment root= DLTKProjectHelper.addLibrary(fProject, libraryPath);
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        executeOperation(IBuildpathInformationProvider.REMOVE_FROM_BP, root, null, null);
        //assertTrue(zipFile.getFileExtension().equals("zip"));
        //assertTrue(BuildpathModifier.isArchive(zipFile, fProject));
        //assertTrue(BuildpathModifier.getBuildpathEntryFor(zipFile.getFullPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        
        validateBuildpath();
    }

    public void testRemoveZipFileFromBP() throws InvocationTargetException, InterruptedException, CoreException {
        IPath srcPath= new Path("src2");
        IProjectFragment parentRoot= addToBuildpath(srcPath);
        IPath libraryPath= parentRoot.getPath().append("archive.zip");
        IProjectFragment root= DLTKProjectHelper.addLibrary(fProject, libraryPath);
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        executeOperation(IBuildpathInformationProvider.REMOVE_FROM_BP, root, null, null);
        //zipFile.create(null, false, null);
       // assertTrue(zipFile.getFileExtension().equals("zip"));
        //assertTrue(BuildpathModifier.isArchive(zipFile, fProject));
        //assertTrue(BuildpathModifier.getBuildpathEntryFor(zipFile.getFullPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        
        validateBuildpath();
    }
    
    public void testRemoveInterpreterEnvironmentFromBP() throws InvocationTargetException, InterruptedException, CoreException {
        IBuildpathEntry[] entries= fProject.getRawBuildpath();
        IBuildpathEntry entry= null;
        for(int i= 0; i < entries.length; i++) {
            if(entries[i].getEntryKind() == IBuildpathEntry.BPE_CONTAINER) {
                entry= entries[i];
                break;
            }
        }
        assertTrue(entry != null);
        assertFalse(BuildpathModifier.getBuildpathEntryFor(entry.getPath(), fProject, IBuildpathEntry.BPE_CONTAINER) == null);
        
        BuildPathContainer container= new BuildPathContainer(fProject, entry);
        IDLTKProject project= (IDLTKProject) executeOperation(IBuildpathInformationProvider.REMOVE_FROM_BP, container, null, null);
        assertTrue(project.equals(fProject));
        assertTrue(BuildpathModifier.getBuildpathEntryFor(entry.getPath(), fProject, IBuildpathEntry.BPE_CONTAINER) == null);
        
        validateBuildpath();
    }
    
    public void testEditFilters() throws CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        final IScriptFolder includedPackage= root.getScriptFolder(fSubFolder);
        final IScriptFolder excludedPackage= root.getScriptFolder(fSubFolder + "2");
        
        assertFalse(contains(new Path(includedPackage.getElementName()).addTrailingSeparator(), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        assertFalse(contains(new Path(excludedPackage.getElementName()).addTrailingSeparator(), root.getRawBuildpathEntry().getExclusionPatterns(), null));
        
        BuildpathModifierQueries.IInclusionExclusionQuery query= new BuildpathModifierQueries.IInclusionExclusionQuery() {

            public boolean doQuery(BPListElement element, boolean focusOnExcluded) {
                return true;
            }

            public IPath[] getInclusionPattern() {
                return new IPath[] {new Path(includedPackage.getElementName()).addTrailingSeparator()};
            }

            public IPath[] getExclusionPattern() {
                return new IPath[] {new Path(excludedPackage.getElementName()).addTrailingSeparator()};
            }
            
        };
        root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.EDIT_FILTERS, root, null, query);
        
        assertTrue(contains(new Path(includedPackage.getElementName()).addTrailingSeparator(), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        assertTrue(contains(new Path(excludedPackage.getElementName()).addTrailingSeparator(), root.getRawBuildpathEntry().getExclusionPatterns(), null));
        
        validateBuildpath();
    }

    public void testResetFilters() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        IFolder folder= getFolderHandle(new Path(fNormalFolder));
        
        IScriptFolder includedPackage= root.getScriptFolder(fSubFolder);
        IScriptFolder excludedPackage= root.getScriptFolder(fSubFolder + "2");
        
        IBuildpathEntry entry= root.getRawBuildpathEntry();
        
        executeOperation(IBuildpathInformationProvider.INCLUDE, includedPackage, null, null);
        executeOperation(IBuildpathInformationProvider.EXCLUDE, excludedPackage, null, null);
        addToBuildpath(folder.getProjectRelativePath().append(fSubFolder + "3"));
        IFolder subSrcFolder= getFolderHandle(folder.getProjectRelativePath().append(fSubFolder + "3"));
        int numberOnBP= fProject.getRawBuildpath().length;
        
        IProjectFragment editedRoot= (IProjectFragment)executeOperation(IBuildpathInformationProvider.RESET, root, null, null);
        
        entry= editedRoot.getRawBuildpathEntry();
        assertTrue(entry.getInclusionPatterns().length == 0);
        // one has to be left because it is a source folder
        assertTrue(entry.getExclusionPatterns().length == 1);
        assertTrue(contains(folder.getFullPath(), getPaths(), null));
        assertTrue(contains(subSrcFolder.getFullPath(), getPaths(), null));
        assertTrue(fProject.getRawBuildpath().length == numberOnBP);
        assertTrue(editedRoot.getPath().equals(root.getPath()));
        
        validateBuildpath();
    }   
    
    // Test file manipulations (include, exclude, ...)
    public void testIncludeSourceModule() throws CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        IScriptFolder fragment= root.createScriptFolder(fSubFolder, false, null);
        ISourceModule cu= createISourceModule("C", fragment);
        
        assertTrue(root.getRawBuildpathEntry().getInclusionPatterns().length == 0);
        
        ISourceModule newCu= (ISourceModule)executeOperation(IBuildpathInformationProvider.INCLUDE, cu, null, null);
        
        assertTrue(contains(newCu.getPath().removeFirstSegments(2), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        assertTrue(newCu.getPath().equals(cu.getPath()));
        
        validateBuildpath();
    }
    
    public void testExcludeSourceModule() throws CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        IScriptFolder fragment= root.createScriptFolder(fSubFolder, false, null);
        ISourceModule cu= createISourceModule("C", fragment);
        
        assertTrue(root.getRawBuildpathEntry().getExclusionPatterns().length == 0);
        
        IFile excludedFile=(IFile)executeOperation(IBuildpathInformationProvider.EXCLUDE, cu, null, null);
        
        assertTrue(contains(excludedFile.getFullPath().removeFirstSegments(2), root.getRawBuildpathEntry().getExclusionPatterns(), null));
        assertTrue(excludedFile.getFullPath().equals(cu.getPath()));
        
        validateBuildpath();
    }
    
    public void testIncludeExcludedFile() throws CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        IScriptFolder fragment= root.createScriptFolder(fSubFolder, false, null);
        ISourceModule cu= createISourceModule("C", fragment);
        
        assertTrue(root.getRawBuildpathEntry().getInclusionPatterns().length == 0);
        assertTrue(root.getRawBuildpathEntry().getExclusionPatterns().length == 0);
        
        IFile excludedFile= (IFile)executeOperation(IBuildpathInformationProvider.EXCLUDE, cu, null, null);
        
        assertTrue(root.getRawBuildpathEntry().getInclusionPatterns().length == 0);
        assertTrue(contains(excludedFile.getFullPath().removeFirstSegments(2), root.getRawBuildpathEntry().getExclusionPatterns(), null));
        
        ISourceModule newCu= (ISourceModule)executeOperation(IBuildpathInformationProvider.INCLUDE, cu, null, null);
        
        assertTrue(root.getRawBuildpathEntry().getExclusionPatterns().length == 0);
        assertTrue(contains(newCu.getPath().removeFirstSegments(2), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        assertTrue(excludedFile.getFullPath().equals(newCu.getPath()));
        
        validateBuildpath();
    }
    
    public void testExcludeIncludedFile() throws CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        IScriptFolder fragment= root.createScriptFolder(fSubFolder, false, null);
        ISourceModule cu= createISourceModule("C", fragment);
        
        assertTrue(root.getRawBuildpathEntry().getInclusionPatterns().length == 0);
        assertTrue(root.getRawBuildpathEntry().getExclusionPatterns().length == 0);
        
        ISourceModule newCu= (ISourceModule)executeOperation(IBuildpathInformationProvider.INCLUDE, cu, null, null);
        
        assertTrue(root.getRawBuildpathEntry().getExclusionPatterns().length == 0);
        assertTrue(contains(cu.getPath().removeFirstSegments(2), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        
        IFile excludedFile= (IFile)executeOperation(IBuildpathInformationProvider.EXCLUDE, newCu, null, null);
        
        assertTrue(root.getRawBuildpathEntry().getInclusionPatterns().length == 0);
        assertTrue(contains(excludedFile.getFullPath().removeFirstSegments(2), root.getRawBuildpathEntry().getExclusionPatterns(), null));
        assertTrue(excludedFile.getFullPath().equals(newCu.getPath()));
        
        validateBuildpath();
    }
    
    public void testUnincludeFile() throws CoreException, InvocationTargetException, InterruptedException {    	    	
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        IScriptFolder fragment= root.createScriptFolder(fSubFolder, false, null);
        ISourceModule cu= createISourceModule("C", fragment);
        
        assertTrue(root.getRawBuildpathEntry().getInclusionPatterns().length == 0);
        ISourceModule newCu= (ISourceModule)executeOperation(IBuildpathInformationProvider.INCLUDE, cu, null, null);
        assertTrue(contains(newCu.getPath().removeFirstSegments(2), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        
        newCu= (ISourceModule)executeOperation(IBuildpathInformationProvider.UNINCLUDE, newCu, null, null);
        
        assertFalse(contains(newCu.getPath().removeFirstSegments(2), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        assertTrue(newCu.getPath().equals(cu.getPath()));
        
        validateBuildpath();    	
    }
    
    public void testUnexcludeFile() throws CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        IScriptFolder fragment= root.createScriptFolder(fSubFolder, false, null);
        ISourceModule cu= createISourceModule("C", fragment);
        
        assertTrue(root.getRawBuildpathEntry().getExclusionPatterns().length == 0);
        IFile excludedFile= (IFile)executeOperation(IBuildpathInformationProvider.EXCLUDE, cu, null, null);
      
        assertTrue(contains(excludedFile.getFullPath().removeFirstSegments(2), root.getRawBuildpathEntry().getExclusionPatterns(), null));
        assertTrue(excludedFile.getFullPath().equals(cu.getPath()));
        
        ISourceModule newCu= (ISourceModule)executeOperation(IBuildpathInformationProvider.UNEXCLUDE, excludedFile, null, null);
        
        assertFalse(contains(excludedFile.getFullPath().removeFirstSegments(2), root.getRawBuildpathEntry().getExclusionPatterns(), null));
        assertTrue(newCu.getPath().equals(cu.getPath()));
        
        validateBuildpath();
    }
    
    public void testIncludeFileOnFolder() throws CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        IScriptFolder fragment2= root.createScriptFolder(fSubFolder+"2", false, null);
        executeOperation(IBuildpathInformationProvider.INCLUDE, fragment2, null, null);
        
        IScriptFolder fragment= root.createScriptFolder(fSubFolder, false, null);
        ISourceModule cu= createISourceModule("C", fragment);
        
        // Note: the parent of cu is a folder which is not excluded! This is important as
        // 'include' on a cu which parent folder is explicitly excluded is not possible!
        // Therefore fragment2 had to be included to test this case!
        assertTrue(fProject.findElement(cu.getPath().makeRelative()) == null);
        
        ISourceModule newCu= (ISourceModule)executeOperation(IBuildpathInformationProvider.INCLUDE, cu.getUnderlyingResource(), null, null);
        
        assertTrue(contains(newCu.getPath().removeFirstSegments(2), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        assertTrue(newCu.getPath().equals(cu.getPath()));
        
        validateBuildpath();
    }
    
    public void testExcludeFileOnFolder() throws CoreException, InvocationTargetException, InterruptedException {
        // Special case: there are 2 packages fSubFolder and fSubFolder2 where the fSubFolder2 is 
        // included. Now we include the compilation unit from fSubFolder and then exlude it.
        // After inclusion, the returned object must be of type ICompilation unit,
        // after exclusion, the returned object must be of type IFile (because
        // only fSubFolder2 is included. We only test that the return type is correct because
        // the correctness of the filters has been tested before.
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        IScriptFolder fragment2= root.createScriptFolder(fSubFolder+"2", false, null);
        IScriptFolder fragment= root.createScriptFolder(fSubFolder, false, null);
        ISourceModule cu= createISourceModule("C", fragment);
        
        // include fragment2
        executeOperation(IBuildpathInformationProvider.INCLUDE, fragment2, null, null);
        
        // Check that the compilation unit cannot be found (because now its only
        // a normal file and not a CU).
        assertTrue(fProject.findElement(cu.getPath().makeRelative()) == null);
        // include the cu --> if cast fails, then include is broken
        ISourceModule newCu= (ISourceModule)executeOperation(IBuildpathInformationProvider.INCLUDE, cu.getUnderlyingResource(), null, null);
        
        // exclude the file --> if cast fails, then exclude is broken
        IFile excludedFile= (IFile)executeOperation(IBuildpathInformationProvider.EXCLUDE, newCu, null, null);
        
        assertTrue(excludedFile.getFullPath().equals(newCu.getPath()));
        
        validateBuildpath();
    }
    
    public void testUnincludeFileOnFolder() throws CoreException, InvocationTargetException, InterruptedException {
        // Same situation as in testExcludeFileOnFolder, but this time, we use
        // uninclude instead of exclude
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        IScriptFolder fragment2= root.createScriptFolder(fSubFolder+"2", false, null);
        IScriptFolder fragment= root.createScriptFolder(fSubFolder, false, null);
        ISourceModule cu= createISourceModule("C", fragment);
        
        // include fragment2
        executeOperation(IBuildpathInformationProvider.INCLUDE, fragment2, null, null);
        
        // Check that the compilation unit cannot be found (because now its only
        // a normal file and not a CU).
        assertTrue(fProject.findElement(cu.getPath().makeRelative()) == null);
        // include the cu --> if cast fails, then include is broken
        ISourceModule newCu= (ISourceModule)executeOperation(IBuildpathInformationProvider.INCLUDE, cu.getUnderlyingResource(), null, null);
        
        // uninclude the file --> if cast fails, then uninclude is broken
        IFile file= (IFile)executeOperation(IBuildpathInformationProvider.UNINCLUDE, newCu, null, null);
        
        assertTrue(file.getFullPath().equals(cu.getPath()));
        validateBuildpath();
    }
    
    public void testIncludeFileOnIncludedFragment() throws CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        IScriptFolder fragment= root.createScriptFolder(fSubFolder, false, null);
        ISourceModule cu= createISourceModule("C", fragment);
        
        fragment= (IScriptFolder)executeOperation(IBuildpathInformationProvider.INCLUDE, fragment, null, null);
        assertTrue(root.getRawBuildpathEntry().getInclusionPatterns().length == 1);
        
        cu= (ISourceModule)executeOperation(IBuildpathInformationProvider.INCLUDE, cu, null, null);
        
        assertTrue(root.getRawBuildpathEntry().getInclusionPatterns().length == 2);
        assertTrue(contains(cu.getPath().removeFirstSegments(2), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        
        validateBuildpath();
    }
    
    public void testExcludeFileOnIncludedFragment() throws CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        IScriptFolder fragment= root.createScriptFolder(fSubFolder, false, null);
        ISourceModule cu= createISourceModule("C", fragment);
        
        fragment= (IScriptFolder)executeOperation(IBuildpathInformationProvider.INCLUDE, fragment, null, null);
        assertTrue(root.getRawBuildpathEntry().getInclusionPatterns().length == 1);
        
        IFile excludedFile= (IFile)executeOperation(IBuildpathInformationProvider.EXCLUDE, cu, null, null);
        
        assertTrue(root.getRawBuildpathEntry().getInclusionPatterns().length == 1);
        assertFalse(contains(excludedFile.getFullPath().removeFirstSegments(2), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        assertTrue(contains(excludedFile.getFullPath().removeFirstSegments(2), root.getRawBuildpathEntry().getExclusionPatterns(), null));
        
        validateBuildpath();
    }
    
    public void testUnincludeOnIncludedFragment() throws CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        IScriptFolder fragment= root.createScriptFolder(fSubFolder, false, null);
        ISourceModule cu= createISourceModule("C", fragment);
        
        fragment= (IScriptFolder)executeOperation(IBuildpathInformationProvider.INCLUDE, fragment, null, null);
        cu= (ISourceModule)executeOperation(IBuildpathInformationProvider.INCLUDE, cu, null, null);
        
        cu= (ISourceModule)executeOperation(IBuildpathInformationProvider.UNINCLUDE, cu, null, null);
        assertFalse(contains(cu.getPath().removeFirstSegments(2), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        
        validateBuildpath();
    }
    
    public void testUnexcludeOnIncludedFragment() throws CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        IScriptFolder fragment= root.createScriptFolder(fSubFolder, false, null);
        ISourceModule cu= createISourceModule("C", fragment);
        
        fragment= (IScriptFolder)executeOperation(IBuildpathInformationProvider.INCLUDE, fragment, null, null);
        IFile excludedFile= (IFile)executeOperation(IBuildpathInformationProvider.EXCLUDE, cu, null, null);
        
        cu= (ISourceModule)executeOperation(IBuildpathInformationProvider.UNEXCLUDE, excludedFile, null, null);
        assertFalse(contains(cu.getPath().removeFirstSegments(2), root.getRawBuildpathEntry().getExclusionPatterns(), null));
        assertTrue(excludedFile.getFullPath().equals(cu.getPath()));
        
        validateBuildpath();
    }
    
    // Helper methods
    protected Object executeOperation(int type, final Object selection, final BuildpathModifierQueries.ICreateFolderQuery creationQuery, final BuildpathModifierQueries.IInclusionExclusionQuery inclQuery) throws InvocationTargetException, InterruptedException {
        final Object[] returnValue= {null};
        IBuildpathInformationProvider provider= new IBuildpathInformationProvider() {

            public void handleResult(List result, CoreException exception, int operationType) {
                if (result.size() == 1)
                    returnValue[0]= result.get(0);
            }

            public IStructuredSelection getSelection() {
                List list= new ArrayList();
                list.add(selection);
                return new StructuredSelection(list);
            }

            public IDLTKProject getDLTKProject() {
                return fProject;
            }

            public BuildpathModifierQueries.IInclusionExclusionQuery getInclusionExclusionQuery() {
                return inclQuery;
            }

            public ILinkToQuery getLinkFolderQuery() throws ModelException {
                return null;
            }
            
            public IAddArchivesQuery getExternalArchivesQuery() throws ModelException {
                return null;
            }

            public IAddLibrariesQuery getLibrariesQuery() throws ModelException {
                return new IAddLibrariesQuery() {

                    public IBuildpathEntry[] doQuery(IDLTKProject project, IBuildpathEntry[] entries) {
                        return new IBuildpathEntry[] {(IBuildpathEntry)selection};
                    }
                    
                };
            }

            public void deleteCreatedResources() {
            	
            }

			public IRemoveLinkedFolderQuery getRemoveLinkedFolderQuery() throws ModelException {
				return null;
			}

			public ICreateFolderQuery getCreateFolderQuery() throws ModelException {
				return null;
			}
        };
        
        BuildpathModifierOperation op= null;
        switch(type) {
        	//case IBuildpathInformationProvider.CREATE_FOLDER: op= new CreateFolderOperation(null, provider); break;
            case IBuildpathInformationProvider.ADD_SEL_SF_TO_BP: op= new AddSelectedSourceFolderOperation(null, provider); break;
            case IBuildpathInformationProvider.ADD_SEL_LIB_TO_BP: op= new AddSelectedLibraryOperation(null, provider); break;
            case IBuildpathInformationProvider.REMOVE_FROM_BP: op= new RemoveFromBuildpathOperation(null, provider); break;
            case IBuildpathInformationProvider.INCLUDE: op= new IncludeOperation(null, provider); break;
            case IBuildpathInformationProvider.UNINCLUDE: op= new UnincludeOperation(null, provider); break;
            case IBuildpathInformationProvider.EXCLUDE: op= new ExcludeOperation(null, provider); break;
            case IBuildpathInformationProvider.UNEXCLUDE: op= new UnexcludeOperation(null, provider); break;
            case IBuildpathInformationProvider.EDIT_FILTERS: op= new EditFiltersOperation(null, provider); break;
            case IBuildpathInformationProvider.RESET: op= new ResetOperation(null, provider); break;            
            case IBuildpathInformationProvider.ADD_LIB_TO_BP: op= new AddLibraryOperation(null, provider); break;
        }
        
        op.run(null);
        
        return returnValue[0];
    }
    
    protected IProjectFragment addToBuildpath(IPath path) throws CoreException, InvocationTargetException, InterruptedException {
        IPath[] paths= getPaths();
        assertFalse(contains(path, paths, null));     
        
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, getFolderHandle(path),  null, null);

        paths= getPaths();
        assertTrue(contains(root.getPath(), getPaths(), null));
        return root;
    }
    
    protected IProjectFragment addToBuildpath(IModelElement element) throws CoreException, InvocationTargetException, InterruptedException {
        IPath[] paths= getPaths();
        assertFalse(contains(element.getPath(), paths, null));
        
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, element,  null, null);
        
        paths= getPaths();
        assertTrue(contains(element.getPath(), paths, null));
        return root;
    }
    
    protected IFolder includePackage() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        IFolder cpFolder= getFolderHandle(new Path(fNormalFolder));
        
        IFolder folder= getFolderHandle(cpFolder.getProjectRelativePath().append(fSubFolder));
        
        IBuildpathEntry entry= root.getRawBuildpathEntry();
        int before= entry.getInclusionPatterns().length;
        
        // include
        executeOperation(IBuildpathInformationProvider.INCLUDE, folder, null, null);
        
        entry= root.getRawBuildpathEntry();
        IPath[] inclusionPatterns= entry.getInclusionPatterns();
        int after= inclusionPatterns.length;
        assertTrue(contains(new Path(folder.getName()), inclusionPatterns, null));
        assertTrue(before + 1 == after);
        return cpFolder;
    }
    
    protected IFolder excludePackage() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        IFolder cpFolder= getFolderHandle(new Path(fNormalFolder));

        IFolder folder= getFolderHandle(cpFolder.getProjectRelativePath().append(fSubFolder));
        IScriptFolder fragment= root.getScriptFolder(folder.getName());
        
        IBuildpathEntry entry= root.getRawBuildpathEntry();
        int nrExcluded= entry.getExclusionPatterns().length;
        executeOperation(IBuildpathInformationProvider.EXCLUDE, fragment, null, null);
        
        entry= root.getRawBuildpathEntry();
        IPath[] exclusionPatterns= entry.getExclusionPatterns();
        assertTrue(nrExcluded + 1 == exclusionPatterns.length);
        assertTrue(contains(new Path(fragment.getElementName()), exclusionPatterns, null));
        return cpFolder;
    }
    
    protected IFolder getFolderHandle(IPath path) {
        IFolder folder= fProject.getProject().getFolder(path);
        try {
            if (!folder.exists())
                folder.create(true, false, null);
        } catch (CoreException e) {
        }
        return folder;
    }
    
    protected ISourceModule createISourceModule(String className, IScriptFolder fragment) throws ModelException {
        String packString= fragment.getElementName().equals("") ? fragment.getElementName() : "package " + fragment.getElementName() +";\n";
        StringBuffer content= getFileContent(className, packString);
        return fragment.createSourceModule(className+".java", content.toString(), false, null);
    }
    
    protected StringBuffer getFileContent(String className, String packageHeader) {
        StringBuffer buf= new StringBuffer();
        buf.append(packageHeader);
        buf.append("\n");   
        buf.append("public class "+className+ " {\n");
        buf.append("    public void foo() {\n");
        buf.append("    }\n");      
        buf.append("}\n");
        return buf;
    }
    
    protected BuildpathModifierQueries.ICreateFolderQuery getSourceFolderCreationQuery() {
        return new BuildpathModifierQueries.ICreateFolderQuery() {

            public boolean doQuery() {
                return true;
            }

            public boolean isSourceFolder() {
                return true;
            }

            public IFolder getCreatedFolder() {
                return getFolderHandle(new Path(fSubFolder));
            }
            
        };
    }
    
    protected BuildpathModifierQueries.ICreateFolderQuery getNormalFolderCreationQuery() {
        return new BuildpathModifierQueries.ICreateFolderQuery() {

            public boolean doQuery() {
                return true;
            }

            public boolean isSourceFolder() {
                return false;
            }

            public IFolder getCreatedFolder() {
                return getFolderHandle(new Path(fNormalFolder));
            }
            
        };
    }
    
    protected IPath[] getPaths() throws ModelException {
        IBuildpathEntry[] entries= fProject.getRawBuildpath();
        IPath[] paths= new IPath[entries.length];
        for(int i= 0; i < entries.length; i++) {
            paths[i]= entries[i].getPath();
        }
        return paths;
    }
    
    protected void validateBuildpath() throws ModelException {
        IModelStatus status= BuildpathEntry.validateBuildpath(fProject, fProject.getRawBuildpath());
        assertFalse(status.getSeverity() == IStatus.ERROR);
    }
    
    protected IProjectFragment getProjectRoot(IResource resource) throws ModelException {
        return BuildpathModifier.getFragmentRoot(resource, fProject, null);
    }
    
    protected static boolean contains(IPath path, IPath[] paths, IProgressMonitor monitor) {
        if (monitor == null)
            monitor= new NullProgressMonitor();
        if (path == null)
            return false;
        try {
            monitor.beginTask(NewWizardMessages.BuildpathModifier_Monitor_ComparePaths, paths.length); 
            if (path.getFileExtension() == null)
                path= new Path(completeName(path.toString())); 
            for (int i=0; i < paths.length; i++) {
                if (paths[i].equals(path))
                    return true;
                monitor.worked(1);
            }
        } finally {
           monitor.done();
       }            
       return false;
    }
    
    /**
     * Add a '/' at the end of the name if
     * it does not end with '.java'.
     * 
     * @param name append '/' at the end if
     * necessary
     * @return modified string
     */
    private static String completeName(String name) {
        if (!name.endsWith(".java")) { //$NON-NLS-1$
            name= name + "/"; //$NON-NLS-1$
            name= name.replace('.', '/');
            return name;
        }
        return name;
    }
}
