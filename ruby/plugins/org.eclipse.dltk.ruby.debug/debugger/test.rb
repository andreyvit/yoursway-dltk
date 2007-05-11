###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

#t1 = Thread.new {
#    10.times {  |i|
#        puts i
#        sleep 0.3
#    }
#}

#t2 = Thread.new {
#    10.times {  |i|
#        puts i
#        sleep 0.1
#    }
#}


puts "xxxx"

class Tank
    def initialize
        @a = 'test'
        @b = 2354
    end

    def test(x)
        puts x
    end
end

tank = Tank.new
tank.test("a")
x = 45
y = 345
z = x + y


x = 34
x = 5
x = 45
z = z
x = x


#t1.join
#t2.join
puts 'Hello, World!!!'
puts 'Morda :)'
