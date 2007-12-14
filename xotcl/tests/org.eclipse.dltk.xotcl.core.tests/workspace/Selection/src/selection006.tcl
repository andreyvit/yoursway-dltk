Class C0
C0 instproc foo {} {return "::C0::foo"}
C0 proc bar {} {return "::C0::bar"}
C3 create c3
c3 foo

namespace eval n1 {
	Class C0
	C0 instproc foo {} {return "::n1::C0::foo"}
	C0 proc bar {} {return "::n1::C0::bar"}
	C0 create c0
	c0 foo
}

namespace eval n1::n2 {
	Class C1 -superclass C0
	Class C2 -superclass ::n1::C0
	Class C3 -superclass {::n1::C0 C0}
}

n1::n2::C1 create c1
c1 foo

n1::n2::C2 create c2
c2 foo

C0 bar
::n1::C0 bar

