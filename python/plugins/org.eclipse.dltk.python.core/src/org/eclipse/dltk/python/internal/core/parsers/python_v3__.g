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

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1669
LPAREN	: '(' {implicitLineJoiningLevel++;} ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1671
RPAREN	: ')' {implicitLineJoiningLevel--;} ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1673
LBRACK	: '[' {implicitLineJoiningLevel++;} ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1675
RBRACK	: ']' {implicitLineJoiningLevel--;} ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1677
COLON 	: ':' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1679
COMMA	: ',' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1681
SEMI	: ';' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1683
PLUS	: '+' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1685
MINUS	: '-' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1687
STAR	: '*' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1689
SLASH	: '/' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1691
VBAR	: '|' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1693
AMPER	: '&' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1695
LESS	: '<' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1697
GREATER	: '>' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1699
ASSIGN	: '=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1701
PERCENT	: '%' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1703
BACKQUOTE	: '`' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1705
LCURLY	: '{' {implicitLineJoiningLevel++;} ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1707
RCURLY	: '}' {implicitLineJoiningLevel--;} ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1709
CIRCUMFLEX	: '^' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1711
TILDE	: '~' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1713
EQUAL	: '==' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1715
NOTEQUAL	: '!=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1717
ALT_NOTEQUAL: '<>' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1719
LESSEQUAL	: '<=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1721
LEFTSHIFT	: '<<' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1723
GREATEREQUAL	: '>=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1725
RIGHTSHIFT	: '>>' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1727
PLUSEQUAL	: '+=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1729
MINUSEQUAL	: '-=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1731
DOUBLESTAR	: '**' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1733
STAREQUAL	: '*=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1735
DOUBLESLASH	: '//' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1737
SLASHEQUAL	: '/=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1739
VBAREQUAL	: '|=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1741
PERCENTEQUAL	: '%=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1743
AMPEREQUAL	: '&=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1745
CIRCUMFLEXEQUAL	: '^=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1747
LEFTSHIFTEQUAL	: '<<=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1749
RIGHTSHIFTEQUAL	: '>>=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1751
DOUBLESTAREQUAL	: '**=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1753
DOUBLESLASHEQUAL	: '//=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1755
DOT : '.' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1757
FLOAT  	:	POINTFLOAT | EXPONENTFLOAT
//	:	'.' DIGITS (Exponent)?
//    |   DIGITS ('.' (DIGITS (Exponent)?)? | Exponent)
    ;
// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1761
POINTFLOAT
	:	DIGITS? FRACTION | DIGITS '.'
	;
// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1764
FRACTION 
	:	'.' DIGITS
	;
// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1767
EXPONENTFLOAT 
	:	(DIGITS | POINTFLOAT) Exponent
	;
// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1770
LONGINT
    :   INT ('l'|'L')
    ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1774
fragment
Exponent
	:	('e' | 'E') ( '+' | '-' )? DIGITS
	;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1779
INT :   // Hex
        '0' ('x' | 'X') ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+
        ('l' | 'L')?
    |   // Octal
        '0' DIGITS*
    |   '1'..'9' DIGITS*
    ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1787
COMPLEX
    :   INT ('j'|'J')
    |   FLOAT ('j'|'J')
    ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1792
fragment
DIGITS : ( '0' .. '9' )+ ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1795
NAME:	( 'a' .. 'z' | 'A' .. 'Z' | '_')
        ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
    ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1799
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
// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1810
fragment
ESC
	:	'\\' .
	;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1815
/** Consume a newline and any whitespace at start of next line */
CONTINUED_LINE
	:	'\\' ('\r')? '\n' (' '|'\t')* {
		 //fModule.acceptLine(getColumn());
		 /*newline();*/ $channel=HIDDEN; }
	;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1822
WS	:	{startPos>0}?=> (' '|'\t')+ {$channel=HIDDEN;}
	; 
// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1824
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

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1864
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
// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1905
DECORATOR_S:
	'@'
;
// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1908
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
