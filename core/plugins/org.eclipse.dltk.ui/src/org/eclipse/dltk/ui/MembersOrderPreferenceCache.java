/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui;

import java.util.StringTokenizer;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

/**
  */
public class MembersOrderPreferenceCache implements IPropertyChangeListener {
	
	public static final int TYPE_INDEX= 0;
	public static final int CONSTRUCTORS_INDEX= 1;
	public static final int METHOD_INDEX= 2;
	public static final int FIELDS_INDEX= 3;	
	public static final int N_CATEGORIES= FIELDS_INDEX + 1;
		
	private static final int DEFAULT_INDEX= 0;
	private static final int N_VISIBILITIES= DEFAULT_INDEX + 1;	
	
	private int[] fCategoryOffsets= null;
	
	private IPreferenceStore fPreferenceStore;
	
	public MembersOrderPreferenceCache() {
		fPreferenceStore= null;
		fCategoryOffsets= null;	
	}
	
	public void install(IPreferenceStore store) {
		fPreferenceStore= store;
		store.addPropertyChangeListener(this);	
	}
	
	public void dispose() {
		fPreferenceStore.removePropertyChangeListener(this);
		fPreferenceStore= null;
	}
	
	public static boolean isMemberOrderProperty(String property) {
		return PreferenceConstants.APPEARANCE_MEMBER_SORT_ORDER.equals(property);
	}

	public void propertyChange(PropertyChangeEvent event) {
		String property= event.getProperty();
		
		if (PreferenceConstants.APPEARANCE_MEMBER_SORT_ORDER.equals(property)) {
			fCategoryOffsets= null;
		}
	}

	public int getCategoryIndex(int kind) {
		if (fCategoryOffsets == null) {
			fCategoryOffsets= getCategoryOffsets();
		}
		return fCategoryOffsets[kind];
	}
	
	private int[] getCategoryOffsets() {
		int[] offsets= new int[N_CATEGORIES];
		IPreferenceStore store= fPreferenceStore;
		String key= PreferenceConstants.APPEARANCE_MEMBER_SORT_ORDER;
		boolean success= fillCategoryOffsetsFromPreferenceString(store.getString(key), offsets);
		if (!success) {
			store.setToDefault(key);
			fillCategoryOffsetsFromPreferenceString(store.getDefaultString(key), offsets);	
		}
		return offsets;
	}
	
	private boolean fillCategoryOffsetsFromPreferenceString(String str, int[] offsets) {
		StringTokenizer tokenizer= new StringTokenizer(str, ","); //$NON-NLS-1$
		int i= 0;
		//offsets[ENUM_CONSTANTS_INDEX]= i++; // enum constants always on top
		
		while (tokenizer.hasMoreTokens()) {
			String token= tokenizer.nextToken().trim();
			if ("T".equals(token)) { //$NON-NLS-1$
				offsets[TYPE_INDEX]= i++;
			} else if ("M".equals(token)) { //$NON-NLS-1$
				offsets[METHOD_INDEX]= i++;
			} else if ("F".equals(token)) { //$NON-NLS-1$
				offsets[FIELDS_INDEX]= i++;			
			} else if ("C".equals(token)) { //$NON-NLS-1$
				offsets[CONSTRUCTORS_INDEX]= i++;
			}
		}
		return i == N_CATEGORIES;
	}
}
