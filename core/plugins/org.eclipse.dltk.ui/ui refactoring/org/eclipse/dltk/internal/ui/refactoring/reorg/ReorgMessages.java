/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.refactoring.reorg;

import org.eclipse.osgi.util.NLS;

public final class ReorgMessages extends NLS {

	private static final String BUNDLE_NAME= "org.eclipse.dltk.internal.ui.refactoring.reorg.ReorgMessages";//$NON-NLS-1$

	private ReorgMessages() {
		// Do not instantiate
	}

	public static String CutAction_text;
	public static String ScriptMoveAction_update_references;
	public static String ReorgQueries_enterNewNameQuestion;
	public static String ReorgQueries_nameConflictMessage;
	public static String ReorgQueries_resourceWithThisNameAlreadyExists;
	public static String ReorgQueries_invalidNameMessage;
	public static String ReorgQueries_resourceExistsWithDifferentCaseMassage;
	public static String ReorgQueries_skip_all;
	public static String CopyToClipboardAction_0;
	public static String CopyToClipboardAction_1;
	public static String CopyToClipboardAction_2;
	public static String CopyToClipboardAction_3;
	public static String CopyToClipboardAction_4;
	public static String CopyToClipboardAction_5;
	public static String DeleteAction_3;
	public static String DeleteAction_4;
	public static String ReorgMoveAction_3;
	public static String ReorgMoveAction_4;
	public static String ReorgMoveWizard_3;
	public static String ReorgMoveWizard_4;
	public static String ReorgMoveWizard_textual_move;
	public static String ReorgMoveWizard_newPackage;
	public static String ReorgUserInputPage_choose_destination_single;
	public static String ReorgUserInputPage_choose_destination_multi;
	public static String PasteAction_4;
	public static String PasteAction_5;
	static {
		NLS.initializeMessages(BUNDLE_NAME, ReorgMessages.class);
	}
}
