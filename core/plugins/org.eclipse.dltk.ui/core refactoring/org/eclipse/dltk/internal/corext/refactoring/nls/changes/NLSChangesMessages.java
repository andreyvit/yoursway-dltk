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
package org.eclipse.dltk.internal.corext.refactoring.nls.changes;

import org.eclipse.osgi.util.NLS;

public final class NLSChangesMessages extends NLS {

	private static final String BUNDLE_NAME= "org.eclipse.dltk.internal.corext.refactoring.nls.changes.NLSChangesMessages";//$NON-NLS-1$

	private NLSChangesMessages() {
		// Do not instantiate
	}

	public static String createFile_creating_resource;
	public static String createFile_Create_file;
	public static String deleteFile_deleting_resource;
	public static String deleteFile_Delete_File;

	public static String CreateFileChange_error_exists;
	public static String CreateFileChange_error_unknownLocation;
	
	static {
		NLS.initializeMessages(BUNDLE_NAME, NLSChangesMessages.class);
	}
}