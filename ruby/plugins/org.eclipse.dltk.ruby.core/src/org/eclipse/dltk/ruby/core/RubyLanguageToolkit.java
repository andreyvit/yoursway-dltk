package org.eclipse.dltk.ruby.core;

import java.text.MessageFormat;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.internal.core.util.Messages;

public class RubyLanguageToolkit implements IDLTKLanguageToolkit {
	private static RubyLanguageToolkit sToolkit = new RubyLanguageToolkit();

	public RubyLanguageToolkit() {

	}

	public IStatus validateSourceModule(String name) {
		if (name == null) {
			return new Status(IStatus.ERROR, RubyPlugin.PLUGIN_ID, -1,
					Messages.convention_unit_nullName, null);
		}

		if (!isRubyLikeFileName(name)) {
			return new Status(IStatus.ERROR, RubyPlugin.PLUGIN_ID, -1,
					MessageFormat.format(
							Messages.convention_unit_notScriptName,
							new String[] { getRubyExtension(), "Ruby" }), null);
		}

		return IModelStatus.VERIFIED_OK;
	}


	private String getRubyExtension() {
		return "rb";
	}

	private boolean isRubyLikeFileName(String name) {
		if (name.endsWith("." + getRubyExtension())) {
			return true;
		}
		return false;
	}

	public boolean languageSupportZIPBuildpath() {
		return false;
	}

	public boolean validateSourcePackage(IPath path) {
		return true;
	}

	public String getNatureID() {
		return RubyNature.NATURE_ID;
	}

	public IStatus validateSourceModule(IResource resource) {
		if (resource == null || resource.getLocation() == null)
			return new Status(IModelStatus.ERROR, RubyPlugin.PLUGIN_ID, 1, "Resource passed to validateSourceModule() is null", null);
		
//		String ext = resource.getLocation().getFileExtension();
//		if (ext == null || ext.length() == 0)
//			if (isRubyHeadered(resource.getLocation().toFile()) == IModelStatus.VERIFIED_OK) {
//				return IModelStatus.VERIFIED_OK;
//			}
		if ("rakefile".equalsIgnoreCase(resource.getLocation().lastSegment())) {
			return IModelStatus.VERIFIED_OK;
		}

		return validateSourceModule(resource.getName());
	}

	public IStatus validateSourceModule(IPath resource) {
		if( resource.toString().startsWith(IBuildpathEntry.BUILDIN_EXTERNAL_ENTRY.toString())) {
			return IModelStatus.VERIFIED_OK;
		}
		return validateSourceModule(resource.lastSegment());
	}

	public IStatus validateSourceModuleName(String str) {
		return validateSourceModule(str);
	}

	public String getDelimeterReplacerString() {
		return ".";
	}

	public static IDLTKLanguageToolkit getDefault() {
		return sToolkit;
	}

	public String[] getLanguageFileExtensions() {
		return new String[] { "rb" };
	}
		
	public String getLanguageName()
	{
		return "Ruby";
	}
}
