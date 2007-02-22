package org.eclipse.dltk.tcl.internal.ui;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.tcl.core.TclLanguageToolkit;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.jface.preference.IPreferenceStore;

public class TclUILanguageToolkit implements IDLTKUILanguageToolkit {
	private static class TclScriptElementLabels extends ScriptElementLabels {
		public void getElementLabel(IModelElement element, long flags, StringBuffer buf) {
			StringBuffer buffer = new StringBuffer(60);
			super.getElementLabel(element, flags, buffer);
			String s = buffer.toString();
			if( s != null && !s.startsWith(element.getElementName())) {
				if( s.indexOf('$') != -1 ) {
					s = s.replaceAll("\\$","::");
				}
			}
			buf.append(s);
		}

		protected char getTypeDelimiter() {
			return '$';
		}	
//		protected void getTypeLabel(IType type, long flags, StringBuffer buf) {
//			StringBuffer buffer = new StringBuffer(60);
//			super.getTypeLabel(type, flags, buffer);
//			if( type.getParent() instanceof ISourceModule ) {
//				buf.append("$");
//			}
//			buf.append(buffer);
//		}
	};	
	private static TclScriptElementLabels sInstance = new TclScriptElementLabels();

	public ScriptElementLabels getScriptElementLabels() {
		return sInstance;
	}

	public IPreferenceStore getPreferenceStore() {
		return TclUI.getDefault().getPreferenceStore();
	}

	public IDLTKLanguageToolkit getCoreToolkit() {
		return TclLanguageToolkit.getDefault();
	}
}
