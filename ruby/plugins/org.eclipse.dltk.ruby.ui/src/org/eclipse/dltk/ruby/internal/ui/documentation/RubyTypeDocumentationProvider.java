package org.eclipse.dltk.ruby.internal.ui.documentation;

import java.io.Reader;
import java.io.StringReader;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.ruby.internal.parsers.jruby.ASTUtils;
import org.eclipse.dltk.ruby.typeinference.RubyClassType;
import org.eclipse.dltk.ti.BasicContext;
import org.eclipse.dltk.ti.DLTKTypeInferenceEngine;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;
import org.eclipse.dltk.ui.documentation.IScriptDocumentationProvider;

public class RubyTypeDocumentationProvider implements
		IScriptDocumentationProvider {

	public Reader getInfo(IMember element, boolean lookIntoParents,
			boolean lookIntoExternal) {
		ISourceModule module = (ISourceModule) element
				.getAncestor(IModelElement.SOURCE_MODULE);
		if (module != null) {
			try {
				ModuleDeclaration unit = SourceParserUtil.getModuleDeclaration(module, null);
				ISourceRange sourceRange = element.getSourceRange();
				ASTNode minimalNode = ASTUtils.findMinimalNode(unit,
						sourceRange.getOffset(), sourceRange.getOffset()
								+ sourceRange.getLength());
				if (minimalNode != null && minimalNode != unit) {
					BasicContext context = new BasicContext(module, unit);
					ExpressionTypeGoal goal = new ExpressionTypeGoal(context, minimalNode);
					DLTKTypeInferenceEngine engine = new DLTKTypeInferenceEngine();
					IEvaluatedType type = engine.evaluateType(goal, 500);
					if (type instanceof RubyClassType) {
						RubyClassType rubyClassType = (RubyClassType) type;
						return new StringReader("Inferenced type: " + rubyClassType.getModelKey()); //$NON-NLS-1$
					}
				}
			} catch (ModelException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Reader getInfo(String content) {
		return null;
	}

}
