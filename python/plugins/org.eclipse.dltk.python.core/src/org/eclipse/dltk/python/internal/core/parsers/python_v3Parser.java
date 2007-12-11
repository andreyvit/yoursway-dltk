// $ANTLR 3.0.1 /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g 2007-12-11 14:45:51

package org.eclipse.dltk.python.internal.core.parsers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.declarations.Decorator;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.ExpressionList;
import org.eclipse.dltk.ast.expressions.NumericLiteral;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.python.parser.ast.PythonArgument;
import org.eclipse.dltk.python.parser.ast.PythonAssertStatement;
import org.eclipse.dltk.python.parser.ast.PythonClassDeclaration;
import org.eclipse.dltk.python.parser.ast.PythonDelStatement;
import org.eclipse.dltk.python.parser.ast.PythonExceptStatement;
import org.eclipse.dltk.python.parser.ast.PythonForStatement;
import org.eclipse.dltk.python.parser.ast.PythonImportFromStatement;
import org.eclipse.dltk.python.parser.ast.PythonImportStatement;
import org.eclipse.dltk.python.parser.ast.PythonRaiseStatement;
import org.eclipse.dltk.python.parser.ast.PythonTryStatement;
import org.eclipse.dltk.python.parser.ast.PythonWhileStatement;
import org.eclipse.dltk.python.parser.ast.PythonYieldStatement;
import org.eclipse.dltk.python.parser.ast.expressions.Assignment;
import org.eclipse.dltk.python.parser.ast.expressions.BinaryExpression;
import org.eclipse.dltk.python.parser.ast.expressions.CallHolder;
import org.eclipse.dltk.python.parser.ast.expressions.ComplexNumericLiteral;
import org.eclipse.dltk.python.parser.ast.expressions.EmptyExpression;
import org.eclipse.dltk.python.parser.ast.expressions.ExtendedVariableReference;
import org.eclipse.dltk.python.parser.ast.expressions.IndexHolder;
import org.eclipse.dltk.python.parser.ast.expressions.NotStrictAssignment;
import org.eclipse.dltk.python.parser.ast.expressions.PrintExpression;
import org.eclipse.dltk.python.parser.ast.expressions.PythonAllImportExpression;
import org.eclipse.dltk.python.parser.ast.expressions.PythonDictExpression;
import org.eclipse.dltk.python.parser.ast.expressions.PythonForListExpression;
import org.eclipse.dltk.python.parser.ast.expressions.PythonFunctionDecorator;
import org.eclipse.dltk.python.parser.ast.expressions.PythonImportAsExpression;
import org.eclipse.dltk.python.parser.ast.expressions.PythonImportExpression;
import org.eclipse.dltk.python.parser.ast.expressions.PythonLambdaExpression;
import org.eclipse.dltk.python.parser.ast.expressions.PythonListExpression;
import org.eclipse.dltk.python.parser.ast.expressions.PythonListForExpression;
import org.eclipse.dltk.python.parser.ast.expressions.PythonSubscriptExpression;
import org.eclipse.dltk.python.parser.ast.expressions.PythonTestListExpression;
import org.eclipse.dltk.python.parser.ast.expressions.PythonTupleExpression;
import org.eclipse.dltk.python.parser.ast.expressions.UnaryExpression;
import org.eclipse.dltk.python.parser.ast.statements.BreakStatement;
import org.eclipse.dltk.python.parser.ast.statements.ContinueStatement;
import org.eclipse.dltk.python.parser.ast.statements.EmptyStatement;
import org.eclipse.dltk.python.parser.ast.statements.ExecStatement;
import org.eclipse.dltk.python.parser.ast.statements.IfStatement;
import org.eclipse.dltk.python.parser.ast.statements.ReturnStatement;
import org.eclipse.dltk.python.parser.ast.statements.TryFinallyStatement;
import org.eclipse.dltk.python.parser.ast.PythonWithStatement;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class python_v3Parser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "INDENT", "DEDENT", "NEWLINE", "DECORATOR_S", "LPAREN", "RPAREN", "NAME", "COLON", "COMMA", "STAR", "DOUBLESTAR", "ASSIGN", "SEMI", "PLUSEQUAL", "MINUSEQUAL", "STAREQUAL", "SLASHEQUAL", "PERCENTEQUAL", "AMPEREQUAL", "VBAREQUAL", "CIRCUMFLEXEQUAL", "LEFTSHIFTEQUAL", "RIGHTSHIFTEQUAL", "DOUBLESTAREQUAL", "DOUBLESLASHEQUAL", "RIGHTSHIFT", "DOT", "LESS", "GREATER", "EQUAL", "GREATEREQUAL", "LESSEQUAL", "ALT_NOTEQUAL", "NOTEQUAL", "VBAR", "CIRCUMFLEX", "AMPER", "LEFTSHIFT", "PLUS", "MINUS", "SLASH", "PERCENT", "DOUBLESLASH", "TILDE", "LBRACK", "RBRACK", "LCURLY", "RCURLY", "BACKQUOTE", "INT", "LONGINT", "FLOAT", "COMPLEX", "STRING", "POINTFLOAT", "EXPONENTFLOAT", "DIGITS", "FRACTION", "Exponent", "ESC", "CONTINUED_LINE", "WS", "LEADING_WS", "COMMENT", "'def'", "'print'", "'del'", "'pass'", "'break'", "'continue'", "'return'", "'yield'", "'raise'", "'import'", "'from'", "'as'", "'global'", "'exec'", "'in'", "'assert'", "'if'", "'elif'", "'else'", "'while'", "'for'", "'try'", "'except'", "'finally'", "'or'", "'and'", "'not'", "'is'", "'lambda'", "'with'", "'class'"
    };
    public static final int DOUBLESLASH=46;
    public static final int EXPONENTFLOAT=59;
    public static final int BACKQUOTE=52;
    public static final int SLASHEQUAL=20;
    public static final int CONTINUED_LINE=64;
    public static final int LBRACK=48;
    public static final int STAR=13;
    public static final int CIRCUMFLEXEQUAL=24;
    public static final int DOUBLESTAR=14;
    public static final int ESC=63;
    public static final int DIGITS=60;
    public static final int Exponent=62;
    public static final int GREATEREQUAL=34;
    public static final int COMPLEX=56;
    public static final int FLOAT=55;
    public static final int DEDENT=5;
    public static final int RIGHTSHIFTEQUAL=26;
    public static final int EOF=-1;
    public static final int POINTFLOAT=58;
    public static final int LPAREN=8;
    public static final int INDENT=4;
    public static final int PLUSEQUAL=17;
    public static final int LEADING_WS=66;
    public static final int NOTEQUAL=37;
    public static final int MINUSEQUAL=18;
    public static final int VBAR=38;
    public static final int RPAREN=9;
    public static final int NAME=10;
    public static final int SLASH=44;
    public static final int GREATER=32;
    public static final int COMMA=12;
    public static final int AMPER=40;
    public static final int DOUBLESTAREQUAL=27;
    public static final int EQUAL=33;
    public static final int TILDE=47;
    public static final int LESS=31;
    public static final int LEFTSHIFTEQUAL=25;
    public static final int LEFTSHIFT=41;
    public static final int PLUS=42;
    public static final int COMMENT=67;
    public static final int DOT=30;
    public static final int RBRACK=49;
    public static final int PERCENT=45;
    public static final int LCURLY=50;
    public static final int INT=53;
    public static final int MINUS=43;
    public static final int RIGHTSHIFT=29;
    public static final int SEMI=16;
    public static final int COLON=11;
    public static final int DOUBLESLASHEQUAL=28;
    public static final int WS=65;
    public static final int NEWLINE=6;
    public static final int AMPEREQUAL=22;
    public static final int VBAREQUAL=23;
    public static final int RCURLY=51;
    public static final int ASSIGN=15;
    public static final int LONGINT=54;
    public static final int DECORATOR_S=7;
    public static final int FRACTION=61;
    public static final int PERCENTEQUAL=21;
    public static final int LESSEQUAL=35;
    public static final int STAREQUAL=19;
    public static final int CIRCUMFLEX=39;
    public static final int STRING=57;
    public static final int ALT_NOTEQUAL=36;

        public python_v3Parser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "/home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g"; }


    public DLTKPythonErrorReporter reporter;
    	
    public ModuleDeclaration decl;
    	
    public int length;
    public DLTKTokenConverter converter;
    DLTKToken toDLTK(Token token) {
    	return converter.convert(token);
    }
    public void emitErrorMessage(String msg) {
    	reporter.reportMessage(msg);
    }
    public void reportError(RecognitionException e) {
    	if( reporter != null ) {
    		reporter.reportError(e);
    	}
    }
    public void setStartEndForEmbracedExpr(Expression exp, Token lb, Token rb)
    {
    	exp.setStart(toDLTK(lb).getColumn());
    	exp.setEnd(toDLTK(rb).getColumn()+1);
    }



    // $ANTLR start file_input
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:134:1: file_input : ( NEWLINE | s= stmt )* EOF ;
    public final void file_input() throws RecognitionException {
        ArrayList s = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:134:11: ( ( NEWLINE | s= stmt )* EOF )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:135:6: ( NEWLINE | s= stmt )* EOF
            {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:135:6: ( NEWLINE | s= stmt )*
            loop1:
            do {
                int alt1=3;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==NEWLINE) ) {
                    alt1=1;
                }
                else if ( ((LA1_0>=DECORATOR_S && LA1_0<=LPAREN)||LA1_0==NAME||(LA1_0>=PLUS && LA1_0<=MINUS)||(LA1_0>=TILDE && LA1_0<=LBRACK)||LA1_0==LCURLY||(LA1_0>=BACKQUOTE && LA1_0<=STRING)||(LA1_0>=68 && LA1_0<=78)||(LA1_0>=80 && LA1_0<=81)||(LA1_0>=83 && LA1_0<=84)||(LA1_0>=87 && LA1_0<=89)||LA1_0==94||(LA1_0>=96 && LA1_0<=98)) ) {
                    alt1=2;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:136:7: NEWLINE
            	    {
            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_file_input101); 

            	    }
            	    break;
            	case 2 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:138:7: s= stmt
            	    {
            	    pushFollow(FOLLOW_stmt_in_file_input126);
            	    s=stmt();
            	    _fsp--;

            	     
            	        				//statements.addAll( s );     
            	        				if( s != null ) {
            	        					Iterator i = s.iterator();
            	        					while( i.hasNext() ) {
            	        						Statement sst = (Statement)i.next();
            	        						if( sst != null ) {
            	    		    					decl.addStatement( sst );
            	        						}
            	        					}
            	        				}
            	        			

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            match(input,EOF,FOLLOW_EOF_in_file_input157); 

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return ;
    }
    // $ANTLR end file_input


    // $ANTLR start decorator
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:163:1: decorator returns [ Decorator decorator = null ] : dec= DECORATOR_S dottedName= dot_name (lp0= LPAREN arguments= arglist rp0= RPAREN )? NEWLINE ;
    public final Decorator decorator() throws RecognitionException {
        Decorator decorator =  null;

        Token dec=null;
        Token lp0=null;
        Token rp0=null;
        Token dottedName = null;

        ExpressionList arguments = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:163:49: (dec= DECORATOR_S dottedName= dot_name (lp0= LPAREN arguments= arglist rp0= RPAREN )? NEWLINE )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:164:2: dec= DECORATOR_S dottedName= dot_name (lp0= LPAREN arguments= arglist rp0= RPAREN )? NEWLINE
            {
            dec=(Token)input.LT(1);
            match(input,DECORATOR_S,FOLLOW_DECORATOR_S_in_decorator185); 
            pushFollow(FOLLOW_dot_name_in_decorator192);
            dottedName=dot_name();
            _fsp--;

            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:166:2: (lp0= LPAREN arguments= arglist rp0= RPAREN )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==LPAREN) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:167:4: lp0= LPAREN arguments= arglist rp0= RPAREN
                    {
                    lp0=(Token)input.LT(1);
                    match(input,LPAREN,FOLLOW_LPAREN_in_decorator204); 
                    pushFollow(FOLLOW_arglist_in_decorator213);
                    arguments=arglist();
                    _fsp--;

                    rp0=(Token)input.LT(1);
                    match(input,RPAREN,FOLLOW_RPAREN_in_decorator221); 

                    				decorator = new PythonFunctionDecorator( toDLTK( dottedName), toDLTK(dec), toDLTK(rp0), arguments );
                    			

                    }
                    break;

            }


            		if( decorator == null ) {
            			decorator = new PythonFunctionDecorator( toDLTK( dottedName ), toDLTK(dec) );
            		}
            	
            match(input,NEWLINE,FOLLOW_NEWLINE_in_decorator237); 

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return decorator;
    }
    // $ANTLR end decorator


    // $ANTLR start decoraror_list
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:183:1: decoraror_list returns [List decorators = new ArrayList() ] : (dec= decorator )+ ;
    public final List decoraror_list() throws RecognitionException {
        List decorators =  new ArrayList();

        Decorator dec = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:183:60: ( (dec= decorator )+ )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:184:2: (dec= decorator )+
            {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:184:2: (dec= decorator )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==DECORATOR_S) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:185:3: dec= decorator
            	    {
            	    pushFollow(FOLLOW_decorator_in_decoraror_list259);
            	    dec=decorator();
            	    _fsp--;


            	    			if( dec != null ) {
            	    				decorators.add( dec );
            	    			}
            	    		

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return decorators;
    }
    // $ANTLR end decoraror_list


    // $ANTLR start funcdef
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:195:1: funcdef returns [ MethodDeclaration methodDeclaration = null; ] : (decorators= decoraror_list )? w= 'def' tu= NAME parameters[ params ] e= COLON body= suite ;
    public final MethodDeclaration funcdef() throws RecognitionException {
        MethodDeclaration methodDeclaration =  null;;

        Token w=null;
        Token tu=null;
        Token e=null;
        List decorators = null;

        Block body = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:195:64: ( (decorators= decoraror_list )? w= 'def' tu= NAME parameters[ params ] e= COLON body= suite )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:196:2: (decorators= decoraror_list )? w= 'def' tu= NAME parameters[ params ] e= COLON body= suite
            {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:196:2: (decorators= decoraror_list )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==DECORATOR_S) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:197:3: decorators= decoraror_list
                    {
                    pushFollow(FOLLOW_decoraror_list_in_funcdef294);
                    decorators=decoraror_list();
                    _fsp--;


                    }
                    break;

            }

            w=(Token)input.LT(1);
            match(input,68,FOLLOW_68_in_funcdef306); 
            tu=(Token)input.LT(1);
            match(input,NAME,FOLLOW_NAME_in_funcdef314); 

            		methodDeclaration = new MethodDeclaration( toDLTK( w ), toDLTK( tu ) );
            		if( decorators != null ) {
            			methodDeclaration.setDecorators( decorators );
            		}
            		//this.fParameters.clear();
            		
            		List params = new ArrayList();
            		
            		//Block body;
               	
            pushFollow(FOLLOW_parameters_in_funcdef320);
            parameters( params );
            _fsp--;


            		methodDeclaration.acceptArguments( params ); 
            	
            e=(Token)input.LT(1);
            match(input,COLON,FOLLOW_COLON_in_funcdef338); 
            pushFollow(FOLLOW_suite_in_funcdef352);
            body=suite();
            _fsp--;


            		methodDeclaration.acceptBody( body );
            	

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return methodDeclaration;
    }
    // $ANTLR end funcdef


    // $ANTLR start parameters
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:224:1: parameters[ List params ] : LPAREN ( varargslist[ params ] )? RPAREN ;
    public final void parameters(List params) throws RecognitionException {
        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:224:26: ( LPAREN ( varargslist[ params ] )? RPAREN )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:225:2: LPAREN ( varargslist[ params ] )? RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_parameters374); 
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:226:2: ( varargslist[ params ] )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==LPAREN||LA5_0==NAME||(LA5_0>=STAR && LA5_0<=DOUBLESTAR)) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:226:3: varargslist[ params ]
                    {
                    pushFollow(FOLLOW_varargslist_in_parameters379);
                    varargslist( params  );
                    _fsp--;


                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_parameters386); 

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return ;
    }
    // $ANTLR end parameters


    // $ANTLR start varargslist
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:231:1: varargslist[ List params ] : ( defparameter[params] ( options {greedy=true; } : COMMA defparameter[ params ] )* ( COMMA ( STAR tu= NAME ( COMMA DOUBLESTAR t1= NAME )? | DOUBLESTAR t2= NAME )? )? | STAR m= NAME ( COMMA DOUBLESTAR m1= NAME )? | DOUBLESTAR m2= NAME );
    public final void varargslist(List params) throws RecognitionException {
        Token tu=null;
        Token t1=null;
        Token t2=null;
        Token m=null;
        Token m1=null;
        Token m2=null;

        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:231:27: ( defparameter[params] ( options {greedy=true; } : COMMA defparameter[ params ] )* ( COMMA ( STAR tu= NAME ( COMMA DOUBLESTAR t1= NAME )? | DOUBLESTAR t2= NAME )? )? | STAR m= NAME ( COMMA DOUBLESTAR m1= NAME )? | DOUBLESTAR m2= NAME )
            int alt11=3;
            switch ( input.LA(1) ) {
            case LPAREN:
            case NAME:
                {
                alt11=1;
                }
                break;
            case STAR:
                {
                alt11=2;
                }
                break;
            case DOUBLESTAR:
                {
                alt11=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("231:1: varargslist[ List params ] : ( defparameter[params] ( options {greedy=true; } : COMMA defparameter[ params ] )* ( COMMA ( STAR tu= NAME ( COMMA DOUBLESTAR t1= NAME )? | DOUBLESTAR t2= NAME )? )? | STAR m= NAME ( COMMA DOUBLESTAR m1= NAME )? | DOUBLESTAR m2= NAME );", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:232:2: defparameter[params] ( options {greedy=true; } : COMMA defparameter[ params ] )* ( COMMA ( STAR tu= NAME ( COMMA DOUBLESTAR t1= NAME )? | DOUBLESTAR t2= NAME )? )?
                    {
                    pushFollow(FOLLOW_defparameter_in_varargslist397);
                    defparameter(params);
                    _fsp--;

                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:233:2: ( options {greedy=true; } : COMMA defparameter[ params ] )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==COMMA) ) {
                            int LA6_1 = input.LA(2);

                            if ( (LA6_1==LPAREN||LA6_1==NAME) ) {
                                alt6=1;
                            }


                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:234:28: COMMA defparameter[ params ]
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_varargslist414); 
                    	    pushFollow(FOLLOW_defparameter_in_varargslist419);
                    	    defparameter( params );
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:237:9: ( COMMA ( STAR tu= NAME ( COMMA DOUBLESTAR t1= NAME )? | DOUBLESTAR t2= NAME )? )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==COMMA) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:237:10: COMMA ( STAR tu= NAME ( COMMA DOUBLESTAR t1= NAME )? | DOUBLESTAR t2= NAME )?
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_varargslist435); 
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:238:14: ( STAR tu= NAME ( COMMA DOUBLESTAR t1= NAME )? | DOUBLESTAR t2= NAME )?
                            int alt8=3;
                            int LA8_0 = input.LA(1);

                            if ( (LA8_0==STAR) ) {
                                alt8=1;
                            }
                            else if ( (LA8_0==DOUBLESTAR) ) {
                                alt8=2;
                            }
                            switch (alt8) {
                                case 1 :
                                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:238:16: STAR tu= NAME ( COMMA DOUBLESTAR t1= NAME )?
                                    {
                                    match(input,STAR,FOLLOW_STAR_in_varargslist452); 
                                    tu=(Token)input.LT(1);
                                    match(input,NAME,FOLLOW_NAME_in_varargslist458); 
                                     
                                    				params.add( new PythonArgument( toDLTK( tu ) ) ); 
                                    			
                                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:242:15: ( COMMA DOUBLESTAR t1= NAME )?
                                    int alt7=2;
                                    int LA7_0 = input.LA(1);

                                    if ( (LA7_0==COMMA) ) {
                                        alt7=1;
                                    }
                                    switch (alt7) {
                                        case 1 :
                                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:242:16: COMMA DOUBLESTAR t1= NAME
                                            {
                                            match(input,COMMA,FOLLOW_COMMA_in_varargslist493); 
                                            match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_varargslist495); 
                                            t1=(Token)input.LT(1);
                                            match(input,NAME,FOLLOW_NAME_in_varargslist501); 
                                            	
                                            					params.add( new PythonArgument( toDLTK( t1 ) ) );
                                            				

                                            }
                                            break;

                                    }


                                    }
                                    break;
                                case 2 :
                                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:247:17: DOUBLESTAR t2= NAME
                                    {
                                    match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_varargslist543); 
                                    t2=(Token)input.LT(1);
                                    match(input,NAME,FOLLOW_NAME_in_varargslist549); 

                                                				params.add( new PythonArgument( toDLTK( t2 ) ) );
                                                			

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:253:4: STAR m= NAME ( COMMA DOUBLESTAR m1= NAME )?
                    {
                    match(input,STAR,FOLLOW_STAR_in_varargslist587); 
                    m=(Token)input.LT(1);
                    match(input,NAME,FOLLOW_NAME_in_varargslist593); 
                     
                    			params.add( new PythonArgument( toDLTK( m ) ) );
                    		
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:257:2: ( COMMA DOUBLESTAR m1= NAME )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==COMMA) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:257:3: COMMA DOUBLESTAR m1= NAME
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_varargslist602); 
                            match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_varargslist607); 
                            m1=(Token)input.LT(1);
                            match(input,NAME,FOLLOW_NAME_in_varargslist613); 

                            				params.add( new PythonArgument( toDLTK( m1 ) ) );
                            			

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:263:4: DOUBLESTAR m2= NAME
                    {
                    match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_varargslist627); 
                    m2=(Token)input.LT(1);
                    match(input,NAME,FOLLOW_NAME_in_varargslist633); 

                    			params.add( new PythonArgument( toDLTK( m2 ) ) );
                    		

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return ;
    }
    // $ANTLR end varargslist


    // $ANTLR start defparameter
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:270:1: defparameter[ List params ] : lastParam= fpdef[ params ] ( ASSIGN initExpr= test )? ;
    public final void defparameter(List params) throws RecognitionException {
        PythonArgument lastParam = null;

        Expression initExpr = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:270:28: (lastParam= fpdef[ params ] ( ASSIGN initExpr= test )? )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:271:2: lastParam= fpdef[ params ] ( ASSIGN initExpr= test )?
            {
            pushFollow(FOLLOW_fpdef_in_defparameter652);
            lastParam=fpdef( params );
            _fsp--;

            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:272:2: ( ASSIGN initExpr= test )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==ASSIGN) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:272:3: ASSIGN initExpr= test
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_defparameter657); 
                    pushFollow(FOLLOW_test_in_defparameter663);
                    initExpr=test();
                    _fsp--;

                    					
                    			if( lastParam != null ) {
                    				lastParam.assign( initExpr );
                    			}				
                    		

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return ;
    }
    // $ANTLR end defparameter


    // $ANTLR start fpdef
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:283:1: fpdef[ List params ] returns [ PythonArgument argument = null ] : (tu= NAME | LPAREN fplist[params] RPAREN );
    public final PythonArgument fpdef(List params) throws RecognitionException {
        PythonArgument argument =  null;

        Token tu=null;

        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:283:65: (tu= NAME | LPAREN fplist[params] RPAREN )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==NAME) ) {
                alt13=1;
            }
            else if ( (LA13_0==LPAREN) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("283:1: fpdef[ List params ] returns [ PythonArgument argument = null ] : (tu= NAME | LPAREN fplist[params] RPAREN );", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:284:6: tu= NAME
                    {
                    tu=(Token)input.LT(1);
                    match(input,NAME,FOLLOW_NAME_in_fpdef695); 

                    			argument = new PythonArgument( toDLTK( tu ) );
                    			params.add( argument );
                    		

                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:289:3: LPAREN fplist[params] RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_fpdef706); 
                    pushFollow(FOLLOW_fplist_in_fpdef708);
                    fplist(params);
                    _fsp--;

                    match(input,RPAREN,FOLLOW_RPAREN_in_fpdef711); 

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return argument;
    }
    // $ANTLR end fpdef


    // $ANTLR start fplist
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:293:1: fplist[List params ] : fpdef[ params ] ( options {greedy=true; } : COMMA fpdef[ params ] )* ( COMMA )? ;
    public final void fplist(List params) throws RecognitionException {
        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:293:22: ( fpdef[ params ] ( options {greedy=true; } : COMMA fpdef[ params ] )* ( COMMA )? )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:294:2: fpdef[ params ] ( options {greedy=true; } : COMMA fpdef[ params ] )* ( COMMA )?
            {
            pushFollow(FOLLOW_fpdef_in_fplist726);
            fpdef( params );
            _fsp--;

            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:295:2: ( options {greedy=true; } : COMMA fpdef[ params ] )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==COMMA) ) {
                    int LA14_1 = input.LA(2);

                    if ( (LA14_1==LPAREN||LA14_1==NAME) ) {
                        alt14=1;
                    }


                }


                switch (alt14) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:296:3: COMMA fpdef[ params ]
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_fplist744); 
            	    pushFollow(FOLLOW_fpdef_in_fplist746);
            	    fpdef( params );
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:298:2: ( COMMA )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==COMMA) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:298:3: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_fplist755); 

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return ;
    }
    // $ANTLR end fplist


    // $ANTLR start simple_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:306:1: simple_stmt[ List stmts ] : small_stmt[ stmts ] ( options {greedy=true; } : SEMI small_stmt[ stmts ] )* ( SEMI )? NEWLINE ;
    public final void simple_stmt(List stmts) throws RecognitionException {
        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:306:27: ( small_stmt[ stmts ] ( options {greedy=true; } : SEMI small_stmt[ stmts ] )* ( SEMI )? NEWLINE )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:307:2: small_stmt[ stmts ] ( options {greedy=true; } : SEMI small_stmt[ stmts ] )* ( SEMI )? NEWLINE
            {
            pushFollow(FOLLOW_small_stmt_in_simple_stmt773);
            small_stmt( stmts );
            _fsp--;

            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:308:2: ( options {greedy=true; } : SEMI small_stmt[ stmts ] )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==SEMI) ) {
                    int LA16_1 = input.LA(2);

                    if ( (LA16_1==LPAREN||LA16_1==NAME||(LA16_1>=PLUS && LA16_1<=MINUS)||(LA16_1>=TILDE && LA16_1<=LBRACK)||LA16_1==LCURLY||(LA16_1>=BACKQUOTE && LA16_1<=STRING)||(LA16_1>=69 && LA16_1<=78)||(LA16_1>=80 && LA16_1<=81)||LA16_1==83||LA16_1==94||LA16_1==96) ) {
                        alt16=1;
                    }


                }


                switch (alt16) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:309:3: SEMI small_stmt[ stmts ]
            	    {
            	    match(input,SEMI,FOLLOW_SEMI_in_simple_stmt792); 
            	    pushFollow(FOLLOW_small_stmt_in_simple_stmt794);
            	    small_stmt( stmts );
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:310:6: ( SEMI )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==SEMI) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:310:7: SEMI
                    {
                    match(input,SEMI,FOLLOW_SEMI_in_simple_stmt804); 

                    }
                    break;

            }

            match(input,NEWLINE,FOLLOW_NEWLINE_in_simple_stmt809); 

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return ;
    }
    // $ANTLR end simple_stmt


    // $ANTLR start stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:312:4: stmt returns [ ArrayList statements = new ArrayList( ) ] : ( simple_stmt[ simpleStatements ] | compoundStatement= compound_stmt ) ;
    public final ArrayList stmt() throws RecognitionException {
        ArrayList statements =  new ArrayList( );

        Statement compoundStatement = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:312:60: ( ( simple_stmt[ simpleStatements ] | compoundStatement= compound_stmt ) )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:313:2: ( simple_stmt[ simpleStatements ] | compoundStatement= compound_stmt )
            {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:313:2: ( simple_stmt[ simpleStatements ] | compoundStatement= compound_stmt )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==LPAREN||LA18_0==NAME||(LA18_0>=PLUS && LA18_0<=MINUS)||(LA18_0>=TILDE && LA18_0<=LBRACK)||LA18_0==LCURLY||(LA18_0>=BACKQUOTE && LA18_0<=STRING)||(LA18_0>=69 && LA18_0<=78)||(LA18_0>=80 && LA18_0<=81)||LA18_0==83||LA18_0==94||LA18_0==96) ) {
                alt18=1;
            }
            else if ( (LA18_0==DECORATOR_S||LA18_0==68||LA18_0==84||(LA18_0>=87 && LA18_0<=89)||(LA18_0>=97 && LA18_0<=98)) ) {
                alt18=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("313:2: ( simple_stmt[ simpleStatements ] | compoundStatement= compound_stmt )", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:314:3: simple_stmt[ simpleStatements ]
                    {

                    		List simpleStatements = new ArrayList();
                    		
                    pushFollow(FOLLOW_simple_stmt_in_stmt830);
                    simple_stmt( simpleStatements );
                    _fsp--;

                     statements.addAll( simpleStatements ); 

                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:319:3: compoundStatement= compound_stmt
                    {
                    pushFollow(FOLLOW_compound_stmt_in_stmt850);
                    compoundStatement=compound_stmt();
                    _fsp--;

                     statements.add( compoundStatement ); 

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return statements;
    }
    // $ANTLR end stmt


    // $ANTLR start small_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:326:1: small_stmt[ List stmts ] returns [ Statement rstatement = null ] : (statement1= expr_stmt | statement2= print_stmt | statement3= del_stmt | statement4= pass_stmt | statement5= flow_stmt | statement6= import_stmt | statement7= global_stmt | statement8= exec_stmt | statement9= assert_stmt ) ;
    public final Statement small_stmt(List stmts) throws RecognitionException {
        Statement rstatement =  null;

        Expression statement1 = null;

        Statement statement2 = null;

        Statement statement3 = null;

        Statement statement4 = null;

        Statement statement5 = null;

        Statement statement6 = null;

        Statement statement7 = null;

        Statement statement8 = null;

        PythonAssertStatement statement9 = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:326:66: ( (statement1= expr_stmt | statement2= print_stmt | statement3= del_stmt | statement4= pass_stmt | statement5= flow_stmt | statement6= import_stmt | statement7= global_stmt | statement8= exec_stmt | statement9= assert_stmt ) )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:327:2: (statement1= expr_stmt | statement2= print_stmt | statement3= del_stmt | statement4= pass_stmt | statement5= flow_stmt | statement6= import_stmt | statement7= global_stmt | statement8= exec_stmt | statement9= assert_stmt )
            {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:327:2: (statement1= expr_stmt | statement2= print_stmt | statement3= del_stmt | statement4= pass_stmt | statement5= flow_stmt | statement6= import_stmt | statement7= global_stmt | statement8= exec_stmt | statement9= assert_stmt )
            int alt19=9;
            switch ( input.LA(1) ) {
            case LPAREN:
            case NAME:
            case PLUS:
            case MINUS:
            case TILDE:
            case LBRACK:
            case LCURLY:
            case BACKQUOTE:
            case INT:
            case LONGINT:
            case FLOAT:
            case COMPLEX:
            case STRING:
            case 94:
            case 96:
                {
                alt19=1;
                }
                break;
            case 69:
                {
                alt19=2;
                }
                break;
            case 70:
                {
                alt19=3;
                }
                break;
            case 71:
                {
                alt19=4;
                }
                break;
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
                {
                alt19=5;
                }
                break;
            case 77:
            case 78:
                {
                alt19=6;
                }
                break;
            case 80:
                {
                alt19=7;
                }
                break;
            case 81:
                {
                alt19=8;
                }
                break;
            case 83:
                {
                alt19=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("327:2: (statement1= expr_stmt | statement2= print_stmt | statement3= del_stmt | statement4= pass_stmt | statement5= flow_stmt | statement6= import_stmt | statement7= global_stmt | statement8= exec_stmt | statement9= assert_stmt )", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:328:4: statement1= expr_stmt
                    {
                    pushFollow(FOLLOW_expr_stmt_in_small_stmt884);
                    statement1=expr_stmt();
                    _fsp--;

                     rstatement = statement1; 

                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:329:4: statement2= print_stmt
                    {
                    pushFollow(FOLLOW_print_stmt_in_small_stmt895);
                    statement2=print_stmt();
                    _fsp--;

                     rstatement = statement2; 

                    }
                    break;
                case 3 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:330:4: statement3= del_stmt
                    {
                    pushFollow(FOLLOW_del_stmt_in_small_stmt905);
                    statement3=del_stmt();
                    _fsp--;

                     rstatement = statement3; 

                    }
                    break;
                case 4 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:331:4: statement4= pass_stmt
                    {
                    pushFollow(FOLLOW_pass_stmt_in_small_stmt915);
                    statement4=pass_stmt();
                    _fsp--;

                     rstatement = statement4; 

                    }
                    break;
                case 5 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:332:4: statement5= flow_stmt
                    {
                    pushFollow(FOLLOW_flow_stmt_in_small_stmt925);
                    statement5=flow_stmt();
                    _fsp--;

                     rstatement = statement5; 

                    }
                    break;
                case 6 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:333:4: statement6= import_stmt
                    {
                    pushFollow(FOLLOW_import_stmt_in_small_stmt935);
                    statement6=import_stmt();
                    _fsp--;

                     rstatement = statement6; 

                    }
                    break;
                case 7 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:334:4: statement7= global_stmt
                    {
                    pushFollow(FOLLOW_global_stmt_in_small_stmt945);
                    statement7=global_stmt();
                    _fsp--;

                     rstatement = statement7; 

                    }
                    break;
                case 8 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:335:4: statement8= exec_stmt
                    {
                    pushFollow(FOLLOW_exec_stmt_in_small_stmt955);
                    statement8=exec_stmt();
                    _fsp--;

                     rstatement = statement8; 

                    }
                    break;
                case 9 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:336:4: statement9= assert_stmt
                    {
                    pushFollow(FOLLOW_assert_stmt_in_small_stmt965);
                    statement9=assert_stmt();
                    _fsp--;

                     rstatement = statement9; 

                    }
                    break;

            }


            		if( rstatement != null )
            			stmts.add( rstatement );
            	

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return rstatement;
    }
    // $ANTLR end small_stmt


    // $ANTLR start expr_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:344:1: expr_stmt returns [ Expression exp = null ] : exp0= testlist (type= augassign right= testlist | (a= ASSIGN right= testlist )+ )? ;
    public final Expression expr_stmt() throws RecognitionException {
        Expression exp =  null;

        Token a=null;
        Expression exp0 = null;

        int type = 0;

        Expression right = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:344:45: (exp0= testlist (type= augassign right= testlist | (a= ASSIGN right= testlist )+ )? )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:345:2: exp0= testlist (type= augassign right= testlist | (a= ASSIGN right= testlist )+ )?
            {
            pushFollow(FOLLOW_testlist_in_expr_stmt993);
            exp0=testlist();
            _fsp--;

             exp = exp0; 
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:346:2: (type= augassign right= testlist | (a= ASSIGN right= testlist )+ )?
            int alt21=3;
            int LA21_0 = input.LA(1);

            if ( ((LA21_0>=PLUSEQUAL && LA21_0<=DOUBLESLASHEQUAL)) ) {
                alt21=1;
            }
            else if ( (LA21_0==ASSIGN) ) {
                alt21=2;
            }
            switch (alt21) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:347:3: type= augassign right= testlist
                    {
                    pushFollow(FOLLOW_augassign_in_expr_stmt1007);
                    type=augassign();
                    _fsp--;

                    pushFollow(FOLLOW_testlist_in_expr_stmt1016);
                    right=testlist();
                    _fsp--;


                    				if( type != 0 ) {
                    		 			NotStrictAssignment e = new NotStrictAssignment( exp, type, right );
                    			    		exp = e;
                    				}
                    				else {
                    					exp = new Assignment( exp, right );
                    				}
                    		 	

                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:359:3: (a= ASSIGN right= testlist )+
                    {
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:359:3: (a= ASSIGN right= testlist )+
                    int cnt20=0;
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==ASSIGN) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:360:4: a= ASSIGN right= testlist
                    	    {
                    	    a=(Token)input.LT(1);
                    	    match(input,ASSIGN,FOLLOW_ASSIGN_in_expr_stmt1038); 
                    	    pushFollow(FOLLOW_testlist_in_expr_stmt1047);
                    	    right=testlist();
                    	    _fsp--;


                    	    				if( type != 0 ) {
                    	    		 			NotStrictAssignment e = new NotStrictAssignment( exp, type, right );
                    	    			    		exp = e;
                    	    				}
                    	    				else {
                    	    					exp = new Assignment( exp, right );
                    	    				}
                    	    			

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt20 >= 1 ) break loop20;
                                EarlyExitException eee =
                                    new EarlyExitException(20, input);
                                throw eee;
                        }
                        cnt20++;
                    } while (true);


                    }
                    break;

            }


            			if( exp == null )
            				exp = new EmptyExpression();
            		

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return exp;
    }
    // $ANTLR end expr_stmt


    // $ANTLR start augassign
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:379:1: augassign returns [ int type = 0 ] : ( PLUSEQUAL | MINUSEQUAL | STAREQUAL | SLASHEQUAL | PERCENTEQUAL | AMPEREQUAL | VBAREQUAL | CIRCUMFLEXEQUAL | LEFTSHIFTEQUAL | RIGHTSHIFTEQUAL | DOUBLESTAREQUAL | DOUBLESLASHEQUAL );
    public final int augassign() throws RecognitionException {
        int type =  0;

        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:379:35: ( PLUSEQUAL | MINUSEQUAL | STAREQUAL | SLASHEQUAL | PERCENTEQUAL | AMPEREQUAL | VBAREQUAL | CIRCUMFLEXEQUAL | LEFTSHIFTEQUAL | RIGHTSHIFTEQUAL | DOUBLESTAREQUAL | DOUBLESLASHEQUAL )
            int alt22=12;
            switch ( input.LA(1) ) {
            case PLUSEQUAL:
                {
                alt22=1;
                }
                break;
            case MINUSEQUAL:
                {
                alt22=2;
                }
                break;
            case STAREQUAL:
                {
                alt22=3;
                }
                break;
            case SLASHEQUAL:
                {
                alt22=4;
                }
                break;
            case PERCENTEQUAL:
                {
                alt22=5;
                }
                break;
            case AMPEREQUAL:
                {
                alt22=6;
                }
                break;
            case VBAREQUAL:
                {
                alt22=7;
                }
                break;
            case CIRCUMFLEXEQUAL:
                {
                alt22=8;
                }
                break;
            case LEFTSHIFTEQUAL:
                {
                alt22=9;
                }
                break;
            case RIGHTSHIFTEQUAL:
                {
                alt22=10;
                }
                break;
            case DOUBLESTAREQUAL:
                {
                alt22=11;
                }
                break;
            case DOUBLESLASHEQUAL:
                {
                alt22=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("379:1: augassign returns [ int type = 0 ] : ( PLUSEQUAL | MINUSEQUAL | STAREQUAL | SLASHEQUAL | PERCENTEQUAL | AMPEREQUAL | VBAREQUAL | CIRCUMFLEXEQUAL | LEFTSHIFTEQUAL | RIGHTSHIFTEQUAL | DOUBLESTAREQUAL | DOUBLESLASHEQUAL );", 22, 0, input);

                throw nvae;
            }

            switch (alt22) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:380:2: PLUSEQUAL
                    {
                    match(input,PLUSEQUAL,FOLLOW_PLUSEQUAL_in_augassign1080); 

                    			type = Expression.E_PLUS_ASSIGN;
                    		

                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:384:4: MINUSEQUAL
                    {
                    match(input,MINUSEQUAL,FOLLOW_MINUSEQUAL_in_augassign1089); 
                    					
                    			type = Expression.E_MINUS_ASSIGN;		
                    		

                    }
                    break;
                case 3 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:388:4: STAREQUAL
                    {
                    match(input,STAREQUAL,FOLLOW_STAREQUAL_in_augassign1099); 

                    			type = Expression.E_MULT_ASSIGN;
                    		

                    }
                    break;
                case 4 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:392:4: SLASHEQUAL
                    {
                    match(input,SLASHEQUAL,FOLLOW_SLASHEQUAL_in_augassign1108); 

                    			type = Expression.E_DIV_ASSIGN;
                    		

                    }
                    break;
                case 5 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:396:4: PERCENTEQUAL
                    {
                    match(input,PERCENTEQUAL,FOLLOW_PERCENTEQUAL_in_augassign1118); 

                    			type = Expression.E_MOD_ASSIGN;
                    		

                    }
                    break;
                case 6 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:400:4: AMPEREQUAL
                    {
                    match(input,AMPEREQUAL,FOLLOW_AMPEREQUAL_in_augassign1127); 

                    			type = Expression.E_BAND_ASSIGN;
                    		

                    }
                    break;
                case 7 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:404:4: VBAREQUAL
                    {
                    match(input,VBAREQUAL,FOLLOW_VBAREQUAL_in_augassign1136); 

                    			type = Expression.E_BOR_ASSIGN;
                    		

                    }
                    break;
                case 8 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:408:4: CIRCUMFLEXEQUAL
                    {
                    match(input,CIRCUMFLEXEQUAL,FOLLOW_CIRCUMFLEXEQUAL_in_augassign1145); 

                    			type = Expression.E_BXOR_ASSIGN;
                    		

                    }
                    break;
                case 9 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:412:4: LEFTSHIFTEQUAL
                    {
                    match(input,LEFTSHIFTEQUAL,FOLLOW_LEFTSHIFTEQUAL_in_augassign1154); 

                    			type = Expression.E_SL_ASSIGN;
                    		

                    }
                    break;
                case 10 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:416:4: RIGHTSHIFTEQUAL
                    {
                    match(input,RIGHTSHIFTEQUAL,FOLLOW_RIGHTSHIFTEQUAL_in_augassign1163); 

                    			type = Expression.E_SR_ASSIGN;
                    		

                    }
                    break;
                case 11 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:420:4: DOUBLESTAREQUAL
                    {
                    match(input,DOUBLESTAREQUAL,FOLLOW_DOUBLESTAREQUAL_in_augassign1172); 

                    			type = Expression.E_DOUBLESTAR_ASSIGN;
                    		

                    }
                    break;
                case 12 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:424:4: DOUBLESLASHEQUAL
                    {
                    match(input,DOUBLESLASHEQUAL,FOLLOW_DOUBLESLASHEQUAL_in_augassign1181); 

                    			type = Expression.E_DOUBLEDIV_ASSIGN;
                    		

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return type;
    }
    // $ANTLR end augassign


    // $ANTLR start print_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:430:1: print_stmt returns [ Statement statement = null ] : tu= 'print' (ex= testlist | RIGHTSHIFT ex= testlist )? ;
    public final Statement print_stmt() throws RecognitionException {
        Statement statement =  null;

        Token tu=null;
        Expression ex = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:430:50: (tu= 'print' (ex= testlist | RIGHTSHIFT ex= testlist )? )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:432:9: tu= 'print' (ex= testlist | RIGHTSHIFT ex= testlist )?
            {
            tu=(Token)input.LT(1);
            match(input,69,FOLLOW_69_in_print_stmt1215); 
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:433:2: (ex= testlist | RIGHTSHIFT ex= testlist )?
            int alt23=3;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==LPAREN||LA23_0==NAME||(LA23_0>=PLUS && LA23_0<=MINUS)||(LA23_0>=TILDE && LA23_0<=LBRACK)||LA23_0==LCURLY||(LA23_0>=BACKQUOTE && LA23_0<=STRING)||LA23_0==94||LA23_0==96) ) {
                alt23=1;
            }
            else if ( (LA23_0==RIGHTSHIFT) ) {
                alt23=2;
            }
            switch (alt23) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:434:3: ex= testlist
                    {
                    pushFollow(FOLLOW_testlist_in_print_stmt1226);
                    ex=testlist();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:435:12: RIGHTSHIFT ex= testlist
                    {
                    match(input,RIGHTSHIFT,FOLLOW_RIGHTSHIFT_in_print_stmt1239); 
                    pushFollow(FOLLOW_testlist_in_print_stmt1255);
                    ex=testlist();
                    _fsp--;


                    }
                    break;

            }


            		statement = new PrintExpression( toDLTK( tu ), ex );
            		if( ex != null ) {
            			statement.setEnd(ex.sourceEnd());
            		}
            	

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end print_stmt


    // $ANTLR start del_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:447:1: del_stmt returns [ Statement statement = null ] : sa= 'del' tu= exprlist ;
    public final Statement del_stmt() throws RecognitionException {
        Statement statement =  null;

        Token sa=null;
        PythonTestListExpression tu = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:447:48: (sa= 'del' tu= exprlist )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:448:2: sa= 'del' tu= exprlist
            {
            sa=(Token)input.LT(1);
            match(input,70,FOLLOW_70_in_del_stmt1286); 
            pushFollow(FOLLOW_exprlist_in_del_stmt1294);
            tu=exprlist();
            _fsp--;


            		statement = new PythonDelStatement( toDLTK( sa ), tu );
            	

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end del_stmt


    // $ANTLR start pass_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:456:1: pass_stmt returns [ Statement statement = null] : tu= 'pass' ;
    public final Statement pass_stmt() throws RecognitionException {
        Statement statement =  null;

        Token tu=null;

        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:456:48: (tu= 'pass' )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:457:2: tu= 'pass'
            {
            tu=(Token)input.LT(1);
            match(input,71,FOLLOW_71_in_pass_stmt1317); 

            		statement = new EmptyStatement( toDLTK( tu ) );
            	

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end pass_stmt


    // $ANTLR start flow_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:464:1: flow_stmt returns [ Statement statement = null ] : (statement0= break_stmt | statement1= continue_stmt | statement2= return_stmt | statement3= raise_stmt | statement4= yield_stmt );
    public final Statement flow_stmt() throws RecognitionException {
        Statement statement =  null;

        Statement statement0 = null;

        Statement statement1 = null;

        Statement statement2 = null;

        PythonRaiseStatement statement3 = null;

        PythonYieldStatement statement4 = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:464:49: (statement0= break_stmt | statement1= continue_stmt | statement2= return_stmt | statement3= raise_stmt | statement4= yield_stmt )
            int alt24=5;
            switch ( input.LA(1) ) {
            case 72:
                {
                alt24=1;
                }
                break;
            case 73:
                {
                alt24=2;
                }
                break;
            case 74:
                {
                alt24=3;
                }
                break;
            case 76:
                {
                alt24=4;
                }
                break;
            case 75:
                {
                alt24=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("464:1: flow_stmt returns [ Statement statement = null ] : (statement0= break_stmt | statement1= continue_stmt | statement2= return_stmt | statement3= raise_stmt | statement4= yield_stmt );", 24, 0, input);

                throw nvae;
            }

            switch (alt24) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:465:4: statement0= break_stmt
                    {
                    pushFollow(FOLLOW_break_stmt_in_flow_stmt1343);
                    statement0=break_stmt();
                    _fsp--;

                     statement = statement0; 

                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:466:4: statement1= continue_stmt
                    {
                    pushFollow(FOLLOW_continue_stmt_in_flow_stmt1354);
                    statement1=continue_stmt();
                    _fsp--;

                     statement = statement1; 

                    }
                    break;
                case 3 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:467:4: statement2= return_stmt
                    {
                    pushFollow(FOLLOW_return_stmt_in_flow_stmt1364);
                    statement2=return_stmt();
                    _fsp--;

                     statement = statement2; 

                    }
                    break;
                case 4 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:468:4: statement3= raise_stmt
                    {
                    pushFollow(FOLLOW_raise_stmt_in_flow_stmt1374);
                    statement3=raise_stmt();
                    _fsp--;

                     statement = statement3; 

                    }
                    break;
                case 5 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:469:4: statement4= yield_stmt
                    {
                    pushFollow(FOLLOW_yield_stmt_in_flow_stmt1384);
                    statement4=yield_stmt();
                    _fsp--;

                     statement = statement4; 

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end flow_stmt


    // $ANTLR start break_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:472:1: break_stmt returns [ Statement statement = null ] : ta= 'break' ;
    public final Statement break_stmt() throws RecognitionException {
        Statement statement =  null;

        Token ta=null;

        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:472:50: (ta= 'break' )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:473:2: ta= 'break'
            {
            ta=(Token)input.LT(1);
            match(input,72,FOLLOW_72_in_break_stmt1405); 

            			statement = new BreakStatement( toDLTK( ta ), null, toDLTK(ta) );
            		

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end break_stmt


    // $ANTLR start continue_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:479:1: continue_stmt returns [ Statement statement = null ] : ta= 'continue' ;
    public final Statement continue_stmt() throws RecognitionException {
        Statement statement =  null;

        Token ta=null;

        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:479:53: (ta= 'continue' )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:480:2: ta= 'continue'
            {
            ta=(Token)input.LT(1);
            match(input,73,FOLLOW_73_in_continue_stmt1429); 

            			statement = new ContinueStatement( toDLTK( ta ), null, toDLTK(ta) );
            		

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end continue_stmt


    // $ANTLR start return_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:486:1: return_stmt returns [ Statement statement = null ] : ra= 'return' (tu= testlist )? ;
    public final Statement return_stmt() throws RecognitionException {
        Statement statement =  null;

        Token ra=null;
        Expression tu = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:486:51: (ra= 'return' (tu= testlist )? )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:487:2: ra= 'return' (tu= testlist )?
            {
            ra=(Token)input.LT(1);
            match(input,74,FOLLOW_74_in_return_stmt1454); 
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:488:3: (tu= testlist )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==LPAREN||LA25_0==NAME||(LA25_0>=PLUS && LA25_0<=MINUS)||(LA25_0>=TILDE && LA25_0<=LBRACK)||LA25_0==LCURLY||(LA25_0>=BACKQUOTE && LA25_0<=STRING)||LA25_0==94||LA25_0==96) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:488:5: tu= testlist
                    {
                    pushFollow(FOLLOW_testlist_in_return_stmt1465);
                    tu=testlist();
                    _fsp--;


                    }
                    break;

            }


            			DLTKToken ret = toDLTK(ra);
            			int end = ret.getColumn()+ret.getText().length();
            			if( tu != null ) {
            				end = tu.sourceEnd();
            			}
            			statement = new ReturnStatement( toDLTK( ra ), tu , end );
            		

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end return_stmt


    // $ANTLR start yield_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:500:1: yield_stmt returns [ PythonYieldStatement statement = null ] : tu= 'yield' r= testlist ;
    public final PythonYieldStatement yield_stmt() throws RecognitionException {
        PythonYieldStatement statement =  null;

        Token tu=null;
        Expression r = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:500:61: (tu= 'yield' r= testlist )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:501:2: tu= 'yield' r= testlist
            {
            tu=(Token)input.LT(1);
            match(input,75,FOLLOW_75_in_yield_stmt1493); 
            pushFollow(FOLLOW_testlist_in_yield_stmt1500);
            r=testlist();
            _fsp--;


            		statement = new PythonYieldStatement( toDLTK( tu ), r );
            	

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end yield_stmt


    // $ANTLR start raise_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:508:1: raise_stmt returns [ PythonRaiseStatement statement = null ] : tu= 'raise' (e1= test ( COMMA e2= test ( COMMA e3= test )? )? )? ;
    public final PythonRaiseStatement raise_stmt() throws RecognitionException {
        PythonRaiseStatement statement =  null;

        Token tu=null;
        Expression e1 = null;

        Expression e2 = null;

        Expression e3 = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:508:61: (tu= 'raise' (e1= test ( COMMA e2= test ( COMMA e3= test )? )? )? )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:509:2: tu= 'raise' (e1= test ( COMMA e2= test ( COMMA e3= test )? )? )?
            {
            tu=(Token)input.LT(1);
            match(input,76,FOLLOW_76_in_raise_stmt1522); 

            		statement = new PythonRaiseStatement( toDLTK( tu ) );
            		int end = -1;
            	
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:514:2: (e1= test ( COMMA e2= test ( COMMA e3= test )? )? )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==LPAREN||LA28_0==NAME||(LA28_0>=PLUS && LA28_0<=MINUS)||(LA28_0>=TILDE && LA28_0<=LBRACK)||LA28_0==LCURLY||(LA28_0>=BACKQUOTE && LA28_0<=STRING)||LA28_0==94||LA28_0==96) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:515:3: e1= test ( COMMA e2= test ( COMMA e3= test )? )?
                    {
                    pushFollow(FOLLOW_test_in_raise_stmt1536);
                    e1=test();
                    _fsp--;


                    			statement.acceptExpression1( e1 );
                    			if( e1 != null && e1.sourceEnd() > end ) {
                    				end = e1.sourceEnd();
                    			}
                    		
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:522:3: ( COMMA e2= test ( COMMA e3= test )? )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==COMMA) ) {
                        alt27=1;
                    }
                    switch (alt27) {
                        case 1 :
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:522:5: COMMA e2= test ( COMMA e3= test )?
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_raise_stmt1547); 
                            pushFollow(FOLLOW_test_in_raise_stmt1556);
                            e2=test();
                            _fsp--;


                            				statement.acceptExpression2( e2 );
                            				if( e2 != null && e2.sourceEnd() > end ) {
                            					end = e2.sourceEnd();
                            				}
                            			
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:530:4: ( COMMA e3= test )?
                            int alt26=2;
                            int LA26_0 = input.LA(1);

                            if ( (LA26_0==COMMA) ) {
                                alt26=1;
                            }
                            switch (alt26) {
                                case 1 :
                                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:530:6: COMMA e3= test
                                    {
                                    match(input,COMMA,FOLLOW_COMMA_in_raise_stmt1569); 
                                    pushFollow(FOLLOW_test_in_raise_stmt1579);
                                    e3=test();
                                    _fsp--;


                                    		   			statement.acceptExpression3( e3 );
                                    		   			if( e3 != null && e3.sourceEnd() > end ) {
                                    						end = e3.sourceEnd();
                                    					}
                                    		   		

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end raise_stmt


    // $ANTLR start import_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:544:1: import_stmt returns [ Statement statement = null ] : ( (tu= 'import' expr0= module_imp ( COMMA expr0= module_imp )* ) | r= 'from' moduleName= dot_name 'import' ( (expr0= module_imp ( COMMA expr0= module_imp )* ) | ( STAR ) ) ) ;
    public final Statement import_stmt() throws RecognitionException {
        Statement statement =  null;

        Token tu=null;
        Token r=null;
        Expression expr0 = null;

        Token moduleName = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:544:52: ( ( (tu= 'import' expr0= module_imp ( COMMA expr0= module_imp )* ) | r= 'from' moduleName= dot_name 'import' ( (expr0= module_imp ( COMMA expr0= module_imp )* ) | ( STAR ) ) ) )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:545:6: ( (tu= 'import' expr0= module_imp ( COMMA expr0= module_imp )* ) | r= 'from' moduleName= dot_name 'import' ( (expr0= module_imp ( COMMA expr0= module_imp )* ) | ( STAR ) ) )
            {

                		Expression impExpr;
                		String impName;
                		String impName2;
                		//Token moduleName;		
                		
                		PythonTestListExpression importNames = new PythonTestListExpression();    		
                	
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:554:6: ( (tu= 'import' expr0= module_imp ( COMMA expr0= module_imp )* ) | r= 'from' moduleName= dot_name 'import' ( (expr0= module_imp ( COMMA expr0= module_imp )* ) | ( STAR ) ) )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==77) ) {
                alt32=1;
            }
            else if ( (LA32_0==78) ) {
                alt32=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("554:6: ( (tu= 'import' expr0= module_imp ( COMMA expr0= module_imp )* ) | r= 'from' moduleName= dot_name 'import' ( (expr0= module_imp ( COMMA expr0= module_imp )* ) | ( STAR ) ) )", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:555:7: (tu= 'import' expr0= module_imp ( COMMA expr0= module_imp )* )
                    {
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:555:7: (tu= 'import' expr0= module_imp ( COMMA expr0= module_imp )* )
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:556:8: tu= 'import' expr0= module_imp ( COMMA expr0= module_imp )*
                    {
                    tu=(Token)input.LT(1);
                    match(input,77,FOLLOW_77_in_import_stmt1696); 
                    	    	
                        				statement = new PythonImportStatement( toDLTK( tu ), importNames );
                        				//Expression expr0 = null;
                        			
                    pushFollow(FOLLOW_module_imp_in_import_stmt1718);
                    expr0=module_imp();
                    _fsp--;


                        					importNames.setStart(expr0.sourceStart());
                        					importNames.setEnd(expr0.sourceEnd());
                        					importNames.addExpression( expr0 );
                        					if( expr0.sourceEnd() > statement.sourceEnd() ) {
                        						statement.setEnd( expr0.sourceEnd() );
                        					}
                        				
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:571:8: ( COMMA expr0= module_imp )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==COMMA) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:571:10: COMMA expr0= module_imp
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_import_stmt1747); 
                    	    pushFollow(FOLLOW_module_imp_in_import_stmt1761);
                    	    expr0=module_imp();
                    	    _fsp--;


                    	        					importNames.addExpression( expr0 );
                    	        					importNames.setEnd( expr0.sourceEnd());
                    	        					if( expr0.sourceEnd() > statement.sourceEnd() ) {
                    	        						statement.setEnd( expr0.sourceEnd() );
                    	        					}
                    	        				

                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);


                    }


                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:583:3: r= 'from' moduleName= dot_name 'import' ( (expr0= module_imp ( COMMA expr0= module_imp )* ) | ( STAR ) )
                    {
                    r=(Token)input.LT(1);
                    match(input,78,FOLLOW_78_in_import_stmt1797); 
                    pushFollow(FOLLOW_dot_name_in_import_stmt1805);
                    moduleName=dot_name();
                    _fsp--;

                    match(input,77,FOLLOW_77_in_import_stmt1812); 
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:587:3: ( (expr0= module_imp ( COMMA expr0= module_imp )* ) | ( STAR ) )
                    int alt31=2;
                    int LA31_0 = input.LA(1);

                    if ( (LA31_0==NAME) ) {
                        alt31=1;
                    }
                    else if ( (LA31_0==STAR) ) {
                        alt31=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("587:3: ( (expr0= module_imp ( COMMA expr0= module_imp )* ) | ( STAR ) )", 31, 0, input);

                        throw nvae;
                    }
                    switch (alt31) {
                        case 1 :
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:588:11: (expr0= module_imp ( COMMA expr0= module_imp )* )
                            {
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:588:11: (expr0= module_imp ( COMMA expr0= module_imp )* )
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:589:6: expr0= module_imp ( COMMA expr0= module_imp )*
                            {

                            						//moduleName.setColumn(moduleName.getColumn()-1);
                                    				statement = new PythonImportFromStatement( toDLTK( r ), new VariableReference( toDLTK( moduleName ) ), importNames );
                                    				//Expression expr0 = null;
                                    			
                            pushFollow(FOLLOW_module_imp_in_import_stmt1852);
                            expr0=module_imp();
                            _fsp--;


                                						importNames.setStart(expr0.sourceStart());
                                						importNames.setEnd(expr0.sourceEnd());
                                						importNames.addExpression( expr0 );
                                						if( expr0.sourceEnd() > statement.sourceEnd() ) {
                                							statement.setEnd( expr0.sourceEnd() );
                                						}
                                					
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:603:9: ( COMMA expr0= module_imp )*
                            loop30:
                            do {
                                int alt30=2;
                                int LA30_0 = input.LA(1);

                                if ( (LA30_0==COMMA) ) {
                                    alt30=1;
                                }


                                switch (alt30) {
                            	case 1 :
                            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:603:11: COMMA expr0= module_imp
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_import_stmt1875); 
                            	    pushFollow(FOLLOW_module_imp_in_import_stmt1890);
                            	    expr0=module_imp();
                            	    _fsp--;


                            	        							importNames.addExpression( expr0 );
                            	        							importNames.setEnd( expr0.sourceEnd());
                            	        							if( expr0.sourceEnd() > statement.sourceEnd() ) {
                            	        								statement.setEnd( expr0.sourceEnd() );
                            	        							}
                            	        						

                            	    }
                            	    break;

                            	default :
                            	    break loop30;
                                }
                            } while (true);


                            }


                            }
                            break;
                        case 2 :
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:615:8: ( STAR )
                            {
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:615:8: ( STAR )
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:616:5: STAR
                            {
                            match(input,STAR,FOLLOW_STAR_in_import_stmt1947); 

                            					//moduleName.setColumn(moduleName.getColumn()-1);
                            					statement = new PythonImportFromStatement( toDLTK( r ), new VariableReference( toDLTK( moduleName ) ), new PythonAllImportExpression( ) );
                            				

                            }


                            }
                            break;

                    }


                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end import_stmt


    // $ANTLR start dotted_name
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:626:1: dotted_name returns [ Token token = null ] : n= NAME ( DOT n2= NAME )+ ;
    public final Token dotted_name() throws RecognitionException {
        Token token =  null;

        Token n=null;
        Token n2=null;

        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:626:43: (n= NAME ( DOT n2= NAME )+ )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:627:2: n= NAME ( DOT n2= NAME )+
            {
            		
            		String value = "";
            	
            n=(Token)input.LT(1);
            match(input,NAME,FOLLOW_NAME_in_dotted_name1997); 

            		value += n.getText();
            	
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:634:2: ( DOT n2= NAME )+
            int cnt33=0;
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==DOT) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:635:3: DOT n2= NAME
            	    {
            	    match(input,DOT,FOLLOW_DOT_in_dotted_name2007); 
            	    n2=(Token)input.LT(1);
            	    match(input,NAME,FOLLOW_NAME_in_dotted_name2014); 

            	    			value += "." + n2.getText();
            	    		

            	    }
            	    break;

            	default :
            	    if ( cnt33 >= 1 ) break loop33;
                        EarlyExitException eee =
                            new EarlyExitException(33, input);
                        throw eee;
                }
                cnt33++;
            } while (true);


            		token = new CommonToken( n );
            		token.setText( value );
            		//token.setColumn( n.getColumn() );
            	

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return token;
    }
    // $ANTLR end dotted_name


    // $ANTLR start dot_name
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:648:1: dot_name returns [ Token token = null ] : ( (moduleName1= dotted_name ) | (moduleName2= NAME ) );
    public final Token dot_name() throws RecognitionException {
        Token token =  null;

        Token moduleName2=null;
        Token moduleName1 = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:648:40: ( (moduleName1= dotted_name ) | (moduleName2= NAME ) )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==NAME) ) {
                int LA34_1 = input.LA(2);

                if ( (LA34_1==NEWLINE||LA34_1==LPAREN||LA34_1==COMMA||LA34_1==SEMI||LA34_1==77||LA34_1==79) ) {
                    alt34=2;
                }
                else if ( (LA34_1==DOT) ) {
                    alt34=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("648:1: dot_name returns [ Token token = null ] : ( (moduleName1= dotted_name ) | (moduleName2= NAME ) );", 34, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("648:1: dot_name returns [ Token token = null ] : ( (moduleName1= dotted_name ) | (moduleName2= NAME ) );", 34, 0, input);

                throw nvae;
            }
            switch (alt34) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:649:2: (moduleName1= dotted_name )
                    {
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:649:2: (moduleName1= dotted_name )
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:650:3: moduleName1= dotted_name
                    {
                    pushFollow(FOLLOW_dotted_name_in_dot_name2049);
                    moduleName1=dotted_name();
                    _fsp--;


                    	    		token = moduleName1;
                    	    	

                    }


                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:656:2: (moduleName2= NAME )
                    {
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:656:2: (moduleName2= NAME )
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:657:3: moduleName2= NAME
                    {
                    moduleName2=(Token)input.LT(1);
                    match(input,NAME,FOLLOW_NAME_in_dot_name2069); 

                    			token = moduleName2;
                    		

                    }


                    		if( token != null ) {
                    			//int column = token.getColumn();
                    			//token.setColumn( column -1 );
                    		}
                    	

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return token;
    }
    // $ANTLR end dot_name


    // $ANTLR start module_imp
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:669:1: module_imp returns [ Expression expr = null ] : impName= dot_name ( 'as' as= NAME )? ;
    public final Expression module_imp() throws RecognitionException {
        Expression expr =  null;

        Token as=null;
        Token impName = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:669:46: (impName= dot_name ( 'as' as= NAME )? )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:670:2: impName= dot_name ( 'as' as= NAME )?
            {
            pushFollow(FOLLOW_dot_name_in_module_imp2099);
            impName=dot_name();
            _fsp--;


            		expr = new PythonImportExpression( toDLTK( impName ) );
            	
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:674:2: ( 'as' as= NAME )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==79) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:675:3: 'as' as= NAME
                    {
                    match(input,79,FOLLOW_79_in_module_imp2111); 
                    as=(Token)input.LT(1);
                    match(input,NAME,FOLLOW_NAME_in_module_imp2118); 

                    				expr = new PythonImportAsExpression( toDLTK( impName ), toDLTK( as ) );
                    			

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return expr;
    }
    // $ANTLR end module_imp


    // $ANTLR start global_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:684:1: global_stmt returns [ Statement statement = null ] : 'global' NAME ( COMMA NAME )* ;
    public final Statement global_stmt() throws RecognitionException {
        Statement statement =  null;

        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:684:51: ( 'global' NAME ( COMMA NAME )* )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:685:3: 'global' NAME ( COMMA NAME )*
            {
            match(input,80,FOLLOW_80_in_global_stmt2145); 
            match(input,NAME,FOLLOW_NAME_in_global_stmt2147); 
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:685:17: ( COMMA NAME )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==COMMA) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:685:18: COMMA NAME
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_global_stmt2150); 
            	    match(input,NAME,FOLLOW_NAME_in_global_stmt2152); 

            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end global_stmt


    // $ANTLR start exec_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:689:1: exec_stmt returns [ Statement statement = null] : e= 'exec' ex= expr ( 'in' ex= test ( COMMA ex= test )? )? ;
    public final Statement exec_stmt() throws RecognitionException {
        Statement statement =  null;

        Token e=null;
        Expression ex = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:689:49: (e= 'exec' ex= expr ( 'in' ex= test ( COMMA ex= test )? )? )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:690:2: e= 'exec' ex= expr ( 'in' ex= test ( COMMA ex= test )? )?
            {
            e=(Token)input.LT(1);
            match(input,81,FOLLOW_81_in_exec_stmt2177); 
            pushFollow(FOLLOW_expr_in_exec_stmt2183);
            ex=expr();
            _fsp--;

             statement = new ExecStatement(this.converter.convert(e), ex); 
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:691:2: ( 'in' ex= test ( COMMA ex= test )? )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==82) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:691:3: 'in' ex= test ( COMMA ex= test )?
                    {
                    match(input,82,FOLLOW_82_in_exec_stmt2190); 
                    pushFollow(FOLLOW_test_in_exec_stmt2199);
                    ex=test();
                    _fsp--;

                     ((ExecStatement)statement).acceptIn(ex); 
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:693:3: ( COMMA ex= test )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==COMMA) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:693:4: COMMA ex= test
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_exec_stmt2207); 
                            pushFollow(FOLLOW_test_in_exec_stmt2213);
                            ex=test();
                            _fsp--;

                             ((ExecStatement)statement).acceptIn2(ex); 

                            }
                            break;

                    }


                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end exec_stmt


    // $ANTLR start assert_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:697:1: assert_stmt returns [ PythonAssertStatement statement = null ] : tu= 'assert' exp1= test ( COMMA exp2= test )? ;
    public final PythonAssertStatement assert_stmt() throws RecognitionException {
        PythonAssertStatement statement =  null;

        Token tu=null;
        Expression exp1 = null;

        Expression exp2 = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:697:63: (tu= 'assert' exp1= test ( COMMA exp2= test )? )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:698:2: tu= 'assert' exp1= test ( COMMA exp2= test )?
            {
            tu=(Token)input.LT(1);
            match(input,83,FOLLOW_83_in_assert_stmt2240); 
            pushFollow(FOLLOW_test_in_assert_stmt2247);
            exp1=test();
            _fsp--;

            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:700:2: ( COMMA exp2= test )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==COMMA) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:700:4: COMMA exp2= test
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_assert_stmt2253); 
                    pushFollow(FOLLOW_test_in_assert_stmt2259);
                    exp2=test();
                    _fsp--;


                    }
                    break;

            }


            			statement = new PythonAssertStatement( toDLTK( tu ), exp1, exp2 );
            		

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end assert_stmt


    // $ANTLR start compound_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:707:1: compound_stmt returns [ Statement statement = null ] : (statement0= if_stmt | statement1= while_stmt | statement2= for_stmt | statement3= try_stmt | statement4= funcdef | statement5= classdef | statement6= with_stmt );
    public final Statement compound_stmt() throws RecognitionException {
        Statement statement =  null;

        IfStatement statement0 = null;

        PythonWhileStatement statement1 = null;

        PythonForStatement statement2 = null;

        PythonTryStatement statement3 = null;

        MethodDeclaration statement4 = null;

        PythonClassDeclaration statement5 = null;

        Statement statement6 = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:707:53: (statement0= if_stmt | statement1= while_stmt | statement2= for_stmt | statement3= try_stmt | statement4= funcdef | statement5= classdef | statement6= with_stmt )
            int alt40=7;
            switch ( input.LA(1) ) {
            case 84:
                {
                alt40=1;
                }
                break;
            case 87:
                {
                alt40=2;
                }
                break;
            case 88:
                {
                alt40=3;
                }
                break;
            case 89:
                {
                alt40=4;
                }
                break;
            case DECORATOR_S:
            case 68:
                {
                alt40=5;
                }
                break;
            case 98:
                {
                alt40=6;
                }
                break;
            case 97:
                {
                alt40=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("707:1: compound_stmt returns [ Statement statement = null ] : (statement0= if_stmt | statement1= while_stmt | statement2= for_stmt | statement3= try_stmt | statement4= funcdef | statement5= classdef | statement6= with_stmt );", 40, 0, input);

                throw nvae;
            }

            switch (alt40) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:708:2: statement0= if_stmt
                    {
                    pushFollow(FOLLOW_if_stmt_in_compound_stmt2287);
                    statement0=if_stmt();
                    _fsp--;

                     statement = statement0; 

                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:709:4: statement1= while_stmt
                    {
                    pushFollow(FOLLOW_while_stmt_in_compound_stmt2298);
                    statement1=while_stmt();
                    _fsp--;

                     statement = statement1; 

                    }
                    break;
                case 3 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:710:4: statement2= for_stmt
                    {
                    pushFollow(FOLLOW_for_stmt_in_compound_stmt2308);
                    statement2=for_stmt();
                    _fsp--;

                     statement = statement2; 

                    }
                    break;
                case 4 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:711:4: statement3= try_stmt
                    {
                    pushFollow(FOLLOW_try_stmt_in_compound_stmt2318);
                    statement3=try_stmt();
                    _fsp--;

                     statement = statement3; 

                    }
                    break;
                case 5 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:712:4: statement4= funcdef
                    {
                    pushFollow(FOLLOW_funcdef_in_compound_stmt2328);
                    statement4=funcdef();
                    _fsp--;

                     statement = statement4; 

                    }
                    break;
                case 6 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:713:4: statement5= classdef
                    {
                    pushFollow(FOLLOW_classdef_in_compound_stmt2338);
                    statement5=classdef();
                    _fsp--;

                     statement = statement5; 

                    }
                    break;
                case 7 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:714:4: statement6= with_stmt
                    {
                    pushFollow(FOLLOW_with_stmt_in_compound_stmt2348);
                    statement6=with_stmt();
                    _fsp--;

                    statement = statement6; 

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end compound_stmt


    // $ANTLR start if_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:717:1: if_stmt returns [ IfStatement statement = null ] : is= 'if' mn= test COLON body= suite (z= 'elif' mn= test COLON body= suite )* ( 'else' COLON body= suite )? ;
    public final IfStatement if_stmt() throws RecognitionException {
        IfStatement statement =  null;

        Token is=null;
        Token z=null;
        Expression mn = null;

        Block body = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:717:49: (is= 'if' mn= test COLON body= suite (z= 'elif' mn= test COLON body= suite )* ( 'else' COLON body= suite )? )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:718:3: is= 'if' mn= test COLON body= suite (z= 'elif' mn= test COLON body= suite )* ( 'else' COLON body= suite )?
            {
            is=(Token)input.LT(1);
            match(input,84,FOLLOW_84_in_if_stmt2371); 
            pushFollow(FOLLOW_test_in_if_stmt2380);
            mn=test();
            _fsp--;

            match(input,COLON,FOLLOW_COLON_in_if_stmt2384); 
            pushFollow(FOLLOW_suite_in_if_stmt2393);
            body=suite();
            _fsp--;

             
            			IfStatement t,base;
            			List ifs = new ArrayList();
            			statement = new IfStatement( toDLTK( is ), mn, body ); 
            			statement.setEnd(body.sourceEnd());
            			ifs.add(statement);
            			base = statement;
            			t = statement; 
            		
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:732:3: (z= 'elif' mn= test COLON body= suite )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==85) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:733:4: z= 'elif' mn= test COLON body= suite
            	    {
            	    z=(Token)input.LT(1);
            	    match(input,85,FOLLOW_85_in_if_stmt2414); 
            	    pushFollow(FOLLOW_test_in_if_stmt2423);
            	    mn=test();
            	    _fsp--;

            	    match(input,COLON,FOLLOW_COLON_in_if_stmt2429); 
            	    pushFollow(FOLLOW_suite_in_if_stmt2437);
            	    body=suite();
            	    _fsp--;

            	     
            	    					IfStatement elseIfStatement = new IfStatement( toDLTK( z ), mn, body );
            	    					t.acceptElse( elseIfStatement );
            	    					t = elseIfStatement;
            	    					ifs.add(t);
            	    					for(Iterator i = ifs.iterator(); i.hasNext(); ((IfStatement)i.next()).setEnd(body.sourceEnd()));
            	    					base.setEnd(elseIfStatement.sourceEnd());
            	    				

            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);

            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:746:3: ( 'else' COLON body= suite )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==86) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:747:4: 'else' COLON body= suite
                    {
                    match(input,86,FOLLOW_86_in_if_stmt2458); 
                    match(input,COLON,FOLLOW_COLON_in_if_stmt2464); 
                    pushFollow(FOLLOW_suite_in_if_stmt2474);
                    body=suite();
                    _fsp--;

                     
                    					t.setElse( body );
                    					for(Iterator i = ifs.iterator(); i.hasNext(); ((IfStatement)i.next()).setEnd(body.sourceEnd()));
                    				

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end if_stmt


    // $ANTLR start while_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:757:1: while_stmt returns [ PythonWhileStatement whileStatement = null ] : is= 'while' expression= test COLON body= suite ( 'else' COLON body= suite )? ;
    public final PythonWhileStatement while_stmt() throws RecognitionException {
        PythonWhileStatement whileStatement =  null;

        Token is=null;
        Expression expression = null;

        Block body = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:757:66: (is= 'while' expression= test COLON body= suite ( 'else' COLON body= suite )? )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:758:3: is= 'while' expression= test COLON body= suite ( 'else' COLON body= suite )?
            {
            is=(Token)input.LT(1);
            match(input,87,FOLLOW_87_in_while_stmt2510); 
            pushFollow(FOLLOW_test_in_while_stmt2518);
            expression=test();
            _fsp--;

            match(input,COLON,FOLLOW_COLON_in_while_stmt2522); 
            pushFollow(FOLLOW_suite_in_while_stmt2532);
            body=suite();
            _fsp--;

             
            				whileStatement = new PythonWhileStatement( toDLTK( is ), expression, body ); 
            			
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:765:3: ( 'else' COLON body= suite )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==86) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:766:4: 'else' COLON body= suite
                    {
                    match(input,86,FOLLOW_86_in_while_stmt2546); 
                    match(input,COLON,FOLLOW_COLON_in_while_stmt2548); 
                    pushFollow(FOLLOW_suite_in_while_stmt2558);
                    body=suite();
                    _fsp--;

                     
                    					whileStatement.setElseStatement( body ); 
                    				

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return whileStatement;
    }
    // $ANTLR end while_stmt


    // $ANTLR start for_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:774:1: for_stmt returns [ PythonForStatement statement = null] : is= 'for' mains= exprlist 'in' conds= testlist COLON body= suite ( 'else' COLON body= suite )? ;
    public final PythonForStatement for_stmt() throws RecognitionException {
        PythonForStatement statement =  null;

        Token is=null;
        PythonTestListExpression mains = null;

        Expression conds = null;

        Block body = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:774:56: (is= 'for' mains= exprlist 'in' conds= testlist COLON body= suite ( 'else' COLON body= suite )? )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:775:3: is= 'for' mains= exprlist 'in' conds= testlist COLON body= suite ( 'else' COLON body= suite )?
            {
            is=(Token)input.LT(1);
            match(input,88,FOLLOW_88_in_for_stmt2589); 
            pushFollow(FOLLOW_exprlist_in_for_stmt2598);
            mains=exprlist();
            _fsp--;

            match(input,82,FOLLOW_82_in_for_stmt2603); 
            pushFollow(FOLLOW_testlist_in_for_stmt2611);
            conds=testlist();
            _fsp--;

            match(input,COLON,FOLLOW_COLON_in_for_stmt2615); 
            pushFollow(FOLLOW_suite_in_for_stmt2624);
            body=suite();
            _fsp--;


            				statement = new PythonForStatement( toDLTK( is ), mains, conds, body );
            			
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:784:3: ( 'else' COLON body= suite )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==86) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:785:4: 'else' COLON body= suite
                    {
                    match(input,86,FOLLOW_86_in_for_stmt2639); 
                    match(input,COLON,FOLLOW_COLON_in_for_stmt2645); 
                    pushFollow(FOLLOW_suite_in_for_stmt2655);
                    body=suite();
                    _fsp--;


                    					statement.acceptElse( body );
                    				

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end for_stmt


    // $ANTLR start try_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:796:1: try_stmt returns [ PythonTryStatement statement = null ] : is= 'try' COLON body= suite ( ( (ex_= 'except' (t1= test ( COMMA t2= test )? )? COLON su= suite )+ ( (elseT= 'else' COLON elseBlock= suite ) )? ) | (fin= 'finally' COLON su= suite ) ) ;
    public final PythonTryStatement try_stmt() throws RecognitionException {
        PythonTryStatement statement =  null;

        Token is=null;
        Token ex_=null;
        Token elseT=null;
        Token fin=null;
        Block body = null;

        Expression t1 = null;

        Expression t2 = null;

        Block su = null;

        Block elseBlock = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:796:58: (is= 'try' COLON body= suite ( ( (ex_= 'except' (t1= test ( COMMA t2= test )? )? COLON su= suite )+ ( (elseT= 'else' COLON elseBlock= suite ) )? ) | (fin= 'finally' COLON su= suite ) ) )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:797:2: is= 'try' COLON body= suite ( ( (ex_= 'except' (t1= test ( COMMA t2= test )? )? COLON su= suite )+ ( (elseT= 'else' COLON elseBlock= suite ) )? ) | (fin= 'finally' COLON su= suite ) )
            {
            is=(Token)input.LT(1);
            match(input,89,FOLLOW_89_in_try_stmt2687); 
            match(input,COLON,FOLLOW_COLON_in_try_stmt2691); 

            			Token lastTok = is;
            			
                    		List catches = new ArrayList();
            		
            pushFollow(FOLLOW_suite_in_try_stmt2706);
            body=suite();
            _fsp--;

            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:805:6: ( ( (ex_= 'except' (t1= test ( COMMA t2= test )? )? COLON su= suite )+ ( (elseT= 'else' COLON elseBlock= suite ) )? ) | (fin= 'finally' COLON su= suite ) )
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==90) ) {
                alt49=1;
            }
            else if ( (LA49_0==91) ) {
                alt49=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("805:6: ( ( (ex_= 'except' (t1= test ( COMMA t2= test )? )? COLON su= suite )+ ( (elseT= 'else' COLON elseBlock= suite ) )? ) | (fin= 'finally' COLON su= suite ) )", 49, 0, input);

                throw nvae;
            }
            switch (alt49) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:806:9: ( (ex_= 'except' (t1= test ( COMMA t2= test )? )? COLON su= suite )+ ( (elseT= 'else' COLON elseBlock= suite ) )? )
                    {
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:806:9: ( (ex_= 'except' (t1= test ( COMMA t2= test )? )? COLON su= suite )+ ( (elseT= 'else' COLON elseBlock= suite ) )? )
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:807:3: (ex_= 'except' (t1= test ( COMMA t2= test )? )? COLON su= suite )+ ( (elseT= 'else' COLON elseBlock= suite ) )?
                    {
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:807:3: (ex_= 'except' (t1= test ( COMMA t2= test )? )? COLON su= suite )+
                    int cnt47=0;
                    loop47:
                    do {
                        int alt47=2;
                        int LA47_0 = input.LA(1);

                        if ( (LA47_0==90) ) {
                            alt47=1;
                        }


                        switch (alt47) {
                    	case 1 :
                    	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:808:4: ex_= 'except' (t1= test ( COMMA t2= test )? )? COLON su= suite
                    	    {
                    	    ex_=(Token)input.LT(1);
                    	    match(input,90,FOLLOW_90_in_try_stmt2744); 
                    	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:809:4: (t1= test ( COMMA t2= test )? )?
                    	    int alt46=2;
                    	    int LA46_0 = input.LA(1);

                    	    if ( (LA46_0==LPAREN||LA46_0==NAME||(LA46_0>=PLUS && LA46_0<=MINUS)||(LA46_0>=TILDE && LA46_0<=LBRACK)||LA46_0==LCURLY||(LA46_0>=BACKQUOTE && LA46_0<=STRING)||LA46_0==94||LA46_0==96) ) {
                    	        alt46=1;
                    	    }
                    	    switch (alt46) {
                    	        case 1 :
                    	            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:810:5: t1= test ( COMMA t2= test )?
                    	            {
                    	            pushFollow(FOLLOW_test_in_try_stmt2759);
                    	            t1=test();
                    	            _fsp--;

                    	            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:811:5: ( COMMA t2= test )?
                    	            int alt45=2;
                    	            int LA45_0 = input.LA(1);

                    	            if ( (LA45_0==COMMA) ) {
                    	                alt45=1;
                    	            }
                    	            switch (alt45) {
                    	                case 1 :
                    	                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:811:6: COMMA t2= test
                    	                    {
                    	                    match(input,COMMA,FOLLOW_COMMA_in_try_stmt2766); 
                    	                    pushFollow(FOLLOW_test_in_try_stmt2772);
                    	                    t2=test();
                    	                    _fsp--;


                    	                    }
                    	                    break;

                    	            }


                    	            }
                    	            break;

                    	    }

                    	    match(input,COLON,FOLLOW_COLON_in_try_stmt2785); 
                    	    pushFollow(FOLLOW_suite_in_try_stmt2794);
                    	    su=suite();
                    	    _fsp--;


                    	    				lastTok = ex_;
                    	    				catches.add( new PythonExceptStatement( toDLTK( ex_ ), t1, t2, su ) );
                    	    			

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt47 >= 1 ) break loop47;
                                EarlyExitException eee =
                                    new EarlyExitException(47, input);
                                throw eee;
                        }
                        cnt47++;
                    } while (true);

                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:820:3: ( (elseT= 'else' COLON elseBlock= suite ) )?
                    int alt48=2;
                    int LA48_0 = input.LA(1);

                    if ( (LA48_0==86) ) {
                        alt48=1;
                    }
                    switch (alt48) {
                        case 1 :
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:821:4: (elseT= 'else' COLON elseBlock= suite )
                            {
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:821:4: (elseT= 'else' COLON elseBlock= suite )
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:821:6: elseT= 'else' COLON elseBlock= suite
                            {
                            elseT=(Token)input.LT(1);
                            match(input,86,FOLLOW_86_in_try_stmt2819); 
                            match(input,COLON,FOLLOW_COLON_in_try_stmt2825); 
                            pushFollow(FOLLOW_suite_in_try_stmt2835);
                            elseBlock=suite();
                            _fsp--;


                            					lastTok = elseT;
                            				

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:833:9: (fin= 'finally' COLON su= suite )
                    {
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:833:9: (fin= 'finally' COLON su= suite )
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:834:10: fin= 'finally' COLON su= suite
                    {
                    fin=(Token)input.LT(1);
                    match(input,91,FOLLOW_91_in_try_stmt2946); 

                    	       		lastTok = fin;
                            	
                    match(input,COLON,FOLLOW_COLON_in_try_stmt2969); 
                    pushFollow(FOLLOW_suite_in_try_stmt2985);
                    su=suite();
                    _fsp--;


                            		catches.add( new TryFinallyStatement( toDLTK( fin ), su ) );
                            	

                    }


                    }
                    break;

            }


            		statement = new PythonTryStatement( toDLTK( is ), body, catches );
            		statement.setElseStatement( elseBlock );
                    

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end try_stmt


    // $ANTLR start suite
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:851:1: suite returns [ Block statement = new Block() ] : ( simple_stmt[ l ] | NEWLINE ind= INDENT (k= stmt )+ d= DEDENT ) ;
    public final Block suite() throws RecognitionException {
        Block statement =  new Block();

        Token ind=null;
        Token d=null;
        ArrayList k = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:851:48: ( ( simple_stmt[ l ] | NEWLINE ind= INDENT (k= stmt )+ d= DEDENT ) )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:852:4: ( simple_stmt[ l ] | NEWLINE ind= INDENT (k= stmt )+ d= DEDENT )
            {

            	  	ArrayList l = new ArrayList();		
            	  	int startPos = -1;
            	  	int endPos = -1;
            	  
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:857:4: ( simple_stmt[ l ] | NEWLINE ind= INDENT (k= stmt )+ d= DEDENT )
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==LPAREN||LA51_0==NAME||(LA51_0>=PLUS && LA51_0<=MINUS)||(LA51_0>=TILDE && LA51_0<=LBRACK)||LA51_0==LCURLY||(LA51_0>=BACKQUOTE && LA51_0<=STRING)||(LA51_0>=69 && LA51_0<=78)||(LA51_0>=80 && LA51_0<=81)||LA51_0==83||LA51_0==94||LA51_0==96) ) {
                alt51=1;
            }
            else if ( (LA51_0==NEWLINE) ) {
                alt51=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("857:4: ( simple_stmt[ l ] | NEWLINE ind= INDENT (k= stmt )+ d= DEDENT )", 51, 0, input);

                throw nvae;
            }
            switch (alt51) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:858:5: simple_stmt[ l ]
                    {
                    pushFollow(FOLLOW_simple_stmt_in_suite3043);
                    simple_stmt( l );
                    _fsp--;


                    	  		Iterator i = l.iterator();
                    	  		while( i.hasNext()) {
                    	  			Statement sst = (Statement)i.next();
                    	  			if( sst != null ) {
                    	  				if( sst.sourceStart() < startPos || startPos == -1 ) {
                    	  					startPos = sst.sourceStart();
                    	  				} 
                    	  				if( sst.sourceEnd() > endPos || endPos == -1 ) {
                    	  					endPos = sst.sourceEnd();
                    	  				}
                    	  				statement.addStatement( sst );
                    	  			}
                    	  		}
                    	  	

                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:875:5: NEWLINE ind= INDENT (k= stmt )+ d= DEDENT
                    {
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_suite3064); 
                    ind=(Token)input.LT(1);
                    match(input,INDENT,FOLLOW_INDENT_in_suite3074); 

                    	  			if( ind != null ) {
                    	  				
                    		  			int s = converter.convert( ind ).getColumn();
                    		  			if( s < startPos && s != -1 ) {
                    		  				startPos = s;
                    		  			}
                    	  			}
                    	  			//ArrayList k;
                    	  		
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:887:5: (k= stmt )+
                    int cnt50=0;
                    loop50:
                    do {
                        int alt50=2;
                        int LA50_0 = input.LA(1);

                        if ( ((LA50_0>=DECORATOR_S && LA50_0<=LPAREN)||LA50_0==NAME||(LA50_0>=PLUS && LA50_0<=MINUS)||(LA50_0>=TILDE && LA50_0<=LBRACK)||LA50_0==LCURLY||(LA50_0>=BACKQUOTE && LA50_0<=STRING)||(LA50_0>=68 && LA50_0<=78)||(LA50_0>=80 && LA50_0<=81)||(LA50_0>=83 && LA50_0<=84)||(LA50_0>=87 && LA50_0<=89)||LA50_0==94||(LA50_0>=96 && LA50_0<=98)) ) {
                            alt50=1;
                        }


                        switch (alt50) {
                    	case 1 :
                    	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:888:6: k= stmt
                    	    {
                    	    pushFollow(FOLLOW_stmt_in_suite3098);
                    	    k=stmt();
                    	    _fsp--;


                    	    	  			//l.addAll( k );
                    	    	  			if( k != null ) {
                    	    		  			Iterator i = k.iterator();
                    	      					while( i.hasNext() ) {
                    	    	  					Statement sst = (Statement)i.next();
                    	    	  					if( sst != null ) {
                    	    	  						statement.addStatement( sst );
                    	    	  						if( sst.sourceStart() < startPos || startPos == -1 ) {
                    	    	  							startPos = sst.sourceStart();
                    	    	  						} 
                    	    	  						if( sst.sourceEnd() > endPos || endPos == -1 ) {
                    	    	  							endPos = sst.sourceEnd();
                    	    	  						}
                    	    	  					}
                    	    	  				}
                    	    	  			}
                    	    	  		

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt50 >= 1 ) break loop50;
                                EarlyExitException eee =
                                    new EarlyExitException(50, input);
                                throw eee;
                        }
                        cnt50++;
                    } while (true);

                    d=(Token)input.LT(1);
                    match(input,DEDENT,FOLLOW_DEDENT_in_suite3124); 

                    	  			if( d != null ) {
                    		  			int e = converter.convert( d ).getColumn() - 1;
                    		  			if( e > endPos ) {
                    		  				endPos = e;
                    		  			}
                    	  			}
                    	  		

                    }
                    break;

            }


            	   	//endPos -= 1;
            	   	//statement = new Block( startPos, endPos, l );
            	   	statement.setStart( startPos );
            	   	//if( endPos == -1 ) {
            	   	//	endPos = length;
            	   	//}
            	   	statement.setEnd( endPos );
            	  

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return statement;
    }
    // $ANTLR end suite


    // $ANTLR start or_test
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:935:1: or_test returns [ Expression exp = null ] : exp0= and_test (r= 'or' v= and_test )* ;
    public final Expression or_test() throws RecognitionException {
        Expression exp =  null;

        Token r=null;
        Expression exp0 = null;

        Expression v = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:935:43: (exp0= and_test (r= 'or' v= and_test )* )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:936:2: exp0= and_test (r= 'or' v= and_test )*
            {
            pushFollow(FOLLOW_and_test_in_or_test3174);
            exp0=and_test();
            _fsp--;


            		exp = exp0;
            	
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:940:2: (r= 'or' v= and_test )*
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==92) ) {
                    alt52=1;
                }


                switch (alt52) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:941:3: r= 'or' v= and_test
            	    {
            	    r=(Token)input.LT(1);
            	    match(input,92,FOLLOW_92_in_or_test3188); 
            	    pushFollow(FOLLOW_and_test_in_or_test3197);
            	    v=and_test();
            	    _fsp--;


            	    				exp = new BinaryExpression( exp0, Expression.E_LOR, v);
            	    			

            	    }
            	    break;

            	default :
            	    break loop52;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return exp;
    }
    // $ANTLR end or_test


    // $ANTLR start test
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:949:1: test returns [ Expression exp = null ] : (exp0= or_test | exp0= lambdef );
    public final Expression test() throws RecognitionException {
        Expression exp =  null;

        Expression exp0 = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:949:39: (exp0= or_test | exp0= lambdef )
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==LPAREN||LA53_0==NAME||(LA53_0>=PLUS && LA53_0<=MINUS)||(LA53_0>=TILDE && LA53_0<=LBRACK)||LA53_0==LCURLY||(LA53_0>=BACKQUOTE && LA53_0<=STRING)||LA53_0==94) ) {
                alt53=1;
            }
            else if ( (LA53_0==96) ) {
                alt53=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("949:1: test returns [ Expression exp = null ] : (exp0= or_test | exp0= lambdef );", 53, 0, input);

                throw nvae;
            }
            switch (alt53) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:950:3: exp0= or_test
                    {
                    pushFollow(FOLLOW_or_test_in_test3226);
                    exp0=or_test();
                    _fsp--;

                    exp = exp0;

                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:952:4: exp0= lambdef
                    {
                    pushFollow(FOLLOW_lambdef_in_test3241);
                    exp0=lambdef();
                    _fsp--;

                    exp = exp0;

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return exp;
    }
    // $ANTLR end test


    // $ANTLR start and_test
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:955:1: and_test returns [ Expression exp = null ] : exp0= not_test (m= 'and' v= not_test )* ;
    public final Expression and_test() throws RecognitionException {
        Expression exp =  null;

        Token m=null;
        Expression exp0 = null;

        Expression v = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:955:43: (exp0= not_test (m= 'and' v= not_test )* )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:956:2: exp0= not_test (m= 'and' v= not_test )*
            {
            pushFollow(FOLLOW_not_test_in_and_test3264);
            exp0=not_test();
            _fsp--;


            		exp = exp0;
            	
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:960:2: (m= 'and' v= not_test )*
            loop54:
            do {
                int alt54=2;
                int LA54_0 = input.LA(1);

                if ( (LA54_0==93) ) {
                    alt54=1;
                }


                switch (alt54) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:961:3: m= 'and' v= not_test
            	    {
            	    m=(Token)input.LT(1);
            	    match(input,93,FOLLOW_93_in_and_test3278); 
            	    pushFollow(FOLLOW_not_test_in_and_test3287);
            	    v=not_test();
            	    _fsp--;


            	    				exp = new BinaryExpression( exp0, Expression.E_LAND,v);
            	    			

            	    }
            	    break;

            	default :
            	    break loop54;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return exp;
    }
    // $ANTLR end and_test


    // $ANTLR start not_test
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:969:1: not_test returns [ Expression exp = null ] : ( (n= 'not' v= not_test ) | exp0= comparison );
    public final Expression not_test() throws RecognitionException {
        Expression exp =  null;

        Token n=null;
        Expression v = null;

        Expression exp0 = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:969:43: ( (n= 'not' v= not_test ) | exp0= comparison )
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==94) ) {
                alt55=1;
            }
            else if ( (LA55_0==LPAREN||LA55_0==NAME||(LA55_0>=PLUS && LA55_0<=MINUS)||(LA55_0>=TILDE && LA55_0<=LBRACK)||LA55_0==LCURLY||(LA55_0>=BACKQUOTE && LA55_0<=STRING)) ) {
                alt55=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("969:1: not_test returns [ Expression exp = null ] : ( (n= 'not' v= not_test ) | exp0= comparison );", 55, 0, input);

                throw nvae;
            }
            switch (alt55) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:970:2: (n= 'not' v= not_test )
                    {
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:970:2: (n= 'not' v= not_test )
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:971:3: n= 'not' v= not_test
                    {
                    n=(Token)input.LT(1);
                    match(input,94,FOLLOW_94_in_not_test3320); 
                    pushFollow(FOLLOW_not_test_in_not_test3329);
                    v=not_test();
                    _fsp--;


                    				exp = new UnaryExpression( toDLTK( n ), Expression.E_LNOT, v );
                    			

                    }


                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:977:4: exp0= comparison
                    {
                    pushFollow(FOLLOW_comparison_in_not_test3348);
                    exp0=comparison();
                    _fsp--;

                     exp = exp0;

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return exp;
    }
    // $ANTLR end not_test


    // $ANTLR start comparison
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:980:1: comparison returns [ Expression exp = null ] : exp0= expr (tu= comp_op v= expr )* ;
    public final Expression comparison() throws RecognitionException {
        Expression exp =  null;

        Expression exp0 = null;

        int tu = 0;

        Expression v = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:980:45: (exp0= expr (tu= comp_op v= expr )* )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:981:2: exp0= expr (tu= comp_op v= expr )*
            {
            pushFollow(FOLLOW_expr_in_comparison3371);
            exp0=expr();
            _fsp--;


            		exp = exp0;
            	
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:985:2: (tu= comp_op v= expr )*
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( ((LA56_0>=LESS && LA56_0<=NOTEQUAL)||LA56_0==82||(LA56_0>=94 && LA56_0<=95)) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:986:3: tu= comp_op v= expr
            	    {
            	    pushFollow(FOLLOW_comp_op_in_comparison3385);
            	    tu=comp_op();
            	    _fsp--;

            	    pushFollow(FOLLOW_expr_in_comparison3394);
            	    v=expr();
            	    _fsp--;


            	    				exp = new BinaryExpression( exp0, tu, v );
            	    			

            	    }
            	    break;

            	default :
            	    break loop56;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return exp;
    }
    // $ANTLR end comparison


    // $ANTLR start comp_op
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:994:1: comp_op returns [ int t = Expression.E_EMPTY ] : (t1= LESS | t2= GREATER | t3= EQUAL | t4= GREATEREQUAL | t5= LESSEQUAL | t6= ALT_NOTEQUAL | t7= NOTEQUAL | t8= 'in' | t9= 'not' 'in' | t10= 'is' | t11= 'is' 'not' );
    public final int comp_op() throws RecognitionException {
        int t =  Expression.E_EMPTY;

        Token t1=null;
        Token t2=null;
        Token t3=null;
        Token t4=null;
        Token t5=null;
        Token t6=null;
        Token t7=null;
        Token t8=null;
        Token t9=null;
        Token t10=null;
        Token t11=null;

        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:994:48: (t1= LESS | t2= GREATER | t3= EQUAL | t4= GREATEREQUAL | t5= LESSEQUAL | t6= ALT_NOTEQUAL | t7= NOTEQUAL | t8= 'in' | t9= 'not' 'in' | t10= 'is' | t11= 'is' 'not' )
            int alt57=11;
            switch ( input.LA(1) ) {
            case LESS:
                {
                alt57=1;
                }
                break;
            case GREATER:
                {
                alt57=2;
                }
                break;
            case EQUAL:
                {
                alt57=3;
                }
                break;
            case GREATEREQUAL:
                {
                alt57=4;
                }
                break;
            case LESSEQUAL:
                {
                alt57=5;
                }
                break;
            case ALT_NOTEQUAL:
                {
                alt57=6;
                }
                break;
            case NOTEQUAL:
                {
                alt57=7;
                }
                break;
            case 82:
                {
                alt57=8;
                }
                break;
            case 94:
                {
                alt57=9;
                }
                break;
            case 95:
                {
                int LA57_10 = input.LA(2);

                if ( (LA57_10==94) ) {
                    alt57=11;
                }
                else if ( (LA57_10==LPAREN||LA57_10==NAME||(LA57_10>=PLUS && LA57_10<=MINUS)||(LA57_10>=TILDE && LA57_10<=LBRACK)||LA57_10==LCURLY||(LA57_10>=BACKQUOTE && LA57_10<=STRING)) ) {
                    alt57=10;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("994:1: comp_op returns [ int t = Expression.E_EMPTY ] : (t1= LESS | t2= GREATER | t3= EQUAL | t4= GREATEREQUAL | t5= LESSEQUAL | t6= ALT_NOTEQUAL | t7= NOTEQUAL | t8= 'in' | t9= 'not' 'in' | t10= 'is' | t11= 'is' 'not' );", 57, 10, input);

                    throw nvae;
                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("994:1: comp_op returns [ int t = Expression.E_EMPTY ] : (t1= LESS | t2= GREATER | t3= EQUAL | t4= GREATEREQUAL | t5= LESSEQUAL | t6= ALT_NOTEQUAL | t7= NOTEQUAL | t8= 'in' | t9= 'not' 'in' | t10= 'is' | t11= 'is' 'not' );", 57, 0, input);

                throw nvae;
            }

            switch (alt57) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:995:3: t1= LESS
                    {
                    t1=(Token)input.LT(1);
                    match(input,LESS,FOLLOW_LESS_in_comp_op3426); 
                    t = Expression.E_LT;

                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:996:3: t2= GREATER
                    {
                    t2=(Token)input.LT(1);
                    match(input,GREATER,FOLLOW_GREATER_in_comp_op3438); 
                    t = Expression.E_GT;

                    }
                    break;
                case 3 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:997:3: t3= EQUAL
                    {
                    t3=(Token)input.LT(1);
                    match(input,EQUAL,FOLLOW_EQUAL_in_comp_op3449); 
                    t = Expression.E_EQUAL;

                    }
                    break;
                case 4 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:998:3: t4= GREATEREQUAL
                    {
                    t4=(Token)input.LT(1);
                    match(input,GREATEREQUAL,FOLLOW_GREATEREQUAL_in_comp_op3462); 
                    t = Expression.E_GE;

                    }
                    break;
                case 5 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:999:3: t5= LESSEQUAL
                    {
                    t5=(Token)input.LT(1);
                    match(input,LESSEQUAL,FOLLOW_LESSEQUAL_in_comp_op3472); 
                    t = Expression.E_LE;

                    }
                    break;
                case 6 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1000:3: t6= ALT_NOTEQUAL
                    {
                    t6=(Token)input.LT(1);
                    match(input,ALT_NOTEQUAL,FOLLOW_ALT_NOTEQUAL_in_comp_op3486); 
                    t = Expression.E_NOT_EQUAL2;

                    }
                    break;
                case 7 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1001:3: t7= NOTEQUAL
                    {
                    t7=(Token)input.LT(1);
                    match(input,NOTEQUAL,FOLLOW_NOTEQUAL_in_comp_op3498); 
                    t = Expression.E_NOT_EQUAL;

                    }
                    break;
                case 8 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1002:3: t8= 'in'
                    {
                    t8=(Token)input.LT(1);
                    match(input,82,FOLLOW_82_in_comp_op3513); 
                    t = Expression.E_IN;

                    }
                    break;
                case 9 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1003:3: t9= 'not' 'in'
                    {
                    t9=(Token)input.LT(1);
                    match(input,94,FOLLOW_94_in_comp_op3523); 
                    match(input,82,FOLLOW_82_in_comp_op3525); 
                    t = Expression.E_NOTIN;

                    }
                    break;
                case 10 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1004:3: t10= 'is'
                    {
                    t10=(Token)input.LT(1);
                    match(input,95,FOLLOW_95_in_comp_op3535); 
                    t = Expression.E_IS;

                    }
                    break;
                case 11 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1005:3: t11= 'is' 'not'
                    {
                    t11=(Token)input.LT(1);
                    match(input,95,FOLLOW_95_in_comp_op3545); 
                    match(input,94,FOLLOW_94_in_comp_op3547); 
                    t = Expression.E_ISNOT;

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return t;
    }
    // $ANTLR end comp_op


    // $ANTLR start expr
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1008:1: expr returns [ Expression e = null] : e0= xor_expr (tu= VBAR v= xor_expr )* ;
    public final Expression expr() throws RecognitionException {
        Expression e =  null;

        Token tu=null;
        Expression e0 = null;

        Expression v = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1008:36: (e0= xor_expr (tu= VBAR v= xor_expr )* )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1009:2: e0= xor_expr (tu= VBAR v= xor_expr )*
            {
            pushFollow(FOLLOW_xor_expr_in_expr3569);
            e0=xor_expr();
            _fsp--;


            		e = e0;
            	
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1013:2: (tu= VBAR v= xor_expr )*
            loop58:
            do {
                int alt58=2;
                int LA58_0 = input.LA(1);

                if ( (LA58_0==VBAR) ) {
                    alt58=1;
                }


                switch (alt58) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1014:3: tu= VBAR v= xor_expr
            	    {
            	    tu=(Token)input.LT(1);
            	    match(input,VBAR,FOLLOW_VBAR_in_expr3583); 
            	    pushFollow(FOLLOW_xor_expr_in_expr3593);
            	    v=xor_expr();
            	    _fsp--;


            	    	 			e = new BinaryExpression( e0, Expression.E_LOR,v);
            	    	 		

            	    }
            	    break;

            	default :
            	    break loop58;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return e;
    }
    // $ANTLR end expr


    // $ANTLR start xor_expr
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1022:1: xor_expr returns [ Expression e = null ] : e0= and_expr (tu= CIRCUMFLEX v= and_expr )* ;
    public final Expression xor_expr() throws RecognitionException {
        Expression e =  null;

        Token tu=null;
        Expression e0 = null;

        Expression v = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1022:41: (e0= and_expr (tu= CIRCUMFLEX v= and_expr )* )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1023:2: e0= and_expr (tu= CIRCUMFLEX v= and_expr )*
            {
            pushFollow(FOLLOW_and_expr_in_xor_expr3626);
            e0=and_expr();
            _fsp--;


            		e = e0;
            	
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1027:2: (tu= CIRCUMFLEX v= and_expr )*
            loop59:
            do {
                int alt59=2;
                int LA59_0 = input.LA(1);

                if ( (LA59_0==CIRCUMFLEX) ) {
                    alt59=1;
                }


                switch (alt59) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1028:3: tu= CIRCUMFLEX v= and_expr
            	    {
            	    tu=(Token)input.LT(1);
            	    match(input,CIRCUMFLEX,FOLLOW_CIRCUMFLEX_in_xor_expr3640); 
            	    pushFollow(FOLLOW_and_expr_in_xor_expr3649);
            	    v=and_expr();
            	    _fsp--;


            	    				e = new BinaryExpression( e0, Expression.E_XOR,v);
            	    			

            	    }
            	    break;

            	default :
            	    break loop59;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return e;
    }
    // $ANTLR end xor_expr


    // $ANTLR start and_expr
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1036:1: and_expr returns [ Expression e = null ] : e0= shift_expr (tu= AMPER v= shift_expr )* ;
    public final Expression and_expr() throws RecognitionException {
        Expression e =  null;

        Token tu=null;
        Expression e0 = null;

        Expression v = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1036:41: (e0= shift_expr (tu= AMPER v= shift_expr )* )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1037:2: e0= shift_expr (tu= AMPER v= shift_expr )*
            {
            pushFollow(FOLLOW_shift_expr_in_and_expr3680);
            e0=shift_expr();
            _fsp--;


            		e = e0;
            	
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1041:2: (tu= AMPER v= shift_expr )*
            loop60:
            do {
                int alt60=2;
                int LA60_0 = input.LA(1);

                if ( (LA60_0==AMPER) ) {
                    alt60=1;
                }


                switch (alt60) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1042:3: tu= AMPER v= shift_expr
            	    {
            	    tu=(Token)input.LT(1);
            	    match(input,AMPER,FOLLOW_AMPER_in_and_expr3695); 
            	    pushFollow(FOLLOW_shift_expr_in_and_expr3705);
            	    v=shift_expr();
            	    _fsp--;


            	    	 			e = new BinaryExpression( e0, Expression.E_LAND, v );
            	    	 		

            	    }
            	    break;

            	default :
            	    break loop60;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return e;
    }
    // $ANTLR end and_expr


    // $ANTLR start shift_expr
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1049:1: shift_expr returns [ Expression e = null ] : e0= arith_expr ( (t1= LEFTSHIFT | t2= RIGHTSHIFT ) v= arith_expr )* ;
    public final Expression shift_expr() throws RecognitionException {
        Expression e =  null;

        Token t1=null;
        Token t2=null;
        Expression e0 = null;

        Expression v = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1049:43: (e0= arith_expr ( (t1= LEFTSHIFT | t2= RIGHTSHIFT ) v= arith_expr )* )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1050:2: e0= arith_expr ( (t1= LEFTSHIFT | t2= RIGHTSHIFT ) v= arith_expr )*
            {
            pushFollow(FOLLOW_arith_expr_in_shift_expr3735);
            e0=arith_expr();
            _fsp--;


            		e = e0;
            	

            			Token tk = null;
            		
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1057:2: ( (t1= LEFTSHIFT | t2= RIGHTSHIFT ) v= arith_expr )*
            loop62:
            do {
                int alt62=2;
                int LA62_0 = input.LA(1);

                if ( (LA62_0==RIGHTSHIFT||LA62_0==LEFTSHIFT) ) {
                    alt62=1;
                }


                switch (alt62) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1058:3: (t1= LEFTSHIFT | t2= RIGHTSHIFT ) v= arith_expr
            	    {
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1058:3: (t1= LEFTSHIFT | t2= RIGHTSHIFT )
            	    int alt61=2;
            	    int LA61_0 = input.LA(1);

            	    if ( (LA61_0==LEFTSHIFT) ) {
            	        alt61=1;
            	    }
            	    else if ( (LA61_0==RIGHTSHIFT) ) {
            	        alt61=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("1058:3: (t1= LEFTSHIFT | t2= RIGHTSHIFT )", 61, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt61) {
            	        case 1 :
            	            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1059:4: t1= LEFTSHIFT
            	            {
            	            t1=(Token)input.LT(1);
            	            match(input,LEFTSHIFT,FOLLOW_LEFTSHIFT_in_shift_expr3759); 

            	            					tk = t1;
            	            				

            	            }
            	            break;
            	        case 2 :
            	            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1064:4: t2= RIGHTSHIFT
            	            {
            	            t2=(Token)input.LT(1);
            	            match(input,RIGHTSHIFT,FOLLOW_RIGHTSHIFT_in_shift_expr3780); 

            	            					tk = t2;
            	            				

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_arith_expr_in_shift_expr3802);
            	    v=arith_expr();
            	    _fsp--;

            	     
            	      	 			if( tk == t1 ) 
            	      	 				e = new BinaryExpression( e0, Expression.E_LSHIFT, v );
            	      	 			else 
            	      	 				e = new BinaryExpression( e0, Expression.E_RSHIFT, v);
            	      	 		

            	    }
            	    break;

            	default :
            	    break loop62;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return e;
    }
    // $ANTLR end shift_expr


    // $ANTLR start arith_expr
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1079:1: arith_expr returns [ Expression e = null ] : e0= term ( (t1= PLUS | t2= MINUS ) v= term )* ;
    public final Expression arith_expr() throws RecognitionException {
        Expression e =  null;

        Token t1=null;
        Token t2=null;
        Expression e0 = null;

        Expression v = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1079:44: (e0= term ( (t1= PLUS | t2= MINUS ) v= term )* )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1080:2: e0= term ( (t1= PLUS | t2= MINUS ) v= term )*
            {
            pushFollow(FOLLOW_term_in_arith_expr3838);
            e0=term();
            _fsp--;


            		e = e0;
            	

            			Token tk = null;
            		
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1087:2: ( (t1= PLUS | t2= MINUS ) v= term )*
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( ((LA64_0>=PLUS && LA64_0<=MINUS)) ) {
                    alt64=1;
                }


                switch (alt64) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1088:3: (t1= PLUS | t2= MINUS ) v= term
            	    {
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1088:3: (t1= PLUS | t2= MINUS )
            	    int alt63=2;
            	    int LA63_0 = input.LA(1);

            	    if ( (LA63_0==PLUS) ) {
            	        alt63=1;
            	    }
            	    else if ( (LA63_0==MINUS) ) {
            	        alt63=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("1088:3: (t1= PLUS | t2= MINUS )", 63, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt63) {
            	        case 1 :
            	            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1089:4: t1= PLUS
            	            {
            	            t1=(Token)input.LT(1);
            	            match(input,PLUS,FOLLOW_PLUS_in_arith_expr3862); 

            	            					tk = t1;
            	            				

            	            }
            	            break;
            	        case 2 :
            	            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1094:4: t2= MINUS
            	            {
            	            t2=(Token)input.LT(1);
            	            match(input,MINUS,FOLLOW_MINUS_in_arith_expr3882); 
            	             
            	            					tk = t2;
            	            				

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_term_in_arith_expr3901);
            	    v=term();
            	    _fsp--;


            	    	 	 		if( tk == t1 ) 
            	    	 	 			e = new BinaryExpression( e0, Expression.E_PLUS, v );
            	    	 	 		else 
            	    	 	 			e = new BinaryExpression( e0, Expression.E_MINUS, v );
            	    	 	 	

            	    }
            	    break;

            	default :
            	    break loop64;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return e;
    }
    // $ANTLR end arith_expr


    // $ANTLR start term
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1109:1: term returns [ Expression e = null] : e0= factor ( (t1= STAR | t2= SLASH | t3= PERCENT | t4= DOUBLESLASH ) v= factor )* ;
    public final Expression term() throws RecognitionException {
        Expression e =  null;

        Token t1=null;
        Token t2=null;
        Token t3=null;
        Token t4=null;
        Expression e0 = null;

        Expression v = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1109:36: (e0= factor ( (t1= STAR | t2= SLASH | t3= PERCENT | t4= DOUBLESLASH ) v= factor )* )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1110:2: e0= factor ( (t1= STAR | t2= SLASH | t3= PERCENT | t4= DOUBLESLASH ) v= factor )*
            {
            pushFollow(FOLLOW_factor_in_term3931);
            e0=factor();
            _fsp--;


            		e = e0;
            	

            			int type = Expression.E_EMPTY;
            		
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1117:2: ( (t1= STAR | t2= SLASH | t3= PERCENT | t4= DOUBLESLASH ) v= factor )*
            loop66:
            do {
                int alt66=2;
                int LA66_0 = input.LA(1);

                if ( (LA66_0==STAR||(LA66_0>=SLASH && LA66_0<=DOUBLESLASH)) ) {
                    alt66=1;
                }


                switch (alt66) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1118:3: (t1= STAR | t2= SLASH | t3= PERCENT | t4= DOUBLESLASH ) v= factor
            	    {
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1118:3: (t1= STAR | t2= SLASH | t3= PERCENT | t4= DOUBLESLASH )
            	    int alt65=4;
            	    switch ( input.LA(1) ) {
            	    case STAR:
            	        {
            	        alt65=1;
            	        }
            	        break;
            	    case SLASH:
            	        {
            	        alt65=2;
            	        }
            	        break;
            	    case PERCENT:
            	        {
            	        alt65=3;
            	        }
            	        break;
            	    case DOUBLESLASH:
            	        {
            	        alt65=4;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("1118:3: (t1= STAR | t2= SLASH | t3= PERCENT | t4= DOUBLESLASH )", 65, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt65) {
            	        case 1 :
            	            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1119:5: t1= STAR
            	            {
            	            t1=(Token)input.LT(1);
            	            match(input,STAR,FOLLOW_STAR_in_term3955); 

            	            	  			type = Expression.E_MULT;
            	            	  		

            	            }
            	            break;
            	        case 2 :
            	            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1124:5: t2= SLASH
            	            {
            	            t2=(Token)input.LT(1);
            	            match(input,SLASH,FOLLOW_SLASH_in_term3979); 

            	            	  			type = Expression.E_DIV;
            	            	  		

            	            }
            	            break;
            	        case 3 :
            	            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1129:6: t3= PERCENT
            	            {
            	            t3=(Token)input.LT(1);
            	            match(input,PERCENT,FOLLOW_PERCENT_in_term4004); 

            	            	   			type = Expression.E_MOD;
            	            	   		

            	            }
            	            break;
            	        case 4 :
            	            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1134:7: t4= DOUBLESLASH
            	            {
            	            t4=(Token)input.LT(1);
            	            match(input,DOUBLESLASH,FOLLOW_DOUBLESLASH_in_term4032); 

            	            	    			type = Expression.E_MOD;
            	            	    		

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_factor_in_term4058);
            	    v=factor();
            	    _fsp--;


            	      	    		e = new BinaryExpression( e0,type,v );
            	      	    	

            	    }
            	    break;

            	default :
            	    break loop66;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return e;
    }
    // $ANTLR end term


    // $ANTLR start factor
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1146:1: factor returns [ Expression e = new EmptyExpression() ] : ( (t1= PLUS | t2= MINUS | t3= TILDE ) e0= factor | e0= power );
    public final Expression factor() throws RecognitionException {
        Expression e =  new EmptyExpression();

        Token t1=null;
        Token t2=null;
        Token t3=null;
        Expression e0 = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1146:57: ( (t1= PLUS | t2= MINUS | t3= TILDE ) e0= factor | e0= power )
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( ((LA68_0>=PLUS && LA68_0<=MINUS)||LA68_0==TILDE) ) {
                alt68=1;
            }
            else if ( (LA68_0==LPAREN||LA68_0==NAME||LA68_0==LBRACK||LA68_0==LCURLY||(LA68_0>=BACKQUOTE && LA68_0<=STRING)) ) {
                alt68=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1146:1: factor returns [ Expression e = new EmptyExpression() ] : ( (t1= PLUS | t2= MINUS | t3= TILDE ) e0= factor | e0= power );", 68, 0, input);

                throw nvae;
            }
            switch (alt68) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1147:4: (t1= PLUS | t2= MINUS | t3= TILDE ) e0= factor
                    {

                    	  	Token tk = null;
                    	  
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1150:4: (t1= PLUS | t2= MINUS | t3= TILDE )
                    int alt67=3;
                    switch ( input.LA(1) ) {
                    case PLUS:
                        {
                        alt67=1;
                        }
                        break;
                    case MINUS:
                        {
                        alt67=2;
                        }
                        break;
                    case TILDE:
                        {
                        alt67=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("1150:4: (t1= PLUS | t2= MINUS | t3= TILDE )", 67, 0, input);

                        throw nvae;
                    }

                    switch (alt67) {
                        case 1 :
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1151:5: t1= PLUS
                            {
                            t1=(Token)input.LT(1);
                            match(input,PLUS,FOLLOW_PLUS_in_factor4104); 

                            	  			tk = t1;
                            	  		

                            }
                            break;
                        case 2 :
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1156:5: t2= MINUS
                            {
                            t2=(Token)input.LT(1);
                            match(input,MINUS,FOLLOW_MINUS_in_factor4127); 

                            	  			tk = t2;
                            	  		

                            }
                            break;
                        case 3 :
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1161:5: t3= TILDE
                            {
                            t3=(Token)input.LT(1);
                            match(input,TILDE,FOLLOW_TILDE_in_factor4149); 

                            	  			tk = t3;
                            	  		

                            }
                            break;

                    }

                    pushFollow(FOLLOW_factor_in_factor4171);
                    e0=factor();
                    _fsp--;


                    	  		if( tk == t1 ) {	  			
                    	  			e = new UnaryExpression( toDLTK( tk ), Expression.E_PLUS, e0);
                    	  		}
                    	  		else if( tk == t2 ) {
                    	  			e = new UnaryExpression( toDLTK( tk ), Expression.E_MINUS, e0);
                    	  		}
                    	  		else if( tk == t3 ) {
                    	  			e = new UnaryExpression( toDLTK( tk ), Expression.E_MINUS, e0);
                    	  		}	  		
                    	  		else {
                    	  			e = new UnaryExpression( toDLTK( tk ), Expression.E_FACTOR, e0);
                    	  		}
                    	  	

                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1182:2: e0= power
                    {
                    pushFollow(FOLLOW_power_in_factor4188);
                    e0=power();
                    _fsp--;


                    		e = e0;
                    	

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return e;
    }
    // $ANTLR end factor


    // $ANTLR start power
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1187:1: power returns [ Expression exp = null; ] : (exp0= atom (ex= trailer[ exp ] )* ( options {greedy=true; } : DOUBLESTAR expr0= factor )? ) ;
    public final Expression power() throws RecognitionException {
        Expression exp =  null;;

        Expression exp0 = null;

        Expression ex = null;

        Expression expr0 = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1187:41: ( (exp0= atom (ex= trailer[ exp ] )* ( options {greedy=true; } : DOUBLESTAR expr0= factor )? ) )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1188:3: (exp0= atom (ex= trailer[ exp ] )* ( options {greedy=true; } : DOUBLESTAR expr0= factor )? )
            {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1188:3: (exp0= atom (ex= trailer[ exp ] )* ( options {greedy=true; } : DOUBLESTAR expr0= factor )? )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1189:3: exp0= atom (ex= trailer[ exp ] )* ( options {greedy=true; } : DOUBLESTAR expr0= factor )?
            {
            pushFollow(FOLLOW_atom_in_power4215);
            exp0=atom();
            _fsp--;


            			//Expression ex = exp;
            			exp = exp0;
            		
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1194:3: (ex= trailer[ exp ] )*
            loop69:
            do {
                int alt69=2;
                int LA69_0 = input.LA(1);

                if ( (LA69_0==LPAREN||LA69_0==DOT||LA69_0==LBRACK) ) {
                    alt69=1;
                }


                switch (alt69) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1195:4: ex= trailer[ exp ]
            	    {
            	    pushFollow(FOLLOW_trailer_in_power4233);
            	    ex=trailer( exp );
            	    _fsp--;


            	    				exp = ex;
            	    			

            	    }
            	    break;

            	default :
            	    break loop69;
                }
            } while (true);

            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1200:5: ( options {greedy=true; } : DOUBLESTAR expr0= factor )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==DOUBLESTAR) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1201:29: DOUBLESTAR expr0= factor
                    {
                    match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_power4266); 
                    pushFollow(FOLLOW_factor_in_power4284);
                    expr0=factor();
                    _fsp--;


                    	  			//TODO: add factor expression.
                    //	  			PythonTestListExpression testListExpr = new PythonTestListExpression();
                    //	  			testListExpr.addExpression( exp );
                    //	  			testListExpr.addExpression( expr );
                    //	  			exp = testListExpr;
                    				exp = new BinaryExpression( exp, Expression.E_POWER, expr0 );
                    	  		

                    }
                    break;

            }


            }


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return exp;
    }
    // $ANTLR end power


    // $ANTLR start trailer
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1216:1: trailer[ Expression expr ] returns [ Expression returnExpression = null ] : (lp0= LPAREN (k= arglist )? rp0= RPAREN | lb1= LBRACK k= subscriptlist rb1= RBRACK | DOT ta= NAME ) ;
    public final Expression trailer(Expression expr) throws RecognitionException {
        Expression returnExpression =  null;

        Token lp0=null;
        Token rp0=null;
        Token lb1=null;
        Token rb1=null;
        Token ta=null;
        Expression k = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1216:74: ( (lp0= LPAREN (k= arglist )? rp0= RPAREN | lb1= LBRACK k= subscriptlist rb1= RBRACK | DOT ta= NAME ) )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1217:2: (lp0= LPAREN (k= arglist )? rp0= RPAREN | lb1= LBRACK k= subscriptlist rb1= RBRACK | DOT ta= NAME )
            {

            		//Expression k=null;
            		// Create extended variable reference.
            		if( !( expr instanceof ExtendedVariableReference ) )
            			expr = new ExtendedVariableReference( expr );
            		ExtendedVariableReference exVariableReference = ( ExtendedVariableReference )expr;
            	
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1224:2: (lp0= LPAREN (k= arglist )? rp0= RPAREN | lb1= LBRACK k= subscriptlist rb1= RBRACK | DOT ta= NAME )
            int alt72=3;
            switch ( input.LA(1) ) {
            case LPAREN:
                {
                alt72=1;
                }
                break;
            case LBRACK:
                {
                alt72=2;
                }
                break;
            case DOT:
                {
                alt72=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1224:2: (lp0= LPAREN (k= arglist )? rp0= RPAREN | lb1= LBRACK k= subscriptlist rb1= RBRACK | DOT ta= NAME )", 72, 0, input);

                throw nvae;
            }

            switch (alt72) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1225:3: lp0= LPAREN (k= arglist )? rp0= RPAREN
                    {
                    lp0=(Token)input.LT(1);
                    match(input,LPAREN,FOLLOW_LPAREN_in_trailer4332); 
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1226:4: (k= arglist )?
                    int alt71=2;
                    int LA71_0 = input.LA(1);

                    if ( (LA71_0==LPAREN||LA71_0==NAME||(LA71_0>=STAR && LA71_0<=DOUBLESTAR)||(LA71_0>=PLUS && LA71_0<=MINUS)||(LA71_0>=TILDE && LA71_0<=LBRACK)||LA71_0==LCURLY||(LA71_0>=BACKQUOTE && LA71_0<=STRING)||LA71_0==94||LA71_0==96) ) {
                        alt71=1;
                    }
                    switch (alt71) {
                        case 1 :
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1226:6: k= arglist
                            {
                            pushFollow(FOLLOW_arglist_in_trailer4344);
                            k=arglist();
                            _fsp--;


                            }
                            break;

                    }

                    rp0=(Token)input.LT(1);
                    match(input,RPAREN,FOLLOW_RPAREN_in_trailer4356); 

                    				// This is Call lets' create it
                    				if( k == null )
                    					k = new EmptyExpression();
                    				exVariableReference.addExpression( new CallHolder( toDLTK(lp0), toDLTK(rp0), k ) );
                    				returnExpression = exVariableReference;
                    			

                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1236:3: lb1= LBRACK k= subscriptlist rb1= RBRACK
                    {
                    lb1=(Token)input.LT(1);
                    match(input,LBRACK,FOLLOW_LBRACK_in_trailer4374); 
                    pushFollow(FOLLOW_subscriptlist_in_trailer4383);
                    k=subscriptlist();
                    _fsp--;

                    rb1=(Token)input.LT(1);
                    match(input,RBRACK,FOLLOW_RBRACK_in_trailer4392); 

                    				// This is subscript lets return it.
                    				//a = new PythonSubscriptAppender(k);
                    				//returnExpression = ExpressionConverter.getIndexed( expr, k );
                    				exVariableReference.addExpression( new IndexHolder( toDLTK(lb1), toDLTK(rb1), k ) );
                    				returnExpression = exVariableReference;
                    			

                    }
                    break;
                case 3 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1247:3: DOT ta= NAME
                    {
                    match(input,DOT,FOLLOW_DOT_in_trailer4407); 
                    ta=(Token)input.LT(1);
                    match(input,NAME,FOLLOW_NAME_in_trailer4415); 

                    				//a=new PythonFieldAppenter(ta);
                    				//returnExpression = ExpressionConverter.getDotted( expr, new VariableReference( toDLTK( ta ) ) );
                    				//ta.setColumn(ta.getColumn()-1);
                    				exVariableReference.addExpression( new VariableReference( toDLTK( ta ) ) );
                    				returnExpression = exVariableReference;
                    			

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return returnExpression;
    }
    // $ANTLR end trailer


    // $ANTLR start atom
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1259:1: atom returns [ Expression exp = null ] : (lb= LPAREN (exp0= tuplelist ) rb= RPAREN | lb= LPAREN rb= RPAREN | lb= LBRACK (exp0= listmaker ) rb= RBRACK | lb= LBRACK rb= RBRACK | a1= LCURLY (exp0= dictmaker ) rb= RCURLY | lb= LCURLY rb= RCURLY | lb= BACKQUOTE exp0= testlist rb= BACKQUOTE | n= NAME | i= INT | li= LONGINT | f= FLOAT | c= COMPLEX | (s= STRING )+ );
    public final Expression atom() throws RecognitionException {
        Expression exp =  null;

        Token lb=null;
        Token rb=null;
        Token a1=null;
        Token n=null;
        Token i=null;
        Token li=null;
        Token f=null;
        Token c=null;
        Token s=null;
        Expression exp0 = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1259:39: (lb= LPAREN (exp0= tuplelist ) rb= RPAREN | lb= LPAREN rb= RPAREN | lb= LBRACK (exp0= listmaker ) rb= RBRACK | lb= LBRACK rb= RBRACK | a1= LCURLY (exp0= dictmaker ) rb= RCURLY | lb= LCURLY rb= RCURLY | lb= BACKQUOTE exp0= testlist rb= BACKQUOTE | n= NAME | i= INT | li= LONGINT | f= FLOAT | c= COMPLEX | (s= STRING )+ )
            int alt74=13;
            switch ( input.LA(1) ) {
            case LPAREN:
                {
                int LA74_1 = input.LA(2);

                if ( (LA74_1==RPAREN) ) {
                    alt74=2;
                }
                else if ( (LA74_1==LPAREN||LA74_1==NAME||(LA74_1>=PLUS && LA74_1<=MINUS)||(LA74_1>=TILDE && LA74_1<=LBRACK)||LA74_1==LCURLY||(LA74_1>=BACKQUOTE && LA74_1<=STRING)||LA74_1==94||LA74_1==96) ) {
                    alt74=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1259:1: atom returns [ Expression exp = null ] : (lb= LPAREN (exp0= tuplelist ) rb= RPAREN | lb= LPAREN rb= RPAREN | lb= LBRACK (exp0= listmaker ) rb= RBRACK | lb= LBRACK rb= RBRACK | a1= LCURLY (exp0= dictmaker ) rb= RCURLY | lb= LCURLY rb= RCURLY | lb= BACKQUOTE exp0= testlist rb= BACKQUOTE | n= NAME | i= INT | li= LONGINT | f= FLOAT | c= COMPLEX | (s= STRING )+ );", 74, 1, input);

                    throw nvae;
                }
                }
                break;
            case LBRACK:
                {
                int LA74_2 = input.LA(2);

                if ( (LA74_2==RBRACK) ) {
                    alt74=4;
                }
                else if ( (LA74_2==LPAREN||LA74_2==NAME||(LA74_2>=PLUS && LA74_2<=MINUS)||(LA74_2>=TILDE && LA74_2<=LBRACK)||LA74_2==LCURLY||(LA74_2>=BACKQUOTE && LA74_2<=STRING)||LA74_2==94||LA74_2==96) ) {
                    alt74=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1259:1: atom returns [ Expression exp = null ] : (lb= LPAREN (exp0= tuplelist ) rb= RPAREN | lb= LPAREN rb= RPAREN | lb= LBRACK (exp0= listmaker ) rb= RBRACK | lb= LBRACK rb= RBRACK | a1= LCURLY (exp0= dictmaker ) rb= RCURLY | lb= LCURLY rb= RCURLY | lb= BACKQUOTE exp0= testlist rb= BACKQUOTE | n= NAME | i= INT | li= LONGINT | f= FLOAT | c= COMPLEX | (s= STRING )+ );", 74, 2, input);

                    throw nvae;
                }
                }
                break;
            case LCURLY:
                {
                int LA74_3 = input.LA(2);

                if ( (LA74_3==RCURLY) ) {
                    alt74=6;
                }
                else if ( (LA74_3==LPAREN||LA74_3==NAME||(LA74_3>=PLUS && LA74_3<=MINUS)||(LA74_3>=TILDE && LA74_3<=LBRACK)||LA74_3==LCURLY||(LA74_3>=BACKQUOTE && LA74_3<=STRING)||LA74_3==94||LA74_3==96) ) {
                    alt74=5;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1259:1: atom returns [ Expression exp = null ] : (lb= LPAREN (exp0= tuplelist ) rb= RPAREN | lb= LPAREN rb= RPAREN | lb= LBRACK (exp0= listmaker ) rb= RBRACK | lb= LBRACK rb= RBRACK | a1= LCURLY (exp0= dictmaker ) rb= RCURLY | lb= LCURLY rb= RCURLY | lb= BACKQUOTE exp0= testlist rb= BACKQUOTE | n= NAME | i= INT | li= LONGINT | f= FLOAT | c= COMPLEX | (s= STRING )+ );", 74, 3, input);

                    throw nvae;
                }
                }
                break;
            case BACKQUOTE:
                {
                alt74=7;
                }
                break;
            case NAME:
                {
                alt74=8;
                }
                break;
            case INT:
                {
                alt74=9;
                }
                break;
            case LONGINT:
                {
                alt74=10;
                }
                break;
            case FLOAT:
                {
                alt74=11;
                }
                break;
            case COMPLEX:
                {
                alt74=12;
                }
                break;
            case STRING:
                {
                alt74=13;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1259:1: atom returns [ Expression exp = null ] : (lb= LPAREN (exp0= tuplelist ) rb= RPAREN | lb= LPAREN rb= RPAREN | lb= LBRACK (exp0= listmaker ) rb= RBRACK | lb= LBRACK rb= RBRACK | a1= LCURLY (exp0= dictmaker ) rb= RCURLY | lb= LCURLY rb= RCURLY | lb= BACKQUOTE exp0= testlist rb= BACKQUOTE | n= NAME | i= INT | li= LONGINT | f= FLOAT | c= COMPLEX | (s= STRING )+ );", 74, 0, input);

                throw nvae;
            }

            switch (alt74) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1260:4: lb= LPAREN (exp0= tuplelist ) rb= RPAREN
                    {
                    lb=(Token)input.LT(1);
                    match(input,LPAREN,FOLLOW_LPAREN_in_atom4445); 
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1260:16: (exp0= tuplelist )
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1260:18: exp0= tuplelist
                    {
                    pushFollow(FOLLOW_tuplelist_in_atom4453);
                    exp0=tuplelist();
                    _fsp--;

                     exp = exp0;

                    }

                    rb=(Token)input.LT(1);
                    match(input,RPAREN,FOLLOW_RPAREN_in_atom4463); 
                     setStartEndForEmbracedExpr(exp,lb,rb); 

                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1261:4: lb= LPAREN rb= RPAREN
                    {
                    lb=(Token)input.LT(1);
                    match(input,LPAREN,FOLLOW_LPAREN_in_atom4474); 
                     exp = new PythonTupleExpression(); 
                    rb=(Token)input.LT(1);
                    match(input,RPAREN,FOLLOW_RPAREN_in_atom4482); 
                     setStartEndForEmbracedExpr(exp,lb,rb); 

                    }
                    break;
                case 3 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1262:4: lb= LBRACK (exp0= listmaker ) rb= RBRACK
                    {
                    lb=(Token)input.LT(1);
                    match(input,LBRACK,FOLLOW_LBRACK_in_atom4494); 
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1262:16: (exp0= listmaker )
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1262:18: exp0= listmaker
                    {
                    pushFollow(FOLLOW_listmaker_in_atom4502);
                    exp0=listmaker();
                    _fsp--;

                    exp = exp0; 

                    }

                    rb=(Token)input.LT(1);
                    match(input,RBRACK,FOLLOW_RBRACK_in_atom4512); 
                     setStartEndForEmbracedExpr(exp,lb,rb); 

                    }
                    break;
                case 4 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1263:4: lb= LBRACK rb= RBRACK
                    {
                    lb=(Token)input.LT(1);
                    match(input,LBRACK,FOLLOW_LBRACK_in_atom4522); 
                     exp = new PythonListExpression( ); 
                    rb=(Token)input.LT(1);
                    match(input,RBRACK,FOLLOW_RBRACK_in_atom4530); 
                     setStartEndForEmbracedExpr(exp,lb,rb); 

                    }
                    break;
                case 5 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1264:4: a1= LCURLY (exp0= dictmaker ) rb= RCURLY
                    {
                    a1=(Token)input.LT(1);
                    match(input,LCURLY,FOLLOW_LCURLY_in_atom4541); 
                    lb =a1;
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1264:24: (exp0= dictmaker )
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1264:26: exp0= dictmaker
                    {
                    pushFollow(FOLLOW_dictmaker_in_atom4551);
                    exp0=dictmaker();
                    _fsp--;

                     exp = exp0; 

                    }

                    rb=(Token)input.LT(1);
                    match(input,RCURLY,FOLLOW_RCURLY_in_atom4561); 
                     setStartEndForEmbracedExpr(exp,lb,rb); 

                    }
                    break;
                case 6 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1265:4: lb= LCURLY rb= RCURLY
                    {
                    lb=(Token)input.LT(1);
                    match(input,LCURLY,FOLLOW_LCURLY_in_atom4571); 
                     exp = new PythonDictExpression(); 
                    rb=(Token)input.LT(1);
                    match(input,RCURLY,FOLLOW_RCURLY_in_atom4579); 
                     setStartEndForEmbracedExpr(exp,lb,rb); 

                    }
                    break;
                case 7 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1266:4: lb= BACKQUOTE exp0= testlist rb= BACKQUOTE
                    {
                    lb=(Token)input.LT(1);
                    match(input,BACKQUOTE,FOLLOW_BACKQUOTE_in_atom4592); 
                    pushFollow(FOLLOW_testlist_in_atom4598);
                    exp0=testlist();
                    _fsp--;

                     exp = exp0; 
                    rb=(Token)input.LT(1);
                    match(input,BACKQUOTE,FOLLOW_BACKQUOTE_in_atom4607); 
                     setStartEndForEmbracedExpr(exp,lb,rb); 

                    }
                    break;
                case 8 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1267:4: n= NAME
                    {
                    n=(Token)input.LT(1);
                    match(input,NAME,FOLLOW_NAME_in_atom4617); 
                     exp = new VariableReference( toDLTK( n ) ); 

                    }
                    break;
                case 9 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1268:4: i= INT
                    {
                    i=(Token)input.LT(1);
                    match(input,INT,FOLLOW_INT_in_atom4629); 
                     exp = new NumericLiteral( toDLTK( i ) );

                    }
                    break;
                case 10 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1269:8: li= LONGINT
                    {
                    li=(Token)input.LT(1);
                    match(input,LONGINT,FOLLOW_LONGINT_in_atom4646); 
                     exp=new NumericLiteral( toDLTK( li ) );

                    }
                    break;
                case 11 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1270:8: f= FLOAT
                    {
                    f=(Token)input.LT(1);
                    match(input,FLOAT,FOLLOW_FLOAT_in_atom4661); 
                     exp=new NumericLiteral( toDLTK( f ) );

                    }
                    break;
                case 12 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1271:8: c= COMPLEX
                    {
                    c=(Token)input.LT(1);
                    match(input,COMPLEX,FOLLOW_COMPLEX_in_atom4679); 
                     exp=new ComplexNumericLiteral( toDLTK( c ) ); 

                    }
                    break;
                case 13 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1273:2: (s= STRING )+
                    {
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1273:2: (s= STRING )+
                    int cnt73=0;
                    loop73:
                    do {
                        int alt73=2;
                        int LA73_0 = input.LA(1);

                        if ( (LA73_0==STRING) ) {
                            alt73=1;
                        }


                        switch (alt73) {
                    	case 1 :
                    	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1274:3: s= STRING
                    	    {
                    	    s=(Token)input.LT(1);
                    	    match(input,STRING,FOLLOW_STRING_in_atom4696); 
                    	     
                    	    				if( exp != null && exp instanceof StringLiteral  )
                    	    					exp = new StringLiteral( exp.sourceStart(), toDLTK(s),  ((StringLiteral)exp).getValue() + s.getText() ); 
                    	    				else
                    	    					exp = new StringLiteral( toDLTK( s ) ); 
                    	    			

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt73 >= 1 ) break loop73;
                                EarlyExitException eee =
                                    new EarlyExitException(73, input);
                                throw eee;
                        }
                        cnt73++;
                    } while (true);


                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return exp;
    }
    // $ANTLR end atom


    // $ANTLR start listmaker
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1284:1: listmaker returns [ PythonListExpression exp = new PythonListExpression() ] : firstExpr= test ( ( list_for[ listExpr ] ) | ( ( options {greedy=true; } : COMMA expr0= test )* ( COMMA )? ) ) ;
    public final PythonListExpression listmaker() throws RecognitionException {
        PythonListExpression exp =  new PythonListExpression();

        Expression firstExpr = null;

        Expression expr0 = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1284:76: (firstExpr= test ( ( list_for[ listExpr ] ) | ( ( options {greedy=true; } : COMMA expr0= test )* ( COMMA )? ) ) )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1285:2: firstExpr= test ( ( list_for[ listExpr ] ) | ( ( options {greedy=true; } : COMMA expr0= test )* ( COMMA )? ) )
            {
            pushFollow(FOLLOW_test_in_listmaker4726);
            firstExpr=test();
            _fsp--;

            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1286:2: ( ( list_for[ listExpr ] ) | ( ( options {greedy=true; } : COMMA expr0= test )* ( COMMA )? ) )
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==88) ) {
                alt77=1;
            }
            else if ( (LA77_0==COMMA||LA77_0==RBRACK) ) {
                alt77=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1286:2: ( ( list_for[ listExpr ] ) | ( ( options {greedy=true; } : COMMA expr0= test )* ( COMMA )? ) )", 77, 0, input);

                throw nvae;
            }
            switch (alt77) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1287:3: ( list_for[ listExpr ] )
                    {
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1287:3: ( list_for[ listExpr ] )
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1288:4: list_for[ listExpr ]
                    {

                    				PythonListForExpression listExpr = new PythonListForExpression( firstExpr );
                    			
                    pushFollow(FOLLOW_list_for_in_listmaker4743);
                    list_for( listExpr );
                    _fsp--;


                    				exp.addExpression( listExpr );
                    			

                    }


                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1297:3: ( ( options {greedy=true; } : COMMA expr0= test )* ( COMMA )? )
                    {
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1297:3: ( ( options {greedy=true; } : COMMA expr0= test )* ( COMMA )? )
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1298:4: ( options {greedy=true; } : COMMA expr0= test )* ( COMMA )?
                    {

                    				exp.addExpression( firstExpr );
                    			
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1301:4: ( options {greedy=true; } : COMMA expr0= test )*
                    loop75:
                    do {
                        int alt75=2;
                        int LA75_0 = input.LA(1);

                        if ( (LA75_0==COMMA) ) {
                            int LA75_1 = input.LA(2);

                            if ( (LA75_1==LPAREN||LA75_1==NAME||(LA75_1>=PLUS && LA75_1<=MINUS)||(LA75_1>=TILDE && LA75_1<=LBRACK)||LA75_1==LCURLY||(LA75_1>=BACKQUOTE && LA75_1<=STRING)||LA75_1==94||LA75_1==96) ) {
                                alt75=1;
                            }


                        }


                        switch (alt75) {
                    	case 1 :
                    	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1303:5: COMMA expr0= test
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_listmaker4791); 
                    	    pushFollow(FOLLOW_test_in_listmaker4801);
                    	    expr0=test();
                    	    _fsp--;


                    	    					exp.addExpression( expr0 );
                    	    				

                    	    }
                    	    break;

                    	default :
                    	    break loop75;
                        }
                    } while (true);

                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1309:4: ( COMMA )?
                    int alt76=2;
                    int LA76_0 = input.LA(1);

                    if ( (LA76_0==COMMA) ) {
                        alt76=1;
                    }
                    switch (alt76) {
                        case 1 :
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1309:5: COMMA
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_listmaker4820); 

                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return exp;
    }
    // $ANTLR end listmaker


    // $ANTLR start list_for
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1315:1: list_for[PythonListForExpression list ] : (forToken= 'for' expr1= exprlist 'in' expr2= testlist (expr0= list_if )* )+ ;
    public final void list_for(PythonListForExpression list) throws RecognitionException {
        Token forToken=null;
        PythonTestListExpression expr1 = null;

        Expression expr2 = null;

        Expression expr0 = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1315:41: ( (forToken= 'for' expr1= exprlist 'in' expr2= testlist (expr0= list_if )* )+ )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1316:2: (forToken= 'for' expr1= exprlist 'in' expr2= testlist (expr0= list_if )* )+
            {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1316:2: (forToken= 'for' expr1= exprlist 'in' expr2= testlist (expr0= list_if )* )+
            int cnt79=0;
            loop79:
            do {
                int alt79=2;
                int LA79_0 = input.LA(1);

                if ( (LA79_0==88) ) {
                    alt79=1;
                }


                switch (alt79) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1317:3: forToken= 'for' expr1= exprlist 'in' expr2= testlist (expr0= list_if )*
            	    {
            	    forToken=(Token)input.LT(1);
            	    match(input,88,FOLLOW_88_in_list_for4851); 
            	    pushFollow(FOLLOW_exprlist_in_list_for4859);
            	    expr1=exprlist();
            	    _fsp--;

            	    match(input,82,FOLLOW_82_in_list_for4863); 
            	    pushFollow(FOLLOW_testlist_in_list_for4872);
            	    expr2=testlist();
            	    _fsp--;


            	    			PythonForListExpression forListExpression = new PythonForListExpression( expr1, expr2 );
            	    			forListExpression.setStart(toDLTK(forToken).getColumn());
            	    			list.addExpression( forListExpression );
            	    		

            	    			PythonListExpression ifList = null;
            	    		
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1329:3: (expr0= list_if )*
            	    loop78:
            	    do {
            	        int alt78=2;
            	        int LA78_0 = input.LA(1);

            	        if ( (LA78_0==84) ) {
            	            alt78=1;
            	        }


            	        switch (alt78) {
            	    	case 1 :
            	    	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1330:4: expr0= list_if
            	    	    {
            	    	    pushFollow(FOLLOW_list_if_in_list_for4895);
            	    	    expr0=list_if();
            	    	    _fsp--;


            	    	    				if( ifList == null )
            	    	    					ifList = new PythonListExpression();
            	    	    				ifList.addExpression( expr0 );
            	    	    				ifList.setStart(expr0.sourceStart());
            	    	    				ifList.setEnd(expr0.sourceEnd());
            	    	    			

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop78;
            	        }
            	    } while (true);


            	    				if( ifList != null )
            	    					forListExpression.setIfListExpression( ifList );				
            	    			

            	    }
            	    break;

            	default :
            	    if ( cnt79 >= 1 ) break loop79;
                        EarlyExitException eee =
                            new EarlyExitException(79, input);
                        throw eee;
                }
                cnt79++;
            } while (true);


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return ;
    }
    // $ANTLR end list_for


    // $ANTLR start list_if
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1346:1: list_if returns [ Expression expr = null ] : 'if' expr0= test ;
    public final Expression list_if() throws RecognitionException {
        Expression expr =  null;

        Expression expr0 = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1346:42: ( 'if' expr0= test )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1347:2: 'if' expr0= test
            {
            match(input,84,FOLLOW_84_in_list_if4928); 
            pushFollow(FOLLOW_test_in_list_if4935);
            expr0=test();
            _fsp--;


            		expr = expr0;
            	

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return expr;
    }
    // $ANTLR end list_if


    // $ANTLR start lambdef
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1352:1: lambdef returns [ Expression e = null ] : lambda= 'lambda' ( varargslist[ params ] )? COLON statement= test ;
    public final Expression lambdef() throws RecognitionException {
        Expression e =  null;

        Token lambda=null;
        Expression statement = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1352:40: (lambda= 'lambda' ( varargslist[ params ] )? COLON statement= test )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1353:2: lambda= 'lambda' ( varargslist[ params ] )? COLON statement= test
            {
            lambda=(Token)input.LT(1);
            match(input,96,FOLLOW_96_in_lambdef4956); 

            		//ArrayList buf = this.fParameters;
            		//this.fParameters = new ArrayList();
            		List params = new ArrayList ();
            		//Expression statement;
            	
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1360:2: ( varargslist[ params ] )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==LPAREN||LA80_0==NAME||(LA80_0>=STAR && LA80_0<=DOUBLESTAR)) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1360:4: varargslist[ params ]
                    {
                    pushFollow(FOLLOW_varargslist_in_lambdef4964);
                    varargslist( params );
                    _fsp--;


                    }
                    break;

            }

            match(input,COLON,FOLLOW_COLON_in_lambdef4971); 
            pushFollow(FOLLOW_test_in_lambdef4979);
            statement=test();
            _fsp--;

            		
            		if( statement == null ) {
            			statement = new EmptyExpression( );
            		}
            		e = new PythonLambdaExpression( toDLTK( lambda ), params, statement );
            	

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return e;
    }
    // $ANTLR end lambdef


    // $ANTLR start subscriptlist
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1371:1: subscriptlist returns [ Expression expression = null ] : expression0= subscript ( options {greedy=true; } : COMMA k= subscript )* ( COMMA )? ;
    public final Expression subscriptlist() throws RecognitionException {
        Expression expression =  null;

        PythonSubscriptExpression expression0 = null;

        PythonSubscriptExpression k = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1371:56: (expression0= subscript ( options {greedy=true; } : COMMA k= subscript )* ( COMMA )? )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1372:2: expression0= subscript ( options {greedy=true; } : COMMA k= subscript )* ( COMMA )?
            {
            pushFollow(FOLLOW_subscript_in_subscriptlist5003);
            expression0=subscript();
            _fsp--;

            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1373:2: ( options {greedy=true; } : COMMA k= subscript )*
            loop81:
            do {
                int alt81=2;
                int LA81_0 = input.LA(1);

                if ( (LA81_0==COMMA) ) {
                    int LA81_1 = input.LA(2);

                    if ( (LA81_1==LPAREN||(LA81_1>=NAME && LA81_1<=COLON)||LA81_1==DOT||(LA81_1>=PLUS && LA81_1<=MINUS)||(LA81_1>=TILDE && LA81_1<=LBRACK)||LA81_1==LCURLY||(LA81_1>=BACKQUOTE && LA81_1<=STRING)||LA81_1==94||LA81_1==96) ) {
                        alt81=1;
                    }


                }


                switch (alt81) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1374:26: COMMA k= subscript
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_subscriptlist5019); 
            	    pushFollow(FOLLOW_subscript_in_subscriptlist5028);
            	    k=subscript();
            	    _fsp--;


            	    				expression = new BinaryExpression( expression0, Expression.E_COMMA, k );
            	    			

            	    }
            	    break;

            	default :
            	    break loop81;
                }
            } while (true);

            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1380:2: ( COMMA )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==COMMA) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1380:3: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_subscriptlist5043); 

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return expression;
    }
    // $ANTLR end subscriptlist


    // $ANTLR start subscript
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1383:1: subscript returns [ PythonSubscriptExpression expression = null ] : (w= DOT DOT DOT | (tu= test ( COLON (tu1= test )? (tu= sliceop )? )? ) | ( COLON (tu1= test )? (tu= sliceop )? ) );
    public final PythonSubscriptExpression subscript() throws RecognitionException {
        PythonSubscriptExpression expression =  null;

        Token w=null;
        Expression tu = null;

        Expression tu1 = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1383:66: (w= DOT DOT DOT | (tu= test ( COLON (tu1= test )? (tu= sliceop )? )? ) | ( COLON (tu1= test )? (tu= sliceop )? ) )
            int alt88=3;
            switch ( input.LA(1) ) {
            case DOT:
                {
                alt88=1;
                }
                break;
            case LPAREN:
            case NAME:
            case PLUS:
            case MINUS:
            case TILDE:
            case LBRACK:
            case LCURLY:
            case BACKQUOTE:
            case INT:
            case LONGINT:
            case FLOAT:
            case COMPLEX:
            case STRING:
            case 94:
            case 96:
                {
                alt88=2;
                }
                break;
            case COLON:
                {
                alt88=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1383:1: subscript returns [ PythonSubscriptExpression expression = null ] : (w= DOT DOT DOT | (tu= test ( COLON (tu1= test )? (tu= sliceop )? )? ) | ( COLON (tu1= test )? (tu= sliceop )? ) );", 88, 0, input);

                throw nvae;
            }

            switch (alt88) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1384:2: w= DOT DOT DOT
                    {
                    w=(Token)input.LT(1);
                    match(input,DOT,FOLLOW_DOT_in_subscript5067); 
                    match(input,DOT,FOLLOW_DOT_in_subscript5069); 
                    match(input,DOT,FOLLOW_DOT_in_subscript5071); 

                    			expression = new PythonSubscriptExpression( toDLTK( w ) );
                    		

                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1390:2: (tu= test ( COLON (tu1= test )? (tu= sliceop )? )? )
                    {
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1390:2: (tu= test ( COLON (tu1= test )? (tu= sliceop )? )? )
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1391:3: tu= test ( COLON (tu1= test )? (tu= sliceop )? )?
                    {

                    			expression = new PythonSubscriptExpression( );	
                    		
                    pushFollow(FOLLOW_test_in_subscript5096);
                    tu=test();
                    _fsp--;


                    				expression.setTest( tu );
                    			
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1398:3: ( COLON (tu1= test )? (tu= sliceop )? )?
                    int alt85=2;
                    int LA85_0 = input.LA(1);

                    if ( (LA85_0==COLON) ) {
                        alt85=1;
                    }
                    switch (alt85) {
                        case 1 :
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1399:4: COLON (tu1= test )? (tu= sliceop )?
                            {
                            match(input,COLON,FOLLOW_COLON_in_subscript5111); 
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1400:4: (tu1= test )?
                            int alt83=2;
                            int LA83_0 = input.LA(1);

                            if ( (LA83_0==LPAREN||LA83_0==NAME||(LA83_0>=PLUS && LA83_0<=MINUS)||(LA83_0>=TILDE && LA83_0<=LBRACK)||LA83_0==LCURLY||(LA83_0>=BACKQUOTE && LA83_0<=STRING)||LA83_0==94||LA83_0==96) ) {
                                alt83=1;
                            }
                            switch (alt83) {
                                case 1 :
                                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1401:5: tu1= test
                                    {
                                    pushFollow(FOLLOW_test_in_subscript5127);
                                    tu1=test();
                                    _fsp--;


                                    						expression.setCondition( tu1 );
                                    					

                                    }
                                    break;

                            }

                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1406:4: (tu= sliceop )?
                            int alt84=2;
                            int LA84_0 = input.LA(1);

                            if ( (LA84_0==COLON) ) {
                                alt84=1;
                            }
                            switch (alt84) {
                                case 1 :
                                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1407:5: tu= sliceop
                                    {
                                    pushFollow(FOLLOW_sliceop_in_subscript5157);
                                    tu=sliceop();
                                    _fsp--;


                                    						expression.setSlice( tu );
                                    					

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 3 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1415:2: ( COLON (tu1= test )? (tu= sliceop )? )
                    {
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1415:2: ( COLON (tu1= test )? (tu= sliceop )? )
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1416:3: COLON (tu1= test )? (tu= sliceop )?
                    {

                    			expression = new PythonSubscriptExpression( );
                    		
                    match(input,COLON,FOLLOW_COLON_in_subscript5199); 
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1420:8: (tu1= test )?
                    int alt86=2;
                    int LA86_0 = input.LA(1);

                    if ( (LA86_0==LPAREN||LA86_0==NAME||(LA86_0>=PLUS && LA86_0<=MINUS)||(LA86_0>=TILDE && LA86_0<=LBRACK)||LA86_0==LCURLY||(LA86_0>=BACKQUOTE && LA86_0<=STRING)||LA86_0==94||LA86_0==96) ) {
                        alt86=1;
                    }
                    switch (alt86) {
                        case 1 :
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1421:9: tu1= test
                            {
                            pushFollow(FOLLOW_test_in_subscript5223);
                            tu1=test();
                            _fsp--;


                                						expression.setCondition( tu1 );
                                					

                            }
                            break;

                    }

                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1426:8: (tu= sliceop )?
                    int alt87=2;
                    int LA87_0 = input.LA(1);

                    if ( (LA87_0==COLON) ) {
                        alt87=1;
                    }
                    switch (alt87) {
                        case 1 :
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1427:9: tu= sliceop
                            {
                            pushFollow(FOLLOW_sliceop_in_subscript5269);
                            tu=sliceop();
                            _fsp--;


                                						expression.setSlice( tu );
                                					

                            }
                            break;

                    }


                    }


                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return expression;
    }
    // $ANTLR end subscript


    // $ANTLR start sliceop
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1437:1: sliceop returns [ Expression e = null ] : COLON (e0= test )? ;
    public final Expression sliceop() throws RecognitionException {
        Expression e =  null;

        Expression e0 = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1437:40: ( COLON (e0= test )? )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1438:2: COLON (e0= test )?
            {
            match(input,COLON,FOLLOW_COLON_in_sliceop5329); 
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1439:2: (e0= test )?
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==LPAREN||LA89_0==NAME||(LA89_0>=PLUS && LA89_0<=MINUS)||(LA89_0>=TILDE && LA89_0<=LBRACK)||LA89_0==LCURLY||(LA89_0>=BACKQUOTE && LA89_0<=STRING)||LA89_0==94||LA89_0==96) ) {
                alt89=1;
            }
            switch (alt89) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1439:4: e0= test
                    {
                    pushFollow(FOLLOW_test_in_sliceop5338);
                    e0=test();
                    _fsp--;

                     e = e0; 

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return e;
    }
    // $ANTLR end sliceop


    // $ANTLR start exprlist
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1443:1: exprlist returns [ PythonTestListExpression p = new PythonTestListExpression( ); ] : e= expr ( options {k=2; greedy=true; } : COMMA e= expr )* ( COMMA )? ;
    public final PythonTestListExpression exprlist() throws RecognitionException {
        PythonTestListExpression p =  new PythonTestListExpression( );;

        Expression e = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1443:84: (e= expr ( options {k=2; greedy=true; } : COMMA e= expr )* ( COMMA )? )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1444:2: e= expr ( options {k=2; greedy=true; } : COMMA e= expr )* ( COMMA )?
            {
            pushFollow(FOLLOW_expr_in_exprlist5365);
            e=expr();
            _fsp--;


            		p.addExpression( e );
            		p.setStart(e.sourceStart());
            		p.setEnd(e.sourceEnd());
            	
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1450:2: ( options {k=2; greedy=true; } : COMMA e= expr )*
            loop90:
            do {
                int alt90=2;
                int LA90_0 = input.LA(1);

                if ( (LA90_0==COMMA) ) {
                    int LA90_1 = input.LA(2);

                    if ( (LA90_1==LPAREN||LA90_1==NAME||(LA90_1>=PLUS && LA90_1<=MINUS)||(LA90_1>=TILDE && LA90_1<=LBRACK)||LA90_1==LCURLY||(LA90_1>=BACKQUOTE && LA90_1<=STRING)) ) {
                        alt90=1;
                    }


                }


                switch (alt90) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1451:3: COMMA e= expr
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_exprlist5387); 
            	    pushFollow(FOLLOW_expr_in_exprlist5396);
            	    e=expr();
            	    _fsp--;


            	    				p.addExpression( e );
            	    				if( e.sourceEnd() > p.sourceEnd() ) {
            	    					p.setEnd( e.sourceEnd() );
            	    				}
            	    			

            	    }
            	    break;

            	default :
            	    break loop90;
                }
            } while (true);

            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1460:2: ( COMMA )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==COMMA) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1460:3: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_exprlist5410); 

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return p;
    }
    // $ANTLR end exprlist


    // $ANTLR start testlist
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1464:1: testlist returns [ Expression p = new EmptyExpression() ] : e0= test ( options {k=2; greedy=true; } : COMMA e0= test )* ( options {greedy=true; } : COMMA )? ;
    public final Expression testlist() throws RecognitionException {
        Expression p =  new EmptyExpression();

        Expression e0 = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1464:58: (e0= test ( options {k=2; greedy=true; } : COMMA e0= test )* ( options {greedy=true; } : COMMA )? )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1465:2: e0= test ( options {k=2; greedy=true; } : COMMA e0= test )* ( options {greedy=true; } : COMMA )?
            {

            		PythonTestListExpression listExpression = new PythonTestListExpression();
            		int end = -1;
            	
            pushFollow(FOLLOW_test_in_testlist5437);
            e0=test();
            _fsp--;

            		
            		p = e0;
            		listExpression.addExpression( e0 );
            		if( p != null) {
            			listExpression.setStart(p.sourceStart());
            			end = p.sourceEnd();
            			listExpression.setEnd(end);
            		}
            	
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1479:6: ( options {k=2; greedy=true; } : COMMA e0= test )*
            loop92:
            do {
                int alt92=2;
                int LA92_0 = input.LA(1);

                if ( (LA92_0==COMMA) ) {
                    int LA92_1 = input.LA(2);

                    if ( (LA92_1==LPAREN||LA92_1==NAME||(LA92_1>=PLUS && LA92_1<=MINUS)||(LA92_1>=TILDE && LA92_1<=LBRACK)||LA92_1==LCURLY||(LA92_1>=INT && LA92_1<=STRING)||LA92_1==94||LA92_1==96) ) {
                        alt92=1;
                    }
                    else if ( (LA92_1==BACKQUOTE) ) {
                        alt92=1;
                    }


                }


                switch (alt92) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1480:7: COMMA e0= test
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_testlist5466); 
            	    pushFollow(FOLLOW_test_in_testlist5478);
            	    e0=test();
            	    _fsp--;


            	        				if( e0 != null && e0.sourceEnd() > end ) {
            	        					end = e0.sourceEnd();
            	        				}
            	        				listExpression.addExpression( e0 );
            	        				p = listExpression;
            	        			

            	    }
            	    break;

            	default :
            	    break loop92;
                }
            } while (true);

            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1490:9: ( options {greedy=true; } : COMMA )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==COMMA) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1490:33: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_testlist5513); 

                    }
                    break;

            }


                    	if( end != -1 && p != null ) {
                    		p.setEnd(end);
                    	}
                    

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return p;
    }
    // $ANTLR end testlist


    // $ANTLR start tuplelist
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1497:1: tuplelist returns [ Expression p = null ] : e0= test ( options {greedy=true; } : COMMA e0= test )* ( options {greedy=true; } : COMMA )? ;
    public final Expression tuplelist() throws RecognitionException {
        Expression p =  null;

        Expression e0 = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1497:42: (e0= test ( options {greedy=true; } : COMMA e0= test )* ( options {greedy=true; } : COMMA )? )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1498:2: e0= test ( options {greedy=true; } : COMMA e0= test )* ( options {greedy=true; } : COMMA )?
            {
            pushFollow(FOLLOW_test_in_tuplelist5547);
            e0=test();
            _fsp--;


            		p = e0;
            		if( p == null ) {
            			p = new EmptyExpression();
            		}
            		//p.addExpression( e );
            	
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1506:6: ( options {greedy=true; } : COMMA e0= test )*
            loop94:
            do {
                int alt94=2;
                int LA94_0 = input.LA(1);

                if ( (LA94_0==COMMA) ) {
                    int LA94_1 = input.LA(2);

                    if ( (LA94_1==LPAREN||LA94_1==NAME||(LA94_1>=PLUS && LA94_1<=MINUS)||(LA94_1>=TILDE && LA94_1<=LBRACK)||LA94_1==LCURLY||(LA94_1>=BACKQUOTE && LA94_1<=STRING)||LA94_1==94||LA94_1==96) ) {
                        alt94=1;
                    }


                }


                switch (alt94) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1507:7: COMMA e0= test
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_tuplelist5572); 
            	    pushFollow(FOLLOW_test_in_tuplelist5584);
            	    e0=test();
            	    _fsp--;


            	        				if( !( p instanceof PythonTupleExpression ) ) {
            	    	    				PythonTupleExpression tuple = new PythonTupleExpression();
            	        					tuple.addExpression( p );
            	        					p = tuple;
            	        				}
            	        				PythonTupleExpression tup = (PythonTupleExpression)p;
            	        				tup.addExpression( e0 );
            	        			

            	    }
            	    break;

            	default :
            	    break loop94;
                }
            } while (true);

            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1519:9: ( options {greedy=true; } : COMMA )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==COMMA) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1519:33: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_tuplelist5620); 

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return p;
    }
    // $ANTLR end tuplelist


    // $ANTLR start with_stmt
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1521:1: with_stmt returns [Statement st = null] : w_token= 'with' exp_what= test ( 'as' exp_as= testlist )? COLON block= suite ;
    public final Statement with_stmt() throws RecognitionException {
        Statement st =  null;

        Token w_token=null;
        Expression exp_what = null;

        Expression exp_as = null;

        Block block = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1521:40: (w_token= 'with' exp_what= test ( 'as' exp_as= testlist )? COLON block= suite )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1522:2: w_token= 'with' exp_what= test ( 'as' exp_as= testlist )? COLON block= suite
            {
            w_token=(Token)input.LT(1);
            match(input,97,FOLLOW_97_in_with_stmt5652); 
            pushFollow(FOLLOW_test_in_with_stmt5660);
            exp_what=test();
            _fsp--;

            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1524:2: ( 'as' exp_as= testlist )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==79) ) {
                alt96=1;
            }
            switch (alt96) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1525:4: 'as' exp_as= testlist
                    {
                    match(input,79,FOLLOW_79_in_with_stmt5668); 
                    pushFollow(FOLLOW_testlist_in_with_stmt5674);
                    exp_as=testlist();
                    _fsp--;


                    }
                    break;

            }

            match(input,COLON,FOLLOW_COLON_in_with_stmt5680); 
            pushFollow(FOLLOW_suite_in_with_stmt5686);
            block=suite();
            _fsp--;


            		DLTKToken token = toDLTK(w_token);
            		st = new PythonWithStatement(token, exp_what, exp_as, block, token.getColumn(), block.sourceEnd());
            	

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return st;
    }
    // $ANTLR end with_stmt


    // $ANTLR start dictmaker
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1533:1: dictmaker returns [ PythonDictExpression d = new PythonDictExpression() ] : t1= test COLON t2= test ( options {k=2; greedy=true; } : COMMA t3= test COLON t4= test )* ( COMMA )? ;
    public final PythonDictExpression dictmaker() throws RecognitionException {
        PythonDictExpression d =  new PythonDictExpression();

        Expression t1 = null;

        Expression t2 = null;

        Expression t3 = null;

        Expression t4 = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1533:75: (t1= test COLON t2= test ( options {k=2; greedy=true; } : COMMA t3= test COLON t4= test )* ( COMMA )? )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1534:2: t1= test COLON t2= test ( options {k=2; greedy=true; } : COMMA t3= test COLON t4= test )* ( COMMA )?
            {
            pushFollow(FOLLOW_test_in_dictmaker5708);
            t1=test();
            _fsp--;

            match(input,COLON,FOLLOW_COLON_in_dictmaker5711); 
            pushFollow(FOLLOW_test_in_dictmaker5718);
            t2=test();
            _fsp--;


            			d.putExpression( t1, t2 );
            		
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1540:9: ( options {k=2; greedy=true; } : COMMA t3= test COLON t4= test )*
            loop97:
            do {
                int alt97=2;
                int LA97_0 = input.LA(1);

                if ( (LA97_0==COMMA) ) {
                    int LA97_1 = input.LA(2);

                    if ( (LA97_1==LPAREN||LA97_1==NAME||(LA97_1>=PLUS && LA97_1<=MINUS)||(LA97_1>=TILDE && LA97_1<=LBRACK)||LA97_1==LCURLY||(LA97_1>=BACKQUOTE && LA97_1<=STRING)||LA97_1==94||LA97_1==96) ) {
                        alt97=1;
                    }


                }


                switch (alt97) {
            	case 1 :
            	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1541:37: COMMA t3= test COLON t4= test
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_dictmaker5754); 
            	    pushFollow(FOLLOW_test_in_dictmaker5769);
            	    t3=test();
            	    _fsp--;

            	    match(input,COLON,FOLLOW_COLON_in_dictmaker5780); 
            	    pushFollow(FOLLOW_test_in_dictmaker5795);
            	    t4=test();
            	    _fsp--;


            	            			d.putExpression( t3, t4 );
            	            		

            	    }
            	    break;

            	default :
            	    break loop97;
                }
            } while (true);

            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1549:9: ( COMMA )?
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( (LA98_0==COMMA) ) {
                alt98=1;
            }
            switch (alt98) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1549:10: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_dictmaker5830); 

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return d;
    }
    // $ANTLR end dictmaker


    // $ANTLR start classdef
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1553:1: classdef returns [ PythonClassDeclaration classDeclaration = null ] : c= 'class' tu= NAME (r= LPAREN (te= testlist )? m= RPAREN )? co= COLON sa= suite ;
    public final PythonClassDeclaration classdef() throws RecognitionException {
        PythonClassDeclaration classDeclaration =  null;

        Token c=null;
        Token tu=null;
        Token r=null;
        Token m=null;
        Token co=null;
        Expression te = null;

        Block sa = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1553:68: (c= 'class' tu= NAME (r= LPAREN (te= testlist )? m= RPAREN )? co= COLON sa= suite )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1554:2: c= 'class' tu= NAME (r= LPAREN (te= testlist )? m= RPAREN )? co= COLON sa= suite
            {
            c=(Token)input.LT(1);
            match(input,98,FOLLOW_98_in_classdef5861); 
            tu=(Token)input.LT(1);
            match(input,NAME,FOLLOW_NAME_in_classdef5868); 

            			classDeclaration = new PythonClassDeclaration( toDLTK( tu ), toDLTK(c) );
            		
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1559:2: (r= LPAREN (te= testlist )? m= RPAREN )?
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( (LA100_0==LPAREN) ) {
                alt100=1;
            }
            switch (alt100) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1560:3: r= LPAREN (te= testlist )? m= RPAREN
                    {
                    r=(Token)input.LT(1);
                    match(input,LPAREN,FOLLOW_LPAREN_in_classdef5887); 
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1561:4: (te= testlist )?
                    int alt99=2;
                    int LA99_0 = input.LA(1);

                    if ( (LA99_0==LPAREN||LA99_0==NAME||(LA99_0>=PLUS && LA99_0<=MINUS)||(LA99_0>=TILDE && LA99_0<=LBRACK)||LA99_0==LCURLY||(LA99_0>=BACKQUOTE && LA99_0<=STRING)||LA99_0==94||LA99_0==96) ) {
                        alt99=1;
                    }
                    switch (alt99) {
                        case 1 :
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1561:5: te= testlist
                            {
                            pushFollow(FOLLOW_testlist_in_classdef5897);
                            te=testlist();
                            _fsp--;


                            }
                            break;

                    }

                    m=(Token)input.LT(1);
                    match(input,RPAREN,FOLLOW_RPAREN_in_classdef5908); 

                    				if (null != te)
                    				{
                    					if( te instanceof ExpressionList ) {
                    						classDeclaration.setParents( toDLTK( r ), (ExpressionList)te, toDLTK( m ) );
                    					}
                    					else {
                    						ExpressionList exprList = new ExpressionList();
                    						exprList.setStart(te.sourceStart());
                    						exprList.setEnd(te.sourceEnd());
                    						exprList.addExpression( te );
                    						classDeclaration.setParents( toDLTK( r ), exprList, toDLTK( m ) );
                    					}
                    				}
                    					
                    			

                    }
                    break;

            }

            co=(Token)input.LT(1);
            match(input,COLON,FOLLOW_COLON_in_classdef5924); 
            pushFollow(FOLLOW_suite_in_classdef5931);
            sa=suite();
            _fsp--;


                  			classDeclaration.setBody( toDLTK(co), sa, sa.sourceEnd() );
            		

            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return classDeclaration;
    }
    // $ANTLR end classdef


    // $ANTLR start arglist
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1587:1: arglist returns [ ExpressionList expressions = new ExpressionList() ] : (k= argument ( options {greedy=true; } : COMMA k= argument )* ( COMMA ( STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )? )? | STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test ) ;
    public final ExpressionList arglist() throws RecognitionException {
        ExpressionList expressions =  new ExpressionList();

        Expression k = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1587:70: ( (k= argument ( options {greedy=true; } : COMMA k= argument )* ( COMMA ( STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )? )? | STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test ) )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1588:2: (k= argument ( options {greedy=true; } : COMMA k= argument )* ( COMMA ( STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )? )? | STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )
            {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1588:2: (k= argument ( options {greedy=true; } : COMMA k= argument )* ( COMMA ( STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )? )? | STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )
            int alt106=3;
            switch ( input.LA(1) ) {
            case LPAREN:
            case NAME:
            case PLUS:
            case MINUS:
            case TILDE:
            case LBRACK:
            case LCURLY:
            case BACKQUOTE:
            case INT:
            case LONGINT:
            case FLOAT:
            case COMPLEX:
            case STRING:
            case 94:
            case 96:
                {
                alt106=1;
                }
                break;
            case STAR:
                {
                alt106=2;
                }
                break;
            case DOUBLESTAR:
                {
                alt106=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1588:2: (k= argument ( options {greedy=true; } : COMMA k= argument )* ( COMMA ( STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )? )? | STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )", 106, 0, input);

                throw nvae;
            }

            switch (alt106) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1589:3: k= argument ( options {greedy=true; } : COMMA k= argument )* ( COMMA ( STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )? )?
                    {
                    pushFollow(FOLLOW_argument_in_arglist5963);
                    k=argument();
                    _fsp--;


                    				expressions.addExpression( k );
                    			
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1593:11: ( options {greedy=true; } : COMMA k= argument )*
                    loop101:
                    do {
                        int alt101=2;
                        int LA101_0 = input.LA(1);

                        if ( (LA101_0==COMMA) ) {
                            int LA101_1 = input.LA(2);

                            if ( (LA101_1==LPAREN||LA101_1==NAME||(LA101_1>=PLUS && LA101_1<=MINUS)||(LA101_1>=TILDE && LA101_1<=LBRACK)||LA101_1==LCURLY||(LA101_1>=BACKQUOTE && LA101_1<=STRING)||LA101_1==94||LA101_1==96) ) {
                                alt101=1;
                            }


                        }


                        switch (alt101) {
                    	case 1 :
                    	    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1594:35: COMMA k= argument
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_arglist6003); 
                    	    pushFollow(FOLLOW_argument_in_arglist6021);
                    	    k=argument();
                    	    _fsp--;


                    	             				expressions.addExpression( k );
                    	             			

                    	    }
                    	    break;

                    	default :
                    	    break loop101;
                        }
                    } while (true);

                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1600:3: ( COMMA ( STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )? )?
                    int alt104=2;
                    int LA104_0 = input.LA(1);

                    if ( (LA104_0==COMMA) ) {
                        alt104=1;
                    }
                    switch (alt104) {
                        case 1 :
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1601:4: COMMA ( STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )?
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_arglist6059); 
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1602:4: ( STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )?
                            int alt103=3;
                            int LA103_0 = input.LA(1);

                            if ( (LA103_0==STAR) ) {
                                alt103=1;
                            }
                            else if ( (LA103_0==DOUBLESTAR) ) {
                                alt103=2;
                            }
                            switch (alt103) {
                                case 1 :
                                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1603:5: STAR k= test ( COMMA DOUBLESTAR k= test )?
                                    {
                                    match(input,STAR,FOLLOW_STAR_in_arglist6071); 
                                    pushFollow(FOLLOW_test_in_arglist6082);
                                    k=test();
                                    _fsp--;


                                                					expressions.addExpression( k );
                                                				
                                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1608:16: ( COMMA DOUBLESTAR k= test )?
                                    int alt102=2;
                                    int LA102_0 = input.LA(1);

                                    if ( (LA102_0==COMMA) ) {
                                        alt102=1;
                                    }
                                    switch (alt102) {
                                        case 1 :
                                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1609:17: COMMA DOUBLESTAR k= test
                                            {
                                            match(input,COMMA,FOLLOW_COMMA_in_arglist6138); 
                                            match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_arglist6157); 
                                            pushFollow(FOLLOW_test_in_arglist6180);
                                            k=test();
                                            _fsp--;


                                                        						expressions.addExpression( k );
                                                        					

                                            }
                                            break;

                                    }


                                    }
                                    break;
                                case 2 :
                                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1617:14: DOUBLESTAR k= test
                                    {
                                    match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_arglist6249); 
                                    pushFollow(FOLLOW_test_in_arglist6269);
                                    k=test();
                                    _fsp--;


                                              					expressions.addExpression( k );
                                              				

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1625:3: STAR k= test ( COMMA DOUBLESTAR k= test )?
                    {
                    match(input,STAR,FOLLOW_STAR_in_arglist6308); 
                    pushFollow(FOLLOW_test_in_arglist6317);
                    k=test();
                    _fsp--;


                    				expressions.addExpression( k );
                    			
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1630:3: ( COMMA DOUBLESTAR k= test )?
                    int alt105=2;
                    int LA105_0 = input.LA(1);

                    if ( (LA105_0==COMMA) ) {
                        alt105=1;
                    }
                    switch (alt105) {
                        case 1 :
                            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1631:4: COMMA DOUBLESTAR k= test
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_arglist6333); 
                            match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_arglist6339); 
                            pushFollow(FOLLOW_test_in_arglist6349);
                            k=test();
                            _fsp--;


                            					expressions.addExpression( k );
                            				

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1639:3: DOUBLESTAR k= test
                    {
                    match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_arglist6369); 
                    pushFollow(FOLLOW_test_in_arglist6378);
                    k=test();
                    _fsp--;


                    				expressions.addExpression( k );
                    			

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return expressions;
    }
    // $ANTLR end arglist


    // $ANTLR start argument
    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1647:1: argument returns [ Expression e = null ] : e0= test ( ASSIGN k= test )? ;
    public final Expression argument() throws RecognitionException {
        Expression e =  null;

        Expression e0 = null;

        Expression k = null;


        try {
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1647:41: (e0= test ( ASSIGN k= test )? )
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1648:2: e0= test ( ASSIGN k= test )?
            {
            pushFollow(FOLLOW_test_in_argument6416);
            e0=test();
            _fsp--;


            		e = e0;
            	
            // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1652:2: ( ASSIGN k= test )?
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==ASSIGN) ) {
                alt107=1;
            }
            switch (alt107) {
                case 1 :
                    // /home/leon/workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1653:3: ASSIGN k= test
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_argument6426); 
                    pushFollow(FOLLOW_test_in_argument6435);
                    k=test();
                    _fsp--;


                    				e = new Assignment( e, k );
                    			

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
        	if( reporter != null ) {
        		reporter.reportError(re);
        	}
        	recover(input,re);
        }
        catch (Throwable extre) {
        	//System.err.println(t);
        	if( reporter != null ) {
        		reporter.reportThrowable(extre);
        	}
        }
        finally {
        }
        return e;
    }
    // $ANTLR end argument


 

    public static final BitSet FOLLOW_NEWLINE_in_file_input101 = new BitSet(new long[]{0x03F58C00000005C0L,0x00000007439B7FF0L});
    public static final BitSet FOLLOW_stmt_in_file_input126 = new BitSet(new long[]{0x03F58C00000005C0L,0x00000007439B7FF0L});
    public static final BitSet FOLLOW_EOF_in_file_input157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECORATOR_S_in_decorator185 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_dot_name_in_decorator192 = new BitSet(new long[]{0x0000000000000140L});
    public static final BitSet FOLLOW_LPAREN_in_decorator204 = new BitSet(new long[]{0x03F58C0000006500L,0x0000000140000000L});
    public static final BitSet FOLLOW_arglist_in_decorator213 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_RPAREN_in_decorator221 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NEWLINE_in_decorator237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_decorator_in_decoraror_list259 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_decoraror_list_in_funcdef294 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_funcdef306 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_funcdef314 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_parameters_in_funcdef320 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_funcdef338 = new BitSet(new long[]{0x03F58C0000000540L,0x00000001400B7FE0L});
    public static final BitSet FOLLOW_suite_in_funcdef352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parameters374 = new BitSet(new long[]{0x0000000000006700L});
    public static final BitSet FOLLOW_varargslist_in_parameters379 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_RPAREN_in_parameters386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_defparameter_in_varargslist397 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_varargslist414 = new BitSet(new long[]{0x0000000000000500L});
    public static final BitSet FOLLOW_defparameter_in_varargslist419 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_varargslist435 = new BitSet(new long[]{0x0000000000006002L});
    public static final BitSet FOLLOW_STAR_in_varargslist452 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_varargslist458 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_varargslist493 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_varargslist495 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_varargslist501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_varargslist543 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_varargslist549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_varargslist587 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_varargslist593 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_varargslist602 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_varargslist607 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_varargslist613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_varargslist627 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_varargslist633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fpdef_in_defparameter652 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_ASSIGN_in_defparameter657 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_defparameter663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_fpdef695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_fpdef706 = new BitSet(new long[]{0x0000000000000500L});
    public static final BitSet FOLLOW_fplist_in_fpdef708 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_RPAREN_in_fpdef711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fpdef_in_fplist726 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_fplist744 = new BitSet(new long[]{0x0000000000000500L});
    public static final BitSet FOLLOW_fpdef_in_fplist746 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_fplist755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_small_stmt_in_simple_stmt773 = new BitSet(new long[]{0x0000000000010040L});
    public static final BitSet FOLLOW_SEMI_in_simple_stmt792 = new BitSet(new long[]{0x03F58C0000000500L,0x00000001400B7FE0L});
    public static final BitSet FOLLOW_small_stmt_in_simple_stmt794 = new BitSet(new long[]{0x0000000000010040L});
    public static final BitSet FOLLOW_SEMI_in_simple_stmt804 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NEWLINE_in_simple_stmt809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_stmt_in_stmt830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compound_stmt_in_stmt850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_stmt_in_small_stmt884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_print_stmt_in_small_stmt895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_del_stmt_in_small_stmt905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pass_stmt_in_small_stmt915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_flow_stmt_in_small_stmt925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import_stmt_in_small_stmt935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_global_stmt_in_small_stmt945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exec_stmt_in_small_stmt955 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assert_stmt_in_small_stmt965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_testlist_in_expr_stmt993 = new BitSet(new long[]{0x000000001FFE8002L});
    public static final BitSet FOLLOW_augassign_in_expr_stmt1007 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_testlist_in_expr_stmt1016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_expr_stmt1038 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_testlist_in_expr_stmt1047 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_PLUSEQUAL_in_augassign1080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUSEQUAL_in_augassign1089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAREQUAL_in_augassign1099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASHEQUAL_in_augassign1108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENTEQUAL_in_augassign1118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AMPEREQUAL_in_augassign1127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VBAREQUAL_in_augassign1136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIRCUMFLEXEQUAL_in_augassign1145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSHIFTEQUAL_in_augassign1154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RIGHTSHIFTEQUAL_in_augassign1163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESTAREQUAL_in_augassign1172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESLASHEQUAL_in_augassign1181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_print_stmt1215 = new BitSet(new long[]{0x03F58C0020000502L,0x0000000140000000L});
    public static final BitSet FOLLOW_testlist_in_print_stmt1226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RIGHTSHIFT_in_print_stmt1239 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_testlist_in_print_stmt1255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_del_stmt1286 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_exprlist_in_del_stmt1294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_pass_stmt1317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_break_stmt_in_flow_stmt1343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_continue_stmt_in_flow_stmt1354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_stmt_in_flow_stmt1364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_raise_stmt_in_flow_stmt1374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_yield_stmt_in_flow_stmt1384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_break_stmt1405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_continue_stmt1429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_return_stmt1454 = new BitSet(new long[]{0x03F58C0000000502L,0x0000000140000000L});
    public static final BitSet FOLLOW_testlist_in_return_stmt1465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_yield_stmt1493 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_testlist_in_yield_stmt1500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_raise_stmt1522 = new BitSet(new long[]{0x03F58C0000000502L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_raise_stmt1536 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_raise_stmt1547 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_raise_stmt1556 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_raise_stmt1569 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_raise_stmt1579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_import_stmt1696 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_module_imp_in_import_stmt1718 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_import_stmt1747 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_module_imp_in_import_stmt1761 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_78_in_import_stmt1797 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_dot_name_in_import_stmt1805 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_import_stmt1812 = new BitSet(new long[]{0x0000000000002400L});
    public static final BitSet FOLLOW_module_imp_in_import_stmt1852 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_import_stmt1875 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_module_imp_in_import_stmt1890 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_STAR_in_import_stmt1947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_dotted_name1997 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_DOT_in_dotted_name2007 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_dotted_name2014 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_dotted_name_in_dot_name2049 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_dot_name2069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dot_name_in_module_imp2099 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_module_imp2111 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_module_imp2118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_global_stmt2145 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_global_stmt2147 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_global_stmt2150 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_global_stmt2152 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_81_in_exec_stmt2177 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_expr_in_exec_stmt2183 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_82_in_exec_stmt2190 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_exec_stmt2199 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_exec_stmt2207 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_exec_stmt2213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_assert_stmt2240 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_assert_stmt2247 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_assert_stmt2253 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_assert_stmt2259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_stmt_in_compound_stmt2287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_stmt_in_compound_stmt2298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_stmt_in_compound_stmt2308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try_stmt_in_compound_stmt2318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_funcdef_in_compound_stmt2328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classdef_in_compound_stmt2338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_with_stmt_in_compound_stmt2348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_if_stmt2371 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_if_stmt2380 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_if_stmt2384 = new BitSet(new long[]{0x03F58C0000000540L,0x00000001400B7FE0L});
    public static final BitSet FOLLOW_suite_in_if_stmt2393 = new BitSet(new long[]{0x0000000000000002L,0x0000000000600000L});
    public static final BitSet FOLLOW_85_in_if_stmt2414 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_if_stmt2423 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_if_stmt2429 = new BitSet(new long[]{0x03F58C0000000540L,0x00000001400B7FE0L});
    public static final BitSet FOLLOW_suite_in_if_stmt2437 = new BitSet(new long[]{0x0000000000000002L,0x0000000000600000L});
    public static final BitSet FOLLOW_86_in_if_stmt2458 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_if_stmt2464 = new BitSet(new long[]{0x03F58C0000000540L,0x00000001400B7FE0L});
    public static final BitSet FOLLOW_suite_in_if_stmt2474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_87_in_while_stmt2510 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_while_stmt2518 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_while_stmt2522 = new BitSet(new long[]{0x03F58C0000000540L,0x00000001400B7FE0L});
    public static final BitSet FOLLOW_suite_in_while_stmt2532 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400000L});
    public static final BitSet FOLLOW_86_in_while_stmt2546 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_while_stmt2548 = new BitSet(new long[]{0x03F58C0000000540L,0x00000001400B7FE0L});
    public static final BitSet FOLLOW_suite_in_while_stmt2558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_for_stmt2589 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_exprlist_in_for_stmt2598 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_82_in_for_stmt2603 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_testlist_in_for_stmt2611 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_for_stmt2615 = new BitSet(new long[]{0x03F58C0000000540L,0x00000001400B7FE0L});
    public static final BitSet FOLLOW_suite_in_for_stmt2624 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400000L});
    public static final BitSet FOLLOW_86_in_for_stmt2639 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_for_stmt2645 = new BitSet(new long[]{0x03F58C0000000540L,0x00000001400B7FE0L});
    public static final BitSet FOLLOW_suite_in_for_stmt2655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_89_in_try_stmt2687 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_try_stmt2691 = new BitSet(new long[]{0x03F58C0000000540L,0x00000001400B7FE0L});
    public static final BitSet FOLLOW_suite_in_try_stmt2706 = new BitSet(new long[]{0x0000000000000000L,0x000000000C000000L});
    public static final BitSet FOLLOW_90_in_try_stmt2744 = new BitSet(new long[]{0x03F58C0000000D00L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_try_stmt2759 = new BitSet(new long[]{0x0000000000001800L});
    public static final BitSet FOLLOW_COMMA_in_try_stmt2766 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_try_stmt2772 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_try_stmt2785 = new BitSet(new long[]{0x03F58C0000000540L,0x00000001400B7FE0L});
    public static final BitSet FOLLOW_suite_in_try_stmt2794 = new BitSet(new long[]{0x0000000000000002L,0x0000000004400000L});
    public static final BitSet FOLLOW_86_in_try_stmt2819 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_try_stmt2825 = new BitSet(new long[]{0x03F58C0000000540L,0x00000001400B7FE0L});
    public static final BitSet FOLLOW_suite_in_try_stmt2835 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_try_stmt2946 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_try_stmt2969 = new BitSet(new long[]{0x03F58C0000000540L,0x00000001400B7FE0L});
    public static final BitSet FOLLOW_suite_in_try_stmt2985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_stmt_in_suite3043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_suite3064 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_INDENT_in_suite3074 = new BitSet(new long[]{0x03F58C0000000580L,0x00000007439B7FF0L});
    public static final BitSet FOLLOW_stmt_in_suite3098 = new BitSet(new long[]{0x03F58C00000005A0L,0x00000007439B7FF0L});
    public static final BitSet FOLLOW_DEDENT_in_suite3124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_test_in_or_test3174 = new BitSet(new long[]{0x0000000000000002L,0x0000000010000000L});
    public static final BitSet FOLLOW_92_in_or_test3188 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000040000000L});
    public static final BitSet FOLLOW_and_test_in_or_test3197 = new BitSet(new long[]{0x0000000000000002L,0x0000000010000000L});
    public static final BitSet FOLLOW_or_test_in_test3226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lambdef_in_test3241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_test_in_and_test3264 = new BitSet(new long[]{0x0000000000000002L,0x0000000020000000L});
    public static final BitSet FOLLOW_93_in_and_test3278 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000040000000L});
    public static final BitSet FOLLOW_not_test_in_and_test3287 = new BitSet(new long[]{0x0000000000000002L,0x0000000020000000L});
    public static final BitSet FOLLOW_94_in_not_test3320 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000040000000L});
    public static final BitSet FOLLOW_not_test_in_not_test3329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comparison_in_not_test3348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_comparison3371 = new BitSet(new long[]{0x0000003F80000002L,0x00000000C0040000L});
    public static final BitSet FOLLOW_comp_op_in_comparison3385 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_expr_in_comparison3394 = new BitSet(new long[]{0x0000003F80000002L,0x00000000C0040000L});
    public static final BitSet FOLLOW_LESS_in_comp_op3426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_comp_op3438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUAL_in_comp_op3449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATEREQUAL_in_comp_op3462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESSEQUAL_in_comp_op3472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALT_NOTEQUAL_in_comp_op3486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOTEQUAL_in_comp_op3498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_comp_op3513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_94_in_comp_op3523 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_82_in_comp_op3525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_95_in_comp_op3535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_95_in_comp_op3545 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_94_in_comp_op3547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_xor_expr_in_expr3569 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_VBAR_in_expr3583 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_xor_expr_in_expr3593 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_and_expr_in_xor_expr3626 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_CIRCUMFLEX_in_xor_expr3640 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_and_expr_in_xor_expr3649 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_shift_expr_in_and_expr3680 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_AMPER_in_and_expr3695 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_shift_expr_in_and_expr3705 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_arith_expr_in_shift_expr3735 = new BitSet(new long[]{0x0000020020000002L});
    public static final BitSet FOLLOW_LEFTSHIFT_in_shift_expr3759 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_RIGHTSHIFT_in_shift_expr3780 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_arith_expr_in_shift_expr3802 = new BitSet(new long[]{0x0000020020000002L});
    public static final BitSet FOLLOW_term_in_arith_expr3838 = new BitSet(new long[]{0x00000C0000000002L});
    public static final BitSet FOLLOW_PLUS_in_arith_expr3862 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_MINUS_in_arith_expr3882 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_term_in_arith_expr3901 = new BitSet(new long[]{0x00000C0000000002L});
    public static final BitSet FOLLOW_factor_in_term3931 = new BitSet(new long[]{0x0000700000002002L});
    public static final BitSet FOLLOW_STAR_in_term3955 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_SLASH_in_term3979 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_PERCENT_in_term4004 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_DOUBLESLASH_in_term4032 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_factor_in_term4058 = new BitSet(new long[]{0x0000700000002002L});
    public static final BitSet FOLLOW_PLUS_in_factor4104 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_MINUS_in_factor4127 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_TILDE_in_factor4149 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_factor_in_factor4171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_power_in_factor4188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_power4215 = new BitSet(new long[]{0x0001000040004102L});
    public static final BitSet FOLLOW_trailer_in_power4233 = new BitSet(new long[]{0x0001000040004102L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_power4266 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_factor_in_power4284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_trailer4332 = new BitSet(new long[]{0x03F58C0000006700L,0x0000000140000000L});
    public static final BitSet FOLLOW_arglist_in_trailer4344 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_RPAREN_in_trailer4356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_trailer4374 = new BitSet(new long[]{0x03F58C0040000D00L,0x0000000140000000L});
    public static final BitSet FOLLOW_subscriptlist_in_trailer4383 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_RBRACK_in_trailer4392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_trailer4407 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_trailer4415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_atom4445 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_tuplelist_in_atom4453 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_RPAREN_in_atom4463 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_atom4474 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_RPAREN_in_atom4482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_atom4494 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_listmaker_in_atom4502 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_RBRACK_in_atom4512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_atom4522 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_RBRACK_in_atom4530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_atom4541 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_dictmaker_in_atom4551 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_atom4561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_atom4571 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_atom4579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BACKQUOTE_in_atom4592 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_testlist_in_atom4598 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_BACKQUOTE_in_atom4607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_atom4617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_atom4629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LONGINT_in_atom4646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_atom4661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMPLEX_in_atom4679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_atom4696 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_test_in_listmaker4726 = new BitSet(new long[]{0x0000000000001002L,0x0000000001000000L});
    public static final BitSet FOLLOW_list_for_in_listmaker4743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_listmaker4791 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_listmaker4801 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_listmaker4820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_list_for4851 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_exprlist_in_list_for4859 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_82_in_list_for4863 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_testlist_in_list_for4872 = new BitSet(new long[]{0x0000000000000002L,0x0000000001100000L});
    public static final BitSet FOLLOW_list_if_in_list_for4895 = new BitSet(new long[]{0x0000000000000002L,0x0000000001100000L});
    public static final BitSet FOLLOW_84_in_list_if4928 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_list_if4935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_lambdef4956 = new BitSet(new long[]{0x0000000000006D00L});
    public static final BitSet FOLLOW_varargslist_in_lambdef4964 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_lambdef4971 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_lambdef4979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subscript_in_subscriptlist5003 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_subscriptlist5019 = new BitSet(new long[]{0x03F58C0040000D00L,0x0000000140000000L});
    public static final BitSet FOLLOW_subscript_in_subscriptlist5028 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_subscriptlist5043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_subscript5067 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_DOT_in_subscript5069 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_DOT_in_subscript5071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_subscript5096 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_COLON_in_subscript5111 = new BitSet(new long[]{0x03F58C0000000D02L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_subscript5127 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_sliceop_in_subscript5157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_subscript5199 = new BitSet(new long[]{0x03F58C0000000D02L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_subscript5223 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_sliceop_in_subscript5269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_sliceop5329 = new BitSet(new long[]{0x03F58C0000000502L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_sliceop5338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_exprlist5365 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_exprlist5387 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_expr_in_exprlist5396 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_exprlist5410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_testlist5437 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_testlist5466 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_testlist5478 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_testlist5513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_tuplelist5547 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_tuplelist5572 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_tuplelist5584 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_tuplelist5620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_97_in_with_stmt5652 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_with_stmt5660 = new BitSet(new long[]{0x0000000000000800L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_with_stmt5668 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_testlist_in_with_stmt5674 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_with_stmt5680 = new BitSet(new long[]{0x03F58C0000000540L,0x00000001400B7FE0L});
    public static final BitSet FOLLOW_suite_in_with_stmt5686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_dictmaker5708 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_dictmaker5711 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_dictmaker5718 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_dictmaker5754 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_dictmaker5769 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_dictmaker5780 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_dictmaker5795 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_dictmaker5830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_98_in_classdef5861 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_classdef5868 = new BitSet(new long[]{0x0000000000000900L});
    public static final BitSet FOLLOW_LPAREN_in_classdef5887 = new BitSet(new long[]{0x03F58C0000000700L,0x0000000140000000L});
    public static final BitSet FOLLOW_testlist_in_classdef5897 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_RPAREN_in_classdef5908 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_classdef5924 = new BitSet(new long[]{0x03F58C0000000540L,0x00000001400B7FE0L});
    public static final BitSet FOLLOW_suite_in_classdef5931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_argument_in_arglist5963 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_arglist6003 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_argument_in_arglist6021 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_arglist6059 = new BitSet(new long[]{0x0000000000006002L});
    public static final BitSet FOLLOW_STAR_in_arglist6071 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_arglist6082 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_arglist6138 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_arglist6157 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_arglist6180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_arglist6249 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_arglist6269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_arglist6308 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_arglist6317 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_arglist6333 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_arglist6339 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_arglist6349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_arglist6369 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_arglist6378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_argument6416 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_ASSIGN_in_argument6426 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000140000000L});
    public static final BitSet FOLLOW_test_in_argument6435 = new BitSet(new long[]{0x0000000000000002L});

}