package org.eclipse.dltk.tcl.internal.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.problem.DLTKProblemReporter;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceElementParser;
import org.eclipse.dltk.core.ISourceModuleInfoCache.ISourceModuleInfo;
import org.eclipse.dltk.tcl.TclKeywords;
import org.eclipse.dltk.tcl.ast.TclConstants;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.ast.expressions.TclExecuteExpression;
import org.eclipse.dltk.tcl.internal.parsers.raw.SimpleTclParser;

public class TclSourceElementParser implements ISourceElementParser {
	private static final int TYPE_MODULE = 0;

	private static final int TYPE_NAMESPACE = 1;

	private static final int TYPE_PROC = 2;

	private ISourceElementRequestor fRequestor;

	private Stack namespacesLevel = new Stack();

	private static String[] kw = TclKeywords.getKeywords();
	private static Map kwMap = new HashMap();

	static {
		for (int q = 0; q < kw.length; ++q) {
			kwMap.put(kw[q], Boolean.TRUE);
		}
	}

	public TclSourceElementParser(ISourceElementRequestor requestor) {
		this(requestor, null);
	}

	public TclSourceElementParser(ISourceElementRequestor requestor,
			DLTKProblemReporter reporter) {
		this.fRequestor = requestor;
	}

	private static int counter = 0;

	public ModuleDeclaration parseSourceModule(char[] contents,
			ISourceModuleInfo astCashe) {

		// System.out.println("TclSourceElementParser.parseSourceModule() "
		// + (counter++));

		// TODO: Add correct visitor like model builder for TCL.
		TclSourceParser sourceParser = new TclSourceParser();
		ModuleDeclaration moduleDeclaration = sourceParser.parse(new String(
				contents));
		moduleDeclaration.disableRebuild();
		List statements = moduleDeclaration.getStatements();
		try {
			fRequestor.enterModule();
			namespacesLevel.push("::");
			buildModel(statements, TYPE_MODULE, "");
			fRequestor.exitModule(contents.length);
		} catch (Exception e) {
			if (DLTKCore.DEBUG_PARSER) {
				e.printStackTrace();
			}
		}
		return moduleDeclaration;
	}

	private void buildModel(List statements, int type, String namespaceName) {
		if (statements == null) {
			return;
		}
		HashSet variablesSet = new HashSet();
		Iterator i = statements.iterator();
		while (i.hasNext()) {
			Statement sst = (Statement) i.next();
			if (sst instanceof TclStatement) {
				TclStatement statement = (TclStatement) sst;
				Expression commandId = statement.getAt(0);
				if (commandId != null && commandId instanceof SimpleReference) {
					String name = ((SimpleReference) commandId).getName();
					if (name.startsWith("::")) {
						name = name.substring(2);
					}
					if (name.equals("proc")) {
						this.processProc(statement, namespaceName);
					} else if (name.equals("set")) {
						this.processVariableSet(statement, variablesSet);
					} else if (name.equals("variable")
							&& (type == TYPE_NAMESPACE || type == TYPE_PROC)) {
						this.processVariable(statement, variablesSet,
								namespaceName);
					} else if (name.equals("upvar") && (type == TYPE_PROC)) {
						// Expression upvarName = statement.getAt(3); //FIXME
						// makeVariable(upvarName, variablesSet,
						// TclConstants.TCL_FIELD_TYPE_UPVAR, namespaceName);
						this.processUpvarVariable(statement, variablesSet);
					} else if (name.equals("global") && type == TYPE_PROC) {
						this.processGlobalVariable(statement, variablesSet);
					} else if (name.equals("package")) {
						processPackage(statement);
					} else if (name.equals("namespace")) {
						processNamespace(statement, namespaceName);
					} else if (name.equals("if")) {
						processIf(statement, namespaceName);
					} else if (name.equals("while")) {
						processWhile(statement, namespaceName);
					} else if (name.equals("for")) {
						processFor(statement, namespaceName);
					} else if (name.equals("catch")) {
						processCatch(statement, namespaceName);
					} else if (name.equals("after")) {
						processAfter(statement, namespaceName);
					}

					for (int j = 1; j < statement.getCount(); ++j) {
						if (statement.getAt(j) instanceof TclExecuteExpression) {
							TclExecuteExpression expr = (TclExecuteExpression) statement
									.getAt(j);
							processBlock(expr, namespaceName);
						}
					}
					processReferences(statement);
					// TODO: Add switch, foreach support code.
				}
			}
		}
	}

	private void processAfter(TclStatement statement, String namespaceName) {
		if (statement.getCount() >= 2) {
			for (int i = 2; i < statement.getCount(); ++i) {
				Expression e = statement.getAt(i);
				if (e instanceof TclExecuteExpression) {
					processBlock((TclExecuteExpression) e, namespaceName);
				}
			}
		}
	}

	private void processCatch(TclStatement statement, String namespaceName) {
		if (statement.getCount() >= 2) {
			Expression e = statement.getAt(1);
			if (e instanceof TclBlockExpression) {
				processBlock(e, namespaceName);
			}
		}
	}

	private void processReferences(TclStatement statement) {
		Expression commandId = statement.getAt(0);
		if (commandId != null && commandId instanceof SimpleReference) {
			String name = ((SimpleReference) commandId).getName();
			if (name.startsWith("::")) {
				name = name.substring(2);
			}
			if (!kwMap.containsKey(name)) {
				int argCount = statement.getCount() - 1;
				if (name.charAt(0) != '$') {
					this.fRequestor.acceptMethodReference(name.toCharArray(),
							argCount, commandId.sourceStart(), commandId
									.sourceEnd());
				}
			}
		}
		for (int j = 1; j < statement.getCount(); ++j) {
			Expression st = statement.getAt(j);
			if (st instanceof TclExecuteExpression) {
				TclExecuteExpression expr = (TclExecuteExpression) st;
				List exprs = expr.parseExpression();
				for (int i = 0; i < exprs.size(); ++i) {
					if (exprs.get(i) instanceof TclStatement) {
						processReferences((TclStatement) exprs.get(i));
					}
				}
			} else if (st instanceof StringLiteral) {
				int pos = 0;
				StringLiteral literal = (StringLiteral) st;
				String value = literal.getValue();
				pos = value.indexOf("$");
				while (pos != -1) {
					SimpleReference ref = TclParseUtils.findVariableFromString(
							literal, pos);
					if (ref != null) {
						this.fRequestor.acceptFieldReference(ref.getName()
								.substring(1).toCharArray(), ref.sourceStart());
						pos = pos + ref.getName().length();
					}
					pos = value.indexOf("$", pos + 1);
				}
			} else if (st instanceof SimpleReference) {
				SimpleReference ref = (SimpleReference) st;
				String name = ref.getName();
				if (name.startsWith("$")) { // This is variable usage.
					this.fRequestor.acceptFieldReference(ref.getName()
							.substring(1).toCharArray(), ref.sourceStart());
				}
			}
		}
	}

	/**
	 * @deprecated
	 * @param statement
	 */
	private void processFor(TclStatement statement, String namespaceName) {
		// TODO: Add variable corrections here.
		List exprs = statement.getExpressions();
		int len = exprs.size();

		if (1 < len) { // Process initializers
			Expression bl = (Expression) exprs.get(1);
			if (bl instanceof TclBlockExpression) {
				processBlock(bl, namespaceName);
			}
		}

		int bi = 4; // Skip expression
		if (bi < len) {
			Expression bl = (Expression) exprs.get(bi);
			if (bl instanceof TclBlockExpression) {
				processBlock(bl, namespaceName);
			}
		}
	}

	/**
	 * @deprecated
	 * @param statement
	 */
	private void processWhile(TclStatement statement, String namespaceName) {
		List exprs = statement.getExpressions();
		int len = exprs.size();
		int bi = 2; // Skip expression
		if (bi < len) {
			Expression bl = (Expression) exprs.get(bi);
			if (bl instanceof TclBlockExpression) {
				processBlock(bl, namespaceName);
			}
		}
	}

	/**
	 * @deprecated
	 * @param statement
	 */
	private void processIf(TclStatement statement, String namespaceName) {
		List exprs = statement.getExpressions();
		int len = exprs.size();
		for (int i = 0; i < len; ++i) {
			Expression e = (Expression) exprs.get(i);
			if (e instanceof SimpleReference) {
				String value = ((SimpleReference) e).getName();
				if (value.equals("if") || value.equals("elseif")) {
					int bi = i + 2; // Skip expression
					while (bi < len) {
						Expression bl = (Expression) exprs.get(bi);
						// Check for then statement
						if (bl instanceof SimpleReference) {
							String thenSt = ((SimpleReference) bl).getName();
							if (thenSt != null && thenSt.equals("then")) {
								bi++;
								continue;
							}
						} else if (bl instanceof TclBlockExpression) {
							processBlock(bl, namespaceName);
						}
						break;
					}
					i += bi - i;
				} else if (value.equals("else")) {
					if (i + 1 < len) {
						Expression bl = (Expression) exprs.get(i + 1);
						if (bl instanceof TclBlockExpression) {
							processBlock(bl, namespaceName);
						}
					}
				}
			}
		}
	}

	private void processBlock(Expression bl, String namespaceName) {
		TclBlockExpression block = (TclBlockExpression) bl;
		List/* < Statement > */code = null;
		code = block.parseBlock(block.sourceStart() + 1);
		buildModel(code, TYPE_PROC, namespaceName);

	}

	private void processBlock(TclExecuteExpression bl, String namespaceName) {
		List/* < Statement > */code = null;
		code = bl.parseExpression(bl.sourceStart() + 1);
		buildModel(code, TYPE_PROC, namespaceName);
	}

	private void processNamespace(TclStatement statement,
			String parentNamespaceName) {
		Expression nameSpaceArg = statement.getAt(1);
		if (nameSpaceArg == null || !(nameSpaceArg instanceof SimpleReference)) {
			// TODO: Add error reporting here.
			if (DLTKCore.DEBUG) {
				System.err
						.println("tcl: namespace argument is null or not simple reference");
			}
			// continue;
		}

		Expression nameSpaceName = statement.getAt(2);
		if (nameSpaceName == null
				|| !(nameSpaceName instanceof SimpleReference)) {
			// TODO: Add error reporting here.
			// continue;
			// by now, just ignore
			return;
		}

		Expression code = statement.getAt(3);
		if (code == null || !(code instanceof TclBlockExpression)) {
			return;
			// TODO: Add error reporting here.
			// continue;
		}

		String sNameSpaceArg = ((SimpleReference) nameSpaceArg).getName();
		String sNameSpaceName = ((SimpleReference) nameSpaceName).getName();
		if (sNameSpaceArg.equals("eval")) {
			/*
			 * this is namespace defining or redefining.
			 */
			ISourceElementRequestor.TypeInfo info = new ISourceElementRequestor.TypeInfo();
			info.modifiers = Modifiers.AccNameSpace;

			String fullName = sNameSpaceName;

			String[] split = sNameSpaceName.split("::");
			if (split.length != 0)
				info.name = split[split.length - 1];
			else
				info.name = "";

			SimpleReference nsName = (SimpleReference) nameSpaceName;
			info.nameSourceStart = nsName.sourceStart();
			info.nameSourceEnd = nsName.sourceEnd() - 1;
			info.declarationStart = statement.getAt(0).sourceStart();

			ExitFromType exit = resolveType(nameSpaceName,
					fullName + "::dummy", true);

			List/* < Statement > */inner = null;

			inner = ((TclBlockExpression) code)
					.parseBlock(code.sourceStart() + 1);

			if (sNameSpaceName.startsWith("::")
					&& parentNamespaceName.length() == 0) {
				this.buildModel(inner, TYPE_NAMESPACE, sNameSpaceName);
			} else {
				this.buildModel(inner, TYPE_NAMESPACE, parentNamespaceName
						+ "::" + sNameSpaceName);
			}

			exit.go();
		}
	}

	private void processPackage(TclStatement statement) {
		if (statement.getCount() < 3) {
			// TODO: Add error reporting here.
			if (DLTKCore.DEBUG) {
				System.err.println("tcl: package argument could incorrect...");
			}
			return;
		}
		Expression nameSpaceArg = statement.getAt(1);
		if (nameSpaceArg == null || !(nameSpaceArg instanceof SimpleReference)) {
			// TODO: Add error reporting here.
			if (DLTKCore.DEBUG) {
				System.err
						.println("tcl: package argument is null or not simple reference");
			}
			return;
		}
		String arg = ((SimpleReference) nameSpaceArg).getName();
		if (arg.equals("provide") || arg.equals("ifneeded")) {
			Expression pkg = statement.getAt(2);
			Expression pkgVer = null;
			if (statement.getCount() > 3) {
				pkgVer = statement.getAt(3);
			}
			if (pkg != null && pkg instanceof SimpleReference) {
				String _pkg = ((SimpleReference) pkg).getName();
				String _pkgVer = null;
				if (pkgVer != null && pkgVer instanceof SimpleReference) {
					_pkgVer = ((SimpleReference) pkgVer).getName();
					this.fRequestor.acceptPackage(pkg.sourceStart(), pkg
							.sourceEnd(), (_pkg + " (" + _pkgVer + ")")
							.toCharArray());
				} else {
					this.fRequestor.acceptPackage(pkg.sourceStart(), pkg
							.sourceEnd(), (_pkg + " (version n/a)")
							.toCharArray());
				}
			}
		}
	}

	private void processVariableSet(TclStatement statement, HashSet vars) {
		if (statement.getCount() < 2) {
			// TODO: Add error reporting here.
			return;
		}
		Expression variableName = statement.getAt(1);
		if (variableName == null) {
			throw new RuntimeException("empty variable name");
		}
		makeVariable(variableName, vars, 0, "");
	}

	private void makeVariable(Expression variableName, HashSet vars, int flags,
			String namespaceName) {
		String name = null;
		int start = 0;
		int end = 0;
		if (variableName instanceof SimpleReference) {
			name = ((SimpleReference) variableName).getName();
		} else if (variableName instanceof TclBlockExpression) {
			name = ((TclBlockExpression) variableName).getBlock();
			name = nameFromBlock(name, '{', '}');
		} else if (variableName instanceof StringLiteral) {
			name = ((StringLiteral) variableName).getValue();
			name = nameFromBlock(name, '"', '"');
		} else if (variableName instanceof TclExecuteExpression) {
			name = ((TclExecuteExpression) variableName).getExpression();
			if (vars.contains(name)) {
				return;
			}
		} else {
			// Unknown Expression
			return;
		}
		if (name != null) {

			// if( name.startsWith("$")) {
			// name = name.substring(1);
			// }

			if (!name.startsWith("::")) {
				if ((flags == TclConstants.TCL_FIELD_TYPE_NAMESPACE)
						&& namespacesLevel.size() > 0) {
					name = this.namespacesLevel.peek() + "::" + name;
				}
			}

			String arrayName = null;
			String arrayIndex = null;
			if (isArrayVariable(name)) {
				int t1 = name.indexOf("(");
				if (name.charAt(t1 - 1) == '\\')
					t1--;
				arrayName = name.substring(0, t1);
				arrayIndex = name.substring(name.indexOf("(") + 1, name
						.length() - 1);
				if (arrayIndex.endsWith("\\"))
					arrayIndex = arrayIndex.substring(0,
							arrayIndex.length() - 1);
			}

			if (arrayName != null)
				name = arrayName;

			String fullName = escapeName(name);

			if (fullName.indexOf("::") != -1) {
				String[] split = name.split("::");
				name = split[split.length - 1];
			}

			start = variableName.sourceStart();
			end = variableName.sourceEnd();
			ISourceElementRequestor.FieldInfo fi = new ISourceElementRequestor.FieldInfo();
			fi.name = name;
			fi.nameSourceStart = start;
			fi.nameSourceEnd = end - 1;
			fi.declarationStart = start;
			fi.modifiers = flags;

			boolean needExit = false;

			ExitFromType exit = resolveType(variableName, fullName, false);
			needExit = this.fRequestor.enterFieldCheckDuplicates(fi);

			if (needExit) {
				if (arrayName != null) {
					ISourceElementRequestor.FieldInfo fiIndex = new ISourceElementRequestor.FieldInfo();
					fiIndex.name = arrayName + "(" + arrayIndex + ")";
					fiIndex.nameSourceStart = start;
					fiIndex.nameSourceEnd = end - 1;
					fiIndex.declarationStart = start;
					fiIndex.modifiers = TclConstants.TCL_FIELD_TYPE_INDEX;
					if (this.fRequestor.enterFieldCheckDuplicates(fiIndex))
						this.fRequestor.exitField(end);
				}
				this.fRequestor.exitField(end);
			}

			exit.go();
		}
	}

	private boolean isArrayVariable(String name) {
		if (name.length() <= 2)
			return false;
		if (!name.endsWith(")"))
			return false;
		if (name.indexOf("(") == -1)
			return false;
		return true;
	}

	private String escapeName(String name) {
		name = SimpleTclParser.magicSubstitute(name);
		StringBuffer res = new StringBuffer();
		int len = name.length();
		for (int i = 0; i < len; i++)
			if (Character.isISOControl(name.charAt(i))) {
				res.append("\\u");
				String tmp = Integer.toHexString(name.charAt(i)).toUpperCase();
				if (tmp.length() == 1)
					res.append("0");
				res.append(tmp);
			} else
				res.append(name.charAt(i));
		String ans = res.toString();
		if (ans.trim().length() == 0) {
			return "{" + name + "}";
		}
		return ans;
	}

	private String nameFromBlock(String name, char c1, char c2) {
		int pos = name.indexOf(c1);
		String nname = name.substring(pos + 1);
		pos = nname.lastIndexOf(c2);
		nname = nname.substring(0, pos);
		return nname;
	}

	private void processUpvarVariable(TclStatement statement, HashSet vars) {
		int statementsCount = statement.getCount();
		if (statementsCount < 2) {
			// TODO: Add error reporting here.
			return;
		}

		Expression level = statement.getAt(1);

		int startIndex = 1;

		if (level instanceof SimpleReference) {
			SimpleReference sLevel = (SimpleReference) level;
			String str = sLevel.getName();
			if (str == null || str.length() == 0) {
				throw new RuntimeException("empty upvar level name");
			}
			if (str.startsWith("#") || str.startsWith("\\#")
					|| Character.isDigit(str.charAt(0))) {
				// first arg is a level
				startIndex++;
			}
		}

		for (int i = startIndex; i < statementsCount; i += 2) {
			Expression variableName = statement.getAt(i + 1);
			if (variableName == null) {
				// throw new RuntimeException("empty variable name");
				if (DLTKCore.DEBUG) {
					System.out.println("Incorrect upvar variable declaration");
				}
				return;
			}
			makeVariable(variableName, vars, TclConstants.TCL_FIELD_TYPE_UPVAR,
					"");
		}
	}

	private void processGlobalVariable(TclStatement statement, HashSet vars) {
		int statementsCount = statement.getCount();
		if (statementsCount < 2) {
			// TODO: Add error reporting here.
			return;
		}

		for (int i = 1; i < statementsCount; i++) {
			Expression variableName = statement.getAt(i);
			if (variableName == null) {
				throw new RuntimeException("empty variable name");
			}
			makeVariable(variableName, vars,
					TclConstants.TCL_FIELD_TYPE_GLOBAL, "");
		}
	}

	private void processVariable(TclStatement statement, HashSet vars,
			String namespaceName) {
		if (statement.getCount() < 2) {
			// TODO: Add error reporting here.
			return;
		}
		for (int j = 1; j < statement.getCount(); j += 2) {
			Expression variableName = statement.getAt(j);
			if (variableName == null) {
				throw new RuntimeException("empty variable name");
			}
			makeVariable(variableName, vars,
					TclConstants.TCL_FIELD_TYPE_NAMESPACE, namespaceName);
		}
	}

	private void processProc(TclStatement statement, String namespaceName) {
		// should be 3 parameters.
		if (statement.getCount() < 4) {
			// TODO: Add error reporting here.
			// System.err.println( "tcl proc not correct..." );
			return;
		}
		Expression procName = statement.getAt(1);
		if (procName == null/* || !( procName instanceof SimpleReference ) */) {
			throw new RuntimeException("empty proc name");
		}

		String sName = null;
		if (procName instanceof SimpleReference)
			sName = ((SimpleReference) procName).getName();
		else if (procName instanceof TclBlockExpression)
			sName = ((TclBlockExpression) procName).getBlock();
		else if (procName instanceof TclExecuteExpression)
			sName = ((TclExecuteExpression) procName).getExpression();
		else
			return;

		Expression procArguments = statement.getAt(2);
		Expression procCode = statement.getAt(3);

		List arguments = null;
		if (procArguments instanceof TclBlockExpression) {
			List/* < Statement > */st = null;

			st = ((TclBlockExpression) procArguments).parseBlock();

			arguments = TclParseUtils.parseArguments(st);
		}
		if (procArguments instanceof SimpleReference) {
			arguments = new ArrayList();
			Argument a = new Argument((SimpleReference) procArguments,
					procArguments.sourceStart(), null, 0);
			arguments.add(a);
		}
		// add element.
		String[] parameter = null;
		String[] parameterInitializers = null;
		if (arguments != null) {
			parameter = new String[arguments.size()];
			parameterInitializers = new String[arguments.size()];
			for (int a = 0; a < arguments.size(); a++) {
				Object node = arguments.get(a);
				parameterInitializers[a] = null;
				if (node instanceof Argument) {
					Argument ref = (Argument) node;
					parameter[a] = ref.getName();
					Expression e = ref.getInitialization();
					if (e != null) {
						if (e instanceof SimpleReference) {
							parameterInitializers[a] = ((SimpleReference) e)
									.getName();
						} else if (e instanceof TclBlockExpression) {
							String name = ((TclBlockExpression) e).getBlock();
							parameterInitializers[a] = nameFromBlock(name, '{',
									'}');
						} else if (e instanceof StringLiteral) {
							String name = ((StringLiteral) e).getValue();
							parameterInitializers[a] = nameFromBlock(name, '"',
									'"');
						} else if (e instanceof TclExecuteExpression) {
							String name = ((TclBlockExpression) e).getBlock();
							parameterInitializers[a] = name;
						}
					}
					// if( parameterInitializers[a] == null ) {
					// parameterInitializers[a] = "";
					// }
				} else if (node instanceof String) {
					parameter[a] = (String) node;
				}
			}
		}
		ISourceElementRequestor.MethodInfo mi = new ISourceElementRequestor.MethodInfo();

		sName = escapeName(sName);
		String fullName = sName;

		if (fullName.indexOf("::") != -1) {
			String[] split = fullName.split("::");
			sName = split[split.length - 1];
		}

		mi.parameterNames = parameter;
		mi.parameterInitializers = parameterInitializers;
		mi.name = sName;
		mi.modifiers = 0;
		mi.nameSourceStart = procName.sourceStart();
		mi.nameSourceEnd = procName.sourceEnd() - 1;
		mi.declarationStart = statement.sourceStart();

		ExitFromType exit = resolveType(procName, fullName, false);
		this.fRequestor.enterMethodRemoveSame(mi);

		if (procCode instanceof TclBlockExpression) {
			List/* < Statement > */code = null;

			code = ((TclBlockExpression) procCode).parseBlock(procCode
					.sourceStart() + 1);
			Iterator i = code.iterator();
			while (i.hasNext()) {
				Statement in = (Statement) i.next();
				in.setStart(in.sourceStart());
				in.setEnd(in.sourceEnd());
			}
			buildModel(code, TYPE_PROC, namespaceName);
		}
		this.fRequestor.exitMethod(statement.sourceEnd());
		exit.go();
	}

	private class ExitFromType {
		private int level;
		private int end;
		private boolean exitFromModule;
		private boolean pop;

		public ExitFromType(int level, int declEnd, boolean mod, boolean pop) {
			this.level = level;
			this.end = declEnd;
			this.exitFromModule = mod;
			this.pop = pop;
		}

		public void go() {
			for (int i = 0; i < level; i++) {
				fRequestor.exitType(this.end);
			}
			if (exitFromModule)
				fRequestor.exitModuleRoot();
			if (pop)
				namespacesLevel.pop();
		}
	}

	private String removeLastSegment(String s, String delimeter) {
		if (s.indexOf("::") == -1)
			return "";
		int pos = s.length() - 1;
		while (s.charAt(pos) != ':')
			pos--;
		if (pos > 1) {
			return s.substring(0, pos - 1);
		} else
			return "::";
	}

	/**
	 * Enters into required type (if type doesn't exists, creates it). If name
	 * is fully-qualified (starting with a "::") then it is always resolved
	 * globally. Else search are done first in current namespace, than in
	 * global. Flags <code>onlyCurrent</code> allows to search
	 * <em>not qualified</em> names only in current namespace. If type doesn't
	 * exists, it will be created. If name is qualified, it will be created
	 * globally, else in current namespace.s
	 * 
	 * @param expr
	 *            expression containing the name for correct source ranges setup
	 * @param name
	 *            name containing a type
	 * @param onlyCurrent
	 * @return ExitFromType object, that should be called to exit
	 */
	private ExitFromType resolveType(Expression expr, String name,
			boolean onlyCurrent) {
		String type = removeLastSegment(name, "::");
		while (type.length() > 2 && type.endsWith("::"))
			type = type.substring(0, type.length() - 2);

		if (type.length() == 0) {
			return new ExitFromType(0, 0, false, false);
		}

		if (type.equals("::")) {
			fRequestor.enterModuleRoot();
			namespacesLevel.push("::");
			return new ExitFromType(0, expr.sourceEnd(), true, true);
		}

		boolean fqn = type.startsWith("::");

		String fullyQualified = type;
		if (!fqn) { // make name fully-qualified
			String e = getEnclosingNamespace();
			if (e == null)
				throw new AssertionError("there are no enclosing namespace!");
			if (!e.endsWith("::"))
				e += "::";
			fullyQualified = e + type;
		}

		// first, try existent
		if (this.fRequestor.enterTypeAppend(type, "::")) {
			namespacesLevel.push(fullyQualified);
			return new ExitFromType(1, expr.sourceEnd(), false, true);
		} else if (!fqn && !onlyCurrent) { // look in global
			if (this.fRequestor.enterTypeAppend("::" + type, "::")) {
				namespacesLevel.push("::" + type);
				return new ExitFromType(1, expr.sourceEnd(), false, true);
			}
		}

		// create it
		int needEnterLeave = 0;
		String[] split = null;
		String e = getEnclosingNamespace();
		if (e == null)
			throw new AssertionError("there are no enclosing namespace!");
		boolean entered = false;
		boolean exitFromModule = false;
		if (e.length() > 0 && !fqn) {
			entered = fRequestor.enterTypeAppend(e, "::");
		}
		if (fqn || !entered) {
			split = fullyQualified.substring(2).split("::");
			fRequestor.enterModuleRoot();
			exitFromModule = true;
		} else {
			if (!entered)
				throw new AssertionError("can't enter to enclosing namespace!");
			needEnterLeave++;
			split = type.split("::");
		}

		for (int i = 0; i < split.length; ++i) {
			if (split[i].length() > 0) {
				needEnterLeave++;
				if (!this.fRequestor.enterTypeAppend(split[i], "::")) {
					ISourceElementRequestor.TypeInfo ti = new ISourceElementRequestor.TypeInfo();
					ti.modifiers = Modifiers.AccNameSpace;
					ti.name = split[i];
					ti.nameSourceStart = expr.sourceStart();
					ti.nameSourceEnd = expr.sourceEnd() - 1;
					ti.declarationStart = expr.sourceStart();
					this.fRequestor.enterType(ti);
				}
			}
		}
		namespacesLevel.push(fullyQualified);
		return new ExitFromType(needEnterLeave, expr.sourceEnd(),
				exitFromModule, true);
	}

	/**
	 * Returns fully-qualified name of current namespace
	 * 
	 * @return
	 */
	private String getEnclosingNamespace() {
		String s = (String) this.namespacesLevel.peek();
		return s;

	}

	public void setRequirestor(ISourceElementRequestor requestor) {
		this.fRequestor = requestor;
	}
}