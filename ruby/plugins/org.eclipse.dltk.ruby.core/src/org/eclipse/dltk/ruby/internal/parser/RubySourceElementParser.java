package org.eclipse.dltk.ruby.internal.parser;

import org.eclipse.dltk.ast.declarations.ISourceParser;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.DLTKParsingManager;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceElementParser;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceModuleInfoCache;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.ISourceModuleInfoCache.ISourceModuleInfo;
import org.eclipse.dltk.internal.core.ModelManager;
import org.eclipse.dltk.ruby.core.RubyLanguageToolkit;
import org.eclipse.dltk.ruby.internal.parser.visitors.RubySourceElementRequestor;

public class RubySourceElementParser implements ISourceElementParser {

	public static final Object AST = "ast";
	private ISourceElementRequestor fRequestor = null;
	private final IProblemReporter problemReporter;

	/**
	 * Python lexer handler helper.
	 * 
	 * @param problemReporter
	 * 
	 * @param enveronment
	 */

	public RubySourceElementParser(ISourceElementRequestor requestor,
			IProblemReporter problemReporter) {
		this.fRequestor = requestor;
		this.problemReporter = problemReporter;
	}

	public ModuleDeclaration parseSourceModule(char[] contents, ISourceModuleInfo astCashe) {

		ModuleDeclaration moduleDeclaration = parseModule(astCashe, contents, this.problemReporter);

		RubySourceElementRequestor requestor = new RubySourceElementRequestor(
				this.fRequestor);

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
			char[] content, IProblemReporter problemReporter ) {
		ModuleDeclaration moduleDeclaration = null;
		if( astCache != null ) {
			moduleDeclaration = (ModuleDeclaration)astCache.get(AST);
		}
		if( moduleDeclaration == null ) {
			ISourceParser sourceParser = DLTKParsingManager.createParser(RubyLanguageToolkit.getDefault());
			moduleDeclaration = sourceParser.parse(content, problemReporter);
			if( moduleDeclaration != null && astCache != null ) {
				astCache.put(AST, moduleDeclaration );
			}
		}
		return moduleDeclaration;
	}

	public void setRequirestor(ISourceElementRequestor requestor) {
		this.fRequestor = requestor;
	}

	public static ModuleDeclaration parseModule(ISourceModule module) {
		ISourceModuleInfoCache sourceModuleInfoCache = ModelManager.getModelManager().getSourceModuleInfoCache();
		try {
			return parseModule(sourceModuleInfoCache.get(module), module.getSourceAsCharArray(), null);
		} catch (ModelException e) {
			if( DLTKCore.DEBUG ) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
