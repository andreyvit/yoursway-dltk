/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core.mixin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.mixin.IMixinParser;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.core.search.SearchDocument;
import org.eclipse.dltk.core.search.index.Index;
import org.eclipse.dltk.core.search.indexing.AbstractIndexer;

public class MixinIndexer extends AbstractIndexer {
	MixinIndexRequestor requestor = new MixinIndexRequestor();
	ISourceModule sourceModule;
	Index currentIndex;
	public int index;
	public String elementName;

	public MixinIndexer(SearchDocument document, ISourceModule module,
			Index currentIndex) {
		super(document);
		this.sourceModule = module;
		this.currentIndex = currentIndex;
	}

	public void indexDocument() {
		IDLTKLanguageToolkit toolkit = this.document.getToolkit();
		if (toolkit == null) {
			toolkit = DLTKLanguageManager.findToolkit(new Path(this.document
					.getPath()));
		}
		if (toolkit == null) {
			return;
		}
		try {
			IMixinParser parser = MixinManager.getMixinParser(toolkit
					.getNatureId());
			if (parser != null) {
				parser.setRequirestor(this.requestor);
				parser.parserSourceModule(false, this.sourceModule);
			}
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
	}

	private class MixinIndexRequestor implements IMixinRequestor {
		public void reportElement(ElementInfo info) {
			if (info.key.length() > 0) {
				// Thread safe support
				synchronized (currentIndex) {
					MixinIndexer.this.addMixin(info.key.toCharArray());
				}
			}
		}
	}
}
