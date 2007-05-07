###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

class Args3

	def wow
		Usable.new.work (42, Args2Class.new)
	end

end


class Usable
	def work(x,y)
		xxx
		x ## expr x => Fixnum%
		xxx
		y ## expr y => Arg2Class% 
	end
end

class Arg2Class

	def use1
	
		z = Usable.new
		z.work (34, self)
	
	end

end

