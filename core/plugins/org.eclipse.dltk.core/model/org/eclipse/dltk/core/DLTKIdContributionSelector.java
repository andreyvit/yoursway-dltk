package org.eclipse.dltk.core;

import org.eclipse.core.resources.IProject;

/**
 * Selects a contributed extension implementation based upon the <code>id</code>
 * it was registered with when the plugin containing its extension definition
 * was loaded.
 * 
 * <p>
 * If a saved contribution id can not be found, the implementation will fall
 * back to selecting based upon priority.
 * </p>
 */
public abstract class DLTKIdContributionSelector extends
		DLTKPriorityContributionSelector {

	/**
	 * Selects the contribution implementation currently in use as defined by
	 * the user's preferences.
	 * 
	 * @param contributions
	 *            list of contribution implementations
	 * 
	 * @return currently selected implementation
	 */
	public IDLTKContributedExtension select(
			IDLTKContributedExtension[] contributions, IProject project) {
		PreferencesLookupDelegate delegate = new PreferencesLookupDelegate(
				project);
		String id = getSavedContributionId(delegate);

		if (id != null) {
			for (int i = 0; i < contributions.length; i++) {
				IDLTKContributedExtension contrib = contributions[i];
				if (contrib.getId().equals(id)) {
					return contrib;
				}
			}
		}

		/*
		 * if a contribution was not found that matched the saved contribution
		 * id, pick one based upon its priority
		 */
		return super.select(contributions, project);
	}

	/**
	 * Returns the id of the currently selected contribution.
	 * 
	 * <p>
	 * Sub-classes should use the preference lookup delegate to retrieve the
	 * saved value.
	 * </p>
	 */
	protected abstract String getSavedContributionId(
			PreferencesLookupDelegate delegate);

}
