package org.eclipse.dltk.javascript.jdt.integration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.javascript.reference.resolvers.IReferenceResolver;
import org.eclipse.dltk.internal.javascript.reference.resolvers.IResolvableReference;
import org.eclipse.dltk.internal.javascript.reference.resolvers.ReferenceResolverContext;
import org.eclipse.dltk.internal.javascript.typeinference.AbstractCallResultReference;
import org.eclipse.dltk.internal.javascript.typeinference.CallResultReference;
import org.eclipse.dltk.internal.javascript.typeinference.IReference;
import org.eclipse.dltk.internal.javascript.typeinference.NewReference;
import org.eclipse.jdt.core.CompletionProposal;
import org.eclipse.jdt.core.CompletionRequestor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.eval.IEvaluationContext;

public class JdtReferenceResolver implements IExecutableExtension,
		IReferenceResolver {
	
	ArrayList imports=new ArrayList();
	public void evaluate(IProject pr) {
		JavaCore.create(pr);
	}

	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {

	}

	public boolean canResolve(ISourceModule module) {
		IProject pr = module.getResource().getProject();
		return JavaCore.create(pr).exists();
	}

	public Set getChilds(IResolvableReference ref
			) {
		if (ref instanceof AbstractCallResultReference) {
			final AbstractCallResultReference cm = (AbstractCallResultReference) ref;
			if (cm instanceof NewReference) {				
				String string = cm.getId() + " z=new " + cm.getId() + ";z.";
				try {
					final HashSet result = new HashSet();
					context.codeComplete(string, string.length(),
							new CompletionRequestor() {
								public void accept(CompletionProposal proposal) {
									IReference r = new JavaProposalReference(context,proposal,
											owner, create,cm.getId());
									result.add(r);
									
								}
							});
					return result;
				} catch (JavaModelException e) {
					return null;
				}
			}
			if (cm instanceof CallResultReference) {
				CallResultReference call = (CallResultReference) cm;
				IReference rm = call.getRoot();
				if (rm != null) {
					int lastIndexOf = call.getId().lastIndexOf('.');
					String substring = call.getId().substring(
							lastIndexOf + 1);
					IReference child = rm.getChild(substring, true);
					if (child == null)
						return null;
					return child.getChilds(true);
				}
			}
		}
		return null;
	}

	public Set resolveGlobals(final String id) {
		
		final HashSet result=new HashSet();
		String sm=id;
		if (id.indexOf('.')==-1)
		{
			sm="import "+sm;
			
			
			try {
				context.codeComplete(sm, sm.length(), new CompletionRequestor(){

					public void accept(CompletionProposal proposal) {
						if (proposal.getName()!=null)
						{
						IReference r = new JavaProposalReference(context,proposal,
								owner, create,"");
						result.add(r);
						}
						else{
							char[] completion = proposal.getCompletion();
							String sm=new String(completion);
							IReference r = new JavaProposalReference(context,sm, proposal,
									owner, create,"");						
							result.add(r);	
						}
					}
					
				});
			} catch (JavaModelException e) {
				
			}
		}
		else{
		try {
			context.codeComplete(sm, sm.length(), new CompletionRequestor(){

				public void accept(CompletionProposal proposal) {
					if (proposal.getName()!=null)
					{
					IReference r = new JavaProposalReference(context,proposal,
							owner, create,"");
					result.add(r);
					}
					else{
						
						char[] completion = proposal.getCompletion();
						String sm=new String(completion);
						String pName=sm;
						if (proposal.getKind()==CompletionProposal.PACKAGE_REF)
						{
						sm=sm.substring(id.length());
						}
						else{
							if (sm.startsWith(id)&&sm.length()!=id.length())sm=sm.substring(id.length()).trim();
							
						}
						IReference r = new JavaProposalReference(context,sm, proposal,
								owner, create,pName);						
						result.add(r);	
					}
				}
				
			});
		} catch (JavaModelException e) {
			
		}
		}
		return result;
	}

	public void processCall(String call, String objId) {
		if (call.equals("importPackage"))imports.add(objId);
	}

	
	ReferenceResolverContext owner;
	IEvaluationContext context;
	IJavaProject create;
	public void init(ReferenceResolverContext owner) {
		ISourceModule module = owner.getModule();
		IProject pr = module.getResource().getProject();
		create = JavaCore.create(pr);
		context= create.newEvaluationContext();
		String[] imps=new String[imports.size()];
		for (int a=0;a<imps.length;a++){
			imps[a]=imports.get(a).toString()+".*";
		}
		context.setImports(imps);
		this.owner=owner;
	}
	


	
}
