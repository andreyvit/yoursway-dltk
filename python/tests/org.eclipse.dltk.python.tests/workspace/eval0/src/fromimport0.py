###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

import _utils
from _simple import *

i1 = a						;_utils.out( "i1", i1 )
i2 = b						;_utils.out( "i2", i2 )
i3 = c						;_utils.out( "i3", i3 )
i4 = d						;_utils.out( "i4", i4 )
i5 = e						;_utils.out( "i5", i5 )

class A:
    from _simple import a,b,e

i5a = A()					;_utils.out( "i5a", i5a )
i6 = A.a					;_utils.out( "i6", i6 )
i7 = A.b					;_utils.out( "i7", i7 )
try:
	i8 = A.c # unknown should be.
except:
	print "i8 unknown"
i9 = A.e					;_utils.out( "i9", i9 )
try:
	i10 = A.d # unknown should be
except:
	print "i10 unknown"

i11 = i5a.a					;_utils.out( "i11", i11 )
i12 = i5a.b					;_utils.out( "i12", i12 )
try:
	i13 = i5a.c					;_utils.out( "i13", i13 )
except:
	print "i13 unknown"
try:
	i14 = i5a.d					;_utils.out( "i14", i14 )
except:
	print "i14 unknown"
i15 = i5a.e					;_utils.out( "i15", i15 )

from pkg import a
i16 = a						;_utils.out( "i16", i16 )