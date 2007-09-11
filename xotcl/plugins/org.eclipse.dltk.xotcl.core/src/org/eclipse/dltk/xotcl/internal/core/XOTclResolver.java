package org.eclipse.dltk.xotcl.internal.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceModuleInfoCache;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.ISourceModuleInfoCache.ISourceModuleInfo;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.internal.core.ModelManager;
import org.eclipse.dltk.xotcl.core.TclParseUtil;
import org.eclipse.dltk.xotcl.internal.core.parser.XOTclSourceElementParser;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.TclMixinModel;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.ITclMixinElement;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclClass;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclObject;

public class XOTclResolver {

	public static IMethod resolveSuperMethod(IMethod method) {
		try {
			IModelElement parent = method.getParent();
			if (parent.getElementType() != IModelElement.TYPE) {
				return null;
			}
			IType parentType = (IType) parent;

			List supersToHandle = new ArrayList();
			fillSupers(parentType, supersToHandle);
			while (!supersToHandle.isEmpty()) {
				String superClass = (String) supersToHandle.get(0);
				supersToHandle.remove(0);
				String memberkey = TclParseUtil.getFQNFromModelElement(parent
						.getParent(), IMixinRequestor.MIXIN_NAME_SEPARATOR)
						+ IMixinRequestor.MIXIN_NAME_SEPARATOR
						+ TclParseUtil.tclNameTo(superClass,
								IMixinRequestor.MIXIN_NAME_SEPARATOR);
				if (memberkey.startsWith(IMixinRequestor.MIXIN_NAME_SEPARATOR)) {
					memberkey = memberkey
							.substring(IMixinRequestor.MIXIN_NAME_SEPARATOR
									.length());
				}
				String[] split = superClass.split("::");
				IModelElement[] types = findTypeMixin(memberkey, split[split.length - 1]);
				for (int j = 0; j < types.length; j++) {
					IType type = (IType) types[j];
					IMethod[] methods = type.getMethods();
					for (int i = 0; i < methods.length; i++) {
						if (methods[i].getElementName().equals(
								method.getElementName())) {
							return methods[i];
						}
					}
				}
			}
		} catch (ModelException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private static void fillSupers(IType parentType, List supers) {
		String[] superClasses;
		try {
			superClasses = parentType.getSuperClasses();
			if (superClasses != null) {
				for (int i = 0; i < superClasses.length; i++) {
					supers.add(superClasses[i]);
				}
			}
		} catch (ModelException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
			return;
		}
	}

	public static ModuleDeclaration parseModule(IModelElement method) {
		ISourceModule sourceModule = (ISourceModule) method
				.getAncestor(IModelElement.SOURCE_MODULE);
		ISourceModuleInfoCache sourceModuleInfoCache = ModelManager
				.getModelManager().getSourceModuleInfoCache();
		// sourceModuleInfoCache.remove(this);
		ISourceModuleInfo mifo = sourceModuleInfoCache.get(sourceModule);
		try {
			ModuleDeclaration module = XOTclSourceElementParser.parseModule(
					mifo, sourceModule.getSource().toCharArray(), null,
					sourceModule.getPath().toOSString().toCharArray());
			return module;
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static IModelElement[] findTypeMixin(String pattern, String name) {
		IMixinElement[] find = TclMixinModel.getInstance().find(pattern + "*");
		List elements = new ArrayList();
		for (int i = 0; i < find.length; i++) {
			Object[] allObjects = find[i].getAllObjects();
			for (int j = 0; j < allObjects.length; j++) {
				if (allObjects[j] != null
						&& (allObjects[j] instanceof XOTclClass || allObjects[j] instanceof XOTclObject)) {
					String name2 = null;
					if (allObjects[j] instanceof XOTclClass) {
						XOTclClass field = (XOTclClass) allObjects[j];
						name2 = field.getName();
					} else if (allObjects[j] instanceof XOTclObject) {
						XOTclObject field = (XOTclObject) allObjects[j];
						name2 = field.getName();
					}
					if (name2 != null && name.equals(name2)) {
						// this.selectionElements.add(field.getModelElement());
						elements.add(((ITclMixinElement) allObjects[j])
								.getModelElement());
					}
				}
			}
		}
		return (IModelElement[]) elements.toArray(new IModelElement[elements
				.size()]);
	}
}
