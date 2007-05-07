###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

class Foo
	
	@abx = 45
	
	@@clvar = 42

	def cool
		@abx = "ab"
		@@clvar = "be" 
	end
	
	@abx ## expr @abx => Fixnum%
	
	@@clvar ## expr @@clvar => Fixnum%
		

end

class Foo

	def wow(a,x)
		@abx ## expr @abx => String%
		@@clvar ## expr @@clvar => String%
	end

end