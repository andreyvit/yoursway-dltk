/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.ui;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;

import org.eclipse.jface.internal.text.html.HTMLTextPresenter;
import org.eclipse.jface.preference.IPreferenceStore;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHoverExtension;
import org.eclipse.jface.text.ITextViewer;

import org.eclipse.ui.editors.text.EditorsUI;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.dltk.core.ICodeAssist;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.model.IScriptStackFrame;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.debug.core.model.IScriptVariable;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.dltk.debug.ui.ScriptDebugModelPresentation;
import org.eclipse.dltk.debug.ui.preferences.DebuggingPreferences;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.text.hover.IScriptEditorTextHover;

public abstract class ScriptDebugHover implements IScriptEditorTextHover,
		ITextHoverExtension {

	private IEditorPart fEditor;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.ui.text.Script.hover.IJavaEditorTextHover#setEditor(org.eclipse.ui.IEditorPart)
	 */
	public void setEditor(IEditorPart editor) {
		fEditor = editor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.ITextHover#getHoverRegion(org.eclipse.jface.text.ITextViewer,
	 *      int)
	 */
	public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
		return ScriptWordFinder.findWord(textViewer.getDocument(), offset);
	}

	/**
	 * Returns the stack frame in which to search for variables, or
	 * <code>null</code> if none.
	 * 
	 * @return the stack frame in which to search for variables, or
	 *         <code>null</code> if none
	 */
	protected IScriptStackFrame getFrame() {
		IAdaptable adaptable = DebugUITools.getDebugContext();
		if (adaptable != null) {
			return (IScriptStackFrame) adaptable
					.getAdapter(IScriptStackFrame.class);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.ITextHover#getHoverInfo(org.eclipse.jface.text.ITextViewer,
	 *      org.eclipse.jface.text.IRegion)
	 */
	public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) {
		IScriptStackFrame frame = getFrame();
		if (frame != null) {
			// first check for 'this' - code resolve does not resolve Script
			// elements for 'this'
			IDocument document = textViewer.getDocument();
			if (document != null) {
				try {
					String variableName = document.get(hoverRegion.getOffset(),
							hoverRegion.getLength());
					if (hoverRegion.getOffset() > 0) {
						IRegion hoverRegion2 = getHoverRegion(textViewer,
								hoverRegion.getOffset());
						hoverRegion = hoverRegion2;
						variableName = document.get(hoverRegion2.getOffset(),
								hoverRegion2.getLength());
					}
					if (variableName.equals("this")) { //$NON-NLS-1$
						IScriptVariable variable = null;
						try {
							variable = frame.findVariable(variableName);
						} catch (DebugException e) {
							DLTKDebugPlugin.log(e);
						}
						if (variable != null) {
							String vs = variable.getValueString();
							return getVariableText(variable) + "=" + vs;
						}
					}
				} catch (BadLocationException e) {
					return null;
				}
			}
			ICodeAssist codeAssist = null;
			if (fEditor != null) {
				IEditorInput input = fEditor.getEditorInput();
				Object element = DLTKUIPlugin.getDefault()
						.getWorkingCopyManager().getWorkingCopy(input);
				if (element instanceof ICodeAssist) {
					codeAssist = ((ICodeAssist) element);
				}
			}
			if (codeAssist != null) {
				return getRemoteHoverInfo(frame, textViewer, hoverRegion);
			}

			// IJavaElement[] resolve = null;
			// try {
			// resolve = codeAssist.codeSelect(hoverRegion.getOffset(), 0);
			// } catch (JavaModelException e1) {
			// resolve = new IJavaElement[0];
			// }
			// try {
			// for (int i = 0; i < resolve.length; i++) {
			// IJavaElement javaElement = resolve[i];
			// if (javaElement instanceof IField) {
			// IField field = (IField) javaElement;
			// IJavaVariable variable = null;
			// IJavaDebugTarget debugTarget = (IJavaDebugTarget) frame
			// .getDebugTarget();
			// if (Flags.isStatic(field.getFlags())) {
			// IJavaType[] javaTypes = debugTarget
			// .getJavaTypes(field.getDeclaringType()
			// .getFullyQualifiedName());
			// if (javaTypes != null) {
			// for (int j = 0; j < javaTypes.length; j++) {
			// IJavaType type = javaTypes[j];
			// if (type instanceof IJavaReferenceType) {
			// IJavaReferenceType referenceType = (IJavaReferenceType) type;
			// variable = referenceType.getField(field
			// .getElementName());
			// }
			// if (variable != null) {
			// break;
			// }
			// }
			// }
			// if (variable == null) {
			// // the class is not loaded yet, but may be an
			// // in-lined primitive constant
			// Object constant = field.getConstant();
			// if (constant != null) {
			// IJavaValue value = null;
			// if (constant instanceof Integer) {
			// value = debugTarget
			// .newValue(((Integer) constant)
			// .intValue());
			// } else if (constant instanceof Byte) {
			// value = debugTarget
			// .newValue(((Byte) constant)
			// .byteValue());
			// } else if (constant instanceof Boolean) {
			// value = debugTarget
			// .newValue(((Boolean) constant)
			// .booleanValue());
			// } else if (constant instanceof Character) {
			// value = debugTarget
			// .newValue(((Character) constant)
			// .charValue());
			// } else if (constant instanceof Double) {
			// value = debugTarget
			// .newValue(((Double) constant)
			// .doubleValue());
			// } else if (constant instanceof Float) {
			// value = debugTarget
			// .newValue(((Float) constant)
			// .floatValue());
			// } else if (constant instanceof Long) {
			// value = debugTarget
			// .newValue(((Long) constant)
			// .longValue());
			// } else if (constant instanceof Short) {
			// value = debugTarget
			// .newValue(((Short) constant)
			// .shortValue());
			// } else if (constant instanceof String) {
			// value = debugTarget
			// .newValue((String) constant);
			// }
			// if (value != null) {
			// variable = new JDIPlaceholderVariable(
			// field.getElementName(), value);
			// }
			// }
			// if (variable == null) {
			// return null; // class not loaded yet and
			// // not a constant
			// }
			// }
			// } else {
			// if (!frame.isStatic()) {
			// String typeSignature = Signature
			// .createTypeSignature(field
			// .getDeclaringType()
			// .getFullyQualifiedName(), true);
			// typeSignature = typeSignature.replace('.', '/');
			// variable = frame.getThis().getField(
			// field.getElementName(), typeSignature);
			// }
			// }
			// if (variable != null) {
			// return getVariableText(variable);
			// }
			// break;
			// }
			// if (javaElement instanceof ILocalVariable) {
			// ILocalVariable var = (ILocalVariable) javaElement;
			// IJavaElement parent = var.getParent();
			// while (!(parent instanceof IMethod) && parent != null) {
			// parent = parent.getParent();
			// }
			// if (parent instanceof IMethod) {
			// IMethod method = (IMethod) parent;
			// boolean equal = false;
			// if (method.isBinary()) {
			// // compare resolved signatures
			// if (method.getSignature().equals(
			// frame.getSignature())) {
			// equal = true;
			// }
			// } else {
			// // compare unresolved signatures
			// if (((frame.isConstructor() && method
			// .isConstructor()) || frame
			// .getMethodName().equals(
			// method.getElementName()))
			// && frame
			// .getDeclaringTypeName()
			// .endsWith(
			// method
			// .getDeclaringType()
			// .getElementName())
			// && frame.getArgumentTypeNames().size() == method
			// .getNumberOfParameters()) {
			// equal = true;
			// }
			// }
			// if (equal) {
			// return generateHoverForLocal(frame, var
			// .getElementName());
			// }
			// }
			// break;
			// }
			// }
			// } catch (CoreException e) {
			// DLTKDebugPlugin.log(e);
			// }
		}
		return null;
	}

	/**
	 * Generate hover info via a variable search, if the Script element is not
	 * avilable.
	 */
	private String getRemoteHoverInfo(IScriptStackFrame frame,
			ITextViewer textViewer, IRegion hoverRegion) {
		if (frame != null) {
			try {
				IDocument document = textViewer.getDocument();
				if (document != null) {
					String variableName = document.get(hoverRegion.getOffset(),
							hoverRegion.getLength());
					return generateHoverForLocal(frame, variableName);
				}
			} catch (BadLocationException x) {
			}
		}
		return null;
	}

	private String generateHoverForLocal(IScriptStackFrame frame, String varName) {
		String variableText = null;
		IScriptVariable variable = null;
		int iip = varName.indexOf('.');
		if (iip != -1)
			try {
				String vn = varName.substring(0, iip);
				IScriptVariable findVariable = frame.findVariable(vn);
				while (iip != -1) {
					
					String name=varName.substring(iip+1);
					int pos=name.indexOf('.');
					if (pos!=-1){
						varName=name;
						name=name.substring(0,pos);
						
					}
					iip=pos;
					if (findVariable==null)return null;
					IScriptVariable[] children = findVariable.getChildren();
					for (int a=0;a<children.length;a++){
						if (children[a].getName().equals(name)){
							findVariable=children[a];
						}
					}
				}
				return getVariableText(findVariable) + "="
					+ findVariable.getValueString();
			} catch (DebugException e) {
				DLTKDebugPlugin.log(e);
				return null;
			}
		try {
			variable = frame.findVariable(varName);
		} catch (DebugException e) {
			DLTKDebugPlugin.log(e);
		}
		if (variable != null) {
			variableText = getVariableText(variable) + "="
					+ variable.getValueString();
		}
		return variableText;
	}

	/**
	 * Returns HTML text for the given variable
	 */
	private String getVariableText(IVariable variable) {
		StringBuffer buffer = new StringBuffer();
		ScriptDebugModelPresentation modelPresentation = getModelPresentation();
		buffer.append("<p><pre>"); //$NON-NLS-1$
		String variableText = modelPresentation
				.getVariableText((IScriptVariable) variable);
		buffer.append(replaceHTMLChars(variableText));
		buffer.append("</pre></p>"); //$NON-NLS-1$
		modelPresentation.dispose();
		if (buffer.length() > 0) {
			return buffer.toString();
		}
		return null;
	}

	/**
	 * Replaces reserved HTML characters in the given string with their escaped
	 * equivalents. This is to ensure that variable values containing reserved
	 * characters are correctly displayed.
	 */
	private static String replaceHTMLChars(String variableText) {
		StringBuffer buffer = new StringBuffer(variableText.length());
		char[] characters = variableText.toCharArray();
		for (int i = 0; i < characters.length; i++) {
			char character = characters[i];
			switch (character) {
			case '<':
				buffer.append("&lt;"); //$NON-NLS-1$
				break;
			case '>':
				buffer.append("&gt;"); //$NON-NLS-1$
				break;
			case '&':
				buffer.append("&amp;"); //$NON-NLS-1$
				break;
			case '"':
				buffer.append("&quot;"); //$NON-NLS-1$
				break;
			default:
				buffer.append(character);
			}
		}
		return buffer.toString();
	}

	/**
	 * Returns a configured model presentation for use displaying variables.
	 */
	protected abstract ScriptDebugModelPresentation getModelPresentation();

	/**
	 * Returns the value of this filters preference (on/off) for the given view.
	 * 
	 * @param part
	 * @return boolean
	 */
	public static boolean getBooleanPreferenceValue(String id, String preference) {
		String compositeKey = id + "." + preference; //$NON-NLS-1$
		IPreferenceStore store = DLTKDebugUIPlugin.getDefault()
				.getPreferenceStore();
		boolean value = false;
		if (store.contains(compositeKey)) {
			value = store.getBoolean(compositeKey);
		} else {
			value = store.getBoolean(preference);
		}
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.ITextHoverExtension#getHoverControlCreator()
	 */
	public IInformationControlCreator getHoverControlCreator() {
		return new IInformationControlCreator() {
			public IInformationControl createInformationControl(Shell parent) {
				return new DefaultInformationControl(parent, SWT.NONE,
						new HTMLTextPresenter(true), EditorsUI
								.getTooltipAffordanceString());
			}
		};
	}
}
