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
		self ## expr self => BozSelfSingletons::Bar
		
		def foo
			self ## expr self => BozSelfSingletons::Bar.new
		end
		
		def self.foo
			self ## expr self => BozSelfSingletons::Bar
		end
		
		def Bar.foo
			self ## expr self => BozSelfSingletons::Bar
		end
	end
end
