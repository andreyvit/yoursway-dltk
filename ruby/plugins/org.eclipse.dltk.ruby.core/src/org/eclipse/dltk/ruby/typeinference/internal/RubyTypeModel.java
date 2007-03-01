package org.eclipse.dltk.ruby.typeinference.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ruby.typeinference.RubyTypeUtils;
import org.eclipse.dltk.ruby.typeinference.fcnm.Unit;
import org.eclipse.dltk.ruby.typeinference.internal.evaluators.AssignmentTypeEvaluator;
import org.eclipse.dltk.ruby.typeinference.internal.evaluators.CallTypeEvaluator;
import org.eclipse.dltk.ruby.typeinference.internal.evaluators.ClassContextTypeEvaluator;
import org.eclipse.dltk.ruby.typeinference.internal.evaluators.ConstantDeclarationTypeEvaluator;
import org.eclipse.dltk.ruby.typeinference.internal.evaluators.ConstantReferenceTypeEvaluator;
import org.eclipse.dltk.ruby.typeinference.internal.evaluators.IfStatementTypeEvaluator;
import org.eclipse.dltk.ruby.typeinference.internal.evaluators.MethodContextTypeEvaluator;
import org.eclipse.dltk.ruby.typeinference.internal.evaluators.NumericLiteralTypeEvaluator;
import org.eclipse.dltk.ruby.typeinference.internal.evaluators.VariableReferenceTypeEvaluator;
import org.eclipse.dltk.ruby.typemodel.classes.ObjectTypeDescriptor;
import org.eclipse.dltk.typeinference.ASTCaching;
import org.eclipse.dltk.typeinference.AnyTypeDescriptor;
import org.eclipse.dltk.typeinference.BaseTypeCalculator;
import org.eclipse.dltk.typeinference.IDependentTypedElement;
import org.eclipse.dltk.typeinference.IElementKind;
import org.eclipse.dltk.typeinference.IMethodDescriptor;
import org.eclipse.dltk.typeinference.INodeElement;
import org.eclipse.dltk.typeinference.IScope;
import org.eclipse.dltk.typeinference.ITypeDescriptor;
import org.eclipse.dltk.typeinference.ITypeModel;
import org.eclipse.dltk.typeinference.IUnit;
import org.eclipse.dltk.typeinference.RecursiveCallTypeDescriptor;
import org.eclipse.dltk.typeinference.UserMethodDescriptor;

public class RubyTypeModel extends BaseTypeCalculator {

	private class Context implements IContext {

		private final ITypeModel modelMaster;

		private ArrayList scopes = new ArrayList();

		public Context(ITypeModel modelMaster) {
			this.modelMaster = modelMaster;
		}

		public void enterScope(IScope scope) {
			Assert.isLegal(scope != null);
			scopes.add(scope);
		}

		public IScope getCurrentScope() {
			return (IScope) scopes.get(scopes.size() - 1);
		}

		public ITypeModel getModelMaster() {
			return modelMaster;
		}

		public IScope leaveScope() {
			return (IScope) scopes.remove(scopes.size() - 1);
		}

		public Object getNodeMapping(ASTNode key) {
			return nodeMapping.get(key);
		}

		public void putNodeMapping(ASTNode key, Object value) {
			nodeMapping.put(key, value);
		}

		public ITypeDescriptor evaluateType(ASTNode node) {
			EvaluatingASTVisitor visitor = new EvaluatingASTVisitor(this, node,
					RubyTypeModel.this.evaluators);
			try {
				node.traverse(visitor);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			return visitor.getEvaluatedType(); // FIXME XXX
		}

		public void updateIndex(ASTNode node) {
			EvaluatingASTVisitor visitor = new IndexingASTVisitor(this,
					RubyTypeModel.this.influencers);
			try {
				node.traverse(visitor);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	};
	
	private class ContextWithGoal extends Context {

		private final ASTNode goal;
		
		private ITypeDescriptor result;

		public ContextWithGoal(ITypeModel modelMaster, ASTNode goal) {
			super(modelMaster);
			this.goal = goal;
		}

		public ITypeDescriptor evaluateType(ASTNode node) {
			ITypeDescriptor result = super.evaluateType(node);
			if (this.goal == node)
				this.result = result;
			return result;
		}

		public ITypeDescriptor getResult() {
			return result;
		}
		
	}

	public final ObjectTypeDescriptor objectType = new ObjectTypeDescriptor(this);

	private Map nodeMapping = new WeakHashMap();
	
	private Map sourceModulesToUnits = new WeakHashMap();

	private Collection methodsToRecalculateTypesFor = new HashSet();

	/**
	 * @deprecated
	 */
	private Context context = new Context(this);

	private EvaluatorConfiguration evaluators = new EvaluatorConfiguration();

	private EvaluatorConfiguration influencers = new EvaluatorConfiguration();

	public RubyTypeModel() {
		// add root scope, because type inferencers might contribute native
		// types from init()
		IContext context = new Context(this);
		context.enterScope(objectType.getScope());
		RubyTypeUtils.addType(context, objectType);
		RubyTypeUtils.addType(context, new NilTypeDescriptor(this));

		addInfluencer(new ClassContextTypeEvaluator());
		addInfluencer(new MethodContextTypeEvaluator());

		addTypeEvaluator(new IfStatementTypeEvaluator());
		
		addTypeEvaluator(new NumericLiteralTypeEvaluator());
		addTypeEvaluator(new ConstantReferenceTypeEvaluator());
		addTypeEvaluator(new VariableReferenceTypeEvaluator());

		addTypeEvaluator(new ConstantDeclarationTypeEvaluator());
		addTypeEvaluator(new CallTypeEvaluator());
		addTypeEvaluator(new AssignmentTypeEvaluator());
	}

	public Object getNodeMapping(ASTNode key) {
		return nodeMapping.get(key);
	}

	public void putNodeMapping(ASTNode key, Object value) {
		nodeMapping.put(key, value);
	}

	public void addTypeEvaluator(ITypeEvaluator affector) {
		evaluators.addTypeEvaluator(affector);
		affector.init(context);
	}

	public void addInfluencer(ITypeEvaluator affector) {
		evaluators.addTypeEvaluator(affector);
		influencers.addTypeEvaluator(affector);
		affector.init(context);
	}

	public ITypeEvaluator getTypeEvaluator(ASTNode node) {
		return evaluators.getTypeEvaluator(node);
	}

	public void invalidateMethodTypeInfo(IMethodDescriptor method) {
		if (method instanceof IDependentTypedElement)
			methodsToRecalculateTypesFor.add(method);
	}

	public ITypeDescriptor makeTypeDescriptorFromListOfTypeDescriptors(Set types) {
		if (types.size() == 1) {
			return (ITypeDescriptor) types.iterator().next();
		} else if (types.contains(RecursiveCallTypeDescriptor.INSTANCE)) {
			Set copy = new HashSet(types);
			copy.remove(RecursiveCallTypeDescriptor.INSTANCE);
			return makeTypeDescriptorFromListOfTypeDescriptors(copy);
		}
		return AnyTypeDescriptor.INSTANCE;
	}

	public UserMethodDescriptor getMethodDescriptor(MethodDeclaration s) {
		return (UserMethodDescriptor) context.getNodeMapping(s);
	}

	public IElementKind getKind() {
		return IElementKind.MODEL;
	}

	public ITypeModel getModel() {
		return this;
	}

	public IUnit getUnit(ISourceModule sourceModule) {
		Assert.isLegal(sourceModule != null);
		IUnit unit = (IUnit) sourceModulesToUnits.get(sourceModule);
		if (unit == null) {
			unit = new Unit(this, sourceModule);
			sourceModulesToUnits.put(sourceModule, unit);
		}
		return unit;
	}

	public void recalculate(IUnit unit) {
		ASTNode moduleNode = unit.getASTNode(ASTCaching.ALLOW_ANY);
		IContext context = new Context(this);
		context.enterScope(unit.getScope());
		try {
			context.evaluateType(moduleNode);

			while (!methodsToRecalculateTypesFor.isEmpty()) {
				Collection methodsToRecalculatesThisTime = methodsToRecalculateTypesFor;
				methodsToRecalculateTypesFor = new HashSet();
				for (Iterator iter = methodsToRecalculatesThisTime.iterator(); iter.hasNext();) {
					IDependentTypedElement md = (IDependentTypedElement) iter.next();
					ASTNode node = md.getASTNode(ASTCaching.ALLOW_ANY);
					ITypeEvaluator typeEvaluator = evaluators.getTypeEvaluator(node);
					Assert.isNotNull(typeEvaluator);
					IReparsableElementTypeEvaluator rete = (IReparsableElementTypeEvaluator) typeEvaluator;
					rete.reparse(md, context);
				}
			}
		} finally {
			context.leaveScope();
		}
	}

	public ObjectTypeDescriptor getObjectType() {
		return objectType;
	}

	public void removeNodeMapping(ASTNode oldNode) {
		nodeMapping.remove(oldNode);
	}

	public ITypeDescriptor calculateType(IUnit unit, ASTNode node) {
		INodeElement element = unit.getInnermostModelElement(node);
		Assert.isLegal(element != null);
		IScope scope = element.getScope();
		Assert.isLegal(scope != null);
		ContextWithGoal context = new ContextWithGoal(this, node);
		context.enterScope(scope);
		try {
			context.evaluateType(element.getASTNode(ASTCaching.ALLOW_ANY));
		} finally {
			context.leaveScope();
		}
		return context.getResult();	
	}

	public IScope getScope() {
		return objectType.getScope();
	}

}
