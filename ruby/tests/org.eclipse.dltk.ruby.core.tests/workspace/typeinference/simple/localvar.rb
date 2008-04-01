###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################


class Bar
	def func
		test = biz(15)
		boz(test)
	end
	
	def boz(foo)
		foo
	end
	
	def biz(foo)
		foo
	end

end

Bar.new.biz(0) ## expr Bar.new.biz(0) => Fixnum%
Bar.new.boz(42) ## expr Bar.new.boz(42) => Fixnum%
Bar.new.func ## expr Bar.new.func => Fixnum%
