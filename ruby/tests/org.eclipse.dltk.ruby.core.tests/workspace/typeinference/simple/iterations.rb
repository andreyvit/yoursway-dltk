###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

class Foo
	
	def dining_philosopher_2
		dining_philosopher
	end
	
	def dining_philosopher
		ultimate_answer
	end

	def ultimate_answer
		42
	end

end

Foo.new.ultimate_answer ## expr Foo.new.ultimate_answer => Fixnum%
Foo.new.dining_philosopher ## expr Foo.new.dining_philosopher => Fixnum%
Foo.new.dining_philosopher_2 ## expr Foo.new.dining_philosopher_2 => Fixnum% 

