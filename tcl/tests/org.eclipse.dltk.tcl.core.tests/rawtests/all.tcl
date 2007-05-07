###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

word0 word1 word2; #it is simple command
fakeCommentWordCmd #and lot of fake word

puts\
42

	tabSeparatedCommand		tabSeparatedArg

set a "simple\t(it was a tab)text\nwow!\
space should go before it."

set a {braces leave 
everything exactly}

proc foo {} {
}

namespace eval Moo {
	proc bar {} {
		return [expr {2+2}]
	}
}

puts $a
puts $(10)
puts $a(\))
puts $() ; #great code! no name, no index! )
puts $abcdef_XYZ(1)
puts $abcdef_XYZ([expr {2+2}]$a)
puts ${!@#$%^&*().,/?:;'"[]{-=_+\|~`}
puts $___::___::___::____(12)
puts $a$b$c[doo 4]$b(123)[foo]${$%@#$^@!!!!}.$___::___::___::____()


word [command] "string" {braces}

proc {
} {} { puts newline! }

{
}

$a $b $c


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

puts \
a
puts \123
puts \999; #not a substitution!
puts \xFF
puts \u12345


set y [set x 0][incr x][incr x]	