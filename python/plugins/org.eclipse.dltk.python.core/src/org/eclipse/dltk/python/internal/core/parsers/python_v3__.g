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

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1650
LPAREN	: '(' {implicitLineJoiningLevel++;} ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1652
RPAREN	: ')' {implicitLineJoiningLevel--;} ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1654
LBRACK	: '[' {implicitLineJoiningLevel++;} ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1656
RBRACK	: ']' {implicitLineJoiningLevel--;} ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1658
COLON 	: ':' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1660
COMMA	: ',' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1662
SEMI	: ';' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1664
PLUS	: '+' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1666
MINUS	: '-' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1668
STAR	: '*' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1670
SLASH	: '/' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1672
VBAR	: '|' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1674
AMPER	: '&' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1676
LESS	: '<' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1678
GREATER	: '>' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1680
ASSIGN	: '=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1682
PERCENT	: '%' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1684
BACKQUOTE	: '`' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1686
LCURLY	: '{' {implicitLineJoiningLevel++;} ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1688
RCURLY	: '}' {implicitLineJoiningLevel--;} ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1690
CIRCUMFLEX	: '^' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1692
TILDE	: '~' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1694
EQUAL	: '==' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1696
NOTEQUAL	: '!=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1698
ALT_NOTEQUAL: '<>' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1700
LESSEQUAL	: '<=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1702
LEFTSHIFT	: '<<' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1704
GREATEREQUAL	: '>=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1706
RIGHTSHIFT	: '>>' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1708
PLUSEQUAL	: '+=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1710
MINUSEQUAL	: '-=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1712
DOUBLESTAR	: '**' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1714
STAREQUAL	: '*=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1716
DOUBLESLASH	: '//' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1718
SLASHEQUAL	: '/=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1720
VBAREQUAL	: '|=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1722
PERCENTEQUAL	: '%=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1724
AMPEREQUAL	: '&=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1726
CIRCUMFLEXEQUAL	: '^=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1728
LEFTSHIFTEQUAL	: '<<=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1730
RIGHTSHIFTEQUAL	: '>>=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1732
DOUBLESTAREQUAL	: '**=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1734
DOUBLESLASHEQUAL	: '//=' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1736
DOT : '.' ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1738
FLOAT
	:	'.' DIGITS (Exponent)?
    |   DIGITS ('.' (DIGITS (Exponent)?)? | Exponent)
    ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1743
LONGINT
    :   INT ('l'|'L')
    ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1747
fragment
Exponent
	:	('e' | 'E') ( '+' | '-' )? DIGITS
	;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1752
INT :   // Hex
        '0' ('x' | 'X') ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+
        ('l' | 'L')?
    |   // Octal
        '0' DIGITS*
    |   '1'..'9' DIGITS*
    ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1760
COMPLEX
    :   INT ('j'|'J')
    |   FLOAT ('j'|'J')
    ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1765
fragment
DIGITS : ( '0' .. '9' )+ ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1768
NAME:	( 'a' .. 'z' | 'A' .. 'Z' | '_')
        ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
    ;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1772
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
// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1783
fragment
ESC
	:	'\\' .
	;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1788
/** Consume a newline and any whitespace at start of next line */
CONTINUED_LINE
	:	'\\' ('\r')? '\n' (' '|'\t')* {
		 //fModule.acceptLine(getColumn());
		 /*newline();*/ $channel=HIDDEN; }
	;

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1795
WS	:	{startPos>0}?=> (' '|'\t')+ {$channel=HIDDEN;}
	; 
// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1797
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

// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1837
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
// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1878
DECORATOR_S:
	'@'
;
// $ANTLR src "/home/leonty/eclipse_workspace/org.eclipse.dltk.python.core/src/org/eclipse/dltk/python/internal/core/parsers/python_v3.g" 1881
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
