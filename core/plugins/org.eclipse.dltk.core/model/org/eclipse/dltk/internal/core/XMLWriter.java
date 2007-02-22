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
package org.eclipse.dltk.internal.core;
import java.io.Writer;

import org.eclipse.dltk.compiler.util.GenericXMLWriter;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.internal.core.util.Util;


class XMLWriter extends GenericXMLWriter {

	public XMLWriter(Writer writer, IDLTKProject project, boolean printXmlVersion) {
		super(writer, Util.getLineSeparator((String) null, project), printXmlVersion);
	}
}
