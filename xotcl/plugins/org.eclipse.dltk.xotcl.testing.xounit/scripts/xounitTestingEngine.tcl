#puts "TCLTESTENGINE:START"
#puts "args: $argv"
#puts $auto_path
set myvars $argv
set script [lindex $argv 0]
set argv [lrange $argv 1 [llength $argv]]
set argv0 $script
catch {
	package require XOTcl
	package require xox
	package require xounit
	package require xoexception
} e
#puts $e
set libs 0
foreach name [package names] {
	if { "x$name" == "xXOTcl" } {
		puts "XOTcl pressent"
		incr libs
	} elseif { "x$name" == "xxox" } {
		puts "xox pressent"
		incr libs
	} elseif { "x$name" == "xxounit" } {
		puts "xounit pressent"
		incr libs
	} elseif { "x$name" == "xxoexception" } {
		puts "xexception pressent"
		incr libs
	}
}
if {$libs != 4 } {
	puts "Could not find xounit libraries. Pleae configure your project."
	exit
}
puts [package names]
package require XOTcl
package require xox
package require xounit
package require xoexception
puts "ENV VARS: $env(XOUNIT_TEST_NAMESPACE) $env(XOUNIT_TEST_NAMES)"
set tests $env(XOUNIT_TEST_NAMES)
set testNames [split $tests { }]
foreach test $testNames {
	puts "$test"
	catch {
		eval ::xounit::RunTest new $env(XOUNIT_TEST_NAMESPACE) $test
	}
} 
#eval ::xounit::RunTests new $env(XOUNIT_TEST_NAMESPACE) testNames 
#$env(XOUNIT_TEST_NAME)
#source $script
#puts "TCLTESTENGINE:END"
