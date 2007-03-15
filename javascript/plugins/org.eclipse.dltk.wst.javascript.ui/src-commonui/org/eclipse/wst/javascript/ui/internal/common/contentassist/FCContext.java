/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.javascript.ui.internal.common.contentassist;



import java.util.Hashtable;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.wst.sse.core.internal.provisional.IndexedRegion;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class FCContext {

	protected Hashtable ht;
	protected Node node;
	protected IndexedRegion nodeScript = null;
	protected ITextViewer itviewer = null;
	protected String path = null;

	public FCContext(CompletionStringNode csn, FCContext fccOld) {
		this.node = fccOld.node;
		this.nodeScript = fccOld.nodeScript;
		this.itviewer = fccOld.itviewer;

		String newPath = computePath(csn, fccOld);
		setPath(newPath);

		String ntf = (String) csn.getAttributesH().get("nodetagfind");//$NON-NLS-1$
		if ((ntf != null) && (ntf.length() > 0)) {
			NodeList nl = JavaScriptContentAssistProcessor.getDescendentsByName(fccOld.node, ntf);
			node = ((nl != null) && (nl.getLength() > 0)) ? nl.item(0) : null;
		}
		ntf = (String) csn.getAttributesH().get("nodeindexfind");//$NON-NLS-1$
		if ((ntf != null) && (ntf.length() > 0)) {
			int idx = Integer.parseInt(ntf);
			node = fccOld.node.getChildNodes().item(idx);
		}

		this.ht = (Hashtable) fccOld.ht.clone();
		// todo: later we might implement this to store the new follow class too.  All the info we need is here and it seems silly to pass two parameters around all the time.  We could consolidate.
		Hashtable ht2 = csn.getAttributesH();
		String hval2 = (String) ht2.get(CompletionStringNode.keyIESupport);
		String hval = (String) ht.get(CompletionStringNode.keyIESupport);
		if (hval == null)
			hval = "q";//$NON-NLS-1$
		if (hval2 == null)
			hval2 = "q";//$NON-NLS-1$
		if (hval.equals("n") || hval2.equals("n")) { //$NON-NLS-1$ //$NON-NLS-2$
			ht.put(CompletionStringNode.keyIESupport, "n"); //$NON-NLS-1$
		}
		else if (hval.equals("q") || hval2.equals("q")) { //$NON-NLS-1$ //$NON-NLS-2$
			ht.put(CompletionStringNode.keyIESupport, "q"); //$NON-NLS-1$
			// already set to 'y'
		}
		hval2 = (String) ht2.get(CompletionStringNode.keyNsSupport);
		hval = (String) ht.get(CompletionStringNode.keyNsSupport);
		if (hval == null)
			hval = "q";//$NON-NLS-1$
		if (hval2 == null)
			hval2 = "q";//$NON-NLS-1$
		if (hval.equals("n") || hval2.equals("n")) { //$NON-NLS-1$ //$NON-NLS-2$
			ht.put(CompletionStringNode.keyNsSupport, "n"); //$NON-NLS-1$
		}
		else if (hval.equals("q") || hval2.equals("q")) { //$NON-NLS-1$ //$NON-NLS-2$
			ht.put(CompletionStringNode.keyNsSupport, "q"); //$NON-NLS-1$
			// already set to 'y'
		}
		hval2 = (String) ht2.get(CompletionStringNode.keyWASJSPSupport);
		hval = (String) ht.get(CompletionStringNode.keyWASJSPSupport);
		if (hval == null)
			hval = "q";//$NON-NLS-1$
		if (hval2 == null)
			hval2 = "q";//$NON-NLS-1$
		if (hval.equals("n") || hval2.equals("n")) { //$NON-NLS-1$ //$NON-NLS-2$
			ht.put(CompletionStringNode.keyWASJSPSupport, "n"); //$NON-NLS-1$
		}
		else if (hval.equals("q") || hval2.equals("q")) { //$NON-NLS-1$ //$NON-NLS-2$
			ht.put(CompletionStringNode.keyWASJSPSupport, "q"); //$NON-NLS-1$
			// already set to 'y'
		}
	}

	/**
	 * Computes the new current path
	 * @param oldPath String parent path
	 * @param addPath String new path to add
	 * 
	 * @return String new path
	 */
	private String computePath(String oldPath, String addPath) {
		int oldLength = oldPath.length();
		int addLength = addPath.length();

		// [] or oldPath[]
		// AFW right now i assume that all strings in the form /blah/ are probably []
		if ((addPath.charAt(0) == '/') && (addPath.charAt(addLength - 1) == '/')) {
			return (oldPath + "[]"); //$NON-NLS-1$
		}
		// oldPath
		if (addLength < 1) {
			return oldPath;
		}
		// oldPath.newPath
		if (oldLength > 0) {
			return (oldPath + "." + addPath); //$NON-NLS-1$
		}
		// newPath
		else
			return addPath;
	}


	protected String computePath(CompletionStringNode csn, FCContext fccOld) {
		String oldPath = null;
		String addPath = null;

		if (fccOld != null)
			oldPath = fccOld.getPath();
		else
			oldPath = ""; //$NON-NLS-1$

		if (csn != null)
			addPath = csn.getCompletionString();
		else
			addPath = ""; //$NON-NLS-1$

		return computePath(oldPath, addPath);
	}

	public FCContext(Node contextNode, IndexedRegion sn, Hashtable table, ITextViewer viewer) {
		this.node = contextNode;
		this.ht = table; // perhaps we should clone ht?
		this.nodeScript = sn;
		this.itviewer = viewer;
	}

	public String getAttribute(String attrname) {
		return (String) ht.get(attrname);
	}

	public Node getNode() {
		return node;
	}

	public IndexedRegion getScriptNode() {
		return nodeScript;
	}

	public ITextViewer getViewer() {
		return itviewer;
	}

	/**
	 * Gets the Path.
	 * @return Returns a String
	 */
	public String getPath() {
		if (path == null)
			path = ""; //$NON-NLS-1$
		return path;
	}

	/**
	 * Sets the Path.
	 * @param Path The Path to set
	 */
	public void setPath(String newPath) {
		if (newPath == null)
			path = ""; //$NON-NLS-1$
		else
			path = newPath;
	}

}
