namespace eval a {
	#This is fa comment
	proc fa { } {
		puts "fa"
	}
	namespace eval c {
		proc fac { } {
			puts "fac"
		}
	}
	namespace eval f {
		proc faf {} {
			puts "faf"
		}
		namespace eval q {
			proc fafq {} {
				puts "fafq"
			}
		}
	}
	namespace eval f::q {
		proc faf_q { } { 
			puts "faf_q"
		}
	}
}
namespace eval b {
	proc fb { } {	
		puts "fb"
		::a::c::fac
		::a::c::fbac
	}
	namespace eval ::a::c {		
		#fbac
		proc fbac { } { 
			puts "fabc"
		}
		fbac
	}
	fb
}
namespace eval ::a::c {
	proc feac { } {
		puts "feac"
	}	
}
namespace eval ::a::f::q::t {
	proc fafqt { } {
		puts "fafqt"
	}
}

#1
::a::c::f 
#2
::a::c::fa
#3
::a::f
#4
::b::fb

