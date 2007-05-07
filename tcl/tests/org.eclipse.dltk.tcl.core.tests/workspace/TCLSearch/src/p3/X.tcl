###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

proc src_p3_X_function { arg1 arg2 arg3 } {
}
proc ::global2::namespace2::function { arg1 arg2 arg3 } {
}
namespace eval X {
	set v1 1
	variable v2 2 v3 3 v4 4
	proc src_p3_X_X_function { arg1 arg2 arg3 } { }
	proc foo { } {
	}
	namespace eval T1 {
		variable v5 
		proc src_p3_X_X_T1_function { arg1 arg2 arg3 } { }
		namespace eval T1 {
			variable v6 6
			proc src_p3_X_X_T1_T1_function { arg1 arg2 arg3 } { }
		}
	}
}

namespace eval Y {
	set v7 7
	proc src_p3_X_Y_function { arg1 arg2 arg3 } { }
	proc foo { } {
		set v8 8
	}
	namespace eval T2 {
		set v9 9
		namespace eval T3 { 
			set v10 10
			namespace eval T4 {
				proc src_p3_X_Y_T2_T3_T4_function { arg1 arg2 arg3 } { }
			}
		}
	}
}

namespace eval Z {
	proc foo { } {
	}
	namespace eval T2 {
		namespace eval T3 {
			namespace eval T4 {
				variable v11 11
			}
		}
	}
}
