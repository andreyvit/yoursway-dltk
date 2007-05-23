package org.eclipse.dltk.ruby.internal.parser.mixin;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.MixinModel;
import org.eclipse.dltk.ruby.ast.RubyAliasExpression;

public class RubyMixinAlias implements IRubyMixinElement {

	private final IMixinElement element;
	private final RubyMixinModel model;
	private final RubyAliasExpression alias;
	private final ISourceModule sourceModule;

	public RubyMixinAlias(RubyMixinModel model, String key) {
		this.model = model;
		element = model.getRawModel().get(key);
		Assert.isNotNull(element);
		ISourceModule[] sourceModules = element.getSourceModules();
		ISourceModule sourceModule2 = null;
		RubyAliasExpression alias2 = null;
		for (int j = 0; j < sourceModules.length; j++) {
			ISourceModule module = sourceModules[j];
			Object[] objects = element.getObjects(module);
			for (int i = 0; i < objects.length; i++) {
				RubyMixinElementInfo info = (RubyMixinElementInfo) objects[i];
				if (info.getKind() == RubyMixinElementInfo.K_ALIAS) {
					alias2 = (RubyAliasExpression) info.getObject();
					sourceModule2 = module;
					break;
				}
			}
		}
		alias = alias2;
		sourceModule = sourceModule2;
		Assert.isNotNull(alias);
	}

	public String getOldKey() {
		String old = alias.getOldValue();
		return element.getParent().getKey() + MixinModel.SEPARATOR + old;
	}

	public IRubyMixinElement getOldElement() {
		return model.createRubyElement(getOldKey());
	}

	public String getKey() {
		return element.getKey();
	}

	public String getNewName() {
		return alias.getNewValue();
	}

	public RubyAliasExpression getAlias() {
		return alias;
	}

	public ISourceModule getSourceModule() {
		return sourceModule;
	}

}
