/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.search;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * This is the interface expected of extensions to the extension point
 * <code>org.eclipse.dltk.ui.queryParticipants</code>.
 * <p> 
 * A <code>IQueryParticipant</code> is called during the execution of a 
 * Script search query. It can report matches via an {@link ISearchRequestor} and 
 * may contribute a {@link IMatchPresentation} to help render the elements it contributes.
 * </p>
 * <p>
 * Clients may implement this interface.
 * </p>
 *
	 *
 */
public interface IQueryParticipant {
	/**
	 * Executes the search described by the given <code>querySpecification</code>. Matches are reported
	 * to the given <code>requester</code>.
	 * The interpretation of what a given Script search (e.g. "References to class Foo") means is up to the 
	 * participant.
	 * @param requestor The requester to report matches to.
	 * @param querySpecification The specification of the query to run.
	 * @param monitor A monitor to report progress on.
	 * @throws CoreException Thrown when the search can not be executed
	 */
	void search(ISearchRequestor requestor, QuerySpecification querySpecification, IProgressMonitor monitor) throws CoreException;
	/**
	 * Returns the number of units of work estimated. The returned number should be normalized such
	 * that the number of ticks for the original Script search job is 1000. For example if the participant
	 * uses the same amount of time as the Script search, it should return 1000, if it uses half the time,
	 * it should return 500, etc.
	 * This method is supposed to give a quick estimate of the work to be done and is assumed
	 * to be much faster than the actual query.
	 * @param specification the specification to estimate.
	 * @return The number of ticks estimated.
	 */
	int estimateTicks(QuerySpecification specification);
	/**
	 * Gets the UI participant responsible for handling the display of elements not known to the Script search UI.  The Script search UI knows
	 * elements are of type <code>IModelElement</code> and <code>IResource</code>.
	 * A participant may return <code>null</code> if matches are only reported against elements of type <code>IResource</code> and <code>IModelElement</code>.
	 * @return The UI participant for this query participant or <code>null</code>.
	 */
	IMatchPresentation getUIParticipant();
}
