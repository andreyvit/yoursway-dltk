package org.eclipse.dltk.validators.internal.core.externalchecker;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;


public class WildcardMatcher {

	public static List match(String wildcard, String s)throws WildcardException
	{
		List tokenList = new ArrayList();
		List tokens = parseWildcard(wildcard);
		
		int start = 0;
		
		for(int i=0; i<tokens.size(); i++){
			WildcardToken tok = (WildcardToken)(tokens.get(i));
			StringBuilder sb_spaces = new StringBuilder(s.substring(start));
			String workingcopy = sb_spaces.toString();
			int spaces = omitSpaces(workingcopy); 
			start = start + spaces;
			StringBuilder sb_nospaces = new StringBuilder(s.substring(start));
			
			
			if(tok.getType()=="string"){
				WildcardToken newstring = parseString((String)tok.getValue(), sb_nospaces.toString());
				
				
				if (newstring ==null){
					throw new WildcardException();
				}
				
				start = start + ((String)newstring.getValue()).length();
				tokenList.add(newstring);
			}
			
			if(tok.getType()=="wcard" && tok.getValue()=="file")
			{
				WildcardToken newstring = parseFilename(sb_nospaces.toString());
				if (newstring ==null){
					throw new WildcardException();
				}
				start = start + (((IPath)newstring.getValue())).toOSString().length();
				tokenList.add(newstring);
			}
			
			if(tok.getType()=="wcard" && tok.getValue()=="linenumber")
			{
				WildcardToken newstring = parseLineNumber(sb_nospaces.toString());
				if (newstring ==null){
					throw new WildcardException();
				}
				start = start + (((Integer)newstring.getValue())).toString().length();
				tokenList.add(newstring);
			}
			
		}
		return tokenList;
	}

	public static ArrayList parseWildcard(String wildcard)
	{
		ArrayList list = new ArrayList();
		String[] tokens = wildcard.split("(\\s)+");
		
		for(int i =0; i< tokens.length; i++){
			StringBuffer sb = new StringBuffer();
			int CLEAN = 0;
			int FULL = 1;
			int sbstatus = CLEAN;
			
			for(int j=0; j<tokens[i].length(); j++){
				
				if(tokens[i].charAt(j)=='%'){
					if (sbstatus ==FULL){
						list.add(new WildcardToken("string",sb.toString()));
						sbstatus = CLEAN;
						sb.delete(0, sb.length());
					}
					list.add(new WildcardToken("wcard", 
										recognizeWildcard(tokens[i].charAt(j+1))));
					
					j = j+1;
					continue;
				}
				else{
					sbstatus = FULL;
					sb.append(tokens[i].charAt(j));
				}
			}
			if (sbstatus ==FULL){
				list.add(new WildcardToken("string",sb.toString()));
				sbstatus = CLEAN;
				sb.delete(0, sb.length());
			}
		}
		
		return list;
	}
	
	public static String recognizeWildcard(char symbol)
	{	
		switch (symbol) {
		case 'f':
			return "file";
		case 'n':
			return "linenumber";
		case 'm':
			return "message";
		default:
			return null;
		}
	}
	
	public static WildcardToken parseFilename(String string){
		String sfilename = "[^.]*[.][^.\\s]*";
		Pattern pfilename = Pattern.compile(sfilename);
		Matcher filematcher = pfilename.matcher(string);
		
		if(filematcher.find()&& filematcher.start()==0){
			IPath path = new Path(filematcher.group());
			return new WildcardToken("path", path);
		}	
		return null;
	}
	
	public static WildcardToken parseLineNumber(String string){
		return new WildcardToken("linenumber", Integer.decode(string));
		     
	}
	
	public static WildcardToken parseString(String substr, String str){
		Pattern ps = Pattern.compile(substr);
		Matcher matcher = ps.matcher(str);
		if(matcher.find() && matcher.start()==0)
			return new WildcardToken("string", matcher.group());
		return null;
	}
	
	private static int omitSpaces(String s)
	{
		int spaces = 0;
		int j = 0;
		while(Character.isWhitespace(s.charAt(j))){
			spaces++;
			j++;
		}
		return spaces;
	}
}
