###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

import _utils
import pkg.pkg2.module

# will import pkg.__init__.py
# will import pkg.pkg2.__init__.py
# will import pkg.pkg2.module

i1 = pkg.pkg2.module.module_val ;_utils.out( "i1", i1 )
i2 = pkg.pkg2.b			;_utils.out( "i2", i2 )
i3 = pkg.a			;_utils.out( "i3", i3 )
