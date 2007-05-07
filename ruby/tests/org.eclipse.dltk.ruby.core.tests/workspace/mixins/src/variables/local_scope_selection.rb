###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################


test = 3
class Boz
	test = 7
	class Bar
		test = 18
		def func
			test = "Abc"
			test = "Def"
			test ## localvar test => Str
		end
		test ## localvar test => Fixnum
	end
end
