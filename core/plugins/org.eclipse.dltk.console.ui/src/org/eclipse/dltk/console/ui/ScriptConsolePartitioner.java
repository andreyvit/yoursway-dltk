/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.console.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.ui.console.IConsoleDocumentPartitioner;

public class ScriptConsolePartitioner extends FastPartitioner
		implements IConsoleDocumentPartitioner {
	
	private static class Constants {
		public static final String MY_DOUBLE_QUOTED = "__my_double";

		public static final String MY_SINGLE_QUOTED = "__my_single";
	}
	
	private static class MyPartitionScanner extends RuleBasedPartitionScanner {
		public MyPartitionScanner() {
			IToken myDouble = new Token(Constants.MY_DOUBLE_QUOTED);
			IToken mySingle = new Token(Constants.MY_SINGLE_QUOTED);

			List rules = new ArrayList();

			rules.add(new MultiLineRule("\'", "\'", mySingle, '\\'));
			rules.add(new MultiLineRule("\"", "\"", myDouble, '\\'));

			IPredicateRule[] result = new IPredicateRule[rules.size()];
			rules.toArray(result);

			setPredicateRules(result);
		}
	}
	
	public ScriptConsolePartitioner() {
		
		super(new MyPartitionScanner(), new String[] {
			Constants.MY_DOUBLE_QUOTED, Constants.MY_SINGLE_QUOTED });
	}

	public StyleRange[] getStyleRanges(int offset, int length) {
//		int i = offset;
//
//		int last = offset + length;
//
//		List list = new ArrayList();
//		while (i < last) {
//			ITypedRegion region = getPartition(i);
//
//			String type = region.getType();
//
//			int off = region.getOffset();
//			int len = region.getLength();
//
//			Color color = null;
//			if (type.equals(Constants.MY_DOUBLE_QUOTED)) {
//				color = new Color(null, 255, 0, 0);
//			}
//
//			if (type.equals(Constants.MY_SINGLE_QUOTED)) {
//				color = new Color(null, 0, 255, 255);
//			}
//
//			list.add(new StyleRange(off, len, color, null, SWT.ITALIC));
//
//			i = off + len;
//		}
//
//		return (StyleRange[]) list.toArray(new StyleRange[list.size()]);
		
		return  new StyleRange[]{new StyleRange(offset, length, null, null, SWT.NO)};
	}

	public boolean isReadOnly(int offset) {
		return false;
	}
}
