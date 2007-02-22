package provide q5

namespace eval I {
	proc k { arg } {
	}
	::I::k 1
	k 2
	::m
}

namespace eval I2 {
	proc k { arg } {
	}
	::I::k2 k2_1
	k2 k2_2
	::m
}

proc m { } {
	::I::k 3
}
m
I::k 4
::I::k 5
