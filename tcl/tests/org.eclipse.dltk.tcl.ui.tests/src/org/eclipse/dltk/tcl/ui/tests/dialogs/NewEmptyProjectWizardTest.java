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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.buildpath.BuildpathModifier;
import org.eclipse.dltk.internal.corext.buildpath.IBuildpathInformationProvider;
import org.eclipse.dltk.internal.ui.wizards.buildpath.BPListElement;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries;
import org.eclipse.dltk.ui.tests.DLTKProjectHelper;


public class NewEmptyProjectWizardTest extends NewProjectWizardTest {
    public static final Class THIS= NewEmptyProjectWizardTest.class;
    
    public NewEmptyProjectWizardTest(String name) {
        super(name);
    }
    
    protected void setUp() throws Exception {
        fProject= fTestSetup.getWorkspaceProject();        
        testProjectIsOnBuildpath(true);
    }
    
    public void testCreateNormalFolderOnProject() throws CoreException, InvocationTargetException, InterruptedException {
        super.testCreateNormalFolderOnProject();
        IFolder folder= getNormalFolderCreationQuery().getCreatedFolder();
        assertTrue(BuildpathModifier.isExcluded(folder, fProject));
    }
    
    public void testCreateSourceFolderOnProjectWithProjAsRoot() throws CoreException, InvocationTargetException, InterruptedException {        
        BuildpathModifierQueries.ICreateFolderQuery folderQuery= getSourceFolderCreationQuery();
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.CREATE_FOLDER, null, folderQuery, null);
        
        assertTrue(root.getUnderlyingResource().exists());
        assertTrue(root.getElementName().equals(fSubFolder));
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_SOURCE) == null);
        testProjectIsOnBuildpath(true);
        assertTrue(BuildpathModifier.isExcluded(fProject.getProject().findMember(root.getPath().removeFirstSegments(1)), fProject));
        
        validateBuildpath();
    }
    
    public void testCreateSourceFolderOnProject() throws CoreException, InvocationTargetException, InterruptedException {
        // ... and remove project as root
        super.testCreateSourceFolderOnProject();
        IFolder folder= getSourceFolderCreationQuery().getCreatedFolder();
        assertFalse(BuildpathModifier.getBuildpathEntryFor(folder.getFullPath(), fProject, IBuildpathEntry.BPE_SOURCE) == null);
        testProjectIsOnBuildpath(false);
    }
    
    public void testCreateSourceFolderOnFragRootWithProjAsRoot() throws CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment parentRoot= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, getFolderHandle(new Path(fNormalFolder)), null, null);
        assertTrue(parentRoot != null);
        testProjectIsOnBuildpath(true);
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
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.CREATE_FOLDER, null, folderQuery, null);
        testProjectIsOnBuildpath(true);
        assertTrue(root.getUnderlyingResource().exists());
        assertTrue(root.getParent().equals(fProject));
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_SOURCE) == null);
        
        validateBuildpath();
    }
    
    public void testCreateSourceFolderOnFragRoot() throws CoreException, InvocationTargetException, InterruptedException {
        // ... and remove project as root
        // first add a source folder, but keep project as root
        IProjectFragment parentRoot= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, getFolderHandle(new Path(fNormalFolder)), null, null);
        testProjectIsOnBuildpath(true);
        
        // now create a child of this source folder and remove the project as root        
        // desired output location for the project is the project root itself, because the output location already changed when 
        // executing adding to the buildpath (it is not possible to have a source folder if the output location is equal to the project folder).
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
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.CREATE_FOLDER, null, folderQuery, null);
        assertTrue(root.getUnderlyingResource().exists());
        assertTrue(root.getUnderlyingResource().getParent().equals(parentRoot.getUnderlyingResource()));
        assertTrue(root.getParent().equals(fProject));
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_SOURCE) == null);
        testProjectIsOnBuildpath(false);
        
        validateBuildpath();
    }
    
    public void testCreateNormalFolderOnFragRootWithProjAsRoot() throws CoreException, InvocationTargetException, InterruptedException {        
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, getFolderHandle(new Path(fNormalFolder)), null, null);
        BuildpathModifierQueries.ICreateFolderQuery folderQuery= new BuildpathModifierQueries.ICreateFolderQuery() {

            public boolean doQuery() {
                return true;
            }

            public boolean isSourceFolder() {
                return false;
            }

            public IFolder getCreatedFolder() {
                return getFolderHandle(new Path(fNormalFolder).append(fSubFolder));
            }
            
        };
        IFolder folder= (IFolder)executeOperation(IBuildpathInformationProvider.CREATE_FOLDER, null, folderQuery, null);
        assertTrue(folder.getParent().equals(root.getUnderlyingResource()));
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    public void testCreateSourceFolderOnFrag() throws CoreException, InvocationTargetException, InterruptedException {
        // ... and remove project as root
        final IPath srcPath= new Path("src");        
        IProjectFragment parentRoot= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, getFolderHandle(srcPath), null, null);
        IFolder fragmentFolder= getFolderHandle(srcPath.append(fNormalFolder));
        assertTrue(fragmentFolder.getParent().equals(parentRoot.getUnderlyingResource()));
        testProjectIsOnBuildpath(true);
        
        BuildpathModifierQueries.ICreateFolderQuery folderQuery= new BuildpathModifierQueries.ICreateFolderQuery() {

            public boolean doQuery() {
                return true;
            }

            public boolean isSourceFolder() {
                return true;
            }

            public IFolder getCreatedFolder() {
                return getFolderHandle(srcPath.append(fNormalFolder).append(fSubFolder));
            }
            
        };
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.CREATE_FOLDER, null, folderQuery, null);
        assertTrue(root.getUnderlyingResource().exists());
        assertTrue(root.getParent().equals(fProject));
        assertTrue(root.getUnderlyingResource().getParent().equals(fragmentFolder));
        testProjectIsOnBuildpath(false);
        
        validateBuildpath();
    }
    
    public void testCreateSourceFolderOnFragWithProjAsRoot() throws CoreException, InvocationTargetException, InterruptedException {
        super.testCreateSourceFolderOnFrag();
        testProjectIsOnBuildpath(false);
    }
    
    public void testCreateNormalFolderOnFrag() throws CoreException, InvocationTargetException, InterruptedException {
        super.testCreateNormalFolderOnFrag();
        testProjectIsOnBuildpath(false);
    }
    
    public void testCreateNormalFolderOnFragWithProjAsRoot() throws CoreException, InvocationTargetException, InterruptedException {
        final IPath srcPath= new Path("src");
        
        IProjectFragment parentRoot= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, getFolderHandle(srcPath), null, null);
        IFolder fragmentFolder= getFolderHandle(srcPath.append(fNormalFolder));
        assertTrue(fragmentFolder.getParent().equals(parentRoot.getUnderlyingResource()));
        testProjectIsOnBuildpath(true);
        
        BuildpathModifierQueries.ICreateFolderQuery folderQuery= new BuildpathModifierQueries.ICreateFolderQuery() {

            public boolean doQuery() {
                return true;
            }

            public boolean isSourceFolder() {
                return false;
            }

            public IFolder getCreatedFolder() {
                return getFolderHandle(srcPath.append(fNormalFolder).append(fSubFolder));
            }
            
        };
        
        IFolder folder= (IFolder)executeOperation(IBuildpathInformationProvider.CREATE_FOLDER, null, folderQuery, null);
        assertTrue(folder.getParent().equals(fragmentFolder));
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    // Test adding/removing to buildpath
    public void testAddProjectToBPAndKeepDefaultOutputLocation() throws CoreException, InvocationTargetException, InterruptedException {
        // first we need to remove the project from the buildpath
        testRemoveProjectToBPAndKeepDefaultOutputLocation();
        
        // then we add it again
        IPath[] paths= getPaths();
        assertFalse(contains(fProject.getPath(), paths, null));
        
        IDLTKProject project= (IDLTKProject)executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, fProject, null, null);
        
        paths= getPaths();
        assertTrue(contains(fProject.getPath(), paths, null));
        assertTrue(project.equals(fProject));
        
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    public void testAddNormalFolderToBP() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        // ... and remove project as root
        int numberOfEntries= fProject.getRawBuildpath().length;
        
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        
        int newNumberOfEntries= fProject.getRawBuildpath().length;
        // the number remains equal because we removed the project
        // as root and added another src folder
        assertTrue(numberOfEntries == newNumberOfEntries);
        assertTrue(root.getParent().equals(fProject));
        testProjectIsOnBuildpath(false);
        
        validateBuildpath();
    }
    
    public void testAddNormalFolderToBPWithProjAsRoot() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        int numberOfEntries= fProject.getRawBuildpath().length;
        IFolder folder= getFolderHandle(new Path(fNormalFolder));
        
        IPath[] paths= getPaths();
        assertFalse(contains(folder.getFullPath(), paths, null));
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, folder, null, null);
        
        paths= getPaths();
        assertTrue(contains(folder.getFullPath(), getPaths(), null));

        int newNumberOfEntries= fProject.getRawBuildpath().length;
        assertTrue(numberOfEntries + 1 == newNumberOfEntries);
        assertTrue(root.getParent().equals(fProject));
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    public void testAddNestedNormalFolderToBP() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        IFolder cpFolder= getFolderHandle(new Path(fNormalFolder));
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, cpFolder, null, null);

        IFolder folder= getFolderHandle(cpFolder.getProjectRelativePath().append(fSubFolder));
        IScriptFolder fragment= root.getScriptFolder(folder.getName());
        
        IBuildpathEntry entry= root.getRawBuildpathEntry();
        int nrExcluded= entry.getExclusionPatterns().length;
        folder= (IFolder)executeOperation(IBuildpathInformationProvider.EXCLUDE, fragment, null, null);
        assertTrue(folder.getFullPath().equals(fragment.getPath()));
        
        entry= root.getRawBuildpathEntry();
        IPath[] exclusionPatterns= entry.getExclusionPatterns();
        assertTrue(nrExcluded + 1 == exclusionPatterns.length);
        assertTrue(contains(new Path(fragment.getElementName()), exclusionPatterns, null));
        
        IProjectFragment newRoot= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, folder, null, null);
        assertTrue(newRoot.getPath().equals(folder.getFullPath()));
        
        entry= root.getRawBuildpathEntry();
        assertTrue(contains(new Path(folder.getName()), entry.getExclusionPatterns(), null));
        assertFalse(BuildpathModifier.getBuildpathEntryFor(folder.getFullPath(), fProject, IBuildpathEntry.BPE_SOURCE) == null);
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    public void testAddNestedNormalFolderToBPWithProjAsRoot() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        // ... and remove project as root
        IFolder cpFolder= getFolderHandle(new Path(fNormalFolder));
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, cpFolder, null, null);

        IFolder folder= getFolderHandle(cpFolder.getProjectRelativePath().append(fSubFolder));
        IScriptFolder fragment= root.getScriptFolder(folder.getName());
        
        IBuildpathEntry entry= root.getRawBuildpathEntry();
        int nrExcluded= entry.getExclusionPatterns().length;
        executeOperation(IBuildpathInformationProvider.EXCLUDE, fragment, null, null);
        
        entry= root.getRawBuildpathEntry();
        IPath[] exclusionPatterns= entry.getExclusionPatterns();
        assertTrue(nrExcluded + 1 == exclusionPatterns.length);
        assertTrue(contains(new Path(fragment.getElementName()), exclusionPatterns, null));

        executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, folder, null, null);
        
        entry= root.getRawBuildpathEntry();
        assertTrue(contains(new Path(folder.getName()), entry.getExclusionPatterns(), null));
        assertFalse(BuildpathModifier.getBuildpathEntryFor(folder.getFullPath(), fProject, IBuildpathEntry.BPE_SOURCE) == null);
        testProjectIsOnBuildpath(false);
        
        validateBuildpath();
    }
    
    public void testAddPackageToBP() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        // ... and remove project as root
        IProjectFragment parentRoot= createFragmentRootAndKeepProjAsRoot();
        getFolderHandle(parentRoot.getPath().removeFirstSegments(1).append(fSubFolder)); // because add to buildpath requires the fragments underlying resource to exist
        IScriptFolder fragment= parentRoot.getScriptFolder(fSubFolder);
        IBuildpathEntry entry= parentRoot.getRawBuildpathEntry();
        
        int nrExclusions= entry.getExclusionPatterns().length;        
        assertFalse(contains(new Path(fragment.getElementName()), entry.getExclusionPatterns(), null));
        
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, fragment, null, null);
        
        entry= parentRoot.getRawBuildpathEntry();
        assertTrue(contains(new Path(fragment.getElementName()), entry.getExclusionPatterns(), null));
        assertTrue(entry.getExclusionPatterns().length - 1 == nrExclusions);
        assertTrue(root.getParent().equals(fProject));
        testProjectIsOnBuildpath(false);
        
        validateBuildpath();
    }
    
    public void testAddPackageToBPWithProjAsRoot() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment parentRoot= createFragmentRootAndKeepProjAsRoot();
        getFolderHandle(parentRoot.getPath().removeFirstSegments(1).append(fSubFolder)); // because add to buildpath requires the fragments underlying resource to exist
        IScriptFolder fragment= parentRoot.getScriptFolder(fSubFolder);
        IBuildpathEntry entry= parentRoot.getRawBuildpathEntry();
        
        int nrExclusions= entry.getExclusionPatterns().length;        
        assertFalse(contains(new Path(fragment.getElementName()), entry.getExclusionPatterns(), null));
        
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, fragment, null, null);
        
        entry= parentRoot.getRawBuildpathEntry();
        assertTrue(contains(new Path(fragment.getElementName()), entry.getExclusionPatterns(), null));
        assertTrue(entry.getExclusionPatterns().length - 1 == nrExclusions);
        assertTrue(root.getParent().equals(fProject));
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    // TODO refine + tests for project as root
    public void testAddArchiveFileToBP() throws InvocationTargetException, InterruptedException, CoreException, IOException {
        super.testAddArchiveFileToBP();
        testProjectIsOnBuildpath(false);
    }
    
    public void testAddJarFileToBPWithProjAsRoot() throws InvocationTargetException, InterruptedException, CoreException {
        // create root parent for zip file
        IProjectFragment parentRoot= createFragmentRootAndKeepProjAsRoot();
        IPath libraryPath= parentRoot.getPath().append("archive.zip");
        IProjectFragment root= DLTKProjectHelper.addLibrary(fProject, libraryPath);
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        
        // after creation, the zip file is on the buildpath --> remove it first
        IFile zipFile= (IFile)executeOperation(IBuildpathInformationProvider.REMOVE_FROM_BP, root, null, null);
        zipFile.create(null, false, null); // underlying resource must exist --> create
        assertTrue(zipFile.getFileExtension().equals("zip"));
        assertTrue(BuildpathModifier.isArchive(zipFile, fProject));
        assertTrue(BuildpathModifier.getBuildpathEntryFor(zipFile.getFullPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        
        // now it can be added and tested
        root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_LIB_TO_BP, zipFile, null, null);
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        testProjectIsOnBuildpath(true);
      
        validateBuildpath();
    }
    
    public void testAddJarFileToBPWithProjAsRootAndParent() throws InvocationTargetException, InterruptedException, CoreException {
        // create root parent for zip file
        IProjectFragment parentRoot= BuildpathModifier.getFragmentRoot(fProject.getUnderlyingResource(), fProject, null);
        IPath libraryPath= parentRoot.getPath().append("archive.zip");
        IProjectFragment root= DLTKProjectHelper.addLibrary(fProject, libraryPath);
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        assertTrue(root.getParent().equals(fProject));
        
        // after creation, the zip file is on the buildpath --> remove it first
        IFile zipFile= (IFile)executeOperation(IBuildpathInformationProvider.REMOVE_FROM_BP, root, null, null);
        zipFile.create(null, false, null); // underlying resource must exist --> create
        assertTrue(zipFile.getFileExtension().equals("zip"));
        assertTrue(BuildpathModifier.isArchive(zipFile, fProject));
        assertTrue(BuildpathModifier.getBuildpathEntryFor(zipFile.getFullPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        
        // now it can be added and tested
        root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_LIB_TO_BP, zipFile, null, null);
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        //testProjectIsOnBuildpath(true);
      
        validateBuildpath();
    }
    
    public void testAddJarFileToBPWithProjWithProjAsParentButRemovedAsRoot() throws InvocationTargetException, InterruptedException, CoreException {
        // create root parent for zip file
        IProjectFragment parentRoot= BuildpathModifier.getFragmentRoot(fProject.getUnderlyingResource(), fProject, null);
        IPath libraryPath= parentRoot.getPath().append("archive.zip");
        IProjectFragment root= DLTKProjectHelper.addLibrary(fProject, libraryPath);
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        assertTrue(root.getParent().equals(fProject));
        
        // after creation, the zip file is on the buildpath --> remove it first
        IFile zipFile= (IFile)executeOperation(IBuildpathInformationProvider.REMOVE_FROM_BP, root, null, null);
        zipFile.create(null, false, null); // underlying resource must exist --> create
        assertTrue(zipFile.getFileExtension().equals("zip"));
        assertTrue(BuildpathModifier.isArchive(zipFile, fProject));
        assertTrue(BuildpathModifier.getBuildpathEntryFor(zipFile.getFullPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        
        // now it can be added and tested
        root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_LIB_TO_BP, zipFile, null, null);
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        //testProjectIsOnBuildpath(false);
      
        validateBuildpath();
    }
  
    public void testAddZipFileToBP() throws InvocationTargetException, InterruptedException, CoreException {
        super.testAddZipFileToBP();
        testProjectIsOnBuildpath(false);
    }
    
    public void testAddZipFileToBPWithProjAsRoot() throws InvocationTargetException, InterruptedException, CoreException {
        // create root parent for zip file
        IProjectFragment parentRoot= createFragmentRootAndKeepProjAsRoot();
        IPath libraryPath= parentRoot.getPath().append("archive.zip");
        IProjectFragment root= DLTKProjectHelper.addLibrary(fProject, libraryPath);
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        
        // after creation, the zip file is on the buildpath --> remove it first
        IFile zipFile= (IFile)executeOperation(IBuildpathInformationProvider.REMOVE_FROM_BP, root, null, null);
        zipFile.create(null, false, null); // underlying resource must exist --> create
        assertTrue(zipFile.getFileExtension().equals("zip"));
        assertTrue(BuildpathModifier.isArchive(zipFile, fProject));
        assertTrue(BuildpathModifier.getBuildpathEntryFor(zipFile.getFullPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        
        // now it can be added and tested
        root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_LIB_TO_BP, zipFile, null, null);
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        testProjectIsOnBuildpath(true);
      
        validateBuildpath();
    }
    
    public void testAddZipFileToBPWithProjAsRootAndParent() throws InvocationTargetException, InterruptedException, CoreException {
        // create root parent for zip file
        IProjectFragment parentRoot= BuildpathModifier.getFragmentRoot(fProject.getUnderlyingResource(), fProject, null);
        IPath libraryPath= parentRoot.getPath().append("archive.zip");
        IProjectFragment root= DLTKProjectHelper.addLibrary(fProject, libraryPath);
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        assertTrue(root.getParent().equals(fProject));
        
        // after creation, the zip file is on the buildpath --> remove it first
        IFile zipFile= (IFile)executeOperation(IBuildpathInformationProvider.REMOVE_FROM_BP, root, null, null);
        zipFile.create(null, false, null); // underlying resource must exist --> create
        assertTrue(zipFile.getFileExtension().equals("zip"));
        assertTrue(BuildpathModifier.isArchive(zipFile, fProject));
        assertTrue(BuildpathModifier.getBuildpathEntryFor(zipFile.getFullPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        
        // now it can be added and tested
        root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_LIB_TO_BP, zipFile, null, null);
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        testProjectIsOnBuildpath(true);
      
        validateBuildpath();
    }
    
    public void testAddZipFileToBPWithProjWithProjAsParentButRemovedAsRoot() throws InvocationTargetException, InterruptedException, CoreException {
        // create root parent for zip file
        IProjectFragment parentRoot= BuildpathModifier.getFragmentRoot(fProject.getUnderlyingResource(), fProject, null);
        IPath libraryPath= parentRoot.getPath().append("archive.zip");
        IProjectFragment root= DLTKProjectHelper.addLibrary(fProject, libraryPath);
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        assertTrue(root.getParent().equals(fProject));
        
        // after creation, the zip file is on the buildpath --> remove it first
        IFile zipFile= (IFile)executeOperation(IBuildpathInformationProvider.REMOVE_FROM_BP, root, null, null);
        zipFile.create(null, false, null); // underlying resource must exist --> create
        assertTrue(zipFile.getFileExtension().equals("zip"));
        assertTrue(BuildpathModifier.isArchive(zipFile, fProject));
        assertTrue(BuildpathModifier.getBuildpathEntryFor(zipFile.getFullPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        
        // now it can be added and tested
        root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_LIB_TO_BP, zipFile, null, null);
        assertFalse(BuildpathModifier.getBuildpathEntryFor(root.getPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);
        //testProjectIsOnBuildpath(false);
      
        validateBuildpath();
    }
    
    public void testAddInterpreterEnvironmentToBP() throws InvocationTargetException, InterruptedException, CoreException {
        super.testAddInterpreterEnvironmentToBP();
        testProjectIsOnBuildpath(true);
    }
      
    public void testAddIncludedPackageToBP() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        // ... and remove project as root
        IProjectFragment parentRoot= includePackageAndKeepProjAsRoot();
        IScriptFolder fragment= parentRoot.getScriptFolder(fSubFolder);
        
        IBuildpathEntry entry= parentRoot.getRawBuildpathEntry();
        
        int nrInclusions= entry.getInclusionPatterns().length;
        int nrExclusions= entry.getExclusionPatterns().length;
        assertTrue(contains(new Path(fragment.getElementName()), entry.getInclusionPatterns(), null));
        assertFalse(contains(new Path(fragment.getElementName()), entry.getExclusionPatterns(), null));
        
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, fragment, null, null);
        
        entry= parentRoot.getRawBuildpathEntry();
        assertFalse(contains(new Path(root.getElementName()), entry.getInclusionPatterns(), null));
        assertTrue(contains(new Path(root.getElementName()), entry.getExclusionPatterns(), null));
        assertTrue(entry.getInclusionPatterns().length + 1 == nrInclusions);
        assertTrue(entry.getExclusionPatterns().length - 1 == nrExclusions);
        assertTrue(root.getParent().equals(fProject));
        testProjectIsOnBuildpath(false);
        
        validateBuildpath();
    }
    
    public void testAddIncludedPackageToBPWithProjAsRoot() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment parentRoot= includePackageAndKeepProjAsRoot();
        IScriptFolder fragment= parentRoot.getScriptFolder(fSubFolder);
        
        IBuildpathEntry entry= parentRoot.getRawBuildpathEntry();
        
        int nrInclusions= entry.getInclusionPatterns().length;
        int nrExclusions= entry.getExclusionPatterns().length;
        assertTrue(contains(new Path(fragment.getElementName()), entry.getInclusionPatterns(), null));
        assertFalse(contains(new Path(fragment.getElementName()), entry.getExclusionPatterns(), null));
        
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, fragment, null, null);
        
        entry= parentRoot.getRawBuildpathEntry();
        assertFalse(contains(new Path(root.getElementName()), entry.getInclusionPatterns(), null));
        assertTrue(contains(new Path(root.getElementName()), entry.getExclusionPatterns(), null));
        assertTrue(entry.getInclusionPatterns().length + 1 == nrInclusions);
        assertTrue(entry.getExclusionPatterns().length - 1 == nrExclusions);
        assertTrue(root.getParent().equals(fProject));
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    public void testAddExcludedPackageToBP() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        // ... and remove project as root
        IProjectFragment parentRoot= excludePackageAndKeepProjAsRoot();
        IScriptFolder fragment= parentRoot.getScriptFolder(fSubFolder);
        
        IBuildpathEntry entry= parentRoot.getRawBuildpathEntry();
        assertTrue(contains(new Path(fragment.getElementName()), entry.getExclusionPatterns(), null));
        
        IPath[] paths= getPaths();
        assertFalse(contains(fragment.getPath(), paths, null));
        
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, fragment, null, null);
        
        paths= getPaths();
        assertTrue(contains(fragment.getPath(), paths, null));
        
        parentRoot= fProject.findProjectFragment(parentRoot.getPath());
        entry= parentRoot.getRawBuildpathEntry();
        
        assertTrue(contains(new Path(root.getElementName()), entry.getExclusionPatterns(), null));
        testProjectIsOnBuildpath(false);
        
        validateBuildpath();
    }
    
    public void testAddExcludedPackageToBPWithProjAsRoot() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment parentRoot= excludePackageAndKeepProjAsRoot();
        IScriptFolder fragment= parentRoot.getScriptFolder(fSubFolder);
        
        IBuildpathEntry entry= parentRoot.getRawBuildpathEntry();
        assertTrue(contains(new Path(fragment.getElementName()), entry.getExclusionPatterns(), null));
        
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, fragment, null, null);
        
        parentRoot= fProject.findProjectFragment(parentRoot.getPath());
        entry= parentRoot.getRawBuildpathEntry();
        
        assertTrue(contains(new Path(root.getElementName()), entry.getExclusionPatterns(), null));
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    public void testRemoveProjectToBPAndKeepDefaultOutputLocation() throws CoreException, InvocationTargetException, InterruptedException {
        executeOperation(IBuildpathInformationProvider.REMOVE_FROM_BP, fProject, null, null);
        testProjectIsOnBuildpath(false);
        
        validateBuildpath();
    }
    
    public void testRemoveFromBP() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        // project is not root
        // add folder
        int before= fProject.getRawBuildpath().length;
        IFolder folder= getFolderHandle(new Path(fNormalFolder));
        IProjectFragment root= addToBuildpath(new Path(fNormalFolder));
        
        // and remove it
        
        executeOperation(IBuildpathInformationProvider.REMOVE_FROM_BP, root, null, null);
        assertFalse(contains(folder.getFullPath(), getPaths(), null));
        int after= fProject.getRawBuildpath().length;
        assertTrue(before - 1 == after);
        // the minus one is correct because:
        // first the project was the root and had an cp entry
        // then a src folder was added and the cp entry from the
        // project was removed.
        // at last, the entry for the folder was removed.
        // It follows that the number of cp entries has decreased by one
        
        validateBuildpath();
        testProjectIsOnBuildpath(false);
    }
    
    public void testRemoveFromBPWithProjAsRoot() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        // add folder
        int before= fProject.getRawBuildpath().length;
        IProjectFragment root= createFragmentRootAndKeepProjAsRoot();
        
        // and remove it
        IFolder folder= (IFolder)executeOperation(IBuildpathInformationProvider.REMOVE_FROM_BP, root, null, null);
        assertFalse(contains(folder.getFullPath(), getPaths(), null));
        int after= fProject.getRawBuildpath().length;
        assertTrue(before == after);
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }

    public void testRemoveZipFileFromBP() throws InvocationTargetException, InterruptedException, CoreException {
        super.testRemoveZipFileFromBP();
        testProjectIsOnBuildpath(false);
    }

    public void testRemoveArchiveFileFromBP() throws InvocationTargetException, InterruptedException, CoreException, IOException {
        super.testRemoveArchiveFileFromBP();
        testProjectIsOnBuildpath(false);
    }
    
    public void testRemoveInterpreterEnvironmentFromBP() throws InvocationTargetException, InterruptedException, CoreException {
        super.testRemoveInterpreterEnvironmentFromBP();
        testProjectIsOnBuildpath(true);
    }
    
    // Test include, exclude, uninclude, unexclude, ...
    
    // Note that include and exclude does not have any impact whether
    // the project is on the buildpath or not as long as the included/excluded
    // element was not a direct child of the project!
    // So the default testing is done by the super class while we have to
    // test only these special cases.
    public void testIncludePackageOnProject() throws ModelException, InvocationTargetException, InterruptedException {
        IScriptFolder fragment= createFragmentOnProject();
        IProjectFragment root= getProjectRoot(fragment.getUnderlyingResource());
        
        assertFalse(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        fragment= (IScriptFolder)executeOperation(IBuildpathInformationProvider.INCLUDE, fragment, null, null);
        assertTrue(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    public void testExcludePackageOnProject() throws ModelException, InvocationTargetException, InterruptedException {
        IScriptFolder fragment= createFragmentOnProject();
        IProjectFragment root= getProjectRoot(fragment.getUnderlyingResource());
        
        assertFalse(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getExclusionPatterns(), null));
        IFolder excludedFolder= (IFolder) executeOperation(IBuildpathInformationProvider.EXCLUDE, fragment, null, null);
        assertTrue(contains(excludedFolder.getProjectRelativePath(), root.getRawBuildpathEntry().getExclusionPatterns(), null));
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    public void testExcludeIncludedPackageOnProject() throws ModelException, InvocationTargetException, InterruptedException {
        IScriptFolder fragment= createFragmentOnProject();
        IProjectFragment root= getProjectRoot(fragment.getUnderlyingResource());
        
        // include
        assertFalse(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        fragment= (IScriptFolder) executeOperation(IBuildpathInformationProvider.INCLUDE, fragment, null, null);
        assertTrue(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        
        IBuildpathEntry entry= root.getRawBuildpathEntry();
        int nrIncluded= entry.getInclusionPatterns().length;
        int nrExcluded= entry.getExclusionPatterns().length;
        
        // exclude
        assertFalse(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getExclusionPatterns(), null));
        IFolder excludedFolder= (IFolder) executeOperation(IBuildpathInformationProvider.EXCLUDE, fragment, null, null);
        assertTrue(contains(excludedFolder.getProjectRelativePath(), root.getRawBuildpathEntry().getExclusionPatterns(), null));
        assertFalse(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        
        assertTrue(root.getRawBuildpathEntry().getInclusionPatterns().length + 1 == nrIncluded);
        assertTrue(root.getRawBuildpathEntry().getExclusionPatterns().length - 1 == nrExcluded);
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    public void testIncludeExcludedFolderOnProject() throws ModelException, CoreException, InvocationTargetException, InterruptedException {
        IScriptFolder fragment= createFragmentOnProject();
        IProjectFragment root= getProjectRoot(fragment.getUnderlyingResource());
        
        // exclude
        assertFalse(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getExclusionPatterns(), null));
        IFolder excludedFolder= (IFolder) executeOperation(IBuildpathInformationProvider.EXCLUDE, fragment, null, null);
        assertTrue(contains(excludedFolder.getProjectRelativePath(), root.getRawBuildpathEntry().getExclusionPatterns(), null));
        
        IBuildpathEntry entry= root.getRawBuildpathEntry();
        int nrIncluded= entry.getInclusionPatterns().length;
        int nrExcluded= entry.getExclusionPatterns().length;
        
        // include
        assertFalse(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        fragment= (IScriptFolder) executeOperation(IBuildpathInformationProvider.INCLUDE, excludedFolder, null, null);
        assertTrue(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        assertFalse(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getExclusionPatterns(), null));
        
        assertTrue(root.getRawBuildpathEntry().getInclusionPatterns().length - 1 == nrIncluded);
        assertTrue(root.getRawBuildpathEntry().getExclusionPatterns().length + 1 == nrExcluded);
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    public void testRemoveInclusionOnProject() throws ModelException, InvocationTargetException, InterruptedException {
        IScriptFolder fragment= createFragmentOnProject();
        IProjectFragment root= getProjectRoot(fragment.getUnderlyingResource());
        
        assertFalse(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        fragment= (IScriptFolder)executeOperation(IBuildpathInformationProvider.INCLUDE, fragment, null, null);
        assertTrue(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        
        // remove inclusion
        fragment= (IScriptFolder)executeOperation(IBuildpathInformationProvider.UNINCLUDE, fragment, null, null);
        assertFalse(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    public void testRemoveExclusionOnProject() throws ModelException, InvocationTargetException, InterruptedException {
        IScriptFolder fragment= createFragmentOnProject();
        IProjectFragment root= getProjectRoot(fragment.getUnderlyingResource());
        
        assertFalse(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getExclusionPatterns(), null));
        IFolder excludedFolder= (IFolder)executeOperation(IBuildpathInformationProvider.EXCLUDE, fragment, null, null);
        assertTrue(contains(excludedFolder.getProjectRelativePath(), root.getRawBuildpathEntry().getExclusionPatterns(), null));
        
        // remove exclusion
        fragment= (IScriptFolder)executeOperation(IBuildpathInformationProvider.UNEXCLUDE, excludedFolder, null, null);
        assertFalse(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getExclusionPatterns(), null));
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    public void testEditFiltersOnProject() throws ModelException, InvocationTargetException, InterruptedException {
        IProjectFragment root= getProjectRoot(fProject.getUnderlyingResource());
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
        IDLTKProject jProject= (IDLTKProject)executeOperation(IBuildpathInformationProvider.EDIT_FILTERS, fProject, null, query);
        assertTrue(jProject.equals(fProject));
        
        root= getProjectRoot(fProject.getUnderlyingResource());
        assertTrue(contains(new Path(includedPackage.getElementName()).addTrailingSeparator(), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        assertTrue(contains(new Path(excludedPackage.getElementName()).addTrailingSeparator(), root.getRawBuildpathEntry().getExclusionPatterns(), null));
        
        validateBuildpath();
    }
    
    public void testResetFiltersOnProject() throws CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment root= createFragmentRootAndKeepProjAsRoot();
        
        IScriptFolder includedPackage= root.getScriptFolder(fSubFolder);
        IScriptFolder excludedPackage= root.getScriptFolder(fSubFolder + "2");
        IFolder subSrcFolder= getFolderHandle(root.getPath().removeFirstSegments(1).append(fSubFolder + "3"));
        
        executeOperation(IBuildpathInformationProvider.INCLUDE, includedPackage, null, null);
        executeOperation(IBuildpathInformationProvider.INCLUDE, excludedPackage, null, null);
        executeOperation(IBuildpathInformationProvider.ADD_SEL_SF_TO_BP, subSrcFolder, null, null);
        int numberOnBP= fProject.getRawBuildpath().length;
        
        executeOperation(IBuildpathInformationProvider.RESET, root, null, null);

        IProjectFragment projectRoot= getProjectRoot(fProject.getUnderlyingResource());
        IBuildpathEntry entry= projectRoot.getRawBuildpathEntry();
        assertTrue(entry.getInclusionPatterns().length == 0);
        // one has to be left because it is a source folder
        assertTrue(entry.getExclusionPatterns().length == 1);
        assertTrue(contains(root.getPath(), getPaths(), null));
        assertTrue(contains(subSrcFolder.getFullPath(), getPaths(), null));
        assertTrue(fProject.getRawBuildpath().length == numberOnBP);
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }   
    
    // Test file manipulations (include, exclude, ...)
    // Note that include and exclude does not have any impact whether
    // the project is on the buildpath or not as long as the included/excluded
    // element was not a direct child of the project!
    // So the default testing is done by the super class while we have to
    // test only these special cases for files.
    public void testIncludeFileOnProject() throws ModelException, InvocationTargetException, InterruptedException {
        IProjectFragment projectRoot= getProjectRoot(fProject.getCorrespondingResource());
        IScriptFolder fragment= projectRoot.createScriptFolder("", false, null);
        ISourceModule cu= createISourceModule("C", fragment);
        
        assertTrue(projectRoot.getRawBuildpathEntry().getInclusionPatterns().length == 0);
        
        cu= (ISourceModule)executeOperation(IBuildpathInformationProvider.INCLUDE, cu, null, null);
        
        assertTrue(contains(cu.getPath().removeFirstSegments(1), projectRoot.getRawBuildpathEntry().getInclusionPatterns(), null));
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    public void testExcludeFileOnProject() throws ModelException, InvocationTargetException, InterruptedException {
        IProjectFragment projectRoot= getProjectRoot(fProject.getCorrespondingResource());
        IScriptFolder fragment= projectRoot.createScriptFolder("", false, null);
        ISourceModule cu= createISourceModule("C", fragment);
        
        assertTrue(projectRoot.getRawBuildpathEntry().getExclusionPatterns().length == 0);
        
        IFile excludedFile= (IFile)executeOperation(IBuildpathInformationProvider.EXCLUDE, cu, null, null);
        
        assertTrue(contains(excludedFile.getFullPath().removeFirstSegments(1), projectRoot.getRawBuildpathEntry().getExclusionPatterns(), null));
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    public void testIncludeExcludedFileOnProject() throws ModelException, InvocationTargetException, InterruptedException {
        IProjectFragment projectRoot= getProjectRoot(fProject.getCorrespondingResource());
        IScriptFolder fragment= projectRoot.createScriptFolder("", false, null);
        ISourceModule cu= createISourceModule("C", fragment);
        
        assertTrue(projectRoot.getRawBuildpathEntry().getInclusionPatterns().length == 0);
        assertTrue(projectRoot.getRawBuildpathEntry().getExclusionPatterns().length == 0);
        
        IFile excludedFile= (IFile)executeOperation(IBuildpathInformationProvider.EXCLUDE, cu, null, null);
        
        assertTrue(projectRoot.getRawBuildpathEntry().getInclusionPatterns().length == 0);
        assertTrue(contains(excludedFile.getFullPath().removeFirstSegments(1), projectRoot.getRawBuildpathEntry().getExclusionPatterns(), null));
        
        cu= (ISourceModule)executeOperation(IBuildpathInformationProvider.INCLUDE, excludedFile, null, null);
        
        assertTrue(projectRoot.getRawBuildpathEntry().getExclusionPatterns().length == 0);
        assertTrue(contains(cu.getPath().removeFirstSegments(1), projectRoot.getRawBuildpathEntry().getInclusionPatterns(), null));
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    public void testExcludeIncludedFileOnProject() throws ModelException, InvocationTargetException, InterruptedException {
        IProjectFragment projectRoot= getProjectRoot(fProject.getCorrespondingResource());
        IScriptFolder fragment= projectRoot.createScriptFolder("", false, null);
        ISourceModule cu= createISourceModule("C", fragment);
        
        assertTrue(projectRoot.getRawBuildpathEntry().getInclusionPatterns().length == 0);
        assertTrue(projectRoot.getRawBuildpathEntry().getExclusionPatterns().length == 0);
        
        cu= (ISourceModule)executeOperation(IBuildpathInformationProvider.INCLUDE, cu, null, null);
        
        assertTrue(projectRoot.getRawBuildpathEntry().getExclusionPatterns().length == 0);
        assertTrue(contains(cu.getPath().removeFirstSegments(1), projectRoot.getRawBuildpathEntry().getInclusionPatterns(), null));
        
        IFile excludedFile= (IFile)executeOperation(IBuildpathInformationProvider.EXCLUDE, cu, null, null);
        
        assertTrue(projectRoot.getRawBuildpathEntry().getInclusionPatterns().length == 0);
        assertTrue(contains(excludedFile.getFullPath().removeFirstSegments(1), projectRoot.getRawBuildpathEntry().getExclusionPatterns(), null));
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    public void testUnincludeFileOnProject() throws ModelException, InvocationTargetException, InterruptedException {
        IProjectFragment projectRoot= getProjectRoot(fProject.getCorrespondingResource());
        IScriptFolder fragment= projectRoot.createScriptFolder("", false, null);
        ISourceModule cu= createISourceModule("C", fragment);
        
        assertTrue(projectRoot.getRawBuildpathEntry().getInclusionPatterns().length == 0);
        cu= (ISourceModule)executeOperation(IBuildpathInformationProvider.INCLUDE, cu, null, null);
        assertTrue(contains(cu.getPath().removeFirstSegments(1), projectRoot.getRawBuildpathEntry().getInclusionPatterns(), null));
        
        cu= (ISourceModule)executeOperation(IBuildpathInformationProvider.UNINCLUDE, cu, null, null);
        
        assertFalse(contains(cu.getPath().removeFirstSegments(1), projectRoot.getRawBuildpathEntry().getInclusionPatterns(), null));
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    public void testUnexcludeFileOnProject() throws ModelException, InvocationTargetException, InterruptedException {
        IProjectFragment projectRoot= getProjectRoot(fProject.getCorrespondingResource());
        IScriptFolder fragment= projectRoot.createScriptFolder("", false, null);
        ISourceModule cu= createISourceModule("C", fragment);
        
        assertTrue(projectRoot.getRawBuildpathEntry().getExclusionPatterns().length == 0);
        IFile excludedFile= (IFile)executeOperation(IBuildpathInformationProvider.EXCLUDE, cu, null, null);
        assertTrue(contains(excludedFile.getFullPath().removeFirstSegments(1), projectRoot.getRawBuildpathEntry().getExclusionPatterns(), null));
        
        cu= (ISourceModule)executeOperation(IBuildpathInformationProvider.UNEXCLUDE, excludedFile, null, null);
        assertFalse(contains(excludedFile.getFullPath().removeFirstSegments(1), projectRoot.getRawBuildpathEntry().getExclusionPatterns(), null));
        
        validateBuildpath();
    }
    
    public void testIncludeFileWithIncludedFragment() throws ModelException, InvocationTargetException, InterruptedException {
        IProjectFragment projectRoot= getProjectRoot(fProject.getCorrespondingResource());
        IScriptFolder fragment= createFragmentOnProject();
        IProjectFragment root= getProjectRoot(fragment.getUnderlyingResource());
        
        // first include the fragment
        assertTrue(projectRoot.getRawBuildpathEntry().getInclusionPatterns().length == 0);
        assertFalse(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        fragment= (IScriptFolder)executeOperation(IBuildpathInformationProvider.INCLUDE, fragment, null, null);
        assertTrue(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        
        // then include the file
        IScriptFolder defaultFragment= projectRoot.createScriptFolder("", false, null);
        ISourceModule cu= createISourceModule("C", defaultFragment);
        
        assertTrue(projectRoot.getRawBuildpathEntry().getInclusionPatterns().length == 1);
        cu= (ISourceModule)executeOperation(IBuildpathInformationProvider.INCLUDE, cu, null, null);
        assertTrue(contains(cu.getPath().removeFirstSegments(1), projectRoot.getRawBuildpathEntry().getInclusionPatterns(), null));
        assertTrue(projectRoot.getRawBuildpathEntry().getInclusionPatterns().length == 2);
        
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    public void testExcludeIncludedFileWithIncludedFragment() throws ModelException, InvocationTargetException, InterruptedException {
        // Important here is that the return value must be of type IFile and not
        // ICompilation unit because the fragment is still included
        IProjectFragment projectRoot= getProjectRoot(fProject.getCorrespondingResource());
        IScriptFolder fragment= createFragmentOnProject();
        IProjectFragment root= getProjectRoot(fragment.getUnderlyingResource());
        
        // first include the fragment
        assertTrue(projectRoot.getRawBuildpathEntry().getInclusionPatterns().length == 0);
        assertFalse(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        fragment= (IScriptFolder)executeOperation(IBuildpathInformationProvider.INCLUDE, fragment, null, null);
        assertTrue(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        
        // then include the file
        IScriptFolder defaultFragment= projectRoot.createScriptFolder("", false, null);
        ISourceModule cu= createISourceModule("C", defaultFragment);
        
        assertTrue(projectRoot.getRawBuildpathEntry().getInclusionPatterns().length == 1);
        cu= (ISourceModule)executeOperation(IBuildpathInformationProvider.INCLUDE, cu, null, null);
        assertTrue(contains(cu.getPath().removeFirstSegments(1), projectRoot.getRawBuildpathEntry().getInclusionPatterns(), null));
        assertTrue(projectRoot.getRawBuildpathEntry().getInclusionPatterns().length == 2);
        
        // exclude the file
        IFile excludedFile= (IFile)executeOperation(IBuildpathInformationProvider.EXCLUDE, cu, null, null);
        assertTrue(projectRoot.getRawBuildpathEntry().getInclusionPatterns().length == 1);
        assertFalse(contains(excludedFile.getProjectRelativePath(), projectRoot.getRawBuildpathEntry().getInclusionPatterns(), null));
        assertTrue(contains(excludedFile.getProjectRelativePath(), projectRoot.getRawBuildpathEntry().getExclusionPatterns(), null));
        
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    public void testUnincludeIncludedFileWithIncludedFragment() throws ModelException, InvocationTargetException, InterruptedException {
        // Important here is that the return value must be of type IFile and not
        // ICompilation unit because the fragment is still included
        IProjectFragment projectRoot= getProjectRoot(fProject.getCorrespondingResource());
        IScriptFolder fragment= createFragmentOnProject();
        IProjectFragment root= getProjectRoot(fragment.getUnderlyingResource());
        
        // first include the fragment
        assertTrue(projectRoot.getRawBuildpathEntry().getInclusionPatterns().length == 0);
        assertFalse(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        fragment= (IScriptFolder)executeOperation(IBuildpathInformationProvider.INCLUDE, fragment, null, null);
        assertTrue(contains(fragment.getPath().removeFirstSegments(1), root.getRawBuildpathEntry().getInclusionPatterns(), null));
        
        // then include the file
        IScriptFolder defaultFragment= projectRoot.createScriptFolder("", false, null);
        ISourceModule cu= createISourceModule("C", defaultFragment);
        
        assertTrue(projectRoot.getRawBuildpathEntry().getInclusionPatterns().length == 1);
        cu= (ISourceModule)executeOperation(IBuildpathInformationProvider.INCLUDE, cu, null, null);
        assertTrue(contains(cu.getPath().removeFirstSegments(1), projectRoot.getRawBuildpathEntry().getInclusionPatterns(), null));
        assertTrue(projectRoot.getRawBuildpathEntry().getInclusionPatterns().length == 2);
        
        // uninclude the file
        IFile file= (IFile)executeOperation(IBuildpathInformationProvider.UNINCLUDE, cu, null, null);
        assertTrue(projectRoot.getRawBuildpathEntry().getInclusionPatterns().length == 1);
        assertFalse(contains(file.getProjectRelativePath(), projectRoot.getRawBuildpathEntry().getInclusionPatterns(), null));
        assertTrue(projectRoot.getRawBuildpathEntry().getExclusionPatterns().length == 0);
        
        testProjectIsOnBuildpath(true);
        
        validateBuildpath();
    }
    
    protected IProjectFragment createFragmentRootAndKeepProjAsRoot() throws CoreException, InvocationTargetException, InterruptedException {
        BuildpathModifierQueries.ICreateFolderQuery folderQuery= new BuildpathModifierQueries.ICreateFolderQuery() {

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
        IProjectFragment root= (IProjectFragment)executeOperation(IBuildpathInformationProvider.CREATE_FOLDER, null, folderQuery, null);
        return root;
    }
    
    protected IScriptFolder createFragmentOnProject() throws ModelException {
        IFolder fragmentFolder= getFolderHandle(new Path(fNormalFolder));
        IProjectFragment root= getProjectRoot(fragmentFolder);
        IScriptFolder fragment= root.getScriptFolder(fragmentFolder.getName());
        assertTrue(fragment.exists());
        return fragment;
    }
    
    protected IProjectFragment includePackageAndKeepProjAsRoot() throws CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment root= createFragmentRootAndKeepProjAsRoot();
        
        IFolder folder= getFolderHandle(root.getPath().removeFirstSegments(1).append(fSubFolder));
        
        IBuildpathEntry entry= root.getRawBuildpathEntry();
        int before= entry.getInclusionPatterns().length;
        
        // include
        executeOperation(IBuildpathInformationProvider.INCLUDE, folder, null, null);
        
        entry= root.getRawBuildpathEntry();
        IPath[] inclusionPatterns= entry.getInclusionPatterns();
        int after= inclusionPatterns.length;
        assertTrue(contains(new Path(folder.getName()), inclusionPatterns, null));
        assertTrue(before + 1 == after);
        return root;
    }
    
    protected IProjectFragment excludePackageAndKeepProjAsRoot() throws CoreException, InvocationTargetException, InterruptedException {
        IProjectFragment root= createFragmentRootAndKeepProjAsRoot();
        
        IFolder folder= getFolderHandle(root.getPath().removeFirstSegments(1).append(fSubFolder));
        
        IBuildpathEntry entry= root.getRawBuildpathEntry();
        int before= entry.getExclusionPatterns().length;
        
        // include
        folder= (IFolder)executeOperation(IBuildpathInformationProvider.EXCLUDE, root.getScriptFolder(folder.getName()), null, null);
        
        entry= root.getRawBuildpathEntry();
        IPath[] exclusionPatterns= entry.getExclusionPatterns();
        int after= exclusionPatterns.length;
        assertTrue(contains(new Path(folder.getName()), exclusionPatterns, null));
        assertTrue(before + 1 == after);
        return root;
    }
    
    protected void testProjectIsOnBuildpath(boolean isOnBuildpath) throws ModelException {
        assertTrue((BuildpathModifier.getBuildpathEntryFor(fProject.getPath(), fProject, IBuildpathEntry.BPE_SOURCE) != null) == isOnBuildpath);
    }
}
