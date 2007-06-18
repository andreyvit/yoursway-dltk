package org.eclipse.dltk.internal.debug.ui;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchActionConstants;
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
		viewer.setEditable(false);

		createActions();
		createMenu();
		createToolbar();
		createContextMenu();
	}

	public void append(String text) {
		try {
			document.replace(document.getLength(), 0, text);
			viewer.revealRange(document.getLength(), 0);

		} catch (BadLocationException e) {

		}
	}

	private IAction copyAction;
	private IAction clearAction;

	private final String COPY_ACTION_NAME = "Copy";
	private final String CLEAR_ACTION_NAME = "Clear";

	public void createActions() {
		copyAction = new Action(COPY_ACTION_NAME) {
			public void run() {
				viewer.doOperation(TextViewer.COPY);
			}
		};

		clearAction = new Action(CLEAR_ACTION_NAME) {
			public void run() {
				try {
					document.replace(0, document.getLength(), "");
				} catch (BadLocationException e) {
				}
			}
		};
	}

	private void createMenu() {
		IMenuManager manager = getViewSite().getActionBars().getMenuManager();
		manager.add(copyAction);
		manager.add(clearAction);
	}

	private void createToolbar() {
		IToolBarManager manager = getViewSite().getActionBars()
				.getToolBarManager();
		manager.add(copyAction);
		manager.add(clearAction);
	}

	private void createContextMenu() {
		// Create menu manager.
		MenuManager menuManager = new MenuManager();
		menuManager.setRemoveAllWhenShown(true);
		menuManager.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				fillContextMenu(manager);
			}
		});

		// Create menu.
		Menu menu = menuManager.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);

		// Register menu for extension.
		getSite().registerContextMenu(menuManager, viewer);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(copyAction);
		manager.add(clearAction);		
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
}
