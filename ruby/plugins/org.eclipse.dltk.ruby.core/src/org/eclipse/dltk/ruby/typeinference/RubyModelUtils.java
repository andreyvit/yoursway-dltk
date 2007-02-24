package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;

public class RubyModelUtils {
	
	public static IType getModelTypeByAST(TypeDeclaration astNode, ISourceModule sourceModule) throws ModelException {
		IType[] types = sourceModule.getAllTypes();
		int astStart = astNode.sourceStart();
		int astEnd = astNode.sourceEnd();
		int bestScore = Integer.MAX_VALUE;
		IType bestType = null;
		for (int i = 0; i < types.length; i++) {
			IType type = types[i];
			ISourceRange sourceRange = type.getSourceRange();
			int modelStart = sourceRange.getOffset();
			int modelEnd = modelStart + sourceRange.getLength();
			if (modelStart == astStart && modelEnd == astEnd)
				// XXX modelEnd == astEnd + 1 currently, so this never happens
				return type;
			if (type.getElementName().equals(astNode.getName())) {
				int diff1 = modelStart - astStart;
				int diff2 = modelEnd - astEnd;
				int score = diff1 * diff1 + diff2 * diff2;
				if (score < bestScore) {
					bestScore = score;
					bestType = type;
				}
			}
		}
		return bestType;
	}

}
