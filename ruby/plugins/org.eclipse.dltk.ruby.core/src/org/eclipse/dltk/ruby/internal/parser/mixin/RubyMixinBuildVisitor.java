package org.eclipse.dltk.ruby.internal.parser.mixin;

import java.util.Stack;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.ConstantReference;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.core.mixin.MixinModel;
import org.eclipse.dltk.core.mixin.IMixinRequestor.ElementInfo;
import org.eclipse.dltk.ruby.ast.RubySingletonClassDeclaration;
import org.eclipse.dltk.ruby.ast.RubySingletonMethodDeclaration;
import org.eclipse.dltk.ruby.ast.SelfReference;
import org.eclipse.dltk.ruby.internal.core.RubyClassDeclaration;
import org.eclipse.dltk.ruby.typeinference.RubyClassType;
import org.eclipse.dltk.ruby.typeinference.RubyTypeInferencer;
import org.eclipse.dltk.ti.BasicContext;
import org.eclipse.dltk.ti.ITypeInferencer;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class RubyMixinBuildVisitor extends ASTVisitor {

	private static final String INSTANCE_SUFFIX = RubyMixin.INSTANCE_SUFFIX;
	private static final String VIRTUAL_SUFFIX = RubyMixin.VIRTUAL_SUFFIX;
	private static final String SEPARATOR = MixinModel.SEPARATOR;
	
	private final ISourceModule sourceModule;
	private final boolean moduleAvailable;
	private final ModuleDeclaration module;
	private final IMixinRequestor requestor;
	
	private Stack keys = new Stack();
	private Stack classes = new Stack();
	private int visibility;
	private ITypeInferencer inferencer;
	
	public RubyMixinBuildVisitor(ModuleDeclaration module, ISourceModule sourceModule,
			boolean moduleAvailable, IMixinRequestor requestor) {
		super();
		this.module = module;
		this.sourceModule = sourceModule;
		this.moduleAvailable = moduleAvailable;
		this.requestor = requestor;
	}

	private String currentKey () {
		return (String) keys.peek();
	}
	
	private String currentClass () {
		return (String) classes.peek();
	}
	
	private ITypeInferencer getInferencer () {
		if (inferencer == null)
			inferencer = new RubyTypeInferencer();
		return inferencer;
	}
	
	public boolean visit(ModuleDeclaration s) throws Exception {
		this.keys.add("Object");
		this.classes.add("Object");
		this.visibility = Modifiers.AccPrivate;
		return true;
	}
	
	private String evaluateSingletonMethodReceiver (Expression receiver) {
		String parentKey = null;
		if (receiver instanceof SelfReference) { //simple optimization
			parentKey = currentClass();				
		} else {
			if (moduleAvailable) {
				//BIG TODO
				//new need to evaluate object(!) that results from receiver
			} else 
				return null;					
		}
		return parentKey;
	}

	public boolean visit(MethodDeclaration decl) throws Exception {
		String name = decl.getName();
		if (decl instanceof RubySingletonMethodDeclaration) {
			RubySingletonMethodDeclaration singDecl = (RubySingletonMethodDeclaration) decl;
			Expression receiver = singDecl.getReceiver();
			String parentKey = evaluateSingletonMethodReceiver(receiver);
			if (parentKey == null)
				return false;
			ElementInfo info = new IMixinRequestor.ElementInfo();
			info.key = parentKey + SEPARATOR + name;
			if (moduleAvailable) {
				info.object = sourceModule.getElementAt(decl.sourceStart() + 1);
			}
			requestor.reportElement(info );
		} else {
			ElementInfo info = new IMixinRequestor.ElementInfo();
			info.key = currentClass() + INSTANCE_SUFFIX + SEPARATOR + name;
			if (moduleAvailable) {
				info.object = sourceModule.getElementAt(decl.sourceStart() + 1);
			}
			requestor.reportElement(info );
		}
		return true;
	}

	public boolean visit(Statement s) throws Exception {
		// TODO Auto-generated method stub
		return super.visit(s);
	}

	public boolean visit(TypeDeclaration decl) throws Exception {
		if (decl instanceof RubySingletonClassDeclaration) {
			RubySingletonClassDeclaration declaration = (RubySingletonClassDeclaration) decl;
			
		} else if (decl instanceof RubyClassDeclaration) {
			RubyClassDeclaration declaration = (RubyClassDeclaration) decl;
			Expression className = declaration.getClassName();
			String key = expressionToKey(className);
			if (key == null) 
				return false;
			Object obj = null;
			if (moduleAvailable)
				obj = sourceModule.getElementAt(decl.sourceStart() + 1);
			report(key, obj);
			report(key + INSTANCE_SUFFIX, obj);
			classes.add(key);
			return true;
		} 
		return false;
	}
	
	private void report (String key, Object object) {
		ElementInfo info = new IMixinRequestor.ElementInfo();
		info.key = key;
		info.object = object;
		requestor.reportElement(info );
	}
	
	/**
	 * Converts constant reference or colon expression to appropriate model key
	 * @param expr
	 * @return
	 */ 
	private String expressionToKey (Expression expr) {
		if (expr instanceof ConstantReference) {
			ConstantReference constantReference = (ConstantReference) expr;
			return currentClass() + SEPARATOR + constantReference.getName(); 
		} else {
			//TODO
			//evaluate type
		}
		return null;
	}
		
}
