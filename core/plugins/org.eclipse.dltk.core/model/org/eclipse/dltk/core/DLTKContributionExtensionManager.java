package org.eclipse.dltk.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * Abstract base class that can be used to manage extension point contributions
 * that may have more then one implementation.
 * 
 * <p>
 * Examples:
 * <ul>
 * <li>Source Parsers
 * <li>Debugging Engines
 * </ul>
 * </p>
 */
public abstract class DLTKContributionExtensionManager {
	private static final String NATURE_ID = "natureId";

	private static final String SELECTOR_TAG = "selector";
	private static final String CLASS_TAG = "class";

	private Map natureToContribMap = new HashMap();
	private Map natureToSelectorMap = new HashMap();

	public static class ContributionElement {
		private static final String PRIORITY_ATTR = "priority";
		private IConfigurationElement configurationElement;

		public ContributionElement(IConfigurationElement config) {
			this.configurationElement = config;
		}

		public IConfigurationElement getConfigurationElement() {
			return configurationElement;
		}

		public void setConfigurationElement(
				IConfigurationElement configurationElement) {
			this.configurationElement = configurationElement;
		}

		public int getPriority() {
			try {
				return new Integer(this.configurationElement
						.getAttribute(PRIORITY_ATTR)).intValue();
			} catch (NumberFormatException e) {
				return 0;
			}
		}

		public Object createExecutableClass() throws CoreException {
			return this.configurationElement
					.createExecutableExtension(CLASS_TAG);
		}
	}

	protected DLTKContributionExtensionManager() {
		loadExtensionPoints();
	}

	/**
	 * Get the contributions registered for the given nature id
	 * 
	 * @param natureId
	 *            nature id
	 * 
	 * @return list of avaiable contributions or
	 *         <code>Collections.EMPTY_LIST</code> if no contributions have
	 *         been registered by the plugin
	 */
	protected List getContributions(String natureId) {
		if (!hasContributions(natureId)) {
			return Collections.EMPTY_LIST;
		}

		return (List) natureToContribMap.get(natureId);
	}

	/**
	 * Checks if any contributions have been created for the given nature id
	 * 
	 * @param natureId
	 *            nature id
	 * 
	 * @return true if there are contributions, false otherwise
	 */
	protected boolean hasContributions(String natureId) {
		if (!natureToContribMap.containsKey(natureId)) {
			return false;
		}

		List list = (List) natureToContribMap.get(natureId);
		return !list.isEmpty();
	}

	/**
	 * Returns the name of the contribution xml element
	 */
	protected abstract String getContributionElementName();

	/**
	 * Returns the name of the extension point to load
	 */
	protected abstract String getExtensionPoint();

	/**
	 * Configure the object being contributed with any configuration data it may
	 * need.
	 * 
	 * <p>
	 * Sub-classes should override this method if the input object was not
	 * configured using
	 * {@link org.eclipse.core.runtime.IExecutableExtension#setInitializationData(IConfigurationElement, String, Object)}
	 * </p>
	 */
	protected Object configureContribution(Object object) {
		return object;
	}

	private void addContribution(String natureId, IConfigurationElement element) {
		List list = (List) natureToContribMap.get(natureId);
		if (list == null) {
			list = new ArrayList();
			natureToContribMap.put(natureId, list);
		}
		list.add(new ContributionElement(element));

	}

	private void addSelector(String natureId, IConfigurationElement element) {
		Object object;
		try {
			object = element.createExecutableExtension(CLASS_TAG);
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
			return;
		}
		natureToSelectorMap.put(natureId, object);
	}

	private void loadChildren(String natureId,
			IConfigurationElement[] innerElements) {
		for (int j = 0; j < innerElements.length; j++) {
			IConfigurationElement innerElement = innerElements[j];
			String name = innerElement.getName();

			if (name.equals(getContributionElementName())) {
				addContribution(natureId, innerElement);
			} else if (name.equals(SELECTOR_TAG)) {
				addSelector(natureId, innerElement);
			}
		}
	}

	private void loadExtensionPoints() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtension[] extensions = registry.getExtensionPoint(
				getExtensionPoint()).getExtensions();

		for (int i = 0; i < extensions.length; i++) {
			IExtension extension = extensions[i];
			IConfigurationElement[] elements = extension
					.getConfigurationElements();

			IConfigurationElement main = elements[0];

			String natureId = main.getAttribute(NATURE_ID);
			IConfigurationElement[] innerElements = main.getChildren();

			loadChildren(natureId, innerElements);
		}
	}

}
