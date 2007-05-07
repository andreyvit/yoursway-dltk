###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

class Foo
	def doo
		creator.cool_method
	end
	
	def creator
		Bar.new
	end
	
end

class Bar
	
	def cool_method
		42
	end
	
end

class Baz
	def bobobo
		Bar.new.cool_method
		f = Foo.new
		f.doo
	end
end

## meth Foo.creator => Bar
## meth Baz.bobobo => Fixnum