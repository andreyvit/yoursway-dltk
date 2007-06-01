/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.scriptchecker.internal.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.validators.core.AbstractValidator;
import org.eclipse.dltk.validators.core.IValidatorType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ScriptChecker extends AbstractValidator {
	private static final String SEVERITY = "severity";
	private static final String NO_STYLE = "noStyle";
	private static final String NO_SYNTAX = "noCheck";
	private static final String PATH = "path";
	private static final String ARGUMENTS = "arguments";
	
	private static final String DIFFS = "diffs";
	private static final String REFERENCES = "references";
	private static final String REPORT = "report";
	private static final String SUMMARY = "summary";
	
	public static final String[] SEVERITY_VALUES = new String[] { "Mandatory",
			"Desirable", "Optional" };
	private IPath path;
	private boolean noStyle;
	private boolean noSyntax;
	private String severity;
	private String arguments;
	private boolean diffs;
	private boolean references;
	private boolean report;
	private boolean summary;
	
	boolean initialized = false;

	private static String spatternSyntax = "((\\w:)?[^:]+):(\\d+)\\s+(.*)";
	private static Pattern patternSyntax = Pattern.compile(spatternSyntax);

	private static class ScriptCheckerCodeModel {
		private String[] codeLines;

		private int[] codeLineLengths;

		public ScriptCheckerCodeModel(String code) {
			this.codeLines = code.split("\n");
			int count = this.codeLines.length;

			this.codeLineLengths = new int[count];

			int sum = 0;
			for (int i = 0; i < count; ++i) {
				this.codeLineLengths[i] = sum;
				sum += this.codeLines[i].length() + 1;
			}
		}

		public int[] getBounds(int lineNumber) {
			String codeLine = codeLines[lineNumber];
			String trimmedCodeLine = codeLine.trim();

			int start = codeLineLengths[lineNumber]
					+ codeLine.indexOf(trimmedCodeLine);
			int end = start + trimmedCodeLine.length();

			return new int[] { start, end };
		}
	}

	private void setDefaults() {
		this.noStyle = false;
		this.noSyntax = false;
		this.severity = SEVERITY_VALUES[1];
		this.arguments = "-- %f";
		this.path = new Path("script_checker");
		this.diffs = false;
		this.summary = false;
		this.references = false;
		this.report = false;
	}

	protected ScriptChecker(String id, IValidatorType type) {
		super(id, null, type);
		setDefaults();
	}

	protected ScriptChecker(String id, String name, IValidatorType type) {
		super(id, name, type);
		setDefaults();
	}

	protected ScriptChecker(String id, Element element, IValidatorType type)
			throws IOException {
		super(id, null, type);
		loadInfo(element);
	}

	public void loadInfo(Element element) {
		if (initialized) {
			return;
		}
		super.loadFrom(element);
		initialized = true;
		this.path = new Path(element.getAttribute(PATH));
		this.noStyle = (new Boolean(element.getAttribute(NO_STYLE)))
				.booleanValue();
		this.noSyntax = (new Boolean(element.getAttribute(NO_SYNTAX)))
				.booleanValue();
		this.severity = element.getAttribute(SEVERITY);
		this.arguments = element.getAttribute(ARGUMENTS);
		
		this.diffs = (new Boolean(element.getAttribute(DIFFS))).booleanValue();
		this.references = (new Boolean(element.getAttribute(REFERENCES))).booleanValue();
		this.report = (new Boolean(element.getAttribute(REPORT))).booleanValue();
		this.summary = (new Boolean(element.getAttribute(SUMMARY))).booleanValue();
	}

	public void storeTo(Document doc, Element element) {
		super.storeTo(doc, element);
		element.setAttribute(PATH, this.path.toOSString());
		element.setAttribute(NO_STYLE, Boolean.toString(this.noStyle));
		element.setAttribute(NO_SYNTAX, Boolean.toString(this.noSyntax));
		element.setAttribute(SEVERITY, this.severity);
		element.setAttribute(ARGUMENTS, this.arguments);
		element.setAttribute(DIFFS, Boolean.toString(this.diffs));
		element.setAttribute(REFERENCES, Boolean.toString(this.references));
		element.setAttribute(REPORT, Boolean.toString(this.report));
		element.setAttribute(SUMMARY, Boolean.toString(this.summary));
	}

	public IStatus validate(IResource resource, OutputStream console) {
		return Status.OK_STATUS;
	}

	public IStatus validate(ISourceModule module, OutputStream console) {
		IResource resource = module.getResource();
		if (resource == null) {
			return Status.CANCEL_STATUS;
		}
		try {
			ScriptCheckerMarker.clearMarkers(resource);
		} catch (CoreException e2) {
			if( DLTKCore.DEBUG ) {
				e2.printStackTrace();
			}
		}
		String args = processArguments(resource);
		List cmd = new ArrayList();
		cmd.add(this.path.toOSString());
		String[] sArgs = args.split("::");
		for (int i = 0; i < sArgs.length; i++) {
			cmd.add(sArgs[i]);
		}

		String[] cmdLine = (String[]) cmd.toArray(new String[cmd.size()]);

		List lines = new ArrayList();

		BufferedReader input = null;
		OutputStreamWriter output = null;

		try {
			try {
				Process process = DebugPlugin.exec(cmdLine, null);

				input = new BufferedReader(new InputStreamReader(process
						.getInputStream()));

				String line = null;
				while ((line = input.readLine()) != null) {
					lines.add(line);
					if (console != null) {
						console.write((line + "\n").getBytes());
					}
				}

			} finally {
				if (output != null) {
					output.close();
				}

				if (input != null) {
					input.close();
				}
			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Status(IStatus.ERROR, ScriptCheckerPlugin.PLUGIN_ID, 0,
					"Failed to execute script_checker", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Status(IStatus.ERROR, ScriptCheckerPlugin.PLUGIN_ID, 0,
					"Failed to execute script_checker", e);
		}

		// check output, and put markers
		String content = "";
		try {
			content = module.getSource();
		} catch (ModelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ScriptCheckerCodeModel model = new ScriptCheckerCodeModel(content);
		for (Iterator iterator = lines.iterator(); iterator.hasNext();) {
			String line = (String) iterator.next();
			ScriptCheckerProblem problem = parseProblem(line);
			if (problem != null) {
				try {
					int[] bounds = model.getBounds(problem.getLineNumber());
					if (problem.getDescription().indexOf("error") == -1) {
						reportWarningProblem(resource, problem, bounds[0],
								bounds[1]);
					} else {
						reportErrorProblem(resource, problem, bounds[0],
								bounds[1]);
					}
				} catch (CoreException e) {
					if (DLTKCore.DEBUG) {
						e.printStackTrace();
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					if (DLTKCore.DEBUG) {
						e.printStackTrace();
					}
				}
			}
		}

		return Status.OK_STATUS;
	}

	public static ScriptCheckerProblem parseProblem(String problem) {
		Matcher matcher = patternSyntax.matcher(problem);

		if (!matcher.find())
			return null;

		String file = matcher.group(1);
		int lineNumber = Integer.parseInt(matcher.group(3));
		String message = matcher.group(4);

		return new ScriptCheckerProblem(file, lineNumber - 1, message);
	}

	protected static IMarker reportErrorProblem(IResource resource,
			ScriptCheckerProblem problem, int start, int end)
			throws CoreException {

		return ScriptCheckerMarker.setMarker(resource, problem.getLineNumber(),
				start, end, problem.getDescription(), IMarker.SEVERITY_ERROR,
				IMarker.PRIORITY_NORMAL);
	}

	protected static IMarker reportWarningProblem(IResource resource,
			ScriptCheckerProblem problem, int start, int end)
			throws CoreException {

		return ScriptCheckerMarker.setMarker(resource, problem.getLineNumber(),
				start, end, problem.getDescription(), IMarker.SEVERITY_WARNING,
				IMarker.PRIORITY_NORMAL);
	}

	private String processArguments(IResource resource) {
		String path = resource.getLocation().makeAbsolute().toOSString();
		String arguments = this.arguments;
		if (arguments.indexOf("--") == -1) {
			arguments = "-- " + arguments;
		}
		String user = replaceSequence(arguments.replaceAll("\t", "::")
				.replaceAll(" ", "::"), 'f', path);
		String result = "";
		if (this.noStyle) {
			result = result + "::-no_style";
		}
		if (this.noSyntax) {
			result = result + "::-no_syntax";
		}
		if (this.references) {
			result = result + "::-references";
		}
		if (this.diffs) {
			result = result + "::-diffs";
		}
		if (this.report) {
			result = result + "::-report";
		}
		if (this.summary) {
			result = result + "::-summary";
		}
		result = result + "::-severity::" + this.severity.toLowerCase();
		return result + "::" + user;
	}

	private String replaceSequence(String from, char pattern, String value) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < from.length(); ++i) {
			char c = from.charAt(i);
			if (c == '%' && i < from.length() - 1
					&& from.charAt(i + 1) == pattern) {
				buffer.append(value);
				i++;
			} else {
				buffer.append(c);
			}
		}
		return buffer.toString();
	}

	public boolean isValidatorValid() {
		File file = new File(this.path.toOSString());

		if (!file.exists()) {
			return false;
		}

		return true;
	}

	public String getPath() {
		return this.path.toOSString();
	}

	public void setPath(String path) {
		initialized = true;
		this.path = new Path(path);
	}

	public boolean isStyle() {
		return !noStyle;
	}

	public void setStyle(boolean style) {
		initialized = true;
		this.noStyle = !style;
	}

	public boolean isSyntax() {
		return !noSyntax;
	}

	public void setSyntax(boolean syntax) {
		initialized = true;
		this.noSyntax = !syntax;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		initialized = true;
		this.severity = severity;
	}

	public String getArguments() {
		return arguments;
	}

	public void setArguments(String arguments) {
		initialized = true;
		this.arguments = arguments;
	}

	public void clean(ISourceModule module) {
		clean(module.getResource());
	}

	public void clean(IResource resource) {
		try {
			ScriptCheckerMarker.clearMarkers(resource);
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
	}

	public void setDiffs(boolean selected) {
		this.diffs = selected;
	}
	public void setReferences(boolean selected) {
		this.references = selected;
	}

	public boolean isDiffs() {
		return diffs;
	}

	public boolean isReferences() {
		return references;
	}

	public boolean isReport() {
		return report;
	}

	public boolean isSummary() {
		return summary;
	}

	public void setSummary(boolean selected) {
		this.summary = selected;
	}
	public void setReport(boolean selected) {
		this.report = selected;
	}
}
