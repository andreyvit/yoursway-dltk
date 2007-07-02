###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################


class FooNew

	def ultimate_answer
		42
	end
	
	def dining_philosopher
		ultimate_answer
	end

end

class BarNew
	def boz
		FooNew.new
	end
	
	def func
		boz.dining_philosopher
	end
end

FooNew.new.ultimate_answer ## expr FooNew.new.ultimate_answer => Fixnum%
FooNew.new.dining_philosopher ## expr FooNew.new.dining_philosopher => Fixnum%
BarNew.new.boz ## expr BarNew.new.boz => FooNew%
BarNew.new.func ## expr BarNew.new.func => Fixnum%

