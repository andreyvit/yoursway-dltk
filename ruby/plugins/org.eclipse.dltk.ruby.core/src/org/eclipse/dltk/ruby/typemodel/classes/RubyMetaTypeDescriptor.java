package org.eclipse.dltk.ruby.typemodel.classes;

import org.eclipse.dltk.ruby.typeinference.ClassLikeFragment;
import org.eclipse.dltk.typeinference.ArgumentDescriptor;
import org.eclipse.dltk.typeinference.IClassLikeFragment;
import org.eclipse.dltk.typeinference.IKnownTypeDescriptor;
import org.eclipse.dltk.typeinference.ITypeModel;
import org.eclipse.dltk.typeinference.IntrinsicMethodDescriptor;
import org.eclipse.dltk.typeinference.UserTypeDescriptor;

public class RubyMetaTypeDescriptor extends UserTypeDescriptor {

	private final IKnownTypeDescriptor instanceType;

	public RubyMetaTypeDescriptor(ITypeModel modelMaster, String name, IKnownTypeDescriptor instanceTypeArg) {
		super(null /* FIXME */, modelMaster, name);
		this.instanceType = instanceTypeArg;
		IClassLikeFragment fragment = new ClassLikeFragment(null, this, getScope() /* FIXME should be null */);
		addMethod(new IntrinsicMethodDescriptor(fragment, "new", new ArgumentDescriptor[0], instanceType));
	}

	public IKnownTypeDescriptor getInstanceType() {
		return instanceType;
	}

}
