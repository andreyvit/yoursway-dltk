###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

def FooClass
	
	@a = 1
	@@a = 3
		

	def foo1
		42	   
	end
	
	def cool?
		hot!
	end
	
	def hot!		
		puts "really hot" * 42
		a = 4
	    a **= 34
	end
	
end

def foo1
	c = 5
end

foo1
c = 34
$a = 45
a = "#{$a}"
b = "#{c}"
b = "#{if nil then 5 else 45 end}"
print "M'kay?"

4242.times do
	puts "M'kay?"
end

df = 5.*(4)

