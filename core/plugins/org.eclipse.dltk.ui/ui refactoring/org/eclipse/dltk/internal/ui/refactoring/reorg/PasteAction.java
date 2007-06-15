/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.refactoring.reorg;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.ExternalProjectFragment;
import org.eclipse.dltk.internal.core.ExternalScriptFolder;
import org.eclipse.dltk.internal.core.ExternalSourceModule;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ModelElementTransfer;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ParentChecker;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ReorgUtils;
import org.eclipse.dltk.internal.ui.refactoring.RefactoringMessages;
import org.eclipse.dltk.internal.ui.workingsets.OthersWorkingSetUpdater;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.actions.SelectionDispatchAction;
import org.eclipse.dltk.ui.util.ExceptionHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.actions.CopyFilesAndFoldersOperation;
import org.eclipse.ui.actions.CopyProjectOperation;
import org.eclipse.ui.part.ResourceTransfer;


public class PasteAction extends SelectionDispatchAction{

	private final Clipboard fClipboard;

	public PasteAction(IWorkbenchSite site, Clipboard clipboard) {
		super(site);
		Assert.isNotNull(clipboard);
		fClipboard= clipboard;
		
		setText(ReorgMessages.PasteAction_4); 
		setDescription(ReorgMessages.PasteAction_5); 

		ISharedImages workbenchImages= DLTKUIPlugin.getDefault().getWorkbench().getSharedImages();
		setDisabledImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
		setImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
		setHoverImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));

		if (DLTKCore.DEBUG) {
			System.err.println("Add help support here...");
		}		
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IScriptHelpContextIds.PASTE_ACTION);
	}
		
	public void selectionChanged(IStructuredSelection selection) {
		// Moved condition checking to run (see http://bugs.eclipse.org/bugs/show_bug.cgi?id=78450)
	}

	private Paster[] createEnabledPasters(TransferData[] availableDataTypes) throws ModelException {
		Paster paster;
		Shell shell = getShell();
		List result= new ArrayList(2);
		paster= new ProjectPaster(shell, fClipboard);
		if (paster.canEnable(availableDataTypes)) 
			result.add(paster);
		
		paster= new ModelElementAndResourcePaster(shell, fClipboard);
		if (paster.canEnable(availableDataTypes)) 
			result.add(paster);

//		paster= new TypedSourcePaster(shell, fClipboard);
//		if (paster.canEnable(availableDataTypes)) 
//			result.add(paster);

		paster= new FilePaster(shell, fClipboard);
		if (paster.canEnable(availableDataTypes)) 
			result.add(paster);
		
		paster= new WorkingSetPaster(shell, fClipboard);
		if (paster.canEnable(availableDataTypes))
			result.add(paster);
		
//		paster= new TextPaster(shell, fClipboard);
//		if (paster.canEnable(availableDataTypes))
//			result.add(paster);
		return (Paster[]) result.toArray(new Paster[result.size()]);
	}

	private static Object getContents(final Clipboard clipboard, final Transfer transfer, Shell shell) {
		//see bug 33028 for explanation why we need this
		final Object[] result= new Object[1];
		shell.getDisplay().syncExec(new Runnable() {
			public void run() {
				result[0]= clipboard.getContents(transfer);
			}
		});
		return result[0];
	}
	
	private static boolean isAvailable(Transfer transfer, TransferData[] availableDataTypes) {
		for (int i= 0; i < availableDataTypes.length; i++) {
			if (transfer.isSupportedType(availableDataTypes[i])) return true;
		}
		return false;
	}

	public void run(IStructuredSelection selection) {
		try {
			TransferData[] availableTypes= fClipboard.getAvailableTypes();
			List elements= selection.toList();
			IResource[] resources= ReorgUtils.getResources(elements);
			IModelElement[] modelElements= ReorgUtils.getModelElements(elements);
			IWorkingSet[] workingSets= ReorgUtils.getWorkingSets(elements);
			Paster[] pasters= createEnabledPasters(availableTypes);
			for (int i= 0; i < pasters.length; i++) {
				if (pasters[i].canPasteOn(modelElements, resources, workingSets)) {
					pasters[i].paste(modelElements, resources, workingSets, availableTypes);
					return;// one is enough
				}
			}
			MessageDialog.openError(DLTKUIPlugin.getActiveWorkbenchShell(), RefactoringMessages.OpenRefactoringWizardAction_refactoring, RefactoringMessages.OpenRefactoringWizardAction_disabled); 
		} catch (ModelException e) {			
			ExceptionHandler.handle(e, RefactoringMessages.OpenRefactoringWizardAction_refactoring, RefactoringMessages.OpenRefactoringWizardAction_exception); 
		} catch (InvocationTargetException e) {
			ExceptionHandler.handle(e, RefactoringMessages.OpenRefactoringWizardAction_refactoring, RefactoringMessages.OpenRefactoringWizardAction_exception); 
		} catch (InterruptedException e) {
			// OK
		}
	}

	private abstract static class Paster{
		private final Shell fShell;
		private final Clipboard fClipboard2;
		protected Paster(Shell shell, Clipboard clipboard){
			fShell= shell;
			fClipboard2= clipboard;
		}
		protected final Shell getShell() {
			return fShell;
		}
		protected final Clipboard getClipboard() {
			return fClipboard2;
		}

		protected final IResource[] getClipboardResources(TransferData[] availableDataTypes) {
			Transfer transfer= ResourceTransfer.getInstance();
			if (isAvailable(transfer, availableDataTypes)) {
				return (IResource[])getContents(fClipboard2, transfer, getShell());
			}
			return null;
		}

		protected final IModelElement[] getClipboardScriptElements(TransferData[] availableDataTypes) {
			Transfer transfer= ModelElementTransfer.getInstance();
			if (isAvailable(transfer, availableDataTypes)) {
				return (IModelElement[])getContents(fClipboard2, transfer, getShell());
			}
			return null;
		}
	
//		protected final TypedSource[] getClipboardTypedSources(TransferData[] availableDataTypes) {
//			Transfer transfer= TypedSourceTransfer.getInstance();
//			if (isAvailable(transfer, availableDataTypes)) {
//				return (TypedSource[])getContents(fClipboard2, transfer, getShell());
//			}
//			return null;
//		}
	
		protected final String getClipboardText(TransferData[] availableDataTypes) {
			Transfer transfer= TextTransfer.getInstance();
			if (isAvailable(transfer, availableDataTypes)) {
				return (String) getContents(fClipboard2, transfer, getShell());
			}
			return null;
		}

		public abstract void paste(IModelElement[] selectedScriptElements, IResource[] selectedResources, IWorkingSet[] selectedWorkingSets, TransferData[] availableTypes) throws ModelException, InterruptedException, InvocationTargetException;
		public abstract boolean canEnable(TransferData[] availableTypes)  throws ModelException;
		public abstract boolean canPasteOn(IModelElement[] selectedScriptElements, IResource[] selectedResources, IWorkingSet[] selectedWorkingSets)  throws ModelException;
	}
    
//    private static class TextPaster extends Paster {
//
//		private static class CuParser {
//			private final IDLTKProject fScriptProject;
//			private final String fText;
//			
//			private String fTypeName;
//			private String fPackageName;
//
//			public CuParser(IDLTKProject scriptProject, String text) {
//				fScriptProject= scriptProject;
//				fText= text;
//			}
//
//			private void parseText() {
//				if (fPackageName != null)
//					return;
//				
//				fPackageName= IProjectFragment.DEFAULT_SCRIPT_FOLDER_NAME;
//				if (DLTKCore.DEBUG) {
//					System.err.println("Add language dependent code here.");
//				}
////				ASTParser parser= ASTParser.newParser(AST.JLS3);
////				parser.setProject(fScriptProject);
////				parser.setSource(fText.toCharArray());
////				SourceModule unit= (SourceModule) parser.createAST(null);
////				
////				if (unit == null)
////					return;
////				
////				int typesCount= unit.types().size();
////				if (typesCount > 0) {
////					// get first most visible type:
////					int maxVisibility= Modifier.PRIVATE;
////					for (ListIterator iter= unit.types().listIterator(typesCount); iter.hasPrevious();) {
////						AbstractTypeDeclaration type= (AbstractTypeDeclaration) iter.previous();
////						int visibility= DLTKFlags.getVisibilityCode(type);
////						if (! DLTKFlags.isHigherVisibility(maxVisibility, visibility)) {
////							maxVisibility= visibility;
////							fTypeName= type.getName().getIdentifier();
////						}
////					}
////				}
////				if (fTypeName == null)
////					return;
////				
////				PackageDeclaration pack= unit.getPackage();
////				if (pack != null) {
////					fPackageName= pack.getName().getFullyQualifiedName();
////				}
//			}
//			
//			/**
//			 * @return the type name, or <code>null</code> iff the text could not be parsed
//			 */
//			public String getTypeName() {
//				parseText();
//				return fTypeName;
//			}
//
//			public String getPackageName() {
//				parseText();
//				return fPackageName;
//			}
//
//			public String getText() {
//				return fText;
//			}
//		}
//		
//		private IProjectFragment fDestinationPack;
//		private CuParser fCuParser;
//		private TransferData[] fAvailableTypes;
//		
//		protected TextPaster(Shell shell, Clipboard clipboard) {
//			super(shell, clipboard);
//		}
//		
//		public boolean canEnable(TransferData[] availableTypes) {
//			fAvailableTypes= availableTypes;
//			return PasteAction.isAvailable(TextTransfer.getInstance(), availableTypes);
//		}
//
//		public boolean canPasteOn(IModelElement[] modelElements, IResource[] resources, IWorkingSet[] selectedWorkingSets) throws ModelException {
//			if (selectedWorkingSets.length != 0)
//				return false;
//			if (resources.length != 0)
//				return false; //alternative: create text file?
//			if (modelElements.length != 1)
//				return false;
//			
//			IModelElement destination= modelElements[0];
//			String text= getClipboardText(fAvailableTypes);
//			fCuParser= new CuParser(destination.getScriptProject(), text);
//			
//			if (fCuParser.getTypeName() == null)
//				return false;
//			
//			switch (destination.getElementType()) {
//				case IModelElement.JAVA_PROJECT :
//					IProjectFragment[] ProjectFragments= ((IDLTKProject) destination).getProjectFragments();
//					for (int i= 0; i < ProjectFragments.length; i++) {
//						IProjectFragment ProjectFragment= ProjectFragments[i];
//						if (ProjectFragment.getKind() == IProjectFragment.K_SOURCE) {
//							fDestinationPack= ProjectFragment.getScriptFolder(fCuParser.getPackageName());
//							if (isWritable(fDestinationPack))
//								return true;
//						}
//					}
//					return false;
//					
//				case IModelElement.PACKAGE_FRAGMENT_ROOT :
//					IProjectFragment ProjectFragment= (IProjectFragment) destination;
//					if (ProjectFragment.getKind() == IProjectFragment.K_SOURCE) {
//						fDestinationPack= ProjectFragment.getScriptFolder(fCuParser.getPackageName());
//						return isWritable(fDestinationPack);
//					}
//					return false;
//					
//				case IModelElement.PACKAGE_FRAGMENT :
//					fDestinationPack= (IProjectFragment) destination;
//					return isWritable(fDestinationPack);
//					
//				case IModelElement.COMPILATION_UNIT :
//					fDestinationPack= (IProjectFragment) destination.getParent();
//					return isWritable(fDestinationPack);
//					
//				default:
//					return false;
//			}
//		}
//		
//		private boolean isWritable(IProjectFragment destinationPack) {
//			if (destinationPack.exists() && destinationPack.isReadOnly()) {
//				return false;
//			} else {
//				IProjectFragment ProjectFragment= ScriptModelUtil.getProjectFragment(destinationPack);
//				try {
//					return ProjectFragment.exists() && ! ProjectFragment.isArchive() && ! ProjectFragment.isReadOnly()
//							&& ProjectFragment.getKind() == IProjectFragment.K_SOURCE;
//				} catch (ScriptModelException e) {
//					return false;
//				}
//			}
//		}
//
//		public void paste(IModelElement[] modelElements, IResource[] resources, IWorkingSet[] selectedWorkingSets, TransferData[] availableTypes) throws ModelException, InterruptedException, InvocationTargetException{
//			final IEditorPart[] editorPart= new IEditorPart[1];
//			
//			IWorkspaceRunnable op= new IWorkspaceRunnable() {
//				public void run(IProgressMonitor pm) throws CoreException {
//					pm.beginTask("", 4); //$NON-NLS-1$
//					
//					if (! fDestinationPack.exists())
//						ScriptModelUtil.getProjectFragment(fDestinationPack).createScriptFolder(fCuParser.getPackageName(), true, new SubProgressMonitor(pm, 1));
//					else
//						pm.worked(1);
//					
//					final String cuName= fCuParser.getTypeName() + ScriptModelUtil.DEFAULT_CU_SUFFIX;
//					final ISourceModule cu= fDestinationPack.getSourceModule(cuName);
//					boolean alreadyExists= cu.exists();
//					if (alreadyExists) {
//						String msg= Messages.format(ReorgMessages.PasteAction_TextPaster_exists, new Object[] {cuName});
//						boolean overwrite= MessageDialog.openQuestion(getShell(), ReorgMessages.PasteAction_TextPaster_confirmOverwriting, msg);
//						if (! overwrite)
//							return;
//						
//						editorPart[0]= openCu(cu); //Open editor before overwriting to allow undo.
//					}
//					
//					fDestinationPack.createSourceModule(cuName, fCuParser.getText(), true, new SubProgressMonitor(pm, 1));
//					
//					if (!alreadyExists) {
//						editorPart[0]= openCu(cu);
//					}
//					if (!fDestinationPack.getElementName().equals(fCuParser.getPackageName())) {
//						if (fDestinationPack.getElementName().length() == 0) {
//							removePackageDeclaration(cu);
//						} else {
//							cu.createPackageDeclaration(fDestinationPack.getElementName(), new SubProgressMonitor(pm, 1));
//						}
//						if (!alreadyExists && editorPart[0] != null)
//							editorPart[0].doSave(new SubProgressMonitor(pm, 1)); //avoid showing error marker due to missing/wrong package declaration
//						else
//							pm.worked(1);
//					} else {
//						pm.worked(1);
//					}
//					BasicNewResourceWizard.selectAndReveal(cu.getResource(), PlatformUI.getWorkbench().getActiveWorkbenchWindow());
//				}
//
//				private void removePackageDeclaration(final ISourceModule cu) throws ModelException, CoreException {
//					IPackageDeclaration[] packageDeclarations= cu.getPackageDeclarations();
//					if (packageDeclarations.length != 0) {
//						ITextFileBuffer buffer= null;
//						try {
//							buffer= RefactoringFileBuffers.acquire(cu);
//							ISourceRange sourceRange= packageDeclarations[0].getSourceRange();
//							buffer.getDocument().replace(sourceRange.getOffset(), sourceRange.getLength(), ""); //$NON-NLS-1$
//						} catch (BadLocationException e) {
//							ScriptPlugin.log(e);
//						} finally {
//							if (buffer != null)
//								RefactoringFileBuffers.release(cu);
//						}
//					}
//				}
//			};
//			
//			IRunnableContext context= ScriptPlugin.getActiveWorkbenchWindow();
//			if (context == null) {
//				context= new BusyIndicatorRunnableContext();
//			}
//			PlatformUI.getWorkbench().getProgressService().runInUI(context, new WorkbenchRunnableAdapter(op), null);
//			
//			if (editorPart[0] != null)
//				editorPart[0].getEditorSite().getPage().activate(editorPart[0]); //activate editor again, since runInUI restores previous active part
//		}
//
//		private IEditorPart openCu(ISourceModule cu) {
//			try {
//				return EditorUtility.openInEditor(cu);
//			} catch (PartInitException e) {
//				ScriptPlugin.log(e);
//				return null;
//			} catch (ScriptModelException e) {
//				ScriptPlugin.log(e);
//				return null;
//			}
//		}
//    }
    
	private static class WorkingSetPaster extends Paster {
		protected WorkingSetPaster(Shell shell, Clipboard clipboard) {
			super(shell, clipboard);
		}
		public void paste(IModelElement[] selectedScriptElements, IResource[] selectedResources, IWorkingSet[] selectedWorkingSets, TransferData[] availableTypes) throws ModelException, InterruptedException, InvocationTargetException {
			IWorkingSet workingSet= selectedWorkingSets[0];
			Set elements= new HashSet(Arrays.asList(workingSet.getElements()));
			IModelElement[] modelElements= getClipboardScriptElements(availableTypes);
			if (modelElements != null) {
				for (int i= 0; i < modelElements.length; i++) {
					if (!ReorgUtils.containsElementOrParent(elements, modelElements[i]))
						elements.add(modelElements[i]);
				}
			}
			IResource[] resources= getClipboardResources(availableTypes);
			if (resources != null) {
				List realScriptElements= new ArrayList();
				List realResource= new ArrayList();
				ReorgUtils.splitIntoModelElementsAndResources(resources, realScriptElements, realResource);
				for (Iterator iter= realScriptElements.iterator(); iter.hasNext();) {
					IModelElement element= (IModelElement)iter.next();
					if (!ReorgUtils.containsElementOrParent(elements, element))
						elements.add(element);
				}
				for (Iterator iter= realResource.iterator(); iter.hasNext();) {
					IResource element= (IResource)iter.next();
					if (!ReorgUtils.containsElementOrParent(elements, element))
						elements.add(element);
				}
			}
			workingSet.setElements((IAdaptable[])elements.toArray(new IAdaptable[elements.size()]));
		}
		public boolean canEnable(TransferData[] availableTypes) throws ModelException {
			return isAvailable(ResourceTransfer.getInstance(), availableTypes) ||
				isAvailable(ModelElementTransfer.getInstance(), availableTypes);
		}
		public boolean canPasteOn(IModelElement[] selectedScriptElements, IResource[] selectedResources, IWorkingSet[] selectedWorkingSets) throws ModelException {
			if (selectedResources.length != 0 || selectedScriptElements.length != 0 || selectedWorkingSets.length != 1)
				return false;
			IWorkingSet ws= selectedWorkingSets[0];
			return !OthersWorkingSetUpdater.ID.equals(ws.getId());
		}
	}
	
    private static class ProjectPaster extends Paster{
    	
    	protected ProjectPaster(Shell shell, Clipboard clipboard) {
			super(shell, clipboard);
		}

		public boolean canEnable(TransferData[] availableDataTypes) {
			boolean resourceTransfer= isAvailable(ResourceTransfer.getInstance(), availableDataTypes);
			boolean modelElementTransfer= isAvailable(ModelElementTransfer.getInstance(), availableDataTypes);
			if (! modelElementTransfer)
				return canPasteSimpleProjects(availableDataTypes);
			if (! resourceTransfer)
				return canPasteScriptProjects(availableDataTypes);
			return canPasteScriptProjects(availableDataTypes) && canPasteSimpleProjects(availableDataTypes);
    	}
    	
		public void paste(IModelElement[] modelElements, IResource[] resources, IWorkingSet[] selectedWorkingSets, TransferData[] availableTypes) {
			pasteProjects(availableTypes);
		}

		private void pasteProjects(TransferData[] availableTypes) {
			pasteProjects(getProjectsToPaste(availableTypes));
		}
		
		private void pasteProjects(IProject[] projects){
			Shell shell= getShell();
			for (int i = 0; i < projects.length; i++) {
				new CopyProjectOperation(shell).copyProject(projects[i]);
			}
		}
		private IProject[] getProjectsToPaste(TransferData[] availableTypes) {
			IResource[] resources= getClipboardResources(availableTypes);
			IModelElement[] modelElements= getClipboardScriptElements(availableTypes);
			Set result= new HashSet();
			if (resources != null)
				result.addAll(Arrays.asList(resources));
			if (modelElements != null)
				result.addAll(Arrays.asList(ReorgUtils.getNotNulls(ReorgUtils.getResources(modelElements))));
			Assert.isTrue(result.size() > 0);
			return (IProject[]) result.toArray(new IProject[result.size()]);
		}

		public boolean canPasteOn(IModelElement[] modelElements, IResource[] resources, IWorkingSet[] selectedWorkingSets) {
			return selectedWorkingSets.length == 0; // Can't paste on working sets here
		}
		
		private boolean canPasteScriptProjects(TransferData[] availableDataTypes) {
			IModelElement[] modelElements= getClipboardScriptElements(availableDataTypes);
			return 	modelElements != null && 
					modelElements.length != 0 && 
					! ReorgUtils.hasElementsNotOfType(modelElements, IModelElement.SCRIPT_PROJECT);
		}

		private boolean canPasteSimpleProjects(TransferData[] availableDataTypes) {
			IResource[] resources= getClipboardResources(availableDataTypes);
			if (resources == null || resources.length == 0) return false;
			for (int i= 0; i < resources.length; i++) {
				if (resources[i].getType() != IResource.PROJECT || ! ((IProject)resources[i]).isOpen())
					return false;
			}
			return true;
		}
    }
    
    private static class FilePaster extends Paster{
		protected FilePaster(Shell shell, Clipboard clipboard) {
			super(shell, clipboard);
		}

		public void paste(IModelElement[] modelElements, IResource[] resources, IWorkingSet[] selectedWorkingSets, TransferData[] availableTypes) throws ModelException {
			String[] fileData= getClipboardFiles(availableTypes);
			if (fileData == null)
				return;
    		
			IContainer container= getAsContainer(getTarget(modelElements, resources));
			if (container == null)
				return;
				
			new CopyFilesAndFoldersOperation(getShell()).copyFiles(fileData, container);
		}
		
		private Object getTarget(IModelElement[] modelElements, IResource[] resources) {
			if (modelElements.length + resources.length == 1){
				if (modelElements.length == 1)
					return modelElements[0];
				else
					return resources[0];
			} else				
				return getCommonParent(modelElements, resources);
		}

		public boolean canPasteOn(IModelElement[] modelElements, IResource[] resources, IWorkingSet[] selectedWorkingSets) throws ModelException {
			Object target= getTarget(modelElements, resources);
			return target != null && canPasteFilesOn(getAsContainer(target)) && selectedWorkingSets.length == 0;
		}

		public boolean canEnable(TransferData[] availableDataTypes) throws ModelException {
			return isAvailable(FileTransfer.getInstance(), availableDataTypes);
		}
				
		private boolean canPasteFilesOn(Object target) {
			boolean isScriptFolder= target instanceof IProjectFragment;
			boolean isScriptProject= target instanceof IScriptProject;
			boolean isProjectFragment= target instanceof IProjectFragment;
			boolean isContainer= target instanceof IContainer;
			
			if( target instanceof ExternalProjectFragment || target instanceof ExternalScriptFolder || target instanceof ExternalSourceModule )  {
				return false;
			}
		
			if (!(isScriptFolder || isScriptProject || isProjectFragment || isContainer)) 
				return false;

			if (isContainer) {
				return true;
			} else {
				IModelElement element= (IModelElement)target;
				return !element.isReadOnly();
			}
		}
		
		private IContainer getAsContainer(Object target) throws ModelException{
			if (target == null) 
				return null;
			if (target instanceof IContainer) 
				return (IContainer)target;
			if (target instanceof IFile)
				return ((IFile)target).getParent();
			return getAsContainer(((IModelElement)target).getCorrespondingResource());
		}
		
		private String[] getClipboardFiles(TransferData[] availableDataTypes) {
			Transfer transfer= FileTransfer.getInstance();
			if (isAvailable(transfer, availableDataTypes)) {
				return (String[])getContents(getClipboard(), transfer, getShell());
			}
			return null;
		}
		private Object getCommonParent(IModelElement[] modelElements, IResource[] resources) {
			return new ParentChecker(resources, modelElements).getCommonParent();		
		}
    }
    private static class ModelElementAndResourcePaster extends Paster {

		protected ModelElementAndResourcePaster(Shell shell, Clipboard clipboard) {
			super(shell, clipboard);
		}

		private TransferData[] fAvailableTypes;

		public void paste(IModelElement[] modelElements, IResource[] resources, IWorkingSet[] selectedWorkingSets, TransferData[] availableTypes) throws ModelException, InterruptedException, InvocationTargetException{
			IResource[] clipboardResources= getClipboardResources(availableTypes);
			if (clipboardResources == null) 
				clipboardResources= new IResource[0];
			IModelElement[] clipboardScriptElements= getClipboardScriptElements(availableTypes);
			if (clipboardScriptElements == null) 
				clipboardScriptElements= new IModelElement[0];

			Object destination= getTarget(modelElements, resources);
			if (destination instanceof IModelElement) {
				ReorgCopyStarter.create(clipboardScriptElements, clipboardResources, (IModelElement)destination).run(getShell());
			}
			else if (destination instanceof IResource) {				
				ReorgCopyStarter.create(clipboardScriptElements, clipboardResources, (IResource)destination).run(getShell());
			}
		}

		private Object getTarget(IModelElement[] modelElements, IResource[] resources) {
			if (modelElements.length + resources.length == 1){
				if (modelElements.length == 1)
					return modelElements[0];
				else
					return resources[0];
			} else				
				return getCommonParent(modelElements, resources);
		}
		
		private Object getCommonParent(IModelElement[] modelElements, IResource[] resources) {
			return new ParentChecker(resources, modelElements).getCommonParent();		
		}

		public boolean canPasteOn(IModelElement[] modelElements, IResource[] resources, IWorkingSet[] selectedWorkingSets) throws ModelException {
			if (selectedWorkingSets.length != 0)
				return false;
			IResource[] clipboardResources= getClipboardResources(fAvailableTypes);
			if (clipboardResources == null) 
				clipboardResources= new IResource[0];
			IModelElement[] clipboardScriptElements= getClipboardScriptElements(fAvailableTypes);
			if (clipboardScriptElements == null) 
				clipboardScriptElements= new IModelElement[0];
			Object destination= getTarget(modelElements, resources);
			if (destination instanceof IModelElement) {				
				return ReorgCopyStarter.create(clipboardScriptElements, clipboardResources, (IModelElement)destination) != null;
			}
			if (destination instanceof IResource) {
				return ReorgCopyStarter.create(clipboardScriptElements, clipboardResources, (IResource)destination) != null;
			}
			return false;
		}
		
		public boolean canEnable(TransferData[] availableTypes) {
			fAvailableTypes= availableTypes;
			return isAvailable(ModelElementTransfer.getInstance(), availableTypes) || isAvailable(ResourceTransfer.getInstance(), availableTypes);
		}
    }
    
//    private static class TypedSourcePaster extends Paster{
//
//		protected TypedSourcePaster(Shell shell, Clipboard clipboard) {
//			super(shell, clipboard);
//		}
//		private TransferData[] fAvailableTypes;
//
//		public boolean canEnable(TransferData[] availableTypes) throws ModelException {
//			fAvailableTypes= availableTypes;
//			return isAvailable(TypedSourceTransfer.getInstance(), availableTypes);
//		}
//
//		public boolean canPasteOn(IModelElement[] selectedScriptElements, IResource[] selectedResources, IWorkingSet[] selectedWorkingSets) throws ModelException {
//			if (selectedResources.length != 0 || selectedWorkingSets.length != 0)
//				return false;
//			TypedSource[] typedSources= getClipboardTypedSources(fAvailableTypes);				
//			Object destination= getTarget(selectedScriptElements, selectedResources);
//			if (destination instanceof IScriptElement)
//				return ReorgTypedSourcePasteStarter.create(typedSources, (IScriptElement)destination) != null;
//			return false;
//		}
//		
//		public void paste(IModelElement[] selectedScriptElements, IResource[] selectedResources, IWorkingSet[] selectedWorkingSets, TransferData[] availableTypes) throws ModelException, InterruptedException, InvocationTargetException {
//			TypedSource[] typedSources= getClipboardTypedSources(availableTypes);
//			IScriptElement destination= getTarget(selectedScriptElements, selectedResources);
//			ReorgTypedSourcePasteStarter.create(typedSources, destination).run(getShell());		
//		}
//		
//		private static IModelElement getTarget(IModelElement[] selectedScriptElements, IResource[] selectedResources) {
//			Assert.isTrue(selectedResources.length == 0);
//			if (selectedScriptElements.length == 1) 
//				return getAsTypeOrCu(selectedScriptElements[0]);
//			Object parent= new ParentChecker(selectedResources, selectedScriptElements).getCommonParent();
//			if (parent instanceof IModelElement)
//				return getAsTypeOrCu((IModelElement)parent);
//			return null;
//		}
//		private static IModelElement getAsTypeOrCu(IModelElement element) {
//			//try to get type first
//			if (element.getElementType() == IModelElement.COMPILATION_UNIT || element.getElementType() == IModelElement.TYPE)
//				return element;
//			IModelElement ancestorType= element.getAncestor(IModelElement.TYPE);
//			if (ancestorType != null)
//				return ancestorType;
//			return ReorgUtils.getSourceModule(element);
//		}
//		private static class ReorgTypedSourcePasteStarter {
//	
//			private final PasteTypedSourcesRefactoring fPasteRefactoring;
//
//			private ReorgTypedSourcePasteStarter(PasteTypedSourcesRefactoring pasteRefactoring) {
//				Assert.isNotNull(pasteRefactoring);
//				fPasteRefactoring= pasteRefactoring;
//			}
//	
//			public static ReorgTypedSourcePasteStarter create(TypedSource[] typedSources, IModelElement destination) {
//				Assert.isNotNull(typedSources);
//				Assert.isNotNull(destination);
//				PasteTypedSourcesRefactoring pasteRefactoring= PasteTypedSourcesRefactoring.create(typedSources);
//				if (pasteRefactoring == null)
//					return null;
//				if (! pasteRefactoring.setDestination(destination).isOK())
//					return null;
//				return new ReorgTypedSourcePasteStarter(pasteRefactoring);
//			}
//
//			public void run(Shell parent) throws InterruptedException, InvocationTargetException {
//				IRunnableContext context= new ProgressMonitorDialog(parent);
//				new RefactoringExecutionHelper(fPasteRefactoring, RefactoringCore.getConditionCheckingFailedSeverity(), false, parent, context).perform(false);
//			}
//		}
//		private static class PasteTypedSourcesRefactoring extends Refactoring {
//			
//			private final TypedSource[] fSources;
//			private IModelElement fDestination;
//			
//			static PasteTypedSourcesRefactoring create(TypedSource[] sources){
//				if (! isAvailable(sources))
//					return null;
//				return new PasteTypedSourcesRefactoring(sources);
//			}
//			public RefactoringStatus setDestination(IModelElement destination) {
//				fDestination= destination;
//				if (ReorgUtils.getSourceModule(destination) == null)
//					return RefactoringStatus.createFatalErrorStatus(ReorgMessages.PasteAction_wrong_destination); 
//				if (! destination.exists())
//					return RefactoringStatus.createFatalErrorStatus(ReorgMessages.PasteAction_element_doesnot_exist); 
//				if (! canPasteAll(destination))
//					return RefactoringStatus.createFatalErrorStatus(ReorgMessages.PasteAction_invalid_destination); 
//				return new RefactoringStatus();
//			}
//			private boolean canPasteAll(IModelElement destination) {
//				for (int i= 0; i < fSources.length; i++) {
//					if (! canPaste(fSources[i].getType(), destination))
//						return false;
//				}
//				return true;
//			}
//			private static boolean canPaste(int elementType, IModelElement destination) {
//				IType ancestorType= getAncestorType(destination);
//				if (ancestorType != null)
//					return canPasteToType(elementType);
//				return canPasteToCu(elementType);
//			}
//			private static boolean canPasteToType(int elementType) {
//				return 	elementType == IModelElement.TYPE || 
//						elementType == IModelElement.FIELD || 
//						elementType == IModelElement.INITIALIZER || 
//						elementType == IModelElement.METHOD;
//			}
//			private static boolean canPasteToCu(int elementType) {
//				return	elementType == IModelElement.PACKAGE_DECLARATION ||
//						elementType == IModelElement.TYPE ||
//						elementType == IModelElement.IMPORT_DECLARATION;
//			}
//			PasteTypedSourcesRefactoring(TypedSource[] sources){
//				Assert.isNotNull(sources);
//				Assert.isTrue(sources.length != 0);
//				fSources= sources;
//			}
//
//			private static boolean isAvailable(TypedSource[] sources) {
//				return sources != null && sources.length > 0;
//			}
//
//			public RefactoringStatus checkInitialConditions(IProgressMonitor pm) throws CoreException {
//				return new RefactoringStatus();
//			}
//
//			public RefactoringStatus checkFinalConditions(IProgressMonitor pm) throws CoreException {
//				RefactoringStatus result= Checks.validateModifiesFiles(
//					ResourceUtil.getFiles(new ISourceModule[]{getDestinationCu()}), getValidationContext());
//				return result;
//			}
//
//			public Change createChange(IProgressMonitor pm) throws CoreException {
//				ASTParser p= ASTParser.newParser(AST.JLS3);
//				p.setSource(getDestinationCu());
//				SourceModule cuNode= (SourceModule) p.createAST(pm);
//				ASTRewrite rewrite= ASTRewrite.create(cuNode.getAST());
//				TypedSource source= null;
//				for (int i= fSources.length - 1; i >= 0; i--) {
//					source= fSources[i];
//					final ASTNode destination= getDestinationNodeForSourceElement(fDestination, source.getType(), cuNode);
//					if (destination != null) {
//						if (destination instanceof SourceModule)
//							insertToCu(rewrite, createNewNodeToInsertToCu(source, rewrite), (SourceModule) destination);
//						else if (destination instanceof AbstractTypeDeclaration)
//							insertToType(rewrite, createNewNodeToInsertToType(source, rewrite), (AbstractTypeDeclaration) destination);
//					}
//				}
//				final SourceModuleChange result= new SourceModuleChange(ReorgMessages.PasteAction_change_name, getDestinationCu()); 
//				try {
//					ITextFileBuffer buffer= RefactoringFileBuffers.acquire(getDestinationCu());
//					TextEdit rootEdit= rewrite.rewriteAST(buffer.getDocument(), fDestination.getScriptProject().getOptions(true));
//					if (getDestinationCu().isWorkingCopy())
//						result.setSaveMode(TextFileChange.LEAVE_DIRTY);
//					TextChangeCompatibility.addTextEdit(result, ReorgMessages.PasteAction_edit_name, rootEdit); 
//				} finally {
//					RefactoringFileBuffers.release(getDestinationCu());
//				}
//				return result;
//			}
//
//			private static void insertToType(ASTRewrite rewrite, ASTNode node, AbstractTypeDeclaration typeDeclaration) {
//				switch (node.getNodeType()) {
//					case ASTNode.ANNOTATION_TYPE_DECLARATION:
//					case ASTNode.ENUM_DECLARATION:
//					case ASTNode.TYPE_DECLARATION:
//					case ASTNode.METHOD_DECLARATION:
//					case ASTNode.FIELD_DECLARATION:
//					case ASTNode.INITIALIZER:
//						rewrite.getListRewrite(typeDeclaration, typeDeclaration.getBodyDeclarationsProperty()).insertAt(node, ASTNodes.getInsertionIndex((BodyDeclaration) node, typeDeclaration.bodyDeclarations()), null);
//						break;
//					default:
//						Assert.isTrue(false, String.valueOf(node.getNodeType()));
//				}
//			}
//
//			private static void insertToCu(ASTRewrite rewrite, ASTNode node, SourceModule cuNode) {
//				switch (node.getNodeType()) {
//					case ASTNode.TYPE_DECLARATION:
//					case ASTNode.ENUM_DECLARATION:
//					case ASTNode.ANNOTATION_TYPE_DECLARATION:
//						rewrite.getListRewrite(cuNode, SourceModule.TYPES_PROPERTY).insertAt(node, ASTNodes.getInsertionIndex((AbstractTypeDeclaration) node, cuNode.types()), null);
//						break;
//					case ASTNode.IMPORT_DECLARATION:
//						rewrite.getListRewrite(cuNode, SourceModule.IMPORTS_PROPERTY).insertLast(node, null);
//						break;
//					case ASTNode.PACKAGE_DECLARATION:
//						// only insert if none exists
//						if (cuNode.getPackage() == null)
//							rewrite.set(cuNode, SourceModule.PACKAGE_PROPERTY, node, null);
//						break;
//					default:
//						Assert.isTrue(false, String.valueOf(node.getNodeType()));
//				}
//			}
//
//			/**
//			 * @return an AbstractTypeDeclaration, a SourceModule, or null
//			 */ 
//			private ASTNode getDestinationNodeForSourceElement(IModelElement destination, int kind, SourceModule unit) throws ModelException {
//				final IType ancestor= getAncestorType(destination);
//				if (ancestor != null)
//					return ASTNodeSearchUtil.getAbstractTypeDeclarationNode(ancestor, unit);
//				if (kind == IScriptElement.TYPE || kind == IScriptElement.PACKAGE_DECLARATION || kind == IScriptElement.IMPORT_DECLARATION || kind == IScriptElement.IMPORT_CONTAINER)
//					return unit;
//				return null;	
//			}
//			
//			private static IType getAncestorType(IModelElement destinationElement) {
//				return destinationElement.getElementType() == IModelElement.TYPE ? (IType)destinationElement: (IType)destinationElement.getAncestor(IModelElement.TYPE);
//			}
//			private ASTNode createNewNodeToInsertToCu(TypedSource source, ASTRewrite rewrite) {
//				switch(source.getType()){
//					case IScriptElement.TYPE:
//						return rewrite.createStringPlaceholder(source.getSource(), ASTNode.TYPE_DECLARATION);
//					case IScriptElement.PACKAGE_DECLARATION:
//						return rewrite.createStringPlaceholder(source.getSource(), ASTNode.PACKAGE_DECLARATION);
//					case IScriptElement.IMPORT_DECLARATION:
//						return rewrite.createStringPlaceholder(source.getSource(), ASTNode.IMPORT_DECLARATION);
//					default: Assert.isTrue(false, String.valueOf(source.getType()));
//						return null;
//				}
//			}
//			
//			private ASTNode createNewNodeToInsertToType(TypedSource source, ASTRewrite rewrite) {
//				switch(source.getType()){
//					case IScriptElement.TYPE:
//						return rewrite.createStringPlaceholder(source.getSource(), ASTNode.TYPE_DECLARATION);
//					case IScriptElement.METHOD:
//						return rewrite.createStringPlaceholder(source.getSource(), ASTNode.METHOD_DECLARATION);
//					case IScriptElement.FIELD:
//						return rewrite.createStringPlaceholder(source.getSource(), ASTNode.FIELD_DECLARATION);
//					case IScriptElement.INITIALIZER:
//						return rewrite.createStringPlaceholder(source.getSource(), ASTNode.INITIALIZER);
//					default: Assert.isTrue(false);
//						return null;
//				}
//			}
//			
//			private ISourceModule getDestinationCu() {
//				return ReorgUtils.getSourceModule(fDestination);
//			}
//
//			public String getName() {
//				return ReorgMessages.PasteAction_name; 
//			}
//		}
//    }
}
