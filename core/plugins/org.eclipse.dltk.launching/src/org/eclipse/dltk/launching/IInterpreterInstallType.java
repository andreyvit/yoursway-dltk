package org.eclipse.dltk.launching;

import java.io.File;

import org.eclipse.core.runtime.IStatus;

/**
 * Represents a particular type of interpreter for which there may be
 * any number of interpreter installations. An example of a interpreter type
 * is the standard TCL from ActiveState. 
 * <p>
 * This interface is intended to be implemented by clients that contribute
 * to the <code>"org.eclipse.dltk.launching.interpreterType"</code> extension point.
 * </p>
 * 
 * @see	IInterpreterInstall
 */
public interface IInterpreterInstallType {
	/**
	 * Creates a new instance of this interpreter Install type.
	 * The newly created IinterpreterInstall is managed by this IinterpreterInstallType.
	 * 
	 * @param	id	An id String that must be unique within this IinterpreterInstallType.
	 * 
	 * @return the newly created interpreter instance
	 * 
	 * @throws	IllegalArgumentException	If the id exists already.
	 */
	IInterpreterInstall createInterpreterInstall(String id);
	/**
	 * Finds the interpreter with the given id.
	 * 
	 * @param id the interpreter id
	 * @return a interpreter instance, or <code>null</code> if not found
	 */
	IInterpreterInstall findInterpreterInstall(String id);
	/**
	 * Finds the interpreter with the given name.
	 * 
	 * @param name the interpreter name
	 * @return a interpreter instance, or <code>null</code> if not found
	 *
	 */
	IInterpreterInstall findInterpreterInstallByName(String name);	
	
	/**
	 * Remove the interpreter associated with the given id from the set of interpreters managed by
	 * this interpreter type. Has no effect if a interpreter with the given id is not currently managed
	 * by this type.
	 * A interpreter install that is disposed may not be used anymore.
	 * 
	 * @param id the id of the interpreter to be disposed.
	 */
	void disposeInterpreterInstall(String id);
	/**
	 * Returns all interpreter instances managed by this interpreter type.
	 * 
	 * @return the list of interpreter instances managed by this interpreter type
	 */
	IInterpreterInstall[] getInterpreterInstalls();
	/**
	 * Returns the display name of this interpreter type.
	 * 
	 * @return the name of this IInterpreterInstallType
	 */ 
	String getName();
	
	/**
	 * Returns the globally unique id of this interpreter type.
	 * Clients are responsible for providing a unique id.
	 * 
	 * @return the id of this IInterpreterInstallType
	 */ 
	String getId();
	/**
	 * Validates the given location of a interpreter installation.
	 * <p>
	 * For example, an implementation might check whether the interpreter executable 
	 * is present.
	 * </p>
	 * 
	 * @param installLocation the root directory of a potential installation for
	 *   this type of interpreter
	 * @return a status object describing whether the install location is valid
	 */
	IStatus validateInstallLocation(File installLocation);
	
	/**
	 * Returns a collection of <code>LibraryLocation</code>s that represent the
	 * default system libraries of this interpreter install type, if a interpreter was installed
	 * at the given <code>installLocation</code>.
	 * The returned <code>LibraryLocation</code>s may not exist if the
	 * <code>installLocation</code> is not a valid install location.
	 * 
	 * @param installLocation home location
	 * @see LibraryLocation
	 * @see IInterpreterInstallType#validateInstallLocation(File)
	 * 
	 * @return default library locations based on the given <code>installLocation</code>.
	 *
	 */
	LibraryLocation[] getDefaultLibraryLocations(File installLocation);	
	
	/**
	 * Return string id of supported language.
	 * @returns id of supported language. 
	 */
	String getNatureId ();
}

