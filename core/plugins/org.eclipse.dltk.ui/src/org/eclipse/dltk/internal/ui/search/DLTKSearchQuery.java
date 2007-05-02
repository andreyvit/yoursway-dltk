/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.search;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.PerformanceStats;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.internal.corext.util.SearchUtils;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.dltk.ui.search.ElementQuerySpecification;
import org.eclipse.dltk.ui.search.IMatchPresentation;
import org.eclipse.dltk.ui.search.IQueryParticipant;
import org.eclipse.dltk.ui.search.ISearchRequestor;
import org.eclipse.dltk.ui.search.PatternQuerySpecification;
import org.eclipse.dltk.ui.search.QuerySpecification;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.ISearchResult;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.search.ui.text.Match;


public class DLTKSearchQuery implements ISearchQuery {

	private static final String PERF_SEARCH_PARTICIPANT= "org.eclipse.dltk.ui/perf/search/participants"; //$NON-NLS-1$

	private ISearchResult fResult;
	private final QuerySpecification fPatternData;
	
	public DLTKSearchQuery(QuerySpecification data) {
		if (data == null) {
			throw new IllegalArgumentException("data must not be null"); //$NON-NLS-1$
		}
		fPatternData= data;
	}
	
	private static class SearchRequestor implements ISearchRequestor {
		private IQueryParticipant fParticipant;
		private DLTKSearchResult fSearchResult;
		public void reportMatch(Match match) {
			IMatchPresentation participant= fParticipant.getUIParticipant();
			if (participant == null || match.getElement() instanceof IModelElement || match.getElement() instanceof IResource) {
				fSearchResult.addMatch(match);
			} else {
				fSearchResult.addMatch(match, participant);
			}
		}
		
		protected SearchRequestor(IQueryParticipant participant, DLTKSearchResult result) {
			super();
			fParticipant= participant;
			fSearchResult= result;
		}
	}
	
	public IStatus run(IProgressMonitor monitor) {
		final DLTKSearchResult textResult= (DLTKSearchResult) getSearchResult();
		textResult.removeAll();
		// Don't need to pass in working copies in 3.0 here
		SearchEngine engine= new SearchEngine();
		try {

			int totalTicks= 1000;
			IProject[] projects= DLTKSearchScopeFactory.getInstance().getProjects(fPatternData.getScope());
			final SearchParticipantRecord[] participantDescriptors= SearchParticipantsExtensionPoint.getInstance().getSearchParticipants(projects);
			final int[] ticks= new int[participantDescriptors.length];
			for (int i= 0; i < participantDescriptors.length; i++) {
				final int iPrime= i;
				ISafeRunnable runnable= new ISafeRunnable() {
					public void handleException(Throwable exception) {
						ticks[iPrime]= 0;
						String message= SearchMessages.DLTKSearchQuery_error_participant_estimate; 
						DLTKUIPlugin.log(new Status(IStatus.ERROR, DLTKUIPlugin.getPluginId(), 0, message, exception));
					}

					public void run() throws Exception {
						ticks[iPrime]= participantDescriptors[iPrime].getParticipant().estimateTicks(fPatternData);
					}
				};
				
				SafeRunner.run(runnable);
				totalTicks+= ticks[i];
			}
			
			SearchPattern pattern;
			String stringPattern;
			
			if (fPatternData instanceof ElementQuerySpecification) {
				IModelElement element= ((ElementQuerySpecification) fPatternData).getElement();
				stringPattern= ScriptElementLabels.getDefault().getElementLabel(element, ScriptElementLabels.ALL_DEFAULT);
				if (!element.exists()) {
					return new Status(IStatus.ERROR, DLTKUIPlugin.getPluginId(), 0, Messages.format(SearchMessages.DLTKSearchQuery_error_element_does_not_exist, stringPattern), null);  
				}
				pattern= SearchPattern.createPattern(element, fPatternData.getLimitTo(), SearchUtils.GENERICS_AGNOSTIC_MATCH_RULE);
			} else {
				PatternQuerySpecification patternSpec = (PatternQuerySpecification) fPatternData;
				stringPattern= patternSpec.getPattern();
				int matchMode= getMatchMode(stringPattern) | SearchPattern.R_ERASURE_MATCH;
				if (patternSpec.isCaseSensitive())
					matchMode |= SearchPattern.R_CASE_SENSITIVE;
				pattern= SearchPattern.createPattern(patternSpec.getPattern(), patternSpec.getSearchFor(), patternSpec.getLimitTo(), matchMode);
			}
			
			if (pattern == null) {
				return new Status(IStatus.ERROR, DLTKUIPlugin.getPluginId(), 0, Messages.format(SearchMessages.DLTKSearchQuery_error_unsupported_pattern, stringPattern), null);  
			}
			monitor.beginTask(Messages.format(SearchMessages.DLTKSearchQuery_task_label, stringPattern), totalTicks); 
			IProgressMonitor mainSearchPM= new SubProgressMonitor(monitor, 1000);

			boolean ignorePotentials= NewSearchUI.arePotentialMatchesIgnored();
			NewSearchResultCollector collector= new NewSearchResultCollector(textResult, ignorePotentials);
			
			
			engine.search(pattern, new SearchParticipant[] { SearchEngine.getDefaultSearchParticipant() }, fPatternData.getScope(), collector, mainSearchPM);
			for (int i= 0; i < participantDescriptors.length; i++) {
				final ISearchRequestor requestor= new SearchRequestor(participantDescriptors[i].getParticipant(), textResult);
				final IProgressMonitor participantPM= new SubProgressMonitor(monitor, ticks[i]);

				final int iPrime= i;
				ISafeRunnable runnable= new ISafeRunnable() {
					public void handleException(Throwable exception) {
						participantDescriptors[iPrime].getDescriptor().disable();
						String message= SearchMessages.DLTKSearchQuery_error_participant_search; 
						DLTKUIPlugin.log(new Status(IStatus.ERROR, DLTKUIPlugin.getPluginId(), 0, message, exception));
					}

					public void run() throws Exception {

						final IQueryParticipant participant= participantDescriptors[iPrime].getParticipant();

						final PerformanceStats stats= PerformanceStats.getStats(PERF_SEARCH_PARTICIPANT, participant);
						stats.startRun();

						participant.search(requestor, fPatternData, participantPM);

						stats.endRun();
					}
				};
				
				SafeRunner.run(runnable);
			}
			
		} catch (CoreException e) {
			return e.getStatus();
		}
		String message= Messages.format(SearchMessages.DLTKSearchQuery_status_ok_message, String.valueOf(textResult.getMatchCount())); 
		return new Status(IStatus.OK, DLTKUIPlugin.getPluginId(), 0, message, null);
	}
	
	private int getMatchMode(String pattern) {
		if (pattern.indexOf('*') != -1 || pattern.indexOf('?') != -1) {
			return SearchPattern.R_PATTERN_MATCH;
		} else if (SearchUtils.isCamelCasePattern(pattern)) {
			return SearchPattern.R_CAMELCASE_MATCH;
		}
		return SearchPattern.R_EXACT_MATCH;
	}

	public String getLabel() {
		return SearchMessages.DLTKSearchQuery_label; 
	}

	public String getResultLabel(int nMatches) {
		if (nMatches == 1) {
			String[] args= { getSearchPatternDescription(), fPatternData.getScopeDescription() };
			switch (fPatternData.getLimitTo()) {
//				case IDLTKSearchConstants.IMPLEMENTORS:
//					return Messages.format(SearchMessages.DLTKSearchOperation_singularImplementorsPostfix, args); 
				case IDLTKSearchConstants.DECLARATIONS:
					return Messages.format(SearchMessages.DLTKSearchOperation_singularDeclarationsPostfix, args); 
				case IDLTKSearchConstants.REFERENCES:
					return Messages.format(SearchMessages.DLTKSearchOperation_singularReferencesPostfix, args); 
				case IDLTKSearchConstants.ALL_OCCURRENCES:
					return Messages.format(SearchMessages.DLTKSearchOperation_singularOccurrencesPostfix, args); 
//				case IDLTKSearchConstants.READ_ACCESSES:
//					return Messages.format(SearchMessages.DLTKSearchOperation_singularReadReferencesPostfix, args); 
//				case IDLTKSearchConstants.WRITE_ACCESSES:
//					return Messages.format(SearchMessages.DLTKSearchOperation_singularWriteReferencesPostfix, args); 
				default:
					return Messages.format(SearchMessages.DLTKSearchOperation_singularOccurrencesPostfix, args); 
			}
		} else {
			Object[] args= { getSearchPatternDescription(), new Integer(nMatches), fPatternData.getScopeDescription() };
			switch (fPatternData.getLimitTo()) {
//				case IDLTKSearchConstants.IMPLEMENTORS:
//					return Messages.format(SearchMessages.DLTKSearchOperation_pluralImplementorsPostfix, args); 
				case IDLTKSearchConstants.DECLARATIONS:
					return Messages.format(SearchMessages.DLTKSearchOperation_pluralDeclarationsPostfix, args); 
				case IDLTKSearchConstants.REFERENCES:
					return Messages.format(SearchMessages.DLTKSearchOperation_pluralReferencesPostfix, args); 
				case IDLTKSearchConstants.ALL_OCCURRENCES:
					return Messages.format(SearchMessages.DLTKSearchOperation_pluralOccurrencesPostfix, args); 
//				case IDLTKSearchConstants.READ_ACCESSES:
//					return Messages.format(SearchMessages.DLTKSearchOperation_pluralReadReferencesPostfix, args); 
//				case IDLTKSearchConstants.WRITE_ACCESSES:
//					return Messages.format(SearchMessages.DLTKSearchOperation_pluralWriteReferencesPostfix, args); 
				default:
					return Messages.format(SearchMessages.DLTKSearchOperation_pluralOccurrencesPostfix, args); 
			}
		}
	}
	
	private String getSearchPatternDescription() {
		if (fPatternData instanceof ElementQuerySpecification) {
			IModelElement element= ((ElementQuerySpecification) fPatternData).getElement();
			return ScriptElementLabels.getDefault().getElementLabel(element, ScriptElementLabels.ALL_DEFAULT
					| ScriptElementLabels.ALL_FULLY_QUALIFIED | ScriptElementLabels.USE_RESOLVED);
		} 
		return ((PatternQuerySpecification) fPatternData).getPattern();
	}

	ImageDescriptor getImageDescriptor() {
		if (/*fPatternData.getLimitTo() == IDLTKSearchConstants.IMPLEMENTORS ||*/ fPatternData.getLimitTo() == IDLTKSearchConstants.DECLARATIONS)
			return DLTKPluginImages.DESC_OBJS_SEARCH_DECL;
		else
			return DLTKPluginImages.DESC_OBJS_SEARCH_REF;
	}

	public boolean canRerun() {
		return true;
	}

	public boolean canRunInBackground() {
		return true;
	}

	public ISearchResult getSearchResult() {
		if (fResult == null) {
			fResult= new DLTKSearchResult(this);
			new SearchResultUpdater((DLTKSearchResult) fResult);
		}
		return fResult;
	}
	
	QuerySpecification getSpecification() {
		return fPatternData;
	}
}
