package org.eclipse.dltk.ruby.internal.ui.templates;

import org.eclipse.dltk.ruby.internal.ui.RubyTemplateAccess;
import org.eclipse.dltk.ui.templates.ScriptTempalteCompletionProcessor;
import org.eclipse.dltk.ui.templates.ScriptTemplateContext;
import org.eclipse.dltk.ui.templates.ScriptTemplateContextType;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.templates.DocumentTemplateContext;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.swt.graphics.Image;

public class RubyTemplateCompletionProcessor extends
		ScriptTempalteCompletionProcessor {

	public RubyTemplateCompletionProcessor(
			ScriptContentAssistInvocationContext context) {
		super(context);
	}

	protected Template[] getTemplates(String contextTypeId) {
		if (contextTypeId
				.equals(RubyUniversalTemplateContextType.CONTEXT_TYPE_ID)) {
			return RubyTemplateAccess.getInstance().getTemplateStore()
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

			if (s.indexOf('.') == -1) {
				return RubyTemplateAccess
						.getInstance()
						.getContextTypeRegistry()
						.getContextType(
								RubyUniversalTemplateContextType.CONTEXT_TYPE_ID);
			}

		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		return null;
	}

	protected Image getImage(Template template) {
		return null;
	}

	protected TemplateContext createContext(ITextViewer viewer, IRegion region) {
		TemplateContextType contextType = getContextType(viewer, region);
		if (contextType instanceof ScriptTemplateContextType) {
			IDocument document = viewer.getDocument();
																	
			
			return ((ScriptTemplateContextType) contextType).createContext(
					document, region.getOffset(), region.getLength(),
					getContext().getSourceModule());
		} else {
			return super.createContext(viewer, region);
		}
	}

	// protected TemplateContext createContext(final ITextViewer viewer, final
	// IRegion region) {
	// TemplateContextType contextType = getContextType(viewer, region);
	// if (contextType != null) {
	// IDocument document= viewer.getDocument();
	// String indent = "";
	// PySelection selection = new PySelection(document,
	// viewer.getTextWidget().getSelection().x);
	// indent = selection.getIndentationFromLine();
	//            
	// final String indentTo = indent;
	//            
	// return new DocumentTemplateContext(contextType, document,
	// region.getOffset(), region.getLength()){
	// public TemplateBuffer evaluate(Template template) throws
	// BadLocationException, TemplateException {
	// if (!canEvaluate(template))
	// return null;
	//
	// String pattern = template.getPattern();
	// List splitted = StringUtils.splitInLines(pattern);
	// if(splitted.size() > 1 && indentTo != null && indentTo.length() > 0){
	// StringBuffer buffer = new StringBuffer(splitted.get(0));
	// for (int i=1; i<splitted.size();i++) { //we don't want to get the first
	// line
	// buffer.append(indentTo);
	// buffer.append(splitted.get(i));
	// }
	// //just to change the pattern...
	// template = new Template(template.getName(), template.getDescription(),
	// template.getContextTypeId(), buffer.toString(),
	// template.isAutoInsertable());
	// }
	//                    
	// TemplateTranslator translator= new TemplateTranslator();
	// TemplateBuffer templateBuffer= translator.translate(template);
	//
	// getContextType().resolve(templateBuffer, this);
	//
	// return templateBuffer;
	// }
	// };
	// }
	// return null;
	// }
}
