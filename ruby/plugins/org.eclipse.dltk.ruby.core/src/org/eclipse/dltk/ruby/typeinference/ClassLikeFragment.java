package org.eclipse.dltk.ruby.typeinference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ruby.typeinference.internal.RubyTypeModel;
import org.eclipse.dltk.ruby.typemodel.classes.RubyMetaTypeDescriptor;
import org.eclipse.dltk.typeinference.ContainedNodeElement;
import org.eclipse.dltk.typeinference.FragmentedScope;
import org.eclipse.dltk.typeinference.IClassLikeFragment;
import org.eclipse.dltk.typeinference.IContainedNodeElement;
import org.eclipse.dltk.typeinference.IElementKind;
import org.eclipse.dltk.typeinference.IMethodDescriptor;
import org.eclipse.dltk.typeinference.INodeElement;
import org.eclipse.dltk.typeinference.INodeElementInternal;
import org.eclipse.dltk.typeinference.IScope;
import org.eclipse.dltk.typeinference.ITypeDescriptor;
import org.eclipse.dltk.typeinference.Scope;
import org.eclipse.dltk.typeinference.StaticScopeAddition;
import org.eclipse.dltk.typeinference.UserMethodDescriptor;
import org.eclipse.dltk.typeinference.UserTypeDescriptor;

public class ClassLikeFragment extends ContainedNodeElement implements IClassLikeFragment {

	private final UserTypeDescriptor type;
	private IScope scope;
	private final Map methods = new HashMap();
	private final Map fragments = new HashMap();

	public ClassLikeFragment(INodeElement enclosingElement, UserTypeDescriptor type, IScope enclosingStaticScope) {
		super(enclosingElement);
		this.type = type;
		Scope myScope = new Scope(type.getScope(), type, this, null);
		((FragmentedScope) type.getScope()).addFragment(myScope);
		if (enclosingStaticScope == null)
			this.scope = myScope;
		else
			this.scope = new StaticScopeAddition(myScope, enclosingStaticScope);
	}
	
	public void addMethod(IMethodDescriptor method) {
		methods.put(method.getName(), methods);
	}
	
	public void addFragment(IClassLikeFragment fragment) {
		fragments.put(fragment.getType().getName(), fragment);
	}
	
	public IScope getScope() {
		return scope;
	}

	public UserTypeDescriptor getType() {
		return type;
	}

	protected void synchronizeChildren(final ASTNode node, boolean offsetsOnly) {
		final Collection presentMethods = new HashSet();
		final Collection addedMethods = new ArrayList();
		final Collection presentFragments = new HashSet();
		final Collection addedFragments = new ArrayList();
		ASTVisitor visitor = new ASTVisitor() {

			public boolean visit(MethodDeclaration s) throws Exception {
				// XXX use signature
				String methodName = s.getName();
				IMethodDescriptor method = (IMethodDescriptor) methods.get(methodName);
				if (method != null) {
					((INodeElementInternal) method).setASTNode(s);
					presentMethods.add(method);
				} else {
					method = new UserMethodDescriptor(s, ClassLikeFragment.this);
					((INodeElementInternal) method).setASTNode(s);
					addedMethods.add(method);
				}
				return false;
			}

			public boolean visit(TypeDeclaration s) throws Exception {
				if (s == node)
					return true;
				String className = s.getName();
				ClassLikeFragment fragment = (ClassLikeFragment) fragments.get(className);
				if (fragment != null) {
					((INodeElementInternal) fragment).setASTNode(s);
					presentFragments.add(fragment);
				} else {
					ITypeDescriptor descriptor = getScope().lookupConstantType(className);
					UserTypeDescriptor type = null;
					if (descriptor instanceof RubyMetaTypeDescriptor)
						type = (UserTypeDescriptor) ((RubyMetaTypeDescriptor) descriptor).getInstanceType();
					else {
						type = new UserTypeDescriptor(((RubyTypeModel) getModel()).getObjectType(),
								getModel(), className);
						RubyTypeUtils.addType(getScope(), getModel(), type);
					}
					fragment = new ClassLikeFragment(ClassLikeFragment.this, type, getScope());
					fragment.setASTNode(s);
					addedFragments.add(fragment);
				}
				return false;
			}
			
		};
		try {
			node.traverse(visitor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
		Set currentMethods = new HashSet(methods.values());
	 	currentMethods.removeAll(presentMethods);
	 	for (Iterator iter = currentMethods.iterator(); iter.hasNext();) {
			IMethodDescriptor method = (IMethodDescriptor) iter.next();
			// TODO: notify about method removal
			System.out.println("Method removed: " + method.getName());
			type.removeMethod(method);
			methods.remove(method.getName());
		}
	 	for (Iterator iter = addedMethods.iterator(); iter.hasNext();) {
			IMethodDescriptor method = (IMethodDescriptor) iter.next();
			// TODO: notify about method addition
			System.out.println("Method added: " + method.getName());
			type.addMethod(method);
			methods.put(method.getName(), method);
		}
	 	Set currentFragments = new HashSet(fragments.values());
	 	currentFragments.removeAll(presentFragments);
	 	for (Iterator iter = currentFragments.iterator(); iter.hasNext();) {
	 		IClassLikeFragment fragment = (IClassLikeFragment) iter.next();
	 		// TODO: notify about method removal
	 		System.out.println("Class fragment removed: " + fragment.getName());
	 		fragments.remove(fragment.getName());
	 		fragment.getType().removeFragment(fragment);
	 	}
	 	for (Iterator iter = addedFragments.iterator(); iter.hasNext();) {
	 		IClassLikeFragment fragment = (IClassLikeFragment) iter.next();
	 		// TODO: notify about method addition
	 		System.out.println("Class fragment added: " + fragment.getName());
	 		fragments.put(fragment.getName(), fragment);
	 		fragment.getType().addFragment(fragment);
	 	}
	}

	public IElementKind getKind() {
		return IElementKind.CLASS_FRAGMENT;
	}

	public String getName() {
		return type.getName();
	}

	public INodeElement getInnermostModelElement(ASTNode node) {
		int res = compareOffset(node.sourceStart(), node.sourceEnd());
		if (res == LOCATED_EQUAL)
			return this;
		if (res == LOCATED_INSIDE) {
			for (Iterator iter = methods.values().iterator(); iter.hasNext();) {
				IContainedNodeElement element = (IContainedNodeElement) iter.next();
				INodeElement result = element.getInnermostModelElement(node);
				if (result != null)
					return result;
			}
			for (Iterator iter = fragments.values().iterator(); iter.hasNext();) {
				IContainedNodeElement element = (IContainedNodeElement) iter.next();
				INodeElement result = element.getInnermostModelElement(node);
				if (result != null)
					return result;
			}
			return this;
		}
		return null;
	}

}
