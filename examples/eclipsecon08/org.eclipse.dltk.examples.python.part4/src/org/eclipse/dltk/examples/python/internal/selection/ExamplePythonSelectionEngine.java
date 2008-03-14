package org.eclipse.dltk.examples.python.internal.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.codeassist.ISelectionEngine;
import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementVisitor;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.python.parser.ast.expressions.ExtendedVariableReference;

public class ExamplePythonSelectionEngine implements ISelectionEngine {
	private org.eclipse.dltk.core.ISourceModule sourceModule;

	public IModelElement[] select(ISourceModule module, final int offset, int i) {
		sourceModule = (org.eclipse.dltk.core.ISourceModule) module
				.getModelElement();
		ModuleDeclaration moduleDeclaration = SourceParserUtil
				.getModuleDeclaration(sourceModule, null);
		final List results = new ArrayList();
		try {
			moduleDeclaration.traverse(new ASTVisitor() {
				public boolean visit(Expression s) throws Exception {
					if (s.sourceStart() <= offset && offset <= s.sourceEnd()) {
						if (s instanceof ExtendedVariableReference) {
							ExtendedVariableReference ref = (ExtendedVariableReference) s;
							int count = ref.getExpressionCount();
							for (int j = 0; j < count; j++) {
								Expression e = ref.getExpression(j);
								if (e.sourceStart() <= offset
										&& offset <= e.sourceEnd()) {
									if (e instanceof VariableReference) {
										findDeclaration(((VariableReference) e)
												.getName(), results);
									}
								}
							}
						} else if (s instanceof VariableReference) {
							findDeclaration(((VariableReference) s).getName(),
									results);
						}
					}
					return super.visit(s);
				}

				public boolean visit(MethodDeclaration s) throws Exception {
					if (s.getNameStart() <= offset && offset <= s.getNameEnd()) {
						findDeclaration(s.getName(), results);
					}
					return super.visit(s);
				}

				public boolean visit(TypeDeclaration s) throws Exception {
					if (s.getNameStart() <= offset && offset <= s.getNameEnd()) {
						findDeclaration(s.getName(), results);
					}
					return super.visit(s);
				}

			});
		} catch (Exception e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		return (IModelElement[]) results.toArray(new IModelElement[results
				.size()]);
	}

	private void findDeclaration(final String name, final List results) {
		try {
			this.sourceModule.accept(new IModelElementVisitor() {
				public boolean visit(IModelElement element) {
					if (element.getElementName().equals(name)) {
						results.add(element);
					}
					return true;
				}
			});
		} catch (ModelException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
	}

	public void setOptions(Map options) {
	}
}
