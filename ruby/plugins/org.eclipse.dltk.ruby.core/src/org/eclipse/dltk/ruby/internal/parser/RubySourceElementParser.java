package org.eclipse.dltk.ruby.internal.parser;

import java.util.Collections;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.IProblemReporter;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.ISourceElementParser;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceModuleInfoCache;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.ISourceModuleInfoCache.ISourceModuleInfo;
import org.eclipse.dltk.internal.core.ModelManager;
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

		String content = new String(contents);

		ModuleDeclaration moduleDeclaration = parseModule(astCashe, content, this.problemReporter);

		RubySourceElementRequestor requestor = new RubySourceElementRequestor(
				this.fRequestor);

		try {
			moduleDeclaration.traverse(requestor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return moduleDeclaration;
	}

	public static ModuleDeclaration parseModule(ISourceModuleInfo astCashe,
			String content, IProblemReporter problemReporter ) {
		ModuleDeclaration moduleDeclaration = null;
		if( astCashe != null ) {
			moduleDeclaration = (ModuleDeclaration)astCashe.get(AST);
		}
		if( moduleDeclaration == null ) {
			JRubySourceParser sourceParser = new JRubySourceParser(problemReporter);
			moduleDeclaration = sourceParser.parse(content);
			if( moduleDeclaration != null && astCashe !=null ) {
				astCashe.put(AST, moduleDeclaration );
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
			return parseModule(sourceModuleInfoCache.get(module), module.getSource(), null);
		} catch (ModelException e) {
			if( DLTKCore.DEBUG ) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
