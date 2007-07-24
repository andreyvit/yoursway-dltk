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

T65 : 'def' ;
T66 : 'print' ;
T67 : 'del' ;
T68 : 'pass' ;
T69 : 'break' ;
T70 : 'continue' ;
T71 : 'return' ;
T72 : 'yield' ;
T73 : 'raise' ;
T74 : 'import' ;
T75 : 'from' ;
T76 : 'as' ;
T77 : 'global' ;
T78 : 'exec' ;
T79 : 'in' ;
T80 : 'assert' ;
T81 : 'if' ;
T82 : 'elif' ;
T83 : 'else' ;
T84 : 'while' ;
T85 : 'for' ;
T86 : 'try' ;
T87 : 'except' ;
T88 : 'finally' ;
T89 : 'or' ;
T90 : 'and' ;
T91 : 'not' ;
T92 : 'is' ;
T93 : 'lambda' ;
T94 : 'class' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1641
LPAREN	: '(' {implicitLineJoiningLevel++;} ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1643
RPAREN	: ')' {implicitLineJoiningLevel--;} ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1645
LBRACK	: '[' {implicitLineJoiningLevel++;} ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1647
RBRACK	: ']' {implicitLineJoiningLevel--;} ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1649
COLON 	: ':' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1651
COMMA	: ',' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1653
SEMI	: ';' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1655
PLUS	: '+' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1657
MINUS	: '-' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1659
STAR	: '*' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1661
SLASH	: '/' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1663
VBAR	: '|' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1665
AMPER	: '&' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1667
LESS	: '<' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1669
GREATER	: '>' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1671
ASSIGN	: '=' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1673
PERCENT	: '%' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1675
BACKQUOTE	: '`' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1677
LCURLY	: '{' {implicitLineJoiningLevel++;} ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1679
RCURLY	: '}' {implicitLineJoiningLevel--;} ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1681
CIRCUMFLEX	: '^' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1683
TILDE	: '~' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1685
EQUAL	: '==' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1687
NOTEQUAL	: '!=' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1689
ALT_NOTEQUAL: '<>' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1691
LESSEQUAL	: '<=' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1693
LEFTSHIFT	: '<<' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1695
GREATEREQUAL	: '>=' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1697
RIGHTSHIFT	: '>>' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1699
PLUSEQUAL	: '+=' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1701
MINUSEQUAL	: '-=' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1703
DOUBLESTAR	: '**' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1705
STAREQUAL	: '*=' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1707
DOUBLESLASH	: '//' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1709
SLASHEQUAL	: '/=' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1711
VBAREQUAL	: '|=' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1713
PERCENTEQUAL	: '%=' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1715
AMPEREQUAL	: '&=' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1717
CIRCUMFLEXEQUAL	: '^=' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1719
LEFTSHIFTEQUAL	: '<<=' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1721
RIGHTSHIFTEQUAL	: '>>=' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1723
DOUBLESTAREQUAL	: '**=' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1725
DOUBLESLASHEQUAL	: '//=' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1727
DOT : '.' ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1729
FLOAT
	:	'.' DIGITS (Exponent)?
    |   DIGITS ('.' (DIGITS (Exponent)?)? | Exponent)
    ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1734
LONGINT
    :   INT ('l'|'L')
    ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1738
fragment
Exponent
	:	('e' | 'E') ( '+' | '-' )? DIGITS
	;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1743
INT :   // Hex
        '0' ('x' | 'X') ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+
        ('l' | 'L')?
    |   // Octal
        '0' DIGITS*
    |   '1'..'9' DIGITS*
    ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1751
COMPLEX
    :   INT ('j'|'J')
    |   FLOAT ('j'|'J')
    ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1756
fragment
DIGITS : ( '0' .. '9' )+ ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1759
NAME:	( 'a' .. 'z' | 'A' .. 'Z' | '_')
        ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
    ;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1763
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
// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1774
fragment
ESC
	:	'\\' .
	;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1779
/** Consume a newline and any whitespace at start of next line */
CONTINUED_LINE
	:	'\\' ('\r')? '\n' (' '|'\t')* {
		 //fModule.acceptLine(getColumn());
		 /*newline();*/ $channel=HIDDEN; }
	;

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1786
WS	:	{startPos>0}?=> (' '|'\t')+ {$channel=HIDDEN;}
	; 
// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1788
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

// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1828
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
// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1869
DECORATOR_S:
	'@'
;
// $ANTLR src "C:\Develop\dltk\org.eclipse.dltk.python.core\src\org\eclipse\dltk\python\internal\core\parsers\python_v3.g" 1872
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
