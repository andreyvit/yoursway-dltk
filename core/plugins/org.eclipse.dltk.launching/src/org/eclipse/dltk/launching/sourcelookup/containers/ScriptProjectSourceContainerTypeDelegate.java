package org.eclipse.dltk.launching.sourcelookup.containers;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.sourcelookup.ISourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.AbstractSourceContainerTypeDelegate;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.launching.LaunchingMessages;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ScriptProjectSourceContainerTypeDelegate extends
		AbstractSourceContainerTypeDelegate {

	private static String SCRIPT_PROJECT = "scriptProject";
	
	public ISourceContainer createSourceContainer(String memento)
			throws CoreException {
		Node node = parseDocument(memento);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			if (SCRIPT_PROJECT.equals(element.getNodeName())) { //$NON-NLS-1$
				String string = element.getAttribute("name"); //$NON-NLS-1$
				if (string == null || string.length() == 0) {
					abort(LaunchingMessages.ScriptProjectSourceContainerTypeDelegate_5, null);
				}
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				IProject project = workspace.getRoot().getProject(string);

				IScriptProject scriptProject = DLTKCore.create(project);
				return new ScriptProjectSourceContainer(scriptProject);
			}
			abort(LaunchingMessages.ScriptProjectSourceContainerTypeDelegate_6, null);
		}
		abort(LaunchingMessages.ScriptProjectSourceContainerTypeDelegate_7, null);
		return null;
	}

	public String getMemento(ISourceContainer container) throws CoreException {
		ScriptProjectSourceContainer project = (ScriptProjectSourceContainer) container;
		Document document = newDocument();
		Element element = document.createElement(SCRIPT_PROJECT);
		element.setAttribute("name", project.getName()); //$NON-NLS-1$
		document.appendChild(element);
		return serializeDocument(document);
	}

}
