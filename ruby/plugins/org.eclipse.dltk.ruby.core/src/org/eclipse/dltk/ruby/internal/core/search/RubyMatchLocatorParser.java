package org.eclipse.dltk.ruby.internal.core.search;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.core.search.matching.MatchLocatorParser;
import org.eclipse.dltk.core.search.matching.PatternLocator;
import org.eclipse.dltk.core.search.matching.PossibleMatch;
import org.eclipse.dltk.ruby.internal.parser.JRubySourceParser;

public class RubyMatchLocatorParser extends MatchLocatorParser {
	private JRubySourceParser parser;


	public RubyMatchLocatorParser(MatchLocator locator) {
		super(locator);
		parser = new JRubySourceParser(null);
	}

	public ModuleDeclaration parse(PossibleMatch possibleMatch) {
		ModuleDeclaration module = parser.parse(possibleMatch.getSourceContents());
		module.rebuild();
		return module;
	}

	public void parseBodies(ModuleDeclaration unit) {
		TypeDeclaration[] types = unit.getTypes();
		if (types != null) {
			for (int i = 0; i < types.length; i++) {
				TypeDeclaration type = types[i];
				getPatternLocator().match(processType(type), getNodeSet());
				parseBodies(type);
			}
		}
		MethodDeclaration[] methods = unit.getFunctions();
		if (methods != null) {
			PatternLocator locator = getPatternLocator();
			for (int i = 0; i < methods.length; i++) {
				MethodDeclaration method = methods[i];
				if (method instanceof MethodDeclaration) {
					MethodDeclaration methodDeclaration = method;
					locator.match(processMethod(methodDeclaration), getNodeSet());
					parseBodies(methodDeclaration);
				}
			}
		}

		ASTNode[] nodes = unit.getNonTypeOrMethodNode();
		int length = nodes.length;
		for (int i = 0; i < length; i++) {
			processStatement(nodes[i]);
		}
	}

	private MethodDeclaration processMethod(MethodDeclaration m) {
		return m;
	}

	private TypeDeclaration processType(TypeDeclaration t) {
		return t;
	}

	protected void parseBodies(TypeDeclaration type) {

		PatternLocator locator = getPatternLocator();

		MethodDeclaration[] methods = type.getMethods();
		if (methods != null) {
			for (int i = 0; i < methods.length; i++) {
				MethodDeclaration method = methods[i];
				if (method instanceof MethodDeclaration) {
					MethodDeclaration methodDeclaration = method;
					locator.match(processMethod(methodDeclaration), getNodeSet());
					parseBodies(methodDeclaration);
				}
			}
		}

		TypeDeclaration[] memberTypes = type.getTypes();
		if (memberTypes != null) {
			for (int i = 0; i < memberTypes.length; i++) {
				TypeDeclaration memberType = memberTypes[i];
				locator.match(processType(memberType), getNodeSet());
				this.parseBodies(memberType);
			}
		}
		ASTNode[] nodes = type.getNonTypeOrMethodNode();
		int length = nodes.length;
		for (int i = 0; i < length; i++) {
			processStatement(nodes[i]);
		}
	}

	protected void parseBodies(MethodDeclaration method) {
		List nodes = method.getStatements();
		int length = nodes.size();
		for (int i = 0; i < length; i++) {
			ASTNode node = (ASTNode) nodes.get(i);
			processStatement(node);
		}
	}

	private void processStatement(ASTNode node) {
		if (node == null) {
			return;
		}
	}
}
