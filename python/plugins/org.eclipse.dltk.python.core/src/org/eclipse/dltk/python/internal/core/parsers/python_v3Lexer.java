// $ANTLR 3.0 /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g 2007-08-17 15:27:18

	package org.eclipse.dltk.python.internal.core.parsers;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class python_v3Lexer extends Lexer {
    public static final int COMMA=12;
    public static final int MINUS=43;
    public static final int DEDENT=5;
    public static final int DECORATOR_S=7;
    public static final int T70=70;
    public static final int T74=74;
    public static final int COMPLEX=56;
    public static final int T85=85;
    public static final int TILDE=47;
    public static final int DOUBLESLASHEQUAL=28;
    public static final int NEWLINE=6;
    public static final int DOT=30;
    public static final int PLUSEQUAL=17;
    public static final int RIGHTSHIFTEQUAL=26;
    public static final int LCURLY=50;
    public static final int T81=81;
    public static final int RPAREN=9;
    public static final int PLUS=42;
    public static final int T68=68;
    public static final int T73=73;
    public static final int T84=84;
    public static final int T78=78;
    public static final int WS=62;
    public static final int STRING=57;
    public static final int T71=71;
    public static final int T72=72;
    public static final int T94=94;
    public static final int LBRACK=48;
    public static final int T76=76;
    public static final int SEMI=16;
    public static final int T75=75;
    public static final int EQUAL=33;
    public static final int LESSEQUAL=35;
    public static final int T89=89;
    public static final int T67=67;
    public static final int ALT_NOTEQUAL=36;
    public static final int COLON=11;
    public static final int AMPER=40;
    public static final int T82=82;
    public static final int NAME=10;
    public static final int DOUBLESTAREQUAL=27;
    public static final int T79=79;
    public static final int PERCENT=45;
    public static final int FLOAT=55;
    public static final int DOUBLESTAR=14;
    public static final int T93=93;
    public static final int SLASHEQUAL=20;
    public static final int T83=83;
    public static final int NOTEQUAL=37;
    public static final int CIRCUMFLEX=39;
    public static final int RCURLY=51;
    public static final int T91=91;
    public static final int LESS=31;
    public static final int T86=86;
    public static final int LONGINT=54;
    public static final int INT=53;
    public static final int LEADING_WS=63;
    public static final int ASSIGN=15;
    public static final int VBAR=38;
    public static final int GREATER=32;
    public static final int LPAREN=8;
    public static final int T77=77;
    public static final int BACKQUOTE=52;
    public static final int CONTINUED_LINE=61;
    public static final int T69=69;
    public static final int Exponent=59;
    public static final int DIGITS=58;
    public static final int SLASH=44;
    public static final int T92=92;
    public static final int T66=66;
    public static final int COMMENT=64;
    public static final int T88=88;
    public static final int AMPEREQUAL=22;
    public static final int ESC=60;
    public static final int T65=65;
    public static final int T87=87;
    public static final int T80=80;
    public static final int RIGHTSHIFT=29;
    public static final int MINUSEQUAL=18;
    public static final int PERCENTEQUAL=21;
    public static final int LEFTSHIFTEQUAL=25;
    public static final int EOF=-1;
    public static final int CIRCUMFLEXEQUAL=24;
    public static final int INDENT=4;
    public static final int Tokens=95;
    public static final int RBRACK=49;
    public static final int GREATEREQUAL=34;
    public static final int DOUBLESLASH=46;
    public static final int STAREQUAL=19;
    public static final int STAR=13;
    public static final int VBAREQUAL=23;
    public static final int T90=90;
    public static final int LEFTSHIFT=41;

    /** Handles context-sensitive lexing of implicit line joining such as
     *  the case where newline is ignored in cases like this:
     *  a = [3,
     *       4]
     */
    public int implicitLineJoiningLevel = 0;
    public int startPos=-1;
    public void emitErrorMessage(String msg) {
    }

    public python_v3Lexer() {;} 
    public python_v3Lexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g"; }

    // $ANTLR start T65
    public final void mT65() throws RecognitionException {
        try {
            int _type = T65;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:21:5: ( 'def' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:21:7: 'def'
            {
            match("def"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T65

    // $ANTLR start T66
    public final void mT66() throws RecognitionException {
        try {
            int _type = T66;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:22:5: ( 'print' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:22:7: 'print'
            {
            match("print"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T66

    // $ANTLR start T67
    public final void mT67() throws RecognitionException {
        try {
            int _type = T67;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:23:5: ( 'del' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:23:7: 'del'
            {
            match("del"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T67

    // $ANTLR start T68
    public final void mT68() throws RecognitionException {
        try {
            int _type = T68;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:24:5: ( 'pass' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:24:7: 'pass'
            {
            match("pass"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T68

    // $ANTLR start T69
    public final void mT69() throws RecognitionException {
        try {
            int _type = T69;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:25:5: ( 'break' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:25:7: 'break'
            {
            match("break"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T69

    // $ANTLR start T70
    public final void mT70() throws RecognitionException {
        try {
            int _type = T70;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:26:5: ( 'continue' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:26:7: 'continue'
            {
            match("continue"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T70

    // $ANTLR start T71
    public final void mT71() throws RecognitionException {
        try {
            int _type = T71;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:27:5: ( 'return' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:27:7: 'return'
            {
            match("return"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T71

    // $ANTLR start T72
    public final void mT72() throws RecognitionException {
        try {
            int _type = T72;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:28:5: ( 'yield' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:28:7: 'yield'
            {
            match("yield"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T72

    // $ANTLR start T73
    public final void mT73() throws RecognitionException {
        try {
            int _type = T73;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:29:5: ( 'raise' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:29:7: 'raise'
            {
            match("raise"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T73

    // $ANTLR start T74
    public final void mT74() throws RecognitionException {
        try {
            int _type = T74;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:30:5: ( 'import' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:30:7: 'import'
            {
            match("import"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T74

    // $ANTLR start T75
    public final void mT75() throws RecognitionException {
        try {
            int _type = T75;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:31:5: ( 'from' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:31:7: 'from'
            {
            match("from"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T75

    // $ANTLR start T76
    public final void mT76() throws RecognitionException {
        try {
            int _type = T76;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:32:5: ( 'as' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:32:7: 'as'
            {
            match("as"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T76

    // $ANTLR start T77
    public final void mT77() throws RecognitionException {
        try {
            int _type = T77;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:33:5: ( 'global' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:33:7: 'global'
            {
            match("global"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T77

    // $ANTLR start T78
    public final void mT78() throws RecognitionException {
        try {
            int _type = T78;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:34:5: ( 'exec' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:34:7: 'exec'
            {
            match("exec"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T78

    // $ANTLR start T79
    public final void mT79() throws RecognitionException {
        try {
            int _type = T79;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:35:5: ( 'in' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:35:7: 'in'
            {
            match("in"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T79

    // $ANTLR start T80
    public final void mT80() throws RecognitionException {
        try {
            int _type = T80;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:36:5: ( 'assert' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:36:7: 'assert'
            {
            match("assert"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T80

    // $ANTLR start T81
    public final void mT81() throws RecognitionException {
        try {
            int _type = T81;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:37:5: ( 'if' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:37:7: 'if'
            {
            match("if"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T81

    // $ANTLR start T82
    public final void mT82() throws RecognitionException {
        try {
            int _type = T82;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:38:5: ( 'elif' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:38:7: 'elif'
            {
            match("elif"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T82

    // $ANTLR start T83
    public final void mT83() throws RecognitionException {
        try {
            int _type = T83;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:39:5: ( 'else' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:39:7: 'else'
            {
            match("else"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T83

    // $ANTLR start T84
    public final void mT84() throws RecognitionException {
        try {
            int _type = T84;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:40:5: ( 'while' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:40:7: 'while'
            {
            match("while"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T84

    // $ANTLR start T85
    public final void mT85() throws RecognitionException {
        try {
            int _type = T85;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:41:5: ( 'for' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:41:7: 'for'
            {
            match("for"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T85

    // $ANTLR start T86
    public final void mT86() throws RecognitionException {
        try {
            int _type = T86;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:42:5: ( 'try' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:42:7: 'try'
            {
            match("try"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T86

    // $ANTLR start T87
    public final void mT87() throws RecognitionException {
        try {
            int _type = T87;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:43:5: ( 'except' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:43:7: 'except'
            {
            match("except"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T87

    // $ANTLR start T88
    public final void mT88() throws RecognitionException {
        try {
            int _type = T88;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:44:5: ( 'finally' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:44:7: 'finally'
            {
            match("finally"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T88

    // $ANTLR start T89
    public final void mT89() throws RecognitionException {
        try {
            int _type = T89;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:45:5: ( 'or' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:45:7: 'or'
            {
            match("or"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T89

    // $ANTLR start T90
    public final void mT90() throws RecognitionException {
        try {
            int _type = T90;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:46:5: ( 'and' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:46:7: 'and'
            {
            match("and"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T90

    // $ANTLR start T91
    public final void mT91() throws RecognitionException {
        try {
            int _type = T91;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:47:5: ( 'not' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:47:7: 'not'
            {
            match("not"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T91

    // $ANTLR start T92
    public final void mT92() throws RecognitionException {
        try {
            int _type = T92;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:48:5: ( 'is' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:48:7: 'is'
            {
            match("is"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T92

    // $ANTLR start T93
    public final void mT93() throws RecognitionException {
        try {
            int _type = T93;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:49:5: ( 'lambda' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:49:7: 'lambda'
            {
            match("lambda"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T93

    // $ANTLR start T94
    public final void mT94() throws RecognitionException {
        try {
            int _type = T94;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:50:5: ( 'class' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:50:7: 'class'
            {
            match("class"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T94

    // $ANTLR start LPAREN
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1650:8: ( '(' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1650:10: '('
            {
            match('('); 
            implicitLineJoiningLevel++;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LPAREN

    // $ANTLR start RPAREN
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1652:8: ( ')' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1652:10: ')'
            {
            match(')'); 
            implicitLineJoiningLevel--;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RPAREN

    // $ANTLR start LBRACK
    public final void mLBRACK() throws RecognitionException {
        try {
            int _type = LBRACK;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1654:8: ( '[' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1654:10: '['
            {
            match('['); 
            implicitLineJoiningLevel++;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LBRACK

    // $ANTLR start RBRACK
    public final void mRBRACK() throws RecognitionException {
        try {
            int _type = RBRACK;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1656:8: ( ']' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1656:10: ']'
            {
            match(']'); 
            implicitLineJoiningLevel--;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RBRACK

    // $ANTLR start COLON
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1658:8: ( ':' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1658:10: ':'
            {
            match(':'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COLON

    // $ANTLR start COMMA
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1660:7: ( ',' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1660:9: ','
            {
            match(','); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COMMA

    // $ANTLR start SEMI
    public final void mSEMI() throws RecognitionException {
        try {
            int _type = SEMI;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1662:6: ( ';' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1662:8: ';'
            {
            match(';'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SEMI

    // $ANTLR start PLUS
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1664:6: ( '+' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1664:8: '+'
            {
            match('+'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PLUS

    // $ANTLR start MINUS
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1666:7: ( '-' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1666:9: '-'
            {
            match('-'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MINUS

    // $ANTLR start STAR
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1668:6: ( '*' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1668:8: '*'
            {
            match('*'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STAR

    // $ANTLR start SLASH
    public final void mSLASH() throws RecognitionException {
        try {
            int _type = SLASH;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1670:7: ( '/' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1670:9: '/'
            {
            match('/'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SLASH

    // $ANTLR start VBAR
    public final void mVBAR() throws RecognitionException {
        try {
            int _type = VBAR;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1672:6: ( '|' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1672:8: '|'
            {
            match('|'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end VBAR

    // $ANTLR start AMPER
    public final void mAMPER() throws RecognitionException {
        try {
            int _type = AMPER;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1674:7: ( '&' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1674:9: '&'
            {
            match('&'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end AMPER

    // $ANTLR start LESS
    public final void mLESS() throws RecognitionException {
        try {
            int _type = LESS;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1676:6: ( '<' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1676:8: '<'
            {
            match('<'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LESS

    // $ANTLR start GREATER
    public final void mGREATER() throws RecognitionException {
        try {
            int _type = GREATER;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1678:9: ( '>' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1678:11: '>'
            {
            match('>'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end GREATER

    // $ANTLR start ASSIGN
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1680:8: ( '=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1680:10: '='
            {
            match('='); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ASSIGN

    // $ANTLR start PERCENT
    public final void mPERCENT() throws RecognitionException {
        try {
            int _type = PERCENT;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1682:9: ( '%' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1682:11: '%'
            {
            match('%'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PERCENT

    // $ANTLR start BACKQUOTE
    public final void mBACKQUOTE() throws RecognitionException {
        try {
            int _type = BACKQUOTE;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1684:11: ( '`' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1684:13: '`'
            {
            match('`'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end BACKQUOTE

    // $ANTLR start LCURLY
    public final void mLCURLY() throws RecognitionException {
        try {
            int _type = LCURLY;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1686:8: ( '{' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1686:10: '{'
            {
            match('{'); 
            implicitLineJoiningLevel++;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LCURLY

    // $ANTLR start RCURLY
    public final void mRCURLY() throws RecognitionException {
        try {
            int _type = RCURLY;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1688:8: ( '}' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1688:10: '}'
            {
            match('}'); 
            implicitLineJoiningLevel--;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RCURLY

    // $ANTLR start CIRCUMFLEX
    public final void mCIRCUMFLEX() throws RecognitionException {
        try {
            int _type = CIRCUMFLEX;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1690:12: ( '^' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1690:14: '^'
            {
            match('^'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CIRCUMFLEX

    // $ANTLR start TILDE
    public final void mTILDE() throws RecognitionException {
        try {
            int _type = TILDE;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1692:7: ( '~' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1692:9: '~'
            {
            match('~'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end TILDE

    // $ANTLR start EQUAL
    public final void mEQUAL() throws RecognitionException {
        try {
            int _type = EQUAL;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1694:7: ( '==' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1694:9: '=='
            {
            match("=="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end EQUAL

    // $ANTLR start NOTEQUAL
    public final void mNOTEQUAL() throws RecognitionException {
        try {
            int _type = NOTEQUAL;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1696:10: ( '!=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1696:12: '!='
            {
            match("!="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NOTEQUAL

    // $ANTLR start ALT_NOTEQUAL
    public final void mALT_NOTEQUAL() throws RecognitionException {
        try {
            int _type = ALT_NOTEQUAL;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1698:13: ( '<>' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1698:15: '<>'
            {
            match("<>"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ALT_NOTEQUAL

    // $ANTLR start LESSEQUAL
    public final void mLESSEQUAL() throws RecognitionException {
        try {
            int _type = LESSEQUAL;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1700:11: ( '<=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1700:13: '<='
            {
            match("<="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LESSEQUAL

    // $ANTLR start LEFTSHIFT
    public final void mLEFTSHIFT() throws RecognitionException {
        try {
            int _type = LEFTSHIFT;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1702:11: ( '<<' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1702:13: '<<'
            {
            match("<<"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LEFTSHIFT

    // $ANTLR start GREATEREQUAL
    public final void mGREATEREQUAL() throws RecognitionException {
        try {
            int _type = GREATEREQUAL;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1704:14: ( '>=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1704:16: '>='
            {
            match(">="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end GREATEREQUAL

    // $ANTLR start RIGHTSHIFT
    public final void mRIGHTSHIFT() throws RecognitionException {
        try {
            int _type = RIGHTSHIFT;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1706:12: ( '>>' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1706:14: '>>'
            {
            match(">>"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RIGHTSHIFT

    // $ANTLR start PLUSEQUAL
    public final void mPLUSEQUAL() throws RecognitionException {
        try {
            int _type = PLUSEQUAL;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1708:11: ( '+=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1708:13: '+='
            {
            match("+="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PLUSEQUAL

    // $ANTLR start MINUSEQUAL
    public final void mMINUSEQUAL() throws RecognitionException {
        try {
            int _type = MINUSEQUAL;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1710:12: ( '-=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1710:14: '-='
            {
            match("-="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MINUSEQUAL

    // $ANTLR start DOUBLESTAR
    public final void mDOUBLESTAR() throws RecognitionException {
        try {
            int _type = DOUBLESTAR;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1712:12: ( '**' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1712:14: '**'
            {
            match("**"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DOUBLESTAR

    // $ANTLR start STAREQUAL
    public final void mSTAREQUAL() throws RecognitionException {
        try {
            int _type = STAREQUAL;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1714:11: ( '*=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1714:13: '*='
            {
            match("*="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STAREQUAL

    // $ANTLR start DOUBLESLASH
    public final void mDOUBLESLASH() throws RecognitionException {
        try {
            int _type = DOUBLESLASH;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1716:13: ( '//' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1716:15: '//'
            {
            match("//"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DOUBLESLASH

    // $ANTLR start SLASHEQUAL
    public final void mSLASHEQUAL() throws RecognitionException {
        try {
            int _type = SLASHEQUAL;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1718:12: ( '/=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1718:14: '/='
            {
            match("/="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SLASHEQUAL

    // $ANTLR start VBAREQUAL
    public final void mVBAREQUAL() throws RecognitionException {
        try {
            int _type = VBAREQUAL;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1720:11: ( '|=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1720:13: '|='
            {
            match("|="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end VBAREQUAL

    // $ANTLR start PERCENTEQUAL
    public final void mPERCENTEQUAL() throws RecognitionException {
        try {
            int _type = PERCENTEQUAL;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1722:14: ( '%=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1722:16: '%='
            {
            match("%="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PERCENTEQUAL

    // $ANTLR start AMPEREQUAL
    public final void mAMPEREQUAL() throws RecognitionException {
        try {
            int _type = AMPEREQUAL;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1724:12: ( '&=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1724:14: '&='
            {
            match("&="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end AMPEREQUAL

    // $ANTLR start CIRCUMFLEXEQUAL
    public final void mCIRCUMFLEXEQUAL() throws RecognitionException {
        try {
            int _type = CIRCUMFLEXEQUAL;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1726:17: ( '^=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1726:19: '^='
            {
            match("^="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CIRCUMFLEXEQUAL

    // $ANTLR start LEFTSHIFTEQUAL
    public final void mLEFTSHIFTEQUAL() throws RecognitionException {
        try {
            int _type = LEFTSHIFTEQUAL;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1728:16: ( '<<=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1728:18: '<<='
            {
            match("<<="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LEFTSHIFTEQUAL

    // $ANTLR start RIGHTSHIFTEQUAL
    public final void mRIGHTSHIFTEQUAL() throws RecognitionException {
        try {
            int _type = RIGHTSHIFTEQUAL;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1730:17: ( '>>=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1730:19: '>>='
            {
            match(">>="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RIGHTSHIFTEQUAL

    // $ANTLR start DOUBLESTAREQUAL
    public final void mDOUBLESTAREQUAL() throws RecognitionException {
        try {
            int _type = DOUBLESTAREQUAL;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1732:17: ( '**=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1732:19: '**='
            {
            match("**="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DOUBLESTAREQUAL

    // $ANTLR start DOUBLESLASHEQUAL
    public final void mDOUBLESLASHEQUAL() throws RecognitionException {
        try {
            int _type = DOUBLESLASHEQUAL;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1734:18: ( '//=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1734:20: '//='
            {
            match("//="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DOUBLESLASHEQUAL

    // $ANTLR start DOT
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1736:5: ( '.' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1736:7: '.'
            {
            match('.'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DOT

    // $ANTLR start FLOAT
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1739:2: ( '.' DIGITS ( Exponent )? | DIGITS ( '.' ( DIGITS ( Exponent )? )? | Exponent ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='.') ) {
                alt5=1;
            }
            else if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1738:1: FLOAT : ( '.' DIGITS ( Exponent )? | DIGITS ( '.' ( DIGITS ( Exponent )? )? | Exponent ) );", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1739:4: '.' DIGITS ( Exponent )?
                    {
                    match('.'); 
                    mDIGITS(); 
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1739:15: ( Exponent )?
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0=='E'||LA1_0=='e') ) {
                        alt1=1;
                    }
                    switch (alt1) {
                        case 1 :
                            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1739:16: Exponent
                            {
                            mExponent(); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1740:9: DIGITS ( '.' ( DIGITS ( Exponent )? )? | Exponent )
                    {
                    mDIGITS(); 
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1740:16: ( '.' ( DIGITS ( Exponent )? )? | Exponent )
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0=='.') ) {
                        alt4=1;
                    }
                    else if ( (LA4_0=='E'||LA4_0=='e') ) {
                        alt4=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("1740:16: ( '.' ( DIGITS ( Exponent )? )? | Exponent )", 4, 0, input);

                        throw nvae;
                    }
                    switch (alt4) {
                        case 1 :
                            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1740:17: '.' ( DIGITS ( Exponent )? )?
                            {
                            match('.'); 
                            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1740:21: ( DIGITS ( Exponent )? )?
                            int alt3=2;
                            int LA3_0 = input.LA(1);

                            if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                                alt3=1;
                            }
                            switch (alt3) {
                                case 1 :
                                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1740:22: DIGITS ( Exponent )?
                                    {
                                    mDIGITS(); 
                                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1740:29: ( Exponent )?
                                    int alt2=2;
                                    int LA2_0 = input.LA(1);

                                    if ( (LA2_0=='E'||LA2_0=='e') ) {
                                        alt2=1;
                                    }
                                    switch (alt2) {
                                        case 1 :
                                            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1740:30: Exponent
                                            {
                                            mExponent(); 

                                            }
                                            break;

                                    }


                                    }
                                    break;

                            }


                            }
                            break;
                        case 2 :
                            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1740:45: Exponent
                            {
                            mExponent(); 

                            }
                            break;

                    }


                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end FLOAT

    // $ANTLR start LONGINT
    public final void mLONGINT() throws RecognitionException {
        try {
            int _type = LONGINT;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1744:5: ( INT ( 'l' | 'L' ) )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1744:9: INT ( 'l' | 'L' )
            {
            mINT(); 
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LONGINT

    // $ANTLR start Exponent
    public final void mExponent() throws RecognitionException {
        try {
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1749:2: ( ( 'e' | 'E' ) ( '+' | '-' )? DIGITS )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1749:4: ( 'e' | 'E' ) ( '+' | '-' )? DIGITS
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1749:16: ( '+' | '-' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='+'||LA6_0=='-') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse =
                            new MismatchedSetException(null,input);
                        recover(mse);    throw mse;
                    }


                    }
                    break;

            }

            mDIGITS(); 

            }

        }
        finally {
        }
    }
    // $ANTLR end Exponent

    // $ANTLR start INT
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1752:5: ( '0' ( 'x' | 'X' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ ( 'l' | 'L' )? | '0' ( DIGITS )* | '1' .. '9' ( DIGITS )* )
            int alt11=3;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='0') ) {
                int LA11_1 = input.LA(2);

                if ( (LA11_1=='X'||LA11_1=='x') ) {
                    alt11=1;
                }
                else {
                    alt11=2;}
            }
            else if ( ((LA11_0>='1' && LA11_0<='9')) ) {
                alt11=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1752:1: INT : ( '0' ( 'x' | 'X' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ ( 'l' | 'L' )? | '0' ( DIGITS )* | '1' .. '9' ( DIGITS )* );", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1753:9: '0' ( 'x' | 'X' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ ( 'l' | 'L' )?
                    {
                    match('0'); 
                    if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse =
                            new MismatchedSetException(null,input);
                        recover(mse);    throw mse;
                    }

                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1753:25: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+
                    int cnt7=0;
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( ((LA7_0>='0' && LA7_0<='9')||(LA7_0>='A' && LA7_0<='F')||(LA7_0>='a' && LA7_0<='f')) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:
                    	    {
                    	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt7 >= 1 ) break loop7;
                                EarlyExitException eee =
                                    new EarlyExitException(7, input);
                                throw eee;
                        }
                        cnt7++;
                    } while (true);

                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1754:9: ( 'l' | 'L' )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0=='L'||LA8_0=='l') ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:
                            {
                            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse =
                                    new MismatchedSetException(null,input);
                                recover(mse);    throw mse;
                            }


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1756:9: '0' ( DIGITS )*
                    {
                    match('0'); 
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1756:13: ( DIGITS )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( ((LA9_0>='0' && LA9_0<='9')) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1756:13: DIGITS
                    	    {
                    	    mDIGITS(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);


                    }
                    break;
                case 3 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1757:9: '1' .. '9' ( DIGITS )*
                    {
                    matchRange('1','9'); 
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1757:18: ( DIGITS )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( ((LA10_0>='0' && LA10_0<='9')) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1757:18: DIGITS
                    	    {
                    	    mDIGITS(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);


                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end INT

    // $ANTLR start COMPLEX
    public final void mCOMPLEX() throws RecognitionException {
        try {
            int _type = COMPLEX;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1761:5: ( INT ( 'j' | 'J' ) | FLOAT ( 'j' | 'J' ) )
            int alt12=2;
            alt12 = dfa12.predict(input);
            switch (alt12) {
                case 1 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1761:9: INT ( 'j' | 'J' )
                    {
                    mINT(); 
                    if ( input.LA(1)=='J'||input.LA(1)=='j' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse =
                            new MismatchedSetException(null,input);
                        recover(mse);    throw mse;
                    }


                    }
                    break;
                case 2 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1762:9: FLOAT ( 'j' | 'J' )
                    {
                    mFLOAT(); 
                    if ( input.LA(1)=='J'||input.LA(1)=='j' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse =
                            new MismatchedSetException(null,input);
                        recover(mse);    throw mse;
                    }


                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COMPLEX

    // $ANTLR start DIGITS
    public final void mDIGITS() throws RecognitionException {
        try {
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1766:8: ( ( '0' .. '9' )+ )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1766:10: ( '0' .. '9' )+
            {
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1766:10: ( '0' .. '9' )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>='0' && LA13_0<='9')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1766:12: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end DIGITS

    // $ANTLR start NAME
    public final void mNAME() throws RecognitionException {
        try {
            int _type = NAME;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1768:5: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1768:7: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1769:9: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>='0' && LA14_0<='9')||(LA14_0>='A' && LA14_0<='Z')||LA14_0=='_'||(LA14_0>='a' && LA14_0<='z')) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NAME

    // $ANTLR start STRING
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1776:5: ( ( 'r' | 'u' | 'ur' )? ( '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\'' | '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"' | '\"' ( ESC | ~ ( '\\\\' | '\\n' | '\"' ) )* '\"' | '\\'' ( ESC | ~ ( '\\\\' | '\\n' | '\\'' ) )* '\\'' ) )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1776:9: ( 'r' | 'u' | 'ur' )? ( '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\'' | '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"' | '\"' ( ESC | ~ ( '\\\\' | '\\n' | '\"' ) )* '\"' | '\\'' ( ESC | ~ ( '\\\\' | '\\n' | '\\'' ) )* '\\'' )
            {
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1776:9: ( 'r' | 'u' | 'ur' )?
            int alt15=4;
            int LA15_0 = input.LA(1);

            if ( (LA15_0=='r') ) {
                alt15=1;
            }
            else if ( (LA15_0=='u') ) {
                int LA15_2 = input.LA(2);

                if ( (LA15_2=='r') ) {
                    alt15=3;
                }
                else if ( (LA15_2=='\"'||LA15_2=='\'') ) {
                    alt15=2;
                }
            }
            switch (alt15) {
                case 1 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1776:10: 'r'
                    {
                    match('r'); 

                    }
                    break;
                case 2 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1776:14: 'u'
                    {
                    match('u'); 

                    }
                    break;
                case 3 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1776:18: 'ur'
                    {
                    match("ur"); 


                    }
                    break;

            }

            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1777:9: ( '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\'' | '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"' | '\"' ( ESC | ~ ( '\\\\' | '\\n' | '\"' ) )* '\"' | '\\'' ( ESC | ~ ( '\\\\' | '\\n' | '\\'' ) )* '\\'' )
            int alt20=4;
            int LA20_0 = input.LA(1);

            if ( (LA20_0=='\'') ) {
                int LA20_1 = input.LA(2);

                if ( (LA20_1=='\'') ) {
                    int LA20_3 = input.LA(3);

                    if ( (LA20_3=='\'') ) {
                        alt20=1;
                    }
                    else {
                        alt20=4;}
                }
                else if ( ((LA20_1>='\u0000' && LA20_1<='\t')||(LA20_1>='\u000B' && LA20_1<='&')||(LA20_1>='(' && LA20_1<='\uFFFE')) ) {
                    alt20=4;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1777:9: ( '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\'' | '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"' | '\"' ( ESC | ~ ( '\\\\' | '\\n' | '\"' ) )* '\"' | '\\'' ( ESC | ~ ( '\\\\' | '\\n' | '\\'' ) )* '\\'' )", 20, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA20_0=='\"') ) {
                int LA20_2 = input.LA(2);

                if ( (LA20_2=='\"') ) {
                    int LA20_5 = input.LA(3);

                    if ( (LA20_5=='\"') ) {
                        alt20=2;
                    }
                    else {
                        alt20=3;}
                }
                else if ( ((LA20_2>='\u0000' && LA20_2<='\t')||(LA20_2>='\u000B' && LA20_2<='!')||(LA20_2>='#' && LA20_2<='\uFFFE')) ) {
                    alt20=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1777:9: ( '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\'' | '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"' | '\"' ( ESC | ~ ( '\\\\' | '\\n' | '\"' ) )* '\"' | '\\'' ( ESC | ~ ( '\\\\' | '\\n' | '\\'' ) )* '\\'' )", 20, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1777:9: ( '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\'' | '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"' | '\"' ( ESC | ~ ( '\\\\' | '\\n' | '\"' ) )* '\"' | '\\'' ( ESC | ~ ( '\\\\' | '\\n' | '\\'' ) )* '\\'' )", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1777:13: '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\''
                    {
                    match("\'\'\'"); 

                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1777:22: ( options {greedy=false; } : . )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0=='\'') ) {
                            int LA16_1 = input.LA(2);

                            if ( (LA16_1=='\'') ) {
                                int LA16_3 = input.LA(3);

                                if ( (LA16_3=='\'') ) {
                                    alt16=2;
                                }
                                else if ( ((LA16_3>='\u0000' && LA16_3<='&')||(LA16_3>='(' && LA16_3<='\uFFFE')) ) {
                                    alt16=1;
                                }


                            }
                            else if ( ((LA16_1>='\u0000' && LA16_1<='&')||(LA16_1>='(' && LA16_1<='\uFFFE')) ) {
                                alt16=1;
                            }


                        }
                        else if ( ((LA16_0>='\u0000' && LA16_0<='&')||(LA16_0>='(' && LA16_0<='\uFFFE')) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1777:47: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);

                    match("\'\'\'"); 


                    }
                    break;
                case 2 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1778:13: '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"'
                    {
                    match("\"\"\""); 

                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1778:19: ( options {greedy=false; } : . )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0=='\"') ) {
                            int LA17_1 = input.LA(2);

                            if ( (LA17_1=='\"') ) {
                                int LA17_3 = input.LA(3);

                                if ( (LA17_3=='\"') ) {
                                    alt17=2;
                                }
                                else if ( ((LA17_3>='\u0000' && LA17_3<='!')||(LA17_3>='#' && LA17_3<='\uFFFE')) ) {
                                    alt17=1;
                                }


                            }
                            else if ( ((LA17_1>='\u0000' && LA17_1<='!')||(LA17_1>='#' && LA17_1<='\uFFFE')) ) {
                                alt17=1;
                            }


                        }
                        else if ( ((LA17_0>='\u0000' && LA17_0<='!')||(LA17_0>='#' && LA17_0<='\uFFFE')) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1778:44: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);

                    match("\"\"\""); 


                    }
                    break;
                case 3 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1779:13: '\"' ( ESC | ~ ( '\\\\' | '\\n' | '\"' ) )* '\"'
                    {
                    match('\"'); 
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1779:17: ( ESC | ~ ( '\\\\' | '\\n' | '\"' ) )*
                    loop18:
                    do {
                        int alt18=3;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0=='\\') ) {
                            alt18=1;
                        }
                        else if ( ((LA18_0>='\u0000' && LA18_0<='\t')||(LA18_0>='\u000B' && LA18_0<='!')||(LA18_0>='#' && LA18_0<='[')||(LA18_0>=']' && LA18_0<='\uFFFE')) ) {
                            alt18=2;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1779:18: ESC
                    	    {
                    	    mESC(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1779:22: ~ ( '\\\\' | '\\n' | '\"' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 4 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1780:13: '\\'' ( ESC | ~ ( '\\\\' | '\\n' | '\\'' ) )* '\\''
                    {
                    match('\''); 
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1780:18: ( ESC | ~ ( '\\\\' | '\\n' | '\\'' ) )*
                    loop19:
                    do {
                        int alt19=3;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0=='\\') ) {
                            alt19=1;
                        }
                        else if ( ((LA19_0>='\u0000' && LA19_0<='\t')||(LA19_0>='\u000B' && LA19_0<='&')||(LA19_0>='(' && LA19_0<='[')||(LA19_0>=']' && LA19_0<='\uFFFE')) ) {
                            alt19=2;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1780:19: ESC
                    	    {
                    	    mESC(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1780:23: ~ ( '\\\\' | '\\n' | '\\'' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop19;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STRING

    // $ANTLR start ESC
    public final void mESC() throws RecognitionException {
        try {
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1785:2: ( '\\\\' . )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1785:4: '\\\\' .
            {
            match('\\'); 
            matchAny(); 

            }

        }
        finally {
        }
    }
    // $ANTLR end ESC

    // $ANTLR start CONTINUED_LINE
    public final void mCONTINUED_LINE() throws RecognitionException {
        try {
            int _type = CONTINUED_LINE;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1790:2: ( '\\\\' ( '\\r' )? '\\n' ( ' ' | '\\t' )* )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1790:4: '\\\\' ( '\\r' )? '\\n' ( ' ' | '\\t' )*
            {
            match('\\'); 
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1790:9: ( '\\r' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0=='\r') ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1790:10: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1790:22: ( ' ' | '\\t' )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0=='\t'||LA22_0==' ') ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:
            	    {
            	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


            		 //fModule.acceptLine(getColumn());
            		 /*newline();*/ channel=HIDDEN; 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CONTINUED_LINE

    // $ANTLR start WS
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1795:4: ({...}? => ( ' ' | '\\t' )+ )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1795:6: {...}? => ( ' ' | '\\t' )+
            {
            if ( !(startPos>0) ) {
                throw new FailedPredicateException(input, "WS", "startPos>0");
            }
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1795:22: ( ' ' | '\\t' )+
            int cnt23=0;
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0=='\t'||LA23_0==' ') ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:
            	    {
            	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt23 >= 1 ) break loop23;
                        EarlyExitException eee =
                            new EarlyExitException(23, input);
                        throw eee;
                }
                cnt23++;
            } while (true);

            channel=HIDDEN;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WS

    // $ANTLR start LEADING_WS
    public final void mLEADING_WS() throws RecognitionException {
        try {
            int _type = LEADING_WS;

                int spaces = 0;

            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1807:5: ({...}? => ({...}? ( ' ' | '\\t' )+ | ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )* ) )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1807:9: {...}? => ({...}? ( ' ' | '\\t' )+ | ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )* )
            {
            if ( !(startPos==0) ) {
                throw new FailedPredicateException(input, "LEADING_WS", "startPos==0");
            }
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1808:6: ({...}? ( ' ' | '\\t' )+ | ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )* )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==' ') ) {
                int LA28_1 = input.LA(2);

                if ( (implicitLineJoiningLevel>0) ) {
                    alt28=1;
                }
                else if ( (true) ) {
                    alt28=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1808:6: ({...}? ( ' ' | '\\t' )+ | ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )* )", 28, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA28_0=='\t') ) {
                int LA28_2 = input.LA(2);

                if ( (implicitLineJoiningLevel>0) ) {
                    alt28=1;
                }
                else if ( (true) ) {
                    alt28=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1808:6: ({...}? ( ' ' | '\\t' )+ | ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )* )", 28, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1808:6: ({...}? ( ' ' | '\\t' )+ | ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )* )", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1808:10: {...}? ( ' ' | '\\t' )+
                    {
                    if ( !(implicitLineJoiningLevel>0) ) {
                        throw new FailedPredicateException(input, "LEADING_WS", "implicitLineJoiningLevel>0");
                    }
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1808:40: ( ' ' | '\\t' )+
                    int cnt24=0;
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0=='\t'||LA24_0==' ') ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:
                    	    {
                    	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt24 >= 1 ) break loop24;
                                EarlyExitException eee =
                                    new EarlyExitException(24, input);
                                throw eee;
                        }
                        cnt24++;
                    } while (true);

                    channel=HIDDEN;

                    }
                    break;
                case 2 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1809:11: ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )*
                    {
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1809:11: ( ' ' | '\\t' )+
                    int cnt25=0;
                    loop25:
                    do {
                        int alt25=3;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==' ') ) {
                            alt25=1;
                        }
                        else if ( (LA25_0=='\t') ) {
                            alt25=2;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1809:14: ' '
                    	    {
                    	    match(' '); 
                    	     spaces++; 

                    	    }
                    	    break;
                    	case 2 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1810:12: '\\t'
                    	    {
                    	    match('\t'); 
                    	     spaces += 8; spaces -= (spaces % 8); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt25 >= 1 ) break loop25;
                                EarlyExitException eee =
                                    new EarlyExitException(25, input);
                                throw eee;
                        }
                        cnt25++;
                    } while (true);


                                // make a string of n spaces where n is column number - 1
                                char[] indentation = new char[spaces];
                                for (int i=0; i<spaces; i++) {
                                    indentation[i] = ' ';
                                }
                                String s = new String(indentation);
                                Token tok = new ClassicToken(LEADING_WS,new String(indentation));
                                emit(tok);
                            
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1823:10: ( ( '\\r' )? '\\n' )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0=='\n'||LA27_0=='\r') ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1823:12: ( '\\r' )? '\\n'
                    	    {
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1823:12: ( '\\r' )?
                    	    int alt26=2;
                    	    int LA26_0 = input.LA(1);

                    	    if ( (LA26_0=='\r') ) {
                    	        alt26=1;
                    	    }
                    	    switch (alt26) {
                    	        case 1 :
                    	            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1823:13: '\\r'
                    	            {
                    	            match('\r'); 

                    	            }
                    	            break;

                    	    }

                    	    match('\n'); 
                    	    if (token!=null) token.setChannel(99); else channel=HIDDEN;

                    	    }
                    	    break;

                    	default :
                    	    break loop27;
                        }
                    } while (true);


                    }
                    break;

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LEADING_WS

    // $ANTLR start COMMENT
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;

                channel=HIDDEN;

            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1861:5: ({...}? => ( ' ' | '\\t' )* '#' (~ '\\n' )* ( '\\n' )+ | {...}? => '#' (~ '\\n' )* )
            int alt33=2;
            alt33 = dfa33.predict(input);
            switch (alt33) {
                case 1 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1861:7: {...}? => ( ' ' | '\\t' )* '#' (~ '\\n' )* ( '\\n' )+
                    {
                    if ( !(startPos==0) ) {
                        throw new FailedPredicateException(input, "COMMENT", "startPos==0");
                    }
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1861:24: ( ' ' | '\\t' )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0=='\t'||LA29_0==' ') ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:
                    	    {
                    	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);

                    match('#'); 
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1861:40: (~ '\\n' )*
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( ((LA30_0>='\u0000' && LA30_0<='\t')||(LA30_0>='\u000B' && LA30_0<='\uFFFE')) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1861:41: ~ '\\n'
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop30;
                        }
                    } while (true);

                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1861:49: ( '\\n' )+
                    int cnt31=0;
                    loop31:
                    do {
                        int alt31=2;
                        int LA31_0 = input.LA(1);

                        if ( (LA31_0=='\n') ) {
                            alt31=1;
                        }


                        switch (alt31) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1861:49: '\\n'
                    	    {
                    	    match('\n'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt31 >= 1 ) break loop31;
                                EarlyExitException eee =
                                    new EarlyExitException(31, input);
                                throw eee;
                        }
                        cnt31++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1862:7: {...}? => '#' (~ '\\n' )*
                    {
                    if ( !(startPos>0) ) {
                        throw new FailedPredicateException(input, "COMMENT", "startPos>0");
                    }
                    match('#'); 
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1862:27: (~ '\\n' )*
                    loop32:
                    do {
                        int alt32=2;
                        int LA32_0 = input.LA(1);

                        if ( ((LA32_0>='\u0000' && LA32_0<='\t')||(LA32_0>='\u000B' && LA32_0<='\uFFFE')) ) {
                            alt32=1;
                        }


                        switch (alt32) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1862:28: ~ '\\n'
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop32;
                        }
                    } while (true);


                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COMMENT

    // $ANTLR start DECORATOR_S
    public final void mDECORATOR_S() throws RecognitionException {
        try {
            int _type = DECORATOR_S;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1878:12: ( '@' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1879:2: '@'
            {
            match('@'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DECORATOR_S

    // $ANTLR start NEWLINE
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1886:5: ( ( ( '\\r' )? '\\n' )+ )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1886:9: ( ( '\\r' )? '\\n' )+
            {
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1886:9: ( ( '\\r' )? '\\n' )+
            int cnt35=0;
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0=='\n'||LA35_0=='\r') ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1886:10: ( '\\r' )? '\\n'
            	    {
            	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1886:10: ( '\\r' )?
            	    int alt34=2;
            	    int LA34_0 = input.LA(1);

            	    if ( (LA34_0=='\r') ) {
            	        alt34=1;
            	    }
            	    switch (alt34) {
            	        case 1 :
            	            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1886:11: '\\r'
            	            {
            	            match('\r'); 

            	            }
            	            break;

            	    }

            	    match('\n'); 

            	    }
            	    break;

            	default :
            	    if ( cnt35 >= 1 ) break loop35;
                        EarlyExitException eee =
                            new EarlyExitException(35, input);
                        throw eee;
                }
                cnt35++;
            } while (true);

            if ( startPos==0 || implicitLineJoiningLevel>0 )
                        channel=HIDDEN;
                    

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NEWLINE

    public void mTokens() throws RecognitionException {
        // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:8: ( T65 | T66 | T67 | T68 | T69 | T70 | T71 | T72 | T73 | T74 | T75 | T76 | T77 | T78 | T79 | T80 | T81 | T82 | T83 | T84 | T85 | T86 | T87 | T88 | T89 | T90 | T91 | T92 | T93 | T94 | LPAREN | RPAREN | LBRACK | RBRACK | COLON | COMMA | SEMI | PLUS | MINUS | STAR | SLASH | VBAR | AMPER | LESS | GREATER | ASSIGN | PERCENT | BACKQUOTE | LCURLY | RCURLY | CIRCUMFLEX | TILDE | EQUAL | NOTEQUAL | ALT_NOTEQUAL | LESSEQUAL | LEFTSHIFT | GREATEREQUAL | RIGHTSHIFT | PLUSEQUAL | MINUSEQUAL | DOUBLESTAR | STAREQUAL | DOUBLESLASH | SLASHEQUAL | VBAREQUAL | PERCENTEQUAL | AMPEREQUAL | CIRCUMFLEXEQUAL | LEFTSHIFTEQUAL | RIGHTSHIFTEQUAL | DOUBLESTAREQUAL | DOUBLESLASHEQUAL | DOT | FLOAT | LONGINT | INT | COMPLEX | NAME | STRING | CONTINUED_LINE | WS | LEADING_WS | COMMENT | DECORATOR_S | NEWLINE )
        int alt36=86;
        alt36 = dfa36.predict(input);
        switch (alt36) {
            case 1 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:10: T65
                {
                mT65(); 

                }
                break;
            case 2 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:14: T66
                {
                mT66(); 

                }
                break;
            case 3 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:18: T67
                {
                mT67(); 

                }
                break;
            case 4 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:22: T68
                {
                mT68(); 

                }
                break;
            case 5 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:26: T69
                {
                mT69(); 

                }
                break;
            case 6 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:30: T70
                {
                mT70(); 

                }
                break;
            case 7 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:34: T71
                {
                mT71(); 

                }
                break;
            case 8 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:38: T72
                {
                mT72(); 

                }
                break;
            case 9 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:42: T73
                {
                mT73(); 

                }
                break;
            case 10 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:46: T74
                {
                mT74(); 

                }
                break;
            case 11 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:50: T75
                {
                mT75(); 

                }
                break;
            case 12 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:54: T76
                {
                mT76(); 

                }
                break;
            case 13 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:58: T77
                {
                mT77(); 

                }
                break;
            case 14 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:62: T78
                {
                mT78(); 

                }
                break;
            case 15 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:66: T79
                {
                mT79(); 

                }
                break;
            case 16 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:70: T80
                {
                mT80(); 

                }
                break;
            case 17 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:74: T81
                {
                mT81(); 

                }
                break;
            case 18 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:78: T82
                {
                mT82(); 

                }
                break;
            case 19 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:82: T83
                {
                mT83(); 

                }
                break;
            case 20 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:86: T84
                {
                mT84(); 

                }
                break;
            case 21 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:90: T85
                {
                mT85(); 

                }
                break;
            case 22 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:94: T86
                {
                mT86(); 

                }
                break;
            case 23 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:98: T87
                {
                mT87(); 

                }
                break;
            case 24 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:102: T88
                {
                mT88(); 

                }
                break;
            case 25 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:106: T89
                {
                mT89(); 

                }
                break;
            case 26 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:110: T90
                {
                mT90(); 

                }
                break;
            case 27 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:114: T91
                {
                mT91(); 

                }
                break;
            case 28 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:118: T92
                {
                mT92(); 

                }
                break;
            case 29 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:122: T93
                {
                mT93(); 

                }
                break;
            case 30 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:126: T94
                {
                mT94(); 

                }
                break;
            case 31 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:130: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 32 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:137: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 33 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:144: LBRACK
                {
                mLBRACK(); 

                }
                break;
            case 34 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:151: RBRACK
                {
                mRBRACK(); 

                }
                break;
            case 35 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:158: COLON
                {
                mCOLON(); 

                }
                break;
            case 36 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:164: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 37 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:170: SEMI
                {
                mSEMI(); 

                }
                break;
            case 38 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:175: PLUS
                {
                mPLUS(); 

                }
                break;
            case 39 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:180: MINUS
                {
                mMINUS(); 

                }
                break;
            case 40 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:186: STAR
                {
                mSTAR(); 

                }
                break;
            case 41 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:191: SLASH
                {
                mSLASH(); 

                }
                break;
            case 42 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:197: VBAR
                {
                mVBAR(); 

                }
                break;
            case 43 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:202: AMPER
                {
                mAMPER(); 

                }
                break;
            case 44 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:208: LESS
                {
                mLESS(); 

                }
                break;
            case 45 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:213: GREATER
                {
                mGREATER(); 

                }
                break;
            case 46 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:221: ASSIGN
                {
                mASSIGN(); 

                }
                break;
            case 47 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:228: PERCENT
                {
                mPERCENT(); 

                }
                break;
            case 48 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:236: BACKQUOTE
                {
                mBACKQUOTE(); 

                }
                break;
            case 49 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:246: LCURLY
                {
                mLCURLY(); 

                }
                break;
            case 50 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:253: RCURLY
                {
                mRCURLY(); 

                }
                break;
            case 51 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:260: CIRCUMFLEX
                {
                mCIRCUMFLEX(); 

                }
                break;
            case 52 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:271: TILDE
                {
                mTILDE(); 

                }
                break;
            case 53 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:277: EQUAL
                {
                mEQUAL(); 

                }
                break;
            case 54 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:283: NOTEQUAL
                {
                mNOTEQUAL(); 

                }
                break;
            case 55 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:292: ALT_NOTEQUAL
                {
                mALT_NOTEQUAL(); 

                }
                break;
            case 56 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:305: LESSEQUAL
                {
                mLESSEQUAL(); 

                }
                break;
            case 57 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:315: LEFTSHIFT
                {
                mLEFTSHIFT(); 

                }
                break;
            case 58 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:325: GREATEREQUAL
                {
                mGREATEREQUAL(); 

                }
                break;
            case 59 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:338: RIGHTSHIFT
                {
                mRIGHTSHIFT(); 

                }
                break;
            case 60 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:349: PLUSEQUAL
                {
                mPLUSEQUAL(); 

                }
                break;
            case 61 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:359: MINUSEQUAL
                {
                mMINUSEQUAL(); 

                }
                break;
            case 62 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:370: DOUBLESTAR
                {
                mDOUBLESTAR(); 

                }
                break;
            case 63 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:381: STAREQUAL
                {
                mSTAREQUAL(); 

                }
                break;
            case 64 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:391: DOUBLESLASH
                {
                mDOUBLESLASH(); 

                }
                break;
            case 65 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:403: SLASHEQUAL
                {
                mSLASHEQUAL(); 

                }
                break;
            case 66 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:414: VBAREQUAL
                {
                mVBAREQUAL(); 

                }
                break;
            case 67 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:424: PERCENTEQUAL
                {
                mPERCENTEQUAL(); 

                }
                break;
            case 68 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:437: AMPEREQUAL
                {
                mAMPEREQUAL(); 

                }
                break;
            case 69 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:448: CIRCUMFLEXEQUAL
                {
                mCIRCUMFLEXEQUAL(); 

                }
                break;
            case 70 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:464: LEFTSHIFTEQUAL
                {
                mLEFTSHIFTEQUAL(); 

                }
                break;
            case 71 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:479: RIGHTSHIFTEQUAL
                {
                mRIGHTSHIFTEQUAL(); 

                }
                break;
            case 72 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:495: DOUBLESTAREQUAL
                {
                mDOUBLESTAREQUAL(); 

                }
                break;
            case 73 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:511: DOUBLESLASHEQUAL
                {
                mDOUBLESLASHEQUAL(); 

                }
                break;
            case 74 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:528: DOT
                {
                mDOT(); 

                }
                break;
            case 75 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:532: FLOAT
                {
                mFLOAT(); 

                }
                break;
            case 76 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:538: LONGINT
                {
                mLONGINT(); 

                }
                break;
            case 77 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:546: INT
                {
                mINT(); 

                }
                break;
            case 78 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:550: COMPLEX
                {
                mCOMPLEX(); 

                }
                break;
            case 79 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:558: NAME
                {
                mNAME(); 

                }
                break;
            case 80 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:563: STRING
                {
                mSTRING(); 

                }
                break;
            case 81 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:570: CONTINUED_LINE
                {
                mCONTINUED_LINE(); 

                }
                break;
            case 82 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:585: WS
                {
                mWS(); 

                }
                break;
            case 83 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:588: LEADING_WS
                {
                mLEADING_WS(); 

                }
                break;
            case 84 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:599: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 85 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:607: DECORATOR_S
                {
                mDECORATOR_S(); 

                }
                break;
            case 86 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:619: NEWLINE
                {
                mNEWLINE(); 

                }
                break;

        }

    }


    protected DFA12 dfa12 = new DFA12(this);
    protected DFA33 dfa33 = new DFA33(this);
    protected DFA36 dfa36 = new DFA36(this);
    static final String DFA12_eotS =
        "\7\uffff";
    static final String DFA12_eofS =
        "\7\uffff";
    static final String DFA12_minS =
        "\3\56\2\uffff\2\56";
    static final String DFA12_maxS =
        "\1\71\1\170\1\152\2\uffff\2\152";
    static final String DFA12_acceptS =
        "\3\uffff\1\2\1\1\2\uffff";
    static final String DFA12_specialS =
        "\7\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\3\1\uffff\1\1\11\2",
            "\1\3\1\uffff\12\5\13\uffff\1\3\4\uffff\1\4\15\uffff\1\4\14\uffff"+
            "\1\3\4\uffff\1\4\15\uffff\1\4",
            "\1\3\1\uffff\12\6\13\uffff\1\3\4\uffff\1\4\32\uffff\1\3\4\uffff"+
            "\1\4",
            "",
            "",
            "\1\3\1\uffff\12\5\13\uffff\1\3\4\uffff\1\4\32\uffff\1\3\4\uffff"+
            "\1\4",
            "\1\3\1\uffff\12\6\13\uffff\1\3\4\uffff\1\4\32\uffff\1\3\4\uffff"+
            "\1\4"
    };

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        public String getDescription() {
            return "1760:1: COMPLEX : ( INT ( 'j' | 'J' ) | FLOAT ( 'j' | 'J' ) );";
        }
    }
    static final String DFA33_eotS =
        "\2\uffff\2\4\1\uffff";
    static final String DFA33_eofS =
        "\5\uffff";
    static final String DFA33_minS =
        "\1\11\1\uffff\2\0\1\uffff";
    static final String DFA33_maxS =
        "\1\43\1\uffff\2\ufffe\1\uffff";
    static final String DFA33_acceptS =
        "\1\uffff\1\1\2\uffff\1\2";
    static final String DFA33_specialS =
        "\1\0\1\uffff\1\1\1\2\1\uffff}>";
    static final String[] DFA33_transitionS = {
            "\1\1\26\uffff\1\1\2\uffff\1\2",
            "",
            "\12\3\1\1\ufff4\3",
            "\12\3\1\1\ufff4\3",
            ""
    };

    static final short[] DFA33_eot = DFA.unpackEncodedString(DFA33_eotS);
    static final short[] DFA33_eof = DFA.unpackEncodedString(DFA33_eofS);
    static final char[] DFA33_min = DFA.unpackEncodedStringToUnsignedChars(DFA33_minS);
    static final char[] DFA33_max = DFA.unpackEncodedStringToUnsignedChars(DFA33_maxS);
    static final short[] DFA33_accept = DFA.unpackEncodedString(DFA33_acceptS);
    static final short[] DFA33_special = DFA.unpackEncodedString(DFA33_specialS);
    static final short[][] DFA33_transition;

    static {
        int numStates = DFA33_transitionS.length;
        DFA33_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA33_transition[i] = DFA.unpackEncodedString(DFA33_transitionS[i]);
        }
    }

    class DFA33 extends DFA {

        public DFA33(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 33;
            this.eot = DFA33_eot;
            this.eof = DFA33_eof;
            this.min = DFA33_min;
            this.max = DFA33_max;
            this.accept = DFA33_accept;
            this.special = DFA33_special;
            this.transition = DFA33_transition;
        }
        public String getDescription() {
            return "1837:1: COMMENT : ({...}? => ( ' ' | '\\t' )* '#' (~ '\\n' )* ( '\\n' )+ | {...}? => '#' (~ '\\n' )* );";
        }
        public int specialStateTransition(int s, IntStream input) throws NoViableAltException {
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA33_0 = input.LA(1);

                         
                        int index33_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA33_0=='\t'||LA33_0==' ') && (startPos==0)) {s = 1;}

                        else if ( (LA33_0=='#') && ((startPos>0||startPos==0))) {s = 2;}

                         
                        input.seek(index33_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA33_2 = input.LA(1);

                         
                        int index33_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA33_2>='\u0000' && LA33_2<='\t')||(LA33_2>='\u000B' && LA33_2<='\uFFFE')) && ((startPos>0||startPos==0))) {s = 3;}

                        else if ( (LA33_2=='\n') && (startPos==0)) {s = 1;}

                        else s = 4;

                         
                        input.seek(index33_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA33_3 = input.LA(1);

                         
                        int index33_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA33_3>='\u0000' && LA33_3<='\t')||(LA33_3>='\u000B' && LA33_3<='\uFFFE')) && ((startPos>0||startPos==0))) {s = 3;}

                        else if ( (LA33_3=='\n') && (startPos==0)) {s = 1;}

                        else s = 4;

                         
                        input.seek(index33_3);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 33, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA36_eotS =
        "\1\uffff\20\54\7\uffff\1\117\1\121\1\124\1\127\1\131\1\133\1\137"+
        "\1\142\1\144\1\146\3\uffff\1\150\2\uffff\1\152\2\154\1\54\3\uffff"+
        "\1\165\1\167\3\uffff\11\54\1\u0082\1\u0083\1\54\1\u0085\3\54\1\u008a"+
        "\6\54\1\u0093\2\54\5\uffff\1\u0097\2\uffff\1\u0099\5\uffff\1\u009b"+
        "\4\uffff\1\u009d\7\uffff\1\u009e\3\uffff\1\u009e\1\uffff\1\154\2"+
        "\uffff\1\154\1\54\4\uffff\1\u00a5\1\u00a6\10\54\2\uffff\1\54\1\uffff"+
        "\2\54\1\u00b2\1\54\1\uffff\1\u00b4\6\54\1\u00bb\1\uffff\1\u00bc"+
        "\1\54\12\uffff\1\154\1\u009e\1\uffff\1\u009e\3\uffff\1\u00c2\11"+
        "\54\1\u00cc\1\uffff\1\54\1\uffff\1\54\1\u00cf\1\54\1\u00d1\1\u00d2"+
        "\1\54\2\uffff\1\54\1\uffff\1\u009e\1\161\2\uffff\1\u00d7\1\u00d8"+
        "\1\54\1\u00da\1\54\1\u00dc\1\u00dd\2\54\1\uffff\2\54\1\uffff\1\54"+
        "\2\uffff\1\u00e3\1\54\1\uffff\1\u009e\2\uffff\1\54\1\uffff\1\u00e6"+
        "\2\uffff\1\u00e7\1\54\1\u00e9\1\u00ea\1\u00eb\1\uffff\1\u00ec\1"+
        "\54\2\uffff\1\u00ee\4\uffff\1\u00ef\2\uffff";
    static final String DFA36_eofS =
        "\u00f0\uffff";
    static final String DFA36_minS =
        "\1\11\1\145\1\141\1\162\1\154\1\42\1\151\1\146\1\151\1\156\2\154"+
        "\1\150\2\162\1\157\1\141\7\uffff\2\75\1\52\1\57\2\75\1\74\3\75\3"+
        "\uffff\1\75\2\uffff\1\60\2\56\1\42\3\uffff\2\11\3\uffff\1\146\1"+
        "\163\1\151\1\145\1\156\1\141\1\164\1\151\1\145\2\60\1\160\1\60\1"+
        "\156\1\157\1\162\1\60\1\144\1\157\1\143\2\151\1\171\1\60\1\164\1"+
        "\155\5\uffff\1\75\2\uffff\1\75\5\uffff\1\75\4\uffff\1\75\7\uffff"+
        "\1\60\1\uffff\1\60\1\uffff\1\60\1\53\1\56\2\uffff\1\56\1\42\1\uffff"+
        "\1\0\1\uffff\1\0\2\60\1\163\1\156\1\141\1\164\1\163\1\165\1\163"+
        "\1\154\2\uffff\1\157\1\uffff\1\141\1\155\1\60\1\145\1\uffff\1\60"+
        "\1\142\1\143\2\145\1\146\1\154\1\60\1\uffff\1\60\1\142\11\uffff"+
        "\1\53\4\60\3\uffff\1\60\1\164\1\153\1\151\1\163\1\162\1\145\1\144"+
        "\1\162\1\154\1\60\1\uffff\1\162\1\uffff\1\141\1\60\1\160\2\60\1"+
        "\145\2\uffff\1\144\2\60\1\112\1\53\1\uffff\2\60\1\156\1\60\1\156"+
        "\2\60\1\164\1\154\1\uffff\1\164\1\154\1\uffff\1\164\2\uffff\1\60"+
        "\1\141\2\60\2\uffff\1\165\1\uffff\1\60\2\uffff\1\60\1\171\3\60\1"+
        "\uffff\1\60\1\145\2\uffff\1\60\4\uffff\1\60\2\uffff";
    static final String DFA36_maxS =
        "\1\176\1\145\2\162\1\157\1\145\1\151\1\163\1\162\1\163\1\154\1\170"+
        "\1\150\2\162\1\157\1\141\7\uffff\6\75\2\76\2\75\3\uffff\1\75\2\uffff"+
        "\1\71\1\170\1\154\1\162\3\uffff\2\43\3\uffff\1\154\1\163\1\151\1"+
        "\145\1\156\1\141\1\164\1\151\1\145\2\172\1\160\1\172\1\156\1\157"+
        "\1\162\1\172\1\144\1\157\1\145\1\163\1\151\1\171\1\172\1\164\1\155"+
        "\5\uffff\1\75\2\uffff\1\75\5\uffff\1\75\4\uffff\1\75\7\uffff\1\152"+
        "\1\uffff\1\146\1\uffff\1\152\1\71\1\154\2\uffff\1\154\1\47\1\uffff"+
        "\1\0\1\uffff\1\0\2\172\1\163\1\156\1\141\1\164\1\163\1\165\1\163"+
        "\1\154\2\uffff\1\157\1\uffff\1\141\1\155\1\172\1\145\1\uffff\1\172"+
        "\1\142\1\143\2\145\1\146\1\154\1\172\1\uffff\1\172\1\142\11\uffff"+
        "\1\71\1\154\1\152\1\71\1\152\3\uffff\1\172\1\164\1\153\1\151\1\163"+
        "\1\162\1\145\1\144\1\162\1\154\1\172\1\uffff\1\162\1\uffff\1\141"+
        "\1\172\1\160\2\172\1\145\2\uffff\1\144\1\71\2\152\1\71\1\uffff\2"+
        "\172\1\156\1\172\1\156\2\172\1\164\1\154\1\uffff\1\164\1\154\1\uffff"+
        "\1\164\2\uffff\1\172\1\141\1\71\1\152\2\uffff\1\165\1\uffff\1\172"+
        "\2\uffff\1\172\1\171\3\172\1\uffff\1\172\1\145\2\uffff\1\172\4\uffff"+
        "\1\172\2\uffff";
    static final String DFA36_acceptS =
        "\21\uffff\1\37\1\40\1\41\1\42\1\43\1\44\1\45\12\uffff\1\60\1\61"+
        "\1\62\1\uffff\1\64\1\66\4\uffff\1\117\1\120\1\121\2\uffff\1\124"+
        "\1\125\1\126\32\uffff\1\74\1\46\1\75\1\47\1\77\1\uffff\1\50\1\101"+
        "\1\uffff\1\51\1\102\1\52\1\104\1\53\1\uffff\1\70\1\67\1\54\1\72"+
        "\1\uffff\1\55\1\65\1\56\1\103\1\57\1\105\1\63\1\uffff\1\112\1\uffff"+
        "\1\115\3\uffff\1\116\1\114\2\uffff\1\123\1\uffff\1\124\13\uffff"+
        "\1\21\1\34\1\uffff\1\17\4\uffff\1\14\10\uffff\1\31\2\uffff\1\110"+
        "\1\76\1\111\1\100\1\106\1\71\1\107\1\73\1\113\5\uffff\1\122\1\1"+
        "\1\3\13\uffff\1\25\1\uffff\1\32\6\uffff\1\26\1\33\5\uffff\1\4\11"+
        "\uffff\1\13\2\uffff\1\16\1\uffff\1\23\1\22\4\uffff\1\2\1\5\1\uffff"+
        "\1\36\1\uffff\1\11\1\10\5\uffff\1\24\2\uffff\1\7\1\12\1\uffff\1"+
        "\20\1\15\1\27\1\35\1\uffff\1\30\1\6";
    static final String DFA36_specialS =
        "\1\0\56\uffff\1\1\1\4\104\uffff\1\2\1\uffff\1\3\170\uffff}>";
    static final String[] DFA36_transitionS = {
            "\1\60\1\63\2\uffff\1\63\22\uffff\1\57\1\47\1\55\1\61\1\uffff"+
            "\1\41\1\35\1\55\1\21\1\22\1\32\1\30\1\26\1\31\1\50\1\33\1\51"+
            "\11\52\1\25\1\27\1\36\1\40\1\37\1\uffff\1\62\32\54\1\23\1\56"+
            "\1\24\1\45\1\54\1\42\1\11\1\3\1\4\1\1\1\13\1\10\1\12\1\54\1"+
            "\7\2\54\1\20\1\54\1\17\1\16\1\2\1\54\1\5\1\54\1\15\1\53\1\54"+
            "\1\14\1\54\1\6\1\54\1\43\1\34\1\44\1\46",
            "\1\64",
            "\1\65\20\uffff\1\66",
            "\1\67",
            "\1\71\2\uffff\1\70",
            "\1\55\4\uffff\1\55\71\uffff\1\73\3\uffff\1\72",
            "\1\74",
            "\1\75\6\uffff\1\77\1\100\4\uffff\1\76",
            "\1\101\5\uffff\1\103\2\uffff\1\102",
            "\1\105\4\uffff\1\104",
            "\1\106",
            "\1\110\13\uffff\1\107",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\116",
            "\1\120",
            "\1\123\22\uffff\1\122",
            "\1\126\15\uffff\1\125",
            "\1\130",
            "\1\132",
            "\1\134\1\135\1\136",
            "\1\140\1\141",
            "\1\143",
            "\1\145",
            "",
            "",
            "",
            "\1\147",
            "",
            "",
            "\12\151",
            "\1\155\1\uffff\12\157\13\uffff\1\156\4\uffff\1\160\1\uffff\1"+
            "\161\13\uffff\1\153\14\uffff\1\156\4\uffff\1\160\1\uffff\1\161"+
            "\13\uffff\1\153",
            "\1\155\1\uffff\12\162\13\uffff\1\156\4\uffff\1\160\1\uffff\1"+
            "\161\30\uffff\1\156\4\uffff\1\160\1\uffff\1\161",
            "\1\55\4\uffff\1\55\112\uffff\1\163",
            "",
            "",
            "",
            "\1\60\1\164\2\uffff\1\164\22\uffff\1\57\2\uffff\1\166",
            "\1\60\1\164\2\uffff\1\164\22\uffff\1\57\2\uffff\1\166",
            "",
            "",
            "",
            "\1\170\5\uffff\1\171",
            "\1\172",
            "\1\173",
            "\1\174",
            "\1\175",
            "\1\176",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u0084",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\22\54\1\u0089\7\54",
            "\1\u008b",
            "\1\u008c",
            "\1\u008e\1\uffff\1\u008d",
            "\1\u0090\11\uffff\1\u008f",
            "\1\u0091",
            "\1\u0092",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u0094",
            "\1\u0095",
            "",
            "",
            "",
            "",
            "",
            "\1\u0096",
            "",
            "",
            "\1\u0098",
            "",
            "",
            "",
            "",
            "",
            "\1\u009a",
            "",
            "",
            "",
            "",
            "\1\u009c",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\151\13\uffff\1\u009f\4\uffff\1\160\32\uffff\1\u009f\4\uffff"+
            "\1\160",
            "",
            "\12\u00a0\7\uffff\6\u00a0\32\uffff\6\u00a0",
            "",
            "\12\u00a1\20\uffff\1\160\37\uffff\1\160",
            "\1\u00a2\1\uffff\1\u00a2\2\uffff\12\u00a3",
            "\1\155\1\uffff\12\157\13\uffff\1\156\4\uffff\1\160\1\uffff\1"+
            "\161\30\uffff\1\156\4\uffff\1\160\1\uffff\1\161",
            "",
            "",
            "\1\155\1\uffff\12\162\13\uffff\1\156\4\uffff\1\160\1\uffff\1"+
            "\161\30\uffff\1\156\4\uffff\1\160\1\uffff\1\161",
            "\1\55\4\uffff\1\55",
            "",
            "\1\uffff",
            "",
            "\1\uffff",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00a7",
            "\1\u00a8",
            "\1\u00a9",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "\1\u00ad",
            "\1\u00ae",
            "",
            "",
            "\1\u00af",
            "",
            "\1\u00b0",
            "\1\u00b1",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00b3",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00b5",
            "\1\u00b6",
            "\1\u00b7",
            "\1\u00b8",
            "\1\u00b9",
            "\1\u00ba",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00bd",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u00be\1\uffff\1\u00be\2\uffff\12\u00bf",
            "\12\u00a0\7\uffff\6\u00a0\3\uffff\1\160\1\uffff\1\u00c0\24\uffff"+
            "\6\u00a0\3\uffff\1\160\1\uffff\1\u00c0",
            "\12\u00a1\13\uffff\1\u00c1\4\uffff\1\160\32\uffff\1\u00c1\4"+
            "\uffff\1\160",
            "\12\u00a3",
            "\12\u00a3\20\uffff\1\160\37\uffff\1\160",
            "",
            "",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00c3",
            "\1\u00c4",
            "\1\u00c5",
            "\1\u00c6",
            "\1\u00c7",
            "\1\u00c8",
            "\1\u00c9",
            "\1\u00ca",
            "\1\u00cb",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "\1\u00cd",
            "",
            "\1\u00ce",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00d0",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00d3",
            "",
            "",
            "\1\u00d4",
            "\12\u00bf",
            "\12\u00bf\20\uffff\1\160\37\uffff\1\160",
            "\1\160\37\uffff\1\160",
            "\1\u00d5\1\uffff\1\u00d5\2\uffff\12\u00d6",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00d9",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00db",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00de",
            "\1\u00df",
            "",
            "\1\u00e0",
            "\1\u00e1",
            "",
            "\1\u00e2",
            "",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00e4",
            "\12\u00d6",
            "\12\u00d6\20\uffff\1\160\37\uffff\1\160",
            "",
            "",
            "\1\u00e5",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00e8",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00ed",
            "",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "",
            "",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            ""
    };

    static final short[] DFA36_eot = DFA.unpackEncodedString(DFA36_eotS);
    static final short[] DFA36_eof = DFA.unpackEncodedString(DFA36_eofS);
    static final char[] DFA36_min = DFA.unpackEncodedStringToUnsignedChars(DFA36_minS);
    static final char[] DFA36_max = DFA.unpackEncodedStringToUnsignedChars(DFA36_maxS);
    static final short[] DFA36_accept = DFA.unpackEncodedString(DFA36_acceptS);
    static final short[] DFA36_special = DFA.unpackEncodedString(DFA36_specialS);
    static final short[][] DFA36_transition;

    static {
        int numStates = DFA36_transitionS.length;
        DFA36_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA36_transition[i] = DFA.unpackEncodedString(DFA36_transitionS[i]);
        }
    }

    class DFA36 extends DFA {

        public DFA36(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 36;
            this.eot = DFA36_eot;
            this.eof = DFA36_eof;
            this.min = DFA36_min;
            this.max = DFA36_max;
            this.accept = DFA36_accept;
            this.special = DFA36_special;
            this.transition = DFA36_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T65 | T66 | T67 | T68 | T69 | T70 | T71 | T72 | T73 | T74 | T75 | T76 | T77 | T78 | T79 | T80 | T81 | T82 | T83 | T84 | T85 | T86 | T87 | T88 | T89 | T90 | T91 | T92 | T93 | T94 | LPAREN | RPAREN | LBRACK | RBRACK | COLON | COMMA | SEMI | PLUS | MINUS | STAR | SLASH | VBAR | AMPER | LESS | GREATER | ASSIGN | PERCENT | BACKQUOTE | LCURLY | RCURLY | CIRCUMFLEX | TILDE | EQUAL | NOTEQUAL | ALT_NOTEQUAL | LESSEQUAL | LEFTSHIFT | GREATEREQUAL | RIGHTSHIFT | PLUSEQUAL | MINUSEQUAL | DOUBLESTAR | STAREQUAL | DOUBLESLASH | SLASHEQUAL | VBAREQUAL | PERCENTEQUAL | AMPEREQUAL | CIRCUMFLEXEQUAL | LEFTSHIFTEQUAL | RIGHTSHIFTEQUAL | DOUBLESTAREQUAL | DOUBLESLASHEQUAL | DOT | FLOAT | LONGINT | INT | COMPLEX | NAME | STRING | CONTINUED_LINE | WS | LEADING_WS | COMMENT | DECORATOR_S | NEWLINE );";
        }
        public int specialStateTransition(int s, IntStream input) throws NoViableAltException {
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA36_0 = input.LA(1);

                         
                        int index36_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA36_0=='d') ) {s = 1;}

                        else if ( (LA36_0=='p') ) {s = 2;}

                        else if ( (LA36_0=='b') ) {s = 3;}

                        else if ( (LA36_0=='c') ) {s = 4;}

                        else if ( (LA36_0=='r') ) {s = 5;}

                        else if ( (LA36_0=='y') ) {s = 6;}

                        else if ( (LA36_0=='i') ) {s = 7;}

                        else if ( (LA36_0=='f') ) {s = 8;}

                        else if ( (LA36_0=='a') ) {s = 9;}

                        else if ( (LA36_0=='g') ) {s = 10;}

                        else if ( (LA36_0=='e') ) {s = 11;}

                        else if ( (LA36_0=='w') ) {s = 12;}

                        else if ( (LA36_0=='t') ) {s = 13;}

                        else if ( (LA36_0=='o') ) {s = 14;}

                        else if ( (LA36_0=='n') ) {s = 15;}

                        else if ( (LA36_0=='l') ) {s = 16;}

                        else if ( (LA36_0=='(') ) {s = 17;}

                        else if ( (LA36_0==')') ) {s = 18;}

                        else if ( (LA36_0=='[') ) {s = 19;}

                        else if ( (LA36_0==']') ) {s = 20;}

                        else if ( (LA36_0==':') ) {s = 21;}

                        else if ( (LA36_0==',') ) {s = 22;}

                        else if ( (LA36_0==';') ) {s = 23;}

                        else if ( (LA36_0=='+') ) {s = 24;}

                        else if ( (LA36_0=='-') ) {s = 25;}

                        else if ( (LA36_0=='*') ) {s = 26;}

                        else if ( (LA36_0=='/') ) {s = 27;}

                        else if ( (LA36_0=='|') ) {s = 28;}

                        else if ( (LA36_0=='&') ) {s = 29;}

                        else if ( (LA36_0=='<') ) {s = 30;}

                        else if ( (LA36_0=='>') ) {s = 31;}

                        else if ( (LA36_0=='=') ) {s = 32;}

                        else if ( (LA36_0=='%') ) {s = 33;}

                        else if ( (LA36_0=='`') ) {s = 34;}

                        else if ( (LA36_0=='{') ) {s = 35;}

                        else if ( (LA36_0=='}') ) {s = 36;}

                        else if ( (LA36_0=='^') ) {s = 37;}

                        else if ( (LA36_0=='~') ) {s = 38;}

                        else if ( (LA36_0=='!') ) {s = 39;}

                        else if ( (LA36_0=='.') ) {s = 40;}

                        else if ( (LA36_0=='0') ) {s = 41;}

                        else if ( ((LA36_0>='1' && LA36_0<='9')) ) {s = 42;}

                        else if ( (LA36_0=='u') ) {s = 43;}

                        else if ( ((LA36_0>='A' && LA36_0<='Z')||LA36_0=='_'||LA36_0=='h'||(LA36_0>='j' && LA36_0<='k')||LA36_0=='m'||LA36_0=='q'||LA36_0=='s'||LA36_0=='v'||LA36_0=='x'||LA36_0=='z') ) {s = 44;}

                        else if ( (LA36_0=='\"'||LA36_0=='\'') ) {s = 45;}

                        else if ( (LA36_0=='\\') ) {s = 46;}

                        else if ( (LA36_0==' ') && ((startPos>0||startPos==0))) {s = 47;}

                        else if ( (LA36_0=='\t') && ((startPos>0||startPos==0))) {s = 48;}

                        else if ( (LA36_0=='#') && ((startPos>0||startPos==0))) {s = 49;}

                        else if ( (LA36_0=='@') ) {s = 50;}

                        else if ( (LA36_0=='\n'||LA36_0=='\r') ) {s = 51;}

                         
                        input.seek(index36_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA36_47 = input.LA(1);

                         
                        int index36_47 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA36_47=='\n'||LA36_47=='\r') && (startPos==0)) {s = 116;}

                        else if ( (LA36_47==' ') && ((startPos>0||startPos==0))) {s = 47;}

                        else if ( (LA36_47=='\t') && ((startPos>0||startPos==0))) {s = 48;}

                        else if ( (LA36_47=='#') && (startPos==0)) {s = 118;}

                        else s = 117;

                         
                        input.seek(index36_47);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA36_117 = input.LA(1);

                         
                        int index36_117 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (startPos>0) ) {s = 164;}

                        else if ( (((startPos==0&&implicitLineJoiningLevel>0)||startPos==0)) ) {s = 116;}

                         
                        input.seek(index36_117);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA36_119 = input.LA(1);

                         
                        int index36_119 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (startPos>0) ) {s = 164;}

                        else if ( (((startPos==0&&implicitLineJoiningLevel>0)||startPos==0)) ) {s = 116;}

                         
                        input.seek(index36_119);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA36_48 = input.LA(1);

                         
                        int index36_48 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA36_48==' ') && ((startPos>0||startPos==0))) {s = 47;}

                        else if ( (LA36_48=='#') && (startPos==0)) {s = 118;}

                        else if ( (LA36_48=='\n'||LA36_48=='\r') && (startPos==0)) {s = 116;}

                        else if ( (LA36_48=='\t') && ((startPos>0||startPos==0))) {s = 48;}

                        else s = 119;

                         
                        input.seek(index36_48);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 36, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}