/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.tests.navigator.scriptexplorer;

import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.ElementChangedEvent;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IElementChangedListener;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.ui.wizards.buildpath.BPListElement;
import org.eclipse.dltk.ui.tests.DLTKProjectHelper;
import org.eclipse.dltk.ui.tests.DLTKUITestsPlugin;
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
public class ContentProviderTests1 extends TestCase {



	public static Test suite() {
		TestSuite suite= new TestSuite("org.eclipse.dltk.ui.ContentProviderTests1"); //$NON-NLS-1$
		//$JUnit-BEGIN$
	   suite.addTestSuite(ContentProviderTests1.class);
		//$JUnit-END$
		return suite;
	}

	private IDLTKProject fJProject1;
	private IDLTKProject fJProject2;
	
	private IProjectFragment fRoot1;
	private IScriptFolder fPack1;
	private IScriptFolder fPack2;
	private IScriptFolder fPack4;
	private IScriptFolder fPack3;
	private IWorkspace fWorkspace;
	private IWorkbench fWorkbench;
	private MockPluginView fMyPart;
	
	private ITreeContentProvider fProvider;
	private IProjectFragment fArchiveFragmentRoot;
	private IScriptFolder fPackJunit;
	private IScriptFolder fPackJunitSamples;
	private IScriptFolder fPackJunitSamplesMoney;

	private IScriptFolder fPack5;
	private IScriptFolder fPack6;
	private IFile fFile1;
	private IFile fFile2;
	private IResource fCUIMoney;
	private IResource fCUMoney;
	private IResource fCUMoneyBag;
	private IResource fCUMoneyTest;
	
	private ISourceModule fCU1;
	private ISourceModule fCU2;
	
	private IWorkbenchPage page;
	private ISourceModule fCUAllTests;
	private ISourceModule fCUVectorTest;
	private ISourceModule fCUSimpleTest;
	private boolean fEnableAutoBuildAfterTesting;
	private ISourceModule fCU3;
	
	public ContentProviderTests1(String name) {
		super(name);
	}
	
	//---------Test for getChildren-------------------

	public void testGetChildrenProjectWithSourceFolders() throws Exception{
		Object[] expectedChildren= new Object[]{fRoot1, fFile1, fFile2};
		Object[] children= fProvider.getChildren(fJProject2);
		assertTrue("Wrong children found for project", compareArrays(children, expectedChildren));//$NON-NLS-1$
	}
	
	
	public void testGetChildrenMidLevelFragment() throws Exception{
		Object[] expectedChildren= new Object[]{fPack4, fPack5};
		Object[] children= fProvider.getChildren(fPack3);
		assertTrue("Wrong children found for ScriptFolder",compareArrays(children, expectedChildren));//$NON-NLS-1$
	}
	
	public void testGetChildrenBottomLevelFragment() throws Exception{
		Object[] expectedChildren= new Object[]{fCU1};
		Object[] children= fProvider.getChildren(fPack2);
		assertTrue("Wrong children found for ScriptFolder",compareArrays(children, expectedChildren));//$NON-NLS-1$
	}

	public void testGetChildrenMidLevelFragmentInArchive() throws Exception{
		Object[] expectedChildren= new Object[]{fPackJunitSamplesMoney, fCUAllTests, fCUSimpleTest, fCUVectorTest };
		Object[] children= fProvider.getChildren(fPackJunitSamples);
		assertTrue("wrong chidren found for mid level ScriptFolder in Archive", compareArrays(children, expectedChildren));//$NON-NLS-1$
	}

	public void testGetChildrenBottomLevelFragmentInArchive() throws Exception{
		Object[] expectedChildren= new Object[]{fCUIMoney, fCUMoney, fCUMoneyBag, fCUMoneyTest};
		Object[] children= fProvider.getChildren(fPackJunitSamplesMoney);
		assertTrue("wrong children found for a bottom ScriptFolder in ProjectFragment Archive", compareArrays(children, expectedChildren));	//$NON-NLS-1$
	}
	
	public void testGetChildrenSourceFolder() throws Exception {
		Object[] expectedChildren = new Object[] { fPack1, fPack2, fPack3, fRoot1.getScriptFolder("")};//$NON-NLS-1$
		Object[] children = fProvider.getChildren(fRoot1);
		assertTrue("Wrong children found for ProjectFragment", compareArrays(children, expectedChildren));//$NON-NLS-1$
	}
	
	public void testGetChildrenArchive(){	Object[] expectedChildren= new Object[]{fPackJunit, fArchiveFragmentRoot.getScriptFolder("")};//$NON-NLS-1$
		Object[] children= fProvider.getChildren(fArchiveFragmentRoot);
		assertTrue("Wrong child found for ProjectFragment Archive", compareArrays(children,expectedChildren));//$NON-NLS-1$
	}
	
	//---------------Get Parent Tests-----------------------------
	
	public void testGetParentArchive() throws Exception{
		Object parent= fProvider.getParent(fArchiveFragmentRoot);
		assertTrue("Wrong parent found for ProjectFragment Archive", parent==fJProject1);//$NON-NLS-1$
	}

	public void testGetParentMidLevelFragmentInArchive() throws Exception{
		Object expectedParent= fPackJunitSamples;
		Object parent= fProvider.getParent(fPackJunitSamplesMoney);
		assertTrue("Wrong parent found for a NON top level ScriptFolder in an Archive", expectedParent.equals(parent));//$NON-NLS-1$
	}	
	
	public void testGetParentTopLevelFragmentInArchive() throws Exception{
		Object expectedParent= fPackJunit;
		Object parent= fProvider.getParent(fPackJunitSamples);
		assertTrue("Wrong parent found for a top level ScriptFolder in an Archive", expectedParent.equals(parent));	//$NON-NLS-1$
	}
	
	public void testGetParentTopLevelFragment() throws Exception{
		Object expectedParent= fRoot1;
		Object parent= fProvider.getParent(fPack3);
		assertTrue("Wrong parent found for a top level ScriptFolder", expectedParent.equals(parent));//$NON-NLS-1$
	}
	
	public void testGetParentMidLevelFragment() throws Exception{
		Object expectedParent= fPack3;
		Object parent= fProvider.getParent(fPack4);
		assertTrue("Wrong parent found for a NON top level ScriptFolder", expectedParent.equals(parent));//$NON-NLS-1$
	}
	
	
	public void testDeleteBottomLevelFragment() throws Exception{
		
		//send a delta indicating fragment deleted
		IElementChangedListener listener= (IElementChangedListener)fProvider;
		IModelElementDelta delta= TestDelta.createDelta(fPack4, IModelElementDelta.REMOVED);
		listener.elementChanged(new ElementChangedEvent(delta, ElementChangedEvent.POST_CHANGE));
		
		//force events from dispaly
		while(fMyPart.getTreeViewer().getControl().getDisplay().readAndDispatch()) {}
		
		assertTrue("Remove happened", fMyPart.hasRemoveHappened());//$NON-NLS-1$
		assertTrue("Correct Remove", fMyPart.getRemovedObject().contains(fPack4));//$NON-NLS-1$
		assertEquals("No refreshes", 0, fMyPart.getRefreshedObject().size());//$NON-NLS-1$
	}
	
	public void testAddBottomLevelFragment() throws Exception {
		IScriptFolder test= fRoot1.createScriptFolder("test", true, null);//$NON-NLS-1$

		//send a delta indicating fragment deleted
		IElementChangedListener listener= (IElementChangedListener) fProvider;
		IModelElementDelta delta= TestDelta.createDelta(test, IModelElementDelta.ADDED);
		listener.elementChanged(new ElementChangedEvent(delta, ElementChangedEvent.POST_CHANGE));

		//force events from dispaly
		while(fMyPart.getTreeViewer().getControl().getDisplay().readAndDispatch()) {}

		assertTrue("Add happened", fMyPart.hasAddHappened()); //$NON-NLS-1$
		assertTrue("Correct Add", test.equals(fMyPart.getAddedObject())); //$NON-NLS-1$
		assertEquals("No refreshes", 0, fMyPart.getRefreshedObject().size()); //$NON-NLS-1$
	}

	public void testChangedTopLevelScriptFolder() throws Exception {
		//send a delta indicating fragment deleted
		IElementChangedListener listener= (IElementChangedListener) fProvider;
		IModelElementDelta delta= TestDelta.createDelta(fPack3, IModelElementDelta.CHANGED);
		listener.elementChanged(new ElementChangedEvent(delta, ElementChangedEvent.POST_CHANGE));

		//force events from display
		while(fMyPart.getTreeViewer().getControl().getDisplay().readAndDispatch()) {}

		assertEquals("No refresh happened", 0, fMyPart.getRefreshedObject().size()); //$NON-NLS-1$
	}
	
	public void testChangeBottomLevelScriptFolder() throws Exception{
		//send a delta indicating fragment deleted
		fMyPart.fRefreshedObjects= new ArrayList();
		IElementChangedListener listener= (IElementChangedListener) fProvider;
		IModelElementDelta delta= TestDelta.createDelta(fPack6, IModelElementDelta.CHANGED);
		listener.elementChanged(new ElementChangedEvent(delta, ElementChangedEvent.POST_CHANGE));
		
		//force events from display
		while(fMyPart.getTreeViewer().getControl().getDisplay().readAndDispatch()) {}

		assertEquals("No refresh happened", 0, fMyPart.getRefreshedObject().size());//$NON-NLS-1$
	}
		
	public void testRemoveCUsFromScriptFolder() throws Exception{
		
		//send a delta indicating fragment deleted
		IElementChangedListener listener= (IElementChangedListener) fProvider;
		IModelElementDelta delta= TestDelta.createCUDelta(new ISourceModule[] { fCU2, fCU3 }, fPack6, IModelElementDelta.REMOVED);
		listener.elementChanged(new ElementChangedEvent(delta, ElementChangedEvent.POST_CHANGE));
		
		//force events from display
		while(fMyPart.getTreeViewer().getControl().getDisplay().readAndDispatch()) {}

		// removing more than one CU now results in a refresh.
		assertEquals("One refresh", 1, fMyPart.getRefreshedObject().size()); //$NON-NLS-1$
	}
	
	public void testRemoveCUFromScriptFolder() throws Exception {
		
		//send a delta indicating fragment deleted
		IElementChangedListener listener= (IElementChangedListener) fProvider;
		IModelElementDelta delta= TestDelta.createCUDelta(new ISourceModule[]{fCU2}, fPack6, IModelElementDelta.REMOVED);
		listener.elementChanged(new ElementChangedEvent(delta, ElementChangedEvent.POST_CHANGE));

		//force events from display			
		while(fMyPart.getTreeViewer().getControl().getDisplay().readAndDispatch()) {}

		assertTrue("Remove happened", fMyPart.hasRemoveHappened()); //$NON-NLS-1$
		assertTrue("Correct refresh", fMyPart.getRemovedObject().contains(fCU2)); //$NON-NLS-1$
		assertEquals("No refreshes", 0, fMyPart.getRefreshedObject().size()); //$NON-NLS-1$
	}
	
	public void testBug65240() throws Exception {
		IBuildpathEntry[] rawBuildpath= fJProject2.getRawBuildpath();
		IBuildpathEntry src1= rawBuildpath[0];
		BPListElement element= BPListElement.createFromExisting(src1, fJProject2);
		element.setAttribute(BPListElement.INCLUSION, new IPath[] {new Path("pack3/pack5/")});
		fJProject2.setRawBuildpath(new IBuildpathEntry[] {element.getBuildpathEntry()}, null);
		//TODO: right answer is Object[]{fPack4, fPack4.getResource(), fPack5} 
		//but i don't know if fPack4 should be equal to fPack4.getResource() or this is a bug
		Object[] expectedChildren= new Object[]{fPack4, fPack5};
		Object[] children= fProvider.getChildren(fPack3.getResource());
		assertTrue("Wrong children found for project", compareArrays(children, expectedChildren));//$NON-NLS-1$
	}
	
//	public void testAddWorkingCopyCU() throws Exception {
//		//test for bug 106452: Paste of source into container doesn't refresh package explorer
//		ISourceModule cu= fPack6.createSourceModule("New.txt","class New {}", true, null);//$NON-NLS-1$//$NON-NLS-2$
//		cu.becomeWorkingCopy(null, null);
//		
//		try {
//			fMyPart.getTreeViewer().setInput(fJProject1.getScriptModel());
//			fMyPart.getTreeViewer().reveal(fCU2);
//			((PackageExplorerContentProvider) fMyPart.getTreeViewer().getContentProvider()).setProvideMembers(false);
//			
//			//force events from display			
//			while (fMyPart.getTreeViewer().getControl().getDisplay().readAndDispatch()) {
//			}
//			
//			IElementChangedListener listener= (IElementChangedListener) fProvider;
//			IModelElementDelta delta= TestDelta.createCUDelta(new ISourceModule[] { cu }, fPack6, IModelElementDelta.ADDED);
//			listener.elementChanged(new ElementChangedEvent(delta, ElementChangedEvent.POST_CHANGE));
//			
//			//force events from display			
//			while (fMyPart.getTreeViewer().getControl().getDisplay().readAndDispatch()) {
//			}
//
//			assertTrue("No add happened", ! fMyPart.hasAddHappened()); //$NON-NLS-1$
//			assertTrue("Refresh happened", fMyPart.hasRefreshHappened()); //$NON-NLS-1$
//			if (fMyPart.getRefreshedObject().size() != 1)
//				fail("One refresh expected, was:\n" + fMyPart.getRefreshedObject()); //$NON-NLS-1$
//			assertEquals("Correct refresh", fPack6, fMyPart.getRefreshedObject().get(0)); //$NON-NLS-1$
//			
//		} finally {
//			cu.discardWorkingCopy();
//		}
//	}
	

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
			DLTKProjectHelper.setAutoBuilding(false);
		
		assertNotNull(fWorkspace);	
		
		fJProject1= DLTKProjectHelper.createDLTKProject("TestProject1");//$NON-NLS-1$
		fJProject2= DLTKProjectHelper.createDLTKProject("TestProject2");//$NON-NLS-1$
		
		assertNotNull("project1 null", fJProject1);//$NON-NLS-1$
		assertNotNull("project2 null", fJProject2);//$NON-NLS-1$
		
		fJProject1.setRawBuildpath(new IBuildpathEntry[0], null);

		Object[] resource= fJProject2.getForeignResources();
		for (int i = 0; i < resource.length; i++) {
			Object object = resource[i];
			if(object instanceof IFile){
				IFile file = (IFile) object;
				if(".buildpath".equals(file.getName()))//$NON-NLS-1$
					fFile1= file;
				else if (".project".equals(file.getName()))//$NON-NLS-1$
					fFile2= file;
			}
		}
		assertNotNull(fFile1);
		assertNotNull(fFile2);
				
		java.io.File junitSrcArchive= DLTKUITestsPlugin.getDefault().getFileInPlugin(DLTKProjectHelper.JUNIT_SRC_381);
		assertTrue("junit src not found", junitSrcArchive != null && junitSrcArchive.exists());//$NON-NLS-1$

		fArchiveFragmentRoot= DLTKProjectHelper.addSourceContainerWithImport(fJProject1, "src", junitSrcArchive, DLTKProjectHelper.JUNIT_SRC_ENCODING);//$NON-NLS-1$
		assertTrue("Unable to create zipfile archive",fArchiveFragmentRoot.exists());//$NON-NLS-1$
		
		fPackJunit= fArchiveFragmentRoot.getScriptFolder("junit");//$NON-NLS-1$
		fPackJunitSamples= fArchiveFragmentRoot.getScriptFolder("junit/samples");//$NON-NLS-1$
		fPackJunitSamplesMoney= fArchiveFragmentRoot.getScriptFolder("junit/samples/money");//$NON-NLS-1$
		
		assertNotNull("creating fPackJunit", fPackJunit);//$NON-NLS-1$
		assertNotNull("creating fPackJunitSamples", fPackJunitSamples);//$NON-NLS-1$
		assertNotNull("creating fPackJunitSamplesMoney",fPackJunitSamplesMoney);//$NON-NLS-1$
		
		fCUIMoney= fPackJunitSamplesMoney.getSourceModule("IMoney.java").getResource();//$NON-NLS-1$
		fCUMoney= fPackJunitSamplesMoney.getSourceModule("Money.java").getResource();//$NON-NLS-1$
		fCUMoneyBag= fPackJunitSamplesMoney.getSourceModule("MoneyBag.java").getResource();//$NON-NLS-1$
		fCUMoneyTest= fPackJunitSamplesMoney.getSourceModule("MoneyTest.java").getResource();//$NON-NLS-1$
		
		fCUAllTests= fPackJunitSamples.getSourceModule("AllTests.txt");//$NON-NLS-1$
		fCUVectorTest= fPackJunitSamples.getSourceModule("VectorTest.txt");//$NON-NLS-1$
		fCUSimpleTest= fPackJunitSamples.getSourceModule("SimpleTest.txt");//$NON-NLS-1$
		//set up project #2: file system structure with in a source folder

	//	DLTKProjectHelper.addVariableEntry(fJProject2, new Path("InterpreterEnvironment_LIB_TEST"), null, null);

		fRoot1= DLTKProjectHelper.addSourceContainer(fJProject2, "src1");//$NON-NLS-1$
		fPack1= fRoot1.createScriptFolder("pack1", true, null);//$NON-NLS-1$
		fPack2= fRoot1.createScriptFolder("pack2", true, null);//$NON-NLS-1$
		fPack3= fRoot1.createScriptFolder("pack3",true,null);//$NON-NLS-1$
		fPack4= fRoot1.createScriptFolder("pack3/pack4", true,null);//$NON-NLS-1$
		fPack5= fRoot1.createScriptFolder("pack3/pack5",true,null);//$NON-NLS-1$
		fPack6= fRoot1.createScriptFolder("pack3/pack5/pack6", true, null);//$NON-NLS-1$
		
		fCU1= fPack2.createSourceModule("Object.txt", "", true, null);//$NON-NLS-1$//$NON-NLS-2$
		fCU2= fPack6.createSourceModule("Object.txt","", true, null);//$NON-NLS-1$//$NON-NLS-2$
		fCU3= fPack6.createSourceModule("Jen.txt","", true,null);//$NON-NLS-1$//$NON-NLS-2$
		
		//set up the mock view
		setUpMockView();
	}
	
	public void setUpMockView() throws Exception{
		
		fWorkbench= PlatformUI.getWorkbench();
		assertNotNull(fWorkbench);
		
		page= fWorkbench.getActiveWorkbenchWindow().getActivePage();
		assertNotNull(page);
		
		//just testing to make sure my part can be created
		IViewPart myPart= new MockPluginView();
		assertNotNull(myPart);
		
		myPart= page.showView("org.eclipse.dltk.ui.tests.packageview.MockPluginView");//$NON-NLS-1$
		if (myPart instanceof MockPluginView) {
			fMyPart= (MockPluginView) myPart;
			fMyPart.setFolding(false);
			// above call might cause a property change event being sent
			fMyPart.fRefreshedObjects.clear();
			fProvider= (ITreeContentProvider)fMyPart.getTreeViewer().getContentProvider();
		}else assertTrue("Unable to get view",false);//$NON-NLS-1$
	
		assertNotNull(fProvider);		
	}

	/**
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		fArchiveFragmentRoot.close();
			
		DLTKProjectHelper.delete(fJProject1);
		DLTKProjectHelper.delete(fJProject2);
		page.hideView(fMyPart);
		
		if (fEnableAutoBuildAfterTesting)
			DLTKProjectHelper.setAutoBuilding(true);
		
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
