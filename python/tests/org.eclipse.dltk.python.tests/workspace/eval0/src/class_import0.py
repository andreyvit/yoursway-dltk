###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

import _utils

from pkg.class0 import *
class test1_0(test1):
    def __init__( self ):
	test1.__init__( self )
	self.test1_0_a0 = self.test1_a0
val_test1_0_class = test1_0( )
ta0 = val_test1_0_class.test1_0_a0			; _utils.out( "ta0", ta0 )