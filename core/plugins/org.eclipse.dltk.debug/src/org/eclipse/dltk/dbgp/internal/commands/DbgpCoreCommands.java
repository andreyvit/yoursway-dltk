/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.internal.commands;

import java.net.URI;
import java.util.Map;

import org.eclipse.dltk.dbgp.IDbgpFeature;
import org.eclipse.dltk.dbgp.IDbgpProperty;
import org.eclipse.dltk.dbgp.IDbgpStackLevel;
import org.eclipse.dltk.dbgp.IDbgpStatus;
import org.eclipse.dltk.dbgp.breakpoints.DbgpBreakpointConfig;
import org.eclipse.dltk.dbgp.breakpoints.IDbgpBreakpoint;
import org.eclipse.dltk.dbgp.commands.IDbgpBreakpointCommands;
import org.eclipse.dltk.dbgp.commands.IDbgpContextCommands;
import org.eclipse.dltk.dbgp.commands.IDbgpContinuationCommands;
import org.eclipse.dltk.dbgp.commands.IDbgpCoreCommands;
import org.eclipse.dltk.dbgp.commands.IDbgpDataTypeCommands;
import org.eclipse.dltk.dbgp.commands.IDbgpFeatureCommands;
import org.eclipse.dltk.dbgp.commands.IDbgpPropertyCommands;
import org.eclipse.dltk.dbgp.commands.IDbgpSourceCommands;
import org.eclipse.dltk.dbgp.commands.IDbgpStatckCommands;
import org.eclipse.dltk.dbgp.commands.IDbgpStatusCommands;
import org.eclipse.dltk.dbgp.commands.IDbgpStreamCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;

public class DbgpCoreCommands implements IDbgpCoreCommands {

	private final IDbgpFeatureCommands featureCommands;

	private final IDbgpStatusCommands statusCommands;

	private final IDbgpBreakpointCommands breakpointCommands;

	private final IDbgpSourceCommands sourceCommands;

	private final IDbgpContextCommands contextCommands;

	private final IDbgpStatckCommands stackCommands;

	private final IDbgpContinuationCommands continuationCommands;

	private final IDbgpStreamCommands streamCommands;

	private final IDbgpDataTypeCommands dataTypeCommands;

	private final IDbgpPropertyCommands propertyCommands;

	public DbgpCoreCommands(IDbgpCommunicator communicator) {
		this.featureCommands = new DbgpFeatureCommands(communicator);
		this.statusCommands = new DbgpStatusCommands(communicator);
		this.breakpointCommands = new DbgpBreakpointCommands(communicator);
		this.sourceCommands = new DbgpSourceCommands(communicator);
		this.contextCommands = new DbgpContextCommands(communicator);
		this.stackCommands = new DbgpStackCommands(communicator);
		this.continuationCommands = new DbgpContinuationCommands(communicator);
		this.streamCommands = new DbgpStreamCommands(communicator);
		this.propertyCommands = new DbgpPropertyCommands(communicator);
		this.dataTypeCommands = new DbgpDataTypeCommands(communicator);
	}

	public IDbgpFeature getFeature(String featureName) throws DbgpException {
		return featureCommands.getFeature(featureName);
	}

	public boolean setFeature(String featureName, String featureValue)
			throws DbgpException {
		return featureCommands.setFeature(featureName, featureValue);
	}

	public IDbgpBreakpoint getBreakpoint(String id) throws DbgpException {
		return breakpointCommands.getBreakpoint(id);
	}

	public IDbgpBreakpoint[] getBreakpoints() throws DbgpException {
		return breakpointCommands.getBreakpoints();
	}

	public void removeBreakpoint(String id) throws DbgpException {
		breakpointCommands.removeBreakpoint(id);
	}

	public String setCallBreakpoint(URI uri, String function,
			DbgpBreakpointConfig info) throws DbgpException {
		return breakpointCommands.setCallBreakpoint(uri, function, info);
	}

	public String setConditionalBreakpoint(URI uri, DbgpBreakpointConfig info)
			throws DbgpException {
		return breakpointCommands.setConditionalBreakpoint(uri, info);
	}

	public String setConditionalBreakpoint(URI uri, int lineNumber,
			DbgpBreakpointConfig info) throws DbgpException {
		return breakpointCommands.setConditionalBreakpoint(uri, lineNumber,
				info);
	}

	public String setExceptionBreakpoint(String exception,
			DbgpBreakpointConfig info) throws DbgpException {
		return breakpointCommands.setExceptionBreakpoint(exception, info);
	}

	public String setLineBreakpoint(URI uri, int lineNumber,
			DbgpBreakpointConfig info) throws DbgpException {
		return breakpointCommands.setLineBreakpoint(uri, lineNumber, info);
	}

	public String setReturnBreakpoint(URI uri, String function,
			DbgpBreakpointConfig info) throws DbgpException {
		return breakpointCommands.setReturnBreakpoint(uri, function, info);
	}

	public String setWatchBreakpoint(URI uri, int line,
			DbgpBreakpointConfig info) throws DbgpException {
		return breakpointCommands.setWatchBreakpoint(uri, line, info);
	}

	public void updateBreakpoint(String id, DbgpBreakpointConfig config)
			throws DbgpException {
		breakpointCommands.updateBreakpoint(id, config);
	}

	public IDbgpStatus detach() throws DbgpException {
		return continuationCommands.detach();
	}

	public IDbgpStatus run() throws DbgpException {
		return continuationCommands.run();
	}

	public IDbgpStatus stepInto() throws DbgpException {
		return continuationCommands.stepInto();
	}

	public IDbgpStatus stepOut() throws DbgpException {
		return continuationCommands.stepOut();
	}

	public IDbgpStatus stepOver() throws DbgpException {
		return continuationCommands.stepOver();
	}

	public IDbgpStatus stop() throws DbgpException {
		return continuationCommands.stop();
	}

	public Map getTypeMap() throws DbgpException {
		return dataTypeCommands.getTypeMap();
	}

	public String getSource(URI uri) throws DbgpException {
		return sourceCommands.getSource(uri);
	}

	public String getSource(URI uri, int beginLine) throws DbgpException {
		return sourceCommands.getSource(uri, beginLine);
	}

	public String getSource(URI uri, int beginLine, int endLine)
			throws DbgpException {
		return sourceCommands.getSource(uri, beginLine, endLine);
	}

	public IDbgpStatus getStatus() throws DbgpException {
		return statusCommands.getStatus();
	}

	public IDbgpStackLevel getStackLevel(int stackDepth) throws DbgpException {
		return stackCommands.getStackLevel(stackDepth);
	}

	public IDbgpStackLevel[] getStackLevels() throws DbgpException {
		return stackCommands.getStackLevels();
	}

	public int getStackDepth() throws DbgpException {
		return stackCommands.getStackDepth();
	}

	public Map getContextNames(int stackDepth) throws DbgpException {
		return contextCommands.getContextNames(stackDepth);
	}

	public IDbgpProperty[] getContextProperties(int stackDepth)
			throws DbgpException {
		return contextCommands.getContextProperties(stackDepth);
	}

	public IDbgpProperty[] getContextProperties(int stackDepth, int contextId)
			throws DbgpException {
		return contextCommands.getContextProperties(stackDepth, contextId);
	}

	public boolean configureStderr(int value) throws DbgpException {
		return streamCommands.configureStderr(value);
	}

	public boolean configureStdout(int value) throws DbgpException {
		return streamCommands.configureStdout(value);
	}

	public IDbgpProperty getProperty(String name) throws DbgpException {
		return propertyCommands.getProperty(name);
	}

	public IDbgpProperty getProperty(String name, int stackDepth)
			throws DbgpException {
		return propertyCommands.getProperty(name, stackDepth);
	}

	public IDbgpProperty getProperty(String name, int stackDepth, int contextId)
			throws DbgpException {
		return propertyCommands.getProperty(name, stackDepth, contextId);
	}

	public boolean setProperty(IDbgpProperty property) throws DbgpException {
		return propertyCommands.setProperty(property);
	}

	public boolean setProperty(String name, int stackDepth, String value)
			throws DbgpException {
		return propertyCommands.setProperty(name, stackDepth, value);
	}

	public IDbgpProperty getPropertyByKey(String name, String key)
			throws DbgpException {
		return propertyCommands.getPropertyByKey(name, key);
	}

	public IDbgpProperty getProperty(int page, String name, int stackDepth)
			throws DbgpException {
		return propertyCommands.getProperty(page, name, stackDepth);
	}
}
