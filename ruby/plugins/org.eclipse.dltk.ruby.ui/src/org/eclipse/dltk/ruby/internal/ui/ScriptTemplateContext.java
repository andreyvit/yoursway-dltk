package org.eclipse.dltk.ruby.internal.ui;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.templates.DocumentTemplateContext;
import org.eclipse.jface.text.templates.TemplateContextType;

public class ScriptTemplateContext extends DocumentTemplateContext {

	private ISourceModule sourceModule;
    
	protected boolean fForceEvaluation;

    protected ScriptTemplateContext(TemplateContextType type, IDocument document, int completionOffset,
            int completionLength, ISourceModule sourceModule) {
        super(type, document, completionOffset, completionLength);
     
        this.sourceModule = sourceModule;
    }
    
	public final ISourceModule getSourceModule() {
	    return sourceModule;
	}

    /**
	 * Sets whether evaluation is forced or not.
	 * 
	 * @param evaluate <code>true</code> in order to force evaluation,
	 *            <code>false</code> otherwise
	 */
	public void setForceEvaluation(boolean evaluate) {
		fForceEvaluation= evaluate;	
	}
}
