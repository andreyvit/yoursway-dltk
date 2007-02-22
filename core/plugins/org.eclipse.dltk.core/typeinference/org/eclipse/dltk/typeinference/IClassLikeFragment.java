package org.eclipse.dltk.typeinference;

public interface IClassLikeFragment extends INodeElement {

	public abstract void addMethod(IMethodDescriptor method);

	public abstract void addFragment(IClassLikeFragment fragment);

	public abstract IScope getScope();

	public abstract UserTypeDescriptor getType();

	public abstract IElementKind getKind();

	public abstract String getName();

}