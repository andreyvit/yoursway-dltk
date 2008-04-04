package org.eclipse.dltk.ui;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;

public class PluginImagesHelper {
	public static final String T_OBJ = "obj16"; //$NON-NLS-1$
	public static final String T_OVR = "ovr16"; //$NON-NLS-1$
	public static final String T_WIZBAN = "wizban"; //$NON-NLS-1$
	public static final String T_ELCL = "elcl16"; //$NON-NLS-1$
	public static final String T_DLCL = "dlcl16"; //$NON-NLS-1$
	public static final String T_ETOOL = "etool16"; //$NON-NLS-1$

	private Bundle bundle;
	private IPath iconsPath;
	private ImageRegistry imageRegistry;
	private HashMap avoidSWTErrorMap;

	public PluginImagesHelper(Bundle bundle, IPath iconsPath) {
		this.bundle = bundle;
		this.iconsPath = iconsPath;
	}

	private void setImageDescriptors(IAction action, String type, String relPath) {
		ImageDescriptor id = create("d" + type, relPath, false); //$NON-NLS-1$
		if (id != null)
			action.setDisabledImageDescriptor(id);
		/*
		 * id= create("c" + type, relPath, false); //$NON-NLS-1$ if (id != null)
		 * action.setHoverImageDescriptor(id);
		 */
		ImageDescriptor descriptor = create("e" + type, relPath); //$NON-NLS-1$
		action.setHoverImageDescriptor(descriptor);
		action.setImageDescriptor(descriptor);
	}

	private ImageDescriptor create(String prefix, String name,
			boolean useMissingImageDescriptor) {
		IPath path = iconsPath.append(prefix).append(name);
		return createImageDescriptor(bundle, path, useMissingImageDescriptor);
	}

	private ImageDescriptor create(String prefix, String name) {
		return create(prefix, name, true);
	}

	/*
	 * Creates an image descriptor for the given path in a bundle. The path can
	 * contain variables like $NL$. If no image could be found, <code>useMissingImageDescriptor</code>
	 * decides if either the 'missing image descriptor' is returned or <code>null</code>.
	 * Added for 3.1.1.
	 */
	public static ImageDescriptor createImageDescriptor(Bundle bundle,
			IPath path, boolean useMissingImageDescriptor) {
		URL url = FileLocator.find(bundle, path, null);
		if (url != null) {
			return ImageDescriptor.createFromURL(url);
		}
		if (useMissingImageDescriptor) {
			return ImageDescriptor.getMissingImageDescriptor();
		}
		return null;
	}

	// Public methods
	
	// Created ImageDescriptor would be only created for you
	public ImageDescriptor createUnManaged(String prefix, String name) {
		return create(prefix, name, true);
	}

	// Created ImageDescriptor would be placed in ImageRegistry and accessed by key
	public ImageDescriptor createManaged(String prefix, String name, String key) {
		try {
			ImageDescriptor result = create(prefix, name, true);
			if (avoidSWTErrorMap == null) {
				avoidSWTErrorMap = new HashMap();
			}
			avoidSWTErrorMap.put(key, result);
			if (imageRegistry != null) {
				throw new Error(Messages.PluginImagesHelper_imageRegistryAlreadyDefined);
			}
			return result;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	public ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
			for (Iterator iter = avoidSWTErrorMap.keySet().iterator(); iter
					.hasNext();) {
				String key = (String) iter.next();
				imageRegistry.put(key, (ImageDescriptor) avoidSWTErrorMap
						.get(key));
			}
			avoidSWTErrorMap = null;
		}
		return imageRegistry;
	}

	/**
	 * Returns the image managed under the given key in this registry.
	 * 
	 * @param key
	 *            the image's key
	 * @return the image managed under the given key
	 */
	public Image get(String key) {
		return getImageRegistry().get(key);
	}

	/**
	 * Returns the image descriptor for the given key in this registry. Might be
	 * called in a non-UI thread.
	 * 
	 * @param key
	 *            the image's key
	 * @return the image descriptor for the given key
	 */
	public ImageDescriptor getDescriptor(String key) {
		if (imageRegistry == null) {
			return (ImageDescriptor) avoidSWTErrorMap.get(key);
		}
		return getImageRegistry().getDescriptor(key);
	}

	/**
	 * Sets the three image descriptors for enabled, disabled, and hovered to an
	 * action. The actions are retrieved from the *tool16 folders.
	 * 
	 * @param action
	 *            the action
	 * @param iconName
	 *            the icon name
	 */
	public void setToolImageDescriptors(IAction action, String iconName) {
		setImageDescriptors(action, "tool16", iconName); //$NON-NLS-1$
	}

	/**
	 * Sets the three image descriptors for enabled, disabled, and hovered to an
	 * action. The actions are retrieved from the *lcl16 folders.
	 * 
	 * @param action
	 *            the action
	 * @param iconName
	 *            the icon name
	 */
	public void setLocalImageDescriptors(IAction action, String iconName) {
		setImageDescriptors(action, "lcl16", iconName); //$NON-NLS-1$
	}
}
