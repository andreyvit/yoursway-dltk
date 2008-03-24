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

T67 : 'def' ;
T68 : 'print' ;
T69 : 'del' ;
T70 : 'pass' ;
T71 : 'break' ;
T72 : 'continue' ;
T73 : 'return' ;
T74 : 'yield' ;
T75 : 'raise' ;
T76 : 'import' ;
T77 : 'from' ;
T78 : 'as' ;
T79 : 'global' ;
T80 : 'exec' ;
T81 : 'in' ;
T82 : 'assert' ;
T83 : 'if' ;
T84 : 'elif' ;
T85 : 'else' ;
T86 : 'while' ;
T87 : 'for' ;
T88 : 'try' ;
T89 : 'except' ;
T90 : 'finally' ;
T91 : 'or' ;
T92 : 'and' ;
T93 : 'not' ;
T94 : 'is' ;
T95 : 'lambda' ;
T96 : 'with' ;
T97 : 'class' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1665
LPAREN	: '(' {implicitLineJoiningLevel++;} ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1667
RPAREN	: ')' {implicitLineJoiningLevel--;} ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1669
LBRACK	: '[' {implicitLineJoiningLevel++;} ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1671
RBRACK	: ']' {implicitLineJoiningLevel--;} ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1673
COLON 	: ':' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1675
COMMA	: ',' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1677
SEMI	: ';' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1679
PLUS	: '+' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1681
MINUS	: '-' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1683
STAR	: '*' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1685
SLASH	: '/' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1687
VBAR	: '|' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1689
AMPER	: '&' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1691
LESS	: '<' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1693
GREATER	: '>' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1695
ASSIGN	: '=' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1697
PERCENT	: '%' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1699
BACKQUOTE	: '`' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1701
LCURLY	: '{' {implicitLineJoiningLevel++;} ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1703
RCURLY	: '}' {implicitLineJoiningLevel--;} ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1705
CIRCUMFLEX	: '^' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1707
TILDE	: '~' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1709
EQUAL	: '==' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1711
NOTEQUAL	: '!=' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1713
ALT_NOTEQUAL: '<>' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1715
LESSEQUAL	: '<=' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1717
LEFTSHIFT	: '<<' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1719
GREATEREQUAL	: '>=' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1721
RIGHTSHIFT	: '>>' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1723
PLUSEQUAL	: '+=' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1725
MINUSEQUAL	: '-=' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1727
DOUBLESTAR	: '**' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1729
STAREQUAL	: '*=' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1731
DOUBLESLASH	: '//' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1733
SLASHEQUAL	: '/=' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1735
VBAREQUAL	: '|=' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1737
PERCENTEQUAL	: '%=' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1739
AMPEREQUAL	: '&=' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1741
CIRCUMFLEXEQUAL	: '^=' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1743
LEFTSHIFTEQUAL	: '<<=' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1745
RIGHTSHIFTEQUAL	: '>>=' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1747
DOUBLESTAREQUAL	: '**=' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1749
DOUBLESLASHEQUAL	: '//=' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1751
DOT : '.' ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1753
FLOAT  	:	POINTFLOAT | EXPONENTFLOAT
//	:	'.' DIGITS (Exponent)?
//    |   DIGITS ('.' (DIGITS (Exponent)?)? | Exponent)
    ;
// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1757
POINTFLOAT
	:	DIGITS? FRACTION | DIGITS '.'
	;
// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1760
FRACTION 
	:	'.' DIGITS
	;
// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1763
EXPONENTFLOAT 
	:	(DIGITS | POINTFLOAT) Exponent
	;
// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1766
fragment
Exponent
	:	('e' | 'E') ( '+' | '-' )? DIGITS
	;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1771
INT :   // Hex
        '0' ('x' | 'X') ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+
        ('l' | 'L')?
    |   // Octal
        '0' DIGITS*
        ('l' | 'L')?
    |   // Decimal
    	'1'..'9' DIGITS*
        ('l' | 'L')?
    ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1782
COMPLEX
    :   DIGITS ('j'|'J')
    |   FLOAT ('j'|'J')
    ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1787
fragment
DIGITS : ( '0' .. '9' )+ ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1790
NAME:	( 'a' .. 'z' | 'A' .. 'Z' | '_')
        ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
    ;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1794
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
// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1805
fragment
ESC
	:	'\\' .
	;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1810
/** Consume a newline and any whitespace at start of next line */
CONTINUED_LINE
	:	'\\' ('\r')? '\n' (' '|'\t')* {
		 //fModule.acceptLine(getColumn());
		 /*newline();*/ $channel=HIDDEN; }
	;

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1817
WS	:	{startPos>0}?=> (' '|'\t')+ {$channel=HIDDEN;}
	; 
// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1819
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

// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1859
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
// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1900
DECORATOR_S:
	'@'
;
// $ANTLR src "/Users/buriy/Documents/Eclipse/dltk-yoursway/python/plugins/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1903
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
