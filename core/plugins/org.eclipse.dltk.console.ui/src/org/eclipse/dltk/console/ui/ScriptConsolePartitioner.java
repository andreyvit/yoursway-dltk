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
import java.util.Iterator;
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
	
	private List ranges = new ArrayList ();
	
	private static class Constants {
		public static final String MY_DOUBLE_QUOTED = "__my_double"; //$NON-NLS-1$

		public static final String MY_SINGLE_QUOTED = "__my_single"; //$NON-NLS-1$
	}
	
	private static class MyPartitionScanner extends RuleBasedPartitionScanner {
		public MyPartitionScanner() {
			IToken myDouble = new Token(Constants.MY_DOUBLE_QUOTED);
			IToken mySingle = new Token(Constants.MY_SINGLE_QUOTED);

			List rules = new ArrayList();

			rules.add(new MultiLineRule("\'", "\'", mySingle, '\\')); //$NON-NLS-1$ //$NON-NLS-2$
			rules.add(new MultiLineRule("\"", "\"", myDouble, '\\')); //$NON-NLS-1$ //$NON-NLS-2$

			IPredicateRule[] result = new IPredicateRule[rules.size()];
			rules.toArray(result);

			setPredicateRules(result);
		}
	}
	
	public ScriptConsolePartitioner() {
		
		super(new MyPartitionScanner(), new String[] {
			Constants.MY_DOUBLE_QUOTED, Constants.MY_SINGLE_QUOTED });
	}
	
	public void addRange (StyleRange r) {
		ranges.add (r);		
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
		List result = new ArrayList ();
		for (Iterator iterator = ranges.iterator(); iterator.hasNext();) {
			StyleRange r = (StyleRange) iterator.next();
			if (r.start >= offset && r.start + r.length <= offset + length)
				result.add(r);
		}
		
		if (result.size() > 0)
			return (StyleRange[]) result.toArray(new StyleRange[result.size()]); 
			
		return  new StyleRange[]{new StyleRange(offset, length, null, null, SWT.NO)};
	}

	public boolean isReadOnly(int offset) {
		return false;
	}
}
