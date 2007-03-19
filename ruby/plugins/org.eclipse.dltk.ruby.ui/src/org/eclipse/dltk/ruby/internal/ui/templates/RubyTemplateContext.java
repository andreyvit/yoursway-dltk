package org.eclipse.dltk.ruby.internal.ui.templates;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.templates.ScriptTemplateContext;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateBuffer;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.jface.text.templates.TemplateTranslator;

public class RubyTemplateContext extends ScriptTemplateContext {
	
	public RubyTemplateContext(TemplateContextType type, IDocument document,
			int completionOffset, int completionLength,
			ISourceModule sourceModule) {
		super(type, document, completionOffset, completionLength, sourceModule);
	}

	public TemplateBuffer evaluate(Template template)
			throws BadLocationException, TemplateException {
		if (!canEvaluate(template)) {
			// TODO: localize
			throw new TemplateException(
					"RubyTemplateMessages.Context_error_cannot_evaluate");
		}

		TemplateTranslator translator = new TemplateTranslator();

		// {
		// protected TemplateVariable createVariable(String type, String name,
		// int[] offsets) {
		// // XXX
		// // return new MultiVariable(type, name, offsets);
		// return null;
		// }
		// };
		TemplateBuffer buffer = translator.translate(template);

		getContextType().resolve(buffer, this);

		// IPreferenceStore prefs = RubyUI.getDefault().getPreferenceStore();

		// boolean useCodeFormatter = prefs
		// .getBoolean(PreferenceConstants.TEMPLATES_USE_CODEFORMATTER);
		//
		// IRubyProject project = getRubyScript() != null ? getRubyScript()
		// .getRubyProject() : null;
		// RubyFormatter formatter = new RubyFormatter(TextUtilities
		// .getDefaultLineDelimiter(getDocument()), getIndentation(),
		// useCodeFormatter, project);
		// formatter.format(buffer, this);

		return buffer;
	}

	public boolean canEvaluate(Template template) {
		String key = getKey();
		return template.matches(key, getContextType().getId())
				&& key.length() != 0
				&& template.getName().toLowerCase().startsWith(
						key.toLowerCase());
	}

	public String getKey() {
		if (getCompletionLength() == 0) {
			return super.getKey();
		}

		try {
			IDocument document = getDocument();

			int start = getStart();
			int end = getCompletionOffset();
			return start <= end ? document.get(start, end - start) : ""; //$NON-NLS-1$

		} catch (BadLocationException e) {
			return super.getKey();
		}
	}

	public int getEnd() {
		if (getCompletionLength() == 0) {
			return super.getEnd();
		}

		try {
			IDocument document = getDocument();

			int start = getCompletionOffset();
			int end = getCompletionOffset() + getCompletionLength();

			while (start != end
					&& Character.isWhitespace(document.getChar(end - 1)))
				end--;

			return end;

		} catch (BadLocationException e) {
			return super.getEnd();
		}
	}

	public int getStart() {
		try {
			IDocument document = getDocument();

			int start = getCompletionOffset();
			int end = getCompletionOffset() + getCompletionLength();

			while (start != 0
					&& Character.isUnicodeIdentifierPart(document
							.getChar(start - 1)))
				start--;

			while (start != end
					&& Character.isWhitespace(document.getChar(start)))
				start++;

			if (start == end)
				start = getCompletionOffset();

			return start;

		} catch (BadLocationException e) {
			return super.getStart();
		}
	}
}
