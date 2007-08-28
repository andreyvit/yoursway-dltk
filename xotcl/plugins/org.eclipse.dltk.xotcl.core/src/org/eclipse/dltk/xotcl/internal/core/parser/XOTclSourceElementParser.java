package org.eclipse.dltk.xotcl.internal.core.parser;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.declarations.ISourceParser;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.ISourceElementParser;
import org.eclipse.dltk.core.ISourceModuleInfoCache.ISourceModuleInfo;
import org.eclipse.dltk.tcl.core.TclNature;

public class XOTclSourceElementParser implements ISourceElementParser {
	public static final Object AST = "ast";
	private ISourceElementRequestor requestor;
	private IProblemReporter reporter;
	public ModuleDeclaration parseSourceModule(char[] contents, ISourceModuleInfo astCashe, char[] filename) {

		ModuleDeclaration moduleDeclaration = parseModule(astCashe, contents, this.reporter, filename);

		XOTclSourceElementRequestVisitor requestor = new XOTclSourceElementRequestVisitor(
				this.requestor, reporter );

		try {
			moduleDeclaration.traverse(requestor);

		} catch (Exception e) {
			if( DLTKCore.DEBUG ) {
				e.printStackTrace();
			}
		}
		return moduleDeclaration;
	}
	public static ModuleDeclaration parseModule(ISourceModuleInfo astCache,
			char[] content, IProblemReporter problemReporter, char[] filename ) {
		ModuleDeclaration moduleDeclaration = null;
		if( astCache != null ) {
			moduleDeclaration = (ModuleDeclaration)astCache.get(AST);
		}
		if( moduleDeclaration == null ) {
			ISourceParser sourceParser = null;
			try {
				sourceParser = DLTKLanguageManager.getSourceParser(TclNature.NATURE_ID);
			} catch (CoreException e) {
				e.printStackTrace();
			}
			if( sourceParser != null ) {
				moduleDeclaration = sourceParser.parse(filename, content, problemReporter);
				if( moduleDeclaration != null && astCache != null ) {
					astCache.put(AST, moduleDeclaration );
				}
			}
		}
		return moduleDeclaration;
	}

	public void setReporter(IProblemReporter reporter) {
		this.reporter = reporter;
	}

	public void setRequestor(ISourceElementRequestor requestor) {
		this.requestor = requestor;
	}
}
