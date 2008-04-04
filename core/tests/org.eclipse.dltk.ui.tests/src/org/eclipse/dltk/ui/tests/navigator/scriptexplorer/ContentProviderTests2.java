/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.ui.tests.navigator.scriptexplorer;

import java.io.File;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.tests.DLTKUITestsPlugin;
import org.eclipse.dltk.ui.tests.ScriptProjectHelper;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;


/**
 * Tests for the PackageExplorerContentProvider.
 * 
	 *
 */
public class ContentProviderTests2 extends TestCase{

//
//	public static Test suite() {
//		TestSuite suite= new TestSuite("org.eclipse.dltk.ui.ContentProviderTests2"); //$NON-NLS-1$
//		//$JUnit-BEGIN$
//	   suite.addTestSuite(ContentProviderTests2.class);
//		//$JUnit-END$
//		return suite;
//	}
//	
	private IProjectFragment fRoot1;
	private IScriptFolder fPack1;
	private IScriptFolder fPack2;
	private IScriptFolder fPack4;
	private IScriptFolder fPack3;
	private IWorkspace fWorkspace;
	private IWorkbench fWorkbench;
	private MockPluginView fMyPart;
	
	private ITreeContentProvider fProvider;
	
	private IScriptFolder fPack5;
	private IScriptProject fJProject3;
	private IScriptFolder fPack6;
	private IProjectFragment fInternalRoot1;
	private IScriptFolder fA;
	private IScriptFolder fX;
	private IScriptFolder fB;
	private IScriptFolder fC;
	private IScriptFolder fY;
	private IFile fFile1;
	private IFile fFile2;
	private ISourceModule fCU1;
	private ISourceModule fCU2;
	
	private IWorkbenchPage page;
	private boolean fEnableAutoBuildAfterTesting;
	
	public ContentProviderTests2(String name) {
		super(name);
	}
	
	public void testGetChildrenProject() throws Exception{
		Object[] expectedChildren= new Object[]{fPack1, fPack2, fPack3, fInternalRoot1, fFile1, fFile2}; //$NON-NLS-1$
		Object[] children= fProvider.getChildren(fJProject3);
		assertTrue("Wrong children found for project", compareArrays(children, expectedChildren)); //$NON-NLS-1$
	}
	
	public void testGetChildrentMidLevelFragment() throws Exception{
		Object[] expectedChildren= new Object[]{fPack4, fPack5};
		Object[] children= fProvider.getChildren(fPack3);
		assertTrue("Wrong children found for ScriptFolder",compareArrays(children, expectedChildren)); //$NON-NLS-1$
	}
	
	public void testGetChildrenBottomLevelFragment() throws Exception{
		Object[] expectedChildren= new Object[]{};
		Object[] children= fProvider.getChildren(fPack1);
		assertTrue("Wrong children found for ScriptFolder",compareArrays(children, expectedChildren)); //$NON-NLS-1$
	}
	
	public void testGetChildrenBottomLevelFragmentWithCU() throws Exception{
		Object[] expectedChildren= new Object[]{fCU1.getResource()};
		Object[] children= fProvider.getChildren(fPack2);
		assertTrue("Wrong children found for ScriptFolder with CU",compareArrays(children, expectedChildren)); //$NON-NLS-1$
	}
	
	public void testGetChildrenBottomLevelFragmentFile() throws Exception{
		Object[] expectedChildren= new Object[]{};
		Object[] children= fProvider.getChildren(fPack1);
		assertTrue("Wrong children found for ScriptFolder with File",compareArrays(children, expectedChildren)); //$NON-NLS-1$
	}
	
	public void testGetChildrenBottomLevelFragment2() throws Exception{
		Object[] expectedChildren= new Object[]{fCU2.getResource()};
		Object[] children= fProvider.getChildren(fPack6);
		assertTrue("Wrong children found for ScriptFolder",compareArrays(children, expectedChildren)); //$NON-NLS-1$
	}

	public void testGetChildrenMidLevelFragmentInInternalArchive() throws Exception{
		Object[] expectedChildren= new Object[]{fC};
		Object[] children= fProvider.getChildren(fB);
		assertTrue("wrong children found for a NON bottom ScriptFolder in ProjectFragment Internal Archive", compareArrays(children, expectedChildren));//$NON-NLS-1$
	}	
	
	public void getChildrenInternalArchive() throws Exception{
		Object[] expectedChildren= new Object[]{fX,fA, fInternalRoot1.getScriptFolder("")}; //$NON-NLS-1$
		Object[] children= fProvider.getChildren(fInternalRoot1);	
		assertTrue("Wrong child found for ProjectFragment Internal Archive", compareArrays(children,expectedChildren));//$NON-NLS-1$
	}
	
	//---------------Get Parent Tests-----------------------------
	
	public void testGetParentArchive() throws Exception{
		Object parent= fProvider.getParent(fInternalRoot1);
//		assertTrue("Wrong parent found for ProjectFragment Archive", parent==fJProject3); //$NON-NLS-1$
	}

	public void testGetParentMidLevelFragmentInArchive() throws Exception{
		Object expectedParent= fB;
		Object parent= fProvider.getParent(fC);
		assertTrue("Wrong parent found for a NON top level ScriptFolder in an Archive", expectedParent.equals(parent)); //$NON-NLS-1$
	}	
	
	public void testGetParentTopLevelFragmentInArchive() throws Exception{
		Object expectedParent= fInternalRoot1;
		Object parent= fProvider.getParent(fA);
		assertTrue("Wrong parent found for a top level ScriptFolder in an Archive", expectedParent.equals(parent));	 //$NON-NLS-1$
	}
	
	public void testGetParentTopLevelFragment() throws Exception{
		Object expectedParent= fJProject3;
		Object parent= fProvider.getParent(fPack3);
		assertTrue("Wrong parent found for a top level ScriptFolder", expectedParent.equals(parent)); //$NON-NLS-1$
	}
	
	public void testGetParentMidLevelFragment() throws Exception{
		Object expectedParent= fPack3;
		Object parent= fProvider.getParent(fPack5);
		assertTrue("Wrong parent found for a NON top level ScriptFolder", expectedParent.equals(parent)); //$NON-NLS-1$
	}
	
	
	//-------------------Set up methods--------------------------------
	/**
	 * @see TestCase#setUp()
	 */
	
	protected void setUp() throws Exception {
		super.setUp();
		
			
		fWorkspace= ResourcesPlugin.getWorkspace();
		assertNotNull(fWorkspace);
		IWorkspaceDescription workspaceDesc= fWorkspace.getDescription();
		fEnableAutoBuildAfterTesting= workspaceDesc.isAutoBuilding();
		if (fEnableAutoBuildAfterTesting)
			ScriptProjectHelper.setAutoBuilding(false);
		
		//create project
		fJProject3= ScriptProjectHelper.createScriptProject("TestProject3"); //$NON-NLS-1$ 
		assertNotNull("project3 null", fJProject3); //$NON-NLS-1$
		
		Object[] resource= fJProject3.getForeignResources();
		for (int i = 0; i < resource.length; i++) {
			Object object = resource[i];
			if(object instanceof IFile){
				IFile file = (IFile) object;
				if(".buildpath".equals(file.getName())) //$NON-NLS-1$
					fFile1= file;
				else if (".project".equals(file.getName())) //$NON-NLS-1$
					fFile2= file;
			}
		}
		assertNotNull(fFile1);
		assertNotNull(fFile2);

		
		//create the ProjectFragment that represents the project as source folder
		fRoot1= ScriptProjectHelper.addSourceContainer(fJProject3, ""); //$NON-NLS-1$
		assertNotNull("getting default package", fRoot1); //$NON-NLS-1$
		
		//set up project #3: file system structure with project as source folder
		//add an internal jar
		File myInternalLibArchive= DLTKUITestsPlugin.getDefault().getFileInPlugin(new Path("testresources/myinternallib.zip")); //$NON-NLS-1$
		assertTrue("lib not found", myInternalLibArchive != null && myInternalLibArchive.exists()); //$NON-NLS-1$
		fInternalRoot1= ScriptProjectHelper.addLibraryWithImport(fJProject3, Path.fromOSString(myInternalLibArchive.getPath()));
	
		//create internal ScriptFolders
		fA= fInternalRoot1.getScriptFolder("a"); //$NON-NLS-1$
		fX= fInternalRoot1.getScriptFolder("x"); //$NON-NLS-1$
		fB= fInternalRoot1.getScriptFolder("a/b"); //$NON-NLS-1$
		fC= fInternalRoot1.getScriptFolder("a/b/c"); //$NON-NLS-1$
		fInternalRoot1.getScriptFolder("a/d"); //$NON-NLS-1$
		fY= fInternalRoot1.getScriptFolder("x/y"); //$NON-NLS-1$
			
		//create ScriptFolders
		fPack1= fRoot1.createScriptFolder("pack1", true, null); //$NON-NLS-1$
		fPack2= fRoot1.createScriptFolder("pack2", true, null); //$NON-NLS-1$
		fPack3= fRoot1.createScriptFolder("pack3",true,null); //$NON-NLS-1$
		fPack4= fRoot1.createScriptFolder("pack3/pack4", true,null); //$NON-NLS-1$
		fPack5= fRoot1.createScriptFolder("pack3/pack5",true,null); //$NON-NLS-1$
		fPack6= fRoot1.createScriptFolder("pack3/pack5/pack6", true, null); //$NON-NLS-1$
		
		fCU1= fPack2.createSourceModule("Object.java", "", true, null); //$NON-NLS-1$ //$NON-NLS-2$
		fCU2= fPack6.createSourceModule("Object.java","", true, null); //$NON-NLS-1$ //$NON-NLS-2$
		
		//set up the mock view
		setUpMockView();
	}
	
	public void setUpMockView() throws Exception {
//		fWorkspace = ResourcesPlugin.getWorkspace();
//		assertNotNull(fWorkspace);

		fWorkbench = PlatformUI.getWorkbench();
		assertNotNull(fWorkbench);

		page = fWorkbench.getActiveWorkbenchWindow().getActivePage();
		assertNotNull(page);

		//just testing to make sure my part can be created
		IViewPart myPart = new MockPluginView();
		assertNotNull(myPart);

		myPart = page.showView("org.eclipse.dltk.ui.tests.packageview.MockPluginView"); //$NON-NLS-1$
		if (myPart instanceof MockPluginView) {
			fMyPart = (MockPluginView) myPart;
			fMyPart.setFolding(false);
			// above call might cause a property change event being sent
			fMyPart.fRefreshedObjects.clear();
			fProvider = (ITreeContentProvider) fMyPart.getTreeViewer().getContentProvider();
		} else
			assertTrue("Unable to get view", false); //$NON-NLS-1$

		assertNotNull(fProvider);
	}
	
	/**
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		fInternalRoot1.close();
		ScriptProjectHelper.delete(fJProject3);
		page.hideView(fMyPart);
		
		if (fEnableAutoBuildAfterTesting)
			ScriptProjectHelper.setAutoBuilding(true);

		super.tearDown();
	}
	
	/**
	 * Method compareArrays. Both arrays must be of IScriptFolders or compare will fail.
	 * @param children
	 * @param expectedChildren
	 * @return boolean
	 */
	private boolean compareArrays(Object[] children, Object[] expectedChildren) {
		if(children.length!=expectedChildren.length)
			return false;
		for (int i= 0; i < children.length; i++) {
			Object child= children[i];
			if (child instanceof IModelElement) {
				IModelElement el= (IModelElement) child;
				if(!contains(el, expectedChildren))
					return false;
			} else if(child instanceof IResource){
				IResource res = (IResource) child;	
				if(!contains(res, expectedChildren)){
					return false;	
				}
			}
		}
		return true;
	}
	/**
	 * Method contains.
	 * @param res
	 * @param expectedChildren
	 * @return boolean
	 */
	private boolean contains(IResource res, Object[] expectedChildren) {
		for (int i= 0; i < expectedChildren.length; i++) {
			Object object= expectedChildren[i];
			if (object instanceof IResource) {
				IResource expres= (IResource) object;
				if(expres.equals(res))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Method contains.
	 * @param fragment
	 * @param expectedChildren
	 * @return boolean
	 */
	private boolean contains(IModelElement fragment, Object[] expectedChildren) {
		for (int i= 0; i < expectedChildren.length; i++) {
			Object object= expectedChildren[i];
			if (object instanceof IModelElement) {
				IModelElement expfrag= (IModelElement) object;
				if(expfrag.equals(fragment))
					return true;
			}
		}
		return false;
	}
}
