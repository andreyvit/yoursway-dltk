package org.eclipse.dltk.tcl.internal.ui.text;


import org.eclipse.core.resources.IMarker;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
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
				String pkgName = marker.getAttribute("tcl.problem.require",
						null);
				if (pkgName != null) {
					ScriptEditor editor = (ScriptEditor) this.fAssistant
							.getEditor();
					IModelElement element = editor.getInputModelElement();
					IScriptProject scriptProject = element.getScriptProject();
					MarkerResolutionProposal prop = new MarkerResolutionProposal(
							new RequirePackageMarkerResulution(pkgName,
									scriptProject), marker);
					return new ICompletionProposal[] { prop };
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
		String pkgName = marker.getAttribute("tcl.problem.require", null);
		if (pkgName != null) {
			return true;
		}
		return false;
	}

	public static boolean hasCorrections(Annotation annotation) {
		if (annotation instanceof MarkerAnnotation) {
			MarkerAnnotation mAnnot = (MarkerAnnotation) annotation;
			IMarker marker = mAnnot.getMarker();
			String pkgName = marker.getAttribute("tcl.problem.require",
					null);
			if( pkgName != null ) {
				return true;
			}
		}
		return false;
	}
}
