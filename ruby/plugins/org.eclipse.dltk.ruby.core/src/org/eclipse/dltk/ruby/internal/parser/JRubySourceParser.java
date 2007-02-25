package org.eclipse.dltk.ruby.internal.parser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.Declaration;
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
	private static final Pattern COLON_FIXER = Pattern.compile("::(?=\\s|$)");
	private IProblemReporter problemReporter;
	private static final String missingName = "_missing_method_name_";
	
	private final List fixPositions = new ArrayList(); 
	
	private String fixBrokenDots (String content) {		
		Matcher matcher = DOT_FIXER.matcher(content);
		StringBuffer result = new StringBuffer();
		int regionStart = 0;
		while (matcher.find(regionStart)) {
			int offset = matcher.start();
			if (offset > regionStart)
				result.append(content.subSequence(regionStart, offset));
			result.append("." + missingName);
			fixPositions.add(new Integer(offset));
			regionStart = offset + 1;
		}
		if (regionStart < content.length() - 1)
			result.append(content.subSequence(regionStart, content.length()));
		if (regionStart == 0)
			return content; // no dots fixed
		else
			return result.toString();
	}
	
	private String fixBrokenColons (String content) {		
		Matcher matcher = COLON_FIXER.matcher(content);
		StringBuffer result = new StringBuffer();
		int regionStart = 0;
		while (matcher.find(regionStart)) {
			int offset = matcher.start();
			if (offset > regionStart)
				result.append(content.subSequence(regionStart, offset));
			result.append("::" + missingName);
			fixPositions.add(new Integer(offset));
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
		fixPositions.clear();
		content = fixBrokenDots(content);
		content = fixBrokenColons(content);
				
		ModuleDeclaration module = new ModuleDeclaration(content.length());
		StringReader reader = new StringReader(content);
		DLTKASTBuildVisitor visitor = new DLTKASTBuildVisitor(module, content);

		Parser parser = new Parser(new IRuby() {});
		Node node = parser.parse("", reader, new RubyParserConfiguration(), problemReporter);
		if( node != null ) {
			node.accept(visitor);
		}

		final int magicLength = missingName.length();
		
		ASTVisitor corrector = new ASTVisitor() {

			public boolean visitGeneral(ASTNode node) throws Exception {
				int st = 0;
				int en = 0;
				int n_st = 0;
				int n_en = 0;
				for (Iterator iterator = fixPositions.iterator(); iterator
						.hasNext();) {
					Integer pos = (Integer) iterator.next();
					int fixPos = pos.intValue();
					//starts
					if (node.sourceStart() > fixPos) {
						st++;
					}
					if (node.sourceEnd() > fixPos) {
						en++;
					}
					if (node instanceof Declaration) {
						Declaration declaration = (Declaration) node;
						if (declaration.getNameStart() > fixPos) {
							n_st++;
						}
						if (declaration.getNameEnd() > fixPos) {
							n_en++;
						}
					}
				}
				
				node.setStart(node.sourceStart() + st * magicLength);
				node.setEnd(node.sourceEnd() + en * magicLength);
				if (node instanceof Declaration) {
					Declaration declaration = (Declaration) node;
					declaration.setNameStart(declaration.getNameStart() + n_st*magicLength);
					declaration.setNameEnd(declaration.getNameEnd() + n_en*magicLength);
				}
				if (st == 0 && en == 0 && n_st == 0 && n_en == 0)
					return false;
				
				return true;
			}
			
		};
		
		
		try {
			module.traverse(corrector);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		
		return module;
	}

	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
	}

}
