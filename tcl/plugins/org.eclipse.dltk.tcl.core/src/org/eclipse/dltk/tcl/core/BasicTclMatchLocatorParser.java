package org.eclipse.dltk.tcl.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.core.search.matching.MatchLocatorParser;
import org.eclipse.dltk.core.search.matching.PatternLocator;
import org.eclipse.dltk.core.search.matching.PossibleMatch;

public abstract class BasicTclMatchLocatorParser extends MatchLocatorParser {

	protected static String[] kw = TclKeywordsManager.getKeywords();
	protected static Map kwMap = new HashMap();
	static {
		for (int q = 0; q < kw.length; ++q) {
			kwMap.put(kw[q], Boolean.TRUE);
		}
	}

	public BasicTclMatchLocatorParser(MatchLocator locator) {
		super(locator);
	}

	public ModuleDeclaration parse(PossibleMatch possibleMatch) {
		ModuleDeclaration module = super.parse(possibleMatch);		
		module.rebuild();
		module.rebuildMethods();
		
		return module;
	}

	public void parseBodies(ModuleDeclaration unit) {
		TypeDeclaration[] types = unit.getTypes();
		if (types != null) {
			for (int i = 0; i < types.length; i++) {
				TypeDeclaration type = types[i];
				this.getPatternLocator().match(this.processType(type),
						this.getNodeSet());
				this.parseBodies(type);
			}
		}
		MethodDeclaration[] methods = unit.getFunctions();
		if (methods != null) {
			PatternLocator locator = this.getPatternLocator();
			for (int i = 0; i < methods.length; i++) {
				MethodDeclaration method = methods[i];
				locator.match(this.processMethod(method), this
						.getNodeSet());
				this.parseBodies(method);
			}
		}

		ASTNode[] nodes = unit.getNonTypeOrMethodNode();
		int length = nodes.length;
		for (int i = 0; i < length; i++) {
			this.processStatement(nodes[i]);
		}
	}

	protected MethodDeclaration processMethod(MethodDeclaration m) {
		String name = m.getName();
		if (name.startsWith("::")) {
			name = name.substring(2);
		}
		if (name.indexOf("::") != -1) {
			int pos = name.lastIndexOf("::");
			String declTypeName = name.substring(0, pos);
			m.setDeclaringTypeName(declTypeName);
			name = name.substring(pos + 2);
		}
		m.setName(name);
		return m;
	}

	protected TypeDeclaration processType(TypeDeclaration t) {
		String name = t.getName();
		if (name.startsWith("::")) {
			name = name.substring(2);
		}
		if (name.endsWith("::")) {
			name = name.substring(0, name.length() - 2);
		}
		t.setName(name);
		return t;
	}

	protected void parseBodies(TypeDeclaration type) {

		PatternLocator locator = this.getPatternLocator();

		MethodDeclaration[] methods = type.getMethods();
		if (methods != null) {
			for (int i = 0; i < methods.length; i++) {
				MethodDeclaration method = methods[i];
				if (method instanceof MethodDeclaration) {
					MethodDeclaration methodDeclaration = method;
					locator.match(this.processMethod(methodDeclaration), this
							.getNodeSet());
					this.parseBodies(methodDeclaration);
				}
			}
		}

		TypeDeclaration[] memberTypes = type.getTypes();
		if (memberTypes != null) {
			for (int i = 0; i < memberTypes.length; i++) {
				TypeDeclaration memberType = memberTypes[i];
				locator.match(this.processType(memberType), this.getNodeSet());
				this.parseBodies(memberType);
			}
		}
		ASTNode[] nodes = type.getNonTypeOrMethodNode();
		int length = nodes.length;
		for (int i = 0; i < length; i++) {
			this.processStatement(nodes[i]);
		}
	}

	protected void parseBodies(MethodDeclaration method) {
		List nodes = method.getStatements();
		int length = nodes.size();
		for (int i = 0; i < length; i++) {
			ASTNode node = (ASTNode) nodes.get(i);
			this.processStatement(node);
		}
	}

	protected abstract void processStatement(ASTNode node);
}