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

import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.javascript.ui.internal.common.JSCommonUIPluginImages;
import org.eclipse.wst.javascript.ui.internal.editor.JSEditorPluginImageHelper;
import org.eclipse.wst.javascript.ui.internal.editor.JSEditorPluginImages;


class CompletionStringNode {

	private final String completionstring;
	private final String attributes;
	private Hashtable htAttributes = new Hashtable();
	//	private static final boolean usehash = true;
	static final String keyFollowClass = "fc";//$NON-NLS-1$
	static final String keyIESupport = "iesupport";//$NON-NLS-1$
	static final String keyNsSupport = "nssupport";//$NON-NLS-1$
	static final String keyECMASupport = "ecmasupport";//$NON-NLS-1$
	static final String keyShortGUIHelpText = "shortguihelptext";//$NON-NLS-1$
	static final String keyWASJSPSupport = "wasjspsupport";//$NON-NLS-1$
	static final String keyDisplayText = "displaytext";//$NON-NLS-1$
	static final String keyAdditionalDisplayText = "additionaldisplaytext";//$NON-NLS-1$
	static final String keyType = "type";//$NON-NLS-1$
	private static final String JAVADOCAT_TYPE = "javadocat"; //$NON-NLS-1$
	private static final String HTMLTAG_TYPE = "htmltag"; //$NON-NLS-1$
	private static final String METHOD_TYPE = "method"; //$NON-NLS-1$

	/**
	 * Creates a new node describing an parsing alternative.
	 *
	 * @param completionstring that can follow the dot in the previous construct.
	 * @param followclass string that describes the parse class that describes what can come after this
	 */
	public CompletionStringNode(String cs, String fc, String newAttributes) {
		completionstring = cs;
		if (true) {
			this.attributes = null;
			String ns = "q";//$NON-NLS-1$
			String ie = "q";//$NON-NLS-1$
			String ecma = "q";//$NON-NLS-1$
			if (newAttributes != null) {
				if (newAttributes.indexOf(";ns=y;") >= 0) //$NON-NLS-1$
					ns = "y"; //$NON-NLS-1$
				else if (newAttributes.indexOf(";ns=n;") >= 0) //$NON-NLS-1$
					ns = "n"; //$NON-NLS-1$
				if (newAttributes.indexOf(";ie=y;") >= 0) //$NON-NLS-1$
					ie = "y"; //$NON-NLS-1$
				else if (newAttributes.indexOf(";ie=n;") >= 0) //$NON-NLS-1$
					ie = "n"; //$NON-NLS-1$
				if (newAttributes.indexOf(";ecma=y;") >= 0) //$NON-NLS-1$
					ecma = "y"; //$NON-NLS-1$
				else if (newAttributes.indexOf(";ecma=n;") >= 0) //$NON-NLS-1$
					ecma = "n"; //$NON-NLS-1$
			}
			htAttributes.put(keyNsSupport, ns);
			htAttributes.put(keyIESupport, ie);
			htAttributes.put(keyECMASupport, ecma);
			htAttributes.put(keyFollowClass, fc);
		}
	}

	/**
	 * Creates a new node describing an parsing alternative.
	 *
	 * @param completionstring that can follow the dot in the previous construct.
	 * @param followclass string that describes the parse class that describes what can come after this
	 */
	public CompletionStringNode(String cs, Hashtable ht) {
		completionstring = cs;
		htAttributes = ht;
		this.attributes = null;
	}

	/**
	 * get info about this completion string. 
	 */
	public Hashtable getAttributesH() {
		return htAttributes;
	}

	/**
	 * get info about this completion string.  The format of this string is
	 *  semicolon  (attributename equalsign attrvalue semicolon )*
	 */
	public String getAttributesS() {
		return attributes;
	}

	/**
	 * get the string that is to follow the dot of the preceeding element
	 */
	public String getCompletionString() {
		return completionstring;
	}

	/**
	 * default display text
	 */
	public String getDefaultDisplayText() {
		String dt = (String) htAttributes.get(keyDisplayText);
		if (dt == null) {
			dt = completionstring;
		}
		String adt = (String) htAttributes.get(keyAdditionalDisplayText);
		if (adt != null)
			dt += " " + adt;//$NON-NLS-1$
		return dt;
	}

	/**
	 * get the name of the class the describes the set of things that can follow this element
	 */
	public String getFCName() {
		return (String) htAttributes.get(keyFollowClass);
	}

	/**
	 * get the image 
	 */
	public Image getImage(FCContext fcc) {
		String completype = (String) htAttributes.get(keyType);
		if (completype != null) {
			if (completype.equals(JAVADOCAT_TYPE)) {
				return JSEditorPluginImageHelper.getInstance().getImage(JSCommonUIPluginImages.IMG_OBJ_JDOC_TAG);
			}
			if (completype.equals(HTMLTAG_TYPE)) {
				return JSEditorPluginImageHelper.getInstance().getImage(JSCommonUIPluginImages.IMG_OBJ_HTML_TAG);
			}
			if (completype.equals(METHOD_TYPE)) {
				return JSEditorPluginImageHelper.getInstance().getImage(JSCommonUIPluginImages.IMG_OBJ_METHPUB);
			}
		}
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=169802 - remove JS icons
//		String fn = null;
//			FCContext fcc2 = new FCContext(this, fcc);
//			if (fcc2.getAttribute(keyWASJSPSupport) == "y") {//$NON-NLS-1$
//				// todo: I have icons available for IMG_MISC_PUBLIC/_PRIVATE/_PROTECTED designations.  I'm not sure if it's useful, but I might as well use them.
//				//return JavaPluginImages.get( JavaPluginImages.IMG_MISC_DEFAULT );
//				return JSEditorPluginImageHelper.getInstance().getImage(JSCommonUIPluginImages.IMG_OBJ_METHPUB); // BSF only exposes public classes
//			}
//			//fn = "e" + htAttributes.get(keyIESupport) + "N" + htAttributes.get(keyNsSupport) ;
//			//fn = "e" + fcc2.getAttribute(keyIESupport) + "N" + fcc2.getAttribute(keyNsSupport) ;//$NON-NLS-2$//$NON-NLS-1$
//
//			// Use new icons names
//			char ieSupport = fcc2.getAttribute(keyIESupport).charAt(0);
//			switch (ieSupport) {
//				case 'y' :
//					fn = "yes"; //$NON-NLS-1$
//					break;
//
//				case 'n' :
//					fn = "no"; //$NON-NLS-1$
//					break;
//
//				default :
//					fn = "unknown"; //$NON-NLS-1$
//					break;
//			}
//
//			fn += "_"; //$NON-NLS-1$
//
//			char nsSupport = fcc2.getAttribute(keyNsSupport).charAt(0);
//			switch (nsSupport) {
//				case 'y' :
//					fn += "yes"; //$NON-NLS-1$
//					break;
//
//				case 'n' :
//					fn += "no"; //$NON-NLS-1$
//					break;
//
//				default :
//					fn += "unknown"; //$NON-NLS-1$
//					break;
//			}
//
//		return JSEditorPluginImageHelper.getInstance().getImage(JSCommonUIPluginImages.buildObjName(fn));
		return JSEditorPluginImageHelper.getInstance().getImage(JSEditorPluginImages.IMG_OBJ_DEFAULT);
	}
}
