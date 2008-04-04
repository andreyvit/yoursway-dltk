/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.infoviews;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.text.HTMLPrinter;
import org.eclipse.dltk.internal.ui.text.HTMLTextPresenter;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.dltk.ui.documentation.ScriptDocumentationAccess;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IAbstractTextEditorHelpContextIds;
import org.osgi.framework.Bundle;


/**
 * Abstract view which shows documentation for a given model element.
 */
public abstract class AbstractDocumentationView extends AbstractInfoView {
	/**
	 * Preference key for the preference whether to show a dialog when the SWT
	 * Browser widget is not available.
	 *
	 *
	 */
	private static final String DO_NOT_WARN_PREFERENCE_KEY = "AbstractDocumentationView.error.doNotWarn"; //$NON-NLS-1$
	// see https://bugs.eclipse.org/bugs/show_bug.cgi?id=73558
	private static final boolean WARNING_DIALOG_ENABLED = false;
	/** Flags used to render a label in the text widget. */
	private static final long LABEL_FLAGS = ScriptElementLabels.ALL_FULLY_QUALIFIED | ScriptElementLabels.M_PRE_RETURNTYPE
			| ScriptElementLabels.M_PARAMETER_TYPES | ScriptElementLabels.M_PARAMETER_NAMES | ScriptElementLabels.M_EXCEPTIONS
			| ScriptElementLabels.F_PRE_TYPE_SIGNATURE | ScriptElementLabels.T_TYPE_PARAMETERS;
	/** The HTML widget. */
	private Browser fBrowser;
	/** The text widget. */
	private StyledText fText;
	/** The information presenter. */
	private DefaultInformationControl.IInformationPresenter fPresenter;
	/** The text presentation. */
	private TextPresentation fPresentation = new TextPresentation();
	/** The select all action */
	private SelectAllAction fSelectAllAction;
	/** The style sheet (css) */
	private static String fgStyleSheet;
	/** The Browser widget */
	private boolean fIsUsingBrowserWidget;
	private RGB fBackgroundColorRGB;
	/**
	 * The Javadoc view's select all action.
	 */
	private class SelectAllAction extends Action {
		/** The control. */
		private Control fControl;
		/** The selection provider. */
		private SelectionProvider fSelectionProvider;

		/**
		 * Creates the action.
		 *
		 * @param control
		 *            the widget
		 * @param selectionProvider
		 *            the selection provider
		 */
		public SelectAllAction(Control control, SelectionProvider selectionProvider) {
			super("selectAll"); //$NON-NLS-1$
			fControl = control;
			fSelectionProvider = selectionProvider;
			// FIXME: see https://bugs.eclipse.org/bugs/show_bug.cgi?id=63022
			setEnabled(!fIsUsingBrowserWidget);
			setText(InfoViewMessages.SelectAllAction_label);
			setToolTipText(InfoViewMessages.SelectAllAction_tooltip);
			setDescription(InfoViewMessages.SelectAllAction_description);
			PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IAbstractTextEditorHelpContextIds.SELECT_ALL_ACTION);
		}

		/**
		 * Selects all in the view.
		 */
		public void run() {
			if (fControl instanceof StyledText)
				((StyledText) fControl).selectAll();
			else {
				// FIXME: see
				// https://bugs.eclipse.org/bugs/show_bug.cgi?id=63022
				// ((Browser)fControl).selectAll();
				if (fSelectionProvider != null)
					fSelectionProvider.fireSelectionChanged();
			}
		}
	}
	/**
	 * The Javadoc view's selection provider.
	 */
	private static class SelectionProvider implements ISelectionProvider {
		/** The selection changed listeners. */
		private ListenerList fListeners = new ListenerList(ListenerList.IDENTITY);
		/** The widget. */
		private Control fControl;

		/**
		 * Creates a new selection provider.
		 *
		 * @param control
		 *            the widget
		 */
		public SelectionProvider(Control control) {
			fControl = control;
			if (fControl instanceof StyledText) {
				((StyledText) fControl).addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						fireSelectionChanged();
					}
				});
			} else {
				// FIXME: see
				// https://bugs.eclipse.org/bugs/show_bug.cgi?id=63022
				// ((Browser)fControl).addSelectionListener(new
				// SelectionAdapter() {
				// public void widgetSelected(SelectionEvent e) {
				// fireSelectionChanged();
				// }
				// });
			}
		}

		/**
		 * Sends a selection changed event to all listeners.
		 */
		public void fireSelectionChanged() {
			ISelection selection = getSelection();
			SelectionChangedEvent event = new SelectionChangedEvent(this, selection);
			Object[] selectionChangedListeners = fListeners.getListeners();
			for (int i = 0; i < selectionChangedListeners.length; i++)
				((ISelectionChangedListener) selectionChangedListeners[i]).selectionChanged(event);
		}

		/*
		 * @see org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
		 */
		public void addSelectionChangedListener(ISelectionChangedListener listener) {
			fListeners.add(listener);
		}

		/*
		 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
		 */
		public ISelection getSelection() {
			if (fControl instanceof StyledText) {
				IDocument document = new Document(((StyledText) fControl).getSelectionText());
				return new TextSelection(document, 0, document.getLength());
			} else {
				// FIXME: see
				// https://bugs.eclipse.org/bugs/show_bug.cgi?id=63022
				return StructuredSelection.EMPTY;
			}
		}

		/*
		 * @see org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
		 */
		public void removeSelectionChangedListener(ISelectionChangedListener listener) {
			fListeners.remove(listener);
		}

		/*
		 * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
		 */
		public void setSelection(ISelection selection) {
		// not supported
		}
	}

	/*
	 * @see AbstractInfoView#internalCreatePartControl(Composite)
	 */
	protected void internalCreatePartControl(Composite parent) {
		try {
			fBrowser = new Browser(parent, SWT.NONE);
			fIsUsingBrowserWidget = true;
		} catch (SWTError er) {
			/*
			 * The Browser widget throws an SWTError if it fails to instantiate
			 * properly. Application code should catch this SWTError and disable
			 * any feature requiring the Browser widget. Platform requirements
			 * for the SWT Browser widget are available from the SWT FAQ web
			 * site.
			 */
			IPreferenceStore store = this.getPreferenceStore();// JavaPlugin.getDefault().getPreferenceStore();
			boolean doNotWarn = store.getBoolean(DO_NOT_WARN_PREFERENCE_KEY);
			if (WARNING_DIALOG_ENABLED && !doNotWarn) {
				String title = InfoViewMessages.ScriptdocView_error_noBrowser_title;
				String message = InfoViewMessages.ScriptdocView_error_noBrowser_message;
				String toggleMessage = InfoViewMessages.ScriptdocView_error_noBrowser_doNotWarn;
				MessageDialogWithToggle dialog = MessageDialogWithToggle.openError(parent.getShell(), title, message, toggleMessage, false,
						null, null);
				if (dialog.getReturnCode() == Window.OK)
					store.setValue(DO_NOT_WARN_PREFERENCE_KEY, dialog.getToggleState());
			}
			fIsUsingBrowserWidget = false;
		}
		if (!fIsUsingBrowserWidget) {
			fText = new StyledText(parent, SWT.V_SCROLL | SWT.H_SCROLL);
			fText.setEditable(false);
			fPresenter = new HTMLTextPresenter(false);
			fText.addControlListener(new ControlAdapter() {
				/*
				 * @see org.eclipse.swt.events.ControlAdapter#controlResized(org.eclipse.swt.events.ControlEvent)
				 */
				public void controlResized(ControlEvent e) {
					setInput(fText.getText());
				}
			});
		}
		initStyleSheet();
		getViewSite().setSelectionProvider(new SelectionProvider(getControl()));
	}

	protected abstract IPreferenceStore getPreferenceStore();
	
	protected abstract String getNature();

	private static void initStyleSheet() {
		Bundle bundle = Platform.getBundle(DLTKUIPlugin.getPluginId());
		URL styleSheetURL = bundle.getEntry("/DocumentationViewStyleSheet.css"); //$NON-NLS-1$
		if (styleSheetURL == null)
			return;
		try {
			styleSheetURL = FileLocator.toFileURL(styleSheetURL);
			BufferedReader reader = new BufferedReader(new InputStreamReader(styleSheetURL.openStream()));
			StringBuffer buffer = new StringBuffer(200);
			String line = reader.readLine();
			while (line != null) {
				buffer.append(line);
				buffer.append('\n');
				line = reader.readLine();
			}
			fgStyleSheet = buffer.toString();
		} catch (IOException ex) {
			DLTKUIPlugin.log(ex);
		}
	}

	/*
	 * @see AbstractInfoView#createActions()
	 */
	protected void createActions() {
		super.createActions();
		fSelectAllAction = new SelectAllAction(getControl(), (SelectionProvider) getSelectionProvider());
	}

	protected IAction getSelectAllAction() {
		// FIXME: see https://bugs.eclipse.org/bugs/show_bug.cgi?id=63022
		if (fIsUsingBrowserWidget)
			return null;
		return fSelectAllAction;
	}

	protected IAction getCopyToClipboardAction() {
		// FIXME: see https://bugs.eclipse.org/bugs/show_bug.cgi?id=63022
		if (fIsUsingBrowserWidget)
			return null;
		return super.getCopyToClipboardAction();
	}

	/*
	 * @see AbstractInfoView#setForeground(Color)
	 */
	protected void setForeground(Color color) {
		getControl().setForeground(color);
	}

	/*
	 * @see AbstractInfoView#setBackground(Color)
	 */
	protected void setBackground(Color color) {
		getControl().setBackground(color);
		// Apply style sheet
		fBackgroundColorRGB = color.getRGB();
		if (getInput() == null) {
			StringBuffer buffer = new StringBuffer(""); //$NON-NLS-1$
			HTMLPrinter.insertPageProlog(buffer, 0, fBackgroundColorRGB, fgStyleSheet);
			setInput(buffer.toString());
		} else {
			setInput(computeInput(getInput()));
		}
	}

	protected String getBackgroundColorKey() {
		return "org.eclipse.dltk.ui.ScriptdocView.backgroundColor"; //$NON-NLS-1$
	}

	/*
	 * @see AbstractInfoView#internalDispose()
	 */
	protected void internalDispose() {
		fText = null;
		fBrowser = null;
	}

	/*
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	public void setFocus() {
		getControl().setFocus();
	}

	/*
	 * @see AbstractInfoView#computeInput(Object)
	 */
	protected Object computeInput(Object input) {
		String javadocHtml;
		if (input instanceof String) {
			javadocHtml = getScriptdocHtml((String)input);
		} else {
			if (getControl() == null || !(input instanceof IModelElement))
				return null;
			IModelElement je = (IModelElement) input;

			switch (je.getElementType()) {
				case IModelElement.SOURCE_MODULE:
					try {
						javadocHtml = getScriptdocHtml(((ISourceModule) je).getTypes());
					} catch (ModelException ex) {
						javadocHtml = null;
					}
					break;
				default:
					javadocHtml = getScriptdocHtml(new IModelElement[] {
						je
					});
			}
		}
		if (javadocHtml == null)
			return null; //$NON-NLS-1$
		return javadocHtml;
	}

	/*
	 * @see AbstractInfoView#setInput(Object)
	 */
	protected void setInput(Object input) {
		String javadocHtml = (String) input;
		if (fIsUsingBrowserWidget) {
			if (javadocHtml != null && javadocHtml.length() > 0) {
				boolean RTL = (getSite().getShell().getStyle() & SWT.RIGHT_TO_LEFT) != 0;
				if (RTL) {
					StringBuffer buffer = new StringBuffer(javadocHtml);
					HTMLPrinter.insertStyles(buffer, new String[] {
						"direction:rtl"}); //$NON-NLS-1$
					javadocHtml = buffer.toString();
				}
			}
			fBrowser.setText(javadocHtml);
		} else {
			fPresentation.clear();
			Rectangle size = fText.getClientArea();
			try {
				javadocHtml = ((DefaultInformationControl.IInformationPresenterExtension) fPresenter).updatePresentation(
						getSite().getShell(), javadocHtml, fPresentation, size.width, size.height);
			} catch (IllegalArgumentException ex) {
				// the javadoc might no longer be valid
				return;
			}
			fText.setText(javadocHtml);
			TextPresentation.applyTextPresentation(fPresentation, fText);
		}
	}

	/**
	 * Returns the doc in HTML format.
	 * @param result
	 *            the Script elements for which to get the Javadoc
	 * @return a string with the Javadoc in HTML format.
	 */
	private String getScriptdocHtml(String result) {
		StringBuffer buffer = new StringBuffer();
		Reader reader;
		try {
			reader = ScriptDocumentationAccess.getHTMLContentReader(getNature(), result);
			if (reader == null) {
				return null;
			}
		} catch (ModelException ex) {
			return null;
		}
		HTMLPrinter.addParagraph(buffer, reader);
		if (buffer.length() > 0) {
			HTMLPrinter.insertPageProlog(buffer, 0, fgStyleSheet);
			HTMLPrinter.addPageEpilog(buffer);
			return buffer.toString();
		}
		return null;
	}

	/**
	 * Returns the Javadoc in HTML format.
	 *
	 * @param result
	 *            the Script elements for which to get the Javadoc
	 * @return a string with the Javadoc in HTML format.
	 */
	private String getScriptdocHtml(IModelElement[] result) {
		StringBuffer buffer = new StringBuffer();
		int nResults = result.length;
		if (nResults == 0)
			return null;
		if (nResults > 1) {
			for (int i = 0; i < result.length; i++) {
				HTMLPrinter.startBulletList(buffer);
				IModelElement curr = result[i];
				if (curr instanceof IMember)
					HTMLPrinter.addBullet(buffer, getInfoText((IMember) curr));
				HTMLPrinter.endBulletList(buffer);
			}
		} else {
			IModelElement curr = result[0];
			if (curr instanceof IMember) {
				IMember member = (IMember) curr;
				// HTMLPrinter.addSmallHeader(buffer, getInfoText(member));
				Reader reader;
				try {
					reader = ScriptDocumentationAccess.getHTMLContentReader(getNature(), member, true, true);
					// Provide hint why there's no Javadoc
					if (reader == null) {
						reader = new StringReader(InfoViewMessages.ScriptdocView_noAttachedInformation);
					}
				} catch (ModelException ex) {
					return null;
				}
				if (reader != null) {
					HTMLPrinter.addParagraph(buffer, reader);
				}
			}
		}
		boolean flushContent = true;
		if (buffer.length() > 0 || flushContent) {
			HTMLPrinter.insertPageProlog(buffer, 0, fBackgroundColorRGB, fgStyleSheet);
			HTMLPrinter.addPageEpilog(buffer);
			return buffer.toString();
		}
		return null;
	}

	/**
	 * Gets the label for the given member.
	 *
	 * @param member
	 *            the Script member
	 * @return a string containing the member's label
	 */
	private String getInfoText(IMember member) {
		return ScriptElementLabels.getDefault().getElementLabel(member, LABEL_FLAGS);
	}

	/*
	 * @see org.eclipse.dltk.internal.ui.infoviews.AbstractInfoView#isIgnoringNewInput(org.eclipse.dltk.core.IModelElement,
	 *      org.eclipse.jface.viewers.ISelection)
	 *
	 */
	protected boolean isIgnoringNewInput(IModelElement je, IWorkbenchPart part, ISelection selection) {
		// XXX: think about me
		return false;
	}

	/*
	 * @see AbstractInfoView#findSelectedModelElement(IWorkbenchPart)
	 */
	protected IModelElement findSelectedModelElement(IWorkbenchPart part, ISelection selection) {
		IModelElement element = super.findSelectedModelElement(part, selection);
		return element;
	}

	/*
	 * @see AbstractInfoView#getControl()
	 */
	protected Control getControl() {
		if (fIsUsingBrowserWidget)
			return fBrowser;
		else
			return fText;
	}

	/*
	 * @see org.eclipse.dltk.internal.ui.infoviews.AbstractInfoView#getHelpContextId()
	 *
	 */
	protected String getHelpContextId() {
		//return IJavaHelpContextIds.JAVADOC_VIEW;
		System.err.println("TODO: add help support here"); //$NON-NLS-1$
		return ""; //$NON-NLS-1$
	}
}
