package org.eclipse.dltk.ruby.internal.parser.visitors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.BooleanLiteral;
import org.eclipse.dltk.ast.expressions.CallArgumentsList;
import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ruby.ast.RubyCallArgument;
import org.eclipse.dltk.ruby.ast.RubySymbolReference;

public class RubyAttributeHandler {
	private static final String ATTR = "attr"; //$NON-NLS-1$
	private static final String ATTR_ACCESSOR = "attr_accessor"; //$NON-NLS-1$
	private static final String ATTR_WRITER = "attr_writer"; //$NON-NLS-1$
	private static final String ATTR_READER = "attr_reader"; //$NON-NLS-1$
    private static final String ATTR_INTERNAL = "attr_internal"; //$NON-NLS-1$
    private static final String ATTR_INTERNAL_ACCESSOR = "attr_internal_accessor"; //$NON-NLS-1$
    private static final String ATTR_INTERNAL_READER = "attr_internal_reader"; //$NON-NLS-1$
    private static final String ATTR_INTERNAL_WRITER = "attr_internal_writer"; //$NON-NLS-1$
    private static final String CATTR_ACCESSOR = "cattr_accessor"; //$NON-NLS-1$
    private static final String CATTR_WRITER = "cattr_writer"; //$NON-NLS-1$
    private static final String CATTR_READER = "cattr_reader"; //$NON-NLS-1$

	private final CallExpression call;
	private List readers;
	private List writers;

	public RubyAttributeHandler(CallExpression call) {
		super();
		if (!isAttributeCreationCall(call)) {
			throw new IllegalArgumentException();
		}
		this.call = call;
		readers = new ArrayList();
		writers = new ArrayList();
		init();
	}

	private void init() {
		String name = call.getName();
		CallArgumentsList list = call.getArgs();
		List expr = list.getChilds();
		Iterator it = expr.iterator();
		boolean create_reader = false;
		boolean create_writer = false;
		if (name.equals(ATTR_READER) || name.equals(ATTR_INTERNAL_READER) || name.equals(CATTR_READER)) {
			create_reader = true;
		} else if (name.equals(ATTR_WRITER) || name.equals(ATTR_INTERNAL_WRITER) || name.equals(CATTR_WRITER)) {
			create_writer = true;
		} else if (name.equals(ATTR_ACCESSOR) || name.equals(ATTR_INTERNAL) || name.equals(ATTR_INTERNAL_ACCESSOR) || name.equals(CATTR_ACCESSOR)) {
			create_reader = true;
			create_writer = true;
		} else if (name.equals(ATTR)) {
			create_reader = true;
			if (expr.size() > 0) {
				ASTNode node = (ASTNode) expr.get(expr.size() - 1);
				if (node instanceof RubyCallArgument) {
					node = ((RubyCallArgument) node).getValue();
				}
				if (node instanceof BooleanLiteral) {
					BooleanLiteral lit = (BooleanLiteral) node;
					create_writer = lit.boolValue();
				}
			}

		}
		while (it.hasNext()) {
			ASTNode sr = (ASTNode) it.next();
			if (!(sr instanceof RubyCallArgument)) {
				continue;
			}
			sr = ((RubyCallArgument) sr).getValue();
			String attr = getText(sr);			
			if (attr == null) {
				continue;
			}
			if (create_reader) {
				readers.add(sr);
			}
			if (create_writer) {
				writers.add(sr);
			}
		}
	}

	public List getReaders() {
		return readers;
	}

	public List getWriters() {
		return writers;
	}

	public static boolean isAttributeCreationCall(CallExpression c) {
		if (c.getReceiver() != null)
			return false;
		String name = c.getName();
		return name.equals(ATTR_READER) || name.equals(ATTR_WRITER)
				|| name.equals(ATTR_ACCESSOR) || name.equals(ATTR) ||
				name.equals(ATTR_INTERNAL) || name.equals(ATTR_INTERNAL_ACCESSOR) ||
				name.equals(ATTR_INTERNAL_READER) || name.equals(ATTR_INTERNAL_WRITER) ||
				name.equals(CATTR_ACCESSOR) || name.equals(CATTR_READER) || name.equals(CATTR_WRITER);
	}

	public static String getText(ASTNode sr) {
		if (sr == null)
			return null;
		String attr = null;
		if (sr instanceof RubySymbolReference) {
			attr = ((RubySymbolReference) sr).getName();
		} else if (sr instanceof StringLiteral) {
			attr = ((StringLiteral) sr).getValue();
		}
		return attr;
	}
	
}
