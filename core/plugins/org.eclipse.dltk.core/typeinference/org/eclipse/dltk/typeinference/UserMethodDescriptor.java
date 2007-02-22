package org.eclipse.dltk.typeinference;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;

public class UserMethodDescriptor extends ContainedNodeElement implements IDependentTypedElement, IMethodDescriptor {

	private final MethodDeclaration node;

	private final Collection incomingCalls = new HashSet();

	private final Collection outgoingCalls = new HashSet();

	private final UserTypeDescriptor enclosingType;

	private ITypeDescriptor returnType = AnyTypeDescriptor.INSTANCE;

	private MethodScope scope;

	private ArgumentDescriptor[] oldArguments;
	
	private ArgumentDescriptor[] arguments;
	
	private Map argumentsByName = new HashMap();

	private final IClassLikeFragment enclosingClassFragment;

	public UserMethodDescriptor(MethodDeclaration node, IClassLikeFragment enclosingClassFragment) {
		super(enclosingClassFragment);
		this.node = node;
		this.enclosingClassFragment = enclosingClassFragment;
		this.enclosingType = enclosingClassFragment.getType();
		enclosingType.addMethod(this);
		createScope(enclosingClassFragment.getScope());
	}

	public ITypeModel getModel() {
		return enclosingType.getModel();
	}
	
	private void createScope(IScope parentScope) {
		scope = new MethodScope(parentScope, this);
		ArgumentDescriptor[] arguments = getArguments();
		for (int i = 0; i < arguments.length; i++) {
			ArgumentDescriptor argument = arguments[i];
			scope.setVariable(argument.getName(), new ArgumentVariableDescriptor(this, scope, argument));
		}
	}

	public IScope getScope() {
		return scope;
	}

	public MethodDeclaration getNode() {
		return node;
	}

	public ITypeDescriptor getReturnType() {
		return returnType;
	}

	public void setReturnType(ITypeDescriptor returnType) {
		if (returnType.equals(this.returnType))
			return;
		this.returnType = returnType;
		for (Iterator iter = incomingCalls.iterator(); iter.hasNext();) {
			CallDescriptor call = (CallDescriptor) iter.next();
			getModel().invalidateMethodTypeInfo(call.getCaller());
		}
	}

	public String getName() {
		return node.getName();
	}

	public void addIncomingCall(CallDescriptor call) {
		if (incomingCalls.add(call)) {
			getModel().invalidateMethodTypeInfo(this);
			IMethodDescriptor caller = call.getCaller();
			if (caller instanceof UserMethodDescriptor)
				((UserMethodDescriptor) caller).$addOutgoingCall(call);
		}
	}

	public void reset() {
		for (Iterator iter = outgoingCalls.iterator(); iter.hasNext();) {
			CallDescriptor call = (CallDescriptor) iter.next();
			IMethodDescriptor callee = call.getCallee();
			if (callee instanceof UserMethodDescriptor)
				((UserMethodDescriptor) callee).$removeIncomingCall(call);
		}
		createScope(scope.getParentScope());
	}

	private void $addOutgoingCall(CallDescriptor call) {
		outgoingCalls.add(call);
	}

	private void $removeIncomingCall(CallDescriptor call) {
		incomingCalls.remove(call);
		setArguments(null);
	}

	public Collection getIncomingCalls() {
		return incomingCalls;
	}

	public IKnownTypeDescriptor getEnclosingType() {
		return enclosingType;
	}

	public void needArguments() {
		ArgumentDescriptor[] newArguments = calculateArgumentTypes();
		setArguments(newArguments);
		oldArguments = newArguments;
	}

	private ArgumentDescriptor[] calculateArgumentTypes() {
		List argDefs = getNode().getArguments();
		int namedArgs = argDefs.size();
		int maxArgs = namedArgs;
		String[] argNames = new String[maxArgs];
		int index = 0;
		for (Iterator iter = argDefs.iterator(); iter.hasNext();) {
			Argument arg = (Argument) iter.next();
			argNames[index++] = arg.getName();
		}

		for (Iterator iter = incomingCalls.iterator(); iter.hasNext();) {
			CallDescriptor call = (CallDescriptor) iter.next();
			maxArgs = Math.max(maxArgs, call.getArguments().length);
		}

		Set[] possibleTypes = new HashSet[maxArgs];
		for (int i = 0; i < maxArgs; i++)
			possibleTypes[i] = new HashSet();
		for (Iterator iter = incomingCalls.iterator(); iter.hasNext();) {
			CallDescriptor call = (CallDescriptor) iter.next();
			ITypeDescriptor[] arguments = call.getArguments();
			for (int i = 0; i < arguments.length; i++) {
				ITypeDescriptor type = arguments[i];
				possibleTypes[i].add(type);
			}
		}

		ArgumentDescriptor[] arguments = new ArgumentDescriptor[maxArgs];
		ITypeModel master = enclosingType.getModel();
		for (int i = 0; i < maxArgs; i++) {
			String name = (i < argNames.length ? argNames[i] : "arg" + (i + 1));
			ITypeDescriptor type = master
					.makeTypeDescriptorFromListOfTypeDescriptors(possibleTypes[i]);
			if (oldArguments != null && i < namedArgs) {
				arguments[i] = oldArguments[i];
				arguments[i].setType(type);
			} else
				arguments[i] = new ArgumentDescriptor(this, name, type);
		}

		return arguments;
	}

	public ASTNode getNodeForReparsing() {
		return node;
	}

	public ITypeDescriptor getCalculatedType() {
		return returnType;
	}

	protected void synchronizeChildren(ASTNode node, boolean offsetsOnly) {
		// no children, dude!
	}

	public ArgumentDescriptor[] getArguments() {
		if (arguments == null)
			needArguments();
		return arguments;
	}

	public ArgumentDescriptor getArgumentByName(String name) {
		if (arguments == null)
			needArguments();
		return (ArgumentDescriptor) argumentsByName.get(name);
	}

	public void setArguments(ArgumentDescriptor[] arguments) {
		this.arguments = arguments;
		argumentsByName.clear();
		if (arguments != null) 
			for (int i = 0; i < arguments.length; i++) {
				arguments[i].$setParent(this, i);
				argumentsByName.put(arguments[i].getName(), arguments[i]);
			}
	}

	public IElementKind getKind() {
		return IElementKind.METHOD;
	}

	public IClassLikeFragment getEnclosingClassFragment() {
		return enclosingClassFragment;
	}

}
