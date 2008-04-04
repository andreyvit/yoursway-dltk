###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

class AA
	def functionA
		1
	end
end

class BB < AA
	def functionB
		2
	end
end

class CC < BB
	def functionC
		3
	end
end

class DD < AA
	def functionD
		4
	end
end