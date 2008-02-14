package org.eclipse.dltk.tcl.internal.core.packages;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.launching.EnvironmentVariable;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.launching.ScriptLaunchUtil;
import org.eclipse.dltk.tcl.core.TclPlugin;
import org.eclipse.dltk.utils.DeployHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DLTKTclHelper {
	public static List getScriptOutput(Process process) {
		List elements = new ArrayList();
		try {
			final BufferedReader input = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			while (true) {
				String line = input.readLine();
				if (line == null) {
					break;
				}
				elements.add(line);
			}
			return elements;
		} catch (IOException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		return elements;
	}

	private static Process deployExecute(String installLocation,
			String[] arguments, EnvironmentVariable[] env) {
		File script = deploy();
		if (script == null) {
			return null;
		}

		File workingDir = script.getParentFile();
		InterpreterConfig config = ScriptLaunchUtil.createInterpreterConfig(
				script, workingDir, env);

		if (arguments != null) {
			config.addScriptArgs(arguments);
		}

		Process process = null;
		try {
			process = ScriptLaunchUtil.runScriptWithInterpreter(
					installLocation, config);
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		return process;
	}

	private static File deploy() {
		File script;
		try {
			script = DeployHelper.deploy(TclPlugin.getDefault(), "scripts/")
					.append("dltk.tcl").toFile();
		} catch (IOException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
			return null;
		}
		return script;
	}

	public static String[] getDefaultPath(File installLocation,
			EnvironmentVariable[] environment) {
		Process process = deployExecute(installLocation.getAbsolutePath(),
				new String[] { "get-paths" }, environment);
		List content = getScriptOutput(process);
		String[] autoPath = getAutoPath(content);
		for (int i = 0; i < autoPath.length; i++) {
			Path p = new Path(autoPath[i]);
			if (p.lastSegment().startsWith("tcl8.")) {
				return new String[] { autoPath[i] };
			}
		}
		return autoPath;
	}

	public static TclPackage[] getSrcs(File installLocation,
			EnvironmentVariable[] environment, String packageName) {
		Process process = deployExecute(installLocation.getAbsolutePath(),
				new String[] { "get-srcs", "-pkgs", packageName }, environment);
		List content = getScriptOutput(process);
		return getPackagePath(content);
	}

	private static boolean isElementName(Node nde, String name) {
		if (nde != null) {
			if (nde.getNodeType() == Node.ELEMENT_NODE) {
				if (name.equalsIgnoreCase(nde.getNodeName())) {
					return true;
				}
			}
		}
		return false;
	}

	private static String[] getAutoPath(List content) {
		String text = getXMLContent(content);
		Document document = getDocument(text);

		Set paths = new HashSet();
		if (document != null) {
			Element element = document.getDocumentElement();
			NodeList childNodes = element.getChildNodes();
			int len = childNodes.getLength();
			for (int i = 0; i < len; i++) {
				Node nde = childNodes.item(i);
				if (isElementName(nde, "path")) {
					Element el = (Element) nde;
					String path = el.getAttribute("name");
					if (path.length() > 0) {
						paths.add(path);
					}
				}
			}
		}
		return (String[]) paths.toArray(new String[paths.size()]);
	}

	public static class TclPackage {
		private String name;
		private Set paths = new HashSet();
		private Set dependencies = new HashSet();

		public TclPackage(String name) {
			this.name = name;
		}

		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TclPackage other = (TclPackage) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Set getPaths() {
			return paths;
		}

		public void setPaths(Set paths) {
			this.paths = paths;
		}

		public Set getDependencies() {
			return dependencies;
		}

		public void setDependencies(Set dependencies) {
			this.dependencies = dependencies;
		}
	};

	private static TclPackage[] getPackagePath(List content) {
		String text = getXMLContent(content);
		Document document = getDocument(text);

		Map packages = new HashMap();
		if (document != null) {
			Element element = document.getDocumentElement();
			NodeList childNodes = element.getChildNodes();
			int len = childNodes.getLength();
			for (int i = 0; i < len; i++) {
				Node nde = childNodes.item(i);
				if (isElementName(nde, "path")) {
					Element el = (Element) nde;
					NodeList elChilds = el.getChildNodes();
					for (int j = 0; j < elChilds.getLength(); j++) {
						Node pkgNde = elChilds.item(j);
						if (isElementName(pkgNde, "package")) {
							populatePackage(packages, pkgNde);
						}
					}
				}
			}
		}
		return (TclPackage[]) packages.values().toArray(
				new TclPackage[packages.size()]);
	}

	private static void populatePackage(Map packages, Node pkgNde) {
		Element pkg = (Element) pkgNde;
		String pkgName = pkg.getAttribute("name");
		TclPackage tclPackage = new TclPackage(pkgName);
		if (packages.containsKey(tclPackage)) {
			tclPackage = (TclPackage) packages.get(tclPackage);
		} else {
			packages.put(tclPackage, tclPackage);
		}
		NodeList childs = pkg.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node nde = childs.item(i);
			if (isElementName(nde, "source")) {
				Element el = (Element) nde;
				String name = el.getAttribute("name");
				IPath path = new Path(name).removeLastSegments(1);
				tclPackage.getPaths().add(path);
			} else if (isElementName(nde, "require")) {
				Element el = (Element) nde;
				String name = el.getAttribute("name");
				tclPackage.getDependencies().add(name);
			}
		}
	}

	private static Document getDocument(String text) {
		try {
			DocumentBuilder parser = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			parser.setErrorHandler(new DefaultHandler());
			Document document = parser.parse(new ByteArrayInputStream(text
					.getBytes()));
			return document;
		} catch (IOException e) {

		} catch (ParserConfigurationException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		} catch (SAXException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private static String getXMLContent(List content) {
		StringBuffer newList = new StringBuffer();
		for (Iterator iterator = content.iterator(); iterator.hasNext();) {
			String line = (String) iterator.next();
			if (!(line.startsWith("NOTICE") || line.startsWith("WARN")
					|| line.startsWith("ERROR") || line.startsWith("INFO") || line
					.startsWith("DEBUG"))) {
				newList.append(line).append("\n");
			}
		}
		return newList.toString();
	}

	public static Set getPackages(IInterpreterInstall install) {
		Process process = deployExecute(install.getInstallLocation()
				.getAbsolutePath(), new String[] { "get-pkgs" }, install
				.getEnvironmentVariables());
		List content = getScriptOutput(process);
		Set packages = new HashSet();
		TclPackage[] packagePath = getPackagePath(content);
		for (int i = 0; i < packagePath.length; i++) {
			packages.add(packagePath[i].getName());
		}
		return packages;
	}
}
