// $ANTLR 3.0 C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g 2007-06-12 21:46:00

package org.eclipse.dltk.python.internal.core.parsers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
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

public class python_v3Parser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "INDENT", "DEDENT", "NEWLINE", "DECORATOR_S", "LPAREN", "RPAREN", "NAME", "COLON", "COMMA", "STAR", "DOUBLESTAR", "ASSIGN", "SEMI", "PLUSEQUAL", "MINUSEQUAL", "STAREQUAL", "SLASHEQUAL", "PERCENTEQUAL", "AMPEREQUAL", "VBAREQUAL", "CIRCUMFLEXEQUAL", "LEFTSHIFTEQUAL", "RIGHTSHIFTEQUAL", "DOUBLESTAREQUAL", "DOUBLESLASHEQUAL", "RIGHTSHIFT", "DOT", "LESS", "GREATER", "EQUAL", "GREATEREQUAL", "LESSEQUAL", "ALT_NOTEQUAL", "NOTEQUAL", "VBAR", "CIRCUMFLEX", "AMPER", "LEFTSHIFT", "PLUS", "MINUS", "SLASH", "PERCENT", "DOUBLESLASH", "TILDE", "LBRACK", "RBRACK", "LCURLY", "RCURLY", "BACKQUOTE", "INT", "LONGINT", "FLOAT", "COMPLEX", "STRING", "DIGITS", "Exponent", "ESC", "CONTINUED_LINE", "WS", "LEADING_WS", "COMMENT", "'def'", "'print'", "'del'", "'pass'", "'break'", "'continue'", "'return'", "'yield'", "'raise'", "'import'", "'from'", "'as'", "'global'", "'exec'", "'in'", "'assert'", "'if'", "'elif'", "'else'", "'while'", "'for'", "'try'", "'except'", "'finally'", "'or'", "'and'", "'not'", "'is'", "'lambda'", "'class'"
    };
    public static final int DOUBLESLASH=46;
    public static final int BACKQUOTE=52;
    public static final int SLASHEQUAL=20;
    public static final int CONTINUED_LINE=61;
    public static final int LBRACK=48;
    public static final int STAR=13;
    public static final int CIRCUMFLEXEQUAL=24;
    public static final int DOUBLESTAR=14;
    public static final int ESC=60;
    public static final int DIGITS=58;
    public static final int Exponent=59;
    public static final int GREATEREQUAL=34;
    public static final int COMPLEX=56;
    public static final int FLOAT=55;
    public static final int DEDENT=5;
    public static final int RIGHTSHIFTEQUAL=26;
    public static final int EOF=-1;
    public static final int LPAREN=8;
    public static final int INDENT=4;
    public static final int PLUSEQUAL=17;
    public static final int LEADING_WS=63;
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
    public static final int COMMENT=64;
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
    public static final int WS=62;
    public static final int NEWLINE=6;
    public static final int AMPEREQUAL=22;
    public static final int VBAREQUAL=23;
    public static final int RCURLY=51;
    public static final int ASSIGN=15;
    public static final int LONGINT=54;
    public static final int DECORATOR_S=7;
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
    public String getGrammarFileName() { return "C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g"; }

    
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



    // $ANTLR start file_input
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:128:1: file_input : ( NEWLINE | s= stmt )* EOF ;
    public final void file_input() throws RecognitionException {
        ArrayList s = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:129:6: ( ( NEWLINE | s= stmt )* EOF )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:129:6: ( NEWLINE | s= stmt )* EOF
            {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:129:6: ( NEWLINE | s= stmt )*
            loop1:
            do {
                int alt1=3;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==NEWLINE) ) {
                    alt1=1;
                }
                else if ( ((LA1_0>=DECORATOR_S && LA1_0<=LPAREN)||LA1_0==NAME||(LA1_0>=PLUS && LA1_0<=MINUS)||(LA1_0>=TILDE && LA1_0<=LBRACK)||LA1_0==LCURLY||(LA1_0>=BACKQUOTE && LA1_0<=STRING)||(LA1_0>=65 && LA1_0<=75)||(LA1_0>=77 && LA1_0<=78)||(LA1_0>=80 && LA1_0<=81)||(LA1_0>=84 && LA1_0<=86)||LA1_0==91||(LA1_0>=93 && LA1_0<=94)) ) {
                    alt1=2;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:130:7: NEWLINE
            	    {
            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_file_input101); 

            	    }
            	    break;
            	case 2 :
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:132:7: s= stmt
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:157:1: decorator returns [ Decorator decorator = null ] : dec= DECORATOR_S dottedName= dot_name (lp0= LPAREN arguments= arglist rp0= RPAREN )? NEWLINE ;
    public final Decorator decorator() throws RecognitionException {
        Decorator decorator =  null;

        Token dec=null;
        Token lp0=null;
        Token rp0=null;
        Token dottedName = null;

        ExpressionList arguments = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:158:2: (dec= DECORATOR_S dottedName= dot_name (lp0= LPAREN arguments= arglist rp0= RPAREN )? NEWLINE )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:158:2: dec= DECORATOR_S dottedName= dot_name (lp0= LPAREN arguments= arglist rp0= RPAREN )? NEWLINE
            {
            dec=(Token)input.LT(1);
            match(input,DECORATOR_S,FOLLOW_DECORATOR_S_in_decorator185); 
            pushFollow(FOLLOW_dot_name_in_decorator192);
            dottedName=dot_name();
            _fsp--;

            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:160:2: (lp0= LPAREN arguments= arglist rp0= RPAREN )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==LPAREN) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:161:4: lp0= LPAREN arguments= arglist rp0= RPAREN
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:177:1: decoraror_list returns [List decorators = new ArrayList() ] : (dec= decorator )+ ;
    public final List decoraror_list() throws RecognitionException {
        List decorators =  new ArrayList();

        Decorator dec = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:178:2: ( (dec= decorator )+ )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:178:2: (dec= decorator )+
            {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:178:2: (dec= decorator )+
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
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:179:3: dec= decorator
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:189:1: funcdef returns [ MethodDeclaration methodDeclaration = null; ] : (decorators= decoraror_list )? w= 'def' tu= NAME parameters[ params ] e= COLON body= suite ;
    public final MethodDeclaration funcdef() throws RecognitionException {
        MethodDeclaration methodDeclaration =  null;;

        Token w=null;
        Token tu=null;
        Token e=null;
        List decorators = null;

        Block body = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:190:2: ( (decorators= decoraror_list )? w= 'def' tu= NAME parameters[ params ] e= COLON body= suite )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:190:2: (decorators= decoraror_list )? w= 'def' tu= NAME parameters[ params ] e= COLON body= suite
            {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:190:2: (decorators= decoraror_list )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==DECORATOR_S) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:191:3: decorators= decoraror_list
                    {
                    pushFollow(FOLLOW_decoraror_list_in_funcdef294);
                    decorators=decoraror_list();
                    _fsp--;


                    }
                    break;

            }

            w=(Token)input.LT(1);
            match(input,65,FOLLOW_65_in_funcdef306); 
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:218:1: parameters[ List params ] : LPAREN ( varargslist[ params ] )? RPAREN ;
    public final void parameters(List params) throws RecognitionException {
        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:219:2: ( LPAREN ( varargslist[ params ] )? RPAREN )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:219:2: LPAREN ( varargslist[ params ] )? RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_parameters374); 
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:220:2: ( varargslist[ params ] )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==LPAREN||LA5_0==NAME||(LA5_0>=STAR && LA5_0<=DOUBLESTAR)) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:220:3: varargslist[ params ]
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:225:1: varargslist[ List params ] : ( defparameter[params] ( options {greedy=true; } : COMMA defparameter[ params ] )* ( COMMA ( STAR tu= NAME ( COMMA DOUBLESTAR t1= NAME )? | DOUBLESTAR t2= NAME )? )? | STAR m= NAME ( COMMA DOUBLESTAR m1= NAME )? | DOUBLESTAR m2= NAME );
    public final void varargslist(List params) throws RecognitionException {
        Token tu=null;
        Token t1=null;
        Token t2=null;
        Token m=null;
        Token m1=null;
        Token m2=null;

        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:226:2: ( defparameter[params] ( options {greedy=true; } : COMMA defparameter[ params ] )* ( COMMA ( STAR tu= NAME ( COMMA DOUBLESTAR t1= NAME )? | DOUBLESTAR t2= NAME )? )? | STAR m= NAME ( COMMA DOUBLESTAR m1= NAME )? | DOUBLESTAR m2= NAME )
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
                    new NoViableAltException("225:1: varargslist[ List params ] : ( defparameter[params] ( options {greedy=true; } : COMMA defparameter[ params ] )* ( COMMA ( STAR tu= NAME ( COMMA DOUBLESTAR t1= NAME )? | DOUBLESTAR t2= NAME )? )? | STAR m= NAME ( COMMA DOUBLESTAR m1= NAME )? | DOUBLESTAR m2= NAME );", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:226:2: defparameter[params] ( options {greedy=true; } : COMMA defparameter[ params ] )* ( COMMA ( STAR tu= NAME ( COMMA DOUBLESTAR t1= NAME )? | DOUBLESTAR t2= NAME )? )?
                    {
                    pushFollow(FOLLOW_defparameter_in_varargslist397);
                    defparameter(params);
                    _fsp--;

                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:227:2: ( options {greedy=true; } : COMMA defparameter[ params ] )*
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
                    	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:228:28: COMMA defparameter[ params ]
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

                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:231:9: ( COMMA ( STAR tu= NAME ( COMMA DOUBLESTAR t1= NAME )? | DOUBLESTAR t2= NAME )? )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==COMMA) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:231:10: COMMA ( STAR tu= NAME ( COMMA DOUBLESTAR t1= NAME )? | DOUBLESTAR t2= NAME )?
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_varargslist435); 
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:232:14: ( STAR tu= NAME ( COMMA DOUBLESTAR t1= NAME )? | DOUBLESTAR t2= NAME )?
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
                                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:232:16: STAR tu= NAME ( COMMA DOUBLESTAR t1= NAME )?
                                    {
                                    match(input,STAR,FOLLOW_STAR_in_varargslist452); 
                                    tu=(Token)input.LT(1);
                                    match(input,NAME,FOLLOW_NAME_in_varargslist458); 
                                     
                                    				params.add( new PythonArgument( toDLTK( tu ) ) ); 
                                    			
                                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:236:15: ( COMMA DOUBLESTAR t1= NAME )?
                                    int alt7=2;
                                    int LA7_0 = input.LA(1);

                                    if ( (LA7_0==COMMA) ) {
                                        alt7=1;
                                    }
                                    switch (alt7) {
                                        case 1 :
                                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:236:16: COMMA DOUBLESTAR t1= NAME
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
                                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:241:17: DOUBLESTAR t2= NAME
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
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:247:4: STAR m= NAME ( COMMA DOUBLESTAR m1= NAME )?
                    {
                    match(input,STAR,FOLLOW_STAR_in_varargslist587); 
                    m=(Token)input.LT(1);
                    match(input,NAME,FOLLOW_NAME_in_varargslist593); 
                     
                    			params.add( new PythonArgument( toDLTK( m ) ) );
                    		
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:251:2: ( COMMA DOUBLESTAR m1= NAME )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==COMMA) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:251:3: COMMA DOUBLESTAR m1= NAME
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
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:257:4: DOUBLESTAR m2= NAME
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:264:1: defparameter[ List params ] : lastParam= fpdef[ params ] ( ASSIGN initExpr= test )? ;
    public final void defparameter(List params) throws RecognitionException {
        PythonArgument lastParam = null;

        Expression initExpr = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:265:2: (lastParam= fpdef[ params ] ( ASSIGN initExpr= test )? )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:265:2: lastParam= fpdef[ params ] ( ASSIGN initExpr= test )?
            {
            pushFollow(FOLLOW_fpdef_in_defparameter652);
            lastParam=fpdef( params );
            _fsp--;

            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:266:2: ( ASSIGN initExpr= test )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==ASSIGN) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:266:3: ASSIGN initExpr= test
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:277:1: fpdef[ List params ] returns [ PythonArgument argument = null ] : (tu= NAME | LPAREN fplist[params] RPAREN );
    public final PythonArgument fpdef(List params) throws RecognitionException {
        PythonArgument argument =  null;

        Token tu=null;

        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:278:6: (tu= NAME | LPAREN fplist[params] RPAREN )
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
                    new NoViableAltException("277:1: fpdef[ List params ] returns [ PythonArgument argument = null ] : (tu= NAME | LPAREN fplist[params] RPAREN );", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:278:6: tu= NAME
                    {
                    tu=(Token)input.LT(1);
                    match(input,NAME,FOLLOW_NAME_in_fpdef695); 
                    
                    			argument = new PythonArgument( toDLTK( tu ) );
                    			params.add( argument );
                    		

                    }
                    break;
                case 2 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:283:3: LPAREN fplist[params] RPAREN
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:287:1: fplist[List params ] : fpdef[ params ] ( options {greedy=true; } : COMMA fpdef[ params ] )* ( COMMA )? ;
    public final void fplist(List params) throws RecognitionException {
        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:288:2: ( fpdef[ params ] ( options {greedy=true; } : COMMA fpdef[ params ] )* ( COMMA )? )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:288:2: fpdef[ params ] ( options {greedy=true; } : COMMA fpdef[ params ] )* ( COMMA )?
            {
            pushFollow(FOLLOW_fpdef_in_fplist726);
            fpdef( params );
            _fsp--;

            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:289:2: ( options {greedy=true; } : COMMA fpdef[ params ] )*
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
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:290:3: COMMA fpdef[ params ]
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

            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:292:2: ( COMMA )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==COMMA) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:292:3: COMMA
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:300:1: simple_stmt[ List stmts ] : small_stmt[ stmts ] ( options {greedy=true; } : SEMI small_stmt[ stmts ] )* ( SEMI )? NEWLINE ;
    public final void simple_stmt(List stmts) throws RecognitionException {
        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:301:2: ( small_stmt[ stmts ] ( options {greedy=true; } : SEMI small_stmt[ stmts ] )* ( SEMI )? NEWLINE )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:301:2: small_stmt[ stmts ] ( options {greedy=true; } : SEMI small_stmt[ stmts ] )* ( SEMI )? NEWLINE
            {
            pushFollow(FOLLOW_small_stmt_in_simple_stmt773);
            small_stmt( stmts );
            _fsp--;

            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:302:2: ( options {greedy=true; } : SEMI small_stmt[ stmts ] )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==SEMI) ) {
                    int LA16_1 = input.LA(2);

                    if ( (LA16_1==LPAREN||LA16_1==NAME||(LA16_1>=PLUS && LA16_1<=MINUS)||(LA16_1>=TILDE && LA16_1<=LBRACK)||LA16_1==LCURLY||(LA16_1>=BACKQUOTE && LA16_1<=STRING)||(LA16_1>=66 && LA16_1<=75)||(LA16_1>=77 && LA16_1<=78)||LA16_1==80||LA16_1==91||LA16_1==93) ) {
                        alt16=1;
                    }


                }


                switch (alt16) {
            	case 1 :
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:303:3: SEMI small_stmt[ stmts ]
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

            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:304:6: ( SEMI )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==SEMI) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:304:7: SEMI
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:306:4: stmt returns [ ArrayList statements = new ArrayList( ) ] : ( simple_stmt[ simpleStatements ] | compoundStatement= compound_stmt ) ;
    public final ArrayList stmt() throws RecognitionException {
        ArrayList statements =  new ArrayList( );

        Statement compoundStatement = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:307:2: ( ( simple_stmt[ simpleStatements ] | compoundStatement= compound_stmt ) )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:307:2: ( simple_stmt[ simpleStatements ] | compoundStatement= compound_stmt )
            {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:307:2: ( simple_stmt[ simpleStatements ] | compoundStatement= compound_stmt )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==LPAREN||LA18_0==NAME||(LA18_0>=PLUS && LA18_0<=MINUS)||(LA18_0>=TILDE && LA18_0<=LBRACK)||LA18_0==LCURLY||(LA18_0>=BACKQUOTE && LA18_0<=STRING)||(LA18_0>=66 && LA18_0<=75)||(LA18_0>=77 && LA18_0<=78)||LA18_0==80||LA18_0==91||LA18_0==93) ) {
                alt18=1;
            }
            else if ( (LA18_0==DECORATOR_S||LA18_0==65||LA18_0==81||(LA18_0>=84 && LA18_0<=86)||LA18_0==94) ) {
                alt18=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("307:2: ( simple_stmt[ simpleStatements ] | compoundStatement= compound_stmt )", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:308:3: simple_stmt[ simpleStatements ]
                    {
                    
                    		List simpleStatements = new ArrayList();
                    		
                    pushFollow(FOLLOW_simple_stmt_in_stmt830);
                    simple_stmt( simpleStatements );
                    _fsp--;

                     statements.addAll( simpleStatements ); 

                    }
                    break;
                case 2 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:313:3: compoundStatement= compound_stmt
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:320:1: small_stmt[ List stmts ] returns [ Statement rstatement = null ] : (statement1= expr_stmt | statement2= print_stmt | statement3= del_stmt | statement4= pass_stmt | statement5= flow_stmt | statement6= import_stmt | statement7= global_stmt | statement8= exec_stmt | statement9= assert_stmt ) ;
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
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:321:2: ( (statement1= expr_stmt | statement2= print_stmt | statement3= del_stmt | statement4= pass_stmt | statement5= flow_stmt | statement6= import_stmt | statement7= global_stmt | statement8= exec_stmt | statement9= assert_stmt ) )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:321:2: (statement1= expr_stmt | statement2= print_stmt | statement3= del_stmt | statement4= pass_stmt | statement5= flow_stmt | statement6= import_stmt | statement7= global_stmt | statement8= exec_stmt | statement9= assert_stmt )
            {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:321:2: (statement1= expr_stmt | statement2= print_stmt | statement3= del_stmt | statement4= pass_stmt | statement5= flow_stmt | statement6= import_stmt | statement7= global_stmt | statement8= exec_stmt | statement9= assert_stmt )
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
            case 91:
            case 93:
                {
                alt19=1;
                }
                break;
            case 66:
                {
                alt19=2;
                }
                break;
            case 67:
                {
                alt19=3;
                }
                break;
            case 68:
                {
                alt19=4;
                }
                break;
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
                {
                alt19=5;
                }
                break;
            case 74:
            case 75:
                {
                alt19=6;
                }
                break;
            case 77:
                {
                alt19=7;
                }
                break;
            case 78:
                {
                alt19=8;
                }
                break;
            case 80:
                {
                alt19=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("321:2: (statement1= expr_stmt | statement2= print_stmt | statement3= del_stmt | statement4= pass_stmt | statement5= flow_stmt | statement6= import_stmt | statement7= global_stmt | statement8= exec_stmt | statement9= assert_stmt )", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:322:4: statement1= expr_stmt
                    {
                    pushFollow(FOLLOW_expr_stmt_in_small_stmt884);
                    statement1=expr_stmt();
                    _fsp--;

                     rstatement = statement1; 

                    }
                    break;
                case 2 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:323:4: statement2= print_stmt
                    {
                    pushFollow(FOLLOW_print_stmt_in_small_stmt895);
                    statement2=print_stmt();
                    _fsp--;

                     rstatement = statement2; 

                    }
                    break;
                case 3 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:324:4: statement3= del_stmt
                    {
                    pushFollow(FOLLOW_del_stmt_in_small_stmt905);
                    statement3=del_stmt();
                    _fsp--;

                     rstatement = statement3; 

                    }
                    break;
                case 4 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:325:4: statement4= pass_stmt
                    {
                    pushFollow(FOLLOW_pass_stmt_in_small_stmt915);
                    statement4=pass_stmt();
                    _fsp--;

                     rstatement = statement4; 

                    }
                    break;
                case 5 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:326:4: statement5= flow_stmt
                    {
                    pushFollow(FOLLOW_flow_stmt_in_small_stmt925);
                    statement5=flow_stmt();
                    _fsp--;

                     rstatement = statement5; 

                    }
                    break;
                case 6 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:327:4: statement6= import_stmt
                    {
                    pushFollow(FOLLOW_import_stmt_in_small_stmt935);
                    statement6=import_stmt();
                    _fsp--;

                     rstatement = statement6; 

                    }
                    break;
                case 7 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:328:4: statement7= global_stmt
                    {
                    pushFollow(FOLLOW_global_stmt_in_small_stmt945);
                    statement7=global_stmt();
                    _fsp--;

                     rstatement = statement7; 

                    }
                    break;
                case 8 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:329:4: statement8= exec_stmt
                    {
                    pushFollow(FOLLOW_exec_stmt_in_small_stmt955);
                    statement8=exec_stmt();
                    _fsp--;

                     rstatement = statement8; 

                    }
                    break;
                case 9 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:330:4: statement9= assert_stmt
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:338:1: expr_stmt returns [ Expression exp = null ] : exp0= testlist (type= augassign right= testlist | (a= ASSIGN right= testlist )+ )? ;
    public final Expression expr_stmt() throws RecognitionException {
        Expression exp =  null;

        Token a=null;
        Expression exp0 = null;

        int type = 0;

        Expression right = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:339:2: (exp0= testlist (type= augassign right= testlist | (a= ASSIGN right= testlist )+ )? )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:339:2: exp0= testlist (type= augassign right= testlist | (a= ASSIGN right= testlist )+ )?
            {
            pushFollow(FOLLOW_testlist_in_expr_stmt993);
            exp0=testlist();
            _fsp--;

             exp = exp0; 
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:340:2: (type= augassign right= testlist | (a= ASSIGN right= testlist )+ )?
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
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:341:3: type= augassign right= testlist
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
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:353:3: (a= ASSIGN right= testlist )+
                    {
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:353:3: (a= ASSIGN right= testlist )+
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
                    	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:354:4: a= ASSIGN right= testlist
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:373:1: augassign returns [ int type = 0 ] : ( PLUSEQUAL | MINUSEQUAL | STAREQUAL | SLASHEQUAL | PERCENTEQUAL | AMPEREQUAL | VBAREQUAL | CIRCUMFLEXEQUAL | LEFTSHIFTEQUAL | RIGHTSHIFTEQUAL | DOUBLESTAREQUAL | DOUBLESLASHEQUAL );
    public final int augassign() throws RecognitionException {
        int type =  0;

        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:374:2: ( PLUSEQUAL | MINUSEQUAL | STAREQUAL | SLASHEQUAL | PERCENTEQUAL | AMPEREQUAL | VBAREQUAL | CIRCUMFLEXEQUAL | LEFTSHIFTEQUAL | RIGHTSHIFTEQUAL | DOUBLESTAREQUAL | DOUBLESLASHEQUAL )
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
                    new NoViableAltException("373:1: augassign returns [ int type = 0 ] : ( PLUSEQUAL | MINUSEQUAL | STAREQUAL | SLASHEQUAL | PERCENTEQUAL | AMPEREQUAL | VBAREQUAL | CIRCUMFLEXEQUAL | LEFTSHIFTEQUAL | RIGHTSHIFTEQUAL | DOUBLESTAREQUAL | DOUBLESLASHEQUAL );", 22, 0, input);

                throw nvae;
            }

            switch (alt22) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:374:2: PLUSEQUAL
                    {
                    match(input,PLUSEQUAL,FOLLOW_PLUSEQUAL_in_augassign1080); 
                    
                    			type = Expression.E_PLUS_ASSIGN;
                    		

                    }
                    break;
                case 2 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:378:4: MINUSEQUAL
                    {
                    match(input,MINUSEQUAL,FOLLOW_MINUSEQUAL_in_augassign1089); 
                    					
                    			type = Expression.E_MINUS_ASSIGN;		
                    		

                    }
                    break;
                case 3 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:382:4: STAREQUAL
                    {
                    match(input,STAREQUAL,FOLLOW_STAREQUAL_in_augassign1099); 
                    
                    			type = Expression.E_MULT_ASSIGN;
                    		

                    }
                    break;
                case 4 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:386:4: SLASHEQUAL
                    {
                    match(input,SLASHEQUAL,FOLLOW_SLASHEQUAL_in_augassign1108); 
                    
                    			type = Expression.E_DIV_ASSIGN;
                    		

                    }
                    break;
                case 5 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:390:4: PERCENTEQUAL
                    {
                    match(input,PERCENTEQUAL,FOLLOW_PERCENTEQUAL_in_augassign1118); 
                    
                    			type = Expression.E_MOD_ASSIGN;
                    		

                    }
                    break;
                case 6 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:394:4: AMPEREQUAL
                    {
                    match(input,AMPEREQUAL,FOLLOW_AMPEREQUAL_in_augassign1127); 
                    
                    			type = Expression.E_BAND_ASSIGN;
                    		

                    }
                    break;
                case 7 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:398:4: VBAREQUAL
                    {
                    match(input,VBAREQUAL,FOLLOW_VBAREQUAL_in_augassign1136); 
                    
                    			type = Expression.E_BOR_ASSIGN;
                    		

                    }
                    break;
                case 8 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:402:4: CIRCUMFLEXEQUAL
                    {
                    match(input,CIRCUMFLEXEQUAL,FOLLOW_CIRCUMFLEXEQUAL_in_augassign1145); 
                    
                    			type = Expression.E_BXOR_ASSIGN;
                    		

                    }
                    break;
                case 9 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:406:4: LEFTSHIFTEQUAL
                    {
                    match(input,LEFTSHIFTEQUAL,FOLLOW_LEFTSHIFTEQUAL_in_augassign1154); 
                    
                    			type = Expression.E_SL_ASSIGN;
                    		

                    }
                    break;
                case 10 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:410:4: RIGHTSHIFTEQUAL
                    {
                    match(input,RIGHTSHIFTEQUAL,FOLLOW_RIGHTSHIFTEQUAL_in_augassign1163); 
                    
                    			type = Expression.E_SR_ASSIGN;
                    		

                    }
                    break;
                case 11 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:414:4: DOUBLESTAREQUAL
                    {
                    match(input,DOUBLESTAREQUAL,FOLLOW_DOUBLESTAREQUAL_in_augassign1172); 
                    
                    			type = Expression.E_DOUBLESTAR_ASSIGN;
                    		

                    }
                    break;
                case 12 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:418:4: DOUBLESLASHEQUAL
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:424:1: print_stmt returns [ Statement statement = null ] : tu= 'print' (ex= testlist | RIGHTSHIFT ex= testlist )? ;
    public final Statement print_stmt() throws RecognitionException {
        Statement statement =  null;

        Token tu=null;
        Expression ex = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:426:9: (tu= 'print' (ex= testlist | RIGHTSHIFT ex= testlist )? )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:426:9: tu= 'print' (ex= testlist | RIGHTSHIFT ex= testlist )?
            {
            tu=(Token)input.LT(1);
            match(input,66,FOLLOW_66_in_print_stmt1215); 
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:427:2: (ex= testlist | RIGHTSHIFT ex= testlist )?
            int alt23=3;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==LPAREN||LA23_0==NAME||(LA23_0>=PLUS && LA23_0<=MINUS)||(LA23_0>=TILDE && LA23_0<=LBRACK)||LA23_0==LCURLY||(LA23_0>=BACKQUOTE && LA23_0<=STRING)||LA23_0==91||LA23_0==93) ) {
                alt23=1;
            }
            else if ( (LA23_0==RIGHTSHIFT) ) {
                alt23=2;
            }
            switch (alt23) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:428:3: ex= testlist
                    {
                    pushFollow(FOLLOW_testlist_in_print_stmt1226);
                    ex=testlist();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:429:12: RIGHTSHIFT ex= testlist
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:441:1: del_stmt returns [ Statement statement = null ] : sa= 'del' tu= exprlist ;
    public final Statement del_stmt() throws RecognitionException {
        Statement statement =  null;

        Token sa=null;
        PythonTestListExpression tu = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:442:2: (sa= 'del' tu= exprlist )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:442:2: sa= 'del' tu= exprlist
            {
            sa=(Token)input.LT(1);
            match(input,67,FOLLOW_67_in_del_stmt1286); 
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:450:1: pass_stmt returns [ Statement statement = null] : tu= 'pass' ;
    public final Statement pass_stmt() throws RecognitionException {
        Statement statement =  null;

        Token tu=null;

        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:451:2: (tu= 'pass' )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:451:2: tu= 'pass'
            {
            tu=(Token)input.LT(1);
            match(input,68,FOLLOW_68_in_pass_stmt1317); 
            
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:458:1: flow_stmt returns [ Statement statement = null ] : (statement0= break_stmt | statement1= continue_stmt | statement2= return_stmt | statement3= raise_stmt | statement4= yield_stmt );
    public final Statement flow_stmt() throws RecognitionException {
        Statement statement =  null;

        Statement statement0 = null;

        Statement statement1 = null;

        Statement statement2 = null;

        PythonRaiseStatement statement3 = null;

        PythonYieldStatement statement4 = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:459:4: (statement0= break_stmt | statement1= continue_stmt | statement2= return_stmt | statement3= raise_stmt | statement4= yield_stmt )
            int alt24=5;
            switch ( input.LA(1) ) {
            case 69:
                {
                alt24=1;
                }
                break;
            case 70:
                {
                alt24=2;
                }
                break;
            case 71:
                {
                alt24=3;
                }
                break;
            case 73:
                {
                alt24=4;
                }
                break;
            case 72:
                {
                alt24=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("458:1: flow_stmt returns [ Statement statement = null ] : (statement0= break_stmt | statement1= continue_stmt | statement2= return_stmt | statement3= raise_stmt | statement4= yield_stmt );", 24, 0, input);

                throw nvae;
            }

            switch (alt24) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:459:4: statement0= break_stmt
                    {
                    pushFollow(FOLLOW_break_stmt_in_flow_stmt1343);
                    statement0=break_stmt();
                    _fsp--;

                     statement = statement0; 

                    }
                    break;
                case 2 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:460:4: statement1= continue_stmt
                    {
                    pushFollow(FOLLOW_continue_stmt_in_flow_stmt1354);
                    statement1=continue_stmt();
                    _fsp--;

                     statement = statement1; 

                    }
                    break;
                case 3 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:461:4: statement2= return_stmt
                    {
                    pushFollow(FOLLOW_return_stmt_in_flow_stmt1364);
                    statement2=return_stmt();
                    _fsp--;

                     statement = statement2; 

                    }
                    break;
                case 4 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:462:4: statement3= raise_stmt
                    {
                    pushFollow(FOLLOW_raise_stmt_in_flow_stmt1374);
                    statement3=raise_stmt();
                    _fsp--;

                     statement = statement3; 

                    }
                    break;
                case 5 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:463:4: statement4= yield_stmt
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:466:1: break_stmt returns [ Statement statement = null ] : ta= 'break' ;
    public final Statement break_stmt() throws RecognitionException {
        Statement statement =  null;

        Token ta=null;

        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:467:2: (ta= 'break' )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:467:2: ta= 'break'
            {
            ta=(Token)input.LT(1);
            match(input,69,FOLLOW_69_in_break_stmt1405); 
            
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:473:1: continue_stmt returns [ Statement statement = null ] : ta= 'continue' ;
    public final Statement continue_stmt() throws RecognitionException {
        Statement statement =  null;

        Token ta=null;

        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:474:2: (ta= 'continue' )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:474:2: ta= 'continue'
            {
            ta=(Token)input.LT(1);
            match(input,70,FOLLOW_70_in_continue_stmt1429); 
            
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:480:1: return_stmt returns [ Statement statement = null ] : ra= 'return' (tu= testlist )? ;
    public final Statement return_stmt() throws RecognitionException {
        Statement statement =  null;

        Token ra=null;
        Expression tu = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:481:2: (ra= 'return' (tu= testlist )? )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:481:2: ra= 'return' (tu= testlist )?
            {
            ra=(Token)input.LT(1);
            match(input,71,FOLLOW_71_in_return_stmt1454); 
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:482:3: (tu= testlist )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==LPAREN||LA25_0==NAME||(LA25_0>=PLUS && LA25_0<=MINUS)||(LA25_0>=TILDE && LA25_0<=LBRACK)||LA25_0==LCURLY||(LA25_0>=BACKQUOTE && LA25_0<=STRING)||LA25_0==91||LA25_0==93) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:482:5: tu= testlist
                    {
                    pushFollow(FOLLOW_testlist_in_return_stmt1465);
                    tu=testlist();
                    _fsp--;


                    }
                    break;

            }

            
            			int end = -1;
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:493:1: yield_stmt returns [ PythonYieldStatement statement = null ] : tu= 'yield' r= testlist ;
    public final PythonYieldStatement yield_stmt() throws RecognitionException {
        PythonYieldStatement statement =  null;

        Token tu=null;
        Expression r = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:494:2: (tu= 'yield' r= testlist )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:494:2: tu= 'yield' r= testlist
            {
            tu=(Token)input.LT(1);
            match(input,72,FOLLOW_72_in_yield_stmt1493); 
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:501:1: raise_stmt returns [ PythonRaiseStatement statement = null ] : tu= 'raise' (e1= test ( COMMA e2= test ( COMMA e3= test )? )? )? ;
    public final PythonRaiseStatement raise_stmt() throws RecognitionException {
        PythonRaiseStatement statement =  null;

        Token tu=null;
        Expression e1 = null;

        Expression e2 = null;

        Expression e3 = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:502:2: (tu= 'raise' (e1= test ( COMMA e2= test ( COMMA e3= test )? )? )? )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:502:2: tu= 'raise' (e1= test ( COMMA e2= test ( COMMA e3= test )? )? )?
            {
            tu=(Token)input.LT(1);
            match(input,73,FOLLOW_73_in_raise_stmt1522); 
            
            		statement = new PythonRaiseStatement( toDLTK( tu ) );
            		int end = -1;
            	
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:507:2: (e1= test ( COMMA e2= test ( COMMA e3= test )? )? )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==LPAREN||LA28_0==NAME||(LA28_0>=PLUS && LA28_0<=MINUS)||(LA28_0>=TILDE && LA28_0<=LBRACK)||LA28_0==LCURLY||(LA28_0>=BACKQUOTE && LA28_0<=STRING)||LA28_0==91||LA28_0==93) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:508:3: e1= test ( COMMA e2= test ( COMMA e3= test )? )?
                    {
                    pushFollow(FOLLOW_test_in_raise_stmt1536);
                    e1=test();
                    _fsp--;

                    
                    			statement.acceptExpression1( e1 );
                    			if( e1 != null && e1.sourceEnd() > end ) {
                    				end = e1.sourceEnd();
                    			}
                    		
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:515:3: ( COMMA e2= test ( COMMA e3= test )? )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==COMMA) ) {
                        alt27=1;
                    }
                    switch (alt27) {
                        case 1 :
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:515:5: COMMA e2= test ( COMMA e3= test )?
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_raise_stmt1547); 
                            pushFollow(FOLLOW_test_in_raise_stmt1556);
                            e2=test();
                            _fsp--;

                            
                            				statement.acceptExpression2( e2 );
                            				if( e2 != null && e2.sourceEnd() > end ) {
                            					end = e2.sourceEnd();
                            				}
                            			
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:523:4: ( COMMA e3= test )?
                            int alt26=2;
                            int LA26_0 = input.LA(1);

                            if ( (LA26_0==COMMA) ) {
                                alt26=1;
                            }
                            switch (alt26) {
                                case 1 :
                                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:523:6: COMMA e3= test
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:537:1: import_stmt returns [ Statement statement = null ] : ( (tu= 'import' expr0= module_imp ( COMMA expr0= module_imp )* ) | r= 'from' moduleName= dot_name 'import' ( (expr0= module_imp ( COMMA expr0= module_imp )* ) | ( STAR ) ) ) ;
    public final Statement import_stmt() throws RecognitionException {
        Statement statement =  null;

        Token tu=null;
        Token r=null;
        Expression expr0 = null;

        Token moduleName = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:538:6: ( ( (tu= 'import' expr0= module_imp ( COMMA expr0= module_imp )* ) | r= 'from' moduleName= dot_name 'import' ( (expr0= module_imp ( COMMA expr0= module_imp )* ) | ( STAR ) ) ) )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:538:6: ( (tu= 'import' expr0= module_imp ( COMMA expr0= module_imp )* ) | r= 'from' moduleName= dot_name 'import' ( (expr0= module_imp ( COMMA expr0= module_imp )* ) | ( STAR ) ) )
            {
            
                		Expression impExpr;
                		String impName;
                		String impName2;
                		//Token moduleName;		
                		
                		PythonTestListExpression importNames = new PythonTestListExpression();    		
                	
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:547:6: ( (tu= 'import' expr0= module_imp ( COMMA expr0= module_imp )* ) | r= 'from' moduleName= dot_name 'import' ( (expr0= module_imp ( COMMA expr0= module_imp )* ) | ( STAR ) ) )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==74) ) {
                alt32=1;
            }
            else if ( (LA32_0==75) ) {
                alt32=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("547:6: ( (tu= 'import' expr0= module_imp ( COMMA expr0= module_imp )* ) | r= 'from' moduleName= dot_name 'import' ( (expr0= module_imp ( COMMA expr0= module_imp )* ) | ( STAR ) ) )", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:548:7: (tu= 'import' expr0= module_imp ( COMMA expr0= module_imp )* )
                    {
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:548:7: (tu= 'import' expr0= module_imp ( COMMA expr0= module_imp )* )
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:549:8: tu= 'import' expr0= module_imp ( COMMA expr0= module_imp )*
                    {
                    tu=(Token)input.LT(1);
                    match(input,74,FOLLOW_74_in_import_stmt1696); 
                    	    	
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
                        				
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:564:8: ( COMMA expr0= module_imp )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==COMMA) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:564:10: COMMA expr0= module_imp
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
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:576:3: r= 'from' moduleName= dot_name 'import' ( (expr0= module_imp ( COMMA expr0= module_imp )* ) | ( STAR ) )
                    {
                    r=(Token)input.LT(1);
                    match(input,75,FOLLOW_75_in_import_stmt1797); 
                    pushFollow(FOLLOW_dot_name_in_import_stmt1805);
                    moduleName=dot_name();
                    _fsp--;

                    match(input,74,FOLLOW_74_in_import_stmt1812); 
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:580:3: ( (expr0= module_imp ( COMMA expr0= module_imp )* ) | ( STAR ) )
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
                            new NoViableAltException("580:3: ( (expr0= module_imp ( COMMA expr0= module_imp )* ) | ( STAR ) )", 31, 0, input);

                        throw nvae;
                    }
                    switch (alt31) {
                        case 1 :
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:581:11: (expr0= module_imp ( COMMA expr0= module_imp )* )
                            {
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:581:11: (expr0= module_imp ( COMMA expr0= module_imp )* )
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:582:6: expr0= module_imp ( COMMA expr0= module_imp )*
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
                                					
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:596:9: ( COMMA expr0= module_imp )*
                            loop30:
                            do {
                                int alt30=2;
                                int LA30_0 = input.LA(1);

                                if ( (LA30_0==COMMA) ) {
                                    alt30=1;
                                }


                                switch (alt30) {
                            	case 1 :
                            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:596:11: COMMA expr0= module_imp
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
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:608:8: ( STAR )
                            {
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:608:8: ( STAR )
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:609:5: STAR
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:619:1: dotted_name returns [ Token token = null ] : n= NAME ( DOT n2= NAME )+ ;
    public final Token dotted_name() throws RecognitionException {
        Token token =  null;

        Token n=null;
        Token n2=null;

        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:620:2: (n= NAME ( DOT n2= NAME )+ )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:620:2: n= NAME ( DOT n2= NAME )+
            {
            		
            		String value = "";
            	
            n=(Token)input.LT(1);
            match(input,NAME,FOLLOW_NAME_in_dotted_name1997); 
            
            		value += n.getText();
            	
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:627:2: ( DOT n2= NAME )+
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
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:628:3: DOT n2= NAME
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:641:1: dot_name returns [ Token token = null ] : ( (moduleName1= dotted_name ) | (moduleName2= NAME ) );
    public final Token dot_name() throws RecognitionException {
        Token token =  null;

        Token moduleName2=null;
        Token moduleName1 = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:642:2: ( (moduleName1= dotted_name ) | (moduleName2= NAME ) )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==NAME) ) {
                int LA34_1 = input.LA(2);

                if ( (LA34_1==NEWLINE||LA34_1==LPAREN||LA34_1==COMMA||LA34_1==SEMI||LA34_1==74||LA34_1==76) ) {
                    alt34=2;
                }
                else if ( (LA34_1==DOT) ) {
                    alt34=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("641:1: dot_name returns [ Token token = null ] : ( (moduleName1= dotted_name ) | (moduleName2= NAME ) );", 34, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("641:1: dot_name returns [ Token token = null ] : ( (moduleName1= dotted_name ) | (moduleName2= NAME ) );", 34, 0, input);

                throw nvae;
            }
            switch (alt34) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:642:2: (moduleName1= dotted_name )
                    {
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:642:2: (moduleName1= dotted_name )
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:643:3: moduleName1= dotted_name
                    {
                    pushFollow(FOLLOW_dotted_name_in_dot_name2049);
                    moduleName1=dotted_name();
                    _fsp--;

                    
                    	    		token = moduleName1;
                    	    	

                    }


                    }
                    break;
                case 2 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:649:2: (moduleName2= NAME )
                    {
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:649:2: (moduleName2= NAME )
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:650:3: moduleName2= NAME
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:662:1: module_imp returns [ Expression expr = null ] : impName= dot_name ( 'as' as= NAME )? ;
    public final Expression module_imp() throws RecognitionException {
        Expression expr =  null;

        Token as=null;
        Token impName = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:663:2: (impName= dot_name ( 'as' as= NAME )? )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:663:2: impName= dot_name ( 'as' as= NAME )?
            {
            pushFollow(FOLLOW_dot_name_in_module_imp2099);
            impName=dot_name();
            _fsp--;

            
            		expr = new PythonImportExpression( toDLTK( impName ) );
            	
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:667:2: ( 'as' as= NAME )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==76) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:668:3: 'as' as= NAME
                    {
                    match(input,76,FOLLOW_76_in_module_imp2111); 
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:677:1: global_stmt returns [ Statement statement = null ] : 'global' NAME ( COMMA NAME )* ;
    public final Statement global_stmt() throws RecognitionException {
        Statement statement =  null;

        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:678:3: ( 'global' NAME ( COMMA NAME )* )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:678:3: 'global' NAME ( COMMA NAME )*
            {
            match(input,77,FOLLOW_77_in_global_stmt2145); 
            match(input,NAME,FOLLOW_NAME_in_global_stmt2147); 
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:678:17: ( COMMA NAME )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==COMMA) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:678:18: COMMA NAME
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:682:1: exec_stmt returns [ Statement statement = null] : e= 'exec' ex= expr ( 'in' ex= test ( COMMA ex= test )? )? ;
    public final Statement exec_stmt() throws RecognitionException {
        Statement statement =  null;

        Token e=null;
        Expression ex = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:683:2: (e= 'exec' ex= expr ( 'in' ex= test ( COMMA ex= test )? )? )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:683:2: e= 'exec' ex= expr ( 'in' ex= test ( COMMA ex= test )? )?
            {
            e=(Token)input.LT(1);
            match(input,78,FOLLOW_78_in_exec_stmt2177); 
            pushFollow(FOLLOW_expr_in_exec_stmt2183);
            ex=expr();
            _fsp--;

             statement = new ExecStatement(this.converter.convert(e), ex); 
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:684:2: ( 'in' ex= test ( COMMA ex= test )? )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==79) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:684:3: 'in' ex= test ( COMMA ex= test )?
                    {
                    match(input,79,FOLLOW_79_in_exec_stmt2190); 
                    pushFollow(FOLLOW_test_in_exec_stmt2199);
                    ex=test();
                    _fsp--;

                     ((ExecStatement)statement).acceptIn(ex); 
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:686:3: ( COMMA ex= test )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==COMMA) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:686:4: COMMA ex= test
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:690:1: assert_stmt returns [ PythonAssertStatement statement = null ] : tu= 'assert' exp1= test ( COMMA exp2= test )? ;
    public final PythonAssertStatement assert_stmt() throws RecognitionException {
        PythonAssertStatement statement =  null;

        Token tu=null;
        Expression exp1 = null;

        Expression exp2 = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:691:2: (tu= 'assert' exp1= test ( COMMA exp2= test )? )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:691:2: tu= 'assert' exp1= test ( COMMA exp2= test )?
            {
            tu=(Token)input.LT(1);
            match(input,80,FOLLOW_80_in_assert_stmt2240); 
            pushFollow(FOLLOW_test_in_assert_stmt2247);
            exp1=test();
            _fsp--;

            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:693:2: ( COMMA exp2= test )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==COMMA) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:693:4: COMMA exp2= test
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:700:1: compound_stmt returns [ Statement statement = null ] : (statement0= if_stmt | statement1= while_stmt | statement2= for_stmt | statement3= try_stmt | statement4= funcdef | statement5= classdef );
    public final Statement compound_stmt() throws RecognitionException {
        Statement statement =  null;

        IfStatement statement0 = null;

        PythonWhileStatement statement1 = null;

        PythonForStatement statement2 = null;

        PythonTryStatement statement3 = null;

        MethodDeclaration statement4 = null;

        PythonClassDeclaration statement5 = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:701:2: (statement0= if_stmt | statement1= while_stmt | statement2= for_stmt | statement3= try_stmt | statement4= funcdef | statement5= classdef )
            int alt40=6;
            switch ( input.LA(1) ) {
            case 81:
                {
                alt40=1;
                }
                break;
            case 84:
                {
                alt40=2;
                }
                break;
            case 85:
                {
                alt40=3;
                }
                break;
            case 86:
                {
                alt40=4;
                }
                break;
            case DECORATOR_S:
            case 65:
                {
                alt40=5;
                }
                break;
            case 94:
                {
                alt40=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("700:1: compound_stmt returns [ Statement statement = null ] : (statement0= if_stmt | statement1= while_stmt | statement2= for_stmt | statement3= try_stmt | statement4= funcdef | statement5= classdef );", 40, 0, input);

                throw nvae;
            }

            switch (alt40) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:701:2: statement0= if_stmt
                    {
                    pushFollow(FOLLOW_if_stmt_in_compound_stmt2287);
                    statement0=if_stmt();
                    _fsp--;

                     statement = statement0; 

                    }
                    break;
                case 2 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:702:4: statement1= while_stmt
                    {
                    pushFollow(FOLLOW_while_stmt_in_compound_stmt2298);
                    statement1=while_stmt();
                    _fsp--;

                     statement = statement1; 

                    }
                    break;
                case 3 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:703:4: statement2= for_stmt
                    {
                    pushFollow(FOLLOW_for_stmt_in_compound_stmt2308);
                    statement2=for_stmt();
                    _fsp--;

                     statement = statement2; 

                    }
                    break;
                case 4 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:704:4: statement3= try_stmt
                    {
                    pushFollow(FOLLOW_try_stmt_in_compound_stmt2318);
                    statement3=try_stmt();
                    _fsp--;

                     statement = statement3; 

                    }
                    break;
                case 5 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:705:4: statement4= funcdef
                    {
                    pushFollow(FOLLOW_funcdef_in_compound_stmt2328);
                    statement4=funcdef();
                    _fsp--;

                     statement = statement4; 

                    }
                    break;
                case 6 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:706:4: statement5= classdef
                    {
                    pushFollow(FOLLOW_classdef_in_compound_stmt2338);
                    statement5=classdef();
                    _fsp--;

                     statement = statement5; 

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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:709:1: if_stmt returns [ IfStatement statement = null ] : is= 'if' mn= test COLON body= suite (z= 'elif' mn= test COLON body= suite )* ( 'else' COLON body= suite )? ;
    public final IfStatement if_stmt() throws RecognitionException {
        IfStatement statement =  null;

        Token is=null;
        Token z=null;
        Expression mn = null;

        Block body = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:710:3: (is= 'if' mn= test COLON body= suite (z= 'elif' mn= test COLON body= suite )* ( 'else' COLON body= suite )? )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:710:3: is= 'if' mn= test COLON body= suite (z= 'elif' mn= test COLON body= suite )* ( 'else' COLON body= suite )?
            {
            is=(Token)input.LT(1);
            match(input,81,FOLLOW_81_in_if_stmt2360); 
            pushFollow(FOLLOW_test_in_if_stmt2369);
            mn=test();
            _fsp--;

            match(input,COLON,FOLLOW_COLON_in_if_stmt2373); 
            pushFollow(FOLLOW_suite_in_if_stmt2382);
            body=suite();
            _fsp--;

             
            			IfStatement t,base;
            			statement = new IfStatement( toDLTK( is ), mn, body ); 
            			statement.setEnd(body.sourceEnd());
            			base = statement;
            			t = statement; 
            		
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:722:3: (z= 'elif' mn= test COLON body= suite )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==82) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:723:4: z= 'elif' mn= test COLON body= suite
            	    {
            	    z=(Token)input.LT(1);
            	    match(input,82,FOLLOW_82_in_if_stmt2403); 
            	    pushFollow(FOLLOW_test_in_if_stmt2412);
            	    mn=test();
            	    _fsp--;

            	    match(input,COLON,FOLLOW_COLON_in_if_stmt2418); 
            	    pushFollow(FOLLOW_suite_in_if_stmt2426);
            	    body=suite();
            	    _fsp--;

            	     
            	    					IfStatement elseIfStatement = new IfStatement( toDLTK( z ), mn, body );
            	    					t.acceptElse( elseIfStatement );
            	    					t = elseIfStatement;
            	    					base.setEnd(elseIfStatement.sourceEnd());
            	    				

            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);

            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:734:3: ( 'else' COLON body= suite )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==83) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:735:4: 'else' COLON body= suite
                    {
                    match(input,83,FOLLOW_83_in_if_stmt2447); 
                    match(input,COLON,FOLLOW_COLON_in_if_stmt2453); 
                    pushFollow(FOLLOW_suite_in_if_stmt2463);
                    body=suite();
                    _fsp--;

                     
                    					t.setElse( body );
                    				

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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:744:1: while_stmt returns [ PythonWhileStatement whileStatement = null ] : is= 'while' expression= test COLON body= suite ( 'else' COLON body= suite )? ;
    public final PythonWhileStatement while_stmt() throws RecognitionException {
        PythonWhileStatement whileStatement =  null;

        Token is=null;
        Expression expression = null;

        Block body = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:745:3: (is= 'while' expression= test COLON body= suite ( 'else' COLON body= suite )? )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:745:3: is= 'while' expression= test COLON body= suite ( 'else' COLON body= suite )?
            {
            is=(Token)input.LT(1);
            match(input,84,FOLLOW_84_in_while_stmt2499); 
            pushFollow(FOLLOW_test_in_while_stmt2507);
            expression=test();
            _fsp--;

            match(input,COLON,FOLLOW_COLON_in_while_stmt2511); 
            pushFollow(FOLLOW_suite_in_while_stmt2521);
            body=suite();
            _fsp--;

             
            				whileStatement = new PythonWhileStatement( toDLTK( is ), expression, body ); 
            			
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:752:3: ( 'else' COLON body= suite )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==83) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:753:4: 'else' COLON body= suite
                    {
                    match(input,83,FOLLOW_83_in_while_stmt2535); 
                    match(input,COLON,FOLLOW_COLON_in_while_stmt2537); 
                    pushFollow(FOLLOW_suite_in_while_stmt2547);
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:761:1: for_stmt returns [ PythonForStatement statement = null] : is= 'for' mains= exprlist 'in' conds= testlist COLON body= suite ( 'else' COLON body= suite )? ;
    public final PythonForStatement for_stmt() throws RecognitionException {
        PythonForStatement statement =  null;

        Token is=null;
        PythonTestListExpression mains = null;

        Expression conds = null;

        Block body = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:762:3: (is= 'for' mains= exprlist 'in' conds= testlist COLON body= suite ( 'else' COLON body= suite )? )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:762:3: is= 'for' mains= exprlist 'in' conds= testlist COLON body= suite ( 'else' COLON body= suite )?
            {
            is=(Token)input.LT(1);
            match(input,85,FOLLOW_85_in_for_stmt2578); 
            pushFollow(FOLLOW_exprlist_in_for_stmt2587);
            mains=exprlist();
            _fsp--;

            match(input,79,FOLLOW_79_in_for_stmt2592); 
            pushFollow(FOLLOW_testlist_in_for_stmt2600);
            conds=testlist();
            _fsp--;

            match(input,COLON,FOLLOW_COLON_in_for_stmt2604); 
            pushFollow(FOLLOW_suite_in_for_stmt2613);
            body=suite();
            _fsp--;

            
            				statement = new PythonForStatement( toDLTK( is ), mains, conds, body );
            			
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:771:3: ( 'else' COLON body= suite )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==83) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:772:4: 'else' COLON body= suite
                    {
                    match(input,83,FOLLOW_83_in_for_stmt2628); 
                    match(input,COLON,FOLLOW_COLON_in_for_stmt2634); 
                    pushFollow(FOLLOW_suite_in_for_stmt2644);
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:783:1: try_stmt returns [ PythonTryStatement statement = null ] : is= 'try' COLON body= suite ( ( (ex_= 'except' (t1= test ( COMMA t2= test )? )? COLON su= suite )+ ( (elseT= 'else' COLON elseBlock= suite ) )? ) | (fin= 'finally' COLON su= suite ) ) ;
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
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:784:2: (is= 'try' COLON body= suite ( ( (ex_= 'except' (t1= test ( COMMA t2= test )? )? COLON su= suite )+ ( (elseT= 'else' COLON elseBlock= suite ) )? ) | (fin= 'finally' COLON su= suite ) ) )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:784:2: is= 'try' COLON body= suite ( ( (ex_= 'except' (t1= test ( COMMA t2= test )? )? COLON su= suite )+ ( (elseT= 'else' COLON elseBlock= suite ) )? ) | (fin= 'finally' COLON su= suite ) )
            {
            is=(Token)input.LT(1);
            match(input,86,FOLLOW_86_in_try_stmt2676); 
            match(input,COLON,FOLLOW_COLON_in_try_stmt2680); 
            
            			Token lastTok = is;
            			
                    		List catches = new ArrayList();
            		
            pushFollow(FOLLOW_suite_in_try_stmt2695);
            body=suite();
            _fsp--;

            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:792:6: ( ( (ex_= 'except' (t1= test ( COMMA t2= test )? )? COLON su= suite )+ ( (elseT= 'else' COLON elseBlock= suite ) )? ) | (fin= 'finally' COLON su= suite ) )
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==87) ) {
                alt49=1;
            }
            else if ( (LA49_0==88) ) {
                alt49=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("792:6: ( ( (ex_= 'except' (t1= test ( COMMA t2= test )? )? COLON su= suite )+ ( (elseT= 'else' COLON elseBlock= suite ) )? ) | (fin= 'finally' COLON su= suite ) )", 49, 0, input);

                throw nvae;
            }
            switch (alt49) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:793:9: ( (ex_= 'except' (t1= test ( COMMA t2= test )? )? COLON su= suite )+ ( (elseT= 'else' COLON elseBlock= suite ) )? )
                    {
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:793:9: ( (ex_= 'except' (t1= test ( COMMA t2= test )? )? COLON su= suite )+ ( (elseT= 'else' COLON elseBlock= suite ) )? )
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:794:3: (ex_= 'except' (t1= test ( COMMA t2= test )? )? COLON su= suite )+ ( (elseT= 'else' COLON elseBlock= suite ) )?
                    {
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:794:3: (ex_= 'except' (t1= test ( COMMA t2= test )? )? COLON su= suite )+
                    int cnt47=0;
                    loop47:
                    do {
                        int alt47=2;
                        int LA47_0 = input.LA(1);

                        if ( (LA47_0==87) ) {
                            alt47=1;
                        }


                        switch (alt47) {
                    	case 1 :
                    	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:795:4: ex_= 'except' (t1= test ( COMMA t2= test )? )? COLON su= suite
                    	    {
                    	    ex_=(Token)input.LT(1);
                    	    match(input,87,FOLLOW_87_in_try_stmt2733); 
                    	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:796:4: (t1= test ( COMMA t2= test )? )?
                    	    int alt46=2;
                    	    int LA46_0 = input.LA(1);

                    	    if ( (LA46_0==LPAREN||LA46_0==NAME||(LA46_0>=PLUS && LA46_0<=MINUS)||(LA46_0>=TILDE && LA46_0<=LBRACK)||LA46_0==LCURLY||(LA46_0>=BACKQUOTE && LA46_0<=STRING)||LA46_0==91||LA46_0==93) ) {
                    	        alt46=1;
                    	    }
                    	    switch (alt46) {
                    	        case 1 :
                    	            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:797:5: t1= test ( COMMA t2= test )?
                    	            {
                    	            pushFollow(FOLLOW_test_in_try_stmt2748);
                    	            t1=test();
                    	            _fsp--;

                    	            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:798:5: ( COMMA t2= test )?
                    	            int alt45=2;
                    	            int LA45_0 = input.LA(1);

                    	            if ( (LA45_0==COMMA) ) {
                    	                alt45=1;
                    	            }
                    	            switch (alt45) {
                    	                case 1 :
                    	                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:798:6: COMMA t2= test
                    	                    {
                    	                    match(input,COMMA,FOLLOW_COMMA_in_try_stmt2755); 
                    	                    pushFollow(FOLLOW_test_in_try_stmt2761);
                    	                    t2=test();
                    	                    _fsp--;


                    	                    }
                    	                    break;

                    	            }


                    	            }
                    	            break;

                    	    }

                    	    match(input,COLON,FOLLOW_COLON_in_try_stmt2774); 
                    	    pushFollow(FOLLOW_suite_in_try_stmt2783);
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

                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:807:3: ( (elseT= 'else' COLON elseBlock= suite ) )?
                    int alt48=2;
                    int LA48_0 = input.LA(1);

                    if ( (LA48_0==83) ) {
                        alt48=1;
                    }
                    switch (alt48) {
                        case 1 :
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:808:4: (elseT= 'else' COLON elseBlock= suite )
                            {
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:808:4: (elseT= 'else' COLON elseBlock= suite )
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:808:6: elseT= 'else' COLON elseBlock= suite
                            {
                            elseT=(Token)input.LT(1);
                            match(input,83,FOLLOW_83_in_try_stmt2808); 
                            match(input,COLON,FOLLOW_COLON_in_try_stmt2814); 
                            pushFollow(FOLLOW_suite_in_try_stmt2824);
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
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:820:9: (fin= 'finally' COLON su= suite )
                    {
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:820:9: (fin= 'finally' COLON su= suite )
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:821:10: fin= 'finally' COLON su= suite
                    {
                    fin=(Token)input.LT(1);
                    match(input,88,FOLLOW_88_in_try_stmt2935); 
                    
                    	       		lastTok = fin;
                            	
                    match(input,COLON,FOLLOW_COLON_in_try_stmt2958); 
                    pushFollow(FOLLOW_suite_in_try_stmt2974);
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:838:1: suite returns [ Block statement = new Block() ] : ( simple_stmt[ l ] | NEWLINE ind= INDENT (k= stmt )+ d= DEDENT ) ;
    public final Block suite() throws RecognitionException {
        Block statement =  new Block();

        Token ind=null;
        Token d=null;
        ArrayList k = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:839:4: ( ( simple_stmt[ l ] | NEWLINE ind= INDENT (k= stmt )+ d= DEDENT ) )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:839:4: ( simple_stmt[ l ] | NEWLINE ind= INDENT (k= stmt )+ d= DEDENT )
            {
            
            	  	ArrayList l = new ArrayList();		
            	  	int startPos = -1;
            	  	int endPos = -1;
            	  
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:844:4: ( simple_stmt[ l ] | NEWLINE ind= INDENT (k= stmt )+ d= DEDENT )
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==LPAREN||LA51_0==NAME||(LA51_0>=PLUS && LA51_0<=MINUS)||(LA51_0>=TILDE && LA51_0<=LBRACK)||LA51_0==LCURLY||(LA51_0>=BACKQUOTE && LA51_0<=STRING)||(LA51_0>=66 && LA51_0<=75)||(LA51_0>=77 && LA51_0<=78)||LA51_0==80||LA51_0==91||LA51_0==93) ) {
                alt51=1;
            }
            else if ( (LA51_0==NEWLINE) ) {
                alt51=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("844:4: ( simple_stmt[ l ] | NEWLINE ind= INDENT (k= stmt )+ d= DEDENT )", 51, 0, input);

                throw nvae;
            }
            switch (alt51) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:845:5: simple_stmt[ l ]
                    {
                    pushFollow(FOLLOW_simple_stmt_in_suite3034);
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
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:862:5: NEWLINE ind= INDENT (k= stmt )+ d= DEDENT
                    {
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_suite3055); 
                    ind=(Token)input.LT(1);
                    match(input,INDENT,FOLLOW_INDENT_in_suite3065); 
                    
                    	  			if( ind != null ) {
                    	  				
                    		  			int s = converter.convert( ind ).getColumn();
                    		  			if( s < startPos && s != -1 ) {
                    		  				startPos = s;
                    		  			}
                    	  			}
                    	  			//ArrayList k;
                    	  		
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:874:5: (k= stmt )+
                    int cnt50=0;
                    loop50:
                    do {
                        int alt50=2;
                        int LA50_0 = input.LA(1);

                        if ( ((LA50_0>=DECORATOR_S && LA50_0<=LPAREN)||LA50_0==NAME||(LA50_0>=PLUS && LA50_0<=MINUS)||(LA50_0>=TILDE && LA50_0<=LBRACK)||LA50_0==LCURLY||(LA50_0>=BACKQUOTE && LA50_0<=STRING)||(LA50_0>=65 && LA50_0<=75)||(LA50_0>=77 && LA50_0<=78)||(LA50_0>=80 && LA50_0<=81)||(LA50_0>=84 && LA50_0<=86)||LA50_0==91||(LA50_0>=93 && LA50_0<=94)) ) {
                            alt50=1;
                        }


                        switch (alt50) {
                    	case 1 :
                    	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:875:6: k= stmt
                    	    {
                    	    pushFollow(FOLLOW_stmt_in_suite3089);
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
                    match(input,DEDENT,FOLLOW_DEDENT_in_suite3115); 
                    
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


    // $ANTLR start test
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:922:1: test returns [ Expression exp = null ] : (exp0= and_test (r= 'or' v= and_test )* | (exp0= lambdef ) );
    public final Expression test() throws RecognitionException {
        Expression exp =  null;

        Token r=null;
        Expression exp0 = null;

        Expression v = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:923:2: (exp0= and_test (r= 'or' v= and_test )* | (exp0= lambdef ) )
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==LPAREN||LA53_0==NAME||(LA53_0>=PLUS && LA53_0<=MINUS)||(LA53_0>=TILDE && LA53_0<=LBRACK)||LA53_0==LCURLY||(LA53_0>=BACKQUOTE && LA53_0<=STRING)||LA53_0==91) ) {
                alt53=1;
            }
            else if ( (LA53_0==93) ) {
                alt53=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("922:1: test returns [ Expression exp = null ] : (exp0= and_test (r= 'or' v= and_test )* | (exp0= lambdef ) );", 53, 0, input);

                throw nvae;
            }
            switch (alt53) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:923:2: exp0= and_test (r= 'or' v= and_test )*
                    {
                    pushFollow(FOLLOW_and_test_in_test3164);
                    exp0=and_test();
                    _fsp--;

                    
                    		exp = exp0;
                    	
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:927:2: (r= 'or' v= and_test )*
                    loop52:
                    do {
                        int alt52=2;
                        int LA52_0 = input.LA(1);

                        if ( (LA52_0==89) ) {
                            alt52=1;
                        }


                        switch (alt52) {
                    	case 1 :
                    	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:928:3: r= 'or' v= and_test
                    	    {
                    	    r=(Token)input.LT(1);
                    	    match(input,89,FOLLOW_89_in_test3178); 
                    	    pushFollow(FOLLOW_and_test_in_test3187);
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
                    break;
                case 2 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:935:3: (exp0= lambdef )
                    {
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:935:3: (exp0= lambdef )
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:935:5: exp0= lambdef
                    {
                    pushFollow(FOLLOW_lambdef_in_test3211);
                    exp0=lambdef();
                    _fsp--;


                    }

                    
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:941:1: and_test returns [ Expression exp = null ] : exp0= not_test (m= 'and' v= not_test )* ;
    public final Expression and_test() throws RecognitionException {
        Expression exp =  null;

        Token m=null;
        Expression exp0 = null;

        Expression v = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:942:2: (exp0= not_test (m= 'and' v= not_test )* )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:942:2: exp0= not_test (m= 'and' v= not_test )*
            {
            pushFollow(FOLLOW_not_test_in_and_test3238);
            exp0=not_test();
            _fsp--;

            
            		exp = exp0;
            	
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:946:2: (m= 'and' v= not_test )*
            loop54:
            do {
                int alt54=2;
                int LA54_0 = input.LA(1);

                if ( (LA54_0==90) ) {
                    alt54=1;
                }


                switch (alt54) {
            	case 1 :
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:947:3: m= 'and' v= not_test
            	    {
            	    m=(Token)input.LT(1);
            	    match(input,90,FOLLOW_90_in_and_test3252); 
            	    pushFollow(FOLLOW_not_test_in_and_test3261);
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:955:1: not_test returns [ Expression exp = null ] : ( (n= 'not' v= not_test ) | exp0= comparison );
    public final Expression not_test() throws RecognitionException {
        Expression exp =  null;

        Token n=null;
        Expression v = null;

        Expression exp0 = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:956:2: ( (n= 'not' v= not_test ) | exp0= comparison )
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==91) ) {
                alt55=1;
            }
            else if ( (LA55_0==LPAREN||LA55_0==NAME||(LA55_0>=PLUS && LA55_0<=MINUS)||(LA55_0>=TILDE && LA55_0<=LBRACK)||LA55_0==LCURLY||(LA55_0>=BACKQUOTE && LA55_0<=STRING)) ) {
                alt55=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("955:1: not_test returns [ Expression exp = null ] : ( (n= 'not' v= not_test ) | exp0= comparison );", 55, 0, input);

                throw nvae;
            }
            switch (alt55) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:956:2: (n= 'not' v= not_test )
                    {
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:956:2: (n= 'not' v= not_test )
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:957:3: n= 'not' v= not_test
                    {
                    n=(Token)input.LT(1);
                    match(input,91,FOLLOW_91_in_not_test3294); 
                    pushFollow(FOLLOW_not_test_in_not_test3303);
                    v=not_test();
                    _fsp--;

                    
                    				exp = new UnaryExpression( toDLTK( n ), Expression.E_LNOT, v );
                    			

                    }


                    }
                    break;
                case 2 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:963:4: exp0= comparison
                    {
                    pushFollow(FOLLOW_comparison_in_not_test3322);
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:966:1: comparison returns [ Expression exp = null ] : exp0= expr (tu= comp_op v= expr )* ;
    public final Expression comparison() throws RecognitionException {
        Expression exp =  null;

        Expression exp0 = null;

        int tu = 0;

        Expression v = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:967:2: (exp0= expr (tu= comp_op v= expr )* )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:967:2: exp0= expr (tu= comp_op v= expr )*
            {
            pushFollow(FOLLOW_expr_in_comparison3345);
            exp0=expr();
            _fsp--;

            
            		exp = exp0;
            	
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:971:2: (tu= comp_op v= expr )*
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( ((LA56_0>=LESS && LA56_0<=NOTEQUAL)||LA56_0==79||(LA56_0>=91 && LA56_0<=92)) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:972:3: tu= comp_op v= expr
            	    {
            	    pushFollow(FOLLOW_comp_op_in_comparison3359);
            	    tu=comp_op();
            	    _fsp--;

            	    pushFollow(FOLLOW_expr_in_comparison3368);
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:980:1: comp_op returns [ int t = Expression.E_EMPTY ] : (t1= LESS | t2= GREATER | t3= EQUAL | t4= GREATEREQUAL | t5= LESSEQUAL | t6= ALT_NOTEQUAL | t7= NOTEQUAL | t8= 'in' | t9= 'not' 'in' | t10= 'is' | t11= 'is' 'not' );
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
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:981:3: (t1= LESS | t2= GREATER | t3= EQUAL | t4= GREATEREQUAL | t5= LESSEQUAL | t6= ALT_NOTEQUAL | t7= NOTEQUAL | t8= 'in' | t9= 'not' 'in' | t10= 'is' | t11= 'is' 'not' )
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
            case 79:
                {
                alt57=8;
                }
                break;
            case 91:
                {
                alt57=9;
                }
                break;
            case 92:
                {
                int LA57_10 = input.LA(2);

                if ( (LA57_10==91) ) {
                    alt57=11;
                }
                else if ( (LA57_10==LPAREN||LA57_10==NAME||(LA57_10>=PLUS && LA57_10<=MINUS)||(LA57_10>=TILDE && LA57_10<=LBRACK)||LA57_10==LCURLY||(LA57_10>=BACKQUOTE && LA57_10<=STRING)) ) {
                    alt57=10;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("980:1: comp_op returns [ int t = Expression.E_EMPTY ] : (t1= LESS | t2= GREATER | t3= EQUAL | t4= GREATEREQUAL | t5= LESSEQUAL | t6= ALT_NOTEQUAL | t7= NOTEQUAL | t8= 'in' | t9= 'not' 'in' | t10= 'is' | t11= 'is' 'not' );", 57, 10, input);

                    throw nvae;
                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("980:1: comp_op returns [ int t = Expression.E_EMPTY ] : (t1= LESS | t2= GREATER | t3= EQUAL | t4= GREATEREQUAL | t5= LESSEQUAL | t6= ALT_NOTEQUAL | t7= NOTEQUAL | t8= 'in' | t9= 'not' 'in' | t10= 'is' | t11= 'is' 'not' );", 57, 0, input);

                throw nvae;
            }

            switch (alt57) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:981:3: t1= LESS
                    {
                    t1=(Token)input.LT(1);
                    match(input,LESS,FOLLOW_LESS_in_comp_op3400); 
                    t = Expression.E_LT;

                    }
                    break;
                case 2 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:982:3: t2= GREATER
                    {
                    t2=(Token)input.LT(1);
                    match(input,GREATER,FOLLOW_GREATER_in_comp_op3412); 
                    t = Expression.E_GT;

                    }
                    break;
                case 3 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:983:3: t3= EQUAL
                    {
                    t3=(Token)input.LT(1);
                    match(input,EQUAL,FOLLOW_EQUAL_in_comp_op3423); 
                    t = Expression.E_EQUAL;

                    }
                    break;
                case 4 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:984:3: t4= GREATEREQUAL
                    {
                    t4=(Token)input.LT(1);
                    match(input,GREATEREQUAL,FOLLOW_GREATEREQUAL_in_comp_op3436); 
                    t = Expression.E_GE;

                    }
                    break;
                case 5 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:985:3: t5= LESSEQUAL
                    {
                    t5=(Token)input.LT(1);
                    match(input,LESSEQUAL,FOLLOW_LESSEQUAL_in_comp_op3446); 
                    t = Expression.E_LE;

                    }
                    break;
                case 6 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:986:3: t6= ALT_NOTEQUAL
                    {
                    t6=(Token)input.LT(1);
                    match(input,ALT_NOTEQUAL,FOLLOW_ALT_NOTEQUAL_in_comp_op3460); 
                    t = Expression.E_NOT_EQUAL2;

                    }
                    break;
                case 7 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:987:3: t7= NOTEQUAL
                    {
                    t7=(Token)input.LT(1);
                    match(input,NOTEQUAL,FOLLOW_NOTEQUAL_in_comp_op3472); 
                    t = Expression.E_NOT_EQUAL;

                    }
                    break;
                case 8 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:988:3: t8= 'in'
                    {
                    t8=(Token)input.LT(1);
                    match(input,79,FOLLOW_79_in_comp_op3487); 
                    t = Expression.E_IN;

                    }
                    break;
                case 9 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:989:3: t9= 'not' 'in'
                    {
                    t9=(Token)input.LT(1);
                    match(input,91,FOLLOW_91_in_comp_op3497); 
                    match(input,79,FOLLOW_79_in_comp_op3499); 
                    t = Expression.E_NOTIN;

                    }
                    break;
                case 10 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:990:3: t10= 'is'
                    {
                    t10=(Token)input.LT(1);
                    match(input,92,FOLLOW_92_in_comp_op3509); 
                    t = Expression.E_IS;

                    }
                    break;
                case 11 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:991:3: t11= 'is' 'not'
                    {
                    t11=(Token)input.LT(1);
                    match(input,92,FOLLOW_92_in_comp_op3519); 
                    match(input,91,FOLLOW_91_in_comp_op3521); 
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:994:1: expr returns [ Expression e = null] : e0= xor_expr (tu= VBAR v= xor_expr )* ;
    public final Expression expr() throws RecognitionException {
        Expression e =  null;

        Token tu=null;
        Expression e0 = null;

        Expression v = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:995:2: (e0= xor_expr (tu= VBAR v= xor_expr )* )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:995:2: e0= xor_expr (tu= VBAR v= xor_expr )*
            {
            pushFollow(FOLLOW_xor_expr_in_expr3543);
            e0=xor_expr();
            _fsp--;

            
            		e = e0;
            	
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:999:2: (tu= VBAR v= xor_expr )*
            loop58:
            do {
                int alt58=2;
                int LA58_0 = input.LA(1);

                if ( (LA58_0==VBAR) ) {
                    alt58=1;
                }


                switch (alt58) {
            	case 1 :
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1000:3: tu= VBAR v= xor_expr
            	    {
            	    tu=(Token)input.LT(1);
            	    match(input,VBAR,FOLLOW_VBAR_in_expr3557); 
            	    pushFollow(FOLLOW_xor_expr_in_expr3567);
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1008:1: xor_expr returns [ Expression e = null ] : e0= and_expr (tu= CIRCUMFLEX v= and_expr )* ;
    public final Expression xor_expr() throws RecognitionException {
        Expression e =  null;

        Token tu=null;
        Expression e0 = null;

        Expression v = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1009:2: (e0= and_expr (tu= CIRCUMFLEX v= and_expr )* )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1009:2: e0= and_expr (tu= CIRCUMFLEX v= and_expr )*
            {
            pushFollow(FOLLOW_and_expr_in_xor_expr3600);
            e0=and_expr();
            _fsp--;

            
            		e = e0;
            	
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1013:2: (tu= CIRCUMFLEX v= and_expr )*
            loop59:
            do {
                int alt59=2;
                int LA59_0 = input.LA(1);

                if ( (LA59_0==CIRCUMFLEX) ) {
                    alt59=1;
                }


                switch (alt59) {
            	case 1 :
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1014:3: tu= CIRCUMFLEX v= and_expr
            	    {
            	    tu=(Token)input.LT(1);
            	    match(input,CIRCUMFLEX,FOLLOW_CIRCUMFLEX_in_xor_expr3614); 
            	    pushFollow(FOLLOW_and_expr_in_xor_expr3623);
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1022:1: and_expr returns [ Expression e = null ] : e0= shift_expr (tu= AMPER v= shift_expr )* ;
    public final Expression and_expr() throws RecognitionException {
        Expression e =  null;

        Token tu=null;
        Expression e0 = null;

        Expression v = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1023:2: (e0= shift_expr (tu= AMPER v= shift_expr )* )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1023:2: e0= shift_expr (tu= AMPER v= shift_expr )*
            {
            pushFollow(FOLLOW_shift_expr_in_and_expr3654);
            e0=shift_expr();
            _fsp--;

            
            		e = e0;
            	
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1027:2: (tu= AMPER v= shift_expr )*
            loop60:
            do {
                int alt60=2;
                int LA60_0 = input.LA(1);

                if ( (LA60_0==AMPER) ) {
                    alt60=1;
                }


                switch (alt60) {
            	case 1 :
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1028:3: tu= AMPER v= shift_expr
            	    {
            	    tu=(Token)input.LT(1);
            	    match(input,AMPER,FOLLOW_AMPER_in_and_expr3669); 
            	    pushFollow(FOLLOW_shift_expr_in_and_expr3679);
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1035:1: shift_expr returns [ Expression e = null ] : e0= arith_expr ( (t1= LEFTSHIFT | t2= RIGHTSHIFT ) v= arith_expr )* ;
    public final Expression shift_expr() throws RecognitionException {
        Expression e =  null;

        Token t1=null;
        Token t2=null;
        Expression e0 = null;

        Expression v = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1036:2: (e0= arith_expr ( (t1= LEFTSHIFT | t2= RIGHTSHIFT ) v= arith_expr )* )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1036:2: e0= arith_expr ( (t1= LEFTSHIFT | t2= RIGHTSHIFT ) v= arith_expr )*
            {
            pushFollow(FOLLOW_arith_expr_in_shift_expr3709);
            e0=arith_expr();
            _fsp--;

            
            		e = e0;
            	
            
            			Token tk = null;
            		
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1043:2: ( (t1= LEFTSHIFT | t2= RIGHTSHIFT ) v= arith_expr )*
            loop62:
            do {
                int alt62=2;
                int LA62_0 = input.LA(1);

                if ( (LA62_0==RIGHTSHIFT||LA62_0==LEFTSHIFT) ) {
                    alt62=1;
                }


                switch (alt62) {
            	case 1 :
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1044:3: (t1= LEFTSHIFT | t2= RIGHTSHIFT ) v= arith_expr
            	    {
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1044:3: (t1= LEFTSHIFT | t2= RIGHTSHIFT )
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
            	            new NoViableAltException("1044:3: (t1= LEFTSHIFT | t2= RIGHTSHIFT )", 61, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt61) {
            	        case 1 :
            	            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1045:4: t1= LEFTSHIFT
            	            {
            	            t1=(Token)input.LT(1);
            	            match(input,LEFTSHIFT,FOLLOW_LEFTSHIFT_in_shift_expr3733); 
            	            
            	            					tk = t1;
            	            				

            	            }
            	            break;
            	        case 2 :
            	            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1050:4: t2= RIGHTSHIFT
            	            {
            	            t2=(Token)input.LT(1);
            	            match(input,RIGHTSHIFT,FOLLOW_RIGHTSHIFT_in_shift_expr3754); 
            	            
            	            					tk = t2;
            	            				

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_arith_expr_in_shift_expr3776);
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1065:1: arith_expr returns [ Expression e = null ] : e0= term ( (t1= PLUS | t2= MINUS ) v= term )* ;
    public final Expression arith_expr() throws RecognitionException {
        Expression e =  null;

        Token t1=null;
        Token t2=null;
        Expression e0 = null;

        Expression v = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1066:2: (e0= term ( (t1= PLUS | t2= MINUS ) v= term )* )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1066:2: e0= term ( (t1= PLUS | t2= MINUS ) v= term )*
            {
            pushFollow(FOLLOW_term_in_arith_expr3812);
            e0=term();
            _fsp--;

            
            		e = e0;
            	
            
            			Token tk = null;
            		
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1073:2: ( (t1= PLUS | t2= MINUS ) v= term )*
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( ((LA64_0>=PLUS && LA64_0<=MINUS)) ) {
                    alt64=1;
                }


                switch (alt64) {
            	case 1 :
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1074:3: (t1= PLUS | t2= MINUS ) v= term
            	    {
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1074:3: (t1= PLUS | t2= MINUS )
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
            	            new NoViableAltException("1074:3: (t1= PLUS | t2= MINUS )", 63, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt63) {
            	        case 1 :
            	            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1075:4: t1= PLUS
            	            {
            	            t1=(Token)input.LT(1);
            	            match(input,PLUS,FOLLOW_PLUS_in_arith_expr3836); 
            	            
            	            					tk = t1;
            	            				

            	            }
            	            break;
            	        case 2 :
            	            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1080:4: t2= MINUS
            	            {
            	            t2=(Token)input.LT(1);
            	            match(input,MINUS,FOLLOW_MINUS_in_arith_expr3856); 
            	             
            	            					tk = t2;
            	            				

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_term_in_arith_expr3875);
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1095:1: term returns [ Expression e = null] : e0= factor ( (t1= STAR | t2= SLASH | t3= PERCENT | t4= DOUBLESLASH ) v= factor )* ;
    public final Expression term() throws RecognitionException {
        Expression e =  null;

        Token t1=null;
        Token t2=null;
        Token t3=null;
        Token t4=null;
        Expression e0 = null;

        Expression v = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1096:2: (e0= factor ( (t1= STAR | t2= SLASH | t3= PERCENT | t4= DOUBLESLASH ) v= factor )* )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1096:2: e0= factor ( (t1= STAR | t2= SLASH | t3= PERCENT | t4= DOUBLESLASH ) v= factor )*
            {
            pushFollow(FOLLOW_factor_in_term3905);
            e0=factor();
            _fsp--;

            
            		e = e0;
            	
            
            			int type = Expression.E_EMPTY;
            		
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1103:2: ( (t1= STAR | t2= SLASH | t3= PERCENT | t4= DOUBLESLASH ) v= factor )*
            loop66:
            do {
                int alt66=2;
                int LA66_0 = input.LA(1);

                if ( (LA66_0==STAR||(LA66_0>=SLASH && LA66_0<=DOUBLESLASH)) ) {
                    alt66=1;
                }


                switch (alt66) {
            	case 1 :
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1104:3: (t1= STAR | t2= SLASH | t3= PERCENT | t4= DOUBLESLASH ) v= factor
            	    {
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1104:3: (t1= STAR | t2= SLASH | t3= PERCENT | t4= DOUBLESLASH )
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
            	            new NoViableAltException("1104:3: (t1= STAR | t2= SLASH | t3= PERCENT | t4= DOUBLESLASH )", 65, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt65) {
            	        case 1 :
            	            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1105:5: t1= STAR
            	            {
            	            t1=(Token)input.LT(1);
            	            match(input,STAR,FOLLOW_STAR_in_term3929); 
            	            
            	            	  			type = Expression.E_MULT;
            	            	  		

            	            }
            	            break;
            	        case 2 :
            	            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1110:5: t2= SLASH
            	            {
            	            t2=(Token)input.LT(1);
            	            match(input,SLASH,FOLLOW_SLASH_in_term3953); 
            	            
            	            	  			type = Expression.E_DIV;
            	            	  		

            	            }
            	            break;
            	        case 3 :
            	            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1115:6: t3= PERCENT
            	            {
            	            t3=(Token)input.LT(1);
            	            match(input,PERCENT,FOLLOW_PERCENT_in_term3978); 
            	            
            	            	   			type = Expression.E_MOD;
            	            	   		

            	            }
            	            break;
            	        case 4 :
            	            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1120:7: t4= DOUBLESLASH
            	            {
            	            t4=(Token)input.LT(1);
            	            match(input,DOUBLESLASH,FOLLOW_DOUBLESLASH_in_term4006); 
            	            
            	            	    			type = Expression.E_MOD;
            	            	    		

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_factor_in_term4032);
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1132:1: factor returns [ Expression e = null ] : ( (t1= PLUS | t2= MINUS | t3= TILDE ) e0= factor | e0= power );
    public final Expression factor() throws RecognitionException {
        Expression e =  null;

        Token t1=null;
        Token t2=null;
        Token t3=null;
        Expression e0 = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1133:4: ( (t1= PLUS | t2= MINUS | t3= TILDE ) e0= factor | e0= power )
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
                    new NoViableAltException("1132:1: factor returns [ Expression e = null ] : ( (t1= PLUS | t2= MINUS | t3= TILDE ) e0= factor | e0= power );", 68, 0, input);

                throw nvae;
            }
            switch (alt68) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1133:4: (t1= PLUS | t2= MINUS | t3= TILDE ) e0= factor
                    {
                    
                    	  	Token tk = null;
                    	  
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1136:4: (t1= PLUS | t2= MINUS | t3= TILDE )
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
                            new NoViableAltException("1136:4: (t1= PLUS | t2= MINUS | t3= TILDE )", 67, 0, input);

                        throw nvae;
                    }

                    switch (alt67) {
                        case 1 :
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1137:5: t1= PLUS
                            {
                            t1=(Token)input.LT(1);
                            match(input,PLUS,FOLLOW_PLUS_in_factor4078); 
                            
                            	  			tk = t1;
                            	  		

                            }
                            break;
                        case 2 :
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1142:5: t2= MINUS
                            {
                            t2=(Token)input.LT(1);
                            match(input,MINUS,FOLLOW_MINUS_in_factor4101); 
                            
                            	  			tk = t2;
                            	  		

                            }
                            break;
                        case 3 :
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1147:5: t3= TILDE
                            {
                            t3=(Token)input.LT(1);
                            match(input,TILDE,FOLLOW_TILDE_in_factor4123); 
                            
                            	  			tk = t3;
                            	  		

                            }
                            break;

                    }

                    pushFollow(FOLLOW_factor_in_factor4145);
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
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1168:2: e0= power
                    {
                    pushFollow(FOLLOW_power_in_factor4162);
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1173:1: power returns [ Expression exp = null; ] : (exp0= atom (ex= trailer[ exp ] )* ( options {greedy=true; } : DOUBLESTAR expr0= factor )? ) ;
    public final Expression power() throws RecognitionException {
        Expression exp =  null;;

        Expression exp0 = null;

        Expression ex = null;

        Expression expr0 = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1174:3: ( (exp0= atom (ex= trailer[ exp ] )* ( options {greedy=true; } : DOUBLESTAR expr0= factor )? ) )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1174:3: (exp0= atom (ex= trailer[ exp ] )* ( options {greedy=true; } : DOUBLESTAR expr0= factor )? )
            {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1174:3: (exp0= atom (ex= trailer[ exp ] )* ( options {greedy=true; } : DOUBLESTAR expr0= factor )? )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1175:3: exp0= atom (ex= trailer[ exp ] )* ( options {greedy=true; } : DOUBLESTAR expr0= factor )?
            {
            pushFollow(FOLLOW_atom_in_power4189);
            exp0=atom();
            _fsp--;

            
            			//Expression ex = exp;
            			exp = exp0;
            		
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1180:3: (ex= trailer[ exp ] )*
            loop69:
            do {
                int alt69=2;
                int LA69_0 = input.LA(1);

                if ( (LA69_0==LPAREN||LA69_0==DOT||LA69_0==LBRACK) ) {
                    alt69=1;
                }


                switch (alt69) {
            	case 1 :
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1181:4: ex= trailer[ exp ]
            	    {
            	    pushFollow(FOLLOW_trailer_in_power4207);
            	    ex=trailer( exp );
            	    _fsp--;

            	    
            	    				exp = ex;
            	    			

            	    }
            	    break;

            	default :
            	    break loop69;
                }
            } while (true);

            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1186:5: ( options {greedy=true; } : DOUBLESTAR expr0= factor )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==DOUBLESTAR) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1187:29: DOUBLESTAR expr0= factor
                    {
                    match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_power4240); 
                    pushFollow(FOLLOW_factor_in_power4258);
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1202:1: trailer[ Expression expr ] returns [ Expression returnExpression = null ] : (lp0= LPAREN (k= arglist )? rp0= RPAREN | lb1= LBRACK k= subscriptlist rb1= RBRACK | DOT ta= NAME ) ;
    public final Expression trailer(Expression expr) throws RecognitionException {
        Expression returnExpression =  null;

        Token lp0=null;
        Token rp0=null;
        Token lb1=null;
        Token rb1=null;
        Token ta=null;
        Expression k = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1203:2: ( (lp0= LPAREN (k= arglist )? rp0= RPAREN | lb1= LBRACK k= subscriptlist rb1= RBRACK | DOT ta= NAME ) )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1203:2: (lp0= LPAREN (k= arglist )? rp0= RPAREN | lb1= LBRACK k= subscriptlist rb1= RBRACK | DOT ta= NAME )
            {
            
            		//Expression k=null;
            		// Create extended variable reference.
            		if( !( expr instanceof ExtendedVariableReference ) )
            			expr = new ExtendedVariableReference( expr );
            		ExtendedVariableReference exVariableReference = ( ExtendedVariableReference )expr;
            	
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1210:2: (lp0= LPAREN (k= arglist )? rp0= RPAREN | lb1= LBRACK k= subscriptlist rb1= RBRACK | DOT ta= NAME )
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
                    new NoViableAltException("1210:2: (lp0= LPAREN (k= arglist )? rp0= RPAREN | lb1= LBRACK k= subscriptlist rb1= RBRACK | DOT ta= NAME )", 72, 0, input);

                throw nvae;
            }

            switch (alt72) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1211:3: lp0= LPAREN (k= arglist )? rp0= RPAREN
                    {
                    lp0=(Token)input.LT(1);
                    match(input,LPAREN,FOLLOW_LPAREN_in_trailer4306); 
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1212:4: (k= arglist )?
                    int alt71=2;
                    int LA71_0 = input.LA(1);

                    if ( (LA71_0==LPAREN||LA71_0==NAME||(LA71_0>=STAR && LA71_0<=DOUBLESTAR)||(LA71_0>=PLUS && LA71_0<=MINUS)||(LA71_0>=TILDE && LA71_0<=LBRACK)||LA71_0==LCURLY||(LA71_0>=BACKQUOTE && LA71_0<=STRING)||LA71_0==91||LA71_0==93) ) {
                        alt71=1;
                    }
                    switch (alt71) {
                        case 1 :
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1212:6: k= arglist
                            {
                            pushFollow(FOLLOW_arglist_in_trailer4318);
                            k=arglist();
                            _fsp--;


                            }
                            break;

                    }

                    rp0=(Token)input.LT(1);
                    match(input,RPAREN,FOLLOW_RPAREN_in_trailer4330); 
                    
                    				// This is Call lets' create it
                    				if( k == null )
                    					k = new EmptyExpression();
                    				exVariableReference.addExpression( new CallHolder( toDLTK(lp0), toDLTK(rp0), k ) );
                    				returnExpression = exVariableReference;
                    			

                    }
                    break;
                case 2 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1222:3: lb1= LBRACK k= subscriptlist rb1= RBRACK
                    {
                    lb1=(Token)input.LT(1);
                    match(input,LBRACK,FOLLOW_LBRACK_in_trailer4348); 
                    pushFollow(FOLLOW_subscriptlist_in_trailer4357);
                    k=subscriptlist();
                    _fsp--;

                    rb1=(Token)input.LT(1);
                    match(input,RBRACK,FOLLOW_RBRACK_in_trailer4366); 
                    
                    				// This is subscript lets return it.
                    				//a = new PythonSubscriptAppender(k);
                    				//returnExpression = ExpressionConverter.getIndexed( expr, k );
                    				exVariableReference.addExpression( new IndexHolder( toDLTK(lb1), toDLTK(rb1), k ) );
                    				returnExpression = exVariableReference;
                    			

                    }
                    break;
                case 3 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1233:3: DOT ta= NAME
                    {
                    match(input,DOT,FOLLOW_DOT_in_trailer4381); 
                    ta=(Token)input.LT(1);
                    match(input,NAME,FOLLOW_NAME_in_trailer4389); 
                    
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1245:1: atom returns [ Expression exp = null ] : ( LPAREN (exp0= tuplelist ) RPAREN | LPAREN RPAREN | LBRACK (exp0= listmaker ) RBRACK | LBRACK RBRACK | LCURLY (exp0= dictmaker ) RCURLY | LCURLY RCURLY | BACKQUOTE exp0= testlist BACKQUOTE | n= NAME | i= INT | li= LONGINT | f= FLOAT | c= COMPLEX | (s= STRING )+ );
    public final Expression atom() throws RecognitionException {
        Expression exp =  null;

        Token n=null;
        Token i=null;
        Token li=null;
        Token f=null;
        Token c=null;
        Token s=null;
        Expression exp0 = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1246:4: ( LPAREN (exp0= tuplelist ) RPAREN | LPAREN RPAREN | LBRACK (exp0= listmaker ) RBRACK | LBRACK RBRACK | LCURLY (exp0= dictmaker ) RCURLY | LCURLY RCURLY | BACKQUOTE exp0= testlist BACKQUOTE | n= NAME | i= INT | li= LONGINT | f= FLOAT | c= COMPLEX | (s= STRING )+ )
            int alt74=13;
            switch ( input.LA(1) ) {
            case LPAREN:
                {
                int LA74_1 = input.LA(2);

                if ( (LA74_1==RPAREN) ) {
                    alt74=2;
                }
                else if ( (LA74_1==LPAREN||LA74_1==NAME||(LA74_1>=PLUS && LA74_1<=MINUS)||(LA74_1>=TILDE && LA74_1<=LBRACK)||LA74_1==LCURLY||(LA74_1>=BACKQUOTE && LA74_1<=STRING)||LA74_1==91||LA74_1==93) ) {
                    alt74=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1245:1: atom returns [ Expression exp = null ] : ( LPAREN (exp0= tuplelist ) RPAREN | LPAREN RPAREN | LBRACK (exp0= listmaker ) RBRACK | LBRACK RBRACK | LCURLY (exp0= dictmaker ) RCURLY | LCURLY RCURLY | BACKQUOTE exp0= testlist BACKQUOTE | n= NAME | i= INT | li= LONGINT | f= FLOAT | c= COMPLEX | (s= STRING )+ );", 74, 1, input);

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
                else if ( (LA74_2==LPAREN||LA74_2==NAME||(LA74_2>=PLUS && LA74_2<=MINUS)||(LA74_2>=TILDE && LA74_2<=LBRACK)||LA74_2==LCURLY||(LA74_2>=BACKQUOTE && LA74_2<=STRING)||LA74_2==91||LA74_2==93) ) {
                    alt74=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1245:1: atom returns [ Expression exp = null ] : ( LPAREN (exp0= tuplelist ) RPAREN | LPAREN RPAREN | LBRACK (exp0= listmaker ) RBRACK | LBRACK RBRACK | LCURLY (exp0= dictmaker ) RCURLY | LCURLY RCURLY | BACKQUOTE exp0= testlist BACKQUOTE | n= NAME | i= INT | li= LONGINT | f= FLOAT | c= COMPLEX | (s= STRING )+ );", 74, 2, input);

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
                else if ( (LA74_3==LPAREN||LA74_3==NAME||(LA74_3>=PLUS && LA74_3<=MINUS)||(LA74_3>=TILDE && LA74_3<=LBRACK)||LA74_3==LCURLY||(LA74_3>=BACKQUOTE && LA74_3<=STRING)||LA74_3==91||LA74_3==93) ) {
                    alt74=5;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1245:1: atom returns [ Expression exp = null ] : ( LPAREN (exp0= tuplelist ) RPAREN | LPAREN RPAREN | LBRACK (exp0= listmaker ) RBRACK | LBRACK RBRACK | LCURLY (exp0= dictmaker ) RCURLY | LCURLY RCURLY | BACKQUOTE exp0= testlist BACKQUOTE | n= NAME | i= INT | li= LONGINT | f= FLOAT | c= COMPLEX | (s= STRING )+ );", 74, 3, input);

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
                    new NoViableAltException("1245:1: atom returns [ Expression exp = null ] : ( LPAREN (exp0= tuplelist ) RPAREN | LPAREN RPAREN | LBRACK (exp0= listmaker ) RBRACK | LBRACK RBRACK | LCURLY (exp0= dictmaker ) RCURLY | LCURLY RCURLY | BACKQUOTE exp0= testlist BACKQUOTE | n= NAME | i= INT | li= LONGINT | f= FLOAT | c= COMPLEX | (s= STRING )+ );", 74, 0, input);

                throw nvae;
            }

            switch (alt74) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1246:4: LPAREN (exp0= tuplelist ) RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_atom4415); 
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1246:11: (exp0= tuplelist )
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1246:13: exp0= tuplelist
                    {
                    pushFollow(FOLLOW_tuplelist_in_atom4423);
                    exp0=tuplelist();
                    _fsp--;

                     exp = exp0;

                    }

                    match(input,RPAREN,FOLLOW_RPAREN_in_atom4429); 

                    }
                    break;
                case 2 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1247:4: LPAREN RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_atom4434); 
                     exp = new PythonTupleExpression(); 
                    match(input,RPAREN,FOLLOW_RPAREN_in_atom4438); 

                    }
                    break;
                case 3 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1248:4: LBRACK (exp0= listmaker ) RBRACK
                    {
                    match(input,LBRACK,FOLLOW_LBRACK_in_atom4445); 
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1248:11: (exp0= listmaker )
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1248:13: exp0= listmaker
                    {
                    pushFollow(FOLLOW_listmaker_in_atom4453);
                    exp0=listmaker();
                    _fsp--;

                    exp = exp0; 

                    }

                    match(input,RBRACK,FOLLOW_RBRACK_in_atom4459); 

                    }
                    break;
                case 4 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1249:4: LBRACK RBRACK
                    {
                    match(input,LBRACK,FOLLOW_LBRACK_in_atom4464); 
                     exp = new PythonListExpression( ); 
                    match(input,RBRACK,FOLLOW_RBRACK_in_atom4468); 

                    }
                    break;
                case 5 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1250:4: LCURLY (exp0= dictmaker ) RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_atom4474); 
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1250:11: (exp0= dictmaker )
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1250:13: exp0= dictmaker
                    {
                    pushFollow(FOLLOW_dictmaker_in_atom4482);
                    exp0=dictmaker();
                    _fsp--;

                     exp = exp0; 

                    }

                    match(input,RCURLY,FOLLOW_RCURLY_in_atom4488); 

                    }
                    break;
                case 6 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1251:4: LCURLY RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_atom4493); 
                     exp = new PythonDictExpression(); 
                    match(input,RCURLY,FOLLOW_RCURLY_in_atom4497); 

                    }
                    break;
                case 7 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1252:4: BACKQUOTE exp0= testlist BACKQUOTE
                    {
                    match(input,BACKQUOTE,FOLLOW_BACKQUOTE_in_atom4503); 
                    pushFollow(FOLLOW_testlist_in_atom4509);
                    exp0=testlist();
                    _fsp--;

                     exp = exp0; 
                    match(input,BACKQUOTE,FOLLOW_BACKQUOTE_in_atom4513); 

                    }
                    break;
                case 8 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1253:4: n= NAME
                    {
                    n=(Token)input.LT(1);
                    match(input,NAME,FOLLOW_NAME_in_atom4521); 
                     exp = new VariableReference( toDLTK( n ) ); 

                    }
                    break;
                case 9 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1254:4: i= INT
                    {
                    i=(Token)input.LT(1);
                    match(input,INT,FOLLOW_INT_in_atom4533); 
                     exp = new NumericLiteral( toDLTK( i ) );

                    }
                    break;
                case 10 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1255:8: li= LONGINT
                    {
                    li=(Token)input.LT(1);
                    match(input,LONGINT,FOLLOW_LONGINT_in_atom4550); 
                     exp=new NumericLiteral( toDLTK( li ) );

                    }
                    break;
                case 11 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1256:8: f= FLOAT
                    {
                    f=(Token)input.LT(1);
                    match(input,FLOAT,FOLLOW_FLOAT_in_atom4565); 
                     exp=new NumericLiteral( toDLTK( f ) );

                    }
                    break;
                case 12 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1257:8: c= COMPLEX
                    {
                    c=(Token)input.LT(1);
                    match(input,COMPLEX,FOLLOW_COMPLEX_in_atom4583); 
                     exp=new ComplexNumericLiteral( toDLTK( c ) ); 

                    }
                    break;
                case 13 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1259:2: (s= STRING )+
                    {
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1259:2: (s= STRING )+
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
                    	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1260:3: s= STRING
                    	    {
                    	    s=(Token)input.LT(1);
                    	    match(input,STRING,FOLLOW_STRING_in_atom4600); 
                    	     
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1270:1: listmaker returns [ PythonListExpression exp = new PythonListExpression() ] : firstExpr= test ( ( list_for[ listExpr ] ) | ( ( options {greedy=true; } : COMMA expr0= test )* ( COMMA )? ) ) ;
    public final PythonListExpression listmaker() throws RecognitionException {
        PythonListExpression exp =  new PythonListExpression();

        Expression firstExpr = null;

        Expression expr0 = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1271:2: (firstExpr= test ( ( list_for[ listExpr ] ) | ( ( options {greedy=true; } : COMMA expr0= test )* ( COMMA )? ) ) )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1271:2: firstExpr= test ( ( list_for[ listExpr ] ) | ( ( options {greedy=true; } : COMMA expr0= test )* ( COMMA )? ) )
            {
            pushFollow(FOLLOW_test_in_listmaker4630);
            firstExpr=test();
            _fsp--;

            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1272:2: ( ( list_for[ listExpr ] ) | ( ( options {greedy=true; } : COMMA expr0= test )* ( COMMA )? ) )
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==85) ) {
                alt77=1;
            }
            else if ( (LA77_0==COMMA||LA77_0==RBRACK) ) {
                alt77=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1272:2: ( ( list_for[ listExpr ] ) | ( ( options {greedy=true; } : COMMA expr0= test )* ( COMMA )? ) )", 77, 0, input);

                throw nvae;
            }
            switch (alt77) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1273:3: ( list_for[ listExpr ] )
                    {
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1273:3: ( list_for[ listExpr ] )
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1274:4: list_for[ listExpr ]
                    {
                    
                    				PythonListForExpression listExpr = new PythonListForExpression( firstExpr );
                    			
                    pushFollow(FOLLOW_list_for_in_listmaker4647);
                    list_for( listExpr );
                    _fsp--;

                    
                    				exp.addExpression( listExpr );
                    			

                    }


                    }
                    break;
                case 2 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1283:3: ( ( options {greedy=true; } : COMMA expr0= test )* ( COMMA )? )
                    {
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1283:3: ( ( options {greedy=true; } : COMMA expr0= test )* ( COMMA )? )
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1284:4: ( options {greedy=true; } : COMMA expr0= test )* ( COMMA )?
                    {
                    
                    				exp.addExpression( firstExpr );
                    			
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1287:4: ( options {greedy=true; } : COMMA expr0= test )*
                    loop75:
                    do {
                        int alt75=2;
                        int LA75_0 = input.LA(1);

                        if ( (LA75_0==COMMA) ) {
                            int LA75_1 = input.LA(2);

                            if ( (LA75_1==LPAREN||LA75_1==NAME||(LA75_1>=PLUS && LA75_1<=MINUS)||(LA75_1>=TILDE && LA75_1<=LBRACK)||LA75_1==LCURLY||(LA75_1>=BACKQUOTE && LA75_1<=STRING)||LA75_1==91||LA75_1==93) ) {
                                alt75=1;
                            }


                        }


                        switch (alt75) {
                    	case 1 :
                    	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1289:5: COMMA expr0= test
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_listmaker4695); 
                    	    pushFollow(FOLLOW_test_in_listmaker4705);
                    	    expr0=test();
                    	    _fsp--;

                    	    
                    	    					exp.addExpression( expr0 );
                    	    				

                    	    }
                    	    break;

                    	default :
                    	    break loop75;
                        }
                    } while (true);

                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1295:4: ( COMMA )?
                    int alt76=2;
                    int LA76_0 = input.LA(1);

                    if ( (LA76_0==COMMA) ) {
                        alt76=1;
                    }
                    switch (alt76) {
                        case 1 :
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1295:5: COMMA
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_listmaker4724); 

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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1301:1: list_for[PythonListForExpression list ] : ( 'for' expr1= exprlist 'in' expr2= testlist (expr0= list_if )* )+ ;
    public final void list_for(PythonListForExpression list) throws RecognitionException {
        PythonTestListExpression expr1 = null;

        Expression expr2 = null;

        Expression expr0 = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1302:2: ( ( 'for' expr1= exprlist 'in' expr2= testlist (expr0= list_if )* )+ )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1302:2: ( 'for' expr1= exprlist 'in' expr2= testlist (expr0= list_if )* )+
            {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1302:2: ( 'for' expr1= exprlist 'in' expr2= testlist (expr0= list_if )* )+
            int cnt79=0;
            loop79:
            do {
                int alt79=2;
                int LA79_0 = input.LA(1);

                if ( (LA79_0==85) ) {
                    alt79=1;
                }


                switch (alt79) {
            	case 1 :
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1303:3: 'for' expr1= exprlist 'in' expr2= testlist (expr0= list_if )*
            	    {
            	    match(input,85,FOLLOW_85_in_list_for4751); 
            	    pushFollow(FOLLOW_exprlist_in_list_for4759);
            	    expr1=exprlist();
            	    _fsp--;

            	    match(input,79,FOLLOW_79_in_list_for4763); 
            	    pushFollow(FOLLOW_testlist_in_list_for4772);
            	    expr2=testlist();
            	    _fsp--;

            	    
            	    			PythonForListExpression forListExpression = new PythonForListExpression( expr1, expr2 );
            	    			list.addExpression( forListExpression );
            	    		
            	    
            	    			PythonListExpression ifList = null;
            	    		
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1314:3: (expr0= list_if )*
            	    loop78:
            	    do {
            	        int alt78=2;
            	        int LA78_0 = input.LA(1);

            	        if ( (LA78_0==81) ) {
            	            alt78=1;
            	        }


            	        switch (alt78) {
            	    	case 1 :
            	    	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1315:4: expr0= list_if
            	    	    {
            	    	    pushFollow(FOLLOW_list_if_in_list_for4795);
            	    	    expr0=list_if();
            	    	    _fsp--;

            	    	    
            	    	    				if( ifList == null )
            	    	    					ifList = new PythonListExpression();
            	    	    				ifList.addExpression( expr0 );
            	    	    			

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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1329:1: list_if returns [ Expression expr = null ] : 'if' expr0= test ;
    public final Expression list_if() throws RecognitionException {
        Expression expr =  null;

        Expression expr0 = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1330:2: ( 'if' expr0= test )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1330:2: 'if' expr0= test
            {
            match(input,81,FOLLOW_81_in_list_if4828); 
            pushFollow(FOLLOW_test_in_list_if4835);
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1335:1: lambdef returns [ Expression e = null ] : lambda= 'lambda' ( varargslist[ params ] )? COLON statement= test ;
    public final Expression lambdef() throws RecognitionException {
        Expression e =  null;

        Token lambda=null;
        Expression statement = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1336:2: (lambda= 'lambda' ( varargslist[ params ] )? COLON statement= test )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1336:2: lambda= 'lambda' ( varargslist[ params ] )? COLON statement= test
            {
            lambda=(Token)input.LT(1);
            match(input,93,FOLLOW_93_in_lambdef4856); 
            
            		//ArrayList buf = this.fParameters;
            		//this.fParameters = new ArrayList();
            		List params = new ArrayList ();
            		//Expression statement;
            	
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1343:2: ( varargslist[ params ] )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==LPAREN||LA80_0==NAME||(LA80_0>=STAR && LA80_0<=DOUBLESTAR)) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1343:4: varargslist[ params ]
                    {
                    pushFollow(FOLLOW_varargslist_in_lambdef4864);
                    varargslist( params );
                    _fsp--;


                    }
                    break;

            }

            match(input,COLON,FOLLOW_COLON_in_lambdef4871); 
            pushFollow(FOLLOW_test_in_lambdef4879);
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1354:1: subscriptlist returns [ Expression expression = null ] : expression0= subscript ( options {greedy=true; } : COMMA k= subscript )* ( COMMA )? ;
    public final Expression subscriptlist() throws RecognitionException {
        Expression expression =  null;

        PythonSubscriptExpression expression0 = null;

        PythonSubscriptExpression k = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1355:2: (expression0= subscript ( options {greedy=true; } : COMMA k= subscript )* ( COMMA )? )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1355:2: expression0= subscript ( options {greedy=true; } : COMMA k= subscript )* ( COMMA )?
            {
            pushFollow(FOLLOW_subscript_in_subscriptlist4903);
            expression0=subscript();
            _fsp--;

            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1356:2: ( options {greedy=true; } : COMMA k= subscript )*
            loop81:
            do {
                int alt81=2;
                int LA81_0 = input.LA(1);

                if ( (LA81_0==COMMA) ) {
                    int LA81_1 = input.LA(2);

                    if ( (LA81_1==LPAREN||(LA81_1>=NAME && LA81_1<=COLON)||LA81_1==DOT||(LA81_1>=PLUS && LA81_1<=MINUS)||(LA81_1>=TILDE && LA81_1<=LBRACK)||LA81_1==LCURLY||(LA81_1>=BACKQUOTE && LA81_1<=STRING)||LA81_1==91||LA81_1==93) ) {
                        alt81=1;
                    }


                }


                switch (alt81) {
            	case 1 :
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1357:26: COMMA k= subscript
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_subscriptlist4919); 
            	    pushFollow(FOLLOW_subscript_in_subscriptlist4928);
            	    k=subscript();
            	    _fsp--;

            	    
            	    				expression = new BinaryExpression( expression0, Expression.E_COMMA, k );
            	    			

            	    }
            	    break;

            	default :
            	    break loop81;
                }
            } while (true);

            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1363:2: ( COMMA )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==COMMA) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1363:3: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_subscriptlist4943); 

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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1366:1: subscript returns [ PythonSubscriptExpression expression = null ] : (w= DOT DOT DOT | (tu= test ( COLON (tu1= test )? (tu= sliceop )? )? ) | ( COLON (tu1= test )? (tu= sliceop )? ) );
    public final PythonSubscriptExpression subscript() throws RecognitionException {
        PythonSubscriptExpression expression =  null;

        Token w=null;
        Expression tu = null;

        Expression tu1 = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1367:2: (w= DOT DOT DOT | (tu= test ( COLON (tu1= test )? (tu= sliceop )? )? ) | ( COLON (tu1= test )? (tu= sliceop )? ) )
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
            case 91:
            case 93:
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
                    new NoViableAltException("1366:1: subscript returns [ PythonSubscriptExpression expression = null ] : (w= DOT DOT DOT | (tu= test ( COLON (tu1= test )? (tu= sliceop )? )? ) | ( COLON (tu1= test )? (tu= sliceop )? ) );", 88, 0, input);

                throw nvae;
            }

            switch (alt88) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1367:2: w= DOT DOT DOT
                    {
                    w=(Token)input.LT(1);
                    match(input,DOT,FOLLOW_DOT_in_subscript4967); 
                    match(input,DOT,FOLLOW_DOT_in_subscript4969); 
                    match(input,DOT,FOLLOW_DOT_in_subscript4971); 
                    
                    			expression = new PythonSubscriptExpression( toDLTK( w ) );
                    		

                    }
                    break;
                case 2 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1373:2: (tu= test ( COLON (tu1= test )? (tu= sliceop )? )? )
                    {
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1373:2: (tu= test ( COLON (tu1= test )? (tu= sliceop )? )? )
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1374:3: tu= test ( COLON (tu1= test )? (tu= sliceop )? )?
                    {
                    
                    			expression = new PythonSubscriptExpression( );	
                    		
                    pushFollow(FOLLOW_test_in_subscript4996);
                    tu=test();
                    _fsp--;

                    
                    				expression.setTest( tu );
                    			
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1381:3: ( COLON (tu1= test )? (tu= sliceop )? )?
                    int alt85=2;
                    int LA85_0 = input.LA(1);

                    if ( (LA85_0==COLON) ) {
                        alt85=1;
                    }
                    switch (alt85) {
                        case 1 :
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1382:4: COLON (tu1= test )? (tu= sliceop )?
                            {
                            match(input,COLON,FOLLOW_COLON_in_subscript5011); 
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1383:4: (tu1= test )?
                            int alt83=2;
                            int LA83_0 = input.LA(1);

                            if ( (LA83_0==LPAREN||LA83_0==NAME||(LA83_0>=PLUS && LA83_0<=MINUS)||(LA83_0>=TILDE && LA83_0<=LBRACK)||LA83_0==LCURLY||(LA83_0>=BACKQUOTE && LA83_0<=STRING)||LA83_0==91||LA83_0==93) ) {
                                alt83=1;
                            }
                            switch (alt83) {
                                case 1 :
                                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1384:5: tu1= test
                                    {
                                    pushFollow(FOLLOW_test_in_subscript5027);
                                    tu1=test();
                                    _fsp--;

                                    
                                    						expression.setCondition( tu1 );
                                    					

                                    }
                                    break;

                            }

                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1389:4: (tu= sliceop )?
                            int alt84=2;
                            int LA84_0 = input.LA(1);

                            if ( (LA84_0==COLON) ) {
                                alt84=1;
                            }
                            switch (alt84) {
                                case 1 :
                                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1390:5: tu= sliceop
                                    {
                                    pushFollow(FOLLOW_sliceop_in_subscript5057);
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
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1398:2: ( COLON (tu1= test )? (tu= sliceop )? )
                    {
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1398:2: ( COLON (tu1= test )? (tu= sliceop )? )
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1399:3: COLON (tu1= test )? (tu= sliceop )?
                    {
                    
                    			expression = new PythonSubscriptExpression( );
                    		
                    match(input,COLON,FOLLOW_COLON_in_subscript5099); 
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1403:8: (tu1= test )?
                    int alt86=2;
                    int LA86_0 = input.LA(1);

                    if ( (LA86_0==LPAREN||LA86_0==NAME||(LA86_0>=PLUS && LA86_0<=MINUS)||(LA86_0>=TILDE && LA86_0<=LBRACK)||LA86_0==LCURLY||(LA86_0>=BACKQUOTE && LA86_0<=STRING)||LA86_0==91||LA86_0==93) ) {
                        alt86=1;
                    }
                    switch (alt86) {
                        case 1 :
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1404:9: tu1= test
                            {
                            pushFollow(FOLLOW_test_in_subscript5123);
                            tu1=test();
                            _fsp--;

                            
                                						expression.setCondition( tu1 );
                                					

                            }
                            break;

                    }

                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1409:8: (tu= sliceop )?
                    int alt87=2;
                    int LA87_0 = input.LA(1);

                    if ( (LA87_0==COLON) ) {
                        alt87=1;
                    }
                    switch (alt87) {
                        case 1 :
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1410:9: tu= sliceop
                            {
                            pushFollow(FOLLOW_sliceop_in_subscript5169);
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1420:1: sliceop returns [ Expression e = null ] : COLON (e0= test )? ;
    public final Expression sliceop() throws RecognitionException {
        Expression e =  null;

        Expression e0 = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1421:2: ( COLON (e0= test )? )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1421:2: COLON (e0= test )?
            {
            match(input,COLON,FOLLOW_COLON_in_sliceop5229); 
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1422:2: (e0= test )?
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==LPAREN||LA89_0==NAME||(LA89_0>=PLUS && LA89_0<=MINUS)||(LA89_0>=TILDE && LA89_0<=LBRACK)||LA89_0==LCURLY||(LA89_0>=BACKQUOTE && LA89_0<=STRING)||LA89_0==91||LA89_0==93) ) {
                alt89=1;
            }
            switch (alt89) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1422:4: e0= test
                    {
                    pushFollow(FOLLOW_test_in_sliceop5238);
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1426:1: exprlist returns [ PythonTestListExpression p = new PythonTestListExpression( ); ] : e= expr ( options {k=2; greedy=true; } : COMMA e= expr )* ( COMMA )? ;
    public final PythonTestListExpression exprlist() throws RecognitionException {
        PythonTestListExpression p =  new PythonTestListExpression( );;

        Expression e = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1427:2: (e= expr ( options {k=2; greedy=true; } : COMMA e= expr )* ( COMMA )? )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1427:2: e= expr ( options {k=2; greedy=true; } : COMMA e= expr )* ( COMMA )?
            {
            pushFollow(FOLLOW_expr_in_exprlist5265);
            e=expr();
            _fsp--;

            
            		p.addExpression( e );
            		p.setStart(e.sourceStart());
            		p.setEnd(e.sourceEnd());
            	
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1433:2: ( options {k=2; greedy=true; } : COMMA e= expr )*
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
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1434:3: COMMA e= expr
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_exprlist5287); 
            	    pushFollow(FOLLOW_expr_in_exprlist5296);
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

            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1443:2: ( COMMA )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==COMMA) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1443:3: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_exprlist5310); 

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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1447:1: testlist returns [ Expression p = new EmptyExpression() ] : e0= test ( options {k=2; greedy=true; } : COMMA e0= test )* ( options {greedy=true; } : COMMA )? ;
    public final Expression testlist() throws RecognitionException {
        Expression p =  new EmptyExpression();

        Expression e0 = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1448:2: (e0= test ( options {k=2; greedy=true; } : COMMA e0= test )* ( options {greedy=true; } : COMMA )? )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1448:2: e0= test ( options {k=2; greedy=true; } : COMMA e0= test )* ( options {greedy=true; } : COMMA )?
            {
            
            		PythonTestListExpression listExpression = new PythonTestListExpression();
            		int end = -1;
            	
            pushFollow(FOLLOW_test_in_testlist5337);
            e0=test();
            _fsp--;

            		
            		p = e0;
            		listExpression.addExpression( e0 );
            		if( p != null && p.sourceEnd() > end ) {
            			end = p.sourceEnd();
            		}
            	
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1460:6: ( options {k=2; greedy=true; } : COMMA e0= test )*
            loop92:
            do {
                int alt92=2;
                int LA92_0 = input.LA(1);

                if ( (LA92_0==COMMA) ) {
                    int LA92_1 = input.LA(2);

                    if ( (LA92_1==BACKQUOTE) ) {
                        alt92=1;
                    }
                    else if ( (LA92_1==LPAREN||LA92_1==NAME||(LA92_1>=PLUS && LA92_1<=MINUS)||(LA92_1>=TILDE && LA92_1<=LBRACK)||LA92_1==LCURLY||(LA92_1>=INT && LA92_1<=STRING)||LA92_1==91||LA92_1==93) ) {
                        alt92=1;
                    }


                }


                switch (alt92) {
            	case 1 :
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1461:7: COMMA e0= test
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_testlist5366); 
            	    pushFollow(FOLLOW_test_in_testlist5378);
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

            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1471:9: ( options {greedy=true; } : COMMA )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==COMMA) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1471:33: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_testlist5413); 

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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1478:1: tuplelist returns [ Expression p = null ] : e0= test ( options {greedy=true; } : COMMA e0= test )* ( options {greedy=true; } : COMMA )? ;
    public final Expression tuplelist() throws RecognitionException {
        Expression p =  null;

        Expression e0 = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1479:2: (e0= test ( options {greedy=true; } : COMMA e0= test )* ( options {greedy=true; } : COMMA )? )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1479:2: e0= test ( options {greedy=true; } : COMMA e0= test )* ( options {greedy=true; } : COMMA )?
            {
            pushFollow(FOLLOW_test_in_tuplelist5447);
            e0=test();
            _fsp--;

            
            		p = e0;
            		if( p == null ) {
            			p = new EmptyExpression();
            		}
            		//p.addExpression( e );
            	
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1487:6: ( options {greedy=true; } : COMMA e0= test )*
            loop94:
            do {
                int alt94=2;
                int LA94_0 = input.LA(1);

                if ( (LA94_0==COMMA) ) {
                    int LA94_1 = input.LA(2);

                    if ( (LA94_1==LPAREN||LA94_1==NAME||(LA94_1>=PLUS && LA94_1<=MINUS)||(LA94_1>=TILDE && LA94_1<=LBRACK)||LA94_1==LCURLY||(LA94_1>=BACKQUOTE && LA94_1<=STRING)||LA94_1==91||LA94_1==93) ) {
                        alt94=1;
                    }


                }


                switch (alt94) {
            	case 1 :
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1488:7: COMMA e0= test
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_tuplelist5472); 
            	    pushFollow(FOLLOW_test_in_tuplelist5484);
            	    e0=test();
            	    _fsp--;

            	    
            	        				if( !( p instanceof PythonTupleExpression ) ) {
            	    	    				PythonTupleExpression tuple = new PythonTupleExpression();
            	    	    				tuple.setStart( p.sourceStart() - 1 );
            	    	    				tuple.setEnd( p.sourceEnd() + 1 );
            	        					tuple.addExpression( p );
            	        					p = tuple;
            	        				}
            	        				PythonTupleExpression tup = (PythonTupleExpression)p;
            	        				tup.addExpression( e0 );
            	        				tup.setEnd(e0.sourceEnd() + 1);
            	        			

            	    }
            	    break;

            	default :
            	    break loop94;
                }
            } while (true);

            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1503:9: ( options {greedy=true; } : COMMA )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==COMMA) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1503:33: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_tuplelist5520); 

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


    // $ANTLR start dictmaker
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1507:1: dictmaker returns [ PythonDictExpression d = new PythonDictExpression() ] : t1= test COLON t2= test ( options {k=2; greedy=true; } : COMMA t3= test COLON t4= test )* ( COMMA )? ;
    public final PythonDictExpression dictmaker() throws RecognitionException {
        PythonDictExpression d =  new PythonDictExpression();

        Expression t1 = null;

        Expression t2 = null;

        Expression t3 = null;

        Expression t4 = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1508:2: (t1= test COLON t2= test ( options {k=2; greedy=true; } : COMMA t3= test COLON t4= test )* ( COMMA )? )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1508:2: t1= test COLON t2= test ( options {k=2; greedy=true; } : COMMA t3= test COLON t4= test )* ( COMMA )?
            {
            pushFollow(FOLLOW_test_in_dictmaker5555);
            t1=test();
            _fsp--;

            match(input,COLON,FOLLOW_COLON_in_dictmaker5558); 
            pushFollow(FOLLOW_test_in_dictmaker5565);
            t2=test();
            _fsp--;

            
            			d.putExpression( t1, t2 );
            		
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1514:9: ( options {k=2; greedy=true; } : COMMA t3= test COLON t4= test )*
            loop96:
            do {
                int alt96=2;
                int LA96_0 = input.LA(1);

                if ( (LA96_0==COMMA) ) {
                    int LA96_1 = input.LA(2);

                    if ( (LA96_1==LPAREN||LA96_1==NAME||(LA96_1>=PLUS && LA96_1<=MINUS)||(LA96_1>=TILDE && LA96_1<=LBRACK)||LA96_1==LCURLY||(LA96_1>=BACKQUOTE && LA96_1<=STRING)||LA96_1==91||LA96_1==93) ) {
                        alt96=1;
                    }


                }


                switch (alt96) {
            	case 1 :
            	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1515:37: COMMA t3= test COLON t4= test
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_dictmaker5601); 
            	    pushFollow(FOLLOW_test_in_dictmaker5616);
            	    t3=test();
            	    _fsp--;

            	    match(input,COLON,FOLLOW_COLON_in_dictmaker5627); 
            	    pushFollow(FOLLOW_test_in_dictmaker5642);
            	    t4=test();
            	    _fsp--;

            	    
            	            			d.putExpression( t3, t4 );
            	            		

            	    }
            	    break;

            	default :
            	    break loop96;
                }
            } while (true);

            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1523:9: ( COMMA )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==COMMA) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1523:10: COMMA
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_dictmaker5677); 

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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1527:1: classdef returns [ PythonClassDeclaration classDeclaration = null ] : c= 'class' tu= NAME (r= LPAREN te= testlist m= RPAREN )? co= COLON sa= suite ;
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
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1528:2: (c= 'class' tu= NAME (r= LPAREN te= testlist m= RPAREN )? co= COLON sa= suite )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1528:2: c= 'class' tu= NAME (r= LPAREN te= testlist m= RPAREN )? co= COLON sa= suite
            {
            c=(Token)input.LT(1);
            match(input,94,FOLLOW_94_in_classdef5708); 
            tu=(Token)input.LT(1);
            match(input,NAME,FOLLOW_NAME_in_classdef5715); 
            
            			classDeclaration = new PythonClassDeclaration( toDLTK( tu ), toDLTK(c) );
            		
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1533:2: (r= LPAREN te= testlist m= RPAREN )?
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( (LA98_0==LPAREN) ) {
                alt98=1;
            }
            switch (alt98) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1534:3: r= LPAREN te= testlist m= RPAREN
                    {
                    r=(Token)input.LT(1);
                    match(input,LPAREN,FOLLOW_LPAREN_in_classdef5734); 
                    pushFollow(FOLLOW_testlist_in_classdef5743);
                    te=testlist();
                    _fsp--;

                    m=(Token)input.LT(1);
                    match(input,RPAREN,FOLLOW_RPAREN_in_classdef5752); 
                    
                    				if( te instanceof ExpressionList ) {
                    					classDeclaration.setParents( toDLTK( r ), (ExpressionList)te, toDLTK( m ) );
                    				}
                    				else {
                    					ExpressionList exprList = new ExpressionList();
                    					exprList.addExpression( te );
                    					classDeclaration.setParents( toDLTK( r ), exprList, toDLTK( m ) );
                    				}
                    				
                    			

                    }
                    break;

            }

            co=(Token)input.LT(1);
            match(input,COLON,FOLLOW_COLON_in_classdef5768); 
            pushFollow(FOLLOW_suite_in_classdef5775);
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1556:1: arglist returns [ ExpressionList expressions = new ExpressionList() ] : (k= argument ( options {greedy=true; } : COMMA k= argument )* ( COMMA ( STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )? )? | STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test ) ;
    public final ExpressionList arglist() throws RecognitionException {
        ExpressionList expressions =  new ExpressionList();

        Expression k = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1557:2: ( (k= argument ( options {greedy=true; } : COMMA k= argument )* ( COMMA ( STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )? )? | STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test ) )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1557:2: (k= argument ( options {greedy=true; } : COMMA k= argument )* ( COMMA ( STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )? )? | STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )
            {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1557:2: (k= argument ( options {greedy=true; } : COMMA k= argument )* ( COMMA ( STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )? )? | STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )
            int alt104=3;
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
            case 91:
            case 93:
                {
                alt104=1;
                }
                break;
            case STAR:
                {
                alt104=2;
                }
                break;
            case DOUBLESTAR:
                {
                alt104=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1557:2: (k= argument ( options {greedy=true; } : COMMA k= argument )* ( COMMA ( STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )? )? | STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )", 104, 0, input);

                throw nvae;
            }

            switch (alt104) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1558:3: k= argument ( options {greedy=true; } : COMMA k= argument )* ( COMMA ( STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )? )?
                    {
                    pushFollow(FOLLOW_argument_in_arglist5807);
                    k=argument();
                    _fsp--;

                    
                    				expressions.addExpression( k );
                    			
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1562:11: ( options {greedy=true; } : COMMA k= argument )*
                    loop99:
                    do {
                        int alt99=2;
                        int LA99_0 = input.LA(1);

                        if ( (LA99_0==COMMA) ) {
                            int LA99_1 = input.LA(2);

                            if ( (LA99_1==LPAREN||LA99_1==NAME||(LA99_1>=PLUS && LA99_1<=MINUS)||(LA99_1>=TILDE && LA99_1<=LBRACK)||LA99_1==LCURLY||(LA99_1>=BACKQUOTE && LA99_1<=STRING)||LA99_1==91||LA99_1==93) ) {
                                alt99=1;
                            }


                        }


                        switch (alt99) {
                    	case 1 :
                    	    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1563:35: COMMA k= argument
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_arglist5847); 
                    	    pushFollow(FOLLOW_argument_in_arglist5865);
                    	    k=argument();
                    	    _fsp--;

                    	    
                    	             				expressions.addExpression( k );
                    	             			

                    	    }
                    	    break;

                    	default :
                    	    break loop99;
                        }
                    } while (true);

                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1569:3: ( COMMA ( STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )? )?
                    int alt102=2;
                    int LA102_0 = input.LA(1);

                    if ( (LA102_0==COMMA) ) {
                        alt102=1;
                    }
                    switch (alt102) {
                        case 1 :
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1570:4: COMMA ( STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )?
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_arglist5903); 
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1571:4: ( STAR k= test ( COMMA DOUBLESTAR k= test )? | DOUBLESTAR k= test )?
                            int alt101=3;
                            int LA101_0 = input.LA(1);

                            if ( (LA101_0==STAR) ) {
                                alt101=1;
                            }
                            else if ( (LA101_0==DOUBLESTAR) ) {
                                alt101=2;
                            }
                            switch (alt101) {
                                case 1 :
                                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1572:5: STAR k= test ( COMMA DOUBLESTAR k= test )?
                                    {
                                    match(input,STAR,FOLLOW_STAR_in_arglist5915); 
                                    pushFollow(FOLLOW_test_in_arglist5926);
                                    k=test();
                                    _fsp--;

                                    
                                                					expressions.addExpression( k );
                                                				
                                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1577:16: ( COMMA DOUBLESTAR k= test )?
                                    int alt100=2;
                                    int LA100_0 = input.LA(1);

                                    if ( (LA100_0==COMMA) ) {
                                        alt100=1;
                                    }
                                    switch (alt100) {
                                        case 1 :
                                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1578:17: COMMA DOUBLESTAR k= test
                                            {
                                            match(input,COMMA,FOLLOW_COMMA_in_arglist5982); 
                                            match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_arglist6001); 
                                            pushFollow(FOLLOW_test_in_arglist6024);
                                            k=test();
                                            _fsp--;

                                            
                                                        						expressions.addExpression( k );
                                                        					

                                            }
                                            break;

                                    }


                                    }
                                    break;
                                case 2 :
                                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1586:14: DOUBLESTAR k= test
                                    {
                                    match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_arglist6093); 
                                    pushFollow(FOLLOW_test_in_arglist6113);
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
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1594:3: STAR k= test ( COMMA DOUBLESTAR k= test )?
                    {
                    match(input,STAR,FOLLOW_STAR_in_arglist6152); 
                    pushFollow(FOLLOW_test_in_arglist6161);
                    k=test();
                    _fsp--;

                    
                    				expressions.addExpression( k );
                    			
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1599:3: ( COMMA DOUBLESTAR k= test )?
                    int alt103=2;
                    int LA103_0 = input.LA(1);

                    if ( (LA103_0==COMMA) ) {
                        alt103=1;
                    }
                    switch (alt103) {
                        case 1 :
                            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1600:4: COMMA DOUBLESTAR k= test
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_arglist6177); 
                            match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_arglist6183); 
                            pushFollow(FOLLOW_test_in_arglist6193);
                            k=test();
                            _fsp--;

                            
                            					expressions.addExpression( k );
                            				

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1608:3: DOUBLESTAR k= test
                    {
                    match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_arglist6213); 
                    pushFollow(FOLLOW_test_in_arglist6222);
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
    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1616:1: argument returns [ Expression e = null ] : e0= test ( ASSIGN k= test )? ;
    public final Expression argument() throws RecognitionException {
        Expression e =  null;

        Expression e0 = null;

        Expression k = null;


        try {
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1617:2: (e0= test ( ASSIGN k= test )? )
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1617:2: e0= test ( ASSIGN k= test )?
            {
            pushFollow(FOLLOW_test_in_argument6260);
            e0=test();
            _fsp--;

            
            		e = e0;
            	
            // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1621:2: ( ASSIGN k= test )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==ASSIGN) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // C:\\Develop\\dltk\\org.eclipse.dltk.python.core\\src\\org\\eclipse\\dltk\\python\\internal\\core\\parsers\\python_v3.g:1622:3: ASSIGN k= test
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_argument6270); 
                    pushFollow(FOLLOW_test_in_argument6279);
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


 

    public static final BitSet FOLLOW_NEWLINE_in_file_input101 = new BitSet(new long[]{0x03F58C00000005C0L,0x0000000068736FFEL});
    public static final BitSet FOLLOW_stmt_in_file_input126 = new BitSet(new long[]{0x03F58C00000005C0L,0x0000000068736FFEL});
    public static final BitSet FOLLOW_EOF_in_file_input157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECORATOR_S_in_decorator185 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_dot_name_in_decorator192 = new BitSet(new long[]{0x0000000000000140L});
    public static final BitSet FOLLOW_LPAREN_in_decorator204 = new BitSet(new long[]{0x03F58C0000006500L,0x0000000028000000L});
    public static final BitSet FOLLOW_arglist_in_decorator213 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_RPAREN_in_decorator221 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NEWLINE_in_decorator237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_decorator_in_decoraror_list259 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_decoraror_list_in_funcdef294 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_funcdef306 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_funcdef314 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_parameters_in_funcdef320 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_funcdef338 = new BitSet(new long[]{0x03F58C0000000540L,0x0000000028016FFCL});
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
    public static final BitSet FOLLOW_ASSIGN_in_defparameter657 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
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
    public static final BitSet FOLLOW_SEMI_in_simple_stmt792 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028016FFCL});
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
    public static final BitSet FOLLOW_augassign_in_expr_stmt1007 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_testlist_in_expr_stmt1016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_expr_stmt1038 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
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
    public static final BitSet FOLLOW_66_in_print_stmt1215 = new BitSet(new long[]{0x03F58C0020000502L,0x0000000028000000L});
    public static final BitSet FOLLOW_testlist_in_print_stmt1226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RIGHTSHIFT_in_print_stmt1239 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_testlist_in_print_stmt1255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_del_stmt1286 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_exprlist_in_del_stmt1294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_pass_stmt1317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_break_stmt_in_flow_stmt1343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_continue_stmt_in_flow_stmt1354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_stmt_in_flow_stmt1364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_raise_stmt_in_flow_stmt1374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_yield_stmt_in_flow_stmt1384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_break_stmt1405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_continue_stmt1429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_return_stmt1454 = new BitSet(new long[]{0x03F58C0000000502L,0x0000000028000000L});
    public static final BitSet FOLLOW_testlist_in_return_stmt1465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_yield_stmt1493 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_testlist_in_yield_stmt1500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_raise_stmt1522 = new BitSet(new long[]{0x03F58C0000000502L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_raise_stmt1536 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_raise_stmt1547 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_raise_stmt1556 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_raise_stmt1569 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_raise_stmt1579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_import_stmt1696 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_module_imp_in_import_stmt1718 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_import_stmt1747 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_module_imp_in_import_stmt1761 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_75_in_import_stmt1797 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_dot_name_in_import_stmt1805 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_import_stmt1812 = new BitSet(new long[]{0x0000000000002400L});
    public static final BitSet FOLLOW_module_imp_in_import_stmt1852 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_import_stmt1875 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_module_imp_in_import_stmt1890 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_STAR_in_import_stmt1947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_dotted_name1997 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_DOT_in_dotted_name2007 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_dotted_name2014 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_dotted_name_in_dot_name2049 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_dot_name2069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dot_name_in_module_imp2099 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_module_imp2111 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_module_imp2118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_global_stmt2145 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_global_stmt2147 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_global_stmt2150 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_global_stmt2152 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_78_in_exec_stmt2177 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_expr_in_exec_stmt2183 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_exec_stmt2190 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_exec_stmt2199 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_exec_stmt2207 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_exec_stmt2213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_assert_stmt2240 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_assert_stmt2247 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_assert_stmt2253 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_assert_stmt2259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_stmt_in_compound_stmt2287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_stmt_in_compound_stmt2298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_stmt_in_compound_stmt2308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try_stmt_in_compound_stmt2318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_funcdef_in_compound_stmt2328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classdef_in_compound_stmt2338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_if_stmt2360 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_if_stmt2369 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_if_stmt2373 = new BitSet(new long[]{0x03F58C0000000540L,0x0000000028016FFCL});
    public static final BitSet FOLLOW_suite_in_if_stmt2382 = new BitSet(new long[]{0x0000000000000002L,0x00000000000C0000L});
    public static final BitSet FOLLOW_82_in_if_stmt2403 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_if_stmt2412 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_if_stmt2418 = new BitSet(new long[]{0x03F58C0000000540L,0x0000000028016FFCL});
    public static final BitSet FOLLOW_suite_in_if_stmt2426 = new BitSet(new long[]{0x0000000000000002L,0x00000000000C0000L});
    public static final BitSet FOLLOW_83_in_if_stmt2447 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_if_stmt2453 = new BitSet(new long[]{0x03F58C0000000540L,0x0000000028016FFCL});
    public static final BitSet FOLLOW_suite_in_if_stmt2463 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_while_stmt2499 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_while_stmt2507 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_while_stmt2511 = new BitSet(new long[]{0x03F58C0000000540L,0x0000000028016FFCL});
    public static final BitSet FOLLOW_suite_in_while_stmt2521 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_while_stmt2535 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_while_stmt2537 = new BitSet(new long[]{0x03F58C0000000540L,0x0000000028016FFCL});
    public static final BitSet FOLLOW_suite_in_while_stmt2547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_85_in_for_stmt2578 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_exprlist_in_for_stmt2587 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_for_stmt2592 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_testlist_in_for_stmt2600 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_for_stmt2604 = new BitSet(new long[]{0x03F58C0000000540L,0x0000000028016FFCL});
    public static final BitSet FOLLOW_suite_in_for_stmt2613 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_for_stmt2628 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_for_stmt2634 = new BitSet(new long[]{0x03F58C0000000540L,0x0000000028016FFCL});
    public static final BitSet FOLLOW_suite_in_for_stmt2644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_86_in_try_stmt2676 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_try_stmt2680 = new BitSet(new long[]{0x03F58C0000000540L,0x0000000028016FFCL});
    public static final BitSet FOLLOW_suite_in_try_stmt2695 = new BitSet(new long[]{0x0000000000000000L,0x0000000001800000L});
    public static final BitSet FOLLOW_87_in_try_stmt2733 = new BitSet(new long[]{0x03F58C0000000D00L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_try_stmt2748 = new BitSet(new long[]{0x0000000000001800L});
    public static final BitSet FOLLOW_COMMA_in_try_stmt2755 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_try_stmt2761 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_try_stmt2774 = new BitSet(new long[]{0x03F58C0000000540L,0x0000000028016FFCL});
    public static final BitSet FOLLOW_suite_in_try_stmt2783 = new BitSet(new long[]{0x0000000000000002L,0x0000000000880000L});
    public static final BitSet FOLLOW_83_in_try_stmt2808 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_try_stmt2814 = new BitSet(new long[]{0x03F58C0000000540L,0x0000000028016FFCL});
    public static final BitSet FOLLOW_suite_in_try_stmt2824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_try_stmt2935 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_try_stmt2958 = new BitSet(new long[]{0x03F58C0000000540L,0x0000000028016FFCL});
    public static final BitSet FOLLOW_suite_in_try_stmt2974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_stmt_in_suite3034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_suite3055 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_INDENT_in_suite3065 = new BitSet(new long[]{0x03F58C0000000580L,0x0000000068736FFEL});
    public static final BitSet FOLLOW_stmt_in_suite3089 = new BitSet(new long[]{0x03F58C00000005A0L,0x0000000068736FFEL});
    public static final BitSet FOLLOW_DEDENT_in_suite3115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_test_in_test3164 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000000L});
    public static final BitSet FOLLOW_89_in_test3178 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000008000000L});
    public static final BitSet FOLLOW_and_test_in_test3187 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000000L});
    public static final BitSet FOLLOW_lambdef_in_test3211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_test_in_and_test3238 = new BitSet(new long[]{0x0000000000000002L,0x0000000004000000L});
    public static final BitSet FOLLOW_90_in_and_test3252 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000008000000L});
    public static final BitSet FOLLOW_not_test_in_and_test3261 = new BitSet(new long[]{0x0000000000000002L,0x0000000004000000L});
    public static final BitSet FOLLOW_91_in_not_test3294 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000008000000L});
    public static final BitSet FOLLOW_not_test_in_not_test3303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comparison_in_not_test3322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_comparison3345 = new BitSet(new long[]{0x0000003F80000002L,0x0000000018008000L});
    public static final BitSet FOLLOW_comp_op_in_comparison3359 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_expr_in_comparison3368 = new BitSet(new long[]{0x0000003F80000002L,0x0000000018008000L});
    public static final BitSet FOLLOW_LESS_in_comp_op3400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_comp_op3412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUAL_in_comp_op3423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATEREQUAL_in_comp_op3436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESSEQUAL_in_comp_op3446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALT_NOTEQUAL_in_comp_op3460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOTEQUAL_in_comp_op3472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_comp_op3487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_comp_op3497 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_comp_op3499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_92_in_comp_op3509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_92_in_comp_op3519 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_91_in_comp_op3521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_xor_expr_in_expr3543 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_VBAR_in_expr3557 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_xor_expr_in_expr3567 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_and_expr_in_xor_expr3600 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_CIRCUMFLEX_in_xor_expr3614 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_and_expr_in_xor_expr3623 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_shift_expr_in_and_expr3654 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_AMPER_in_and_expr3669 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_shift_expr_in_and_expr3679 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_arith_expr_in_shift_expr3709 = new BitSet(new long[]{0x0000020020000002L});
    public static final BitSet FOLLOW_LEFTSHIFT_in_shift_expr3733 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_RIGHTSHIFT_in_shift_expr3754 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_arith_expr_in_shift_expr3776 = new BitSet(new long[]{0x0000020020000002L});
    public static final BitSet FOLLOW_term_in_arith_expr3812 = new BitSet(new long[]{0x00000C0000000002L});
    public static final BitSet FOLLOW_PLUS_in_arith_expr3836 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_MINUS_in_arith_expr3856 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_term_in_arith_expr3875 = new BitSet(new long[]{0x00000C0000000002L});
    public static final BitSet FOLLOW_factor_in_term3905 = new BitSet(new long[]{0x0000700000002002L});
    public static final BitSet FOLLOW_STAR_in_term3929 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_SLASH_in_term3953 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_PERCENT_in_term3978 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_DOUBLESLASH_in_term4006 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_factor_in_term4032 = new BitSet(new long[]{0x0000700000002002L});
    public static final BitSet FOLLOW_PLUS_in_factor4078 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_MINUS_in_factor4101 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_TILDE_in_factor4123 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_factor_in_factor4145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_power_in_factor4162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_power4189 = new BitSet(new long[]{0x0001000040004102L});
    public static final BitSet FOLLOW_trailer_in_power4207 = new BitSet(new long[]{0x0001000040004102L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_power4240 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_factor_in_power4258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_trailer4306 = new BitSet(new long[]{0x03F58C0000006700L,0x0000000028000000L});
    public static final BitSet FOLLOW_arglist_in_trailer4318 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_RPAREN_in_trailer4330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_trailer4348 = new BitSet(new long[]{0x03F58C0040000D00L,0x0000000028000000L});
    public static final BitSet FOLLOW_subscriptlist_in_trailer4357 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_RBRACK_in_trailer4366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_trailer4381 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_trailer4389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_atom4415 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_tuplelist_in_atom4423 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_RPAREN_in_atom4429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_atom4434 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_RPAREN_in_atom4438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_atom4445 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_listmaker_in_atom4453 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_RBRACK_in_atom4459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_atom4464 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_RBRACK_in_atom4468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_atom4474 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_dictmaker_in_atom4482 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_atom4488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_atom4493 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_atom4497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BACKQUOTE_in_atom4503 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_testlist_in_atom4509 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_BACKQUOTE_in_atom4513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_atom4521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_atom4533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LONGINT_in_atom4550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_atom4565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMPLEX_in_atom4583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_atom4600 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_test_in_listmaker4630 = new BitSet(new long[]{0x0000000000001002L,0x0000000000200000L});
    public static final BitSet FOLLOW_list_for_in_listmaker4647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_listmaker4695 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_listmaker4705 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_listmaker4724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_85_in_list_for4751 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_exprlist_in_list_for4759 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_list_for4763 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_testlist_in_list_for4772 = new BitSet(new long[]{0x0000000000000002L,0x0000000000220000L});
    public static final BitSet FOLLOW_list_if_in_list_for4795 = new BitSet(new long[]{0x0000000000000002L,0x0000000000220000L});
    public static final BitSet FOLLOW_81_in_list_if4828 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_list_if4835 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_93_in_lambdef4856 = new BitSet(new long[]{0x0000000000006D00L});
    public static final BitSet FOLLOW_varargslist_in_lambdef4864 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_lambdef4871 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_lambdef4879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subscript_in_subscriptlist4903 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_subscriptlist4919 = new BitSet(new long[]{0x03F58C0040000D00L,0x0000000028000000L});
    public static final BitSet FOLLOW_subscript_in_subscriptlist4928 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_subscriptlist4943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_subscript4967 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_DOT_in_subscript4969 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_DOT_in_subscript4971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_subscript4996 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_COLON_in_subscript5011 = new BitSet(new long[]{0x03F58C0000000D02L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_subscript5027 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_sliceop_in_subscript5057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_subscript5099 = new BitSet(new long[]{0x03F58C0000000D02L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_subscript5123 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_sliceop_in_subscript5169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_sliceop5229 = new BitSet(new long[]{0x03F58C0000000502L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_sliceop5238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_exprlist5265 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_exprlist5287 = new BitSet(new long[]{0x03F58C0000000500L});
    public static final BitSet FOLLOW_expr_in_exprlist5296 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_exprlist5310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_testlist5337 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_testlist5366 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_testlist5378 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_testlist5413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_tuplelist5447 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_tuplelist5472 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_tuplelist5484 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_tuplelist5520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_dictmaker5555 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_dictmaker5558 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_dictmaker5565 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_dictmaker5601 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_dictmaker5616 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_dictmaker5627 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_dictmaker5642 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_dictmaker5677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_94_in_classdef5708 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_classdef5715 = new BitSet(new long[]{0x0000000000000900L});
    public static final BitSet FOLLOW_LPAREN_in_classdef5734 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_testlist_in_classdef5743 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_RPAREN_in_classdef5752 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_classdef5768 = new BitSet(new long[]{0x03F58C0000000540L,0x0000000028016FFCL});
    public static final BitSet FOLLOW_suite_in_classdef5775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_argument_in_arglist5807 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_arglist5847 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_argument_in_arglist5865 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_arglist5903 = new BitSet(new long[]{0x0000000000006002L});
    public static final BitSet FOLLOW_STAR_in_arglist5915 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_arglist5926 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_arglist5982 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_arglist6001 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_arglist6024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_arglist6093 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_arglist6113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_arglist6152 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_arglist6161 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_COMMA_in_arglist6177 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_arglist6183 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_arglist6193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_arglist6213 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_arglist6222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_argument6260 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_ASSIGN_in_argument6270 = new BitSet(new long[]{0x03F58C0000000500L,0x0000000028000000L});
    public static final BitSet FOLLOW_test_in_argument6279 = new BitSet(new long[]{0x0000000000000002L});

}