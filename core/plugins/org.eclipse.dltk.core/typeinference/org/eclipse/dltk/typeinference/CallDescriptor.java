package org.eclipse.dltk.typeinference;

public class CallDescriptor {
	
	private final IMethodDescriptor caller;
	
	private final IMethodDescriptor callee;
	
	private ITypeDescriptor[] arguments;

	public CallDescriptor(IMethodDescriptor caller, IMethodDescriptor callee, ITypeDescriptor[] arguments) {
		this.caller = caller;
		this.callee = callee;
		this.arguments = arguments;
	}

	public ITypeDescriptor[] getArguments() {
		return arguments;
	}

	public IMethodDescriptor getCaller() {
		return caller;
	}

	public IMethodDescriptor getCallee() {
		return callee;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof CallDescriptor))
			return false;
		CallDescriptor peer = (CallDescriptor) obj;
		// TODO: if arrays are not compared structurely by equals, do this comparison by hands
		return caller.equals(peer.caller) && callee.equals(peer.callee) && arguments.equals(peer.arguments);
	}

	public int hashCode() {
		return caller.hashCode() ^ callee.hashCode() ^ arguments.hashCode();
	}
	
	
	
}
