package org.eclipse.dltk.tcl.internal.ui.text;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.internal.core.packages.PackagesManager;
import org.eclipse.dltk.tcl.internal.core.packages.TclCheckBuilder;
import org.eclipse.dltk.ui.text.MarkerResolutionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.quickassist.IQuickAssistInvocationContext;
import org.eclipse.jface.text.quickassist.IQuickAssistProcessor;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.ui.texteditor.MarkerAnnotation;

public class TclCorrectionProcessor implements IQuickAssistProcessor {
	TclCorrectionAssistant fAssistant;

	public TclCorrectionProcessor(TclCorrectionAssistant tclCorrectionAssistant) {
		this.fAssistant = tclCorrectionAssistant;
	}

	public boolean canAssist(IQuickAssistInvocationContext invocationContext) {
		return true;
	}

	public boolean canFix(Annotation annotation) {
		return isQuickFixableType(annotation);
	}

	public ICompletionProposal[] computeQuickAssistProposals(
			IQuickAssistInvocationContext invocationContext) {
		Annotation[] annotations = fAssistant.getAnnotationsAtOffset();
		for (int i = 0; i < annotations.length; i++) {
			Annotation annotation = annotations[i];
			if (annotation instanceof MarkerAnnotation) {
				MarkerAnnotation mAnnot = (MarkerAnnotation) annotation;
				IMarker marker = mAnnot.getMarker();
				if (isFixable(marker)) {
					String pkgName = marker.getAttribute(
							TclCheckBuilder.TCL_PROBLEM_REQUIRE, null);
					if (pkgName != null) {
						ScriptEditor editor = (ScriptEditor) this.fAssistant
								.getEditor();
						IModelElement element = editor.getInputModelElement();
						IScriptProject scriptProject = element
								.getScriptProject();
						MarkerResolutionProposal prop = new MarkerResolutionProposal(
								new TclRequirePackageMarkerResolution(pkgName,
										scriptProject), marker);
						return new ICompletionProposal[] { prop };
					}
				}
			}
		}
		return null;
	}

	public String getErrorMessage() {
		return null;
	}

	public static boolean isQuickFixableType(Annotation annotation) {
		if (annotation instanceof MarkerAnnotation) {
			MarkerAnnotation mAnnot = (MarkerAnnotation) annotation;
			IMarker marker = mAnnot.getMarker();
			return isFixable(marker);
		}
		return false;
	}

	public static boolean isFixable(IMarker marker) {
		String pkgName = marker.getAttribute(
				TclCheckBuilder.TCL_PROBLEM_REQUIRE, null);
		if (pkgName != null) {
			IResource resource = marker.getResource();
			IProject project = resource.getProject();
			IScriptProject scriptProject = DLTKCore.create(project);
			if (isFixable(pkgName, scriptProject)) {
				return true;
			}
			return false;
		}
		return false;
	}

	public static boolean isFixable(String pkgName, IScriptProject scriptProject) {
		IDLTKLanguageToolkit toolkit = null;
		try {
			toolkit = DLTKLanguageManager.getLanguageToolkit(scriptProject);
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		if (toolkit != null
				&& toolkit.getNatureId().equals(TclNature.NATURE_ID)) {
			IInterpreterInstall install = null;
			try {
				install = ScriptRuntime.getInterpreterInstall(scriptProject);
			} catch (CoreException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			}
			if (install != null) {
				PackagesManager manager = PackagesManager.getInstance();
				IPath[] paths = manager
						.getPathsForPackage(install, pkgName);
				if (paths != null && paths.length > 0) {
					return true;
				}
				Map dependencies = manager.getDependencies(pkgName, install);
				for (Iterator iterator = dependencies.keySet().iterator(); iterator
						.hasNext();) {
					String pkg = (String) iterator.next();
					IPath[] paths2 = manager
							.getPathsForPackage(install, pkg);
					if (paths2 != null && paths2.length > 0) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean hasCorrections(Annotation annotation) {
		if (annotation instanceof MarkerAnnotation) {
			MarkerAnnotation mAnnot = (MarkerAnnotation) annotation;
			IMarker marker = mAnnot.getMarker();
			return isFixable(marker);
		}
		return false;
	}
}
