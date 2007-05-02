/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.ui.tests.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.ruby.internal.ui.text.RubyPartitionScanner;
import org.eclipse.dltk.ruby.internal.ui.text.RubyPartitions;
import org.eclipse.dltk.ruby.internal.ui.text.syntax.RubyBlocks;
import org.eclipse.dltk.ui.text.blocks.BlocksConfiguration;
import org.eclipse.dltk.ui.text.util.IRangeFilter;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.FastPartitioner;

public class TestUtils {

	public static String getData(String filename) throws IOException {
		File file = RubyUITestsPlugin.getDefault().getFileInPlugin(new Path(filename));
		InputStream stream = new FileInputStream(file.getAbsolutePath());
		int length = stream.available();
		byte[] data = new byte[length];
		stream.read(data);
		stream.close();
		return new String(data, "utf-8");
	}
	
	/**
	 * Installs a partitioner with <code>document</code>.
	 * 
	 * @param document
	 *            the document
	 */
	public static void installStuff(Document document) {
		String[] types = new String[] { RubyPartitions.RUBY_STRING,
				RubyPartitions.RUBY_COMMENT, IDocument.DEFAULT_CONTENT_TYPE };
		FastPartitioner partitioner = new FastPartitioner(
				new RubyPartitionScanner(), types);
		partitioner.connect(document);
		document.setDocumentPartitioner(RubyPartitions.RUBY_PARTITIONING,
				partitioner);
	}

	public static final IRangeFilter ALL_RANGES_ALLOWED = new IRangeFilter() {
		
		public boolean allowRange(IDocument document, int start, int length) throws BadLocationException {
			return true;
		}
		
	};
	public static final BlocksConfiguration ALL_RUBY_BLOCKS = RubyBlocks.ALL_BLOCKS;
	
}
