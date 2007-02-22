/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.tcl.ui.tests.dialogs;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.buildpath.AddSelectedLibraryOperation;
import org.eclipse.dltk.internal.corext.buildpath.BuildpathModifier;
import org.eclipse.dltk.internal.corext.buildpath.ExcludeOperation;
import org.eclipse.dltk.internal.corext.buildpath.IBuildpathInformationProvider;
import org.eclipse.dltk.internal.corext.buildpath.IPackageExplorerActionListener;
import org.eclipse.dltk.internal.corext.buildpath.PackageExplorerActionEvent;
import org.eclipse.dltk.internal.corext.buildpath.RemoveFromBuildpathOperation;
import org.eclipse.dltk.internal.ui.util.CoreUtility;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierAction;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.DialogPackageExplorerActionGroup;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.IAddArchivesQuery;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.IAddLibrariesQuery;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.ICreateFolderQuery;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.IInclusionExclusionQuery;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.ILinkToQuery;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.BuildpathModifierQueries.IRemoveLinkedFolderQuery;
import org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage.DialogPackageExplorerActionGroup.DialogExplorerActionContext;
import org.eclipse.dltk.ui.tests.DLTKProjectHelper;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;


/**
 */
public class NewProjectWizardOperationTest extends TestCase implements IBuildpathInformationProvider {
    
    public static final Class THIS= NewProjectWizardOperationTest.class;
    protected IDLTKProject fProject;
    protected DialogPackageExplorerActionGroup fActionGroup;
    protected List fSelection;
    protected Object[] fItems;
    protected IPackageExplorerActionListener fListener;
    
    private final int PROJ= 0x00;
    private final int SRC= 0x01;
    private final int NF= 0x02;
    private final int PACK= 0x03;
    private final int CU= 0x04;
    private final int EXCLUDED_FILE= 0x05;
    private final int FILE= 0x06;
    private final int EXCLUDED_PACK= 0x07;
    private final int DEFAULT_PACK= 0x08;    
    private final int ZIP= 0xA; // not on buildpath
    
    /* ### Project Structure:
     * - DummyProject
     *        |- src
     *            |- default package
     *            |- pack1
     *                 |- A.java
     *                 |- B.java (excluded)
     *                 |- NormalFile
     *                 |- pack2 (excluded)
     *            |- archive.zip (on buildpath)
     *            |- archive.zip (excluded)
     *        |- NormalFolder
     */
    
    public NewProjectWizardOperationTest() {
        super(THIS.getName());
    }
    
    protected void setUp() throws Exception {
        fProject= createProject();
        fSelection= new ArrayList();
        fActionGroup= new DialogPackageExplorerActionGroup(this, null);
        assertFalse(fProject.isOnBuildpath(fProject.getUnderlyingResource()));
    }

    protected void tearDown() throws Exception {
        fActionGroup.dispose();
        fSelection.clear();
        fProject.getProject().delete(true, true, null);
    }
    
    public void testProjectWithOthers() throws ModelException {
        addToSelection(new int[] {PROJ});
        DialogExplorerActionContext context= createContext();
        addListener(new int[] {ADD_SEL_SF_TO_BP, CREATE_LINK});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, SRC});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, NF});
        context= createContext();
        addListener(new int[] {ADD_SEL_SF_TO_BP});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, PACK});
        context= createContext();
        addListener(new int[] {ADD_SEL_SF_TO_BP});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, CU});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, EXCLUDED_FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, EXCLUDED_PACK});
        context= createContext();
        addListener(new int[] {ADD_SEL_SF_TO_BP});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, DEFAULT_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();               
        
        addToSelection(new int[] {PROJ, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, SRC, NF});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, SRC, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, NF, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
                    
        addToSelection(new int[] {PROJ, PACK, NF});
        context= createContext();
        addListener(new int[] {ADD_SEL_SF_TO_BP});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, PACK, NF, DEFAULT_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, PACK, NF, EXCLUDED_PACK});
        context= createContext();
        addListener(new int[] {ADD_SEL_SF_TO_BP});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, PACK, NF, EXCLUDED_PACK, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, PACK, NF, EXCLUDED_PACK, DEFAULT_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, PACK, NF, CU});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
     
        
        addToSelection(new int[] {PROJ, PACK, NF, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, PACK, EXCLUDED_PACK, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
       
        
        addToSelection(new int[] {PROJ, SRC, PACK, NF, CU});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, PACK, NF, EXCLUDED_PACK, CU});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, PACK, NF, EXCLUDED_PACK, CU, DEFAULT_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, PACK, NF, EXCLUDED_PACK, EXCLUDED_FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, PACK, NF, EXCLUDED_PACK, EXCLUDED_FILE, DEFAULT_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, PACK, NF, EXCLUDED_PACK, FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, PACK, NF, EXCLUDED_PACK, FILE, DEFAULT_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, PACK, NF, EXCLUDED_PACK, CU, EXCLUDED_FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, PACK, NF, EXCLUDED_PACK, CU, DEFAULT_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, PACK, NF, EXCLUDED_PACK, CU, FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, PACK, NF, EXCLUDED_PACK, CU, FILE, DEFAULT_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, PACK, NF, EXCLUDED_PACK, EXCLUDED_FILE, FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, PACK, NF, EXCLUDED_PACK, EXCLUDED_FILE, FILE, DEFAULT_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, PACK, NF, EXCLUDED_PACK, CU, FILE, EXCLUDED_FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PROJ, PACK, NF, EXCLUDED_PACK, CU, FILE, EXCLUDED_FILE, DEFAULT_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
  
    }
    
    public void testSrcWithOthers() throws ModelException {
        addToSelection(new int[] {SRC});
        DialogExplorerActionContext context= createContext();
        addListener(new int[] {REMOVE_FROM_BP, EDIT_FILTERS});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {SRC, NF});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {SRC, PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {SRC, CU});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {SRC, EXCLUDED_FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {SRC, FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {SRC, EXCLUDED_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {SRC, DEFAULT_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {SRC, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
    }
    
    public void testNormalFolderWithOthers() throws ModelException {
        addToSelection(new int[] {NF});
        DialogExplorerActionContext context= createContext();
        addListener(new int[] {ADD_SEL_SF_TO_BP});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {NF, PACK});
        context= createContext();
        addListener(new int[] {ADD_SEL_SF_TO_BP});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {NF, CU});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {NF, EXCLUDED_FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {NF, FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {NF, EXCLUDED_PACK});
        context= createContext();
        addListener(new int[] {ADD_SEL_SF_TO_BP});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {NF, DEFAULT_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
 
        
        addToSelection(new int[] {NF, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {NF, PACK, CU});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {NF, PACK, FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {NF, PACK, EXCLUDED_PACK});
        context= createContext();
        addListener(new int[] {ADD_SEL_SF_TO_BP});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {NF, PACK, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
       
        addToSelection(new int[] {NF, PACK, CU, FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {NF, PACK, EXCLUDED_PACK, DEFAULT_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {NF, PACK, EXCLUDED_PACK, EXCLUDED_FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {NF, PACK, EXCLUDED_PACK, EXCLUDED_FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
    
        
        addToSelection(new int[] {NF, PACK, EXCLUDED_FILE, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {NF, PACK, EXCLUDED_PACK, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
     
    }
    
    public void testPackageWithOthers() throws ModelException {
        addToSelection(new int[] {PACK});
        DialogExplorerActionContext context= createContext();
        addListener(new int[] {ADD_SEL_SF_TO_BP, EXCLUDE});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PACK, CU});
        context= createContext();
        addListener(new int[] {EXCLUDE});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PACK, EXCLUDED_FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PACK, FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PACK, EXCLUDED_PACK});
        context= createContext();
        addListener(new int[] {ADD_SEL_SF_TO_BP});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PACK, DEFAULT_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
      
        
        addToSelection(new int[] {PACK, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PACK, EXCLUDED_PACK, DEFAULT_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
       
        
        addToSelection(new int[] {PACK, EXCLUDED_PACK, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PACK, EXCLUDED_PACK, CU, FILE, EXCLUDED_FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
       
        
        addToSelection(new int[] {PACK, EXCLUDED_PACK, CU, FILE, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {PACK, EXCLUDED_PACK, CU, FILE, EXCLUDED_FILE, DEFAULT_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
       
        
        addToSelection(new int[] {PACK, EXCLUDED_PACK, CU, FILE, EXCLUDED_FILE, DEFAULT_PACK, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
      
    }
    
    public void testCUWithOthers() throws ModelException {
        addToSelection(new int[] {CU});
        DialogExplorerActionContext context= createContext();
        addListener(new int[] {EXCLUDE});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {CU, PACK});
        context= createContext();
        addListener(new int[] {EXCLUDE});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {CU, EXCLUDED_FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {CU, FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {CU, EXCLUDED_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {CU, DEFAULT_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
      
        
        addToSelection(new int[] {CU, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {CU, EXCLUDED_PACK, PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        
        
        addToSelection(new int[] {CU, EXCLUDED_PACK, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
      
        
        addToSelection(new int[] {CU, PACK, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        
    }
    
    public void testExcludedFileWithOthers() throws ModelException {
        addToSelection(new int[] {EXCLUDED_FILE});
        DialogExplorerActionContext context= createContext();
        addListener(new int[] {UNEXCLUDE});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {EXCLUDED_FILE, EXCLUDED_PACK});
        context= createContext();
        addListener(new int[] {UNEXCLUDE});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {EXCLUDED_FILE, FILE});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {EXCLUDED_FILE, EXCLUDED_PACK});
        context= createContext();
        addListener(new int[] {UNEXCLUDE});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {EXCLUDED_FILE, DEFAULT_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
      
        
        addToSelection(new int[] {EXCLUDED_FILE, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {EXCLUDED_FILE, EXCLUDED_PACK, DEFAULT_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
       
        
        addToSelection(new int[] {EXCLUDED_FILE, EXCLUDED_PACK, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
    }
    
    public void testFileWithOthers() throws ModelException {
        addToSelection(new int[] {FILE});
        DialogExplorerActionContext context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {FILE, EXCLUDED_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {FILE, DEFAULT_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
      
        addToSelection(new int[] {FILE, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
    }
    
    public void testExcludedPackWithOthers() throws ModelException {
        addToSelection(new int[] {EXCLUDED_PACK});
        DialogExplorerActionContext context= createContext();
        addListener(new int[] {ADD_SEL_SF_TO_BP, UNEXCLUDE});
        fActionGroup.setContext(context);
        reset();
        
        addToSelection(new int[] {EXCLUDED_PACK, DEFAULT_PACK});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
    
        
        addToSelection(new int[] {EXCLUDED_PACK, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
      
        addToSelection(new int[] {EXCLUDED_PACK, DEFAULT_PACK, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
  
        
     
    }
    
    public void testDefaultPackWithOthers() throws ModelException {
        addToSelection(new int[] {DEFAULT_PACK});
        DialogExplorerActionContext context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
   
        
        addToSelection(new int[] {DEFAULT_PACK, ZIP});
        context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
      
    }
    
    public void testDefaultArchiveWithOthers() throws ModelException {
      
    }
    
    public void testDefaultZipWithOthers() throws ModelException, InvocationTargetException {
        addToSelection(new int[] {ZIP});
        DialogExplorerActionContext context= createContext();
        addListener(new int[] {});
        fActionGroup.setContext(context);
        reset();
        
        // if the zip file is added to the buildpath, then both, the zip and the zip file 
        // should have the option to be removed and the reset all operation additionally becomes 
        // available as we changed the project.
        final IProjectFragment[] addedZipArchive= {null};
        AddSelectedLibraryOperation operation= new AddSelectedLibraryOperation(null, new IBuildpathInformationProvider() {

            public void handleResult(List resultElements, CoreException exception, int operationType) {
                addedZipArchive[0]= (IProjectFragment)resultElements.get(0);
            }

            public IStructuredSelection getSelection() {
                List list= new ArrayList();
                list.add(fItems[ZIP]); 
                return new StructuredSelection(list);
            }

            public IDLTKProject getDLTKProject() {
                return fProject;
            }


            public IInclusionExclusionQuery getInclusionExclusionQuery() throws ModelException {
                return null;
            }

            public ILinkToQuery getLinkFolderQuery() throws ModelException {
                return null;
            }
            
            public IAddArchivesQuery getExternalArchivesQuery() throws ModelException {
                return null;
            }

            public IAddLibrariesQuery getLibrariesQuery() throws ModelException {
                return null;
            }

            public void deleteCreatedResources() {
            }

			public IRemoveLinkedFolderQuery getRemoveLinkedFolderQuery() throws ModelException {
				return null;
			}

			public ICreateFolderQuery getCreateFolderQuery() throws ModelException {
				return null;
			}
        });
        operation.run(null);
        
        fSelection.add(addedZipArchive[0]);
       
        context= createContext();
        addListener(new int[] {REMOVE_FROM_BP, RESET_ALL});
        fActionGroup.setContext(context);
        reset();
    }
    
    private IDLTKProject createProject() throws CoreException, InvocationTargetException {
        fProject= DLTKProjectHelper.createDLTKProject("Dummy project");
        IPath srcPath= new Path("src");
        IPath normalFolderPath= new Path("NormalFolder");
        IPath packagePath= srcPath.append("pack1");
        IPath filePath= packagePath.append("NormalFile");
        
        // src folder
        IFolder folder= fProject.getProject().getFolder(srcPath);
        CoreUtility.createFolder(folder, true, true, null);
        
        // one normal folder
        IFolder folder2= fProject.getProject().getFolder(normalFolderPath);
        CoreUtility.createFolder(folder, true, true, null);
        
        final IPath projectPath= fProject.getProject().getFullPath();

        // configure the buildpath entries, including the default InterpreterEnvironment library.
        List cpEntries= new ArrayList();
        cpEntries.add(DLTKCore.newSourceEntry(projectPath.append(srcPath)));
        //cpEntries.addAll(Arrays.asList(PreferenceConstants.getDefaultInterpreterEnvironmentLibrary()));
        IBuildpathEntry[] entries= (IBuildpathEntry[]) cpEntries.toArray(new IBuildpathEntry[cpEntries.size()]);
        fProject.setRawBuildpath(entries, null);
        
        // one package in src folder
        IProjectFragment root= fProject.findProjectFragment(fProject.getPath().append(srcPath));
        IScriptFolder pack1= root.createScriptFolder("pack1", true, null);
        final IScriptFolder pack2= root.createScriptFolder("pack1.pack2", true, null);
        IScriptFolder defaultPack= root.getScriptFolder("");
        
        IPath libraryPath= root.getPath().append("archive.zip");
        final IProjectFragment zipRoot= DLTKProjectHelper.addLibrary(fProject, libraryPath);
        assertFalse(BuildpathModifier.getBuildpathEntryFor(zipRoot.getPath(), fProject, IBuildpathEntry.BPE_LIBRARY) == null);               
        
        // two compilation units A and B in 'package'
        ISourceModule cuA= createISourceModule("A", pack1);
        final IResource excludedElements[]= {null, null}; 
        final ISourceModule cuB= createISourceModule("B", pack1);
        ExcludeOperation op= new ExcludeOperation(null, new IBuildpathInformationProvider() {

            public void handleResult(List resultElements, CoreException exception, int operationType) {
                excludedElements[0]= (IFile)resultElements.get(0);
                excludedElements[1]= (IFolder)resultElements.get(1);
            }

            public IStructuredSelection getSelection() {
                List list= new ArrayList();
                list.add(cuB); // exclude compilation unit B
                list.add(pack2); // exclude pack2
                return new StructuredSelection(list);
            }

            public IDLTKProject getDLTKProject() {
                return fProject;
            }

      
            public IInclusionExclusionQuery getInclusionExclusionQuery() throws ModelException {
                return null;
            }

            public ILinkToQuery getLinkFolderQuery() throws ModelException {
                return null;
            }
            
            public IAddArchivesQuery getExternalArchivesQuery() throws ModelException {
                return null;
            }

            public IAddLibrariesQuery getLibrariesQuery() throws ModelException {
                return null;
            }

            public void deleteCreatedResources() {
            }

			public IRemoveLinkedFolderQuery getRemoveLinkedFolderQuery() throws ModelException {
				return null;
			}

			public ICreateFolderQuery getCreateFolderQuery() throws ModelException {
				return null;
			}
            
        });
        op.run(null);
        IFile file= fProject.getProject().getFile(filePath);
        file.create(null, false, null);
        
        final IFile[] removedZipFile= {null};
        RemoveFromBuildpathOperation operation= new RemoveFromBuildpathOperation(null, new IBuildpathInformationProvider() {

            public void handleResult(List resultElements, CoreException exception, int operationType) {
                removedZipFile[0]= (IFile)resultElements.get(0);
            }

            public IStructuredSelection getSelection() {
                List list= new ArrayList();
                list.add(zipRoot); 
                return new StructuredSelection(list);
            }

            public IDLTKProject getDLTKProject() {
                return fProject;
            }

            public IInclusionExclusionQuery getInclusionExclusionQuery() throws ModelException {
                return null;
            }

            public ILinkToQuery getLinkFolderQuery() throws ModelException {
                return null;
            }
            
            public IAddArchivesQuery getExternalArchivesQuery() throws ModelException {
                return null;
            }

            public IAddLibrariesQuery getLibrariesQuery() throws ModelException {
                return null;
            }

            public void deleteCreatedResources() {
            }

			public IRemoveLinkedFolderQuery getRemoveLinkedFolderQuery() throws ModelException {
				return null;
			}

			public ICreateFolderQuery getCreateFolderQuery() throws ModelException {
				return null;
			}
            
        });
        operation.run(null);
        removedZipFile[0].create(null, false, null); // create the zip file
        
        fItems= new Object[11];
        fItems[PROJ]= fProject;
        fItems[SRC]= root;
        fItems[NF]= folder2;
        fItems[PACK]= pack1;
        fItems[CU]= cuA;
        fItems[EXCLUDED_FILE]= excludedElements[0];
        fItems[FILE]= file;
        fItems[EXCLUDED_PACK]= excludedElements[1];
        fItems[DEFAULT_PACK]= defaultPack;
        fItems[ZIP]= zipRoot;
        
        return fProject;
    }
    
    protected int getID(BuildpathModifierAction action) {
        return Integer.parseInt(action.getId());
    }
    
    protected void addToSelection(int[] indices) {
        for (int i= 0; i < indices.length; i++) {
            fSelection.add(fItems[indices[i]]);
        }
    }
    
    protected void addToSelection(Object obj) {
        fSelection.add(obj);
    }
    
    protected DialogExplorerActionContext createContext() {
        return new DialogExplorerActionContext(fSelection, fProject);
    }
    
    protected void addListener(final int[] expectedValues) {
        fListener= new IPackageExplorerActionListener() {
            public void handlePackageExplorerActionEvent(PackageExplorerActionEvent event) {
                BuildpathModifierAction[] actions= event.getEnabledActions();
                if (actions.length != expectedValues.length) {
                	assertTrue(false);
                }
                for(int i= 0; i < actions.length; i++) {
                    assertTrue(getID(actions[i]) == expectedValues[i]);
                }
            }
        };
        fActionGroup.addListener(fListener);
    }
    
    protected void reset() {
        fSelection.clear();
        fActionGroup.removeListener(fListener);
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

    /* (non-Javadoc)
     * @see org.eclipse.dltk.internal.corext.buildpath.IBuildpathInformationProvider#handleResult(java.util.List, org.eclipse.core.runtime.CoreException, int)
     */
    public void handleResult(List resultElements, CoreException exception, int operationType) {
    }

    /* (non-Javadoc)
     * @see org.eclipse.dltk.internal.corext.buildpath.IBuildpathInformationProvider#getSelection()
     */
    public IStructuredSelection getSelection() {
        return new StructuredSelection(fSelection);
    }

    /* (non-Javadoc)
     * @see org.eclipse.dltk.internal.corext.buildpath.IBuildpathInformationProvider#getScriptProject()
     */
    public IDLTKProject getDLTKProject() {
        return fProject;
    }

    /* (non-Javadoc)
     * @see org.eclipse.dltk.internal.corext.buildpath.IBuildpathInformationProvider#getInclusionExclusionQuery()
     */
    public IInclusionExclusionQuery getInclusionExclusionQuery() throws ModelException {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.dltk.internal.corext.buildpath.IBuildpathInformationProvider#getLinkFolderQuery()
     */
    public ILinkToQuery getLinkFolderQuery() throws ModelException {
        return null;
    }
    
    public IAddArchivesQuery getExternalArchivesQuery() throws ModelException {
        return null;
    }

    public IAddLibrariesQuery getLibrariesQuery() throws ModelException {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.dltk.internal.corext.buildpath.IBuildpathInformationProvider#deleteCreatedResources()
     */
    public void deleteCreatedResources() {
    }

	/*
	 * @see org.eclipse.dltk.internal.corext.buildpath.IBuildpathInformationProvider#getRemoveLinkedFolderQuery()
	 */
	public IRemoveLinkedFolderQuery getRemoveLinkedFolderQuery() throws ModelException {
		return null;
	}

	public ICreateFolderQuery getCreateFolderQuery() throws ModelException {
		return null;
	}
}
