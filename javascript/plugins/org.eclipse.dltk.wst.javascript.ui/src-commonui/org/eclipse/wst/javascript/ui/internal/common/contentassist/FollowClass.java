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



import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.eclipse.wst.javascript.ui.internal.common.Debug;

class FollowClass {

	protected Vector compstrings = new Vector();
	private String name = null;

	public FollowClass(String strName) {
		name = strName;
		if (Debug.jsDebugContextAssist)
			System.out.println("follow class loaded: " + name);//$NON-NLS-1$
	}

	public void add(String compstring, String folclass) {
		compstrings.add(new CompletionStringNode(compstring, folclass, null));
	}

	public void add(String compstring, Hashtable ht) {
		compstrings.add(new CompletionStringNode(compstring, ht));
	}

	public CompletionStringNode add2(String compstring, String folclass, String attributes) {
		CompletionStringNode retval = new CompletionStringNode(compstring, folclass, attributes);
		compstrings.add(retval);
		return retval;
	}

	public void adde(String compstring, String folclass, String attributes) {
		// add event handler
		compstrings.add(new CompletionStringNode(compstring, folclass, attributes));
	}

	public Enumeration elements() {
		return compstrings.elements();
	}

	public void externalize(String fn) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(fn));
		for (Enumeration enu = elements(); enu.hasMoreElements();) {
			CompletionStringNode csn = (CompletionStringNode) enu.nextElement();
			String cs = csn.getCompletionString();
			pw.println("#/---------------------------------------------");//$NON-NLS-1$
			pw.println("#!" + cs);//$NON-NLS-1$
			if (Debug.jsDebugContextAssist)
				System.out.println(cs);
			pw.println("#@type=" + ((cs.indexOf('(') >= 0) ? "method" : "unspecified"));//$NON-NLS-3$//$NON-NLS-2$//$NON-NLS-1$
			if (true) {
				String val;
				val = (String) csn.getAttributesH().get(CompletionStringNode.keyIESupport);
				pw.println("#@iesupport=" + ((val == null) ? "q" : val));//$NON-NLS-2$//$NON-NLS-1$
				val = (String) csn.getAttributesH().get(CompletionStringNode.keyNsSupport);
				pw.println("#@nssupport=" + ((val == null) ? "q" : val));//$NON-NLS-2$//$NON-NLS-1$
				val = (String) csn.getAttributesH().get(CompletionStringNode.keyECMASupport);
				pw.println("#@ecmasupport=" + ((val == null) ? "q" : val));//$NON-NLS-2$//$NON-NLS-1$
			}
			else {
				String attr = csn.getAttributesS();
				String key;
				int idxi, idxe;
				System.out.println(attr);
				key = ";ie="; //$NON-NLS-1$
				idxi = attr.indexOf(key);
				idxe = attr.indexOf(';', idxi + key.length());
				pw.println("#@iesupport=" + ((idxi < 0) ? "q" : attr.substring(idxi + key.length(), idxe)));//$NON-NLS-2$//$NON-NLS-1$
				key = ";ns="; //$NON-NLS-1$
				idxi = attr.indexOf(key);
				idxe = attr.indexOf(';', idxi + key.length());
				pw.println("#@nssupport=" + ((idxi < 0) ? "q" : attr.substring(idxi + key.length(), idxe)));//$NON-NLS-2$//$NON-NLS-1$
				key = ";ecma="; //$NON-NLS-1$
				idxi = attr.indexOf(key);
				idxe = attr.indexOf(';', idxi + key.length());
				pw.println("#@ecmasupport=" + ((idxi < 0) ? "q" : attr.substring(idxi + key.length(), idxe)));//$NON-NLS-2$//$NON-NLS-1$
			}
			pw.println("#@fc=" + csn.getFCName());//$NON-NLS-1$
		}
		pw.close();
	}

	public String getName() {
		return name;
	}

	//	private static String getShortClassName(Class cls) {
	//		String retval = cls.toString();
	//		if (cls.isArray()) retval = cls.getComponentType().toString();
	//		int i = retval.lastIndexOf('.');
	//		if (i>0) retval = retval.substring(i+1);
	//		if (cls.isArray()) retval += "[]";//$NON-NLS-1$
	//		return retval;
	//	}
	public void hydrate(BufferedReader br) {
		try {
			String strLine = br.readLine();
			boolean blPushBack = false;
			while (strLine != null) {
				if (strLine.startsWith("#/")) {//$NON-NLS-1$
					// comment, skip
					// todo: consider adding support for loading (and saving) comments too.  
				}
				else if (strLine.startsWith("#!")) {//$NON-NLS-1$
					java.util.Hashtable ht = new java.util.Hashtable();

					// keyword line
					int idx0 = 2;
					char chIn = 0;
					String stField = "";//$NON-NLS-1$
					while ((idx0 < strLine.length()) && (((chIn = strLine.charAt(idx0)) == ' ') || (chIn == '\t'))) {
						idx0++;
					}
					//if (idx0<strLine.length()) stField += chIn;
					while ((idx0 < strLine.length()) && !(((chIn = strLine.charAt(idx0)) == ' ') || (chIn == '\t'))) {
						stField += chIn;
						idx0++;
					}
					// test the value of st;
					//System.out.println( stField );
					if (stField.length() == 0) {
						System.out.println("bad #! line: " + strLine); //$NON-NLS-1$
						return;
					}
					while (true) {
						if (!blPushBack) {
							strLine = br.readLine();
						}
						blPushBack = false;
						if (strLine == null) {
							// todo: add EOF handling code
							break;
						}
						else if (strLine.startsWith("#/")) {//$NON-NLS-1$
							// comment, skip
						}
						else if (strLine.startsWith("#!")) {//$NON-NLS-1$
							// start of new record
							break;
						}
						else if (strLine.startsWith("#@")) {//$NON-NLS-1$
							// todo: code it
							idx0 = 1;
							int iLineLen = strLine.length();
							String stAttrNm = "";//$NON-NLS-1$
							StringBuffer sbAttrVal = new StringBuffer();
							while ((++idx0 < iLineLen) && (((chIn = strLine.charAt(idx0)) == ' ') || (chIn == '\t'))) {
							}
							if (idx0 < iLineLen)
								stAttrNm += chIn;
							while ((++idx0 < iLineLen) && ((chIn = strLine.charAt(idx0)) != '=')) {
								stAttrNm += chIn;
							}
							if ((stAttrNm.length() == 0) || (idx0 == iLineLen)) {
								System.out.println("bad #@ line: " + strLine); //$NON-NLS-1$
								return;
							}
							sbAttrVal.append(strLine.substring(idx0 + 1));
							while ((strLine = br.readLine()) != null) {
								if (strLine.startsWith("#/")) {//$NON-NLS-1$
									// comment, skip
								}
								else if (strLine.startsWith("#")) {//$NON-NLS-1$
									break;
								}
								else {
									sbAttrVal.append("\r\n");//$NON-NLS-1$
									sbAttrVal.append(strLine);
								}
							}
							// moving on to next attr or field, finish this attr
							ht.put(stAttrNm, sbAttrVal.toString());
							blPushBack = true;
						}
						else {
							if (Debug.jsDebugContextAssist)
								System.out.println("bad line after #! line: " + strLine); //$NON-NLS-1$
							return;
						}
					} // endwhile
					add(stField, ht);
					blPushBack = true;
				}
				else {
					if (Debug.jsDebugContextAssist)
						System.out.println("error: CPFC file has a bad line, expecting keyword (#!) line: " + strLine);//$NON-NLS-1$
					return;
				}
				if (!blPushBack) {
					strLine = br.readLine();
				}
				blPushBack = false;
			} // endwhile
		}
		catch (IOException exc) {
			// just ignore it.  Testers should find problems without us flagging it actively.
			if (Debug.jsDebugContextAssist) {
				System.out.println("error hydrating CPFC:");//$NON-NLS-1$
				exc.printStackTrace();
			}
		}
	}
}
