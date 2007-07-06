###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

## exit

class YAClass
end

a = YAClass.new

def a.singl1
	42
end

## get a%v{singl1

def (a.class).singl2
	43
end

## get YAClass{singl2

class << a
	class Internal
		def foo
		end
	end
end

## get a%v{Internal
## get a%v{Internal%{foo

def (::YAClass).doo
end

## get YAClass{doo