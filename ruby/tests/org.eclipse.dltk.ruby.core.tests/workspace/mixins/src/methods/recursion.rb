###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

class FooRecursion
	def foo(x)
		if x > 0
			foo(x-1)
		else
			42
		end
	end
	
	def bar
		foo ## expr foo => Fixnum
	end
end

