package org.eclipse.dltk.internal.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.dltk.core.IBuffer;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.core.IModelStatusConstants;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.util.Messages;
import org.eclipse.dltk.internal.core.util.Util;



/**
 * <p>This operation creates a compilation unit (CU).
 * If the CU doesn't exist yet, a new compilation unit will be created with the content provided.
 * Otherwise the operation will override the contents of an existing CU with the new content.
 *
 * <p>Note: It is possible to create a CU automatically when creating a
 * class or interface. Thus, the preferred method of creating a CU is
 * to perform a create type operation rather than
 * first creating a CU and secondly creating a type inside the CU.
 *
 * <p>Required Attributes:<ul>
 *  <li>The package fragment in which to create the compilation unit.
 *  <li>The name of the compilation unit.  
 *      Do not include the <code>".java"</code> suffix (ex. <code>"Object"</code> -
 * 		the <code>".java"</code> will be added for the name of the compilation unit.)
 *  <li>
  * </ul>
 */
public class CreateSourceModuleOperation extends ModelOperation {

	/**
	 * The name of the compilation unit being created.
	 */
	protected String fName;
	/**
	 * The source code to use when creating the element.
	 */
	protected String fSource= null;
/**
 * When executed, this operation will create a compilation unit with the given name.
 * The name should have the ".java" suffix.
 */
public CreateSourceModuleOperation(IScriptFolder parentElement, String name, String source, boolean force) {
	super(null, new IModelElement[] {parentElement}, force);
	fName = name;
	fSource = source;
}
/**
 * Creates a compilation unit.
 *
 * @exception ModelException if unable to create the compilation unit.
 */
protected void executeOperation() throws ModelException {
	try {
		//beginTask(Messages.operation_createUnitProgress, 2); 
		ModelElementDelta delta = newModelElementDelta();
		ISourceModule unit = getSourceModule();
		IScriptFolder pkg = (IScriptFolder) getParentElement();
		IContainer folder = (IContainer) pkg.getResource();
		worked(1);
		IFile compilationUnitFile = folder.getFile(new Path(fName));
		if (compilationUnitFile.exists()) {
			// update the contents of the existing unit if fForce is true
			if (force) {
				IBuffer buffer = unit.getBuffer();
				if (buffer == null) return;
				buffer.setContents(fSource);
				unit.save(new NullProgressMonitor(), false);
				resultElements = new IModelElement[] {unit};
				if (!Util.isExcluded(unit)
						&& unit.getParent().exists()) {
					for (int i = 0; i < resultElements.length; i++) {
						delta.changed(resultElements[i], IModelElementDelta.F_CONTENT);
					}
					addDelta(delta);
				}
			} else {
				throw new ModelException(new ModelStatus(
					IModelStatusConstants.NAME_COLLISION, 
					Messages.bind(Messages.status_nameCollision, compilationUnitFile.getFullPath().toString()))); 
			}
		} else {
			try {
				String encoding = null;
				try {
					encoding = folder.getDefaultCharset(); // get folder encoding as file is not accessible
				}
				catch (CoreException ce) {
					// use no encoding
				}
				InputStream stream = new ByteArrayInputStream(encoding == null ? fSource.getBytes() : fSource.getBytes(encoding));
				createFile(folder, unit.getElementName(), stream, force);
				resultElements = new IModelElement[] {unit};
				if (!Util.isExcluded(unit)
						&& unit.getParent().exists()) {
					for (int i = 0; i < resultElements.length; i++) {
						delta.added(resultElements[i]);
					}
					addDelta(delta);
				}
			} catch (IOException e) {
				throw new ModelException(e, IModelStatusConstants.IO_EXCEPTION);
			}
		} 
		worked(1);
	} finally {
		done();
	}
}
/**
 * @see CreateElementInCUOperation#getSourceModule()
 */
protected ISourceModule getSourceModule() {
	return ((IScriptFolder)getParentElement()).getSourceModule(fName);
}
protected ISchedulingRule getSchedulingRule() {
	IResource resource  = getSourceModule().getResource();
	IWorkspace workspace = resource.getWorkspace();
	if (resource.exists()) {
		return workspace.getRuleFactory().modifyRule(resource);
	} else {
		return workspace.getRuleFactory().createRule(resource);
	}
}
/**
 * Possible failures: <ul>
 *  <li>NO_ELEMENTS_TO_PROCESS - the package fragment supplied to the operation is
 * 		<code>null</code>.
 *	<li>INVALID_NAME - the compilation unit name provided to the operation 
 * 		is <code>null</code> or has an invalid syntax
 *  <li>INVALID_CONTENTS - the source specified for the compiliation unit is null
 * </ul>
 */
public IModelStatus verify() {
	if (getParentElement() == null) {
		return new ModelStatus(IModelStatusConstants.NO_ELEMENTS_TO_PROCESS);
	}
	if (fSource == null) {
		return new ModelStatus(IModelStatusConstants.INVALID_CONTENTS);
	}
	return ModelStatus.VERIFIED_OK;
}
}


