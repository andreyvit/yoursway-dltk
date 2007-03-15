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

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.javascript.internal.ui.text.completion.JavaScriptCompletionProcessor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.ContextInformation;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.javascript.core.internal.jsparser.lexer.ILexer;
import org.eclipse.wst.javascript.core.internal.jsparser.node.TCommenttok;
import org.eclipse.wst.javascript.core.internal.jsparser.node.Token;
import org.eclipse.wst.javascript.ui.internal.common.Debug;
import org.eclipse.wst.javascript.ui.internal.common.JSCommonUIMessages;
import org.eclipse.wst.javascript.ui.internal.common.JSCommonUIPluginImages;
import org.eclipse.wst.javascript.ui.internal.common.JSContentElementConstants;
import org.eclipse.wst.javascript.ui.internal.common.JSContentElementImpl;
import org.eclipse.wst.javascript.ui.internal.common.LexerCacheForJavaScript;
import org.eclipse.wst.javascript.ui.internal.common.contentassist.javadoc.JavaDoc2HTMLTextReader;
import org.eclipse.wst.javascript.ui.internal.common.preferences.JSCommonUIPreferenceNames;
import org.eclipse.wst.javascript.ui.internal.common.taginfo.JavaScriptTagInfoProvider;
import org.eclipse.wst.javascript.ui.internal.editor.JSEditorPlugin;
import org.eclipse.wst.javascript.ui.internal.editor.JSEditorPluginImageHelper;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.IndexedRegion;
import org.eclipse.wst.sse.core.internal.util.URIResolver;
import org.eclipse.wst.sse.ui.internal.IReleasable;
import org.eclipse.wst.sse.ui.internal.StructuredTextViewer;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMText;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class JavaScriptContentAssistProcessor implements
		IContentAssistProcessor, IPropertyChangeListener, IReleasable {
	static protected final Character chRightBrace = new Character(')');
	static protected final Character chRightBracket = new Character(']');
	static protected final HashMap follow3classes = new java.util.HashMap();
	static private HashMap fJavadocinfos;
	static protected JavaScriptTagInfoProvider tagInfoProvider = null;

	private static void addFunctionsNVars(String nextstr, int docpos,
			CompletionStringNode csn, Vector outval, FCContext fcc) {
		List vec = getFunctionNameList2(fcc);
		String nextstrUC = nextstr.toUpperCase();
		for (int i = 0; i < vec.size(); i++) {
			JSContentElementImpl coe = (JSContentElementImpl) vec.get(i);
			String cname = coe.getName();
			if (cname.toUpperCase().startsWith(nextstrUC)) {
				String fullstring = cname;
				if (coe.getType() == JSContentElementConstants.JS_FUNCTION)
					fullstring += "()"; //$NON-NLS-1$
				// ==> // String reststring =
				// fullstring.substring(nextstr.length());
				CustomCompletionProposal cap = new CustomCompletionProposal(
						fullstring // reststring
						, docpos - nextstr.length(), nextstr.length() // ,
																		// docpos,
																		// 0
						, fullstring.length() // , reststring.length()
						, csn.getImage(fcc), fullstring, null, /*
																 * additional
																 * info:
																 */
						JavaDoc2HTML(coe.getJavaDocString()));
				outval.add(cap);
			}
		}

	}

	/**
	 * return a list of the "names" of all tags with the given name.
	 */
	protected static FollowClass constructAllArrayFC(FCContext fcc) {

		Node topnode = fcc.getNode();
		FollowClass fc4 = new FollowClass(null);
		constructAllArrayFC(topnode, fc4);
		return fc4;
	}

	/**
	 * return a list of the "names" of all tags with the given name.
	 */
	protected static FollowClass constructAllArrayFC(Node topnode,
			FollowClass fc4) {
		if (topnode == null)
			return fc4;
		// ==> // NodeList nl = topnode.getChildNodes();
		CompletionStringNode csn2;
		// ==> // int nllen = nl.getLength();
		int idxChildNodes = 0;
		// ----------------------------------------------------------------------
		// (pa) 20021217
		// cmvc defect 235554
		// performance enhancement: using child.getNextSibling() rather than
		// nodeList(item) for O(n) vs. O(n*n)
		// ----------------------------------------------------------------------

		// for (int i=0; i<nllen; i++) {
		// Node node = nl.item(i);
		for (Node node = topnode.getFirstChild(); node != null; node = node
				.getNextSibling()) {
			int ntype = node.getNodeType();
			switch (ntype) {
			case Node.ATTRIBUTE_NODE:
			case Node.CDATA_SECTION_NODE:
			case Node.COMMENT_NODE:
				break;
			case Node.DOCUMENT_FRAGMENT_NODE:
			case Node.DOCUMENT_NODE:
			case Node.DOCUMENT_TYPE_NODE:
				break;
			case Node.ELEMENT_NODE: {
				Node ndId = node.getAttributes().getNamedItem("id"); //$NON-NLS-1$
				if (ndId != null) {
					String ntagname = node.getLocalName();
					String fcname = "unknowntagclass"; //$NON-NLS-1$
					if (ntagname.equalsIgnoreCase("input")) { //$NON-NLS-1$
						Node ndType = node.getAttributes().getNamedItem("type"); //$NON-NLS-1$
						if (ndType == null) {
							fcname = "INPUTtagclass"; //$NON-NLS-1$
						} else {
							fcname = "INPUTtagtype" + ndType.getNodeValue().toLowerCase() + "class"; //$NON-NLS-2$//$NON-NLS-1$
						}
					} else {
						fcname = ntagname.toUpperCase() + "tagclass"; //$NON-NLS-1$
					}
					csn2 = fc4.add2(ndId.getNodeValue(), fcname,
							";ie=y;ns=n;ecma=q;"); //$NON-NLS-1$
					csn2.getAttributesH().put("nodepointer", node); //$NON-NLS-1$
					if (Debug.jsDebugContextAssist)
						csn2.getAttributesH().put(
								CompletionStringNode.keyAdditionalDisplayText,
								"debug <" + ntagname); //$NON-NLS-1$
				}
				Node ndName = node.getAttributes().getNamedItem("name"); //$NON-NLS-1$
				if (ndName != null) {
					String ntagname = node.getLocalName();
					if (ntagname.equalsIgnoreCase("img") || //$NON-NLS-1$
							ntagname.equalsIgnoreCase("applet") || //$NON-NLS-1$
							ntagname.equalsIgnoreCase("embed") || //$NON-NLS-1$
							ntagname.equalsIgnoreCase("iframe") || //$NON-NLS-1$
							ntagname.equalsIgnoreCase("textarea") || //$NON-NLS-1$
							ntagname.equalsIgnoreCase("form") || //$NON-NLS-1$
							ntagname.equalsIgnoreCase("a") || //$NON-NLS-1$
							ntagname.equalsIgnoreCase("input") //$NON-NLS-1$
					) {
						// todo: add code. Also check other name tags IE lists
						String fcname = "unknowntagclass"; //$NON-NLS-1$
						if (ntagname.equalsIgnoreCase("input")) { //$NON-NLS-1$
							Node ndType = node.getAttributes().getNamedItem(
									"type"); //$NON-NLS-1$
							if (ndType == null) {
								fcname = "INPUTtagclass"; //$NON-NLS-1$
							} else {
								fcname = "INPUTtagtype" + ndType.getNodeValue().toLowerCase() + "class"; //$NON-NLS-2$//$NON-NLS-1$
							}
						} else {
							fcname = ntagname.toUpperCase() + "tagclass"; //$NON-NLS-1$
						}
						csn2 = fc4.add2(ndName.getNodeValue(), fcname,
								";ie=y;ns=n;ecma=q;"); //$NON-NLS-1$
						csn2.getAttributesH().put("nodepointer", node); //$NON-NLS-1$
						if (Debug.jsDebugContextAssist)
							csn2
									.getAttributesH()
									.put(
											CompletionStringNode.keyAdditionalDisplayText,
											"debug <" + ntagname); //$NON-NLS-1$
					}
				}
				constructAllArrayFC(node, fc4);
				idxChildNodes++;
			}
				break;
			case Node.ENTITY_NODE:
			case Node.ENTITY_REFERENCE_NODE:
			case Node.NOTATION_NODE:
			case Node.PROCESSING_INSTRUCTION_NODE:
				break;
			case Node.TEXT_NODE: {
				String nval = node.getNodeValue().trim();
				// only listed in .childNodes if it has some width and probably
				// has to have characters other than \r \n and space.
				if (nval.length() != 0) {
					idxChildNodes++;
				}
				// not listed in .children
			}
				break;
			}
		}
		return fc4;

	}

	/**
	 * return a list of the "names" of all tags with the given name.
	 */
	protected static FollowClass constructChildNodesFC(FCContext fcc) {

		Node topnode = fcc.getNode();
		FollowClass fc4 = new FollowClass(null);
		// ==> // NodeList nl = topnode.getChildNodes();
		CompletionStringNode csn2;
		// ==> // int nllen = nl.getLength();
		int idxChildNodes = 0;
		// ----------------------------------------------------------------------
		// (pa) 20021217
		// cmvc defect 235554
		// performance enhancement: using child.getNextSibling() rather than
		// nodeList(item) for O(n) vs. O(n*n)
		// ----------------------------------------------------------------------
		int i = 0;
		for (Node node = topnode.getFirstChild(); node != null; node = node
				.getNextSibling()) {
			// for (int i=0; i<nllen; i++) {
			// Node node = nl.item(i);
			int ntype = node.getNodeType();
			switch (ntype) {
			case Node.ATTRIBUTE_NODE:
			case Node.CDATA_SECTION_NODE:
			case Node.COMMENT_NODE:
				break;
			case Node.DOCUMENT_FRAGMENT_NODE:
			case Node.DOCUMENT_NODE:
			case Node.DOCUMENT_TYPE_NODE:
				break;
			case Node.ELEMENT_NODE: {
				String ntagname = node.getLocalName();
				String fcname = "unknowntagclass"; //$NON-NLS-1$
				if (ntagname.equalsIgnoreCase("input")) { //$NON-NLS-1$
					Node ndType = node.getAttributes().getNamedItem("type"); //$NON-NLS-1$
					if (ndType == null) {
						fcname = "INPUTtagclass"; //$NON-NLS-1$
					} else {
						fcname = "INPUTtagtype" + ndType.getNodeValue().toLowerCase() + "class"; //$NON-NLS-2$//$NON-NLS-1$
					}
				} else {
					fcname = ntagname.toUpperCase() + "tagclass"; //$NON-NLS-1$
				}
				csn2 = fc4.add2(Integer.toString(idxChildNodes) /* +fcname+"debug" */
				, fcname, ";ie=y;ns=y;ecma=q;"); //$NON-NLS-1$
				csn2.getAttributesH().put("nodeindexfind", Integer.toString(i)); //$NON-NLS-1$
				if (Debug.jsDebugContextAssist)
					csn2.getAttributesH().put(
							CompletionStringNode.keyAdditionalDisplayText,
							"debug <" + ntagname); //$NON-NLS-1$
				Node ndId = node.getAttributes().getNamedItem("id"); //$NON-NLS-1$
				if (ndId != null) {
					csn2 = fc4.add2(ndId.getNodeValue(), fcname,
							";ie=y;ns=y;ecma=q;"); //$NON-NLS-1$
					csn2.getAttributesH().put(
							"nodeindexfind", Integer.toString(i)); //$NON-NLS-1$
				}
				ndId = node.getAttributes().getNamedItem("name"); //$NON-NLS-1$
				if (ndId != null) {
					csn2 = fc4.add2(ndId.getNodeValue(), fcname,
							";ie=y;ns=y;ecma=q;"); //$NON-NLS-1$
					csn2.getAttributesH().put(
							"nodeindexfind", Integer.toString(i)); //$NON-NLS-1$
				}
				idxChildNodes++;
			}
				break;
			case Node.ENTITY_NODE:
			case Node.ENTITY_REFERENCE_NODE:
			case Node.NOTATION_NODE:
			case Node.PROCESSING_INSTRUCTION_NODE:
				break;
			case Node.TEXT_NODE: {
				String nval = node.getNodeValue().trim();
				// only listed in .childNodes if it has some width and probably
				// has to have characters other than \r \n and space.
				if (nval.length() != 0) {
					csn2 = fc4.add2(Integer.toString(idxChildNodes),
							"TextNodeobjclass", ";ie=y;ns=y;ecma=q;"); //$NON-NLS-2$//$NON-NLS-1$
					csn2.getAttributesH().put(
							"nodeindexfind", Integer.toString(i)); //$NON-NLS-1$
					if (Debug.jsDebugContextAssist)
						csn2.getAttributesH().put(
								CompletionStringNode.keyAdditionalDisplayText,
								"debug " + node.getNodeValue()); //$NON-NLS-1$
					idxChildNodes++;
				}
				// not listed in .children
			}
				break;
			}
			i++; // for namfing things
		}
		return fc4;

	}

	/**
	 * return a list of the "names" of all tags with the given name.
	 */
	protected static FollowClass constructChildrenFC(FCContext fcc) {

		Node topnode = fcc.getNode();
		FollowClass fc4 = new FollowClass(null);
		// ==> // NodeList nl = topnode.getChildNodes();
		CompletionStringNode csn2;
		// ==> // int nllen = nl.getLength();
		int idxChildNodes = 0;
		// ----------------------------------------------------------------------
		// (pa) 20021217
		// cmvc defect 235554
		// performance enhancement: using child.getNextSibling() rather than
		// nodeList(item) for O(n) vs. O(n*n)
		// ----------------------------------------------------------------------
		int i = 0;
		for (Node node = topnode.getFirstChild(); node != null; node = node
				.getNextSibling()) {
			// for (int i=0; i<nllen; i++) {
			// Node node = nl.item(i);
			int ntype = node.getNodeType();
			switch (ntype) {
			case Node.ATTRIBUTE_NODE:
			case Node.CDATA_SECTION_NODE:
			case Node.COMMENT_NODE:
				break;
			case Node.DOCUMENT_FRAGMENT_NODE:
			case Node.DOCUMENT_NODE:
			case Node.DOCUMENT_TYPE_NODE:
				break;
			case Node.ELEMENT_NODE: {
				String ntagname = node.getLocalName();
				String fcname = "unknowntagclass"; //$NON-NLS-1$
				if (ntagname.equalsIgnoreCase("input")) { //$NON-NLS-1$
					Node ndType = node.getAttributes().getNamedItem("type"); //$NON-NLS-1$
					if (ndType == null) {
						fcname = "INPUTtagclass"; //$NON-NLS-1$
					} else {
						fcname = "INPUTtagtype" + ndType.getNodeValue().toLowerCase() + "class"; //$NON-NLS-1$//$NON-NLS-2$
					}
				} else {
					fcname = ntagname.toUpperCase() + "tagclass"; //$NON-NLS-1$
				}
				csn2 = fc4.add2(Integer.toString(idxChildNodes) /* +fcname+"debug" */
				, fcname, ";ie=y;ns=y;ecma=q;"); //$NON-NLS-1$
				csn2.getAttributesH().put("nodeindexfind", Integer.toString(i)); //$NON-NLS-1$
				if (Debug.jsDebugContextAssist)
					csn2.getAttributesH().put(
							CompletionStringNode.keyAdditionalDisplayText,
							"debug <" + ntagname); //$NON-NLS-1$
				Node ndId = node.getAttributes().getNamedItem("id"); //$NON-NLS-1$
				if (ndId != null) {
					csn2 = fc4.add2(ndId.getNodeValue(), fcname,
							";ie=y;ns=y;ecma=q;"); //$NON-NLS-1$
					csn2.getAttributesH().put(
							"nodeindexfind", Integer.toString(i)); //$NON-NLS-1$
				}
				ndId = node.getAttributes().getNamedItem("name"); //$NON-NLS-1$
				if (ndId != null) {
					csn2 = fc4.add2(ndId.getNodeValue(), fcname,
							";ie=y;ns=y;ecma=q;"); //$NON-NLS-1$
					csn2.getAttributesH().put(
							"nodeindexfind", Integer.toString(i)); //$NON-NLS-1$
				}
				idxChildNodes++;
			}
				break;
			case Node.ENTITY_NODE:
			case Node.ENTITY_REFERENCE_NODE:
			case Node.NOTATION_NODE:
			case Node.PROCESSING_INSTRUCTION_NODE:
				break;
			case Node.TEXT_NODE: {
				String nval = node.getNodeValue().trim();
				// only listed in .childNodes if it has some width and probably
				// has to have characters other than \r \n and space.
				if (nval.length() != 0) {
					csn2 = fc4.add2(Integer.toString(idxChildNodes),
							"TextNodeobjclass", ";ie=y;ns=y;ecma=q;"); //$NON-NLS-1$//$NON-NLS-2$
					csn2.getAttributesH().put(
							"nodeindexfind", Integer.toString(i)); //$NON-NLS-1$
					if (Debug.jsDebugContextAssist)
						csn2.getAttributesH().put(
								CompletionStringNode.keyAdditionalDisplayText,
								"debug " + node.getNodeValue()); //$NON-NLS-1$
					idxChildNodes++;
				}
				// not listed in .children
			}
				break;
			}
			i++;
		}
		return fc4;

	}

	/**
	 * return a list of the "names" of all tags with the given name.
	 */
	protected static FollowClass constructUseBeanFC(FCContext fcc) {

		FollowClass fc4 = new FollowClass(null);

		NodeList nl = null;
		IndexedRegion ndScriptIn = fcc.getScriptNode();
		if (ndScriptIn instanceof Node) {
			Node nnn = (Node) ndScriptIn;
			nl = nnn.getOwnerDocument().getElementsByTagName(
					JSPNamespace.ElementName.USEBEAN);
		} else {
			return fc4;
		}
		// if (ndScriptIn instanceof org.w3c.dom.Document) { nl =
		// ((org.w3c.dom.Document)ndScriptIn).getElementsByTagName("script");
		// } else if (ndScriptIn instanceof org.w3c.dom.Element) { nl =
		// ((org.w3c.dom.Element)ndScriptIn).getElementsByTagName("script");
		// }
		int idx;
		for (idx = nl.getLength() - 1; idx >= 0; idx--) {
			Node ndUseBean = nl.item(idx);
			Node ndIDAttr = ndUseBean.getAttributes().getNamedItem(
					JSPNamespace.ATTR_NAME_ID);
			Node ndTypeAttr = ndUseBean.getAttributes().getNamedItem(
					JSPNamespace.ATTR_NAME_TYPE);
			if (ndTypeAttr == null)
				ndTypeAttr = ndUseBean.getAttributes().getNamedItem(
						JSPNamespace.ATTR_NAME_CLASS);
			if ((ndTypeAttr != null) && (ndIDAttr != null)) {
				String strType = ndTypeAttr.getNodeValue();
				String strID = ndIDAttr.getNodeValue();
				Hashtable ht = new Hashtable();
				ht.put(CompletionStringNode.keyFollowClass,
						"javaclassreflect." + strType); //$NON-NLS-1$
				ht.put(CompletionStringNode.keyWASJSPSupport, "y"); //$NON-NLS-1$
				String dt = strID + " " + strType; //$NON-NLS-1$
				ht.put(CompletionStringNode.keyDisplayText, dt);
				fc4.add(strID, ht);
			}
		}
		return fc4;
	}

	protected static String getAdditionalInfoText(FCContext fcc,
			FollowClass fc, CompletionStringNode csn) {
		String result = null;
		String strFCName = null;

		if ((fcc != null) && (fcc.getPath() != null))
			strFCName = fcc.getPath();

		if ((csn != null) && (csn.getCompletionString() != null)) {
			if (strFCName != null)
				strFCName = strFCName + "." + csn.getCompletionString(); //$NON-NLS-1$
			else
				strFCName = csn.getCompletionString();
		}

		result = getTagInfoProvider().getTagInfo(strFCName);

		return result;
	}

	/**
	 * given the existing string to complete, return a list of completions
	 * 
	 * @param estring
	 *            the string in the document at which the cursor is at the end
	 *            of and which we are to complete
	 * @param docPosition
	 *            the position in the document that the cursor and the end of
	 *            the estring are at.
	 */
	protected static Vector getCommentProposalVector(ITextViewer viewer,
			String estring, int docPosition, IndexedRegion idxnodeScript) {
		Vector retval = new Vector();
		if (estring.startsWith("/**")) { //$NON-NLS-1$
			IDocument doc = viewer.getDocument();
			String rootstring = ""; //$NON-NLS-1$
			try {
				// ==> // IRegion ir =
				// doc.getLineInformationOfOffset(docPosition);
				// ==> // int offLine = ir.getOffset();
				int off = docPosition - 1;
				while (off >= 0) {
					char ch = doc.getChar(off);
					if (ch == '@') {
						rootstring = doc.get(off, docPosition - off);
						break;
					} else if (!Character.isJavaIdentifierPart(ch)) {
						rootstring = ""; //$NON-NLS-1$
						break;
					}
					off--;
				}
				String strStartClass = "javadocstartclass"; //$NON-NLS-1$
				FollowClass fc = getFollowClass(strStartClass);
				Node nodeDoc = null;

				Hashtable ht0 = new Hashtable();
				ht0.put(CompletionStringNode.keyNsSupport, "y"); //$NON-NLS-1$
				ht0.put(CompletionStringNode.keyIESupport, "y"); //$NON-NLS-1$
				ht0.put(CompletionStringNode.keyWASJSPSupport, "y"); //$NON-NLS-1$
				FCContext fcc = new FCContext(nodeDoc, idxnodeScript, ht0,
						viewer);
				String nextstr = rootstring;
				String nextstrUC = nextstr.toUpperCase();
				Enumeration elements = fc.elements();
				while (elements.hasMoreElements()) {
					CompletionStringNode csn = (CompletionStringNode) elements
							.nextElement();
					String cs = csn.getCompletionString();
					if (cs.length() > 0 && cs.charAt(0) == '/') {
						// do nothing.
					} else if (cs.toUpperCase().startsWith(nextstrUC)) {
						// String strAddInfo = getAdditionalInfoText( fcc, fc,
						// csn );
						String key = cs.substring(1);
						String strAddInfo = (cs.charAt(0) == '@') ? (String) getJavadocinfo()
								.get(key)
								: null;
						// ==> // String reststring =
						// cs.substring(nextstr.length()) + " "; //$NON-NLS-1$
						if (cs.length() > 0) {
							CustomCompletionProposal cap = new CustomCompletionProposal(
									cs + " " // reststring //$NON-NLS-1$
									, docPosition - nextstr.length(),
									nextstr.length() + 1 // , docPosition, 0
									,
									cs.length() + 1 // , reststring.length()
									, /* image: */
									csn.getImage(fcc), csn
											.getDefaultDisplayText(), null,
									strAddInfo);
							retval.add(cap);
						}
					}
				} // endwhile
			} catch (BadLocationException exc) {
				return retval; // give up
			}
			return retval;
		}
		if (estring.startsWith("//")) { //$NON-NLS-1$
			// starts with //, but isn't yet a javadoc
			// Just add an option to turn it in to a javadoc.
			IDocument doc = viewer.getDocument();
			try {
				IRegion ir = doc.getLineInformationOfOffset(docPosition);
				int endoff = ir.getOffset() + ir.getLength();
				String strLine = doc.get(ir.getOffset(), ir.getLength());
				int offBegComment = docPosition - estring.length();
				CustomCompletionProposal cap = new CustomCompletionProposal(
						"/**"	+ strLine.substring(2 + offBegComment - ir.getOffset()) + "*/" //$NON-NLS-1$ //$NON-NLS-2$
						, offBegComment, endoff - offBegComment, estring
								.length() + 1, /* image: */
						null, "/**...", null, /* additional info: *///$NON-NLS-1$
						JSCommonUIMessages.Convert_to_a_JavaDoc_comment); //$NON-NLS-1$ 
				retval.add(cap);
			} catch (BadLocationException exc) {
				return retval; // give up
			}
		}
		if (estring.startsWith("/*")) { //$NON-NLS-1$
			// starts with /*, but isn't yet a javadoc
			// Just add an option to turn it in to a javadoc.
			CustomCompletionProposal cap = new CustomCompletionProposal(
					"/**" + estring.substring(2) //$NON-NLS-1$
					, docPosition - estring.length(), estring.length(), estring
							.length() + 1, /* image: */
					null, "/**...", null, /* additional info: *///$NON-NLS-1$
					JSCommonUIMessages.Convert_to_a_JavaDoc_comment); //$NON-NLS-1$
			retval.add(cap);
		}
		return retval;
	}

	/**
	 * return a list of descendent nodes that have the specified tag name
	 */
	protected static NodeList getDescendentsByName(Node node, String nodeName) {
		if (node == null) {
			return null;
		} else {
			NodeList nl = null;
			if (node instanceof Document) {
				nl = ((Document) node).getElementsByTagName(nodeName);
			} else if (node instanceof Element) {
				nl = ((Element) node).getElementsByTagName(nodeName);
			}
			return nl;
		}
	}

	/**
	 * parses the file and grabs the full string that we have to help complete.
	 */
	protected static String getExistingString(ITextViewer viewer,
			int documentPosition, Node node) {
		org.eclipse.jface.text.IDocument doc = viewer.getDocument();
		int beg = 0; // todo: set this to the offset of the beginning of the
						// javascript block
		if (documentPosition <= beg)
			return ""; // technically the test should be relative to the
						// beginning of the javascript block, but this will do
						// for now.//$NON-NLS-1$
		int iPos = documentPosition - 1;
		int iPos0 = iPos;
		String prefix = null;
		Stack stk = new Stack();
		try {
			// let's check if we're in a comment or javadoc...
			{
				LexerCacheForJavaScript lc = null;
				int offNew = documentPosition;
				if (viewer instanceof StructuredTextViewer) {
					if ((node != null) && (node instanceof IDOMText)) {
						IDOMText ti = (IDOMText) node;
						int offNode = ti.getStartOffset();
						offNew -= offNode;
						// todo: do we want to specify "" below or specify that
						// we are just browsing? We want to say that we're just
						// browsing, but how?
						lc = LexerCacheForJavaScript.getCache(node, ""); //$NON-NLS-1$
					}
				} else {
					lc = LexerCacheForJavaScript.getCache(doc, ""); //$NON-NLS-1$
				}
				if (lc != null) {
					try {
						ILexer lex = lc.getParser(offNew, /* startHere: */
						false);
						Token tk = lex.next();
						if (tk instanceof TCommenttok) {
							// we're requesting a completion in a comment. Tell
							// the caller.
							String txt = tk.getText();

							return txt.substring(0, offNew - tk.getLPOffset());
						}
					} catch (Exception exc) {
					}
				}
			}
			// we're not in a comment...
			// ==> // char firstch = doc.getChar(iPos);
			// todo: add a limit so that we don't scan backwards a huge amount
			// of time
			while (iPos >= beg) {
				if (iPos < (documentPosition - 100)) {
					// don't scan backwards a lot. If it's long, just give up.
					return null;
				}
				char ch = doc.getChar(iPos);
				if (Character.isJavaIdentifierPart(ch) || (ch == '.')) {
					// if ((ch=='.') && (iPosLastDot!=-1)) { iPosLastDot = iPos;
					// }
					iPos--;
				} else if (ch == ']') {
					stk.push(chRightBracket);
					iPos--;
				} else if (ch == '[') {
					if (stk.isEmpty()) {
						if (iPos == iPos0) {
							// todo: adjust the test above to allow for not just
							// the first char if the first char is a space type
							// character
							// todo: In this case we don't know if they want to
							// provide a number of a quoted string or a variable
							// whose value is the index. We need to decide on
							// the best action.
							// for now we'll assume they want to work locally.
							// We'll offer a quote character or a variable. If
							// we ever want to offer a number, we'll have to let
							// it parse backwards further
							// because "document[" can be completed with 'links'
							// or a formula, but not a number, but we can't know
							// that a number is not acceptable without scanning
							// back before the [ to recognize what comes before.
							break;
						} else {
							// in this case, unless we started with " or ', we
							// want to be local, but let's not try
							// to support internal quoted string completion for
							// arrays. In other words, we are local.
							break;
						}
					} else {
						Character xx = (Character) stk.pop();
						if (xx != chRightBracket)
							return null; // wrong thing popped
						iPos--;
					}
					// if (poppedout) break;
					// iPos--;
					// poppedout = true;
				} else if (ch == '(') {
					if (stk.isEmpty())
						break; // we will return whatever is in parenthesis
					Character xx = (Character) stk.pop();
					if (xx != chRightBrace)
						return null; // wrong thing popped
					iPos--;
				} else if (ch == ')') {
					stk.push(chRightBrace);
					iPos--;
				} else if ((ch == ',') || (ch == ' ')) {
					if (stk.isEmpty())
						break;
					iPos--;
				} else if ((ch == '\'') || (ch == '\"')) {
					if (stk.isEmpty())
						return null; // we don't complete empty strings
					// I guess we're just trying to parse past a parameter to an
					// array ref or method
					char ch0 = ch;
					while (true) {
						if (iPos < (documentPosition - 100))
							return null; // long, something probably wrong
						if (iPos <= beg)
							return null; // something probably wrong
						ch = doc.getChar(--iPos);
						if (ch == ch0)
							break;
					}
					// todo: we can test for \" and \' escaped characters
					iPos--;
				} else {
					break;
				}
			} // endwhile
			iPos++;
			prefix = doc.get(iPos, documentPosition - iPos);
		} catch (org.eclipse.jface.text.BadLocationException ble) {
			return null;
		}
		if (Debug.jsDebugContextAssist)
			System.out
					.println("the string to complete is: <<<" + prefix + ">>>"); //$NON-NLS-2$//$NON-NLS-1$
		return prefix;
	}

	private static java.util.HashMap getJavadocinfo() {
		if (fJavadocinfos == null) {
			fJavadocinfos = new java.util.HashMap();
			fJavadocinfos.put("author", JSCommonUIMessages.javadocinfo_author); //$NON-NLS-1$
			fJavadocinfos.put("param", JSCommonUIMessages.javadocinfo_param); //$NON-NLS-1$
			fJavadocinfos.put("return", JSCommonUIMessages.javadocinfo_return); //$NON-NLS-1$
			fJavadocinfos.put(
					"exception", JSCommonUIMessages.javadocinfo_exception); //$NON-NLS-1$
			fJavadocinfos.put("throws", JSCommonUIMessages.javadocinfo_throws); //$NON-NLS-1$
			fJavadocinfos.put("see", JSCommonUIMessages.javadocinfo_see); //$NON-NLS-1$
			fJavadocinfos.put("since", JSCommonUIMessages.javadocinfo_since); //$NON-NLS-1$
			fJavadocinfos
					.put("version", JSCommonUIMessages.javadocinfo_version); //$NON-NLS-1$
			fJavadocinfos.put(
					"deprecated", JSCommonUIMessages.javadocinfo_deprecated); //$NON-NLS-1$
		}
		return fJavadocinfos;
	}

	/**
	 * return a FollowClass class for the given statename. If you can't find a
	 * file specifying it, just return an empty state and generate an message to
	 * stdout.
	 */
	protected static FollowClass getFollowClass(String str) {
		FollowClass retval = (FollowClass) follow3classes.get("one." + str); //$NON-NLS-1$
		// System.out.println( "getfc1 "+str );
		if (retval == null) {
			// String javarprefix = "javaclassreflect.";//$NON-NLS-1$
			if (false /* str.startsWith(javarprefix) */
			) {
				// todo: reflection based code can be placed here. Especially
				// for dynamic classes. If the classes are truly dynamic though,
				// they shouldn't be cached... or the cache should be
				// invalidated if the class changes
			}
			// System.out.println( "getfc2 "+str );
			retval = new FollowClass(str);
			String bfname = str + ".fcrec"; //$NON-NLS-1$
			// String bdir = "c:\\temp\\followclasses\\"; //$NON-NLS-1$
			String bdirr = "fcs/js1/"; //$NON-NLS-1$
			InputStream inStream = JavaScriptContentAssistProcessor.class
					.getResourceAsStream(bdirr + bfname);
			if (inStream == null) {
				// not necessarily an error
				return retval;
			}
			java.io.Reader inn = new java.io.InputStreamReader(inStream);
			// java.io.Reader inn = new java.io.FileReader(bdir+bfname);
			retval.hydrate(new java.io.BufferedReader(inn));
			follow3classes.put("one." + str, retval); //$NON-NLS-1$
			if (Debug.jsDebugContextAssist)
				retval
						.add2(
								"debugtesting (turn off debugging before release)", "test0class", ";ie=y;ns=y;ecma=q;"); //$NON-NLS-1$//$NON-NLS-3$//$NON-NLS-2$
		}

		return retval;
	}

	/**
	 * return a list of the "names" of all functions with the given name.
	 */
	private static HashSet getFunctionNameList(FCContext fcc) {
		// note: comment this out if you don't want to include the package that
		// does much of the parsing.
		NodeList nl = null;
		IndexedRegion ndScriptIn = fcc.getScriptNode();
		if (ndScriptIn instanceof Node) {
			Node nnn = (Node) ndScriptIn;
			nl = nnn.getOwnerDocument().getElementsByTagName("script"); //$NON-NLS-1$
		} else {
			HashSet retval = new HashSet();
			if (fcc.itviewer instanceof StructuredTextViewer) {
				// don't support function search for jsp's
			} else {
				org.eclipse.jface.text.IDocument doc = fcc.itviewer
						.getDocument();
				LexerCacheForJavaScript lc = LexerCacheForJavaScript.getCache(
						doc, ""); //$NON-NLS-1$
				if (lc != null) {
					lc.parseForFunctionNVarNames(retval);
				}
			}
			return retval;
		}
		// if (ndScriptIn instanceof org.w3c.dom.Document) { nl =
		// ((org.w3c.dom.Document)ndScriptIn).getElementsByTagName("script");
		// } else if (ndScriptIn instanceof org.w3c.dom.Element) { nl =
		// ((org.w3c.dom.Element)ndScriptIn).getElementsByTagName("script");
		// }
		int idx;
		HashSet retval = new HashSet();
		for (idx = nl.getLength() - 1; idx >= 0; idx--) {
			Node ndScript = nl.item(idx);
			Node ndSrcAttr = ndScript.getAttributes().getNamedItem("src"); //$NON-NLS-1$
			Node ndLangAttr = ndScript.getAttributes().getNamedItem("language"); //$NON-NLS-1$
			if (ndSrcAttr != null) {
				// looking in a seperate file for the javascript to snoop

				// CachedJSResource cjsr = ResourceCache.get(
				// ndSrcAttr.getNodeValue().toLowerCase() );

				LexerCacheForJavaScript lc = LexerCacheForJavaScript.getCache(
						ndSrcAttr.getNodeValue().toLowerCase(), ""); //$NON-NLS-1$
				// todo: avoid all these if you are using the cached value. To
				// do this, we need to automatically update it when the file
				// changes... and we need to detect when we are putting it in
				// the cache for the first time.
				// todo: add support for loading JS files from http:// addresses
				// if this isn't happening now. (Doesn't seem to be.)
				// todo: check into caching this list beyond simply caching the
				// buffer. If we do this, we can probably afford to keep the
				// buffer around longer.
				// todo: see FileDocumentProvider for code that can invalidate
				// this buffer cache if changed on disk or in memory.
				ITextViewer v2 = fcc.getViewer();
				URIResolver ur = null;
				IStructuredModel sModel = null;
				try {
					sModel = StructuredModelManager.getModelManager()
							.getExistingModelForRead(v2.getDocument());
					if (sModel != null) {
						ur = sModel.getResolver();
					}
				} finally {
					if (sModel != null)
						sModel.releaseFromRead();
				}
				if (ur != null) {
					ur.getProject();
					// IProject iproj = ur.getProject();
					// if (iproj instanceof
					// org.eclipse.jdt.internal.core.JavaProject) {
					// org.eclipse.jdt.internal.core.JavaProject jproj =
					// (org.eclipse.jdt.internal.core.JavaProject)iproj;
					// String xx = null; try { xx =
					// iproj.getPersistentProperty(jproj.getClasspathPropertyName());
					// } catch (Exception exc) {};
					// System.out.println( "cp is "+xx );
					// }
					// JavaProject jproj = (JavaProject)iproj;
					// jproj.getClasspath();
					// jproj.getClasspathPropertyName();
					// P_BEANINFO_SEARCH_PATH
					// iproj.getPersistentProperty()

					// note: this approach to getting the contents of the .js
					// file only works if the .js file is local and the URI is
					// relative. A poll of a lot of web pages indicates this
					// happens rarely, but perhaps people will change their
					// habits if this feature is available. Also, it's possible
					// that the underlying resolving code might be updated to
					// resolve absolute URI's (/js/joe.js) also based on some
					// project settings indicating where the project will
					// eventually be placed.
					// org.eclipse.core.resources.IFile ifile =
					// iproj.getFile("main.js");

					// org.eclipse.core.resources.IFile ifile =
					// iproj.getFile(ndSrcAttr.getNodeValue());
					String srcName = ndSrcAttr.getNodeValue();
					// RemoteResourceCache cache =
					// ExtensionPlugin.getRemoteResourceCache();
					// String fileName =
					// cache.lookupOrCreate(ur.getLocationByURI(srcName));
					// if (fileName == null)
					// fileName = ur.getLocationByURI(srcName);
					String fileName = ur.getLocationByURI(srcName);
					String fileProtocol = "file:"; //$NON-NLS-1$
					if (fileName.startsWith(fileProtocol)) {
						fileName = fileName.substring(fileProtocol.length());
					}

					try {
						Reader is = new FileReader(fileName);
						char cbuf[] = new char[4096];
						StringBuffer s = new StringBuffer();
						int length = 0;
						while ((length = is.read(cbuf)) >= 0) {
							s.append(cbuf, 0, length);
						}
						String str = s.toString();
						lc.notifyChange(str, -1, 0, 0);
					} catch (Exception exc) {
					}
				}
				lc.parseForFunctionNVarNames(retval);
			} else if ((ndLangAttr == null)
					|| (ndLangAttr.getNodeValue().toLowerCase().indexOf(
							"javascript") >= 0)) { //$NON-NLS-1$
				Element elScript = (Element) ndScript;
				Node ndT = elScript.getFirstChild();
				if (ndT != null) {
					LexerCacheForJavaScript lc = LexerCacheForJavaScript
							.getCache(ndT, ""); //$NON-NLS-1$
					if (ndT != null) {
						String strScript = ndT.getNodeValue();
						lc.notifyChange(strScript, -1, 0, 0);
					}
					lc.parseForFunctionNVarNames(retval);
				}
				// todo: possibly move most of this code to fcc.
			}
		}
		return retval;
	}

	/**
	 * return a list of the "names" of all functions with the given name.
	 */
	private static Vector getFunctionNameList2(FCContext fcc) {
		// note: comment this out if you don't want to include the package that
		// does much of the parsing.
		NodeList nl = null;
		IndexedRegion ndScriptIn = fcc.getScriptNode();
		if (ndScriptIn instanceof Node) {
			Node nnn = (Node) ndScriptIn;
			nl = nnn.getOwnerDocument().getElementsByTagName("script"); //$NON-NLS-1$
		} else {
			Vector retval = new Vector();
			if (fcc.itviewer instanceof StructuredTextViewer) {
				// don't support function search for jsp's
			} else {
				IDocument doc = fcc.itviewer.getDocument();
				LexerCacheForJavaScript lc = LexerCacheForJavaScript.getCache(
						doc, ""); //$NON-NLS-1$
				if (lc != null) {
					return lc.parseForFunctionsNVariables();
				}
			}
			return retval;
		}
		// if (ndScriptIn instanceof org.w3c.dom.Document) { nl =
		// ((org.w3c.dom.Document)ndScriptIn).getElementsByTagName("script");
		// } else if (ndScriptIn instanceof org.w3c.dom.Element) { nl =
		// ((org.w3c.dom.Element)ndScriptIn).getElementsByTagName("script");
		// }
		int idx;
		// HashSet retval = new HashSet();
		Vector retval = new Vector();
		for (idx = nl.getLength() - 1; idx >= 0; idx--) {
			Node ndScript = nl.item(idx);
			Node ndSrcAttr = ndScript.getAttributes().getNamedItem("src"); //$NON-NLS-1$
			Node ndLangAttr = ndScript.getAttributes().getNamedItem("language"); //$NON-NLS-1$
			if (ndSrcAttr != null) {
				// CachedJSResource cjsr = ResourceCache.get(
				// ndSrcAttr.getNodeValue().toLowerCase() );

				LexerCacheForJavaScript lc = LexerCacheForJavaScript.getCache(
						ndSrcAttr.getNodeValue().toLowerCase(), ""); //$NON-NLS-1$
				// todo: avoid all these if you are using the cached value. To
				// do this, we need to automatically update it when the file
				// changes... and we need to detect when we are putting it in
				// the cache for the first time.
				// todo: add support for loading JS files from http:// addresses
				// if this isn't happening now. (Doesn't seem to be.)
				// todo: check into caching this list beyond simply caching the
				// buffer. If we do this, we can probably afford to keep the
				// buffer around longer.
				// todo: see FileDocumentProvider for code that can invalidate
				// this buffer cache if changed on disk or in memory.
				ITextViewer v2 = fcc.getViewer();
				URIResolver ur = null;
				IStructuredModel sModel = null;
				try {
					sModel = StructuredModelManager.getModelManager()
							.getExistingModelForRead(v2.getDocument());
					if (sModel != null) {
						ur = sModel.getResolver();
					}
				} finally {
					if (sModel != null)
						sModel.releaseFromRead();
				}
				if (ur != null) {
					// IProject iproj = ur.getProject();
					// if (iproj instanceof
					// org.eclipse.jdt.internal.core.JavaProject) {
					// org.eclipse.jdt.internal.core.JavaProject jproj =
					// (org.eclipse.jdt.internal.core.JavaProject)iproj;
					// String xx = null; try { xx =
					// iproj.getPersistentProperty(jproj.getClasspathPropertyName());
					// } catch (Exception exc) {};
					// System.out.println( "cp is "+xx );
					// }
					// JavaProject jproj = (JavaProject)iproj;
					// jproj.getClasspath();
					// jproj.getClasspathPropertyName();
					// P_BEANINFO_SEARCH_PATH
					// iproj.getPersistentProperty()

					// note: this approach to getting the contents of the .js
					// file only works if the .js file is local and the URI is
					// relative. A poll of a lot of web pages indicates this
					// happens rarely, but perhaps people will change their
					// habits if this feature is available. Also, it's possible
					// that the underlying resolving code might be updated to
					// resolve absolute URI's (/js/joe.js) also based on some
					// project settings indicating where the project will
					// eventually be placed.
					// org.eclipse.core.resources.IFile ifile =
					// iproj.getFile("main.js");

					// org.eclipse.core.resources.IFile ifile =
					// iproj.getFile(ndSrcAttr.getNodeValue());
					String srcName = ndSrcAttr.getNodeValue();
					// RemoteResourceCache cache =
					// ExtensionPlugin.getRemoteResourceCache();
					// String fileName =
					// cache.lookupOrCreate(ur.getLocationByURI(srcName));
					// if (fileName == null)
					// fileName = ur.getLocationByURI(srcName);
					String fileName = ur.getLocationByURI(srcName);
					String fileProtocol = "file:"; //$NON-NLS-1$
					if (fileName.startsWith(fileProtocol)) {
						fileName = fileName.substring(fileProtocol.length());
					}

					try {
						Reader is = new FileReader(fileName);
						char cbuf[] = new char[4096];
						StringBuffer s = new StringBuffer();
						int length = 0;
						while ((length = is.read(cbuf)) >= 0) {
							s.append(cbuf, 0, length);
						}
						String str = s.toString();
						lc.notifyChange(str, -1, 0, 0);
					} catch (Exception exc) {
					}
				}
				Vector vv = lc.parseForFunctionsNVariables();
				retval.addAll(vv);
			} else if ((ndLangAttr == null)
					|| (ndLangAttr.getNodeValue().toLowerCase().indexOf(
							"javascript") >= 0)) { //$NON-NLS-1$
				Element elScript = (Element) ndScript;
				Node ndT = elScript.getFirstChild();
				if (ndT != null) {
					LexerCacheForJavaScript lc = LexerCacheForJavaScript
							.getCache(ndT, ""); //$NON-NLS-1$
					if (ndT != null) {
						String strScript = ndT.getNodeValue();
						lc.notifyChange(strScript, -1, 0, 0);
					}
					Vector vv = lc.parseForFunctionsNVariables();
					retval.addAll(vv);
				}
				// todo: possibly move most of this code to fcc.
			}
		}
		return retval;
	}

	/**
	 * return a list of the "names" of all tags with the given name.
	 */
	private static HashSet getNameList(FCContext fcc, String strKey) {
		Node node = fcc.getNode();
		if (strKey.equals("/imagename/")) { //$NON-NLS-1$
			// ie supports image name and id as per document.images[****]
			// ns supports image name, not id.
			return getNameList(node, "img", "name"); //$NON-NLS-2$//$NON-NLS-1$
		} else if (strKey.equals("/imageid/")) { //$NON-NLS-1$
			return getNameList(node, "img", "id"); //$NON-NLS-2$//$NON-NLS-1$
		} else if (strKey.equals("/anchorname/")) { //$NON-NLS-1$
			// ie doesn't seem to support name or id, only number
			// ns supports name, not id
			return getNameList(node, "a", "name"); //$NON-NLS-1$//$NON-NLS-2$
		} else if (strKey.equals("/formname/")) { //$NON-NLS-1$
			// ie supports by name or id
			// ns supports by name, not id
			return getNameList(node, "form", "name"); //$NON-NLS-2$//$NON-NLS-1$
		} else if (strKey.equals("/formid/")) { //$NON-NLS-1$
			return getNameList(node, "form", "id"); //$NON-NLS-2$//$NON-NLS-1$
		} else if (strKey.equals("/appletname/")) { //$NON-NLS-1$
			// ie supports name or id
			// ns does not support name or id, just number
			return getNameList(node, "applet", "name"); //$NON-NLS-2$//$NON-NLS-1$
		} else if (strKey.equals("/appletid/")) { //$NON-NLS-1$
			return getNameList(node, "applet", "id"); //$NON-NLS-2$//$NON-NLS-1$
		} else if (strKey.equals("/embedname/")) { //$NON-NLS-1$
			// ie supports by name but not id
			// ns is only by number
			return getNameList(node, "embed", "name"); //$NON-NLS-2$//$NON-NLS-1$
		} else if (strKey.equals("/pluginname/")) { //$NON-NLS-1$
			// ie seems to treat this like the /embedname/. It doesn't seem to
			// list plugins... or maybe I have none. But it does list my embed.
			// ns is by number only
			return getNameList(node, "embed", "name"); //$NON-NLS-2$//$NON-NLS-1$
		} else if (strKey.equals("/layername/")) { //$NON-NLS-1$
			// ie doesn't even have a document.layers object
			// ns is by name, not id.
			return getNameList(node, "layer", "name"); //$NON-NLS-2$//$NON-NLS-1$
		} else if (strKey.equals("/framename/")) { //$NON-NLS-1$
			// document.frames seems to largely be a window object, and less an
			// array.
			// frames only lists children of the given object. We can't support
			// this right now. We don't pass in context
			// ie lists iframes and frames by name and id
			// ns: lists by name but not id
			// but the only frames we can know about statically have to be a
			// child of
			// this window and we can't know much about that.
		} else if (strKey.equals("/functionname/")) { //$NON-NLS-1$
			// note: comment this out if you don't want to include the package
			// that does much of the parsing.
			// todo: this code probably should search other blocks too.
			// todo; this code probably should also somehow check included js
			// files.
			// todo: set this up to pick up variables also. Even locals one's in
			// context.
			return getFunctionNameList(fcc);
		} else {
			if (Debug.jsDebugContextAssist)
				System.out
						.println("we've been asked to expand " + strKey + " but support for that has not been coded"); //$NON-NLS-2$//$NON-NLS-1$
			// todo: add support somewhere for /elementname/
			// todo: add support for /globalvariable/ and /globalfunction/
			// todo: recheck support for document.forms and anchors, etc They
			// might be able to use the tagclass and DOM
			// todo: make sure the code handles the A tag in the dom. I think we
			// handled that differently.
		}
		return new HashSet();
	}

	/**
	 * return a list of the "names" of all tags with the given name.
	 */
	private static HashSet getNameList(Node node, String nodeName,
			String attrName) {
		HashSet retval = new HashSet();
		NodeList nl = getDescendentsByName(node, nodeName);
		if (nl != null) {
			for (int i = 0; i < nl.getLength(); i++) {
				Node nd = nl.item(i);
				Node nd2 = nd.getAttributes().getNamedItem(attrName);
				if (nd2 != null) {
					String st = nd2.getNodeValue();
					retval.add(st);
				}
			}
		}
		return retval;
	}

	/**
	 * given the existing string to complete, return a list of completions
	 * 
	 * @param estring
	 *            the string in the document at which the cursor is at the end
	 *            of and which we are to complete
	 * @param docPosition
	 *            the position in the document that the cursor and the end of
	 *            the estring are at.
	 */
	protected static Vector getProposalVector(ITextViewer viewer,
			String estring, int docPosition, IndexedRegion idxnodeScript) {
		Vector retval = new Vector();
		String strStartClass = "startclass"; //$NON-NLS-1$
		Node node = null;
		if (idxnodeScript instanceof Node) {
			node = (Node) idxnodeScript;
			String nodename = node.getNodeName();

			// check if current node is a jsp node
			if (nodename.equalsIgnoreCase("jsp:scriptlet") || nodename.equalsIgnoreCase("jsp:declaration") || nodename.equalsIgnoreCase("jsp:expression")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				strStartClass = "was_js_jsp_startclass"; //$NON-NLS-1$
			} else {
				// check if parent node is a jsp node
				Node ndParent = node.getParentNode();
				nodename = ndParent.getNodeName();
				if (nodename.equalsIgnoreCase("jsp:scriptlet") || nodename.equalsIgnoreCase("jsp:declaration") || nodename.equalsIgnoreCase("jsp:expression")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					strStartClass = "was_js_jsp_startclass"; //$NON-NLS-1$
				}
			}
		}

		FollowClass fc = getFollowClass(strStartClass);
		Node nodeDoc = null;
		if (idxnodeScript != null) {
			if (idxnodeScript instanceof Node) {
				Node node0 = (Node) idxnodeScript;
				Document doc = node0.getOwnerDocument();
				nodeDoc = doc;
			}
		}
		
		 
		 
		  
		Hashtable ht0 = new Hashtable();
		ht0.put(CompletionStringNode.keyNsSupport, "y"); //$NON-NLS-1$
		ht0.put(CompletionStringNode.keyIESupport, "y"); //$NON-NLS-1$
		ht0.put(CompletionStringNode.keyWASJSPSupport, "y"); //$NON-NLS-1$
		FCContext fcc = new FCContext(nodeDoc, idxnodeScript, ht0, viewer);
		getProposalVector("." + estring, docPosition, fc, retval, fcc); //$NON-NLS-1$
		return retval;
	}

	/**
	 * Given the existing string to complete, return a list of completions
	 * 
	 * @param estring
	 *            the string in the document at which the cursor is at the end
	 *            of and which we are to complete. This string is required to
	 *            begin with a . or a [. This means the caller might need to add
	 *            one.
	 * @param docpos
	 *            the position in the document that the cursor and the end of
	 *            the estring are at.
	 * @param fc
	 *            the followclass that we start our parse at.
	 * @param outval
	 *            is the vector to which this method should add
	 *            CompletionProposals The behavior we want is firstly to analyze
	 *            the right string. Here are the cases... ((Note: $ represents
	 *            the end of string marker.)) ((Note: ... represents arbitrary
	 *            text. .... represents a dot followed by arbitrary text.
	 * 
	 * 
	 * a) [$ In this case we really don't want to be called. There are so many
	 * possibilities. They might want something like [0] or ['images'] or
	 * [arbitraryexpression] The first two situations technically require that
	 * we know what is left of the [, but the final case is always possible. I
	 * think for the moment we will only support the final case. So, in this
	 * case we don't want to be called with anything other than ".$".
	 * 
	 * b) [blah$ In the case we should be able to detect which the three cases
	 * above is being attempted simply by looking at the first character to the
	 * right of the [. There is the issue of us determining if what they've
	 * typed is even valid though. For now though, I think we'll only handle the
	 * final case of just having an arbitrary expression to the right of the [
	 * So like case (a), we don't want to be called. We want the caller to have
	 * passed us ".blah$" instead.
	 * 
	 * c) [blahindex$ See (b). We'd like to treat blahindex as an arbitrary
	 * expression right now to avoid having to consider what occurs to the left.
	 * 
	 * d) [blahindex]$ In this case the next character can be a dot or a [ or
	 * the value to the left can be considered complete. I guess we could offer
	 * the choices of <complete> or "." or "[". I'd prefer to offer nothing
	 * though.
	 * 
	 * e) [blahindex][... Recurse starting with the index on that next [ for the
	 * next segment. The state that the next analysis begins with can be
	 * determined by possibly taking the union of all possible values for
	 * blahindex, or we can take a stab at analyzing blah index to determine
	 * what it's value is and what follow state maps to that.
	 * 
	 * f) [blahindex].... See (e) except that the next index points to that
	 * first dot.
	 * 
	 * g) .$ h) .blah$ i) .blahname$ These cases are pretty straight forward. In
	 * the case (i) we will probably chose to return nothing. Another
	 * possibility is to return "blahname"... and if also a syntactic
	 * possibility "blahnamelonger". This last comment deals with the
	 * possibility of having possible values like "location" and "locationColor"
	 * where one complete value is a substring of another.
	 * 
	 * j) .blahname[...]... Straight forward. Next segment to parse will begin
	 * with [. And we'll definitely consider the value "blahname" to figure out
	 * the followclass.
	 * 
	 * k) .blahname.... See (j) except the next segment begins with "." instead
	 * of "[".
	 * 
	 */
	protected static void getProposalVector(String oestring, int docpos,
			FollowClass fc, Vector outval, FCContext fcc) {
		int idx0 = 0;
		int idx1;
		if (oestring.length() == 0) {
			throw new RuntimeException(
					"getProposalVector: estring input parameter must have a length"); //$NON-NLS-1$
		}
		if (Debug.jsDebugContextAssist) {
			// System.out.println( "in getProposalVector( "+oestring+"
			// ,,"+fc.getName()+",,)" );
		}
		// todo: do a lot more checking for end of string in here. Don't need
		// index exceptions
		char startchar = oestring.charAt(0);
		// assert:
		String estring = oestring.substring(1);
		if (fc == null)
			return;
		if ((startchar == '[') || (startchar == '(')) {
			int popcnt = 1;
			idx1 = idx0;
			while (popcnt > 0) {
				int idx2 = strNextPos(estring, idx1, ')', ']');
				int idx3 = strNextPos(estring, idx1, '(', '[');
				// todo: enhance this to not be thrown off by quoted chars like
				// '(' or "]" within the area we're scanning
				idx1 = (idx2 == -1) ? idx3 : (idx3 == -1) ? idx2
						: (idx2 < idx3) ? idx2 : idx3;
				if (idx1 == -1)
					break; // not a matching number of brackets on the
							// remainder of this line
				popcnt = (idx2 == idx1) ? popcnt - 1 : popcnt + 1;
				idx1++; // skip it for the next pass
			}
			if (idx1 == -1) {
				// case (a), (b), (c) above. We prefer that the caller not call
				// us
				// for this.
				throw new RuntimeException(
						"caller passed the context parser too much file"); //$NON-NLS-1$
			} else {
				idx1--; // we over indexed, so correct it to point to that last
						// ]
				// case (d), (e), (f).
			}
		} else {
			int idx2 = estring.indexOf('.', idx0);
			int idx3 = strNextPos(estring, idx0, '(', '[');
			idx1 = (idx2 == -1) ? idx3 : (idx3 == -1) ? idx2
					: (idx2 < idx3) ? idx2 : idx3;
		}
		// case (a), (b) and (c) eliminated.
		// idx1 points to ] for cases (d)(e)(f)
		// idx1 == -1 for cases (g)(h)(i) ( .$, .blah$, .blahindex$ )
		// idx1 points to [ for case (j)
		// idx1 points to . for case (k)

		if (idx1 < 0) {
			// cases (g)(h)(i) ( .$, .blah$, .blahindex$ )
			String nextstr = estring.substring(idx0);
			Enumeration elements = fc.elements();
			while (elements.hasMoreElements()) {
				CompletionStringNode csn = (CompletionStringNode) elements
						.nextElement();
				String cs = csn.getCompletionString();
				if (cs.length() > 0 && cs.charAt(0) == '/') {
					if (cs.equals("/number/")) { //$NON-NLS-1$
						// syntactically won't work
					} else if (cs.equals("/childnodes/")) { //$NON-NLS-1$
						FollowClass fc3 = constructChildNodesFC(fcc);
						getProposalVector(oestring, docpos, fc3, outval,
								new FCContext(csn, fcc));
					} else if (cs.equals("/usebeanvar/")) { //$NON-NLS-1$
						FollowClass fc3 = constructUseBeanFC(fcc);
						getProposalVector(oestring, docpos, fc3, outval,
								new FCContext(csn, fcc));
					} else if (cs.equals("/children/")) { //$NON-NLS-1$
						FollowClass fc3 = constructChildrenFC(fcc);
						getProposalVector(oestring, docpos, fc3, outval,
								new FCContext(csn, fcc));
					} else if (cs.equals("/allid/")) { //$NON-NLS-1$
						FollowClass fc3 = constructAllArrayFC(fcc);
						getProposalVector(oestring, docpos, fc3, outval,
								new FCContext(csn, fcc));
					} else if (cs.equals("/functionname/")) { //$NON-NLS-1$
						addFunctionsNVars(nextstr, docpos, csn, outval, fcc);
					} else {
						String nextstrUC = nextstr.toUpperCase();
						Iterator iter = getNameList(fcc, cs).iterator();
						while (iter.hasNext()) {
							String strImageName = (String) iter.next();
							if (strImageName.toUpperCase()
									.startsWith(nextstrUC)) {
								// ==> // String reststring =
								// strImageName.substring(nextstr.length());
								CustomCompletionProposal cap = new CustomCompletionProposal(
										strImageName // reststring
										, docpos - nextstr.length(),
										nextstr.length() // , docpos, 0
										,
										strImageName.length() // ,
																// reststring.length()
										, csn.getImage(fcc), strImageName,
										null, /* additional info: */
										null);
								outval.add(cap);
							}
						}
					}
				} else if (cs.equals("#isa")) { //$NON-NLS-1$
					// also parse here with another class
					FollowClass fc2 = getFollowClass(csn.getFCName());
					getProposalVector(oestring, docpos, fc2, outval,
							new FCContext(csn, fcc));
				} else if (cs.toUpperCase().startsWith(nextstr.toUpperCase())) {
					// String strADText =
					// (String)csn.getAttributesH().get(CompletionStringNode.keyAdditionalDisplayText);
					// if (strADText==null) strADText = ""; //$NON-NLS-1$
					String strAddInfo = getAdditionalInfoText(fcc, fc, csn);
					if (cs.length() > 0
							&& (Character.isDigit(cs.charAt(0)) || (cs
									.indexOf(' ') >= 0))) {
						// needs to be quoted
						CustomCompletionProposal cap = new CustomCompletionProposal(
								"['" + cs + "']" //$NON-NLS-2$//$NON-NLS-1$
								, docpos - (nextstr.length() + 1), nextstr
										.length() + 1,
								cs.length() + 4
								// , csn.getImage(fcc), cs + " " +strADText
								// //$NON-NLS-1$
								, csn.getImage(fcc), csn
										.getDefaultDisplayText(), null,
								strAddInfo);
						outval.add(cap);
					} else {
						// ==> // String reststring =
						// cs.substring(nextstr.length());
						if (cs.length() > 0) {
							CustomCompletionProposal cap = new CustomCompletionProposal(
									cs // reststring
									,
									docpos - nextstr.length(),
									nextstr.length() // , docpos, 0
									,
									cs.length() // , reststring.length()
									// , csn.getImage(fcc), cs + " " +strADText
									// //$NON-NLS-1$
									, csn.getImage(fcc), csn
											.getDefaultDisplayText(), null,
									strAddInfo);
							outval.add(cap);
						}
					}
				}
			} // endwhile
		} else if (estring.charAt(idx1) == ')') {
			// todo: test this
			if ((idx1 + 1) == estring.length()) {
				// case (d) [blahindex]$
				return; // nothing to suggest until they give us one more
				// character as a clue. And in fact they might be done.
			}
			getProposalVector(estring.substring(idx1 + 1), docpos, fc, outval,
					fcc);
		} else if (estring.charAt(idx1) == ']') {
			// cases (d), (e), (f) ([blahindex]$ [blahindex][ [blahindex].)
			String nextstr = estring.substring(idx0, idx1);
			if ((idx1 + 1) == estring.length()) {
				// case (d) [blahindex]$
				return; // nothing to suggest until they give us one more
				// character as a clue. And in fact they might be done.
			}
			HashSet tHashSet = new HashSet();
			char ch1 = nextstr.charAt(0);
			int intt = -1;
			try {
				intt = Integer.parseInt(nextstr.substring(0, idx1));
			} catch (Exception exc) {
			}
			if ((ch1 == '\'') || (ch1 == '\"') || (intt >= 0)) {
				String ch1str = ((ch1 == '\'') || (ch1 == '\"')) ? String
						.valueOf(ch1) : ""; //$NON-NLS-1$
				Enumeration elements = fc.elements();
				while (elements.hasMoreElements()) {
					CompletionStringNode csn = (CompletionStringNode) elements
							.nextElement();
					String cs = csn.getCompletionString();
					boolean match = false;
					if (cs.charAt(0) == '/') {
						if (cs.equals("/number/")) { //$NON-NLS-1$
							int nint = -1;
							try {
								nint = Integer.parseInt(nextstr);
							} catch (Exception x) {
							}
							match = (nint >= 0);
							// it's not worth the code for check for a quoted
							// number like '0' or "1".
						} else if (cs.equals("/childnodes/")) { //$NON-NLS-1$
							// also parse here with another class
							FollowClass fc5 = constructChildNodesFC(fcc);
							getProposalVector(oestring, docpos, fc5, outval,
									new FCContext(csn, fcc));
							// match = false;
						} else if (cs.equals("/usebeanvar/")) { //$NON-NLS-1$
							FollowClass fc5 = constructUseBeanFC(fcc);
							getProposalVector(oestring, docpos, fc5, outval,
									new FCContext(csn, fcc));
						} else if (cs.equals("/children/")) { //$NON-NLS-1$
							// also parse here with another class
							FollowClass fc5 = constructChildrenFC(fcc);
							getProposalVector(oestring, docpos, fc5, outval,
									new FCContext(csn, fcc));
							// match = false;
						} else if (cs.equals("/allid/")) { //$NON-NLS-1$
							FollowClass fc5 = constructAllArrayFC(fcc);
							getProposalVector(oestring, docpos, fc5, outval,
									new FCContext(csn, fcc));
							// match = false;
							// ////////////////////////////////////////////////////
							// } else if (cs.equals("/functionname/"))
							// {//$NON-NLS-1$
							// addFunctionsNVars(nextstr,docpos,csn,outval,fcc);
							// // removed because don't need to explicitly check
							// for function names. We just need to know *if*
							// there is a completion there, not what it is.
							// getNameList() in the } else { clause below
							// handles that.
							// //////////////////////////////////////////////////
						} else {
							// todo: maybe we don't really need to do this
							// check, maybe just
							// assume that it's a name of an image (or whatever
							// we're checking for)
							// since it's probably
							// possible to dynamically create images and name
							// them.
							HashSet imageNames = getNameList(fcc, cs);
							String strStripped = nextstr.substring(ch1str
									.length(), nextstr.length()
									- ch1str.length());
							match = imageNames.contains(strStripped);
						}
					} else if (cs.equals("#isa")) { //$NON-NLS-1$
						// also parse here with another class
						FollowClass fc2 = getFollowClass(csn.getFCName());
						getProposalVector(oestring, docpos, fc2, outval,
								new FCContext(csn, fcc));
						// match = false;
					} else {
						match = (ch1str + cs + ch1str)
								.equalsIgnoreCase(nextstr);
						if (cs.indexOf("()") > 0) //$NON-NLS-1$
							match = false; // we are very unlikely to specify a
											// method or function via []
											// notation.//$NON-NLS-1$
					}
					// if (match) tHashSet.add( csn.getFCName());
					if (match)
						tHashSet.add(csn);
				}
			} else {
				// case (e) & (f) [blahindex].... [blahindex][...
				// this appears to be a formula inside the [], so anything is
				// possible as a follow
				// class.
				Enumeration enumeration = fc.elements();
				while (enumeration.hasMoreElements()) {
					CompletionStringNode csn = (CompletionStringNode) enumeration
							.nextElement();
					String cs = csn.getCompletionString();
					boolean match = false;
					if (cs.charAt(0) == '/') {
						if (cs.equals("/number/")) { //$NON-NLS-1$
							match = true;
						} else if (cs.equals("/childnodes/")) { //$NON-NLS-1$
							FollowClass fc5 = constructChildNodesFC(fcc);
							getProposalVector(oestring, docpos, fc5, outval,
									new FCContext(csn, fcc));
							// match = true;
						} else if (cs.equals("/usebeanvar/")) { //$NON-NLS-1$
							FollowClass fc5 = constructUseBeanFC(fcc);
							getProposalVector(oestring, docpos, fc5, outval,
									new FCContext(csn, fcc));
							// match = true;
						} else if (cs.equals("/children/")) { //$NON-NLS-1$
							FollowClass fc5 = constructChildrenFC(fcc);
							getProposalVector(oestring, docpos, fc5, outval,
									new FCContext(csn, fcc));
							// match = true;
						} else if (cs.equals("/allid/")) { //$NON-NLS-1$
							FollowClass fc5 = constructAllArrayFC(fcc);
							getProposalVector(oestring, docpos, fc5, outval,
									new FCContext(csn, fcc));
							// match = true;
						} else /* if (cs.equals("/imagename/")) */{
							match = true;
						}
					} else if (cs.equals("#isa")) { //$NON-NLS-1$
						// also parse here with another class
						FollowClass fc2 = getFollowClass(csn.getFCName());
						getProposalVector(oestring, docpos, fc2, outval,
								new FCContext(csn, fcc));
						// match = false;
					} else {
						// just a string, but since the formula could generate
						// that string, it's a possibility too.
						match = true;
						if (cs.indexOf("()") > 0) //$NON-NLS-1$
							match = false; // we are very unlikely to specify a
											// method or function via []
											// notation.//$NON-NLS-1$
					}
					// if (match) tHashSet.add( csn.getFCName());
					if (match)
						tHashSet.add(csn);
				} // endwhile
			}
			Iterator iter2 = tHashSet.iterator();
			while (iter2.hasNext()) {
				CompletionStringNode csn = (CompletionStringNode) iter2.next();
				FCContext fccNew = new FCContext(csn, fcc);
				FollowClass fc2 = getFollowClass(csn.getFCName());
				getProposalVector(estring.substring(idx1 + 1), docpos, fc2,
						outval, fccNew);
			}
		} else if (estring.charAt(idx1) == '(') {
			String nextstr = estring.substring(idx0, idx1 + 1);
			String nextstrUC = nextstr.toUpperCase();
			Enumeration elements = fc.elements();
			while (elements.hasMoreElements()) {
				CompletionStringNode csn = (CompletionStringNode) elements
						.nextElement();
				String cs = csn.getCompletionString();
				if (cs.toUpperCase().startsWith(nextstrUC)) {
					FCContext fccNew = new FCContext(csn, fcc);
					FollowClass fc2 = getFollowClass(csn.getFCName());
					getProposalVector(estring.substring(idx1), docpos, fc2,
							outval, fccNew);
				}
			}
		} else {
			// cases (j) & (k) (.blahname[...]... .blahname....
			String nextstr = estring.substring(idx0, idx1);
			Enumeration elements = fc.elements();
			while (elements.hasMoreElements()) {
				CompletionStringNode csn = (CompletionStringNode) elements
						.nextElement();
				String cs = csn.getCompletionString();
				boolean match = false;
				if (cs.length() > 0 && cs.charAt(0) == '/') {
					if (cs.equals("/number/")) { //$NON-NLS-1$
						int nint = -1;
						try {
							nint = Integer.parseInt(nextstr);
						} catch (Exception x) {
						}
						match = (nint >= 0);
					} else if (cs.equals("/childnodes/")) { //$NON-NLS-1$
						FollowClass fc5 = constructChildNodesFC(fcc);
						getProposalVector(oestring, docpos, fc5, outval,
								new FCContext(csn, fcc));
						// match = true;
					} else if (cs.equals("/usebeanvar/")) { //$NON-NLS-1$
						FollowClass fc5 = constructUseBeanFC(fcc);
						getProposalVector(oestring, docpos, fc5, outval,
								new FCContext(csn, fcc));
						// match = true;
					} else if (cs.equals("/children/")) { //$NON-NLS-1$
						FollowClass fc5 = constructChildrenFC(fcc);
						getProposalVector(oestring, docpos, fc5, outval,
								new FCContext(csn, fcc));
						// match = true;
					} else if (cs.equals("/allid/")) { //$NON-NLS-1$
						FollowClass fc5 = constructAllArrayFC(fcc);
						getProposalVector(oestring, docpos, fc5, outval,
								new FCContext(csn, fcc));
						// match = true;
					} else {
						// todo: maybe we don't really need to do this check,
						// maybe just
						// assume that it's a name of an image (if this is an
						// image array)
						// since it's probably possible to dynamically create
						// images and name them.
						HashSet imageNames = getNameList(fcc, cs);
						match = imageNames.contains(nextstr);
					}
				} else if (cs.equals("#isa")) { //$NON-NLS-1$
					// also parse here with another class
					FollowClass fc2 = getFollowClass(csn.getFCName());
					getProposalVector(oestring, docpos, fc2, outval,
							new FCContext(csn, fcc));
					// match = false;
				} else {
					if (cs.equalsIgnoreCase(nextstr)) {
						match = true;
					}
				}
				if (match) {
					FollowClass fc2 = getFollowClass(csn.getFCName());
					getProposalVector(estring.substring(idx1), docpos, fc2,
							outval, new FCContext(csn, fcc));
				}
			}
		}
		return;
	}

	/**
	 * Gets the tagInfoProvider.
	 * 
	 * @return Returns javascripttaginfoprovider
	 */
	protected static JavaScriptTagInfoProvider getTagInfoProvider() {
		if (tagInfoProvider == null)
			tagInfoProvider = new JavaScriptTagInfoProvider();

		return tagInfoProvider;
	}

	// static private final DelayTimer timerClearFCCache;

	// static {
	// org.eclipse.swt.widgets.Listener list = new
	// org.eclipse.swt.widgets.Listener() {
	// public void handleEvent( org.eclipse.swt.widgets.Event ev ) {
	// follow3classes.clear();; }
	// };
	// timerClearFCCache = new DelayTimer();
	// timerClearFCCache.start(list,15000);
	// timerClearFCCache.stop(); // we just started it to tell it who our
	// listener is and our timeout period.
	// }

	// todo: finish specifying the follow classes for properties of the IE dom
	// types
	// todo: make sure .prototype is avail
	// todo: inside a constructor "this" refers to the object being created...
	// also in methods

	public static String JavaDoc2HTML(String in) {
		if (in == null)
			return null;
		// code copied from JavaDocCommentReader
		int fCurrPos = 3;
		int fEndPos = in.length() - 2;
		boolean fWasNewLine = false;
		StringBuffer sb = new StringBuffer();
		while (fCurrPos < fEndPos) {
			char ch;
			if (fWasNewLine) {
				do {
					ch = in.charAt(fCurrPos++);
				} while (fCurrPos < fEndPos && Character.isWhitespace(ch));
				if (ch == '*') {
					if (fCurrPos < fEndPos) {
						do {
							ch = in.charAt(fCurrPos++);
						} while (ch == '*');
					} else {
						break;
					}
				}
			} else {
				ch = in.charAt(fCurrPos++);
			}
			fWasNewLine = (ch == '\n') || (ch == '\r');

			sb.append(ch);
		}

		Reader rdr0 = new StringReader(sb.toString());
		Reader rdr1 = new JavaDoc2HTMLTextReader(rdr0);
		String strHTM = ""; //$NON-NLS-1$
		try {
			char ca[] = new char[9000];
			int cnt = rdr1.read(ca, 0, 9000);
			strHTM = new String(ca, 0, cnt);
		} catch (IOException exc) {
		}
		return strHTM;
	}

	/**
	 * give the next position of either of the two characters starting at the
	 * given index of the given string
	 * 
	 * @param estring
	 *            the string to search
	 * @param sidx
	 *            index at which to start the search
	 * @param ch1
	 *            one of the characters to search for
	 * @param ch2
	 *            one of the characters to search for
	 */
	protected static int strNextPos(String estring, int sidx, char ch1, char ch2) {
		int idx1;
		int idx11 = estring.indexOf(ch1, sidx);
		int idx12 = estring.indexOf(ch2, sidx);
		if (idx11 == -1) {
			idx1 = idx12;
		} else if (idx12 == -1) {
			idx1 = idx11;
		} else {
			idx1 = (idx11 < idx12) ? idx11 : idx12;
		}
		return idx1;
	}

	// todo: David has expressed a desire not to have those double iconed
	// browser compatibility icons in order to be compatible with the rest of
	// the GUI. I think expressing browser compatibility info is important, but
	// I don't think it has to be via icons.
	// todo: Jason would like the default color scheme to have a distinctive
	// grey background since we're in another language.
	// todo: add a concept of class path so that we know where to find beans
	// referenced by the usebean tag.
	// todo: Jason would like the background end beyond the end of line to the
	// right edge of the window.
	// todo: Jason prefers that bold characters be the same width as unbolded
	// characters.
	// todo: In the preferences dialog don't optimize adjacent regions. Optimize
	// colors, not regions. As it is, some white space areas look are
	// categorized as keywords in that dialog.
	// todo: shorten the name of this class.
	// todo: support childNodes.urns and childNodes.tags and possibly
	// childnodes.item()
	// todo: support completions within javadoc comments

	protected char completionProposalAutoActivationCharacters[] = null;
	protected char contextInformationAutoActivationCharacters[] = null;
	protected String errorMessage = null;
	protected IPreferenceStore fPreferenceStore = null;

	/**
	 * 
	 */
	
	
	public JavaScriptContentAssistProcessor() {
		init();		
	}

	/**
	 * Return a list of proposed code completions based on the specified
	 * location within the document that corresponds to the current cursor
	 * position within the text-editor control.
	 * 
	 * @param documentPosition
	 *            a location within the document
	 * @return an array of code-assist items
	 */
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int documentPosition) {
		// clear any error messages from the last request
		errorMessage = null;
		if (false) {
			IContextInformation dummyTip = new ContextInformation(
					"tip:....", "Tip o'the day: int != Integer"); //$NON-NLS-2$//$NON-NLS-1$
			String strH0 = "/** \n * This is a good function \n * @param parmname1 this does soemthing  \n **/"; //$NON-NLS-1$
			String strH1 = JavaDoc2HTML(strH0);
			String proposedText = "System.out.println(\"Hello, world!\");"; //$NON-NLS-1$
			ICompletionProposal dummyProposal = new CustomCompletionProposal(
					proposedText,
					documentPosition,
					0,
					proposedText.length(),
					null,
					"Hello World Example", dummyTip, "<b>additional</b> info string that is long enough to span at least one line"); //$NON-NLS-1$//$NON-NLS-2$
			ICompletionProposal dummyProposal2 = new CustomCompletionProposal(
					proposedText, documentPosition, 0, proposedText.length(),
					null, "Hello World Example", dummyTip, strH1); //$NON-NLS-1$//$NON-NLS-2$
			return new ICompletionProposal[] { dummyProposal, dummyProposal2 };
		} else {
			org.w3c.dom.Node node = null;
			int startOffset = 0;
			//int endOffset=0;
			IndexedRegion indexedNode = null;
			if (viewer instanceof StructuredTextViewer)
			{
				indexedNode = ContentAssistUtils.getNodeAt(
						(StructuredTextViewer) viewer, documentPosition);
				startOffset=indexedNode.getStartOffset();
				//endOffset=indexedNode.getEndOffset();
			}	
			if (indexedNode instanceof Node)
				node = (Node) indexedNode;
			// node.getOwnerDocument();
			// System.out.println("The document is: " +
			// node.getOwnerDocument());
			// IDocument doc = viewer.getDocument();
			String prefix = getExistingString(viewer, documentPosition, node);
			if (prefix == null)
				return null;
			Vector props = null;
			if (prefix.startsWith("/")) { //$NON-NLS-1$
				props = getCommentProposalVector(viewer, prefix,
						documentPosition, indexedNode);
			} else {
				props = getProposalVector(viewer, prefix, documentPosition,
						indexedNode);
			}

			// get end tag
			Object o = computeXMLEndTagProposal(viewer, documentPosition,
					indexedNode, HTML40Namespace.ElementName.SCRIPT,
					JSCommonUIPluginImages.IMG_OBJ_GENERIC_TAG);
			if (o != null)
				props.add(o);

			java.util.Enumeration elements = props.elements();
			IStructuredModel sModel = null;
			ITextViewer v2 = viewer;
			URIResolver ur = null;
			
			try {
				sModel = StructuredModelManager.getModelManager()
						.getExistingModelForRead(v2.getDocument());
				if (sModel != null) {
					ur = sModel.getResolver();
				}
			} finally {
				if (sModel != null)
					sModel.releaseFromRead();
			}
			IDLTKProject dltkProject=null;
			IProject project=null;
			if (ur != null) {
				project = ur.getProject();
				IDLTKProject create = DLTKCore.create(project);
				if (create.exists()){
					dltkProject=create;
				}
			}
			IResource resource=project.getFile(ur.getFileBaseLocation());
			JavaScriptCompletionProcessor proc=new JavaScriptCompletionProcessor(null,new ContentAssistant(),IDocument.DEFAULT_CONTENT_TYPE,dltkProject,resource);
			
			String textContent = viewer.getDocument().get();
			char[] content=new char[indexedNode.getEndOffset()];
			for (int a=0;a<startOffset;a++)content[a]=' ';
			for (int a=startOffset;a<content.length;a++){
				content[a]=textContent.charAt(a);
			}						
			proc.setFakeModuleContext(new String(content));
			ICompletionProposal[] computedCompletionProposals = proc.computeCompletionProposals(viewer, documentPosition);
			ICompletionProposal[] result = null;
			// todo: put the one's most commonly/recently used first. Right now
			// we don't have access to that info. The caller does though.
			// timerClearFCCache.stop();
			if (false) {
				result = new CustomCompletionProposal[props.size()];
				for (int i = props.size(); i-- > 0;) {
					result[i] = (ICompletionProposal) props.elementAt(i);
				}
			} else {
				// todo: note the following code removes duplicates. See that
				// this is done correctly.
				java.util.SortedMap tm = new java.util.TreeMap();
				for (int i = props.size(); i-- > 0;) {
					ICompletionProposal cp = (ICompletionProposal) elements
							.nextElement();
					tm.put(cp.getDisplayString(), cp);
				}
				for (int i=0;i<computedCompletionProposals.length;i++){
					ICompletionProposal completionProposal = computedCompletionProposals[i];
					tm.put(completionProposal.getDisplayString(),completionProposal );
				}
				int tmsize = tm.values().size();
				result = new ICompletionProposal[tmsize];
				Iterator iter = tm.values().iterator();
				int i = 0;
				while (iter.hasNext()) {
					result[i++] = (ICompletionProposal) iter.next();
				}
			}

			// not sure if we want to dump all this or cache it, but we'll dump
			// it for now.
			// timerClearFCCache.restart();

			return result;
		}
	}

	/**
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeContextInformation(org.eclipse.jface.text.ITextViewer,
	 *      int)
	 */
	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int documentOffset) {
		return null;
	}

	/**
	 * A convenience method for getting the closing proposal given the contents
	 * (IndexedRegion) of a tag that is started, but possibly not ended
	 * 
	 * @param viewer
	 *            the text viewer
	 * @param documentPosition
	 *            the cursor position in the viewer
	 * @param indexedNode
	 *            the contents of the tag that is started but possibly not ended
	 * @param parentTagName
	 *            the tag on which you are checkin for an ending tag
	 * @param imagePath
	 *            relative content assist image path
	 * @return ICompletionProposal
	 */
	private ICompletionProposal computeXMLEndTagProposal(ITextViewer viewer,
			int documentPosition, IndexedRegion indexedNode,
			String parentTagName, String imagePath) {
		ICompletionProposal p = null;

		// check if tag is closed
		boolean hasEndTag = true;
		IDOMNode xnode = null;
		String tagName = ""; //$NON-NLS-1$
		if (indexedNode instanceof IDOMNode) {
			xnode = ((IDOMNode) indexedNode);
			// it's ended already...
			if (xnode.getEndStructuredDocumentRegion() != null)
				return null;
			IDOMNode styleNode = null;
			if (!xnode.getNodeName().equalsIgnoreCase(parentTagName))
				styleNode = (IDOMNode) xnode.getParentNode();
			if (styleNode != null) {
				tagName = styleNode.getNodeName();
				hasEndTag = (styleNode.getEndStructuredDocumentRegion() != null);
			}
		}

		// it's closed, don't add close tag proposal
		if (!hasEndTag) {

			// create appropriate close tag text
			String proposedText = "</" + tagName; // ResourceHandler
													// //$NON-NLS-1$
			// wants text
			// w/out
			// ending '>'
			// //$NON-NLS-1$
			String viewerText = viewer.getTextWidget().getText();
			if (viewerText.length() >= documentPosition
					&& viewerText.length() >= 2 && documentPosition >= 2) {
				String last2chars = viewerText.substring(documentPosition - 2,
						documentPosition);
				if (last2chars.endsWith("</")) //$NON-NLS-1$
					proposedText = tagName;
				else if (last2chars.endsWith("<")) //$NON-NLS-1$
					proposedText = "/" + tagName; //$NON-NLS-1$
			}

			// create proposal
			p = new CustomCompletionProposal(
					proposedText + ">", //$NON-NLS-1$
					documentPosition,
					0,
					proposedText.length() + 1,
					JSEditorPluginImageHelper.getInstance().getImage(imagePath), //$NON-NLS-1$
					NLS.bind(JSCommonUIMessages.End_with,
							(new Object[] { proposedText })), null, null, 1400); // XMLRelevanceConstants.R_END_TAG=1400
		}
		return p;
	}

	/**
	 * Returns the characters which when entered by the user should
	 * automatically trigger the presentation of possible completions.
	 * 
	 * @return the auto activation characters for completion proposal or
	 *         <code>null</code> if no auto activation is desired
	 */
	public char[] getCompletionProposalAutoActivationCharacters() {
		return completionProposalAutoActivationCharacters;
	}

	/**
	 * Returns the characters which when entered by the user should
	 * automatically trigger the presentation of context information.
	 * 
	 * @return the auto activation characters for presenting context information
	 *         or <code>null</code> if no auto activation is desired
	 */
	public char[] getContextInformationAutoActivationCharacters() {
		return contextInformationAutoActivationCharacters;
	}

	/**
	 * Returns a validator used to determine when displayed context information
	 * should be dismissed. May only return <code>null</code> if the processor
	 * is incapable of computing context information.
	 * 
	 * @return a context information validator, or <code>null</code> if the
	 *         processor is incapable of computing context information
	 */
	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}

	/**
	 * Return the reason why computeCompletionProposals was not able to find any
	 * completions.
	 * 
	 * @return an error message or null if no error occurred
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	protected IPreferenceStore getPreferenceStore() {
		if (fPreferenceStore == null)
			fPreferenceStore = JSEditorPlugin.getDefault().getPreferenceStore();
		// fPreferenceStore =
		// CommonPreferencesPlugin.getDefault().getPreferenceStore(ContentType.ContentTypeID_XML);

		return fPreferenceStore;
	}

	protected void init() {
		getPreferenceStore().addPropertyChangeListener(this);
		reinit();
	}

	public void release() {
		getPreferenceStore().removePropertyChangeListener(this);
	}

	public void propertyChange(PropertyChangeEvent event) {
		String property = event.getProperty();

		String autoProposeKey = JSCommonUIPreferenceNames.AUTO_PROPOSE;
		String autoProposeCodeKey = JSCommonUIPreferenceNames.AUTO_PROPOSE_CODE;
		if (property.compareTo(autoProposeKey) == 0
				|| property.compareTo(autoProposeCodeKey) == 0) {
			reinit();
		}
	}

	protected void reinit() {
		String autoProposeKey = JSCommonUIPreferenceNames.AUTO_PROPOSE;
		boolean doAuto = getPreferenceStore().getBoolean(autoProposeKey);
		if (doAuto) {
			String autoProposeCodeKey = JSCommonUIPreferenceNames.AUTO_PROPOSE_CODE;
			completionProposalAutoActivationCharacters = getPreferenceStore()
					.getString(autoProposeCodeKey).toCharArray();
		} else {
			completionProposalAutoActivationCharacters = null;
		}
	}
}
