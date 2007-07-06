###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

class SomeExt
	def SomeExt.foo
	end
end

## get SomeExt{foo

class MAbc
end

def MAbc.meta1
end

def MAbc::meta2
end

## get MAbc{meta1
## get MAbc{meta2
