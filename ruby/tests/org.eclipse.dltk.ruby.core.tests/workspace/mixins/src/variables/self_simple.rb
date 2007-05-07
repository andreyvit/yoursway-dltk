###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################


class BozSelf
	class Bar
		self ## expr self => BozSelf::Bar
		
		def foo
			self ## expr self => BozSelf::Bar.new
		end
	end

	def foo
		self ## expr self => BozSelf.new
	end

	self ## expr self => BozSelf
end
