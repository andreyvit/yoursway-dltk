package org.eclipse.dltk.core;

/**
 * Selects a contributed extension implementation based upon the
 * <code>priority</code> it was registered with when the plugin containing its
 * extension definition was loaded.
 */
public class DLTKPriorityContributionSelector implements
		IDLTKContributionSelector {

	/*
	 * @see org.eclipse.dltk.core.IDLTKPriorityContributionSelector#select(org.eclipse.dltk.core.IDLTKContributedExtension[])
	 */
	public IDLTKContributedExtension select(
			IDLTKContributedExtension[] contributions) {
		int maxPriority = Integer.MIN_VALUE;
		IDLTKContributedExtension selected = null;

		for (int i = 0; i < contributions.length; i++) {
			IDLTKContributedExtension contrib = contributions[i];

			/*
			 * if more then one contribution has the same priority, the first
			 * one found in the array wins
			 */
			if (contrib.getPriority() > maxPriority) {
				selected = contrib;
				maxPriority = contrib.getPriority();
			}
		}

		return selected;
	}
}
