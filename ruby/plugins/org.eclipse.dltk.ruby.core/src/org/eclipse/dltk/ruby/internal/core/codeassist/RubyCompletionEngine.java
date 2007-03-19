package org.eclipse.dltk.ruby.internal.core.codeassist;

import java.util.HashSet;
import java.util.Map;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.ast.references.ConstantReference;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.codeassist.IAssistParser;
import org.eclipse.dltk.codeassist.RelevanceConstants;
import org.eclipse.dltk.codeassist.ScriptCompletionEngine;
import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.core.CompletionRequestor;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.ISearchableEnvironment;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.MixinModel;
import org.eclipse.dltk.evaluation.types.IClassType;
import org.eclipse.dltk.evaluation.types.SimpleType;
import org.eclipse.dltk.internal.core.ModelElement;
import org.eclipse.dltk.ruby.ast.ColonExpression;
import org.eclipse.dltk.ruby.ast.RubyArrayExpression;
import org.eclipse.dltk.ruby.core.RubyPlugin;
import org.eclipse.dltk.ruby.internal.parser.JRubySourceParser;
import org.eclipse.dltk.ruby.internal.parsers.jruby.ASTUtils;
import org.eclipse.dltk.ruby.typeinference.RubyClassType;
import org.eclipse.dltk.ruby.typeinference.RubyEvaluatorFactory;
import org.eclipse.dltk.ruby.typeinference.RubyMetaClassType;
import org.eclipse.dltk.ruby.typeinference.RubyModelUtils;
import org.eclipse.dltk.ruby.typeinference.RubyTypeInferencer;
import org.eclipse.dltk.ruby.typeinference.RubyTypeInferencingUtils;
import org.eclipse.dltk.ti.BasicContext;
import org.eclipse.dltk.ti.TypeInferencer;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class RubyCompletionEngine extends ScriptCompletionEngine {

	private TypeInferencer inferencer;	
	private JRubySourceParser parser = new JRubySourceParser(null);
	private MixinModel model;

	public RubyCompletionEngine(ISearchableEnvironment nameEnvironment,
			CompletionRequestor requestor, Map settings,
			IDLTKProject dltkProject) {
		super(nameEnvironment, requestor, settings, dltkProject);
		inferencer = new TypeInferencer(new RubyEvaluatorFactory());
		model = RubyTypeInferencer.getModel();
	}

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
		if (content.charAt(position - 1) == ':'
				&& content.charAt(position - 2) == ':')
			return true;
		return false;
	}

	public void complete(ISourceModule module, int position, int i) {
		this.actualCompletionPosition = position;
		this.requestor.beginReporting();
		org.eclipse.dltk.core.ISourceModule modelModule = (org.eclipse.dltk.core.ISourceModule) module;
		try {
			String content = module.getSourceContents();
			ModuleDeclaration moduleDeclaration = parser.parse(content);
//			ModuleDeclaration moduleDeclaration;
			
//			moduleDeclaration = RubySourceElementParser.parseModule((org.eclipse.dltk.core.ISourceModule)module.getModelElement());
			if (afterColons(content, position)) {				
				
				ASTNode node = ASTUtils.findMaximalNodeEndingAt(
						moduleDeclaration, position - 2);
				this.setSourceRange(position, position);	
				if (node != null && node instanceof Statement) {					
					BasicContext basicContext = new BasicContext(modelModule, moduleDeclaration);
					ExpressionTypeGoal goal = new ExpressionTypeGoal(basicContext, (Statement) node);
					IEvaluatedType type = inferencer.evaluateType(goal, null);
					reportSubElements(modelModule, type, "");
				} else {					
					completeConstant(modelModule, moduleDeclaration, "", position);					
				}
			} else {
				ASTNode minimalNode = ASTUtils.findMinimalNode(
						moduleDeclaration, position, position);
				if (minimalNode != null) {
//					System.out.println(minimalNode.getClass());
					if (minimalNode instanceof CallExpression) {
						completeCall(modelModule, moduleDeclaration,
								(CallExpression) minimalNode, position);
					} else if (minimalNode instanceof ConstantReference) {
						completeConstant(modelModule, moduleDeclaration,
								(ConstantReference) minimalNode, position);
					} else if (minimalNode instanceof ColonExpression) {
						completeColonExpression(modelModule, moduleDeclaration,
								(ColonExpression) minimalNode, position);
					} else if (minimalNode instanceof SimpleReference) {
						completeSimpleRef(modelModule, moduleDeclaration,
								(SimpleReference) minimalNode, position);
					} else {
//						System.out.println("Node "
//								+ minimalNode.getClass().getName()
//								+ " is unsuppored by now");
					}
				}

			}

		} finally {
			this.requestor.endReporting();
		}
	}

	
	private IMethod[] getMethodsForReceiver(org.eclipse.dltk.core.ISourceModule modelModule, ModuleDeclaration moduleDeclaration, ASTNode receiver) {
		ExpressionTypeGoal goal = new ExpressionTypeGoal(new BasicContext(
					modelModule, moduleDeclaration), (Statement) receiver);
			IEvaluatedType type = inferencer.evaluateType(goal, null);
			return getMethodsForReceiver(modelModule, moduleDeclaration, type);
	}
	
	private IMethod[] getMethodsForReceiver(org.eclipse.dltk.core.ISourceModule modelModule, ModuleDeclaration moduleDeclaration, IEvaluatedType type) {
		if (type instanceof RubyClassType) {
			RubyClassType rubyClassType = (RubyClassType) type;
			rubyClassType = RubyTypeInferencingUtils
					.resolveMethods(modelModule.getScriptProject(),
							rubyClassType);
			return rubyClassType.getAllMethods();			
		} else if (type instanceof RubyMetaClassType) {
			RubyMetaClassType metaType = (RubyMetaClassType) type;
			metaType = RubyTypeInferencingUtils.resolveMethods(modelModule, metaType);
			return metaType.getMethods();
		} else if (type instanceof SimpleType) {
			SimpleType simpleType = (SimpleType) type;
			IMethod[] meth = null;
			switch (simpleType.getType()) {
				case SimpleType.TYPE_NUMBER:
					meth = RubyModelUtils.getFakeMethods((ModelElement) modelModule, "Fixnum");
					break;
				case SimpleType.TYPE_STRING:
					meth = RubyModelUtils.getFakeMethods((ModelElement) modelModule, "String");
					break;
				case SimpleType.TYPE_ARRAY:
					meth = RubyModelUtils.getFakeMethods((ModelElement) modelModule, "Array");
					break;
			}
			return meth;
		}
		return null;
	}

	private void completeSimpleRef(org.eclipse.dltk.core.ISourceModule module,
			ModuleDeclaration moduleDeclaration, SimpleReference node,
			int position) {
		String prefix = getPrefix(module, node, position);
		this.setSourceRange(node.sourceStart(), position);
		IField[] fields = RubyModelUtils.findFields(module, moduleDeclaration, prefix, node.sourceStart());
		int relevance = 424242;
		for (int i = 0; i < fields.length; i++) {
			reportField(fields[i], relevance--);
		}
	}

	private String getPrefix(org.eclipse.dltk.core.ISourceModule module, ASTNode node, int position) {
		String content;
		try {
			content = module.getSource();
		} catch (ModelException e) {
			return "";
		}
		return content.substring(node.sourceStart(), position);
	}
	
	private void reportSubElements (org.eclipse.dltk.core.ISourceModule module, 
			IEvaluatedType type, String prefix) {
		if (type instanceof RubyClassType) {
			RubyClassType rubyClassType = (RubyClassType) type;
			IMixinElement mixinElement = model.get(rubyClassType.getModelKey());
			if (mixinElement != null) {
				IMixinElement[] children = mixinElement.getChildren();
				int relevance = 424242;
				for (int i = 0; i < children.length; i++) {
					Object obj = children[i].getAllObjects()[0];
					if (obj instanceof IType) {
						IType type2 = (IType) obj;
						if (type2.getElementName().startsWith(prefix)) 
							reportType(type2, relevance--);
					} else if (obj instanceof IMethod) {
						IMethod method2 = (IMethod) obj;
						if (method2.getElementName().startsWith(prefix)) 
							reportMethod(method2, relevance--);
					}
					
				}				
			}
		} else {
			//never should be here
		}		
	}

	private void completeColonExpression(
			org.eclipse.dltk.core.ISourceModule module,
			ModuleDeclaration moduleDeclaration, ColonExpression node,
			int position) {
		String content;
		try {
			content = module.getSource();
		} catch (ModelException e) {
			return;
		}
		int pos = (node.getLeft() != null) ? (node.getLeft().sourceEnd() + 2) : (node
				.sourceStart());
		String starting = null;
		try {
			starting = content.substring(pos, position).trim();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return;
		}
		
		if (starting.startsWith("::")) {
			this.setSourceRange(position - starting.length() + 2, position);
			completeConstant(module, moduleDeclaration, starting.substring(2), position);
			return;
		}
		
		this.setSourceRange(position - starting.length(), position);
		
		ExpressionTypeGoal goal = new ExpressionTypeGoal(new BasicContext(
				module, moduleDeclaration), (Statement) (node.getLeft()));
		IEvaluatedType type = inferencer.evaluateType(goal, null);
		reportSubElements(module, type, starting);
	}
	
	private void completeConstant(org.eclipse.dltk.core.ISourceModule module,
			ModuleDeclaration moduleDeclaration, String prefix, int position) {
		
		IMixinElement[] modelStaticScopes = RubyTypeInferencingUtils.getModelStaticScopes(model, moduleDeclaration, position);
		
		for (int i = modelStaticScopes.length - 1; i >= 0; i--) {
			IMixinElement scope = modelStaticScopes[i];
			reportSubElements(module, new RubyClassType(scope.getKey()), prefix);		
		}
		
		int relevance = 4242;
		
		HashSet names = new HashSet ();
		IType[] allTypes = RubyTypeInferencingUtils.getAllTypes(module, prefix);
		for (int i = 0; i < allTypes.length; i++) {
			String elementName = allTypes[i].getElementName();
			if (names.contains(elementName))
				continue;
			names.add(elementName);
			reportType(allTypes[i], relevance--);
		}
		
		//TODO: add search for all fields for non-type constants completion 		
	}

	private void completeConstant(org.eclipse.dltk.core.ISourceModule module,
			ModuleDeclaration moduleDeclaration, ConstantReference node,
			int position) {
		String content;
		try {
			content = module.getSource();
		} catch (ModelException e) {
			return;
		}
			
		String prefix = content.substring(node.sourceStart(), position);
		this.setSourceRange(position - prefix.length(), position);
		completeConstant(module, moduleDeclaration, prefix, position);
	}
	
	private void completeCall(org.eclipse.dltk.core.ISourceModule module,
			ModuleDeclaration moduleDeclaration, CallExpression node,
			int position) {
		Statement receiver = node.getReceiver();

		String content;
		try {
			content = module.getSource();
		} catch (ModelException e) {
			return;
		}

		int pos = (receiver != null) ? (receiver.sourceEnd() + 1) : (node
				.sourceStart());

		String starting = content.substring(pos, position).trim();
		
		

		this.setSourceRange(position - starting.length(), position);

		IMethod[] methods = null;
		int relevance = 424242;

		if (receiver != null) {
			if (receiver instanceof RubyArrayExpression) {
				int st = position;
				while (st >= 0 && content.charAt(st) != '.')
					st--;
				if (st > 0) {
					starting = content.substring(st + 1, position).trim();
					this.setSourceRange(position - starting.length(), position);
				}
			}
			if (receiver instanceof ConstantReference) {
				ConstantReference constantReference = (ConstantReference) receiver;
				//resolve static scopes, etc...
				String name = constantReference.getName();
				IMixinElement mixinElement = model.get(name); 
				if (mixinElement != null) {
					try {
						Object[] allObjects2 = mixinElement.getAllObjects();
						IMixinElement[] children = mixinElement.getChildren();
						for (int i = 0; i < children.length; i++) {
							Object[] allObjects = children[i].getAllObjects();
							for (int j = 0; j < allObjects.length; j++) {
								if (allObjects[j] instanceof IMethod) {
									IMethod method = (IMethod) allObjects[j];
									if (method.getElementName().startsWith(starting))
										reportMethod(method, relevance--);	
								}
							}
						}
					} catch (Throwable t) {
						t.printStackTrace();
					}
					return;
				}
			} 
			methods = getMethodsForReceiver(module, moduleDeclaration, receiver);
		} else {
			IClassType self = RubyTypeInferencingUtils.determineSelfClass(
					module, moduleDeclaration, position);
			methods = getMethodsForReceiver(module, moduleDeclaration, self);
						
		}
		if (methods != null) {
			for (int j = 0; j < methods.length; j++) {
				if (methods[j].getElementName().startsWith(starting))
					reportMethod(methods[j],
							relevance--);				
			}
		}		
		
	}

	protected String processFieldName(IField field, String token) {
		// TODO Auto-generated method stub
		return null;
	}

	private void reportMethod(IMethod method, int rel) {
				
		String elementName = method.getElementName();
		if (elementName.indexOf('.') != -1) {
			elementName = elementName.substring(elementName.indexOf('.') + 1);
		}
		char[] name = elementName.toCharArray();
		
		int relevance = RelevanceConstants.R_INTERESTING;
		relevance += RelevanceConstants.R_NON_RESTRICTED;
		relevance += rel; 
		
		// accept result
		noProposal = false;
		if (!requestor.isIgnored(CompletionProposal.METHOD_DECLARATION)) {
			CompletionProposal proposal = createProposal(
					CompletionProposal.METHOD_DECLARATION,
					actualCompletionPosition);

			String[] params = null;
			try {
				params = method.getParameters();
			} catch (ModelException e) {
				e.printStackTrace();
			}
				
			if (params != null && params.length > 0) {
				char[][] args = new char[params.length][];
				for (int i = 0; i < params.length; ++i) {
					args[i] = params[i].toCharArray();
				}
				proposal.setParameterNames(args);
			}

			proposal.setModelElement(method);
			proposal.setName(name);
			proposal.setCompletion(name);
			try {
				proposal.setFlags(method.getFlags());
			} catch (ModelException e) {
				RubyPlugin.log(e);
			}
			proposal.setReplaceRange(this.startPosition - this.offset,
					this.endPosition - this.offset);
			proposal.setRelevance(relevance);
			this.requestor.accept(proposal);
			if (DEBUG) {
				this.printDebug(proposal);
			}
		}
	
	}

	private void reportType(IType type, int rel) {
		char[] name = type.getElementName().toCharArray();
		if (name.length == 0)
			return;
		
		int relevance = RelevanceConstants.R_INTERESTING;
		relevance += RelevanceConstants.R_NON_RESTRICTED;
		relevance += rel;
		
		// accept result
		noProposal = false;
		if (!requestor.isIgnored(CompletionProposal.TYPE_REF)) {
			CompletionProposal proposal = createProposal(
					CompletionProposal.TYPE_REF, actualCompletionPosition);

			proposal.setModelElement(type);
			proposal.setName(name);
			proposal.setCompletion(type.getElementName().toCharArray());
			// proposal.setFlags(Flags.AccDefault);
			proposal.setReplaceRange(this.startPosition - this.offset,
					this.endPosition - this.offset);
			proposal.setRelevance(relevance);
			this.requestor.accept(proposal);
			if (DEBUG) {
				this.printDebug(proposal);
			}
		}
	
	}

	
	private void reportField(IField field, int rel) {
		char[] name = field.getElementName().toCharArray();
		if (name.length == 0)
			return;
		
		int relevance = RelevanceConstants.R_INTERESTING;
		relevance += RelevanceConstants.R_NON_RESTRICTED;
		relevance += rel;
		
		// accept result
		noProposal = false;
		if (!requestor.isIgnored(CompletionProposal.FIELD_REF)) {
			CompletionProposal proposal = createProposal(
					CompletionProposal.FIELD_REF, actualCompletionPosition);

			proposal.setModelElement(field);
			proposal.setName(name);
			proposal.setCompletion(field.getElementName().toCharArray());
			// proposal.setFlags(Flags.AccDefault);
			proposal.setReplaceRange(this.startPosition - this.offset,
					this.endPosition - this.offset);
			proposal.setRelevance(relevance);
			this.requestor.accept(proposal);
			if (DEBUG) {
				this.printDebug(proposal);
			}
		}
	
	}
	
}