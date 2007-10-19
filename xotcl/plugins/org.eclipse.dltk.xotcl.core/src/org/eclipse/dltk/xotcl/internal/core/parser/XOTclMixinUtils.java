package org.eclipse.dltk.xotcl.internal.core.parser;

import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.tcl.internal.core.search.mixin.TclMixinModel;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclClass;

public class XOTclMixinUtils {
	public static XOTclClass findMixinElement(String commandNameValue) {
		IMixinElement[] find = TclMixinModel.getInstance().find(
				commandNameValue.replaceAll("::",
						IMixinRequestor.MIXIN_NAME_SEPARATOR), 100);
		if (find.length > 0) {
			for (int i = 0; i < find.length; i++) {
				Object[] allObjects = find[i].getAllObjects();
				for (int j = 0; j < allObjects.length; j++) {
					if (allObjects[j] != null
							&& allObjects[j] instanceof XOTclClass) {
						XOTclClass class_ = (XOTclClass) allObjects[j];
						return class_;
					}
				}
			}
		}
		return null;
	}

}
