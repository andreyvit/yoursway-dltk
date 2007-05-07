###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################


class BozSelfSingletons
	class Bar
		xxx
		self ## expr self => BozSelfSingletons{Bar
		
		def foo
			xxx
			self ## expr self => BozSelfSingletons{Bar%
		end
		
		def self.foo
			xxxx
			self ## expr self => BozSelfSingletons{Bar
		end
		
		def Bar.foo
			xxx
			self ## expr self => BozSelfSingletons{Bar
		end
	end
end
