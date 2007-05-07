###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

set x1 90
puts $x1 ;# work
puts "$x1" ;# work
puts "a $x1 b" ;# work
puts "a$x1 b" ;# work
puts ${x1} ;# not work
puts "${x1}" ;# not work

set {x1 2} 91
puts ${x1 2} ;# work
puts "${x1 2}" ;# not work

set x2(0) 1
set x2(1) 2
set x2("gamma") 2
set {x2("gamma a")} 2

puts $x2(0) ;#not work
puts ${x2(0)} ;#not work
puts "$x2(0)" ;#not work
puts "${x2(0)}" ;#not work

puts $x2("gamma a") ;#not work
puts ${x2("gamma a")} ;#not work
puts "$x2(\"gamma a\")" ;#not work
puts "cool"
puts "This is in string ${x2("gamma a")} after variable" ;#not work

proc ff {} {
	return "aa"
}
set [ff] "cool"
puts "cool:"
puts $aa

catch { set x3 93 }
puts $x3
puts ${x3}

after 800 [set x4 92]
puts [set x5 95]
puts $x5

set x6 1
puts [$x6]
puts "[$x6]"

# end of file test
set x7 2
puts $x7