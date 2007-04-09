package org.eclipse.dltk.javascript.internal.ui.templates;

import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.javascript.parser.JavaScriptSourceParser;
import org.eclipse.dltk.javascript.internal.ui.formatting.JavaScriptFormattingConstants;
import org.eclipse.dltk.javascript.internal.ui.formatting.OldCodeFormatter;
import org.eclipse.dltk.ui.templates.ScriptTemplateContext;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateBuffer;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateException;

import com.xored.org.mozilla.javascript.ObjToIntMap.Iterator;

public class JavaScriptTemplateContext extends ScriptTemplateContext {

	public JavaScriptTemplateContext(TemplateContextType type, IDocument document,
			int completionOffset, int completionLength,
			ISourceModule sourceModule) {
		super(type, document, completionOffset, completionLength, sourceModule);
	}
	
	

	private static class FormattingAstVisitor extends ASTVisitor
	{	
		private int indentLevel;
		
		private String text;
		
		private void printContent(ASTNode node)
		{			
			int start = node.sourceStart();
			int end = node.sourceEnd();
			
			System.out.println("Begin index: " + start);
			System.out.println("End index: " + end);
			System.out.println("Real node type: " + node.getClass());
			
			if (start >= 0 && start < text.length() && end >= 0 && end < text.length()) {			
				System.out.println("=== Text ===");
				System.out.println(text.substring(start, end));
			}
		}
		
		public FormattingAstVisitor(String text){
			this.text = text;
			indentLevel = 0;
		}
		
		public boolean visit(Expression s) throws Exception {
			//System.out.println("FormattingAstVisitor.visit(Expression s)");
			//indentLevel++;
			
			printContent(s);
			
			return true;
		}

		public boolean visit(MethodDeclaration s) throws Exception {
			//System.out.println("FormattingAstVisitor.visit(MethodDeclaration s)");
			//indentLevel++;
			
			printContent(s);
			
			return true;
		}

		public boolean visit(ModuleDeclaration s) throws Exception {
			//System.out.println("FormattingAstVisitor.visit(ModuleDeclaration s)");
			//indentLevel++;
			
			printContent(s);
			
			return true;
		}

		public boolean visit(Statement s) throws Exception {
			System.out.println("FormattingAstVisitor.visit(Statement s)");
			// TODO Auto-generated method stub
			return true;
		}

		public boolean visit(TypeDeclaration s) throws Exception {
			//int start = s.sourceStart();
			//int end = s.sourceEnd();			
			//text.substring(start, end);			
			//System.out.println("FormattingAstVisitor.visit(TypeDeclaration s)");
			
			printContent(s);
			
			// TODO Auto-generated method stub
			return true;
		}

		public boolean visitGeneral(ASTNode node) throws Exception {
			return true;
		}
	
		public boolean endvisit(Expression s) throws Exception {
			//--indentLevel;
			return true;
		}

		public boolean endvisit(MethodDeclaration s) throws Exception {
			//--indentLevel;
			return true;
		}

		public boolean endvisit(ModuleDeclaration s) throws Exception {
			//--indentLevel;
			return true;
		}

		public boolean endvisit(Statement s) throws Exception {
			//--indentLevel;
			return true;
		}

		public boolean endvisit(TypeDeclaration s) throws Exception {
			//--indentLevel;
			return true;
		}		
	}

	
	// Just for testing
	public TemplateBuffer evaluate(Template template)
			throws BadLocationException, TemplateException {
		if (!canEvaluate(template)) {
			return null;
		}

		String indentTo = calculateIndent(getDocument(), getStart());
		
		System.out.println("Indent: |" + indentTo + "|");

		String delimeter = TextUtilities.getDefaultLineDelimiter(getDocument());
		String lines = template.getPattern();
		StringBuffer bf=new StringBuffer();
		
		boolean in = false;
		HashMap qs=new HashMap();
		String sp0 = "specialSecret12435Id";
		String sp=sp0+'0';
		String cId=null;
		int r=0;
		int pos=-1;
		for (int a=0;a<lines.length();a++){
			char c=lines.charAt(a);
			if (c=='$'){
				if (a<lines.length()-1) if (lines.charAt(a+1)=='{'){
					in=true;
					bf.append(sp);
					pos=a;
				}
			}
			
			if (in){
				if (c=='}'){
					String tv=lines.substring(pos,a+1);
					if (tv.equals("${cursor}")){
						cId=sp;
					}
					qs.put(sp, tv);
					r++;
					sp=sp0+r;
					in=false;
					continue;
				}
			}
			if (!in){
				bf.append(c);
			}
		}
		OldCodeFormatter formater = new OldCodeFormatter(Collections.EMPTY_MAP);		
		String string = bf.toString();		
		String formatted = formater.formatString(string,new StringBuffer());
		java.util.Iterator it=qs.keySet().iterator();
		while (it.hasNext()){
			String key=(String) it.next();
			String value=(String) qs.get(key);
			if (key==cId){
				formatted=replaceSeq(formatted,key+";", value);
			}
			else formatted=replaceSeq(formatted,key, value);
		}		
		template = new Template(template.getName(), template
					.getDescription(), template.getContextTypeId(), formatted, template.isAutoInsertable());		

		return super.evaluate(template);
	}
	private static String replaceSeq(String sq, CharSequence target, CharSequence replacement) {
        return Pattern.compile(target.toString(), Pattern.LITERAL).matcher(
            sq).replaceAll(Matcher.quoteReplacement(replacement.toString()));
    }
}
