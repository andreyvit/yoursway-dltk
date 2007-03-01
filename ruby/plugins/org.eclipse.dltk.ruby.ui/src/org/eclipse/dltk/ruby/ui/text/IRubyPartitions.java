package org.eclipse.dltk.ruby.ui.text;

import org.eclipse.dltk.ruby.core.IRubyConstants;
import org.eclipse.jface.text.IDocument;

public interface IRubyPartitions {

	public final static String RUBY_PARTITIONING = IRubyConstants.RUBY_PARTITIONING;

	public final static String RUBY_COMMENT = "__ruby_comment";
	public final static String RUBY_STRING = "__ruby_string";
	public final static String RUBY_DOC = "__ruby_doc";

	public final static String[] RUBY_PARTITION_TYPES = new String[] {
		IDocument.DEFAULT_CONTENT_TYPE, IRubyPartitions.RUBY_STRING,
		IRubyPartitions.RUBY_COMMENT, IRubyPartitions.RUBY_DOC
	};
}
