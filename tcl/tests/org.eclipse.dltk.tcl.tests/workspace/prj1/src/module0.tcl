###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

proc foo0 { a b c d } {
	proc qwe {} {}
}
namespace eval namespace1 {
	proc a {} {}
	namespace eval inner1 {
		proc b {} {}
	}
}

namespace eval namespace2 {
	proc c {} {}
	namespace eval inner2 {
		proc d {} {}
		namespace eval inner4 {
			proc e {} {}
		}
	}
}