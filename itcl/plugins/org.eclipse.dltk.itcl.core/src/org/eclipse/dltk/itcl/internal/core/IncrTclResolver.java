package org.eclipse.dltk.itcl.internal.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.itcl.internal.core.search.mixin.model.IncrTclClass;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.internal.core.search.mixin.TclMixinModel;
import org.eclipse.dltk.tcl.internal.core.search.mixin.model.ITclMixinElement;

public class IncrTclResolver {

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
				IModelElement[] types = findTypeMixin(memberkey,
						split[split.length - 1]);
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

		ModuleDeclaration module = SourceParserUtil.getModuleDeclaration(
				sourceModule, null);
		return module;

	}

	public static IModelElement[] findTypeMixin(String pattern, String name) {
		IMixinElement[] find = TclMixinModel.getInstance().find(pattern + "*");
		List elements = new ArrayList();
		for (int i = 0; i < find.length; i++) {
			Object[] allObjects = find[i].getAllObjects();
			for (int j = 0; j < allObjects.length; j++) {
				if (allObjects[j] != null
						&& (allObjects[j] instanceof IncrTclClass)) {
					String name2 = null;
					if (allObjects[j] instanceof IncrTclClass) {
						IncrTclClass field = (IncrTclClass) allObjects[j];
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
