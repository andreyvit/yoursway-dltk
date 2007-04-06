package org.eclipse.dltk.console;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public final class ScriptConsoleXmlHelper {
	private ScriptConsoleXmlHelper() {
	}

	protected static int convertState(String state) {
		if (state.equals("new")) {
			return IScriptConsoleInterpreter.WAIT_NEW_COMMAND;
		} else if (state.equals("continue")) {
			return IScriptConsoleInterpreter.WAIT_CONTINUE_COMMAND;
		} else if (state.equals("user")) {
			return IScriptConsoleInterpreter.WAIT_USER_INPUT;
		}

		return -1;
	}

	protected static boolean isElement(Node node, String name) {
		return node.getNodeType() == Node.ELEMENT_NODE
				&& node.getNodeName().equals(name);
	}

	protected static String getAttrib(NamedNodeMap attribs, String name) {
		return attribs.getNamedItem(name).getNodeValue();
	}

	protected static Document parse(String xml) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringReader reader = new StringReader(xml);
			return builder.parse(new InputSource(reader));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	protected static List parseCompletionList(Node completionNode) {
		NodeList children = completionNode.getChildNodes();

		List completions = new ArrayList();
		for (int i = 0; i < children.getLength(); ++i) {
			Node node = children.item(i);

			if (!isElement(node, "case")) {
				continue;
			}

			Element element = (Element) node;

			String display = element.getAttribute("display");
			String insert = element.getAttribute("insert");
			String type = element.getAttribute("type");

			completions.add(new ScriptConsoleCompletionProposal(insert,
					display, type));
		}

		return completions;
	}

	public static String parseInfoXml(String xml) {
		Document doc = parse(xml);

		if (doc == null) {
			return null;
		}

		NodeList list = doc.getElementsByTagName("console");
		Node node = list.item(0);

		list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); ++i) {
			Node n = list.item(i);
			if (isElement(n, "info")) {
				return ((Element) n).getAttribute("id");
			}
		}

		return null;
	}

	public static ShellResponse parseShellXml(String xml) {
		Document doc = parse(xml);

		if (doc == null) {
			return null;
		}

		NodeList list1 = doc.getElementsByTagName("console");
		Node node = list1.item(0);

		list1 = node.getChildNodes();
		for (int i = 0; i < list1.getLength(); ++i) {
			Node n1 = list1.item(i);
			if (isElement(n1, "shell")) {
				NodeList list2 = n1.getChildNodes();
				for (int j = 0; j < list2.getLength(); ++j) {
					Node n2 = list2.item(j);
					if (isElement(n2, "completion")) {
						List completions = parseCompletionList(n2);
						return new ShellResponse(completions);
					} else if (isElement(n2, "description")) {
						if (n2.getChildNodes().getLength() == 0) {
							return new ShellResponse("");
						} else {
							return new ShellResponse(n2.getFirstChild()
									.getNodeValue());
						}
					} else if (isElement(n2, "close")) {
						return new ShellResponse();
					}
				}
			}
		}

		return null;
	}

	public static InterpreterResponse parseInterpreterXml(String xml) {
		Document doc = parse(xml);

		NodeList list = doc.getElementsByTagName("console").item(0)
				.getChildNodes();

		for (int i = 0; i < list.getLength(); ++i) {
			Node n = list.item(i);
			if (isElement(n, "interpreter")) {
				String state = getAttrib(n.getAttributes(), "state");
				String response = "";

				// Check for empty response
				if (n.getChildNodes().getLength() > 0) {
					response = n.getFirstChild().getNodeValue();
				}

				return new InterpreterResponse(convertState(state), response);
			}
		}

		return null;
	}
}
