package org.eclipse.dltk.validators.internal.core.externalchecker;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.validators.internal.core.ValidatorsCore;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ExternalCheckerWildcardManager {
	private static final String STRING = "wildcards"; //$NON-NLS-1$
	private static final String WILDCARD = "wildcard"; //$NON-NLS-1$
	private static final String DESCRIPTION = "description"; //$NON-NLS-1$
	private static final String PATTERN = "pattern"; //$NON-NLS-1$
	private static final String LETTER = "letter"; //$NON-NLS-1$
	public static final String WILDCARDS = ValidatorsCore.PLUGIN_ID
			+ ".wildcards"; //$NON-NLS-1$

	public static String getDefaultWildcards() {
		CustomWildcard[] wildcards = {
				new CustomWildcard("f", "[\\w]?:?.+", Messages.ExternalCheckerWildcardManager_fileName), //$NON-NLS-1$ //$NON-NLS-2$
				new CustomWildcard("m", ".*", Messages.ExternalCheckerWildcardManager_message), //$NON-NLS-1$ //$NON-NLS-2$
				new CustomWildcard("n", "[0-9]+", Messages.ExternalCheckerWildcardManager_lineNumber) }; //$NON-NLS-1$ //$NON-NLS-2$
		return rulesToXML(wildcards);
	}

	public static List loadCustomWildcards() {
		List wildcards = new ArrayList();
		String preference = ValidatorsCore.getDefault().getPluginPreferences()
				.getString(ExternalCheckerWildcardManager.WILDCARDS);
		if (DLTKCore.DEBUG) {
			System.out.println(preference);
		}
		if (preference != "") { //$NON-NLS-1$
			try {
				DocumentBuilderFactory factorryr = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder builder = factorryr.newDocumentBuilder();
				Document doc = builder.parse(new InputSource(new StringReader(
						preference)));
				Element docElement = doc.getDocumentElement();
				if (!docElement.getNodeName().equalsIgnoreCase(STRING)) {
					return wildcards;
				}
				NodeList nlist = docElement.getChildNodes();

				for (int i = 0; i < nlist.getLength(); i++) {
					Node node = nlist.item(i);
					short type = node.getNodeType();
					if (type == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						if (element.getNodeName().equalsIgnoreCase(WILDCARD)) {
							String name = element.getAttribute(LETTER);
							String value = element.getAttribute(PATTERN);
							String descr = element.getAttribute(DESCRIPTION);
							CustomWildcard wcard = new CustomWildcard(name,
									value, descr);
							wildcards.add(wcard);
						}
					}
				}
			} catch (Exception x) {
				if (DLTKCore.DEBUG) {
					System.out.println(x.toString());
				}
			}
		}
		return wildcards;
	}

	public static void storeWildcards(CustomWildcard[] customWildcards) {
		String xmlString = rulesToXML(customWildcards);
		ValidatorsCore.getDefault().getPluginPreferences().setValue(WILDCARDS,
				xmlString);
		ValidatorsCore.getDefault().savePluginPreferences();
	}

	private static String rulesToXML(CustomWildcard[] customWildcards)
			throws TransformerFactoryConfigurationError {
		String xmlString = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			Element root = (Element) document.createElement(STRING);
			document.appendChild(root);
			for (int i = 0; i < customWildcards.length; i++) {
				Element wildcard = (Element) document.createElement(WILDCARD);
				String name = customWildcards[i].getLetter();
				String value = customWildcards[i].getSpattern();
				String description = customWildcards[i].getDescription();
				wildcard.setAttribute(LETTER, name);
				wildcard.setAttribute(PATTERN, value);
				wildcard.setAttribute(DESCRIPTION, description);
				root.appendChild(wildcard);
			}

			TransformerFactory tfactory = TransformerFactory.newInstance();
			Transformer trannsformer = tfactory.newTransformer();
			trannsformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
					"yes"); //$NON-NLS-1$
			trannsformer.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$
			;

			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(document);
			trannsformer.transform(source, result);
			xmlString = sw.toString();

			if (DLTKCore.DEBUG) {
				System.out.println(xmlString);
			}
		} catch (Exception x) {
			if (DLTKCore.DEBUG) {
				System.out.println(x.toString());
			}
		}
		return xmlString;
	}
}
