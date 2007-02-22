header { 
	package org.eclipse.dltk.ruby.internal.parsers; 
	
	
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import antlr.CommonToken;
import antlr.MismatchedCharException;
import antlr.MismatchedTokenException;
import antlr.NoViableAltException;
import antlr.NoViableAltForCharException;
import antlr.ParserSharedInputState;
import antlr.RecognitionException;
import antlr.SemanticException;
import antlr.Token;
import antlr.TokenBuffer;
import antlr.TokenStream;
import antlr.TokenStreamException;
import antlr.collections.impl.BitSet;

import org.eclipse.dltk.ruby.internal.parser.RubySourceParser;


import org.eclipse.dltk.ast.declarations.*;
import org.eclipse.dltk.ast.expressions.*;
import org.eclipse.dltk.ast.references.*;
import org.eclipse.dltk.ast.statements.*;
}  

/**
 * PARSER    
 **/
class RubyParser extends Parser;

options {
	k = 6;
    buildAST = false;
   	defaultErrorHandler = true; // error handlig.
}

{
	private ModuleDeclaration decl;
	
	private int length;
	
	public void setLength( int len ) {
		this.length = len;
	}
		
	public void setModuleDeclaration( ModuleDeclaration moduleDeclaration ) {
		this.decl = moduleDeclaration;
	}
	
	public void reportError(RecognitionException arg0) {
		//if( reporter == null ) {
		//	return;
		//}
	    	//reporter.referenceContext = decl;

		if (arg0 instanceof NoViableAltException) {
			NoViableAltException ec = (NoViableAltException) arg0;
			String[] messages = { "Syntax Error:"+ arg0.getErrorMessage(),
					ec.token.getText() };
			int st = ec.token.getColumn() - 1;
			String sm = ec.token.getText();
			int et = st + ((sm != null) ? sm.length() : 1);
			if (st==-1) return ;
		//	reporter.handle(CompilerOptions.OFFSET, messages, messages, st, et);
		} else if (arg0 instanceof MismatchedTokenException) {
			MismatchedTokenException ec = (MismatchedTokenException) arg0;
			String[] messages = { "Syntax Error:" + arg0.getErrorMessage(),
					arg0.getErrorMessage(), ec.token.getText() };
			int st = ec.token.getColumn() - 1;
			String sm = ec.token.getText();
			int et = st + ((sm != null) ? sm.length() : 1);
			if (et>=this.length) {et=this.length-1;st-=2;}
		//	reporter.handle(CompilerOptions.OFFSET, messages, messages, st, et);
		} else if (arg0 instanceof SemanticException) {
			SemanticException ec = (SemanticException) arg0;
			String[] messages = { "Syntax Error:" + arg0.getErrorMessage(),
					arg0.getErrorMessage() };
			int st = ec.getColumn();
			int et = st + 1;
		//	reporter.handle(CompilerOptions.OFFSET, messages, messages, st, et);
		} else if (arg0 instanceof NoViableAltForCharException) {
			NoViableAltForCharException ec = (NoViableAltForCharException) arg0;
			String[] messages = { "Syntax Error:" + arg0.getErrorMessage(),
					arg0.getErrorMessage() };
			int st = ec.getColumn();
			int et = st + 1;
		//	reporter.handle(CompilerOptions.OFFSET, messages, messages, st, et);
		} else if (arg0 instanceof MismatchedCharException) {
			MismatchedCharException ec = (MismatchedCharException) arg0;
			String[] messages = { "Syntax Error:" + arg0.getErrorMessage(),
					arg0.getErrorMessage() };
			int st = ec.getColumn();
			int et = st + 1;
		//	reporter.handle(CompilerOptions.OFFSET, messages, messages, st, et);
		} else {
			throw new IllegalArgumentException("Unsupported Exception type");
		}	    				
	}

	public void reportError(String arg0) 
	{

	}
	public void reportWarning(String arg0) 
	{

	}
}

program : 
	{
    	Statement st;
	} 
	( st = classe 
		{
			if( st != null ) {
				decl.addStatement( st );
			}
		}
	| 
	st = statement
	{
		if( st != null ) {
			decl.addStatement( st );
		}
	}
	)*
;

classe returns [ Statement statement = null ]:
	{
		TypeDeclaration decl = null;
	}
	c:"class"^ 
		(t:IDENTIFIER 
			{
				decl = new TypeDeclaration( t, c.getColumn(), -1 );
			}
		| 
		{
			List bases = new ArrayList();
		}
		n:IDENTIFIER "<" b2:IDENTIFIER { bases.add(b2); } ("<" n3:IDENTIFIER { bases.add(n3); })*) 
		
		(statement)* 
		body_class "end"
		{
			if( decl != null ) {
				statement = decl;
			}
		}
     ;

body_class : (definition)+
           ;

definition: 
	(statement)* "def"^ IDENTIFIER (IDENTIFIER)* ( "("(IDENTIFIER)+ ("," IDENTIFIER)* ")" )* statement (statement)* "end"
          ;

statement returns [ Statement statement = null ]:
		("if"^ | "while"^) condition (statement)+ "end"
	  | "loop" "do"^ (statement)+ "end"
	  | "yield"^ "(" (IDENTIFIER | instruction) ")"!
	  | "return"^ ret   
          | IDENTIFIER "="^ (NUMBER | IDENTIFIER | STRING)
	  | IDENTIFIER "="^ instruction
	  | IDENTIFIER "="^ "(" (IDENTIFIER | NUMBER | STRING) ")"
	  | IDENTIFIER "="^ "(" instruction ")"
          | IDENTIFIER "="^ IDENTIFIER "(" (IDENTIFIER)+ ("," IDENTIFIER)* ")"
          | IDENTIFIER ( "(" (IDENTIFIER | NUMBER)+ ("," (IDENTIFIER | NUMBER))* ")" )* "do"^ "|" IDENTIFIER "|" (statement)* "end"
          | IDENTIFIER
          ;

condition : "("condition ")" ((operator | booleano) (IDENTIFIER | NUMBER))?
          | IDENTIFIER booleano IDENTIFIER
          | IDENTIFIER booleano NUMBER
          | NUMBER booleano IDENTIFIER
          | NUMBER booleano NUMBER
	  | instruction
	  | IDENTIFIER booleano instruction
          ;

instruction : NUMBER operator NUMBER
	    | IDENTIFIER operator NUMBER
	    | NUMBER operator IDENTIFIER
            | IDENTIFIER operator IDENTIFIER
            ;

booleano : "<"^
         | "<="^
         | ">="^
	 | ">"^
	 | "=="^
	 | "%"^
	 | "&"^
	 | "|"^;
	// | "("!
	// | ")"!
        // ;

ret : IDENTIFIER
    | NUMBER
    | instruction
    | "true"
    | "false"
    | "("! IDENTIFIER ")"!
    | "("! NUMBER ")"!
    | "("! "true" ")"!
    | "("! "false" ")"!
    ;

operator : "/"^
         | "*"^
	 | "+"^
	 | "-"^
	 | "%"^
         ;


/**
 * LEXER
 **/
class RubyLexer extends Lexer;

options {
  k = 6;
}
{
	private RubySourceParser fModule;

	public RubySourceParser getSourceHandler()
	{
	 	return fModule;
	}	
	public void setSourceHandler ( RubySourceParser fModule )
	{
	 	this.fModule=fModule;
	}
   int implicitLineJoiningLevel = 0;
}


LPAREN  : '(' {implicitLineJoiningLevel++;} ;

RPAREN  : ')' {implicitLineJoiningLevel--;} ;

LT      : '<'
        ;
LE      : "<="
        ;
GE      : ">="
        ;
        
COLON 	: ':' ;

COMMA	: ',' ;

SEMI	: ';' ;    

GT      : '>'
        ;
EGUAL   : "=="
        ;
DIV     : '/'
        ;
MUL     : '*'
        ;
ASSIGN  : '='
        ;
PLUS    : '+'
        ;
OR      : '|'
        ;  
AND     : '&'
        ;
SUB     : '-'
        ;
MOD     : '%'
        ;
        
QUESTION : '?';

NOT : '!';
LBRACE: '{' {implicitLineJoiningLevel++;} ;

RBRACE: '}'{implicitLineJoiningLevel--;} ;

LBRACK: '['{implicitLineJoiningLevel++;} ;

RBRACK: ']'{implicitLineJoiningLevel--;} ;

NUMBER  : ('0'..'9')+
        ;
	 
IDENTIFIER : ("@")* ('a'..'z'|'A'..'Z'|'.'|':'|'_'|'$')+ (NUMBER)*;

protected
ESC
	:	'\\' .
	;

STRING : 
	'\'' (ESC|~('\\'|'\n'|'\''))* '\''
	|   '"' (ESC|~('\\'|'\n'|'"'))* '"'
;
   
/** Treat a sequence of blank lines as a single blank line.  If
 *  nested within a (..), {..}, or [..], then ignore newlines.
 *  If the first newline starts in column one, they are to be ignored.
 */
NEWLINE
{
    int startCol = getColumn();
}
    :   (options{greedy=true;}:
    	'\r'|('\r')?'\n'
    {
       fModule.acceptLine(getColumn());    	        	    	
        newline();
    })+
        {
        	//if ( startCol==1 || implicitLineJoiningLevel>0 )
            $setType(Token.SKIP);		 
        }
    ;

WS  :   (' '|
		(
		{int k=getColumn();}'\t'
		{
			fModule.tab(-1,getColumn(),k);		
		}
		)		
		)+ 
		{$setType(Token.SKIP);		
		}
    ;    
COMMENT
{
    int startCol = getColumn();
}
    :   '#' (~'\n')* // let NEWLINE handle \n unless column = 1 for '#'
        { $setType(Token.SKIP); }
        ( {startCol==1}? ('\n' {
        	fModule.acceptLine(getColumn());        	
        	newline();})+ )?
    ;    
