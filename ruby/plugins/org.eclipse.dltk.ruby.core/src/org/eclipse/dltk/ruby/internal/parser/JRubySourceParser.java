package org.eclipse.dltk.ruby.internal.parser;

import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.dltk.ast.declarations.ISourceParser;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.IProblemReporter;
import org.eclipse.dltk.ruby.internal.parsers.jruby.DLTKASTBuildVisitor;
import org.jruby.IRuby;
import org.jruby.ast.Node;
import org.jruby.parser.Parser;
import org.jruby.parser.RubyParserConfiguration;


public class JRubySourceParser implements IExecutableExtension, ISourceParser {
	
	private static final Pattern DOT_FIXER = Pattern.compile("\\.(?=\\s|$)");
	private IProblemReporter problemReporter;
	
	private static String fixBrokenDots (String content) {
		Matcher matcher = DOT_FIXER.matcher(content);
		StringBuffer result = new StringBuffer();
		int regionStart = 0;
		while (matcher.find(regionStart)) {
			int offset = matcher.start();
			if (offset > regionStart)
				result.append(content.subSequence(regionStart, offset));
			result.append("._missing_method_name_");
			regionStart = offset + 1;
		}
		if (regionStart < content.length() - 1)
			result.append(content.subSequence(regionStart, content.length()));
		if (regionStart == 0)
			return content; // no dots fixed
		else
			return result.toString();
	}
	
	public JRubySourceParser(IProblemReporter problemReporter) {
		this.problemReporter = problemReporter;
	}
	
	public ModuleDeclaration parse(String content) {// throws
		//content = fixBrokenDots(content);
		
		ModuleDeclaration module = new ModuleDeclaration(content.length());
		StringReader reader = new StringReader(content);
		DLTKASTBuildVisitor visitor = new DLTKASTBuildVisitor(module, content);

		Parser parser = new Parser(new IRuby() {});
		Node node = parser.parse("", reader, new RubyParserConfiguration(), problemReporter);
		if( node != null ) {
			node.accept(visitor);
		}
		return module;
	}

	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
	}

}
