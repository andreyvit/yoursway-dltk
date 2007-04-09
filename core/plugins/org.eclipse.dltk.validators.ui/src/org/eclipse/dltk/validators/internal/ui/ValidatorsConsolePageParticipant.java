package org.eclipse.dltk.validators.internal.ui;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsolePageParticipant;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.part.IPageBookViewPage;

public class ValidatorsConsolePageParticipant implements
		IConsolePageParticipant {
	public static final String DLTK_VALIDATORS_CONSOLE = "DLTK Validators output";
	public void activated() {
	}

	public void deactivated() {
	}

	public void dispose() {
	}

	public void init(IPageBookViewPage page, IConsole console) {
		if( console.getName().equals(DLTK_VALIDATORS_CONSOLE) && console instanceof IOConsole ) {					
			IActionBars bars = page.getSite().getActionBars();
			IToolBarManager toolbarManager = bars.getToolBarManager();
			
			CloseValidatorsConsoleAction closeConsoleAction = new CloseValidatorsConsoleAction(
					(IOConsole) console,
					"Close",
					"Close console");

			toolbarManager.appendToGroup(IConsoleConstants.LAUNCH_GROUP, closeConsoleAction );
		}
	}

	public Object getAdapter(Class adapter) {
		return null;
	}
}
