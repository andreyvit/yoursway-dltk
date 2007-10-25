/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal.utils;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.dltk.dbgp.exceptions.DbgpDebuggingEngineException;
import org.eclipse.dltk.dbgp.exceptions.DbgpIOException;
import org.eclipse.dltk.dbgp.exceptions.DbgpProtocolException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DbgpXmlParser {
	protected DbgpXmlParser() {
	}

	protected static int getPosition(String s) {
		return Integer.parseInt(s.substring(s.indexOf(':') + 1));
	}

	protected static boolean makeBoolean(String s) {
		return Integer.parseInt(s) == 0 ? false : true;
	}

	public static Document parseXml(String xml) throws DbgpProtocolException {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();
			StringReader reader = new StringReader(xml);

			// builder.setErrorHandler(new ErrorHandler() {
			// public void error(SAXParseException exception)
			// throws SAXException {
			// }
			//
			// public void fatalError(SAXParseException exception)
			// throws SAXException {
			// }
			//
			// public void warning(SAXParseException exception)
			// throws SAXException {
			// }
			// });

			return builder.parse(new InputSource(reader));
		} catch (ParserConfigurationException e) {
			throw new DbgpProtocolException(e);
		} catch (SAXException e) {
			throw new DbgpProtocolException(e);
		} catch (IOException e) {
			throw new DbgpProtocolException(e);
		}
	}

	protected static String parseContent(Element element) {
		NodeList list = element.getChildNodes();
		for (int i = 0; i < list.getLength(); ++i) {
			Node e = list.item(i);
			int type = e.getNodeType();

			if (type == Element.TEXT_NODE || type == Element.CDATA_SECTION_NODE) {
				return e.getNodeValue();
			}
		}

		return "";
	}

	public static String parseBase64Content(Element element) {
		try {
			return Base64Helper.decodeString(parseContent(element));
		} catch (DbgpIOException e) {

		}
		return "";
	}

	public static DbgpDebuggingEngineException checkError(Element element) {
		final String TAG_ERROR = "error";
		final String TAG_MESSAGE = "message";
		final String ATTR_CODE = "code";

		NodeList errors = element.getElementsByTagName(TAG_ERROR);
		if (errors.getLength() > 0) {
			Element error = (Element) errors.item(0);
			int errorCode = Integer.parseInt(error.getAttribute(ATTR_CODE));

			String errorText = "No message";
			NodeList messages = error.getElementsByTagName(TAG_MESSAGE);

			if (messages.getLength() > 0) {
				errorText = parseContent((Element) messages.item(0));
			}

			return new DbgpDebuggingEngineException(errorCode, errorText);
		}

		return null;
	}

	public static boolean parseSuccess(Element response) {
		final String ATTR_SUCCESS = "success";

		// Strange assumption but it's required for compatibility
		if (!response.hasAttribute(ATTR_SUCCESS)) {
			return true;
		}

		return makeBoolean(response.getAttribute(ATTR_SUCCESS));
	}

	protected static String getStringAttribute(Element element, String name) {

		String value = "";
		if (element.hasAttribute(name)) {
			value = element.getAttribute(name);
		}

		return value;
	}

	protected static int getIntAttribute(Element element, String name,
			int defaultValue) {

		int value = defaultValue;

		if (element.hasAttribute(name)) {
			try {
				value = Integer.parseInt(element.getAttribute(name));
			} catch (NumberFormatException e) {
				// TODO: this should probably be logged
			}
		}

		return value;
	}

}
