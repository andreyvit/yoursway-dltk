// $ANTLR : "RubyGrammar.g" -> "RubyLexer.java"$
 
	package org.eclipse.dltk.ruby.internal.parsers; 
	
	




public interface RubyParserTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int LITERAL_class = 4;
	int IDENTIFIER = 5;
	// "<" = 6
	int LITERAL_end = 7;
	int LITERAL_def = 8;
	// "(" = 9
	// "," = 10
	// ")" = 11
	int LITERAL_if = 12;
	int LITERAL_while = 13;
	int LITERAL_loop = 14;
	int LITERAL_do = 15;
	int LITERAL_yield = 16;
	int LITERAL_return = 17;
	// "=" = 18
	int NUMBER = 19;
	int STRING = 20;
	// "|" = 21
	// "<=" = 22
	// ">=" = 23
	// ">" = 24
	// "==" = 25
	// "%" = 26
	// "&" = 27
	int LITERAL_true = 28;
	int LITERAL_false = 29;
	// "/" = 30
	// "*" = 31
	// "+" = 32
	// "-" = 33
	int LPAREN = 34;
	int RPAREN = 35;
	int LT = 36;
	int LE = 37;
	int GE = 38;
	int COLON = 39;
	int COMMA = 40;
	int SEMI = 41;
	int GT = 42;
	int EGUAL = 43;
	int DIV = 44;
	int MUL = 45;
	int ASSIGN = 46;
	int PLUS = 47;
	int OR = 48;
	int AND = 49;
	int SUB = 50;
	int MOD = 51;
	int QUESTION = 52;
	int NOT = 53;
	int LBRACE = 54;
	int RBRACE = 55;
	int LBRACK = 56;
	int RBRACK = 57;
	int ESC = 58;
	int NEWLINE = 59;
	int WS = 60;
	int COMMENT = 61;
}
