package org.eclipse.dltk.ruby.internal.core.codeassist;

import java.util.Map;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.ast.references.ConstantReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.codeassist.CompletionEngine;
import org.eclipse.dltk.codeassist.IAssistParser;
import org.eclipse.dltk.codeassist.RelevanceConstants;
import org.eclipse.dltk.compiler.CharOperation;
import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.core.CompletionRequestor;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.ISearchableEnvironment;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.ddp.BasicContext;
import org.eclipse.dltk.ddp.ExpressionGoal;
import org.eclipse.dltk.ddp.TypeInferencer;
import org.eclipse.dltk.evaluation.types.IEvaluatedType;
import org.eclipse.dltk.ruby.ast.ColonExpression;
import org.eclipse.dltk.ruby.internal.parser.JRubySourceParser;
import org.eclipse.dltk.ruby.internal.parsers.jruby.ASTUtils;
import org.eclipse.dltk.ruby.typeinference.RubyClassType;
import org.eclipse.dltk.ruby.typeinference.RubyEvaluatorFactory;
import org.eclipse.dltk.ruby.typeinference.RubyTypeInferencingUtils;

public class RubyCompletionEngine extends CompletionEngine {

	private TypeInferencer inferencer;

	public RubyCompletionEngine(ISearchableEnvironment nameEnvironment,
			CompletionRequestor requestor, Map settings, IDLTKProject dltkProject) {
		super(nameEnvironment, requestor, settings, dltkProject);
		inferencer = new TypeInferencer(new RubyEvaluatorFactory());
	}

	private JRubySourceParser parser = new JRubySourceParser(null);

	protected int getEndOfEmptyToken() {
		// TODO Auto-generated method stub
		return 0;
	}

	protected String processMethodName(IMethod method, String token) {
		return null;
	}

	protected String processTypeName(IType method, String token) {
		return null;
	}

	public IAssistParser getParser() {
		return null;
	}
	
	private boolean afterColons(String content, int position) {
		if (position < 2)
			return false;
		if (content.charAt(position - 1) == ':' && content.charAt(position - 2) == ':') 
			return true;
		return false;
	}
	
	private boolean afterDot(String content, int position) {
		if (position < 1)
			return false;
		if (content.charAt(position - 1) == '.') 
			return true;
		return false;
	}
	
	private static String cut(String content, int position, int length) {
		content = content.substring(0, position) + content.substring(position + length);
		return content;
	}
	
	private static boolean widowDot (String content, int position) {
		while (position < content.length() && ( 
				content.charAt(position) == ' ' ||
				content.charAt(position) == '\t'))
			position++;
		if (position >= content.length())
			return true;
		if (content.charAt(position) == '\r' ||content.charAt(position) == '\n')
			return true;
		return false;
	}

	public void complete(ISourceModule module, int position, int i) {
		this.actualCompletionPosition = position;
		this.requestor.beginReporting();
		try {
			String content = module.getSourceContents();
			if (afterDot(content, position)) {
				if (widowDot(content, position)) {
					content = cut(content, position - 1, 1);
					position--;
				}
				this.setSourceRange(position+1, position+1);
				ModuleDeclaration moduleDeclaration = parser.parse(content);
				ASTNode node = ASTUtils.findMaximalNodeEndingAt(moduleDeclaration, position-1);
				if (node instanceof Statement) {
					org.eclipse.dltk.core.ISourceModule modelModule = (org.eclipse.dltk.core.ISourceModule) module;
					ExpressionGoal goal = new ExpressionGoal(new BasicContext(modelModule, moduleDeclaration), 
							(Statement) node);
					IEvaluatedType type = inferencer.evaluateGoal(goal, 0);
					if (type instanceof RubyClassType) {
						RubyClassType rubyClassType = (RubyClassType) type;
						rubyClassType = RubyTypeInferencingUtils.resolveMethods(modelModule.getScriptProject(), rubyClassType);
						IMethod[] allMethods = rubyClassType.getAllMethods();
						for (int j = 0; j < allMethods.length; j++) {
							reportLocalMethod("".toCharArray(), 0, allMethods[j]);
						}				
					}
				}
			} else if (afterColons(content, position)) {
				if (widowDot(content, position)) {
					content = cut(content, position - 2, 2);
					position -= 2;
				}
			} else {
				ModuleDeclaration moduleDeclaration = parser.parse(content);
				ASTNode minimalNode = ASTUtils.findMinimalNode(moduleDeclaration, position - 1, position - 1);
				if (minimalNode != null) {
					if (minimalNode instanceof CallExpression) {
						completeCall(module, moduleDeclaration, (CallExpression)minimalNode, position);
					} else if (minimalNode instanceof ConstantReference) {
						completeConstant(module, moduleDeclaration, (ConstantReference)minimalNode, position);
					} else if (minimalNode instanceof ColonExpression) {
						completeColonExpression(module, moduleDeclaration, (ColonExpression)minimalNode, position);
					} else {
						System.out.println("Node " + minimalNode.getClass().getName() + " is unsuppored by now");
					}
				}
				
			}

		} finally {
			this.requestor.endReporting();
		}
	}

	private void completeColonExpression(ISourceModule module,
			ModuleDeclaration moduleDeclaration, ColonExpression minimalNode,
			int position) {
		// TODO Auto-generated method stub
		
	}

	private void completeConstant(ISourceModule module,
			ModuleDeclaration moduleDeclaration, ConstantReference minimalNode,
			int position) {
		// TODO Auto-generated method stub
		
	}

	private void completeCall(ISourceModule module,
			ModuleDeclaration moduleDeclaration, CallExpression minimalNode,
			int position) {
		// TODO Auto-generated method stub
		
	}

	

	protected String processFieldName(IField field, String token) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void reportLocalMethod(char[] token, int length, IMethod method) {
		char[] name = method.getElementName().toCharArray();
		if (length <= name.length && CharOperation.prefixEquals(token, name, false)) {
			int relevance = RelevanceConstants.R_INTERESTING;
			relevance += computeRelevanceForCaseMatching(token, name);
			relevance += RelevanceConstants.R_NON_RESTRICTED;

			// accept result
			noProposal = false;
			if (!requestor.isIgnored(CompletionProposal.METHOD_DECLARATION)) {
				CompletionProposal proposal = createProposal(CompletionProposal.METHOD_DECLARATION,
						actualCompletionPosition);
				// proposal.setSignature(getSignature(typeBinding));
				// proposal.setPackageName(q);
				// proposal.setTypeName(displayName);
//				ArgumentDescriptor[] arguments = method.getArguments();
//				if(arguments.length > 0 ) {
//					char[][] args = new char[arguments.length][];
//					for( int j = 0; j < arguments.length; ++j ) {
//						args[j] = arguments[j].getName().toCharArray();
//					}
//					proposal.setParameterNames(args);
//				}

				proposal.setName(name);
				proposal.setCompletion(name);
				// proposal.setFlags(Flags.AccDefault);
				proposal.setReplaceRange(this.startPosition - this.offset, this.endPosition - this.offset);
				proposal.setRelevance(relevance);
				this.requestor.accept(proposal);
				if (DEBUG) {
					this.printDebug(proposal);
				}
			}
		}
	}

}
