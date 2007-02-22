package org.eclipse.dltk.console.ui;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;

public abstract class ScriptConsoleTextHover implements ITextHover {

	protected abstract String getHoverInfoImpl(IScriptConsoleViewer viewer, IRegion hoverRegion);
		
	public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) {
		return getHoverInfoImpl((IScriptConsoleViewer)textViewer, hoverRegion);
	}

	public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
		return new Region(offset, 0); 
	}
}
