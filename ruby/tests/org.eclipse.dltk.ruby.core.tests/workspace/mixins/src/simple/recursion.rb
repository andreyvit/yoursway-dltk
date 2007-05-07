###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

class Foo
	def foo(x)
		if x > 0
			foo(x-1)
		else
			42
		end
	end
end

## meth Foo.foo => Fixnum
