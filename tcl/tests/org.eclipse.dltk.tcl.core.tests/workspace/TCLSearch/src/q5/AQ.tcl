###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

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
