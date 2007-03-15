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
package org.eclipse.wst.javascript.ui.internal.editor;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.texteditor.MarkerAnnotation;
import org.eclipse.wst.sse.core.utils.StringUtils;

public class JSAnnotationHover implements IAnnotationHover {

	protected HTMLPrinter printer = new HTMLPrinter();
	private IDebugModelPresentation fDebugModelPresentation;

	/**
	 * Provides a set of convenience methods for creating HTML pages.
	 * Taken from org.eclipse.jdt.internal.ui.text.HTMLPrinter
	 */
	protected class HTMLPrinter {

		public HTMLPrinter() {
		}

		private String replace(String text, char c, String s) {

			int previous = 0;
			int current = text.indexOf(c, previous);

			if (current == -1)
				return text;

			StringBuffer buffer = new StringBuffer();
			while (current > -1) {
				buffer.append(text.substring(previous, current));
				buffer.append(s);
				previous = current + 1;
				current = text.indexOf(c, previous);
			}
			buffer.append(text.substring(previous));

			return buffer.toString();
		}

		public String convertToHTMLContent(String content) {
			content = replace(content, '<', "&lt;"); //$NON-NLS-1$
			return replace(content, '>', "&gt;"); //$NON-NLS-1$
		}

		public String read(Reader rd) {

			StringBuffer buffer = new StringBuffer();
			char[] readBuffer = new char[2048];

			try {
				int n = rd.read(readBuffer);
				while (n > 0) {
					buffer.append(readBuffer, 0, n);
					n = rd.read(readBuffer);
				}
				return buffer.toString();
			}
			catch (IOException x) {
			}

			return null;
		}

		public void insertPageProlog(StringBuffer buffer, int position) {
			buffer.insert(position, "<html><body text=\"#000000\" bgcolor=\"#FFFF88\"><font size=-1>"); //$NON-NLS-1$
		}

		public void addPageProlog(StringBuffer buffer) {
			insertPageProlog(buffer, buffer.length());
		}

		public void addPageEpilog(StringBuffer buffer) {
			buffer.append("</font></body></html>"); //$NON-NLS-1$
		}

		public void startBulletList(StringBuffer buffer) {
			buffer.append("<ul>"); //$NON-NLS-1$
		}

		public void endBulletList(StringBuffer buffer) {
			buffer.append("</ul>"); //$NON-NLS-1$
		}

		public void addBullet(StringBuffer buffer, String bullet) {
			if (bullet != null) {
				buffer.append("<li>"); //$NON-NLS-1$
				buffer.append(bullet);
				buffer.append("</li>"); //$NON-NLS-1$
			}
		}

		public void addSmallHeader(StringBuffer buffer, String header) {
			if (header != null) {
				buffer.append("<h5>"); //$NON-NLS-1$
				buffer.append(header);
				buffer.append("</h5>"); //$NON-NLS-1$
			}
		}

		public void addParagraph(StringBuffer buffer, String paragraph) {
			if (paragraph != null) {
				buffer.append("<p>"); //$NON-NLS-1$
				buffer.append(paragraph);
			}
		}

		public void addParagraph(StringBuffer buffer, Reader paragraphReader) {
			if (paragraphReader != null)
				addParagraph(buffer, read(paragraphReader));
		}
	}

	/**
	 * Returns the distance to the ruler line.
	 */
	protected int compareRulerLine(Position position, IDocument document, int line) {

		if (position.getOffset() > -1 && position.getLength() > -1) {
			try {
				int markerLine = document.getLineOfOffset(position.getOffset());
				if (line == markerLine)
					return 1;
				if (markerLine <= line && line <= document.getLineOfOffset(position.getOffset() + position.getLength()))
					return 2;
			}
			catch (BadLocationException x) {
			}
		}

		return 0;
	}

	/*
	 * Formats the message of this hover to fit onto the screen.
	 */
	private String formatHoverText(String text, ISourceViewer sourceViewer) {
		String lineDelim = new String();
		try {
			lineDelim = sourceViewer.getDocument().getLineDelimiter(0);
		}
		catch (org.eclipse.jface.text.BadLocationException exception) {
			// skip, just use default
		}
		Display display = sourceViewer.getTextWidget().getDisplay();

		// replace special characters in text with html entity (like <, >, & to &lt;, &gt;, &&;)
		text = StringUtils.convertToHTMLContent(text);

		Reader textReader = new StringReader(text);
		GC gc = new GC(display);
		try {
			StringBuffer buf = new StringBuffer();

			LineBreakingReader reader = new LineBreakingReader(textReader, gc, getHoverWidth(display));
			String line = reader.readLine();
			while (line != null) {
				if (buf.length() != 0) {
					buf.append(lineDelim);
				}
				buf.append(line);
				line = reader.readLine();
			}
			return buf.toString();
		}
		catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		finally {
			gc.dispose();
		}
	}

	/*
	 * Formats several message as HTML text.
	 */
	private String formatMultipleHoverText(List messages) {

		StringBuffer buffer = new StringBuffer();
		printer.addPageProlog(buffer);
		printer.addParagraph(buffer, JavaScriptUIMessages.Multiple_errors); //$NON-NLS-1$

		printer.startBulletList(buffer);
		Iterator e = messages.iterator();
		while (e.hasNext())
			printer.addBullet(buffer, printer.convertToHTMLContent((String) e.next()));
		printer.endBulletList(buffer);

		printer.addPageEpilog(buffer);
		return buffer.toString();
	}


	public String getHoverInfo(ISourceViewer sourceViewer, int lineNumber) {
		IMarker marker = getMarker(sourceViewer, lineNumber);
		List messages = new ArrayList(marker == null ? 0 : 1);
		if (marker != null) {
			String text = marker.getAttribute(IMarker.MESSAGE, (String) null);
			if (text != null) {
				messages.add(text);
			}
			else {
				try {
					if(marker.isSubtypeOf(IBreakpoint.BREAKPOINT_MARKER)){
						IBreakpointManager manager = DebugPlugin.getDefault().getBreakpointManager();
						IBreakpoint[] breakpoints = manager.getBreakpoints();
						for (int i = 0; i < breakpoints.length; i++) {
							IBreakpoint breakpoint = breakpoints[i];
							if (breakpoint.getMarker().equals(marker)) {
								if(fDebugModelPresentation == null) {
									fDebugModelPresentation = DebugUITools.newDebugModelPresentation();
								}
								text = fDebugModelPresentation.getText(breakpoint);
								if (text != null) {
									messages.add(text);
								}
							}
						}
					}
				}
				catch (CoreException e) {
					Logger.logException(e);
				}
			}
		}
		List temporaryAnnotations = getTemporaryAnnotationsForLine(sourceViewer, lineNumber);
		for (int i = 0; i < temporaryAnnotations.size(); i++) {
			String message = ((Annotation) temporaryAnnotations.get(i)).getText();
			if (message != null) {
				boolean duplicated = false;
				for (int j = 0; j < messages.size(); j++)
					duplicated = duplicated || messages.get(j).equals(message);
				if (!duplicated)
					messages.add(message);
				//				else
				//					System.out.println("duplicated message found: " + StringUtils.escape(message));
			}
			else
				messages.add(((Annotation) temporaryAnnotations.get(i)).toString());
		}
		if (messages.size() > 1)
			return formatMultipleHoverText(messages);
		else if (messages.size() > 0)
			return formatHoverText(messages.get(0).toString(), sourceViewer);
		else
			return null;
	}

	private int getHoverWidth(Display display) {
		Rectangle displayBounds = display.getBounds();
		int hoverWidth = displayBounds.width - (display.getCursorLocation().x - displayBounds.x);
		hoverWidth -= 12; // XXX: Add some space to the border, Revisit
		if (hoverWidth < 200) {
			hoverWidth = 200;
		}
		return hoverWidth;
	}

	/**
	 * Returns one marker which includes the ruler's line of activity.
	 */
	protected IMarker getMarker(ISourceViewer viewer, int line) {

		IDocument document = viewer.getDocument();
		IAnnotationModel model = viewer.getAnnotationModel();

		if (model == null)
			return null;

		List exact = new ArrayList();
		List including = new ArrayList();

		Iterator e = model.getAnnotationIterator();
		while (e.hasNext()) {
			Object o = e.next();
			if (o instanceof MarkerAnnotation) {
				MarkerAnnotation a = (MarkerAnnotation) o;
				switch (compareRulerLine(model.getPosition(a), document, line)) {
					case 1 :
						exact.add(a.getMarker());
						break;
					case 2 :
						including.add(a.getMarker());
						break;
				}
			}
		}

		return select(exact, including);
	}

	/**
	 * Returns one marker which includes the ruler's line of activity.
	 */
	protected List getTemporaryAnnotationsForLine(ISourceViewer viewer, int line) {

		IDocument document = viewer.getDocument();
		IAnnotationModel model = viewer.getAnnotationModel();

		if (model == null)
			return null;

		List exact = new ArrayList();
		List including = new ArrayList();

		Iterator e = model.getAnnotationIterator();
		while (e.hasNext()) {
			Object o = e.next();
			if (o instanceof Annotation) {
				Annotation a = (Annotation) o;
				Position position = model.getPosition(a);
				if (position == null)
					continue;

				switch (compareRulerLine(position, document, line)) {
					case 1 :
						exact.add(a);
						break;
					case 2 :
						including.add(a);
						break;
				}
			}
		}

		return exact;
	}
	
	public void release() {
		if(fDebugModelPresentation != null) {
			fDebugModelPresentation.dispose();
		}
	}

	/**
	 * Selects one marker from the two lists.
	 */
	protected IMarker select(List firstChoice, List secondChoice) {
		if (!firstChoice.isEmpty())
			return (IMarker) firstChoice.get(0);
		if (!secondChoice.isEmpty())
			return (IMarker) secondChoice.get(0);
		return null;
	}
}
