// $ANTLR 3.0 /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g 2007-08-24 12:59:06

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
    public static final int FRACTION=61;
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
    public static final int WS=65;
    public static final int STRING=57;
    public static final int POINTFLOAT=58;
    public static final int T96=96;
    public static final int T71=71;
    public static final int T72=72;
    public static final int T94=94;
    public static final int LBRACK=48;
    public static final int T76=76;
    public static final int SEMI=16;
    public static final int EXPONENTFLOAT=59;
    public static final int T75=75;
    public static final int EQUAL=33;
    public static final int LESSEQUAL=35;
    public static final int T89=89;
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
    public static final int LEADING_WS=66;
    public static final int ASSIGN=15;
    public static final int VBAR=38;
    public static final int GREATER=32;
    public static final int LPAREN=8;
    public static final int T77=77;
    public static final int BACKQUOTE=52;
    public static final int CONTINUED_LINE=64;
    public static final int T69=69;
    public static final int Exponent=62;
    public static final int T95=95;
    public static final int DIGITS=60;
    public static final int SLASH=44;
    public static final int T92=92;
    public static final int COMMENT=67;
    public static final int T88=88;
    public static final int AMPEREQUAL=22;
    public static final int ESC=63;
    public static final int T98=98;
    public static final int T87=87;
    public static final int T80=80;
    public static final int T97=97;
    public static final int RIGHTSHIFT=29;
    public static final int MINUSEQUAL=18;
    public static final int PERCENTEQUAL=21;
    public static final int LEFTSHIFTEQUAL=25;
    public static final int EOF=-1;
    public static final int CIRCUMFLEXEQUAL=24;
    public static final int INDENT=4;
    public static final int Tokens=99;
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

    // $ANTLR start T68
    public final void mT68() throws RecognitionException {
        try {
            int _type = T68;
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
    // $ANTLR end T68

    // $ANTLR start T69
    public final void mT69() throws RecognitionException {
        try {
            int _type = T69;
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
    // $ANTLR end T69

    // $ANTLR start T70
    public final void mT70() throws RecognitionException {
        try {
            int _type = T70;
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
    // $ANTLR end T70

    // $ANTLR start T71
    public final void mT71() throws RecognitionException {
        try {
            int _type = T71;
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
    // $ANTLR end T71

    // $ANTLR start T72
    public final void mT72() throws RecognitionException {
        try {
            int _type = T72;
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
    // $ANTLR end T72

    // $ANTLR start T73
    public final void mT73() throws RecognitionException {
        try {
            int _type = T73;
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
    // $ANTLR end T73

    // $ANTLR start T74
    public final void mT74() throws RecognitionException {
        try {
            int _type = T74;
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
    // $ANTLR end T74

    // $ANTLR start T75
    public final void mT75() throws RecognitionException {
        try {
            int _type = T75;
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
    // $ANTLR end T75

    // $ANTLR start T76
    public final void mT76() throws RecognitionException {
        try {
            int _type = T76;
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
    // $ANTLR end T76

    // $ANTLR start T77
    public final void mT77() throws RecognitionException {
        try {
            int _type = T77;
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
    // $ANTLR end T77

    // $ANTLR start T78
    public final void mT78() throws RecognitionException {
        try {
            int _type = T78;
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
    // $ANTLR end T78

    // $ANTLR start T79
    public final void mT79() throws RecognitionException {
        try {
            int _type = T79;
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
    // $ANTLR end T79

    // $ANTLR start T80
    public final void mT80() throws RecognitionException {
        try {
            int _type = T80;
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
    // $ANTLR end T80

    // $ANTLR start T81
    public final void mT81() throws RecognitionException {
        try {
            int _type = T81;
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
    // $ANTLR end T81

    // $ANTLR start T82
    public final void mT82() throws RecognitionException {
        try {
            int _type = T82;
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
    // $ANTLR end T82

    // $ANTLR start T83
    public final void mT83() throws RecognitionException {
        try {
            int _type = T83;
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
    // $ANTLR end T83

    // $ANTLR start T84
    public final void mT84() throws RecognitionException {
        try {
            int _type = T84;
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
    // $ANTLR end T84

    // $ANTLR start T85
    public final void mT85() throws RecognitionException {
        try {
            int _type = T85;
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
    // $ANTLR end T85

    // $ANTLR start T86
    public final void mT86() throws RecognitionException {
        try {
            int _type = T86;
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
    // $ANTLR end T86

    // $ANTLR start T87
    public final void mT87() throws RecognitionException {
        try {
            int _type = T87;
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
    // $ANTLR end T87

    // $ANTLR start T88
    public final void mT88() throws RecognitionException {
        try {
            int _type = T88;
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
    // $ANTLR end T88

    // $ANTLR start T89
    public final void mT89() throws RecognitionException {
        try {
            int _type = T89;
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
    // $ANTLR end T89

    // $ANTLR start T90
    public final void mT90() throws RecognitionException {
        try {
            int _type = T90;
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
    // $ANTLR end T90

    // $ANTLR start T91
    public final void mT91() throws RecognitionException {
        try {
            int _type = T91;
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
    // $ANTLR end T91

    // $ANTLR start T92
    public final void mT92() throws RecognitionException {
        try {
            int _type = T92;
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
    // $ANTLR end T92

    // $ANTLR start T93
    public final void mT93() throws RecognitionException {
        try {
            int _type = T93;
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
    // $ANTLR end T93

    // $ANTLR start T94
    public final void mT94() throws RecognitionException {
        try {
            int _type = T94;
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
    // $ANTLR end T94

    // $ANTLR start T95
    public final void mT95() throws RecognitionException {
        try {
            int _type = T95;
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
    // $ANTLR end T95

    // $ANTLR start T96
    public final void mT96() throws RecognitionException {
        try {
            int _type = T96;
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
    // $ANTLR end T96

    // $ANTLR start T97
    public final void mT97() throws RecognitionException {
        try {
            int _type = T97;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:50:5: ( 'with' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:50:7: 'with'
            {
            match("with"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T97

    // $ANTLR start T98
    public final void mT98() throws RecognitionException {
        try {
            int _type = T98;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:51:5: ( 'class' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:51:7: 'class'
            {
            match("class"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T98

    // $ANTLR start LPAREN
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1669:8: ( '(' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1669:10: '('
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1671:8: ( ')' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1671:10: ')'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1673:8: ( '[' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1673:10: '['
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1675:8: ( ']' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1675:10: ']'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1677:8: ( ':' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1677:10: ':'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1679:7: ( ',' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1679:9: ','
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1681:6: ( ';' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1681:8: ';'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1683:6: ( '+' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1683:8: '+'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1685:7: ( '-' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1685:9: '-'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1687:6: ( '*' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1687:8: '*'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1689:7: ( '/' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1689:9: '/'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1691:6: ( '|' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1691:8: '|'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1693:7: ( '&' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1693:9: '&'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1695:6: ( '<' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1695:8: '<'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1697:9: ( '>' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1697:11: '>'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1699:8: ( '=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1699:10: '='
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1701:9: ( '%' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1701:11: '%'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1703:11: ( '`' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1703:13: '`'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1705:8: ( '{' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1705:10: '{'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1707:8: ( '}' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1707:10: '}'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1709:12: ( '^' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1709:14: '^'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1711:7: ( '~' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1711:9: '~'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1713:7: ( '==' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1713:9: '=='
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1715:10: ( '!=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1715:12: '!='
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1717:13: ( '<>' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1717:15: '<>'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1719:11: ( '<=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1719:13: '<='
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1721:11: ( '<<' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1721:13: '<<'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1723:14: ( '>=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1723:16: '>='
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1725:12: ( '>>' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1725:14: '>>'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1727:11: ( '+=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1727:13: '+='
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1729:12: ( '-=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1729:14: '-='
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1731:12: ( '**' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1731:14: '**'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1733:11: ( '*=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1733:13: '*='
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1735:13: ( '//' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1735:15: '//'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1737:12: ( '/=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1737:14: '/='
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1739:11: ( '|=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1739:13: '|='
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1741:14: ( '%=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1741:16: '%='
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1743:12: ( '&=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1743:14: '&='
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1745:17: ( '^=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1745:19: '^='
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1747:16: ( '<<=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1747:18: '<<='
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1749:17: ( '>>=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1749:19: '>>='
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1751:17: ( '**=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1751:19: '**='
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1753:18: ( '//=' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1753:20: '//='
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1755:5: ( '.' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1755:7: '.'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1757:9: ( POINTFLOAT | EXPONENTFLOAT )
            int alt1=2;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1757:11: POINTFLOAT
                    {
                    mPOINTFLOAT(); 

                    }
                    break;
                case 2 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1757:24: EXPONENTFLOAT
                    {
                    mEXPONENTFLOAT(); 

                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end FLOAT

    // $ANTLR start POINTFLOAT
    public final void mPOINTFLOAT() throws RecognitionException {
        try {
            int _type = POINTFLOAT;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1762:2: ( ( DIGITS )? FRACTION | DIGITS '.' )
            int alt3=2;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1762:4: ( DIGITS )? FRACTION
                    {
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1762:4: ( DIGITS )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1762:4: DIGITS
                            {
                            mDIGITS(); 

                            }
                            break;

                    }

                    mFRACTION(); 

                    }
                    break;
                case 2 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1762:23: DIGITS '.'
                    {
                    mDIGITS(); 
                    match('.'); 

                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end POINTFLOAT

    // $ANTLR start FRACTION
    public final void mFRACTION() throws RecognitionException {
        try {
            int _type = FRACTION;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1765:2: ( '.' DIGITS )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1765:4: '.' DIGITS
            {
            match('.'); 
            mDIGITS(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end FRACTION

    // $ANTLR start EXPONENTFLOAT
    public final void mEXPONENTFLOAT() throws RecognitionException {
        try {
            int _type = EXPONENTFLOAT;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1768:2: ( ( DIGITS | POINTFLOAT ) Exponent )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1768:4: ( DIGITS | POINTFLOAT ) Exponent
            {
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1768:4: ( DIGITS | POINTFLOAT )
            int alt4=2;
            alt4 = dfa4.predict(input);
            switch (alt4) {
                case 1 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1768:5: DIGITS
                    {
                    mDIGITS(); 

                    }
                    break;
                case 2 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1768:14: POINTFLOAT
                    {
                    mPOINTFLOAT(); 

                    }
                    break;

            }

            mExponent(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end EXPONENTFLOAT

    // $ANTLR start LONGINT
    public final void mLONGINT() throws RecognitionException {
        try {
            int _type = LONGINT;
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1771:5: ( INT ( 'l' | 'L' ) )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1771:9: INT ( 'l' | 'L' )
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1776:2: ( ( 'e' | 'E' ) ( '+' | '-' )? DIGITS )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1776:4: ( 'e' | 'E' ) ( '+' | '-' )? DIGITS
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1776:16: ( '+' | '-' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='+'||LA5_0=='-') ) {
                alt5=1;
            }
            switch (alt5) {
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1779:5: ( '0' ( 'x' | 'X' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ ( 'l' | 'L' )? | '0' ( DIGITS )* | '1' .. '9' ( DIGITS )* )
            int alt10=3;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='0') ) {
                int LA10_1 = input.LA(2);

                if ( (LA10_1=='X'||LA10_1=='x') ) {
                    alt10=1;
                }
                else {
                    alt10=2;}
            }
            else if ( ((LA10_0>='1' && LA10_0<='9')) ) {
                alt10=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1779:1: INT : ( '0' ( 'x' | 'X' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ ( 'l' | 'L' )? | '0' ( DIGITS )* | '1' .. '9' ( DIGITS )* );", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1780:9: '0' ( 'x' | 'X' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ ( 'l' | 'L' )?
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

                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1780:25: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+
                    int cnt6=0;
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0>='0' && LA6_0<='9')||(LA6_0>='A' && LA6_0<='F')||(LA6_0>='a' && LA6_0<='f')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
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
                    	    if ( cnt6 >= 1 ) break loop6;
                                EarlyExitException eee =
                                    new EarlyExitException(6, input);
                                throw eee;
                        }
                        cnt6++;
                    } while (true);

                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1781:9: ( 'l' | 'L' )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0=='L'||LA7_0=='l') ) {
                        alt7=1;
                    }
                    switch (alt7) {
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
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1783:9: '0' ( DIGITS )*
                    {
                    match('0'); 
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1783:13: ( DIGITS )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( ((LA8_0>='0' && LA8_0<='9')) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1783:13: DIGITS
                    	    {
                    	    mDIGITS(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);


                    }
                    break;
                case 3 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1784:9: '1' .. '9' ( DIGITS )*
                    {
                    matchRange('1','9'); 
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1784:18: ( DIGITS )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( ((LA9_0>='0' && LA9_0<='9')) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1784:18: DIGITS
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1788:5: ( INT ( 'j' | 'J' ) | FLOAT ( 'j' | 'J' ) )
            int alt11=2;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1788:9: INT ( 'j' | 'J' )
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
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1789:9: FLOAT ( 'j' | 'J' )
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1793:8: ( ( '0' .. '9' )+ )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1793:10: ( '0' .. '9' )+
            {
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1793:10: ( '0' .. '9' )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>='0' && LA12_0<='9')) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1793:12: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1795:5: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1795:7: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1796:9: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>='0' && LA13_0<='9')||(LA13_0>='A' && LA13_0<='Z')||LA13_0=='_'||(LA13_0>='a' && LA13_0<='z')) ) {
                    alt13=1;
                }


                switch (alt13) {
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
            	    break loop13;
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1803:5: ( ( 'r' | 'u' | 'ur' )? ( '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\'' | '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"' | '\"' ( ESC | ~ ( '\\\\' | '\\n' | '\"' ) )* '\"' | '\\'' ( ESC | ~ ( '\\\\' | '\\n' | '\\'' ) )* '\\'' ) )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1803:9: ( 'r' | 'u' | 'ur' )? ( '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\'' | '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"' | '\"' ( ESC | ~ ( '\\\\' | '\\n' | '\"' ) )* '\"' | '\\'' ( ESC | ~ ( '\\\\' | '\\n' | '\\'' ) )* '\\'' )
            {
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1803:9: ( 'r' | 'u' | 'ur' )?
            int alt14=4;
            int LA14_0 = input.LA(1);

            if ( (LA14_0=='r') ) {
                alt14=1;
            }
            else if ( (LA14_0=='u') ) {
                int LA14_2 = input.LA(2);

                if ( (LA14_2=='r') ) {
                    alt14=3;
                }
                else if ( (LA14_2=='\"'||LA14_2=='\'') ) {
                    alt14=2;
                }
            }
            switch (alt14) {
                case 1 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1803:10: 'r'
                    {
                    match('r'); 

                    }
                    break;
                case 2 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1803:14: 'u'
                    {
                    match('u'); 

                    }
                    break;
                case 3 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1803:18: 'ur'
                    {
                    match("ur"); 


                    }
                    break;

            }

            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1804:9: ( '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\'' | '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"' | '\"' ( ESC | ~ ( '\\\\' | '\\n' | '\"' ) )* '\"' | '\\'' ( ESC | ~ ( '\\\\' | '\\n' | '\\'' ) )* '\\'' )
            int alt19=4;
            int LA19_0 = input.LA(1);

            if ( (LA19_0=='\'') ) {
                int LA19_1 = input.LA(2);

                if ( (LA19_1=='\'') ) {
                    int LA19_3 = input.LA(3);

                    if ( (LA19_3=='\'') ) {
                        alt19=1;
                    }
                    else {
                        alt19=4;}
                }
                else if ( ((LA19_1>='\u0000' && LA19_1<='\t')||(LA19_1>='\u000B' && LA19_1<='&')||(LA19_1>='(' && LA19_1<='\uFFFE')) ) {
                    alt19=4;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1804:9: ( '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\'' | '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"' | '\"' ( ESC | ~ ( '\\\\' | '\\n' | '\"' ) )* '\"' | '\\'' ( ESC | ~ ( '\\\\' | '\\n' | '\\'' ) )* '\\'' )", 19, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA19_0=='\"') ) {
                int LA19_2 = input.LA(2);

                if ( (LA19_2=='\"') ) {
                    int LA19_5 = input.LA(3);

                    if ( (LA19_5=='\"') ) {
                        alt19=2;
                    }
                    else {
                        alt19=3;}
                }
                else if ( ((LA19_2>='\u0000' && LA19_2<='\t')||(LA19_2>='\u000B' && LA19_2<='!')||(LA19_2>='#' && LA19_2<='\uFFFE')) ) {
                    alt19=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1804:9: ( '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\'' | '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"' | '\"' ( ESC | ~ ( '\\\\' | '\\n' | '\"' ) )* '\"' | '\\'' ( ESC | ~ ( '\\\\' | '\\n' | '\\'' ) )* '\\'' )", 19, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1804:9: ( '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\'' | '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"' | '\"' ( ESC | ~ ( '\\\\' | '\\n' | '\"' ) )* '\"' | '\\'' ( ESC | ~ ( '\\\\' | '\\n' | '\\'' ) )* '\\'' )", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1804:13: '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\''
                    {
                    match("\'\'\'"); 

                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1804:22: ( options {greedy=false; } : . )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0=='\'') ) {
                            int LA15_1 = input.LA(2);

                            if ( (LA15_1=='\'') ) {
                                int LA15_3 = input.LA(3);

                                if ( (LA15_3=='\'') ) {
                                    alt15=2;
                                }
                                else if ( ((LA15_3>='\u0000' && LA15_3<='&')||(LA15_3>='(' && LA15_3<='\uFFFE')) ) {
                                    alt15=1;
                                }


                            }
                            else if ( ((LA15_1>='\u0000' && LA15_1<='&')||(LA15_1>='(' && LA15_1<='\uFFFE')) ) {
                                alt15=1;
                            }


                        }
                        else if ( ((LA15_0>='\u0000' && LA15_0<='&')||(LA15_0>='(' && LA15_0<='\uFFFE')) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1804:47: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);

                    match("\'\'\'"); 


                    }
                    break;
                case 2 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1805:13: '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"'
                    {
                    match("\"\"\""); 

                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1805:19: ( options {greedy=false; } : . )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0=='\"') ) {
                            int LA16_1 = input.LA(2);

                            if ( (LA16_1=='\"') ) {
                                int LA16_3 = input.LA(3);

                                if ( (LA16_3=='\"') ) {
                                    alt16=2;
                                }
                                else if ( ((LA16_3>='\u0000' && LA16_3<='!')||(LA16_3>='#' && LA16_3<='\uFFFE')) ) {
                                    alt16=1;
                                }


                            }
                            else if ( ((LA16_1>='\u0000' && LA16_1<='!')||(LA16_1>='#' && LA16_1<='\uFFFE')) ) {
                                alt16=1;
                            }


                        }
                        else if ( ((LA16_0>='\u0000' && LA16_0<='!')||(LA16_0>='#' && LA16_0<='\uFFFE')) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1805:44: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);

                    match("\"\"\""); 


                    }
                    break;
                case 3 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1806:13: '\"' ( ESC | ~ ( '\\\\' | '\\n' | '\"' ) )* '\"'
                    {
                    match('\"'); 
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1806:17: ( ESC | ~ ( '\\\\' | '\\n' | '\"' ) )*
                    loop17:
                    do {
                        int alt17=3;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0=='\\') ) {
                            alt17=1;
                        }
                        else if ( ((LA17_0>='\u0000' && LA17_0<='\t')||(LA17_0>='\u000B' && LA17_0<='!')||(LA17_0>='#' && LA17_0<='[')||(LA17_0>=']' && LA17_0<='\uFFFE')) ) {
                            alt17=2;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1806:18: ESC
                    	    {
                    	    mESC(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1806:22: ~ ( '\\\\' | '\\n' | '\"' )
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
                    	    break loop17;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 4 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1807:13: '\\'' ( ESC | ~ ( '\\\\' | '\\n' | '\\'' ) )* '\\''
                    {
                    match('\''); 
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1807:18: ( ESC | ~ ( '\\\\' | '\\n' | '\\'' ) )*
                    loop18:
                    do {
                        int alt18=3;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0=='\\') ) {
                            alt18=1;
                        }
                        else if ( ((LA18_0>='\u0000' && LA18_0<='\t')||(LA18_0>='\u000B' && LA18_0<='&')||(LA18_0>='(' && LA18_0<='[')||(LA18_0>=']' && LA18_0<='\uFFFE')) ) {
                            alt18=2;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1807:19: ESC
                    	    {
                    	    mESC(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1807:23: ~ ( '\\\\' | '\\n' | '\\'' )
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
                    	    break loop18;
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1812:2: ( '\\\\' . )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1812:4: '\\\\' .
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1817:2: ( '\\\\' ( '\\r' )? '\\n' ( ' ' | '\\t' )* )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1817:4: '\\\\' ( '\\r' )? '\\n' ( ' ' | '\\t' )*
            {
            match('\\'); 
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1817:9: ( '\\r' )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0=='\r') ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1817:10: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1817:22: ( ' ' | '\\t' )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0=='\t'||LA21_0==' ') ) {
                    alt21=1;
                }


                switch (alt21) {
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
            	    break loop21;
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1822:4: ({...}? => ( ' ' | '\\t' )+ )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1822:6: {...}? => ( ' ' | '\\t' )+
            {
            if ( !(startPos>0) ) {
                throw new FailedPredicateException(input, "WS", "startPos>0");
            }
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1822:22: ( ' ' | '\\t' )+
            int cnt22=0;
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
            	    if ( cnt22 >= 1 ) break loop22;
                        EarlyExitException eee =
                            new EarlyExitException(22, input);
                        throw eee;
                }
                cnt22++;
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

            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1834:5: ({...}? => ({...}? ( ' ' | '\\t' )+ | ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )* ) )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1834:9: {...}? => ({...}? ( ' ' | '\\t' )+ | ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )* )
            {
            if ( !(startPos==0) ) {
                throw new FailedPredicateException(input, "LEADING_WS", "startPos==0");
            }
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1835:6: ({...}? ( ' ' | '\\t' )+ | ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )* )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==' ') ) {
                int LA27_1 = input.LA(2);

                if ( (implicitLineJoiningLevel>0) ) {
                    alt27=1;
                }
                else if ( (true) ) {
                    alt27=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1835:6: ({...}? ( ' ' | '\\t' )+ | ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )* )", 27, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA27_0=='\t') ) {
                int LA27_2 = input.LA(2);

                if ( (implicitLineJoiningLevel>0) ) {
                    alt27=1;
                }
                else if ( (true) ) {
                    alt27=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1835:6: ({...}? ( ' ' | '\\t' )+ | ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )* )", 27, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1835:6: ({...}? ( ' ' | '\\t' )+ | ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )* )", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1835:10: {...}? ( ' ' | '\\t' )+
                    {
                    if ( !(implicitLineJoiningLevel>0) ) {
                        throw new FailedPredicateException(input, "LEADING_WS", "implicitLineJoiningLevel>0");
                    }
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1835:40: ( ' ' | '\\t' )+
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
                    break;
                case 2 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1836:11: ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )*
                    {
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1836:11: ( ' ' | '\\t' )+
                    int cnt24=0;
                    loop24:
                    do {
                        int alt24=3;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==' ') ) {
                            alt24=1;
                        }
                        else if ( (LA24_0=='\t') ) {
                            alt24=2;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1836:14: ' '
                    	    {
                    	    match(' '); 
                    	     spaces++; 

                    	    }
                    	    break;
                    	case 2 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1837:12: '\\t'
                    	    {
                    	    match('\t'); 
                    	     spaces += 8; spaces -= (spaces % 8); 

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


                                // make a string of n spaces where n is column number - 1
                                char[] indentation = new char[spaces];
                                for (int i=0; i<spaces; i++) {
                                    indentation[i] = ' ';
                                }
                                String s = new String(indentation);
                                Token tok = new ClassicToken(LEADING_WS,new String(indentation));
                                emit(tok);
                            
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1850:10: ( ( '\\r' )? '\\n' )*
                    loop26:
                    do {
                        int alt26=2;
                        int LA26_0 = input.LA(1);

                        if ( (LA26_0=='\n'||LA26_0=='\r') ) {
                            alt26=1;
                        }


                        switch (alt26) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1850:12: ( '\\r' )? '\\n'
                    	    {
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1850:12: ( '\\r' )?
                    	    int alt25=2;
                    	    int LA25_0 = input.LA(1);

                    	    if ( (LA25_0=='\r') ) {
                    	        alt25=1;
                    	    }
                    	    switch (alt25) {
                    	        case 1 :
                    	            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1850:13: '\\r'
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
                    	    break loop26;
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

            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1888:5: ({...}? => ( ' ' | '\\t' )* '#' (~ '\\n' )* ( '\\n' )+ | {...}? => '#' (~ '\\n' )* )
            int alt32=2;
            alt32 = dfa32.predict(input);
            switch (alt32) {
                case 1 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1888:7: {...}? => ( ' ' | '\\t' )* '#' (~ '\\n' )* ( '\\n' )+
                    {
                    if ( !(startPos==0) ) {
                        throw new FailedPredicateException(input, "COMMENT", "startPos==0");
                    }
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1888:24: ( ' ' | '\\t' )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0=='\t'||LA28_0==' ') ) {
                            alt28=1;
                        }


                        switch (alt28) {
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
                    	    break loop28;
                        }
                    } while (true);

                    match('#'); 
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1888:40: (~ '\\n' )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( ((LA29_0>='\u0000' && LA29_0<='\t')||(LA29_0>='\u000B' && LA29_0<='\uFFFE')) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1888:41: ~ '\\n'
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
                    	    break loop29;
                        }
                    } while (true);

                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1888:49: ( '\\n' )+
                    int cnt30=0;
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0=='\n') ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1888:49: '\\n'
                    	    {
                    	    match('\n'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt30 >= 1 ) break loop30;
                                EarlyExitException eee =
                                    new EarlyExitException(30, input);
                                throw eee;
                        }
                        cnt30++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1889:7: {...}? => '#' (~ '\\n' )*
                    {
                    if ( !(startPos>0) ) {
                        throw new FailedPredicateException(input, "COMMENT", "startPos>0");
                    }
                    match('#'); 
                    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1889:27: (~ '\\n' )*
                    loop31:
                    do {
                        int alt31=2;
                        int LA31_0 = input.LA(1);

                        if ( ((LA31_0>='\u0000' && LA31_0<='\t')||(LA31_0>='\u000B' && LA31_0<='\uFFFE')) ) {
                            alt31=1;
                        }


                        switch (alt31) {
                    	case 1 :
                    	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1889:28: ~ '\\n'
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
                    	    break loop31;
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1905:12: ( '@' )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1906:2: '@'
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
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1913:5: ( ( ( '\\r' )? '\\n' )+ )
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1913:9: ( ( '\\r' )? '\\n' )+
            {
            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1913:9: ( ( '\\r' )? '\\n' )+
            int cnt34=0;
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0=='\n'||LA34_0=='\r') ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1913:10: ( '\\r' )? '\\n'
            	    {
            	    // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1913:10: ( '\\r' )?
            	    int alt33=2;
            	    int LA33_0 = input.LA(1);

            	    if ( (LA33_0=='\r') ) {
            	        alt33=1;
            	    }
            	    switch (alt33) {
            	        case 1 :
            	            // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1913:11: '\\r'
            	            {
            	            match('\r'); 

            	            }
            	            break;

            	    }

            	    match('\n'); 

            	    }
            	    break;

            	default :
            	    if ( cnt34 >= 1 ) break loop34;
                        EarlyExitException eee =
                            new EarlyExitException(34, input);
                        throw eee;
                }
                cnt34++;
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
        // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:8: ( T68 | T69 | T70 | T71 | T72 | T73 | T74 | T75 | T76 | T77 | T78 | T79 | T80 | T81 | T82 | T83 | T84 | T85 | T86 | T87 | T88 | T89 | T90 | T91 | T92 | T93 | T94 | T95 | T96 | T97 | T98 | LPAREN | RPAREN | LBRACK | RBRACK | COLON | COMMA | SEMI | PLUS | MINUS | STAR | SLASH | VBAR | AMPER | LESS | GREATER | ASSIGN | PERCENT | BACKQUOTE | LCURLY | RCURLY | CIRCUMFLEX | TILDE | EQUAL | NOTEQUAL | ALT_NOTEQUAL | LESSEQUAL | LEFTSHIFT | GREATEREQUAL | RIGHTSHIFT | PLUSEQUAL | MINUSEQUAL | DOUBLESTAR | STAREQUAL | DOUBLESLASH | SLASHEQUAL | VBAREQUAL | PERCENTEQUAL | AMPEREQUAL | CIRCUMFLEXEQUAL | LEFTSHIFTEQUAL | RIGHTSHIFTEQUAL | DOUBLESTAREQUAL | DOUBLESLASHEQUAL | DOT | FLOAT | POINTFLOAT | FRACTION | EXPONENTFLOAT | LONGINT | INT | COMPLEX | NAME | STRING | CONTINUED_LINE | WS | LEADING_WS | COMMENT | DECORATOR_S | NEWLINE )
        int alt35=90;
        alt35 = dfa35.predict(input);
        switch (alt35) {
            case 1 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:10: T68
                {
                mT68(); 

                }
                break;
            case 2 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:14: T69
                {
                mT69(); 

                }
                break;
            case 3 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:18: T70
                {
                mT70(); 

                }
                break;
            case 4 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:22: T71
                {
                mT71(); 

                }
                break;
            case 5 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:26: T72
                {
                mT72(); 

                }
                break;
            case 6 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:30: T73
                {
                mT73(); 

                }
                break;
            case 7 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:34: T74
                {
                mT74(); 

                }
                break;
            case 8 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:38: T75
                {
                mT75(); 

                }
                break;
            case 9 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:42: T76
                {
                mT76(); 

                }
                break;
            case 10 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:46: T77
                {
                mT77(); 

                }
                break;
            case 11 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:50: T78
                {
                mT78(); 

                }
                break;
            case 12 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:54: T79
                {
                mT79(); 

                }
                break;
            case 13 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:58: T80
                {
                mT80(); 

                }
                break;
            case 14 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:62: T81
                {
                mT81(); 

                }
                break;
            case 15 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:66: T82
                {
                mT82(); 

                }
                break;
            case 16 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:70: T83
                {
                mT83(); 

                }
                break;
            case 17 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:74: T84
                {
                mT84(); 

                }
                break;
            case 18 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:78: T85
                {
                mT85(); 

                }
                break;
            case 19 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:82: T86
                {
                mT86(); 

                }
                break;
            case 20 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:86: T87
                {
                mT87(); 

                }
                break;
            case 21 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:90: T88
                {
                mT88(); 

                }
                break;
            case 22 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:94: T89
                {
                mT89(); 

                }
                break;
            case 23 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:98: T90
                {
                mT90(); 

                }
                break;
            case 24 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:102: T91
                {
                mT91(); 

                }
                break;
            case 25 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:106: T92
                {
                mT92(); 

                }
                break;
            case 26 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:110: T93
                {
                mT93(); 

                }
                break;
            case 27 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:114: T94
                {
                mT94(); 

                }
                break;
            case 28 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:118: T95
                {
                mT95(); 

                }
                break;
            case 29 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:122: T96
                {
                mT96(); 

                }
                break;
            case 30 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:126: T97
                {
                mT97(); 

                }
                break;
            case 31 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:130: T98
                {
                mT98(); 

                }
                break;
            case 32 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:134: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 33 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:141: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 34 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:148: LBRACK
                {
                mLBRACK(); 

                }
                break;
            case 35 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:155: RBRACK
                {
                mRBRACK(); 

                }
                break;
            case 36 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:162: COLON
                {
                mCOLON(); 

                }
                break;
            case 37 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:168: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 38 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:174: SEMI
                {
                mSEMI(); 

                }
                break;
            case 39 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:179: PLUS
                {
                mPLUS(); 

                }
                break;
            case 40 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:184: MINUS
                {
                mMINUS(); 

                }
                break;
            case 41 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:190: STAR
                {
                mSTAR(); 

                }
                break;
            case 42 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:195: SLASH
                {
                mSLASH(); 

                }
                break;
            case 43 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:201: VBAR
                {
                mVBAR(); 

                }
                break;
            case 44 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:206: AMPER
                {
                mAMPER(); 

                }
                break;
            case 45 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:212: LESS
                {
                mLESS(); 

                }
                break;
            case 46 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:217: GREATER
                {
                mGREATER(); 

                }
                break;
            case 47 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:225: ASSIGN
                {
                mASSIGN(); 

                }
                break;
            case 48 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:232: PERCENT
                {
                mPERCENT(); 

                }
                break;
            case 49 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:240: BACKQUOTE
                {
                mBACKQUOTE(); 

                }
                break;
            case 50 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:250: LCURLY
                {
                mLCURLY(); 

                }
                break;
            case 51 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:257: RCURLY
                {
                mRCURLY(); 

                }
                break;
            case 52 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:264: CIRCUMFLEX
                {
                mCIRCUMFLEX(); 

                }
                break;
            case 53 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:275: TILDE
                {
                mTILDE(); 

                }
                break;
            case 54 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:281: EQUAL
                {
                mEQUAL(); 

                }
                break;
            case 55 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:287: NOTEQUAL
                {
                mNOTEQUAL(); 

                }
                break;
            case 56 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:296: ALT_NOTEQUAL
                {
                mALT_NOTEQUAL(); 

                }
                break;
            case 57 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:309: LESSEQUAL
                {
                mLESSEQUAL(); 

                }
                break;
            case 58 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:319: LEFTSHIFT
                {
                mLEFTSHIFT(); 

                }
                break;
            case 59 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:329: GREATEREQUAL
                {
                mGREATEREQUAL(); 

                }
                break;
            case 60 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:342: RIGHTSHIFT
                {
                mRIGHTSHIFT(); 

                }
                break;
            case 61 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:353: PLUSEQUAL
                {
                mPLUSEQUAL(); 

                }
                break;
            case 62 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:363: MINUSEQUAL
                {
                mMINUSEQUAL(); 

                }
                break;
            case 63 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:374: DOUBLESTAR
                {
                mDOUBLESTAR(); 

                }
                break;
            case 64 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:385: STAREQUAL
                {
                mSTAREQUAL(); 

                }
                break;
            case 65 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:395: DOUBLESLASH
                {
                mDOUBLESLASH(); 

                }
                break;
            case 66 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:407: SLASHEQUAL
                {
                mSLASHEQUAL(); 

                }
                break;
            case 67 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:418: VBAREQUAL
                {
                mVBAREQUAL(); 

                }
                break;
            case 68 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:428: PERCENTEQUAL
                {
                mPERCENTEQUAL(); 

                }
                break;
            case 69 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:441: AMPEREQUAL
                {
                mAMPEREQUAL(); 

                }
                break;
            case 70 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:452: CIRCUMFLEXEQUAL
                {
                mCIRCUMFLEXEQUAL(); 

                }
                break;
            case 71 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:468: LEFTSHIFTEQUAL
                {
                mLEFTSHIFTEQUAL(); 

                }
                break;
            case 72 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:483: RIGHTSHIFTEQUAL
                {
                mRIGHTSHIFTEQUAL(); 

                }
                break;
            case 73 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:499: DOUBLESTAREQUAL
                {
                mDOUBLESTAREQUAL(); 

                }
                break;
            case 74 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:515: DOUBLESLASHEQUAL
                {
                mDOUBLESLASHEQUAL(); 

                }
                break;
            case 75 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:532: DOT
                {
                mDOT(); 

                }
                break;
            case 76 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:536: FLOAT
                {
                mFLOAT(); 

                }
                break;
            case 77 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:542: POINTFLOAT
                {
                mPOINTFLOAT(); 

                }
                break;
            case 78 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:553: FRACTION
                {
                mFRACTION(); 

                }
                break;
            case 79 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:562: EXPONENTFLOAT
                {
                mEXPONENTFLOAT(); 

                }
                break;
            case 80 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:576: LONGINT
                {
                mLONGINT(); 

                }
                break;
            case 81 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:584: INT
                {
                mINT(); 

                }
                break;
            case 82 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:588: COMPLEX
                {
                mCOMPLEX(); 

                }
                break;
            case 83 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:596: NAME
                {
                mNAME(); 

                }
                break;
            case 84 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:601: STRING
                {
                mSTRING(); 

                }
                break;
            case 85 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:608: CONTINUED_LINE
                {
                mCONTINUED_LINE(); 

                }
                break;
            case 86 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:623: WS
                {
                mWS(); 

                }
                break;
            case 87 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:626: LEADING_WS
                {
                mLEADING_WS(); 

                }
                break;
            case 88 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:637: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 89 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:645: DECORATOR_S
                {
                mDECORATOR_S(); 

                }
                break;
            case 90 :
                // /home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g:1:657: NEWLINE
                {
                mNEWLINE(); 

                }
                break;

        }

    }


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA3 dfa3 = new DFA3(this);
    protected DFA4 dfa4 = new DFA4(this);
    protected DFA11 dfa11 = new DFA11(this);
    protected DFA32 dfa32 = new DFA32(this);
    protected DFA35 dfa35 = new DFA35(this);
    static final String DFA1_eotS =
        "\4\uffff\2\6\1\uffff";
    static final String DFA1_eofS =
        "\7\uffff";
    static final String DFA1_minS =
        "\2\56\1\60\1\uffff\2\60\1\uffff";
    static final String DFA1_maxS =
        "\1\71\1\145\1\71\1\uffff\2\145\1\uffff";
    static final String DFA1_acceptS =
        "\3\uffff\1\2\2\uffff\1\1";
    static final String DFA1_specialS =
        "\7\uffff}>";
    static final String[] DFA1_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\4\1\uffff\12\1\13\uffff\1\3\37\uffff\1\3",
            "\12\5",
            "",
            "\12\5\13\uffff\1\3\37\uffff\1\3",
            "\12\5\13\uffff\1\3\37\uffff\1\3",
            ""
    };

    static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
    static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
    static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
    static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
    static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
    static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
    static final short[][] DFA1_transition;

    static {
        int numStates = DFA1_transitionS.length;
        DFA1_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
        }
    }

    class DFA1 extends DFA {

        public DFA1(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 1;
            this.eot = DFA1_eot;
            this.eof = DFA1_eof;
            this.min = DFA1_min;
            this.max = DFA1_max;
            this.accept = DFA1_accept;
            this.special = DFA1_special;
            this.transition = DFA1_transition;
        }
        public String getDescription() {
            return "1757:1: FLOAT : ( POINTFLOAT | EXPONENTFLOAT );";
        }
    }
    static final String DFA3_eotS =
        "\3\uffff\1\4\1\uffff";
    static final String DFA3_eofS =
        "\5\uffff";
    static final String DFA3_minS =
        "\2\56\1\uffff\1\60\1\uffff";
    static final String DFA3_maxS =
        "\2\71\1\uffff\1\71\1\uffff";
    static final String DFA3_acceptS =
        "\2\uffff\1\1\1\uffff\1\2";
    static final String DFA3_specialS =
        "\5\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\3\1\uffff\12\1",
            "",
            "\12\2",
            ""
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "1761:1: POINTFLOAT : ( ( DIGITS )? FRACTION | DIGITS '.' );";
        }
    }
    static final String DFA4_eotS =
        "\4\uffff";
    static final String DFA4_eofS =
        "\4\uffff";
    static final String DFA4_minS =
        "\2\56\2\uffff";
    static final String DFA4_maxS =
        "\1\71\1\145\2\uffff";
    static final String DFA4_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA4_specialS =
        "\4\uffff}>";
    static final String[] DFA4_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\2\1\uffff\12\1\13\uffff\1\3\37\uffff\1\3",
            "",
            ""
    };

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "1768:4: ( DIGITS | POINTFLOAT )";
        }
    }
    static final String DFA11_eotS =
        "\7\uffff";
    static final String DFA11_eofS =
        "\7\uffff";
    static final String DFA11_minS =
        "\3\56\2\uffff\2\56";
    static final String DFA11_maxS =
        "\1\71\1\170\1\152\2\uffff\2\152";
    static final String DFA11_acceptS =
        "\3\uffff\1\2\1\1\2\uffff";
    static final String DFA11_specialS =
        "\7\uffff}>";
    static final String[] DFA11_transitionS = {
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

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "1787:1: COMPLEX : ( INT ( 'j' | 'J' ) | FLOAT ( 'j' | 'J' ) );";
        }
    }
    static final String DFA32_eotS =
        "\2\uffff\2\4\1\uffff";
    static final String DFA32_eofS =
        "\5\uffff";
    static final String DFA32_minS =
        "\1\11\1\uffff\2\0\1\uffff";
    static final String DFA32_maxS =
        "\1\43\1\uffff\2\ufffe\1\uffff";
    static final String DFA32_acceptS =
        "\1\uffff\1\1\2\uffff\1\2";
    static final String DFA32_specialS =
        "\1\2\1\uffff\1\1\1\0\1\uffff}>";
    static final String[] DFA32_transitionS = {
            "\1\1\26\uffff\1\1\2\uffff\1\2",
            "",
            "\12\3\1\1\ufff4\3",
            "\12\3\1\1\ufff4\3",
            ""
    };

    static final short[] DFA32_eot = DFA.unpackEncodedString(DFA32_eotS);
    static final short[] DFA32_eof = DFA.unpackEncodedString(DFA32_eofS);
    static final char[] DFA32_min = DFA.unpackEncodedStringToUnsignedChars(DFA32_minS);
    static final char[] DFA32_max = DFA.unpackEncodedStringToUnsignedChars(DFA32_maxS);
    static final short[] DFA32_accept = DFA.unpackEncodedString(DFA32_acceptS);
    static final short[] DFA32_special = DFA.unpackEncodedString(DFA32_specialS);
    static final short[][] DFA32_transition;

    static {
        int numStates = DFA32_transitionS.length;
        DFA32_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA32_transition[i] = DFA.unpackEncodedString(DFA32_transitionS[i]);
        }
    }

    class DFA32 extends DFA {

        public DFA32(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 32;
            this.eot = DFA32_eot;
            this.eof = DFA32_eof;
            this.min = DFA32_min;
            this.max = DFA32_max;
            this.accept = DFA32_accept;
            this.special = DFA32_special;
            this.transition = DFA32_transition;
        }
        public String getDescription() {
            return "1864:1: COMMENT : ({...}? => ( ' ' | '\\t' )* '#' (~ '\\n' )* ( '\\n' )+ | {...}? => '#' (~ '\\n' )* );";
        }
        public int specialStateTransition(int s, IntStream input) throws NoViableAltException {
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA32_3 = input.LA(1);

                         
                        int index32_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA32_3=='\n') && (startPos==0)) {s = 1;}

                        else if ( ((LA32_3>='\u0000' && LA32_3<='\t')||(LA32_3>='\u000B' && LA32_3<='\uFFFE')) && ((startPos>0||startPos==0))) {s = 3;}

                        else s = 4;

                         
                        input.seek(index32_3);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA32_2 = input.LA(1);

                         
                        int index32_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA32_2>='\u0000' && LA32_2<='\t')||(LA32_2>='\u000B' && LA32_2<='\uFFFE')) && ((startPos>0||startPos==0))) {s = 3;}

                        else if ( (LA32_2=='\n') && (startPos==0)) {s = 1;}

                        else s = 4;

                         
                        input.seek(index32_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA32_0 = input.LA(1);

                         
                        int index32_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA32_0=='\t'||LA32_0==' ') && (startPos==0)) {s = 1;}

                        else if ( (LA32_0=='#') && ((startPos>0||startPos==0))) {s = 2;}

                         
                        input.seek(index32_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 32, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA35_eotS =
        "\1\uffff\20\54\7\uffff\1\120\1\122\1\125\1\130\1\132\1\134\1\140"+
        "\1\143\1\145\1\147\3\uffff\1\151\2\uffff\1\153\2\155\1\54\3\uffff"+
        "\1\165\1\170\3\uffff\11\54\1\u0083\1\u0084\1\54\1\u0086\3\54\1\u008b"+
        "\7\54\1\u0095\2\54\5\uffff\1\u0099\1\uffff\1\u009b\10\uffff\1\u009d"+
        "\1\uffff\1\u009f\10\uffff\1\u00a0\3\uffff\1\u00a0\1\155\3\uffff"+
        "\1\155\1\54\4\uffff\1\u00a6\1\u00a7\10\54\2\uffff\1\54\1\uffff\2"+
        "\54\1\u00b3\1\54\1\uffff\1\u00b5\7\54\1\u00bd\1\uffff\1\u00be\1"+
        "\54\11\uffff\1\155\1\u00a0\1\uffff\1\u00a0\3\uffff\1\u00c1\11\54"+
        "\1\u00cb\1\uffff\1\54\1\uffff\1\54\1\u00ce\1\54\1\u00d0\1\u00d1"+
        "\1\54\1\u00d3\2\uffff\1\54\1\162\1\uffff\1\u00d5\1\u00d6\1\54\1"+
        "\u00d8\1\u00d9\1\54\1\u00db\2\54\1\uffff\2\54\1\uffff\1\54\2\uffff"+
        "\1\u00e1\1\uffff\1\54\2\uffff\1\54\2\uffff\1\u00e4\1\uffff\1\u00e5"+
        "\1\54\1\u00e7\1\u00e8\1\u00e9\1\uffff\1\u00ea\1\54\2\uffff\1\u00ec"+
        "\4\uffff\1\u00ed\2\uffff";
    static final String DFA35_eofS =
        "\u00ee\uffff";
    static final String DFA35_minS =
        "\1\11\1\145\1\141\1\162\1\154\1\42\1\151\1\146\1\151\1\156\2\154"+
        "\1\150\2\162\1\157\1\141\7\uffff\2\75\1\52\1\57\2\75\1\74\3\75\3"+
        "\uffff\1\75\2\uffff\1\60\2\56\1\42\3\uffff\2\11\3\uffff\1\146\1"+
        "\163\1\151\1\145\1\156\1\141\1\151\1\164\1\145\2\60\1\160\1\60\1"+
        "\156\1\157\1\162\1\60\1\144\1\157\1\143\2\151\1\164\1\171\1\60\1"+
        "\164\1\155\5\uffff\1\75\1\uffff\1\75\10\uffff\1\75\1\uffff\1\75"+
        "\10\uffff\1\60\1\uffff\1\60\1\uffff\1\60\1\56\1\53\2\uffff\1\56"+
        "\1\42\1\0\2\uffff\1\0\2\60\1\163\1\156\1\141\1\164\2\163\1\165\1"+
        "\154\2\uffff\1\157\1\uffff\1\141\1\155\1\60\1\145\1\uffff\1\60\1"+
        "\142\1\143\2\145\1\146\1\154\1\150\1\60\1\uffff\1\60\1\142\11\uffff"+
        "\4\60\3\uffff\1\60\1\164\1\153\1\151\1\163\1\145\1\162\1\144\1\162"+
        "\1\154\1\60\1\uffff\1\162\1\uffff\1\141\1\60\1\160\2\60\1\145\1"+
        "\60\2\uffff\1\144\1\112\1\uffff\2\60\1\156\2\60\1\156\1\60\1\164"+
        "\1\154\1\uffff\1\164\1\154\1\uffff\1\164\2\uffff\1\60\1\uffff\1"+
        "\141\2\uffff\1\165\2\uffff\1\60\1\uffff\1\60\1\171\3\60\1\uffff"+
        "\1\60\1\145\2\uffff\1\60\4\uffff\1\60\2\uffff";
    static final String DFA35_maxS =
        "\1\176\1\145\2\162\1\157\1\145\1\151\1\163\1\162\1\163\1\154\1\170"+
        "\1\151\2\162\1\157\1\141\7\uffff\6\75\2\76\2\75\3\uffff\1\75\2\uffff"+
        "\1\71\1\170\1\154\1\162\3\uffff\2\43\3\uffff\1\154\1\163\1\151\1"+
        "\145\1\156\1\141\1\151\1\164\1\145\2\172\1\160\1\172\1\156\1\157"+
        "\1\162\1\172\1\144\1\157\1\145\1\163\1\151\1\164\1\171\1\172\1\164"+
        "\1\155\5\uffff\1\75\1\uffff\1\75\10\uffff\1\75\1\uffff\1\75\10\uffff"+
        "\1\152\1\uffff\1\146\1\uffff\1\152\1\154\1\71\2\uffff\1\154\1\47"+
        "\1\0\2\uffff\1\0\2\172\1\163\1\156\1\141\1\164\2\163\1\165\1\154"+
        "\2\uffff\1\157\1\uffff\1\141\1\155\1\172\1\145\1\uffff\1\172\1\142"+
        "\1\143\2\145\1\146\1\154\1\150\1\172\1\uffff\1\172\1\142\11\uffff"+
        "\1\154\1\152\1\71\1\152\3\uffff\1\172\1\164\1\153\1\151\1\163\1"+
        "\145\1\162\1\144\1\162\1\154\1\172\1\uffff\1\162\1\uffff\1\141\1"+
        "\172\1\160\2\172\1\145\1\172\2\uffff\1\144\1\152\1\uffff\2\172\1"+
        "\156\2\172\1\156\1\172\1\164\1\154\1\uffff\1\164\1\154\1\uffff\1"+
        "\164\2\uffff\1\172\1\uffff\1\141\2\uffff\1\165\2\uffff\1\172\1\uffff"+
        "\1\172\1\171\3\172\1\uffff\1\172\1\145\2\uffff\1\172\4\uffff\1\172"+
        "\2\uffff";
    static final String DFA35_acceptS =
        "\21\uffff\1\40\1\41\1\42\1\43\1\44\1\45\1\46\12\uffff\1\61\1\62"+
        "\1\63\1\uffff\1\65\1\67\4\uffff\1\123\1\124\1\125\2\uffff\1\130"+
        "\1\131\1\132\33\uffff\1\75\1\47\1\76\1\50\1\100\1\uffff\1\51\1\uffff"+
        "\1\102\1\52\1\103\1\53\1\105\1\54\1\70\1\71\1\uffff\1\55\1\uffff"+
        "\1\73\1\56\1\66\1\57\1\104\1\60\1\106\1\64\1\uffff\1\113\1\uffff"+
        "\1\121\3\uffff\1\122\1\120\3\uffff\1\130\1\127\13\uffff\1\34\1\17"+
        "\1\uffff\1\21\4\uffff\1\14\11\uffff\1\31\2\uffff\1\111\1\77\1\112"+
        "\1\101\1\107\1\72\1\110\1\74\1\114\4\uffff\1\126\1\3\1\1\13\uffff"+
        "\1\25\1\uffff\1\32\7\uffff\1\26\1\33\2\uffff\1\4\11\uffff\1\13\2"+
        "\uffff\1\16\1\uffff\1\23\1\22\1\uffff\1\36\1\uffff\1\2\1\5\1\uffff"+
        "\1\37\1\11\1\uffff\1\10\5\uffff\1\24\2\uffff\1\7\1\12\1\uffff\1"+
        "\20\1\15\1\27\1\35\1\uffff\1\30\1\6";
    static final String DFA35_specialS =
        "\1\3\56\uffff\1\2\1\4\104\uffff\1\0\2\uffff\1\1\165\uffff}>";
    static final String[] DFA35_transitionS = {
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
            "\1\55\4\uffff\1\55\71\uffff\1\72\3\uffff\1\73",
            "\1\74",
            "\1\100\6\uffff\1\77\1\76\4\uffff\1\75",
            "\1\101\5\uffff\1\103\2\uffff\1\102",
            "\1\105\4\uffff\1\104",
            "\1\106",
            "\1\110\13\uffff\1\107",
            "\1\111\1\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "\1\116",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\117",
            "\1\121",
            "\1\124\22\uffff\1\123",
            "\1\126\15\uffff\1\127",
            "\1\131",
            "\1\133",
            "\1\137\1\136\1\135",
            "\1\142\1\141",
            "\1\144",
            "\1\146",
            "",
            "",
            "",
            "\1\150",
            "",
            "",
            "\12\152",
            "\1\156\1\uffff\12\157\13\uffff\1\160\4\uffff\1\161\1\uffff\1"+
            "\162\13\uffff\1\154\14\uffff\1\160\4\uffff\1\161\1\uffff\1\162"+
            "\13\uffff\1\154",
            "\1\156\1\uffff\12\163\13\uffff\1\160\4\uffff\1\161\1\uffff\1"+
            "\162\30\uffff\1\160\4\uffff\1\161\1\uffff\1\162",
            "\1\55\4\uffff\1\55\112\uffff\1\164",
            "",
            "",
            "",
            "\1\60\1\167\2\uffff\1\167\22\uffff\1\57\2\uffff\1\166",
            "\1\60\1\167\2\uffff\1\167\22\uffff\1\57\2\uffff\1\166",
            "",
            "",
            "",
            "\1\172\5\uffff\1\171",
            "\1\173",
            "\1\174",
            "\1\175",
            "\1\176",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "\1\u0082",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u0085",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u0087",
            "\1\u0088",
            "\1\u0089",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\22\54\1\u008a\7\54",
            "\1\u008c",
            "\1\u008d",
            "\1\u008f\1\uffff\1\u008e",
            "\1\u0091\11\uffff\1\u0090",
            "\1\u0092",
            "\1\u0093",
            "\1\u0094",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u0096",
            "\1\u0097",
            "",
            "",
            "",
            "",
            "",
            "\1\u0098",
            "",
            "\1\u009a",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u009c",
            "",
            "\1\u009e",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\152\13\uffff\1\160\4\uffff\1\161\32\uffff\1\160\4\uffff"+
            "\1\161",
            "",
            "\12\u00a1\7\uffff\6\u00a1\32\uffff\6\u00a1",
            "",
            "\12\u00a2\13\uffff\1\160\4\uffff\1\161\32\uffff\1\160\4\uffff"+
            "\1\161",
            "\1\156\1\uffff\12\157\13\uffff\1\160\4\uffff\1\161\1\uffff\1"+
            "\162\30\uffff\1\160\4\uffff\1\161\1\uffff\1\162",
            "\1\u00a3\1\uffff\1\u00a3\2\uffff\12\u00a4",
            "",
            "",
            "\1\156\1\uffff\12\163\13\uffff\1\160\4\uffff\1\161\1\uffff\1"+
            "\162\30\uffff\1\160\4\uffff\1\161\1\uffff\1\162",
            "\1\55\4\uffff\1\55",
            "\1\uffff",
            "",
            "",
            "\1\uffff",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00a8",
            "\1\u00a9",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "\1\u00ad",
            "\1\u00ae",
            "\1\u00af",
            "",
            "",
            "\1\u00b0",
            "",
            "\1\u00b1",
            "\1\u00b2",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00b4",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00b6",
            "\1\u00b7",
            "\1\u00b8",
            "\1\u00b9",
            "\1\u00ba",
            "\1\u00bb",
            "\1\u00bc",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00bf",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\u00a1\7\uffff\6\u00a1\3\uffff\1\161\1\uffff\1\u00c0\24\uffff"+
            "\6\u00a1\3\uffff\1\161\1\uffff\1\u00c0",
            "\12\u00a2\13\uffff\1\160\4\uffff\1\161\32\uffff\1\160\4\uffff"+
            "\1\161",
            "\12\u00a4",
            "\12\u00a4\20\uffff\1\161\37\uffff\1\161",
            "",
            "",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00c2",
            "\1\u00c3",
            "\1\u00c4",
            "\1\u00c5",
            "\1\u00c6",
            "\1\u00c7",
            "\1\u00c8",
            "\1\u00c9",
            "\1\u00ca",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "\1\u00cc",
            "",
            "\1\u00cd",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00cf",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00d2",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "",
            "\1\u00d4",
            "\1\161\37\uffff\1\161",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00d7",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00da",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00dc",
            "\1\u00dd",
            "",
            "\1\u00de",
            "\1\u00df",
            "",
            "\1\u00e0",
            "",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "\1\u00e2",
            "",
            "",
            "\1\u00e3",
            "",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00e6",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00eb",
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

    static final short[] DFA35_eot = DFA.unpackEncodedString(DFA35_eotS);
    static final short[] DFA35_eof = DFA.unpackEncodedString(DFA35_eofS);
    static final char[] DFA35_min = DFA.unpackEncodedStringToUnsignedChars(DFA35_minS);
    static final char[] DFA35_max = DFA.unpackEncodedStringToUnsignedChars(DFA35_maxS);
    static final short[] DFA35_accept = DFA.unpackEncodedString(DFA35_acceptS);
    static final short[] DFA35_special = DFA.unpackEncodedString(DFA35_specialS);
    static final short[][] DFA35_transition;

    static {
        int numStates = DFA35_transitionS.length;
        DFA35_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA35_transition[i] = DFA.unpackEncodedString(DFA35_transitionS[i]);
        }
    }

    class DFA35 extends DFA {

        public DFA35(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 35;
            this.eot = DFA35_eot;
            this.eof = DFA35_eof;
            this.min = DFA35_min;
            this.max = DFA35_max;
            this.accept = DFA35_accept;
            this.special = DFA35_special;
            this.transition = DFA35_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T68 | T69 | T70 | T71 | T72 | T73 | T74 | T75 | T76 | T77 | T78 | T79 | T80 | T81 | T82 | T83 | T84 | T85 | T86 | T87 | T88 | T89 | T90 | T91 | T92 | T93 | T94 | T95 | T96 | T97 | T98 | LPAREN | RPAREN | LBRACK | RBRACK | COLON | COMMA | SEMI | PLUS | MINUS | STAR | SLASH | VBAR | AMPER | LESS | GREATER | ASSIGN | PERCENT | BACKQUOTE | LCURLY | RCURLY | CIRCUMFLEX | TILDE | EQUAL | NOTEQUAL | ALT_NOTEQUAL | LESSEQUAL | LEFTSHIFT | GREATEREQUAL | RIGHTSHIFT | PLUSEQUAL | MINUSEQUAL | DOUBLESTAR | STAREQUAL | DOUBLESLASH | SLASHEQUAL | VBAREQUAL | PERCENTEQUAL | AMPEREQUAL | CIRCUMFLEXEQUAL | LEFTSHIFTEQUAL | RIGHTSHIFTEQUAL | DOUBLESTAREQUAL | DOUBLESLASHEQUAL | DOT | FLOAT | POINTFLOAT | FRACTION | EXPONENTFLOAT | LONGINT | INT | COMPLEX | NAME | STRING | CONTINUED_LINE | WS | LEADING_WS | COMMENT | DECORATOR_S | NEWLINE );";
        }
        public int specialStateTransition(int s, IntStream input) throws NoViableAltException {
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA35_117 = input.LA(1);

                         
                        int index35_117 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (startPos>0) ) {s = 165;}

                        else if ( (((startPos==0&&implicitLineJoiningLevel>0)||startPos==0)) ) {s = 119;}

                         
                        input.seek(index35_117);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA35_120 = input.LA(1);

                         
                        int index35_120 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (startPos>0) ) {s = 165;}

                        else if ( (((startPos==0&&implicitLineJoiningLevel>0)||startPos==0)) ) {s = 119;}

                         
                        input.seek(index35_120);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA35_47 = input.LA(1);

                         
                        int index35_47 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA35_47==' ') && ((startPos>0||startPos==0))) {s = 47;}

                        else if ( (LA35_47=='#') && (startPos==0)) {s = 118;}

                        else if ( (LA35_47=='\n'||LA35_47=='\r') && (startPos==0)) {s = 119;}

                        else if ( (LA35_47=='\t') && ((startPos>0||startPos==0))) {s = 48;}

                        else s = 117;

                         
                        input.seek(index35_47);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA35_0 = input.LA(1);

                         
                        int index35_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA35_0=='d') ) {s = 1;}

                        else if ( (LA35_0=='p') ) {s = 2;}

                        else if ( (LA35_0=='b') ) {s = 3;}

                        else if ( (LA35_0=='c') ) {s = 4;}

                        else if ( (LA35_0=='r') ) {s = 5;}

                        else if ( (LA35_0=='y') ) {s = 6;}

                        else if ( (LA35_0=='i') ) {s = 7;}

                        else if ( (LA35_0=='f') ) {s = 8;}

                        else if ( (LA35_0=='a') ) {s = 9;}

                        else if ( (LA35_0=='g') ) {s = 10;}

                        else if ( (LA35_0=='e') ) {s = 11;}

                        else if ( (LA35_0=='w') ) {s = 12;}

                        else if ( (LA35_0=='t') ) {s = 13;}

                        else if ( (LA35_0=='o') ) {s = 14;}

                        else if ( (LA35_0=='n') ) {s = 15;}

                        else if ( (LA35_0=='l') ) {s = 16;}

                        else if ( (LA35_0=='(') ) {s = 17;}

                        else if ( (LA35_0==')') ) {s = 18;}

                        else if ( (LA35_0=='[') ) {s = 19;}

                        else if ( (LA35_0==']') ) {s = 20;}

                        else if ( (LA35_0==':') ) {s = 21;}

                        else if ( (LA35_0==',') ) {s = 22;}

                        else if ( (LA35_0==';') ) {s = 23;}

                        else if ( (LA35_0=='+') ) {s = 24;}

                        else if ( (LA35_0=='-') ) {s = 25;}

                        else if ( (LA35_0=='*') ) {s = 26;}

                        else if ( (LA35_0=='/') ) {s = 27;}

                        else if ( (LA35_0=='|') ) {s = 28;}

                        else if ( (LA35_0=='&') ) {s = 29;}

                        else if ( (LA35_0=='<') ) {s = 30;}

                        else if ( (LA35_0=='>') ) {s = 31;}

                        else if ( (LA35_0=='=') ) {s = 32;}

                        else if ( (LA35_0=='%') ) {s = 33;}

                        else if ( (LA35_0=='`') ) {s = 34;}

                        else if ( (LA35_0=='{') ) {s = 35;}

                        else if ( (LA35_0=='}') ) {s = 36;}

                        else if ( (LA35_0=='^') ) {s = 37;}

                        else if ( (LA35_0=='~') ) {s = 38;}

                        else if ( (LA35_0=='!') ) {s = 39;}

                        else if ( (LA35_0=='.') ) {s = 40;}

                        else if ( (LA35_0=='0') ) {s = 41;}

                        else if ( ((LA35_0>='1' && LA35_0<='9')) ) {s = 42;}

                        else if ( (LA35_0=='u') ) {s = 43;}

                        else if ( ((LA35_0>='A' && LA35_0<='Z')||LA35_0=='_'||LA35_0=='h'||(LA35_0>='j' && LA35_0<='k')||LA35_0=='m'||LA35_0=='q'||LA35_0=='s'||LA35_0=='v'||LA35_0=='x'||LA35_0=='z') ) {s = 44;}

                        else if ( (LA35_0=='\"'||LA35_0=='\'') ) {s = 45;}

                        else if ( (LA35_0=='\\') ) {s = 46;}

                        else if ( (LA35_0==' ') && ((startPos>0||startPos==0))) {s = 47;}

                        else if ( (LA35_0=='\t') && ((startPos>0||startPos==0))) {s = 48;}

                        else if ( (LA35_0=='#') && ((startPos>0||startPos==0))) {s = 49;}

                        else if ( (LA35_0=='@') ) {s = 50;}

                        else if ( (LA35_0=='\n'||LA35_0=='\r') ) {s = 51;}

                         
                        input.seek(index35_0);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA35_48 = input.LA(1);

                         
                        int index35_48 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA35_48==' ') && ((startPos>0||startPos==0))) {s = 47;}

                        else if ( (LA35_48=='#') && (startPos==0)) {s = 118;}

                        else if ( (LA35_48=='\n'||LA35_48=='\r') && (startPos==0)) {s = 119;}

                        else if ( (LA35_48=='\t') && ((startPos>0||startPos==0))) {s = 48;}

                        else s = 120;

                         
                        input.seek(index35_48);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 35, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}