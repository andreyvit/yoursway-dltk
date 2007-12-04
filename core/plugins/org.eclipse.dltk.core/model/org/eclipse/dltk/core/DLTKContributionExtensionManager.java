package org.eclipse.dltk.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
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
	public static final String CLASS_TAG = "class";

	private IDLTKContributionSelector defaultSelector;
	
	private Map natureToContribMap = new HashMap();
	private Map natureToSelectorMap = new HashMap();

	protected DLTKContributionExtensionManager() {
		this.defaultSelector = new DLTKPriorityContributionSelector();
		
		loadExtensionPoints();		
	}

	public IDLTKContributedExtension[] getContributions(String natureId) {
		List contributions = getContributionsByNature(natureId);
		return (IDLTKContributedExtension[]) contributions
				.toArray(new IDLTKContributedExtension[contributions.size()]);
	}

	public IDLTKContributedExtension getSelectedContribution(String natureId) {
		IDLTKContributedExtension[] contributions = getContributions(natureId);
		
		if (contributions.length > 0) {
			
			IDLTKContributionSelector selector = getSelector(natureId);
			if (selector == null) {
				selector = defaultSelector;
			}

			return selector.select(contributions);
		}
	
		return null;
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
	protected final List getContributionsByNature(String natureId) {
		if (!hasContributions(natureId)) {
			return Collections.EMPTY_LIST;
		}

		List contributions = (List) natureToContribMap.get(natureId);
	
		Collections.sort(contributions, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				if (arg0 instanceof IDLTKContributedExtension
						&& arg1 instanceof IDLTKContributedExtension) {
					IDLTKContributedExtension e1 = (IDLTKContributedExtension) arg0;
					IDLTKContributedExtension e2 = (IDLTKContributedExtension) arg1;
					if (e1.getPriority() == e2.getPriority()) {
						return 0;
					}
					if (e1.getPriority() < e2.getPriority()) {
						return -1;
					}
					return 1;
				}
				return 0;
			}
		});

		return contributions;
	}

	protected final IDLTKContributionSelector getSelector(String natureId) {
		return (IDLTKContributionSelector) natureToSelectorMap.get(natureId);
	}

	/**
	 * Checks if any contributions have been created for the given nature id
	 * 
	 * @param natureId
	 *            nature id
	 * 
	 * @return true if there are contributions, false otherwise
	 */
	protected final boolean hasContributions(String natureId) {
		if (natureToContribMap.containsKey(natureId)) {
			List list = (List) natureToContribMap.get(natureId);
			return !list.isEmpty();
		}

		return false;
	}

	/**
	 * Has a selector been configured for the contribution
	 * 
	 * @param natureId
	 *            nature id
	 * 
	 * @return true if a selector has been configured, false otherwise
	 */
	public final boolean hasSelector(String natureId) {
		return natureToSelectorMap.containsKey(natureId);
	}

	/**
	 * Returns a contributed extension implementation based on id.
	 * 
	 * @param id
	 *            contribution id
	 * 
	 * @return contribution implementation
	 */
	public final IDLTKContributedExtension getContributionById(String id) {
		Iterator keys = natureToContribMap.keySet().iterator();
		while (keys.hasNext()) {
			List list = (List) natureToContribMap.get(keys.next());

			for (Iterator iter = list.iterator(); iter.hasNext();) {
				IDLTKContributedExtension contrib = (IDLTKContributedExtension) iter
						.next();
				if (contrib.getId().equals(id)) {
					return contrib;
				}
			}
		}

		return null;
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
	 * Checks if the passed object is valid for the given contribution.
	 * 
	 * <p>
	 * The passed object will have been created via a call to
	 * {@link IConfigurationElement#createExecutableExtension(String)}
	 * </p>
	 * 
	 * @param object
	 *            contribution implementation class
	 * 
	 * @return true if valid, false otherwise
	 */
	protected abstract boolean isValidContribution(Object object);

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
	protected Object configureContribution(Object object,
			IConfigurationElement config) {
		return object;
	}

	protected final void addContribution(String natureId,
			IConfigurationElement element) {
		try {
			Object object = element.createExecutableExtension(CLASS_TAG);

			if (isValidContribution(object)) {
				/*
				 * handle the case where the contribution is not the object that
				 * was just created.
				 */
				Object contrib = configureContribution(object, element);

				List list = (List) natureToContribMap.get(natureId);
				if (list == null) {
					list = new ArrayList();
					natureToContribMap.put(natureId, list);
				}

				list.add(contrib);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}

	}

	protected final void addSelector(String natureId,
			IConfigurationElement element) {
		try {
			Object object = element.createExecutableExtension(CLASS_TAG);
			if (object instanceof IDLTKContributionSelector) {
				// XXX: what if multiple extensions define a selector
				natureToSelectorMap.put(natureId, object);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
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
