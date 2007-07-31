package org.eclipse.dltk.xotcl.internal.ui;

import org.eclipse.dltk.tcl.internal.ui.TclUILanguageToolkit;
import org.eclipse.dltk.ui.ScriptElementImageProvider;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.viewsupport.ScriptUILabelProvider;

public class XOTclUILanguageToolkit extends TclUILanguageToolkit {
	private class XOTclUILabelProvide extends ScriptUILabelProvider {
		public XOTclUILabelProvide() {
			this.fImageLabelProvider = new ScriptElementImageProvider() {
				
			};
		}
	};
	public ScriptUILabelProvider createScripUILabelProvider() {
		return new XOTclUILabelProvide();
	}
	public ScriptTextTools getTextTools() {
		return XOTclUI.getDefault().getTextTools();
	}
}
