package org.eclipse.dltk.ruby.internal.ui;

import org.eclipse.dltk.ruby.ui.RubyTemplateContextType;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.swt.graphics.Image;

public class RubyTemplateCompletionProcessor extends
		ScriptTempalteCompletionProcessor {

	public RubyTemplateCompletionProcessor(
			ScriptContentAssistInvocationContext context) {
		super(context);
	}

	protected Template[] getTemplates(String contextTypeId) {
		return RubyUI.getDefault().getTemplateStore().getTemplates();
	}

	protected TemplateContextType getContextType(ITextViewer viewer,
			IRegion region) {
		System.out.println("==> Region: " + region.getLength());

		// Should create context here
		return RubyUI.getDefault().getContextTypeRegistry().getContextType(
				RubyTemplateContextType.CONTEXT_TYPE_ID);
	}

	protected Image getImage(Template template) {
		return null;
	}
}
