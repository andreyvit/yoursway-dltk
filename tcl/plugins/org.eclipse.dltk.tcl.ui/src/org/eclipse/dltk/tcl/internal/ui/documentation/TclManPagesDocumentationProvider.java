/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.documentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.dltk.tcl.ui.TclPreferenceConstants;
import org.eclipse.dltk.ui.documentation.IScriptDocumentationProvider;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

public class TclManPagesDocumentationProvider implements
		IScriptDocumentationProvider {

	private List folders = null;

	public Reader getInfo(IMember element, boolean lookIntoParents,
			boolean lookIntoExternal) {
		return null;
	}

	public Reader getInfo(String content) {
		initalizeLocations(false);

		if (folders != null) {
			for (Iterator iterator = folders.iterator(); iterator.hasNext();) {
				ManPageFolder f = (ManPageFolder) iterator.next();
				HashMap pages = f.getPages();
				String ans = (String) pages.get(content);
				if (ans != null) {
					IPath filePath = new Path(f.getPath()).append(ans);
					File file = filePath.toFile();
					if (file != null && file.isFile()) {
						try {
							return new FileReader(file);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
					}
					break;
				}
			}
		}

		return null;
	}

	private void initalizeLocations(boolean force) {
		if (!force && this.folders != null)
			return;

		TclUI.getDefault().getPreferenceStore().addPropertyChangeListener(
				new IPropertyChangeListener() {

					public void propertyChange(PropertyChangeEvent event) {
						initalizeLocations(true);
					}

				});

		String value = TclUI.getDefault().getPreferenceStore().getString(
				TclPreferenceConstants.DOC_MAN_PAGES_LOCATIONS);

		try {
			this.folders = ManPageFolder.readXML(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
