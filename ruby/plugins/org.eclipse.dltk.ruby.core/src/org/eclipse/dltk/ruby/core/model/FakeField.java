/**
 * 
 */
package org.eclipse.dltk.ruby.core.model;

import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.ModelElement;
import org.eclipse.dltk.internal.core.SourceField;
import org.eclipse.dltk.internal.core.SourceRange;

public class FakeField extends SourceField {
	private int offset;
	private int length;
	
	public FakeField(ModelElement parent, String name,int offset,int length) {
		super(parent, name);
		this.offset=offset;
		this.length=length;
	}

	public ISourceRange getNameRange() throws ModelException {
		return new SourceRange(offset,length);
	}

	public ISourceRange getSourceRange() throws ModelException {
		return new SourceRange(offset,length);
	}
}