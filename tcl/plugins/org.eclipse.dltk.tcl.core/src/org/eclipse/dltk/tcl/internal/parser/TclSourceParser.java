package org.eclipse.dltk.tcl.internal.parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.parser.AbstractSourceParser;
import org.eclipse.dltk.ast.parser.ISourceParserConstants;
import org.eclipse.dltk.ast.parser.ISourceParserExtension;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclExecuteExpression;
import org.eclipse.dltk.tcl.core.ITclCommandDetector;
import org.eclipse.dltk.tcl.core.ITclCommandDetectorExtension;
import org.eclipse.dltk.tcl.core.ITclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.core.ITclSourceParser;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.core.ITclCommandDetector.CommandInfo;
import org.eclipse.dltk.tcl.core.TclParseUtil.CodeModel;
import org.eclipse.dltk.tcl.core.ast.TclAdvancedExecuteExpression;
import org.eclipse.dltk.tcl.internal.parser.ext.CommandManager;
import org.eclipse.dltk.tcl.internal.parsers.raw.SimpleTclParser;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclElement;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclParseException;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclScript;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclWord;

public class TclSourceParser extends AbstractSourceParser implements
		ITclSourceParser, ITclParser, ISourceParserExtension {
	private IProblemReporter problemReporter;
	protected CodeModel codeModel;
	protected String content;
	private char[] fileName;
	private int startPos = 0;
	boolean useProcessors = true;
	private int flags;
	
	Map commandToStatement = new HashMap();
	private static class CommandToStatementKey {
		public TclCommand command;
		public int offset;
		public CommandToStatementKey(TclCommand command2, int offset2) {
			this.command = command2;
			this.offset = offset2;
		}
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((command == null) ? 0 : command.hashCode());
			result = prime * result + offset;
			return result;
		}
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CommandToStatementKey other = (CommandToStatementKey) obj;
			if (command == null) {
				if (other.command != null)
					return false;
			} else if (!command.equals(other.command))
				return false;
			if (offset != other.offset)
				return false;
			return true;
		}
	}

	private ModuleDeclaration moduleDeclaration;

	// private Map commandParserCache = new HashMap();

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
			// if (commandParserCache.containsKey(command)) {
			// ASTNode st = (ASTNode) commandParserCache.get(command);
			// if (parent != null) {
			// TclParseUtil.addToDeclaration(parent, st);
			// }
			// return st;
			// }
			TclStatement st = TclParseUtil.convertToAST(command, parser
					.getFileName(), offset, TclSourceParser.this.content,
					TclSourceParser.this.startPos);
			// commandParserCache.put(command, st);
			if (parent != null) {
				TclParseUtil.addToDeclaration(parent, st);
				// Replace execute expressions and parse they content.
				convertExecuteToBlocks(st);
			}
			return st;
		}

		public void setCurrentASTTree(ModuleDeclaration module) {
		}

		public void setDetectedParameter(Object parameter) {
		}
	};

	private void convertExecuteToBlocks(TclStatement st) {
		ASTNode[] nodes = (ASTNode[]) st.getExpressions().toArray(
				new ASTNode[st.getCount()]);
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] instanceof TclExecuteExpression) {

				TclExecuteExpression tclExecuteExpression = ((TclExecuteExpression) nodes[i]);
				String expression = tclExecuteExpression.getExpression();
				expression = expression.substring(1, expression.length() - 1);
				TclAdvancedExecuteExpression newExpr = new TclAdvancedExecuteExpression(
						nodes[i].sourceStart() + 1, nodes[i].sourceEnd());
				nodes[i] = newExpr;
				st.setExpressions(Arrays.asList(nodes));
				TclSourceParser.this.parse(expression, nodes[i].sourceStart()
						- getStartPos(), newExpr);
			}
		}
		st.setExpressions(Arrays.asList(nodes));
	}

	public TclStatement processLocal(TclCommand command, int offset,
			ASTNode parent) {
		
		CommandToStatementKey key = new CommandToStatementKey(command,offset);
		if( commandToStatement.containsKey(key)) {
			return (TclStatement) commandToStatement.get(key);
		}
		TclStatement node = (TclStatement) this.localProcessor.process(command,
				this, offset, null);
		TclParseUtil.addToDeclaration(parent, node);
		this.convertExecuteToBlocks((TclStatement) node);
		TclParseUtil.removeFromDeclaration(parent, node);
		
		commandToStatement.put(key, node);
		return node;
	}

	public void parse(String content, int offset, ASTNode decl) {
		commandToStatement.clear();
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
				try {
					if (processor.process(command, this, offset, decl) == null) {
						localProcessor.process(command, this, offset, decl);
					}
				} catch (Exception e) {
					if (DLTKCore.DEBUG) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private ITclCommandProcessor locateProcessor(TclCommand command,
			String content, int offset, ASTNode decl) {
		if (this.useProcessors == false) {
			return localProcessor;
		}

		List words = command.getWords();
		if (words == null || words.size() == 0) {
			return null;
		}
		Object object = words.get(0);
		if (object instanceof TclWord) {
			String name = TclParseUtil
					.extractWord((TclElement) object, content);
			if (name.startsWith("::")) {
				name = name.substring(2);
			}

			ITclCommandProcessor processor = CommandManager.getInstance()
					.getProcessor(name);
			if (processor == null) {
				// advanced command detection.
				ITclCommandDetector[] detectors = CommandManager.getInstance()
						.getDetectors();
				for (int i = 0; i < detectors.length; i++) {
					if (detectors[i] instanceof ITclCommandDetectorExtension) {
						((ITclCommandDetectorExtension) detectors[i])
								.setBuildRuntimeModelFlag(isBuildingRuntimeModel());
					}
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
		if (!isBuildingRuntimeModel()) {
			return this.localProcessor;
		}
		return null;
	}

	public CodeModel getCodeModel() {
		return this.codeModel;
	}

	public String getContent() {
		return this.content;
	}

	public String substring(int start, int end) {
		if (start > end) {
			return "";
		}
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

	public void setProcessorsState(boolean state) {
		this.useProcessors = state;
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}

	private boolean isBuildingRuntimeModel() {
		return (flags & ISourceParserConstants.RUNTIME_MODEL) != 0;
	}
}
