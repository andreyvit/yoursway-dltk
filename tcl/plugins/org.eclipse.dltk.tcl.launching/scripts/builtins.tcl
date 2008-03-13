puts "#Tcl interpreter builtins for:[info nameofexecutable]"
set vars [info vars]
set procs [info procs]
puts "\n"
puts "#Procedures"
foreach p $procs {
    puts "proc $p {[info args $p]} {}"
}

puts "\n"
puts "#Variables"
foreach v $vars {
    if {[array exists $v]} {
	set ns [array names $v]
	foreach n $ns {
	    puts "set $v\($n\) [expr "$$v\($n\)"]"
	}
    } else {
	puts "set $v [expr "$$v"]"
    }
}