lexer grammar python_v3;
options {
  language=Java;

}
@members {
/** Handles context-sensitive lexing of implicit line joining such as
 *  the case where newline is ignored in cases like this:
 *  a = [3,
 *       4]
 */
public int implicitLineJoiningLevel = 0;
public int startPos=-1;
public void emitErrorMessage(String msg) {
}
}
@header {
	package org.eclipse.dltk.python.internal.core.parsers;
}

T68 : 'def' ;
T69 : 'print' ;
T70 : 'del' ;
T71 : 'pass' ;
T72 : 'break' ;
T73 : 'continue' ;
T74 : 'return' ;
T75 : 'yield' ;
T76 : 'raise' ;
T77 : 'import' ;
T78 : 'from' ;
T79 : 'as' ;
T80 : 'global' ;
T81 : 'exec' ;
T82 : 'in' ;
T83 : 'assert' ;
T84 : 'if' ;
T85 : 'elif' ;
T86 : 'else' ;
T87 : 'while' ;
T88 : 'for' ;
T89 : 'try' ;
T90 : 'except' ;
T91 : 'finally' ;
T92 : 'or' ;
T93 : 'and' ;
T94 : 'not' ;
T95 : 'is' ;
T96 : 'lambda' ;
T97 : 'with' ;
T98 : 'class' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1664
LPAREN	: '(' {implicitLineJoiningLevel++;} ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1666
RPAREN	: ')' {implicitLineJoiningLevel--;} ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1668
LBRACK	: '[' {implicitLineJoiningLevel++;} ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1670
RBRACK	: ']' {implicitLineJoiningLevel--;} ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1672
COLON 	: ':' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1674
COMMA	: ',' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1676
SEMI	: ';' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1678
PLUS	: '+' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1680
MINUS	: '-' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1682
STAR	: '*' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1684
SLASH	: '/' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1686
VBAR	: '|' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1688
AMPER	: '&' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1690
LESS	: '<' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1692
GREATER	: '>' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1694
ASSIGN	: '=' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1696
PERCENT	: '%' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1698
BACKQUOTE	: '`' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1700
LCURLY	: '{' {implicitLineJoiningLevel++;} ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1702
RCURLY	: '}' {implicitLineJoiningLevel--;} ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1704
CIRCUMFLEX	: '^' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1706
TILDE	: '~' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1708
EQUAL	: '==' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1710
NOTEQUAL	: '!=' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1712
ALT_NOTEQUAL: '<>' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1714
LESSEQUAL	: '<=' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1716
LEFTSHIFT	: '<<' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1718
GREATEREQUAL	: '>=' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1720
RIGHTSHIFT	: '>>' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1722
PLUSEQUAL	: '+=' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1724
MINUSEQUAL	: '-=' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1726
DOUBLESTAR	: '**' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1728
STAREQUAL	: '*=' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1730
DOUBLESLASH	: '//' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1732
SLASHEQUAL	: '/=' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1734
VBAREQUAL	: '|=' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1736
PERCENTEQUAL	: '%=' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1738
AMPEREQUAL	: '&=' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1740
CIRCUMFLEXEQUAL	: '^=' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1742
LEFTSHIFTEQUAL	: '<<=' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1744
RIGHTSHIFTEQUAL	: '>>=' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1746
DOUBLESTAREQUAL	: '**=' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1748
DOUBLESLASHEQUAL	: '//=' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1750
DOT : '.' ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1752
FLOAT  	:	POINTFLOAT | EXPONENTFLOAT
//	:	'.' DIGITS (Exponent)?
//    |   DIGITS ('.' (DIGITS (Exponent)?)? | Exponent)
    ;
// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1756
POINTFLOAT
	:	DIGITS? FRACTION | DIGITS '.'
	;
// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1759
FRACTION 
	:	'.' DIGITS
	;
// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1762
EXPONENTFLOAT 
	:	(DIGITS | POINTFLOAT) Exponent
	;
// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1765
LONGINT
    :   INT ('l'|'L')
    ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1769
fragment
Exponent
	:	('e' | 'E') ( '+' | '-' )? DIGITS
	;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1774
INT :   // Hex
        '0' ('x' | 'X') ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+
        ('l' | 'L')?
    |   // Octal
        '0' DIGITS*
    |   '1'..'9' DIGITS*
    ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1782
COMPLEX
    :   INT ('j'|'J')
    |   FLOAT ('j'|'J')
    ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1787
fragment
DIGITS : ( '0' .. '9' )+ ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1790
NAME:	( 'a' .. 'z' | 'A' .. 'Z' | '_')
        ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
    ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1794
/** Match various string types.  Note that greedy=false implies '''
 *  should make us exit loop not continue.
 */
STRING
    :   ('r'|'u'|'ur')?
        (   '\'\'\'' (options {greedy=false;}:.)* '\'\'\''
        |   '"""' (options {greedy=false;}:.)* '"""'
        |   '"' (ESC|~('\\'|'\n'|'"'))* '"'
        |   '\'' (ESC|~('\\'|'\n'|'\''))* '\''
        )
	; 
// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1805
fragment
ESC
	:	'\\' .
	;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1810
/** Consume a newline and any whitespace at start of next line */
CONTINUED_LINE
	:	'\\' ('\r')? '\n' (' '|'\t')* {
		 //fModule.acceptLine(getColumn());
		 /*newline();*/ $channel=HIDDEN; }
	;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1817
WS	:	{startPos>0}?=> (' '|'\t')+ {$channel=HIDDEN;}
	; 
// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1819
/** Grab everything before a real symbol.  Then if newline, kill it
 *  as this is a blank line.  If whitespace followed by comment, kill it
 *  as it's a comment on a line by itself.
 *
 *  Ignore leading whitespace when nested in [..], (..), {..}.
 */
LEADING_WS
@init {
    int spaces = 0;
}
    :   {startPos==0}?=>
    	(   {implicitLineJoiningLevel>0}? ( ' ' | '\t' )+ {$channel=HIDDEN;}
       	|	( 	' '  { spaces++; }
        	|	'\t' { spaces += 8; spaces -= (spaces \% 8); }
       		)+
        	{
            // make a string of n spaces where n is column number - 1
            char[] indentation = new char[spaces];
            for (int i=0; i<spaces; i++) {
                indentation[i] = ' ';
            }
            String s = new String(indentation);
            Token tok = new ClassicToken(LEADING_WS,new String(indentation));
            emit(tok);
        }
        	// kill trailing newline if present and then ignore
        	( ('\r')? '\n' {if (token!=null) token.setChannel(99); else $channel=HIDDEN;})*
           // {token.setChannel(99); }
        )

/*
        |   // if comment, then only thing on a line; kill so we
            // ignore totally also wack any following newlines as
            // they cannot be terminating a statement
            '#' (~'\n')* ('\n')+ 
            {if (token!=null) token.setChannel(99); else $channel=HIDDEN;}
        )?
        */
    ;

// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1859
/** Comments not on line by themselves are turned into newlines because
    sometimes they are newlines like

    b = a # end of line comment

    or

    a = [1, # weird
         2]

    This rule is invoked directly by nextToken when the comment is in
    first column or when comment is on end of nonwhitespace line.

    The problem is that then we have lots of newlines heading to
    the parser.  To fix that, column==1 implies we should kill whole line.

    Consume any newlines following this comment as they are not statement
    terminators.  Don't let NEWLINE token handle them.
 */

COMMENT
@init {
    $channel=HIDDEN;
}
    :	{startPos==0}?=> (' '|'\t')* '#' (~'\n')* '\n'+
    |	{startPos>0}?=> '#' (~'\n')* // let NEWLINE handle \n unless char pos==0 for '#'
    ;
    
//SPECIAL
//{
//    int startCol = getColumn();
//}
//    :  (~'\n')* // let NEWLINE handle \n unless column = 1 for '#'
//        { $setType(Token.SKIP);        	
//        	fModule.acceptModifier($getText); 
//        	}
//        ( {startCol==1}? ('\n' {
//        	fModule.acceptLine(getColumn());newline();
//}
//        )+ )?
//    ;    
// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1900
DECORATOR_S:
	'@'
;
// $ANTLR src "/Users/fourdman/Projects/internal/ys/dltk-git/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1903
/** Treat a sequence of blank lines as a single blank line.  If
 *  nested within a (..), {..}, or [..], then ignore newlines.
 *  If the first newline starts in column one, they are to be ignored.
 */
NEWLINE
    :   (('\r')? '\n' )+
        {if ( startPos==0 || implicitLineJoiningLevel>0 )
            $channel=HIDDEN;
        }
    ;
