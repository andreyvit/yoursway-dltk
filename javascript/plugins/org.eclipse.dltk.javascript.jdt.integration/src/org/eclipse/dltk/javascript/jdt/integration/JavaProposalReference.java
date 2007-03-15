package org.eclipse.dltk.javascript.jdt.integration;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.dltk.internal.core.ModelElement;
import org.eclipse.dltk.internal.javascript.reference.resolvers.ReferenceResolverContext;
import org.eclipse.dltk.internal.javascript.reference.resolvers.SelfCompletingReference;
import org.eclipse.dltk.internal.javascript.typeinference.FakeField;
import org.eclipse.dltk.internal.javascript.typeinference.IReference;
import org.eclipse.dltk.internal.javascript.typeinference.UncknownReference;
import org.eclipse.jdt.core.CompletionProposal;
import org.eclipse.jdt.core.CompletionRequestor;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.eval.IEvaluationContext;

public class JavaProposalReference extends UncknownReference implements SelfCompletingReference {

	private IJavaProject project;
	private CompletionProposal proposal;
	private ReferenceResolverContext owner;
	private IEvaluationContext context;
	private String parentName;

	public JavaProposalReference(IEvaluationContext context, CompletionProposal proposal,
			ReferenceResolverContext owner2, IJavaProject project, String parentName) {
		super(new String(proposal.getName()), true);
		if (context==null)
			throw new IllegalArgumentException();
		this.context=context;
		this.project = project;
		this.proposal = proposal;
		this.parentName = parentName;
		this.owner = owner2;
	}

	boolean isGlobal;
	public JavaProposalReference(IEvaluationContext context,String completion,
			CompletionProposal proposal2, ReferenceResolverContext owner2,
			IJavaProject create, String string) {
		super(completion, true);
		if (context==null)
			throw new IllegalArgumentException();
		this.project = create;
		this.proposal = proposal2;
		this.parentName = completion;
		this.owner = owner2;
		this.context=context;
		this.isGlobal=true;
	}

	
	public void addModelElements(Collection toAdd) {
		try {
			
			IType r = findType(parentName);
			if (isGlobal){
				if (r==null)return;
				ISourceRange sourceRange = r.getSourceRange();
				FakeField fakeField = new JavaReferenceFakeField(
					(ModelElement) owner.getModule(),r
								.getElementName(), sourceRange
								.getOffset(), sourceRange.getLength(),
						r);
				toAdd.add(fakeField);
				return;				
			}
			while (r != null) {
				IJavaElement[] children = r.getChildren();
				boolean added = false;
				for (int a = 0; a < children.length; a++) {
					IJavaElement javaElement = children[a];
					IMember m = (IMember) javaElement;
					if (m.getElementName().equals(this.getName())) {
						
						ISourceRange sourceRange = m.getSourceRange();
						FakeField fakeField = new JavaReferenceFakeField(
							(ModelElement) owner.getModule(), javaElement
										.getElementName(), sourceRange
										.getOffset(), sourceRange.getLength(),
								javaElement);
						int flags = m.getFlags();
						if (Flags.isPublic(flags)||Flags.isProtected(flags))
						{
						toAdd.add(fakeField);
						added = true;
						break;
						}
						
					}
					
				}
				if (added)
					break;
				String sm = r.getSuperclassTypeSignature();
				
				if (sm != null)
				{
					sm=sm.substring(1,sm.length()-1);
					r = project.findType(sm);
				}
				else
					r = null;
			}
		} catch (JavaModelException e) {
		}
	}

	private IType findType(String parentName) throws JavaModelException {
		String parentName2 = parentName;
		IJavaElement[] codeSelect = this.context.codeSelect(parentName2, 0, parentName2.length());
		if (codeSelect.length>=1)
		{
			if (codeSelect[0] instanceof IType){
				IType t=(IType) codeSelect[0];
				return t;
			}
		}
		return null;		
	}

	public String getChildType() {
		try {
			IType r = findType(parentName);
			while (r != null) {
				IJavaElement[] children = r.getChildren();
				boolean added = false;
				for (int a = 0; a < children.length; a++) {
					IJavaElement javaElement = children[a];
					IMember m = (IMember) javaElement;
					if (m.getElementName().equals(this.getName())) {
						if (m instanceof IMethod) {
							IMethod method=(IMethod) m;
							String returnType = method.getReturnType();
							return returnType;
						}
						if (m instanceof IField){
							IField method=(IField) m;
							String returnType = method.getTypeSignature();
							return returnType;
						}
						added = true;
					}
				}
				if (added)
					break;
				String sm = r.getSuperclassTypeSignature();
				if (sm != null)
					r = project.findType(sm);
				else
					r = null;
			}
		} catch (JavaModelException e) {
		}
		return null;
	}

	public Set getChilds(boolean resolveLocals) {
		if (resolveLocals) {
			String chType=getChildType();
			if (chType.charAt(0)=='T')chType="Ljava.lang.Object;";
			chType=chType.substring(1);
			int k=chType.indexOf('<');
			if (k!=-1)chType=chType.substring(0,k);
			k=chType.indexOf(';');
			if (k!=-1)chType=chType.substring(0,k);
			
			final IJavaProject create = project;
			
			String string = chType + " z=new " + chType + ";z.";
			final String id=chType;
			try {
				final HashSet result = new HashSet();
				context.codeComplete(string, string.length(),
						new CompletionRequestor() {
							public void accept(CompletionProposal proposal) {
								IReference r = new JavaProposalReference(context, proposal,
										owner, create,id);
								result.add(r);
								
							}
						});
				return result;
			} catch (JavaModelException e) {
				return null;
			}
		}
		return new HashSet();
	}

	public int getKind() {		
		int kind = proposal.getKind();
		switch (kind) {
		case CompletionProposal.PACKAGE_REF:
			kind=org.eclipse.dltk.core.CompletionProposal.PACKAGE_REF;
			break;
		case CompletionProposal.FIELD_REF:
			kind=org.eclipse.dltk.core.CompletionProposal.FIELD_REF;
			break;
		case CompletionProposal.METHOD_REF:
			kind=org.eclipse.dltk.core.CompletionProposal.METHOD_REF;
			break;
		case CompletionProposal.TYPE_REF:
			kind=org.eclipse.dltk.core.CompletionProposal.TYPE_REF;
			break;
		default:
			break;
		}
		return kind;
	}


	public char[] getDeclarationSignature() {
		return proposal.getDeclarationSignature();
	}


	public char[] getSignature() {
		return proposal.getSignature();
	}


	public char[][] getParameterNames() {
		return proposal.findParameterNames(null);
	}
	

	
}
