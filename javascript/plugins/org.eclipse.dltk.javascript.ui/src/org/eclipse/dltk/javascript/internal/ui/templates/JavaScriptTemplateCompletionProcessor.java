package org.eclipse.dltk.javascript.internal.ui.templates;

import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.templates.ScriptTempalteCompletionProcessor;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.swt.graphics.Image;

public class JavaScriptTemplateCompletionProcessor extends
		ScriptTempalteCompletionProcessor {

	public JavaScriptTemplateCompletionProcessor(
			ScriptContentAssistInvocationContext context) {
		super(context);
	}

	protected Template[] getTemplates(String contextTypeId) {
		if (contextTypeId
				.equals(JavaScriptUniversalTemplateContextType.CONTEXT_TYPE_ID)) {
			return JavaScriptTemplateAccess.getInstance().getTemplateStore()
					.getTemplates();
		}

		return new Template[0];
	}

	protected TemplateContextType getContextType(ITextViewer viewer,
			IRegion region) {

		// Simple checking if completion string contains '.'
		// TODO: make smarter
		IDocument doc = viewer.getDocument();
		try {
			IRegion line = doc.getLineInformationOfOffset(region.getOffset()
					+ region.getLength());
			int len = region.getOffset() + region.getLength()
					- line.getOffset();
			String s = doc.get(line.getOffset(), len);

			int spaceIndex = s.lastIndexOf(' ');
			if (spaceIndex != -1) {
				s = s.substring(spaceIndex);
			}

			if (s.indexOf('.') == -1 && s.indexOf(':') == -1
					&& s.indexOf('@') == -1 && s.indexOf('$') == -1) {
				return JavaScriptTemplateAccess
						.getInstance()
						.getContextTypeRegistry()
						.getContextType(
								JavaScriptUniversalTemplateContextType.CONTEXT_TYPE_ID);
			}

		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		return null;
	}

	protected Image getImage(Template template) {
		return DLTKPluginImages.get(DLTKPluginImages.IMG_OBJS_TEMPLATE);
	}
}
