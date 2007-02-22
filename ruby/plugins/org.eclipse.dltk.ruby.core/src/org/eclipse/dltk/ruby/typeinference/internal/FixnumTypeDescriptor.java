package org.eclipse.dltk.ruby.typeinference.internal;

import org.eclipse.dltk.ruby.typeinference.ClassLikeFragment;
import org.eclipse.dltk.typeinference.ArgumentDescriptor;
import org.eclipse.dltk.typeinference.IClassLikeFragment;
import org.eclipse.dltk.typeinference.ITypeModel;
import org.eclipse.dltk.typeinference.IntrinsicMethodDescriptor;
import org.eclipse.dltk.typeinference.UserTypeDescriptor;

public class FixnumTypeDescriptor extends UserTypeDescriptor {
	
	public FixnumTypeDescriptor(ITypeModel modelMaster) {
		super(((RubyTypeModel) modelMaster).getObjectType(), modelMaster, "Fixnum");
		IClassLikeFragment fragment = new ClassLikeFragment(null, this, getScope() /* FIXME should be null */);
		addMethod(new IntrinsicMethodDescriptor(fragment, "ceil", new ArgumentDescriptor[0], this));
	}

}
