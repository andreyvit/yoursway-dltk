###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

class Abc
end

class XYZ
	
	include Abc

	class Wow
	end
end

## get Abc
## get XYZ
## get XYZ{Wow

class ::Global1
	class ::Global2
		class ::XYZ::Wow::Subwow
		end
	end
end

## get Global1
## get Global2
## get XYZ{Wow{Subwow


module MegaModule
	def megamethod
	end
end

## get MegaModule{megamethod