package org.eclipse.dltk.tcl.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.ast.declarations.Declaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.ast.expressions.TclExecuteExpression;
import org.eclipse.dltk.tcl.core.ast.ExtendedTclMethodDeclaration;
import org.eclipse.dltk.tcl.internal.core.codeassist.TclASTUtil;
import org.eclipse.dltk.tcl.internal.parsers.raw.BracesSubstitution;
import org.eclipse.dltk.tcl.internal.parsers.raw.CommandSubstitution;
import org.eclipse.dltk.tcl.internal.parsers.raw.QuotesSubstitution;
import org.eclipse.dltk.tcl.internal.parsers.raw.SimpleTclParser;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclElement;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclWord;

public class TclParseUtil {
	public static String extractWord(TclElement element, String content) {
		return content.substring((element).getStart(), (element).getEnd() + 1);
	}

	public static String extractWord(TclElement element, ITclParser parser,
			int s, int e) {
		return parser.substring((element).getStart() + s, (element).getEnd()
				+ 1 + e);
	}

	public static String extractWord(TclElement element, ITclParser parser) {
		return parser.substring((element).getStart(), (element).getEnd() + 1);
	}

	public static boolean isCommandStart(Object object) {
		return object != null && object.getClass().equals(TclWord.class);
	}

	public static class CodeModel {
		private String[] codeLines;

		private int[] codeLineLengths;

		public CodeModel(String code) {
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
			String codeLine = this.codeLines[lineNumber];

			int start = this.codeLineLengths[lineNumber];
			int end = start + codeLine.length();

			return new int[] { start, end };
		}

		public int getLineNumber(TclElement command) {
			return this.getLineNumber(command.getStart(), command.getEnd());
		}

		public int getLineNumber(int start, int end) {
			int len = this.codeLines.length;
			for (int i = 0; i < len; ++i) {
				String codeLine = this.codeLines[i];

				int s = this.codeLineLengths[i];
				int e = start + codeLine.length();

				if (start <= s && end <= e) {
					return i + 1;
				}
			}
			return this.codeLines.length;
		}
	}

	public static String nameFromBlock(String name, char c1, char c2) {
		int pos = name.indexOf(c1);
		String nname = name.substring(pos + 1);
		pos = nname.lastIndexOf(c2);
		nname = nname.substring(0, pos);
		return nname;
	}

	public static boolean isArrayVariable(String name) {
		if (name.length() <= 2) {
			return false;
		}
		if (!name.endsWith(")")) {
			return false;
		}
		if (name.indexOf("(") == -1) {
			return false;
		}
		return true;
	}

	public static String escapeName(String name) {
		name = SimpleTclParser.magicSubstitute(name);
		StringBuffer res = new StringBuffer();
		int len = name.length();
		for (int i = 0; i < len; i++) {
			if (Character.isISOControl(name.charAt(i))) {
				res.append("\\u");
				String tmp = Integer.toHexString(name.charAt(i)).toUpperCase();
				if (tmp.length() == 1) {
					res.append("0");
				}
				res.append(tmp);
			} else {
				res.append(name.charAt(i));
			}
		}
		String ans = res.toString();
		if (ans.trim().length() == 0) {
			return "{" + name + "}";
		}
		return ans;
	}

	public static SimpleReference makeVariable(Expression variableName) {
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
		}
		if (name != null) {

			// String arrayName = null;
			// String arrayIndex = null;
			// if (isArrayVariable(name)) {
			// int t1 = name.indexOf("(");
			// if (t1 > 0 && (name.charAt(t1 - 1) == '\\')) {
			// t1--;
			// }
			// arrayName = name.substring(0, t1);
			// arrayIndex = name.substring(name.indexOf("(") + 1, name
			// .length() - 1);
			// if (arrayIndex.endsWith("\\")) {
			// arrayIndex = arrayIndex.substring(0,
			// arrayIndex.length() - 1);
			// }
			// }
			//
			// if (arrayName != null) {
			// name = arrayName;
			// }

			String fullName = escapeName(name);

			start = variableName.sourceStart();
			end = variableName.sourceEnd();
			return new SimpleReference(start, end, fullName);
		}
		return null;
	}

	public static TclStatement convertToAST(TclCommand command,
			char[] filename, int offset, String content, int startPos) {
		List words = command.getWords();
		List exprs = new ArrayList();
		for (Iterator iterator = words.iterator(); iterator.hasNext();) {
			TclWord word = (TclWord) iterator.next();
			String wordText = content.substring(offset + word.getStart(), word
					.getEnd()
					+ 1 + offset);
			// wordText = SimpleTclParser.magicSubstitute(wordText);
			Object o = word.getContents().get(0);
			if (o instanceof QuotesSubstitution) {
				QuotesSubstitution qs = (QuotesSubstitution) o;

				exprs.add(new StringLiteral(startPos + offset + qs.getStart(),
						startPos + offset + qs.getEnd() + 1, wordText));
			} else if (o instanceof BracesSubstitution) {
				BracesSubstitution bs = (BracesSubstitution) o;
				wordText = content.substring(offset + word.getStart(), word
						.getEnd()
						+ 1 + offset);
				TclBlockExpression tclBlockExpression = new TclBlockExpression(
						startPos + offset + bs.getStart(), startPos + offset
								+ bs.getEnd() + 1, wordText);
				// Advanced content for tcl blocks.
				tclBlockExpression.setFilename(filename);
				exprs.add(tclBlockExpression);
			} else if (o instanceof CommandSubstitution
					&& (word.getContents().size() == 1)) {
				CommandSubstitution bs = (CommandSubstitution) o;

				exprs.add(new TclExecuteExpression(startPos + offset
						+ bs.getStart(), startPos + offset + bs.getEnd() + 1,
						wordText));
			} else {
				exprs.add(new SimpleReference(startPos + offset
						+ word.getStart(), startPos + offset + word.getEnd()
						+ 1, wordText));
			}
		}
		TclStatement st = new TclStatement(exprs);
		return st;
	}

	public static void addToDeclaration(ASTNode decl, ASTNode node) {
		if (decl instanceof ModuleDeclaration && node instanceof Statement) {
			((ModuleDeclaration) decl).addStatement((Statement) node);
		} else if (decl instanceof TypeDeclaration) {
			((TypeDeclaration) decl).getStatements().add(node);
		} else if (decl instanceof MethodDeclaration) {
			((MethodDeclaration) decl).getStatements().add(node);
		} else if (decl instanceof Block) {
			((Block) decl).addStatement(node);
		}
	}

	public static void removeFromDeclaration(ASTNode decl, ASTNode node) {
		if (decl instanceof ModuleDeclaration && node instanceof Statement) {
			((ModuleDeclaration) decl).removeStatement((Statement) node);
		} else if (decl instanceof TypeDeclaration) {
			((TypeDeclaration) decl).getStatements().remove(node);
		} else if (decl instanceof MethodDeclaration) {
			((MethodDeclaration) decl).getStatements().remove(node);
		} else if (decl instanceof Block) {
			((Block) decl).removeStatement(node);
		}
	}

	public static List findLevelsTo(ModuleDeclaration module,
			ASTNode astNodeParent) {
		List elements = new ArrayList();
		if (module != null || astNodeParent instanceof ModuleDeclaration) {
			if (module == null) {
				module = (ModuleDeclaration) astNodeParent;
			}
			elements.add(module);
			findElementsTo(TclASTUtil.getStatements(module), astNodeParent,
					elements);
		}
		return elements;
	}

	public static void findElementsTo(List statements, ASTNode node,
			List elements) {
		if (statements == null) {
			return;
		}
		Iterator i = statements.iterator();
		while (i.hasNext()) {
			ASTNode n = (ASTNode) i.next();
			if (n.equals(node)) {
				elements.add(n);
				return;
			}
			if (n.sourceStart() <= node.sourceStart()
					&& node.sourceEnd() <= n.sourceEnd()) {
				elements.add(n);
				findElementsTo(TclASTUtil.getStatements(n), node, elements);
				return;
			}
		}
	}

	public static TypeDeclaration findXOTclTypeDeclarationFrom(
			ModuleDeclaration module, ASTNode parent, String originalName) {
		return findTclTypeDeclarationFrom(module, parent, originalName, true);
	}

	public static TypeDeclaration findTclTypeDeclarationFrom(
			ModuleDeclaration module, ASTNode node) {
		String name = getNameFromNode(node);
		if (name == null) {
			return null;
		}
		if (name.indexOf("::") != -1) {
			name = name.substring(0, name.lastIndexOf("::"));
		}
		List levels = findLevelsTo(module, node);
		if (levels.size() == 2) {
			return findTclTypeDeclarationFrom(module, module, name, false);
		} else if (levels.size() - 2 > 0) {
			return findTclTypeDeclarationFrom(module, (ASTNode) levels
					.get(levels.size() - 2), name, false);
		}
		return null;
	}

	public static String getNameFromNode(ASTNode node) {
		if (node instanceof Declaration) {
			return ((Declaration) node).getName();
		} else if (node instanceof SimpleReference) {
			return ((SimpleReference) node).getName();
		}
		return null;
	}

	public static TypeDeclaration findTclTypeDeclarationFrom(
			ModuleDeclaration module, ASTNode parent, String originalName,
			boolean onlyXOTcl) {
		String name = originalName;
		boolean startFromTop = false;
		if (name.startsWith("::")) {
			startFromTop = true;
			name = name.substring(2);
		}
		String[] split = name.split("::");
		// Set name last
		name = split[split.length - 1];
		if (!startFromTop) {
			List levels = TclParseUtil.findLevelsTo(module, parent);
			int len = levels.size();
			// We need to observe only previous level.
			// for (int j = 0; j < len; ++j) {
			if (len > 0) {
				ASTNode astNodeParent = (ASTNode) levels.get(len - 1/* - j */);
				List childs = TclASTUtil.getStatements(astNodeParent);
				if (childs != null) {
					TypeDeclaration ty = findTclTypeCheckASTLevel(originalName,
							split, childs, onlyXOTcl);
					if (ty != null) {
						return ty;
					}
				}
			}
		} else {
			List childs = TclASTUtil.getStatements(module);
			if (childs == null) {
				return null;
			}
			TypeDeclaration ty = findTclTypeCheckASTLevel(originalName, split,
					childs, onlyXOTcl);
			if (ty != null) {
				return ty;
			}
		}
		return null;
	}

	private static TypeDeclaration findTclTypeCheckASTLevel(
			String originalName, String[] split, List childs, boolean onlyXOTcl) {
		for (int i = 0; i < childs.size(); i++) {
			if (!(childs.get(i) instanceof TypeDeclaration)) {
				continue;
			}
			TypeDeclaration type = (TypeDeclaration) childs.get(i);
			if ((type.getModifiers() & Modifiers.AccNameSpace) == 0
					|| !onlyXOTcl) {
				// if (type.getName().equals(originalName)) { // Check for
				// // original name
				// return type;
				// }
				// Check complex in
				String cName = split[0];
				String tName = type.getName();
				if (tName.startsWith("::")) {
					tName = tName.substring(2);
				}
				for (int q = 1; q <= split.length; ++q) {
					if (tName.equals(cName)) {
						if (q == split.length) {
							return type;
						} else {
							String nsplit[] = new String[split.length - q];
							System.arraycopy(split, q, nsplit, 0, split.length
									- q);
							List nchilds = TclASTUtil.getStatements(type);
							if (childs == null) {
								continue;
							}
							TypeDeclaration ty = findTclTypeCheckASTLevel(
									originalName, nsplit, nchilds, onlyXOTcl);
							if (ty != null) {
								return ty;
							}
						}
					}
					if (q != split.length) {
						cName += "::" + split[q];
					}
				}
				if (tName.equals(split[0]) && split.length == 1) {
					return type;
				} else {
					if (split.length > 1) {
						List nchilds = TclASTUtil.getStatements(type);
						if (childs == null) {
							continue;
						}
						String nsplit[] = new String[split.length - 1];
						System.arraycopy(split, 1, nsplit, 0, split.length - 1);
						TypeDeclaration ty = findTclTypeCheckASTLevel(
								originalName, nsplit, nchilds, onlyXOTcl);
						if (ty != null) {
							return ty;
						}
					}
				}
			}
		}
		return null;
	}

	
	public static TypeDeclaration findTypesFromASTNode(
			ModuleDeclaration module, ASTNode node, String name) {
		List levels = findLevelsTo(module, node);
		String[] split = name.split("::");
		for (int i = 0; i < levels.size() - 1; i++) {
			ASTNode nde = (ASTNode) levels.get(levels.size() - i - 2);
			if (nde instanceof TypeDeclaration) {
				TypeDeclaration type = (TypeDeclaration) nde;
				if (split.length == 2) {
					if (type.getName().equals(split[0])) {
						return type;
					}
				}
				TypeDeclaration[] types = type.getTypes();
			}
		}
		return null;
	}

	public static String getElementFQN(ASTNode node, String separator,
			ModuleDeclaration module) {
		List nodes = findLevelsTo(module, node);
		return getElementFQN(nodes, separator, module);
	}

	public static String getElementFQN(List nodes, String separator,
			ModuleDeclaration module) {
		StringBuffer prefix = new StringBuffer();
		for (Iterator iterator = nodes.iterator(); iterator.hasNext();) {
			ASTNode ns = (ASTNode) iterator.next();
			String name = null;
			if (ns instanceof ModuleDeclaration) {
				name = "";
				// module = (ModuleDeclaration) ns;
			} else if (ns instanceof TypeDeclaration) {
				name = ((TypeDeclaration) ns).getName();
			} else if (ns instanceof MethodDeclaration) {
				if (ns instanceof ExtendedTclMethodDeclaration) {
					ExtendedTclMethodDeclaration m = (ExtendedTclMethodDeclaration) ns;
					ASTNode declaringXOTclType = m.getDeclaringType();
					List ndss = findLevelsTo(module, declaringXOTclType);
					name = "::" + getElementFQN(ndss, separator, module)
							+ separator + m.getName();
				} else {
					name = ((MethodDeclaration) ns).getName();
				}
			}
			if (name != null) {
				if (name.startsWith("::")) {
					prefix.delete(0, prefix.length());
					name = name.substring(2);
				}
				if (name.length() > 0) {
					prefix.append(tclNameTo(name, separator) + separator);
				}
			}
		}

		String result = prefix.toString();
		if (result.endsWith(separator)) {
			return result.substring(0, result.length() - separator.length());
		}
		return result;
	}

	public static String tclNameTo(String name, String separator) {
		if (!separator.equals("::")) {
			return name.replaceAll("::", separator);
		}
		return name;
	}

	public static String extractArrayName(String name) {
		int t1 = name.indexOf("(");
		if (t1 > 0 && (name.charAt(t1 - 1) == '\\')) {
			t1--;
		}
		return name.substring(0, t1);
	}

	public static String extractArrayIndex(String name) {
		int t1 = name.indexOf("(");
		if (t1 > 0 && (name.charAt(t1 - 1) == '\\')) {
			t1--;
		}
		String arrayIndex = name.substring(name.indexOf("(") + 1,
				name.length() - 1);
		if (arrayIndex.endsWith("\\")) {
			arrayIndex = arrayIndex.substring(0, arrayIndex.length() - 1);
		}
		return arrayIndex;
	}

	public static ASTNode getScopeParent(ModuleDeclaration module, ASTNode node) {
		List levels = TclParseUtil.findLevelsTo(module, node);
		for (int i = 0; i < levels.size(); i++) {
			ASTNode nde = (ASTNode) levels.get(levels.size() - i - 1);
			if (nde instanceof TypeDeclaration
					|| nde instanceof MethodDeclaration
					|| nde instanceof ModuleDeclaration && nde instanceof Block) {
				return nde;
			}
		}
		return module;
	}

	public static ASTNode getPrevParent(ModuleDeclaration module,
			ASTNode declaringType) {
		ASTNode parent = TclParseUtil.getScopeParent(module, declaringType);
		if (parent instanceof ModuleDeclaration) {
			return parent;
		}
		List levels = TclParseUtil.findLevelsTo(module, parent);
		return (ASTNode) levels.get(levels.size() - 2);
	}

	public static List findLevelFromModule(final ModuleDeclaration module,
			final IMember member, final String memberFQN) {
		final List levels = new ArrayList();

		ASTVisitor visitor = new ASTVisitor() {
			public boolean visitGeneral(ASTNode s) throws Exception {
				if (s instanceof Declaration) {
					Declaration d = (Declaration) s;
					String key = "::" + getElementFQN(s, "::", module);
					if (key.equals(memberFQN)) {
						levels.add(d);
					}
				}
				return true;
			}
		};
		try {
			module.traverse(visitor);
		} catch (Exception e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
			return levels;
		}
		return levels;
	}

	public static String getNameFromModelElement(IModelElement member) {
		return getFQNFromModelElement(member, "::");
	}

	public static String getFQNFromModelElement(IModelElement member,
			String separator) {
		String buffer = new String();
		IModelElement m = member;
		while (m.getElementType() != IModelElement.SOURCE_MODULE) {
			buffer = separator + m.getElementName() + buffer;
			m = m.getParent();
		}
		return buffer;
	}
}
