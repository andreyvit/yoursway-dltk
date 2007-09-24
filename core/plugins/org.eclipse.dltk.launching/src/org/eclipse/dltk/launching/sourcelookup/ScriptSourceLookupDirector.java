package org.eclipse.dltk.launching.sourcelookup;

import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupDirector;
import org.eclipse.debug.core.sourcelookup.ISourceLookupParticipant;

/**
 * Script source lookup director
 * 
 * @see org.eclipse.debug.core.model.IPersistableSourceLocator
 */
public class ScriptSourceLookupDirector extends AbstractSourceLookupDirector {

	/*
	 * @see org.eclipse.debug.core.sourcelookup.ISourceLookupDirector#initializeParticipants()
	 */
	public void initializeParticipants() {
		addParticipants(new ISourceLookupParticipant[] { new DBGPSourceLookupParticipant() });
		addParticipants(new ISourceLookupParticipant[] { new ScriptSourceLookupParticipant() });
	}
}
