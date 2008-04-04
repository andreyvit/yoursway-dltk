/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.commands;

import org.eclipse.dltk.dbgp.IDbgpFeature;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;

public interface IDbgpFeatureCommands {
	// These features must be available

	// get [0|1]
	final String LANGUAGE_SUPPORTS_THREADS = "language_supports_threads"; //$NON-NLS-1$

	// get {eg. PHP, Python, Perl}
	final String LANGUAGE_NAME = "language_name"; //$NON-NLS-1$

	// get {version string}
	final String LANGUAGE_VERSION = "language_version"; //$NON-NLS-1$

	// get current encoding in use by the debugger session
	final String ENCODING = "encoding"; //$NON-NLS-1$

	// get {for now, always 1}
	final String PROTOCOL_VERSION = "protocol_version"; //$NON-NLS-1$

	// get {for commands such as break}
	final String SUPPORTS_ASYNC = "supports_async"; //$NON-NLS-1$

	// get optional, allows to turn off the default base64 encoding of data.
	// This should only be used for development and debugging of the debugger
	// engines themselves, and not for general use. If implemented the value
	// 'base64' must be supported to turn back on regular encoding. the value
	// 'none' means no encoding is in use. all elements that use encoding must
	// include an encoding attribute.
	final String DATA_ENCODING = "data_encoding"; //$NON-NLS-1$

	// get some engines may support more than one language. This feature returns
	// a string which is a comma separated list of supported languages. If the
	// engine does not provide this feature, then it is assumed that the engine
	// only supports the language defined in the feature language_name. One
	// example of this is an XSLT debugger engine which supports XSLT, XML, HTML
	// and XHTML. An IDE may need this information to to know what types of
	// breakpoints an engine will accept.
	final String BREAKPOINT_LANGUAGES = "breakpoint_languages"; //$NON-NLS-1$

	// get|set {0|1}
	final String MULTIPLE_SESSIONS = "multiple_sessions"; //$NON-NLS-1$

	// get|set max number of array or object children to initially retrieve
	final String MAX_CHILDREN = "max_children"; //$NON-NLS-1$

	// get|set max amount of variable data to initially retrieve.
	final String MAX_DATA = "max_data"; //$NON-NLS-1$

	// get|set maximum depth that the debugger engine may return when sending
	// arrays, hashs or object structures to the IDE.
	final String MAX_DEPTH = "max_depth"; //$NON-NLS-1$

	// Optional features

	// get [0|1] This feature lets an IDE know that there is benefit to
	// continuing interaction during the STOPPING state (sect. 7.1).
	final String SUPPORTS_POSTMORTEN = "supports_postmortem"; //$NON-NLS-1$

	// get|set [0|1] This feature can get set by the IDE if it wants to have
	// more detailed internal information on properties (eg. private members of
	// classes, etc.) Zero means that hidden members are not shown to the IDE.
	final String SHOW_HIDDEN = "show_hidden"; //$NON-NLS-1$

	// get|set [0|1] See section 8.5
	final String NOTIFY_OK = "notify_ok"; //$NON-NLS-1$

	IDbgpFeature getFeature(String featureName) throws DbgpException;

	boolean setFeature(String featureName, String featureValue)
			throws DbgpException;
}
