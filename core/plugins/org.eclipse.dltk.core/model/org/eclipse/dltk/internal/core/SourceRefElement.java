package org.eclipse.dltk.internal.core;

import java.util.HashMap;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IBuffer;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IOpenable;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.ISourceReference;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.WorkingCopyOwner;
import org.eclipse.dltk.internal.core.util.MementoTokenizer;


public abstract class SourceRefElement extends ModelElement implements ISourceReference {

	/*
	 * A count to uniquely identify this element in the case
	 * that a duplicate named element exists. For example, if
	 * there are two fields in a compilation unit with the
	 * same name, the occurrence count is used to distinguish
	 * them.  The occurrence count starts at 1 (thus the first 
	 * occurrence is occurrence 1, not occurrence 0).
	 */
	public int occurrenceCount = 1;

	protected SourceRefElement(ModelElement parent) throws IllegalArgumentException {
		super(parent);
	}
	
	protected Object createElementInfo() {
		//do not used for source elements
		return null;
	}

	protected void generateInfos(Object info, HashMap newElements,
			IProgressMonitor pm) throws ModelException {
		Openable openableParent = (Openable)getOpenableParent();
		if (openableParent == null) return;

		ModelElementInfo openableParentInfo = (ModelElementInfo) ModelManager.getModelManager().getInfo(openableParent);
		if (openableParentInfo == null) {
			openableParent.generateInfos(openableParent.createElementInfo(), newElements, pm);
		}
	}

	/**
	 * Return the first instance of IOpenable in the hierarchy of this
	 * type (going up the hierarchy from this type);
	 */
	public IOpenable getOpenableParent() {
		IModelElement current = getParent();
		while (current != null){
			if (current instanceof IOpenable){
				return (IOpenable) current;
			}
			current = current.getParent();
		}
		return null;
	}
	
	public IResource getResource() {
		return getParent().getResource();
	}
	
	/**
	 * @see ISourceReference
	 */
	public ISourceRange getSourceRange() throws ModelException {
		SourceRefElementInfo info = (SourceRefElementInfo) getElementInfo();
		return info.getSourceRange();
	}

	public IPath getPath() {
		return getParent().getPath();
	}

	public IResource getUnderlyingResource() throws ModelException {
		if (!exists()) throw newNotPresentException();
		return getParent().getUnderlyingResource();
	}
	
	/**
	 * @see IParent 
	 */
	public boolean hasChildren() throws ModelException {
		return getChildren().length > 0;
	}
	
	/**
	 * @see IModelElement
	 */
	public boolean isStructureKnown() throws ModelException {
		// structure is always known inside an openable
		return true;
	}
		
	public boolean equals(Object o) {
		if (!(o instanceof SourceRefElement)) return false;
		return this.occurrenceCount == ((SourceRefElement)o).occurrenceCount &&
				super.equals(o);
	}
	
	protected void toStringName(StringBuffer buffer) {
		super.toStringName(buffer);
		if (this.occurrenceCount > 1) {
			buffer.append("#"); //$NON-NLS-1$
			buffer.append(this.occurrenceCount);
		}
	}
	/**
	 * Elements within compilation units and class files have no
	 * corresponding resource.
	 *
	 * @see IModelElement
	 */
	public IResource getCorrespondingResource() throws ModelException {
		if (!exists()) throw newNotPresentException();
		return null;
	}
	public IModelElement getHandleUpdatingCountFromMemento(MementoTokenizer memento, WorkingCopyOwner owner) {
		if (!memento.hasMoreTokens()) return this;
		this.occurrenceCount = Integer.parseInt(memento.nextToken());
		if (!memento.hasMoreTokens()) return this;
		String token = memento.nextToken();
		return getHandleFromMemento(token, memento, owner);
	}
	public IModelElement getHandleFromMemento(String token, MementoTokenizer memento, WorkingCopyOwner workingCopyOwner) {
		switch (token.charAt(0)) {
			case JEM_COUNT:
				return getHandleUpdatingCountFromMemento(memento, workingCopyOwner);
		}
		return this;
	}
	public ISourceModule getSourceModule() {
		return ((ModelElement)getParent()).getSourceModule();
	}
	/**
	 * @see ISourceReference
	 */
	public String getSource() throws ModelException {
		IOpenable openable = getOpenableParent();
		IBuffer buffer = openable.getBuffer();
		if (buffer == null) {
			return null;
		}
		ISourceRange range = getSourceRange();
		int offset = range.getOffset();
		int length = range.getLength();
		if (offset == -1 || length == 0 ) {
			return null;
		}
		int bufLen = buffer.getLength();
		if (bufLen < offset + length)
			length--;
		try {
			return buffer.getText(offset, length);
		} catch(RuntimeException e) {
			return null;
		}
	}
}
