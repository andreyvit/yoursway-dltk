package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.ConstantReference;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ruby.ast.ColonExpression;
import org.eclipse.dltk.ruby.typeinference.internal.IContext;
import org.eclipse.dltk.ruby.typemodel.classes.RubyMetaTypeDescriptor;
import org.eclipse.dltk.typeinference.IKnownTypeDescriptor;
import org.eclipse.dltk.typeinference.IScope;
import org.eclipse.dltk.typeinference.ITypeDescriptor;
import org.eclipse.dltk.typeinference.ITypeModel;

public class RubyTypeUtils {

	public static IKnownTypeDescriptor lookupType(IContext context, String typeName) {
		RubyMetaTypeDescriptor objectMetaType = (RubyMetaTypeDescriptor) context.getCurrentScope().lookupConstantType("Object");
		IKnownTypeDescriptor objectType = (IKnownTypeDescriptor) objectMetaType.getInstanceType();
		RubyMetaTypeDescriptor metatype = (RubyMetaTypeDescriptor) objectType.getScope().lookupConstantType(typeName);
		return (IKnownTypeDescriptor) metatype.getInstanceType();
	}
	
	public static IKnownTypeDescriptor lookupType(IScope scope, String typeName) {
		RubyMetaTypeDescriptor objectMetaType = (RubyMetaTypeDescriptor) scope.lookupConstantType("Object");
		IKnownTypeDescriptor objectType = (IKnownTypeDescriptor) objectMetaType.getInstanceType();
		RubyMetaTypeDescriptor metatype = (RubyMetaTypeDescriptor) objectType.getScope().lookupConstantType(typeName);
		return (IKnownTypeDescriptor) metatype.getInstanceType();
	}
	
	public static IKnownTypeDescriptor lookupTypeByFQN (IContext context, String fqnName) {
		String[] names = fqnName.split("::");
		IKnownTypeDescriptor type = lookupType(context, "Object");
		for (int i = 1; i < names.length; i++) {
			 RubyMetaTypeDescriptor constantType = (RubyMetaTypeDescriptor) (type.getScope()).lookupConstantType(names[i]);
			 type = (IKnownTypeDescriptor)constantType.getInstanceType();
			 if (type == null)
				 break;
		}
		return type;
	}
	
	public static void addType(IContext context, IKnownTypeDescriptor type) {
		context.getCurrentScope().setConstantType(type.getName(),
				new RubyMetaTypeDescriptor(context.getModelMaster(), type.getName(), type));
	}
	
	public static void addType(IScope scope, ITypeModel model, IKnownTypeDescriptor type) {
		scope.setConstantType(type.getName(),
				new RubyMetaTypeDescriptor(model, type.getName(), type));
	}

	/**
	 * 
	 * @param context
	 * @param path
	 * @return RubyMetaTypeDescriptor
	 */
	public static IKnownTypeDescriptor getConstant(IContext context, Expression path) {
		if (path instanceof ConstantReference) {
			RubyMetaTypeDescriptor metatype = (RubyMetaTypeDescriptor) context.getCurrentScope()
					.lookupConstantType(((ConstantReference) path).getName());
			if (metatype == null)
				return null;
			//return metatype.getInstanceType();
			return metatype;
		} else if (path instanceof ColonExpression) {
			ColonExpression expression = (ColonExpression) path;
			Expression left = expression.getLeft();
			if (left == null) {
				ITypeDescriptor value;
				if (expression.isFull())
					value = RubyTypeUtils.lookupType(context, expression.getName());
				else
					value = context.getCurrentScope().lookupConstantType(expression.getName());
				if (!(value instanceof IKnownTypeDescriptor))
					return null;
				return (IKnownTypeDescriptor) value;
			}
			IKnownTypeDescriptor leftValue = getConstant(context, left);
			if (leftValue == null)
				return null;
			ITypeDescriptor value = leftValue.getScope().lookupConstantType(expression.getName());
			if (!(value instanceof IKnownTypeDescriptor))
				return null;
			return (IKnownTypeDescriptor) value;
		}
		System.out.println("RubyTypeUtils.getConstant() - unknown node type " + path.getClass().getName());
		return null;
	}

	public static String getMethodNameFromNode(Expression name) {
		if (name instanceof SimpleReference) {
			SimpleReference reference = (SimpleReference) name;
			return reference.getName();
		}
		Assert.isTrue(2 + 2 == 5);
		return null;
	}

}
