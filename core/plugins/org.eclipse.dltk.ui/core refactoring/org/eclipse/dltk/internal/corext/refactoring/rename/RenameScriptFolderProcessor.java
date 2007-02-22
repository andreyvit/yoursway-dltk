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
package org.eclipse.dltk.internal.corext.refactoring.rename;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.Checks;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringAvailabilityTester;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringCoreMessages;
import org.eclipse.dltk.internal.corext.refactoring.ScriptRefactoringArguments;
import org.eclipse.dltk.internal.corext.refactoring.ScriptRefactoringDescriptor;
import org.eclipse.dltk.internal.corext.refactoring.ScriptRefactoringDescriptorComment;
import org.eclipse.dltk.internal.corext.refactoring.base.ScriptStatusContext;
import org.eclipse.dltk.internal.corext.refactoring.changes.DynamicValidationRefactoringChange;
import org.eclipse.dltk.internal.corext.refactoring.changes.RenameScriptFolderChange;
import org.eclipse.dltk.internal.corext.refactoring.code.ScriptableRefactoring;
import org.eclipse.dltk.internal.corext.refactoring.participants.ScriptProcessors;
import org.eclipse.dltk.internal.corext.refactoring.tagging.IQualifiedNameUpdating;
import org.eclipse.dltk.internal.corext.refactoring.tagging.IReferenceUpdating;
import org.eclipse.dltk.internal.corext.refactoring.tagging.ITextUpdating;
import org.eclipse.dltk.internal.corext.refactoring.util.ModelElementUtil;
import org.eclipse.dltk.internal.corext.refactoring.util.ResourceUtil;
import org.eclipse.dltk.internal.corext.refactoring.util.TextChangeManager;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.internal.corext.util.Resources;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.RefactoringStatusContext;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments;
import org.eclipse.ltk.core.refactoring.participants.RenameArguments;


public class RenameScriptFolderProcessor extends ScriptRenameProcessor implements IReferenceUpdating, ITextUpdating, IQualifiedNameUpdating {
	
	private static final String ID_RENAME_PACKAGE= "org.eclipse.dltk.ui.rename.script.folder"; //$NON-NLS-1$
	private static final String ATTRIBUTE_QUALIFIED= "qualified"; //$NON-NLS-1$
	private static final String ATTRIBUTE_REFERENCES= "references"; //$NON-NLS-1$
	private static final String ATTRIBUTE_TEXTUAL_MATCHES= "textual"; //$NON-NLS-1$
	private static final String ATTRIBUTE_PATTERNS= "patterns"; //$NON-NLS-1$
	private static final String ATTRIBUTE_HIERARCHICAL= "hierarchical"; //$NON-NLS-1$

	private IScriptFolder fPackage;
	
	private TextChangeManager fChangeManager;
	static {
		if (DLTKCore.DEBUG) {
			System.err.println("TODO: RenameScriptFolderProcessor add import manager supprot code here...");
		}
	}
	//private ImportsManager fImportsManager;
	//private QualifiedNameSearchResult fQualifiedNameSearchResult;
	
	private boolean fUpdateReferences;
	private boolean fUpdateTextualMatches;
	private boolean fUpdateQualifiedNames;
	private String fFilePatterns;
	private boolean fRenameSubpackages;

	public static final String IDENTIFIER= "org.eclipse.dltk.ui.renamePackageProcessor"; //$NON-NLS-1$

	/**
	 * Creates a new rename package processor.
	 * @param fragment the package fragment, or <code>null</code> if invoked by scripting
	 */
	public RenameScriptFolderProcessor(IScriptFolder fragment) {
		fPackage= fragment;
		if (fPackage != null)
			setNewElementName(fPackage.getElementName());
		fUpdateReferences= true;
		fUpdateTextualMatches= false;
		fRenameSubpackages= false;
	}

	public String getIdentifier() {
		return IDENTIFIER;
	}
	
	public boolean isApplicable() throws CoreException {
		return RefactoringAvailabilityTester.isRenameAvailable(fPackage);
	}
	
	public String getProcessorName(){
		return RefactoringCoreMessages.RenamePackageRefactoring_name;
	}
	
	protected String[] getAffectedProjectNatures() throws CoreException {
		return ScriptProcessors.computeAffectedNatures(fPackage);
	}
	
	public Object[] getElements() {
		return new Object[] {fPackage};
	}
	
	protected RenameModifications computeRenameModifications() throws CoreException {
		RenameModifications result= new RenameModifications();
		result.rename(fPackage, new RenameArguments(getNewElementName(), getUpdateReferences()), fRenameSubpackages);
		return result;
	}
	
	protected IFile[] getChangedFiles() throws CoreException {
		Set combined= new HashSet();
		combined.addAll(Arrays.asList(ResourceUtil.getFiles(fChangeManager.getAllSourceModules())));
		if (fRenameSubpackages) {
			IScriptFolder[] allPackages= ModelElementUtil.getPackageAndSubpackages(fPackage);
			for (int i= 0; i < allPackages.length; i++) {
				combined.addAll(Arrays.asList(ResourceUtil.getFiles(allPackages[i].getSourceModules())));
			}
		} else {
			combined.addAll(Arrays.asList(ResourceUtil.getFiles(fPackage.getSourceModules())));
		}
		if (DLTKCore.DEBUG) {
			System.err.println("TODO: RenameScriptFolderProcessor Add fQualifiedNameSearchResult suppor code");
		}
//		if (fQualifiedNameSearchResult != null)
//			combined.addAll(Arrays.asList(fQualifiedNameSearchResult.getAllFiles()));
		return (IFile[]) combined.toArray(new IFile[combined.size()]);
	}
	
	//---- ITextUpdating -------------------------------------------------

	public boolean canEnableTextUpdating() {
		return true;
	}
	
	public boolean getUpdateTextualMatches() {
		return fUpdateTextualMatches;
	}

	public void setUpdateTextualMatches(boolean update) {
		fUpdateTextualMatches= update;
	}

	//---- IReferenceUpdating --------------------------------------
		
	public boolean canEnableUpdateReferences() {
		return true;
	}

	public void setUpdateReferences(boolean update) {
		fUpdateReferences= update;
	}	
	
	public boolean getUpdateReferences(){
		return fUpdateReferences;
	}
	
	//---- IQualifiedNameUpdating ----------------------------------

	public boolean canEnableQualifiedNameUpdating() {
		return !fPackage.isRootFolder();
	}
	
	public boolean getUpdateQualifiedNames() {
		return fUpdateQualifiedNames;
	}
	
	public void setUpdateQualifiedNames(boolean update) {
		fUpdateQualifiedNames= update;
	}
	
	public String getFilePatterns() {
		return fFilePatterns;
	}
	
	public void setFilePatterns(String patterns) {
		Assert.isNotNull(patterns);
		fFilePatterns= patterns;
	}
	
	//----
	
	public boolean canEnableRenameSubpackages() throws ModelException {
		return fPackage.hasSubfolders();
	}
	
	public boolean getRenameSubpackages() {
		return fRenameSubpackages;
	}

	public void setRenameSubpackages(boolean rename) {
		fRenameSubpackages= rename;
	}	
	
	//---- IRenameProcessor ----------------------------------------------
	
	public final String getCurrentElementName(){
		return fPackage.getElementName();
	}
	
	public String getCurrentElementQualifier() {
		return ""; //$NON-NLS-1$
	}
	
	public RefactoringStatus checkNewElementName(String newName) throws CoreException {
		Assert.isNotNull(newName, "new name"); //$NON-NLS-1$
		//RefactoringStatus result= Checks.checkPackageName(newName);
		if (DLTKCore.DEBUG) {
			System.err.println("TODO: Add correct package name validation here...");
		}
		//#replace begin
		RefactoringStatus result= new RefactoringStatus();
		if ("".equals(newName)) //$NON-NLS-1$			
			return RefactoringStatus.createFatalErrorStatus(RefactoringCoreMessages.Checks_Choose_name);
		//#end
		if (Checks.isAlreadyNamed(fPackage, newName))
			result.addFatalError(RefactoringCoreMessages.RenamePackageRefactoring_another_name); 
		result.merge(checkPackageInCurrentRoot(newName));
		return result;
	}
	
	public Object getNewElement(){
		IModelElement parent= fPackage.getParent();
		if (!(parent instanceof IProjectFragment))
			return fPackage;//??
			
		IProjectFragment root= (IProjectFragment)parent;
		return root.getScriptFolder(getNewElementName());
	}
	
	public RefactoringStatus checkInitialConditions(IProgressMonitor pm) throws CoreException {
		return new RefactoringStatus();
	}
	
	protected RefactoringStatus doCheckFinalConditions(IProgressMonitor pm, CheckConditionsContext context) throws CoreException {
		try{
			pm.beginTask("", 23 + (fUpdateQualifiedNames ? 10 : 0)); //$NON-NLS-1$
			pm.setTaskName(RefactoringCoreMessages.RenamePackageRefactoring_checking); 
			RefactoringStatus result= new RefactoringStatus();
			result.merge(checkNewElementName(getNewElementName()));
			pm.worked(1);
			//result.merge(checkForMainAndNativeMethods());
			pm.worked(2);
			
			if (fPackage.isReadOnly()){
				String message= Messages.format(RefactoringCoreMessages.RenamePackageRefactoring_Packagered_only, fPackage.getElementName()); 
				result.addFatalError(message);
			} else if (Resources.isReadOnly(fPackage.getResource())) {
				String message= Messages.format(RefactoringCoreMessages.RenamePackageRefactoring_resource_read_only, fPackage.getElementName());
				result.addError(message);
			}				
				
			result.merge(checkPackageName(getNewElementName()));
			if (result.hasFatalError())
				return result;
			
			fChangeManager= new TextChangeManager();
			//fImportsManager= new ImportsManager();
			
			SubProgressMonitor subPm= new SubProgressMonitor(pm, 16);
			if (fRenameSubpackages) {
				IScriptFolder[] allSubpackages= ModelElementUtil.getPackageAndSubpackages(fPackage);
				subPm.beginTask("", allSubpackages.length); //$NON-NLS-1$
				for (int i= 0; i < allSubpackages.length; i++) {
					new PackageRenamer(allSubpackages[i], this, fChangeManager/*, fImportsManager*/).doRename(new SubProgressMonitor(subPm, 1), result);
				}
				subPm.done();
			} else {
				new PackageRenamer(fPackage, this, fChangeManager/*, fImportsManager*/).doRename(subPm, result);
			}
			
//			fImportsManager.rewriteImports(fChangeManager, new SubProgressMonitor(pm, 3));
			
			if (DLTKCore.DEBUG) {
				System.err.println("TODO: Add updating of fUpdateQualifiedNames");
			}
//			if (fUpdateQualifiedNames)
//				computeQualifiedNameMatches(new SubProgressMonitor(pm, 10));
			
			return result;
		} finally{
			pm.done();
		}	
	}
	
	public IScriptFolder getPackage() {
		return fPackage;
	}	
	
	/*
	 * returns true if the new name is ok if the specified root.
	 * if a package fragment with this name exists and has script resources,
	 * then the name is not ok.
	 */
	public static boolean isPackageNameOkInRoot(String newName, IProjectFragment root) throws CoreException {
		IScriptFolder pack= root.getScriptFolder(newName);
		if (! pack.exists())
			return true;
		else if (! pack.hasSubfolders()) //leaves are no good
			return false;			
		else if (pack.containsScriptResources())
			return false;
		else if (pack.getForeignResources().length != 0)
			return false;
		else 
			return true;	
	}
	
	private RefactoringStatus checkPackageInCurrentRoot(String newName) throws CoreException {
		if (isPackageNameOkInRoot(newName, getProjectFragment()))
			return null;
		else
			return RefactoringStatus.createFatalErrorStatus(RefactoringCoreMessages.RenamePackageRefactoring_package_exists);
	}

	private IProjectFragment getProjectFragment() {
		return ((IProjectFragment)fPackage.getParent());
	}
	
	private RefactoringStatus checkPackageName(String newName) throws CoreException {		
		RefactoringStatus status= new RefactoringStatus();
		IProjectFragment[] roots= fPackage.getScriptProject().getProjectFragments();
		Set topLevelTypeNames= getTopLevelTypeNames();
		for (int i= 0; i < roots.length; i++) {
			if (! isPackageNameOkInRoot(newName, roots[i])){
				String message= Messages.format(RefactoringCoreMessages.RenamePackageRefactoring_aleady_exists, new Object[]{getNewElementName(), roots[i].getElementName()});
				status.merge(RefactoringStatus.createWarningStatus(message));
				status.merge(checkTypeNameConflicts(roots[i], newName, topLevelTypeNames)); 
			}
		}
		return status;
	}
	
	private Set getTopLevelTypeNames() throws CoreException {
		ISourceModule[] cus= fPackage.getSourceModules();
		Set result= new HashSet(2 * cus.length); 
		for (int i= 0; i < cus.length; i++) {
			result.addAll(getTopLevelTypeNames(cus[i]));
		}
		return result;
	}
	
	private static Collection getTopLevelTypeNames(ISourceModule iSourceModule) throws CoreException {
		IType[] types= iSourceModule.getTypes();
		List result= new ArrayList(types.length);
		for (int i= 0; i < types.length; i++) {
			result.add(types[i].getElementName());
		}
		return result;
	}
	
	private RefactoringStatus checkTypeNameConflicts(IProjectFragment root, String newName, Set topLevelTypeNames) throws CoreException {
		IScriptFolder otherPack= root.getScriptFolder(newName);
		if (fPackage.equals(otherPack))
			return null;
		ISourceModule[] cus= otherPack.getSourceModules();
		RefactoringStatus result= new RefactoringStatus();
		for (int i= 0; i < cus.length; i++) {
			result.merge(checkTypeNameConflicts(cus[i], topLevelTypeNames));
		}
		return result;
	}
	
	private RefactoringStatus checkTypeNameConflicts(ISourceModule iSourceModule, Set topLevelTypeNames) throws CoreException {
		RefactoringStatus result= new RefactoringStatus();
		IType[] types= iSourceModule.getTypes();
		String packageName= iSourceModule.getParent().getElementName();
		for (int i= 0; i < types.length; i++) {
			String name= types[i].getElementName();
			if (topLevelTypeNames.contains(name)){
				String[] keys= {packageName, name};
				String msg= Messages.format(RefactoringCoreMessages.RenamePackageRefactoring_contains_type, keys); 
				RefactoringStatusContext context= ScriptStatusContext.create(types[i]);
				result.addError(msg, context);
			}	
		}
		return result;
	}

	public Change createChange(IProgressMonitor monitor) throws CoreException {
		try {
			monitor.beginTask(RefactoringCoreMessages.RenamePackageRefactoring_creating_change, 1);
			final Map arguments= new HashMap();
			String project= null;
			IDLTKProject scriptProject= fPackage.getScriptProject();
			if (scriptProject != null)
				project= scriptProject.getElementName();
			final int flags= ScriptRefactoringDescriptor.ARCHIVE_IMPORTABLE | ScriptRefactoringDescriptor.ARCHIVE_REFACTORABLE | RefactoringDescriptor.STRUCTURAL_CHANGE | RefactoringDescriptor.MULTI_CHANGE;
			final String description= Messages.format(RefactoringCoreMessages.RenamePackageProcessor_descriptor_description_short, fPackage.getElementName());
			final String header= Messages.format(RefactoringCoreMessages.RenamePackageProcessor_descriptor_description, new String[] { fPackage.getElementName(), getNewElementName()});
			final ScriptRefactoringDescriptorComment comment= new ScriptRefactoringDescriptorComment(this, header);
			if (fRenameSubpackages)
				comment.addSetting(RefactoringCoreMessages.RenamePackageProcessor_rename_subpackages);
			final ScriptRefactoringDescriptor descriptor= new ScriptRefactoringDescriptor(ID_RENAME_PACKAGE, project, description, comment.asString(), arguments, flags);
			arguments.put(ScriptRefactoringDescriptor.ATTRIBUTE_INPUT, descriptor.elementToHandle(fPackage));
			arguments.put(ScriptRefactoringDescriptor.ATTRIBUTE_NAME, getNewElementName());
			if (fFilePatterns != null && !"".equals(fFilePatterns)) //$NON-NLS-1$
				arguments.put(ATTRIBUTE_PATTERNS, fFilePatterns);
			arguments.put(ATTRIBUTE_REFERENCES, Boolean.valueOf(fUpdateReferences).toString());
			arguments.put(ATTRIBUTE_QUALIFIED, Boolean.valueOf(fUpdateQualifiedNames).toString());
			arguments.put(ATTRIBUTE_TEXTUAL_MATCHES, Boolean.valueOf(fUpdateTextualMatches).toString());
			arguments.put(ATTRIBUTE_HIERARCHICAL, Boolean.valueOf(fRenameSubpackages).toString());
			final DynamicValidationRefactoringChange result= new DynamicValidationRefactoringChange(descriptor, RefactoringCoreMessages.RenamePackageRefactoring_change_name);
			result.addAll(fChangeManager.getAllChanges());
			result.add(new RenameScriptFolderChange(null, fPackage, getNewElementName(), comment.asString(), fRenameSubpackages));
			monitor.worked(1);
			return result;
		} finally {
			fChangeManager= null;
			//fImportsManager= null;
			monitor.done();
		}
	}

	public Change postCreateChange(Change[] participantChanges, IProgressMonitor pm) throws CoreException {
//		if (fQualifiedNameSearchResult != null) {
//			try {
//				return fQualifiedNameSearchResult.getSingleChange(Changes.getModifiedFiles(participantChanges));
//			} finally {
//				fQualifiedNameSearchResult= null;
//			}
//		} else {
			return null;
//		}
	}

	public String getNewPackageName(String oldSubPackageName) {
		String oldPackageName= getPackage().getElementName();
		return getNewElementName() + oldSubPackageName.substring(oldPackageName.length());
	}
	
	private static class PackageRenamer {
		private final IScriptFolder fPackage;
		private final RenameScriptFolderProcessor fProcessor;
		private final TextChangeManager fTextChangeManager;
		//private final ImportsManager fImportsManager;
		
		/** references to fPackage (can include star imports which also import namesake package fragments) */
		//private SearchResultGroup[] fOccurrences;
		
		/** References in CUs from fOccurrences and fPackage to types in namesake packages.
		 * <p>These need an import with the old package name.
		 * <p>- from fOccurrences (without namesakes): may have shared star import
		 * 		(star-import not updated here, but for fOccurrences)
		 * <p>- from fPackage: may have unimported references to types of namesake packages
		 * <p>- both: may have unused imports of namesake packages.
		 * <p>Mutable List of SearchResultGroup. */
		//private List fReferencesToTypesInNamesakes;
	
		/** References in CUs from namesake packages to types in fPackage.
		 * <p>These need an import with the new package name.
		 * <p>Mutable List of SearchResultGroup. */
		//private List fReferencesToTypesInPackage;
		
		public PackageRenamer(IScriptFolder pack, RenameScriptFolderProcessor processor, TextChangeManager textChangeManager/*, ImportsManager importsManager*/) {
			fPackage= pack;
			fProcessor= processor;
			fTextChangeManager= textChangeManager;
			//fImportsManager= importsManager;
		}
	
		void doRename(IProgressMonitor pm, RefactoringStatus result) throws CoreException {
			pm.beginTask("", 16 + (fProcessor.getUpdateTextualMatches() ? 10 : 0)); //$NON-NLS-1$
			if (fProcessor.getUpdateReferences()){
				pm.setTaskName(RefactoringCoreMessages.RenamePackageRefactoring_searching);	 
				//fOccurrences= getReferences(new SubProgressMonitor(pm, 4), result);	
				//fReferencesToTypesInNamesakes= getReferencesToTypesInNamesakes(new SubProgressMonitor(pm, 4), result);
				//fReferencesToTypesInPackage= getReferencesToTypesInPackage(new SubProgressMonitor(pm, 4), result);
				pm.setTaskName(RefactoringCoreMessages.RenamePackageRefactoring_checking); 
				result.merge(analyzeAffectedSourceModules());
				pm.worked(1);
			} else {
				//fOccurrences= new SearchResultGroup[0];
				pm.worked(13);
			}
		
			if (result.hasFatalError())
				return;
			
			if (fProcessor.getUpdateReferences())
				addReferenceUpdates(new SubProgressMonitor(pm, 3));
			else
				pm.worked(3);
			
			if (fProcessor.getUpdateTextualMatches() && fPackage.equals(fProcessor.getPackage())) {
				pm.subTask(RefactoringCoreMessages.RenamePackageRefactoring_searching_text); 
				//addTextMatches(new SubProgressMonitor(pm, 10));
			}
			
			pm.done();
		}
		private RefactoringStatus analyzeAffectedSourceModules() throws CoreException {
			//TODO: also for both fReferencesTo...; only check each CU once!
			RefactoringStatus result= new RefactoringStatus();
			//fOccurrences= Checks.excludeSourceModules(fOccurrences, result);
			if (result.hasFatalError())
				return result;
			
			//result.merge(Checks.checkCompileErrorsInAffectedFiles(fOccurrences));	
			return result;
		}
			
		private void addReferenceUpdates(IProgressMonitor pm) throws CoreException {
			if (DLTKCore.DEBUG) {
				System.err.println("TODO: Add search support code.");
			}
	
//			if (fReferencesToTypesInNamesakes.size() != 0) {
//				for (Iterator iter= fReferencesToTypesInNamesakes.iterator(); iter.hasNext();) {
//					//SearchResultGroup referencesToTypesInNamesakes= (SearchResultGroup) iter.next();
//					//addTypeImports(referencesToTypesInNamesakes);
//					pm.worked(1);
//				}
//			}
//			if (fReferencesToTypesInPackage.size() != 0) {
//				for (Iterator iter= fReferencesToTypesInPackage.iterator(); iter.hasNext();) {
//					//SearchResultGroup namesakeReferencesToPackage= (SearchResultGroup) iter.next();
//					//updateTypeImports(namesakeReferencesToPackage);
//					pm.worked(1);
//				}
//			} 
			pm.done();
		}
		
		private static String cutOffInnerTypes(String reference) {
			int dotPos= reference.indexOf('.'); // cut off inner types
			if (dotPos != -1)
				reference= reference.substring(0, dotPos);
			return reference;
		}
		
		private String getNewPackageName() {
			return fProcessor.getNewPackageName(fPackage.getElementName());
		}
	}

	public RefactoringStatus initialize(RefactoringArguments arguments) {
		if (arguments instanceof ScriptRefactoringArguments) {
			final ScriptRefactoringArguments extended= (ScriptRefactoringArguments) arguments;
			final String handle= extended.getAttribute(ScriptRefactoringDescriptor.ATTRIBUTE_INPUT);
			if (handle != null) {
				final IModelElement element= ScriptRefactoringDescriptor.handleToElement(extended.getProject(), handle, false);
				if (element == null || !element.exists() || element.getElementType() != IModelElement.SCRIPT_FOLDER)
					return ScriptableRefactoring.createInputFatalStatus(element, getRefactoring().getName(), ID_RENAME_PACKAGE);
				else
					fPackage= (IScriptFolder) element;
			} else
				return RefactoringStatus.createFatalErrorStatus(Messages.format(RefactoringCoreMessages.InitializableRefactoring_argument_not_exist, ScriptRefactoringDescriptor.ATTRIBUTE_INPUT));
			final String name= extended.getAttribute(ScriptRefactoringDescriptor.ATTRIBUTE_NAME);
			if (name != null && !"".equals(name)) //$NON-NLS-1$
				setNewElementName(name);
			else
				return RefactoringStatus.createFatalErrorStatus(Messages.format(RefactoringCoreMessages.InitializableRefactoring_argument_not_exist, ScriptRefactoringDescriptor.ATTRIBUTE_NAME));
			final String patterns= extended.getAttribute(ATTRIBUTE_PATTERNS);
			if (patterns != null && !"".equals(patterns)) //$NON-NLS-1$
				fFilePatterns= patterns;
			else
				fFilePatterns= ""; //$NON-NLS-1$
			final String references= extended.getAttribute(ATTRIBUTE_REFERENCES);
			if (references != null) {
				fUpdateReferences= Boolean.valueOf(references).booleanValue();
			} else
				return RefactoringStatus.createFatalErrorStatus(Messages.format(RefactoringCoreMessages.InitializableRefactoring_argument_not_exist, ATTRIBUTE_REFERENCES));
			final String matches= extended.getAttribute(ATTRIBUTE_TEXTUAL_MATCHES);
			if (matches != null) {
				fUpdateTextualMatches= Boolean.valueOf(matches).booleanValue();
			} else
				return RefactoringStatus.createFatalErrorStatus(Messages.format(RefactoringCoreMessages.InitializableRefactoring_argument_not_exist, ATTRIBUTE_TEXTUAL_MATCHES));
			final String qualified= extended.getAttribute(ATTRIBUTE_QUALIFIED);
			if (qualified != null) {
				fUpdateQualifiedNames= Boolean.valueOf(qualified).booleanValue();
			} else
				return RefactoringStatus.createFatalErrorStatus(Messages.format(RefactoringCoreMessages.InitializableRefactoring_argument_not_exist, ATTRIBUTE_QUALIFIED));
			final String hierarchical= extended.getAttribute(ATTRIBUTE_HIERARCHICAL);
			if (hierarchical != null) {
				fRenameSubpackages= Boolean.valueOf(hierarchical).booleanValue();
			} else
				return RefactoringStatus.createFatalErrorStatus(Messages.format(RefactoringCoreMessages.InitializableRefactoring_argument_not_exist, ATTRIBUTE_HIERARCHICAL));
		} else
			return RefactoringStatus.createFatalErrorStatus(RefactoringCoreMessages.InitializableRefactoring_inacceptable_arguments);
		return new RefactoringStatus();
	}	
}
