package org.eclipse.dltk.internal.debug.ui;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class ScriptDebugLogView extends ViewPart {
	public static final String VIEW_ID = "org.eclipse.dltk.debug.ui.dbgpLogView";

	private IDocument document;
	private TextViewer viewer;

	public ScriptDebugLogView() {
		super();

		this.document = new Document();
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}

	public void createPartControl(Composite parent) {
		viewer = new TextViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setInput(document);
	}

	public void append(String text) {
		try {
			document.replace(document.getLength(), 0, text);
			viewer.revealRange(document.getLength(), 0);

		} catch (BadLocationException e) {

		}
	}
}
