package org.eclipse.dltk.ui;

import java.net.URL;
import java.util.HashMap;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.ImageData;
import org.osgi.framework.Bundle;

public class ImageUtil {
	public static final String T_OBJ = "obj16"; //$NON-NLS-1$
	public static final String T_OVR = "ovr16"; //$NON-NLS-1$
	public static final String T_WIZBAN = "wizban"; //$NON-NLS-1$
	public static final String T_ELCL = "elcl16"; //$NON-NLS-1$
	public static final String T_DLCL = "dlcl16"; //$NON-NLS-1$
	public static final String T_ETOOL = "etool16"; //$NON-NLS-1$
	public static final String T_EVIEW = "eview16"; //$NON-NLS-1$
	
	private static final class CachedImageDescriptor extends ImageDescriptor {
		private ImageDescriptor fDescriptor;
		private ImageData fData;

		public CachedImageDescriptor(ImageDescriptor descriptor) {
			fDescriptor = descriptor;
		}

		public ImageData getImageData() {
			if (fData == null) {
				fData = fDescriptor.getImageData();
			}
			return fData;
		}
	}
	
	private ImageRegistry fgImageRegistry = null;
	private HashMap fgAvoidSWTErrorMap = null;
	
	private Bundle bundle;
	private IPath iconsPath;
	
	public ImageUtil(Bundle bundle, IPath iconsPath){
		this.bundle = bundle;
		this.iconsPath = iconsPath;
	}

	public  void setImageDescriptors(IAction action, String type,
			String relPath) {
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

	/*
	 * Creates an image descriptor for the given prefix and name in the DLTK UI
	 * bundle. The path can contain variables like $NL$. If no image could be
	 * found, <code>useMissingImageDescriptor</code> decides if either the
	 * 'missing image descriptor' is returned or <code>null</code>. or <code>null</code>.
	 */
	private ImageDescriptor create(String prefix, String name,
			boolean useMissingImageDescriptor) {
		IPath path = iconsPath.append(prefix).append(name);		
		return createImageDescriptor(bundle, path, useMissingImageDescriptor);
	}

	private ImageDescriptor create(String prefix, String name) {
		return create(prefix, name, true);
	}

	public  ImageDescriptor createImageDescriptor(Bundle bundle,
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

	// UnManaged
	public  ImageDescriptor createUnManaged(String prefix, String name) {
		return create(prefix, name, true);
	}

	public ImageDescriptor createUnManagedCached(String prefix,
			String name) {
		return new CachedImageDescriptor(createUnManaged(prefix, name));
	}

	// Managed
	public ImageDescriptor createManaged(String prefix, String name,
			String key) {
		try {
			ImageDescriptor result = create(prefix, name, true);
			if (fgAvoidSWTErrorMap == null) {
				fgAvoidSWTErrorMap = new HashMap();
			}
			fgAvoidSWTErrorMap.put(key, result);
			if (fgImageRegistry != null) {
				System.err.println("Image registry already defined");
			}
			return result;
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		return null;
	}
}

