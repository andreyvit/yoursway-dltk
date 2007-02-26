package org.eclipse.dltk.ruby.typeinference;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.dltk.evaluation.types.AmbiguousType;
import org.eclipse.dltk.evaluation.types.IClassType;
import org.eclipse.dltk.evaluation.types.IEvaluatedType;
import org.eclipse.dltk.evaluation.types.SimpleType;
import org.eclipse.dltk.evaluation.types.UnknownType;

public class BuiltinMethods {

	private static final RubyClassType fixnumType = new RubyClassType(new String[] { "Fixnum" },
			null, null);
	
	private static final RubyClassType arrayType = new RubyClassType(new String[] { "Array" },
			null, null);
	
	private static final RubyClassType strType = new RubyClassType(new String[] { "Str" },
			null, null);

	private static final RubyClassType classType = new RubyClassType(new String[] { "Class" },
			null, null);

	private static final IEvaluatedType boolType = new AmbiguousType(new IEvaluatedType[] {
			new RubyClassType(new String[] { "TrueClass" }, null, null),
			new RubyClassType(new String[] { "FalseClass" }, null, null) });

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
			if (receiver instanceof RubyMetaClassType) {
				RubyMetaClassType metaClass = (RubyMetaClassType) receiver;
				return metaClass.getInstanceType();
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

		klass = addClass(new BuiltinClass("Object"));
		klass.addMethod("object_id", new SimpleIntrinsicMethod(fixnumType));
		klass.addMethod("__id__", new SimpleIntrinsicMethod(fixnumType));
		klass.addMethod("send", new SimpleIntrinsicMethod(UnknownType.INSTANCE));
		klass.addMethod("class", new ClassMethod());
		klass.addMethod("clone", new ReceiverTypeReturningMethod());
		klass.addMethod("dup", new ReceiverTypeReturningMethod());
		klass.addMethod("extend", new SimpleIntrinsicMethod(UnknownType.INSTANCE));
		klass.addMethod("freeze", new ReceiverTypeReturningMethod());
		klass.addMethod("frozen?", new SimpleIntrinsicMethod(boolType));
		klass.addMethod("hash", new SimpleIntrinsicMethod(fixnumType));
		klass.addMethod("inspect", new SimpleIntrinsicMethod(strType));
		klass.addMethod("instance_eval", new SimpleIntrinsicMethod(UnknownType.INSTANCE));
		klass.addMethod("instance_of?", new SimpleIntrinsicMethod(boolType));
		klass.addMethod("instance_variable_get", new SimpleIntrinsicMethod(UnknownType.INSTANCE));
		klass.addMethod("instance_variable_set", new SimpleIntrinsicMethod(UnknownType.INSTANCE));
		klass.addMethod("instance_variables", new SimpleIntrinsicMethod(arrayType));
		klass.addMethod("is_a?", new SimpleIntrinsicMethod(boolType));
		klass.addMethod("kind_of?", new SimpleIntrinsicMethod(boolType));
		klass.addMethod("nil?", new SimpleIntrinsicMethod(boolType));
		klass.addMethod("respond_to?", new SimpleIntrinsicMethod(boolType));
		klass.addMethod("to_a", new SimpleIntrinsicMethod(arrayType));
		klass.addMethod("to_s", new SimpleIntrinsicMethod(strType));
		klass.addMethod("to_i", new SimpleIntrinsicMethod(fixnumType));
		
		klass = addClass(new BuiltinClass("Class"));
		klass.addMethod("new", new NewMethod());

		klass = addClass(new BuiltinClass("Fixnum"));
		klass.addMethod("abs", new SimpleIntrinsicMethod(fixnumType));
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
		if (receiver instanceof RubyMetaClassType) {
			return "Class";
		} else if (receiver instanceof RubyClassType) {
			RubyClassType type = (RubyClassType) receiver;
			return type.getUnqualifiedName();
		} else if (receiver instanceof SimpleType) {
			SimpleType type = (SimpleType) receiver;
			switch (type.getType()) {
			case SimpleType.TYPE_NUMBER:
				return "Fixnum";
			case SimpleType.TYPE_STRING:
				return "Str";
			}
		}
		return null;
	}
	
	

}
