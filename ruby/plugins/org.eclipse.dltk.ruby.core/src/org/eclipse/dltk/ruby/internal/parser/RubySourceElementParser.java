package org.eclipse.dltk.ruby.internal.parser;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.IProblemReporter;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.core.ISourceElementParser;
import org.eclipse.dltk.ruby.internal.parser.visitors.RubySourceElementRequestor;


public class RubySourceElementParser implements ISourceElementParser
{
	
	private ISourceElementRequestor fRequestor = null;
	private final IProblemReporter problemReporter;
	/**
	 * Python lexer handler helper.
	 * @param problemReporter 
	 * 
	 * @param enveronment
	 */	

	public RubySourceElementParser( ISourceElementRequestor requestor, IProblemReporter problemReporter ) {
		this.fRequestor = requestor;
		this.problemReporter = problemReporter;		
	}


	public ModuleDeclaration parseSourceModule( char[] contents ) {
		
		String content = new String( contents );
		
		JRubySourceParser sourceParser = new JRubySourceParser(problemReporter);
		ModuleDeclaration moduleDeclaration = sourceParser.parse( content );				
		
		RubySourceElementRequestor requestor = new RubySourceElementRequestor( this.fRequestor );
		
		try {
			moduleDeclaration.traverse( requestor );
		}
		catch( Exception e ) {
			e.printStackTrace( );
		}
		return moduleDeclaration;
	}
	public void setRequirestor( ISourceElementRequestor requestor ) {
		this.fRequestor = requestor;
	}
}
