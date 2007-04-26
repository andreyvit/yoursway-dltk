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