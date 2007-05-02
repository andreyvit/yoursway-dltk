/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.refactoring.reorg;

import org.eclipse.dltk.internal.corext.refactoring.rename.RenameDLTKProjectProcessor;
import org.eclipse.dltk.internal.corext.refactoring.rename.RenameResourceProcessor;
import org.eclipse.dltk.internal.corext.refactoring.rename.RenameScriptFolderProcessor;
import org.eclipse.dltk.internal.corext.refactoring.rename.RenameSourceFolderProcessor;
import org.eclipse.dltk.internal.corext.refactoring.rename.RenameSourceModuleProcessor;
import org.eclipse.dltk.internal.ui.refactoring.UserInterfaceManager;

public class RenameUserInterfaceManager extends UserInterfaceManager {
	private static final UserInterfaceManager fgInstance= new RenameUserInterfaceManager();
	
	public static UserInterfaceManager getDefault() {
		return fgInstance;
	}
	
	private RenameUserInterfaceManager() {
		put(RenameResourceProcessor.class, RenameUserInterfaceStarter.class, RenameResourceWizard.class);
//		
		put(RenameDLTKProjectProcessor.class, RenameUserInterfaceStarter.class, RenameDLTKProjectWizard.class);
		put(RenameSourceFolderProcessor.class, RenameUserInterfaceStarter.class, RenameSourceFolderWizard.class);
		put(RenameScriptFolderProcessor.class, RenameUserInterfaceStarter.class, RenameScriptFolderWizard.class);
		put(RenameSourceModuleProcessor.class, RenameUserInterfaceStarter.class, RenameSourceModuleWizard.class);
//		put(RenameTypeProcessor.class, RenameUserInterfaceStarter.class, RenameTypeWizard.class);
//		put(RenameFieldProcessor.class, RenameUserInterfaceStarter.class, RenameFieldWizard.class);
//		put(RenameEnumConstProcessor.class, RenameUserInterfaceStarter.class, RenameEnumConstWizard.class);
//		put(RenameTypeParameterProcessor.class, RenameUserInterfaceStarter.class, RenameTypeParameterWizard.class);
//		put(RenameNonVirtualMethodProcessor.class, RenameMethodUserInterfaceStarter.class, RenameMethodWizard.class);
//		put(RenameVirtualMethodProcessor.class, RenameMethodUserInterfaceStarter.class, RenameMethodWizard.class);
//		put(RenameLocalVariableProcessor.class, RenameUserInterfaceStarter.class, RenameLocalVariableWizard.class);
	}
}
