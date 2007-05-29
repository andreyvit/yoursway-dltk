package org.eclipse.dltk.validators.internal.core.externalchecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.validators.core.AbstractValidator;
import org.eclipse.dltk.validators.core.IValidatorType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class ExternalChecker extends AbstractValidator {
	
	//private IPath path;
	private String path;
	private String arguments;
	private String commmand;
	private String syntax;
	private Pattern pattern;
	private String error;
	boolean initialized = false;
	private static final String PATH = "path";
	private static final String ARGUMENTS = "arguments";
	private static final String SYNTAX = "syntax";
	private static final String COMMAND = "command";
	private static final String ERROR = "error";
	private List rules = new ArrayList(); 
	
	private static String spatternSyntax = "((\\w:)?[^:]+):(\\d+)\\s+(.*)";
	private static Pattern patternSyntax = Pattern.compile(spatternSyntax);
	
	public void setCommand(String text){
		this.commmand = text;
	}
	
	public void setError(String err){
		error = err;
	}
	
	public String getError(){
		return error;
	}

	
	public String getCommand(){
		return commmand;
	}
	
	public void setSyntax(String s){
		this.syntax = s;
		this.pattern = Pattern.compile(s);
		
	}
	
	public String getSyntax(){
		return syntax;
	}
	
	private static class ExternalCheckerCodeModel {
		private String[] codeLines;

		private int[] codeLineLengths;

		public ExternalCheckerCodeModel(String code) {
			this.codeLines = code.split("\n");
			int count = this.codeLines.length;

			this.codeLineLengths = new int[count];

			int sum = 0;
			for (int i = 0; i < count; ++i) {
				this.codeLineLengths[i] = sum;
				sum += this.codeLines[i].length() + 1;
			}
		}

		public int[] getBounds(int lineNumber) {
			String codeLine = codeLines[lineNumber];
			String trimmedCodeLine = codeLine.trim();

			int start = codeLineLengths[lineNumber]
					+ codeLine.indexOf(trimmedCodeLine);
			int end = start + trimmedCodeLine.length();

			return new int[] { start, end };
		}
	}
	public static ExternalCheckerProblem parseProblem(String problem) {
		Matcher matcher = patternSyntax.matcher(problem);

		if (!matcher.find())
			return null;

		String file = matcher.group(1);
		int lineNumber = Integer.parseInt(matcher.group(3));
		String message = matcher.group(4);

		return new ExternalCheckerProblem(file, lineNumber-1, message);
	}
	
	public ExternalChecker(String id, String name, IValidatorType type)	{
		super(id, name, type);
	//	this.path = new Path("");
		this.path = "";
		this.arguments = "";
		this.commmand = "";
		this.syntax = "";
		this.error = "";
	}
	protected ExternalChecker(String id, IValidatorType type) {
		super(id, null, type);
		//this.path = new Path("");
		this.path = "";
		this.arguments = "";
		this.commmand = "";
		this.syntax = "";
		this.error="";
	}
	
	protected ExternalChecker(String id, Element element, IValidatorType type)
	throws IOException {
		super(id, null, type);
		loadInfo(element);
	}
	
	public void loadInfo(Element element) {
		if (initialized) {
			return;
		}
		super.loadFrom(element);
		initialized = true;
	//	this.path = new Path(element.getAttribute(PATH));
		this.commmand = new String(element.getAttribute(COMMAND));
		this.arguments = element.getAttribute(ARGUMENTS);
		this.error = element.getAttribute(ERROR);
	//	this..rules.add(element.)
		
	
	}
	public void storeTo(Document doc, Element element) {
		super.storeTo(doc, element);
	//	element.setAttribute(PATH, this.path.toOSString()); //$NON-NLS-1$
		element.setAttribute(ARGUMENTS, this.arguments);
		element.setAttribute(COMMAND, this.commmand);
	}

	public void setPath(String path) {
		initialized = true;
		String s = "";
		
		try {
			byte b[] = path.getBytes("UTF8");
			s = new String(b, "UTF8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.path = s;
	}
	
	public void setArguments(String arguments) {
		initialized = true;
		this.arguments = arguments;
	}

	public String getPath() {
		return this.path;

	}
	
	public String getArguments() {
		return arguments;
	}
	public IStatus validate(IResource resource, OutputStream console) {
		return Status.OK_STATUS;
	}
	public void setRules(Vector rules) {
		this.rules = rules;
	}
	public IStatus validate(ISourceModule module, OutputStream console) {
		IResource resource = module.getResource();
		if( resource == null ) {
			return Status.CANCEL_STATUS;
		}
		//String args = processArguments(resource);
	//	List cmd = new ArrayList();
	//	cmd.add(this.path.toOSString());
//		cmd.add(this.path);
	//	String[] sArgs = args.split("::");
	///	for (int i = 0; i < sArgs.length; i++) {
	//		cmd.add(sArgs[i]);
	//	}		

	//	String[] cmdLine = (String[]) cmd.toArray(new String[cmd.size()]);

		List lines = new ArrayList();
		String filepath = resource.getLocation().makeAbsolute().toOSString();
		String com = this.commmand;
		String wcard = this.error;
		
		StringBuilder sb = new StringBuilder();
		sb.append(com);
		sb.append(" ");
		sb.append("\"");;
		sb.append(filepath);
		sb.append("\"");
		
		String extcom = new String(sb);
		
		BufferedReader input = null;
		OutputStreamWriter output = null;
		Process process = null;
		try {
			try{
				process = Runtime.getRuntime().exec(extcom);
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
			input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
			String line = null;
			while ((line = input.readLine()) != null) {
				lines.add(line);
			//	Matcher matcher = pattern.matcher(line);
				
		//		while(matcher.find()){
		//			if (console != null) {
	    //					console.write((line + "\n").getBytes());
		//			}
			}
			
			for (Iterator it = lines.iterator(); it.hasNext();){
				
				String outputline = (String)it.next();
			//	outputline = "Error: C:\\hello.jpg line 67";
				try	{
					List tlist  = WildcardMatcher.match(wcard, outputline);
					for (Iterator iter = tlist.iterator(); iter.hasNext();){
						String cline = iter.toString();
						console.write((cline+"\n").getBytes());
					}
					
				}
				catch(WildcardException x){
					
				}
				
			}
			
			
		//	}
			
/*			String content = "";
			content = module.getSource();
			ExternalCheckerCodeModel model = new ExternalCheckerCodeModel(content);
			for (Iterator iterator = lines.iterator(); iterator.hasNext();) {
				String line1 = (String) iterator.next();
				ExternalCheckerProblem problem = parseProblem(line1);
				if (problem != null) {
					int[] bounds = model.getBounds(problem.getLineNumber());
				}

			}*/
		}
			
		catch(Exception e){
			System.out.println(e.toString());
		}
		return Status.OK_STATUS;
	}
	
/*	private String processArguments(IResource resource) {
		String path = resource.getLocation().makeAbsolute().toOSString();
		String arguments = this.arguments;
		if( arguments.indexOf("--")==-1) {
			arguments = "-- " + arguments;
		}
		String user = replaceSequence(arguments.replaceAll("\t", "::").replaceAll(" ", "::"), 'f', path);
		String result = "";
		
		return result + "::" + user;
	}*/

	/*private String replaceSequence(String from, char pattern, String value) {
		StringBuffer buffer = new StringBuffer();
		for( int i = 0; i < from.length(); ++i ) {
			char c = from.charAt(i);
			if( c == '%' && i < from.length() - 1 && from.charAt(i + 1) == pattern ) {
				buffer.append(value);
				i++;
			}
			else {
				buffer.append(c);
			}
		}
		return buffer.toString();
	}*/
	public boolean isValidatorValid(){
		return true;
	}
}
