#! /bin/sh
# uses Jay, see http://www.cs.rit.edu/~ats/projects/lp/doc/jay/package-summary.html
# uses skeleton files installed in /usr/local/share/jay/, correct the path if necessary

jay DefaultRubyParser.y </usr/local/share/jay/skeleton.tables >DefaultRubyParser.java
grep ^//yy DefaultRubyParser.java | cut -c5- > RubyParser.tables
perl -i -pe 's/yyInput yyLex/RubyYaccLexer yyLex/g' DefaultRubyParser.java
perl -i -pe 's/yyExpecting\(yyState\)/yyExpecting(yyState), yyNames[yyToken]/g' DefaultRubyParser.java
perl -i -pe 's/yyerror\(message, null\);/yyerror(message, null, null);/g' DefaultRubyParser.java
perl -i -pe 's/public void yyerror \(String message, String\[\] expected\)/public void yyerror (String message, String[] expected, String found)/g' DefaultRubyParser.java
perl -i -pe 's/\{ if \(yyLhs == null\)/{yyLhs = null; if (yyLhs == null)/g' DefaultRubyParser.java
perl -i -pe 's/\r//g' DefaultRubyParser.java
