package org.eclipse.dltk.ruby.typeinference.fcnm;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.ruby.internal.parser.Activator;
import org.eclipse.dltk.ruby.internal.parser.JRubySourceParser;
import org.eclipse.dltk.ruby.typeinference.ClassLikeFragment;
import org.eclipse.dltk.ruby.typeinference.internal.RubyTypeModel;
import org.eclipse.dltk.typeinference.ASTCaching;
import org.eclipse.dltk.typeinference.IClassLikeFragment;
import org.eclipse.dltk.typeinference.IElementKind;
import org.eclipse.dltk.typeinference.INodeElement;
import org.eclipse.dltk.typeinference.INodeElementInternal;
import org.eclipse.dltk.typeinference.IScope;
import org.eclipse.dltk.typeinference.ITypeModel;
import org.eclipse.dltk.typeinference.IUnit;
import org.eclipse.dltk.typeinference.NodeElement;
import org.eclipse.dltk.typeinference.Stability;

public class Unit extends NodeElement implements IUnit {

	private final ISourceModule sourceModule;
	
	private final ITypeModel model;
	
	private final IClassLikeFragment rootFragment;

	public Unit(ITypeModel model, ISourceModule sourceModule) {
		this.model = model;
		this.sourceModule = sourceModule;
		rootFragment = new ClassLikeFragment(this, ((RubyTypeModel) model).getObjectType(), null);
	}

	public ITypeModel getModel() {
		return model;
	}

	public ISourceModule getSourceModule() {
		return sourceModule;
	}

	protected ASTNode obtainASTNode(ASTCaching caching) {
		JRubySourceParser parser = new JRubySourceParser(null);
		ModuleDeclaration result;
		try {
			result = parser.parse(sourceModule.getSource());
		} catch (ModelException e) {
			Activator.log(e);
			result = null;
		}
		((INodeElementInternal) rootFragment).setASTNode(result);
		return result;
	}

	public Stability getStability() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setStability(Stability stability) {
		// TODO Auto-generated method stub
	}

	public IUnit getUnit() {
		return this;
	}

	public IElementKind getKind() {
		return IElementKind.UNIT;
	}

	protected void synchronizeChildren(ASTNode node, boolean offsetsOnly) {
	}

	public IScope getScope() {
		return rootFragment.getScope();
	}

	public INodeElement getInnermostModelElement(ASTNode node) {
		return rootFragment.getInnermostModelElement(node);
	}
	
}
