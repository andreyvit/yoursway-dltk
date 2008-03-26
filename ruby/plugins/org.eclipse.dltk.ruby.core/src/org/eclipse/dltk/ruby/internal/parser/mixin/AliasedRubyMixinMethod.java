package org.eclipse.dltk.ruby.internal.parser.mixin;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.internal.core.ModelElement;
import org.eclipse.dltk.ruby.ast.RubyAliasExpression;
import org.eclipse.dltk.ruby.core.model.FakeMethod;

public class AliasedRubyMixinMethod extends RubyMixinMethod {

	private final RubyMixinAlias alias;

	public AliasedRubyMixinMethod(RubyMixinModel model, RubyMixinAlias alias) {
		super(model, alias.getKey());
		this.alias = alias;
		RubyAliasExpression node = alias.getAlias();
		String newName = node.getNewValue();
		int length = node.sourceEnd() - node.sourceStart();
		FakeMethod fakeMethod = new FakeMethod((ModelElement) alias
				.getSourceModule(), newName, node.sourceStart(), length, node
				.sourceStart(), length);
		IMethod[] sourceMethods2 = RubyMixinMethod.getSourceMethods(model,
				alias.getOldKey());
		if (sourceMethods2.length == 1 && sourceMethods2[0] != null) {
			IMethod method = sourceMethods2[0];
			try {
				fakeMethod.setFlags(method.getFlags());
				fakeMethod.setParameters(method.getParameters());
				fakeMethod.setParameterInitializers(method
						.getParameterInitializers());
			} catch (ModelException e) {
			}
		}
		this.setSourceMethods(new IMethod[] { fakeMethod });
	}

	public String getName() {
		return alias.getNewName();
	}

	public RubyMixinVariable[] getVariables() {
		List result = new ArrayList();
		IMixinElement mixinElement = model.getRawModel().get(alias.getOldKey());
		IMixinElement[] children = mixinElement.getChildren();
		for (int i = 0; i < children.length; i++) {
			IRubyMixinElement element = model.createRubyElement(children[i]);
			if (element instanceof RubyMixinVariable)
				result.add(element);
		}
		return (RubyMixinVariable[]) result
				.toArray(new RubyMixinVariable[result.size()]);
	}

}
