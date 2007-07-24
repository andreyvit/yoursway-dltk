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
    def __init__( self ):
	pass
    def c(self):
	pass
class B( A ):
    def __init__( self ):
	A.__init__( self )
	pass
class D( B ):
    def __init__( self ):
	B.__init__( self )
	pass

a = A() 				; _utils.out( "a", a )
b = B()					; _utils.out( "b", b )
d = D()					; _utils.out( "d", d )