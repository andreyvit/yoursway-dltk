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
import java.io.FilenameFilter;
import java.io.Reader;

public class HtmlManPagesLocation implements IManPagesLocation {

	private File fLocation;

	/**
	 * @param location
	 *            directory(!)
	 */
	public HtmlManPagesLocation(File location) {
		if (!location.isDirectory()) {
			throw new IllegalArgumentException();			
		}
		
		fLocation = location;
	}

	public Reader getHtmlInfo(String keyword) {
		final String pattern = keyword + ".htm";
		File[] result = fLocation.listFiles(new FilenameFilter() {

			public boolean accept(File dir, String name) {
				if (name.equals(pattern))
					return true;
				return false;
			}

		});
		if (result != null && result.length >= 1 && result[0] != null) {
			try {
				FileReader reader = new FileReader(result[0]);
				return reader;
			} catch (FileNotFoundException e) {
				// hmmm! but nothing to do.
			}
		}
		return null;
	}

	public File getLocation() {
		return fLocation;
	}

	public void setLocation(File location) {
		if (!location.isDirectory())
			return;
		fLocation = location;
	}

}
