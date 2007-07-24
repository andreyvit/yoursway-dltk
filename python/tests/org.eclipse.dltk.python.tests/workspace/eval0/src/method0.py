###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

import _utils
class A:
	def a1(self):
		return 1
	def a2(self):
		return "S"
a = A()

c1 = a.a1()		; _utils.out( "c1", c1 )
c2 = a.a2()		; _utils.out( "c2", c2 )