package org.eclipse.dltk.core.builder;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.core.IDLTKProject;

/**
 * Interace called from script builder to builde selected resource.
 * 
 * @author Haiodo
 * 
 */
public interface IScriptBuilder {
	/**
	 * Called for earch resource required to builde.
	 * Only resorces with specified project nature are here.
	 * @return
	 */
	IStatus buildResources( IDLTKProject project, List resources, IProgressMonitor monitor );
	
	/**
	 * Called for earch resource required to builde.
	 * Only resorces with specified project nature are here.
	 * @return
	 */
	IStatus buildModelElements( IDLTKProject project, List elements, IProgressMonitor monitor );

	/**
	 * Return all dependencies for selected resource. Should also return all
	 * dependencies of returned elements.
	 * 
	 * For example if c depends on b and b depends on a, and we ask for
	 * dependenies or a, then b and c should be returned.
	 * 
	 * Resources should be checked for type. Because different kind of resource could be here.
	 * 
	 * @param resource
	 * @return null, if no dependencies are found. Should not return elements
	 *         from resources list.
	 */
	List getDependencies(IDLTKProject project, List resources);
}
