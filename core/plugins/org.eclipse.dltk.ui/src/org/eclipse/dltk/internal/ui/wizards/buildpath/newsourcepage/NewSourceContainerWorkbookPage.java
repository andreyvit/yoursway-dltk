/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.internal.ui.wizards.buildpath.newsourcepage;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.buildpath.BuildpathModifier;
import org.eclipse.dltk.internal.corext.buildpath.BuildpathModifier.IBuildpathModifierListener;
import org.eclipse.dltk.internal.ui.preferences.ScrolledPageContent;
import org.eclipse.dltk.internal.ui.util.ViewerPane;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.internal.ui.wizards.buildpath.BPListElement;
import org.eclipse.dltk.internal.ui.wizards.buildpath.BPListElementAttribute;
import org.eclipse.dltk.internal.ui.wizards.buildpath.BuildPathBasePage;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.LayoutUtil;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.ListDialogField;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.util.PixelConverter;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;


public class NewSourceContainerWorkbookPage extends BuildPathBasePage implements IBuildpathModifierListener {
    
    public static final String OPEN_SETTING= "org.eclipse.dltk.internal.ui.wizards.buildpaths.NewSourceContainerPage.openSetting";  //$NON-NLS-1$
    
    private ListDialogField fBuildpathList;
    private HintTextGroup fHintTextGroup;
    private DialogPackageExplorer fPackageExplorer;    
	
	private IScriptProject fScriptProject;

	final private IPreferenceStore fStore;


    /**
     * Constructor of the <code>NewSourceContainerWorkbookPage</code> which consists of 
     * a tree representing the project, a toolbar with the available actions, an area 
     * containing hyperlinks that perform the same actions as those in the toolbar but 
     * additionally with some short description.
     * 
     * @param BuildpathList
     * @param outputLocationField
     * @param context a runnable context, can be <code>null</code>
     */
    public NewSourceContainerWorkbookPage(ListDialogField BuildpathList, IRunnableContext context, IPreferenceStore store) {
        fBuildpathList= BuildpathList;
        this.fStore = store;
        
		fPackageExplorer= new DialogPackageExplorer() {
			protected IPreferenceStore getPreferenceStore() {
				return fStore;
			}			
		};
		fHintTextGroup= new HintTextGroup(fPackageExplorer, context);

     }
    
    /**
     * Initialize the controls displaying
     * the content of the script project and saving 
     * the '.buildpath' and '.project' file.
     * 
     * Must be called before initializing the 
     * controls using <code>getControl(Composite)</code>.
     * 
     * @param scriptProject the current script project
     */
    public void init(IScriptProject scriptProject) {
		fScriptProject= scriptProject;
        fHintTextGroup.setScriptProject(scriptProject);
        
        fPackageExplorer.setInput(scriptProject);
		
//		boolean useFolderOutputs= false;
//		List cpelements= fBuildpathList.getElements();		
    }
    
     
    /**
     * Initializes controls and return composite containing
     * these controls.
     * 
     * Before calling this method, make sure to have 
     * initialized this instance with a script project 
     * using <code>init(IScriptProject)</code>.
     * 
     * @param parent the parent composite
     * @return composite containing controls
     * 
     * @see #init(IScriptProject)
     */
    public Control getControl(Composite parent) {
        final int[] sashWeight= {60};
        final IPreferenceStore preferenceStore= DLTKUIPlugin.getDefault().getPreferenceStore();
        preferenceStore.setDefault(OPEN_SETTING, true);
        
        // ScrolledPageContent is needed for resizing on expand the expandable composite
        ScrolledPageContent scrolledContent = new ScrolledPageContent(parent);
        Composite body= scrolledContent.getBody();
        body.setLayout(new GridLayout());
        
        final SashForm sashForm= new SashForm(body, SWT.VERTICAL | SWT.NONE);
        sashForm.setFont(sashForm.getFont());
        
        ViewerPane pane= new ViewerPane(sashForm, SWT.BORDER | SWT.FLAT);
        pane.setContent(fPackageExplorer.createControl(pane));
		fPackageExplorer.setContentProvider();
        
        final ExpandableComposite excomposite= new ExpandableComposite(sashForm, SWT.NONE, ExpandableComposite.TWISTIE | ExpandableComposite.CLIENT_INDENT);
        excomposite.setFont(sashForm.getFont());
        excomposite.setText(NewWizardMessages.NewSourceContainerWorkbookPage_HintTextGroup_title);
        final boolean isExpanded= preferenceStore.getBoolean(OPEN_SETTING);
        excomposite.setExpanded(isExpanded);
        excomposite.addExpansionListener(new ExpansionAdapter() {
                       public void expansionStateChanged(ExpansionEvent e) {
                           ScrolledPageContent parentScrolledComposite= getParentScrolledComposite(excomposite);
                           if (parentScrolledComposite != null) {
                              boolean expanded= excomposite.isExpanded();
                              parentScrolledComposite.reflow(true);
                              adjustSashForm(sashWeight, sashForm, expanded);
                              preferenceStore.setValue(OPEN_SETTING, expanded);
                           }
                       }
                 });
        
        excomposite.setClient(fHintTextGroup.createControl(excomposite));        
		
	    final DialogPackageExplorerActionGroup actionGroup= new DialogPackageExplorerActionGroup(fHintTextGroup, this);       
        
        Composite outputLocation= new Composite(body, SWT.NONE);
        outputLocation.setLayout(new GridLayout(2, false));
        outputLocation.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        
		LayoutUtil.doDefaultLayout(outputLocation, new DialogField[] { }, true, SWT.DEFAULT, SWT.DEFAULT);		
        
        // Create toolbar with actions on the left
        ToolBarManager tbm= actionGroup.createLeftToolBarManager(pane);
        pane.setTopCenter(null);
        pane.setTopLeft(tbm.getControl());
        
        // Create toolbar with help on the right
        tbm= actionGroup.createLeftToolBar(pane);
        pane.setTopRight(tbm.getControl());
        
        fHintTextGroup.setActionGroup(actionGroup);
        fPackageExplorer.setActionGroup(actionGroup);
        actionGroup.addListener(fHintTextGroup);
        
		sashForm.setWeights(new int[] {60, 40});
		adjustSashForm(sashWeight, sashForm, excomposite.isExpanded());
		GridData gd= new GridData(GridData.FILL_BOTH);
		PixelConverter converter= new PixelConverter(parent);
		gd.heightHint= converter.convertHeightInCharsToPixels(20);
		sashForm.setLayoutData(gd);
        
        parent.layout(true);

        return scrolledContent;
    }
    
    /**
     * Adjust the size of the sash form.
     * 
     * @param sashWeight the weight to be read or written
     * @param sashForm the sash form to apply the new weights to
     * @param isExpanded <code>true</code> if the expandable composite is 
     * expanded, <code>false</code> otherwise
     */
    private void adjustSashForm(int[] sashWeight, SashForm sashForm, boolean isExpanded) {
        if (isExpanded) {
            int upperWeight= sashWeight[0];
            sashForm.setWeights(new int[]{upperWeight, 100 - upperWeight});
        }
        else {
            // TODO Dividing by 10 because of https://bugs.eclipse.org/bugs/show_bug.cgi?id=81939
            sashWeight[0]= sashForm.getWeights()[0] / 10;
            sashForm.setWeights(new int[]{95, 5});
        }
        sashForm.layout(true);
    }
    
    /**
     * Get the scrolled page content of the given control by 
     * traversing the parents.
     * 
     * @param control the control to get the scrolled page content for 
     * @return the scrolled page content or <code>null</code> if none found
     */
    private ScrolledPageContent getParentScrolledComposite(Control control) {
       Control parent= control.getParent();
       while (!(parent instanceof ScrolledPageContent)) {
           parent= parent.getParent();
       }
       if (parent instanceof ScrolledPageContent) {
           return (ScrolledPageContent) parent;
       }
       return null;
   }
    
    /**
     * Get the active shell.
     * 
     * @return the active shell
     */
//    private Shell getShell() {
//        return DLTKUIPlugin.getActiveWorkbenchShell();
//    }
    
    public List getSelection() {
        List selectedList= new ArrayList();
        
        IScriptProject project= fHintTextGroup.getScriptProject();
        try {
            List list= fHintTextGroup.getSelection().toList();
            List existingEntries= BuildpathModifier.getExistingEntries(project);
        
            for(int i= 0; i < list.size(); i++) {
                Object obj= list.get(i);
                if (obj instanceof IProjectFragment) {
                    IProjectFragment element= (IProjectFragment)obj;
                    BPListElement cpElement= BuildpathModifier.getBuildpathEntry(existingEntries, element); 
                    selectedList.add(cpElement);
                }
                else if (obj instanceof IScriptProject) {
                    IBuildpathEntry entry= BuildpathModifier.getBuildpathEntryFor(project.getPath(), project, IBuildpathEntry.BPE_SOURCE);
                    if (entry == null)
                        continue;
                    BPListElement cpElement= BPListElement.createFromExisting(entry, project);
                    selectedList.add(cpElement);
                }
            }
        } catch (ModelException e) {
            return new ArrayList();
        }
        return selectedList;
    }
    
    public void setSelection(List selection, boolean expand) {
		// page switch
		
        if (selection.size() == 0)
            return;
	    
		List cpEntries= new ArrayList();
		
		for (int i= 0; i < selection.size(); i++) {
			Object obj= selection.get(i);
			if (obj instanceof BPListElement) {
				BPListElement element= (BPListElement) obj;
				if (element.getEntryKind() == IBuildpathEntry.BPE_SOURCE) {
					cpEntries.add(element);
				}
			} else if (obj instanceof BPListElementAttribute) {
				BPListElementAttribute attribute= (BPListElementAttribute)obj;
				BPListElement element= attribute.getParent();
				if (element.getEntryKind() == IBuildpathEntry.BPE_SOURCE) {
					cpEntries.add(element);
				}
			}
		}
		
        // refresh buildpath
        List list= fBuildpathList.getElements();
        IBuildpathEntry[] entries= new IBuildpathEntry[list.size()];
        for(int i= 0; i < list.size(); i++) {
            BPListElement entry= (BPListElement) list.get(i);
            entries[i]= entry.getBuildpathEntry(); 
        }
        try {
			fScriptProject.setRawBuildpath(entries, null);
            fPackageExplorer.refresh();
        } catch (ModelException e) {
            DLTKUIPlugin.log(e);
        }
        
        fPackageExplorer.setSelection(cpEntries);
    }
    
    public boolean isEntryKind(int kind) {
        return kind == IBuildpathEntry.BPE_SOURCE;
    }
    
    /**
     * Update <code>fBuildpathList</code>.
     */
    public void buildpathEntryChanged(List newEntries) {
        fBuildpathList.setElements(newEntries);
    }
}
