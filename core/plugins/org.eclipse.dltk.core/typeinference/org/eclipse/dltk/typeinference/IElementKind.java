package org.eclipse.dltk.typeinference;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.ast.references.VariableKind;

public interface IElementKind {
	
	public static class Model implements IElementKind {
		
	}
	
	public static class Unit implements IElementKind {
		
	}

	public static class Method implements IElementKind {
		
	}
	
	public static abstract class ClassLike implements IElementKind {
		
	}
	
	public static class ClassLikeFragment implements IElementKind {
		
	}
	
	public static class Class extends ClassLike {
		
	}
	
	public static class Mixin extends ClassLike {
		
	}
	
	public static class ClassFragment extends ClassLikeFragment {
		
	}
	
	public static class MixinFragment extends ClassLikeFragment {
		
	}
	
	public static class Variable implements IElementKind {
		
		private final VariableKind kind;

		public Variable(VariableKind kind) {
			this.kind = kind;
		}

		public VariableKind getKind() {
			return kind;
		}
		
		protected static final Map variableKindsToElementKinds = new HashMap();
		
		static {
			variableKindsToElementKinds.put(VariableKind.LOCAL, new Variable(VariableKind.LOCAL));
			variableKindsToElementKinds.put(VariableKind.INSTANCE, new Variable(VariableKind.INSTANCE));
			variableKindsToElementKinds.put(VariableKind.CLASS, new Variable(VariableKind.CLASS));
			variableKindsToElementKinds.put(VariableKind.GLOBAL, new Variable(VariableKind.GLOBAL));
			variableKindsToElementKinds.put(VariableKind.MIXIN, new Variable(VariableKind.MIXIN));
			variableKindsToElementKinds.put(VariableKind.ARGUMENT, new Variable(VariableKind.ARGUMENT));
		}
		
		public static Variable byVariableKind(VariableKind kind) {
			return (Variable) variableKindsToElementKinds.get(kind);
		}
		
	}
	
	public static final IElementKind MODEL = new Method();
	
	public static final IElementKind UNIT = new Method();
	
	public static final IElementKind METHOD = new Method();
	
	public static final IElementKind CLASS = new Class();
	
	public static final IElementKind MIXIN = new Mixin();
	
	public static final IElementKind CLASS_FRAGMENT = new ClassFragment();
	
	public static final IElementKind MIXIN_FRAGMENT = new MixinFragment();
	
	public static final IElementKind LOCAL_VARIABLE = Variable.byVariableKind(VariableKind.INSTANCE);
	
	public static final IElementKind INSTANCE_VARIABLE = Variable.byVariableKind(VariableKind.INSTANCE);
	
	public static final IElementKind CLASS_VARIABLE = Variable.byVariableKind(VariableKind.CLASS);
	
	public static final IElementKind GLOBAL_VARIABLE = Variable.byVariableKind(VariableKind.GLOBAL);
	
	public static final IElementKind MIXIN_VARIABLE = Variable.byVariableKind(VariableKind.MIXIN);
	
	public static final IElementKind ARGUMENT_VARIABLE = Variable.byVariableKind(VariableKind.ARGUMENT);
	
}
