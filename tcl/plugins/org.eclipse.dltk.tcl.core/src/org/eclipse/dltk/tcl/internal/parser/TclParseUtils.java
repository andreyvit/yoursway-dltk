/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.ast.expressions.TclExecuteExpression;

public class TclParseUtils {
	public interface IProcessStatementAction {
		void doAction(String name, Expression bl, int beforePosition);
	}

	public static List parseArguments(List st) {
		List arguments = new ArrayList();
		if (st != null && st.size() > 0) {
			TclStatement tclSt = (TclStatement) st.get(0);
			if (tclSt instanceof TclStatement) {
				Iterator i = tclSt.getExpressions().iterator();
				while (i.hasNext()) {
					Expression ex = (Expression) i.next();
					if (ex instanceof SimpleReference) {
						Argument a = new Argument();
						a.set((SimpleReference) ex, null);
						arguments.add(a);
					} else if (ex instanceof TclBlockExpression) {
						TclBlockExpression bl = (TclBlockExpression) ex;
						// try {
						List elements = bl.parseBlock();
						if (elements.size() > 0) {
							ASTNode node = (ASTNode) elements.get(0);
							Expression initializer = null;
							if (elements.size() > 1) {
								initializer = (Expression) elements.get(1);
							}
							if (node instanceof TclStatement
									&& ((TclStatement) node).getCount() > 0) {
								TclStatement stat = ((TclStatement) node);
								node = stat.getAt(0);
								if (stat.getCount() > 1) {
									initializer = stat.getAt(1);
								}
							}
							if (node instanceof SimpleReference) {
								Argument a = new Argument();
								a.set((SimpleReference) node, null);
								a.setInitializationExpression(initializer);
								arguments.add(a);
							} else if (node instanceof TclBlockExpression) {
								String name = ((TclBlockExpression) node)
										.getBlock();

								Argument a = new Argument();
								a.setStart(node.sourceStart() + 1);
								a.setEnd(node.sourceEnd() + 1);
								a
										.setArgumentName(nameFromBlock(name,
												'{', '}'));
								a.setInitializationExpression(initializer);
								arguments.add(a);
							} else if (node instanceof StringLiteral) {
								String name = ((StringLiteral) node).getValue();

								Argument a = new Argument();
								a.setStart(node.sourceStart() + 1);
								a.setEnd(node.sourceEnd() + 1);
								a
										.setArgumentName(nameFromBlock(name,
												'"', '"'));
								a.setInitializationExpression(initializer);
								arguments.add(a);
							} else if (node instanceof TclExecuteExpression) {
								String name = ((TclBlockExpression) node)
										.getBlock();

								Argument a = new Argument();
								a.setStart(node.sourceStart() + 1);
								a.setEnd(node.sourceEnd() + 1);
								a.setArgumentName(name);
								a.setInitializationExpression(initializer);
								arguments.add(a);
							}
						}
						/*
						 * } catch (ANTLRException e) { if
						 * (DLTKCore.DEBUG_PARSER) {
						 * System.out.println("ANTLRException: " +
						 * e.getMessage());
						 * System.out.println("TclParseUtils.parseArguments()");
						 * e.printStackTrace(); } }
						 */
					}
				}
			}
		}
		return arguments;
	}

	public static String nameFromBlock(String name, char c1, char c2) {
		int pos = name.indexOf(c1);
		String nname = name.substring(pos + 1);
		pos = nname.lastIndexOf(c2);
		if (pos < 0) {
			return "";
		}
		nname = nname.substring(0, pos);
		return nname;
	}

	public static String[] returnVariable(TclStatement s) {
		Expression sName = s.getAt(0);
		List variableName = new ArrayList();
		if (!(sName instanceof SimpleReference)) {
			return null;
		}
		String name = ((SimpleReference) sName).getName();
		if (name.equals("set") && s.getCount() > 2) {
			variableName.add(s.getAt(1));
		} else if (name.equals("variable") && s.getCount() >= 2) {
			for (int j = 1; j < s.getCount(); j += 2) {
				variableName.add(s.getAt(j));
			}
		} else if (name.equals("global") && s.getCount() >= 2) {
			for (int j = 1; j < s.getCount(); ++j) {
				variableName.add(s.getAt(j));
			}
		} else if (name.equals("upvar")) {
			Expression level = s.getAt(1);

			int startIndex = 1;

			if (level instanceof SimpleReference) {
				SimpleReference sLevel = (SimpleReference) level;
				String str = sLevel.getName();
				if (str == null || str.length() == 0) {
					throw new RuntimeException("empty upvar level name");
				}
				if (str.startsWith("#") || Character.isDigit(str.charAt(0))) {
					// first arg is a level
					startIndex++;
				}
			}
			// TODO: Add multiple variable definitions here.
			for (int i = startIndex; i < s.getCount(); i += 2) {
				Expression vName = s.getAt(i + 1);
				if (vName == null) {
					return null;
				}
				variableName.add(vName);
				break;
			}
		}
		if (variableName == null) {
			return null;
		}

		List names = new ArrayList();

		for (int k = 0; k < variableName.size(); ++k) {
			if (variableName.get(k) instanceof SimpleReference) {
				name = ((SimpleReference) variableName.get(k)).getName();
				if (name.indexOf('(') != -1) {
					name = name.substring(0, name.indexOf('('));
				}
			} else if (variableName.get(k) instanceof TclBlockExpression) {
				name = ((TclBlockExpression) variableName.get(k)).getBlock();
				name = nameFromBlock(name, '{', '}');
			} else if (variableName.get(k) instanceof StringLiteral) {
				name = ((StringLiteral) variableName.get(k)).getValue();
				name = nameFromBlock(name, '"', '"');
			} else if (variableName.get(k) instanceof TclExecuteExpression) {
				name = ((TclExecuteExpression) variableName.get(k))
						.getExpression();
			} else {
				name = null;
			}
			if (name != null) {
				names.add(name);
			}
		}
		if (names.size() == 0) {
			return null;
		}
		return (String[]) names.toArray(new String[names.size()]);
	}

	public static String returnVariableCheck(TclStatement s, int position) {
		Expression sName = s.getAt(0);
		Expression variableName;
		if (!(sName instanceof SimpleReference)) {
			return null;
		}
		String name = ((SimpleReference) sName).getName();
		if (name.equals("set") && s.getCount() > 2) {
			variableName = s.getAt(1);
		} else if (name.equals("variable") && s.getCount() >= 2) {
			variableName = s.getAt(1);
		} else if (name.equals("global") && s.getCount() >= 2) {
			variableName = s.getAt(1);
		} else if (name.equals("upvar") && s.getCount() >= 4) {
			variableName = s.getAt(3);
		} else {
			return null;
		}
		if (variableName.sourceStart() > position
				|| position > variableName.sourceEnd()) {
			return null;
		}

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
		} else {
			return null;
		}
		return name;
	}

	public static SimpleReference extractVariableFromString(
			StringLiteral literal, int pos) {
		String content = nameFromBlock(literal.getValue(), '"', '"');
		int position = pos - 1;
		int index = content.lastIndexOf('$', position);
		if (index != -1 && index < position) {
			String sub = content.substring(index, position);
			if (sub.indexOf(' ') == -1) {
				return new SimpleReference(literal.sourceStart() + index + 1,
						literal.sourceStart() + index + 1 + sub.length(), sub);
			}
		}
		return null;
	}

	public static SimpleReference findVariableFromString(StringLiteral literal,
			int pos) {
		String content = nameFromBlock(literal.getValue(), '"', '"');
		int position = pos - 1;
		int start = startLineOrNoSymbol(position, content);
		try {
			if (content.charAt(position) == '$') {
				position++;
				if (position < content.length() && content.charAt(position) == '{')
					position++;
			}
		} catch (IndexOutOfBoundsException e) {
		}
		int end = endLineOrNoSymbol(position, content);
		if (start < 0) {
			start = 0;
		}
		if (start + 1 < content.length() && (content.charAt(start) == '$' && content.charAt(start + 1) == '{')
				|| (content.charAt(start) == '{')) {
			while (content.charAt(end) != '}' && content.charAt(end) != '\\' && end < content.length())
				end++;
			end++;
			
		}
		if (start < end) {
			String sub = content.substring(start, end);
			return new SimpleReference(literal.sourceStart() + start + 1,
					literal.sourceStart() + 1 + sub.length(), sub);
		}
		return null;
	}

	public static int endLineOrSymbol(int from, String content) {
		int pos = 0;
		for (pos = from; pos < content.length(); ++pos) {
			char c = content.charAt(pos);
			if (c == '\n' || c == '\r' || c == ';') {
				return pos;
			}
			if (!Character.isWhitespace(c)) {
				return pos;
			}
		}
		if (pos == content.length()) {
			return pos;
		}
		return from;
	}

	public static int startLineOrSymbol(int from, String content) {
		if (from == -1) {
			from = 0;
		}
		if (from >= content.length())
			from--;
		for (int pos = from - 1; pos > 0; --pos) {
			char c = content.charAt(pos);
			if (c == '\n' || c == '\r' || c == ';') {
				return pos + 1;
			}
			if (!Character.isWhitespace(c)) {
				return pos + 1;
			}
		}
		return from;
	}

	public static int endLineOrNoSymbol(int from, String content) {
		int pos = 0;
		if (from == -1) {
			from = 0;
		}
		if (from >= content.length())
			from--;
		for (pos = from; pos < content.length(); ++pos) {
			if (checkBounds(content, pos)) {
				if (content.charAt(pos) == '$' && pos == from) {
					continue;
				}
				return pos;
			}
		}
		if (pos == content.length()) {
			return pos;
		}
		return pos;
	}

	private static boolean checkBounds(String content, int pos) {
		char[] syms = { ' ', '\t', '\n', '\r', ']', '[', '}', '{', '(', ')',
				'$', '\\' };
		char c = content.charAt(pos);
		for (int i = 0; i < syms.length; ++i) {
			if (syms[i] == c) {
				return true;
			}
		}
		return false;
	}

	public static int startLineOrNoSymbol(int from, String content) {
		if (from == -1) {
			from = 0;
		}
		if (from >= content.length())
			from--;
		int pos;
		for (pos = from; pos > 0; --pos) {
			if (checkBounds(content, pos)) {
				if (content.charAt(pos) == '$') {
					return pos;
				}
				return pos + 1;
			}
		}
		return pos;
	}

	public static String processMethodName(IMethod method, String tok) {
		String elName = method.getElementName();
		if (elName.startsWith("::")) {
			return checkDots(elName,tok);
		}
		String name = "";
		try {
			name = method.getTypeQualifiedName("$", false);
		} catch (ModelException e1) {
			e1.printStackTrace();
		}
		// name = name.replace("$", "::");
		name = name.replaceAll("\\$", "::");
		if (tok.startsWith("::") && !name.startsWith("::")) {
			name = "::" + name;
		}
		return checkDots(name,tok);
	}
	public static String processFieldName(IField field, String tok) {
		String elName = field.getElementName();
		if (elName.startsWith("::")) {
			return checkDots( elName, tok );
		}
		String name = "";
		try {
			name = field.getTypeQualifiedName("$", false);
		} catch (ModelException e1) {
			e1.printStackTrace();
		}
		// name = name.replace("$", "::");
		name = name.replaceAll("\\$", "::");
		if (tok.startsWith("::") && !name.startsWith("::")) {
			name = "::" + name;
		}
		return checkDots( name, tok );
	}

	private static String checkDots(String elName, String tok) {
		if( elName.startsWith("::") && !tok.startsWith("::") ) {
			return elName.substring(2);
		}
		return elName;
	}

	public static String processTypeName(IType type, String tok) {
		String elName = type.getElementName();
		if (elName.startsWith("::")) {
			return elName;
		}
		String name = type.getTypeQualifiedName("$");
		name = name.replaceAll("\\$", "::");
		if (tok.startsWith("::") && !name.startsWith("::")) {
			name = "::" + name;
		}
		return name;
	}

	public static FieldDeclaration[] returnVariableDeclarations(
			TclStatement statement) {
		List fields = new ArrayList();

		String[] vars = TclParseUtils.returnVariable(statement);
		if (vars != null) {
			for (int j = 0; j < vars.length; ++j) {
				int nStart = statement.sourceStart();
				int nEnd = statement.sourceEnd();
				List nodes = statement.getExpressions();
				for (int k = 0; k < nodes.size(); ++k) {
					Object o = nodes.get(k);
					if (o instanceof SimpleReference) {
						SimpleReference r = (SimpleReference) o;
						if (r.getName().equals(vars[j])) {
							nStart = r.sourceStart();
							nEnd = r.sourceEnd();
						}
					}
				}
				fields.add(new FieldDeclaration(vars[j], nStart, nEnd,
						nStart, nEnd));
			}
		}
		return (FieldDeclaration[]) fields.toArray(new FieldDeclaration[fields
				.size()]);
	}
	public static void processIf(List exprs, String name, int beforePosition, IProcessStatementAction action) {
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
							action.doAction(name, bl, beforePosition);
						}
						break;
					}
					i = bi - 1;
				} else if (value.equals("else")) {
					if (i + 1 < len) {
						Expression bl = (Expression) exprs.get(i + 1);
						if (bl instanceof TclBlockExpression) {
							action.doAction(name, bl, beforePosition);
						}
					}
				}
			}
		}
	}
}
