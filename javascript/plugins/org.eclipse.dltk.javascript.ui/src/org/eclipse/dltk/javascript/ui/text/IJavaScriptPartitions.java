package org.eclipse.dltk.javascript.ui.text;

import org.eclipse.dltk.javascript.core.IJavaScriptConstants;
import org.eclipse.jface.text.IDocument;

public interface IJavaScriptPartitions {

	public final static String JS_PARTITIONING = IJavaScriptConstants.JS_PARTITIONING;
	
	public final static String JS_COMMENT = "__javascript_comment";		
	public final static String JS_STRING = "__javascript_string";
	public static final String JS_DOC ="__javascript_doc";

	public final static String[] JS_PARTITION_TYPES = new String[] {
		IDocument.DEFAULT_CONTENT_TYPE, IJavaScriptPartitions.JS_STRING,
		IJavaScriptPartitions.JS_COMMENT, IJavaScriptPartitions.JS_DOC
	};
}
