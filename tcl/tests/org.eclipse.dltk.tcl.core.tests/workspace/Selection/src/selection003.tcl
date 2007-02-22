namespace eval a {
	variable va 0
	#This is fa comment
	proc fa { } {
		set vfa 1
		puts "fa"
	}
	namespace eval c {
		set vac 2
		variable vac2 3
		proc fac { } {
			puts "fac"
		}
	}
	namespace eval f {
		variable vaf 4
		proc faf {} {
			puts "faf"
		}
		namespace eval q {
			variable vafq 5
			proc fafq {} {
				puts "fafq"
			}
		}
	}
	namespace eval f::q {
		variable vafq2 6
		proc faf_q { } { 
			puts "faf_q"
		}
	}
}
namespace eval b {
	variable vb 7
	proc fb { } {	
		puts "fb"
		::a::c::fac
		::a::c::fbac		
	}
	namespace eval ::a::c {
		variable vca3 8
		#fbac
		proc fbac { } { 
			puts "fabc"
		}
		fbac
	}
	fb
}
namespace eval ::a::c {
	variable vac4 10
	proc feac { } {
		puts "feac"
	}	
}
namespace eval ::a::f::q::t {
	variable vafqt0 11
	proc fafqt { } {
		puts "fafqt"
	}
}


proc ::b::a::c::vca3 {} {
	puts "vca3"
}

puts $::a::c::vac
puts $::a::c::vac2
puts $::a::c::vac4
puts $::a::f::q::t::vafqt0
puts $::a::f::q::vafq
puts $::a::f::q::vafq2
puts $::a::f::vaf
puts $::a::va
puts $::b::a::c::vca3
puts $::b::vb
