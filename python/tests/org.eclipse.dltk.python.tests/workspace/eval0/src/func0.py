###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

import _utils
def a1():
	return 10
def a2():
	return "A"
def a3():
	return [50]
def a4():
	return (1)

_a1 = a1()				;_utils.out( "_a1", _a1 )
_a2 = a2()				;_utils.out( "_a2", _a2 )
_a3 = a3()				;_utils.out( "_a3", _a3 )
_a4 = a4()				;_utils.out( "_a4", _a4 )

# complex

def c1():
	return a1()
def c2():
	return a1() + 2
def c3():
	return a2()
def c4():
	return a3()
def c5():
	return a4

_c1 = c1()				;_utils.out( "_c1", _c1 )
_c2 = c2()				;_utils.out( "_c2", _c2 )
_c3 = c3()				;_utils.out( "_c3", _c3 )
_c4 = c4()				;_utils.out( "_c4", _c4 )
_c5 = c5()				;_utils.out( "_c5", _c5 )


# with if's
def if0():
	if 1:
		return a1()
	else:
		return a2()
def if1():
	if a1() == 10:
		return a2()
	elif a2() == "A":
		return a3()
_if0 = if0()				;_utils.out( "_if0", _if0 )
_if1 = if1()				;_utils.out( "_if1", _if1 )
# subfunctions
def sf0():
	def sf00():
		return 20
	def sf01():
		return "A"
	r = sf00()
	if 2:
		r = sf01()
	return r
_sf0 = sf0()				;_utils.out( "_sf0", _sf0 )
# Argumented functions
def a1(a = 10 ):
	return a + 20
def a2( a, b, c, d, q ):
	if a:
		return b
	elif c:
		return d
	else:
		return q

_ar1_0 = a1()				;_utils.out( "_ar1_0", _ar1_0 )
_ar1_1 = a1( 1 )			;_utils.out( "_ar1_1", _ar1_1 )
try:
	_a1_2 = a1( "A" )
except:
	print "_a1_2 error_defined"

_ar2_0 = a2( 1, 1, 0, "A", 20 )		;_utils.out( "_ar2_0", _ar2_0 )
_ar2_1 = a2( 0, 2, 1, ["A"], "B" )	;_utils.out( "_ar2_1", _ar2_1 )
_ar2_2 = a2( 1, 3, 0, ("A"), 56 )	;_utils.out( "_ar2_2", _ar2_2 )
_ar2_3 = a2( 1, 4, 1, {"A":1}, "B" )	;_utils.out( "_ar2_3", _ar2_3 )
_ar2_4 = a2( 0, 5, 0, 0, 0 )		;_utils.out( "_ar2_4", _ar2_4 )
_ar2_5 = a2( 1, 6, 1, 5, "A" )		;_utils.out( "_ar2_5", _ar2_5 )
