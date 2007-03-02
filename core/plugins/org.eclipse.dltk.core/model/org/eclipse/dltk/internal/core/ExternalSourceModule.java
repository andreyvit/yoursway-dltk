package org.eclipse.dltk.internal.core;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.compiler.CharOperation;
import org.eclipse.dltk.core.CompletionRequestor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IBuffer;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IExternalSourceModule;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelStatusConstants;
import org.eclipse.dltk.core.IPackageDeclaration;
import org.eclipse.dltk.core.IProblemRequestor;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ISourceElementParser;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceModuleInfoCache;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.WorkingCopyOwner;
import org.eclipse.dltk.internal.core.util.MementoTokenizer;
import org.eclipse.dltk.internal.core.util.Messages;
import org.eclipse.dltk.internal.core.util.Util;
import org.eclipse.dltk.utils.CorePrinter;


public class ExternalSourceModule extends Openable implements IExternalSourceModule, IStorage, org.eclipse.dltk.compiler.env.ISourceModule {

	private static final boolean DEBUG_PRINT_MODEL = DLTKCore.DEBUG_PRINT_MODEL;

	protected String name;

	public WorkingCopyOwner owner;
	final private boolean fReadOnly;
	private IStorage fStorage;

	public ExternalSourceModule(ScriptFolder parent, String name, WorkingCopyOwner owner, IStorage storage) {

		super(parent);
		this.name = name;
		this.owner = owner;
		this.fReadOnly = true;
		fStorage = storage;
	}
	
	public ExternalSourceModule(ScriptFolder parent, String name, WorkingCopyOwner owner, boolean readOnly, IStorage storage) {

		super(parent);
		this.name = name;
		this.owner = owner;
		this.fReadOnly = readOnly;
		fStorage = storage;
	}
	
	/*
	 * @see ISourceModule#getOwner()
	 */
	public WorkingCopyOwner getOwner() {
		return isPrimary() || !isWorkingCopy() ? null : this.owner;
	}

	/**
	 * @see ISourceModule#commitWorkingCopy(boolean, IProgressMonitor)
	 */
	public void commitWorkingCopy(boolean force, IProgressMonitor monitor) throws ModelException {
		throw new ModelException(new ModelStatus(IModelStatusConstants.INVALID_ELEMENT_TYPES, this)); 
	}

	/*
	 * @see ISourceModule#becomeWorkingCopy(IProblemRequestor,
	 *      IProgressMonitor)
	 */
	public void becomeWorkingCopy(IProblemRequestor problemRequestor, IProgressMonitor monitor) throws ModelException {		
	}

	protected boolean buildStructure(OpenableElementInfo info, IProgressMonitor pm, Map newElements, IResource underlyingResource) throws ModelException {

		// check if this source module can be opened
		if (!isWorkingCopy()) { // no check is done on root kind or
			// exclusion
			// pattern for working copies
			IStatus status = validateSourceModule(underlyingResource);
			if (!status.isOK())
				throw newModelException(status);
		}
		// prevents reopening of non-primary working copies (they are closed
		// when they are discarded and should not be reopened)
		if (!isPrimary() ) {
			throw newNotPresentException();
		}
		SourceModuleElementInfo moduleInfo = (SourceModuleElementInfo) info;
		// get buffer contents
		IBuffer buffer = getBufferManager().getBuffer(this);
		if (buffer == null) {
			buffer = openBuffer(pm, moduleInfo); // open buffer independently
			// from the info, since we
			// are building the info
		}
		final char[] contents = buffer == null ? null : buffer.getCharacters();
		try {
			// generate structure and compute syntax problems if needed
			SourceModuleStructureRequestor requestor = new SourceModuleStructureRequestor(this, moduleInfo, newElements);
			IDLTKLanguageToolkit toolkit = null;								
			toolkit = DLTKLanguageManager.findToolkit(this.getFullPath());
			if(toolkit == null) {
				throw new ModelException(new ModelStatus(ModelStatus.INVALID_NAME));
			}
			ISourceElementParser parser = toolkit.createSourceElementParser(requestor, null, Collections.EMPTY_MAP);
			
			ISourceModuleInfoCache sourceModuleInfoCache = ModelManager.getModelManager().getSourceModuleInfoCache();
//			sourceModuleInfoCache.remove(this);
			parser.parseSourceModule(contents, sourceModuleInfoCache.get(this));
			
//			parser.parseSourceModule(contents, null);
			if (ExternalSourceModule.DEBUG_PRINT_MODEL) {
				System.out.println("Source Module Debug print:");
				CorePrinter printer = new CorePrinter(System.out);
				this.printNode(printer);
				printer.flush();
			}
			return moduleInfo.isStructureKnown();
		} catch (CoreException e) {
			throw new ModelException(e);
		}
	}

	protected Object createElementInfo() {

		return new SourceModuleElementInfo();
	}

	public int getElementType() {

		return SOURCE_MODULE;
	}

	public IResource getResource() {
		return null;
	}

	public IPath getPath() {

		ProjectFragment root = this.getProjectFragment();
		if (root.isArchive()) {
			return root.getPath();
		} else {
			return this.getParent().getPath().append(this.getElementName());
		}
	}

	public boolean isWorkingCopy() {
		return true;
	}

	public boolean isPrimary() {

		return this.owner == DefaultWorkingCopyOwner.PRIMARY;
	}

	/*
	 * Returns the per working copy info for the receiver, or null if none
	 * exist. Note: the use count of the per working copy info is NOT
	 * incremented.
	 */
	public ModelManager.PerWorkingCopyInfo getPerWorkingCopyInfo() {
		return null;
	}

	public void discardWorkingCopy() throws ModelException {	
	}

	protected boolean hasBuffer() {

		return true;
	}

	/**
	 * Returns true if this handle represents the same element as the given
	 * handle.
	 * 
	 * @see Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {

		if (!(obj instanceof ExternalSourceModule))
			return false;
		ExternalSourceModule other = (ExternalSourceModule) obj;
		return this.owner.equals(other.owner) && super.equals(obj);
	}

	protected IBuffer openBuffer(IProgressMonitor pm, Object info) throws ModelException {

		// create buffer
		boolean isWorkingCopy = isWorkingCopy();
		IBuffer buffer = isWorkingCopy ? this.owner.createBuffer(this) : BufferManager.getDefaultBufferManager().createBuffer(this);
		if (buffer == null)
			return null;

		// set the buffer source
		if (buffer.getCharacters() == null) {
			if (isWorkingCopy) {
				ISourceModule original;
				if (!isPrimary() && (original = new ExternalSourceModule((ScriptFolder) getParent(), getElementName(), DefaultWorkingCopyOwner.PRIMARY, this.fStorage)).isOpen()) {
					buffer.setContents(original.getSource());
				} else {
					if( getResource()!= null) {
						IFile file = (IFile) getResource();
						if (file == null || !file.exists()) {
						// initialize buffer with empty contents
						buffer.setContents(CharOperation.NO_CHAR);
						} else {
							buffer.setContents(Util.getResourceContentsAsCharArray(file));
						}
					}
					else {
						char[] content = getBufferContent();
						buffer.setContents(content);
					}
				}
			} else {						
				char[] content = getBufferContent();
				buffer.setContents(content);					
			}
		}

		// add buffer to buffer cache
		BufferManager bufManager = getBufferManager();
		bufManager.addBuffer(buffer);

		// listen to buffer changes
		buffer.addBufferChangedListener(this);

		return buffer;
	}

	private char[] getBufferContent() throws ModelException {
		File file = new File(getPath().toOSString());//(IFile) this.getResource();
		if (file == null || !file.exists())
			throw newNotPresentException();
		
		InputStream stream= null;
		char[] content;
		try {
			stream = new BufferedInputStream( new FileInputStream(file));
		} catch (FileNotFoundException e) {
			throw new ModelException(e, IModelStatusConstants.ELEMENT_DOES_NOT_EXIST);
		}
		try {
			content=org.eclipse.dltk.compiler.util.Util.getInputStreamAsCharArray(stream, -1, "utf-8");
		} catch (IOException e) {
			throw new ModelException(e, IModelStatusConstants.IO_EXCEPTION);
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				// ignore
			}
		}
		return content;
	}

	public String getSource() throws ModelException {

		IBuffer buffer = getBuffer();
		if (buffer == null)
			return ""; //$NON-NLS-1$
		return buffer.getContents();
	}

	public String getElementName() {

		return this.name;
	}

	public ISourceModule getWorkingCopy(IProgressMonitor monitor) throws ModelException {

		return getWorkingCopy(new WorkingCopyOwner() {/*
														 * non shared working
														 * copy
														 */
		}, null/* no problem requestor */, monitor);
	}

	public ISourceModule getWorkingCopy(WorkingCopyOwner workingCopyOwner, IProblemRequestor problemRequestor, IProgressMonitor monitor) throws ModelException {
		return this;
	}

	public boolean exists() {

		// if not a working copy, it exists only if it is a primary compilation
		// unit
		return isPrimary() && validateSourceModule(null).isOK();
	}

	protected IStatus validateSourceModule(IResource resource) {
		IProjectFragment root = getProjectFragment();
		try {
			if (root.getKind() != IProjectFragment.K_SOURCE) {
				return new ModelStatus(IModelStatusConstants.INVALID_ELEMENT_TYPES, root);
			}
		} catch (ModelException e) {
			return e.getModelStatus();
		}
		IPath path = this.getFullPath();
		if(!root.isArchive()) { 
			try {
				IDLTKLanguageToolkit toolkit = DLTKLanguageManager.getLangaugeToolkit(this);			
				if (toolkit != null) {
					return toolkit.validateSourceModule(path);
				} else {
					toolkit = DLTKLanguageManager.findToolkit(path);
					if (toolkit != null) {
						return toolkit.validateSourceModule(path);
					}
					return new ModelStatus(IModelStatusConstants.INVALID_RESOURCE, root);
				}
			} catch (CoreException ex) {
				return new ModelStatus(ex);
			}
		}
		else {
			IDLTKLanguageToolkit toolkit = DLTKLanguageManager.findToolkit(path);
			if (toolkit != null) {
				return toolkit.validateSourceModule(path);
			}
			return new ModelStatus(IModelStatusConstants.INVALID_RESOURCE, root);
		}
	}

	public boolean canBeRemovedFromCache() {		
		// they are destroyed
		return super.canBeRemovedFromCache();
	}

	public boolean canBufferBeRemovedFromCache(IBuffer buffer) {		
		// until working copy is destroyed
		return super.canBufferBeRemovedFromCache(buffer);
	}

	public void close() throws ModelException {		
		super.close();
	}

	protected void closing(Object info) {
		// lifetime of the working copy
	}

	public IModelElement getPrimaryElement(boolean checkOwner) {

		if (checkOwner && isPrimary())
			return this;
		return new ExternalSourceModule((ScriptFolder) getParent(), getElementName(), DefaultWorkingCopyOwner.PRIMARY, this.fStorage);
	}

	public IResource getUnderlyingResource() throws ModelException {

		if (isWorkingCopy() && !isPrimary())
			return null;
		return super.getUnderlyingResource();
	}

	public boolean isConsistent() {

		return !ModelManager.getModelManager().getElementsOutOfSynchWithBuffers().contains(this);
	}

	public void makeConsistent(IProgressMonitor monitor) throws ModelException {

		// makeConsistent(false/*don't create AST*/, 0, monitor);
		openWhenClosed(createElementInfo(), monitor);
	}

	protected void openParent(Object childInfo, HashMap newElements, IProgressMonitor pm) throws ModelException {

		if (!isWorkingCopy())
			super.openParent(childInfo, newElements, pm);
		// don't open parent for a working copy to speed up the first
		// becomeWorkingCopy
	}

	public void save(IProgressMonitor pm, boolean force) throws ModelException {

		if (isWorkingCopy()) {
			// no need to save the buffer for a working copy (this is a noop)
			throw new RuntimeException("not implemented"); // not simply
			// makeConsistent,
			// also computes
			// fine-grain deltas
			// in case the working copy is being reconciled already (if not it
			// would miss
			// one iteration of deltas).
		} else {
			super.save(pm, force);
		}
	}

	public void reconcile(boolean forceProblemDetection, WorkingCopyOwner workingCopyOwner, IProgressMonitor monitor) throws ModelException {
	}

	public ISourceModule getPrimary() {
		return (ISourceModule) getPrimaryElement(true);
	}

	/*
	 * Assume that this is a working copy
	 */
	protected void updateTimeStamp(ExternalSourceModule original) throws ModelException {
		long timeStamp = ((IFile) original.getResource()).getModificationStamp();
		if (timeStamp == IResource.NULL_STAMP) {
			throw new ModelException(new ModelStatus(IModelStatusConstants.INVALID_RESOURCE));
		}
		((SourceModuleElementInfo) getElementInfo()).timestamp = timeStamp;
	}

	public boolean hasResourceChanged() {
		if (!isWorkingCopy())
			return false;

		// if resource got deleted, then #getModificationStamp() will answer
		// IResource.NULL_STAMP, which is always different from the cached
		// timestamp
		Object info = ModelManager.getModelManager().getInfo(this);
		if (info == null)
			return false;
		return ((SourceModuleElementInfo) info).timestamp != getResource().getModificationStamp();
	}

	public IModelElement getElementAt(int position) throws ModelException {
		IModelElement e = getSourceElementAt(position);
		if (e == this) {
			return null;
		} else {
			return e;
		}
	}

	public ISourceRange getSourceRange() throws ModelException {
		return ((SourceModuleElementInfo) getElementInfo()).getSourceRange();
	}

	public void printNode(CorePrinter output) {
		output.formatPrint("DLTK ExSource Module:" + getElementName());
		output.indent();
		try {
			IModelElement modelElements[] = this.getChildren();
			for (int i = 0; i < modelElements.length; ++i) {
				IModelElement element = modelElements[i];
				if (element instanceof ModelElement) {
					((ModelElement) element).printNode(output);
				} else {
					output.print("Unknown element:" + element);
				}
			}
		} catch (ModelException ex) {
			output.formatPrint(ex.getLocalizedMessage());
		}
		output.dedent();
	}	
	public IType getType(String typeName) {
		return new SourceType(this, typeName);
	}	
	public IType[] getTypes() throws ModelException {
		ArrayList list = getChildrenOfType(TYPE);
		IType[] array= new IType[list.size()];
		list.toArray(array);
		return array;
	}
	public IModelElement getHandleFromMemento(String token, MementoTokenizer memento, WorkingCopyOwner workingCopyOwner) {
		switch (token.charAt(0)) {
			case JEM_IMPORTDECLARATION:
				if (DLTKCore.DEBUG) {
					System.err.println("Add import support in SourceModule getHandleFromMemento");
				}
//				ModelElement container = (ModelElement)getImportContainer();
//				return container.getHandleFromMemento(token, memento, workingCopyOwner);
				break;
			case JEM_TYPE:
				if (!memento.hasMoreTokens()) return this;
				String typeName = memento.nextToken();
				ModelElement type = (ModelElement)getType(typeName);
				return type.getHandleFromMemento(memento, workingCopyOwner);
		}
		return null;
	}

	protected char getHandleMementoDelimiter() {
		return JEM_SOURCEMODULE;
	}

	public boolean isReadOnly() {
		return this.fReadOnly;
	}

	public InputStream getContents() throws CoreException {
		return this.fStorage.getContents();
	}

	public IPath getFullPath() {
		return this.fStorage.getFullPath();
	}

	public String getName() {
		return this.fStorage.getName();
	}
	public Object getAdapter(Class adapter) {
		if( adapter == IStorage.class ) {
			return this;
		}
		return super.getAdapter(adapter);
	}

	public void copy(IModelElement container, IModelElement sibling, String rename, boolean replace, IProgressMonitor monitor) throws ModelException {
		if (container == null) {
			throw new IllegalArgumentException(Messages.operation_nullContainer); 
		}
		IModelElement[] elements = new IModelElement[] {this};
		IModelElement[] containers = new IModelElement[] {container};
		String[] renamings = null;
		if (rename != null) {
			renamings = new String[] {rename};
		}
		getModel().copy(elements, containers, null, renamings, replace, monitor);		
	}

	public void delete(boolean force, IProgressMonitor monitor) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void move(IModelElement container, IModelElement sibling, String rename, boolean replace, IProgressMonitor monitor) throws ModelException {
		copy(container, sibling, rename, replace, monitor);		
	}

	public void rename(String name, boolean replace, IProgressMonitor monitor) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void codeComplete(int offset, CompletionRequestor requestor) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void codeComplete(int offset, CompletionRequestor requestor, WorkingCopyOwner owner) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public IModelElement[] codeSelect(int offset, int length) throws ModelException {
		return codeSelect(offset, length, DefaultWorkingCopyOwner.PRIMARY);
	}

	public IModelElement[] codeSelect(int offset, int length, WorkingCopyOwner owner) throws ModelException {
		return super.codeSelect(this, offset, length, owner);
	}

	public IPackageDeclaration getPackageDeclaration(String pkg) {
		return new PackageDeclaration(this, pkg);
	}

	public IPackageDeclaration[] getPackageDeclarations() throws ModelException {
		ArrayList list = getChildrenOfType(PACKAGE_DECLARATION);
		IPackageDeclaration[] array= new IPackageDeclaration[list.size()];
		list.toArray(array);
		return array;
	}
	public IMethod getMethod(String selector) {
		return new SourceMethod(this, selector);
	}

	public IMethod[] getMethods() throws ModelException {
		ArrayList list = getChildrenOfType(METHOD);
		IMethod[] array = new IMethod[list.size()];
		list.toArray(array);
		return array;
	}

	public IModelElement getModelElement() {
		return this;
	}

	public IPath getScriptFolder() {
		return null;
	}

	public char[] getFileName() {
		return this.getPath().toOSString().toCharArray();
	}

	public String getSourceContents() {
		try {
			return getSource();
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	public ISourceModule getSourceModule() {
		return this;
	}
	
	public IField getField(String fieldName) {
		return new SourceField(this, fieldName);
	}

	public IField[] getFields() throws ModelException {
		ArrayList list = getChildrenOfType(FIELD);
		IField[] array = new IField[list.size()];
		list.toArray(array);
		return array;
	}
	
	public IType[] getAllTypes() throws ModelException {
		IModelElement[] types = getTypes();
		int i;
		ArrayList allTypes = new ArrayList(types.length);
		ArrayList typesToTraverse = new ArrayList(types.length);
		for (i = 0; i < types.length; i++) {
			typesToTraverse.add(types[i]);
		}
		while (!typesToTraverse.isEmpty()) {
			IType type = (IType) typesToTraverse.get(0);
			typesToTraverse.remove(type);
			allTypes.add(type);
			types = type.getTypes();
			for (i = 0; i < types.length; i++) {
				typesToTraverse.add(types[i]);
			}
		} 
		IType[] arrayOfAllTypes = new IType[allTypes.size()];
		allTypes.toArray(arrayOfAllTypes);
		return arrayOfAllTypes;
	}	
}