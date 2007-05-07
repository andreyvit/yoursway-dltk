###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

proc foo { a } {
	puts $a ; puts "wow!"
}

set x 13 ; #good number \
	isn't it?
	
puts $x
foo $x

puts [expr {$x + 1}]

set string "_________! and number really is $x..."

set {a
b} 12 ; puts ${a
b} 

#simple comment here

puts "bye! \
and thanks for a fish!"