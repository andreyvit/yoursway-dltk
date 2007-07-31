package org.eclipse.dltk.xotcl.internal.core.parser;

import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.core.ITclSourceParser;
import org.eclipse.dltk.tcl.internal.parsers.raw.SimpleTclParser;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclElement;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclParseException;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclScript;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclWord;
import org.eclipse.dltk.xotcl.core.ITclCommandDetector;
import org.eclipse.dltk.xotcl.core.ITclCommandProcessor;
import org.eclipse.dltk.xotcl.core.ITclParser;
import org.eclipse.dltk.xotcl.core.TclParseUtil;
import org.eclipse.dltk.xotcl.core.ITclCommandDetector.CommandInfo;
import org.eclipse.dltk.xotcl.core.TclParseUtil.CodeModel;

public class XOTclSourceParser implements ITclSourceParser, ITclParser {
	private IProblemReporter problemReporter;
	private CodeModel codeModel;
	private String content;
	private char[] fileName;
	private int startPos = 0;
	private ModuleDeclaration moduleDeclaration;
//	private Map commandParserCache = new HashMap();

	public ModuleDeclaration parse(char[] fileName, char[] source,
			IProblemReporter reporter) {
		this.problemReporter = reporter;
		this.content = new String(source);
		this.codeModel = new CodeModel(this.content);
		this.fileName = fileName;

		this.moduleDeclaration = new ModuleDeclaration(this.content.length());

		this.parse(this.content, 0, moduleDeclaration);
		return moduleDeclaration;
	}

	ITclCommandProcessor localProcessor = new ITclCommandProcessor() {
		public ASTNode process(TclCommand command, ITclParser parser,
				int offset, ASTNode parent) {
//			if (commandParserCache.containsKey(command)) {
//				ASTNode st = (ASTNode) commandParserCache.get(command);
//				if (parent != null) {
//					TclParseUtil.addToDeclaration(parent, st);
//				}
//				return st;
//			}
			TclStatement st = TclParseUtil.convertToAST(command, parser,
					offset, XOTclSourceParser.this.content,
					XOTclSourceParser.this.startPos);
//			commandParserCache.put(command, st);
			if (parent != null) {
				TclParseUtil.addToDeclaration(parent, st);
			}
			return st;
		}

		public void setCurrentASTTree(ModuleDeclaration module) {
		}

		public void setDetectedParameter(Object parameter) {
		}
	};

	public ASTNode processLocal(TclCommand command, int offset) {
		return this.localProcessor.process(command, this, offset, null);
	}

	public void parse(String content, int offset, ASTNode decl) {
		TclScript script = null;
		try {
			script = SimpleTclParser.parse(content);
		} catch (TclParseException e) {
			if (DLTKCore.DEBUG_PARSER) {
				e.printStackTrace();
			}
			return;
		}
		if (script == null) {
			return;
		}
		List commands = script.getCommands();
		for (Iterator iter = commands.iterator(); iter.hasNext();) {
			TclCommand command = (TclCommand) iter.next();
			// Command handling
			ITclCommandProcessor processor = this.locateProcessor(command,
					content, offset, decl);
			if (processor != null) {
				if( processor.process(command, this, offset, decl) == null ) {
					localProcessor.process(command, this, offset, decl);
				}
				// We thinks processor add node to parent by itself
			}
		}
	}

	private ITclCommandProcessor locateProcessor(TclCommand command,
			String content, int offset, ASTNode decl) {
		List words = command.getWords();
		if (words == null || words.size() == 0) {
			return null;
		}
		Object object = words.get(0);
		if (object instanceof TclWord) {
			String name = TclParseUtil
					.extractWord((TclElement) object, content);
			if( name.startsWith("::")) {
				name = name.substring(2);
			}
			ITclCommandProcessor processor = CommandManager.getInstance()
					.getProcessor(name);
			if (processor == null) {
				// advanced command detection.
				ITclCommandDetector[] detectors = CommandManager.getInstance()
						.getDetectors();
				for (int i = 0; i < detectors.length; i++) {
					CommandInfo commandName = detectors[i]
							.detectCommand(command, offset,
									this.moduleDeclaration, this, decl);
					if (commandName != null) {
						processor = CommandManager.getInstance().getProcessor(
								commandName.commandName);
						if (processor != null) {
							processor
									.setDetectedParameter(commandName.parameter);
						}
						break;
					}
				}
			}
			if (processor != null) {
				processor.setCurrentASTTree(this.moduleDeclaration);
				return processor;
			}
		}
		return this.localProcessor;
	}

	public CodeModel getCodeModel() {
		return this.codeModel;
	}

	public String getContent() {
		return this.content;
	}

	public String substring(int start, int end) {
		try {
			return this.content.substring(start - this.startPos, end
					- this.startPos);
		} catch (IndexOutOfBoundsException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		return "####error####";
	}

	public IProblemReporter getProblemReporter() {
		return this.problemReporter;
	}

	public char[] getFileName() {
		return this.fileName;
	}

	public void setOffset(int offset) {
		this.startPos = offset;
	}

	public int getStartPos() {
		return this.startPos;
	}
}
