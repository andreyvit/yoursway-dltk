###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

class A
	def functionA
		1
	end
end

class B < A
	def functionB
		2
	end
end

class C < B
	def functionC
		3
	end
end

class D < A
	def functionD
		4
	end
end