package org.eclipse.dltk.xotcl.core.ast.xotcl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Statement;

public class XOTclDocumentationNode extends Statement {
	Map descriptions = new HashMap();

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			visitor.endvisit(this);
		}
	}

	public void putDescription(String key, String content) {
		this.descriptions.put(key, content);
	}

	public void appendDescription(String key, String content) {
		if (this.descriptions.containsKey(key)) {
			this.descriptions.put(key, this.descriptions.get(key) + "\n"
					+ content);
		}
		else {
			this.descriptions.put(key, content);
		}
	}

	public String getDescription(String key) {
		if (this.descriptions.containsKey(key)) {
			return (String) this.descriptions.get(key);
		}
		return null;
	}

	public int getKind() {
		return 0;
	}
}
