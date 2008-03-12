/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.typeinference;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.dltk.evaluation.types.AmbiguousType;
import org.eclipse.dltk.evaluation.types.IClassType;
import org.eclipse.dltk.evaluation.types.UnknownType;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class BuiltinMethods {

	private static final RubyClassType fixnumType = new RubyClassType("Fixnum"); //$NON-NLS-1$
	
	private static final RubyClassType arrayType = new RubyClassType("Array"); //$NON-NLS-1$
	
	private static final RubyClassType strType = new RubyClassType("String"); //$NON-NLS-1$

	private static final RubyClassType classType = new RubyClassType("Class"); //$NON-NLS-1$

	private static final IEvaluatedType boolType = new AmbiguousType(new IEvaluatedType[] {
			new RubyClassType("TrueClass"), //$NON-NLS-1$
			new RubyClassType("FalseClass")}); //$NON-NLS-1$

	private static final class NewMethod implements IntrinsicMethod {
		public boolean dependsOnArguments() {
			return false;
		}

		public boolean dependsOnReceiver() {
			return true;
		}

		public IEvaluatedType getReturnType() {
			return null;
		}

		public IEvaluatedType getReturnType(IEvaluatedType receiver, IEvaluatedType[] arguments) {
			if (receiver instanceof RubyClassType) {
				RubyClassType rubyClassType = (RubyClassType) receiver;
				String key = rubyClassType.getModelKey();
				if (!key.endsWith("%")) //$NON-NLS-1$
					return new RubyClassType(key + "%"); //$NON-NLS-1$
				return null;
			} else if (receiver instanceof AmbiguousType) {
				Set possibleReturns = new HashSet();
				AmbiguousType ambiguousType = (AmbiguousType) receiver;
				IEvaluatedType[] possibleTypes = ambiguousType.getPossibleTypes();
				for (int i = 0; i < possibleTypes.length; i++) {
					IEvaluatedType type = possibleTypes[i];
					IEvaluatedType possibleReturn = getReturnType(type, arguments);
					possibleReturns.add(possibleReturn);
				}
				return RubyTypeInferencingUtils.combineTypes(possibleReturns);
			}
			return null;
		}
	}

	private static final class ClassMethod implements IntrinsicMethod {

		public boolean dependsOnArguments() {
			return false;
		}

		public boolean dependsOnReceiver() {
			return true;
		}

		public IEvaluatedType getReturnType() {
			return classType;
		}

		public IEvaluatedType getReturnType(IEvaluatedType receiver, IEvaluatedType[] arguments) {
			return RubyTypeInferencingUtils.getAmbiguousMetaType(receiver);
		}
	}

	private static final class ReceiverTypeReturningMethod implements IntrinsicMethod {
		public boolean dependsOnArguments() {
			return false;
		}

		public boolean dependsOnReceiver() {
			return true;
		}

		public IEvaluatedType getReturnType() {
			return null;
		}

		public IEvaluatedType getReturnType(IEvaluatedType receiver, IEvaluatedType[] arguments) {
			return receiver;
		}
	}

	public interface IntrinsicMethod {

		boolean dependsOnReceiver();

		boolean dependsOnArguments();

		IEvaluatedType getReturnType();

		IEvaluatedType getReturnType(IEvaluatedType receiver, IEvaluatedType[] arguments);

	}

	private static class SimpleIntrinsicMethod implements IntrinsicMethod {

		private final IEvaluatedType returnType;

		public SimpleIntrinsicMethod(IEvaluatedType returnType) {
			this.returnType = returnType;
		}

		public boolean dependsOnArguments() {
			return false;
		}

		public boolean dependsOnReceiver() {
			return false;
		}

		public IEvaluatedType getReturnType() {
			return returnType;
		}

		public IEvaluatedType getReturnType(IEvaluatedType receiver, IEvaluatedType[] arguments) {
			return getReturnType();
		}

	}

	public static class BuiltinClass {

		private final String name;

		public BuiltinClass(String name) {
			this.name = name;
		}

		private Map methods = new HashMap();

		public void addMethod(String name, IntrinsicMethod method) {
			methods.put(name, method);
		}

		public String getName() {
			return name;
		}

		IntrinsicMethod getMethod(String name) {
			return (IntrinsicMethod) methods.get(name);
		}
		
		public Collection getMethods(){
			return methods.values();
		}

	}

	private static Map builtinClasses = new HashMap();

	private static BuiltinClass addClass(BuiltinClass klass) {
		builtinClasses.put(klass.getName(), klass);
		return klass;
	}

	static {
		BuiltinClass klass;

		klass = addClass(new BuiltinClass("Object")); //$NON-NLS-1$
		klass.addMethod("object_id", new SimpleIntrinsicMethod(fixnumType)); //$NON-NLS-1$
		klass.addMethod("__id__", new SimpleIntrinsicMethod(fixnumType)); //$NON-NLS-1$
		klass.addMethod("send", new SimpleIntrinsicMethod(UnknownType.INSTANCE)); //$NON-NLS-1$
		klass.addMethod("class", new ClassMethod()); //$NON-NLS-1$
		klass.addMethod("clone", new ReceiverTypeReturningMethod()); //$NON-NLS-1$
		klass.addMethod("dup", new ReceiverTypeReturningMethod()); //$NON-NLS-1$
		klass.addMethod("extend", new SimpleIntrinsicMethod(UnknownType.INSTANCE)); //$NON-NLS-1$
		klass.addMethod("freeze", new ReceiverTypeReturningMethod()); //$NON-NLS-1$
		klass.addMethod("frozen?", new SimpleIntrinsicMethod(boolType)); //$NON-NLS-1$
		klass.addMethod("hash", new SimpleIntrinsicMethod(fixnumType)); //$NON-NLS-1$
		klass.addMethod("inspect", new SimpleIntrinsicMethod(strType)); //$NON-NLS-1$
		klass.addMethod("instance_eval", new SimpleIntrinsicMethod(UnknownType.INSTANCE)); //$NON-NLS-1$
		klass.addMethod("instance_of?", new SimpleIntrinsicMethod(boolType)); //$NON-NLS-1$
		klass.addMethod("instance_variable_get", new SimpleIntrinsicMethod(UnknownType.INSTANCE)); //$NON-NLS-1$
		klass.addMethod("instance_variable_set", new SimpleIntrinsicMethod(UnknownType.INSTANCE)); //$NON-NLS-1$
		klass.addMethod("instance_variables", new SimpleIntrinsicMethod(arrayType)); //$NON-NLS-1$
		klass.addMethod("is_a?", new SimpleIntrinsicMethod(boolType)); //$NON-NLS-1$
		klass.addMethod("kind_of?", new SimpleIntrinsicMethod(boolType)); //$NON-NLS-1$
		klass.addMethod("nil?", new SimpleIntrinsicMethod(boolType)); //$NON-NLS-1$
		klass.addMethod("respond_to?", new SimpleIntrinsicMethod(boolType)); //$NON-NLS-1$
		klass.addMethod("to_a", new SimpleIntrinsicMethod(arrayType)); //$NON-NLS-1$
		klass.addMethod("to_s", new SimpleIntrinsicMethod(strType)); //$NON-NLS-1$
		klass.addMethod("to_i", new SimpleIntrinsicMethod(fixnumType)); //$NON-NLS-1$
		
		klass = addClass(new BuiltinClass("Class")); //$NON-NLS-1$
		klass.addMethod("new", new NewMethod()); //$NON-NLS-1$

		klass = addClass(new BuiltinClass("Fixnum")); //$NON-NLS-1$
		klass.addMethod("abs", new SimpleIntrinsicMethod(fixnumType)); //$NON-NLS-1$
	}

	public static IEvaluatedType getIntrinsicMethodReturnType(IEvaluatedType receiver,
			String methodName, IEvaluatedType[] arguments) {
		if (receiver instanceof IClassType)
			return getIntrinsicMethodReturnType((IClassType) receiver, methodName, arguments);
		else if (receiver instanceof AmbiguousType) {
			Set possibleReturns = new HashSet();
			AmbiguousType ambiguousType = (AmbiguousType) receiver;
			IEvaluatedType[] possibleTypes = ambiguousType.getPossibleTypes();
			for (int i = 0; i < possibleTypes.length; i++) {
				IEvaluatedType type = possibleTypes[i];
				IEvaluatedType possibleReturn = getIntrinsicMethodReturnType(type, methodName,
						arguments);
				possibleReturns.add(possibleReturn);
			}
			return RubyTypeInferencingUtils.combineTypes(possibleReturns);
		}
		return null;
	}

	public static IEvaluatedType getIntrinsicMethodReturnType(IClassType receiver,
			String methodName, IEvaluatedType[] arguments) {
		String className = getPossibleIntrinsicClassName(receiver);
		BuiltinClass klass = (BuiltinClass) builtinClasses.get(className);
		if (klass != null) {
			IntrinsicMethod method = klass.getMethod(methodName);
			if (method != null) {
				return method.getReturnType(receiver, arguments);
			}
		}
		return null;
	}
	
	public static Collection getIntrinsicMethods(IEvaluatedType receiver) {
		if (receiver instanceof IClassType) {
			String className = getPossibleIntrinsicClassName((IClassType) receiver);
			BuiltinClass klass = (BuiltinClass) builtinClasses.get(className);
			if (klass != null)
				return klass.getMethods();
			else
				return null;
		} else if (receiver instanceof AmbiguousType) {
			Set methods = new HashSet();
			AmbiguousType ambiguousType = (AmbiguousType) receiver;
			IEvaluatedType[] possibleTypes = ambiguousType.getPossibleTypes();
			for (int i = 0; i < possibleTypes.length; i++) {
				IEvaluatedType type = possibleTypes[i];
				Collection typeMethods = getIntrinsicMethods(type);
				methods.addAll(typeMethods);
			}
			return methods;
		}
		return null;		
	}

	private static String getPossibleIntrinsicClassName(IClassType receiver) {
		/*if (receiver instanceof RubyMetaClassType) {
			return "Class"; //$NON-NLS-1$
		} else*/ if (receiver instanceof RubyClassType) {
//			RubyClassType type = (RubyClassType) receiver;						
//			return RubyMixinModel.getInstance().createRubyClass(type).getName();
			return "Class"; //$NON-NLS-1$
		} /*else if (receiver instanceof SimpleType) {
			SimpleType type = (SimpleType) receiver;
			switch (type.getType()) {
			case SimpleType.TYPE_NUMBER:
				return "Fixnum"; //$NON-NLS-1$
			case SimpleType.TYPE_STRING:
				return "Str"; //$NON-NLS-1$
			}
		}*/
		return null;
	}
	
	

}
