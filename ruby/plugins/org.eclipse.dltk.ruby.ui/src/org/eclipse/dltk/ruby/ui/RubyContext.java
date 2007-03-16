package org.eclipse.dltk.ruby.ui;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ruby.internal.ui.ScriptTemplateContext;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateBuffer;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.jface.text.templates.TemplateTranslator;

public class RubyContext extends ScriptTemplateContext {

	/**
	 * Creates a ruby template context.
	 * 
	 * @param type
	 *            the context type.
	 * @param document
	 *            the document.
	 * @param completionOffset
	 *            the completion offset within the document.
	 * @param completionLength
	 *            the completion length.
	 * @param compilationUnit
	 *            the compilation unit (may be <code>null</code>).
	 */
	public RubyContext(TemplateContextType type, IDocument document,
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
		
//		{
//			protected TemplateVariable createVariable(String type, String name,
//					int[] offsets) {
//				// XXX
//				// return new MultiVariable(type, name, offsets);
//				return null;
//			}
//		};
		TemplateBuffer buffer = translator.translate(template);

		getContextType().resolve(buffer, this);

		//IPreferenceStore prefs = RubyUI.getDefault().getPreferenceStore();

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

	/**
	 * Returns the indentation level at the position of code completion.
	 * 
	 * @return the indentation level at the position of the code completion
	 */
	private int getIndentation() {
		int start = getStart();
		IDocument document = getDocument();
		try {
			IRegion region = document.getLineInformationOfOffset(start);
			String lineContent = document.get(region.getOffset(), region
					.getLength());

			// IRubyScript compilationUnit = getRubyScript();
			// IRubyProject project = compilationUnit == null ? null
			// : compilationUnit.getRubyProject();
			// return Strings.computeIndentUnits(lineContent, project);
			return 0;
		} catch (BadLocationException e) {
			return 0;
		}
	}

	/*
	 * @see TemplateContext#canEvaluate(Template templates)
	 */
	public boolean canEvaluate(Template template) {
		if (fForceEvaluation)
			return true;

		String key = getKey();
		return template.matches(key, getContextType().getId())
				&& key.length() != 0
				&& template.getName().toLowerCase().startsWith(
						key.toLowerCase());
	}

	/*
	 * @see org.eclipse.jdt.internal.corext.template.DocumentTemplateContext#getKey()
	 */
	public String getKey() {

		if (getCompletionLength() == 0)
			return super.getKey();

		try {
			IDocument document = getDocument();

			int start = getStart();
			int end = getCompletionOffset();
			return start <= end ? document.get(start, end - start) : ""; //$NON-NLS-1$

		} catch (BadLocationException e) {
			return super.getKey();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.templates.DocumentTemplateContext#getEnd()
	 */
	public int getEnd() {

		if (getCompletionLength() == 0)
			return super.getEnd();

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.templates.DocumentTemplateContext#getStart()
	 */
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
