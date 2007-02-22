package org.eclipse.dltk.ruby.typeinference.internal;

import org.eclipse.dltk.ruby.typeinference.ClassLikeFragment;
import org.eclipse.dltk.typeinference.ArgumentDescriptor;
import org.eclipse.dltk.typeinference.IClassLikeFragment;
import org.eclipse.dltk.typeinference.IElementKind;
import org.eclipse.dltk.typeinference.ITypeModel;
import org.eclipse.dltk.typeinference.IntrinsicMethodDescriptor;
import org.eclipse.dltk.typeinference.UserTypeDescriptor;

public class NilTypeDescriptor extends UserTypeDescriptor {
	
	public NilTypeDescriptor(ITypeModel modelMaster) {
		super(((RubyTypeModel) modelMaster).getObjectType(), modelMaster, "NilClass");
		
		// XXX remove
		IClassLikeFragment fragment = new ClassLikeFragment(null, this, getScope() /* FIXME should be null */);
		addMethod(new IntrinsicMethodDescriptor(fragment, "this_is_nil", new ArgumentDescriptor[0], this));
	}

}
