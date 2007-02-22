package org.eclipse.dltk.ruby.internal.ui.text.completion;

import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.ui.text.completion.CompletionProposalLabelProvider;

public class RubyCompletionProposalLabelProvider extends
		CompletionProposalLabelProvider {
	protected String createMethodProposalLabel(CompletionProposal methodProposal) {
		StringBuffer nameBuffer = new StringBuffer();

		// method name
		nameBuffer.append(methodProposal.getName());

		// parameters
		nameBuffer.append('(');
		appendUnboundedParameterList(nameBuffer, methodProposal);
		nameBuffer.append(')');

		// return type
		if (!methodProposal.isConstructor()) {
			// TODO remove SignatureUtil.fix83600 call when bugs are fixed
			// char[] returnType=
			// createTypeDisplayName(methodProposal.getSignature());
			// nameBuffer.append(" "); //$NON-NLS-1$
			// nameBuffer.append(returnType);
		}

		// declaring type
		// nameBuffer.append(" - "); //$NON-NLS-1$
		// String declaringType= extractDeclaringTypeFQN(methodProposal);
		// declaringType= Signature.getSimpleName(declaringType);
		// nameBuffer.append(declaringType);

		return nameBuffer.toString();
	}

	protected String createOverrideMethodProposalLabel(
			CompletionProposal methodProposal) {
		StringBuffer nameBuffer = new StringBuffer();

		// method name
		nameBuffer.append(methodProposal.getName());

		// parameters
		nameBuffer.append('(');
		appendUnboundedParameterList(nameBuffer, methodProposal);
		nameBuffer.append(")  "); //$NON-NLS-1$

		// return type
		// TODO remove SignatureUtil.fix83600 call when bugs are fixed
		// char[] returnType=
		// createTypeDisplayName(methodProposal.getSignature());
		// nameBuffer.append(returnType);

		// declaring type
		// nameBuffer.append(" - "); //$NON-NLS-1$

		return nameBuffer.toString();
	}

	protected final StringBuffer appendParameterSignature(StringBuffer buffer,
			char[][] parameterTypes, char[][] parameterNames) {
		if (parameterNames != null) {
			for (int i = 0; i < parameterNames.length; i++) {
				if (i > 0) {
					buffer.append(',');
				}
				buffer.append(parameterNames[i]);
			}
		}
		return buffer;
	}
}
