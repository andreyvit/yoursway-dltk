###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

class Foo66
	
	class <<self
		def cool(x,y,z)
			p "1"
		end
	end
	
	def self.cool2
		p "2"
		Foo66.new
	end
	
	def inst_meth
	end
	
end

Foo66.cool
Foo66.cool2
Foo66.