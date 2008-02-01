package org.eclipse.dltk.ui.dialogs;

import java.util.Iterator;

import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.internal.WorkbenchMessages;

import com.ibm.icu.text.MessageFormat;

/**
 * Creates a link between a property page and preference page
 */
public class PropToPrefLinkArea {

	private Link pageLink;

	public PropToPrefLinkArea(Composite parent, int style, final String pageId,
			String message, final Shell shell, final Object pageData) {
		/*
		 * breaking new ground yet again - want to link between property and
		 * preference paes. ie: project specific debug engine options to 
		 * general debugging options
		 */
		pageLink = new Link(parent, style);

		IPreferenceNode node = getPreferenceNode(pageId);
		String result;
		if (node == null) {
			result = NLS
					.bind(WorkbenchMessages.PreferenceNode_NotFound, pageId);
		} else {
			result = MessageFormat.format(message, new String[] { node
					.getLabelText() });

			// only add the selection listener if the node is found
			pageLink.addSelectionListener(new SelectionAdapter() {

				public void widgetSelected(SelectionEvent e) {
					PreferencesUtil.createPreferenceDialogOn(shell, pageId,
							new String[] { pageId }, pageData).open();
				}

			});
		}
		pageLink.setText(result);

	}

	/**
	 * Returns the property link control
	 */
	public Control getControl() {
		return pageLink;
	}

	private IPreferenceNode getPreferenceNode(String pageId) {
		Iterator iterator = PlatformUI.getWorkbench().getPreferenceManager()
				.getElements(PreferenceManager.PRE_ORDER).iterator();
		while (iterator.hasNext()) {
			IPreferenceNode next = (IPreferenceNode) iterator.next();
			if (next.getId().equals(pageId)) {
				return next;
			}
		}
		return null;
	}
}
