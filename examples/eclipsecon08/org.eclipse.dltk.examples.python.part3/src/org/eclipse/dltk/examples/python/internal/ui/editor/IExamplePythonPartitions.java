package org.eclipse.dltk.examples.python.internal.ui.editor;

import org.eclipse.jface.text.IDocument;

public interface IExamplePythonPartitions {

	public final static String PYTHON_PARTITIONING = "__python_partitioning";

	public final static String PYTHON_COMMENT = "__python_comment";
	public final static String PYTHON_STRING = "__python_string";

	public final static String[] PYTHON_PARITION_TYPES = new String[] {
			IExamplePythonPartitions.PYTHON_STRING, IExamplePythonPartitions.PYTHON_COMMENT,
			IDocument.DEFAULT_CONTENT_TYPE };
}
