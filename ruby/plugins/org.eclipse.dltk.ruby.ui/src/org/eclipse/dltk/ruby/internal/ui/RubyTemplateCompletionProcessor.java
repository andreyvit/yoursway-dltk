package org.eclipse.dltk.ruby.internal.ui;

import org.eclipse.dltk.ruby.ui.RubyTemplateContext;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.swt.graphics.Image;

public class RubyTemplateCompletionProcessor extends TemplateCompletionProcessor {
	
	protected Template[] getTemplates(String contextTypeId) {
		return RubyUI.getDefault().getTemplateStore().getTemplates();
	}

	protected TemplateContextType getContextType(ITextViewer viewer,
			IRegion region) {		
		System.out.println("==== Region: " + region.getLength());
				
		return RubyUI.getDefault().getContextTypeRegistry().getContextType(
				RubyTemplateContext.TEMPLATE_ID);
	}

	protected Image getImage(Template template) {
		return null;
	}
	
//	protected void addTemplateProposals(ITextViewer viewer, int documentOffset,
//			List propList) {
//
//		String str = extractPrefix(viewer, documentOffset);
//
//		ICompletionProposal[] templateProposals = computeCompletionProposals(
//				viewer, documentOffset);
//
//		for (int j = 0; j < templateProposals.length; j++) {
//			if (templateProposals[j].getDisplayString().startsWith(str)) {
//				propList.add(templateProposals[j]);
//			}
//		}
//	}
}
