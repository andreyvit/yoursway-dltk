###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

namespace eval mynamespace {
	proc a1 { aa1 } {
		return $aa1
	}

	proc a2 { aa21 aa22 aa23 } {
		return $aa23
	}
}

namespace eval mynamespace2 {
	proc a1 { aa1 } {
		return $aa1
	}

	proc a2 { aa21 aa22 aa23 } {
		return $aa23
	}
}	

namespace eval mynamespace3 {
	proc a1 { aa1 } {
		return $aa1
	}

	proc a2 { aa21 aa22 aa23 } {
		return $aa23
	}
}

namespace eval mynamespace2 {
	proc a3 { aa1 } {
		return $aa1
	}

	proc a4 { aa21 aa22 aa23 } {
		return $aa23
	}
}