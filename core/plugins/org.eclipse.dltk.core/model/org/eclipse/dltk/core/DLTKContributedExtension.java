package org.eclipse.dltk.core;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;

public abstract class DLTKContributedExtension implements
		IDLTKContributedExtension, IExecutableExtension {

	private String description;
	private String id;
	private String name;
	private String natureId;
	private String preferencePageId;
	private int priority;
	
	/*
	 * @see org.eclipse.dltk.ast.parser.IDLTKContributedExtension#getDescription()
	 */
	public String getDescription() {
		return description;
	}

	/*
	 * @see org.eclipse.dltk.ast.parser.IDLTKContributedExtension#getId()
	 */
	public String getId() {
		return id;
	}

	/*
	 * @see org.eclipse.dltk.ast.parser.IDLTKContributedExtension#getName()
	 */
	public String getName() {
		return name;
	}

	/*
	 * @see org.eclipse.dltk.ast.parser.IDLTKContributedExtension#getNatureId()
	 */
	public String getNatureId() {
		return natureId;
	}

	/*
	 * @see org.eclipse.dltk.ast.parser.IDLTKContributedExtension#getPreferencePageId()
	 */
	public String getPreferencePageId() {
		return preferencePageId;
	}

	/*
	 * @see org.eclipse.dltk.ast.parser.IDLTKContributedExtension#getPriority()
	 */
	public int getPriority() {
		return priority;
	}

	/*
	 * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement,
	 *      java.lang.String, java.lang.Object)
	 */
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) {
		id = config.getAttribute(ID);
		name = config.getAttribute(NAME);
		description = config.getAttribute(DESCRIPTION);
		priority = Integer.parseInt(config.getAttribute(PRIORITY));
		preferencePageId = config.getAttribute(PREF_PAGE_ID);

		// get the natureId from the parent
		natureId = ((IConfigurationElement) config.getParent())
				.getAttribute(NATURE_ID);
	}
}
