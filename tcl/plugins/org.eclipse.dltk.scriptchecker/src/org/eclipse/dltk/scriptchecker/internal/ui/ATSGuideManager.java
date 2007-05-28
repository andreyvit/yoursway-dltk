package org.eclipse.dltk.scriptchecker.internal.ui;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.scriptchecker.internal.core.ScriptCheckerPlugin;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

public class ATSGuideManager implements IPropertyChangeListener {
	private static final String URI = "uri";
	private static final String PATTERN = "pattern";
	private static final String GUIDE = "guide";
	private static final String ATSGUIDES = "atsguides";
	private final static String GUIDE_PREFERENCE = ScriptCheckerPlugin.PLUGIN_ID
			+ ".astguide";
	private static ATSGuideManager sInstance = null;
	private List fGuides = new ArrayList();

	public static class GuideNode {
		private String uri;
		private String pattern;

		public GuideNode() {
		}

		public GuideNode(String pattern, String uri) {
			this.pattern = pattern;
			this.uri = uri;
		}

		public String getUri() {
			return uri;
		}

		public void setUri(String uri) {
			this.uri = uri;
		}

		public String getPattern() {
			return pattern;
		}

		public void setPattern(String pattern) {
			this.pattern = pattern;
		}

		public boolean equals(Object obj) {
			if (!(obj instanceof GuideNode)) {
				return false;
			}
			GuideNode node = (GuideNode) obj;
			if (this.pattern != null && !this.pattern.equals(node.pattern)) {
				return false;
			}
			return true;
		}

		public int hashCode() {
			if( this.pattern == null ) {
				super.hashCode();
			}
			return this.pattern.hashCode();
		}

	}

	private ATSGuideManager() {
		ScriptCheckerPlugin.getDefault().getPreferenceStore()
				.addPropertyChangeListener(this);
		load();
	}

	public void load() {
		String preference = ScriptCheckerPlugin.getDefault().getPreferenceStore()
				.getString(GUIDE_PREFERENCE);
		if (preference != null && preference.length() > 0 ) {
			try {
				StringReader reader = new StringReader(preference);
				DocumentBuilder parser = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder();
				parser.setErrorHandler(new DefaultHandler());
				Element documentElement = parser.parse(new InputSource(reader))
						.getDocumentElement();
				if (!documentElement.getNodeName().equalsIgnoreCase(ATSGUIDES)) {
					return;
				}
				List addedElements = new ArrayList();
				List oldElements = new ArrayList();
				oldElements.addAll(this.fGuides);

				NodeList list = documentElement.getChildNodes();
				int length = list.getLength();
				for (int i = 0; i < length; ++i) {
					Node node = list.item(i);
					short type = node.getNodeType();
					if (type == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						if (element.getNodeName().equalsIgnoreCase(GUIDE)) {
							String pattern = element.getAttribute(PATTERN);
							String uri = element.getAttribute(URI);
							addedElements.add(addGuide(pattern, uri));
						}
					}
				}
				oldElements.removeAll(addedElements);
				this.fGuides.removeAll(oldElements);
			} catch (Exception e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			}
		}
	}

	public synchronized GuideNode addGuide(String pattern, String uri) {
		GuideNode guide = new GuideNode(pattern, uri);
		if (!this.fGuides.contains(guide)) {
			fGuides.add(guide);
			return guide;
		}
		return guide;
	}

	public synchronized void removeGuide(GuideNode node) {
		this.fGuides.remove(node);
	}

	public synchronized GuideNode[] getGuides() {
		return (GuideNode[]) this.fGuides.toArray(new GuideNode[this.fGuides
				.size()]);
	}

	public static ATSGuideManager getInstance() {
		if (sInstance != null) {
			return sInstance;
		}
		sInstance = new ATSGuideManager();
		return sInstance;
	}
	public static boolean saving = false;
	public void propertyChange(PropertyChangeEvent event) {
		if( saving ) {
			return;
		}
		if (event.getProperty().equals(GUIDE_PREFERENCE)) {
			load();
			try {
				save();
			}
			catch(Exception e) {
				if( DLTKCore.DEBUG ) {
					e.printStackTrace();
				}
			}
		}
	}

	public void save() throws TransformerException {
		DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = dfactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			if( DLTKCore.DEBUG ) {
				e.printStackTrace();
			}
			return;
		}
		Document doc = docBuilder.newDocument();
		Element config = doc.createElement(ATSGUIDES); //$NON-NLS-1$
		doc.appendChild(config);
		GuideNode[] nodes = this.getGuides();
		for (int i = 0; i < nodes.length; i++) {
			Element e = doc.createElement(GUIDE);
			e.setAttribute(URI, nodes[i].getUri());
			e.setAttribute(PATTERN, nodes[i].getPattern());
			config.appendChild(e);
		}
		
		ByteArrayOutputStream s = new ByteArrayOutputStream();

		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.setOutputProperty(OutputKeys.METHOD, "xml"); //$NON-NLS-1$
		transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$

		DOMSource source = new DOMSource(doc);
		StreamResult outputTarget = new StreamResult(s);
		transformer.transform(source, outputTarget);

		try {
			String xmlValue = s.toString("UTF8");
			saving = true;
			ScriptCheckerPlugin.getDefault().getPreferenceStore().setValue(GUIDE_PREFERENCE, xmlValue);
			saving = false;
		} catch (UnsupportedEncodingException e) {
			if( DLTKCore.DEBUG ) {
				e.printStackTrace();
			}
		} 		
	}

	public boolean isDuplicateName(String pattern2) {
		GuideNode[] guides = this.getGuides();
		for (int i = 0; i < guides.length; i++) {
			if( guides[i].getPattern() != null && guides[i].getPattern().equals(pattern2)) {
				return true;
			}
		}
		return false;
	}
}
