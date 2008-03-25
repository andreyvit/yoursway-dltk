/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.console.ui.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.console.ScriptConsoleHistory;
import org.eclipse.dltk.console.ScriptConsolePrompt;
import org.eclipse.dltk.console.ui.IConsoleStyleProvider;
import org.eclipse.dltk.console.ui.IScriptConsoleViewer;
import org.eclipse.dltk.console.ui.ScriptConsole;
import org.eclipse.dltk.console.ui.ScriptConsolePartitioner;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ST;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.console.TextConsoleViewer;

public class ScriptConsoleViewer extends TextConsoleViewer implements
		IScriptConsoleViewer {

	public static class ConsoleDocumentListener implements IDocumentListener {

		private ICommandHandler handler;

		private ScriptConsolePrompt prompt;

		private ScriptConsoleHistory history;

		private int offset;

		private IDocument doc;

		private List viewerList = new ArrayList();

		private void addViewer(ScriptConsoleViewer viewer) {
		  viewerList.add(viewer);
		}

		protected void connectListener() {
			doc.addDocumentListener(this);
		}

		protected void disconnectListener() {
			doc.removeDocumentListener(this);
		}

		public void clear() {
			try {
				disconnectListener();
				doc.set(""); //$NON-NLS-1$
				appendInvitation();
				for (Iterator iter = viewerList.iterator(); iter.hasNext();) {
				  ((ScriptConsoleViewer)iter.next()).setCaretPosition(doc.getLength());
				}
				connectListener();
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}

		public ConsoleDocumentListener(ICommandHandler handler, ScriptConsolePrompt prompt,
				ScriptConsoleHistory history) {
			this.prompt = prompt;
			this.handler = handler;
			this.history = history;

			this.offset = 0;

			this.doc = null;
		}

		public void setDocument(IDocument doc) {
			if (this.doc != null) {
				disconnectListener();
			}

			this.doc = doc;

			if (this.doc != null) {
				connectListener();
			}
		}

		public void documentAboutToBeChanged(DocumentEvent event) {

		}

		protected void handleCommandLine() throws BadLocationException,
				IOException {
			// viewer.getTextWidget().setEditable(false);

			final String command = getCommandLine();
			appendDelimeter();

			processResult(handler.handleCommand(command));

		}

		protected void processResult(final String result)
				throws BadLocationException {
			if (result != null) {
				int start = appendText(result);
				ScriptConsoleViewer viewer;

				for (Iterator iter = viewerList.iterator(); iter.hasNext();) {
					viewer = (ScriptConsoleViewer)iter.next();
				  if (viewer.styleProvider != null) {
					  StyleRange[] styles = viewer.styleProvider.createInterpreterOutputStyle(result, start);
					  if ((styles != null) && (styles.length > 0)) {
						  addToPartitioner(viewer, styles);
					  }
				  }
				}
				
				history.commit();
				offset = getLastLineLength();
			}
			appendInvitation();
		}
		
		private void addToPartitioner (ScriptConsoleViewer viewer, StyleRange[] styles) {
			IDocumentPartitioner partitioner = viewer.getDocument().getDocumentPartitioner();
			if (partitioner instanceof ScriptConsolePartitioner) {
				ScriptConsolePartitioner scriptConsolePartitioner = (ScriptConsolePartitioner) partitioner;						
				scriptConsolePartitioner.addRanges(styles);
				viewer.getTextWidget().redraw();
			}
		}

		protected void proccessAddition(int offset, String text) {
			try {
				String delim = getDelimeter();

				text = doc.get(offset, doc.getLength() - offset);

				doc.replace(offset, text.length(), ""); //$NON-NLS-1$

				text = text.replaceAll("\r\n|\n|\r", delim); //$NON-NLS-1$

				int start = 0;
				int index = -1;
				while ((index = text.indexOf(delim, start)) != -1) {
					String cmd = text.substring(start, index);
					appendText(cmd);
					ScriptConsoleViewer viewer;

					for (Iterator iter = viewerList.iterator(); iter.hasNext();) {
						viewer = (ScriptConsoleViewer)iter.next();
					  if (viewer.styleProvider != null) {
						  StyleRange[] styles = viewer.styleProvider.createUserInputStyle(getCommandLine(), getCommandLineOffset());
						  if ((styles != null) && (styles.length > 0)) {
							  addToPartitioner(viewer, styles);
						  }
					  }
					}
					
					history.update(getCommandLine());
					start = index + delim.length();
					handleCommandLine();
				}

				appendText(text.substring(start, text.length()));
				history.update(getCommandLine());
			} catch (BadLocationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void documentChanged(DocumentEvent event) {
			disconnectListener();
			proccessAddition(event.getOffset(), event.getText());
			connectListener();
		}

		protected int appendText(String text) throws BadLocationException {
			int offset = doc.getLength();
			doc.replace(doc.getLength(), 0, text);
			return offset;
		}

		protected void appendInvitation() throws BadLocationException {
			int start = doc.getLength();
			appendText(prompt.toString());
			ScriptConsoleViewer viewer;

			for (Iterator iter = viewerList.iterator(); iter.hasNext();) {
				viewer = (ScriptConsoleViewer)iter.next();
			  viewer.setCaretPosition(doc.getLength());
			  viewer.revealEndOfDocument();
			  if (viewer.styleProvider != null) {
				  StyleRange[] styles = viewer.styleProvider.createPromptStyle(prompt, start);
				  if ((styles != null) && (styles.length > 0)) {
					  addToPartitioner(viewer, styles);
				  }
			  }
			}
		}

		protected void appendDelimeter() throws BadLocationException {
			appendText(getDelimeter());
		}

		protected String getDelimeter() {
			return TextUtilities.getDefaultLineDelimiter(doc);
		}

		protected int getLastLineLength() throws BadLocationException {
			int lastLine = doc.getNumberOfLines() - 1;
			return doc.getLineLength(lastLine);
		}

		public int getLastLineReadOnlySize() {
			return prompt.toString().length() + offset;
		}

		public int getCommandLineOffset() throws BadLocationException {
			int lastLine = doc.getNumberOfLines() - 1;
			return doc.getLineOffset(lastLine) + getLastLineReadOnlySize();
		}

		public int getCommandLineLength() throws BadLocationException {
			int lastLine = doc.getNumberOfLines() - 1;
			return doc.getLineLength(lastLine) - getLastLineReadOnlySize();
		}

		public String getCommandLine() throws BadLocationException {
			return doc.get(getCommandLineOffset(), getCommandLineLength());
		}

		public void setCommandLine(String command) throws BadLocationException {
			doc
					.replace(getCommandLineOffset(), getCommandLineLength(),
							command);
		}
	}

	private class ScriptCnosoleStyledText extends StyledText {

		public ScriptCnosoleStyledText(Composite parent, int style) {
			super(parent, style);
		}

		public void invokeAction(int action) {
			if (isCaretOnLastLine()) {
				try {
					switch (action) {
					case ST.LINE_UP:
						history.prev();
						console.getDocumentListener().setCommandLine(history.get());
						setCaretOffset(getDocument().getLength());
						return;

					case ST.LINE_DOWN:
						history.next();
						console.getDocumentListener().setCommandLine(history.get());
						setCaretOffset(getDocument().getLength());
						return;

					case ST.DELETE_PREVIOUS:
						if (getCaretOffset() <= getCommandLineOffset()) {
							return;
						}
						break;

					case ST.DELETE_NEXT:
						if (getCaretOffset() < getCommandLineOffset()) {
							return;
						}
						break;

					case ST.DELETE_WORD_PREVIOUS:
						return;
					}

				} catch (BadLocationException e) {
					e.printStackTrace();
					return;
				}

				super.invokeAction(action);

				if (isCaretOnLastLine()
						&& getCaretOffset() <= getCommandLineOffset()) {
					setCaretOffset(getCommandLineOffset());
				}
			} else {

				super.invokeAction(action);
			}
		}

		public void paste() {
			if (isCaretOnLastLine()) {
				super.paste();
			}
		}
	}

	private ScriptConsoleHistory history;

	private ScriptConsole console;

	private IConsoleStyleProvider styleProvider;

	public int getCaretPosition() {
		return getTextWidget().getCaretOffset();
	}

	public void setCaretPosition(final int offset) {
		getTextWidget().getDisplay().asyncExec(new Runnable() {
			public void run() {
				getTextWidget().setCaretOffset(offset);
			}
		});
	}

	public int beginLineOffset() throws BadLocationException {
		IDocument doc = getDocument();
		int offset = getCaretPosition();
		int line = doc.getLineOfOffset(offset);
		return offset - doc.getLineOffset(line);
	}

	protected boolean isCaretOnLastLine() {
		try {
			IDocument doc = getDocument();
			int line = doc.getLineOfOffset(getCaretPosition());
			return line == doc.getNumberOfLines() - 1;
		} catch (BadLocationException e) {
			e.printStackTrace();
			return false;
		}
	}

	protected StyledText createTextWidget(Composite parent, int styles) {
		return new ScriptCnosoleStyledText(parent, styles);
	}

	public ScriptConsoleViewer(Composite parent, final ScriptConsole console,
			final IScriptConsoleContentHandler contentHandler, IConsoleStyleProvider styleProvider) {
		super(parent, console);
		
		this.console = console;
		this.styleProvider = styleProvider;

		this.history = console.getHistory();

		console.getDocumentListener().addViewer(this);

		final StyledText styledText = getTextWidget();

		// styledText.setEditable(false);

		// Correct keyboard actions
		styledText.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				setCaretPosition(getDocument().getLength());
				styledText.removeFocusListener(this);
			}

			public void focusLost(FocusEvent e) {

			}
		});

		styledText.addVerifyKeyListener(new VerifyKeyListener() {
			public void verifyKey(VerifyEvent event) {
				try {
					if (event.character != '\0') {
						// Printable character
			            // ssanders: Ensure selection is on last line
			            ConsoleDocumentListener listener = console.getDocumentListener();
			            int selStart = getSelectedRange().x;
			            int selEnd = (getSelectedRange().x + getSelectedRange().y);
			            int clOffset = listener.getCommandLineOffset();
			            int clLength = listener.getCommandLineLength();
			            if (selStart < clOffset) {
			              int selLength;

			              if (selEnd < clOffset) {
			                selStart = (clOffset + clLength);
			                selLength = 0;
			              }
			              else {
			                selStart = clOffset;
			                selLength = (selEnd - selStart);
			              }

			              setSelectedRange(selStart, selLength);
			            }

						if (beginLineOffset() < console.getDocumentListener()
								.getLastLineReadOnlySize()) {
							event.doit = false;
							return;
						}

						if (event.character == SWT.CR) {
							getTextWidget().setCaretOffset(
									getDocument().getLength());
							return;
						}
						if (event.keyCode == 9) {
							event.doit = false;
							return;
						}
						if (event.keyCode == 32
								&& (event.stateMask & SWT.CTRL) > 0) {
							event.doit = false;
							return;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		styledText.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 9) {
					contentHandler.contentAssistRequired();
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		});

		clear();
	}

	// IConsoleTextViewer
	public String getCommandLine() {
		try {
			return console.getDocumentListener().getCommandLine();
		} catch (BadLocationException e) {
			return null;
		}
	}

	public int getCommandLineOffset() {
		try {
			return console.getDocumentListener().getCommandLineOffset();
		} catch (BadLocationException e) {
			return -1;
		}
	}

	public void clear() {
		console.getDocumentListener().clear();
	}

	public void insertText(String text) {
		getTextWidget().append(text);
	}
	public boolean canDoOperation(int operation) {
	    boolean canDoOperation = super.canDoOperation(operation);

	    if (canDoOperation) {
	      switch (operation) {
	        case CUT:
	        case DELETE:
	        case PASTE:
	        case SHIFT_LEFT:
	        case SHIFT_RIGHT:
	        case PREFIX:
	        case STRIP_PREFIX:
	          canDoOperation = isCaretOnLastLine();
	      }
	    }

	    return canDoOperation;
	  }

	public void setStyleProvider(IConsoleStyleProvider provider) {
		this.styleProvider = provider;		
	}
}
