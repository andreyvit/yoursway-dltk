/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.tests.navigator.scriptexplorer;

import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.IScriptModel;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.core.SourceModule;


/**
 * @author Jen's account
 *
 */
public class TestDelta implements IModelElementDelta {

	private int fKind;
	private IModelElement fElement;

	private IModelElementDelta[] fAffectedChildren;

	public TestDelta(int kind, IModelElement element) {
		fKind= kind;
		fElement= element;
	}
	
	public IModelElementDelta[] getAddedChildren() {
		return null;
	}
	
	public IModelElementDelta[] getAffectedChildren() {
		if (fAffectedChildren == null)
			return new IModelElementDelta[0];
		else
			return fAffectedChildren;
	}
	
	public IModelElementDelta[] getChangedChildren() {
		return null;
	}
	
	public IModelElement getElement() {
		return fElement;
	}
	
	public int getFlags() {
		return 0;
	}
	
	public int getKind() {
		return fKind;
	}
	
	public IModelElement getMovedFromElement() {
		return null;
	}
	
	public IModelElement getMovedToElement() {
		return null;
	}
	
	public IModelElementDelta[] getRemovedChildren() {
		return null;
	}
	
	public IResourceDelta[] getResourceDeltas() {
		return null;
	}

	public void setAffectedChildren(IModelElementDelta[] children) {
		fAffectedChildren= children;
	}
	
	public static TestDelta createParentDeltas(IScriptFolder frag, TestDelta delta) {
		IModelElement root= frag.getParent();
		TestDelta rootDelta= new TestDelta(IModelElementDelta.CHANGED, root);

		IDLTKProject proj= root.getScriptProject();
		TestDelta projectDelta= new TestDelta(IModelElementDelta.CHANGED, proj);

		IScriptModel model= proj.getModel();
		TestDelta modelDelta= new TestDelta(IModelElementDelta.CHANGED, model);

		//set affected children
		modelDelta.setAffectedChildren(new IModelElementDelta[] { projectDelta });
		projectDelta.setAffectedChildren(new IModelElementDelta[] { rootDelta });
		rootDelta.setAffectedChildren(new IModelElementDelta[] { delta });
		return modelDelta;
	}

	public static IModelElementDelta createCUDelta(ISourceModule[] cu, IScriptFolder parent, int action) {
		TestDelta fragmentDelta= new TestDelta(IModelElementDelta.CHANGED, parent);

		TestDelta[] deltas= new TestDelta[cu.length];
		for (int i= 0; i < cu.length; i++) {
			deltas[i]= new TestDelta(action, cu[i]);
		}

		fragmentDelta.setAffectedChildren(deltas);
		return createParentDeltas(parent, fragmentDelta);
	}

	public static IModelElementDelta createDelta(IScriptFolder frag, int action) {
		TestDelta delta= new TestDelta(action, frag);
		return createParentDeltas(frag, delta);
	}
		
	public SourceModule getSourceModuleAST() {
		// TODO Auto-generated method stub
		return null;
	}

}
