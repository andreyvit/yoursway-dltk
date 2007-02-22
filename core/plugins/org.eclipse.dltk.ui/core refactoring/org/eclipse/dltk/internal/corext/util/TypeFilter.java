/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.util;

import java.util.StringTokenizer;

import org.eclipse.dltk.core.DLTKModelUtil;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.search.TypeNameMatch;
import org.eclipse.dltk.internal.ui.util.StringMatcher;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

/**
 *
 */
public class TypeFilter implements IPropertyChangeListener {
	private IDLTKUILanguageToolkit fToolkit;
	
	public TypeFilter(IDLTKUILanguageToolkit toolkit ) {
		this.fToolkit = toolkit;
	}
	public boolean isFiltered(String fullTypeName) {
		return filter(fullTypeName);
	}
	
	public boolean isFiltered(char[] fullTypeName) {
		return filter(new String(fullTypeName));
	}
	
	protected String concatenate( char[]packageName, char[]typeName ) {
		return new String(packageName) + " " + new String( typeName );
	}
		
	public boolean isFiltered(char[] packageName, char[] typeName) {
		return filter(concatenate(packageName, typeName));
	}
	
	public boolean isFiltered(IType type) {
		if( hasFilters()) {
			return filter(DLTKModelUtil.getFullyQualifiedName(type));
		}
		return false;
	}
	
	public boolean isFiltered(TypeNameMatch match) {
		return filter(match.getFullyQualifiedName());
	}

	private StringMatcher[] fStringMatchers;

	protected IPreferenceStore getPreferenceStore() {
		return this.fToolkit.getPreferenceStore();
	}
	/**
	 * 
	 */
	public TypeFilter() {
		fStringMatchers= null;
		getPreferenceStore().addPropertyChangeListener(this);
	}
	
	private synchronized StringMatcher[] getStringMatchers() {
		if (fStringMatchers == null) {
			String str= this.getPreferenceStore().getString(PreferenceConstants.TYPEFILTER_ENABLED);
			StringTokenizer tok= new StringTokenizer(str, ";"); //$NON-NLS-1$
			int nTokens= tok.countTokens();
			
			fStringMatchers= new StringMatcher[nTokens];
			for (int i= 0; i < nTokens; i++) {
				String curr= tok.nextToken();
				if (curr.length() > 0) { 
					fStringMatchers[i]= new StringMatcher(curr, false, false);
				}
			}
		}
		return fStringMatchers;
	}
	
	public void dispose() {
		this.getPreferenceStore().removePropertyChangeListener(this);
		fStringMatchers= null;
	}
	
	
	public boolean hasFilters() {
		return getStringMatchers().length > 0;
	}
	
	public boolean filter(String fullTypeName) {
		StringMatcher[] matchers= getStringMatchers();
		for (int i= 0; i < matchers.length; i++) {
			StringMatcher curr= matchers[i];
			if (curr.match(fullTypeName)) {
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
	 */
	public synchronized void propertyChange(PropertyChangeEvent event) {
		if (PreferenceConstants.TYPEFILTER_ENABLED.equals(event.getProperty())) {
			fStringMatchers= null;
		}
	}


}
