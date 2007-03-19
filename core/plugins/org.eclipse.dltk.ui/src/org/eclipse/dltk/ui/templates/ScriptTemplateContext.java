package org.eclipse.dltk.ui.templates;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.templates.DocumentTemplateContext;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateBuffer;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateException;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

public class ScriptTemplateContext extends DocumentTemplateContext {
	private ISourceModule sourceModule;

	protected ScriptTemplateContext(TemplateContextType type,
			IDocument document, int completionOffset, int completionLength,
			ISourceModule sourceModule) {
		super(type, document, completionOffset, completionLength);

		if (sourceModule == null) {
			throw new IllegalArgumentException();
		}

		this.sourceModule = sourceModule;
	}

	public final ISourceModule getSourceModule() {
		return sourceModule;
	}

	protected static String calculateIndent(IDocument document, int offset) {
		String indent = "";

		try {
			IRegion line = document.getLineInformationOfOffset(offset);

			String s = document.get(line.getOffset(), line.getLength());

			if (s.length() > 0) {
				int index = 0;
				while (!Character.isJavaIdentifierPart(s.charAt(index))) {
					++index;
				}
				indent = s.substring(0, index);
			}

		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		return indent;
	}

	public TemplateBuffer evaluate(Template template)
			throws BadLocationException, TemplateException {
		if (!canEvaluate(template)) {
			return null;
		}

		String indentTo = calculateIndent(getDocument(), getStart());

		String delimeter = TextUtilities.getDefaultLineDelimiter(getDocument());
		String[] lines = template.getPattern().split("\n");

		if (lines.length > 1 && indentTo != null && indentTo.length() > 0) {
			StringBuffer buffer = new StringBuffer(lines[0]);

			// Except first line
			for (int i = 1; i < lines.length; i++) {
				buffer.append(delimeter);
				buffer.append(indentTo);
				buffer.append(lines[i]);
			}

			template = new Template(template.getName(), template
					.getDescription(), template.getContextTypeId(), buffer
					.toString(), template.isAutoInsertable());
		}

		return super.evaluate(template);
	}
}
