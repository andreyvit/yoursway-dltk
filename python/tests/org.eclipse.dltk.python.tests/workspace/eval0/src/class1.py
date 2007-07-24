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
    s1 = 20
    def __init__( self ):
	self.a1 = 20
	self.a2 = "A"    
	self.a3 = { "A":0 }
	self.a4 = ("A")
	self.a5 = ["A"]
class B( A ):
    s2 = "B"
    def __init__( self ):
	A.__init__( self )
	self.b1 = self.a1 + 1
	self.b2 = self.a2 + "B"
	self.b3 = self.a3
	self.b4 = self.a4 + ("B")
	self.b5 = self.a5 + ["B"]
class D( B ):
    s3 = "S3"
    def __init__( self ):
	B.__init__( self )
	self.d1 = self.b1 + 1
	self.d2 = self.b2 + "D"
	self.d3 = self.b3
	self.d4 = self.b4 + ("D")
	self.d5 = self.b5 + ["D"]
a = A()					; _utils.out( "a", a )
b = B()					; _utils.out( "b", b )
d = D()					; _utils.out( "d", d )

# A tests
c1 = a.s1				; _utils.out( "c1", c1 )
c2 = a.a1				; _utils.out( "c2", c2 )
c3 = a.a2				; _utils.out( "c3", c3 )
c4 = a.a3				; _utils.out( "c4", c4 )
c5 = a.a4				; _utils.out( "c5", c5 )
c6 = a.a5				; _utils.out( "c6", c6 )

# B Tests
e1 = b.s2				; _utils.out( "e1", e1 )
e2 = b.s1				; _utils.out( "e2", e2 )
e3 = b.b1				; _utils.out( "e3", e3 )
e4 = b.b2				; _utils.out( "e4", e4 )
e5 = b.b3				; _utils.out( "e5", e5 )
e6 = b.b4				; _utils.out( "e6", e6 )
e7 = b.b5				; _utils.out( "e7", e7 )
e8 = b.a1				; _utils.out( "e8", e8 )
e9 = b.a2				; _utils.out( "e9", e9 )
e10 = b.a3				; _utils.out( "e10", e10 )
e11 = b.a4				; _utils.out( "e11", e11 )
e12 = b.a5				; _utils.out( "e12", e12 )

# D tests
f0 = d.s3				; _utils.out( "f0", f0 )
f1 = d.s2				; _utils.out( "f1", f1 )
f2 = d.s1				; _utils.out( "f2", f2 )
f3 = d.b1				; _utils.out( "f3", f3 )
f4 = d.b2				; _utils.out( "f4", f4 )
f5 = d.b3				; _utils.out( "f5", f5 )
f6 = d.b4				; _utils.out( "f6", f6 )
f7 = d.b5				; _utils.out( "f7", f7 )
f8 = d.a1				; _utils.out( "f8", f8 )
f9 = d.a2				; _utils.out( "f9", f9 )
f10 = d.a3				; _utils.out( "f10", f10 )
f11 = d.a4				; _utils.out( "f11", f11 )
f12 = d.a5				; _utils.out( "f12", f12 )
f13 = d.d1				; _utils.out( "f13", f13 )
f14 = d.d2				; _utils.out( "f14", f14 )
f15 = d.d3				; _utils.out( "f15", f15 )
f16 = d.d4				; _utils.out( "f16", f16 )
f17 = d.d5				; _utils.out( "f17", f17 )
