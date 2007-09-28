###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

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

::a::c::fac
::a::fa
::b::fb
::a::c::fbac
::a::c::feac
::a::f::q::faf_q
::a::f::q::fafq
::a::f::q::t::fafqt
