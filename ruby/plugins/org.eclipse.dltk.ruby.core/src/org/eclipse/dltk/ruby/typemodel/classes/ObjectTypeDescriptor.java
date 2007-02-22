package org.eclipse.dltk.ruby.typemodel.classes;

import org.eclipse.dltk.ruby.typeinference.ClassLikeFragment;
import org.eclipse.dltk.typeinference.ArgumentDescriptor;
import org.eclipse.dltk.typeinference.IClassLikeFragment;
import org.eclipse.dltk.typeinference.ITypeModel;
import org.eclipse.dltk.typeinference.IntrinsicMethodDescriptor;
import org.eclipse.dltk.typeinference.UserTypeDescriptor;

public class ObjectTypeDescriptor extends UserTypeDescriptor {
	
	public ObjectTypeDescriptor(ITypeModel modelMaster) {
		super(null, modelMaster, "Object");
		IClassLikeFragment fragment = new ClassLikeFragment(null, this, getScope() /* FIXME should be null */);
		addMethod(new IntrinsicMethodDescriptor(fragment, "freeze", new ArgumentDescriptor[0], this));
	}

}
