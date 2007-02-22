package org.eclipse.dltk.dbgp.internal.commands;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.eclipse.dltk.dbgp.IDbgpContinuationHandler;
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
import org.eclipse.dltk.dbgp.internal.managers.IDbgpStreamManager;

public class DbgpCoreCommands implements IDbgpCoreCommands {

	private IDbgpFeatureCommands featureCommands;

	private IDbgpStatusCommands statusCommands;

	private IDbgpBreakpointCommands breakpointCommands;

	private IDbgpSourceCommands sourceCommands;

	private IDbgpContextCommands contextCommands;

	private IDbgpStatckCommands stackCommands;

	private IDbgpContinuationCommands continuationCommands;

	private IDbgpStreamCommands streamCommands;

	private IDbgpDataTypeCommands dataTypeCommands;

	private IDbgpPropertyCommands propertyCommands;

	public DbgpCoreCommands(IDbgpCommunicator communicator,
			IDbgpStreamManager streamManager) {
		this.featureCommands = new DbgpFeatureCommands(communicator);
		this.statusCommands = new DbgpStatusCommands(communicator);
		this.breakpointCommands = new DbgpBreakpointCommands(communicator);
		this.sourceCommands = new DbgpSourceCommands(communicator);
		this.contextCommands = new DbgpContextCommands(communicator);
		this.stackCommands = new DbgpStackCommands(communicator);
		this.continuationCommands = new DbgpContinuationCommands(communicator,
				streamManager);
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

	public List getBreakpoints() throws DbgpException {
		return breakpointCommands.getBreakpoints();
	}

	public void removeBreakpoint(String id) throws DbgpException {
		breakpointCommands.removeBreakpoint(id);
	}

	public String setCallBreakpoint(String function, DbgpBreakpointConfig info)
			throws DbgpException {
		return breakpointCommands.setCallBreakpoint(function, info);
	}

	public String setConditionalBreakpoint(URI uri, int lineNumber,
			String expression, DbgpBreakpointConfig info) throws DbgpException {
		return breakpointCommands.setConditionalBreakpoint(uri, lineNumber,
				expression, info);
	}

	public String setExceptionBreakpoint(String exception,
			DbgpBreakpointConfig info) throws DbgpException {
		return breakpointCommands.setExceptionBreakpoint(exception, info);
	}

	public String setLineBreakpoint(URI uri, int lineNumber,
			DbgpBreakpointConfig info) throws DbgpException {
		return breakpointCommands.setLineBreakpoint(uri, lineNumber, info);
	}

	public String setReturnBreakpoint(String function, DbgpBreakpointConfig info)
			throws DbgpException {
		return breakpointCommands.setReturnBreakpoint(function, info);
	}

	public String setWatchBreakpoint(String expression,
			DbgpBreakpointConfig info) throws DbgpException {
		return breakpointCommands.setWatchBreakpoint(expression, info);
	}

	public void updateBreakpoint(String id, DbgpBreakpointConfig config)
			throws DbgpException {
		breakpointCommands.updateBreakpoint(id, config);
	}

	public IDbgpStatus detach() throws DbgpException {
		return continuationCommands.detach();
	}

	public IDbgpStatus run(IDbgpContinuationHandler handler)
			throws DbgpException {
		return continuationCommands.run(handler);
	}

	public IDbgpStatus stepInto(IDbgpContinuationHandler handler)
			throws DbgpException {
		return continuationCommands.stepInto(handler);
	}

	public IDbgpStatus stepOut(IDbgpContinuationHandler handler)
			throws DbgpException {
		return continuationCommands.stepOut(handler);
	}

	public IDbgpStatus stepOver(IDbgpContinuationHandler handler)
			throws DbgpException {
		return continuationCommands.stepOver(handler);
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

	public List getStackLevels() throws DbgpException {
		return stackCommands.getStackLevels();
	}

	public int getStackDepth() throws DbgpException {
		return stackCommands.getStackDepth();
	}

	public Map getContextNames(int stackDepth) throws DbgpException {
		return contextCommands.getContextNames(stackDepth);
	}

	public List getContextProperties(int stackDepth) throws DbgpException {
		return contextCommands.getContextProperties(stackDepth);
	}

	public List getContextProperties(int stackDepth, int contextId)
			throws DbgpException {
		return contextCommands.getContextProperties(stackDepth, contextId);
	}

	public boolean configureStderr(int value) throws DbgpException {
		return streamCommands.configureStderr(value);
	}

	public boolean configureStdout(int value) throws DbgpException {
		return streamCommands.configureStdout(value);
	}

	public IDbgpProperty getPropery(String name) throws DbgpException {
		return propertyCommands.getPropery(name);
	}

	public IDbgpProperty getPropery(String name, int stackDepth)
			throws DbgpException {
		return propertyCommands.getPropery(name, stackDepth);
	}

	public IDbgpProperty getPropery(String name, int stackDepth, int contextId)
			throws DbgpException {
		return propertyCommands.getPropery(name, stackDepth, contextId);
	}

	public IDbgpProperty getPropery(String name, int stackDepth, int contextId,
			String dataType, String dataPage) throws DbgpException {
		return propertyCommands.getPropery(name, stackDepth, contextId,
				dataType, dataPage);
	}

	public boolean setPropery(IDbgpProperty property) throws DbgpException {
		return propertyCommands.setPropery(property);
	}

	public boolean setPropery(String name, int stackDepth, String value)
			throws DbgpException {
		return propertyCommands.setPropery(name, stackDepth, value);
	}
}
