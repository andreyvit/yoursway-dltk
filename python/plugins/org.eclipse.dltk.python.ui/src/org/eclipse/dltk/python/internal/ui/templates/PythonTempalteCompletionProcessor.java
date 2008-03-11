package org.eclipse.dltk.python.internal.ui.templates;

import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.templates.ScriptTempalteCompletionProcessor;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.swt.graphics.Image;

public class PythonTempalteCompletionProcessor extends
		ScriptTempalteCompletionProcessor {

	public PythonTempalteCompletionProcessor(ScriptContentAssistInvocationContext context) {
		super(context);
	}

	protected TemplateContextType getContextType(ITextViewer viewer, IRegion region) {
		// TODO extend the functionality
		return PythonTemplateAccess.
				getInstance().
				getContextTypeRegistry().
				getContextType(PythonTemplateContextType.CONTEXT_TYPE_ID);
	}

	protected Image getImage(Template template) {
		return DLTKPluginImages.get(DLTKPluginImages.IMG_OBJS_TEMPLATE);
	}

	protected Template[] getTemplates(String contextTypeId) {
		if (contextTypeId.equals(PythonTemplateContextType.CONTEXT_TYPE_ID)) {
			return PythonTemplateAccess.getInstance().getTemplateStore().getTemplates();
		}
		else return new Template[0];
	}

}