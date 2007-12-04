package org.eclipse.dltk.core;

/**
 * Provides an interface to select between multiple implementations of a
 * contributed extension point.
 */
public interface IDLTKContributionSelector {

	/**
	 * Select a contribution implementation
	 * 
	 * @param contributions
	 *            list of contribution implementations
	 * 
	 * @return contribution
	 */
	IDLTKContributedExtension select(IDLTKContributedExtension[] contributions);		
}
