package org.eclipse.dltk.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;

public class DLTKContentTypeManager {
	public static boolean isValidFileNameForContentType(
			IDLTKLanguageToolkit toolkit, IPath path) {
		if (isValidFileNameForContentType(toolkit, path.lastSegment())) {
			return true;
		}
		if (path.isAbsolute()) {
			File file = new File(path.toOSString());
			if (file.exists() && file.isFile()
					&& (file.getName().indexOf('.') == -1)) {
				IContentType[] derived = getDerivedContentTypes(toolkit
						.getLanguageContentType());
				// Look for derived for associated extensions.
				for (int i = 0; i < derived.length; i++) {
					IContentType type = derived[i];
					InputStream stream = null;
					try {
						stream = new FileInputStream(file);
						IContentDescription description = type
								.getDescriptionFor(stream,
										IContentDescription.ALL);
						if (description != null) {
							if (checkDescription(type, description)) {
								return true;
							}
						}
					} catch (IOException e) {
						if (DLTKCore.DEBUG) {
							e.printStackTrace();
						}
					} finally {
						closeSream(stream);
					}
				}
			}
		}
		return false;
	}

	private static void closeSream(InputStream stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			}
		}
	}

	public static boolean isValidFileNameForContentType(
			IDLTKLanguageToolkit toolkit, String name) {
		IContentType[] derived = getDerivedContentTypes(toolkit
				.getLanguageContentType());
		// Look for derived for associated extensions.
		for (int i = 0; i < derived.length; i++) {
			IContentType type = derived[i];
			if (type.isAssociatedWith(name)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValidResourceForContentType(
			IDLTKLanguageToolkit toolkit, IResource resource) {
		if (resource instanceof IFile) {
			// Custom filtering via language tookit
			IStatus status = toolkit.validateSourceModule(resource);
			if( status.getSeverity() != IStatus.OK) {
				return false;
			}
			IFile file = (IFile) resource;
			IContentType masterType = getMasterContentType(toolkit
					.getLanguageContentType());
			if (masterType == null) {
				return false;
			}
			try {
				IContentDescription descr = file.getContentDescription();
				if (descr != null) {
					if (descr.getContentType().isKindOf(masterType)) {
						return true;
					}
				}
			} catch (CoreException e1) {
				if (DLTKCore.DEBUG) {
					e1.printStackTrace();
				}
			}

			IContentType[] derived = getDerivedContentTypes(toolkit
					.getLanguageContentType());
			String name = resource.getName();
			// Look for derived for associated extensions.
			for (int i = 0; i < derived.length; i++) {
				IContentType type = derived[i];
				if (type.isAssociatedWith(name)) {
					return true;
				}
			}
			// Check resource contents if name without extension
			IPath path = file.getFullPath();
			if (path.getFileExtension() == null) {
				for (int i = 0; i < derived.length; i++) {
					IContentType type = derived[i];
					IContentDescription description;
					InputStream contents = null;
					try {
						contents = file.getContents();
						description = type.getDescriptionFor(contents,
								IContentDescription.ALL);
						if (description != null) {
							if (checkDescription(type, description)) {
								return true;
							}
						}
					} catch (IOException e) {
						if (DLTKCore.DEBUG) {
							e.printStackTrace();
						}
					} catch (CoreException e) {
						if (DLTKCore.DEBUG) {
							e.printStackTrace();
						}
					} finally {
						closeSream(contents);
					}
				}
			}
		}
		return false;
	}

	private static IContentType getMasterContentType(String languageContentType) {
		IContentTypeManager manager = Platform.getContentTypeManager();
		IContentType masterContentType = manager
				.getContentType(languageContentType);
		return masterContentType;
	}

	private static boolean checkDescription(IContentType type,
			IContentDescription description) {
		Object object = description
				.getProperty(ScriptContentDescriber.DLTK_VALID);
		if (object != null && ScriptContentDescriber.TRUE.equals(object)) {
			return description.getContentType().isKindOf(type);
		}
		return false;
	}

	private static IContentType[] getDerivedContentTypes(String name) {
		IContentTypeManager manager = Platform.getContentTypeManager();
		IContentType masterContentType = manager.getContentType(name);
		if (masterContentType == null) {
			return new IContentType[0];
		}
		IContentType[] types = manager.getAllContentTypes();
		Set derived = new HashSet();
		for (int i = 0; i < types.length; i++) {
			if (types[i].isKindOf(masterContentType)) {
				derived.add(types[i]);
			}
		}
		return (IContentType[]) derived
				.toArray(new IContentType[derived.size()]);
	}
}
