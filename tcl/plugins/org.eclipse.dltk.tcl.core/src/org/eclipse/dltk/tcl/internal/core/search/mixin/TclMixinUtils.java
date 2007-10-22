package org.eclipse.dltk.tcl.internal.core.search.mixin;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.tcl.internal.core.search.mixin.model.ITclMixinElement;

public class TclMixinUtils {
	private static final boolean TRACE_COMPLETION_TIME = false;

	public static IModelElement[] findModelElementsFromMixin(String pattern,
			Class mixinClass) {
		long delta = 200;
		long time = System.currentTimeMillis();
		List elements = new ArrayList();
		IMixinElement[] find = TclMixinModel.getInstance().find(pattern, delta);
		if (TRACE_COMPLETION_TIME) {
			System.out.println("findMethod from mixin: request model:"
					+ Long.toString(System.currentTimeMillis() - time));
		}
		time = System.currentTimeMillis();
		for (int i = 0; i < find.length; i++) {
			Object[] allObjects = find[i].getAllObjects();
			for (int j = 0; j < allObjects.length; j++) {
				if (allObjects[j] != null
						&& mixinClass.isInstance(allObjects[j])) {
					ITclMixinElement field = (ITclMixinElement) allObjects[j];
					IModelElement element = field.getModelElement();
					if (element != null) {
						elements.add(element);
					}
				}
			}
		}
		return (IModelElement[]) elements.toArray(new IModelElement[elements
				.size()]);
	}
}
