###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

import _utils
# number operations
a1 = 1					; _utils.out( "a1", a1 )
a2 = 2					; _utils.out( "a2", a2 )

b1 = a1 + a2				; _utils.out( "b1", b1 )
b2 = a1 * a2				; _utils.out( "b2", b2 )
b3 = a1 / a2				; _utils.out( "b3", b3 )
b4 = a1 ** a2				; _utils.out( "b4", b4 )
b5 = a1 // a2				; _utils.out( "b5", b5 )
b6 = a1 >> a2				; _utils.out( "b6", b6 )
b7 = a1 << a2				; _utils.out( "b7", b7 )
b8 = a1 ^ a2				; _utils.out( "b8", b8 )

# string operations

s1 = "A%s"				; _utils.out( "s1", s1 )
s2 = "B"				; _utils.out( "s2", s1 )
su1= u"A"				; _utils.out( "su1", su1 )

sb1 = s1 + s2				; _utils.out( "sb1", sb1 )
sb2 = s1 * 2				; _utils.out( "sb2", sb2 )
sb3 = s1 % s2				; _utils.out( "sb3", sb3 )
sb4 = s1 + su1				; _utils.out( "sb4", sb4 )
sb5 = su1 + s1				; _utils.out( "sb5", sb5 )
sb6 = s1 + s2 + su1			; _utils.out( "sb6", sb6 )

# list operations
l1 = [1,2,3]				; _utils.out( "l1", l1 )
l2 = []					; _utils.out( "l2", l2 )
l3 = [1,2,3,4,[1,2,3,[4]],5]		; _utils.out( "l3", l3 )

ls1 = l1 + l2				; _utils.out( "ls1", ls1 )
ls2 = l1 * 2				; _utils.out( "ls2", ls2 )
ls3 = l1 * a2				; _utils.out( "ls3", ls3 )
ls4 = l3[1:2]				; _utils.out( "ls4", ls4 )

# tuple operations
t1 = (1,2,3)				; _utils.out( "t1", t1 )
t2 = (1,2)				; _utils.out( "t2", t2 )
t3 = ()					; _utils.out( "t3", t3 )

ts1 = t1 + t2				; _utils.out( "ts1", ts1 )
ts2 = t1 * 2				; _utils.out( "ts2", ts2 )
ts3 = t1 * a2				; _utils.out( "ts3", ts3 )
ts4 = t1[1:2]				; _utils.out( "ts4", ts4 )

# dict operations

d1 = { "A":1, "B":2, "C":3 }		; _utils.out( "d1", d1 )
d2 = { "B":2, "C":3, "D":4 }		; _utils.out( "d2", d2 )

# boolean operations section.

bool0 = ( 1 == 2 )			; _utils.out( "bool0", bool0 )
bool1 = ( a1 == 2 )			; _utils.out( "bool1", bool1 )
bool2 = ( a1 == a2 )			; _utils.out( "bool2", bool2 )
bool3 = ( 1 <= 2 )			; _utils.out( "bool3", bool3 )
bool4 = ( 1 >= 2 )			; _utils.out( "bool4", bool4 )
bool5 = ( 1 != 2 )			; _utils.out( "bool5", bool5 )
bool6 = ( 1 == 2 )			; _utils.out( "bool6", bool6 )

bool7 = ( a1 <= 2 )			; _utils.out( "bool7", bool7 )
bool8 = ( a1 >= 2 )			; _utils.out( "bool8", bool8 )
bool9 = ( a1 != 2 )			; _utils.out( "bool9", bool9 )
bool10 = ( a1 == 2 )			; _utils.out( "bool10", bool10 )

bool11 = ( 1 < 2 )			; _utils.out( "bool11", bool11 )
bool12 = ( 2 > 2 )			; _utils.out( "bool12", bool12 )

bool13 = ( a1 < 2.0 )			; _utils.out( "bool13", bool13 )
bool14 = ( a2 > 2.0 )			; _utils.out( "bool14", bool14 )