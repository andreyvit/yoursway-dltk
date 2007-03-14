package org.eclipse.dltk.ruby.internal.parser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.Declaration;
import org.eclipse.dltk.ast.declarations.ISourceParser;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.IProblem;
import org.eclipse.dltk.compiler.IProblemReporter;
import org.eclipse.dltk.ruby.core.RubyPlugin;
import org.eclipse.dltk.ruby.internal.parsers.jruby.DLTKRubyParser;
import org.eclipse.dltk.ruby.internal.parsers.jruby.RubyASTBuildVisitor;
import org.jruby.ast.Node;
import org.jruby.ast.visitor.NodeVisitor;

public class JRubySourceParser implements IExecutableExtension, ISourceParser {

	private static boolean silentState = true;

	public static boolean isSilentState() {
		return silentState;
	}

	/**
	 * This option allows parser to suppress errors and exceptions and in result
	 * generate possibly partially non-correct AST instead of failing with
	 * exception. For running parser tests this option are being set to
	 * <code>false</code>.
	 */
	public static void setSilentState(boolean s) {
		silentState = s;
	}

	private final class ASTPositionsCorrector extends ASTVisitor {
		public boolean visitGeneral(ASTNode node) throws Exception {
			int st = 0;
			int en = 0;
			int n_st = 0;
			int n_en = 0;
			for (Iterator iterator = fixPositions.iterator(); iterator
					.hasNext();) {
				Integer pos = (Integer) iterator.next();
				int fixPos = pos.intValue();
				// starts
				if (node.sourceStart() > fixPos) {
					st++;
				}
				if (node.sourceEnd() > fixPos) {
					en++;
				}
				if (node instanceof Declaration) {
					Declaration declaration = (Declaration) node;
					if (declaration.getNameStart() > fixPos) {
						n_st++;
					}
					if (declaration.getNameEnd() > fixPos) {
						n_en++;
					}
				}
			}

			node.setStart(node.sourceStart() + st * magicLength);
			node.setEnd(node.sourceEnd() + en * magicLength);
			if (node instanceof Declaration) {
				Declaration declaration = (Declaration) node;
				declaration.setNameStart(declaration.getNameStart() + n_st
						* magicLength);
				declaration.setNameEnd(declaration.getNameEnd() + n_en
						* magicLength);
			}
			if (st == 0 && en == 0 && n_st == 0 && n_en == 0)
				return false;

			return true;
		}
	}

	private static final boolean TRACE_AST_JRUBY = Boolean.valueOf(
			Platform.getDebugOption("org.eclipse.dltk.core/traceAST/jruby"))
			.booleanValue();

	private static final boolean TRACE_AST_DLTK = Boolean.valueOf(
			Platform.getDebugOption("org.eclipse.dltk.core/traceAST/dltk"))
			.booleanValue();

	private static final Pattern DOT_FIXER = Pattern.compile("\\.(?=\\s|$)");
	private static final Pattern COLON_FIXER = Pattern.compile("::(?=\\s|$)");
	private IProblemReporter problemReporter;
	private static final String missingName = "_missing_method_name_";
	private static final String missingName2 = "NoConstant__________";
	private static final int magicLength = missingName.length();

	private final List fixPositions = new ArrayList();

	private String fixBrokenDots(String content) {
		Matcher matcher = DOT_FIXER.matcher(content);
		StringBuffer result = new StringBuffer();
		int regionStart = 0;
		while (matcher.find(regionStart)) {
			int offset = matcher.start();
			if (offset > regionStart)
				result.append(content.subSequence(regionStart, offset));
			result.append("." + missingName);
			fixPositions.add(new Integer(offset));
			regionStart = offset + 1;
		}
		if (regionStart < content.length() - 1)
			result.append(content.subSequence(regionStart, content.length()));
		if (regionStart == 0)
			return content; // no dots fixed
		else
			return result.toString();
	}

	private String fixBrokenColons(String content) {
		Matcher matcher = COLON_FIXER.matcher(content);
		StringBuffer result = new StringBuffer();
		int regionStart = 0;
		while (matcher.find(regionStart)) {
			int offset = matcher.start();
			if (offset > regionStart)
				result.append(content.subSequence(regionStart, offset));
			result.append("::" + missingName2);
			fixPositions.add(new Integer(offset));
			regionStart = offset + 2;
		}
		if (regionStart < content.length() - 1)
			result.append(content.subSequence(regionStart, content.length()));
		if (regionStart == 0)
			return content; // no dots fixed
		else
			return result.toString();
	}

	private final boolean[] errorState = new boolean[1];

	private class ProxyProblemReporter implements IProblemReporter {

		private final IProblemReporter original;

		public ProxyProblemReporter(IProblemReporter original) {
			super();
			this.original = original;
		}

		public void reportProblem(IProblem problem) throws CoreException {
			if (original != null)
				original.reportProblem(problem);
			if (problem.isError()) {
				errorState[0] = true;
			}
		}

	}

	public JRubySourceParser(IProblemReporter problemReporter) {
		this.problemReporter = problemReporter;
	}

	/**
	 * Should return visitor for creating ModuleDeclaration from JRuby's AST
	 * @param module
	 * @param content
	 * @return
	 */
	protected NodeVisitor getASTBuilderVisitor(ModuleDeclaration module,
			String content) {
		return new RubyASTBuildVisitor(module, content);
	}

	public ModuleDeclaration parse(String content) {
		try {
			DLTKRubyParser parser = new DLTKRubyParser();
			ProxyProblemReporter proxyProblemReporter = new ProxyProblemReporter(
					problemReporter);
			errorState[0] = false;

			long timeStart = System.currentTimeMillis();
			Node node = parser.parse("", new StringReader(content),
					proxyProblemReporter);
			fixPositions.clear();
			if (!parser.isSuccess() || errorState[0]) {
				content = fixBrokenDots(content);
				content = fixBrokenColons(content);

				Node node2 = parser.parse("", new StringReader(content), null);
				if (node2 != null)
					node = node2;
				else
					fixPositions.clear();
			}

			ModuleDeclaration module = new ModuleDeclaration(content.length());
			NodeVisitor visitor = getASTBuilderVisitor(module, content);
			if (node != null)
				node.accept(visitor);

			if (node != null) {
				if (TRACE_AST_JRUBY || TRACE_AST_DLTK)
					System.out.println("\n\nAST rebuilt\n");
				if (TRACE_AST_JRUBY)
					System.out.println("JRuby AST:\n" + node.toString());
				if (TRACE_AST_DLTK)
					System.out.println("DLTK AST:\n" + module.toString());
			}

			if (!fixPositions.isEmpty())
				try {
					module.traverse(new ASTPositionsCorrector());
				} catch (Exception e) {
					RubyPlugin.log(e);
				}

			long timeEnd = System.currentTimeMillis();
			if (TRACE_AST_DLTK)
				System.out.println("Parsing took " + (timeEnd - timeStart)
						+ " ms");
			return module;
		} catch (Throwable t) {
			t.printStackTrace();
			if (isSilentState()) {
				ModuleDeclaration mdl = new ModuleDeclaration(1);
				return mdl;
			}
			throw new RuntimeException(t);
		}
	}

	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
	}

}
