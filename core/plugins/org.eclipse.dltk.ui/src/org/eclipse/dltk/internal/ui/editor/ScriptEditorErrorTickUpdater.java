/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.editor;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.DLTKUILanguageManager;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.ProblemsLabelDecorator;
import org.eclipse.dltk.ui.ScriptElementImageProvider;
import org.eclipse.dltk.ui.viewsupport.IProblemChangedListener;
import org.eclipse.dltk.ui.viewsupport.ScriptUILabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;

/**
 * The <code>JavaEditorErrorTickUpdater</code> will register as a IProblemChangedListener
 * to listen on problem changes of the editor's input. It updates the title images when the annotation
 * model changed.
 */
public class ScriptEditorErrorTickUpdater implements IProblemChangedListener {

	private ScriptEditor fScriptEditor;
	private ScriptUILabelProvider fLabelProvider;

	public ScriptEditorErrorTickUpdater(ScriptEditor editor) {
		Assert.isNotNull(editor);
		fScriptEditor= editor;
//		fLabelProvider=  new ScriptUILabelProvider(0, ScriptElementImageProvider.SMALL_ICONS);
		fLabelProvider = DLTKUILanguageManager.createLabelProvider(editor.getLanguageToolkit().getNatureId());
		fLabelProvider.setTextFlags(0);
		fLabelProvider.setImageFlags(ScriptElementImageProvider.SMALL_ICONS);
		fLabelProvider.addLabelDecorator(new ProblemsLabelDecorator(null));
		DLTKUIPlugin.getDefault().getProblemMarkerManager().addListener(this);
	}

	/* (non-Javadoc)
	 * @see IProblemChangedListener#problemsChanged(IResource[], boolean)
	 */
	public void problemsChanged(IResource[] changedResources, boolean isMarkerChange) {
		if (!isMarkerChange)
			return;

		IEditorInput input= fScriptEditor.getEditorInput();
		if (input != null) { // might run async, tests needed
			IModelElement jelement= (IModelElement) input.getAdapter(IModelElement.class);
			if (jelement != null) {
				IResource resource= jelement.getResource();
				for (int i = 0; i < changedResources.length; i++) {
					if (changedResources[i].equals(resource)) {
						updateEditorImage(jelement);
					}
				}
			}
		}
	}

	public void updateEditorImage(IModelElement jelement) {
		Image titleImage= fScriptEditor.getTitleImage();
		if (titleImage == null) {
			return;
		}
		Image newImage;
		if (jelement instanceof ISourceModule && !jelement.getScriptProject().isOnBuildpath(jelement))
			newImage= fLabelProvider.getImage(jelement.getResource());
		else
			newImage= fLabelProvider.getImage(jelement);
		if (titleImage != newImage) {
			postImageChange(newImage);
		}
	}

	private void postImageChange(final Image newImage) {
		Shell shell= fScriptEditor.getEditorSite().getShell();
		if (shell != null && !shell.isDisposed()) {
			shell.getDisplay().syncExec(new Runnable() {
				public void run() {
					fScriptEditor.updatedTitleImage(newImage);
				}
			});
		}
	}

	public void dispose() {
		fLabelProvider.dispose();
		DLTKUIPlugin.getDefault().getProblemMarkerManager().removeListener(this);
	}


}


