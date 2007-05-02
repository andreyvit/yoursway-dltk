/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.launching;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.InterpreterStandin;
import org.eclipse.dltk.launching.LaunchingMessages;
import org.eclipse.dltk.launching.LibraryLocation;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * This is a container for interprter definitions such as the interpreter definitions that are
 * stored in the workbench preferences.  
 * <p>
 * An instance of this class may be obtained from an XML document by calling
 * <code>parseXMLIntoContainer</code>.
 * </p>
 * <p>
 * An instance of this class may be translated into an XML document by calling
 * <code>getAsXML</code>.
 * </p>
 * <p>
 * Clients may instantiate this class; it is not intended to be subclassed.
 * </p>
 */
public class InterpreterDefinitionsContainer {
		
	/**
	 * Map of InterpreterInstallTypes to Lists of corresponding InterpreterInstalls.
	 */
	private Map fInterTypeToInterMap;
	
	/**
	 * Cached list of Interpreters in this container
	 */
	private List fInterpreterList;
	
	/**
	 * Interpreters managed by this container whose install locations don't actually exist.
	 */
	private List fInvalidInterpreterList;
			
	/**
	 * The composite identifier of the default Interpreter.  This consists of the install type ID
	 * plus an ID for the Interpreter.
	 */
	//map bind default interpreter to each nature 
	private Map fDefaultInterpreterInstallCompositeID;
	
	
	/**
	 * The identifier of the connector to use for the default Interpreter.
	 */
	//	map bind default connector to each nature
	private Map fDefaultInterpreterInstallConnectorTypeID;
	
	/**
	 * Constructs an empty Interpreter container 
	 */
	public InterpreterDefinitionsContainer() {
		fInterTypeToInterMap = new HashMap(10);
		fInvalidInterpreterList = new ArrayList(10);	
		fInterpreterList = new ArrayList(10);	
		fDefaultInterpreterInstallCompositeID = new HashMap ();
		fDefaultInterpreterInstallConnectorTypeID = new HashMap ();
	}
	
	/**
	 * Returns list of default interpreters natures
	 * TODO: rename 
	 * @return
	 */
	public String[] getInterpreterNatures () {
		Set s = fDefaultInterpreterInstallCompositeID.keySet();
		return (String[])s.toArray(new String[s.size()]);
	}
		
	/**
	 * Add the specified Interpreter to the Interpreter definitions managed by this container.
	 * <p>
	 * If distinguishing valid from invalid Interpreters is important, the specified Interpreter must
	 * have already had its install location set.  An invalid Interpreter is one whose install
	 * location doesn't exist.
	 * </p>
	 * 
	 * @param Interpreter the Interpreter to be added to this container
	 */
	public void addInterpreter(IInterpreterInstall Interpreter) {
		if (!fInterpreterList.contains(Interpreter)) {	
			IInterpreterInstallType InterpreterInstallType = Interpreter.getInterpreterInstallType();
			List InterpreterList = (List) fInterTypeToInterMap.get(InterpreterInstallType);
			if (InterpreterList == null) {
				InterpreterList = new ArrayList(3);
				fInterTypeToInterMap.put(InterpreterInstallType, InterpreterList);			
			}
			InterpreterList.add(Interpreter);
			File installLocation = Interpreter.getInstallLocation();
			if (installLocation == null || !InterpreterInstallType.validateInstallLocation(installLocation).isOK()) {
				fInvalidInterpreterList.add(Interpreter);
			}
			fInterpreterList.add(Interpreter);
		}
	}
	
	/**
	 * Add all Interpreter's in the specified list to the Interpreter definitions managed by this container.
	 * <p>
	 * If distinguishing valid from invalid Interpreters is important, the specified Interpreters must
	 * have already had their install locations set.  An invalid Interpreter is one whose install
	 * location doesn't exist.
	 * </p>
	 * 
	 * @param InterpreterList a list of Interpreters to be added to this container
	 */
	public void addInterpreterList(List InterpreterList) {
		Iterator iterator = InterpreterList.iterator();
		while (iterator.hasNext()) {
			IInterpreterInstall Interpreter = (IInterpreterInstall) iterator.next();
			addInterpreter(Interpreter);
		}
	}

	/**
	 * Return a mapping of Interpreter install types to lists of Interpreters.  The keys of this map are instances of
	 * <code>IInterpreterInstallType</code>.  The values are instances of <code>java.util.List</code>
	 * which contain instances of <code>IInterpreterInstall</code>.  
	 * 
	 * @return Map the mapping of Interpreter install types to lists of Interpreters
	 */
	public Map getInterpreterTypeToInterpreterMap() {
		return fInterTypeToInterMap;
	}
	
	/**
	 * Return a list of all Interpreters in this container, including any invalid Interpreters.  An invalid
	 * Interpreter is one whose install location does not exist on the file system.
	 * The order of the list is not specified.
	 * 
	 * @return List the data structure containing all Interpreters managed by this container
	 */
	public List getInterpreterList() {
		return fInterpreterList;
	}
	
	/**
	 * Return filtered list of all Interpreters in this container, including any invalid Interpreters.  An invalid
	 * Interpreter is one whose install location does not exist on the file system.
	 * The order of the list is not specified.
	 * 
	 * @return List the data structure containing all Interpreters managed by this container
	 */
	public List getInterpreterList(String nature) {
		List res = new ArrayList(fInterpreterList.size());
		for (Iterator iter = fInterpreterList.iterator(); iter.hasNext();) {
			IInterpreterInstall Interpreter = (IInterpreterInstall) iter.next();
			if (Interpreter.getInterpreterInstallType().getNatureId().equals(nature))
				res.add (Interpreter);
		}
		return res;
	}
	
	/**
	 * Return a list of all valid Interpreters in this container.  A valid Interpreter is one whose install
	 * location exists on the file system.  The order of the list is not specified.
	 * 
	 * @return List 
	 */
	public List getValidInterpreterList() {
		List Interpreters = getInterpreterList();
		List resultList = new ArrayList(Interpreters.size());
		resultList.addAll(Interpreters);
		resultList.removeAll(fInvalidInterpreterList);
		return resultList;
	}
	
	/**
	 * Return filtered list of valid Interpreters in this container.  A valid Interpreter is one whose install
	 * location exists on the file system.  The order of the list is not specified.
	 * 
	 * @return List 
	 */
	public List getValidInterpreterList(String nature) {
		List Interpreters = getInterpreterList(nature);
		List resultList = new ArrayList(Interpreters.size());
		resultList.addAll(Interpreters);
		resultList.removeAll(fInvalidInterpreterList);
		return resultList;
	}
	
	/**
	 * Returns the composite ID for the default Interpreter.  The composite ID consists
	 * of an ID for the Interpreter install type together with an ID for Interpreter.  This is
	 * necessary because Interpreter ids by themselves are not necessarily unique across
	 * Interpreter install types.
	 * 
	 * @return String returns the composite ID of the current default Interpreter
	 */
	public String getDefaultInterpreterInstallCompositeID(String nature){
		return (String)fDefaultInterpreterInstallCompositeID.get(nature);
	}
	
	public String[] getDefaultInterpreterInstallCompositeID(){
		Collection ids = fDefaultInterpreterInstallCompositeID.values();
		return (String[])ids.toArray(new String[ids.size()]);
	}
	
	/**
	 * Sets the composite ID for the default Interpreter.  The composite ID consists
	 * of an ID for the Interpreter install type together with an ID for Interpreter.  This is
	 * necessary because Interpreter ids by themselves are not necessarily unique across
	 * Interpreter install types.
	 * 
	 * @param id identifies the new default Interpreter using a composite ID
	 */
	public void setDefaultInterpreterInstallCompositeID(String nature, String id){
		if (id != null)
			fDefaultInterpreterInstallCompositeID.put(nature, id);
		else
			fDefaultInterpreterInstallCompositeID.remove(nature);
	}
	
	/**
	 * Return the default Interpreter's connector type ID.
	 * 
	 * @return String the current value of the default Interpreter's connector type ID
	 */
	public String getDefaultInterpreterInstallConnectorTypeID(String nature) {
		return (String)fDefaultInterpreterInstallConnectorTypeID.get(nature);
	}
	
	/**
	 * Set the default Interpreter's connector type ID.
	 * 
	 * @param id the new value of the default Interpreter's connector type ID
	 */
	public void  setDefaultInterpreterInstallConnectorTypeID(String nature, String id){
		fDefaultInterpreterInstallConnectorTypeID.put(nature, id);
	}
	
	/**
	 * Return the Interpreter definitions contained in this object as a String of XML.  The String
	 * is suitable for storing in the workbench preferences.
	 * <p>
	 * The resulting XML is compatible with the static method <code>parseXMLIntoContainer</code>.
	 * </p>
	 * @return String the results of flattening this object into XML
	 * @throws IOException if this method fails. Reasons include:<ul>
	 * <li>serialization of the XML document failed</li>
	 * </ul>
	 * @throws ParserConfigurationException if creation of the XML document failed
	 * @throws TransformerException if serialization of the XML document failed
	 */
	public String getAsXML() throws ParserConfigurationException, IOException, TransformerException {
		
		// Create the Document and the top-level node
		Document doc = DLTKLaunchingPlugin.getDocument();
		Element config = doc.createElement("interpreterSettings");    //$NON-NLS-1$
		doc.appendChild(config);
		
		// Set the defaultInterpreter attribute on the top-level node
		for (Iterator iter = fDefaultInterpreterInstallCompositeID.keySet().iterator(); iter.hasNext();) {
			String nature = (String) iter.next();
			Element defaulte = doc.createElement("defaultInterpreter");
			config.appendChild(defaulte);
			defaulte.setAttribute("nature", nature);
			defaulte.setAttribute("id", (String)fDefaultInterpreterInstallCompositeID.get(nature));
		}
		
		// Set the defaultInterpreterConnector attribute on the top-level node
		for (Iterator iter = fDefaultInterpreterInstallConnectorTypeID.keySet().iterator(); iter.hasNext();) {
			String nature = (String) iter.next();
			Element defaulte = doc.createElement("defaultInterpreterConnector");
			config.appendChild(defaulte);
			defaulte.setAttribute("nature", nature);
			defaulte.setAttribute("id", (String)fDefaultInterpreterInstallConnectorTypeID.get(nature));
		}
				
		// Create a node for each install type represented in this container
		Set InterpreterInstallTypeSet = getInterpreterTypeToInterpreterMap().keySet();
		Iterator keyIterator = InterpreterInstallTypeSet.iterator();
		while (keyIterator.hasNext()) {
			IInterpreterInstallType InterpreterInstallType = (IInterpreterInstallType) keyIterator.next();
			Element InterpreterTypeElement = interpreterTypeAsElement(doc, InterpreterInstallType);
			config.appendChild(InterpreterTypeElement);
		}
		
		// Serialize the Document and return the resulting String
		return DLTKLaunchingPlugin.serializeDocument(doc);
	}
	
	/**
	 * Create and return a node for the specified Interpreter install type in the specified Document.
	 */
	private Element interpreterTypeAsElement(Document doc, IInterpreterInstallType InterpreterType) {
		
		// Create a node for the Interpreter type and set its 'id' attribute
		Element element= doc.createElement("interpreterType");   //$NON-NLS-1$
		element.setAttribute("id", InterpreterType.getId());     //$NON-NLS-1$
		
		// For each Interpreter of the specified type, create a subordinate node for it
		List InterpreterList = (List) getInterpreterTypeToInterpreterMap().get(InterpreterType);
		Iterator InterpreterIterator = InterpreterList.iterator();
		while (InterpreterIterator.hasNext()) {
			IInterpreterInstall Interpreter = (IInterpreterInstall) InterpreterIterator.next();
			Element InterpreterElement = interpreterAsElement(doc, Interpreter);
			element.appendChild(InterpreterElement);
		}
		
		return element;
	}
	
	/**
	 * Create and return a node for the specified Interpreter in the specified Document.
	 */
	private Element interpreterAsElement(Document doc, IInterpreterInstall Interpreter) {
		
		// Create the node for the Interpreter and set its 'id' & 'name' attributes
		Element element= doc.createElement("interpreter");        //$NON-NLS-1$
		element.setAttribute("id", Interpreter.getId());	         //$NON-NLS-1$
		element.setAttribute("name", Interpreter.getName());      //$NON-NLS-1$
		
		// Determine and set the 'path' attribute for the Interpreter
		String installPath= "";                          //$NON-NLS-1$
		File installLocation= Interpreter.getInstallLocation();
		if (installLocation != null) {
			installPath= installLocation.toString();
		}
		element.setAttribute("path", installPath);       //$NON-NLS-1$
		
		// If the 'libraryLocations' attribute is specified, create a node for it 
		LibraryLocation[] libraryLocations= Interpreter.getLibraryLocations();
		if (libraryLocations != null) {
			Element libLocationElement = libraryLocationsAsElement(doc, libraryLocations);
			element.appendChild(libLocationElement);
		}
		
		
		String[] InterpreterArgs = Interpreter.getInterpreterArguments();
		if (InterpreterArgs != null && InterpreterArgs.length > 0) {
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < InterpreterArgs.length; i++) {
				buffer.append(InterpreterArgs[i] + " "); //$NON-NLS-1$
			}
			element.setAttribute("iargs", buffer.toString()); //$NON-NLS-1$
		}

		
		return element;
	}
	
	/**
	 * Create and return a 'libraryLocations' node.  This node owns subordinate nodes that
	 * list individual library locations.
	 */
	private static Element libraryLocationsAsElement(Document doc, LibraryLocation[] locations) {
		Element root = doc.createElement("libraryLocations");       //$NON-NLS-1$
		for (int i = 0; i < locations.length; i++) {
			Element element = doc.createElement("libraryLocation");  //$NON-NLS-1$
			element.setAttribute("libraryPath", locations[i].getSystemLibraryPath().toString()); //$NON-NLS-1$
			root.appendChild(element);
		}
		return root;
	}
	
	public static InterpreterDefinitionsContainer parseXMLIntoContainer(InputStream inputStream) throws IOException {
		InterpreterDefinitionsContainer container = new InterpreterDefinitionsContainer();
		parseXMLIntoContainer(inputStream, container);
		return container;
	}
			
	/**
	 * Parse the Interpreter definitions contained in the specified InputStream into the
	 * specified container.
	 * <p>
	 * The Interpreters in the returned container are instances of <code>InterpreterStandin</code>.
	 * </p>
	 * <p>
	 * This method has no side-effects.  That is, no notifications are sent for Interpreter adds,
	 * changes, deletes, and the workbench preferences are not affected.
	 * </p>
	 * <p>
	 * If the <code>getAsXML</code> method is called on the returned container object,
	 * the resulting XML will be sematically equivalent (though not necessarily syntactically equivalent) as
	 * the XML contained in <code>inputStream</code>.
	 * </p>
	 * @param inputStream the <code>InputStream</code> containing XML that declares a set of Interpreters and a default Interpreter
	 * @param container the container to add the Interpreter defs to
	 * @return InterpreterDefinitionsContainer a container for the Interpreter objects declared in <code>inputStream</code>
	 * @throws IOException if this method fails. Reasons include:<ul>
	 * <li>the XML in <code>inputStream</code> was badly formatted</li>
	 * <li>the top-level node was not 'InterpreterSettings'</li>
	 * </ul>
	 *
	 */
	public static void parseXMLIntoContainer(InputStream inputStream, InterpreterDefinitionsContainer container) throws IOException {

		// Wrapper the stream for efficient parsing
		InputStream stream= new BufferedInputStream(inputStream);

		// Do the parsing and obtain the top-level node
		Element config= null;		
		try {
			DocumentBuilder parser= DocumentBuilderFactory.newInstance().newDocumentBuilder();
			parser.setErrorHandler(new DefaultHandler());
			config = parser.parse(new InputSource(stream)).getDocumentElement();
		} catch (SAXException e) {
			throw new IOException(LaunchingMessages.ScriptRuntime_badFormat); 
		} catch (ParserConfigurationException e) {
			stream.close();
			throw new IOException(LaunchingMessages.ScriptRuntime_badFormat); 
		} finally {
			stream.close();
		}
		
		// If the top-level node wasn't what we expected, bail out
		if (!config.getNodeName().equalsIgnoreCase("interpreterSettings")) { //$NON-NLS-1$
			throw new IOException(LaunchingMessages.ScriptRuntime_badFormat); 
		}

		// Traverse the parsed structure and populate the InterpreterType to Interpreter Map
		NodeList list = config.getChildNodes();
		int length = list.getLength();
		for (int i = 0; i < length; ++i) {
			Node node = list.item(i);
			short type = node.getNodeType();
			if (type == Node.ELEMENT_NODE) {
				Element InterpreterTypeElement = (Element) node;
				if (InterpreterTypeElement.getNodeName().equalsIgnoreCase("interpreterType")) { //$NON-NLS-1$
					populateInterpreterTypes(InterpreterTypeElement, container);
				}
				if (InterpreterTypeElement.getNodeName().equalsIgnoreCase("defaultInterpreter")) { //$NON-NLS-1$
					String nature = InterpreterTypeElement.getAttribute("nature");
					String id  = InterpreterTypeElement.getAttribute("id");
					container.setDefaultInterpreterInstallCompositeID(nature, id); 
				}
				if (InterpreterTypeElement.getNodeName().equalsIgnoreCase("defaultInterpreterConnector")) { //$NON-NLS-1$
					String nature = InterpreterTypeElement.getAttribute("nature");
					String id  = InterpreterTypeElement.getAttribute("id");
					container.setDefaultInterpreterInstallConnectorTypeID(nature, id); 
				}
			}
		}
	}
	
	/**
	 * For the specified Interpreter type node, parse all subordinate Interpreter definitions and add them
	 * to the specified container.
	 */
	private static void populateInterpreterTypes(Element InterpreterTypeElement, InterpreterDefinitionsContainer container) {
		
		// Retrieve the 'id' attribute and the corresponding Interpreter type object
		String id = InterpreterTypeElement.getAttribute("id");         //$NON-NLS-1$
		IInterpreterInstallType InterpreterType= ScriptRuntime.getInterpreterInstallType(id);
		if (InterpreterType != null) {
			
			// For each Interpreter child node, populate the container with a subordinate node
			NodeList InterpreterNodeList = InterpreterTypeElement.getChildNodes();
			for (int i = 0; i < InterpreterNodeList.getLength(); ++i) {
				Node InterpreterNode = InterpreterNodeList.item(i);
				short type = InterpreterNode.getNodeType();
				if (type == Node.ELEMENT_NODE) {
					Element InterpreterElement = (Element) InterpreterNode;
					if (InterpreterElement.getNodeName().equalsIgnoreCase("interpreter")) { //$NON-NLS-1$
						populateInterpreterForType(InterpreterType, InterpreterElement, container);
					}
				}
			}
		} else {
			DLTKLaunchingPlugin.log("Interpreter type element with unknown id.");  //$NON-NLS-1$
		}
	}

	/**
	 * Parse the specified Interpreter node, create a InterpreterStandin for it, and add this to the 
	 * specified container.
	 */
	private static void populateInterpreterForType(IInterpreterInstallType InterpreterType, Element InterpreterElement, InterpreterDefinitionsContainer container) {
		String id= InterpreterElement.getAttribute("id"); //$NON-NLS-1$
		if (id != null) {
			
			// Retrieve the 'path' attribute.  If none, skip this node.
			String installPath= InterpreterElement.getAttribute("path"); //$NON-NLS-1$
			if (installPath == null) {
				return;
			}
						
			// Create a InterpreterStandin for the node and set its 'name' & 'installLocation' attributes
			InterpreterStandin InterpreterStandin = new InterpreterStandin(InterpreterType, id);
			InterpreterStandin.setName(InterpreterElement.getAttribute("name")); //$NON-NLS-1$
			File installLocation= new File(installPath);
			InterpreterStandin.setInstallLocation(installLocation);
			container.addInterpreter(InterpreterStandin);
			
			// Look for subordinate nodes.  These may be 'libraryLocation',
			// 'libraryLocations' or 'versionInfo'.
			NodeList list = InterpreterElement.getChildNodes();
			int length = list.getLength();
			for (int i = 0; i < length; ++i) {
				Node node = list.item(i);
				short type = node.getNodeType();
				if (type == Node.ELEMENT_NODE) {
					Element subElement = (Element)node;
					String subElementName = subElement.getNodeName();
					if (subElementName.equals("libraryLocation")) { //$NON-NLS-1$
						LibraryLocation loc = getLibraryLocation(subElement);
						InterpreterStandin.setLibraryLocations(new LibraryLocation[]{loc});
						break;
					} else if (subElementName.equals("libraryLocations")) { //$NON-NLS-1$
						setLibraryLocations(InterpreterStandin, subElement);
						break;
					}
				}
			}
						
			// Interpreter Arguments
			String InterpreterArgs = InterpreterElement.getAttribute("iargs"); //$NON-NLS-1$
			if (InterpreterArgs != null && InterpreterArgs.length() >0) {
				InterpreterStandin.setInterpreterArgs(InterpreterArgs);
			}
		} else {
			DLTKLaunchingPlugin.log("id attribute missing from Interpreter element specification.");  //$NON-NLS-1$
		}
	}	
	
	/**
	 * Create & return a LibraryLocation object populated from the attribute values
	 * in the specified node.
	 */
	private static LibraryLocation getLibraryLocation(Element libLocationElement) {
		String interpreterEnvironmentArchive= libLocationElement.getAttribute("libraryPath"); //$NON-NLS-1$		
		if (interpreterEnvironmentArchive != null) {
			return new LibraryLocation(new Path(interpreterEnvironmentArchive));
		}
		DLTKLaunchingPlugin.log("Library location element is specified incorrectly.");  //$NON-NLS-1$
		return null;
	}
	
	/**
	 * Set the LibraryLocations on the specified Interpreter, by extracting the subordinate
	 * nodes from the specified 'lirbaryLocations' node.
	 */
	private static void setLibraryLocations(IInterpreterInstall Interpreter, Element libLocationsElement) {
		NodeList list = libLocationsElement.getChildNodes();
		int length = list.getLength();
		List locations = new ArrayList(length);
		for (int i = 0; i < length; ++i) {
			Node node = list.item(i);
			short type = node.getNodeType();
			if (type == Node.ELEMENT_NODE) {
				Element libraryLocationElement= (Element)node;
				if (libraryLocationElement.getNodeName().equals("libraryLocation")) { //$NON-NLS-1$
					locations.add(getLibraryLocation(libraryLocationElement));
				}
			}
		}	
		Interpreter.setLibraryLocations((LibraryLocation[])locations.toArray(new LibraryLocation[locations.size()]));
	}
	
	/**
	 * Removes the Interpreter from this container.
	 * 
	 * @param Interpreter Interpreter intall
	 */
	public void removeInterpreter(IInterpreterInstall Interpreter) {
		fInterpreterList.remove(Interpreter);
		fInvalidInterpreterList.remove(Interpreter);
		List list = (List) fInterTypeToInterMap.get(Interpreter.getInterpreterInstallType());
		if (list != null) {
			list.remove(Interpreter);
		}
	}
		
}
