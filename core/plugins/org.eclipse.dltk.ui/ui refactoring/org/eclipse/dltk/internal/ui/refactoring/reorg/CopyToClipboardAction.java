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
package org.eclipse.dltk.internal.ui.refactoring.reorg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKModelUtil;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.ExternalScriptFolder;
import org.eclipse.dltk.internal.corext.refactoring.reorg.IReorgEnablementPolicy;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ModelElementTransfer;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ParentChecker;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ReorgUtils;
import org.eclipse.dltk.internal.corext.refactoring.util.ModelElementUtil;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.ModelElementLabelProvider;
import org.eclipse.dltk.ui.actions.SelectionDispatchAction;
import org.eclipse.dltk.ui.util.ExceptionHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.part.ResourceTransfer;



public class CopyToClipboardAction extends SelectionDispatchAction{

	private final Clipboard fClipboard;
	private SelectionDispatchAction fPasteAction;//may be null
	private boolean fAutoRepeatOnFailure= false;

	public CopyToClipboardAction(IWorkbenchSite site, Clipboard clipboard, SelectionDispatchAction pasteAction) {
		super(site);
		setText(ReorgMessages.CopyToClipboardAction_0); 
		setDescription(ReorgMessages.CopyToClipboardAction_1); 
		Assert.isNotNull(clipboard);
		fClipboard= clipboard;
		fPasteAction= pasteAction;
		ISharedImages workbenchImages= getWorkbenchSharedImages();
		setDisabledImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
		setImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
		setHoverImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
		update(getSelection());

		if (DLTKCore.DEBUG) {
			System.err.println("Add help support here...");
		}
		
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IScriptHelpContextIds.COPY_ACTION);
	}

	public void setAutoRepeatOnFailure(boolean autorepeatOnFailure){
		fAutoRepeatOnFailure= autorepeatOnFailure;
	}
	
	private static ISharedImages getWorkbenchSharedImages() {
		return DLTKUIPlugin.getDefault().getWorkbench().getSharedImages();
	}

	public void selectionChanged(IStructuredSelection selection) {
		try {
			List elements= selection.toList();
			IResource[] resources= ReorgUtils.getResources(elements);
			IModelElement[] modelElements= ReorgUtils.getModelElements(elements);
			if (elements.size() != resources.length + modelElements.length)
				setEnabled(false);
			else
				setEnabled(canEnable(resources, modelElements));
		} catch (ModelException e) {
			//no ui here - this happens on selection changes
			// http://bugs.eclipse.org/bugs/show_bug.cgi?id=19253
			if (DLTKModelUtil.isExceptionToBeLogged(e))
				DLTKUIPlugin.log(e);
			setEnabled(false);
		}
	}
	
	public void run(IStructuredSelection selection) {
		try {
			List elements= selection.toList();
			IResource[] resources= ReorgUtils.getResources(elements);
			IModelElement[] modelElements= ReorgUtils.getModelElements(elements);
			if (elements.size() == resources.length + modelElements.length && canEnable(resources, modelElements)) 
				doRun(resources, modelElements);
		} catch (CoreException e) {
			ExceptionHandler.handle(e, getShell(), ReorgMessages.CopyToClipboardAction_2, ReorgMessages.CopyToClipboardAction_3); 
		}
	}

	private void doRun(IResource[] resources, IModelElement[] modelElements) throws CoreException {
		new ClipboardCopier(resources, modelElements, fClipboard, getShell(), fAutoRepeatOnFailure).copyToClipboard();

		// update the enablement of the paste action
		// workaround since the clipboard does not support callbacks				
		if (fPasteAction != null && fPasteAction.getSelection() != null)
			fPasteAction.update(fPasteAction.getSelection());
	}

	private boolean canEnable(IResource[] resources, IModelElement[] modelElements) throws ModelException {
		return new CopyToClipboardEnablementPolicy(resources, modelElements).canEnable();
	}
	
	//----------------------------------------------------------------------------------------//
	
	private static class ClipboardCopier{
		private final boolean fAutoRepeatOnFailure;
		private final IResource[] fResources;
		private final IModelElement[] fScriptElements;
		private final Clipboard fClipboard;
		private final Shell fShell;
		private final ILabelProvider fLabelProvider;
		
		private ClipboardCopier(IResource[] resources, IModelElement[] modelElements, Clipboard clipboard, Shell shell, boolean autoRepeatOnFailure){
			Assert.isNotNull(resources);
			Assert.isNotNull(modelElements);
			Assert.isNotNull(clipboard);
			Assert.isNotNull(shell);
			fResources= resources;
			fScriptElements= modelElements;
			fClipboard= clipboard;
			fShell= shell;
			fLabelProvider= createLabelProvider();
			fAutoRepeatOnFailure= autoRepeatOnFailure;
		}

		public void copyToClipboard() throws CoreException{
			//Set<String> fileNames
			Set fileNames= new HashSet(fResources.length + fScriptElements.length);
			StringBuffer namesBuf = new StringBuffer();
			processResources(fileNames, namesBuf);
			processScriptElements(fileNames, namesBuf);

			List typesList= ReorgUtils.getElementsOfType(fScriptElements, IModelElement.TYPE);
			IType types[] = (IType[])typesList.toArray(new IType[typesList.size()]);
			ISourceModule[] cusOfMainTypes= ReorgUtils.getSourceModules(types);
			IResource[] resourcesOfMainTypes= ReorgUtils.getResources(cusOfMainTypes);
			addFileNames(fileNames, resourcesOfMainTypes);
			
			IResource[] cuResources= ReorgUtils.getResources(getSourceModules(fScriptElements));
			addFileNames(fileNames, cuResources);

			IResource[] resourcesForClipboard= ReorgUtils.union(fResources, ReorgUtils.union(cuResources , resourcesOfMainTypes));
			IModelElement[] modelElementsForClipboard= ReorgUtils.union(fScriptElements, cusOfMainTypes);
			
//			TypedSource[] typedSources= TypedSource.createTypedSources(modelElementsForClipboard);
			String[] fileNameArray= (String[]) fileNames.toArray(new String[fileNames.size()]);
			copyToClipboard(resourcesForClipboard, fileNameArray, namesBuf.toString(), modelElementsForClipboard/*, typedSources*/, 0);
		}

		private static IModelElement[] getSourceModules(IModelElement[] modelElements) {
			List cus= ReorgUtils.getElementsOfType(modelElements, IModelElement.SOURCE_MODULE);
			return (ISourceModule[]) cus.toArray(new ISourceModule[cus.size()]);
		}

		private void processResources(Set fileNames, StringBuffer namesBuf) {
			for (int i= 0; i < fResources.length; i++) {
				IResource resource= fResources[i];
				addFileName(fileNames, resource);

				if (i > 0)
					namesBuf.append('\n');
				namesBuf.append(getName(resource));
			}
		}

		private void processScriptElements(Set fileNames, StringBuffer namesBuf) {
			for (int i= 0; i < fScriptElements.length; i++) {
				IModelElement element= fScriptElements[i];
				switch (element.getElementType()) {
					case IModelElement.SCRIPT_PROJECT :
					case IModelElement.PROJECT_FRAGMENT :
					case IModelElement.SCRIPT_FOLDER :
					case IModelElement.SOURCE_MODULE :
						addFileName(fileNames, ReorgUtils.getResource(element));
						break;
					default :
						break;
				}

				if (fResources.length > 0 || i > 0)
					namesBuf.append('\n');
				namesBuf.append(getName(element));
			}
		}

		private static void addFileNames(Set fileName, IResource[] resources) {
			for (int i= 0; i < resources.length; i++) {
				addFileName(fileName, resources[i]);
			}
		}

		private static void addFileName(Set fileName, IResource resource){
			if (resource == null)
				return;
			IPath location = resource.getLocation();
			if (location != null) {
				fileName.add(location.toOSString());
			} else {
				// not a file system path. skip file.
			}
		}
		
		private void copyToClipboard(IResource[] resources, String[] fileNames, String names, IModelElement[] modelElements, /*TypedSource[] typedSources,*/ int repeat){
			final int repeat_max_count= 10;
			try{
				fClipboard.setContents( createDataArray(resources, modelElements, fileNames, names/*, typedSources*/),
										createDataTypeArray(resources, modelElements, fileNames/*, typedSources*/));
			} catch (SWTError e) {
				if (e.code != DND.ERROR_CANNOT_SET_CLIPBOARD || repeat >= repeat_max_count)
					throw e;
				if (fAutoRepeatOnFailure) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						// do nothing.
					}
				}
				if (fAutoRepeatOnFailure || MessageDialog.openQuestion(fShell, ReorgMessages.CopyToClipboardAction_4, ReorgMessages.CopyToClipboardAction_5)) 
					copyToClipboard(resources, fileNames, names, modelElements/*, typedSources*/, repeat+1);
			}
		}
		
		private static Transfer[] createDataTypeArray(IResource[] resources, IModelElement[] modelElements, String[] fileNames/*, TypedSource[] typedSources*/) {
			List result= new ArrayList(4);
			if (resources.length != 0)
				result.add(ResourceTransfer.getInstance());
			if (modelElements.length != 0)
				result.add(ModelElementTransfer.getInstance());
			if (fileNames.length != 0)
				result.add(FileTransfer.getInstance());
//			if (typedSources.length != 0)
//				result.add(TypedSourceTransfer.getInstance());
			result.add(TextTransfer.getInstance());			
			return (Transfer[]) result.toArray(new Transfer[result.size()]);
		}

		private static Object[] createDataArray(IResource[] resources, IModelElement[] modelElements, String[] fileNames, String names/*, TypedSource[] typedSources*/) {
			List result= new ArrayList(4);
			if (resources.length != 0)
				result.add(resources);
			if (modelElements.length != 0)
				result.add(modelElements);
			if (fileNames.length != 0)
				result.add(fileNames);
//			if (typedSources.length != 0)
//				result.add(typedSources);
			result.add(names);
			return result.toArray();
		}

		private static ILabelProvider createLabelProvider(){
			return new ModelElementLabelProvider(
				ModelElementLabelProvider.SHOW_VARIABLE
				+ ModelElementLabelProvider.SHOW_PARAMETERS
				+ ModelElementLabelProvider.SHOW_TYPE
			);		
		}
		private String getName(IResource resource){
			return fLabelProvider.getText(resource);
		}
		private String getName(IModelElement modelElement){
			return fLabelProvider.getText(modelElement);
		}
	}
	
	private static class CopyToClipboardEnablementPolicy implements IReorgEnablementPolicy{
		private final IResource[] fResources;
		private final IModelElement[] fScriptElements;
		public CopyToClipboardEnablementPolicy(IResource[] resources, IModelElement[] modelElements){
			Assert.isNotNull(resources);
			Assert.isNotNull(modelElements);
			fResources= resources;
			fScriptElements= modelElements;
		}

		public boolean canEnable() throws ModelException{
			if (fResources.length + fScriptElements.length == 0)
				return false;
			if (hasProjects() && hasNonProjects())
				return false;
			if (! canCopyAllToClipboard())
				return false;
			if (! new ParentChecker(fResources, fScriptElements).haveCommonParent())
				return false;
			return true;
		}

		private boolean canCopyAllToClipboard() throws ModelException {
			for (int i= 0; i < fResources.length; i++) {
				if (! canCopyToClipboard(fResources[i])) return false;
			}
			for (int i= 0; i < fScriptElements.length; i++) {
				if (! canCopyToClipboard(fScriptElements[i])) return false;
			}
			return true;
		}

		private static boolean canCopyToClipboard(IModelElement element) throws ModelException {
			if (element == null || ! element.exists())
				return false;
				
			if (ModelElementUtil.isDefaultPackage(element))		
				return false;
			
			if (DLTKCore.DEBUG) {
				System.err.println("TODO: Add inner element copy action..");
			}
			//TODO: Add copy operation for external script folders.. Posible this is not required at all.
			if( element instanceof ExternalScriptFolder ) {
				return false;
			}
			if( element.getElementType() > IModelElement.BINARY_MODULE) {
				return false;
			}
			return true;
		}

		private static boolean canCopyToClipboard(IResource resource) {
			return 	resource != null && 
					resource.exists() &&
					! resource.isPhantom() &&
					resource.getType() != IResource.ROOT;
		}

		private boolean hasProjects() {
			for (int i= 0; i < fResources.length; i++) {
				if (ReorgUtils.isProject(fResources[i])) return true;
			}
			for (int i= 0; i < fScriptElements.length; i++) {
				if (ReorgUtils.isProject(fScriptElements[i])) return true;
			}
			return false;
		}

		private boolean hasNonProjects() {
			for (int i= 0; i < fResources.length; i++) {
				if (! ReorgUtils.isProject(fResources[i])) return true;
			}
			for (int i= 0; i < fScriptElements.length; i++) {
				if (! ReorgUtils.isProject(fScriptElements[i])) return true;
			}
			return false;
		}
	}
}
