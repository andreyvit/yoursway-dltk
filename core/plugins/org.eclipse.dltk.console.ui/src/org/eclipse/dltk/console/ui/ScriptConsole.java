/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.console.ui;

import java.io.IOException;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.dltk.console.IScriptInterpreter;
import org.eclipse.dltk.console.ScriptConsoleHistory;
import org.eclipse.dltk.console.ScriptConsolePrompt;
import org.eclipse.dltk.console.ui.internal.ICommandHandler;
import org.eclipse.dltk.console.ui.internal.ScriptConsoleInput;
import org.eclipse.dltk.console.ui.internal.ScriptConsolePage;
import org.eclipse.dltk.console.ui.internal.ScriptConsoleSession;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.ui.console.IConsoleDocumentPartitioner;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.TextConsole;
import org.eclipse.ui.part.IPageBookViewPage;


public class ScriptConsole extends TextConsole implements ICommandHandler {

	private ScriptConsolePage page;

	private ScriptConsolePartitioner partitioner;

	private IContentAssistProcessor processor;

	private ITextHover hover;

	private IScriptInterpreter interpreter;

	private ScriptConsoleSession session;

	private ListenerList consoleListeners;

	private ScriptConsolePrompt prompt;

	private ScriptConsoleHistory history;

	private IConsoleStyleProvider styleProvider;

	protected IConsoleDocumentPartitioner getPartitioner() {
		return partitioner;
	}

	public ScriptConsole(String consoleName, String consoleType) {
		super(consoleName, consoleType, null, true);

		this.consoleListeners = new ListenerList(ListenerList.IDENTITY);
		this.prompt = new ScriptConsolePrompt("=>", "->");
		this.history = new ScriptConsoleHistory();

		this.session = new ScriptConsoleSession();
		addListener(this.session);

		partitioner = new ScriptConsolePartitioner();
		getDocument().setDocumentPartitioner(partitioner);
		partitioner.connect(getDocument());	
	}

	public IScriptConsoleSession getSession() {
		return session;
	}

	public void addListener(IScriptConsoleListener listener) {
		consoleListeners.add(listener);
	}

	public void removeListener(IScriptConsoleListener listener) {
		consoleListeners.remove(listener);
	}

	protected void setContentAssistProcessor(IContentAssistProcessor processor) {
		this.processor = processor;
	}

	protected void setInterpreter(IScriptInterpreter interpreter) {
		this.interpreter = interpreter;
	}
	
	protected void setStyleProvider (IConsoleStyleProvider provider) {
		this.styleProvider = provider;		
	}

	public void setPrompt(ScriptConsolePrompt prompt) {
		this.prompt = prompt;
	}

	public ScriptConsolePrompt getPrompt() {
		return prompt;
	}

	public ScriptConsoleHistory getHistory() {
		return history;
	}

	protected void setTextHover(ITextHover hover) {
		this.hover = hover;
	}

	public IPageBookViewPage createPage(IConsoleView view) {
		SourceViewerConfiguration cfg = new ScriptConsoleSourceViewerConfiguration(
				processor, hover);
		page = new ScriptConsolePage(this, view, cfg);
		if (styleProvider != null)
			page.setStyleProviser(styleProvider);
		return page;
	}

	public void clearConsole() {
		page.clearConsolePage();
	}

	public IScriptConsoleInput getInput() {
		return new ScriptConsoleInput(page);
	}

	public String handleCommand(String userInput) throws IOException {
		Object[] listeners = consoleListeners.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			((IScriptConsoleListener) listeners[i]).userRequest(userInput);
		}

		interpreter.exec(userInput);

		String output = interpreter.getOutput();

		
		if (interpreter.getState() == IScriptInterpreter.WAIT_NEW_COMMAND){
			prompt.setMode(true);
		} else {
			prompt.setMode(false);
		}

		for (int i = 0; i < listeners.length; i++) {
			((IScriptConsoleListener) listeners[i]).interpreterResponse(output);
		}

		return output;
	}

	public void terminate() {
		try {
			interpreter.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
